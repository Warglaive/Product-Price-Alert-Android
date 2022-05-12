package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;

public class LoginUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        //back btn
        Button backToMainBtn = findViewById(R.id.backToMainButton);
        redirectToMainOnBackButtonClick(backToMainBtn);
        //submit login btn
        Button submitLoginBtn = findViewById(R.id.loginBtnLogin);
        //Check if role == "Product Manager" -> redirect to corresponding view
        // TODO: Get data from login fields and find user by Email
        EditText emailField = findViewById(R.id.logEmail);
        EditText passwordField = findViewById(R.id.logPassword);

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        
        //Check if role == "Customer" -> redirect to corresponding view.
        submitLoginBtn.setOnClickListener(v -> {
            //TODO: Take logged in user's data and pass it to new logged in view depending on Role
            //TODO: 1. Get current user's data from DB Using LB4
            //TODO: 2. Check if role is "Product Manager" and startActivity
        });
    }

    private void redirectToMainOnBackButtonClick(Button backToMainBtn) {
        backToMainBtn.setOnClickListener(v -> startActivity(new Intent(v.getContext(), MainActivity.class)));
    }
}
