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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de represerta la tabla solicitud_baja
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "solicitud_baja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudBaja.findAll", query = "SELECT s FROM SolicitudBaja s"),
    @NamedQuery(name = "SolicitudBaja.findByIdSolicitudBaja", query = "SELECT s FROM SolicitudBaja s WHERE s.idSolicitudBaja = :idSolicitudBaja"),
    @NamedQuery(name = "SolicitudBaja.findByAgremiado", query = "SELECT s FROM SolicitudBaja s WHERE s.agremiadoObj = :agremiado"),
    @NamedQuery(name = "SolicitudBaja.findLastDateByAgremiado", query = "SELECT s.fechaBaja FROM SolicitudBaja s WHERE s.agremiadoObj = :agremiado"),
    @NamedQuery(name = "SolicitudBaja.findLastByAgremmiado", query = "SELECT s FROM SolicitudBaja s WHERE s.agremiadoObj = :agremiado ORDER BY s.idSolicitudBaja DESC LIMIT 1"),
    @NamedQuery(name = "SolicitudBaja.findByCliente", query = "SELECT s FROM SolicitudBaja s WHERE s.clienteObj = :cliente")})
public class SolicitudBaja implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    /**
     * EmbeddedId SolicitudBajaPK
     */
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_solicitud_baja",insertable=false,updatable=false)
    private int idSolicitudBaja;
    
    @Column(name = "detalles")
    private String motivo;
    
    @Column(name = "fecha_baja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    
    @Column(name = "observaciones")
    private String observaciones;
    
    @Column(name = "motivo_de_rechazo")
    private String motivoDeRechazo;
    
    @Size(max = 2)
    @Column(name = "sueldo")
    private String sueldo;
    
    @JoinColumn(name = "agremiado", foreignKey = @ForeignKey(name = "sBaja_IdAgremiado"), referencedColumnName = "id_agremiado", updatable = false)
    @ManyToOne(optional = false)
    private Agremiado agremiadoObj;
    
    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "sBaja_idCliente"), referencedColumnName = "id_cliente", updatable = false)
    @ManyToOne(optional = false)
    private Cliente clienteObj;
    
    @JoinColumn(name = "motivo_baja", foreignKey = @ForeignKey(name = "sBaja_idMotivoBaja"), referencedColumnName = "id_motivo_baja", updatable = false)
    @ManyToOne(optional = false)
    private MotivoBaja motivoBaja;
    
    @JoinColumn(name = "tipo_finiquito", foreignKey = @ForeignKey(name = "sBaja_idTipoFiniquito"), referencedColumnName = "id_tipo_finiquito", updatable = false)
    @ManyToOne(optional = false)
    private TipoFiniquito tipoFiniquitoObj;

    public SolicitudBaja() {
    }

    public String getMotivo() {
        return motivo;
    }

    public void setDMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public Agremiado getAgremiadoObj() {
        return agremiadoObj;
    }

    public void setAgremiadoObj(Agremiado agremiadoObj) {
        this.agremiadoObj = agremiadoObj;
    }

    public Cliente getClienteObj() {
        return clienteObj;
    }

    public void setClienteObj(Cliente clienteObj) {
        this.clienteObj = clienteObj;
    }

    public MotivoBaja getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(MotivoBaja motivoBaja) {
        this.motivoBaja = motivoBaja;
    }

    public TipoFiniquito getTipoFiniquitoObj() {
        return tipoFiniquitoObj;
    }

    public void setTipoFiniquitoObj(TipoFiniquito tipoFiniquitoObj) {
        this.tipoFiniquitoObj = tipoFiniquitoObj;
    }

    public String getMotivoDeRechazo() {
        return motivoDeRechazo;
    }

    public void setMotivoDeRechazo(String motivoDeRechazo) {
        this.motivoDeRechazo = motivoDeRechazo;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idSolicitudBaja;
        hash = 37 * hash + Objects.hashCode(this.motivo);
        hash = 37 * hash + Objects.hashCode(this.fechaBaja);
        hash = 37 * hash + Objects.hashCode(this.observaciones);
        hash = 37 * hash + Objects.hashCode(this.sueldo);
        hash = 37 * hash + Objects.hashCode(this.agremiadoObj);
        hash = 37 * hash + Objects.hashCode(this.clienteObj);
        hash = 37 * hash + Objects.hashCode(this.motivoBaja);
        hash = 37 * hash + Objects.hashCode(this.tipoFiniquitoObj);
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
        final SolicitudBaja other = (SolicitudBaja) obj;
        if (this.idSolicitudBaja != other.idSolicitudBaja) {
            return false;
        }
        if (!Objects.equals(this.motivo, other.motivo)) {
            return false;
        }
        if (!Objects.equals(this.observaciones, other.observaciones)) {
            return false;
        }
        if (!Objects.equals(this.sueldo, other.sueldo)) {
            return false;
        }
        if (!Objects.equals(this.fechaBaja, other.fechaBaja)) {
            return false;
        }
        if (!Objects.equals(this.agremiadoObj, other.agremiadoObj)) {
            return false;
        }
        if (!Objects.equals(this.clienteObj, other.clienteObj)) {
            return false;
        }
        if (!Objects.equals(this.motivoBaja, other.motivoBaja)) {
            return false;
        }
        if (!Objects.equals(this.tipoFiniquitoObj, other.tipoFiniquitoObj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SolicitudBaja{" + "idSolicitudBaja=" + idSolicitudBaja + ", motivo=" + motivo + ", fechaBaja=" + fechaBaja + ", observaciones=" + observaciones + ", sueldo=" + sueldo + ", agremiadoObj=" + agremiadoObj + ", clienteObj=" + clienteObj + ", motivoBaja=" + motivoBaja + ", tipoFiniquitoObj=" + tipoFiniquitoObj + '}';
    }

    public int getIdSolicitudBaja() {
        return idSolicitudBaja;
    }

    public void setIdSolicitudBaja(int idSolicitudBaja) {
        this.idSolicitudBaja = idSolicitudBaja;
    }
    
}
