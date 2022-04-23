package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestAPI {
    @GET("http://192.168.0.116:3000/users/")
    Call<List<UserData>> getAllUsers();
    //TODO: Post
    //@FormUrlEncoded
    //@POST("http://192.168.0.116:3000/users/")
   //Call<User> postUser(@Body User user)

}
