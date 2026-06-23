package com.example.taskmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.repository.TaskRepository;
import com.example.taskmanagementsystem.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private UserRepository userrepo;

    @Mock
    private TaskRepository taskrepo;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldReturnTaskForOwner() {

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        "test@gmail.com",
                        null
                );

        SecurityContextHolder
                .getContext()
                .setAuthentication(auth);

        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Learn JWT");
        task.setUser(user);

        when(userrepo.findByEmail("test@gmail.com"))
                .thenReturn(Optional.of(user));

        when(taskrepo.findById(1L))
                .thenReturn(Optional.of(task));

        Task result = taskService.getTask(1L);

        assertEquals(
                "Learn JWT",
                result.getTitle()
        );
    }
}