package com.taskmanager;

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

}

