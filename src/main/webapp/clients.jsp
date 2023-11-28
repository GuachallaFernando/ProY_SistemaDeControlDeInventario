<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Client"%>
<%

    List<Client> clients = (List<Client>) request.getAttribute("clients");
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Clientes</h1>
        <p>
            <a href="ClientServlet?action=add">Nuevo</a>
        </p>
        <table border 1>
            <tr>
                <th>Id</th>
                <th>DNI</th>
                <th>Nombre Proveedor</th>
                <th>Telefono</th>
                <th>Direccion</th>
                <th></th>
                <th></th>
            </tr>
            <%
                for(Client item : clients){
            %>
            <tr>
                <td><%= item.getId() %></td>
                <td><%= item.getDni()%></td>
                <td><%= item.getNombre()%></td>
                <td><%= item.getTelefono()%></td>
                <td><%= item.getDireccion()%></td>
                <td><a href="ClientServlet?action=edit&id=<%= item.getId() %>">Editar</td>
                <td><a href="ClientServlet?action=dele&id=<%= item.getId() %>" onclick="return(confirm('Esta Seguro de quere hacerlo'))">Eliminar</td>
            </tr>
             <%
                 }
            %>
        </table>
        <li><a href="Bienvenido.jsp">Volver</a></li>
    </body>
</html>