package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestAPI {
    @GET("http://172.25.80.1:3000/users/")
    Call<List<UserData>> getAllUsers();

    @POST("http://172.25.80.1:3000/users/")
    Call<UserData> postUser(@Body UserData userData);


    @GET("http://172.25.80.1:3000/products/")
    Call<List<ProductData>> getAllProducts();

    @POST("http://172.25.80.1:3000/products/")
    Call<ProductData> postProduct(@Body ProductData productData);


    @GET
    Call<UserData> findByName(@Query("name") String name);

   

    @GET("http://192.168.0.117:3000/products/")
    Call<List<ProductData>> getFilteredUsers(@Query("$filter") String filter);

}
