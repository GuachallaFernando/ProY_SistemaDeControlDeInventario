package com.emergentes.controller;

import com.emergentes.bean.BeanProveedo;
import com.emergentes.entidades.Proveedo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProveedoServlet", urlPatterns = {"/ProveedoServlet"})
public class ProveedoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;

        BeanProveedo daoProveedo = new BeanProveedo();
        Proveedo c = new Proveedo();

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

        switch (action) {

            case "add":
                request.setAttribute("proveedo", c);
                request.getRequestDispatcher("proveedo-edit.jsp").forward(request, response);
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                c = daoProveedo.buscar(id);
                request.setAttribute("proveedo", c);
                request.getRequestDispatcher("proveedo-edit.jsp").forward(request, response);
                break;

            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoProveedo.eliminar(id);
                response.sendRedirect("ProveedoServlet");
                break;

            case "view":
                List<Proveedo> lista = daoProveedo.listarTodos();
                request.setAttribute("proveedos", lista);
                request.getRequestDispatcher("proveedos.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanProveedo daoProveedo = new BeanProveedo();

        int id = Integer.parseInt(request.getParameter("id"));
        String ruc = request.getParameter("ruc");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        Proveedo c = new Proveedo();
        c.setId(id);
        c.setRuc(ruc);
        c.setNombre(nombre);
        c.setTelefono(telefono);
        c.setDireccion(direccion);

        if (id > 0) {
            daoProveedo.editar(c);
        } else {
            daoProveedo.insertar(c);
        }
        response.sendRedirect("ProveedoServlet");

    }
}


