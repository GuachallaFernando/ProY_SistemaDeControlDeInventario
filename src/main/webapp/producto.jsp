<%@page import="com.emergentes.entidades.Producto"%>
<%@page import="java.util.List"%>
<%

    List<Producto> productoas = (List<Producto>) request.getAttribute("productoas");

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Productos</h1>
        <p>
            <a href="ProductoServlet?action=add">Nuevo</a>
        </p>
        <table border 1>
            <tr>
                <th>Id</th>
                <th>Codigo</th>
                <th>Nombre</th>
                <th>Proveedor</th>
                <th>Proveedor Producto</th>
                <th>Cantidad en Stock</th>
                <th>Precio</th>
                <th></th>
                <th></th>
            </tr>
            <%     for (Producto item : productoas) {
            %>
            <tr>
                <td><%= item.getId()%></td>
                <td><%= item.getCodigo()%></td>
                <td><%= item.getNombre()%></td>
                <td><%= (item.getProveedor()!= null) ? item.getProveedor().getId() : ""%></td>
                <td><%= item.getProveedorPro()%></td>
                <td><%= item.getCantidadStock()%></td>
                <td><%= item.getPrecio()%></td>
                <td><a href="ProductoServlet?action=edit&id=<%= item.getId()%>">Editar</td>
                <td><a href="ProductoServlet?action=dele&id=<%= item.getId()%>" onclick="return(confirm('¿Está seguro de querer hacerlo?'))">Eliminar</td>
            </tr>
            <%
                }
            %>

        </table>
            <li><a href="Bienvenido.jsp">Volver</a></li>
    </body>
</html>
