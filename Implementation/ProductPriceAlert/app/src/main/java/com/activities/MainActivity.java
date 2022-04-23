package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.productpricealert.R;
import com.vogella.retrofitgerrit.RestClient;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.RestAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    //static final String baseUrl = "http://192.168.0.116:3000/";
    //public Retrofit retrofit;
   // public RestAPI restAPI;

    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize XML elements
        this.registerButton = findViewById(R.id.registerButton);
        //Works just fine
    /*    this.restAPI = RestClient.getClient();
        //TODO: Ask someone why it works like that
        Call<List<UserData>> callUser = this.restAPI.getAllUsers();
        // List<UserData> receivedUsers = new ArrayList<>();
        callUser.enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                //  receivedUsers.addAll(response.body());
                System.out.println("Reached on response!");
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                System.out.println("Reached on Failure!");
                t.printStackTrace();
            }
        });*/
        //
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