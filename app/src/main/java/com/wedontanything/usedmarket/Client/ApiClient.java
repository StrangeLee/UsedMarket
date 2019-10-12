package com.wedontanything.usedmarket.Client;//package com.wedontanything.retrofitexample.Client;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.wedontanything.retrofitexample.Utils;
//import com.wedontanything.retrofitexample.retrofit2.Interface.ProductService;
//import com.wedontanything.retrofitexample.retrofit2.Interface.UserService;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class ApiClient {
//    private static Retrofit retrofit = null;
//
//    private static Retrofit retrofit2 = null;
//
//    private UserService user;
//
//    public ApiClient() {
//        user = Utils.RETROFIT.create(UserService.class);
//    }
//
//    public static UserService getUserClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//            retrofit = new Retrofit.Builder()
//                    .baseUrl("http://10.80.163.141:3065")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(client)
//                    .build();
//
//            return retrofit.create(UserService.class);
//    }
//
//    public static ProductService getProductClient() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//        retrofit2 = new Retrofit.Builder()
//                .baseUrl("http://10.80.163.141:3065")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build();
//
//        return retrofit2.create(ProductService.class);
//    }
//}
