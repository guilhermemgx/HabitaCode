package com.example.HabitaCode.domain.entity;

import com.example.HabitaCode.core.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.HABITACODE.PESSOA, schema = Constants.HABITACODE.SCHEMA)
public class Pessoa {
    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(length = 20)
    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condominio_id", nullable = false)
    private Condominio condominio;

    @PrePersist
    public void gerarId() {
        if (id == null) {
            id = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "");
        }
    }
}
