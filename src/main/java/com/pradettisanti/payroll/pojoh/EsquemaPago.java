/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Pojo encargado de administrar los esquemas de contratación de los colaboradores
 * @author PabloSagoz pablo.samperio@it-seekers.com 
 * @version 2
 * @since 23/02/2018
 */
@Entity
@Table(name = "esquema_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsquemaPago.findAll", query = "SELECT e FROM EsquemaPago e")})
public class EsquemaPago implements Serializable{
    
    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idesquema_pago")
    private Short idEsquemaPago;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    
    @ManyToMany(mappedBy = "esquemaPagos", fetch=FetchType.EAGER)
    private List<TipoDocumento> tipoDocumentos;

    public EsquemaPago() {
    }

    public EsquemaPago(Short idEsquemaPago) {
        this.idEsquemaPago = idEsquemaPago;
    }

    public EsquemaPago(Short idEsquemaPago, String descripcion) {
        this.idEsquemaPago = idEsquemaPago;
        this.descripcion = descripcion;
    }

    public Short getIdEsquemaPago() {
        return idEsquemaPago;
    }

    public void setIdEsquemaPago(Short idEsquemaPago) {
        this.idEsquemaPago = idEsquemaPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idEsquemaPago);
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
        final EsquemaPago other = (EsquemaPago) obj;
        return Objects.equals(this.idEsquemaPago, other.idEsquemaPago);
    }

    public List<TipoDocumento> getTipoDocumentos() {
        return Collections.unmodifiableList(tipoDocumentos);
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    @Override
    public String toString() {
        return "EsquemaPago{" + "idEsquemaPago=" + idEsquemaPago + ", descripcion=" + descripcion + '}';
    }
}
