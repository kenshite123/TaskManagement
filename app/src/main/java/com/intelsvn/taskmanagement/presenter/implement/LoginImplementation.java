package com.intelsvn.taskmanagement.presenter.implement;

import android.app.Activity;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.common.EncryptDecrypt;
import com.intelsvn.taskmanagement.model.interactor.UserHandler;
import com.intelsvn.taskmanagement.presenter.interfaces.LoginInterface;
import com.intelsvn.taskmanagement.view.interfaces.LoginView;

public class LoginImplementation implements LoginInterface {
    Activity activity;
    LoginView loginView;

    public LoginImplementation(Activity activity) {
        this.activity = activity;
        this.loginView = (LoginView) activity;
    }

    @Override
    public void login(String username, String password) {
        if (validate(username, password)) {
            UserHandler userHandler = new UserHandler(activity);
            userHandler.loginUser(username, EncryptDecrypt.encrypt(password, "thebestsecretkey"));
        } else {
            onLoginError("Login failed", Constant.TYPE_SHOW_MESSAGE_DIALOG);
        }
    }

    private boolean validate(String username, String password) {
        return true;
    }

    @Override
    public void onLoginSuccess() {
        if (loginView != null) {
            loginView.onLoginSuccess();
        }
    }

    @Override
    public void onLoginError(String message, int type) {
        if (loginView != null) {
            if (type == Constant.TYPE_SHOW_MESSAGE_DIALOG) {
                loginView.showDialogMessage("Thông báo", message);
            } else {
                loginView.showToastMessage(message);
            }
        }
    }
}
