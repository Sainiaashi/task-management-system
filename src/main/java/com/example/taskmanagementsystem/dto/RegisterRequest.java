package com.example.taskmanagementsystem.dto;
import com.example.taskmanagementsystem.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public class RegisterRequest
{
    @NotBlank(message="Name not be empty")
    private String name;

    @Email(message="invalid email format")
    @NotBlank(message="email not be empty")
    private String email;

    @Size(min=6,message="must be of 6 character")
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