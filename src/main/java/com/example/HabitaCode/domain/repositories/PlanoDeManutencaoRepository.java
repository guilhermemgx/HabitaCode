package com.example.HabitaCode.domain.repositories;

import com.example.HabitaCode.domain.entity.PlanoDeManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoDeManutencaoRepository extends JpaRepository<PlanoDeManutencao, String> {
}
