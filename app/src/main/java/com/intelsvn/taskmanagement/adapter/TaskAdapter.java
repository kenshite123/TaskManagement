package com.intelsvn.taskmanagement.adapter;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intelsvn.taskmanagement.R;
import com.intelsvn.taskmanagement.common.Constant;
import com.intelsvn.taskmanagement.model.entity.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    Activity activity;
    List<Task> arrTask;

    public TaskAdapter(Activity activity, List<Task> arrTask) {
        this.activity = activity;
        this.arrTask = arrTask;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.layout_item_task, null, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        final Task task = arrTask.get(position);
        if (task.getIsDeleted() == 1) {
            holder.llItemTask.setVisibility(View.GONE);
        } else {
            holder.llItemTask.setVisibility(View.VISIBLE);
            holder.txtTaskName.setText(task.getName());
            holder.txtAssignee.setText(task.getAssignee());
            holder.txtTaskDueDate.setText(task.getActual_finish());
            try {
                if (task.getStatus().equals(Constant.TASK_DONE_STATUS)) {

                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dueDate = sdf.parse(task.getActual_finish());
                    Date today = new Date();
                    if (dueDate.compareTo(today) < 0) { // dueDate < today
                        holder.llItemTask.setBackgroundResource(R.drawable.background_item_task_red);
                    } else if (dueDate.compareTo(today) == 0) { // dueDate = today
                        holder.llItemTask.setBackgroundResource(R.drawable.background_item_task_orange);
                    } else {

                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        holder.cvTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrTask.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llItemTask, llItemCard;
        CardView cvTask;
        TextView txtTaskName, txtAssignee, txtTaskDueDate;

        public TaskViewHolder(View itemView) {
            super(itemView);
            llItemTask = itemView.findViewById(R.id.llItemTask);
            llItemCard = itemView.findViewById(R.id.llItemCard);
            cvTask = itemView.findViewById(R.id.cvTask);
            cvTask.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            txtTaskName = itemView.findViewById(R.id.txtTaskName);
            txtAssignee = itemView.findViewById(R.id.txtAssignee);
            txtTaskDueDate = itemView.findViewById(R.id.txtTaskDueDate);
        }

    }
}