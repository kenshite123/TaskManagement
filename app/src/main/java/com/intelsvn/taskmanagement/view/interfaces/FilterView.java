package com.intelsvn.taskmanagement.view.interfaces;

import com.intelsvn.taskmanagement.model.entity.Feature;

import java.util.ArrayList;

public interface FilterView {
    void onSuccessGetListFeature(ArrayList<Feature> arrFeature);
    void showToastMessage(String message);
    void showDialogMessage(String title, String message);
}
