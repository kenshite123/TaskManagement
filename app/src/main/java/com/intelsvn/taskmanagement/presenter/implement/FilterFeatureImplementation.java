package com.intelsvn.taskmanagement.presenter.implement;

import android.app.Activity;

import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.model.entity.Feature;
import com.intelsvn.taskmanagement.model.interactor.FeatureHandler;
import com.intelsvn.taskmanagement.presenter.interfaces.FilterFeatureInterface;
import com.intelsvn.taskmanagement.view.interfaces.FilterView;

import java.util.ArrayList;

public class FilterFeatureImplementation implements FilterFeatureInterface {
    Activity activity;
    FilterView filterView;

    public FilterFeatureImplementation(Activity activity) {
        this.activity = activity;
        this.filterView = (FilterView) activity;
    }

    @Override
    public void getListFeature() {
        FeatureHandler featureHandler = new FeatureHandler(activity);
        featureHandler.getListFeature(Constant.GET_LIST_FEATURE_SPINNER);
    }

    @Override
    public void onSuccess(ArrayList<Feature> arrFeature) {
        if (filterView != null) {
            filterView.onSuccessGetListFeature(arrFeature);
        }
    }

    @Override
    public void onError(String message, int type) {
        if (filterView != null) {
            if (type == Constant.TYPE_SHOW_MESSAGE_DIALOG) {
                filterView.showDialogMessage("Thông báo", message);
            } else {
                filterView.showToastMessage(message);
            }
        }
    }
}
