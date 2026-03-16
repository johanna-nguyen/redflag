package com.project.redflag.report.entity;

import com.project.redflag.category.entity.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="report")
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1)
    private String firstName;

    @Column(length = 1)
    private String lastName;

    private String ageRange;

    private String city;

    private String nationality;

    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name="category_name")
    private Category category;

}
