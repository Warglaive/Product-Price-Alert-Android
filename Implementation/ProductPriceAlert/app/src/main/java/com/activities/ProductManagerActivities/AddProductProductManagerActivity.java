package com.activities.ProductManagerActivities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
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
import com.productpricealert.R;
import com.services.ProductStorageService;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddProductProductManagerActivity extends AppCompatActivity {
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, 111);
                    } else {
                        openCamera();
                    }
                } else {
                    openCamera();
                }
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product newProduct = CreateProduct(productName,productPrice);
                RegisterProduct(newProduct);
            }
        });

        addGallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), GALLERY_REQUEST);

            }
        });
}
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            try{
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                imageView.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    Uri image_uri;
    private static final int RESULT_LOAD_IMAGE = 123;
    public static final int IMAGE_CAPTURE_CODE = 654;

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,CAMERA_REQUEST);
        }
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
