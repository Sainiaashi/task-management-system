package com.example.taskmanagementsystem.dto;
public class RegisterRequest
{
    private String name;
    private String email;
    private String password;
    public String getName()
    {
        return this.name;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getPassword()
    {
        return this.password;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public setEmail(String email)
    {
        this.email=email;
    }
    public setPassword(String password)
    {
        this.password=password;
    }
}