/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entidad encargada de representar la tabla empresas_domicilio
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "empresas_domicilio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpresasDomicilio.findAll", query = "SELECT e FROM EmpresasDomicilio e")})
public class EmpresasDomicilio implements Serializable {

    @ManyToMany(fetch = FetchType.EAGER ,mappedBy = "empresasDomicilioList")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Contratista> contratistaList;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "empresasDomicilioList")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Cliente> clienteList;

    private static final long serialVersionUID = 270890L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresas_domicilio")
    private Integer idEmpresasDomicilio;
    
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
    @Column(name = "colonia")
    private String colonia;
    
    @Size(max = 100)
    @Column(name = "ciudad")
    private String ciudad;
    
    @Size(max = 100)
    @Column(name = "estado")
    private String estado;
    
    @JoinColumn(name = "tipo_domicilio", referencedColumnName = "id_tipo_domicilio", foreignKey = @ForeignKey(name = "eDomicilio_idTipoDomicilio"), insertable = false, updatable = false)
    @Column(name = "tipo_domicilio")
    private Short tipoDomicilioObj;

    public EmpresasDomicilio() {
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

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Short getTipoDomicilioObj() {
        return tipoDomicilioObj;
    }

    public void setTipoDomicilioObj(Short tipoDomicilioObj) {
        this.tipoDomicilioObj = tipoDomicilioObj;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @XmlTransient
    public List<Contratista> getContratistaList() {
        return contratistaList;
    }

    public void setContratistaList(List<Contratista> contratistaList) {
        this.contratistaList = contratistaList;
    }

    public Integer getIdEmpresasDomicilio() {
        return idEmpresasDomicilio;
    }

    public void setIdEmpresasDomicilio(Integer idEmpresasDomicilio) {
        this.idEmpresasDomicilio = idEmpresasDomicilio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.contratistaList);
        hash = 37 * hash + Objects.hashCode(this.clienteList);
        hash = 37 * hash + Objects.hashCode(this.idEmpresasDomicilio);
        hash = 37 * hash + Objects.hashCode(this.tipoDomicilioObj);
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
        final EmpresasDomicilio other = (EmpresasDomicilio) obj;
        if (!Objects.equals(this.idEmpresasDomicilio, other.idEmpresasDomicilio)) {
            return false;
        }
        if (!Objects.equals(this.contratistaList, other.contratistaList)) {
            return false;
        }
        if (!Objects.equals(this.clienteList, other.clienteList)) {
            return false;
        }
        return Objects.equals(this.tipoDomicilioObj, other.tipoDomicilioObj);
    }
    
    @Override
    public String toString() {
        return "EmpresasDomicilio{" + "contratistaList=" + contratistaList + ", clienteList=" + clienteList + ", idEmpresasDomicilio=" + idEmpresasDomicilio + ", calle=" + calle + ", numero=" + numero + ", codigoPostal=" + codigoPostal + ", colonia=" + colonia + ", ciudad=" + ciudad + ", estado=" + estado + ", tipoDomicilioObj=" + tipoDomicilioObj + '}';
    }
}
