package com.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;

public class RegisterUserActivity extends AppCompatActivity {
    Button registerButton = findViewById(R.id.registerButton);
    EditText nameField = findViewById(R.id.nameText);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        registerButton = findViewById(R.id.registerButton);
        nameField = findViewById(R.id.nameText);
    }
}
