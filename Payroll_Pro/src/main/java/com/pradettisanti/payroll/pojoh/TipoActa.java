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
 * Entidad encargada de representar a la tabla tipo_acta
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "tipo_acta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoActa.findAll", query = "SELECT t FROM TipoActa t"),
    @NamedQuery(name = "TipoActa.findByIdTipoActa", query = "SELECT t FROM TipoActa t WHERE t.idTipoActa = :idTipoActa"),
    @NamedQuery(name = "TipoActa.findByDescripcion", query = "SELECT t FROM TipoActa t WHERE t.descripcion = :descripcion")})
public class TipoActa implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_acta")
    private Short idTipoActa;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;

    public TipoActa() {
    }

    public TipoActa(Short idTipoActa) {
        this.idTipoActa = idTipoActa;
    }

    public TipoActa(Short idTipoActa, String descripcion) {
        this.idTipoActa = idTipoActa;
        this.descripcion = descripcion;
    }

    public Short getIdTipoActa() {
        return idTipoActa;
    }

    public void setIdTipoActa(Short idTipoActa) {
        this.idTipoActa = idTipoActa;
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
        hash += (idTipoActa != null ? idTipoActa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoActa)) {
            return false;
        }
        TipoActa other = (TipoActa) object;
        return !((this.idTipoActa == null && other.idTipoActa != null) || (this.idTipoActa != null && !this.idTipoActa.equals(other.idTipoActa)));
    }

    @Override
    public String toString() {
        return "TipoActa{" + "idTipoActa=" + idTipoActa + ", descripcion=" + descripcion + '}';
    }

}
