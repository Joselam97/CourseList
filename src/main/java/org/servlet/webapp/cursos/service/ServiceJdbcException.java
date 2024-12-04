package org.servlet.webapp.cursos.service;

// Constructor that accepts a message and a cause
public class ServiceJdbcException extends RuntimeException {
    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause); // Pass the message and cause to the superclass (RuntimeException)
    }
}
