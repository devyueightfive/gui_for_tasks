/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.start;

import com.mycompany.start.gui.MainWindow;
import com.mycompany.start.worker.Info;
import com.mycompany.start.worker.Subscriber;
import com.mycompany.start.worker.Task;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author yuri
 */
public class Zapp { //

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new MainWindow();
        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }

    private void todo() {
        var app = new MainWindow(); //create app in main thread
        int n = 5; //set number of workers
        ArrayList<Thread> threads = new ArrayList<>(); //  container for workers
        for (int i = 1; i <= n; i++) { // worker creation
            Task task = new Task(); // new worker
            task.addSubscriber(app);// add subsriber , as app in main thread
            var t = new Thread(task); // worker added to new thread
            threads.add(t); //worker- thread added to container
        }
        threads.forEach((Thread t) -> {
            //start worker-threads
            t.start();
        });
        threads.forEach((Thread t) -> {
            // main thread waits all worker-threads
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Zapp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * Start point
     *
     * @param args no used
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

        System.out.println("\nMain Thread stopped."); // stop mark of main thread

    }

}
