package com.wedontanything.usedmarket.Interface;

import com.wedontanything.usedmarket.Product.AddProduct;
import com.wedontanything.usedmarket.Product.HeartProduct;
import com.wedontanything.usedmarket.Product.TestResponse;
import com.wedontanything.usedmarket.Response.Response;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {

    @Multipart
    @POST("/api/product/")
    Call<AddProduct> postProductApply(
            @Header("token") String token,
            @Part("productName") String productName,
            @Part("description") String description,
            @Part("price") int price,
            @Part("hashtag") String hashtag,
            @Part("category") String category,
            @Part("image") String image
    );

    @GET("/api/product/all")
    Call<TestResponse> getAllProduct (
           @Header("token") String token
    );

    @FormUrlEncoded
    @PATCH("/api/product/updateProduct/{id}")
    Call<Response> putUpdateProduct(
            @Path("id") String id,
            @Header("token") String token,
            @Field("productName") String productName,
            @Field("description") String description,
            @Field("price") int price,
            @Field("hashtag") String hashtag,
            @Field("category") String category,
            @Field("state") String state,
            @Field("image") String image
    );

    @DELETE("/api/product/deleteProduct/:id")
    Call<Response> deleteProduct(
            @Header("token") String token,
            @Field("id") Integer id
    );

    @GET("/api/product/myProduct")
    Call<TestResponse> getMyProduct(
            @Header("token") String token
    );

    @GET("/api/product/hashtag?hashtag=")
    Call<Response> getHashTag(
            @Query("hashtag") String hashtag
    );

    @GET("/api/product/searchProduct?product_name=")
    Call<Response> getSearchProduct(
            @Query("product_name") String product_name
    );

    @GET("/api/product/heartProductList")
    Call<List<HeartProduct>> getheartProduct(
            @Header("token") String token
    );

    @GET("/api/product/mypageProductDetails?id=")
    Call<Response> getProductInfo(
            @Query("id") Integer id
    );


}
