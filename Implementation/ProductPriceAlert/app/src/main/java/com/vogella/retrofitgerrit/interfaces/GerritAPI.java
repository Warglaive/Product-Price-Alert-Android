package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GerritAPI {
    @GET("http://192.168.0.116:3000/users/")
    Call<List<UserData>> getUsers();
    //TODO: Post
}
