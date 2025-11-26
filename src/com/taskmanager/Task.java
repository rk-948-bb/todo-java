package com.taskmanager;

import java.util.Objects;

public class Task {
    private Long id;
    private String title;
    private String description;
    private Status status;

    public Task(Long Id, String Title, String description, Status status) {
        this.description = description;
        this.id = Id;
        this.title = Title;
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
    public Status getStatus() {
        return status;
    }
    public String getStatusAsString() {
        return status.name();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

