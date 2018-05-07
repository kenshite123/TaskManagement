package com.intelsvn.taskmanagement.view.interfaces;

import com.intelsvn.taskmanagement.model.entity.Task;

import java.util.ArrayList;

public interface MainView {
    void onGetTaskByStatusSuccess(ArrayList<Task> arrTask, String status);

    void showToastMessage(String message);
    void showDialogMessage(String title, String message);
}
