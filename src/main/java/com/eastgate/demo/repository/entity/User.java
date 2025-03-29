package com.eastgate.demo.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String username;

    @Column(unique = true, nullable = false)
    private String secretKey;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    public User(String username, String secretKey) {
        this.username = username;
        this.secretKey = secretKey;
        this.createdDate = LocalDateTime.now();
    }
}
