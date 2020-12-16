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
 * Entidad encargada de representar la tabla solicitud_renovacion_contrato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "solicitud_renovacion_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudRenovacionContrato.findAll", query = "SELECT s FROM SolicitudRenovacionContrato s"),
    @NamedQuery(name = "SolicitudRenovacionContrato.findByIdSolicitudRenovacion", query = "SELECT s FROM SolicitudRenovacionContrato s WHERE s.idSolicitudRenovacion = :idSolicitudRenovacion"),
    @NamedQuery(name = "SolicitudRenovacionContrato.findByAgremiado", query = "SELECT s FROM SolicitudRenovacionContrato s WHERE s.agremiadoObj.idAgremiado = :agremiado"),
    @NamedQuery(name = "SolicitudRenovacionContrato.findByCliente", query = "SELECT s FROM SolicitudRenovacionContrato s WHERE s.clienteObj.idCliente = :cliente"),
    @NamedQuery(name = "SolicitudRenovacionContrato.findBySindicato", query = "SELECT s FROM SolicitudRenovacionContrato s WHERE s.sindicatoObj.idSindicato = :sindicato"),
    @NamedQuery(name = "SolicitudRenovacionContrato.findLastByAgremmiado", query = "SELECT s FROM SolicitudRenovacionContrato s WHERE s.agremiadoObj = :agremiado ORDER BY s.idSolicitudRenovacion DESC LIMIT 1"),
    @NamedQuery(name = "SolicitudRenovacionContrato.findByTipoContrato", query = "SELECT s FROM SolicitudRenovacionContrato s WHERE s.tipoContratoObj.idTipoContrato = :tipoContrato")})
public class SolicitudRenovacionContrato implements Serializable {

    private static final long serialVersionUID = 270890L;

    @Basic(optional = false)
    @Id
    @Column(name = "id_solicitud_renovacion")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idSolicitudRenovacion;
    
    @Column(name = "periodo")
    private Integer periodo;
    
    @Size(max = 255)
    @Column(name = "salarios_imss")
    private String salariosImss;
    
    @Size(max = 255)
    @Column(name = "salario_diario")
    private String salarioDiario;
    
    @Size(max = 255)
    @Column(name = "salario_diario_integrado")
    private String salarioDiarioIntegrado;
    
    @Size(max = 255)
    @Column(name = "sueldo_mensual")
    private String sueldoMensual;
    
    @Column(name = "fecha_aplicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAplicacion;
    
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    
    @Size(max = 300)
    @Column(name = "motivo_rechazo")
    private String motivoRechazo;
    
    @Size(max = 800)
    @Column(name = "url_contrato_renovado")
    private String urlContratoRenovado;
    
    @JoinColumn(name = "agremiado", foreignKey = @ForeignKey(name = "sRenovacionContrato_idAgremiado"), referencedColumnName = "id_agremiado")
    @ManyToOne(optional = false)
    private Agremiado agremiadoObj;
    
    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "sRenovacionContrato_idCliente"), referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente clienteObj;
    
    @JoinColumn(name = "sindicato", foreignKey = @ForeignKey(name = "sRenovacionContrato_idSindicato"), referencedColumnName = "id_sindicato")
    @ManyToOne(optional = true)
    private Sindicato sindicatoObj;
    
    @JoinColumn(name = "tipo_contrato", foreignKey = @ForeignKey(name = "sRenovacionContrato_idTipoContrato"), referencedColumnName = "id_tipo_contrato")
    @ManyToOne(optional = false)
    private TipoContrato tipoContratoObj;
    
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDeSolicitud;
    
    @Size(max = 250)
    @Column(name = "periodo_contrato")
    private String periodoContrato;
    
    @JoinColumn(name = "sup", foreignKey = @ForeignKey(name = "sRenovacionContrato_idSup"), referencedColumnName = "id_sup")
    @ManyToOne(optional = true)
    private SalarioUnicoProfesional SUPObj;
    
    @Column(name = "modificacion_salario")
    private Boolean modificacionSalario;

    public String getPeriodoContrato() {
        return periodoContrato;
    }

    public void setPeriodoContrato(String periodoContrato) {
        this.periodoContrato = periodoContrato;
    }
    
    public SolicitudRenovacionContrato() {
    }

    public Date getFechaDeSolicitud() {
        return fechaDeSolicitud;
    }

