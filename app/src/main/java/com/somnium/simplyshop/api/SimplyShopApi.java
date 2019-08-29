package com.somnium.simplyshop.api;

import com.somnium.simplyshop.entities.LoginModel;
import com.somnium.simplyshop.entities.Product;
import com.somnium.simplyshop.entities.ResponseModel;
import com.somnium.simplyshop.entities.UserCreate;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SimplyShopApi {
    @GET("products")
    Observable<Response<ResponseModel<List<Product>>>> getProducts(@Header("Authorization") String authorization);

    @POST("auth")
    Observable<Response<ResponseModel<UserCreate>>> auth(@Body LoginModel user);

    @POST("users/registration")
    Observable<Response<ResponseModel<UserCreate>>> registration(@Body LoginModel user);

    @POST("users/forgetPassword")
    Observable<Response<ResponseModel<UserCreate>>> resetPassword(@Query("name") String name, @Query("email") String email);


}
