package com.intelsvn.taskmanagement.model.service;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.model.entity.Feature;

import java.util.ArrayList;

public class FeatureService {

    Activity activity;
    SQLiteDatabase database = null;

    public FeatureService(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Feature> getListFeature() {
        ArrayList<Feature> arrFeature = new ArrayList<>();

        database = activity.openOrCreateDatabase(Constant.DATABASE_NAME, Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("select * from feature");
        Cursor cursor = database.rawQuery(sql.toString(), null);
        while (cursor.moveToNext()) {
            Feature feature = new Feature();
            feature.setId(cursor.getString(0));
            feature.setName(cursor.getString(1));
            feature.setDescription(cursor.getString(2));
            feature.setOwner(cursor.getString(3));
            feature.setProduct_id(cursor.getString(4));
            feature.setActual_start(cursor.getString(5));
            feature.setActual_finish(cursor.getString(6));
            feature.setCreated_date(cursor.getString(7));
            feature.setLast_updated(cursor.getString(8));
            arrFeature.add(feature);
        }

        return arrFeature;
    }
}
