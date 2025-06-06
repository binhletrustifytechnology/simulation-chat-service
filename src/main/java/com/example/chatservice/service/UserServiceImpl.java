package com.example.chatservice.service;

import com.example.chatservice.model.LoginRequest;
import com.example.chatservice.model.LoginResponse;
import com.example.chatservice.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    // In-memory user store (for demo purposes only)
    private final Map<String, User> users = new HashMap<>();
    
    // In-memory token store (for demo purposes only)
    private final Map<String, String> tokens = new HashMap<>();

    public UserServiceImpl() {
        // Initialize with some demo users
        users.put("user_a", new User(1L, "user_a", "password@123", "user_a@example.com", "User One"));
        users.put("user_b", new User(2L, "user_b", "password@123", "user_b@example.com", "User Two"));
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = users.get(loginRequest.getUsername());
        
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            // Generate a simple token (in a real app, use JWT or similar)
            String token = UUID.randomUUID().toString();
            tokens.put(token, user.getUsername());
            
            return new LoginResponse(token, user.getUsername(), "Login successful");
        }
        
        return new LoginResponse(null, null, "Invalid credentials");
    }

    @Override
    public User getCurrentUser(String username) {
        User user = users.get(username);
        if (user != null) {
            // Create a copy without the password for security
            return new User(
                user.getId(),
                user.getUsername(),
                null, // Don't send password
                user.getEmail(),
                user.getFullName()
            );
        }
        return null;
    }
    
    // Helper method to validate token and get username
    public String getUsernameFromToken(String token) {
        return tokens.get(token);
    }
}