package com.vogella.retrofitgerrit.interfaces;

import com.models.RequestNotification;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestAPI {
    @GET("http://lb4latest.herokuapp.com/users/")
    Call<List<UserData>> getAllUsers();

    @POST("http://lb4latest.herokuapp.com/users/")
    Call<UserData> postUser(@Body UserData userData);

    @GET("http://lb4latest.herokuapp.com/products/")
    Call<List<ProductData>> getAllProducts();

    @POST("http://lb4latest.herokuapp.com/products/")
    Call<ProductData> postProduct(@Body ProductData productData);

    @GET("http://lb4latest.herokuapp.com/products/searchBy/{searchTerm}")
    Call<List<ProductData>> getFilteredProducts(@Path("searchTerm") String searchTerm);

    @GET("http://lb4latest.herokuapp.com/users/login/{email}")
    Call<UserData> findByEmail(@Path("email") String email);

    @PATCH("http://lb4latest.herokuapp.com/products/{id}")
    Call<ProductData> updatePrice(@Path("id") String id, @Body ProductData productData);

    //Cloud messaging
    @Headers("Authorization : key=BIIgxlXPWeoBjdULNKYB-x3VTL5opk8Vyq8Ef1vK9xJcgKmOsLCqf74jQKpR4jDWEZU7d6_kcUvtXE9w6ATjrKQ")
    @POST("fcm/send")
    Call<ResponseBody> sendChatNotification(@Field("to") String token, @Body RequestNotification requestNotificaton);
}
