package com.wedontanything.usedmarket.Interface;

import com.wedontanything.usedmarket.Response.Response;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ProductService {

    @FormUrlEncoded
    @POST("/api/product")
    Call<Response> postProductApply(
            @Header("token") String token,
            @Field("productName") String productName,
            @Field("description") String description,
            @Field("price") int price,
            @Field("hashtag") String hashtag
    );

    @FormUrlEncoded
    @PUT("/api/product/updateProduct/:id")
    Call<Response> putUpdateProduct(
            @Header("token") String token,
            @Field("id") String id,
            @Field("userId") String userId,
            @Field("productName") String productName,
            @Field("description") String description,
            @Field("price") int price,
            @Field("hashtag") String hashtag
    );

    @DELETE("/api/product/deleteProduct/:id")
    Call<Response> deleteProduct(
            @Header("token") String token,
            @Field("id") Integer id
    );

    @GET("/api/product/myProduct?member_id=")
    Call<Response> getMyProduct(
            @Query("member_id") String member_id
    );

    @GET("/api/product/hashtag?hashtag=")
    Call<Response> getHashTag(
            @Query("hashtag") String hashtag
    );

    @GET("/api/product/searchProduct?product_name=")
    Call<Response> getSearchProduct(
            @Query("product_name") String product_name
    );

    @GET("/api/product/heartProduct?member_id=")
    Call<Response> getheartProduct(
            @Query("member_id") String member_id
    );

    @GET("/api/product/mypageProductDetails?id=")
    Call<Response> getProductInfo(
            @Query("id") Integer id
    );


}
