package com.intelsvn.taskmanagement.presenter.interfaces;

public interface ResetPasswordInterface {
    void resetPassword(String username, String email, String phone);
    void onSuccessResetPassword();
    void onError(String message, int type);
}
