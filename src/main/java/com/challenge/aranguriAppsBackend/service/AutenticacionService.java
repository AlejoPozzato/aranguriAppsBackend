package com.challenge.aranguriAppsBackend.service;

import com.challenge.aranguriAppsBackend.dto.LoginRequest;
import com.challenge.aranguriAppsBackend.dto.LoginResponse;
import com.challenge.aranguriAppsBackend.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AutenticacionService(UsuarioService usuarioService,
                                PasswordEncoder passwordEncoder,
                                JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioService.buscarPorEmail(request.email());

        if (!passwordEncoder.matches(request.password(), usuario.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtService.generarToken(usuario.getEmail());
        //Retorno Dto
        return new LoginResponse(token, usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
