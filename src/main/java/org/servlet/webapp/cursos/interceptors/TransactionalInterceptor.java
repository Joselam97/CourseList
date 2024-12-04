package org.servlet.webapp.cursos.interceptors;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import org.servlet.webapp.cursos.service.ServiceJdbcException;

import java.sql.Connection;
import java.util.logging.Logger;

@TransactionalJDBC // Custom annotation used to activate this interceptor
@Interceptor // Specifies that this class intercepts method calls
public class TransactionalInterceptor {

    // The database connection injected via CDI
    @Inject
    private Connection conn;

    // Logger for logging transactional details
    @Inject
    private Logger log;

    // Specifies that this method wraps around the target method invocation
    @AroundInvoke
    public Object transactional(InvocationContext invocation) throws Exception {

        // Ensure the connection is not in auto-commit mode to allow transaction control
        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false); // Disable auto-commit to manage transactions manually
        }

        try {
            // Log the start of the transaction
            log.info(" ------> Iniciando Transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());

            // Proceed with the execution of the intercepted method
            Object resultado = invocation.proceed();
            // Commit the transaction after successful execution
            conn.commit();

            // Log the successful completion of the transaction
            log.info(" ------> Realizando Commit y Finalizando Transaccion " + invocation.getMethod().getName() +
                    " de la clase " + invocation.getMethod().getDeclaringClass());

            // Return the result from the intercepted method
            return resultado;

        } catch (ServiceJdbcException e) {
            // If an exception occurs, roll back the transaction to maintain data integrity
            conn.rollback();
            // Re-throw the exception to propagate it to the caller
            throw e;
        }
    }
}
