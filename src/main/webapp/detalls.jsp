<%@page import="com.emergentes.entidades.Detall"%>
<%@page import="java.util.List"%>
<%

    List<Detall> detallas = (List<Detall>) request.getAttribute("detallas");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Detalles</h1>
        <p>
            <a href="DetallServlet?action=add">Nuevo</a>
        </p>
        <table border 1>
            <tr>
                <th>Id</th>
                <th>ID Venta</th>
                <th>ID producto</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th></th>
                <th></th>
            </tr>
            <%     for (Detall item : detallas) {
            %>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= (item.getIdVenta() != null) ? item.getIdVenta().getId() : ""%></td>
                <td><%= (item.getIdPro() != null) ? item.getIdPro().getId() : ""%></td>
                <td><%= item.getCantidad()%></td>
                <td><%= item.getPrecio()%></td>
                <td><a href="DetallServlet?action=edit&id=<%= item.getId()%>">Editar</td>
                <td><a href="DetallServlet?action=dele&id=<%= item.getId()%>" onclick="return(confirm('¿Está seguro de querer hacerlo?'))">Eliminar</td>
            </tr>
            <%
                }
            %>

        </table>
            <li><a href="Bienvenido.jsp">Volver</a></li>
    </body>
</html>
