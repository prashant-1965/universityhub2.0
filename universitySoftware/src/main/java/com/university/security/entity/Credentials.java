package com.university.security.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    Long id;

    @Column(nullable = false)
    @NotBlank(message = "username is required")
    @JsonProperty("username")
    private String username;

    @Column(nullable = false,unique = true)
    @NotBlank(message = "password is required")
    @Size(min=6, message = "Password must be at least 6 characters")
    @JsonProperty("password")
    private String password;

    @Column(nullable = false)
    @JsonProperty("role")
    private String role;
}
