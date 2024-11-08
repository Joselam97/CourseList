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
      <p><a href="${pageContext.request.contextPath}/cursos/form">Crear [+]</a></p>
      <form action="${pageContext.request.contextPath}/cursos/buscar" method="post">
            <input type="text" name="nombre">
            <input type="submit" value="Buscar">
      </form>
      <table>
         <tr>
            <th>id</th>
            <th>nombre</th>
            <th>instructor</th>
            <th>duracion</th>
            <th>editar</th>
            <th>eliminar</th>
         </tr>
         <c:forEach items="${cursos}" var="c">
         <tr>
            <td>${c.id}</td>
            <td>${c.nombre}</td>
            <td>${c.instructor}</td>
            <td>${c.duracion}</td>
            <td><a href="${pageContext.request.contextPath}/cursos/form?id=${c.id}">Editar</a></td>
            <td><a onclick="return confirm('esta seguro que desea eliminar?');"
            href="${pageContext.request.contextPath}/cursos/eliminar?id=${c.id}">Eliminar</a></td>
         </tr>
         </c:forEach>
      </table>
   </body>
</html>
