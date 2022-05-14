package com.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.productpricealert.R;
import com.vogella.retrofitgerrit.UserData;

public class ProductManagerActivity extends AppCompatActivity {
    //Constants
    private final String UserDataKey = "userDataKey";
    //
    private TextView welcomeNamePM;
    private TextView showRolePM;
    private Button browseProductsPM;
    private Button addNewPRBtn;
    private Button logoutPM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manager);
        //Get current PM Data
        Gson gson = new Gson();
        UserData userData = gson.fromJson(getIntent().getStringExtra(UserDataKey), UserData.class);
        //Find fields
        findFieldsById();
        //set fields initial values
        DisplayUserData(userData);
        //
       
    }

    private void DisplayUserData(UserData userData) {
        String welcomeMessage = "Welcome " + userData.getName();
        this.welcomeNamePM.setText(welcomeMessage);
        this.welcomeNamePM.setTextColor(Color.parseColor("#2596be"));
        //
        String roleDisplay = "Your Role is: " + userData.getRole();
        this.showRolePM.setText(roleDisplay);
        this.showRolePM.setTextColor(Color.parseColor("#2596be"));
    }

    private void findFieldsById() {
        this.welcomeNamePM = findViewById(R.id.welcomeNamePM);
        this.showRolePM = findViewById(R.id.showRolePM);
        this.browseProductsPM = findViewById(R.id.browseProductsPM);
        this.addNewPRBtn = findViewById(R.id.addNewPRBtn);
        this.logoutPM = findViewById(R.id.logoutPM);
    }

}
