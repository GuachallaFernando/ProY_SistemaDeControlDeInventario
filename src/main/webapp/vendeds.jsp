<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Vendedo"%>
<%

    List<Vendedo> vendedos = (List<Vendedo>) request.getAttribute("vendedos");
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Vendedores</h1>
        <p>
            <a href="VendedoServlet?action=add">Nuevo</a>
        </p>
        <table border 1>
            <tr>
                <th>Id</th>
                <th>CI</th>
                <th>Nombre Provvedor</th>
                <th>Telefono</th>
                <th>Direccion</th>
                <th></th>
                <th></th>
            </tr>
            <%
                for(Vendedo item : vendedos){
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getCi()%></td>
                <td><%= item.getNombre()%></td>
                <td><%= item.getTelefono()%></td>
                <td><%= item.getDireccion()%></td>
                <td><a href="VendedoServlet?action=edit&id=<%= item.getId() %>">Editar</td>
                <td><a href="VendedoServlet?action=dele&id=<%= item.getId() %>" onclick="return(confirm('Esta Seguro de quere hacerlo'))">Eliminar</td>
            </tr>
             <%
                 }
            %>
        </table>
        <li><a href="Bienvenido.jsp">Volver</a></li>
    </body>
</html>