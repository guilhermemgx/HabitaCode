package com.example.HabitaCode.domain.entity.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class CidadeDTO {
    private String cidadeId;
    private String nome;
    private String estadoId;
    private String estadoNome;

    public CidadeDTO(String cidadeId, String nome, String estadoId, String estadoNome) {
        this.cidadeId = cidadeId;
        this.nome = nome;
        this.estadoId = estadoId;
        this.estadoNome = estadoNome;
    }
}
