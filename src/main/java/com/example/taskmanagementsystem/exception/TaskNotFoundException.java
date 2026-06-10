package com.example.taskmanagementsystem.exception;
public class TaskNotFoundExcpection extends RuntimeException
{
    public TaskNotFoundException(String msg)
    {
        super(msg);
    }
}