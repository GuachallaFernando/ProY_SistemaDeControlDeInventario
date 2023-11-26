package com.emergentes.controlador;

import com.emergentes.dao.ClienteDao;
////import com.emergentes.dao.AlmacenDAOimpl;
import com.emergentes.modelo.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id;
            Cliente alm = new Cliente();
            ClienteDao dao = new ClienteDao();
            
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch (action) {
                case "add":
                    request.setAttribute("Cliente", alm);
                    request.getRequestDispatcher("frmalmacen.jsp").forward(request, response);
                    break;
                //case "edit":
                  //  id = Integer.parseInt(request.getParameter("id"));
                    //alm = dao.ModificarCliente(id);
                    
                    //request.setAttribute("Cliente", alm);
                    //request.getRequestDispatcher("frmalmacen.jsp").forward(request, response);
                    //break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.EliminarCliente(id);
                    response.sendRedirect("Inicio");
                    break;
                case "view":
                    List<Cliente> lista = dao.ListarCliente();
                    request.setAttribute("Cliente", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClienteDao dao = new ClienteDao();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        
        Cliente alm = new Cliente();
        alm.setId(id);
        alm.setDni(dni);
        alm.setNombre(nombre);
        alm.setDireccion(direccion);
        alm.setTelefono(telefono);
        try {
            if (id == 0) {
                //Adicionar reg
                dao.RegistrarCliente(alm);
            } else {
                //Actualizar reg
                dao.ModificarCliente(alm);
            }
        } catch (Exception ex) {
            System.out.println("Error al guardar datos...");
        }
        
        response.sendRedirect("Inicio");
      }    
}