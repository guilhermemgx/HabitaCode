package com.example.HabitaCode.domain.service;

import com.example.HabitaCode.domain.entity.CondominioItemPlanoManutencao;
import com.example.HabitaCode.domain.entity.dto.CondominioItemPlanoManutencaoDTO;
import com.example.HabitaCode.domain.repositories.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CondominioItemPlanoManutencaoService {
    private final CidadeRepository repository;

    public void saveItemByCondominio (CondominioItemPlanoManutencaoDTO dto) {
        CondominioItemPlanoManutencao itemSaved = new CondominioItemPlanoManutencao();
        itemSaved.getCondominio().setCondominioId(dto.getIdCondominio());
        itemSaved.getItemPlanoManutencao().setIdItemPlanoManutencao(dto.getIdItemPlanoManutencao());

    }
}
