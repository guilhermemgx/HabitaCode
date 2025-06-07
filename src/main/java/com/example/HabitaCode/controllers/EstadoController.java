package com.example.HabitaCode.controllers;

import com.example.HabitaCode.domain.entity.dto.EstadoDTO;
import com.example.HabitaCode.domain.entity.dto.PessoaDTO;
import com.example.HabitaCode.domain.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estado")
public class EstadoController {
    private final EstadoService service;

    @GetMapping("/")
    public List<EstadoDTO> getAll () {
        return service.getAll();
    }
}
