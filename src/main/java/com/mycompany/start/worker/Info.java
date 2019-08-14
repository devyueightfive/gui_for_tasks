/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.start.worker;

/**
 *
 * @author yuri
 */
public class Info {

    //
    // Fields
    //
    private int progress;    // progress
    private int bound;       // bound 
    private String name;     // name

    //
    // Accessors
    //
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    // Constructor
    //
    public Info(String name, int progress, int bound) {
        this.name = name;
        this.progress = progress;
        this.bound = bound;
    }
}
