package com.taskmanager;

import java.util.List;

public class TaskRepository {

    private final List<Task> tasks;
    private final IfileSource fileSource;


    public TaskRepository(IfileSource fileSource) {
        this.fileSource = fileSource;
        this.tasks = fileSource.load();
    }

    public void add(Task task) {
        tasks.add(task);
        fileSource.save(tasks);
    }

    public void updateTask(Task task) {
        fileSource.save(tasks);
    }

    public boolean delete(Task task) {
        boolean removed = tasks.remove(task);
        if (removed)
            fileSource.save(tasks);
        return removed;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getById(long id) {
        {
            for (Task task : tasks) {
                if (task.getId() ==id) {
                    return task;
                }
            }
            return null;
        }
    }

}

