/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.start.gui;

import com.mycompany.start.worker.Info;
import com.mycompany.start.worker.Subscriber;
import com.mycompany.start.worker.Task;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

/**
 * Data model for the table of tasks
 *
 * @author yuri
 */
class MyTableModel extends AbstractTableModel implements Subscriber {

    //
    // Fields
    //
    ArrayList<Task> tasks; // list of tasks

    private final String[] columnNames = { // list of columns
        "Name",
        "Status",
        "Progress",
        "Bound"};

    ArrayList<TaskData> data = new ArrayList(); // list of task info

    //
    // Constructor
    //
    public MyTableModel() {
        tasks = new ArrayList();
    }

    //
    // Implementations
    //
    @Override
    public int getRowCount() {
        return columnNames.length;
    }

    @Override
    public int getColumnCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex).get(columnIndex);
    }

    @Override
    public void refresh(Info newInfo) {
        String name = newInfo.getName();
        TaskData td = searchTaskByName(name);
        if (td == null) {
            return;
        }
        td.setProgress(newInfo.getProgress());
    }

    //
    // Methods
    //
    public void addTask() {
        Task t = new Task();
        t.addSubscriber(this);
    }

    public void runTask(Task t) {
        new Thread(t).start();
    }

    //
    // OtherMethods
    //
    /**
     * Search task in data by {@code name}
     *
     * @param name
     * @return
     */
    private TaskData searchTaskByName(String name) {
        return data.stream()
                .filter(td -> td.getName().equals(name))
                .findAny()
                .orElse(null);
    }

}
