package com.somnium.simplyshop.api;

import com.somnium.simplyshop.entities.Product;
import com.somnium.simplyshop.entities.ResponseModel;
import com.somnium.simplyshop.entities.UserCreate;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SimplyShopApi {
    @GET("/products")
    Observable<Response<ResponseModel<List<Product>>>> getProducts();

    @POST("auth")
    Observable<Response<ResponseModel<UserCreate>>> auth(@Body UserCreate user);


}
