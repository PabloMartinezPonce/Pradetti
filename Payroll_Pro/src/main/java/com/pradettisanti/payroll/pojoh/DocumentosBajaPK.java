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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 * Entidad encargada de representar las llaves primarias de la tabla documentos_baja
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class DocumentosBajaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_baja_terminada")
    private int idBajaTerminada;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "solicitud_baja")
    private int solicitudBaja;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_documento")
    private int tipoDocumento;

    public DocumentosBajaPK() {
    }

    public DocumentosBajaPK(int idBajaTerminada, int solicitudBaja, int tipoDocumento) {
        this.idBajaTerminada = idBajaTerminada;
        this.solicitudBaja = solicitudBaja;
        this.tipoDocumento = tipoDocumento;
    }

    public int getIdBajaTerminada() {
        return idBajaTerminada;
    }

    public void setIdBajaTerminada(int idBajaTerminada) {
        this.idBajaTerminada = idBajaTerminada;
    }

    public int getSolicitudBaja() {
        return solicitudBaja;
    }

    public void setSolicitudBaja(int solicitudBaja) {
        this.solicitudBaja = solicitudBaja;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += idBajaTerminada;
        hash += solicitudBaja;
        hash += tipoDocumento;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DocumentosBajaPK)) {
            return false;
        }
        DocumentosBajaPK other = (DocumentosBajaPK) object;
        if (this.idBajaTerminada != other.idBajaTerminada) {
            return false;
        }
        if (this.solicitudBaja != other.solicitudBaja) {
            return false;
        }
        return this.tipoDocumento == other.tipoDocumento;
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.DocumentosBajaPK[ idBajaTerminada=" + idBajaTerminada + ", solicitudBaja=" + solicitudBaja + ", tipoDocumento=" + tipoDocumento + " ]";
    }
    
}
