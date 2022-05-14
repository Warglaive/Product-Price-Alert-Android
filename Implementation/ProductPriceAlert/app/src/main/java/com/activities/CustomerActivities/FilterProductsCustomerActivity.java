package com.activities.CustomerActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.activities.ProductManagerActivities.BrowseProductsProductManagerActivity;
import com.productpricealert.R;
import com.services.ProductStorageService;

public class FilterProductsCustomerActivity extends AppCompatActivity {
    private EditText input;
    private TextView enter;
    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_products);

        ProductStorageService service = new ProductStorageService();
        Button filterfil = findViewById(R.id.filterfil);
        Button backp = findViewById(R.id.backp);

        this.enter = findViewById(R.id.enter);
        this.input = findViewById(R.id.input);
        this.search = input.getText().toString();

        filter(filterfil);
        backToBrowse(backp);
    }

    public void filter(Button filter){
        Intent intent = new Intent(this, ShowFilteredProductsCustomerActivity.class);
        intent.putExtra("key", this.search);
        filter.setOnClickListener(view1 -> startActivity(intent));
    }

    public void backToBrowse(Button backp){
        Intent intent = new Intent(this, BrowseProductsProductManagerActivity.class);
        backp.setOnClickListener(view1 -> startActivity(intent));
    }
}
