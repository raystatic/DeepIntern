package com.example.deepintern;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.deepintern.login.LoginActivity;
import com.example.deepintern.util.PrefManager;

public class MainActivity extends AppCompatActivity {

    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefManager = new PrefManager(this);

        if (!prefManager.getBoolean(PrefManager.LOGGGEDIN)){
            startActivity(new Intent(this, LoginActivity.class));
        }else{

        }

    }
}
