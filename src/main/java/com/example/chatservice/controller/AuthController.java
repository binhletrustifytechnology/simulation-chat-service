package com.example.chatservice.controller;

import com.example.chatservice.model.CommonResponse;
import com.example.chatservice.model.LoginRequest;
import com.example.chatservice.model.LoginResponse;
import com.example.chatservice.model.User;
import com.example.chatservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
public class AuthController {

    private final UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CommonResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.login(loginRequest);
        
        if (response.getAccessToken() != null) {
            return CommonResponse.ok(response);
        } else {
            return null;
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        // Extract token from Authorization header
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        
        if (token != null) {
            String username = userService.getUsernameFromToken(token);
            if (username != null) {
                User user = userService.getCurrentUser(username);
                if (user != null) {
                    return ResponseEntity.ok(user);
                }
            }
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}