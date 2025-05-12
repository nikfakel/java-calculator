package com.raven.calculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true, nullable = false)
    private String username;

    @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Setter
    @Column(nullable = false)
    private String password;

    private ZonedDateTime createdAt = ZonedDateTime.now();
}
