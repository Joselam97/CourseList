package org.servlet.webapp.cursos.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.servlet.webapp.cursos.util.ConexionJDBC;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try (Connection conn = ConexionJDBC.getConnection()) {
            request.setAttribute("conn", conn);
            chain.doFilter(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Error en la conexión a la base de datos", e);
        }
    }
}
