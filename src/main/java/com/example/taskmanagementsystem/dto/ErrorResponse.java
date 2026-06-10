package com.example.taskmanagementsystem.dto;
public class ErrorResponse
{
    private String message;
    private String status;
    public ErrorResponse(String message,String status){
        this.message=message;
        this.status=status;
    }
    public String getMessage()
    {
        return this.message;
    }
    public String getStatus()
    {
        return this.status;
    }
}