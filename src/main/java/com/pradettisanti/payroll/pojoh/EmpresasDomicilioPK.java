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
 * Entidad encargada de representar las llaves primarias de la tabla empresas_domicilio
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Embeddable
public class EmpresasDomicilioPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresas_domicilio")
    private int idEmpresasDomicilio;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_domicilio")
    private short tipoDomicilio;

    public EmpresasDomicilioPK() {
    }

    public EmpresasDomicilioPK(int idEmpresasDomicilio, short tipoDomicilio) {
        this.idEmpresasDomicilio = idEmpresasDomicilio;
        this.tipoDomicilio = tipoDomicilio;
    }

    public int getIdEmpresasDomicilio() {
        return idEmpresasDomicilio;
    }

    public void setIdEmpresasDomicilio(int idEmpresasDomicilio) {
        this.idEmpresasDomicilio = idEmpresasDomicilio;
    }

    public short getTipoDomicilio() {
        return tipoDomicilio;
    }

    public void setTipoDomicilio(short tipoDomicilio) {
        this.tipoDomicilio = tipoDomicilio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += idEmpresasDomicilio;
        hash += tipoDomicilio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EmpresasDomicilioPK)) {
            return false;
        }
        EmpresasDomicilioPK other = (EmpresasDomicilioPK) object;
        if (this.idEmpresasDomicilio != other.idEmpresasDomicilio) {
            return false;
        }
        return this.tipoDomicilio == other.tipoDomicilio;
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.EmpresasDomicilioPK[ idEmpresasDomicilio=" + idEmpresasDomicilio + ", tipoDomicilio=" + tipoDomicilio + " ]";
    }
    
}
