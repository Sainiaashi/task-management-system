package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestParam RegisterRequest res) {

        User user = new User();

        user.setName(res.getName());
        user.setEmail(res.getEmail())
        user.setPassword(passwordEncoder.encode(res.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        return "user registered";
    }
    
}