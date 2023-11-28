<%@page import="com.emergentes.entidades.Vendedo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Vendedo vendedo = (Vendedo) request.getAttribute("vendedo");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registra Vendedor</h1>
        <form action="VendedoServlet" method="post">
            <label>Descripcion</label>
            <input type="hidden" name="id" value="<%= vendedo.getId()%>">
            <tr>
                <td>CI</td>
                <input type="text" name="ci" value="<%= vendedo.getCi()%>">
            </tr>
            <tr>
                <td>Nombre</td>
                <input type="text" name="nombre" value="<%= vendedo.getNombre()%>">
            </tr>
            <tr>
                <td>Telefono</td>
                <input type="text" name="telefono" value="<%= vendedo.getTelefono()%>">
            </tr>
            <tr>
                <td>Direccion</td>
                <input type="text" name="direccion" value="<%= vendedo.getDireccion()%>">
            </tr>
            <input type="submit">
        </form>
    </body>
</html>