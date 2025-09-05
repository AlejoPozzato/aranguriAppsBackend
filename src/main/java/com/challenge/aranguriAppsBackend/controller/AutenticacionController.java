package com.challenge.aranguriAppsBackend.controller;

import com.challenge.aranguriAppsBackend.dto.LoginResponse;
import com.challenge.aranguriAppsBackend.dto.LoginRequest;
import com.challenge.aranguriAppsBackend.service.AutenticacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AutenticacionController {
    private final AutenticacionService authService;

    public AutenticacionController(AutenticacionService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/autenticacion/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
