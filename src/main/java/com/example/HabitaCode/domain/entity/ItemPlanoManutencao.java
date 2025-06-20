package com.example.HabitaCode.domain.entity;

import com.example.HabitaCode.core.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.HABITACODE.ITEMPLANOMANUTENCAO, schema = Constants.HABITACODE.SCHEMA)
public class ItemPlanoManutencao {
    @Id
    @Column(name = "idItemPlanoManutencao", length = 32)
    private String idItemPlanoManutencao;

    @Column(name = "nomeItemPlanoManutencao", length = 255, nullable = false)
    private String nomeItemPlanoManutencao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipoId", nullable = false)
    private TipoPlanoManutencao tipoPlanoManutencao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "planoId", nullable = false)
    private PlanoDeManutencao planoDeManutencao;
}
