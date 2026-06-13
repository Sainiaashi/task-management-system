package com.example.taskmanagementsystem.service;
import org.springframework.stereotype.Service;
import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.taskmanagementsystem.repository.UserRepository;
import com.example.taskmanagementsystem.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.taskmanagementsystem.dto.CreateTask;
import com.example.taskmanagementsystem.exception.UserNotFoundException;
import com.example.taskmanagementsystem.exception.TaskNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TaskService
{
    private static final Logger log= LoggerFactory.getLogger(TaskService.class);
    @Autowired
    private UserRepository userrepo;
    @Autowired
    private TaskRepository taskrepo;
   public Task getTask(Long id)
   {
    String email=SecurityContextHolder
                 .getContext()
                 .getAuthentication()
                 .getName();

    User currentuser= userrepo.findByEmail(email).orElseThrow(()->new UserNotFoundException("user not found"));
    Task task=taskrepo.findById(id).orElseThrow(
        ()->new TaskNotFoundException ("task not found")
    );
    if(!task.getUser().getId().equals(currentuser.getId()))
    {
        log.warn("user {} attempts to access task {}",currentuser.getEmail(),task.getId());
        throw new RuntimeException("Access denied");
    }
    return task;
   }
   public Task createtask(CreateTask ct)
   {
    String email =SecurityContextHolder
                  .getContext()
                  .getAuthentication()
                  .getName();
    User currentuser=userrepo.findByEmail(email).orElseThrow(()->new UserNotFoundException("user not found"));
    Task task=new Task();
    task.setTitle(ct.getTitle());
    task.setDescription(ct.getDescription());
    task.setStatus(ct.getStatus());
    task.setUser(currentuser);
    return taskrepo.save(task);
   }
}