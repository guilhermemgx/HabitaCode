package com.example.HabitaCode.domain.entity;
import com.example.HabitaCode.core.Constants;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.HABITACODE.CONDOMINIOITEMPLANOMANUTENCAO, schema = Constants.HABITACODE.SCHEMA)
public class CondominioItemPlanoManutencao {
    @EmbeddedId
    private CondominioItemPlanoManutencaoId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("condominioId")
    @JoinColumn(name = "idCondominio", nullable = false)
    private Condominio condominio;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("itemPlanoManutencaoId")
    @JoinColumn(name = "idItemPlanoManutencao", nullable = false)
    private ItemPlanoManutencao itemPlanoManutencao;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class CondominioItemPlanoManutencaoId implements Serializable {

        private String condominioId;
        private String itemPlanoManutencaoId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CondominioItemPlanoManutencaoId)) return false;
            CondominioItemPlanoManutencaoId that = (CondominioItemPlanoManutencaoId) o;
            return Objects.equals(condominioId, that.condominioId) &&
                    Objects.equals(itemPlanoManutencaoId, that.itemPlanoManutencaoId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(condominioId, itemPlanoManutencaoId);
        }
    }
}
