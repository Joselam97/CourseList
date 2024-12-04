package org.servlet.webapp.cursos.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.cursos.model.Curso;
import org.servlet.webapp.cursos.service.CursoService;

import java.io.IOException;
import java.util.Optional;

// This servlet is mapped to the "/cursos/eliminar" URL pattern
@WebServlet("/cursos/eliminar")
public class CursoEliminarServlet extends HttpServlet {

    // The CursoService is injected into this servlet for handling business logic
    @Inject
    private CursoService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;

        // Attempt to parse the "id" parameter from the request URL
        try{
            id = Long.parseLong(req.getParameter("id")); // Parse the ID from the query string
        } catch (NumberFormatException e){
            id = 0L; // If parsing fails, set the id to 0
        }

        // If a valid id is provided (greater than 0), proceed with deletion logic
        if (id > 0){
            // Retrieve the course with the given ID from the service
            Optional<Curso> o = service.porId(id);

            // Check if the course with the provided ID exists
            if (o.isPresent()){
                service.eliminar(id); // If the course exists, delete it from the database
                resp.sendRedirect(req.getContextPath() + "/cursos"); // Redirect the user back to the list of courses
            } else {
                // If no course is found with the given ID, send a 404 error with a custom message
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el curso en la base de datos!");
            }
        } else {
            // If the ID is invalid (0 or missing), send a 404 error indicating that the ID must be provided
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error el id es null, se debe enviar como parametro en la url!");
        }
    }
}
