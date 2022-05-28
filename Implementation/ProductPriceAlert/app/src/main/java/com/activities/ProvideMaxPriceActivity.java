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
import com.services.ProductStorageService;
import com.services.UserStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class ProvideMaxPriceActivity extends AppCompatActivity {

    private UserData user;
    private ProductData productData;
    private EditText maxPriceField;
    private ProductStorageService productStorageService;
    private UserStorageService userStorageService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_maximum_price);

        this.productStorageService = new ProductStorageService();
        this.userStorageService = new UserStorageService();
        TextView viewCurrent = findViewById(R.id.currentPriceTextMax);
        TextView fieldCurrent = findViewById(R.id.currentPriceFieldMax);
        this.maxPriceField = findViewById(R.id.maxPriceField);
        TextView maxPriceText = findViewById(R.id.MaxPriceText);
        Button back = findViewById(R.id.backMax);
        TextView textView = findViewById(R.id.textProvideMax);
        Button edit = findViewById(R.id.maxButton);

        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);
        this.productData = gson.fromJson(getIntent().getStringExtra("productDataKey"), ProductData.class);
        fieldCurrent.setText(String.valueOf(productData.getPrice()));


        back.setOnClickListener(this::backToDetails);
        edit.setOnClickListener(this::maxPrice);
        maxPossible(this);
    }

    public void backToDetails(View view){
        Intent intent = new Intent(this, ProductDetailsCustomerActivity.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        intent.putExtra("key", productData.getName());
        startActivity(intent);
    }

    public void maxPrice(View view){
        String price = this.maxPriceField.getText().toString();
        double newPrice = Double.parseDouble(price);
        user.setMax(price);
        user.setProduct(productData);
        this.userStorageService.maxPrice(this.user.getID(), user, new ResponseWait() {
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

        Intent intent = new Intent(this, ProductDetailsCustomerActivity.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        intent.putExtra("key", productData.getName());
        startActivity(intent);
    }

    private void maxPossible(Context context) {
        CharSequence text = "You can now provide maximum price of your chosen product!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
