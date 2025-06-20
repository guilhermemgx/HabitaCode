package com.example.HabitaCode.domain.service;

import com.example.HabitaCode.domain.entity.*;
import com.example.HabitaCode.domain.entity.dto.PlanosManutencaoDTO;
import com.example.HabitaCode.domain.entity.dto.SavePlansCondominiumDTO;
import com.example.HabitaCode.domain.repositories.CondominioItemPlanoManutencaoRepository;
import com.example.HabitaCode.domain.repositories.CondominioRepository;
import com.example.HabitaCode.domain.repositories.ItemPlanoManutencaoRepository;
import com.example.HabitaCode.domain.repositories.PlanoDeManutencaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanoManutencaoService {
    private final ItemPlanoManutencaoRepository repository;
    private final PlanoDeManutencaoRepository planoDeManutencaoRepository;
    private final CondominioItemPlanoManutencaoRepository condominioItemPlanoManutencaoRepository;
    private final CondominioRepository condominioRepository;

    public List<ItemPlanoManutencao> getAll() {
        return repository.listAllItens();
    }

    public List<PlanoDeManutencao> getAllPlans() {
        return planoDeManutencaoRepository.findAll();
    }


    public PlanosManutencaoDTO getAllByPlanoId(String planoId, String condominium) {
        List<ItemPlanoManutencao> items = repository.listAllItensByPlanoId(planoId);
        List<CondominioItemPlanoManutencao> getAllByCondominiumId = condominioItemPlanoManutencaoRepository.getAllByCondominiumId(condominium);

        PlanoDeManutencao plano = items.get(0).getPlanoDeManutencao();
        PlanosManutencaoDTO dto = new PlanosManutencaoDTO();

        dto.setId(plano.getIdPlanoManutencao());
        dto.setNome(plano.getNome());

        Map<String, PlanosManutencaoDTO.GrupoDTO> gruposMap = new LinkedHashMap<>();

        for(ItemPlanoManutencao item : items){
            TipoPlanoManutencao tipo = item.getTipoPlanoManutencao();
            String tipoId = tipo.getIdTipoPlanoManutencao();

            PlanosManutencaoDTO.GrupoDTO grupo = gruposMap.computeIfAbsent(tipoId, id -> {
                PlanosManutencaoDTO.GrupoDTO novoGrupo = new PlanosManutencaoDTO.GrupoDTO();
                novoGrupo.setId(tipoId);
                novoGrupo.setNome(tipo.getNomeTipoPlanoManutencao());
                return novoGrupo;
            });

            List<CondominioItemPlanoManutencao> condItemSelect = getAllByCondominiumId.stream()
                    .filter(e ->
                            e.getItemPlanoManutencao().getIdItemPlanoManutencao().equals(item.getIdItemPlanoManutencao()))
                    .toList();

            PlanosManutencaoDTO.ItemDTO itemDTO = new PlanosManutencaoDTO.ItemDTO();
            itemDTO.setId(item.getIdItemPlanoManutencao());
            itemDTO.setNome(item.getNomeItemPlanoManutencao());
            itemDTO.setDescricao("");
            itemDTO.setRealizado(condItemSelect.size() > 0);

            grupo.getItens().add(itemDTO);
        }

        dto.setGrupos(new ArrayList<>(gruposMap.values()));

        return dto;
    }


    public void savePlansCondominium (SavePlansCondominiumDTO ids) {
        Condominio cond = condominioRepository.findById(ids.getCondominiumId())
                .orElseThrow(() -> new RuntimeException("Condomínio não encontrado"));
        ItemPlanoManutencao plan = repository.findById(ids.getPlansId())
                .orElseThrow(() -> new RuntimeException("Item de manutenção não encontrado"));

        CondominioItemPlanoManutencao.CondominioItemPlanoManutencaoId id = new CondominioItemPlanoManutencao.CondominioItemPlanoManutencaoId();
        id.setCondominioId(cond.getCondominioId());
        id.setItemPlanoManutencaoId(plan.getIdItemPlanoManutencao());

        CondominioItemPlanoManutencao condPlan = new CondominioItemPlanoManutencao();
        condPlan.setId(id);
        condPlan.setCondominio(cond);
        condPlan.setItemPlanoManutencao(plan);

        condominioItemPlanoManutencaoRepository.save(condPlan);
    }
}
