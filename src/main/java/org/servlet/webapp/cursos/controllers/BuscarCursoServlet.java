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
import java.util.List;

// The servlet is mapped to the "/cursos/buscar" URL pattern.
@WebServlet("/cursos/buscar")
public class BuscarCursoServlet extends HttpServlet {

    // This annotation injects an instance of CursoService to handle the business logic
    @Inject
    private CursoService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieve the "nombre" parameter from the HTTP request (search keyword)
        String nombre = req.getParameter("nombre");

        // Call the service method to find courses by name
        List<Curso> cursos = service.porNombre(nombre);

        // Set attributes to be forwarded to the view (JSP)
        req.setAttribute("titulo", "Filtrando Cursos");// Set the title for the page
        req.setAttribute("cursos", cursos); // Set the list of filtered courses

        // Forward the request to "listar.jsp" for rendering the results
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
