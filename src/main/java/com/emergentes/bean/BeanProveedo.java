package com.emergentes.bean;

import com.emergentes.entidades.Proveedo;
import com.emergentes.jpa.ProveedoJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanProveedo {
    private EntityManagerFactory emf;
    private ProveedoJpaController jpaProveedo;

    public BeanProveedo() {
        emf = Persistence.createEntityManagerFactory("Inventario2077V");
        jpaProveedo = new ProveedoJpaController(emf);
    }
    
    public List<Proveedo> listarTodos(){
        return jpaProveedo.findProveedoEntities();
    }
    
    public void insertar (Proveedo c){
        jpaProveedo.create(c);
    }
    
    public void editar (Proveedo c){
        try {
            jpaProveedo.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanProveedo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar (Integer id){
        try {
            jpaProveedo.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanProveedo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Proveedo buscar(Integer id){
        return jpaProveedo.findProveedo(id);
    }
    
}
