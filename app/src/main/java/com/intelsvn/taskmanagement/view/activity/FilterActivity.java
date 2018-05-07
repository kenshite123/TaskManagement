package com.intelsvn.taskmanagement.view.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.intelsvn.taskmanagement.R;
import com.intelsvn.taskmanagement.adapter.SpinnerFeatureAdapter;
import com.intelsvn.taskmanagement.model.entity.Feature;
import com.intelsvn.taskmanagement.presenter.implement.FilterFeatureImplementation;
import com.intelsvn.taskmanagement.presenter.interfaces.FilterFeatureInterface;
import com.intelsvn.taskmanagement.view.interfaces.FilterView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class FilterActivity extends AppCompatActivity implements FilterView {

    Toolbar toolbar;
    Spinner spFeature;
    TextInputEditText edtAssignee, edtTaskName;
    EditText edtFromDate, edtToDate;
    Button btnFilter;

    Calendar myCalendar = Calendar.getInstance();

    SpinnerFeatureAdapter spinnerFeatureAdapter;
    FilterFeatureInterface filterFeatureInterface;

    String feature_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        initViews();
        initInterface();
        addEvents();
    }

    private void initInterface() {
        filterFeatureInterface = new FilterFeatureImplementation(this);
        filterFeatureInterface.getListFeature();
    }

    private void addEvents() {
        edtFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate(edtFromDate);
            }
        });

        edtToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate(edtToDate);
            }
        });

        spFeature.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                feature_id = spinnerFeatureAdapter.getItem(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilterActivity.this, MainActivity.class);
                intent.putExtra("TYPE", "Where");
                intent.putExtra("FEATURE_ID", feature_id);
                intent.putExtra("ASSIGNEE", edtAssignee.getText().toString());
                intent.putExtra("TASK_NAME", edtTaskName.getText().toString());
                intent.putExtra("FROM_DATE", edtFromDate.getText().toString());
                intent.putExtra("TO_DATE", edtToDate.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void chooseDate(final EditText edtDate) {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate(edtDate);

//                int mHour = myCalendar.get(Calendar.HOUR_OF_DAY);
//                int mMinute = myCalendar.get(Calendar.MINUTE);
//                TimePickerDialog timePickerDialog = new TimePickerDialog(FilterActivity.this,
//                        new TimePickerDialog.OnTimeSetListener() {
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                                myCalendar.set(Calendar.HOUR, hourOfDay);
//                                myCalendar.set(Calendar.MINUTE, minute);
//                                myCalendar.set(Calendar.SECOND, 0);
//                                updateDate(edtDate);
//                            }
//                        }, mHour, mMinute, false);
//                timePickerDialog.show();
            }

        };

        new DatePickerDialog(this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void updateDate(EditText edtDate) {
//        String myFormat = "dd/MM/yyyy HH:mm:ss"; //In which you need put here
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);

        edtDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Filter Task");

        spFeature = findViewById(R.id.spFeature);
        edtAssignee = findViewById(R.id.edtAssignee);
        edtTaskName = findViewById(R.id.edtTaskName);
        edtFromDate = findViewById(R.id.edtFromDate);
        edtToDate = findViewById(R.id.edtToDate);
        btnFilter = findViewById(R.id.btnFilter);

        edtFromDate.setFocusable(false);
        edtFromDate.setKeyListener(null);

        spinnerFeatureAdapter = new SpinnerFeatureAdapter(this, R.layout.item_spinner_feature);
        spinnerFeatureAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spFeature.setAdapter(spinnerFeatureAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onSuccessGetListFeature(ArrayList<Feature> arrFeature) {
        spinnerFeatureAdapter.addAll(arrFeature);
        spinnerFeatureAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialogMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }
}
