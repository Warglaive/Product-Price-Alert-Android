package com.vogella.retrofitgerrit;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.retrofitgerrit.Interfaces.GerritAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Change>> {
    //TODO: Fix BaseURl
    static final String baseUrl = "localhost:3000";

    public void start() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GerritAPI gerritAPI = retrofit.create(GerritAPI.class);
        Call<List<Change>> call = gerritAPI.loadChanges("status:open");
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
        if (response.isSuccessful()) {
            List<Change> changesList = response.body();
            changesList.forEach(c -> System.out.println(c.subject));
        }
    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {
        t.printStackTrace();
    }
}

