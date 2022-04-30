package com.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.models.User;
import com.productpricealert.R;
import com.services.UserStorageService;

public class RegisterUserActivity extends AppCompatActivity {
    private Button submitRegister;
    private EditText nameField;
    private EditText emailField;
    private EditText passwordField;
    private EditText roleField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        submitRegister = findViewById(R.id.registerButton);
        this.submitRegister.setOnClickListener(v -> {
            RegisterAndRedirect();
        });
        //find fields
        this.nameField = findViewById(R.id.regName);
        this.emailField = findViewById(R.id.regEmail);
        this.passwordField = findViewById(R.id.regPassword);
        this.roleField = findViewById(R.id.regRole);
        //
    }

    /**
     * 1. get user data from the fields and register user via LB4 (add validation later)
     * 2. redirect to login view
     */
    void RegisterAndRedirect() {
        String name = this.nameField.getText().toString();
        String email = this.emailField.getText().toString();
        String password = this.passwordField.getText().toString();
        String role = this.roleField.getText().toString();
        //Create user
        User user = CreateUser(name, email, password, role);
        //Register
        RegisterUser(user);
        //TODO: Redirect to login
    }

    private User CreateUser(String name, String email, String password, String role) {
        //Call storage service to store it.
        return new User(name, email, password, role);
    }


    private void RegisterUser(User user) {
        UserStorageService storageService = new UserStorageService();
        storageService.registerUser(user);
    }
}
