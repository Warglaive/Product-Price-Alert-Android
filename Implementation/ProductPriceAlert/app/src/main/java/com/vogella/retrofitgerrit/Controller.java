package com.vogella.retrofitgerrit;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.retrofitgerrit.Interfaces.GerritAPI;

import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<UserData>> {
    //TODO: Fix BaseURl
    static final String baseUrl = "http://127.0.0.1:3000/";
    Retrofit retrofit;
    GerritAPI gerritAPI;

    public void start() {
        Gson gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.gerritAPI = retrofit.create(GerritAPI.class);
        /*Call<List<UserData>> call = gerritAPI.getUsers("status:open");
        call.enqueue(this);*/

    }

    public Call<List<UserData>> getAllUsers() {
        Call<List<UserData>> call = gerritAPI.getUsers("status:open");
        return call;
    }

    @Override
    public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
        if (response.isSuccessful()) {
            List<UserData> changesList = response.body();
            changesList.forEach(c -> System.out.println(c.getName()));
        }
    }

    @Override
    public void onFailure(Call<List<UserData>> call, Throwable t) {
        t.printStackTrace();
    }
}

