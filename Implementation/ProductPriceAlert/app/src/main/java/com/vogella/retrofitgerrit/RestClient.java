package com.vogella.retrofitgerrit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vogella.retrofitgerrit.interfaces.RestAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    //
    static final String baseUrl = "http://192.168.0.117:3000/";
    static final String pushNotificationsURL="https://fcm.googleapis.com/";

    //GET
    public static RestAPI getClient() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RestAPI.class);
    }
    //POST
    public static RestAPI getPushNotificationClient(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(pushNotificationsURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RestAPI.class);
    }
}
