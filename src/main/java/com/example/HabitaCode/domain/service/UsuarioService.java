package com.example.HabitaCode.domain.service;

import com.example.HabitaCode.domain.entity.Usuario;
import com.example.HabitaCode.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public Optional<Usuario> autenticar(String username, String senha) {
        return usuarioRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(senha, user.getPassword()));
    }

    public Usuario cadastrar(Usuario usuario) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe.");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRole("USER"); // ou defina conforme sua lógica
        return usuarioRepository.save(usuario);
    }

}
