package com.vogella.retrofitgerrit;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Change>> {
    @Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {

    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {

    }
}

