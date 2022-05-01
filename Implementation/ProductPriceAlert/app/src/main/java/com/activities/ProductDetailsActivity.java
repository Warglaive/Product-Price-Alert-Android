package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;

public class ProductDetailsActivity extends AppCompatActivity {
    private TextView name;
    private TextView price;
    private TextView description;
    private ImageView image;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
    }

    public void backToBrowse(View view) {
        //Intent intent = new Intent(view.getContext(), BrowseProductsActivity.class);
        //this.button.setOnClickListener(view1 -> startActivity(intent));
    }
}
