package com.intelsvn.taskmanagement.presenter.interfaces;

import com.intelsvn.taskmanagement.model.entity.Feature;

import java.util.ArrayList;

public interface FilterFeatureInterface {
    void getListFeature();
    void onSuccess(ArrayList<Feature> arrFeature);
    void onError(String message, int type);
}
