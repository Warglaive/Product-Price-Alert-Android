package com.activities;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import static java.security.AccessController.getContext;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ProductPriceAlert.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.services.PushNotificationService;
import android.provider.Settings.Secure;


public class MainActivity extends AppCompatActivity {

    private Button registerButton;
    private Button loginButton;
    //Notification test
    private Button notifyMe;

  //  private String android_id = Secure.getString((ContentResolver) getContext().getDomainCombiner(),
  //          Secure.ANDROID_ID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialize XML elements
        this.registerButton = findViewById(R.id.registerButton);
        this.loginButton = findViewById(R.id.loginButton);
        this.notifyMe = findViewById(R.id.notifyMe);
        loginOrRegister(findViewById(R.id.mainActivity).getRootView());
        //TODO: Test notifications
        NotificationOnClickListenerSet();
        sendToServer();
    }

    private void sendToServer() {
        // Fetching Android ID and storing it into a constant
    }

    private void NotificationOnClickListenerSet() {
        this.notifyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Subscribing to weather topic");
                // [START subscribe_topics]
                FirebaseMessaging.getInstance().subscribeToTopic("weather")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = getString(R.string.msg_subscribed);
                                if (!task.isSuccessful()) {
                                    msg = getString(R.string.msg_subscribe_failed);
                                }
                                Log.d(TAG, msg);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                                System.out.println("TOKEN1: "+msg);

                            }
                        });
                // [END subscribe_topics]
            }
        });

        /**
         * Log Token
         */

        this.notifyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get token
                // [START log_reg_token]
                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                    return;
                                }

                                // Get new FCM registration token
                                String token = task.getResult();

                                // Log and toast
                                //TODO: Ignore the warning
                                String msg = getString(R.string.msg_token_fmt, token);
                                Log.d(TAG, msg);
                                Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                                System.out.println("TOKEN2: "+token);

                            }
                        });
                // [END log_reg_token]
            }
        });
    }



    /**
     * Called when the user taps the Send button
     */
    public void loginOrRegister(View view) {
        //Build intent so the 2 activities can bind
        //view.GetContext() can be replaced by "MainActivity.class" or just "this" if current activity is passed
        Intent registerIntent = new Intent(view.getContext(), RegisterUserActivity.class);
        Intent loginIntent = new Intent(view.getContext(), LoginUserActivity.class);
        //Intent intent = new Intent(view.getContext(), BrowseProducts.class);
        // Do something in response to button
        //TODO: Add login view connection
        this.registerButton.setOnClickListener(v -> startActivity(registerIntent));
        this.loginButton.setOnClickListener(v -> startActivity(loginIntent));

        //this.registerButton.setOnClickListener(view1 -> startActivity(intent));
        //this.loginButton.setOnClickListener(view1 -> startActivity(new Intent(view.getContext(), BrowseProducts.class)));

    }
}