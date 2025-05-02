package com.university.application.faculty.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uId")
    @JsonProperty("uId")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "last_name",nullable = false)
    @JsonProperty("lastName")
    private String lastName;

    @Column(name = "designation", nullable = false)
    @JsonProperty("designation")
    private String designation;

    @Column(name = "email", nullable = false)
    @JsonProperty("email")
    private String email;

    @Column(name = "location",nullable = false)
    @JsonProperty("location")
    private String location;
}
