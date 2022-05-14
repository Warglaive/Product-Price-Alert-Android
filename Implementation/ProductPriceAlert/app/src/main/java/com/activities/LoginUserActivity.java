package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.exceptions.WrongPasswordException;
import com.google.gson.Gson;
import com.models.User;
import com.productpricealert.R;
import com.services.UserStorageService;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
        this.userStorageService.findByLoginCredentials(email, password, new ResponseWait<UserData>() {
            @Override
            public void responseWaitArray(List<UserData> response) {
                //Useless at the moment, but good for extendability
            }

            /**
             *
             */
            @Override
            public void responseWaitSingle(UserData userData) {
                //Check if role == "Product Manager" -> redirect to corresponding view
                if (isRoleProductManager(userData)) {
                    Intent intent = new Intent(LoginUserActivity.this, ProductManagerActivity.class);
                    //Pass the object as JSON
                    Gson gson = new Gson();
                    String userDataJSON = gson.toJson(userData);

                    intent.putExtra("userDataKey", userDataJSON);
                    startActivity(intent);
                }
                if (isRoleCustomer(userData)) {
                    Intent intent = new Intent(LoginUserActivity.this, BrowseProducts.class);
                    //Pass the object as JSON
                    Gson gson = new Gson();
                    String userDataJSON = gson.toJson(userData);

                    intent.putExtra("userDataKey", userDataJSON);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean isRoleCustomer(UserData userData) {
        return Objects.equals(userData.getRole(), "Customer");
    }

    private boolean isRoleProductManager(UserData userData) {
        return Objects.equals(userData.getRole(), "Product Manager");
    }

    /**
     * redirect to home main activity
     */
    private void redirectToMainOnBackButtonClick(Button backToMainBtn) {
        backToMainBtn.setOnClickListener(v -> startActivity(new Intent(v.getContext(), MainActivity.class)));
    }
}
