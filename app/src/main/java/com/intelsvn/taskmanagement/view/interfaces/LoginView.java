package com.intelsvn.taskmanagement.view.interfaces;

public interface LoginView {

    void setErrorEditTextUsername(String message);
    void setErrorEditTextPassword(String message);

    void onLoginSuccess();
    void showToastMessage(String message);
    void showDialogMessage(String title, String message);
}
