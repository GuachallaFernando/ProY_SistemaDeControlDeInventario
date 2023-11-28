<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Proveedo"%>
<%

    List<Proveedo> proveedos = (List<Proveedo>) request.getAttribute("proveedos");
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Proveedores</h1>
        <p>
            <a href="ProveedoServlet?action=add">Nuevo</a>
        </p>
        <table border 1>
            <tr>
                <th>Id</th>
                <th>RUC</th>
                <th>Nombre Provvedor</th>
                <th>Telefono</th>
                <th>Direccion</th>
                <th></th>
                <th></th>
            </tr>
            <%
                for(Proveedo item : proveedos){
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getRuc()%></td>
                <td><%= item.getNombre()%></td>
                <td><%= item.getTelefono()%></td>
                <td><%= item.getDireccion()%></td>
                <td><a href="ProveedoServlet?action=edit&id=<%= item.getId() %>">Editar</td>
                <td><a href="ProveedoServlet?action=dele&id=<%= item.getId() %>" onclick="return(confirm('Esta Seguro de quere hacerlo'))">Eliminar</td>
            </tr>
             <%
                 }
            %>
        </table>
        <li><a href="Bienvenido.jsp">Volver</a></li>
    </body>
</html>