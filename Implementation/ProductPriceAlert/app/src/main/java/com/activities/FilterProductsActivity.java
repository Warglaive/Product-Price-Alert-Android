package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;

public class FilterProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_products);
        Button filterfil = findViewById(R.id.filterfil);
        TextView enter = findViewById(R.id.enter);
        EditText input = findViewById(R.id.input);
        Button backp = findViewById(R.id.backp);

        filter(filterfil);
        backToBrowse(backp);
    }

    public void filter(Button filter){

    }

    public void backToBrowse(Button backp){
        Intent intent = new Intent(this, BrowseProducts.class);
        backp.setOnClickListener(view1 -> startActivity(intent));
    }
}
