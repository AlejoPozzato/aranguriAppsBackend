package com.challenge.aranguriAppsBackend.dto;

import java.time.LocalDate;
import java.util.List;

public record MateriaResponse(int id, String nombre, String profesor, String descripcion, LocalDate fechaExamen1, LocalDate fechaExamen2 ,List<ArchivoResponse> archivos) { }

