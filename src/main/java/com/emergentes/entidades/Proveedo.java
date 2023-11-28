/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Virtual_7
 */
@Entity
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedo.findAll", query = "SELECT p FROM Proveedo p"),
    @NamedQuery(name = "Proveedo.findById", query = "SELECT p FROM Proveedo p WHERE p.id = :id"),
    @NamedQuery(name = "Proveedo.findByRuc", query = "SELECT p FROM Proveedo p WHERE p.ruc = :ruc"),
    @NamedQuery(name = "Proveedo.findByNombre", query = "SELECT p FROM Proveedo p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Proveedo.findByTelefono", query = "SELECT p FROM Proveedo p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Proveedo.findByDireccion", query = "SELECT p FROM Proveedo p WHERE p.direccion = :direccion")})
public class Proveedo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "ruc")
    private String ruc;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 15)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productoList;

    public Proveedo() {
        this.id = 0;
        this.ruc = "";
        this.nombre = "";
        this.telefono = "";
        this.direccion = "";
        this.productoList = new ArrayList<Producto>();
    }

    public Proveedo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedo)) {
            return false;
        }
        Proveedo other = (Proveedo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proveedo{" + "id=" + id + ", ruc=" + ruc + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + ", productoList=" + productoList + '}';
    }

}
