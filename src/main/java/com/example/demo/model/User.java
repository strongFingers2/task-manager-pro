package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private String username;
    private String password;
    public User() {}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public void setid(long id) {this.id = id;}
    public long getId() {return id;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
