package com.challenge.aranguriAppsBackend.controller;

import com.challenge.aranguriAppsBackend.dto.RegisterRequest;
import com.challenge.aranguriAppsBackend.dto.RegisterResponse;
import com.challenge.aranguriAppsBackend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/api/usuarios/registrar")
    public RegisterResponse registrar(@RequestBody @Valid RegisterRequest request) {
        var usuario = usuarioService.registrar(request);
        //Retorno Dto
        return new RegisterResponse(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
