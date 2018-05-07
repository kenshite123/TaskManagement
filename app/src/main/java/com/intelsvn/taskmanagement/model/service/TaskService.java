package com.intelsvn.taskmanagement.model.service;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.common.Support;
import com.intelsvn.taskmanagement.model.entity.Task;

import java.util.ArrayList;

public class TaskService {

    Activity activity;
    SQLiteDatabase database = null;

    public TaskService(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Task> getTaskByStatus(String status) {
        ArrayList<Task> arrTask = new ArrayList<>();
        database = activity.openOrCreateDatabase(Constant.DATABASE_NAME, Context.MODE_PRIVATE, null);
        String query = "select * from task where status='" + status + "'";
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setName(cursor.getString(1));
            task.setDescription(cursor.getString(2));
            task.setFeature_id(cursor.getString(3));
            task.setAssignee(cursor.getString(4));
            task.setPlan_start(cursor.getString(5));
            task.setPlan_finish(cursor.getString(6));
            task.setActual_start(cursor.getString(7));
            task.setActual_finish(cursor.getString(8));
            task.setStatus(cursor.getString(9));
            task.setResolution(cursor.getString(10));
            task.setIsDeleted(cursor.getInt(11));
            task.setCreated_date(cursor.getString(12));
            task.setLast_updated(cursor.getString(13));
            arrTask.add(task);
        }

        return arrTask;
    }

    public ArrayList<Task> getTaskFilter(String feature_id, String assignee, String taskName, String fromDate, String toDate) {
        ArrayList<Task> arrTask = new ArrayList<>();
        database = activity.openOrCreateDatabase(Constant.DATABASE_NAME, Context.MODE_PRIVATE, null);

        StringBuilder query = new StringBuilder();
        ArrayList<String> params = new ArrayList<>();

        query.append("select * from task where 1 = 1 ");
        if (!feature_id.isEmpty()) {
            query.append(" and feature_id = ? ");
            params.add(feature_id);
        }

        if (!assignee.isEmpty()) {
            query.append(" and assignee like '%?%' ");
            params.add(assignee);
        }

        if (!taskName.isEmpty()) {
            query.append(" and name like '%?%' ");
            params.add(taskName);
        }

        if (!fromDate.isEmpty()) {
            query.append(" and substr(actual_start, 1, 10) >= substr(?, 1, 10) ");
            params.add(fromDate);
        }

        if (!toDate.isEmpty()) {
            query.append(" and substr(actual_start, 1, 10) <= substr(?, 1, 10) ");
            params.add(toDate);
        }

        Cursor cursor = database.rawQuery(query.toString(), Support.convertParams(params));
        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setName(cursor.getString(1));
            task.setDescription(cursor.getString(2));
            task.setFeature_id(cursor.getString(3));
            task.setAssignee(cursor.getString(4));
            task.setPlan_start(cursor.getString(5));
            task.setPlan_finish(cursor.getString(6));
            task.setActual_start(cursor.getString(7));
            task.setActual_finish(cursor.getString(8));
            task.setStatus(cursor.getString(9));
            task.setResolution(cursor.getString(10));
            task.setIsDeleted(cursor.getInt(11));
            task.setCreated_date(cursor.getString(12));
            task.setLast_updated(cursor.getString(13));
            arrTask.add(task);
        }

        return arrTask;
    }
}
