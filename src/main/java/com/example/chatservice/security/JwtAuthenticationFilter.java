package com.example.chatservice.security;

import com.example.chatservice.service.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserServiceImpl userService;

    public JwtAuthenticationFilter(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Get authorization header
        String authHeader = request.getHeader("Authorization");
        
        // Check if header is present and has Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extract token
            String token = authHeader.substring(7);
            
            // Validate token and get username
            String username = userService.getUsernameFromToken(token);
            
            if (username != null) {
                // Create authentication object
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
                
                // Set authentication in security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        // Continue filter chain
        filterChain.doFilter(request, response);
    }
}