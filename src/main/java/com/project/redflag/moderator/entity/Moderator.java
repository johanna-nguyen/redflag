package com.project.redflag.moderator.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="moderator")
public class Moderator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private  String role;
}
