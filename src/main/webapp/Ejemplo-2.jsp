<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    List<Client> clients = (List<Client>) request.getAttribute("clients");
    
%>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de Clientes</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        header {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 20px;
        }

        section {
            margin: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #555;
            color: white;
        }

        a {
            text-decoration: none;
            color: #3498db;
        }

        a:hover {
            color: #2980b9;
        }
    </style>
</head>
<body>
    <header>
        <h1>Listado de Clientes</h1>
    </header>
    
    <section>
        <p>
            <a href="ClientServlet?action=add">Nuevo Cliente</a>
        </p>
        <table>
            <tr>
                <th>Id</th>
                <th>DNI</th>
                <th>Nombre del Proveedor</th>
                <th>Teléfono</th>
                <th>Dirección</th>
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
                <td><a href="ClientServlet?action=edit&id=<%= item.getId() %>">Editar</a></td>
                <td><a href="ClientServlet?action=dele&id=<%= item.getId() %>" onclick="return(confirm('¿Está seguro de querer hacerlo?'))">Eliminar</a></td>
            </tr>
             <%
                 }
            %>
        </table>
        <p><a href="index.jsp">Volver</a></p>
    </section>
</body>
</html>
