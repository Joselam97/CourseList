package org.servlet.webapp.cursos.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.cursos.model.Curso;
import org.servlet.webapp.cursos.service.CursoService;
import org.servlet.webapp.cursos.service.CursoServiceImpl;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet({"/index.html", "/cursos"})
public class CursoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CursoService service = new CursoServiceImpl(conn);
        List<Curso> cursos = service.listar();
        req.setAttribute("titulo", "Listado de cursos");
        req.setAttribute("cursos", cursos);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
