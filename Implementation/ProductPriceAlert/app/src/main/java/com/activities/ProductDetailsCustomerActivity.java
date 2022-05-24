package com.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ProductPriceAlert.R;
import com.google.gson.Gson;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsCustomerActivity extends AppCompatActivity implements ProductDetailsActivityInterface {
    private UserData user;
    Context currentContext;
    TextView location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_customer);

        currentContext = this;
        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);
        ProductStorageService service = new ProductStorageService();
        Bundle extras = getIntent().getExtras();
        String productName = extras.getString("key");


        TextView name = (TextView) findViewById(R.id.nameGetC);
        TextView price = (TextView) findViewById(R.id.priceGetC);
        TextView description = (TextView) findViewById(R.id.descriptionGetC);
        location = (TextView) findViewById(R.id.locationGetC);
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
                if(product.hasLocation()){
                    location.setText(product.getLocation());
                }

                if(product.hasImage()) {
                    URL url = new URL(product.getImage());
                    Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection() .getInputStream());
                    image.setImageBitmap(bitmap);
                }

                System.out.println("Does product have location? - " + product.hasLocation());
            }

            @Override
            public void responseWaitSingle(ProductData productData) {

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
        Intent intent = new Intent(this, BrowseProductsCustomerActivity.class);
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
    public void openMap(View view){
        Intent intent = new Intent(currentContext, MapsActivityCurrentPlace.class);
        intent.putExtra("location", location.getText().toString());
        startActivity(intent);
    }
}
