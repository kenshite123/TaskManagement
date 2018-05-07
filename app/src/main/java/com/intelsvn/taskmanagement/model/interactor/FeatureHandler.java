package com.intelsvn.taskmanagement.model.interactor;

import android.app.Activity;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.model.entity.Feature;
import com.intelsvn.taskmanagement.model.service.FeatureService;
import com.intelsvn.taskmanagement.presenter.implement.FilterFeatureImplementation;
import com.intelsvn.taskmanagement.presenter.interfaces.FilterFeatureInterface;

import java.util.ArrayList;

public class FeatureHandler {
    Activity activity;
    FilterFeatureInterface filterFeatureInterface;
    FeatureService featureService;

    public FeatureHandler(Activity activity) {
        this.activity = activity;
        featureService = new FeatureService(activity);
    }

    public void getListFeature(int type) {
        ArrayList<Feature> arrFeature = featureService.getListFeature();
        switch (type) {
            case Constant.GET_LIST_FEATURE_SPINNER:
                filterFeatureInterface = new FilterFeatureImplementation(activity);
                if (arrFeature.size() > 0) {
                    filterFeatureInterface.onSuccess(arrFeature);
                } else {
                    filterFeatureInterface.onError("Không có dữ liệu của Feature", Constant.TYPE_SHOW_MESSAGE_TOAST);
                }
                break;
            default:
                break;
        }
    }
}
