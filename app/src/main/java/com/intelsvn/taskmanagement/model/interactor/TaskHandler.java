package com.intelsvn.taskmanagement.model.interactor;

import android.app.Activity;

import com.intelsvn.taskmanagement.model.entity.Task;
import com.intelsvn.taskmanagement.model.service.TaskService;
import com.intelsvn.taskmanagement.presenter.implement.TaskImplementation;
import com.intelsvn.taskmanagement.presenter.interfaces.TaskInterface;

import java.util.ArrayList;

public class TaskHandler {

    Activity activity;
    TaskInterface taskInterface;
    TaskService taskService;

    public TaskHandler(Activity activity) {
        this.activity = activity;
        taskService = new TaskService(activity);
    }

    public void getTaskByStatusHandler(String status) {
        taskInterface = new TaskImplementation(activity);

        ArrayList<Task> arrTask = taskService.getTaskByStatus(status);
        taskInterface.onGetTaskByStatusSuccess(arrTask, status);
    }

    public void getTaskFilter(String feature_id, String assignee, String taskName, String fromDate, String toDate) {
        taskInterface = new TaskImplementation(activity);

    }
}
