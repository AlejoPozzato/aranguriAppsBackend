package com.challenge.aranguriAppsBackend.controller;

import com.challenge.aranguriAppsBackend.dto.ArchivoResponse;
import com.challenge.aranguriAppsBackend.dto.MateriaRequest;
import com.challenge.aranguriAppsBackend.dto.MateriaResponse;
import com.challenge.aranguriAppsBackend.model.Materia;
import com.challenge.aranguriAppsBackend.service.JwtService;
import com.challenge.aranguriAppsBackend.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/materias")
public class MateriaController {

    private final MateriaService materiaService;
    private final JwtService jwtService;

    public MateriaController(MateriaService materiaService, JwtService jwtService) {
        this.materiaService = materiaService;
        this.jwtService = jwtService;
    }

    //Crear materia
    @PostMapping
    public ResponseEntity<MateriaResponse> crearMateria(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody MateriaRequest request) {

        int usuarioId = jwtService.extraerUserId(token.substring(7));
        Materia materia = materiaService.crearMateria(usuarioId, request);

        MateriaResponse response = new MateriaResponse(
                materia.getId(),
                materia.getNombre(),
                materia.getDescripcion(),
                materia.getProfesor(),
                materia.getFechaExamen1(),
                materia.getFechaExamen2(),
                materia.getArchivos().stream()
                        .map(a -> new ArchivoResponse(a.getId(), a.getNombre(), a.getTipo(), a.getUrl()))
                        .toList()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Listar materias del usuario
    @GetMapping
    public ResponseEntity<List<MateriaResponse>> listarMaterias(
            @RequestHeader("Authorization") String token) {

        int usuarioId = jwtService.extraerUserId(token.substring(7));
        List<MateriaResponse> response = materiaService.listarMaterias(usuarioId)
                .stream()
                .map(m -> new MateriaResponse(
                        m.getId(),
                        m.getNombre(),
                        m.getProfesor(),
                        m.getDescripcion(),
                        m.getFechaExamen1(),
                        m.getFechaExamen2(),
                        m.getArchivos().stream()
                                .map(a -> new ArchivoResponse(a.getId(), a.getNombre(), a.getTipo(), a.getUrl()))
                                .toList()
                )).toList();

        return ResponseEntity.ok(response);
    }

    //Editar materia
    @PutMapping("/{materiaId}")
    public ResponseEntity<MateriaResponse> editarMateria(
            @RequestHeader("Authorization") String token,
            @PathVariable int materiaId,
            @RequestBody MateriaRequest request) {

        int usuarioId = jwtService.extraerUserId(token.substring(7));
        Materia materia = materiaService.editarMateria(usuarioId, materiaId, request);

        MateriaResponse response = new MateriaResponse(
                materia.getId(),
                materia.getNombre(),
                materia.getDescripcion(),
                materia.getProfesor(),
                materia.getFechaExamen1(),
                materia.getFechaExamen2(),
                materia.getArchivos().stream()
                        .map(a -> new ArchivoResponse(a.getId(), a.getNombre(), a.getTipo(), a.getUrl()))
                        .toList()
        );

        return ResponseEntity.ok(response);
    }
}
