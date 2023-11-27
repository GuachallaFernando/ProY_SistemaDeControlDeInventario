/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vent.findAll", query = "SELECT v FROM Vent v"),
    @NamedQuery(name = "Vent.findById", query = "SELECT v FROM Vent v WHERE v.id = :id"),
    @NamedQuery(name = "Vent.findByNombreCli", query = "SELECT v FROM Vent v WHERE v.nombreCli = :nombreCli"),
    @NamedQuery(name = "Vent.findByFecha", query = "SELECT v FROM Vent v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Vent.findByTotal", query = "SELECT v FROM Vent v WHERE v.total = :total")})
public class Vent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "nombre_cli")
    private String nombreCli;
    @Size(max = 255)
    @Column(name = "fecha")
    private String fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne
    private Client cliente;
    @JoinColumn(name = "vendedor", referencedColumnName = "id")
    @ManyToOne
    private Vendedo vendedor;
    @OneToMany(mappedBy = "idVenta")
    private List<Detall> detallList;

    public Vent() {
        this.id = 0;
        this.nombreCli = "";
        this.fecha = "";
        this.total = BigDecimal.ZERO;
        this.cliente = new Client(); // Debes ajustar esta línea si tienes un constructor adecuado para Client
        this.vendedor = new Vendedo(); // Ajusta esta línea según tu constructor para Vendedo
        this.detallList = new ArrayList<Detall>(); // Puedes usar otra implementación de List si lo prefieres
    }

    public Vent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Vendedo getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedo vendedor) {
        this.vendedor = vendedor;
    }

    @XmlTransient
    public List<Detall> getDetallList() {
        return detallList;
    }

    public void setDetallList(List<Detall> detallList) {
        this.detallList = detallList;
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
        if (!(object instanceof Vent)) {
            return false;
        }
        Vent other = (Vent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    ///@Override
    ///public String toString() {
       //7 return "Vent{" + "id=" + id + ", nombreCli=" + nombreCli + ", fecha=" + fecha + ", total=" + total + ", cliente=" + cliente + ", vendedor=" + vendedor + ", detallList=" + detallList + '}';
    ///}

    public void setTotal(double total) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
