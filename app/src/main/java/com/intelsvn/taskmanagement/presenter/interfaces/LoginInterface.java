package com.intelsvn.taskmanagement.presenter.interfaces;

public interface LoginInterface {

    void login(String username, String password);
    void onLoginSuccess();
    void onLoginError(String message, int type);

}
