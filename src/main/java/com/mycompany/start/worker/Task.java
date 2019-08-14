/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.start.worker;

import com.mycompany.start.Zapp;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yuri
 */
public class Task implements Runnable {

    // class for worker
    //
    // Fields
    //
    ArrayList<Subscriber> subscribers; // to notify about new data

    //constructor
    public Task() {
        subscribers = new ArrayList<>();
    }

    //
    // Methods
    //
    /**
     * Adds sub to the list
     *
     * @param sub
     */
    public void addSubscriber(Subscriber sub) {
        subscribers.add(sub); //adds to the list of subs
    }

    /**
     * Notifies about new data
     *
     * @param newInfo
     */
    private void notifySubscribers(Info newInfo) {
        this.subscribers.forEach((sub) -> {
            sub.refresh(newInfo); //notify subscribers
        });
    }

    /**
     * Performs general work (implement Runnable)
     */
    @Override
    public void run() {
        // Determines parameters of work
        int step = ThreadLocalRandom.current().nextInt(1, 3);
        int bound = ThreadLocalRandom.current().nextInt(10, 30);
        int progress = 0;
        Info newInfo = new Info(Thread.currentThread().getName(), progress, bound);
        int duration = ThreadLocalRandom.current().nextInt(500, 1500); // ms to sleep
        // Main loop of work
        int k = 0;
        while (bound - k * step > 0) {
            newInfo.setProgress(progress); // set new data
            this.notifySubscribers(newInfo); // notify subs about new data
            progress = progress + step;
            k++;
            try {
                Thread.sleep(duration); // work immitation
            } catch (InterruptedException ex) {
                Logger.getLogger(Zapp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Work is finished
        newInfo.setProgress(bound);
        this.notifySubscribers(newInfo);
    }

}
