package com.example.chatservice.controller;

import com.example.chatservice.model.CommonResponse;
import com.example.chatservice.model.LoginRequest;
import com.example.chatservice.model.LoginResponse;
import com.example.chatservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class AuthController {

    private final UserServiceImpl userService;

    @Autowired
    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public CommonResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest, @RequestHeader(value = "X-Tenant-Id", required = false) String tenantId) {
        // If X-Tenant-Id header is provided, use it to override the tenantIdentifier in the request
        if (tenantId != null && !tenantId.isEmpty()) {
            loginRequest.setTenantIdentifier(tenantId);
        }

        LoginResponse response = userService.login(loginRequest);

        if (response.getAccessToken() != null) {
            return CommonResponse.ok(response);
        } else {
            return null;
        }
    }
}
