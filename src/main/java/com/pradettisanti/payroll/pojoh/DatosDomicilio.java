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
 * Entidad encargada de representar a la tabla datos_domicilio
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "datos_domicilio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosDomicilio.findAll", query = "SELECT d FROM DatosDomicilio d"),
    @NamedQuery(name = "DatosDomicilio.findByAgremiado", query = "SELECT d FROM DatosDomicilio d WHERE d.agremiado = :agremiado"),
    @NamedQuery(name = "DatosDomicilio.findByEstado", query = "SELECT d FROM DatosDomicilio d WHERE d.estado = :estado"),
    @NamedQuery(name = "DatosDomicilio.findByCiudad", query = "SELECT d FROM DatosDomicilio d WHERE d.ciudad = :ciudad"),
    @NamedQuery(name = "DatosDomicilio.findByMunicipio", query = "SELECT d FROM DatosDomicilio d WHERE d.municipio = :municipio"),
    @NamedQuery(name = "DatosDomicilio.findByColonia", query = "SELECT d FROM DatosDomicilio d WHERE d.colonia = :colonia"),
    @NamedQuery(name = "DatosDomicilio.findByCalle", query = "SELECT d FROM DatosDomicilio d WHERE d.calle = :calle"),
    @NamedQuery(name = "DatosDomicilio.findByNumero", query = "SELECT d FROM DatosDomicilio d WHERE d.numero = :numero"),
    @NamedQuery(name = "DatosDomicilio.findByCodigoPostal", query = "SELECT d FROM DatosDomicilio d WHERE d.codigoPostal = :codigoPostal"),
    @NamedQuery(name = "DatosDomicilio.findByEntreCalles", query = "SELECT d FROM DatosDomicilio d WHERE d.entreCalles = :entreCalles"),
    @NamedQuery(name = "DatosDomicilio.findByTipoVivienda", query = "SELECT d FROM DatosDomicilio d WHERE d.tipoVivienda = :tipoVivienda"),
    @NamedQuery(name = "DatosDomicilio.findByFachada", query = "SELECT d FROM DatosDomicilio d WHERE d.fachada = :fachada"),
    @NamedQuery(name = "DatosDomicilio.findByColorFachada", query = "SELECT d FROM DatosDomicilio d WHERE d.colorFachada = :colorFachada"),
    @NamedQuery(name = "DatosDomicilio.findByTipoVia", query = "SELECT d FROM DatosDomicilio d WHERE d.tipoVia = :tipoVia"),
    @NamedQuery(name = "DatosDomicilio.findByReferencia", query = "SELECT d FROM DatosDomicilio d WHERE d.referencia = :referencia")})
public class DatosDomicilio implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "agremiado")
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="agremiadoObj"))
    private Integer agremiado;
    
    @Size(max = 100)
    @Column(name = "estado")
    private String estado;
    
    @Size(max = 100)
    @Column(name = "ciudad")
    private String ciudad;
    
    @Size(max = 100)
    @Column(name = "municipio")
    private String municipio;
    
    @Size(max = 100)
    @Column(name = "colonia")
    private String colonia;
    
    @Size(max = 100)
    @Column(name = "calle")
    private String calle;
    
    @Size(max = 25)
    @Column(name = "numero")
    private String numero;
    
    @Size(max = 15)
    @Column(name = "codigo_postal")
    private String codigoPostal;
    
    @Size(max = 100)
    @Column(name = "entre_calles")
    private String entreCalles;
    
    @Size(max = 100)
    @Column(name = "tipo_vivienda")
    private String tipoVivienda;
    
    @Size(max = 100)
    @Column(name = "fachada")
    private String fachada;
    
    @Size(max = 100)
    @Column(name = "color_fachada")
    private String colorFachada;
    
    @Size(max = 100)
    @Column(name = "tipo_via")
    private String tipoVia;
    
    @Size(max = 200)
    @Column(name = "referencia")
    private String referencia;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Agremiado agremiadoObj;

    public DatosDomicilio() {
    }

    public DatosDomicilio(Integer agremiado) {
        this.agremiado = agremiado;
    }

    public Integer getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(Integer agremiado) {
        this.agremiado = agremiado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEntreCalles() {
        return entreCalles;
    }

    public void setEntreCalles(String entreCalles) {
        this.entreCalles = entreCalles;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public String getFachada() {
        return fachada;
    }

    public void setFachada(String fachada) {
        this.fachada = fachada;
    }

    public String getColorFachada() {
        return colorFachada;
    }

    public void setColorFachada(String colorFachada) {
        this.colorFachada = colorFachada;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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
        if (!(object instanceof DatosDomicilio)) {
            return false;
        }
        DatosDomicilio other = (DatosDomicilio) object;
        return !((this.agremiado == null && other.agremiado != null) || (this.agremiado != null && !this.agremiado.equals(other.agremiado)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.DatosDomicilio[ agremiado=" + agremiado + " ]";
    }
    
}
