package com.example.chatservice.model;

public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private String username;
    private String message;

    // Constructors
    public LoginResponse() {
    }

    public LoginResponse(String token, String username, String message) {
        this.accessToken = token;
        this.refreshToken = token;
        this.username = username;
        this.message = message;
    }

    // Getters and Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}