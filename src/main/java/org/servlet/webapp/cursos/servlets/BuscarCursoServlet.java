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

@WebServlet("/cursos/buscar")
public class BuscarCursoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        CursoService service = new CursoServiceImpl(conn);
        String nombre = req.getParameter("nombre");

        List<Curso> cursos = service.porNombre(nombre);

        req.setAttribute("titulo", "Filtrando Cursos");
        req.setAttribute("cursos", cursos);
        getServletContext().getRequestDispatcher("/listar.jsp").forward(req, resp);
    }
}