    public void setFechaDeSolicitud(Date fechaDeSolicitud) {
        this.fechaDeSolicitud = fechaDeSolicitud;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getSalariosImss() {
        return salariosImss;
    }

    public void setSalariosImss(String salariosImss) {
        this.salariosImss = salariosImss;
    }

    public String getSalarioDiario() {
        return salarioDiario;
    }

    public void setSalarioDiario(String salarioDiario) {
        this.salarioDiario = salarioDiario;
    }

    public String getSalarioDiarioIntegrado() {
        return salarioDiarioIntegrado;
    }

    public void setSalarioDiarioIntegrado(String salarioDiarioIntegrado) {
        this.salarioDiarioIntegrado = salarioDiarioIntegrado;
    }

    public String getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(String sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
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

    public Sindicato getSindicatoObj() {
        return sindicatoObj;
    }

    public void setSindicatoObj(Sindicato sindicatoObj) {
        this.sindicatoObj = sindicatoObj;
    }

    public TipoContrato getTipoContratoObj() {
        return tipoContratoObj;
    }

    public void setTipoContratoObj(TipoContrato tipoContratoObj) {
        this.tipoContratoObj = tipoContratoObj;
    }

    public int getIdSolicitudRenovacion() {
        return idSolicitudRenovacion;
    }

    public void setIdSolicitudRenovacion(int idSolicitudRenovacion) {
        this.idSolicitudRenovacion = idSolicitudRenovacion;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public String getUrlContratoRenovado() {
        return urlContratoRenovado;
    }

    public void setUrlContratoRenovado(String urlContratoRenovado) {
        this.urlContratoRenovado = urlContratoRenovado;
    }

    public SalarioUnicoProfesional getSUPObj() {
        return SUPObj;
    }

    public void setSUPObj(SalarioUnicoProfesional SUPObj) {
        this.SUPObj = SUPObj;
    }

    public Boolean getModificacionSalario() {
        return modificacionSalario;
    }

    public void setModificacionSalario(Boolean modificacionSalario) {
        this.modificacionSalario = modificacionSalario;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idSolicitudRenovacion;
        hash = 89 * hash + Objects.hashCode(this.periodo);
        hash = 89 * hash + Objects.hashCode(this.salariosImss);
        hash = 89 * hash + Objects.hashCode(this.salarioDiario);
        hash = 89 * hash + Objects.hashCode(this.salarioDiarioIntegrado);
        hash = 89 * hash + Objects.hashCode(this.sueldoMensual);
        hash = 89 * hash + Objects.hashCode(this.fechaAplicacion);
        hash = 89 * hash + Objects.hashCode(this.fechaInicial);
        hash = 89 * hash + Objects.hashCode(this.fechaFinal);
        hash = 89 * hash + Objects.hashCode(this.agremiadoObj);
        hash = 89 * hash + Objects.hashCode(this.clienteObj);
        hash = 89 * hash + Objects.hashCode(this.sindicatoObj);
        hash = 89 * hash + Objects.hashCode(this.tipoContratoObj);
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
        final SolicitudRenovacionContrato other = (SolicitudRenovacionContrato) obj;
        if (this.idSolicitudRenovacion != other.idSolicitudRenovacion) {
            return false;
        }
        if (!Objects.equals(this.salariosImss, other.salariosImss)) {
            return false;
        }
        if (!Objects.equals(this.salarioDiario, other.salarioDiario)) {
            return false;
        }
        if (!Objects.equals(this.salarioDiarioIntegrado, other.salarioDiarioIntegrado)) {
            return false;
        }
        if (!Objects.equals(this.sueldoMensual, other.sueldoMensual)) {
            return false;
        }
        if (!Objects.equals(this.periodo, other.periodo)) {
            return false;
        }
        if (!Objects.equals(this.fechaAplicacion, other.fechaAplicacion)) {
            return false;
        }
        if (!Objects.equals(this.fechaInicial, other.fechaInicial)) {
            return false;
        }
        if (!Objects.equals(this.fechaFinal, other.fechaFinal)) {
            return false;
        }
        if (!Objects.equals(this.agremiadoObj, other.agremiadoObj)) {
            return false;
        }
        if (!Objects.equals(this.clienteObj, other.clienteObj)) {
            return false;
        }
        if (!Objects.equals(this.sindicatoObj, other.sindicatoObj)) {
            return false;
        }
        if (!Objects.equals(this.tipoContratoObj, other.tipoContratoObj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SolicitudRenovacionContrato{" + "idSolicitudRenovacion=" + idSolicitudRenovacion + ", periodo=" + periodo + ", salariosImss=" + salariosImss + ", salarioDiario=" + salarioDiario + ", salarioDiarioIntegrado=" + salarioDiarioIntegrado + ", sueldoMensual=" + sueldoMensual + ", fechaAplicacion=" + fechaAplicacion + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", agremiadoObj=" + agremiadoObj + ", clienteObj=" + clienteObj + ", sindicatoObj=" + sindicatoObj + ", tipoContratoObj=" + tipoContratoObj + '}';
    }
    
    
}
