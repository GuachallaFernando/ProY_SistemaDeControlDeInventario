<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Detall"%>
<%@page import="com.emergentes.entidades.Vent"%>
<%@page import="com.emergentes.entidades.Producto"%>
<%
    Detall detalla = (Detall) request.getAttribute("detalla");
    List<Vent> client = (List<Vent>) request.getAttribute("Vente");
    List<Producto> vendedo = (List<Producto>) request.getAttribute("Productor");


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar Detalles de Venta</h1>
        <form action="DetallServlet" method="post">
            <input type="hidden" name="id" value="<%= detalla.getId()%>">
            <table>
                <tr>
                    <td>Id Venta</td>
                    <td>
                        <select name="venta_id">
                            <% for (Vent item : client) {%>
                            <option value="<%= item.getId()%>" <%=(detalla.getIdVenta()!= null && item.getId() == detalla.getIdVenta().getId()) ? "selected" : ""%>>
                                <%= item.getId()%>
                            </option>
                            <% }%>
                        </select>
                    </td>
                </tr>
                                <tr>
                    <td>ID Producto</td>
                    <td>
                        <select name="producto_id">
                            <% for (Producto item : vendedo) {%>
                            <option value="<%= item.getId()%>" <%=(detalla.getIdPro() != null && item.getId() == detalla.getIdPro().getId()) ? "selected" : ""%>>
                                <%= item.getId()%>
                            </option>
                            <% }%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Cantidad</td>
                    <td><input type="text" name="cantidad" value="<%= detalla.getCantidad()%>"></td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td><input type="text" name="total" value="<%= detalla.getPrecio()%>"></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>

