/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.start.gui;

import com.mycompany.start.worker.Info;

/**
 *
 * @author yuri
 */
public class TaskData implements Comparable<TaskData> {
    //
    // Fields
    //

    String name;
    String status;
    int progress;
    int bound;

    public TaskData(String name, int progress, int bound) {
        this.name = name;
        this.progress = progress;
        this.bound = bound;
        this.updateStatus();
    }

    //
    // Accesssors
    //
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getBound() {
        return bound;
    }

    public void setBound(int bound) {
        this.bound = bound;
    }

    //
    // Implementations
    //
    /**
     * Compares as field {@code name}
     *
     * @param o another TaskData
     * @return
     */
    @Override
    public int compareTo(TaskData o) { // compares as 'name' fiels
        return this.name.compareTo(o.getName());
    }

    //
    // Methods
    //
    /**
     * Retrieves data from {@code info} sent by task
     *
     * @param info sent by Task
     * @return returns data
     */
    TaskData getData(Info info) {
        this.setName(info.getName());
        this.setBound(info.getBound());
        this.setProgress(info.getProgress());
        this.updateStatus();
        return this;
    }

    /**
     * Get field by index
     *
     * @param index
     * @return
     */
    Object get(int index) {
        switch (index) {
            case 0:
                return name;
            case 1:
                return status;
            case 2:
                return progress;
            case 3:
                return bound;
            default:
                return null;
        }
    }

    //
    // Other methods
    //
    public void updateStatus() {
        this.setStatus((progress < bound) ? "Active" : "Completed");
    }
}
