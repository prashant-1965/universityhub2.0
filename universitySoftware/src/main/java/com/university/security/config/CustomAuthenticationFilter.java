//package com.university.security.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//    {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
//        setDetails(request,token);
//        return this.getAuthenticationManager().authenticate(token);
//    }
//}
