package com.example.HabitaCode.domain.repositories;

import com.example.HabitaCode.domain.entity.TipoPlanoManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPlanoManutencaoRepository extends JpaRepository<TipoPlanoManutencao, String> {

}
