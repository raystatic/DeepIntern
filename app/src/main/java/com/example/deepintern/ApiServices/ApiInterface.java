package com.example.deepintern.ApiServices;

import com.example.deepintern.models.Login;
import com.example.deepintern.models.LoginPost;
import com.example.deepintern.models.Test;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/test")
    Call<Test> getTest();

    @POST("/user/login")
    Call<Login> getLogin(@Body LoginPost loginPost);

}
