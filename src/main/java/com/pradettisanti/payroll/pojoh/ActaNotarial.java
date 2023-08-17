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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad encargada de representar la tabla acta_notarial
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "acta_notarial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActaNotarial.findAll", query = "SELECT a FROM ActaNotarial a "),
    @NamedQuery(name = "ActaNotarial.findByIdActaNotarial", query = "SELECT a FROM ActaNotarial a WHERE a.idActaNotarial = :idActaNotarial "),
    @NamedQuery(name = "ActaNotarial.findByAttribs", query = "SELECT a FROM ActaNotarial a WHERE a.tipoActa = :tipoActa AND a.instrumento = :instrumento " +
                                                    "AND a.volumen = :volumen AND a.fechaVolumen = :fechaVolumen AND a.numeroNotarial = :numeroNotarial AND a.notario = :notario " +
                                                    "AND a.direccionNotario = :direccionNotario AND a.tienePoderLegal = :tienePoderLegal")})
public class ActaNotarial implements Serializable {

     @ManyToMany(mappedBy = "actaNotarialList", fetch=FetchType.EAGER)
    private List<Cliente> clienteList;

    
    @ManyToMany(mappedBy = "actaNotarialList", fetch=FetchType.EAGER)
    private List<Contratista> contratistaList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "actaNotarialObj")
    private PoderLegal poderLegal;

    private static final long serialVersionUID = 270890L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "id_acta_notarial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idActaNotarial;
    
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
    
    @Size(max = 100)
    @Column(name = "notario")
    private String notario;
    
    @Size(max = 100)
    @Column(name = "direccion_notario")
    private String direccionNotario;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_documento")
    private String urlDocumento;
    
    @Column(name = "tiene_poder_legal")
    private Boolean tienePoderLegal;
    
    @JoinColumn(name = "tipo_acta", referencedColumnName = "id_tipo_acta", foreignKey = @ForeignKey(name = "aNotarial_idTipoActa"), insertable = false, updatable = false)
    @Column(name = "tipo_acta")
    private Short tipoActa;

    public ActaNotarial() {
    }

    public ActaNotarial(Integer idActaNotarial) {
        this.idActaNotarial = idActaNotarial;
    }

    public Integer getIdActaNotarial() {
        return idActaNotarial;
    }

    public void setIdActaNotarial(Integer idActaNotarial) {
        this.idActaNotarial = idActaNotarial;
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

    public Boolean getTienePoderLegal() {
        return tienePoderLegal;
    }

    public void setTienePoderLegal(Boolean tienePoderLegal) {
        this.tienePoderLegal = tienePoderLegal;
    }

    public Short getTipoActa() {
        return tipoActa;
    }

    public void setTipoActa(Short tipoActa) {
        this.tipoActa = tipoActa;
    }

    public PoderLegal getPoderLegal() {
        return poderLegal;
    }

    public void setPoderLegal(PoderLegal poderLegal) {
        this.poderLegal = poderLegal;
    }

    @XmlTransient
    public List<Contratista> getContratistaList() {
        return contratistaList;
    }

    public void setContratistaList(List<Contratista> contratistaList) {
        this.contratistaList = contratistaList;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActaNotarial != null ? idActaNotarial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ActaNotarial)) {
            return false;
        }
        ActaNotarial other = (ActaNotarial) object;
        return !((this.idActaNotarial == null && other.idActaNotarial != null) || (this.idActaNotarial != null && !this.idActaNotarial.equals(other.idActaNotarial)));
    }

    @Override
    public String toString() {
        return "ActaNotarial{" + "clienteList=" + clienteList + ", contratistaList=" + contratistaList + ", poderLegal=" + poderLegal + ", idActaNotarial=" + idActaNotarial + ", instrumento=" + instrumento + ", volumen=" + volumen + ", fechaVolumen=" + fechaVolumen + ", numeroNotarial=" + numeroNotarial + ", notario=" + notario + ", direccionNotario=" + direccionNotario + ", urlDocumento=" + urlDocumento + ", tienePoderLegal=" + tienePoderLegal + ", tipoActa=" + tipoActa + '}';
    }

}
