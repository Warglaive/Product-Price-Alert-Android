package com.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsCustomerActivity extends AppCompatActivity implements ProductDetailsActivityInterface {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_customer);

        ProductStorageService service = new ProductStorageService();
        Bundle extras = getIntent().getExtras();
        String productName = extras.getString("key");


        TextView name = (TextView) findViewById(R.id.nameGetC);
        TextView price = (TextView) findViewById(R.id.priceGetC);
        TextView description = (TextView) findViewById(R.id.descriptionGetC);
        ImageView image = findViewById(R.id.imageC);
        Button button = findViewById(R.id.buttonC);
        Button purchase = findViewById(R.id.purchase);
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

        backToBrowse(button);
        purchaseProduct(purchase);
    }

    @Override
    public void backToBrowse(Button button) {
        Intent intent = new Intent(this, BrowseProducts.class);
        button.setOnClickListener(view1 -> startActivity(intent));
    }

    @Override
    public void editProduct(Button button) {

    }

    @Override
    public void purchaseProduct(Button button) {

    }
}
