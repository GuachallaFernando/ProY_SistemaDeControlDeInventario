/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.jpa;

import com.emergentes.entidades.Client;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.Vent;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Virtual_7
 */
public class ClientJpaController implements Serializable {

    public ClientJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Client client) {
        if (client.getVentList() == null) {
            client.setVentList(new ArrayList<Vent>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vent> attachedVentList = new ArrayList<Vent>();
            for (Vent ventListVentToAttach : client.getVentList()) {
                ventListVentToAttach = em.getReference(ventListVentToAttach.getClass(), ventListVentToAttach.getId());
                attachedVentList.add(ventListVentToAttach);
            }
            client.setVentList(attachedVentList);
            em.persist(client);
            for (Vent ventListVent : client.getVentList()) {
                Client oldClienteOfVentListVent = ventListVent.getCliente();
                ventListVent.setCliente(client);
                ventListVent = em.merge(ventListVent);
                if (oldClienteOfVentListVent != null) {
                    oldClienteOfVentListVent.getVentList().remove(ventListVent);
                    oldClienteOfVentListVent = em.merge(oldClienteOfVentListVent);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Client client) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client persistentClient = em.find(Client.class, client.getId());
            List<Vent> ventListOld = persistentClient.getVentList();
            List<Vent> ventListNew = client.getVentList();
            List<Vent> attachedVentListNew = new ArrayList<Vent>();
            for (Vent ventListNewVentToAttach : ventListNew) {
                ventListNewVentToAttach = em.getReference(ventListNewVentToAttach.getClass(), ventListNewVentToAttach.getId());
                attachedVentListNew.add(ventListNewVentToAttach);
            }
            ventListNew = attachedVentListNew;
            client.setVentList(ventListNew);
            client = em.merge(client);
            for (Vent ventListOldVent : ventListOld) {
                if (!ventListNew.contains(ventListOldVent)) {
                    ventListOldVent.setCliente(null);
                    ventListOldVent = em.merge(ventListOldVent);
                }
            }
            for (Vent ventListNewVent : ventListNew) {
                if (!ventListOld.contains(ventListNewVent)) {
                    Client oldClienteOfVentListNewVent = ventListNewVent.getCliente();
                    ventListNewVent.setCliente(client);
                    ventListNewVent = em.merge(ventListNewVent);
                    if (oldClienteOfVentListNewVent != null && !oldClienteOfVentListNewVent.equals(client)) {
                        oldClienteOfVentListNewVent.getVentList().remove(ventListNewVent);
                        oldClienteOfVentListNewVent = em.merge(oldClienteOfVentListNewVent);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = client.getId();
                if (findClient(id) == null) {
                    throw new NonexistentEntityException("The client with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client client;
            try {
                client = em.getReference(Client.class, id);
                client.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The client with id " + id + " no longer exists.", enfe);
            }
            List<Vent> ventList = client.getVentList();
            for (Vent ventListVent : ventList) {
                ventListVent.setCliente(null);
                ventListVent = em.merge(ventListVent);
            }
            em.remove(client);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Client> findClientEntities() {
        return findClientEntities(true, -1, -1);
    }

    public List<Client> findClientEntities(int maxResults, int firstResult) {
        return findClientEntities(false, maxResults, firstResult);
    }

    private List<Client> findClientEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Client.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Client findClient(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Client.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(Client.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
