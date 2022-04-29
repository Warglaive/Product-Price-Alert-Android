package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.models.User;
import com.productpricealert.R;
import com.services.UserStorageService;

public class RegisterUserActivity extends AppCompatActivity {
    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText roleField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        //back btn
        Button backToMainBtn = findViewById(R.id.backToMainButton);
        redirectToMainOnBackButtonClick(backToMainBtn);
        //register btn
        Button submitRegister = findViewById(R.id.registerButton);
        submitRegister.setOnClickListener(this::RegisterAndRedirect);
        //find fields
        this.nameField = findViewById(R.id.regName);
        this.emailField = findViewById(R.id.regEmail);
        this.passwordField = findViewById(R.id.regPassword);
        this.roleField = findViewById(R.id.regRole);
        //
    }

    private void redirectToMainOnBackButtonClick(Button backToMainBtn) {
        backToMainBtn.setOnClickListener(v -> startActivity(new Intent(v.getContext(), MainActivity.class)));
    }


    /**
     * 1. get user data from the fields and register user via LB4 (add validation later)
     * 2. redirect to login view
     */
    private void RegisterAndRedirect(View v) {
        String name = this.nameField.getText().toString();
        String email = this.emailField.getText().toString();
        String password = this.passwordField.getText().toString();
        String role = this.roleField.getText().toString();
        //Create user
        User user = CreateUser(name, email, password, role);
        //if Register is successful redirect to login, else ->
        if (RegisterUser(user)) {
            startActivity(new Intent(v.getContext(), LoginUserActivity.class));
        } else {
            System.out.println("Registration failed");
        }

    }

    private User CreateUser(String name, String email, String password, String role) {
        //Call storage service to store it.
        return new User(name, email, password, role);
    }


    private boolean RegisterUser(User user) {
        UserStorageService storageService = new UserStorageService();
        return storageService.registerUser(user);
    }
}
