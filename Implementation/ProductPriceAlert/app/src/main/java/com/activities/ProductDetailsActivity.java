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

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private TextView name;
    private TextView price;
    private TextView description;
    private ImageView image;
    private Button button;
    private ProductData product;
    private ProductStorageService service;
    private ArrayList<ProductData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Bundle extras = getIntent().getExtras();
        this.product = extras.getParcelable("key");


        this.name = findViewById(R.id.nameGet);
        this.price = findViewById(R.id.priceGet);
        this.description = findViewById(R.id.descriptionGet);
        this.image = findViewById(R.id.image);
        this.button = findViewById(R.id.button);
        this.list = new ArrayList<ProductData>();

/*        ResponseWait response = response1 -> {
            for (Object t : response1) {
                ProductData data = (ProductData) t;
                this.list.add(data);
            }
        };*/

        //this.service.getAllProducts(response);

        this.name.setText(this.product.getName());
        this.price.setText(String.valueOf(this.product.getPrice()));
        this.description.setText(this.product.getDescription());

        backToBrowse(this.button);
    }

    public void backToBrowse(Button button) {
        Intent intent = new Intent(this, BrowseProducts.class);
        button.setOnClickListener(view1 -> startActivity(intent));
    }
}
