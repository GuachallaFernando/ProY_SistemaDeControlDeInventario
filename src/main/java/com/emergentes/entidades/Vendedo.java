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
@Table(name = "vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedo.findAll", query = "SELECT v FROM Vendedo v"),
    @NamedQuery(name = "Vendedo.findById", query = "SELECT v FROM Vendedo v WHERE v.id = :id"),
    @NamedQuery(name = "Vendedo.findByNombre", query = "SELECT v FROM Vendedo v WHERE v.nombre = :nombre"),
    @NamedQuery(name = "Vendedo.findByCi", query = "SELECT v FROM Vendedo v WHERE v.ci = :ci"),
    @NamedQuery(name = "Vendedo.findByTelefono", query = "SELECT v FROM Vendedo v WHERE v.telefono = :telefono"),
    @NamedQuery(name = "Vendedo.findByDireccion", query = "SELECT v FROM Vendedo v WHERE v.direccion = :direccion")})
public class Vendedo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "ci")
    private String ci;
    @Size(max = 255)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @OneToMany(mappedBy = "vendedor")
    private List<Vent> ventList;

    public Vendedo() {
        this.id = 0;
        this.nombre = "";
        this.ci = "";
        this.telefono = "";
        this.direccion = "";
        ventList = new ArrayList<Vent>();
    }

    public Vendedo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
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
    public List<Vent> getVentList() {
        return ventList;
    }

    public void setVentList(List<Vent> ventList) {
        this.ventList = ventList;
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
        if (!(object instanceof Vendedo)) {
            return false;
        }
        Vendedo other = (Vendedo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vendedo{" + "id=" + id + ", nombre=" + nombre + ", ci=" + ci + ", telefono=" + telefono + ", direccion=" + direccion + ", ventList=" + ventList + '}';
    }
    
    
}
