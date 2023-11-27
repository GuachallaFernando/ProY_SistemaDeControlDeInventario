package com.emergentes.controller;

import com.emergentes.bean.BeanClient;
import com.emergentes.bean.BeanDetall;
import com.emergentes.bean.BeanVendedo;
import com.emergentes.bean.BeanVent;
import com.emergentes.entidades.Client;
import com.emergentes.entidades.Detall;
import com.emergentes.entidades.Vendedo;
import com.emergentes.entidades.Vent;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "VentServlet", urlPatterns = {"/VentServlet"})
public class VentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;

        BeanVent daoVent = new BeanVent();
        BeanClient daoClient = new BeanClient();
        BeanVendedo daoVendedo = new BeanVendedo();
        BeanDetall daoDetall = new BeanDetall();
        

        Vent venta = new Vent();
        List<Client> lista;
        List<Vendedo> listaV;
        List<Detall> listaD;

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

        switch (action) {

            case "add":
                lista = daoClient.listarTodos();
                request.setAttribute("Cliente", lista);
                listaV = daoVendedo.listarTodos();
                request.setAttribute("Vendedor", listaV);
                listaD = daoDetall.listarTodos();
                request.setAttribute("categorias", listaD);
                request.setAttribute("venta", venta);
                request.getRequestDispatcher("venta-edit.jsp").forward(request, response);
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                venta = daoVent.buscar(id);

                lista = daoClient.listarTodos();
                request.setAttribute("Cliente", lista);
                listaV = daoVendedo.listarTodos();
                request.setAttribute("Vendedor", listaV);
                listaD = daoDetall.listarTodos();
                request.setAttribute("categorias", listaD);

                request.setAttribute("venta", venta);
                request.getRequestDispatcher("venta-edit.jsp").forward(request, response);
                break;

            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoVent.eliminar(id);
                response.sendRedirect("VentServlet");
                break;

            case "view":
                List<Vent> ventas = daoVent.listarTodos();
                request.setAttribute("ventas", ventas);
                request.getRequestDispatcher("ventas.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BeanVent daoVent = new BeanVent();
        BeanClient daoClient = new BeanClient();
        BeanVendedo daoVendedo = new BeanVendedo();
        ///BeanDetall daoDetall = new BeanDetall();

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        double total = Double.parseDouble(request.getParameter("total"));
        int cliente_id = Integer.parseInt(request.getParameter("cliente_id"));
        int vendedor_id = Integer.parseInt(request.getParameter("vendedor_id"));
        ////int detalle_id = Integer.parseInt(request.getParameter("detalle_id"));

        Client cli = daoClient.buscar(cliente_id);
        Vendedo ven = daoVendedo.buscar(vendedor_id);
        ////Detall det = daoDetall.buscar(detalle_id);

        Vent venta = new Vent();
        venta.setId(id);
        venta.setNombreCli(nombre);
        venta.setFecha(fecha);
        venta.setTotal(total);
        venta.setCliente(cli);
        venta.setVendedor(ven);
        ////venta.setCategoriaId(cate);

        if (id > 0) {
            daoVent.editar(venta);
        } else {
            daoVent.insertar(venta);
        }
        response.sendRedirect("VentServlet");

    }
}


