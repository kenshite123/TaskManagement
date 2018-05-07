package com.intelsvn.taskmanagement.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.intelsvn.taskmanagement.R;
import com.intelsvn.taskmanagement.common.MyItemDecoration;
import com.intelsvn.taskmanagement.adapter.TaskAdapter;
import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.model.entity.Task;
import com.intelsvn.taskmanagement.presenter.implement.TaskImplementation;
import com.intelsvn.taskmanagement.presenter.interfaces.TaskInterface;
import com.intelsvn.taskmanagement.view.interfaces.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    TaskInterface taskInterface;
    RecyclerView lstTaskOpen, lstTaskInProgress, lstTaskDone;
    List<Task> arrTaskOpen, arrTaskInProgress, arrTaskDone;
    TaskAdapter taskOpenAdapter, taskInProgressAdapter, taskDoneAdapter;
    String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        type = getIntent().getStringExtra("TYPE");

        initViews();
        initInterface();
    }

    private void initInterface() {
        taskInterface = new TaskImplementation(this);
        if (type.equals("All")) {
            taskInterface.getTaskByStatus(Constant.TASK_OPEN_STATUS);
            taskInterface.getTaskByStatus(Constant.TASK_IN_PROGRESS_STATUS);
            taskInterface.getTaskByStatus(Constant.TASK_DONE_STATUS);
        } else if (type.equals("Where")){
            String feature_id = getIntent().getStringExtra("FEATURE_ID");
            String assignee = getIntent().getStringExtra("ASSIGNEE");
            String taskName = getIntent().getStringExtra("TASK_NAME");
            String fromDate = getIntent().getStringExtra("FROM_DATE");
            String toDate = getIntent().getStringExtra("TO_DATE");
            Log.d("MainActivity", "feature_id: " + feature_id +
                    "\nassignee: " + assignee +
                    "\ntaskName: " + taskName +
                    "\nfromDate: " + fromDate +
                    "\ntoDate: " + toDate);
        } else {

        }
    }

    private void initViews() {
        lstTaskOpen = findViewById(R.id.lstTaskOpen);
        lstTaskInProgress = findViewById(R.id.lstTaskInProgress);
        lstTaskDone = findViewById(R.id.lstTaskDone);

//        lstTaskOpen.setHasFixedSize(true);
        LinearLayoutManager llmOpen = new LinearLayoutManager(this);
        llmOpen.setOrientation(LinearLayoutManager.VERTICAL);
        lstTaskOpen.setLayoutManager(llmOpen);
        lstTaskOpen.addItemDecoration(new MyItemDecoration(this));
        arrTaskOpen = new ArrayList<>();
        taskOpenAdapter = new TaskAdapter(this, arrTaskOpen);
        lstTaskOpen.setAdapter(taskOpenAdapter);

//        lstTaskInProgress.setHasFixedSize(true);
        LinearLayoutManager llmInProgress = new LinearLayoutManager(this);
        llmInProgress.setOrientation(LinearLayoutManager.VERTICAL);
        lstTaskInProgress.setLayoutManager(llmInProgress);
        lstTaskInProgress.addItemDecoration(new MyItemDecoration(this));
        arrTaskInProgress = new ArrayList<>();
        taskInProgressAdapter = new TaskAdapter(this, arrTaskInProgress);
        lstTaskInProgress.setAdapter(taskInProgressAdapter);

//        lstTaskDone.setHasFixedSize(true);
        LinearLayoutManager llmDone = new LinearLayoutManager(this);
        llmDone.setOrientation(LinearLayoutManager.VERTICAL);
        lstTaskDone.setLayoutManager(llmDone);
        lstTaskDone.addItemDecoration(new MyItemDecoration(this));
        arrTaskDone = new ArrayList<>();
        taskDoneAdapter = new TaskAdapter(this, arrTaskDone);
        lstTaskDone.setAdapter(taskDoneAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuFilter:
                startActivity(new Intent(this, FilterActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onGetTaskByStatusSuccess(ArrayList<Task> arrTask, String status) {
        if (status.equals(Constant.TASK_OPEN_STATUS)) {
            arrTaskOpen.addAll(arrTask);
            taskOpenAdapter.notifyDataSetChanged();
        } else if (status.equals(Constant.TASK_IN_PROGRESS_STATUS)) {
            arrTaskInProgress.addAll(arrTask);
            taskInProgressAdapter.notifyDataSetChanged();
        } else if (status.equals(Constant.TASK_DONE_STATUS)) {
            arrTaskDone.addAll(arrTask);
            taskDoneAdapter.notifyDataSetChanged();
        } else {

        }
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
