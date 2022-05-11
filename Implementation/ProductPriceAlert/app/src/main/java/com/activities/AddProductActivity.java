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

        //TODO ask for permission of camera upon first launch of application
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, 111);
            }
        }

        this.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddProductActivity.this, HomePageActivity.class));
            }
        });

        //TODO chose image from gallery
        addGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

            }
        });

        //TODO capture image
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
}

    Uri image_uri;
    private static final int RESULT_LOAD_IMAGE = 123;
    public static final int IMAGE_CAPTURE_CODE = 654;
    //TODO opens camera so that user can capture image
    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == RESULT_OK){
            //imageView.setImageURI(image_uri);
            Bitmap bitmap = uriToBitmap(image_uri);
            imageView.setImageBitmap(bitmap);

        }


        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            image_uri = data.getData();
            //imageView.setImageURI(image_uri);
            Bitmap bitmap = uriToBitmap(image_uri);
            imageView.setImageBitmap(bitmap);
        }
    }

    //TODO takes URI of the image and returns bitmap
    private Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);

            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
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
