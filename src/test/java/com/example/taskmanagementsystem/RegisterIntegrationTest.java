package com.example.taskmanagementsystem;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterIntegrationTest
{
    @Autowired
    private MockMvc mockMvc;

@Test
void testRegisterEndpoint() throws Exception {

    String email = "test" + System.currentTimeMillis() + "@gmail.com";

String json = """
{
  "name":"Test User",
  "email":"%s",
  "password":"123456",
  "role":"USER"
}
""".formatted(email);


    mockMvc.perform(
            post("/register")
                    .contentType("application/json")
                    .content(json)
    )
    .andExpect(status().isOk());
}
}