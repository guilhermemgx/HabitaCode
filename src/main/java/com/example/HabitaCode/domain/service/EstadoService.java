package com.example.HabitaCode.domain.service;

import com.example.HabitaCode.domain.entity.Estado;
import com.example.HabitaCode.domain.entity.dto.EstadoDTO;
import com.example.HabitaCode.domain.entity.dto.PessoaDTO;
import com.example.HabitaCode.domain.repositories.CidadeRepository;
import com.example.HabitaCode.domain.repositories.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private final EstadoRepository repository;

    public List<EstadoDTO> getAll () {
        return repository.findAll().stream().map(p -> {
            return new EstadoDTO(
                p.getEstadoId(),
                p.getNome(),
                p.getUf()
            );
        }).toList();
    }
}
