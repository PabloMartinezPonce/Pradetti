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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entidad encargada de representar la tabla agremiado
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "agremiado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agremiado.findAll", query = "SELECT a FROM Agremiado a"),
    @NamedQuery(name = "Agremiado.findbyParameters", query = "FROM Agremiado where Agremiado.datosLaborales.clienteObj.idCliente = :cliente and Agremiado.datosPersonales.nombre = :nombre"),
    @NamedQuery(name = "Agremiado.findByIdAgremiado", query = "FROM Agremiado WHERE idAgremiado = :idAgremiado")})

public class Agremiado implements Serializable {


    @OneToMany(mappedBy = "agremiadoObj", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SolicitudRenovacionContrato> solicitudRenovacionContratoList;

    @OneToMany(mappedBy = "agremiado", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Recibo> reciboList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agremiadoObj")
    private DatosPersonales datosPersonales;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agremiadoObj")
    private DatosLaborales datosLaborales;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agremiadoObj")
    private DatosDomicilio datosDomicilio;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agremiadoObj")
    private DatosBeneficiario datosBeneficiario;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "agremiadoObj")
    private DatosBancarios datosBancarios;
    
    @OneToMany(mappedBy = "agremiadoObj", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RelacionContrato> relacionContratoList;

    @OneToMany(mappedBy = "agremiadoObj", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Incidencia> incidenciaList;

    @OneToMany(mappedBy = "agremiadoObj", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Documento> documentoList;

    @OneToMany(mappedBy = "agremiadoObj", fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SolicitudBaja> solicitudBajaList;

    private static final long serialVersionUID = 270890L;

    /**
     * Id idAgremiado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_agremiado")
    private Integer idAgremiado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    @Size(max = 2500)
    @Column(name = "observaciones")
    private String observaciones;
    
    @Size(max = 35)
    @Column(name = "contrasena")
    private String contrasena;
    
    @JoinColumn(name = "status_agremiado", foreignKey = @ForeignKey(name = "agremiado_idStatusAgremiado"), referencedColumnName = "id_status_agremiado", insertable = false, updatable = false)
    @Column(name = "status_agremiado")
    private Short statusAgremiado;

    public Agremiado() {
    }

     public Agremiado(Integer idAgremiado) {
        this.idAgremiado = idAgremiado;
    }

    public Integer getIdAgremiado() {
        return idAgremiado;
    }

    public void setIdAgremiado(Integer idAgremiado) {
        this.idAgremiado = idAgremiado;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Short getStatusAgremiado() {
        return statusAgremiado;
    }

    public void setStatusAgremiado(Short statusAgremiado) {
        this.statusAgremiado = statusAgremiado;
    }

    @XmlTransient
    public List<SolicitudBaja> getSolicitudBajaList() {
        return solicitudBajaList;
    }

    public void setSolicitudBajaList(List<SolicitudBaja> solicitudBajaList) {
        this.solicitudBajaList = solicitudBajaList;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    @XmlTransient
    public List<Incidencia> getIncidenciaList() {
        return incidenciaList;
    }

    public void setIncidenciaList(List<Incidencia> incidenciaList) {
        this.incidenciaList = incidenciaList;
    }

    @XmlTransient
    public List<RelacionContrato> getRelacionContratoList() {
        return relacionContratoList;
    }

    public void setRelacionContratoList(List<RelacionContrato> relacionContratoList) {
        this.relacionContratoList = relacionContratoList;
    }

    public DatosBancarios getDatosBancarios() {
        return datosBancarios;
    }

    public void setDatosBancarios(DatosBancarios datosBancarios) {
        this.datosBancarios = datosBancarios;
    }

    public DatosBeneficiario getDatosBeneficiario() {
        return datosBeneficiario;
    }

    public void setDatosBeneficiario(DatosBeneficiario datosBeneficiario) {
        this.datosBeneficiario = datosBeneficiario;
    }

    public DatosDomicilio getDatosDomicilio() {
        return datosDomicilio;
    }

    public void setDatosDomicilio(DatosDomicilio datosDomicilio) {
        this.datosDomicilio = datosDomicilio;
    }

    public DatosLaborales getDatosLaborales() {
        return datosLaborales;
    }

    public void setDatosLaborales(DatosLaborales datosLaborales) {
        this.datosLaborales = datosLaborales;
    }

    public DatosPersonales getDatosPersonales() {
        return datosPersonales;
    }

    public void setDatosPersonales(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
    }

    @XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }

    @XmlTransient
    public List<SolicitudRenovacionContrato> getSolicitudRenovacionContratoList() {
        return solicitudRenovacionContratoList;
    }

    public void setSolicitudRenovacionContratoList(List<SolicitudRenovacionContrato> solicitudRenovacionContratoList) {
        this.solicitudRenovacionContratoList = solicitudRenovacionContratoList;
    }

   @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgremiado != null ? idAgremiado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Agremiado)) {
            return false;
        }
        Agremiado other = (Agremiado) object;
        if ((this.idAgremiado == null && other.idAgremiado != null) || (this.idAgremiado != null && !this.idAgremiado.equals(other.idAgremiado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.Agremiado[ idAgremiado=" + idAgremiado + " ]";
    }
    
}
