/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad encargada de representar la tabla zona_sm
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "zona_sm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ZonaSm.findAll", query = "SELECT z FROM ZonaSm z"),
    @NamedQuery(name = "ZonaSm.findByIdZonaSm", query = "SELECT z FROM ZonaSm z WHERE z.idZonaSm = :idZonaSm"),
    @NamedQuery(name = "ZonaSm.findByZona", query = "SELECT z FROM ZonaSm z WHERE z.zona = :zona"),
    @NamedQuery(name = "ZonaSm.findByAnio", query = "SELECT z FROM ZonaSm z WHERE z.anio = :anio")})
public class ZonaSm implements Serializable {

    private Float salario;
    @OneToMany(mappedBy = "zonaSm", fetch = FetchType.EAGER)
    private List<Cliente> clienteList;

    private static final long serialVersionUID = 270890L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_zona_sm")
    private Short idZonaSm;
    
    @Size(max = 100)
    @Column(name = "zona")
    private String zona;

    @Size(max = 100)
    @Column(name = "anio")
    private String anio;

    public ZonaSm() {
    }

    public ZonaSm(Short idZonaSm) {
        this.idZonaSm = idZonaSm;
    }

    public Short getIdZonaSm() {
        return idZonaSm;
    }

    public void setIdZonaSm(Short idZonaSm) {
        this.idZonaSm = idZonaSm;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
 
    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZonaSm != null ? idZonaSm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ZonaSm)) {
            return false;
        }
        ZonaSm other = (ZonaSm) object;
        return !((this.idZonaSm == null && other.idZonaSm != null) || (this.idZonaSm != null && !this.idZonaSm.equals(other.idZonaSm)));
    }

    @Override
    public String toString() {
        return "ZonaSm{" + "salario=" + salario + ", clienteList=" + clienteList + ", idZonaSm=" + idZonaSm + ", zona=" + zona + ", anio=" + anio + '}';
    }

}
