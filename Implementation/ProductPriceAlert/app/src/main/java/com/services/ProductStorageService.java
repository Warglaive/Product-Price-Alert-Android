package com.services;

import com.models.Product;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.RestClient;
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

    public List<ProductData> getAllProducts() {
        Call<List<ProductData>> callProduct = this.restAPI.getAllProducts();
        List<ProductData> receivedProducts = new ArrayList<>();
        callProduct.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                System.out.println("Reached on response!");
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                System.out.println("Reached on Failure!");
                t.printStackTrace();
            }
        });
        return receivedProducts;
    }

    public Product getProduct() {
        return product;
    }
}
