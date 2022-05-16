package com.activities;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ProductStorageService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        this.service = new ProductStorageService();

        Bundle extras = getIntent().getExtras();
        String productName = extras.getString("key");


        TextView name = (TextView) findViewById(R.id.nameGet);
        TextView price = (TextView) findViewById(R.id.priceGet);
        TextView description = (TextView) findViewById(R.id.descriptionGet);
        ImageView image = findViewById(R.id.image);
        Button button = findViewById(R.id.button);
        ArrayList<ProductData> list = new ArrayList<ProductData>();

        this.service.getAllProducts(new ResponseWait() {
            @Override
            public void responseWaitArray(List response) {
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
                description.setText(product.getDescription());
            }

            @Override
            public void responseWaitSingle(UserData userData) {

            }
        });

        backToBrowse(button);
    }


    public void backToBrowse(Button button) {
        Intent intent = new Intent(this, BrowseProducts.class);
        button.setOnClickListener(view1 -> startActivity(intent));
    }
}
