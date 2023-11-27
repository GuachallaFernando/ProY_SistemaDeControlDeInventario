/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.jpa;

import com.emergentes.entidades.Detall;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.Producto;
import com.emergentes.entidades.Vent;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Virtual_7
 */
public class DetallJpaController implements Serializable {

    public DetallJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detall detall) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idPro = detall.getIdPro();
            if (idPro != null) {
                idPro = em.getReference(idPro.getClass(), idPro.getId());
                detall.setIdPro(idPro);
            }
            Vent idVenta = detall.getIdVenta();
            if (idVenta != null) {
                idVenta = em.getReference(idVenta.getClass(), idVenta.getId());
                detall.setIdVenta(idVenta);
            }
            em.persist(detall);
            if (idPro != null) {
                idPro.getDetallList().add(detall);
                idPro = em.merge(idPro);
            }
            if (idVenta != null) {
                idVenta.getDetallList().add(detall);
                idVenta = em.merge(idVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detall detall) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detall persistentDetall = em.find(Detall.class, detall.getId());
            Producto idProOld = persistentDetall.getIdPro();
            Producto idProNew = detall.getIdPro();
            Vent idVentaOld = persistentDetall.getIdVenta();
            Vent idVentaNew = detall.getIdVenta();
            if (idProNew != null) {
                idProNew = em.getReference(idProNew.getClass(), idProNew.getId());
                detall.setIdPro(idProNew);
            }
            if (idVentaNew != null) {
                idVentaNew = em.getReference(idVentaNew.getClass(), idVentaNew.getId());
                detall.setIdVenta(idVentaNew);
            }
            detall = em.merge(detall);
            if (idProOld != null && !idProOld.equals(idProNew)) {
                idProOld.getDetallList().remove(detall);
                idProOld = em.merge(idProOld);
            }
            if (idProNew != null && !idProNew.equals(idProOld)) {
                idProNew.getDetallList().add(detall);
                idProNew = em.merge(idProNew);
            }
            if (idVentaOld != null && !idVentaOld.equals(idVentaNew)) {
                idVentaOld.getDetallList().remove(detall);
                idVentaOld = em.merge(idVentaOld);
            }
            if (idVentaNew != null && !idVentaNew.equals(idVentaOld)) {
                idVentaNew.getDetallList().add(detall);
                idVentaNew = em.merge(idVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detall.getId();
                if (findDetall(id) == null) {
                    throw new NonexistentEntityException("The detall with id " + id + " no longer exists.");
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
            Detall detall;
            try {
                detall = em.getReference(Detall.class, id);
                detall.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detall with id " + id + " no longer exists.", enfe);
            }
            Producto idPro = detall.getIdPro();
            if (idPro != null) {
                idPro.getDetallList().remove(detall);
                idPro = em.merge(idPro);
            }
            Vent idVenta = detall.getIdVenta();
            if (idVenta != null) {
                idVenta.getDetallList().remove(detall);
                idVenta = em.merge(idVenta);
            }
            em.remove(detall);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detall> findDetallEntities() {
        return findDetallEntities(true, -1, -1);
    }

    public List<Detall> findDetallEntities(int maxResults, int firstResult) {
        return findDetallEntities(false, maxResults, firstResult);
    }

    private List<Detall> findDetallEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detall.class));
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

    public Detall findDetall(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detall.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detall> rt = cq.from(Detall.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
