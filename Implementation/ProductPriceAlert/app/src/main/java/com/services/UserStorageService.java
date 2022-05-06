package com.services;

import com.models.User;
import com.vogella.retrofitgerrit.RestClient;
import com.vogella.retrofitgerrit.UserData;
import com.vogella.retrofitgerrit.interfaces.RestAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * CRUD and more for the User
 */
public class UserStorageService {
    public RestAPI restAPI;
    private User user;
    //may be useless
    private boolean isSuccessful = true;

    /**
     * initialize retrofit connection
     */
    public UserStorageService() {
        this.restAPI = RestClient.getClient();
    }

    /**
     * register a single user
     *
     * @param user
     */
    public boolean registerUser(User user) {
        this.user = user;
        //Make UserData from User and use LB4 to add it to the DB
        UserData userData = new UserData();
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setPassword(user.getPassword());
        userData.setRole(user.getRole());
        //add UserData to DB
        Call<UserData> call = this.restAPI.postUser(userData);
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                System.out.println(response.isSuccessful());
                isSuccessful = response.isSuccessful();
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                System.out.println("On Failure Reached");
                isSuccessful = false;
            }
        });
        return isSuccessful;
    }

    /**
     * get all users from the db
     *
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

    /**
     * get a User instance from DB by name
     * @return
     */
    public User getUserByName() {
        Call<List<UserData>> callUser= this.restAPI.getAllUsers()
    }
}
