/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar la tabla periodo_fondo_ahorro
 * @author Gabriela Jaime gabriela.jaime@it-seekers.com
 */
@Entity
@Table(name = "periodo_fondo_ahorro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodoFA.findAll", query = "SELECT p FROM PeriodoFA p"),
    @NamedQuery(name = "PeriodoFA.findByIdPeriodoFA", query = "SELECT p FROM PeriodoFA p WHERE p.idPeriodoFA = :idPeriodoFA"),
    @NamedQuery(name = "PeriodoFA.findByNombrePeriodo", query = "SELECT p FROM PeriodoFA p WHERE p.nombrePeriodo = :nombrePeriodo"),
    @NamedQuery(name = "PeriodoFA.findByAgrFechainicialFechafinal", query = "FROM PeriodoFA p WHERE p.fechaIniPeriodo BETWEEN :diaRegistroDesde AND :diaRegistroHasta OR p.fechaFinPeriodo BETWEEN :diaRegistroDesde AND :diaRegistroHasta"),
    @NamedQuery(name = "PeriodoFA.findDateStartByIdPeriodo", query = "SELECT p.fechaIniPeriodo FROM PeriodoFA p WHERE p.idPeriodoFA = :idPeriodoFA"),
    @NamedQuery(name = "PeriodoFA.findDateEndByIdPeriodo", query = "SELECT p.fechaFinPeriodo FROM PeriodoFA p WHERE p.idPeriodoFA = :idPeriodoFA")})
public class PeriodoFA implements Serializable{
    
    private static final long serialVersionUID = 270890L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodo_fa")
    private Integer idPeriodoFA;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_periodo")
    private String nombrePeriodo;
    
    
    @Column(name = "fecha_inicio")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIniPeriodo;
    
    @Column(name = "fecha_fin")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinPeriodo;
    
    @JoinColumn(name = "contratista", foreignKey = @ForeignKey(name = "periodoFA_idContratista"), referencedColumnName = "id_contratista")
    @ManyToOne(optional = false)
    private Contratista contratistaObj;
    
    public PeriodoFA() {    
    }

    public Integer getIdPeriodoFA() {
        return idPeriodoFA;
    }

    public void setIdPeriodoFA(Integer idPeriodoFA) {
        this.idPeriodoFA = idPeriodoFA;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public Date getFechaIniPeriodo() {
        return fechaIniPeriodo;
    }

    public void setFechaIniPeriodo(Date fechaIniPeriodo) {
        this.fechaIniPeriodo = fechaIniPeriodo;
    }

    public Date getFechaFinPeriodo() {
        return fechaFinPeriodo;
    }

    public void setFechaFinPeriodo(Date fechaFinPeriodo) {
        this.fechaFinPeriodo = fechaFinPeriodo;
    }

    public Contratista getContratistaObj() {
        return contratistaObj;
    }

    public void setContratistaObj(Contratista contratistaObj) {
        this.contratistaObj = contratistaObj;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodoFA != null ? idPeriodoFA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PeriodoFA)) {
            return false;
        }
        PeriodoFA other = (PeriodoFA) object;
        return !((this.idPeriodoFA == null && other.idPeriodoFA != null) || (this.idPeriodoFA != null && !this.idPeriodoFA.equals(other.idPeriodoFA)));
    }

    @Override
    public String toString() {
        return "PeriodoFA{" + "idPeriodoFA=" + idPeriodoFA + ", nombrePeriodo=" + nombrePeriodo + ", fechaIniPeriodo=" + fechaIniPeriodo + ", fechaFinPeriodo=" + fechaFinPeriodo +'}';
    }
}
