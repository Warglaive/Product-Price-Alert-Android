package com.activities;

import androidx.annotation.XmlRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.productpricealert.R;
import com.vogella.retrofitgerrit.interfaces.GerritAPI;
import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        registerUser(findViewById(R.id.mainActivity).getRootView());
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
        Intent intent = new Intent(this, RegisterUserActivity.class);
        // Do something in response to button
        this.registerButton.setOnClickListener(view1 -> startActivity(intent));
    }
}