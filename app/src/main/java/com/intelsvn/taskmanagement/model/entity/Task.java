package com.intelsvn.taskmanagement.model.entity;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private String description;
    private String feature_id;
    private String assignee;
    private String plan_start;
    private String plan_finish;
    private String actual_start;
    private String actual_finish;
    private String status;
    private String resolution;
    private int isDeleted;
    private String created_date;
    private String last_updated;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", feature_id='" + feature_id + '\'' +
                ", assignee='" + assignee + '\'' +
                ", plan_start='" + plan_start + '\'' +
                ", plan_finish='" + plan_finish + '\'' +
                ", actual_start='" + actual_start + '\'' +
                ", actual_finish='" + actual_finish + '\'' +
                ", status='" + status + '\'' +
                ", resolution='" + resolution + '\'' +
                ", isDeleted=" + isDeleted +
                ", created_date='" + created_date + '\'' +
                ", last_updated='" + last_updated + '\'' +
                '}';
    }

    public Task() {

    }

    public Task(int id, String name, String description, String feature_id, String assignee, String plan_start, String plan_finish, String actual_start, String actual_finish, String status, String resolution, int isDeleted, String created_date, String last_updated) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.feature_id = feature_id;
        this.assignee = assignee;
        this.plan_start = plan_start;
        this.plan_finish = plan_finish;
        this.actual_start = actual_start;
        this.actual_finish = actual_finish;
        this.status = status;
        this.resolution = resolution;
        this.isDeleted = isDeleted;
        this.created_date = created_date;
        this.last_updated = last_updated;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeature_id() {
        return feature_id;
    }

    public void setFeature_id(String feature_id) {
        this.feature_id = feature_id;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getPlan_start() {
        return plan_start;
    }

    public void setPlan_start(String plan_start) {
        this.plan_start = plan_start;
    }

    public String getPlan_finish() {
        return plan_finish;
    }

    public void setPlan_finish(String plan_finish) {
        this.plan_finish = plan_finish;
    }

    public String getActual_start() {
        return actual_start;
    }

    public void setActual_start(String actual_start) {
        this.actual_start = actual_start;
    }

    public String getActual_finish() {
        return actual_finish;
    }

    public void setActual_finish(String actual_finish) {
        this.actual_finish = actual_finish;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }
}
