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
import com.example.taskmanagementsystem.dto.CreateTask;
import com.example.taskmanagementsystem.service.TaskService;
import com.example.taskmanagementsystem.entity.Task;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired 
    private TaskService taskser;
    @GetMapping("/profile")
    public String profile() {
        return "User Profile";
    }

    @PostMapping("/create_task")
    public Task createtask(@RequestBody CreateTask ct)
    {
        return taskser.createtask(ct);
    }
}