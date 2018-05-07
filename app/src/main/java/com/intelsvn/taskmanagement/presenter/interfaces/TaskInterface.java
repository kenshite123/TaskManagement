package com.intelsvn.taskmanagement.presenter.interfaces;

import com.intelsvn.taskmanagement.model.entity.Task;

import java.util.ArrayList;

public interface TaskInterface {
    void getTaskByStatus(String status);
    void getTaskFilter(String feature_id, String assignee, String taskName, String fromDate, String toDate);
    void onGetTaskByStatusSuccess(ArrayList<Task> arrTask, String status);
    void onError(String message, int type);
}
