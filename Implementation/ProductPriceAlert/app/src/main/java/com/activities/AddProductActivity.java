package com.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.models.Product;
import com.models.User;
import com.productpricealert.R;
import com.services.ProductStorageService;
import com.services.UserStorageService;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddProductActivity extends AppCompatActivity {
    private Button takePhotoButton;
    private Button addGallery;
    private Button addButton;
    private Button homeButton;
    private EditText productName;
    private EditText productPrice;
    private EditText productDes;
    private ImageView imageView;

    private static final int GALLERY_REQUEST = 100;
    private static final int CAMERA_REQUEST = 200;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        this.takePhotoButton = findViewById(R.id.takePhotoButton);
        this.addGallery = findViewById(R.id.addGallery);
        this.addButton = findViewById(R.id.addButton);
        this.productName = findViewById(R.id.productName);
        this.productPrice = findViewById(R.id.productPrice);
        this.productDes = findViewById(R.id.productDes);
        this.imageView = findViewById(R.id.imageView);

        String productName = this.productName.getText().toString();
        Double productPrice = Double.parseDouble(String.valueOf(this.productPrice.getText()));
        String productDes = this.productDes.getText().toString();



        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


}



    private Product CreateProduct(String name, Double price) {
        //Call storage service to store it.
        return new Product(name,price);
    }

    private Product CreateProduct(String name, Double price,String description) {
        //Call storage service to store it.
        return new Product(name,price,description);
    }

    private Product CreateProduct(String name, Double price, String description, String image) {
        //Call storage service to store it.
        return new Product(name,price,description,image);
    }

    private void RegisterProduct(Product product) {
        ProductStorageService storageService = new ProductStorageService();
        storageService.registerProduct(product);
    }

}
