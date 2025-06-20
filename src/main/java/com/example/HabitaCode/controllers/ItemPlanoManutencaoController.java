package com.example.HabitaCode.controllers;

import com.example.HabitaCode.domain.entity.ItemPlanoManutencao;
import com.example.HabitaCode.domain.entity.PlanoDeManutencao;
import com.example.HabitaCode.domain.entity.dto.PlanosManutencaoDTO;
import com.example.HabitaCode.domain.entity.dto.SavePlansCondominiumDTO;
import com.example.HabitaCode.domain.service.PlanoManutencaoService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-plano-manutencao")
@RequiredArgsConstructor
public class ItemPlanoManutencaoController {
    private final PlanoManutencaoService service;

    @GetMapping
    public List<ItemPlanoManutencao> getAllItems() {
        return service.getAll();
    }

    @GetMapping(value = "/getall-plans")
    public List<PlanoDeManutencao> getAllPlans() {
        return service.getAllPlans();
    }

    @GetMapping(value = "/find-plans")
    public PlanosManutencaoDTO getAllItems(
            @RequestParam(value = "planoId") String planoId,
            @RequestParam(value = "condominiumId") String condominiumId) {
        return service.getAllByPlanoId(planoId, condominiumId);
    }

    @PostMapping(value = "/save-plans-condominium")
    public void savePlansCondominium(@RequestBody() SavePlansCondominiumDTO ids) {
        service.savePlansCondominium(ids);
    }
}
