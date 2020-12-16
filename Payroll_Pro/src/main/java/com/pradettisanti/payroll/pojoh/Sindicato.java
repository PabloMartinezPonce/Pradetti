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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
 * Entidad encargada de representar la tabla sindicato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "sindicato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sindicato.findAll", query = "SELECT s FROM Sindicato s"),
    @NamedQuery(name = "Sindicato.findAllByNombreCortoDesc", query = "SELECT s FROM Sindicato s order by s.nombreCorto desc"),
    @NamedQuery(name = "Sindicato.findByIdSindicato", query = "SELECT s FROM Sindicato s WHERE s.idSindicato = :idSindicato")})
public class Sindicato implements Serializable {

    @OneToMany(mappedBy = "sindicatoObj", fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SolicitudRenovacionContrato> solicitudRenovacionContratoList;

    @OneToMany(mappedBy = "sindicato", fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Recibo> reciboList;

    @OneToMany(mappedBy = "sindicatoObj", fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<DatosLaborales> datosLaboralesList;

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sindicato")
    private Integer idSindicato;
    
    @Size(max = 500)
    @Column(name = "nombre_sindicato")
    private String nombreSindicato;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_logo_derecha")
    private String urlLogoDerecha;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_logo_izquierda")
    private String urlLogoIzquierda;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "percepciones")
    private String percepciones;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "deducciones")
    private String deducciones;
    
    @Size(max = 200)
    @Column(name = "nombre_corto")
    private String nombreCorto;
    
    @Size(max = 15)
    @Column(name = "rfc")
    private String rfc;
    
    @Column(name = "status")
    private Boolean status;
    
    @Basic(optional = false)
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;

    public Sindicato() {
    }

    public Sindicato(Integer idSindicato) {
        this.idSindicato = idSindicato;
    }

    public Sindicato(Integer idSindicato, Date creado) {
        this.idSindicato = idSindicato;
        this.creado = creado;
    }

    public Integer getIdSindicato() {
        return idSindicato;
    }

    public void setIdSindicato(Integer idSindicato) {
        this.idSindicato = idSindicato;
    }

    public String getNombreSindicato() {
        return nombreSindicato;
    }

    public void setNombreSindicato(String nombreSindicato) {
        this.nombreSindicato = nombreSindicato;
    }

    public String getUrlLogoDerecha() {
        return urlLogoDerecha;
    }

    public void setUrlLogoDerecha(String urlLogoDerecha) {
        this.urlLogoDerecha = urlLogoDerecha;
    }

    public String getUrlLogoIzquierda() {
        return urlLogoIzquierda;
    }

    public void setUrlLogoIzquierda(String urlLogoIzquierda) {
        this.urlLogoIzquierda = urlLogoIzquierda;
    }

    public String getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(String percepciones) {
        this.percepciones = percepciones;
    }

    public String getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(String deducciones) {
        this.deducciones = deducciones;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
        int hash = 0;
        hash += (idSindicato != null ? idSindicato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sindicato)) {
            return false;
        }
        Sindicato other = (Sindicato) object;
        return !((this.idSindicato == null && other.idSindicato != null) || (this.idSindicato != null && !this.idSindicato.equals(other.idSindicato)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.Sindicato[ idSindicato=" + idSindicato + " ]";
    }

    @XmlTransient
    public List<DatosLaborales> getDatosLaboralesList() {
        return datosLaboralesList;
    }

    public void setDatosLaboralesList(List<DatosLaborales> datosLaboralesList) {
        this.datosLaboralesList = datosLaboralesList;
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
    
}
