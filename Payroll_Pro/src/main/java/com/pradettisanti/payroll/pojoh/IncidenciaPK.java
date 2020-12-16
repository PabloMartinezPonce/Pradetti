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
 * Entidad encargada de representar las llaves primarias de la tabla incidencia
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class IncidenciaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_incidencia")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idIncidencia;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "agremiado")
    private int agremiado;

    public IncidenciaPK() {
    }

    public IncidenciaPK(int idIncidencia, int agremiado) {
        this.idIncidencia = idIncidencia;
        this.agremiado = agremiado;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public int getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(int agremiado) {
        this.agremiado = agremiado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += idIncidencia;
        hash += agremiado;
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
        final IncidenciaPK other = (IncidenciaPK) obj;
        if (this.idIncidencia != other.idIncidencia) {
            return false;
        }
        return this.agremiado == other.agremiado;
    }

    

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.IncidenciaPK[ idIncidencia=" + idIncidencia + ", agremiado=" + agremiado + " ]";
    }
    
}
