package com.intelsvn.taskmanagement.model.service;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.common.EncryptDecrypt;

public class UserService {

    Activity activity;
    SQLiteDatabase database = null;

    public UserService(Activity activity) {
        this.activity = activity;
    }

    public boolean loginUser(String username, String password) {
        database = activity.openOrCreateDatabase(Constant.DATABASE_NAME, Context.MODE_PRIVATE, null);
        String query = "select count(*) from user where username='" + username + "' and password='" + password + "'";
        Cursor cursor = database.rawQuery(query, null);
        int count = 0;
        while (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getUserToResetPassword(String username, String email, String phone) {
        database = activity.openOrCreateDatabase(Constant.DATABASE_NAME, Context.MODE_PRIVATE, null);
        String query = "select count(*) from user where username='" + username + "' and email='" + email + "' and phone='" + phone + "'";
        Cursor cursor = database.rawQuery(query, null);
        int count = 0;
        while (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void resetPassword(String username) {
        database = activity.openOrCreateDatabase(Constant.DATABASE_NAME, Context.MODE_PRIVATE, null);
        String query = "update user set password='" + EncryptDecrypt.encrypt("12345", "thebestsecretkey") + "' where username='" + username + "'";
        database.execSQL(query);
    }
}
