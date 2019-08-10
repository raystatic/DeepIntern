package com.example.deepintern.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.deepintern.ApiServices.ApiInteractor;
import com.example.deepintern.MainActivity;
import com.example.deepintern.R;
import com.example.deepintern.models.Login;
import com.example.deepintern.models.LoginPost;
import com.example.deepintern.models.Test;
import com.example.deepintern.util.PrefManager;
import com.example.deepintern.viewactions.LoginListener;
import com.example.deepintern.viewactions.TestListener;


public class LoginActivity extends AppCompatActivity implements TestListener, LoginListener {

    EditText et_username, et_password;
    Button btn_submit;
    String username = "", password="";
    ApiInteractor apiInteractor = new ApiInteractor();
    LoginPost loginPost = new LoginPost();
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefManager = new PrefManager(this);

        et_username = findViewById(R.id.login_username);
        et_password = findViewById(R.id.login_pass);
        btn_submit = findViewById(R.id.login_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = et_username.getText().toString();
                password = et_password.getText().toString();

             //   callTest();


                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) ){
                    loginPost.setEmail(username);
                    loginPost.setPassword(password);
                    callLogin(loginPost);
                }else{
                    Toast.makeText(getApplicationContext(),"fill credentials carefully", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void callLogin(LoginPost loginPost) {
        apiInteractor.getLogin(this,loginPost);
    }

    @Override
    public void onLoginSuccess(Login login) {
        try {
            if (login.getData().getEmail().equalsIgnoreCase(username)){
                prefManager.saveString(PrefManager.EMAIL, username);
                prefManager.saveString(PrefManager.PASSWORD, password);
                prefManager.saveString(PrefManager.UID, login.getData().getId());
                prefManager.saveBoolean(PrefManager.LOGGGEDIN, true);
                startActivity(new Intent(this, MainActivity.class));
            }
        }catch (Exception e){
            Log.d("login_error",e.getLocalizedMessage());
            Toast.makeText(getApplicationContext(),"invalid email or password!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginError(String error) {
        Log.d("login_data",error);
        Toast.makeText(getApplicationContext(),"something went wrong!", Toast.LENGTH_SHORT).show();
    }

    private void callTest() {
        apiInteractor.getTest(this);
    }

    @Override
    public void onTestSuccess(Test response) {
        Log.d("test_data",response.getServer_name()+"\n"+response.getMethod()+"\n"+response.getPort()+"\n"+
                response.getTime()+"\n"+response.getUrl()+"\n"+response.getStatus());
    }

    @Override
    public void onTestError(String error) {
        Log.d("test_data",error);

    }
}
