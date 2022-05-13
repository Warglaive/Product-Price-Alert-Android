package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.models.User;
import com.productpricealert.R;
import com.services.UserStorageService;

public class LoginUserActivity extends AppCompatActivity {
    private UserStorageService userStorageService;
    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        //back btn
        Button backToMainBtn = findViewById(R.id.backToMainButton);
        redirectToMainOnBackButtonClick(backToMainBtn);
        Button submitLoginBtn = findViewById(R.id.loginBtnLogin);
        this.emailField = findViewById(R.id.logEmail);
        this.passwordField = findViewById(R.id.logPassword);
        submitLoginBtn.setOnClickListener(v -> login());
    }

    /**
     * redirect to corresponding login view with respect to role
     */
    private void login() {
        String email = this.emailField.getText().toString();
        String password = this.passwordField.getText().toString();

        this.userStorageService = new UserStorageService();
        User user = this.userStorageService.findByLoginCredentials(email, password);
        //Check if role == "Product Manager" -> redirect to corresponding view
        if (user.getRole().equals("Product Manager")){
            //TODO: redirect
        }
        // TODO: Get data from login fields and find user by Email
        //TODO: Take logged in user's data and pass it to new logged in view depending on Role
        //TODO: 1. Get current user's data from DB Using LB4
        //TODO: 2. Check if role is "Product Manager" and startActivity
    }

    /**
     * redirect to home main activity
     */
    private void redirectToMainOnBackButtonClick(Button backToMainBtn) {
        backToMainBtn.setOnClickListener(v -> startActivity(new Intent(v.getContext(), MainActivity.class)));
    }
}
