<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Vent"%>
<%@page import="com.emergentes.entidades.Client"%>
<%@page import="com.emergentes.entidades.Vendedo"%>
<%
    Vent venta = (Vent) request.getAttribute("venta");
    List<Client> client = (List<Client>) request.getAttribute("Cliente");
    List<Vendedo> vendedo = (List<Vendedo>) request.getAttribute("Vendedor");


%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Editar Ventas</h1>
        <form action="VentServlet" method="post">
            <input type="hidden" name="id" value="<%= venta.getId()%>">
            <table>
                <tr>
                    <td>Cliente DNI</td>
                    <td>
                        <select name="cliente_id">
                            <% for (Client item : client) {%>
                            <option value="<%= item.getId()%>" <%=(venta.getCliente() != null && item.getId() == venta.getCliente().getId()) ? "selected" : ""%>>
                                <%= item.getId()%>
                            </option>
                            <% }%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Nombre Cliente</td>
                    <td><input type="text" name="nombre" value="<%= venta.getNombreCli()%>"></td>
                </tr>
                <tr>
                    <td>Vendedor</td>
                    <td>
                        <select name="vendedor_id">
                            <% for (Vendedo item : vendedo) {%>
                            <option value="<%= item.getId()%>" <%=(venta.getVendedor() != null && item.getId() == venta.getVendedor().getId()) ? "selected" : ""%>>
                                <%= item.getId()%>
                            </option>
                            <% }%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td><input type="text" name="fecha" value="<%= venta.getFecha()%>"></td>
                </tr>
                <tr>
                    <td>Total</td>
                    <td><input type="text" name="total" value="<%= venta.getTotal()%>"></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>

