/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.bean;

import com.emergentes.entidades.Producto;
import com.emergentes.jpa.ProductoJpaController;
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
public class BeanProducto {
        private EntityManagerFactory emf;
    private ProductoJpaController jpaProducto;

    public BeanProducto() {
        emf = Persistence.createEntityManagerFactory("Inventario2077V");
        jpaProducto = new ProductoJpaController(emf);
    }
    
    public List<Producto> listarTodos(){
        return jpaProducto.findProductoEntities();
    }
    
    public void insertar (Producto c){
        jpaProducto.create(c);
    }
    
    public void editar (Producto c){
        try {
            jpaProducto.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar (Integer id){
        try {
            jpaProducto.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Producto buscar(Integer id){
        return jpaProducto.findProducto(id);
    }
}
