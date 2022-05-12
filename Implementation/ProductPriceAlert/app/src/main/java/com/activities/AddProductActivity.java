package com.activities;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.models.Product;
import com.models.User;
import com.productpricealert.R;
import com.services.ProductStorageService;
import com.services.UserStorageService;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
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

   // private static final int GALLERY_REQUEST = 100;
   // private static final int CAMERA_REQUEST = 200;
    private static final int REQUEST_ID_READ_WRITE_PERMISSION = 99;
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    private static final int REQUEST_ID_VIDEO_CAPTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ProductStorageService service = new ProductStorageService();
        this.takePhotoButton = findViewById(R.id.takePhotoButton);
        this.addGallery = findViewById(R.id.addGallery);
        this.addButton = findViewById(R.id.addButton);
        this.productName = findViewById(R.id.productName);
        this.productPrice = findViewById(R.id.productPrice);
        this.productDes = findViewById(R.id.productDes);
        this.imageView = findViewById(R.id.imageView);
        this.homeButton = findViewById(R.id.homeButton);

        ActivityCompat.requestPermissions(AddProductActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


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
