package com.emergentes.controller;

import com.emergentes.bean.BeanVent;
import com.emergentes.bean.BeanDetall;
import com.emergentes.bean.BeanProducto;
import com.emergentes.bean.BeanDetall;
import com.emergentes.entidades.Vent;
import com.emergentes.entidades.Detall;
import com.emergentes.entidades.Producto;
import com.emergentes.entidades.Detall;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetallServlet", urlPatterns = {"/DetallServlet"})
public class DetallServlet extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;

        BeanDetall daoDetall = new BeanDetall();
        BeanVent daoVent = new BeanVent();
        BeanProducto daoProducto = new BeanProducto();

        Detall detallas = new Detall();
        List<Vent> lista;
        List<Producto> listaV;


        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

        switch (action) {

            case "add":
                lista = daoVent.listarTodos();
                request.setAttribute("Vente", lista);

                listaV = daoProducto.listarTodos();
                request.setAttribute("Productor", listaV);

                // Configura el objeto "detalla" pero también configura los atributos "client" y "vendedo"
                request.setAttribute("detalla", detallas);
                request.setAttribute("client", lista); // Asegúrate de tener el atributo "client" configurado
                request.setAttribute("vendedo", listaV); // Asegúrate de tener el atributo "vendedo" configurado

                request.getRequestDispatcher("detall-edit.jsp").forward(request, response);
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                detallas = daoDetall.buscar(id);

                lista = daoVent.listarTodos();
                request.setAttribute("Vente", lista);
                listaV = daoProducto.listarTodos();
                request.setAttribute("Productor", listaV);

                request.setAttribute("detalla", detallas);
                request.getRequestDispatcher("detall-edit.jsp").forward(request, response);
                break;

            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoDetall.eliminar(id);
                response.sendRedirect("DetallServlet");
                break;

            case "view":
                List<Detall> detalla = daoDetall.listarTodos();
                request.setAttribute("detallas", detalla);
                request.getRequestDispatcher("detalls.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BeanDetall daoDetall = new BeanDetall();
        BeanVent daoVent = new BeanVent();
        BeanProducto daoProducto = new BeanProducto();
        ///BeanDetall daoDetall = new BeanDetall();

        int id = Integer.parseInt(request.getParameter("id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        String totalParameter = request.getParameter("total");
        BigDecimal total = BigDecimal.ZERO;  // O cualquier otro valor predeterminado

        if (totalParameter != null && !totalParameter.isEmpty()) {
            try {
                total = new BigDecimal(totalParameter);
            } catch (NumberFormatException | ArithmeticException e) {
                // Manejar la excepción si la conversión no es posible
                e.printStackTrace(); // O realiza un manejo más adecuado según tus necesidades
            }
        }

        int venta_id = Integer.parseInt(request.getParameter("venta_id"));
        int producto_id = Integer.parseInt(request.getParameter("producto_id"));

        Vent ven = daoVent.buscar(venta_id);
        Producto pro = daoProducto.buscar(producto_id);
        ////Detall det = daoDetall.buscar(detalle_id);

        Detall detalla = new Detall();
        
        detalla.setId(id);
        detalla.setIdVenta(ven);
        detalla.setIdPro(pro);
        detalla.setCantidad(cantidad);
        detalla.setPrecio(total);



        if (id > 0) {
            daoDetall.editar(detalla);
        } else {
            daoDetall.insertar(detalla);
        }
        response.sendRedirect("DetallServlet");

    }
}
