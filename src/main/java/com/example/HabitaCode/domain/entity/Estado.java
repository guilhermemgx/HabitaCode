package com.example.HabitaCode.domain.entity;

import com.example.HabitaCode.core.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.HABITACODE.ESTADO, schema = Constants.HABITACODE.SCHEMA)
public class Estado {
    @Id
    @Column(name = "estado_id", length = 32)
    private String estadoId;

    @Column(nullable = false)
    private String nome;

    @Column(length = 2, nullable = false, unique = true)
    private String uf;

    @PrePersist
    public void gerarId() {
        if (estadoId == null) {
            estadoId = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "");
        }
    }
}
