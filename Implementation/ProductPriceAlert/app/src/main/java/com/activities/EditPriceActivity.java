package com.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ProductPriceAlert.R;
import com.models.User;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;

public class EditPriceActivity extends AppCompatActivity {
    private UserData user;
    private ProductData productData;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_price);

        TextView viewCurrent = findViewById(R.id.currentPriceText);
        TextView fieldCurrent = findViewById(R.id.currentPriceField);
        EditText newPriceField = findViewById(R.id.priceField);
        TextView newPriceText = findViewById(R.id.newPriceText);
        Button back = findViewById(R.id.backEdit);
        TextView textView = findViewById(R.id.textView);


    }

    public void backToDetails(Button back){

    }

    public void editPrice(Button edit){

    }
}
