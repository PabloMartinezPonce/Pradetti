/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Entidad encargada de representar las llaves primarias de la tabla documento
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class DocumentoPK implements Serializable {
    
    @Basic(optional = false)
    @Column(name = "id_documento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;
    
    @Basic(optional = false)
    @Column(name = "agremiado")
    private int agremiado;
    
    @Basic(optional = false)
    @Column(name = "tipo_documento")
    private int tipoDocumento;

    public DocumentoPK() {
    }

    public DocumentoPK(int idDocumento, int agremiado, int tipoDocumento) {
        this.idDocumento = idDocumento;
        this.agremiado = agremiado;
        this.tipoDocumento = tipoDocumento;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public int getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(int agremiado) {
        this.agremiado = agremiado;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idDocumento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DocumentoPK other = (DocumentoPK) obj;
        if (!Objects.equals(this.idDocumento, other.idDocumento)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.DocumentoPK[ idDocumento=" + idDocumento + ", agremiado=" + agremiado + ", tipoDocumento=" + tipoDocumento + " ]";
    }
    
}
