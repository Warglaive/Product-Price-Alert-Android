package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestAPI {
    @GET("http://192.168.0.116:3000/users/")
    Call<List<UserData>> getAllUsers();

    @POST("http://192.168.0.116:3000/users/")
    Call<UserData> postUser(@Body UserData userData);

}
