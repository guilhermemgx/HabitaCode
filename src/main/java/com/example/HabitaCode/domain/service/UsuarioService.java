package com.example.HabitaCode.domain.service;

import com.example.HabitaCode.domain.entity.Usuario;
import com.example.HabitaCode.domain.enums.StatusUsuario;
import com.example.HabitaCode.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    public Usuario autenticar(String username, String senha) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas."));

        if (!passwordEncoder.matches(senha, usuario.getPassword())) {
            throw new RuntimeException("Credenciais inválidas.");
        }

        if (!usuario.isEmailVerificado()) {
            throw new RuntimeException("E-mail não verificado. Verifique sua caixa de entrada.");
        }

        StatusUsuario status = usuario.getStatus();
        if (status != StatusUsuario.ATIVO) {
            throw switch (status) {
                case PENDENTE -> new RuntimeException("Seu cadastro ainda não foi aprovado.");
                case SUSPENSO -> new RuntimeException("Seu acesso foi suspenso temporariamente.");
                case DESATIVADO -> new RuntimeException("Sua conta foi desativada. Entre em contato com o suporte.");
                default -> new RuntimeException("Status de usuário inválido.");
            };
        }

        return usuario;
    }

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe.");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRole("USER");
        usuario.setStatus(StatusUsuario.PENDENTE);
        usuario.setTokenVerificacaoEmail(UUID.randomUUID().toString());

        Usuario novoUsuario = usuarioRepository.save(usuario);
        emailService.enviarVerificacaoEmail(usuario.getUsername(), usuario.getTokenVerificacaoEmail());


        return novoUsuario;
    }

    public void confirmarEmail(String token) {
        Usuario usuario = usuarioRepository.findByTokenVerificacaoEmail(token)
                .orElseThrow(() -> new RuntimeException("Token inválido ou expirado."));

        usuario.setEmailVerificado(true);
        usuario.setTokenVerificacaoEmail(null);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> listarPendentes() {
        return usuarioRepository.findByStatus(StatusUsuario.PENDENTE);
    }

    public void atualizarStatus(String id, StatusUsuario novoStatus) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setStatus(novoStatus);
        usuarioRepository.save(usuario);
    }
    public List<Usuario> listarPorStatus(StatusUsuario status) {
        return usuarioRepository.findByStatus(status);
    }

    public List<Usuario> listarPorStatusIn(List<StatusUsuario> statusList) {
        return usuarioRepository.findByStatusIn(statusList);
    }




}
