package com.intelsvn.taskmanagement.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.intelsvn.taskmanagement.R;
import com.intelsvn.taskmanagement.model.entity.Feature;

public class SpinnerFeatureAdapter extends ArrayAdapter<Feature> {
    Activity activity;
    int resource;
    public SpinnerFeatureAdapter(@NonNull Activity activity, int resource) {
        super(activity, resource);
        this.activity = activity;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(this.resource, null);
        Feature feature = this.getItem(position);
        TextView txtItemSpinner = view.findViewById(R.id.txtItemSpinner);
        txtItemSpinner.setText(feature.getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(this.getItem(position).getName());

        return label;
    }
}
