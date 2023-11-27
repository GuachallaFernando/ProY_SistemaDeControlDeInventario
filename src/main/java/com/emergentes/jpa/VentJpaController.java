/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.Client;
import com.emergentes.entidades.Vendedo;
import com.emergentes.entidades.Detall;
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
public class VentJpaController implements Serializable {

    public VentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vent vent) {
        if (vent.getDetallList() == null) {
            vent.setDetallList(new ArrayList<Detall>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client cliente = vent.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getId());
                vent.setCliente(cliente);
            }
            Vendedo vendedor = vent.getVendedor();
            if (vendedor != null) {
                vendedor = em.getReference(vendedor.getClass(), vendedor.getId());
                vent.setVendedor(vendedor);
            }
            List<Detall> attachedDetallList = new ArrayList<Detall>();
            for (Detall detallListDetallToAttach : vent.getDetallList()) {
                detallListDetallToAttach = em.getReference(detallListDetallToAttach.getClass(), detallListDetallToAttach.getId());
                attachedDetallList.add(detallListDetallToAttach);
            }
            vent.setDetallList(attachedDetallList);
            em.persist(vent);
            if (cliente != null) {
                cliente.getVentList().add(vent);
                cliente = em.merge(cliente);
            }
            if (vendedor != null) {
                vendedor.getVentList().add(vent);
                vendedor = em.merge(vendedor);
            }
            for (Detall detallListDetall : vent.getDetallList()) {
                Vent oldIdVentaOfDetallListDetall = detallListDetall.getIdVenta();
                detallListDetall.setIdVenta(vent);
                detallListDetall = em.merge(detallListDetall);
                if (oldIdVentaOfDetallListDetall != null) {
                    oldIdVentaOfDetallListDetall.getDetallList().remove(detallListDetall);
                    oldIdVentaOfDetallListDetall = em.merge(oldIdVentaOfDetallListDetall);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vent vent) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vent persistentVent = em.find(Vent.class, vent.getId());
            Client clienteOld = persistentVent.getCliente();
            Client clienteNew = vent.getCliente();
            Vendedo vendedorOld = persistentVent.getVendedor();
            Vendedo vendedorNew = vent.getVendedor();
            List<Detall> detallListOld = persistentVent.getDetallList();
            List<Detall> detallListNew = vent.getDetallList();
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getId());
                vent.setCliente(clienteNew);
            }
            if (vendedorNew != null) {
                vendedorNew = em.getReference(vendedorNew.getClass(), vendedorNew.getId());
                vent.setVendedor(vendedorNew);
            }
            List<Detall> attachedDetallListNew = new ArrayList<Detall>();
            for (Detall detallListNewDetallToAttach : detallListNew) {
                detallListNewDetallToAttach = em.getReference(detallListNewDetallToAttach.getClass(), detallListNewDetallToAttach.getId());
                attachedDetallListNew.add(detallListNewDetallToAttach);
            }
            detallListNew = attachedDetallListNew;
            vent.setDetallList(detallListNew);
            vent = em.merge(vent);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getVentList().remove(vent);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getVentList().add(vent);
                clienteNew = em.merge(clienteNew);
            }
            if (vendedorOld != null && !vendedorOld.equals(vendedorNew)) {
                vendedorOld.getVentList().remove(vent);
                vendedorOld = em.merge(vendedorOld);
            }
            if (vendedorNew != null && !vendedorNew.equals(vendedorOld)) {
                vendedorNew.getVentList().add(vent);
                vendedorNew = em.merge(vendedorNew);
            }
            for (Detall detallListOldDetall : detallListOld) {
                if (!detallListNew.contains(detallListOldDetall)) {
                    detallListOldDetall.setIdVenta(null);
                    detallListOldDetall = em.merge(detallListOldDetall);
                }
            }
            for (Detall detallListNewDetall : detallListNew) {
                if (!detallListOld.contains(detallListNewDetall)) {
                    Vent oldIdVentaOfDetallListNewDetall = detallListNewDetall.getIdVenta();
                    detallListNewDetall.setIdVenta(vent);
                    detallListNewDetall = em.merge(detallListNewDetall);
                    if (oldIdVentaOfDetallListNewDetall != null && !oldIdVentaOfDetallListNewDetall.equals(vent)) {
                        oldIdVentaOfDetallListNewDetall.getDetallList().remove(detallListNewDetall);
                        oldIdVentaOfDetallListNewDetall = em.merge(oldIdVentaOfDetallListNewDetall);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vent.getId();
                if (findVent(id) == null) {
                    throw new NonexistentEntityException("The vent with id " + id + " no longer exists.");
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
            Vent vent;
            try {
                vent = em.getReference(Vent.class, id);
                vent.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vent with id " + id + " no longer exists.", enfe);
            }
            Client cliente = vent.getCliente();
            if (cliente != null) {
                cliente.getVentList().remove(vent);
                cliente = em.merge(cliente);
            }
            Vendedo vendedor = vent.getVendedor();
            if (vendedor != null) {
                vendedor.getVentList().remove(vent);
                vendedor = em.merge(vendedor);
            }
            List<Detall> detallList = vent.getDetallList();
            for (Detall detallListDetall : detallList) {
                detallListDetall.setIdVenta(null);
                detallListDetall = em.merge(detallListDetall);
            }
            em.remove(vent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vent> findVentEntities() {
        return findVentEntities(true, -1, -1);
    }

    public List<Vent> findVentEntities(int maxResults, int firstResult) {
        return findVentEntities(false, maxResults, firstResult);
    }

    private List<Vent> findVentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vent.class));
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

    public Vent findVent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vent.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vent> rt = cq.from(Vent.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
