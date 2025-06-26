package com.example.HabitaCode.domain.repositories;

import com.example.HabitaCode.domain.entity.Pessoa;
import com.example.HabitaCode.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, String> {
    List<Pessoa> findByUsuarioId(Usuario usuarioId);
}
