/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Entidad encargada de representar las llaves primarias de la tabla bitacora
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class BitacoraPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date idFecha;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
    private int usuario;

    /**
     * Constructor BitacoraPK
     */
    public BitacoraPK() {
    }

    public BitacoraPK(Date idFecha, int usuario) {
        this.idFecha = idFecha;
        this.usuario = usuario;
    }

    public Date getIdFecha() {
        return idFecha;
    }

    public void setIdFecha(Date idFecha) {
        this.idFecha = idFecha;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFecha != null ? idFecha.hashCode() : 0);
        hash += usuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BitacoraPK)) {
            return false;
        }
        BitacoraPK other = (BitacoraPK) object;
        if ((this.idFecha == null && other.idFecha != null) || (this.idFecha != null && !this.idFecha.equals(other.idFecha))) {
            return false;
        }
        return this.usuario == other.usuario;
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.BitacoraPK[ idFecha=" + idFecha + ", usuario=" + usuario + " ]";
    }
    
}
