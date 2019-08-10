package com.example.deepintern.ApiServices;

import com.example.deepintern.models.Login;
import com.example.deepintern.models.LoginPost;
import com.example.deepintern.models.Test;
import com.example.deepintern.viewactions.LoginListener;
import com.example.deepintern.viewactions.TestListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiInteractor {
    public ApiInteractor() {
    }

    ApiRequest apiRequest = new ApiRequest();

    public void getTest(final TestListener listener){
        apiRequest.getTest(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                listener.onTestSuccess((Test) response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.onTestError(t.getLocalizedMessage());
            }
        });
    }

    public void getLogin(final LoginListener listener, LoginPost loginPost){
        apiRequest.getLogin(loginPost, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                listener.onLoginSuccess((Login) response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                listener.onLoginError(t.getLocalizedMessage());
            }
        });
    }

}
