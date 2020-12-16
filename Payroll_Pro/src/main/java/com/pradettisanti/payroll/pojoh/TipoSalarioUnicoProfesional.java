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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase encargada de representar la tabla tipo_sup
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since   2017/12/06
 */
@Entity
@Table(name = "tipo_sup")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "TipoSalarioUnicoProfesional.findAll",query = "FROM TipoSalarioUnicoProfesional t"),
                                    @NamedQuery(name = "TipoSalarioUnicoProfesional.findById",query = "FROM TipoSalarioUnicoProfesional t WHERE t.idTipoSalarioUnicoProfesional = :idTipoSalarioUnicoProfesional")})
public class TipoSalarioUnicoProfesional implements Serializable {

    private static final long serialVersionUID = 270890L;
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_sup")
    private Integer idTipoSalarioUnicoProfesional;
     
     @NotNull
     @Basic(optional = false)
     @Size(max = 200)
     @Column(name = "sector")
     private String sector;

    public TipoSalarioUnicoProfesional() {
    }

    public Integer getIdTipoSalarioUnicoProfesional() {
        return idTipoSalarioUnicoProfesional;
    }

    public void setIdTipoSalarioUnicoProfesional(Integer idTipoSalarioUnicoProfesional) {
        this.idTipoSalarioUnicoProfesional = idTipoSalarioUnicoProfesional;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public int hashCode() {
        int hash = 8;
        hash = 27 * hash + Objects.hashCode(this.idTipoSalarioUnicoProfesional);
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
        final TipoSalarioUnicoProfesional other = (TipoSalarioUnicoProfesional) obj;
        return Objects.equals(this.idTipoSalarioUnicoProfesional, other.idTipoSalarioUnicoProfesional);
    }

    @Override
    public String toString() {
        return "TipoSalarioUnicoProfesional{" + "idTipoSalarioUnicoProfesional=" + idTipoSalarioUnicoProfesional + ", sector=" + sector + '}';
    }
    
}
