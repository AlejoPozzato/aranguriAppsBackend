package com.challenge.aranguriAppsBackend.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record MateriaRequest(

        @NotBlank(message = "El nombre de la materia no puede estar vacío")
        @Size(max = 100, message = "El nombre de la materia no puede superar los 100 caracteres")
        String nombre,

        @Size(max = 100, message = "El nombre del profesor no puede superar los 100 caracteres")
        String profesor,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @Future(message = "Fecha de exámen inválida")
        LocalDate fechaExamen1,

        @Future(message = "Fecha de exámen inválida")
        LocalDate fechaExamen2
) { }

