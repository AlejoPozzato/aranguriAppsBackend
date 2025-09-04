package com.challenge.aranguriAppsBackend.repository;

import com.challenge.aranguriAppsBackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;
import java.util.*;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
