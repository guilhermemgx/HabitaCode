package com.example.HabitaCode.controllers;

import com.example.HabitaCode.domain.entity.ItemPlanoManutencao;
import com.example.HabitaCode.domain.entity.Pessoa;
import com.example.HabitaCode.domain.entity.PlanoDeManutencao;
import com.example.HabitaCode.domain.entity.dto.PessoaDTO;
import com.example.HabitaCode.domain.entity.dto.PessoaRequestDTO;
import com.example.HabitaCode.domain.entity.dto.PlanosManutencaoDTO;
import com.example.HabitaCode.domain.entity.dto.SavePlansCondominiumDTO;
import com.example.HabitaCode.domain.service.CondominioService;
import com.example.HabitaCode.domain.service.PessoaService;
import com.example.HabitaCode.domain.service.PlanoManutencaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/condominium")
public class condominiumController {
    private final CondominioService condominioService;
    private final PlanoManutencaoService planoManutencaoService;
    private final PessoaService pessoaService;

    @GetMapping("/condominium-by-userid")
    public List<PessoaDTO> getCondominioByPessoa (@RequestParam String userId) {
        return condominioService.getCondominioByPessoa(userId);
    }

    @GetMapping(value = "/getall-plans")
    public List<PlanoDeManutencao> getAllPlans() {
        return planoManutencaoService.getAllPlans();
    }

    @GetMapping(value = "/find-item-plans")
    public PlanosManutencaoDTO getAllItems(
            @RequestParam(value = "planoId") String planoId,
            @RequestParam(value = "condominiumId") String condominiumId) {
        return planoManutencaoService.getAllByPlanoId(planoId, condominiumId);
    }

    @PostMapping(value = "/save-item-condominium")
    public void savePlansCondominium(@RequestBody() SavePlansCondominiumDTO ids) {
        planoManutencaoService.savePlansCondominium(ids);
    }

    @PostMapping(value = "/register-condominium")
    public void registerCondominium(@RequestBody() PessoaRequestDTO dto) {
        pessoaService.save(dto);
    }

}
