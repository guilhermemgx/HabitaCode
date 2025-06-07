package com.example.HabitaCode.domain.entity;

import com.example.HabitaCode.core.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Constants.HABITACODE.USUARIO, schema = Constants.HABITACODE.SCHEMA)
public class Usuario {
    @Id
    @Column(length = 32)
    private String id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 20)
    private String role;

    @PrePersist
    public void gerarId() {
        if (id == null) {
            id = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "");
        }
    }
}
