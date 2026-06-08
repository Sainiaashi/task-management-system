package com.example.taskmanagementsystem.entity;
import jakarta.persistence.*;
import java.util.*;
@Entity
@Table(name="users")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="email",nullable=false,unique=true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)     // to store enum as string in database
    private Role role;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    List<Task> tasks;

    public Long getId()
    {
        return this.id;
    }
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
    public Role getRole()
    {
        return this.role;
    }
    public void setRole(Role role)
    {
        this.role=role;
    }
    public void setId(Long id)
    {
        this.id=id;
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
    public List<Task> getTasks() {
    return tasks;
}

public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
}
}