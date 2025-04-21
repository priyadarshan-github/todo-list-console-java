package com.priyadarshan.todolistconsolejava;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToDoManager {
        private List<Task> tasks=new ArrayList<>();
        private int nextId=1;

        public ToDoManager() {
                loadTasksFromFile();
        }

        public void addTask(Task task) {
            task.setId(String.valueOf(nextId++));
            tasks.add(task);
            System.out.println("Task added successfully!");
            saveTaskToFile();


        }

        public List<Task> getTasks() {
                return tasks;
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

        public Task getTaskById(String id){
                for(Task task:tasks){
                        if(task.getId().equals(id)){
                                return task;
                        }
                }
                return null;

        }

        public void saveTaskToFile() {
                try {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.registerModule(new JavaTimeModule());
                        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/tasks.json"), tasks);
                        System.out.println("Task saved to file successfully.");
                } catch (IOException e) {
                        System.out.println("Error saving task to file: " + e.getMessage());
                }
        }

        private void loadTasksFromFile(){
                try{
                        File file = new File("src/main/resources/tasks.json");
                        if (!file.exists()) {
                                System.out.println("File not found. Creating a new one.");
                                file.createNewFile();
                                return;
                        }
                        ObjectMapper mapper=new ObjectMapper();
                        mapper.registerModule(new JavaTimeModule());
                        Task[] LoadedTasks=mapper.readValue(file, Task[].class);
                        for(Task task:LoadedTasks){
                                tasks.add(task);
                                nextId=Math.max(nextId,Integer.parseInt(task.getId())+1);
                        }

                }
                catch (IOException e){
                        System.out.println("Error loading tasks from file: " + e.getMessage());
                }
        }
}
