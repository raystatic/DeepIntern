package com.example.deepintern.viewactions;

import com.example.deepintern.models.Test;

public interface TestListener {
    public void onTestSuccess(Test response);
    public void onTestError(String error);
}
