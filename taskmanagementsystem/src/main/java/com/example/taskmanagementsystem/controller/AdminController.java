package com.example.taskmanagementsystem.controller;
import com.example.taskmanagementsystem.dto.RegisterRequest;
import com.example.taskmanagementsystem.dto.LoginRequest;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.taskmanagementsystem.security.JwtService;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @GetMapping("/profile")
    public String profile() {
        return "User Profile";
    }
}