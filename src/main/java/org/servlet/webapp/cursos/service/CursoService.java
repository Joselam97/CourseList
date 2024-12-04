package org.servlet.webapp.cursos.service;

import org.servlet.webapp.cursos.model.Curso;
import java.util.List;
import java.util.Optional;

// Service interface for handling business logic related to Curso entities
public interface CursoService {

    // Method to retrieve a list of all Curso objects.
    // Returns a List containing all Curso objects from the repository
    List<Curso> listar();

    // Method to search for Curso objects by name.
    // Accepts a 'nombre' parameter to filter the results.
    // Returns a List of Curso objects that match the search criteria
    List<Curso> porNombre(String nombre);

    // Method to retrieve a Curso object by its unique ID
    // Returns an Optional containing the Curso object if found, or an empty Optional if not
    Optional<Curso> porId(Long id);

    // Method to save or update a Curso object
    void guardar(Curso curso);

    // Method to delete a Curso object by its unique ID
    void eliminar(Long id);
}

