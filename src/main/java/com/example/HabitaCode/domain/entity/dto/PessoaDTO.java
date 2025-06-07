package com.example.HabitaCode.domain.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PessoaDTO {
    private String id;
    private String nomeCompleto;
    private String cpf;
    private String condominio;
    private String cidade;
    private String estado;

    public PessoaDTO (String id,
        String nomeCompleto,
        String cpf,
        String condominio,
        String cidade,
        String estado
    ){
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.condominio = condominio;
        this.cidade = cidade;
        this.estado = estado;
    }
}
