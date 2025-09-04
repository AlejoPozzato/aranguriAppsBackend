package com.challenge.aranguriAppsBackend.dto;

import jakarta.validation.constraints.*;

public record RegisterRequest(@Email(message = "Email inválido") @NotBlank(message = "El email es obligatorio") String email,
                              @NotBlank(message = "El nombre es obligatorio") String nombre,
                              @Size(min = 6, max = 20, message = "La contraseña debe tener entre 6 y 20 caracteres") @NotBlank(message = "La contraseña es obligatoria") String password) {}
