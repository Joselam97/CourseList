package org.servlet.webapp.cursos.service;

import org.servlet.webapp.cursos.model.Curso;
import java.util.List;

public interface CursoService {
    List<Curso> listar();
    List<Curso> porNombre(String nombre);
}

