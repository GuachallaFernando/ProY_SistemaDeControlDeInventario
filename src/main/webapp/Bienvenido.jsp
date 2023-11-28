<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Almacen</title>
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

            nav {
                background-color: #555;
                padding: 10px;
            }

            nav ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
            }

            nav ul li {
                display: inline;
                margin-right: 15px;
            }

            nav ul li a {
                text-decoration: none;
                color: white;
                font-weight: bold;
            }

            nav ul li a:hover {
                color: #ddd;
            }
        </style>
    </head>
    <body>
        <header>
            <h1>Almacen</h1>
        </header>

        <nav>
            <ul>
                <li><a href="VentServlet">Ventas</a></li>
                <li><a href="ProveedoServlet">Proveedor</a></li>
                <li><a href="ClientServlet">Clientes</a></li>
                <li><a href="DetallServlet">Detalles</a></li>
                <li><a href="VendedoServlet">Vendedor</a></li>
                <li><a href="ProductoServlet">Producto</a></li>
                <li><a href="Bienvenido.jsp">Volver</a></li>
            </ul>
        </nav>

        <!-- Contenido adicional si es necesario -->
        <h1>GRUPO BY ABEL FERNANDO GUACHALLA FERNANDEZ AND </h1>
    </body>
</html>
