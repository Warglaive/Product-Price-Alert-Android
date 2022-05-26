package com.vogella.retrofitgerrit.interfaces;

import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {

    @GET("http://192.168.0.116:3000/users/")
    Call<List<UserData>> getAllUsers();

    @POST("http://192.168.0.116:3000/users/")
    Call<UserData> postUser(@Body UserData userData);

    @GET("http://192.168.0.116:3000/products/")
    Call<List<ProductData>> getAllProducts();

    @POST("http://192.168.0.116:3000/products/")
    Call<ProductData> postProduct(@Body ProductData productData);

    @GET("http://192.168.0.116:3000/products/searchBy/{searchTerm}")
    Call<List<ProductData>> getFilteredProducts(@Path("searchTerm") String searchTerm);

    @GET("http://192.168.0.116:3000/users/login/{email}")
    Call<UserData> findByEmail(@Path("email") String email);

    @PATCH("http://192.168.0.116:3000/products/{id}")
    Call<ProductData> updatePrice(@Path("id") String id, @Body ProductData productData);
    //Cloud messaging
  //  @Headers("Authorization : key=AAAA4Ubio1Q:APA91bGWkw84b1XX2nnnOKn8MO25U2giLRXXXTUkXidojFluZk_qKXXXlS27oMZZV5goTQdwRtpdmvI1iAPRZZDNKz6c-mpU6nvHZJ-Jg9f1fQ5NdttftqUpqwAkObLEEX26VFDDbXN8")
  //  @POST("fcm/send")
  //  Call<ResponseBody> sendChatNotification(@Field("to") String token, @Body RequestNotificaton requestNotificaton);


}
