<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Producto"%>
<%@page import="com.emergentes.entidades.Proveedo"%>
<%
    Producto productoa = (Producto) request.getAttribute("productoa");
    List<Proveedo> Proveedoe = (List<Proveedo>) request.getAttribute("Proveedoe");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar Productos</h1>
        <form action="ProductoServlet" method="post">
            <input type="hidden" name="id" value="<%= productoa.getId()%>">
            <table>
                <tr>
                    <tr>
                    <td>Codigo</td>
                    <td><input type="text" name="codigo" value="<%= productoa.getCodigo()%>"></td>
                </tr>
                <tr>
                    <td>Nombre</td>
                    <td><input type="text" name="nombre" value="<%= productoa.getNombre()%>"></td>
                </tr>
                    <td>Id Proveedor</td>
                    <td>
                        <select name="proveedoa_id">
                            <% for (Proveedo item : Proveedoe) {%>
                            <option value="<%= item.getId()%>" <%=(productoa.getProveedor()!= null && item.getId() == productoa.getProveedor().getId()) ? "selected" : ""%>>
                                <%= item.getId()%>
                            </option>
                            <% }%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Proveedor Pro</td>
                    <td><input type="text" name="proveedorPro" value="<%= productoa.getProveedorPro()%>"></td>
                </tr>
                <tr>
                    <td>Cantida en Stock</td>
                    <td><input type="text" name="CantidadStock" value="<%= productoa.getCantidadStock()%>"></td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td><input type="text" name="precio" value="<%= productoa.getPrecio()%>"></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>

