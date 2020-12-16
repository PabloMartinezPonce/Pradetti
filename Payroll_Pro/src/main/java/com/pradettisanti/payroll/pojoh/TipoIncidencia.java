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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar la tabla tipo_incidencia
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "tipo_incidencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIncidencia.findAll", query = "SELECT t FROM TipoIncidencia t"),
    @NamedQuery(name = "TipoIncidencia.findByIdTipoIncidencia", query = "SELECT t FROM TipoIncidencia t WHERE t.idTipoIncidencia = :idTipoIncidencia")})
public class TipoIncidencia implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_incidencia")
    private Short idTipoIncidencia;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "dias")
    private boolean dias;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "criterio")
    private boolean criterio;

    public TipoIncidencia() {
    }

    public TipoIncidencia(Short idTipoIncidencia) {
        this.idTipoIncidencia = idTipoIncidencia;
    }

    public TipoIncidencia(Short idTipoIncidencia, boolean dias, boolean criterio) {
        this.idTipoIncidencia = idTipoIncidencia;
        this.dias = dias;
        this.criterio = criterio;
    }

    public Short getIdTipoIncidencia() {
        return idTipoIncidencia;
    }

    public void setIdTipoIncidencia(Short idTipoIncidencia) {
        this.idTipoIncidencia = idTipoIncidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getDias() {
        return dias;
    }

    public void setDias(boolean dias) {
        this.dias = dias;
    }

    public boolean getCriterio() {
        return criterio;
    }

    public void setCriterio(boolean criterio) {
        this.criterio = criterio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoIncidencia != null ? idTipoIncidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoIncidencia)) {
            return false;
        }
        TipoIncidencia other = (TipoIncidencia) object;
        return !((this.idTipoIncidencia == null && other.idTipoIncidencia != null) || (this.idTipoIncidencia != null && !this.idTipoIncidencia.equals(other.idTipoIncidencia)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.TipoIncidencia[ idTipoIncidencia=" + idTipoIncidencia + " ]";
    }

}
