package com.somnium.simplyshop.api;

import com.somnium.simplyshop.entities.Product;
import com.somnium.simplyshop.entities.ResponseModel;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface SimplyShopApi {
    @GET("/products")
    Observable<Response<ResponseModel<List<Product>>>> getProducts();

}
