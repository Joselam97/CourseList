package org.servlet.webapp.cursos.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionJDBC {
    // Static method to get a database connection using JNDI
    public static Connection getConnection() throws SQLException, NamingException {

        Context initContext = null;


        // Step 1: Create a new InitialContext instance
        // InitialContext is used to access the JNDI context, which helps in looking up resources like database connections.
        initContext = new InitialContext();

        // Step 2: Look up the environment context in JNDI
        // 'java:/comp/env' is a standard naming convention used for looking up environment-related resources
        Context envContext = (Context) initContext.lookup("java:/comp/env");

        // Step 3: Retrieve the DataSource object from the JNDI context
        // 'jdbc/mysqlDB' is the JNDI name of the DataSource configured in the server, representing a connection pool.
        // The DataSource object handles managing the connections to the database.
        DataSource ds = (DataSource) envContext.lookup("jdbc/mysqlDB");

        // Step 4: Return a connection from the DataSource
        // The Data Source is used to get a connection to the database
        return ds.getConnection();
    }
}


