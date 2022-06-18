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
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsCustomerActivity extends AppCompatActivity implements ProductDetailsActivityInterface {
    private UserData user;
    private ProductData product;
    private ProductStorageService service;
    private String productName;
    private ProductData productDataa;
    private Context context;
    TextView location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_customer);

<<<<<<< HEAD
        context = this;
=======
>>>>>>> parent of 48d797e9 (Merge branch 'TimBranch')
        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);
        this.service = new ProductStorageService();
        Bundle extras = getIntent().getExtras();
        this.productName = extras.getString("key");
        this.context = this;


        TextView name = (TextView) findViewById(R.id.nameGetC);
        TextView price = (TextView) findViewById(R.id.priceGetC);
        TextView description = (TextView) findViewById(R.id.descriptionGetC);
        ImageView image = findViewById(R.id.imageC);
        Button button = findViewById(R.id.buttonC);
        Button purchase = findViewById(R.id.purchase);
        Button request = findViewById(R.id.request);
        Button maxPrice = findViewById(R.id.button2);
        ArrayList<ProductData> list = new ArrayList<ProductData>();
        location = findViewById(R.id.locationGetC);


        service.getAllProducts(new ResponseWait() {
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
                    image.setImageBitmap(decodedImage);
                }
            }

            @Override
            public void responseWaitSingle(ProductData productData) {

            }

            @Override
            public void responseWaitSingle(UserData userData) {

            }
        });

        backToBrowse(button);
        purchaseProduct(purchase);
        landedOnDetails(this);
        maxPrice(maxPrice);
    }

    @Override
    public void backToBrowse(Button button) {
        Intent intent = new Intent(this, BrowseProductsCustomerActivity.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        button.setOnClickListener(view1 -> startActivity(intent));
    }

    @Override
    public void editProduct(Button button) {

    }

    @Override
    public void purchaseProduct(Button button) {

    }
    public void openMap(View view) {

        Intent intent = new Intent(context, MapsActivityCurrentPlace.class);
        intent.putExtra("location", location.getText().toString());
        intent.putExtra("key", productName);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        startActivity(intent);


    }
<<<<<<< HEAD
    private void landedOnDetails (Context context){
=======

    public void maxPrice(Button button) {
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
                Intent intent = new Intent(context, ProvideMaxPriceActivity.class);
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
        /*Intent intent = new Intent(this, ProvideMaxPriceActivity.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(user);
        intent.putExtra("userDataKey", userDataJSON);
        intent.putExtra("product", this.p)
        button.setOnClickListener(view1 -> startActivity(intent));*/
    }

    private void landedOnDetails(Context context) {
>>>>>>> parent of 48d797e9 (Merge branch 'TimBranch')
        CharSequence text = "You got to the Product Details!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}