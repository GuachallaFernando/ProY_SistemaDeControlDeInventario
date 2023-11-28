<%@page import="com.emergentes.entidades.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Client client = (Client) request.getAttribute("client");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>cRegistra Cliente</h1>
        <form action="ClientServlet" method="post">
            <label>Descripcion</label>
            <input type="hidden" name="id" value="<%= client.getId()%>">
            <tr>
                <td>DNI</td>
                <input type="text" name="dni" value="<%= client.getDni()%>">
            </tr>
            <tr>
                <td>Nombre</td>
                <input type="text" name="nombre" value="<%= client.getNombre()%>">
            </tr>
            <tr>
                <td>Telefono</td>
                <input type="text" name="telefono" value="<%= client.getTelefono()%>">
            </tr>
            <tr>
                <td>Direccion</td>
                <input type="text" name="direccion" value="<%= client.getDireccion()%>">
            </tr>
            <input type="submit">
        </form>
    </body>
</html>