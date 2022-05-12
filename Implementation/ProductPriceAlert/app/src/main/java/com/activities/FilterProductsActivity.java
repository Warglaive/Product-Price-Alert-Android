package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.util.ArrayList;
import java.util.List;

public class FilterProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_products);

        ProductStorageService service = new ProductStorageService();
        Button filterfil = findViewById(R.id.filterfil);
        TextView enter = findViewById(R.id.enter);
        EditText input = findViewById(R.id.input);
        Button backp = findViewById(R.id.backp);
        List<ProductData> list = new ArrayList<>();

        String search = input.getText().toString();

        filter(filterfil);
        backToBrowse(backp);

        service.filterProducts(search, new ResponseWait() {
            @Override
            public void responseWaitArray(List response) {
                for (Object t : response) {
                    ProductData data = (ProductData) t;
                    list.add(data);
                }

                

                /*ProductData product = new ProductData();
                for (ProductData p : list) {
                    if (p.getName().equals(productName)) {
                        product = p;
                        break;
                    }
                }*/
            }
        });
    }

    public void filter(Button filter){

    }

    public void backToBrowse(Button backp){
        Intent intent = new Intent(this, BrowseProducts.class);
        backp.setOnClickListener(view1 -> startActivity(intent));
    }
}
