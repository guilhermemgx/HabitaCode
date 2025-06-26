package com.example.HabitaCode.controllers;

import com.example.HabitaCode.core.JwtTokenUtil;
import com.example.HabitaCode.domain.entity.Usuario;
import com.example.HabitaCode.domain.entity.dto.LoginDTO;
import com.example.HabitaCode.domain.entity.dto.LoginRequestDTO;
import com.example.HabitaCode.domain.service.UsuarioService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO.LoginRequestDTO login) {
        Optional<Usuario> usuario = usuarioService.autenticar(login.username(), login.password());

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais invalidas");
        }

        String token = jwtTokenUtil.generateToken(usuario.get().getId());
        return ResponseEntity.ok(new LoginDTO.LoginResponseDTO(usuario.get().getId(), token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuario));
    }
}
