package com.example.chatservice.model;

public class LoginRequest {
    private String username;
    private String password;
    private String tenantIdentifier;

    // Constructors
    public LoginRequest() {
    }

    public LoginRequest(String username, String password, String tenantIdentifier) {
        this.username = username;
        this.password = password;
        this.tenantIdentifier = tenantIdentifier;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTenantIdentifier() {
        return tenantIdentifier;
    }

    public void setTenantIdentifier(String tenantIdentifier) {
        this.tenantIdentifier = tenantIdentifier;
    }
}