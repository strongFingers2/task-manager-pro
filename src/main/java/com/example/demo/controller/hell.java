package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.service.TaskService;
import com.example.demo.token.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.UserService;

import java.util.*;

@RestController
public class hell {
    private final TaskService taskService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    public hell(TaskService taskService, UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.taskService = taskService;
        this.userService = userService;
        this.authenticationManager = authenticationManager ;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/tasks")
    public ResponseEntity<Task> CreateTask(@RequestBody Task task) {
        Task create = taskService.createTask(task);
        return ResponseEntity.status(201).body(create);
    }
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> UpdateTask(@PathVariable long id, @RequestBody Task task) {

        Optional<Task> t = taskService.updateTask(id, task);
        if (!t.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(t.get());
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        if (taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskByid(@PathVariable Long id) {
        Optional<Task> desire = taskService.getTaskById(id);
        if (desire.isPresent()) {
            return ResponseEntity.ok(desire.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/register")
    public ResponseEntity<String> newUser(@RequestBody User user) {
        User n;
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            n = userService.registerUser(password, username);
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
        return ResponseEntity.status(201).body(n.getUsername());
    }
    public record LoginInfo(String username, String password) {}
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginInfo loginInfo) {
        String username = loginInfo.username ;
        String password = loginInfo.password ;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}