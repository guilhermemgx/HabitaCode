package com.example.HabitaCode.domain.repositories;

import com.example.HabitaCode.domain.entity.CondominioItemPlanoManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CondominioItemPlanoManutencaoRepository extends JpaRepository<CondominioItemPlanoManutencao, String> {
    @Query(value = """
        select * from hc."condominioItemPlanoManutencao" cip
        where cip."idCondominio" = :condId
    """, nativeQuery = true)
    List<CondominioItemPlanoManutencao> getAllByCondominiumId(
            @Param(value = "condId") String condId
    );
}