package org.servlet.webapp.cursos.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.servlet.webapp.cursos.model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// This annotation specifies that the bean is application-scoped, meaning a single instance is shared across the application
@ApplicationScoped
// Implementation of the Repositorio interface for the Curso model
public class CursoRepositorioImpl implements Repositorio<Curso> {

    // Dependency injection for the database connection
    @Inject
    private Connection conn;


    @Override
    // Retrieves a list of all courses from the database
    public List<Curso> listar() throws SQLException {
        // Initialize an empty list to store courses
        List<Curso> cursos = new ArrayList<>();

        try (Statement stmt = conn.createStatement(); // Create a Statement object
             ResultSet rs = stmt.executeQuery("SELECT * FROM cursos as c")) { // Execute the query
            while(rs.next()) {
                Curso c = getCurso(rs); // Map the current row to a Curso object
                cursos.add(c); // Add the course to the list
            }
        }
        return cursos;
    }

    @Override
    public List<Curso> porNombre(String nombre) throws SQLException {
        // Retrieves a list of courses whose names match a pattern
        List<Curso> cursos = new ArrayList<>(); // Initialize an empty list to store results

        // Use a prepared statement to prevent SQL injection
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cursos as c WHERE c.nombre like ?")) {
            stmt.setString(1, "%" + nombre + "%"); // Set the pattern for the query

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cursos.add(getCurso(rs)); // Map each result to a Curso object
                }
            }
        }
        return cursos;
    }

    @Override
    // Retrieves a course by its unique ID
    public Curso porId(Long id) throws SQLException {
        // Initialize to null in case no course is found
        Curso curso = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cursos as c WHERE c.id=?")){
            stmt.setLong(1,id); // Set the ID parameter

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    curso = getCurso(rs); // Map the result to a Curso object if found
                }
            }
        }
        return curso;
    }

    @Override
    // Saves or updates a course in the database
    public void guardar(Curso curso) throws SQLException {
        String sql;
        // If the course has a valid ID, update the existing record
        if(curso.getId() != null && curso.getId() > 0){
            sql = "UPDATE cursos SET nombre=?, descripcion=?, instructor=?, duracion=? WHERE id=?";

            // Otherwise, insert a new record
        } else {
            sql = "INSERT INTO cursos (nombre, descripcion, instructor, duracion) VALUES (?,?,?,?)";
        }

        // Set the common parameters for both insert and update operations
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setString(3, curso.getInstructor());
            stmt.setDouble(4,curso.getDuracion());

            if (curso.getId() != null && curso.getId() > 0){
                stmt.setLong(5,curso.getId()); // Set the ID for update operation
            }
            stmt.executeUpdate(); // Execute the insert or update statement
        }
    }

    @Override
    // Deletes a course from the database by its ID
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM cursos WHERE id=?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1,id); // Set the ID parameter
            stmt.executeUpdate(); // Execute the delete statement
        }
    }


    // Utility method to map a ResultSet row to a Curso object
    private static Curso getCurso(ResultSet rs) throws SQLException {
        Curso c = new Curso();
        c.setId(rs.getLong("id"));
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setInstructor(rs.getString("instructor"));
        c.setDuracion(rs.getDouble("duracion"));
        return c;
    }
}


