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
@Service
public class TaskService
{
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

    User currentuser= userrepo.findByEmail(email);
    Task task=taskrepo.findById(id).orElseThrow(
        ()->new TaskNotFoundException ("task not found")
    );
    if(!task.getUser().getId().equals(currentuser.getId()))
    {
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
    User currentuser=userrepo.findByEmail(email);
    Task task=new Task();
    task.setTitle(ct.getTitle());
    task.setDescription(ct.getDescription());
    task.setStatus(ct.getStatus());
    task.setUser(currentuser);
    return taskrepo.save(task);
   }
}