<%@page import="com.emergentes.entidades.Vent"%>
<%@page import="java.util.List"%>
<%

    List<Vent> ventas = (List<Vent>) request.getAttribute("ventas");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Ventas</h1>
        <p>
            <a href="VentServlet?action=add">Nuevo</a>
        </p>
        <table border 1>
            <tr>
                <th>Id</th>
                <th>Cliente</th>
                <th>Nombre_Cliente</th>
                <th>Vendedor</th>
                <th>Fecha</th>
                <th>Total</th>
                <th></th>
                <th></th>
            </tr>
            <%    for (Vent item : ventas) {
            %>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= (item.getCliente() != null) ? item.getCliente().getId() : ""%></td>
                <td><%= item.getNombreCli()%></td>
                <td><%= (item.getVendedor() != null) ? item.getVendedor().getId() : ""%></td>
                <td><%= item.getFecha()%></td>
                <td><%= item.getTotal()%></td>
                <td><a href="VentServlet?action=edit&id=<%= item.getId()%>">Editar</td>
                <td><a href="VentServlet?action=dele&id=<%= item.getId()%>" onclick="return(confirm('¿Está seguro de querer hacerlo?'))">Eliminar</td>
            </tr>
            <%
                }
            %>
        </table>
            <li><a href="Bienvenido.jsp">Volver</a></li>
    </body>
</html>
