package org.servlet.webapp.cursos.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.servlet.webapp.cursos.interceptors.TransactionalJDBC;
import org.servlet.webapp.cursos.model.Curso;
import org.servlet.webapp.cursos.repository.CursoRepositorioImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

// Service implementation for handling Curso business logic.
@ApplicationScoped
public class CursoServiceImpl implements CursoService{

    // Dependency injection of the repository layer for Curso
    @Inject
    private CursoRepositorioImpl repository;


    @Override
    // Retrieves a list of all Curso entities
    public List<Curso> listar() {
        try {
            return repository.listar(); // Calls the repository method to fetch all Cursos
        } catch (SQLException e) {
            // Wraps and rethrows SQL exceptions as ServiceJdbcException
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Curso> porNombre(String nombre) {
        try {
            // Retrieves Cursos based on a name filter
            return repository.porNombre(nombre); // Calls the repository method to filter Cursos by name
        } catch (SQLException e) {

            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    // Retrieves a Curso by its unique ID, wrapped in an Optional
    public Optional<Curso> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id)); // Calls repository and wraps the result in Optional
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @TransactionalJDBC
    // Saves or updates a Curso entity. Marked as a transactional operation
    public void guardar(Curso curso) {
        try{
            repository.guardar(curso); // Delegates the save/update logic to the repository
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @TransactionalJDBC
    // Deletes a Curso entity by its ID. Marked as a transactional operation
    public void eliminar(Long id) {
        try{
            repository.eliminar(id); // Delegates the delete operation to the repository
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
