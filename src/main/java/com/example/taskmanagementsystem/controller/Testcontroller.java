package com.example.taskmanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class Testcontroller {
    @Autowired
    private TaskService taskservice;

    @GetMapping("/public")
    public String publicApi() {
        return "Public API";
    }

    @GetMapping("/private")
    public String privateApi() {
        return "Private API";
    }

    @GetMapping("/task/{id}")
    public Task gettask(@PathVariable Long id)
    {
        return taskservice.getTask(id);
    }
}