/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Virtual_7
 */
@Entity
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detall.findAll", query = "SELECT d FROM Detall d"),
    @NamedQuery(name = "Detall.findById", query = "SELECT d FROM Detall d WHERE d.id = :id"),
    @NamedQuery(name = "Detall.findByCantidad", query = "SELECT d FROM Detall d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "Detall.findByPrecio", query = "SELECT d FROM Detall d WHERE d.precio = :precio")})
public class Detall implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @JoinColumn(name = "id_pro", referencedColumnName = "id")
    @ManyToOne
    private Producto idPro;
    @JoinColumn(name = "id_venta", referencedColumnName = "id")
    @ManyToOne
    private Vent idVenta;

    public Detall() {
        this.id = 0;
        this.cantidad =0;
        this.precio = BigDecimal.ZERO;
        this.idVenta = new Vent();
        this.idPro = new Producto();
    }

    public Detall(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Producto getIdPro() {
        return idPro;
    }

    public void setIdPro(Producto idPro) {
        this.idPro = idPro;
    }

    public Vent getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Vent idVenta) {
        this.idVenta = idVenta;
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
        if (!(object instanceof Detall)) {
            return false;
        }
        Detall other = (Detall) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
   /// @Override
    ///public String toString() {
       /// return "com.emergentes.entidades.Detall[ id=" + id + " ]";
   //// }

    @Override
    public String toString() {
        return "Detall{" + "id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", idPro=" + idPro + ", idVenta=" + idVenta + '}';
    }
    
}
