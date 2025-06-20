package com.example.HabitaCode.controllers;

import com.example.HabitaCode.domain.service.CondominioItemPlanoManutencaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/condominio")
public class CondominioItemPlanoManutencaoController {
    private final CondominioItemPlanoManutencaoService service;


}
