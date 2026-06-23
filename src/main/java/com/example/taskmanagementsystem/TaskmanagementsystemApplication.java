package com.example.taskmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cache.annotation.EnableCaching;

import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
public class TaskmanagementsystemApplication {
	@Value("${spring.datasource.username}")
    private String username;

    @PostConstruct
    public void test() {
        System.out.println("[" + username + "]");
        System.out.println("Length = " + username.length());
    }

	public static void main(String[] args) {
		SpringApplication.run(TaskmanagementsystemApplication.class, args);
	}

}
