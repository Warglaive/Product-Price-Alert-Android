package com.ProductPriceAlert;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class FilterProductsActivity extends AppCompatActivity {
    private EditText input;
    private TextView enter;
    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_products);

        Button filterfil = findViewById(R.id.filterfil);
        Button backp = findViewById(R.id.backp);

        this.enter = findViewById(R.id.enter);
        this.input = findViewById(R.id.input);

        filterfil.setOnClickListener(this::filter);
        backToBrowse(backp);
    }

    public void filter(View view){
        this.search = this.input.getText().toString();
        Intent intent = new Intent(this, ShowFilteredProductsActivity.class);
        intent.putExtra("key", this.search);
        startActivity(intent);

    }

    public void backToBrowse(Button backp){
        Intent intent = new Intent(this, BrowseProducts.class);
        backp.setOnClickListener(view1 -> startActivity(intent));
    }
}
