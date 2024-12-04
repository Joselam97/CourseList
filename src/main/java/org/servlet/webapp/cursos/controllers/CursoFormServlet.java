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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// This servlet is mapped to the "/cursos/form" URL pattern, which handles requests to display and submit the course form
@WebServlet("/cursos/form")
public class CursoFormServlet extends HttpServlet {

    // Injecting the CursoService to access business logic for course management
    @Inject
    private CursoService service;

    // Method to handle GET requests, used for displaying the course form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id;

        // Attempt to parse the "id" parameter from the request URL to determine if we're editing an existing course
        try{
            id = Long.parseLong(req.getParameter("id")); // Parse the course ID
        } catch (NumberFormatException e){
            id = 0L; // If parsing fails, set the ID to 0 (indicating a new course)
        }

        Curso curso = new Curso(); // Create a new empty Curso object
        if (id > 0){
            // If the ID is valid, attempt to retrieve the course with that ID from the service
            Optional<Curso> o = service.porId(id);
            if (o.isPresent()) { // If the course is found, set it to the curso object
                curso = o.get();
            }
        }

        // Set the course and title attributes to be used in the form
        req.setAttribute("curso", curso);
        req.setAttribute("titulo", id > 0 ? "Editar Curso" : "Crear Curso"); // Set the form title based on whether we're editing or creating
        getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp); // Forward the request to the form.jsp page for rendering
    }


    // Method to handle POST requests, used for submitting the form data
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieve the course details from the form submission
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String instructor = req.getParameter("instructor");

        double duracion;
        // Attempt to parse the duration from the form. If parsing fails, set it to 0
        try{
            duracion = Double.parseDouble(req.getParameter("duracion"));
        } catch (NumberFormatException e){
            duracion = 0;
        }

        // Create a map to store validation errors
        Map<String, String> errores = new HashMap<>();
        // Validate the form fields and add errors if necessary
        if (nombre == null || nombre.isBlank()){
            errores.put("nombre", "el nombre es requerido!");
        }
        if (descripcion == null || descripcion.isBlank()){
            errores.put("descripcion", "la descripcion es requerida!");
        }
        if (instructor == null || instructor.isBlank()) {
            errores.put("instructor", "el instructor es requerido!");
        }
        if (duracion == 0){
            errores.put("duracion", "la duracion es requerida!");
        }

        long id;
        // Retrieve and parse the "id" parameter again to either edit or create the course
        try{
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            id = 0L;
        }

        // Create a new course object and set the fields with the submitted data
        Curso curso = new Curso();
        curso.setId(id);
        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        curso.setInstructor(instructor);
        curso.setDuracion(duracion);

        // If no errors, save the course and redirect to the course list page
        if (errores.isEmpty()){
            service.guardar(curso); // Save the course using the service
            resp.sendRedirect(req.getContextPath() + "/cursos"); // Redirect to the list of courses after saving
        } else {
            // If there are validation errors, set the errors and course data in the request and forward to the form again
            req.setAttribute("errores", errores);
            req.setAttribute("curso", curso);
            req.setAttribute("titulo", id > 0 ? "Editar Curso" : "Crear Curso"); // Set the appropriate title for the form
            getServletContext().getRequestDispatcher("/form.jsp").forward(req,resp); // Forward back to the form page to show errors
        }
    }
}
