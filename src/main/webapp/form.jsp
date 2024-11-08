<%@page contentType="text/html" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${titulo}</title>
    </head>
    <body>
        <h1>${titulo}</h1>
        <p><a href="${pageContext.request.contextPath}/cursos">Volver</a></p>
        <form action="${pageContext.request.contextPath}/cursos/form" method="post">
            <div>
                <label for="nombre">Nombre</label>
                <div>
                    <input type="text" name="nombre" id="nombre" value="${curso.nombre}">
                </div>
                <c:if test="${errores != null && errores.containsKey('nombre')}">
                   <div style="color:red;">${errores.nombre}</div>
                </c:if>
            </div>

            <div>
                <label for="instructor">Instructor</label>
                <div>
                    <input type="text" name="instructor" id="instructor" value="${curso.instructor}">
                </div>
                <c:if test="${errores != null && errores.containsKey('instructor')}">
                   <div style="color:red;">${errores.instructor}</div>
                </c:if>
            </div>

            <div>
                <label for="duracion">Duracion</label>
                <div>
                    <input type="text" name="duracion" id="duracion" value="${curso.duracion != null && curso.duracion > 0? curso.duracion: ""}">
                </div>
                <c:if test="${errores != null && errores.containsKey('duracion')}">
                   <div style="color:red;">${errores.duracion}</div>
                </c:if>
            </div>

            <div>
                <label for="descripcion">Descripcion</label>
                <div>
                    <textarea name="descripcion" id="descripcion">${curso.descripcion}</textarea>
                </div>
                <c:if test="${errores != null && errores.containsKey('descripcion')}">
                   <div style="color:red;">${errores.descripcion}</div>
                </c:if>
            </div>


            <div><input type="submit" value="${curso.id != null && curso.id>0? "Editar": "Crear"}"></div>
            <input type="hidden" name="id" value="${curso.id}">
        </form>
    </body>
</html>