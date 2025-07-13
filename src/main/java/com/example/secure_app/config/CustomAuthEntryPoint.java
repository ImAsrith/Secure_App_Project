package com.example.secure_app.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String message;
        if (authHeader == null || authHeader.isBlank()) {
            message = "Missing credentials: please include an Authorization header.";
        } else {
            message = "Invalid credentials: username or password is incorrect.";
        }

        String json = String.format(
                "{\"error\":\"Unauthorized\",\"message\":\"%s\"}",
                message
        );

        response.getWriter().write(json);
    }
}