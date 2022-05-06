package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDetailsActivity extends AppCompatActivity {
    private ProductStorageService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        this.service = new ProductStorageService();

        Bundle extras = getIntent().getExtras();
        String productName = extras.getString("key");
        ProductData product = new ProductData();


        TextView name = (TextView) findViewById(R.id.nameGet);
        TextView price = (TextView) findViewById(R.id.priceGet);
        TextView description = (TextView) findViewById(R.id.descriptionGet);
        ImageView image = findViewById(R.id.image);
        Button button = findViewById(R.id.button);
        ArrayList<ProductData> list = new ArrayList<ProductData>();

        ArrayList<ArrayList<ProductData>> listt = new ArrayList<>();
        listt.add(list);

        this.service.getAllProducts(response -> {
            for (Object t : response) {
                ProductData data = (ProductData) t;
                listt.get(0).add(data);
            }
        });

        for(ProductData p : list){
            if(p.getName().equals(productName)){
                product = p;
                break;
            }
        }

        name.setText(product.getName());
        price.setText(String.valueOf(product.getPrice()));
        description.setText(product.getDescription());

        backToBrowse(button);
    }

    public void backToBrowse(Button button) {
        Intent intent = new Intent(this, BrowseProducts.class);
        button.setOnClickListener(view1 -> startActivity(intent));
    }
}
