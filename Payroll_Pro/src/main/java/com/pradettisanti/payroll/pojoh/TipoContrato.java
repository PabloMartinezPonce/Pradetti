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
 * Entidad encargada de representar la tabla tipo_contrato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "tipo_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoContrato.findAll", query = "SELECT t FROM TipoContrato t"),
    @NamedQuery(name = "TipoContrato.findByIdTipoContrato", query = "SELECT t FROM TipoContrato t WHERE t.idTipoContrato = :idTipoContrato")})
public class TipoContrato implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_contrato")
    private Short idTipoContrato;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "Descripcion")
    private String descripcion;

    public TipoContrato() {
    }

    public TipoContrato(Short idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public TipoContrato(Short idTipoContrato, String descripcion) {
        this.idTipoContrato = idTipoContrato;
        this.descripcion = descripcion;
    }

    public Short getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(Short idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
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
        hash += (idTipoContrato != null ? idTipoContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoContrato)) {
            return false;
        }
        TipoContrato other = (TipoContrato) object;
        return !((this.idTipoContrato == null && other.idTipoContrato != null) || (this.idTipoContrato != null && !this.idTipoContrato.equals(other.idTipoContrato)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.TipoContrato[ idTipoContrato=" + idTipoContrato + " ]";
    }
}
