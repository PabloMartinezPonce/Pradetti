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
 * Entidad encargda de representar a la tabla motivo_baja
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "motivo_baja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MotivoBaja.findAll", query = "SELECT m FROM MotivoBaja m"),
    @NamedQuery(name = "MotivoBaja.findByIdMotivoBaja", query = "SELECT m FROM MotivoBaja m WHERE m.idMotivoBaja = :idMotivoBaja"),
    @NamedQuery(name = "MotivoBaja.findByMotivo", query = "SELECT m FROM MotivoBaja m WHERE m.motivo = :motivo")})
public class MotivoBaja implements Serializable {

    private static final long serialVersionUID = 270890L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false) 
    @Column(name = "id_motivo_baja")
    private Short idMotivoBaja;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "motivo")
    private String motivo;

    public MotivoBaja() {
    }

    public MotivoBaja(Short idMotivoBaja) {
        this.idMotivoBaja = idMotivoBaja;
    }

    public MotivoBaja(Short idMotivoBaja, String motivo) {
        this.idMotivoBaja = idMotivoBaja;
        this.motivo = motivo;
    }

    public Short getIdMotivoBaja() {
        return idMotivoBaja;
    }

    public void setIdMotivoBaja(Short idMotivoBaja) {
        this.idMotivoBaja = idMotivoBaja;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMotivoBaja != null ? idMotivoBaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MotivoBaja)) {
            return false;
        }
        MotivoBaja other = (MotivoBaja) object;
        return !((this.idMotivoBaja == null && other.idMotivoBaja != null) || (this.idMotivoBaja != null && !this.idMotivoBaja.equals(other.idMotivoBaja)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.MotivoBaja[ idMotivoBaja=" + idMotivoBaja + " ]";
    }
    
}
