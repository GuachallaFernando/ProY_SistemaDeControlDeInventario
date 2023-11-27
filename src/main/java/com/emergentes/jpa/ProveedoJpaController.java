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
import com.emergentes.entidades.Producto;
import com.emergentes.entidades.Proveedo;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Virtual_7
 */
public class ProveedoJpaController implements Serializable {

    public ProveedoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedo proveedo) {
        if (proveedo.getProductoList() == null) {
            proveedo.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : proveedo.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getId());
                attachedProductoList.add(productoListProductoToAttach);
            }
            proveedo.setProductoList(attachedProductoList);
            em.persist(proveedo);
            for (Producto productoListProducto : proveedo.getProductoList()) {
                Proveedo oldProveedorOfProductoListProducto = productoListProducto.getProveedor();
                productoListProducto.setProveedor(proveedo);
                productoListProducto = em.merge(productoListProducto);
                if (oldProveedorOfProductoListProducto != null) {
                    oldProveedorOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldProveedorOfProductoListProducto = em.merge(oldProveedorOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedo proveedo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedo persistentProveedo = em.find(Proveedo.class, proveedo.getId());
            List<Producto> productoListOld = persistentProveedo.getProductoList();
            List<Producto> productoListNew = proveedo.getProductoList();
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getId());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            proveedo.setProductoList(productoListNew);
            proveedo = em.merge(proveedo);
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    productoListOldProducto.setProveedor(null);
                    productoListOldProducto = em.merge(productoListOldProducto);
                }
            }
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Proveedo oldProveedorOfProductoListNewProducto = productoListNewProducto.getProveedor();
                    productoListNewProducto.setProveedor(proveedo);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldProveedorOfProductoListNewProducto != null && !oldProveedorOfProductoListNewProducto.equals(proveedo)) {
                        oldProveedorOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldProveedorOfProductoListNewProducto = em.merge(oldProveedorOfProductoListNewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedo.getId();
                if (findProveedo(id) == null) {
                    throw new NonexistentEntityException("The proveedo with id " + id + " no longer exists.");
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
            Proveedo proveedo;
            try {
                proveedo = em.getReference(Proveedo.class, id);
                proveedo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedo with id " + id + " no longer exists.", enfe);
            }
            List<Producto> productoList = proveedo.getProductoList();
            for (Producto productoListProducto : productoList) {
                productoListProducto.setProveedor(null);
                productoListProducto = em.merge(productoListProducto);
            }
            em.remove(proveedo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedo> findProveedoEntities() {
        return findProveedoEntities(true, -1, -1);
    }

    public List<Proveedo> findProveedoEntities(int maxResults, int firstResult) {
        return findProveedoEntities(false, maxResults, firstResult);
    }

    private List<Proveedo> findProveedoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedo.class));
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

    public Proveedo findProveedo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedo.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedo> rt = cq.from(Proveedo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
