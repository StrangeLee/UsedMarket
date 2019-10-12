package com.wedontanything.usedmarket.Interface;

import com.wedontanything.usedmarket.Response.Response;
import com.wedontanything.usedmarket.User.UpdateEmail;
import com.wedontanything.usedmarket.User.UpdatePhoneNumber;
import com.wedontanything.usedmarket.User.UpdateSchoolName;
import com.wedontanything.usedmarket.User.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface UserService {
//    @GET("/api")
//    void test(String sort, Callback<String> callback);
    @FormUrlEncoded
    @POST("/api/user/")
    Call<User> postSignup(
            @Field("id") String id,
            @Field("name") String name,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phoneNumber") String phoneNumber,
            @Field("schoolName") String schoolName
    );

    @FormUrlEncoded
    @POST("/api/user/findId")
    Call<User> postFindId(
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("/api/user/findPassword")
    Call<Response> postFindPassword(
            @Field("id") String id,
            @Field("updatePassword") String updatePassword
    );

//    @PUT("/api/register/updatePassword")
//    Call<UpdatePassword> putUpdatePassword (
//        @Query("id") String id,
//        @Query("password") String password,
//        @Query("updatePassword") String updatePassword
//    );

    @FormUrlEncoded
    @PATCH("/api/user/updateEmail")
    Call<UpdateEmail> putUpdateEmail(
            @Field("id") String id,
            @Field("email") String email,
            @Field("updateEmail") String updateEmail
    );

    @FormUrlEncoded
    @PATCH("/api/user/updatePhoneNumber")
    Call<UpdatePhoneNumber> putUpdatePhoneNumber(
            @Field("id") String id,
            @Field("phoneNumber") String phoneNumber,
            @Field("updatePhoneNumber") String updatePhoneNumber
    );

    @FormUrlEncoded
    @PATCH("/api/user/updateSchool")
    Call<UpdateSchoolName> putUpdateSchool(
            @Field("id") String member_id,
            @Field("updateSchoolName") String updateSchoolName
    );

//    @DELETE("api/user/deleteUser")
//    Call<User> deleteMember (
//        @HTTP(method = DELETE, path = "")
//    );

    @FormUrlEncoded
    @POST("/api/user/login")
    Call<retrofit2.Response<Response<User>>> postLogin(
            @Field("id") String id,
            @Field("password") String password
    );
}
