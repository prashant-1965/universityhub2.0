package com.university.application.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    @JsonProperty("stdId")
    private Long stdId;

    @Column(name = "first_name", nullable = false)
    @JsonProperty("firstName")
    private String firstName;

    @Column(name = "last_name",nullable = false)
    @JsonProperty("lastName")
    private String lastName;

    @Column(name = "location",nullable = false)
    @JsonProperty("location")
    private String location;

    @Column(name = "email", nullable = false)
    @JsonProperty("email")
    private String email;
}
