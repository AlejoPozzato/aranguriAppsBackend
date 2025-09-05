package com.challenge.aranguriAppsBackend.repository;

import com.challenge.aranguriAppsBackend.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    List<Materia> findByUsuarioId(Integer usuarioId);
}
