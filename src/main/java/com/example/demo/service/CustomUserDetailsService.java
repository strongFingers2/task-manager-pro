package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;
@Service
public class CustomUserDetailsService implements UserDetailsService {
     private final UserService userservice;
     public CustomUserDetailsService(UserService userservice) {
         this.userservice = userservice;
     }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userservice.getUserByUsername(username);
        if (user.isPresent()) {
            User u = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(u.getUsername())
                    .password(u.getPassword())
                    .roles("USER")
                    .build();
        }
        else {
            throw new UsernameNotFoundException("User not found: "+username);
        }
    }
}
