package com.example.HabitaCode.domain.repositories;

import com.example.HabitaCode.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, String> {

    @Query(value = """
            select * from hc.cidade ci
            where ci.estado_id = :estadoId
            """, nativeQuery = true)
    public List<Cidade> findCidade (@Param("estadoId") String estadoId);
}
