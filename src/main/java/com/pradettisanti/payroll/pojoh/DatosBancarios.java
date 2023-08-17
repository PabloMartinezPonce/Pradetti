/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Entidad encargada de representar la tabla datos_bancarios
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "datos_bancarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosBancarios.findAll", query = "SELECT d FROM DatosBancarios d"),
    @NamedQuery(name = "DatosBancarios.findByAgremiado", query = "SELECT d FROM DatosBancarios d WHERE d.agremiado = :agremiado"),
    @NamedQuery(name = "DatosBancarios.findByNombreBanco", query = "SELECT d FROM DatosBancarios d WHERE d.nombreBanco = :nombreBanco"),
    @NamedQuery(name = "DatosBancarios.findByTitular", query = "SELECT d FROM DatosBancarios d WHERE d.titular = :titular"),
    @NamedQuery(name = "DatosBancarios.findByClabe", query = "SELECT d FROM DatosBancarios d WHERE d.clabe = :clabe"),
    @NamedQuery(name = "DatosBancarios.findByNumeroCuenta", query = "SELECT d FROM DatosBancarios d WHERE d.numeroCuenta = :numeroCuenta")})
public class DatosBancarios implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "agremiado")
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="agremiadoObj"))
    private Integer agremiado;
    
    @Size(max = 255)
    @Column(name = "nombre_banco")
    private String nombreBanco;
    
    @Size(max = 255)
    @Column(name = "titular")
    private String titular;
    
    @Size(max = 255)
    @Column(name = "clabe")
    private String clabe;
    
    @Size(max = 255)
    @Column(name = "numero_cuenta")
    private String numeroCuenta;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Agremiado agremiadoObj;

    public DatosBancarios() {
    }

    public DatosBancarios(Integer agremiado) {
        this.agremiado = agremiado;
    }

    public Integer getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(Integer agremiado) {
        this.agremiado = agremiado;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Agremiado getAgremiadoObj() {
        return agremiadoObj;
    }

    public void setAgremiadoObj(Agremiado agremiadoObj) {
        this.agremiadoObj = agremiadoObj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agremiado != null ? agremiado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DatosBancarios)) {
            return false;
        }
        DatosBancarios other = (DatosBancarios) object;
        return !((this.agremiado == null && other.agremiado != null) || (this.agremiado != null && !this.agremiado.equals(other.agremiado)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.DatosBancarios[ agremiado=" + agremiado + " ]";
    }
    
}
