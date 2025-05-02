package com.university.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
public class SecurityConfig {

    @Autowired
    CustomSuccessHandel customSuccessHandel;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/home/**").authenticated()
                            .anyRequest().permitAll())
                    .formLogin(form -> form
                            .loginPage("/login").permitAll()
                            .loginProcessingUrl("/login").permitAll()
                            .successHandler(customSuccessHandel))
                    .logout(logout -> logout.logoutUrl("/logout").permitAll());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
