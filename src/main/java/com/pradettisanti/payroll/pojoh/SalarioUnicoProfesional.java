/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *  Clase encargada  de representar la tabla salarios_unicos_profesionales
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version  1
 * @version  2017/12/06
 */
@Entity
@Table(name = "salarios_unicos_profesionales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalarioUnicoProfesional.findAll",query = "FROM SalarioUnicoProfesional"),
    @NamedQuery(name = "SalarioUnicoProfesional.findById",query = "FROM SalarioUnicoProfesional s WHERE s.idSalarioUnicoProfesional = :idSalarioUnicoProfesional")})
public class SalarioUnicoProfesional implements Serializable {
    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sup")
    private Integer idSalarioUnicoProfesional;
    
    @Column(name ="oficio")
     @Size(max = 300)
    private String oficio;
    
    @Column(name="descripcion")
    @Size(max = 827)
    private String descripcion;
    
    @Column(name="pesos_diarios")
    private double pesosDiarios;
    
    @JoinColumn(name = "tipo_sup", foreignKey = @ForeignKey(name = "salariosUnicosProfesionales_tipoSub"), referencedColumnName = "id_tipo_sup", insertable = false, updatable = false)
    @Column(name = "tipo_sup")
    private Integer tipoSUP; 
    
    @Column(name = "fecha_inicio")
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "salarioUnicoProfesionalList")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ContratoEmpresas> contratoEmpresasList;

    public Integer getIdSalarioUnicoProfesional() {
        return idSalarioUnicoProfesional;
    }

    public void setIdSalarioUnicoProfesional(Integer idSalarioUnicoProfesional) {
        this.idSalarioUnicoProfesional = idSalarioUnicoProfesional;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPesosDiarios() {
        return pesosDiarios;
    }

    public void setPesosDiarios(double pesosDiarios) {
        this.pesosDiarios = pesosDiarios;
    }

    public Integer getTipoSUP() {
        return tipoSUP;
    }

    public void setTipoSUP(Integer tipoSUP) {
        this.tipoSUP = tipoSUP;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<ContratoEmpresas> getContratoEmpresasList() {
        return contratoEmpresasList;
    }

    public void setContratoEmpresasList(List<ContratoEmpresas> contratoEmpresasList) {
        this.contratoEmpresasList = contratoEmpresasList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.idSalarioUnicoProfesional);
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
        final SalarioUnicoProfesional other = (SalarioUnicoProfesional) obj;
        return Objects.equals(this.idSalarioUnicoProfesional, other.idSalarioUnicoProfesional);
    }
   
    @Override
    public String toString() {
        return "SalarioUnicoProfesional{" + "idSalarioUnicoProfesional=" + idSalarioUnicoProfesional + ", oficio=" + oficio + ", descripcion=" + descripcion + ", pesosDiarios=" + pesosDiarios + ", tipoSUP=" + tipoSUP + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", fechaModificacion=" + fechaModificacion + '}';
    }
 
}
