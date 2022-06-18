package com.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ProductPriceAlert.R;
import com.google.gson.Gson;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity implements ProductDetailsActivityInterface {
    private UserData user;
    private ProductData productDataa;
    private String productName;
    private ProductStorageService service;
    private Context context;
    private TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);
        this.service = new ProductStorageService();
        Bundle extras = getIntent().getExtras();
        this.productName = extras.getString("key");

        TextView name = (TextView) findViewById(R.id.nameGet);
        TextView price = (TextView) findViewById(R.id.priceGet);
        TextView description = (TextView) findViewById(R.id.descriptionGet);
        this.location = findViewById(R.id.locationGetM);
        ImageView productImage = findViewById(R.id.productImage);
        Button button = findViewById(R.id.button);
        Button edit = findViewById(R.id.edit);
        Button rotate = findViewById(R.id.buttonRot);
        this.context = this;
        ArrayList<ProductData> list = new ArrayList<ProductData>();

        this.service.getAllProducts(new ResponseWait() {
            @Override
            public void responseWaitArray(List response) throws IOException {
                for (Object t : response) {
                    ProductData data = (ProductData) t;
                    list.add(data);
                }

                ProductData product = new ProductData();
                for (ProductData p : list) {
                    if (p.getName().equals(productName)) {
                        product = p;
                        break;
                    }
                }

                name.setText(product.getName());
                price.setText(String.valueOf(product.getPrice()));

                if(product.hasDescription()) {
                    description.setText(product.getDescription());
                }
                if (product.hasLocation()){
                    location.setText(product.getLocation());
                }
                if(product.hasImage()) {
                    String encodedImage = product.getImage();
                    byte[] imageBytes = Base64.decode(encodedImage,Base64.DEFAULT);
                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    productImage.setImageBitmap(decodedImage);
                }

            }

            @Override
            public void responseWaitSingle(ProductData productData) {

            }

            @Override
            public void responseWaitSingle(UserData userData) {

            }
        });

        editProduct(edit);
        backToBrowse(button);
        landedOnDetails(this);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rotateImage(productImage);
            }
        });
    }

    private void rotateImage(ImageView image) {
        image.setPivotX((float) image.getWidth() / 2);
        image.setPivotY((float) image.getHeight() / 2);
        image.setRotation(90);
    }

    @Override
    public void backToBrowse(Button button) {
        Intent intent = new Intent(this, BrowseProducts.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        button.setOnClickListener(view1 -> startActivity(intent));
    }

    @Override
    public void editProduct(Button button) {
        List<ProductData> list = new ArrayList<>();
        this.service.getAllProducts(new ResponseWait() {
            @Override
            public void responseWaitArray(List response) throws MalformedURLException, IOException {
                for (Object t : response) {
                    ProductData data = (ProductData) t;
                    list.add(data);
                }

                ProductData product = new ProductData();
                for (ProductData p : list) {
                    if (p.getName().equals(productName)) {
                        product = p;
                        break;
                    }
                }

                productDataa = product;
                Intent intent = new Intent(context, EditPriceActivity.class);
                Gson gson = new Gson();
                String userDataJSON = gson.toJson(user);

                intent.putExtra("userDataKey", userDataJSON);
                String productDataJSON = gson.toJson(productDataa);
                intent.putExtra("productDataKey", productDataJSON);

                button.setOnClickListener(view1 -> startActivity(intent));
            }

            @Override
            public void responseWaitSingle(ProductData productData) {

            }

            @Override
            public void responseWaitSingle(UserData userData) {

            }
        });
    }

    @Override
    public void purchaseProduct(Button button) {

    }

    private void landedOnDetails(Context context) {
        CharSequence text = "You got to the Product Details!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
