package com.example.HabitaCode.controllers;

import com.example.HabitaCode.domain.entity.Pessoa;
import com.example.HabitaCode.domain.entity.dto.PessoaDTO;
import com.example.HabitaCode.domain.entity.dto.PessoaRequestDTO;
import com.example.HabitaCode.domain.service.PessoaService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")
public class PessoaController {
    private final PessoaService service;

    @GetMapping("/")
    public List<PessoaDTO> getAll () {
        return service.getAll();
    }

    @PostMapping("/save")
    public Pessoa save (@RequestBody PessoaRequestDTO dto) {
        return service.save(dto);
    }

}
