
package com.emergentes.controller;

import com.emergentes.bean.BeanVendedo;
import com.emergentes.entidades.Vendedo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Virtual_7
 */
@WebServlet(name = "VendedoServlet", urlPatterns = {"/VendedoServlet"})
public class VendedoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;

        BeanVendedo daoVendedo = new BeanVendedo();
        Vendedo c = new Vendedo();

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

        switch (action) {

            case "add":
                request.setAttribute("vendedo", c);
                request.getRequestDispatcher("vendedo-edit.jsp").forward(request, response);
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                c = daoVendedo.buscar(id);
                request.setAttribute("vendedo", c);
                request.getRequestDispatcher("vendedo-edit.jsp").forward(request, response);
                break;

            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoVendedo.eliminar(id);
                response.sendRedirect("VendedoServlet");
                break;

            case "view":
                List<Vendedo> lista = daoVendedo.listarTodos();
                request.setAttribute("vendedos", lista);
                request.getRequestDispatcher("vendeds.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanVendedo daoVendedo = new BeanVendedo();

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String ci = request.getParameter("ci");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        Vendedo c = new Vendedo();
        c.setId(id);
        c.setNombre(nombre);
        c.setCi(ci);        
        c.setTelefono(telefono);
        c.setDireccion(direccion);

        if (id > 0) {
            daoVendedo.editar(c);
        } else {
            daoVendedo.insertar(c);
        }
        response.sendRedirect("VendedoServlet");

    }

}

