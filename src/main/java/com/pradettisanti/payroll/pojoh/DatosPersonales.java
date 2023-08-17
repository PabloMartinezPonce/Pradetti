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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
 * Entidad encargada de representar la tabla datos_personales
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "datos_personales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosPersonales.findAll", query = "SELECT d FROM DatosPersonales d"),
    @NamedQuery(name = "DatosPersonales.findByAgremiado", query = "SELECT d FROM DatosPersonales d WHERE d.agremiado = :agremiado"),
    @NamedQuery(name = "DatosPersonales.findByNombre", query = "SELECT d FROM DatosPersonales d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "DatosPersonales.findByApellidoPaterno", query = "SELECT d FROM DatosPersonales d WHERE d.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "DatosPersonales.findByApellidoMaterno", query = "SELECT d FROM DatosPersonales d WHERE d.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "DatosPersonales.findByTelefono", query = "SELECT d FROM DatosPersonales d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DatosPersonales.findByEmail", query = "SELECT d FROM DatosPersonales d WHERE d.email = :email"),
    @NamedQuery(name = "DatosPersonales.findByEscolaridad", query = "SELECT d FROM DatosPersonales d WHERE d.escolaridad = :escolaridad"),
    @NamedQuery(name = "DatosPersonales.findByEdad", query = "SELECT d FROM DatosPersonales d WHERE d.edad = :edad"),
    @NamedQuery(name = "DatosPersonales.findByFechaNacimiento", query = "SELECT d FROM DatosPersonales d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "DatosPersonales.findByLugarNacimiento", query = "SELECT d FROM DatosPersonales d WHERE d.lugarNacimiento = :lugarNacimiento"),
    @NamedQuery(name = "DatosPersonales.findByNacionalidad", query = "SELECT d FROM DatosPersonales d WHERE d.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "DatosPersonales.findByEstadoCivil", query = "SELECT d FROM DatosPersonales d WHERE d.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "DatosPersonales.findByRegimenMatrimonial", query = "SELECT d FROM DatosPersonales d WHERE d.regimenMatrimonial = :regimenMatrimonial"),
    @NamedQuery(name = "DatosPersonales.findByHijos", query = "SELECT d FROM DatosPersonales d WHERE d.hijos = :hijos"),
    @NamedQuery(name = "DatosPersonales.findByRfc", query = "SELECT d FROM DatosPersonales d WHERE d.rfc = :rfc"),
    @NamedQuery(name = "DatosPersonales.findByCurp", query = "SELECT d FROM DatosPersonales d WHERE d.curp = :curp")})
public class DatosPersonales implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="agremiadoObj"))
    private Integer agremiado;
    
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    
    @Size(max = 100)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    
    @Size(max = 100)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    
    @Size(max = 100)
    @Column(name = "telefono")
    private String telefono;
    
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    
    @Size(max = 100)
    @Column(name = "escolaridad")
    private String escolaridad;
    
    @Size(max = 5)
    @Column(name = "edad")
    private String edad;
    
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;
    
    @Size(max = 100)
    @Column(name = "lugar_nacimiento")
    private String lugarNacimiento;
    
    @Size(max = 100)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    
    @Size(max = 100)
    @Column(name = "estado_civil")
    private String estadoCivil;
    
    @Size(max = 100)
    @Column(name = "regimen_matrimonial")
    private String regimenMatrimonial;
    
    @Size(max = 100)
    @Column(name = "hijos")
    private String hijos;
    
    @Size(max = 15)
    @Column(name = "rfc")
    private String rfc;
    
    @Size(max = 18)
    @Column(name = "curp")
    private String curp;
    
    @Size(max = 100)
    @Column(name = "sexo")
    private String Sexo;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Agremiado agremiadoObj;

    public DatosPersonales() {
    }

    public DatosPersonales(Integer agremiado) {
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

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getRegimenMatrimonial() {
        return regimenMatrimonial;
    }

    public void setRegimenMatrimonial(String regimenMatrimonial) {
        this.regimenMatrimonial = regimenMatrimonial;
    }

    public String getHijos() {
        return hijos;
    }

    public void setHijos(String hijos) {
        this.hijos = hijos;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public Agremiado getAgremiadoObj() {
        return agremiadoObj;
    }

    public void setAgremiadoObj(Agremiado agremiadoObj) {
        this.agremiadoObj = agremiadoObj;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agremiado != null ? agremiado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DatosPersonales)) {
            return false;
        }
        DatosPersonales other = (DatosPersonales) object;
        return !((this.agremiado == null && other.agremiado != null) || (this.agremiado != null && !this.agremiado.equals(other.agremiado)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.DatosPersonales[ agremiado=" + agremiado + " ]";
    }
    
}
