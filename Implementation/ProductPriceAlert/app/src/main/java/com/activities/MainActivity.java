package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;

public class MainActivity extends AppCompatActivity {
    //static final String baseUrl = "http://192.168.0.116:3000/";
    //public Retrofit retrofit;
   // public RestAPI restAPI;

    Button registerButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize XML elements
        this.registerButton = findViewById(R.id.registerButton);
        this.loginButton = findViewById(R.id.loginButton);
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
        this.loginButton.setOnClickListener(view1 -> startActivity(new Intent(view.getContext(), BrowseProducts.class)));

    }


}