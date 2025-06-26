package com.example.HabitaCode.controllers;

import com.example.HabitaCode.domain.entity.dto.CidadeDTO;
import com.example.HabitaCode.domain.entity.dto.EstadoDTO;
import com.example.HabitaCode.domain.service.CidadeService;
import com.example.HabitaCode.domain.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {
    private final CidadeService cidadeService;
    private final EstadoService estadoService;

    @GetMapping("/city")
    public List<CidadeDTO> getListInCity (@RequestParam("estadoid") String estadoId) {
        return cidadeService.getCityByStateId(estadoId);
    }

    @GetMapping("/stay")
    public List<EstadoDTO> getAll () {
        return estadoService.getAll();
    }
}
