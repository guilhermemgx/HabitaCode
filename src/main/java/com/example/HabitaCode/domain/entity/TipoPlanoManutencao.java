package com.example.HabitaCode.domain.entity;

import com.example.HabitaCode.core.Constants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.HABITACODE.TIPOPLANOMANUTENCAO, schema = Constants.HABITACODE.SCHEMA)
public class TipoPlanoManutencao {
    @Id
    @Column(name = "idTipoPlanoManutencao", length = 32)
    private String idTipoPlanoManutencao;

    @Column(name = "nomeTipoPlanoManutencao", length = 255, nullable = false)
    private String nomeTipoPlanoManutencao;
}
