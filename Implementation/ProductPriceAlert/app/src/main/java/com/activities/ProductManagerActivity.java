package com.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.productpricealert.R;
import com.vogella.retrofitgerrit.UserData;

public class ProductManagerActivity extends AppCompatActivity {
    private final String UserDataKey = "userDataKey";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        Gson gson = new Gson();
        UserData userData = gson.fromJson(getIntent().getStringExtra(UserDataKey), UserData.class);
    }
}
