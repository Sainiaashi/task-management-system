package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import org.springframework.cache.annotation.Cacheable;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    @Autowired
    private TaskService taskService;

    @GetMapping
    public Page<Task> getAllTasks(
            @PageableDefault(size = 5,
                              sort="title")
            Pageable pageable)
    {
        return taskService.getALLTasks(pageable);
    }
    @GetMapping("/filter")
    
        public Page<Task> gettaskbystatus(@RequestParam String status,Pageable pageable)
        {
           return taskService.gettaskbystatus(status,pageable);
        }

        @Cacheable(value="tasks", key = "#keyword")// why use key beacuse due to pageable different cahce formed as pageable contains size contenet and more
    @GetMapping("/search")
    public Page<Task>  getbytitle(@RequestParam String keyword,Pageable pageable)
    {
        return taskService.getbytitle(keyword,pageable);
    }
}