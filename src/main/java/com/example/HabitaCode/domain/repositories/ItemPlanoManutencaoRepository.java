package com.example.HabitaCode.domain.repositories;

import com.example.HabitaCode.domain.entity.ItemPlanoManutencao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPlanoManutencaoRepository extends JpaRepository<ItemPlanoManutencao, String> {

    @Query(value = """
            select * from hc."itemPlanoManutencao"
            """, nativeQuery = true)
    List<ItemPlanoManutencao> listAllItens();


    @Query(value = """
            select *
            from hc."itemPlanoManutencao" ipm
            inner join hc."planoDeManutencao" pdm on ipm."planoId" = pdm."idPlanoManutencao"
            where pdm."idPlanoManutencao" = :planoId
            """, nativeQuery = true)
    List<ItemPlanoManutencao> listAllItensByPlanoId(
            @Param(value = "planoId") String planoId
    );

}
