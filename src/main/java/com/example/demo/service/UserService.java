package com.example.demo.service;

import com.example.demo.config.PasswordEncoderConfig;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.demo.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    public Optional<User> getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername((username));
        return user;
    }
    public User registerUser(String password, String username) {
        Optional<User> oldUser = getUserByUsername(username);
        if (oldUser.isPresent()) {
            throw new IllegalStateException("Username already exists");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        userRepository.save(newUser);
        return newUser;
    }
}
