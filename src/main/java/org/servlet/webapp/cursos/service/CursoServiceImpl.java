package org.servlet.webapp.cursos.service;

import org.servlet.webapp.cursos.model.Curso;
import org.servlet.webapp.cursos.repository.CursoRepositorioImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CursoServiceImpl implements CursoService{

    private CursoRepositorioImpl repository;

    public CursoServiceImpl(Connection connection) {
        this.repository = new CursoRepositorioImpl(connection);
    }

    @Override
    public List<Curso> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Curso> porNombre(String nombre) {
        try {
            return repository.porNombre(nombre);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
