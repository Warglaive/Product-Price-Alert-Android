package com.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;

public class RegisterUserActivity extends AppCompatActivity {
    private Button submitRegister;
    private EditText nameField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        submitRegister = findViewById(R.id.registerButton);
        nameField = findViewById(R.id.nameText);
    }

    /**
     * 1. get user data from the fields and register user via LB4 (add validation later)
     * 2. redirect to login view
     */
    void getUserInput(){
        this.submitRegister.setOnClickListener(v->{

        });
    }
}
