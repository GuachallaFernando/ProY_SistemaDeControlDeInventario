
package com.emergentes.bean;

import com.emergentes.entidades.Client;
import com.emergentes.jpa.ClientJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanClient {
        private EntityManagerFactory emf;
    private ClientJpaController jpaClient;

    public BeanClient() {
        emf = Persistence.createEntityManagerFactory("Inventario2077V");
        jpaClient = new ClientJpaController(emf);
    }
    
    public List<Client> listarTodos(){
        return jpaClient.findClientEntities();
    }
    
    public void insertar (Client c){
        jpaClient.create(c);
    }
    
    public void editar (Client c){
        try {
            jpaClient.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminar (Integer id){
        try {
            jpaClient.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Client buscar(Integer id){
        return jpaClient.findClient(id);
    }
    
}
