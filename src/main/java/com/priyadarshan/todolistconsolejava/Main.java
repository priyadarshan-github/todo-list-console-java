package com.priyadarshan.todolistconsolejava;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner= new Scanner(System.in);
    public static final ToDoManager manager=new ToDoManager();

    public static void main(String[] args) {
        boolean running= true;

        while(running){
            printMenu();
            String choice = scanner.nextLine().trim();
            System.out.println("You selected: " + choice);

            switch (choice) {
                case "1":   handleAddTask(); break;
                case "2":   handleListTasks(); break;
                case "3":
                    System.out.println("Marking the task as completed...");
                    System.out.println("Enter the task ID to mark as completed:");
                    String taskId =scanner.nextLine();
                    Task taskToComplete=manager.getTaskById(taskId);
                    if(taskToComplete!=null){
                        taskToComplete.setStatus(TaskStatus.COMPLETED);
                        System.out.println("Task marked as completed.");
                    }
                    else{
                        System.out.println("Task not found.");
                    }
                    break;

                case "4":
                    System.out.println("Updating task status...");
                    System.out.println("Enter the task ID to mark as updated:");
                    String taskUpdId =scanner.nextLine();
                    Task taskToUpdate=manager.getTaskById(taskUpdId);
                    if(taskToUpdate!=null){
                        System.out.println("Enter the new status (PENDING, IN_PROGRESS, COMPLETED):");
                        String newStatusInput=scanner.nextLine();
                        TaskStatus newStatus=TaskStatus.valueOf(newStatusInput.toUpperCase());
                        taskToUpdate.setStatus(newStatus);
                    }
                    else{
                        System.out.println("Task not found.");
                    }
                    break;

                case "5":
                    System.out.println("Deleting a task...");
                    System.out.println("Enter the task ID to delete:");
                    String taskDelId =scanner.nextLine();
                    Task taskToDelete=manager.getTaskById(taskDelId);
                    if(taskToDelete!=null){
                        manager.getTasks().remove(taskToDelete);
                        System.out.println("Task deleted successfully.");
                    }
                    else{
                        System.out.println("Task not found.");
                    }
                    break;

                case "6":
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    manager.saveTaskToFile();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            //scanner.close();

        }

    }

    private static void printMenu(){
        System.out.println("\nWelcome to the ToDo List Console Application!");
        System.out.println("1. Add Task");
        System.out.println("2. List Tasks");
        System.out.println("3. Mark task as completed");
        System.out.println("4. Update Status");
        System.out.println("5. Delete Task");
        System.out.println("6. Exit");
        System.out.print("Please select an option: ");
    }

    private static void handleAddTask(){
        System.out.println("Adding a new task...");
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task status (PENDING, IN_PROGRESS, COMPLETED): ");
        String statusInput = scanner.nextLine();
        TaskStatus status = TaskStatus.valueOf(statusInput.toUpperCase());
        System.out.print("Enter task due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter task priority (High, Medium, Low): ");
        String priority = scanner.nextLine();
        Task task = new Task(String.valueOf(System.currentTimeMillis()), name, description, status, dueDate, priority);
        manager.addTask(task);
    }

    private static void handleListTasks(){
        System.out.println("Listing all tasks...");
        manager.listTasks();
    }



}