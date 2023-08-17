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
 * Entidad encargada de representar a la tabla status_agremiado
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "status_agremiado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusAgremiado.findAll", query = "SELECT s FROM StatusAgremiado s"),
    @NamedQuery(name = "StatusAgremiado.findByIdStatusAgremiado", query = "SELECT s FROM StatusAgremiado s WHERE s.idStatusAgremiado = :idStatusAgremiado"),
    @NamedQuery(name = "StatusAgremiado.findByStatus", query = "SELECT s FROM StatusAgremiado s WHERE s.status = :status")})
public class StatusAgremiado implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_status_agremiado")
    private Short idStatusAgremiado;
    
    @Size(max = 100)
    @Column(name = "status")
    private String status;

    public StatusAgremiado() {
    }

    public StatusAgremiado(Short idStatusAgremiado) {
        this.idStatusAgremiado = idStatusAgremiado;
    }

    public Short getIdStatusAgremiado() {
        return idStatusAgremiado;
    }

    public void setIdStatusAgremiado(Short idStatusAgremiado) {
        this.idStatusAgremiado = idStatusAgremiado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStatusAgremiado != null ? idStatusAgremiado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StatusAgremiado)) {
            return false;
        }
        StatusAgremiado other = (StatusAgremiado) object;
        return !((this.idStatusAgremiado == null && other.idStatusAgremiado != null) || (this.idStatusAgremiado != null && !this.idStatusAgremiado.equals(other.idStatusAgremiado)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.StatusAgremiado[ idStatusAgremiado=" + idStatusAgremiado + " ]";
    }
    
}
