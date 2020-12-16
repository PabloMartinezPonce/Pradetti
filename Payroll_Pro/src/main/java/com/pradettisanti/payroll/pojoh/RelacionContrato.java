/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar la tabla relacion_contrato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "relacion_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionContrato.findAll", query = "SELECT r FROM RelacionContrato r"),
    @NamedQuery(name = "RelacionContrato.findByIdContrato", query = "SELECT r FROM RelacionContrato r WHERE r.idContrato = :idContrato"),
    @NamedQuery(name = "RelacionContrato.findByAgremiado", query = "SELECT r FROM RelacionContrato r WHERE r.agremiadoObj = :agremiado"),
    @NamedQuery(name = "RelacionContrato.findByCliente", query = "SELECT r FROM RelacionContrato r WHERE r.clienteObj = :cliente"),
    @NamedQuery(name = "RelacionContrato.findLastByAgremmiado", query = "SELECT r FROM RelacionContrato r WHERE r.agremiadoObj = :agremiado ORDER BY r.idContrato DESC LIMIT 1"),
    @NamedQuery(name = "RelacionContrato.findByNombreContrato", query = "SELECT r FROM RelacionContrato r WHERE r.nombreContrato = :nombreContrato")})
public class RelacionContrato implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    /**
     * EmbeddedId RelacionContratoPK
     */
    @Basic(optional = false)
    @Id
    @Column(name = "id_contrato")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idContrato;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_documento")
    private String urlDocumento;
    
    @Size(max = 100)
    @Column(name = "nombre_contrato")
    private String nombreContrato;
    
    @Basic(optional = false)
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    @JoinColumn(name = "agremiado", nullable=false, foreignKey = @ForeignKey(name = "rContrato_idAgremiado"), referencedColumnName = "id_agremiado", updatable = false)
    @ManyToOne(optional = false)
    private Agremiado agremiadoObj;
    
    @JoinColumn(name = "cliente", nullable=false, foreignKey = @ForeignKey(name = "rContrato_idCliente"), referencedColumnName = "id_cliente", updatable = false)
    @ManyToOne(optional = false)
    private Cliente clienteObj;

    public RelacionContrato() {
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getNombreContrato() {
        return nombreContrato;
    }

    public void setNombreContrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idContrato);
        hash = 97 * hash + Objects.hashCode(this.urlDocumento);
        hash = 97 * hash + Objects.hashCode(this.nombreContrato);
        hash = 97 * hash + Objects.hashCode(this.creado);
        hash = 97 * hash + Objects.hashCode(this.modificado);
        return hash;
    }

    public Agremiado getAgremiadoObj() {
        return agremiadoObj;
    }

    public Cliente getClienteObj() {
        return clienteObj;
    }

    public void setAgremiadoObj(Agremiado agremiadoObj) {
        this.agremiadoObj = agremiadoObj;
    }

    public void setClienteObj(Cliente clienteObj) {
        this.clienteObj = clienteObj;
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
        final RelacionContrato other = (RelacionContrato) obj;
        if (!Objects.equals(this.idContrato, other.idContrato)) {
            return false;
        }
        if (!Objects.equals(this.agremiadoObj, other.agremiadoObj)) {
            return false;
        }
        if (!Objects.equals(this.clienteObj, other.clienteObj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RelacionContrato{" + "idContrato=" + idContrato + ", agremiadoObj=" + agremiadoObj + ", clienteObj=" + clienteObj + '}';
    }

   
}
