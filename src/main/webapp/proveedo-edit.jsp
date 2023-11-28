<%@page import="com.emergentes.entidades.Proveedo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Proveedo proveedo = (Proveedo) request.getAttribute("proveedo");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registra Proveedor</h1>
        <form action="ProveedoServlet" method="post">
            <label>Descripcion</label>
            <input type="hidden" name="id" value="<%= proveedo.getId()%>">
            <tr>
                <td>RUC</td>
                <input type="text" name="ruc" value="<%= proveedo.getRuc()%>">
            </tr>
            <tr>
                <td>Nombre</td>
                <input type="text" name="nombre" value="<%= proveedo.getNombre()%>">
            </tr>
            <tr>
                <td>Telefono</td>
                <input type="text" name="telefono" value="<%= proveedo.getTelefono()%>">
            </tr>
            <tr>
                <td>Direccion</td>
                <input type="text" name="direccion" value="<%= proveedo.getDireccion()%>">
            </tr>
            <input type="submit">
        </form>
    </body>
</html>
