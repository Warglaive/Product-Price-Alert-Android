package com.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.ProductPriceAlert.R;
import com.google.gson.Gson;
import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsActivityInterface {
    private UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);
        ProductStorageService service = new ProductStorageService();
        Bundle extras = getIntent().getExtras();
        String productName = extras.getString("key");

        TextView name = (TextView) findViewById(R.id.nameGet);
        TextView price = (TextView) findViewById(R.id.priceGet);
        TextView description = (TextView) findViewById(R.id.descriptionGet);
        ImageView image = findViewById(R.id.image);
        Button button = findViewById(R.id.button);
        Button edit = findViewById(R.id.edit);
        ArrayList<ProductData> list = new ArrayList<ProductData>();

        service.getAllProducts(new ResponseWait() {
            @Override
            public void responseWaitArray(List response) throws IOException {
                for (Object t : response) {
                    ProductData data = (ProductData) t;
                    list.add(data);
                }

                ProductData product = new ProductData();
                for (ProductData p : list) {
                    if (p.getName().equals(productName)) {
                        product = p;
                        break;
                    }
                }

                name.setText(product.getName());
                price.setText(String.valueOf(product.getPrice()));

                if(product.hasDescription()) {
                    description.setText(product.getDescription());
                }
                if(product.hasImage()) {
                    URL url = new URL(product.getImage());
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection() .getInputStream());
                    image.setImageBitmap(bitmap);
                }
            }

            @Override
            public void responseWaitSingle(UserData userData) {

            }
        });

        editProduct(edit);
        backToBrowse(button);
    }

    @Override
    public void backToBrowse(Button button) {
        Intent intent = new Intent(this, BrowseProducts.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        button.setOnClickListener(view1 -> startActivity(intent));
    }

    @Override
    public void editProduct(Button button) {

    }

    @Override
    public void purchaseProduct(Button button) {

    }
}
