package com.example.HabitaCode.domain.entity;
import com.example.HabitaCode.core.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.HABITACODE.CONDOMINIO, schema = Constants.HABITACODE.SCHEMA)
public class Condominio {
    @Id
    @Column(name = "condominio_id", length = 32)
    private String condominioId;

    @Column(nullable = false)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @PrePersist
    public void gerarId() {
        if (condominioId == null) {
            condominioId = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "");
        }
    }
}
