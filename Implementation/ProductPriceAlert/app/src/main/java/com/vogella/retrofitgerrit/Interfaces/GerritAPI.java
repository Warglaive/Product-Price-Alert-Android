package com.vogella.retrofitgerrit.Interfaces;

import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GerritAPI {
    @GET("users/")
    Call<List<UserData>> loadChanges(@Query("q") String status);
}
