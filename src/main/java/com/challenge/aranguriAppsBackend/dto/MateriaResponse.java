package com.challenge.aranguriAppsBackend.dto;

import java.time.LocalDate;
import java.util.List;

public record MateriaResponse(Long id, String nombre, String profesor, LocalDate fechaExamen1, LocalDate fechaExamen2 ,List<ArchivoResponse> archivos) { }

