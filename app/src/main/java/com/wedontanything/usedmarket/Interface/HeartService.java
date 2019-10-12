package com.wedontanything.usedmarket.Interface;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HeartService {
    @FormUrlEncoded
    @POST("/api/heart/click/:id")
    Call<Response> postClickHeart(
            @Field("product_id") String product_id,
            @Field("member_id") String member_id
    );

    @FormUrlEncoded
    @POST("/api/heart/unClick/:id")
    Call<Response> postUnClickHeart(
            @Field("product_id") String product_id,
            @Field("member_id") String member_id
    );
}
