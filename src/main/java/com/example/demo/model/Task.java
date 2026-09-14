package com.example.demo.model;

import jakarta.persistence.*;


@Entity
@Table(name = "task")
public class Task {
    private String title;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User owner;
    public Task() {

    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public void setTitle(String Title) {
        this.title = Title;
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
    public User getOwner() {
        return owner ;
    }
    public void setOwner(User user) {
        owner = user;
    }
}
