package com.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.models.User;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.RestAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * CRUD and more for the User
 */
public class UserStorageService {
    //
    static final String baseUrl = "http://192.168.0.116:3000/";
    public Retrofit retrofit;
    public RestAPI restAPI;
    //
    private User user;

    /**
     * initialize retrofit connection
     */
    public UserStorageService() {
        Gson gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.restAPI = retrofit.create(RestAPI.class);


        Call<List<UserData>> callUser = this.restAPI.getAllUsers();
       // List<UserData> receivedUsers = new ArrayList<>();
        callUser.enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
              //  receivedUsers.addAll(response.body());
                System.out.println("Reached on response!");
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                System.out.println("Reached on Failure!");
                t.printStackTrace();
            }
        });
    }

    /**
     * register a single user
     *
     * @param user
     */
    public void registerUser(User user) {
        this.user = user;
        //do LB4 to register
        //TODO: implement
    }

    /**
     * get all users from the db
     * @return list of all users
     */
    public List<UserData> getAllUsers() {
        Call<List<UserData>> callUser = this.restAPI.getAllUsers();
        List<UserData> receivedUsers = new ArrayList<>();
        callUser.enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {

                System.out.println("Reached on response!");
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                System.out.println("Reached on Failure!");
                t.printStackTrace();
            }
        });
        return receivedUsers;
    }

    public User getUser() {
        return user;
    }
}
