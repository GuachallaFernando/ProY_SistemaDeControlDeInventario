package com.emergentes.controller;

import com.emergentes.bean.BeanLogi;
import com.emergentes.entidades.Logi;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LogiServlet", urlPatterns = {"/LogiServlet"})
public class LogiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;

        BeanLogi daoLogi = new BeanLogi();
        Logi c = new Logi();

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

        switch (action) {

            case "add":
                request.setAttribute("logi", c);
                request.getRequestDispatcher("logi-edit.jsp").forward(request, response);
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                c = daoLogi.buscar(id);
                request.setAttribute("logi", c);
                request.getRequestDispatcher("logi-edit.jsp").forward(request, response);
                break;

            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoLogi.eliminar(id);
                response.sendRedirect("LogiServlet");
                break;

            case "view":
                List<Logi> lista = daoLogi.listarTodos();
                request.setAttribute("logis", lista);
                request.getRequestDispatcher("logis.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanLogi daoLogi = new BeanLogi();

        String usuario = request.getParameter("usuario");
        String passwrd = request.getParameter("passwrd");

        Logi c = daoLogi.autenticar(usuario, passwrd);

        if (c != null) {
            // Usuario autenticado, crear sesión
            request.getSession().setAttribute("usuario", c);
            response.sendRedirect("Bienvenido.jsp"); // Redirigir a la página de bienvenida o a donde desees
        } else {
            // Autenticación fallida, mostrar mensaje de error o redirigir a página de inicio de sesión
            response.sendRedirect("index.jsp?error=true");
        }
    }

}
