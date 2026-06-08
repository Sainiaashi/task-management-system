package com.example.taskmanagementsystem.entity;
import jakarta.persistence.*;
@Entity
@Table(name="tasks")
public class Task
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String title;
    private String description;
    private String status;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Long getId()
    {
        return this.id;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getDescription()
    {
        return this.description;
    }
    public String getStatus()
    {
        return this.status;
    }
    public void setId(Long id)
    {
        this.id=id;
    }
    public void setTitle(String title)
    {
        this.title=title;
    }
    public void setDescription(String des)
    {
        this.description=des;
    }
    public void setStatus(String status)
    {
        this.status=status;
    }
    public User getUser() {
    return user;
}

public void setUser(User user) {
    this.user = user;
}
}