package com.wedontanything.usedmarket.Interface;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface AdminService {

    @GET("/api/admin/reportList")
    Call<Response> getReportList(
            // api 정보 x
    );

    @PUT("/api/admin/updateUser")
    Call<Response> putUpdateUser(
            @Field("member_id") String member_id,
            @Field("name") String name,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone_number") String phone_number,
            @Field("school_name") String school_name
    );

    @DELETE("/api/admin/deleteUser")
    Call<Response> deleteUser(
            // id
    );

    @GET("/api/admin/productList")
    Call<Response> getProductList(
            // api 정보 x
    );


}
