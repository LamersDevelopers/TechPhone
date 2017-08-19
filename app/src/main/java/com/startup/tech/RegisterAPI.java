package com.startup.tech;

import com.startup.tech.Object.DetalObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;public interface RegisterAPI {
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/")
    void createUser(
                    @Field("op")String op,
                    @Field("userName")String userName,
                    @Field("pass")String pass,
                    Callback<String> User);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/")
    void getCallsCount(
            @Field("op")String op,
            @Field("userCode")String userName,
            Callback<String> User);

    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/")
    void getCalls(
            @Field("op")String op,
            @Field("userCode")String userName,
            Callback<ArrayList<Object>> User);

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/")
    @FormUrlEncoded
    void getDetalies(
            @Field("op")String op,
            Callback<ArrayList<Object>> detalies);

}
