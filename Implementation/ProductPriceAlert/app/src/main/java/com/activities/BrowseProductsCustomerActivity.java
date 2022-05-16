package com.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class BrowseProductsCustomerActivity extends AppCompatActivity implements BrowseProductsActivityInterface {
    private UserData user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products_customer);

        ProductStorageService service = new ProductStorageService();
        Context currentContext = this;
        final ListView listview = (ListView) findViewById(R.id.listviewC);
        final ArrayList<String> list = new ArrayList<String>();
        Gson gson = new Gson();
        UserData userData = gson.fromJson(getIntent().getStringExtra("userDataKey"), UserData.class);
        this.user = userData;

        Button filter = findViewById(R.id.filterC);
        Button popular = findViewById(R.id.popularC);

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
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        view.animate().setDuration(1000).alpha(0)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        String p = (String) parent.getItemAtPosition(position);
                                        Intent intent = new Intent(currentContext, ProductDetailsCustomerActivity.class);
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
