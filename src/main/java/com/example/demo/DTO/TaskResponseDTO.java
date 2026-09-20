package com.example.demo.DTO;

import com.example.demo.model.Task;

public class TaskResponseDTO {
    private String title;
    private String description;
    private long id;
    private long ownerId;
    public TaskResponseDTO(Task task) {
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.ownerId = task.getOwner().getId();
        this.id = task.getId();
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public long getId() {
        return id;
    }
    public long getOwnerId() {
        return ownerId;
    }
}
