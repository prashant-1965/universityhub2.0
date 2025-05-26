package com.university.application.branch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "branch_code", nullable = false, unique = true)
    @JsonProperty("branchCode")
    private String branchCode;

    @Column(name = "branch_name", nullable = false, unique = true)
    @JsonProperty("branchName")
    private String branchName;

    @Column(name = "branch_Hod", nullable = false, unique = true)
    @JsonProperty("branchHod")
    private String branchHod;

}
