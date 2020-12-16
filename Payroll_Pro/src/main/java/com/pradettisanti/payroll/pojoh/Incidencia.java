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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar la tabla incidencia 
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "incidencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencia.findAll", query = "SELECT i FROM Incidencia i"),
    @NamedQuery(name = "Incidencia.findByIdIncidencia", query = "SELECT i FROM Incidencia i WHERE i.idIncidencia = :idIncidencia"),
    @NamedQuery(name = "Incidencia.findByAgremiado", query = "SELECT i FROM Incidencia i WHERE i.agremiadoObj.idAgremiado = :agremiado AND i.fechaRegistro BETWEEN  :fechaRegistroDesde AND  :fechaRegistroHasta"),
    @NamedQuery(name = "Incidencia.findByClienteFechaRegistro", query = "SELECT i FROM Incidencia i WHERE i.clienteObj = :cliente AND i.fechaRegistro BETWEEN  :fechaRegistroDesde AND  :fechaRegistroHasta"),
    @NamedQuery(name = "Incidencia.findByClienteFechaIncidencia", query = "SELECT i FROM Incidencia i WHERE i.clienteObj = :cliente AND i.fechaIncidencia BETWEEN  :fechaIncidenciaDesde AND  :fechaIncidenciaHasta"),
    @NamedQuery(name = "Incidencia.findByCAIT", query = "SELECT i FROM Incidencia i WHERE i.clienteObj.idCliente = :cliente AND i.agremiadoObj.idAgremiado = :agremiado AND  i.idIncidencia = :incidencia AND i.tipoIncidenciaObj = :tipo"),
    @NamedQuery(name = "Incidencia.findByClienteEstadoFechaIncidencia", query = "SELECT i FROM Incidencia i WHERE i.clienteObj = :cliente AND i.status = :estado AND i.fechaIncidencia BETWEEN  :fechaIncidenciaDesde AND  :fechaIncidenciaHasta"),
    @NamedQuery(name = "Incidencia.findByClienteAndStatus", query = "SELECT i FROM Incidencia i WHERE i.clienteObj.idCliente = :cliente AND i.status = :estado"),
    @NamedQuery(name = "Incidencia.findByTipoIncidencia", query = "SELECT i FROM Incidencia i WHERE i.tipoIncidenciaObj = :tipoIncidencia")})
public class Incidencia implements Serializable {


    private static final long serialVersionUID = 270890L;

    @Basic(optional = false)
    @Id
    @Column(name = "id_incidencia")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idIncidencia;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_incidencia")
    @Temporal(TemporalType.DATE)
    private Date fechaIncidencia;
    
    @Basic(optional = false)
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
    @Size(max = 255)
    @Column(name = "comentarios")
    private String comentarios;
    
    @Size(max = 30)
    @Column(name = "cantidad")
    private String cantidad;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    
    @JoinColumn(name = "agremiado", foreignKey = @ForeignKey(name = "incidencia_idAgremiado"), referencedColumnName = "id_agremiado", updatable = false)
    @ManyToOne(optional = false)
    private Agremiado agremiadoObj;
    
    @JoinColumn(name = "tipo_incidencia", foreignKey = @ForeignKey(name = "incidencia_idTipoIncidencia"), referencedColumnName = "id_tipo_incidencia")
    @ManyToOne(optional = false)
    private TipoIncidencia tipoIncidenciaObj;
    
    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "incidencia_idCliente"), referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente clienteObj;

    public Incidencia() {
    }

    public Incidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public Incidencia(int idIncidencia, Date fechaIncidencia, Date fechaRegistro, boolean status) {
        this.idIncidencia = idIncidencia;
        this.fechaIncidencia = fechaIncidencia;
        this.fechaRegistro = fechaRegistro;
        this.status = status;
    }

    public Date getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(Date fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Agremiado getAgremiadoObj() {
        return agremiadoObj;
    }

    public void setAgremiadoObj(Agremiado agremiadoObj) {
        this.agremiadoObj = agremiadoObj;
    }

    public TipoIncidencia getTipoIncidenciaObj() {
        return tipoIncidenciaObj;
    }

    public void setTipoIncidenciaObj(TipoIncidencia tipoIncidenciaObj) {
        this.tipoIncidenciaObj = tipoIncidenciaObj;
    }

     public Cliente getClienteObj() {
        return clienteObj;
    }

    public void setClienteObj(Cliente clienteObj) {
        this.clienteObj = clienteObj;
    }

    public int getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(int idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.idIncidencia;
        hash = 47 * hash + Objects.hashCode(this.fechaIncidencia);
        hash = 47 * hash + Objects.hashCode(this.fechaRegistro);
        hash = 47 * hash + Objects.hashCode(this.fechaModificacion);
        hash = 47 * hash + Objects.hashCode(this.comentarios);
        hash = 47 * hash + Objects.hashCode(this.cantidad);
        hash = 47 * hash + (this.status ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.agremiadoObj);
        hash = 47 * hash + Objects.hashCode(this.tipoIncidenciaObj);
        hash = 47 * hash + Objects.hashCode(this.clienteObj);
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
        final Incidencia other = (Incidencia) obj;
        if (this.idIncidencia != other.idIncidencia) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        if (!Objects.equals(this.fechaIncidencia, other.fechaIncidencia)) {
            return false;
        }
        if (!Objects.equals(this.fechaRegistro, other.fechaRegistro)) {
            return false;
        }
        if (!Objects.equals(this.fechaModificacion, other.fechaModificacion)) {
            return false;
        }
        if (!Objects.equals(this.agremiadoObj, other.agremiadoObj)) {
            return false;
        }
        if (!Objects.equals(this.tipoIncidenciaObj, other.tipoIncidenciaObj)) {
            return false;
        }
        return Objects.equals(this.clienteObj, other.clienteObj);
    }

    @Override
    public String toString() {
        return "Incidencia{" + "idIncidencia=" + idIncidencia + ", fechaIncidencia=" + fechaIncidencia + ", fechaRegistro=" + fechaRegistro + ", fechaModificacion=" + fechaModificacion + ", comentarios=" + comentarios + ", cantidad=" + cantidad + ", status=" + status + ", agremiadoObj=" + agremiadoObj + ", tipoIncidenciaObj=" + tipoIncidenciaObj + ", clienteObj=" + clienteObj + '}';
    }

    
    
}
