package com.intelsvn.taskmanagement.model.entity;

import java.io.Serializable;

public class Feature implements Serializable {
    private String id;
    private String name;
    private String description;
    private String owner;
    private String product_id;
    private String actual_start;
    private String actual_finish;
    private String created_date;
    private String last_updated;

    public Feature() {
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", owner='" + owner + '\'' +
                ", product_id='" + product_id + '\'' +
                ", actual_start='" + actual_start + '\'' +
                ", actual_finish='" + actual_finish + '\'' +
                ", created_date='" + created_date + '\'' +
                ", last_updated='" + last_updated + '\'' +
                '}';
    }

    public Feature(String id, String name, String description, String owner, String product_id, String actual_start, String actual_finish, String created_date, String last_updated) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.product_id = product_id;
        this.actual_start = actual_start;
        this.actual_finish = actual_finish;
        this.created_date = created_date;
        this.last_updated = last_updated;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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
