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
import com.emergentes.entidades.Proveedo;
import com.emergentes.entidades.Detall;
import com.emergentes.entidades.Producto;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Virtual_7
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getDetallList() == null) {
            producto.setDetallList(new ArrayList<Detall>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedo proveedor = producto.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getId());
                producto.setProveedor(proveedor);
            }
            List<Detall> attachedDetallList = new ArrayList<Detall>();
            for (Detall detallListDetallToAttach : producto.getDetallList()) {
                detallListDetallToAttach = em.getReference(detallListDetallToAttach.getClass(), detallListDetallToAttach.getId());
                attachedDetallList.add(detallListDetallToAttach);
            }
            producto.setDetallList(attachedDetallList);
            em.persist(producto);
            if (proveedor != null) {
                proveedor.getProductoList().add(producto);
                proveedor = em.merge(proveedor);
            }
            for (Detall detallListDetall : producto.getDetallList()) {
                Producto oldIdProOfDetallListDetall = detallListDetall.getIdPro();
                detallListDetall.setIdPro(producto);
                detallListDetall = em.merge(detallListDetall);
                if (oldIdProOfDetallListDetall != null) {
                    oldIdProOfDetallListDetall.getDetallList().remove(detallListDetall);
                    oldIdProOfDetallListDetall = em.merge(oldIdProOfDetallListDetall);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getId());
            Proveedo proveedorOld = persistentProducto.getProveedor();
            Proveedo proveedorNew = producto.getProveedor();
            List<Detall> detallListOld = persistentProducto.getDetallList();
            List<Detall> detallListNew = producto.getDetallList();
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getId());
                producto.setProveedor(proveedorNew);
            }
            List<Detall> attachedDetallListNew = new ArrayList<Detall>();
            for (Detall detallListNewDetallToAttach : detallListNew) {
                detallListNewDetallToAttach = em.getReference(detallListNewDetallToAttach.getClass(), detallListNewDetallToAttach.getId());
                attachedDetallListNew.add(detallListNewDetallToAttach);
            }
            detallListNew = attachedDetallListNew;
            producto.setDetallList(detallListNew);
            producto = em.merge(producto);
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getProductoList().remove(producto);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getProductoList().add(producto);
                proveedorNew = em.merge(proveedorNew);
            }
            for (Detall detallListOldDetall : detallListOld) {
                if (!detallListNew.contains(detallListOldDetall)) {
                    detallListOldDetall.setIdPro(null);
                    detallListOldDetall = em.merge(detallListOldDetall);
                }
            }
            for (Detall detallListNewDetall : detallListNew) {
                if (!detallListOld.contains(detallListNewDetall)) {
                    Producto oldIdProOfDetallListNewDetall = detallListNewDetall.getIdPro();
                    detallListNewDetall.setIdPro(producto);
                    detallListNewDetall = em.merge(detallListNewDetall);
                    if (oldIdProOfDetallListNewDetall != null && !oldIdProOfDetallListNewDetall.equals(producto)) {
                        oldIdProOfDetallListNewDetall.getDetallList().remove(detallListNewDetall);
                        oldIdProOfDetallListNewDetall = em.merge(oldIdProOfDetallListNewDetall);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getId();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            Proveedo proveedor = producto.getProveedor();
            if (proveedor != null) {
                proveedor.getProductoList().remove(producto);
                proveedor = em.merge(proveedor);
            }
            List<Detall> detallList = producto.getDetallList();
            for (Detall detallListDetall : detallList) {
                detallListDetall.setIdPro(null);
                detallListDetall = em.merge(detallListDetall);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
