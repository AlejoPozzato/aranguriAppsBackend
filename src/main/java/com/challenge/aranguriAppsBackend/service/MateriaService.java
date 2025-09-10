package com.challenge.aranguriAppsBackend.service;

import com.challenge.aranguriAppsBackend.dto.MateriaRequest;
import com.challenge.aranguriAppsBackend.exception.MateriaNotFoundException;
import com.challenge.aranguriAppsBackend.exception.NoPermissionException;
import com.challenge.aranguriAppsBackend.model.Materia;
import com.challenge.aranguriAppsBackend.model.Usuario;
import com.challenge.aranguriAppsBackend.repository.MateriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;
    private final UsuarioService usuarioService;

    public MateriaService(MateriaRepository materiaRepository, UsuarioService usuarioService) {
        this.materiaRepository = materiaRepository;
        this.usuarioService = usuarioService;
    }
    //Crear Materias
    @Transactional
    public Materia crearMateria(int usuarioId, MateriaRequest request) {
        Usuario usuario = usuarioService.obtenerUsuarioPorId(usuarioId);

        Materia materia = new Materia();
        materia.setNombre(request.nombre());
        materia.setProfesor(request.profesor());
        materia.setDescripcion(request.descripcion());
        materia.setUsuario(usuario);
        materia.setFechaExamen1(request.fechaExamen1());
        materia.setFechaExamen2(request.fechaExamen2());

        return materiaRepository.save(materia);
    }

    //Listar materias de un usuario
    public List<Materia> listarMaterias(int usuarioId) {
        usuarioService.obtenerUsuarioPorId(usuarioId);
        return materiaRepository.findByUsuarioId(usuarioId);
    }

    //Editar materia existente
    @Transactional
    public Materia editarMateria(int usuarioId, int materiaId, MateriaRequest request) {
        Materia materia = materiaRepository.findById(materiaId)
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada"));

        if (materia.getUsuario().getId() != usuarioId) {
            throw new NoPermissionException("No tiene permiso para editar esta materia");
        }

        if (request.nombre() != null) materia.setNombre(request.nombre());
        if (request.descripcion() != null) materia.setDescripcion(request.descripcion());
        if (request.profesor() != null) materia.setProfesor(request.profesor());
        if (request.fechaExamen1() != null) {
            materia.setFechaExamen1(request.fechaExamen1());
        }
        if (request.fechaExamen2() != null) {
            materia.setFechaExamen2(request.fechaExamen2());
        }

        return materiaRepository.save(materia);
    }

    public Materia buscarPorId(int materiaId) {
        return materiaRepository.findById(materiaId)
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada"));
    }

}
