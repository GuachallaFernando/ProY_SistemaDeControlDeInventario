/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.jpa;

import com.emergentes.entidades.Vendedo;
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
public class VendedoJpaController implements Serializable {

    public VendedoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vendedo vendedo) {
        if (vendedo.getVentList() == null) {
            vendedo.setVentList(new ArrayList<Vent>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Vent> attachedVentList = new ArrayList<Vent>();
            for (Vent ventListVentToAttach : vendedo.getVentList()) {
                ventListVentToAttach = em.getReference(ventListVentToAttach.getClass(), ventListVentToAttach.getId());
                attachedVentList.add(ventListVentToAttach);
            }
            vendedo.setVentList(attachedVentList);
            em.persist(vendedo);
            for (Vent ventListVent : vendedo.getVentList()) {
                Vendedo oldVendedorOfVentListVent = ventListVent.getVendedor();
                ventListVent.setVendedor(vendedo);
                ventListVent = em.merge(ventListVent);
                if (oldVendedorOfVentListVent != null) {
                    oldVendedorOfVentListVent.getVentList().remove(ventListVent);
                    oldVendedorOfVentListVent = em.merge(oldVendedorOfVentListVent);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vendedo vendedo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendedo persistentVendedo = em.find(Vendedo.class, vendedo.getId());
            List<Vent> ventListOld = persistentVendedo.getVentList();
            List<Vent> ventListNew = vendedo.getVentList();
            List<Vent> attachedVentListNew = new ArrayList<Vent>();
            for (Vent ventListNewVentToAttach : ventListNew) {
                ventListNewVentToAttach = em.getReference(ventListNewVentToAttach.getClass(), ventListNewVentToAttach.getId());
                attachedVentListNew.add(ventListNewVentToAttach);
            }
            ventListNew = attachedVentListNew;
            vendedo.setVentList(ventListNew);
            vendedo = em.merge(vendedo);
            for (Vent ventListOldVent : ventListOld) {
                if (!ventListNew.contains(ventListOldVent)) {
                    ventListOldVent.setVendedor(null);
                    ventListOldVent = em.merge(ventListOldVent);
                }
            }
            for (Vent ventListNewVent : ventListNew) {
                if (!ventListOld.contains(ventListNewVent)) {
                    Vendedo oldVendedorOfVentListNewVent = ventListNewVent.getVendedor();
                    ventListNewVent.setVendedor(vendedo);
                    ventListNewVent = em.merge(ventListNewVent);
                    if (oldVendedorOfVentListNewVent != null && !oldVendedorOfVentListNewVent.equals(vendedo)) {
                        oldVendedorOfVentListNewVent.getVentList().remove(ventListNewVent);
                        oldVendedorOfVentListNewVent = em.merge(oldVendedorOfVentListNewVent);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vendedo.getId();
                if (findVendedo(id) == null) {
                    throw new NonexistentEntityException("The vendedo with id " + id + " no longer exists.");
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
            Vendedo vendedo;
            try {
                vendedo = em.getReference(Vendedo.class, id);
                vendedo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendedo with id " + id + " no longer exists.", enfe);
            }
            List<Vent> ventList = vendedo.getVentList();
            for (Vent ventListVent : ventList) {
                ventListVent.setVendedor(null);
                ventListVent = em.merge(ventListVent);
            }
            em.remove(vendedo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vendedo> findVendedoEntities() {
        return findVendedoEntities(true, -1, -1);
    }

    public List<Vendedo> findVendedoEntities(int maxResults, int firstResult) {
        return findVendedoEntities(false, maxResults, firstResult);
    }

    private List<Vendedo> findVendedoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vendedo.class));
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

    public Vendedo findVendedo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vendedo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendedoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vendedo> rt = cq.from(Vendedo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
