/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar la tabla documentos_baja
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "documentos_baja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocumentosBaja.findAll", query = "SELECT d FROM DocumentosBaja d"),
    @NamedQuery(name = "DocumentosBaja.findByIdBajaTerminada", query = "SELECT d FROM DocumentosBaja d WHERE d.idBajaTerminada = :idBajaTerminada"),
    @NamedQuery(name = "DocumentosBaja.findBySolicitudBaja", query = "SELECT d FROM DocumentosBaja d WHERE d.solicitudBajaObj = :solicitudBaja"),
    @NamedQuery(name = "DocumentosBaja.findByTipoDocumento", query = "SELECT d FROM DocumentosBaja d WHERE d.tipoDocumentoObj = :idTipoDocumento and d.solicitudBajaObj.idSolicitudBaja = :idSolicitudBaja"),
    @NamedQuery(name = "DocumentosBaja.findByCargaGuardadaPra", query = "SELECT d FROM DocumentosBaja d WHERE d.cargaGuardadaPra = :cargaGuardadaPra"),
    @NamedQuery(name = "DocumentosBaja.findByCargaGuardadaUsu", query = "SELECT d FROM DocumentosBaja d WHERE d.cargaGuardadaUsu = :cargaGuardadaUsu")})
public class DocumentosBaja implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_baja_terminada")
    private int idBajaTerminada;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_documento_cargar_usu")
    private String urlDocumentoCargarUsu;
    
    @Column(name = "carga_guardada_usu")
    private Boolean cargaGuardadaUsu;
    
    @Size(max = 200)
    @Column(name = "nombre_documento_pra")
    private String nombreDocumento;
    
    @Size(max = 200)
    @Column(name = "nombre_documento_usu")
    private String nombreDocumentoUsu;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_documento_cargar_pra")
    private String urlDocumentoCargarPra;
    
    @Column(name = "carga_guardada_pra")
    private Boolean cargaGuardadaPra;
    
    @JoinColumn(name = "solicitud_baja", foreignKey = @ForeignKey(name = "dBaja_idSolicitudBaja"), referencedColumnName = "id_solicitud_baja", updatable = false)
    @ManyToOne(optional = false)
    private SolicitudBaja solicitudBajaObj;
    
    @JoinColumn(name = "tipo_documento", foreignKey = @ForeignKey(name = "dBaja_idTipoDocumento"), referencedColumnName = "id_tipo_documento", updatable = false)
    @ManyToOne(optional = false)
    private TipoDocumento tipoDocumentoObj;

    public DocumentosBaja() {
    }

    public String getUrlDocumentoCargarUsu() {
        return urlDocumentoCargarUsu;
    }

    public void setUrlDocumentoCargarUsu(String urlDocumentoCargarUsu) {
        this.urlDocumentoCargarUsu = urlDocumentoCargarUsu;
    }

    public Boolean getCargaGuardadaUsu() {
        return cargaGuardadaUsu;
    }

    public void setCargaGuardadaUsu(Boolean cargaGuardadaUsu) {
        this.cargaGuardadaUsu = cargaGuardadaUsu;
    }

    public String getUrlDocumentoCargarPra() {
        return urlDocumentoCargarPra;
    }

    public void setUrlDocumentoCargarPra(String urlDocumentoCargarPra) {
        this.urlDocumentoCargarPra = urlDocumentoCargarPra;
    }

    public Boolean getCargaGuardadaPra() {
        return cargaGuardadaPra;
    }

    public void setCargaGuardadaPra(Boolean cargaGuardadaPra) {
        this.cargaGuardadaPra = cargaGuardadaPra;
    }

    public SolicitudBaja getSolicitudBajaObj() {
        return solicitudBajaObj;
    }

    public void setSolicitudBajaObj(SolicitudBaja solicitudBajaObj) {
        this.solicitudBajaObj = solicitudBajaObj;
    }

    public TipoDocumento getTipoDocumentoObj() {
        return tipoDocumentoObj;
    }

    public void setTipoDocumentoObj(TipoDocumento tipoDocumentoObj) {
        this.tipoDocumentoObj = tipoDocumentoObj;
    }

    public int getIdBajaTerminada() {
        return idBajaTerminada;
    }

    public void setIdBajaTerminada(int idBajaTerminada) {
        this.idBajaTerminada = idBajaTerminada;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getNombreDocumentoUsu() {
        return nombreDocumentoUsu;
    }

    public void setNombreDocumentoUsu(String nombreDocumentoUsu) {
        this.nombreDocumentoUsu = nombreDocumentoUsu;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idBajaTerminada;
        hash = 53 * hash + Objects.hashCode(this.urlDocumentoCargarUsu);
        hash = 53 * hash + Objects.hashCode(this.cargaGuardadaUsu);
        hash = 53 * hash + Objects.hashCode(this.urlDocumentoCargarPra);
        hash = 53 * hash + Objects.hashCode(this.cargaGuardadaPra);
        hash = 53 * hash + Objects.hashCode(this.solicitudBajaObj);
        hash = 53 * hash + Objects.hashCode(this.tipoDocumentoObj);
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
        final DocumentosBaja other = (DocumentosBaja) obj;
        if (this.idBajaTerminada != other.idBajaTerminada) {
            return false;
        }
        if (!Objects.equals(this.urlDocumentoCargarUsu, other.urlDocumentoCargarUsu)) {
            return false;
        }
        if (!Objects.equals(this.urlDocumentoCargarPra, other.urlDocumentoCargarPra)) {
            return false;
        }
        if (!Objects.equals(this.cargaGuardadaUsu, other.cargaGuardadaUsu)) {
            return false;
        }
        if (!Objects.equals(this.cargaGuardadaPra, other.cargaGuardadaPra)) {
            return false;
        }
        if (!Objects.equals(this.solicitudBajaObj, other.solicitudBajaObj)) {
            return false;
        }
        if (!Objects.equals(this.tipoDocumentoObj, other.tipoDocumentoObj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DocumentosBaja{" + "idBajaTerminada=" + idBajaTerminada + ", urlDocumentoCargarUsua=" + urlDocumentoCargarUsu + ", cargaGuardadaUsu=" + cargaGuardadaUsu + ", urlDocumentoCargarPra=" + urlDocumentoCargarPra + ", cargaGuardadaPra=" + cargaGuardadaPra + ", solicitudBajaObj=" + solicitudBajaObj + ", tipoDocumentoObj=" + tipoDocumentoObj + '}';
    }

   
    
}
