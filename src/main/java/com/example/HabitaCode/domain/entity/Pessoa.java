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
    @Column(name = "pessoaid", length = 32)
    private String pessoaId;

    @Column(name = "nomecompleto", nullable = false)
    private String nomeCompleto;

    @Column(length = 20)
    private String telefone;

    @Column(name = "datanascimento")
    private LocalDate dataNascimento;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condominioid", nullable = false)
    private Condominio condominioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioid", nullable = false)
    private Usuario usuarioId;

    @PrePersist
    public void gerarId() {
        if (pessoaId == null) {
            pessoaId = UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "");
        }
    }
}
