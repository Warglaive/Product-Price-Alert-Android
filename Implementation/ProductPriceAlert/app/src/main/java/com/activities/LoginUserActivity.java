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
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

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
            public void responseWaitArray(List response) {
                //Useless at the moment, but good for extendability
            }

            /**
             *
             * @param userData
             */
            @Override
            public void responseWaitSingle(UserData userData) {
                System.out.println(userData.getRole());
                //Check if role == "Product Manager" -> redirect to corresponding view
                if (Objects.equals(userData.getRole(), "Product Manager")) {
                    //TODO: redirect to new Customer Manager view to display User Data
                    System.out.println("1");
                }


                userData.getPassword().equals(password);
                //
            }
        });

    }

    /**
     * redirect to home main activity
     */
    private void redirectToMainOnBackButtonClick(Button backToMainBtn) {
        backToMainBtn.setOnClickListener(v -> startActivity(new Intent(v.getContext(), MainActivity.class)));
    }
}
