package com.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.models.Product;
import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.ResponseWaitImpl;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BrowseProducts extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);

        ProductStorageService service = new ProductStorageService();

        Context currentContext = this;

        final ListView listview = (ListView) findViewById(R.id.listview);

        final ArrayList<String> list = new ArrayList<String>();

        Context context = this;

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
                        final String item = (String) parent.getItemAtPosition(position);
                        view.animate().setDuration(1000).alpha(0)
                                .withEndAction(new Runnable() {
                                    @Override
                                    public void run() {
                                        String p = (String) parent.getItemAtPosition(position);
                                        Intent intent = new Intent(context, ProductDetailsActivity.class);
                                        intent.putExtra("key", p);
                                        startActivity(intent);
                                    }
                                });
                    }

                });
            }

            @Override
            public void responseWaitSingle(UserData userData) {

            }
        });

        filter(filter);
        popular(popular);


    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

    public Intent intent() {
        return new Intent(this, ProductDetailsActivity.class);
    }

    public void filter(Button filter) {
        Intent intent = new Intent(this, FilterProductsActivity.class);
        filter.setOnClickListener(view1 -> startActivity(intent));
    }

    public void popular(Button popular) {

    }

}