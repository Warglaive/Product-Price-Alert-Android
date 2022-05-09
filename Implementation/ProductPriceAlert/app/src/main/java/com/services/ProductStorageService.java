package com.services;

import com.models.Product;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.RestClient;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;
import com.vogella.retrofitgerrit.interfaces.RestAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductStorageService {
    public RestAPI restAPI;
    private Product product;

    public ProductStorageService() {
        this.restAPI = RestClient.getClient();
    }

    public void getAllProducts(ResponseWait callback) {
        Call<List<ProductData>> callProduct = this.restAPI.getAllProducts();
        callProduct.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                if(response.isSuccessful()){
                    List<ProductData> productData = response.body();
                    for (ProductData data: productData
                    ) {
                        System.out.println(data.toString());
                    }
                    callback.responseWaitArray(productData);
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                System.out.println("Reached on Failure!");
                t.printStackTrace();
            }
        });
    }

    public Product getProduct() {
        return product;
    }
}
