package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.util.ArrayList;
import java.util.List;

public class FilterProductsActivity extends AppCompatActivity {
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
        Intent intent = new Intent(this, ShowFilteredProductsActivity.class);
        intent.putExtra("key", this.search);
        filter.setOnClickListener(view1 -> startActivity(intent));
    }

    public void backToBrowse(Button backp){
        Intent intent = new Intent(this, BrowseProducts.class);
        backp.setOnClickListener(view1 -> startActivity(intent));
    }
}
