package com.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.productpricealert.R;
import com.services.ProductStorageService;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowFilteredProductsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_products);

        ProductStorageService service = new ProductStorageService();
        TextView textView = findViewById(R.id.textViewFilter);
        ListView listview = findViewById(R.id.filterList);
        List<String> list = new ArrayList<>();
        Context context = this;

        Bundle extras = getIntent().getExtras();
        String searchTerm = extras.getString("key");

        service.filterProducts(searchTerm, new ResponseWait() {
            @Override
            public void responseWaitArray(List response) {
                for (Object t : response) {
                    ProductData data = (ProductData) t;
                    list.add(data.getName());
                }

                final ShowFilteredProductsActivity.StableArrayAdapter adapter = new ShowFilteredProductsActivity.StableArrayAdapter
                        (context, android.R.layout.simple_list_item_1, list);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
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
        });
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
}
