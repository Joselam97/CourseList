package org.servlet.webapp.cursos.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.servlet.webapp.cursos.interceptors.TransactionalJDBC;
import org.servlet.webapp.cursos.model.Curso;
import org.servlet.webapp.cursos.repository.CursoRepositorioImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CursoServiceImpl implements CursoService{

    @Inject
    private CursoRepositorioImpl repository;


    @Override
    public List<Curso> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Curso> porNombre(String nombre) {
        try {
            return repository.porNombre(nombre);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Curso> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @TransactionalJDBC
    public void guardar(Curso curso) {
        try{
            repository.guardar(curso);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @TransactionalJDBC
    public void eliminar(Long id) {
        try{
            repository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
