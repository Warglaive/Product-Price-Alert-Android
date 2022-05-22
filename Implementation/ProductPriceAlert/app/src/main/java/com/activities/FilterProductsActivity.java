package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.UserData;

public class FilterProductsActivity extends AppCompatActivity {
    private EditText input;
    private TextView enter;
    private String search;
    private UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_products);

        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);

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

        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);

        startActivity(intent);
    }

    public void backToBrowse(Button backp){
        Intent intent;

        if(this.user.getRole().equals("Product Manager")){
            intent = new Intent(this, BrowseProducts.class);
            Gson gson = new Gson();
            String userDataJSON = gson.toJson(user);
            intent.putExtra("userDataKey", userDataJSON);
        } else {
            intent = new Intent(this, BrowseProductsCustomerActivity.class);
            Gson gson = new Gson();
            String userDataJSON = gson.toJson(user);
            intent.putExtra("userDataKey", userDataJSON);
        }

        backp.setOnClickListener(view1 -> startActivity(intent));
    }
}
