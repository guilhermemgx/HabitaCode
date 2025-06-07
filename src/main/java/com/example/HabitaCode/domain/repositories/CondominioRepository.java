package com.example.HabitaCode.domain.repositories;

import com.example.HabitaCode.domain.entity.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, String> {
}
