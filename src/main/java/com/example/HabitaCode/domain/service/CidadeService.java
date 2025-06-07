package com.example.HabitaCode.domain.service;

import com.example.HabitaCode.domain.entity.Cidade;
import com.example.HabitaCode.domain.entity.dto.CidadeDTO;
import com.example.HabitaCode.domain.entity.dto.PessoaDTO;
import com.example.HabitaCode.domain.repositories.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final CidadeRepository repository;

    public List<CidadeDTO> getCityByStateId (String estadoId) {
        List<Cidade> listCity = repository.findCidade(estadoId);
        List<CidadeDTO> cityDto = new ArrayList<>();
        listCity.stream().forEach(e -> {
            cityDto.add(new CidadeDTO(
                    e.getCidadeId(),
                    e.getNome(),
                    "", ""
            ));
        });

        return cityDto;
    }
}
