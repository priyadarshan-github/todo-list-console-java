package com.priyadarshan.todolistconsolejava;

import java.util.ArrayList;
import java.util.List;

public class ToDoManager {
        private List<Task> tasks=new ArrayList<>();
        private int nextId=1;

        public void addTask(Task task) {
            task.setId(String.valueOf(nextId++));
            tasks.add(task);
            System.out.println("Task added successfully!");

        }

        public void listTasks() {
                if (tasks.isEmpty()) {
                        System.out.println("No tasks available.");
                } else {
                        System.out.println("Listing all tasks:");
                        for (Task task : tasks) {
                                System.out.println(task);
                        }
                }

        }


}
