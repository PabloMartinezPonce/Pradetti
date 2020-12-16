/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar a la tabla tipo_finiquito
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "tipo_finiquito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoFiniquito.findAll", query = "SELECT t FROM TipoFiniquito t"),
    @NamedQuery(name = "TipoFiniquito.findByIdTipoFiniquito", query = "SELECT t FROM TipoFiniquito t WHERE t.idTipoFiniquito = :idTipoFiniquito"),
    @NamedQuery(name = "TipoFiniquito.findByDescripcion", query = "SELECT t FROM TipoFiniquito t WHERE t.descripcion = :descripcion")})
public class TipoFiniquito implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_finiquito")
    private Short idTipoFiniquito;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;

    public TipoFiniquito() {
    }

    public TipoFiniquito(Short idTipoFiniquito) {
        this.idTipoFiniquito = idTipoFiniquito;
    }

    public TipoFiniquito(Short idTipoFiniquito, String descripcion) {
        this.idTipoFiniquito = idTipoFiniquito;
        this.descripcion = descripcion;
    }

    public Short getIdTipoFiniquito() {
        return idTipoFiniquito;
    }

    public void setIdTipoFiniquito(Short idTipoFiniquito) {
        this.idTipoFiniquito = idTipoFiniquito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoFiniquito != null ? idTipoFiniquito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoFiniquito)) {
            return false;
        }
        TipoFiniquito other = (TipoFiniquito) object;
        return !((this.idTipoFiniquito == null && other.idTipoFiniquito != null) || (this.idTipoFiniquito != null && !this.idTipoFiniquito.equals(other.idTipoFiniquito)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.TipoFiniquito[ idTipoFiniquito=" + idTipoFiniquito + " ]";
    }

    
}
