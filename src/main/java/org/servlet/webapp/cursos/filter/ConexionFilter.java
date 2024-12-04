package org.servlet.webapp.cursos.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.servlet.webapp.cursos.service.ServiceJdbcException;

import java.io.IOException;

// This filter is applied to all incoming requests (/*)
@WebFilter("/*")
public class ConexionFilter implements Filter {

    @Override
    // The `doFilter` method intercepts the request/response cycle
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


            try {
                // Passes the request and response to the next filter or servlet in the chain
                chain.doFilter(request, response);

                // Catches any ServiceJdbcException thrown during request processing
            } catch (ServiceJdbcException e) {

                // Sends an HTTP 500 Internal Server Error response with the exception's message
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                // Prints the stack trace of the exception for debugging purposes
                e.printStackTrace();
            }
    }
}