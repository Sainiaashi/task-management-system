package com.example.taskmanagementsystem.dto;
import com.example.taskmanagementsystem.entity.Role;
public class RegisterRequest
{
    private String name;
    private String email;
    private String password;
    private Role role;
    public RegisterRequest(){}
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
    public void setEmail(String email)
    {
        this.email=email;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    public Role getRole()
    {
        return this.role;
    }
    public void setRole(Role role)
    {
        this.role=role;
    }
}