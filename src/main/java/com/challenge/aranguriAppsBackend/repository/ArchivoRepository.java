package com.challenge.aranguriAppsBackend.repository;

import com.challenge.aranguriAppsBackend.model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {
    List<Archivo> findByMateriaId(Integer materiaId);
}
