/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad encargada de representar la tabla contrato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
                                    @NamedQuery(name = "Contrato.findByIdContrato",query = "SELECT c FROM Contrato c WHERE c.idContrato = :idContrato")})
public class Contrato implements Serializable {

    @ManyToMany(mappedBy = "contratoList", fetch=FetchType.EAGER)
    private List<Contratista> contratistaList;

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contrato")
    private Integer idContrato;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100) // se cambia el max de nombre por 100
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "contrato")
    private String contrato;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    
    @Basic(optional = false)
    @Column(name = "contrato_de_colaborador")
    private boolean contratoDeColaborador;

    public Contrato() {
    }

    public Contrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Contrato(Integer idContrato, String nombre, Date creado, boolean activo) {
        this.idContrato = idContrato;
        this.nombre = nombre;
        this.creado = creado;
        this.activo = activo;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getContratoDeColaborador() {
        return contratoDeColaborador;
    }

    public void setContratoDeColaborador(boolean contratoDeColaborador) {
        this.contratoDeColaborador = contratoDeColaborador;
    }

    @XmlTransient
    public List<Contratista> getContratistaList() {
        return contratistaList;
    }

    public void setContratistaList(List<Contratista> contratistaList) {
        this.contratistaList = contratistaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        return !((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.Contrato[ idContrato=" + idContrato + " ]";
    }
}
