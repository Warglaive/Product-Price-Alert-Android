package com.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.RestClient;
import com.vogella.retrofitgerrit.interfaces.RestAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseProducts extends AppCompatActivity {

    private RestAPI restAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);

        this.restAPI = RestClient.getClient();
        final Button filter = findViewById(R.id.filter);
        final Button popular = findViewById(R.id.popular);
        final ListView listview = (ListView) findViewById(R.id.listview);

        Call<List<ProductData>> callProduct = this.restAPI.getAllProducts();
        List<ProductData> receivedProducts = new ArrayList<>();
        callProduct.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                List<ProductData> data = response.body();
                final StableArrayAdapter adapter = new StableArrayAdapter(this,
                        android.R.layout.simple_list_item_1, data);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                System.out.println("Reached on Failure!");
                t.printStackTrace();
            }
        });

        filter(filter);
        popular(popular);
    }

    public void productDetails() {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        startActivity(intent);
    }

    public void filter(Button filter){
        Intent intent = new Intent(this, FilterProductsActivity.class);
        filter.setOnClickListener(view1 -> startActivity(intent));
    }

    public void popular(Button popular){

    }

    private class StableArrayAdapter extends ArrayAdapter<ProductData> {

        HashMap<ProductData, Integer> mIdMap = new HashMap<>();

        public StableArrayAdapter(Callback<List<ProductData>> context, int textViewResourceId,
                                  List<ProductData> objects) {
            super((Context) context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            ProductData item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}