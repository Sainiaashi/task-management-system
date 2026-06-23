package com.example.taskmanagementsystem.repository;
import com.example.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepository extends JpaRepository<Task,Long>
{
    Page<Task> findByStatus(String status,Pageable pageable);
    Page<Task> findByTitleContaining(String keyword, Pageable pageable);
}