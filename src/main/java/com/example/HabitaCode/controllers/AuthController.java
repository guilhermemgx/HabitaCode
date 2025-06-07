package com.example.HabitaCode.controllers;

import com.example.HabitaCode.domain.entity.Usuario;
import com.example.HabitaCode.domain.entity.dto.LoginRequestDTO;
import com.example.HabitaCode.domain.service.UsuarioService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO login) {
        return usuarioService.autenticar(login.getUsername(), login.getPassword())
                .map(user -> ResponseEntity.ok("Login realizado com sucesso"))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuario));
    }
}
