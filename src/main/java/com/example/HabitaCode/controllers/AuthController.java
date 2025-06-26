package com.example.HabitaCode.controllers;

import com.example.HabitaCode.core.JwtTokenUtil;
import com.example.HabitaCode.domain.entity.Usuario;
import com.example.HabitaCode.domain.entity.dto.LoginDTO;
import com.example.HabitaCode.domain.entity.dto.LoginRequestDTO;
import com.example.HabitaCode.domain.enums.StatusUsuario;
import com.example.HabitaCode.domain.service.UsuarioService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UsuarioService usuarioService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO.LoginRequestDTO login) {
        try {
            Usuario usuario = usuarioService.autenticar(login.username(), login.password());
            String token = jwtTokenUtil.generateToken(usuario.getId());
            return ResponseEntity.ok(new LoginDTO.LoginResponseDTO(usuario.getId(), token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.cadastrar(usuario));
    }

    @GetMapping("/verificar")
    public ResponseEntity<String> verificarEmail(@RequestParam String token) {
        usuarioService.confirmarEmail(token);
        return ResponseEntity.ok("E-mail verificado com sucesso. Aguarde aprovação do administrador.");
    }

    @GetMapping("/pendentes")
    public List<Usuario> listarPendentes() {
        return usuarioService.listarPendentes();
    }

    @GetMapping("/usuarios/ativos")
    public List<Usuario> listarAtivos() {
        return usuarioService.listarPorStatus(StatusUsuario.ATIVO);
    }

    @GetMapping("/usuarios/inativos")
    public List<Usuario> listarInativos() {
        return usuarioService.listarPorStatusIn(List.of(StatusUsuario.SUSPENSO, StatusUsuario.DESATIVADO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/usuarios/{id}/aprovar")
    public ResponseEntity<String> aprovarUsuario(@PathVariable String id) {
        usuarioService.atualizarStatus(id, StatusUsuario.ATIVO);
        return ResponseEntity.ok("Usuário aprovado com sucesso.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/usuarios/{id}/recusar")
    public ResponseEntity<String> recusarUsuario(@PathVariable String id) {
        usuarioService.atualizarStatus(id, StatusUsuario.DESATIVADO);
        return ResponseEntity.ok("Cadastro recusado e marcado como desativado.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/usuarios/{id}/suspender")
    public ResponseEntity<String> suspenderUsuario(@PathVariable String id) {
        usuarioService.atualizarStatus(id, StatusUsuario.SUSPENSO);
        return ResponseEntity.ok("Usuário suspenso.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/usuarios/{id}/desativar")
    public ResponseEntity<String> desativarUsuario(@PathVariable String id) {
        usuarioService.atualizarStatus(id, StatusUsuario.DESATIVADO);
        return ResponseEntity.ok("Usuário desativado.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/usuarios/{id}/reativar")
    public ResponseEntity<String> reativarUsuario(@PathVariable String id) {
        usuarioService.atualizarStatus(id, StatusUsuario.ATIVO);
        return ResponseEntity.ok("Usuário reativado com sucesso.");
    }

}
