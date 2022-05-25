package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {
    @GET("http://145.93.116.27:3000/users/")
    Call<List<UserData>> getAllUsers();

    @POST("http://145.93.116.27:3000/users/")
    Call<UserData> postUser(@Body UserData userData);

    @GET("http://145.93.116.27:3000/products/")
    Call<List<ProductData>> getAllProducts();

    @POST("http://145.93.116.27:3000/products/")
    Call<ProductData> postProduct(@Body ProductData productData);

    @GET("http://145.93.116.27:3000/products/searchBy/{searchTerm}")
    Call<List<ProductData>> getFilteredProducts(@Path("searchTerm") String searchTerm);

    @GET("http://145.93.116.27:3000/users/login/{email}")
    Call<UserData> findByEmail(@Path("email") String email);

    @PATCH("http://145.93.116.27:3000/products/{id}")
    Call<ProductData> updatePrice(@Path("id") String id, @Body ProductData productData);
}
