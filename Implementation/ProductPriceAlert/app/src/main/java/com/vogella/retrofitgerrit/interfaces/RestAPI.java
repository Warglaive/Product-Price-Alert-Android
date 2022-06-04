package com.vogella.retrofitgerrit.interfaces;

import com.models.SendNotificationModel;
import com.vogella.retrofitgerrit.ProductData;
import com.vogella.retrofitgerrit.UserData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
    @Headers({"Authorization: key=" + "AAAAs8iUhBw:APA91bEMSHh6PCV0i27jd4q0LxRaWauVqqnBOCzZlEFyIUSwOXmt4FcAt2Un3zFsxSNUCChlipcDbcv_ZY2hXFQO0wSUHWe2VzzK552-GwNF2LZH16qHW3-qgzuGjrndsmkD_7d0xeWr", "Content-Type:application/json"})
    @POST("https://fcm.googleapis.com/fcm/send")
    Call<ResponseBody> sendChatNotification(@Body SendNotificationModel model);
}
