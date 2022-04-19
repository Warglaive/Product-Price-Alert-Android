package com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.productpricealert.R;
import com.vogella.retrofitgerrit.interfaces.GerritAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    static final String baseUrl = "http://192.168.0.116:3000/";
    public Retrofit retrofit;
    public GerritAPI gerritAPI;

    Button registerButton;

    //Tim
    Button browseProductsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize XML elements
        this.registerButton = findViewById(R.id.registerButton);
        // TODO it should be connected to a different button
        this.browseProductsButton = findViewById(R.id.loginButton);
        //
        Gson gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.gerritAPI = retrofit.create(GerritAPI.class);
        //Used for test purposes, gonna refactor later, works though via LB4!
/*        Call<List<UserData>> callUser = this.gerritAPI.getUsers();
        callUser.enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                System.out.println("Reached on response!");
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                System.out.println("Reached on Failure!");
                t.printStackTrace();
            }
        });*/
    }

    /**
     * Called when the user taps the Send button
     */
    public void registerUser(View view) {
        //Build intent so the 2 activities can bind
        Intent intent = new Intent(this, BrowseProducts.class);
        // Do something in response to button
        this.registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    public void browseProducts(View view) {
        //Build intent so the 2 activities can bind
        Intent intent = new Intent(this, BrowseProducts.class);
        // Do something in response to button
        this.browseProductsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

}