package com.example.chatservice.controller;

import com.example.chatservice.model.ChatUserConfig;
import com.example.chatservice.model.User;
import com.example.chatservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/front-office/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(
            @RequestHeader("Authorization") String authHeader,
            @RequestHeader(value = "X-Tenant-Id", required = false) String tenantId) {
        // Extract token from Authorization header
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token != null) {
            String username = userService.getUsernameFromToken(token);
            if (username != null) {
                User user = userService.getCurrentUser(username);
                user.setChatUserConfig(
                        ChatUserConfig.builder()
                                .chatUserId("fjsrTPcaDmwkdF65X")
                                .chatUserToken("gekiVflo-VogrcGU_sYtaGciHCOPTc0ckJGhSBH-svh")
                                .build()
                );
                user.setPrivileges(Collections.emptyList());
                return ResponseEntity.ok(user);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
