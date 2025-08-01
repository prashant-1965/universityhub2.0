package com.university.security.config;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomSuccessHandel implements AuthenticationSuccessHandler {
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String role = request.getParameter("role");
        switch (role) {
            case "dean" -> response.sendRedirect("/home/dean");
            case "faculty" -> response.sendRedirect("/home/faculty");
            case "student" -> response.sendRedirect("/home/student");
            default -> response.sendRedirect("/login");
        }
    }
}