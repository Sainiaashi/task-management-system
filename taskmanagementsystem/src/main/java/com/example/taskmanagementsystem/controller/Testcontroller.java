package com.example.taskmanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testcontroller {

    @GetMapping("/public")
    public String publicApi() {
        return "Public API";
    }

    @GetMapping("/private")
    public String privateApi() {
        return "Private API";
    }
}