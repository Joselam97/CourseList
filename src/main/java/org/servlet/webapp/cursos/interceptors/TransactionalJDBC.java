package org.servlet.webapp.cursos.interceptors;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@InterceptorBinding // Marks this annotation as a binding for interceptors
@Retention(RetentionPolicy.RUNTIME) // Makes the annotation available at runtime, essential for dynamic processing

/* Specifies where this annotation can be applied:
   - ElementType.METHOD: Can be used on methods.
   - ElementType.TYPE: Can be used on classes or interfaces. */
@Target({ElementType.METHOD, ElementType.TYPE})

public @interface TransactionalJDBC {
    // This is a marker annotation (no methods defined),
    // used solely to bind transactional behavior to methods or classes
}
