package com.example.HabitaCode.domain.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PessoaRequestDTO {
    private String nomeCompleto;
    private String telefone;
    private LocalDate dataNascimento;
    private String cpf;
    private String estadoId;
    private String cidadeId;
    private String condominio;
    private String email;
}
