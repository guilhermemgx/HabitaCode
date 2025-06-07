package com.example.HabitaCode.domain.entity;

import com.example.HabitaCode.core.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.HABITACODE.CIDADE, schema = Constants.HABITACODE.SCHEMA)
public class Cidade {
    @Id
    @Column(name = "cidade_id", nullable = false, length = 32)
    private String cidadeId;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @PrePersist
    public void gerarId() {
        if (cidadeId == null) {
            cidadeId = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "");
        }
    }

}
