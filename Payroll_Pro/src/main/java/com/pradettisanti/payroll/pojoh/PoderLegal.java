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
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar la tabla poder_legal
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "poder_legal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PoderLegal.findAll", query = "SELECT p FROM PoderLegal p"),
    @NamedQuery(name = "PoderLegal.findByActaNotarial", query = "SELECT p FROM PoderLegal p WHERE p.actaNotarial = :actaNotarial"),
    @NamedQuery(name = "PoderLegal.findByRepresentanteLegal", query = "SELECT p FROM PoderLegal p WHERE p.representanteLegal = :representanteLegal"),
    @NamedQuery(name = "PoderLegal.findByRfc", query = "SELECT p FROM PoderLegal p WHERE p.rfc = :rfc"),
    @NamedQuery(name = "PoderLegal.findByInstrumento", query = "SELECT p FROM PoderLegal p WHERE p.instrumento = :instrumento"),
    @NamedQuery(name = "PoderLegal.findByVolumen", query = "SELECT p FROM PoderLegal p WHERE p.volumen = :volumen"),
    @NamedQuery(name = "PoderLegal.findByFechaVolumen", query = "SELECT p FROM PoderLegal p WHERE p.fechaVolumen = :fechaVolumen"),
    @NamedQuery(name = "PoderLegal.findByNumeroNotarial", query = "SELECT p FROM PoderLegal p WHERE p.numeroNotarial = :numeroNotarial"),
    @NamedQuery(name = "PoderLegal.findByNotario", query = "SELECT p FROM PoderLegal p WHERE p.notario = :notario"),
    @NamedQuery(name = "PoderLegal.findByDireccionNotario", query = "SELECT p FROM PoderLegal p WHERE p.direccionNotario = :direccionNotario")})
public class PoderLegal implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "acta_notarial")
    private Integer actaNotarial;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "representante_legal")
    private String representanteLegal;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "rfc")
    private String rfc;
    
    @Size(max = 100)
    @Column(name = "instrumento")
    private String instrumento;
    
    @Size(max = 100)
    @Column(name = "volumen")
    private String volumen;
    
    @Column(name = "fecha_volumen")
    @Temporal(TemporalType.DATE)
    private Date fechaVolumen;
    
    @Size(max = 100)
    @Column(name = "numero_notarial")
    private String numeroNotarial;
    
    @Size(max = 200)
    @Column(name = "notario")
    private String notario;
    
    @Size(max = 250)
    @Column(name = "direccion_notario")
    private String direccionNotario;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_documento")
    private String urlDocumento;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private ActaNotarial actaNotarialObj;

    public PoderLegal() {
    }

    public PoderLegal(Integer actaNotarial) {
        this.actaNotarial = actaNotarial;
    }

    public PoderLegal(Integer actaNotarial, String representanteLegal, String rfc) {
        this.actaNotarial = actaNotarial;
        this.representanteLegal = representanteLegal;
        this.rfc = rfc;
    }

    public Integer getActaNotarial() {
        return actaNotarial;
    }

    public void setActaNotarial(Integer actaNotarial) {
        this.actaNotarial = actaNotarial;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public Date getFechaVolumen() {
        return fechaVolumen;
    }

    public void setFechaVolumen(Date fechaVolumen) {
        this.fechaVolumen = fechaVolumen;
    }

    public String getNumeroNotarial() {
        return numeroNotarial;
    }

    public void setNumeroNotarial(String numeroNotarial) {
        this.numeroNotarial = numeroNotarial;
    }

    public String getNotario() {
        return notario;
    }

    public void setNotario(String notario) {
        this.notario = notario;
    }

    public String getDireccionNotario() {
        return direccionNotario;
    }

    public void setDireccionNotario(String direccionNotario) {
        this.direccionNotario = direccionNotario;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public ActaNotarial getActaNotarialObj() {
        return actaNotarialObj;
    }

    public void setActaNotarialObj(ActaNotarial actaNotarialObj) {
        this.actaNotarialObj = actaNotarialObj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actaNotarial != null ? actaNotarial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PoderLegal)) {
            return false;
        }
        PoderLegal other = (PoderLegal) object;
        return !((this.actaNotarial == null && other.actaNotarial != null) || (this.actaNotarial != null && !this.actaNotarial.equals(other.actaNotarial)));
    }

    @Override
    public String toString() {
        return "PoderLegal{" + "representanteLegal=" + representanteLegal + ", rfc=" + rfc + ", instrumento=" + instrumento + ", volumen=" + volumen + ", fechaVolumen=" + fechaVolumen + ", numeroNotarial=" + numeroNotarial + ", notario=" + notario + ", direccionNotario=" + direccionNotario + ", urlDocumento=" + urlDocumento + ", actaNotarialObj=" + actaNotarialObj.getIdActaNotarial() + '}';
    }
}
