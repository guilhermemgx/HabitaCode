package com.example.HabitaCode.domain.service;

import com.example.HabitaCode.domain.entity.Cidade;
import com.example.HabitaCode.domain.entity.Condominio;
import com.example.HabitaCode.domain.entity.Pessoa;
import com.example.HabitaCode.domain.entity.dto.PessoaDTO;
import com.example.HabitaCode.domain.entity.dto.PessoaRequestDTO;
import com.example.HabitaCode.domain.repositories.CidadeRepository;
import com.example.HabitaCode.domain.repositories.CondominioRepository;
import com.example.HabitaCode.domain.repositories.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaService {
    private final PessoaRepository repository;
    private final CondominioRepository condominioRepository;
    private final CidadeRepository cidadeRepository;


    public List<PessoaDTO> getAll () {
        return repository.findAll().stream().map(p -> {
            var cond = p.getCondominio();
            var cid = cond.getCidade();
            var est = cid.getEstado();

            return new PessoaDTO(
                    p.getId(),
                    p.getNomeCompleto(),
                    p.getCpf(),
                    cond.getNome(),
                    cid.getNome(),
                    est.getNome()
            );
        }).toList();
    }


    public Pessoa save(PessoaRequestDTO dto){
        Condominio condominio = condominioWriter(dto);
        Pessoa pessoa = pessoaWriter(dto);
        pessoa.setCondominio(condominio);
        return repository.save(pessoa);
    }

    private Condominio condominioWriter (PessoaRequestDTO dto) {
        Condominio condominio = new Condominio();
        condominio.setNome(dto.getCondominio());
        Cidade findItem = cidadeRepository.findById(dto.getCidadeId()).orElseThrow(() -> new EntityNotFoundException("Cidade não encontrada"));
        condominio.setCidade(findItem);

        return condominioRepository.save(condominio);
    }

    private Pessoa pessoaWriter (PessoaRequestDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(dto.getCpf());
        pessoa.setNomeCompleto(dto.getNomeCompleto());
        pessoa.setEmail(dto.getEmail());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setDataNascimento(dto.getDataNascimento());
        return pessoa;
    }


}
