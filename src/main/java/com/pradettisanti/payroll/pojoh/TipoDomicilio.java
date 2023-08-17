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
 * Entidad encargada de representar la tabla tipo_domicilio
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "tipo_domicilio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDomicilio.findAll", query = "SELECT t FROM TipoDomicilio t"),
    @NamedQuery(name = "TipoDomicilio.findByIdTipoDomicilio", query = "SELECT t FROM TipoDomicilio t WHERE t.idTipoDomicilio = :idTipoDomicilio")})
public class TipoDomicilio implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_domicilio")
    private Short idTipoDomicilio;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;

    public TipoDomicilio() {
    }

    public TipoDomicilio(Short idTipoDomicilio) {
        this.idTipoDomicilio = idTipoDomicilio;
    }

    public TipoDomicilio(Short idTipoDomicilio, String descripcion) {
        this.idTipoDomicilio = idTipoDomicilio;
        this.descripcion = descripcion;
    }

    public Short getIdTipoDomicilio() {
        return idTipoDomicilio;
    }

    public void setIdTipoDomicilio(Short idTipoDomicilio) {
        this.idTipoDomicilio = idTipoDomicilio;
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
        hash += (idTipoDomicilio != null ? idTipoDomicilio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoDomicilio)) {
            return false;
        }
        TipoDomicilio other = (TipoDomicilio) object;
        return !((this.idTipoDomicilio == null && other.idTipoDomicilio != null) || (this.idTipoDomicilio != null && !this.idTipoDomicilio.equals(other.idTipoDomicilio)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.TipoDomicilio[ idTipoDomicilio=" + idTipoDomicilio + " ]";
    }
    
}
