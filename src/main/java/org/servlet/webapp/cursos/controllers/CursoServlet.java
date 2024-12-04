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

// Maps this servlet to handle requests for "/index.html" and "/cursos"
@WebServlet({"/index.html", "/cursos"})
public class CursoServlet extends HttpServlet {

    // Injects the CursoService, allowing us to access its methods without manual instantiation
    @Inject
    private CursoService service;

    @Override
    // Handle GET requests. This is called when the user accesses "/index.html" or "/cursos"
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Curso> cursos = service.listar(); // Retrieves the list of all courses using the service layer

        req.setAttribute("titulo", "Listado de cursos"); // Adds a title attribute to the request for use in the JSP
        req.setAttribute("cursos", cursos); // Adds the list of courses as an attribute for rendering in the JSP

        // Forwards the request and response to the "listar.jsp" page to render the list of courses
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
