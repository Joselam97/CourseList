<%@page contentType="UTF-8" import="java.util.*, org.servlet.webapp.cursos.model.*"%>
<%
List<Curso> cursos = (List<Curso>) request.getAttribute("cursos");
String titulo = (String) request.getAttribute("titulo");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tarea 9: Listado de cursos</title>
</head>
<body>
<h1><%=titulo%></h1>
<form action="<%=request.getContextPath()%>/cursos/buscar" method="post">
            <input type="text" name="nombre">
            <input type="submit" value="Buscar">
        </form>
<table>
  <tr>
    <th>id</th>
    <th>nombre</th>
    <th>instructor</th>
    <th>duracion</th>
  </tr>
  <%for (Curso c : cursos){%>
  <tr>
    <td><%=c.getId()%></td>
    <td><%=c.getNombre()%></td>
    <td><%=c.getInstructor()%></td>
    <td><%=c.getDuracion()%></td>
  </tr>
  <%}%>
</table>
</body>
</html>
