package com.intelsvn.taskmanagement.presenter.implement;

import android.app.Activity;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.model.interactor.UserHandler;
import com.intelsvn.taskmanagement.presenter.interfaces.ResetPasswordInterface;
import com.intelsvn.taskmanagement.view.interfaces.ResetPasswordView;

public class ResetPasswordImplementation implements ResetPasswordInterface {

    Activity activity;
    ResetPasswordView resetPasswordView;

    public ResetPasswordImplementation(Activity activity) {
        this.activity = activity;
        this.resetPasswordView = (ResetPasswordView) activity;
    }

    @Override
    public void resetPassword(String username, String email, String phone) {
        UserHandler userHandler = new UserHandler(activity);
        userHandler.resetPassword(username, email, phone);
    }

    @Override
    public void onSuccessResetPassword() {
        if (resetPasswordView != null) {
            resetPasswordView.showDialogMessage("Thông báo", "Đặt lại mật khẩu thành công. Mật khẩu của bạn là 12345");
        }
    }

    @Override
    public void onError(String message, int type) {
        if (resetPasswordView != null) {
            if (type == Constant.TYPE_SHOW_MESSAGE_DIALOG) {
                resetPasswordView.showDialogMessage("Thông báo", message);
            } else {
                resetPasswordView.showToastMessage(message);
            }
        }
    }
}
