package com.example.deepintern.ApiServices;

import android.util.Log;

import com.example.deepintern.models.LoginPost;

import retrofit2.Callback;

public class ApiRequest {
    ApiInterface apiInterface=ApiClient.getApiClient().create(ApiInterface.class);

    public ApiRequest() {
    }

    public void getTest(Callback callback){
        apiInterface.getTest().enqueue(callback);
    }

    public void getLogin(LoginPost loginPost, Callback callback){
        apiInterface.getLogin(loginPost).enqueue(callback);
        Log.d("req_url",apiInterface.getLogin(loginPost).request().method()+"," + apiInterface.getLogin(loginPost).request().body()+","+apiInterface.getLogin(loginPost).request().url());
    }

}
