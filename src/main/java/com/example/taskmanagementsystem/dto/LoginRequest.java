package com.example.taskmanagementsystem.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest
{
    @NotBlank(message="email can't be empty")
    @Email(message="invalid email format")
    private String email;

    @Size(min=8, message="be of size 8")
    private String password;
    public LoginRequest(){}
    public String getEmail()
    {
        return this.email;
    }
    public String getPassword()
    {
        return this.password;
    }
    public void setEmail(String email)
    {
     this.email=email;   
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
}