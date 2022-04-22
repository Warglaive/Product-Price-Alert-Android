package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.productpricealert.R;
import com.services.UserStorageService;
import com.vogella.retrofitgerrit.interfaces.GerritAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String baseUrl = "http://192.168.0.116:3000/";
    public Retrofit retrofit;
    public GerritAPI gerritAPI;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize XML elements
        this.registerButton = findViewById(R.id.registerButton);
        //
        Gson gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.gerritAPI = retrofit.create(GerritAPI.class);
        loginOrRegister(findViewById(R.id.mainActivity).getRootView());
    }

    /**
     * Called when the user taps the Send button
     */
    public void loginOrRegister(View view) {
        //Build intent so the 2 activities can bind
        //view.GetContext() can be replaced by "MainActivity.class" or just "this" if current activity is passed
        Intent intent = new Intent(view.getContext(), RegisterUserActivity.class);
/*
        Intent intent = new Intent(view.getContext(), LoginUserActivity.class);
*/

        // Do something in response to button
        //TODO: Add login view connection
        this.registerButton.setOnClickListener(view1 -> startActivity(intent));
    }
}