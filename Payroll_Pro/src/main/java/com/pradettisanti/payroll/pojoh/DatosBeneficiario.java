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
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Entidad encargada de represenatar la tabla datos_beneficiario
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "datos_beneficiario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosBeneficiario.findAll", query = "SELECT d FROM DatosBeneficiario d"),
    @NamedQuery(name = "DatosBeneficiario.findByAgremiado", query = "SELECT d FROM DatosBeneficiario d WHERE d.agremiado = :agremiado"),
    @NamedQuery(name = "DatosBeneficiario.findByNombre", query = "SELECT d FROM DatosBeneficiario d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DatosBeneficiario.findByPorcentaje", query = "SELECT d FROM DatosBeneficiario d WHERE d.porcentaje = :porcentaje"),
    @NamedQuery(name = "DatosBeneficiario.findByFechaNacimiento", query = "SELECT d FROM DatosBeneficiario d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "DatosBeneficiario.findByCurp", query = "SELECT d FROM DatosBeneficiario d WHERE d.curp = :curp"),
    @NamedQuery(name = "DatosBeneficiario.findByParentesco", query = "SELECT d FROM DatosBeneficiario d WHERE d.parentesco = :parentesco"),
    @NamedQuery(name = "DatosBeneficiario.findByEstado", query = "SELECT d FROM DatosBeneficiario d WHERE d.estado = :estado"),
    @NamedQuery(name = "DatosBeneficiario.findByCiudad", query = "SELECT d FROM DatosBeneficiario d WHERE d.ciudad = :ciudad"),
    @NamedQuery(name = "DatosBeneficiario.findByMunicipio", query = "SELECT d FROM DatosBeneficiario d WHERE d.municipio = :municipio"),
    @NamedQuery(name = "DatosBeneficiario.findByColonia", query = "SELECT d FROM DatosBeneficiario d WHERE d.colonia = :colonia"),
    @NamedQuery(name = "DatosBeneficiario.findByCalle", query = "SELECT d FROM DatosBeneficiario d WHERE d.calle = :calle"),
    @NamedQuery(name = "DatosBeneficiario.findByNumero", query = "SELECT d FROM DatosBeneficiario d WHERE d.numero = :numero"),
    @NamedQuery(name = "DatosBeneficiario.findByCodigoPostal", query = "SELECT d FROM DatosBeneficiario d WHERE d.codigoPostal = :codigoPostal")})
public class DatosBeneficiario implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "agremiado")
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="agremiadoObj"))
    private Integer agremiado;
    
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    
    @Size(max = 5)
    @Column(name = "porcentaje")
    private String porcentaje;
    
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Size(max = 18)
    @Column(name = "curp")
    private String curp;
    
    @Size(max = 100)
    @Column(name = "parentesco")
    private String parentesco;
    
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
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Agremiado agremiadoObj;

    public DatosBeneficiario() {
    }

    public DatosBeneficiario(Integer agremiado) {
        this.agremiado = agremiado;
    }

    public Integer getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(Integer agremiado) {
        this.agremiado = agremiado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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
        if (!(object instanceof DatosBeneficiario)) {
            return false;
        }
        DatosBeneficiario other = (DatosBeneficiario) object;
        return !((this.agremiado == null && other.agremiado != null) || (this.agremiado != null && !this.agremiado.equals(other.agremiado)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.DatosBeneficiario[ agremiado=" + agremiado + " ]";
    }
    
}
