package com.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ProductPriceAlert.R;
import com.google.gson.Gson;
import com.models.User;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class EditPriceActivity extends AppCompatActivity {
    private UserData user;
    private ProductData productData;
    private EditText newPriceField;
    private ProductStorageService productStorageService;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_price);

        this.productStorageService = new ProductStorageService();
        TextView viewCurrent = findViewById(R.id.currentPriceText);
        TextView fieldCurrent = findViewById(R.id.currentPriceField);
        this.newPriceField = findViewById(R.id.priceField);
        TextView newPriceText = findViewById(R.id.newPriceText);
        Button back = findViewById(R.id.backEdit);
        TextView textView = findViewById(R.id.textView);
        Button edit = findViewById(R.id.editButton);

        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);
        this.productData = gson.fromJson(getIntent().getStringExtra("productDataKey"), ProductData.class);
        fieldCurrent.setText(String.valueOf(productData.getPrice()));


        back.setOnClickListener(this::backToDetails);
        edit.setOnClickListener(this::editPrice);
        editPossible(this);
    }

    public void backToDetails(View view){
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        intent.putExtra("key", productData.getName());
        startActivity(intent);
    }

    public void editPrice(View view){
        String price = this.newPriceField.getText().toString();
        double newPrice = Double.parseDouble(price);
        productData.setPrice(newPrice);
        this.productStorageService.updatePrice(this.productData.getID(), productData, new ResponseWait() {
            @Override
            public void responseWaitArray(List response) throws MalformedURLException, IOException {

            }

            @Override
            public void responseWaitSingle(ProductData productData) {

            }

            @Override
            public void responseWaitSingle(UserData userData) {

            }
        });

        Intent intent = new Intent(this, ProductDetailsActivity.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        intent.putExtra("key", productData.getName());
        startActivity(intent);
    }

    private void editPossible(Context context) {
        CharSequence text = "You can now edit the price of your chosen product!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
