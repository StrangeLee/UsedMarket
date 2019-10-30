package com.wedontanything.usedmarket.Interface;

import com.wedontanything.usedmarket.Response.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface HeartService {
    @POST("/api/heart/click/{id}")
    Call<Response> postClickHeart(
            @Header("token") String token,
            @Path("id") int id
    );

    @POST("/api/heart/unClick/{id}")
    Call<Response> postUnClickHeart(
            @Header("token") String token,
            @Path("id") int id
    );
}
