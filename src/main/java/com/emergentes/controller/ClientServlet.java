
package com.emergentes.controller;

import com.emergentes.bean.BeanClient;
import com.emergentes.entidades.Client;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClientServlet", urlPatterns = {"/ClientServlet"})
public class ClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;

        BeanClient daoClient = new BeanClient();
        Client c = new Client();

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

        switch (action) {

            case "add":
                request.setAttribute("client", c);
                request.getRequestDispatcher("client-edit.jsp").forward(request, response);
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                c = daoClient.buscar(id);
                request.setAttribute("client", c);
                request.getRequestDispatcher("client-edit.jsp").forward(request, response);
                break;

            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoClient.eliminar(id);
                response.sendRedirect("ClientServlet");
                break;

            case "view":
                List<Client> lista = daoClient.listarTodos();
                request.setAttribute("clients", lista);
                request.getRequestDispatcher("clients.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanClient daoClient = new BeanClient();

        int id = Integer.parseInt(request.getParameter("id"));
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        Client c = new Client();
        c.setId(id);
        c.setDni(dni);
        c.setNombre(nombre);
        c.setTelefono(telefono);
        c.setDireccion(direccion);

        if (id > 0) {
            daoClient.editar(c);
        } else {
            daoClient.insertar(c);
        }
        response.sendRedirect("ClientServlet");

    }

}
