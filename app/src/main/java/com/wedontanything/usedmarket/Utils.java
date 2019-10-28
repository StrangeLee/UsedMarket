package com.wedontanything.usedmarket;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Utils {

    public static final String HOST_URL = "http://10.80.163.141:3065";

    public static Retrofit RETROFIT =
            new Retrofit.Builder()
                    .client(getClient())
                    .baseUrl(HOST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(1, TimeUnit.MINUTES);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }
}
