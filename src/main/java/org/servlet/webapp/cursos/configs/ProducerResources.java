package org.servlet.webapp.cursos.configs;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Inject;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

/* This annotation specifies that the class is scoped at the application level, meaning a single instance
of this class is used for the entire application lifecycle. */
@ApplicationScoped
public class ProducerResources {

    // Injects a Logger instance to log information. Logger is managed by the CDI container.
    @Inject
    private Logger log;

    // This annotation is used to inject a DataSource resource with the name "jdbc/mysqlDB", which points to the database connection pool
    @Resource(name = "jdbc/mysqlDB")
    private DataSource ds;

    @Produces // Marks the method as a producer of a CDI bean
    @RequestScoped // This annotation ensures that a new Connection instance is created for each HTTP request
    private Connection beanConnection() throws NamingException, SQLException {
        return ds.getConnection();
    }

    @Produces // Marks the method as a producer for the Logger bean
    private Logger beanLogger(InjectionPoint injectionPoint){
        // Creates and returns a Logger instance that is associated with the class where it's being injected
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    // The method below is used to close a Connection when it is no longer needed
    public void close(@Disposes Connection connection) throws SQLException {
        connection.close(); // Closes the database connection
        log.info("Cerrando la Conexion a la Base de Datos MySql!"); // Logs a message indicating the connection is being closed.
    }


}
