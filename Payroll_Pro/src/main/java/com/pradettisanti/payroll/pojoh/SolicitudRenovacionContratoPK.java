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
 * Entidad encargada de represerta las llaves primarias de la tabla solicitud_renovacion_contrato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class SolicitudRenovacionContratoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_solicitud_renovacion")
    private int idSolicitudRenovacion;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "agremiado")
    private int agremiado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente")
    private int cliente;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "sindicato")
    private int sindicato;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_contrato")
    private short tipoContrato;

    public SolicitudRenovacionContratoPK() {
    }

    public SolicitudRenovacionContratoPK(int idSolicitudRenovacion, int agremiado, int cliente, int sindicato, short tipoContrato) {
        this.idSolicitudRenovacion = idSolicitudRenovacion;
        this.agremiado = agremiado;
        this.cliente = cliente;
        this.sindicato = sindicato;
        this.tipoContrato = tipoContrato;
    }

    public int getIdSolicitudRenovacion() {
        return idSolicitudRenovacion;
    }

    public void setIdSolicitudRenovacion(int idSolicitudRenovacion) {
        this.idSolicitudRenovacion = idSolicitudRenovacion;
    }

    public int getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(int agremiado) {
        this.agremiado = agremiado;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getSindicato() {
        return sindicato;
    }

    public void setSindicato(int sindicato) {
        this.sindicato = sindicato;
    }

    public short getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(short tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += idSolicitudRenovacion;
        hash += agremiado;
        hash += cliente;
        hash += sindicato;
        hash += tipoContrato;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SolicitudRenovacionContratoPK)) {
            return false;
        }
        SolicitudRenovacionContratoPK other = (SolicitudRenovacionContratoPK) object;
        if (this.idSolicitudRenovacion != other.idSolicitudRenovacion) {
            return false;
        }
        if (this.agremiado != other.agremiado) {
            return false;
        }
        if (this.cliente != other.cliente) {
            return false;
        }
        if (this.sindicato != other.sindicato) {
            return false;
        }
        return this.tipoContrato == other.tipoContrato;
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.SolicitudRenovacionContratoPK[ idSolicitudRenovacion=" + idSolicitudRenovacion + ", agremiado=" + agremiado + ", cliente=" + cliente + ", sindicato=" + sindicato + ", tipoContrato=" + tipoContrato + " ]";
    }
    
}
