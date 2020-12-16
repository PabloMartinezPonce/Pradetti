/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entidad encargada de represnetar la tabla tipo_documento
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByIdTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "TipoDocumento.findByModulo", query = "SELECT t FROM TipoDocumento t WHERE t.moduloObj = :modulo"),
    @NamedQuery(name = "TipoDocumento.findByNombreDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.nombreDocumento = :nombreDocumento"),
    @NamedQuery(name = "TipoDocumento.findByStatus", query = "SELECT t FROM TipoDocumento t WHERE t.status = :status"),
    @NamedQuery(name = "TipoDocumento.findByObligatorio", query = "SELECT t FROM TipoDocumento t WHERE t.obligatorio = :obligatorio"),
    @NamedQuery(name = "TipoDocumento.findByCreado", query = "SELECT t FROM TipoDocumento t WHERE t.creado = :creado"),
    @NamedQuery(name = "TipoDocumento.findByidMdulo", query = "SELECT t.idTipoDocumento FROM TipoDocumento t WHERE t.moduloObj = :modulo AND t.obligatorio = true"),
    @NamedQuery(name = "TipoDocumento.findByModificado", query = "SELECT t FROM TipoDocumento t WHERE t.modificado = :modificado")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id_tipo_documento")  
    private Integer idTipoDocumento;
    
    @Size(max = 100)
    @Column(name = "nombre_documento")
    private String nombreDocumento;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "obligatorio")
    private boolean obligatorio;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    @Column(name = "modulo")
    @JoinColumn(name = "modulo", foreignKey = @ForeignKey(name = "tDocumento_idModulo"), referencedColumnName = "id_modulo", insertable = false, updatable = false)
    private Short moduloObj;
    
    /**
     * Mapea la relacion de la tabla catalogo_documental y le asigna una variable de tipo Integer
     */
    @JoinColumn(name = "catalogo_documental", foreignKey = @ForeignKey(name = "tDocumento_idCatalogoDocumental"), referencedColumnName = "id_catalogo")
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @NotNull
    private CatalogoDocumental catalogoDocumentalObj;
    
    @JoinTable(name="relacion_esquema_tipo_documentos",joinColumns = {
        @JoinColumn(name = "tipo_documento", foreignKey = @ForeignKey(name = "rEsquemaPagoTipoDocumento_idTipoDocumento"))},inverseJoinColumns = {
        @JoinColumn(name="esquema",foreignKey = @ForeignKey(name="rEquemaPagoTipoDocumento_idEsquemaPago"))})
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EsquemaPago> esquemaPagos;

    public TipoDocumento() {
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
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

    public Short getModuloObj() {
        return moduloObj;
    }

    public void setModuloObj(Short moduloObj) {
        this.moduloObj = moduloObj;
    }
    
     public List<EsquemaPago> getEsquemaPagos() {
        return esquemaPagos;
    }

    public void setEsquemaPagos(List<EsquemaPago> esquemaPagos) {
        this.esquemaPagos = esquemaPagos;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idTipoDocumento);
        hash = 79 * hash + Objects.hashCode(this.nombreDocumento);
        hash = 79 * hash + (this.status ? 1 : 0);
        hash = 79 * hash + (this.obligatorio ? 1 : 0);
        hash = 79 * hash + Objects.hashCode(this.creado);
        hash = 79 * hash + Objects.hashCode(this.modificado);
        hash = 79 * hash + Objects.hashCode(this.moduloObj);
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
        final TipoDocumento other = (TipoDocumento) obj;
        if (this.status != other.status) {
            return false;
        }
        if (this.obligatorio != other.obligatorio) {
            return false;
        }
        if (!Objects.equals(this.nombreDocumento, other.nombreDocumento)) {
            return false;
        }
        if (!Objects.equals(this.idTipoDocumento, other.idTipoDocumento)) {
            return false;
        }
        if (!Objects.equals(this.creado, other.creado)) {
            return false;
        }
        if (!Objects.equals(this.modificado, other.modificado)) {
            return false;
        }
        if (!Objects.equals(this.moduloObj, other.moduloObj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoDocumento{" + "idTipoDocumento=" + idTipoDocumento + ", nombreDocumento=" + nombreDocumento + ", status=" + status + ", obligatorio=" + obligatorio + ", creado=" + creado + ", modificado=" + modificado + ", modulo=" + moduloObj+ ", esquema=" + esquemaPagos+'}';
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public CatalogoDocumental getCatalogoDocumentalObj() {
        return catalogoDocumentalObj;
    }

    public void setCatalogoDocumentalObj(CatalogoDocumental catalogoDocumentalObj) {
        this.catalogoDocumentalObj = catalogoDocumentalObj;
    }

}
