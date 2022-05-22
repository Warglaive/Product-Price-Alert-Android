package com.ProductPriceAlert;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize XML elements
        this.registerButton = findViewById(R.id.registerButton);
        this.loginButton = findViewById(R.id.loginButton);

        loginOrRegister(findViewById(R.id.mainActivity).getRootView());
    }

    /**
     * Called when the user taps the Send button
     */
    public void loginOrRegister(View view) {
        //Build intent so the 2 activities can bind
        //view.GetContext() can be replaced by "MainActivity.class" or just "this" if current activity is passed
        Intent registerIntent = new Intent(view.getContext(), RegisterUserActivity.class);
        Intent loginIntent = new Intent(view.getContext(), LoginUserActivity.class);
        //Intent intent = new Intent(view.getContext(), BrowseProducts.class);
        // Do something in response to button
        //TODO: Add login view connection
        this.registerButton.setOnClickListener(v -> startActivity(registerIntent));
        this.loginButton.setOnClickListener(v -> startActivity(loginIntent));

        //this.registerButton.setOnClickListener(view1 -> startActivity(intent));
        //this.loginButton.setOnClickListener(view1 -> startActivity(new Intent(view.getContext(), BrowseProducts.class)));

    }
}