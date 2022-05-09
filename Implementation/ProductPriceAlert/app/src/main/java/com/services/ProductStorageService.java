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
    private boolean isSuccessful = true;

    public ProductStorageService() {
        this.restAPI = RestClient.getClient();
    }

    public boolean registerProduct(Product product) {
        this.product = product;
        //Make ProductData from User and use LB4 to add it to the DB
        ProductData productData = new ProductData();
        productData.setName(product.getName());
        productData.setPrice(product.getPrice());
        productData.setDescription(product.getDescription());
        productData.setImage(product.getImage());
        //add ProductData to DB
        Call<ProductData> call = this.restAPI.postProduct(productData);
        call.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                System.out.println(response.isSuccessful());
                isSuccessful = response.isSuccessful();
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                System.out.println("On Failure Reached");
                isSuccessful = false;
            }
        });
        return isSuccessful;
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
