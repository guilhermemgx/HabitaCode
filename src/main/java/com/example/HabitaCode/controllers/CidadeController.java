package com.example.HabitaCode.controllers;

import com.example.HabitaCode.domain.entity.dto.CidadeDTO;
import com.example.HabitaCode.domain.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cidade")
public class CidadeController {
    private final CidadeService service;

    @GetMapping("/")
    public List<CidadeDTO> getListInCity (@RequestParam("estadoid") String estadoId) {
        return service.getCityByStateId(estadoId);
    }
}
