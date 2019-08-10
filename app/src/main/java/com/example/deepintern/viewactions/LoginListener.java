package com.example.deepintern.viewactions;

import com.example.deepintern.models.Login;

public interface LoginListener {
    public void onLoginSuccess(Login login);
    public void onLoginError(String error);
}
