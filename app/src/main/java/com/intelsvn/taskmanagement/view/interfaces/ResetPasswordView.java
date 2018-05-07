package com.intelsvn.taskmanagement.view.interfaces;

public interface ResetPasswordView {

    void setErrorEditTextUsername(String message);
    void setErrorEditTextEmail(String message);
    void setErrorEditTextPhone(String message);

    void showToastMessage(String message);
    void showDialogMessage(String title, String message);

}
