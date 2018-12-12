package com.savvysherpa.savvysherpaviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.savvysherpa.loginview.LoginView;
import com.savvysherpa.loginview.listeners.LoginViewListener;

public class MainActivity extends AppCompatActivity implements LoginViewListener{
    private static String TAG = "LOGIN_LIB";
    LoginView loginView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginView = findViewById(R.id.LoginView);

        loginView.setListener(this);
    }

    @Override
    public void onLoginSubmit(String login, String password) {
        Log.i(TAG, "Login: " + login + " | Password: " + password );
        if(login.equals("error"))
            loginView.showLoginError("Username is error");

        if(password.equals("error"))
            loginView.showPasswordError("Password is error");

        if(login.equals("reset"))
            loginView.resetLoginAndPassword();
    }

    @Override
    public void onLoginTyping(String login) {
        Log.i(TAG, "Login Typing: " + login );
    }

    @Override
    public void onPasswordTyping(String password) {
        Log.i(TAG, "Password Typing: " + password );
    }

    @Override
    public void onLoginTapped() {
        Log.i(TAG, "Login Tapped");
    }

    @Override
    public void onPasswordTapped() {
        Log.i(TAG, "Password Tapped");
    }

    @Override
    public void onForgotPasswordTapped() {
        Log.i(TAG, "Forgot Password Tapped");
    }

    @Override
    public void onLogoTapped() {
        Log.i(TAG, "Logo Tapped");
    }
}
