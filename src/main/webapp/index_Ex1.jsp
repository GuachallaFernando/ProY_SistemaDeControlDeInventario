<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Cliente"%>
<%
    List<Cliente> cliente = (List<Cliente>) request.getAttribute("cliente");
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <title>Registro de estudiantes</title>
        <style>
            .box {
                margin: auto;
                width: 50%;
                padding: 10px;
                border: 1px solid #ccc;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="box">
            <h3>PROYECTO SEMESTRAL TEM-742</h3>
            <p>Nombre: V</p>
            <p>Carnet: 2077</p>
        </div>
        <h1>Gestion de productos</h1>
        <a href="Inicio?action=add">Nuevo producto</a>
        <table border="1">
            <tr>
                <th>id</th>
                <th>dni</th>
                <th>nombre</th>
                <th>telefono</th>
                <th>direccion</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${cliente}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.dni}</td>
                    <td>${item.nombre}</td>
                    <td>${item.telefono}</td>
                    <td>${item.direccion}</td>
                    <td><a href="Inicio?action=edit&id=${item.id}">Editar</a></td>
                    <td><a href="Inicio?action=delete&id=${item.id}" onclick="return(confirm('Esta seguro de eliminar???'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>    
    </body>
</html>
