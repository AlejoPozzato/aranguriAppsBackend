package com.challenge.aranguriAppsBackend.dto;

import jakarta.validation.constraints.*;

public record LoginRequest(@Email(message = "Email inválido") @NotBlank(message = "El email es obligatorio") String email,
                           @NotBlank(message = "La contraseña es obligatoria") String password) { }
