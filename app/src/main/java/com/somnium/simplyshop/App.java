package com.somnium.simplyshop;

import android.app.Application;

import com.somnium.simplyshop.api.SimplyShopApi;
import com.somnium.simplyshop.entities.Jwtoken;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static SimplyShopApi simplyShopAPI;
    private static final int CONNECT_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 30;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        simplyShopAPI = createSimplyShopApi(interceptor);
    }

    private SimplyShopApi createSimplyShopApi(HttpLoggingInterceptor interceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Jwtoken token = Jwtoken.getToken(getApplicationContext());
            Request request = original.newBuilder()
                    .header("Authorization", token != null ? "Bearer " + token.getAuth_token() : "")
                    .header("Source", "android")
                    .header("Version", BuildConfig.VERSION_NAME)
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        OkHttpClient client = httpClient.addInterceptor(interceptor).connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS).readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).build();
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
