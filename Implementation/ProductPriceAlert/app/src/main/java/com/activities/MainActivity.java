package com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.productpricealert.R;
import com.vogella.retrofitgerrit.Controller;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Controller controller = new Controller();
        controller.start();
    }

}