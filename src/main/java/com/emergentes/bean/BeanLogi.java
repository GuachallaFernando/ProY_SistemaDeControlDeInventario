package com.emergentes.bean;

import com.emergentes.entidades.Logi;
import com.emergentes.jpa.LogiJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanLogi {

    private EntityManagerFactory emf;
    private LogiJpaController jpaLogi;

    public BeanLogi() {
        emf = Persistence.createEntityManagerFactory("Inventario2077V");
        jpaLogi = new LogiJpaController(emf);
    }


    public Logi autenticar(String usuario, String passwrd) {
        List<Logi> usuarios = listarTodos();

        for (Logi c : usuarios) {
            if (c.getNombreUsuario().equals(usuario) && c.getPasswrd().equals(passwrd)) {
                return c; // Usuario autenticado
            }
        }

        return null; // Autenticación fallida
    }

    public List<Logi> listarTodos() {
        return jpaLogi.findLogiEntities();
    }

    public void insertar(Logi c) {
        jpaLogi.create(c);
    }

    public void editar(Logi c) {
        try {
            jpaLogi.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanLogi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) {
        try {
            jpaLogi.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanLogi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Logi buscar(Integer id) {
        return jpaLogi.findLogi(id);
    }

}
