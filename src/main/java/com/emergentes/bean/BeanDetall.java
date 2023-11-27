/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.bean;

import com.emergentes.entidades.Detall;
import com.emergentes.jpa.DetallJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Virtual_7
 */
public class BeanDetall {
        private EntityManagerFactory emf;
    private DetallJpaController jpaDetall;

    public BeanDetall() {
        emf = Persistence.createEntityManagerFactory("Inventario2077V");
        jpaDetall = new DetallJpaController(emf);
    }
    
    public List<Detall> listarTodos(){
        return jpaDetall.findDetallEntities();
    }
    
    public void insertar (Detall c){
        jpaDetall.create(c);
    }
    
    public void editar (Detall c){
        try {
            jpaDetall.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanDetall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar (Integer id){
        try {
            jpaDetall.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanDetall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Detall buscar(Integer id){
        return jpaDetall.findDetall(id);
    }
    
}
