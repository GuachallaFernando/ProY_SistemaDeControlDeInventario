/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.bean;

import com.emergentes.entidades.Vendedo;
import com.emergentes.jpa.VendedoJpaController;
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
public class BeanVendedo {
        private EntityManagerFactory emf;
    private VendedoJpaController jpaVendedo;

    public BeanVendedo() {
        emf = Persistence.createEntityManagerFactory("Inventario2077V");
        jpaVendedo = new VendedoJpaController(emf);
    }
    
    public List<Vendedo> listarTodos(){
        return jpaVendedo.findVendedoEntities();
    }
    
    public void insertar (Vendedo c){
        jpaVendedo.create(c);
    }
    
    public void editar (Vendedo c){
        try {
            jpaVendedo.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanVendedo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar (Integer id){
        try {
            jpaVendedo.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanVendedo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vendedo buscar(Integer id){
        return jpaVendedo.findVendedo(id);
    }
}
