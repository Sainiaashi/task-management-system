package com.example.taskmanagementsystem.controller;
import com.example.taskmanagementsystem.dto.RegisterRequest;
import com.example.taskmanagementsystem.dto.LoginRequest;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.taskmanagementsystem.security.JwtService;
import com.example.taskmanagementsystem.exception.UserNotFoundException;
import com.example.taskmanagementsystem.exception.TaskNotFoundException;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwt;

    @PostMapping("/register")
public String register(@RequestBody RegisterRequest res) {

    try {


        User user = new User();

        user.setName(res.getName());
        user.setEmail(res.getEmail());
        user.setPassword(passwordEncoder.encode(res.getPassword()));
        user.setRole(res.getRole());

        userRepository.save(user);

        return "user registered";

    } catch (Exception e) {

        e.printStackTrace();

        return e.getClass().getName() + " : " + e.getMessage();
    }
}
   @PostMapping("/public/login")
   public String login(@RequestBody LoginRequest lo)
   {
    String email=lo.getEmail();
    String password=lo.getPassword();
    User user=new User();
    user=userRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("user not found"));
    
        boolean passmatch=passwordEncoder.matches(password,user.getPassword());
        if(passmatch)
        {
            String token=jwt.generateKey(email,user.getRole().name());//.name to access the name only as getrole return enum like role.USER.
            return token;
        }
        else{
             throw new RuntimeException("INVALID_PASSWORD");
        }
   } 

   @GetMapping("/getemail")
   public String getemail(@RequestBody String token)
   {
    String email=jwt.ExtractEmail(token);
    return email;
   }
    
}