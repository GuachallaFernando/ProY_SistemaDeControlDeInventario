
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
        BeanProveedo daoProveedo = new BeanProveedo();
        List<Proveedo> lista =daoProveedo.listarTodos();
        
        for(Proveedo c : lista){
            System.out.println("id:  " + c.getId());
            System.out.println("ruc:  " + c.getRuc());
            System.out.println("nombre:  " + c.getNombre());
            System.out.println("telefono:  " + c.getTelefono());
            System.out.println("direccion:  " + c.getDireccion());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
