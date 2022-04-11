/*
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
    static final String baseUrl = "http://127.0.0.1:3000/";
    public Retrofit retrofit;
    public GerritAPI gerritAPI;

    public Controller() {
        Gson gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.gerritAPI = retrofit.create(GerritAPI.class);
        Call<List<UserData>> call = gerritAPI.getUsers();
        call.enqueue(this);

    }

    public Call<List<UserData>> getAllUsers() {
        return gerritAPI.getUsers();
    }

    @Override
    public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
        List<UserData> listResult = response.body();
        String[] users = new String[listResult.size()];

        for (int i = 0; i < listResult.size(); i++) {
            users[i] = listResult.get(i).getName();
        }
        //TODO: Add users to a list
        //  superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
    }


    @Override
    public void onFailure(Call<List<UserData>> call, Throwable t) {
        t.printStackTrace();
    }
}

*/
