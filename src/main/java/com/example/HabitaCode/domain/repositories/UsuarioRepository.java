package com.example.HabitaCode.domain.repositories;

import com.example.HabitaCode.domain.entity.Pessoa;
import com.example.HabitaCode.domain.entity.Usuario;
import com.example.HabitaCode.domain.enums.StatusUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByTokenVerificacaoEmail(String token);

    List<Usuario> findByStatus(StatusUsuario status);
    List<Usuario> findByStatusIn(List<StatusUsuario> statusList);

}
