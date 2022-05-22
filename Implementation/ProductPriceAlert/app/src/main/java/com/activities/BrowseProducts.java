package com.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrowseProducts extends AppCompatActivity implements BrowseProductsActivityInterface {
    private UserData user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);

        Gson gson = new Gson();
        this.user = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);

        ProductStorageService service = new ProductStorageService();
        Context currentContext = this;
        final ListView listview = (ListView) findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<String>();

        Button filter = findViewById(R.id.filter);
        Button popular = findViewById(R.id.popular);

        service.getAllProducts(new ResponseWait() {
            @Override
            public void responseWaitArray(List response) {
                for (Object t : response) {
                    ProductData data = (ProductData) t;
                    list.add(data.getName());
                }

                final StableArrayAdapter adapter = new StableArrayAdapter(currentContext,
                        android.R.layout.simple_list_item_1, list);
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view,
                                            int position, long id) {
                        view.animate().setDuration(1000).alpha(0)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        String p = (String) parent.getItemAtPosition(position);
                                        Intent intent = new Intent(currentContext, ProductDetailsActivity.class);
                                        intent.putExtra("key", p);
                                        startActivity(intent);
                                    }
                                });
                    }
                });
            }

            @Override
            public void responseWaitSingle(UserData userData) {}

        });

        filter(filter);
        popular(popular);
    }

    @Override
    public void filter(Button filter) {
        Intent intent = new Intent(this, FilterProductsActivity.class);
        Gson gson = new Gson();
        String userDataJSON = gson.toJson(this.user);

        intent.putExtra("userDataKey", userDataJSON);
        filter.setOnClickListener(view1 -> startActivity(intent));
    }

    @Override
    public void popular(Button popular) {

    }
}