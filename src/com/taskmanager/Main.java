package com.taskmanager;

import com.taskmanager.Task;
import com.taskmanager.IfileSource;
import com.taskmanager.TaskRepository;
import com.taskmanager.TaskService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IfileSource fileSource = new JsonfileSource("tasks.json");
        TaskRepository taskRepository = new TaskRepository(fileSource);
        TaskService taskService = new TaskService(taskRepository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    List<Task> tasks = taskService.getAllTasks();
                    if (!tasks.isEmpty()){
                        for(Task task :tasks)
                            System.out.println(task);
                    }
                    else System.out.println("empty TODO list");
                    break;
                case 2:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    Task newTask = taskService.createNewTask(title, description);
                    System.out.println("Task created successfully: " + newTask);
                    break;
                case 3:
                    System.out.print("Enter task ID to update: ");
                    long idToUpdate = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("Enter new status (NEW, IN_PROGRESS, DONE): ");
                    String statusStr = scanner.nextLine().toUpperCase();

                    try {
                        Status newStatus = Status.valueOf(statusStr);
                        boolean updated = taskService.updateTaskStatus(idToUpdate, newStatus);
                        if (updated) {
                            System.out.println("Task status updated successfully.");
                        } else {
                            System.out.println("Task not found.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid status. Please use NEW, IN_PROGRESS, or DONE.");
                    }
                    break;
                case 4:
                    System.out.print("Enter search term: ");
                    String searchTerm = scanner.nextLine();
                    List<Task> foundTasks = taskService.searchTasks(searchTerm);
                    if (foundTasks.isEmpty()) {
                        System.out.println("No tasks found matching your search.");
                    } else {
                        System.out.println("\n--- Search Results ---");
                        foundTasks.forEach(System.out::println);
                        System.out.println("----------------------");
                    }
                    break;
                case 5:
                    List<Task> sortedTasks = taskService.getTasksSortedByStatus();
                    if (sortedTasks.isEmpty()) {
                        System.out.println("No tasks to show.");
                    } else {
                        System.out.println("\n--- Tasks Sorted by Status ---");
                        sortedTasks.forEach(System.out::println);
                        System.out.println("------------------------------");
                    }
                    break;
                case 6:
                    System.out.print("Enter task ID to delete: ");
                    long idToDelete = scanner.nextLong();
                    scanner.nextLine();
                    boolean deleted = taskService.deleteTask(idToDelete);
                    if (deleted) {
                        System.out.println("Task deleted successfully.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Task Manager Menu ---");
        System.out.println("1. View all tasks");
        System.out.println("2. Add a new task");
        System.out.println("3. Update task status");
        System.out.println("4. Search tasks by title or description");
        System.out.println("5. View tasks sorted by status");
        System.out.println("6. Delete a task");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
}

