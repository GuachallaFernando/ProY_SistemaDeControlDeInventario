/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Virtual_7
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logi.findAll", query = "SELECT l FROM Logi l"),
    @NamedQuery(name = "Logi.findByUsuarioID", query = "SELECT l FROM Logi l WHERE l.usuarioID = :usuarioID"),
    @NamedQuery(name = "Logi.findByNombreUsuario", query = "SELECT l FROM Logi l WHERE l.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Logi.findByPasswrd", query = "SELECT l FROM Logi l WHERE l.passwrd = :passwrd"),
    @NamedQuery(name = "Logi.findByTipo", query = "SELECT l FROM Logi l WHERE l.tipo = :tipo")})
public class Logi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UsuarioID")
    private Integer usuarioID;
    @Size(max = 255)
    @Column(name = "NombreUsuario")
    private String nombreUsuario;
    @Size(max = 255)
    @Column(name = "passwrd")
    private String passwrd;
    @Size(max = 255)
    @Column(name = "Tipo")
    private String tipo;

    public Logi() {
        this.usuarioID=0;
        this.nombreUsuario="";
        this.passwrd="";
        this.tipo="";
    }

    public Logi(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioID != null ? usuarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logi)) {
            return false;
        }
        Logi other = (Logi) object;
        if ((this.usuarioID == null && other.usuarioID != null) || (this.usuarioID != null && !this.usuarioID.equals(other.usuarioID))) {
            return false;
        }
        return true;
    }

    ////@Override
    ////public String toString() {
       /// return "com.emergentes.entidades.Logi[ usuarioID=" + usuarioID + " ]";
    ///}

    @Override
    public String toString() {
        return "Logi{" + "usuarioID=" + usuarioID + ", nombreUsuario=" + nombreUsuario + ", passwrd=" + passwrd + ", tipo=" + tipo + '}';
    }
    
}
