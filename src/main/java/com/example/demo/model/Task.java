package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {

    private String Title;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public Task() {

    }
    public String getTitle() {
        return Title;
    }
    public String getDescription() {
        return description;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
