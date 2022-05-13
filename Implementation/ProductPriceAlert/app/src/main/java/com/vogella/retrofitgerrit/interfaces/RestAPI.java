package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {
    @GET("http://192.168.0.117:3000/users/")
    Call<List<UserData>> getAllUsers();

    @POST("http://192.168.0.117:3000/users/")
    Call<UserData> postUser(@Body UserData userData);

    @GET("http://192.168.0.117:3000/products/")
    Call<List<ProductData>> getAllProducts();

    @POST("http://192.168.0.117:3000/products/")
    Call<ProductData> postProduct(@Body ProductData productData);

    @GET("http://192.168.0.117:3000/products/searchBy/{searchTerm}")
    Call<List<ProductData>> getFilteredProducts(@Path("searchTerm") String searchTerm);

    @GET("http://192.168.0.117:3000/users/findByEmail/")
    Call<UserData> findByEmail(@Query("email") String email);
}
