package com.MadeInMyHome.WebService;

import android.util.Base64;

import androidx.annotation.NonNull;

import com.MadeInMyHome.utilities.constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class RestClientEmail {


    private static final String API_USERNAME = "api";

    private static final String API_PASSWORD = "03a6801fad7a55e6d16cd3d9508e6547-76f111c4-c06f95f0";

    private static final String AUTH = "Basic " + Base64.encodeToString((API_USERNAME + ":" + API_PASSWORD).getBytes(), Base64.NO_WRAP);

    public static AppApi getService() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .header("Authorization", AUTH)
                                .method(chain.request().method(), chain.request().body())
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(constants.BASE_HOST_EMAIL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(AppApi.class);


    }


}
