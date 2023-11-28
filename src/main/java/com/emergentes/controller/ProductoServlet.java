package com.emergentes.controller;

import com.emergentes.bean.BeanProducto;
import com.emergentes.bean.BeanProveedo;

import com.emergentes.entidades.Producto;
import com.emergentes.entidades.Proveedo;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virtual_7
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;

        BeanProducto daoProducto = new BeanProducto();
        BeanProveedo daoProveedo = new BeanProveedo();


        Producto productoas = new Producto();
        List<Proveedo> lista;
        List<Producto> listaV;


        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

        switch (action) {

            case "add":
                lista = daoProveedo.listarTodos();
                request.setAttribute("Proveedoe", lista);



                // Configura el objeto "productoa" pero también configura los atributos "client" y "vendedo"
                request.setAttribute("productoa", productoas);
                request.setAttribute("client", lista); // Asegúrate de tener el atributo "client" configurado


                request.getRequestDispatcher("producto-edit.jsp").forward(request, response);
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                productoas = daoProducto.buscar(id);

                lista = daoProveedo.listarTodos();
                request.setAttribute("Proveedoe", lista);


                request.setAttribute("productoa", productoas);
                request.getRequestDispatcher("producto-edit.jsp").forward(request, response);
                break;

            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoProducto.eliminar(id);
                response.sendRedirect("ProductoServlet");
                break;

            case "view":
                List<Producto> productoa = daoProducto.listarTodos();
                request.setAttribute("productoas", productoa);
                request.getRequestDispatcher("producto.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BeanProducto daoProducto = new BeanProducto();
        BeanProveedo daoProveedo = new BeanProveedo();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String codigo = request.getParameter("codigo");
        String nombre = request.getParameter("nombre");
        String proveedorPro = request.getParameter("proveedorPro");
        int CantidadStock = Integer.parseInt(request.getParameter("CantidadStock"));
        

        String totalParameter = request.getParameter("precio");
        BigDecimal precio = BigDecimal.ZERO;  // O cualquier otro valor predeterminado

        if (totalParameter != null && !totalParameter.isEmpty()) {
            try {
                precio = new BigDecimal(totalParameter);
            } catch (NumberFormatException | ArithmeticException e) {
                // Manejar la excepción si la conversión no es posible
                e.printStackTrace(); // O realiza un manejo más adecuado según tus necesidades
            }
        }

        int proveedo_id = Integer.parseInt(request.getParameter("proveedoa_id"));

        Proveedo ven = daoProveedo.buscar(proveedo_id);

        ////Producto det = daoProducto.buscar(productoe_id);

        Producto productos = new Producto();
        
        productos.setId(id);
        productos.setCodigo(codigo);
        productos.setNombre(nombre);
        productos.setProveedor(ven);
        productos.setProveedorPro(proveedorPro);
        productos.setCantidadStock(CantidadStock);
        productos.setPrecio(precio);



        if (id > 0) {
            daoProducto.editar(productos);
        } else {
            daoProducto.insertar(productos);
        }
        response.sendRedirect("ProductoServlet");

    }
}