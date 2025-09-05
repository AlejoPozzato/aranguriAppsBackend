package com.challenge.aranguriAppsBackend.dto;

import jakarta.validation.constraints.*;

public record ArchivoRequest(

        @NotBlank(message = "El nombre del archivo no puede estar vacío")
        @Size(max = 150, message = "El nombre del archivo no puede superar los 150 caracteres")
        String nombre,

        @NotBlank(message = "El tipo de archivo no puede estar vacío")
        String tipo,

        @NotBlank(message = "La URL del archivo no puede estar vacía")
        String url

) { }

