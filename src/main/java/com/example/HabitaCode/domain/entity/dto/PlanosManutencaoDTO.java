package com.example.HabitaCode.domain.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PlanosManutencaoDTO {
    private String id;
    private String nome;
    private List<GrupoDTO> grupos = new ArrayList<>();

    @Data
    @NoArgsConstructor
    public static class GrupoDTO {
        private String id;
        private String nome;
        private List<ItemDTO> itens = new ArrayList<>();
    }

    @Data
    @NoArgsConstructor
    public static class ItemDTO {
        private String id;
        private String nome;
        private String descricao;
        private boolean realizado;
    }
}




