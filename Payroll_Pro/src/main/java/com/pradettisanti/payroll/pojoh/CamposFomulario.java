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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Pojo encargado de registrar ciertos campos existentes en payroll
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @since 22/02/2018
 * @version 1
 */
@Entity
@Table(name = "campos_formulario")
@XmlRootElement
public class CamposFomulario implements Serializable {
    
    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nombre_campo")
    private Integer idCampo;
     
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_campo")
    private String nombre;

    public Integer getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(Integer idCampo) {
        this.idCampo = idCampo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idCampo);
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
        final CamposFomulario other = (CamposFomulario) obj;
        if (!Objects.equals(this.idCampo, other.idCampo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CamposFomulario{" + "idCampo=" + idCampo + ", nombre=" + nombre + '}';
    }

    public CamposFomulario() {
    }

    public CamposFomulario(String nombre) {
        this.nombre = nombre;
    }
    
}
