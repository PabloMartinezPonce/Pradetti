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
 * Entidad encargada de representar las llaves primarias de la tabla datos_laborales
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class DatosLaboralesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "agremiado")
    private int agremiado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente")
    private int cliente;

    public DatosLaboralesPK() {
    }

    public DatosLaboralesPK(int agremiado, short tipoNomina, int sindicato, int cliente, short tipoContrato) {
        this.agremiado = agremiado;
        this.cliente = cliente;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += agremiado;
        hash += cliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DatosLaboralesPK)) {
            return false;
        }
        DatosLaboralesPK other = (DatosLaboralesPK) object;
        if (this.agremiado != other.agremiado) {
            return false;
        }
        return this.cliente == other.cliente;
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.DatosLaboralesPK[ agremiado=" + agremiado + ", cliente=" + cliente + " ]";
    }
    
}
