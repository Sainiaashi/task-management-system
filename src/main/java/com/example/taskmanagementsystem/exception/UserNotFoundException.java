package com.examle.taskmanagementsystem.exception;
public UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String msg)
    {
        super(msg);
    }
}