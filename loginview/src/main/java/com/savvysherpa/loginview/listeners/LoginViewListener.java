package com.savvysherpa.loginview.listeners;

public interface LoginViewListener {
    void onLoginSubmit(String login, String password);
    void onLoginTyping(String login);
    void onPasswordTyping(String password);
    void onLoginTapped();
    void onPasswordTapped();
    void onForgotPasswordTapped();
    void onLogoTapped();
}
