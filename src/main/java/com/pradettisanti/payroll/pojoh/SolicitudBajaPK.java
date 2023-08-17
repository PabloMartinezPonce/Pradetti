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
 * Entidad encargada de represerta las llaves primarias de la tabla solicitud_baja
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class SolicitudBajaPK implements Serializable {

    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud_baja")
    private int idSolicitudBaja;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "agremiado")
    private int agremiado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "motivo_baja")
    private short motivoBaja;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_finiquito")
    private short tipoFiniquito;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente")
    private int cliente;

    public SolicitudBajaPK() {
    }

    public SolicitudBajaPK(int idSolicitudBaja, int agremiado, short motivoBaja, short tipoFiniquito, int cliente) {
        this.idSolicitudBaja = idSolicitudBaja;
        this.agremiado = agremiado;
        this.motivoBaja = motivoBaja;
        this.tipoFiniquito = tipoFiniquito;
        this.cliente = cliente;
    }

    public int getIdSolicitudBaja() {
        return idSolicitudBaja;
    }

    public void setIdSolicitudBaja(int idSolicitudBaja) {
        this.idSolicitudBaja = idSolicitudBaja;
    }

    public int getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(int agremiado) {
        this.agremiado = agremiado;
    }

    public short getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(short motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public short getTipoFiniquito() {
        return tipoFiniquito;
    }

    public void setTipoFiniquito(short tipoFiniquito) {
        this.tipoFiniquito = tipoFiniquito;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += idSolicitudBaja;
        hash += agremiado;
        hash += motivoBaja;
        hash += tipoFiniquito;
        hash += cliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SolicitudBajaPK)) {
            return false;
        }
        SolicitudBajaPK other = (SolicitudBajaPK) object;
        if (this.idSolicitudBaja != other.idSolicitudBaja) {
            return false;
        }
        if (this.agremiado != other.agremiado) {
            return false;
        }
        if (this.motivoBaja != other.motivoBaja) {
            return false;
        }
        if (this.tipoFiniquito != other.tipoFiniquito) {
            return false;
        }
        return this.cliente == other.cliente;
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.SolicitudBajaPK[ idSolicitudBaja=" + idSolicitudBaja + ", agremiado=" + agremiado + ", motivoBaja=" + motivoBaja + ", tipoFiniquito=" + tipoFiniquito + ", cliente=" + cliente + " ]";
    }
    
}
