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
 * Entidad encargada de representar a la tabla tipo_nomina
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "tipo_nomina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoNomina.findAll", query = "SELECT t FROM TipoNomina t"),
    @NamedQuery(name = "TipoNomina.findByIdTipoNomina", query = "SELECT t FROM TipoNomina t WHERE t.idTipoNomina = :idTipoNomina"),
    @NamedQuery(name = "TipoNomina.findByTipoNomina", query = "SELECT t FROM TipoNomina t WHERE t.tipoNomina = :tipoNomina")})
public class TipoNomina implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_nomina")
    private Short idTipoNomina;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_nomina")
    private String tipoNomina;

    public TipoNomina() {
    }

    public TipoNomina(Short idTipoNomina) {
        this.idTipoNomina = idTipoNomina;
    }

    public TipoNomina(Short idTipoNomina, String tipoNomina) {
        this.idTipoNomina = idTipoNomina;
        this.tipoNomina = tipoNomina;
    }

    public Short getIdTipoNomina() {
        return idTipoNomina;
    }

    public void setIdTipoNomina(Short idTipoNomina) {
        this.idTipoNomina = idTipoNomina;
    }

    public String getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(String tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoNomina != null ? idTipoNomina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoNomina)) {
            return false;
        }
        TipoNomina other = (TipoNomina) object;
        return !((this.idTipoNomina == null && other.idTipoNomina != null) || (this.idTipoNomina != null && !this.idTipoNomina.equals(other.idTipoNomina)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.TipoNomina[ idTipoNomina=" + idTipoNomina + " ]";
    }

}
