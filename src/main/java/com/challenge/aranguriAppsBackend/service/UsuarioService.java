package com.challenge.aranguriAppsBackend.service;

import com.challenge.aranguriAppsBackend.dto.RegisterRequest;
import com.challenge.aranguriAppsBackend.model.Usuario;
import com.challenge.aranguriAppsBackend.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrar(RegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre());
        usuario.setEmail(request.email());
        usuario.setPassword(passwordEncoder.encode(request.password()));

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isPresent()) {
            return usuarioOpt.get();
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }
}

