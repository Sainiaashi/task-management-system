package com.example.taskmanagementsystem.controller;
import com.example.taskmanagementsystem.dto.RegisterRequest;
import com.example.taskmanagementsystem.dto.LoginRequest;
import com.example.taskmanagementsystem.dto.ApiResponse;
import com.example.taskmanagementsystem.entity.User;
import com.example.taskmanagementsystem.entity.Role;
import com.example.taskmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.taskmanagementsystem.security.JwtService;
import com.example.taskmanagementsystem.exception.UserNotFoundException;
import com.example.taskmanagementsystem.exception.TaskNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import for config
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(
    name="tasks",
    description="register or login or getemail"
)
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwt;

    private static final Logger log=LoggerFactory.getLogger(MainController.class);

 @Operation
 (
    summary="register useer by name,email and password",
    description="return a message that user register"
 )
 @ApiResponses({
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Task found"),
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Task not found")// i use complete import beacuse dto has also Apiresponse
})
    @PostMapping("/register")
public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest res) {

    try {
        
        log.info("registration request recieved from {}",res.getEmail());

        User user = new User();

        user.setName(res.getName());
        user.setEmail(res.getEmail());
        user.setPassword(passwordEncoder.encode(res.getPassword()));
        user.setRole(res.getRole());

        userRepository.save(user);
        log.info("user registrated successfully {}",res.getEmail());

        return ResponseEntity.ok(new ApiResponse<>(true,"user registered successfully",null));

    } catch (Exception e) {

        log.error("user failed to register {}",res.getEmail(),e);


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(
                                new ApiResponse<>
                                (false,
                                e.getMessage(),null)
                             );
    }
}

@Operation(
    summary="login user by email and password",
    description="return a token"
)
@ApiResponses({
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "user not found"),
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "invalid password"),
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",description="user login")
})
@SecurityRequirement(name = "bearerAuth")
   @PostMapping("/public/login")
   public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequest lo)
   {

    log.info("login request is made by {}",lo.getEmail());
    String email=lo.getEmail();
    String password=lo.getPassword();
    User user=new User();
    user=userRepository.findByEmail(email).orElseThrow(()->
                                {log.warn("user not found {}",email);
                                    return new UserNotFoundException("user not found");});
    
        boolean passmatch=passwordEncoder.matches(password,user.getPassword());
        if(passmatch)
        {
            String token=jwt.generateKey(email,user.getRole().name());//.name to access the name only as getrole return enum like role.USER.
            log.info("login successful for {}",email);
            return ResponseEntity.ok(
                new ApiResponse<>(true,"user login",token)
            );
        }
        else{
            log.warn("invalid password for {}",email);
             throw new RuntimeException("INVALID_PASSWORD");
        }
   } 
@Operation(
    summary="get email ny token",
    description="return email"
)
@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200",description="get email")
@SecurityRequirement(name = "bearerAuth")
   @GetMapping("/getemail")
   public String getemail(@RequestBody String token)
   {
    String email=jwt.ExtractEmail(token);
    return email;
   }
    
}