package org.servlet.webapp.cursos.repository;

import java.sql.SQLException;
import java.util.List;

// Generic interface to define basic CRUD (Create, Read, Update, Delete) operations for any type T
public interface Repositorio<T> {

    // Method to list all elements of type T from the database.
    // Throws SQLException to handle any database access errors.
    List<T> listar() throws SQLException;

    // Method to retrieve elements of type T that match a specific name pattern.
    List<T> porNombre(String nombre) throws SQLException;

    // Method to retrieve an element of type T by its unique identifier (id).
    T porId(Long id) throws SQLException;

    // Method to save (insert or update) an element of type T in the database
    void guardar(T t) throws SQLException;

    // Method to delete an element of type T from the database using its id
    void eliminar(Long id) throws SQLException;
}