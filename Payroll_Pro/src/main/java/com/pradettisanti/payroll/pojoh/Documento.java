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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar la tabla documento
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d"),
    @NamedQuery(name = "Documento.findByIdDocumento", query = "SELECT d FROM Documento d WHERE d.documentoPK.idDocumento = :idDocumento"),
    @NamedQuery(name = "Documento.findByAgremiado", query = "SELECT d FROM Documento d WHERE d.documentoPK.agremiado = :agremiado"),
    @NamedQuery(name = "Documento.findByTipoDocumento", query = "SELECT d FROM Documento d WHERE d.documentoPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Documento.findByAgremiadoTipoDocumento", query = "SELECT d FROM Documento d WHERE d.documentoPK.agremiado = :agremiado and d.documentoPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Documento.findByNombreDocumento", query = "SELECT d FROM Documento d WHERE d.nombreDocumento = :nombreDocumento")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 270890L;

    /**
     * EmbeddedId DocumentoPK
     */
    @EmbeddedId
    protected DocumentoPK documentoPK;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_documento")
    private String urlDocumento;
    
    @Size(max = 200)
    @Column(name = "nombre_documento")
    private String nombreDocumento;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    @JoinColumn(name = "agremiado", foreignKey = @ForeignKey(name = "documento_idAgremiado"), referencedColumnName = "id_agremiado", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Agremiado agremiadoObj;
    
    @JoinColumn(name = "tipo_documento", foreignKey = @ForeignKey(name = "documento_idTipoDocumento"), referencedColumnName = "id_tipo_documento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumentoObj;

    public Documento() {
    }

    public Documento(DocumentoPK documentoPK) {
        this.documentoPK = documentoPK;
    }

    public Documento(DocumentoPK documentoPK, Date creado) {
        this.documentoPK = documentoPK;
        this.creado = creado;
    }

    public Documento(int idDocumento, int agremiado, int tipoDocumento) {
        this.documentoPK = new DocumentoPK(idDocumento, agremiado, tipoDocumento);
    }

    public DocumentoPK getDocumentoPK() {
        return documentoPK;
    }

    public void setDocumentoPK(DocumentoPK documentoPK) {
        this.documentoPK = documentoPK;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
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

    public Agremiado getAgremiadoObj() {
        return agremiadoObj;
    }

    public void setAgremiadoObj(Agremiado agremiadoObj) {
        this.agremiadoObj = agremiadoObj;
    }

    public TipoDocumento getTipoDocumentoObj() {
        return tipoDocumentoObj;
    }

    public void setTipoDocumentoObj(TipoDocumento tipoDocumentoObj) {
        this.tipoDocumentoObj = tipoDocumentoObj;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.documentoPK);
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
        final Documento other = (Documento) obj;
        if (!Objects.equals(this.documentoPK, other.documentoPK)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Documento{" + "documentoPK=" + documentoPK + ", urlDocumento=" + urlDocumento + ", nombreDocumento=" + nombreDocumento + ", creado=" + creado + ", modificado=" + modificado + ", agremiadoObj=" + agremiadoObj + ", tipoDocumentoObj=" + tipoDocumentoObj + '}';
    }

    
}
