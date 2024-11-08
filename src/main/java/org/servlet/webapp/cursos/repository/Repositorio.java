package org.servlet.webapp.cursos.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repositorio<T> {

    List<T> listar() throws SQLException;

    List<T> porNombre(String nombre) throws SQLException;

    T porId(Long id) throws SQLException;

    void guardar(T t) throws SQLException;

    void eliminar(Long id) throws SQLException;
}