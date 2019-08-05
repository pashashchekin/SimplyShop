package com.somnium.simplyshop;

import android.app.Application;

import com.somnium.simplyshop.api.SimplyShopApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static SimplyShopApi simplyShopAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        simplyShopAPI = createSimplyShopApi(interceptor);
    }

    private SimplyShopApi createSimplyShopApi(HttpLoggingInterceptor interceptor) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit.create(SimplyShopApi.class);
    }

    public static SimplyShopApi getSimplyShopApi(){
        return simplyShopAPI;
    }
}
