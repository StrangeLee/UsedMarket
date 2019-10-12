package com.wedontanything.usedmarket.Interface;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CommentService {
    @FormUrlEncoded
    @POST("/api/comment/createComment")
    Call<Response> postCreateComment(
            @Field("member_id") String member_id,
            @Field("content") String content
    );

    @DELETE("/api/comment/deleteComment")
    Call<Response> deleteComment(
            @Field("member_id") String member_id,
            @Field("content") String content
    );
}
