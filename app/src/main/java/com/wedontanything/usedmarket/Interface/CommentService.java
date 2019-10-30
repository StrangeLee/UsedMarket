package com.wedontanything.usedmarket.Interface;

import com.wedontanything.usedmarket.Comment.CommentList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @GET("/api/comment/list/{id}")
    Call<List<CommentList>> getCommentList(
            @Header("token") String token,
            @Path("id") int id
    );
}
