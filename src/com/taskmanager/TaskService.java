package com.taskmanager;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createNewTask(String title, String description) {
        long newId = System.currentTimeMillis();
        Task task = new Task(newId, title, description, Status.NEW);
        repository.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return repository.getAllTasks();
    }

    public void markTaskAsDone(long id) {
        updateTaskStatus(id, Status.DONE);
    }

    public boolean updateTaskStatus(long id, Status newStatus) {
        Task task = repository.getById(id);
        if (task != null) {
            task.setStatus(newStatus);
            repository.updateTask(task);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteTask(long id) {
        Task task = repository.getById(id);
        if (task != null) {
            repository.delete(task);
            return true;
        } else {
            return false;
        }
    }

    public List<Task> searchTasks(String query) {
        String lowerCaseQuery = query.toLowerCase();
        List<Task> allTasks = repository.getAllTasks();
        return allTasks.stream()
                .filter(task ->
                        task.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                                task.getDescription().toLowerCase().contains(lowerCaseQuery)
                )
                .collect(Collectors.toList());
    }

    public List<Task> getTasksSortedByStatus() {
        List<Task> allTasks = repository.getAllTasks();
        return allTasks.stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }

}
