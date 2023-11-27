
package com.emergentes.bean;

import com.emergentes.entidades.Vent;
import com.emergentes.jpa.VentJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class BeanVent {
    
      private EntityManagerFactory emf;
    private VentJpaController jpaVent;

    public BeanVent() {
        emf = Persistence.createEntityManagerFactory("Inventario2077V");
        jpaVent = new VentJpaController(emf);
    }
    
    public List<Vent> listarTodos(){
        return jpaVent.findVentEntities();
    }
    
    public void insertar (Vent c){
        jpaVent.create(c);
    }
    
    public void editar (Vent c){
        try {
            jpaVent.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanVent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar (Integer id){
        try {
            jpaVent.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanVent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vent buscar(Integer id){
        return jpaVent.findVent(id);
    }
    
}
