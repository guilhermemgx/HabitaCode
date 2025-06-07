package com.example.HabitaCode.domain.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EstadoDTO {
    private String estadoId;
    private String nome;
    private String uf;

    public EstadoDTO(String estadoId, String nome, String uf) {
        this.estadoId = estadoId;
        this.nome = nome;
        this.uf = uf;
    }
}
