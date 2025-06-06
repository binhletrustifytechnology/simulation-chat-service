package com.example.chatservice.service;

import com.example.chatservice.model.LoginRequest;
import com.example.chatservice.model.LoginResponse;
import com.example.chatservice.model.User;

public interface UserService {
    /**
     * Authenticate a user with the provided credentials
     * @param loginRequest the login credentials
     * @return a response containing authentication token if successful
     */
    LoginResponse login(LoginRequest loginRequest);
    
    /**
     * Get the current user's information
     * @param username the username of the current user
     * @return the user information
     */
    User getCurrentUser(String username);
}