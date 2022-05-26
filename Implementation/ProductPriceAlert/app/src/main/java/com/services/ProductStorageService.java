package com.services;

import com.models.Product;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.RestClient;
import com.vogella.retrofitgerrit.interfaces.ResponseWait;
import com.vogella.retrofitgerrit.interfaces.RestAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void getAllProducts(ResponseWait callback) {
        Call<List<ProductData>> callProduct = this.restAPI.getAllProducts();
        callProduct.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                if(response.isSuccessful()){
                    List<ProductData> productData = response.body();
                    for (ProductData data: productData) {
                        System.out.println(data.toString());
                    }
                    try {
                        callback.responseWaitArray(productData);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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

    public void filterProducts(String filter, ResponseWait callback){
        Call<List<ProductData>> callFilter = this.restAPI.getFilteredProducts(filter);
        callFilter.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                if(response.isSuccessful()){
                    List<ProductData> filterResult = response.body();
                    try {
                        callback.responseWaitArray(filterResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                System.out.println("Failure!");
                t.printStackTrace();
            }
        });
    }

    public void updatePrice(String id, ProductData productData, ResponseWait callback){
        /*Map<String, Double> map = new HashMap<>();
        double p = Double.parseDouble(price);
        map.put("price", p);*/
        Call<ProductData> callUpdate = this.restAPI.updatePrice(id, productData);
        callUpdate.enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                if(response.isSuccessful()) {
                    ProductData productToUpdate = response.body();
                    callback.responseWaitSingle(productToUpdate);
                    //System.out.println(productToUpdate.getName());
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ProductData> call, Throwable t) {
                System.out.println("Failure!");
                t.printStackTrace();
            }
        });
    }

    public Product getProduct() {
        return product;
    }
}
