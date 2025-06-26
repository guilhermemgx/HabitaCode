package com.example.HabitaCode.domain.service;

import com.example.HabitaCode.domain.entity.Condominio;
import com.example.HabitaCode.domain.entity.CondominioItemPlanoManutencao;
import com.example.HabitaCode.domain.entity.Pessoa;
import com.example.HabitaCode.domain.entity.Usuario;
import com.example.HabitaCode.domain.entity.dto.CondominioItemPlanoManutencaoDTO;
import com.example.HabitaCode.domain.entity.dto.PessoaDTO;
import com.example.HabitaCode.domain.repositories.CidadeRepository;
import com.example.HabitaCode.domain.repositories.CondominioRepository;
import com.example.HabitaCode.domain.repositories.PessoaRepository;
import com.example.HabitaCode.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CondominioService {
    private final PessoaRepository pessoaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<PessoaDTO> getCondominioByPessoa (String userId) {
        Usuario user = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        return pessoaRepository.findByUsuarioId(user).stream().map(p -> {
            var cond = p.getCondominioId();
            var cid = cond.getCidade();
            var est = cid.getEstado();

            return new PessoaDTO(
                    p.getPessoaId(),
                    p.getNomeCompleto(),
                    p.getCpf(),
                    cond.getNome(),
                    cid.getNome(),
                    est.getNome(),
                    p.getUsuarioId().getId()
            );
        }).toList();
    }
}
