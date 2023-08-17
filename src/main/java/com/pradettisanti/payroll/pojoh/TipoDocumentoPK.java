/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * Entidad encargada de representar las llaves primarias de la tabla tipo_documento
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class TipoDocumentoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private int idTipoDocumento;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "modulo")
    private short modulo;

    public TipoDocumentoPK() {
    }

    public TipoDocumentoPK(int idTipoDocumento, short modulo) {
        this.idTipoDocumento = idTipoDocumento;
        this.modulo = modulo;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public short getModulo() {
        return modulo;
    }

    public void setModulo(short modulo) {
        this.modulo = modulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += idTipoDocumento;
        hash += modulo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoDocumentoPK)) {
            return false;
        }
        TipoDocumentoPK other = (TipoDocumentoPK) object;
        if (this.idTipoDocumento != other.idTipoDocumento) {
            return false;
        }
        return this.modulo == other.modulo;
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.TipoDocumentoPK[ idTipoDocumento=" + idTipoDocumento + ", modulo=" + modulo + " ]";
    }
    
}
