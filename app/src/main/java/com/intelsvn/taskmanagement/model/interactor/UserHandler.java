package com.intelsvn.taskmanagement.model.interactor;

import android.app.Activity;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.model.service.UserService;
import com.intelsvn.taskmanagement.presenter.implement.LoginImplementation;
import com.intelsvn.taskmanagement.presenter.implement.ResetPasswordImplementation;
import com.intelsvn.taskmanagement.presenter.interfaces.LoginInterface;
import com.intelsvn.taskmanagement.presenter.interfaces.ResetPasswordInterface;

public class UserHandler {
    Activity activity;
    UserService userService;

    ResetPasswordInterface resetPasswordInterface;
    LoginInterface loginInterface;

    public UserHandler(Activity activity) {
        this.activity = activity;
        userService = new UserService(activity);
    }

    public void loginUser(String username, String password) {
        boolean isLoginSuccess = userService.loginUser(username, password);
        loginInterface = new LoginImplementation(activity);
        if (isLoginSuccess) {
            loginInterface.onLoginSuccess();
        } else {
            loginInterface.onLoginError("Tên đăng nhập hoặc mật khẩu không đúng. Vui lòng kiểm tra lại", Constant.TYPE_SHOW_MESSAGE_DIALOG);
        }
    }

    public void resetPassword(String username, String email, String phone) {
        resetPasswordInterface = new ResetPasswordImplementation(activity);
        if (userService.getUserToResetPassword(username, email, phone)) {
            userService.resetPassword(username);
            resetPasswordInterface.onSuccessResetPassword();
        } else {
            resetPasswordInterface.onError("Thông tin nhập vào không đúng. Vui lòng kiểm tra lại.", Constant.TYPE_SHOW_MESSAGE_DIALOG);
        }
    }
}
