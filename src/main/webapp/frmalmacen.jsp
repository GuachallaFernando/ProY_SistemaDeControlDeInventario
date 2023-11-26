<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${almacen.id==0}">
                Nuevo registro
            </c:if> 
            <c:if test="${almacen.id!=0}">
                Editar registro
            </c:if> 
        </h1>
        <form action="Inicio" method="POST">
            <input type="hidden" name="id" value="${almacen.id}"/>
            <table>
                <tr>
                    <td>Descripcion:</td>
                    <td><input type="text" name="descripcion" value="${almacen.descripcion}" /></td>
                </tr>
                <tr>
                    <td>Cantidad:</td>
                    <td><input type="number" name="cantidad" value="${almacen.cantidad}" /></td>
                </tr>
                <tr>
                    <td>Precio:</td>
                    <td><input type="number" name="precio" value="${almacen.precio}" /></td>
                </tr>
                <tr>
                    <td>Categoria:</td>
                    <td><input type="text" name="categoria" value="${almacen.categoria}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>