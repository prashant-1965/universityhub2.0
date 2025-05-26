package com.university.application.sections.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(name = "section_code", unique = true, nullable = false)
    @JsonProperty("sectionCode")
    private String sectionCode;

    @Column(name = "section_incharge",unique = false,nullable = false)
    @JsonProperty("sectionInCharge")
    private String sectionInCharge;
}
