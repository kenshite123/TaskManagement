package com.intelsvn.taskmanagement.presenter.implement;

import android.app.Activity;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.model.entity.Task;
import com.intelsvn.taskmanagement.model.interactor.TaskHandler;
import com.intelsvn.taskmanagement.presenter.interfaces.TaskInterface;
import com.intelsvn.taskmanagement.view.interfaces.MainView;

import java.util.ArrayList;

public class TaskImplementation implements TaskInterface {

    Activity activity;
    MainView mainView;
    TaskHandler taskHandler;

    public TaskImplementation(Activity activity) {
        this.activity = activity;
        this.mainView = (MainView) activity;
    }

    @Override
    public void getTaskByStatus(String status) {
        taskHandler = new TaskHandler(activity);
        taskHandler.getTaskByStatusHandler(status);
    }

    @Override
    public void getTaskFilter(String feature_id, String assignee, String taskName, String fromDate, String toDate) {
        taskHandler = new TaskHandler(activity);
    }

    @Override
    public void onGetTaskByStatusSuccess(ArrayList<Task> arrTask, String status) {
        if (mainView != null) {
            mainView.onGetTaskByStatusSuccess(arrTask, status);
        }
    }

    @Override
    public void onError(String message, int type) {
        if (mainView != null) {
            if (type == Constant.TYPE_SHOW_MESSAGE_DIALOG) {
                mainView.showDialogMessage("Thông báo", message);
            } else {
                mainView.showToastMessage(message);
            }
        }
    }
}
