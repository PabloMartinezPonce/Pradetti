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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Entidad encarga de representar la tabla datos_laborales
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 2
 * @since 2018/02/21
 */
@Entity
@Table(name = "datos_laborales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosLaborales.findAll", query = "SELECT d FROM DatosLaborales d"),
    @NamedQuery(name = "DatosLaborales.findByAgremiado", query = "SELECT d FROM DatosLaborales d WHERE d.agremiado = :agremiado"),
    @NamedQuery(name = "DatosLaborales.findByTipoNomina", query = "SELECT d FROM DatosLaborales d WHERE d.tipoNominaObj = :tipoNomina"),
    @NamedQuery(name = "DatosLaborales.findBySindicato", query = "SELECT d FROM DatosLaborales d WHERE d.sindicatoObj = :sindicato"),
    @NamedQuery(name = "DatosLaborales.findByCliente", query = "SELECT d FROM DatosLaborales d WHERE d.clienteObj = :cliente"),
    @NamedQuery(name = "DatosLaborales.findByTipoContrato", query = "SELECT d FROM DatosLaborales d WHERE d.tipoContratoObj = :tipoContrato"),
    @NamedQuery(name = "DatosLaborales.findByFechaAltaImss", query = "SELECT d FROM DatosLaborales d WHERE d.fechaAltaImss = :fechaAltaImss"),
    @NamedQuery(name = "DatosLaborales.findBySalariosImss", query = "SELECT d FROM DatosLaborales d WHERE d.salariosImss = :salariosImss"),
    @NamedQuery(name = "DatosLaborales.findBySalarioDiario", query = "SELECT d FROM DatosLaborales d WHERE d.salarioDiario = :salarioDiario"),
    @NamedQuery(name = "DatosLaborales.findBySalarioDiarioIntegrado", query = "SELECT d FROM DatosLaborales d WHERE d.salarioDiarioIntegrado = :salarioDiarioIntegrado"),
    @NamedQuery(name = "DatosLaborales.findBySueldoMensual", query = "SELECT d FROM DatosLaborales d WHERE d.sueldoMensual = :sueldoMensual"),
    @NamedQuery(name = "DatosLaborales.findByNumeroSeguro", query = "SELECT d FROM DatosLaborales d WHERE d.numeroSeguro = :numeroSeguro"),
    @NamedQuery(name = "DatosLaborales.findByReconocimientoAntiguedad", query = "SELECT d FROM DatosLaborales d WHERE d.reconocimientoAntiguedad = :reconocimientoAntiguedad"),
    @NamedQuery(name = "DatosLaborales.findByTarjetaTdu", query = "SELECT d FROM DatosLaborales d WHERE d.tarjetaTdu = :tarjetaTdu"),
    @NamedQuery(name = "DatosLaborales.findByCredencialLaboral", query = "SELECT d FROM DatosLaborales d WHERE d.credencialLaboral = :credencialLaboral"),
    @NamedQuery(name = "DatosLaborales.findByLugarTrabajo", query = "SELECT d FROM DatosLaborales d WHERE d.lugarTrabajo = :lugarTrabajo"),
    @NamedQuery(name = "DatosLaborales.findByActividadProfesional", query = "SELECT d FROM DatosLaborales d WHERE d.actividadProfesional = :actividadProfesional"),
    @NamedQuery(name = "DatosLaborales.findByAreaDepartamento", query = "SELECT d FROM DatosLaborales d WHERE d.areaDepartamento = :areaDepartamento"),
    @NamedQuery(name = "DatosLaborales.findByPeriodoContrato", query = "SELECT d FROM DatosLaborales d WHERE d.periodoContrato = :periodoContrato"),
    @NamedQuery(name = "DatosLaborales.findByFechaFinContrato", query = "SELECT d FROM DatosLaborales d WHERE d.fechaFinContrato = :fechaFinContrato"),
    @NamedQuery(name = "DatosLaborales.findByFechaTimbreContrato", query = "SELECT d FROM DatosLaborales d WHERE d.fechaTimbreContrato = :fechaTimbreContrato"),
    @NamedQuery(name = "DatosLaborales.findByNumeroInfonavit", query = "SELECT d FROM DatosLaborales d WHERE d.numeroInfonavit = :numeroInfonavit"),
    @NamedQuery(name = "DatosLaborales.findByEmpresaAnterior", query = "SELECT d FROM DatosLaborales d WHERE d.empresaAnterior = :empresaAnterior")})
public class DatosLaborales implements Serializable {

    private static final long serialVersionUID = 270890L;

    /**
     * EmbeddedId DatosLaboralesPK
     */
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="agremiadoObj"))
    private Integer agremiado;
    
    @Column(name = "fecha_alta_imss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAltaImss;
    
    @Size(max = 50)
    @Column(name = "salarios_imss")
    private String salariosImss;
    
    @Size(max = 50)
    @Column(name = "salario_diario")
    private String salarioDiario;
    
    @Size(max = 50)
    @Column(name = "salario_diario_integrado")
    private String salarioDiarioIntegrado;
    
    @Size(max = 50)
    @Column(name = "sueldo_mensual")
    private String sueldoMensual;
    
    @Size(max = 50)
    @Column(name = "numero_seguro")
    private String numeroSeguro;
    
    @Size(max = 50)
    @Column(name = "reconocimiento_antiguedad")
    private String reconocimientoAntiguedad;
    
    @Size(max = 2)
    @Column(name = "tarjeta_tdu")
    private String tarjetaTdu;
    
    @Size(max = 2)
    @Column(name = "credencial_laboral")
    private String credencialLaboral;
    
    @Size(max = 200)
    @Column(name = "lugar_trabajo")
    private String lugarTrabajo;
    
    @Size(max = 200)
    @Column(name = "actividad_profesional")
    private String actividadProfesional;
    
    @Size(max = 200)
    @Column(name = "area_departamento")
    private String areaDepartamento;
    
    @Size(max = 150)
    @Column(name = "periodo_contrato")
    private String periodoContrato;
    
    @Column(name = "fecha_fin_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaFinContrato;
    
    @Column(name = "fecha_timbre_contrato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTimbreContrato;
    
    @Size(max = 15)
    @Column(name = "numero_infonavit")
    private String numeroInfonavit;
    
    @Size(max = 255)
    @Column(name = "empresa_anterior")
    private String empresaAnterior;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Agremiado agremiadoObj;
            
    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "dLaborales_idCliente"), referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente clienteObj;
    
    @JoinColumn(name = "sindicato", foreignKey = @ForeignKey(name = "dLaborales_idSindicato"), referencedColumnName = "id_sindicato")
    @ManyToOne(optional = true)//@ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Sindicato sindicatoObj;
    
    @JoinColumn(name = "tipo_contrato", foreignKey = @ForeignKey(name = "dLaborales_idTipoContrato"), referencedColumnName = "id_tipo_contrato")
    @ManyToOne(optional = true)
    private TipoContrato tipoContratoObj;
    
    @JoinColumn(name = "tipo_nomina", foreignKey = @ForeignKey(name = "dLaborales_idTipoNomina"), referencedColumnName = "id_tipo_nomina")
    @ManyToOne(optional = true)
    private TipoNomina tipoNominaObj;
    
    @JoinColumn(name = "esquema_pago", foreignKey = @ForeignKey(name = "dLaborales_idEsquemaPago"), referencedColumnName = "idesquema_pago")
    @ManyToOne(optional = true)
    private EsquemaPago esquemaPago;
    
    @JoinColumn(name ="sup",foreignKey = @ForeignKey(name = "dLaborales_idSup"), referencedColumnName = "id_sup")
    @ManyToOne(optional = true)
    private SalarioUnicoProfesional salarioUnicoProfesional;
    
    /**
     * Mapea el campo nombre_contrato con las anotaciones @Size @Column, asignandolo a una variable de tipo String
     * Se va a utilizar el campo para almacenar el nombre de un contrato
     */
    @Size(max = 100)
    @Column(name = "nombre_contrato")
    private String nombreContrato;
   
    @Column(name = "fa_disponible")
    private Boolean fondoDeAhorroDisponible;
    
    @Column(name = "fa_actual")    
    private Short fondoDeAhorroActual;

    @Column(name = "fa_fecha")
    @Temporal(TemporalType.DATE)
    private Date fondoDeAhorroFechaSol;
    
    @JoinColumn(name = "contratista", foreignKey = @ForeignKey(name = "dLaborales_idContratista"), referencedColumnName = "id_contratista")
    @ManyToOne(optional = false)
    private Contratista contratistaObj;
        
    @Column(name = "fecha_inicio_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioContrato;
    
    @Column(name = "antiguedad")
    private Boolean antiguedadReconocida;
    
    @Column(name="fecha_antiguedad")    
    @Temporal(TemporalType.DATE)
    private Date antiguedadDesde;
    
     @Size(max = 250)
    @Column(name = "tiempo_contrato")
    private String tiempoContrato;
     
    @Size(max = 250)
    @Column(name = "tiempo_antiguedad")
    private String tiempoAntiguedad;    
    
    @Column(name = "percepcion_sup")
    private Boolean percepcionBasadaEnSUP;

    public DatosLaborales() {
    }

    public Date getFechaAltaImss() {
        return fechaAltaImss;
    }

    public void setFechaAltaImss(Date fechaAltaImss) {
        this.fechaAltaImss = fechaAltaImss;
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

    public String getNumeroSeguro() {
        return numeroSeguro;
    }

    public void setNumeroSeguro(String numeroSeguro) {
        this.numeroSeguro = numeroSeguro;
    }

    public String getReconocimientoAntiguedad() {
        return reconocimientoAntiguedad;
    }

    public void setReconocimientoAntiguedad(String reconocimientoAntiguedad) {
        this.reconocimientoAntiguedad = reconocimientoAntiguedad;
    }

    public String getTarjetaTdu() {
        return tarjetaTdu;
    }

    public void setTarjetaTdu(String tarjetaTdu) {
        this.tarjetaTdu = tarjetaTdu;
    }

    public String getCredencialLaboral() {
        return credencialLaboral;
    }

    public void setCredencialLaboral(String credencialLaboral) {
        this.credencialLaboral = credencialLaboral;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getActividadProfesional() {
        return actividadProfesional;
    }

    public void setActividadProfesional(String actividadProfesional) {
        this.actividadProfesional = actividadProfesional;
    }

    public String getAreaDepartamento() {
        return areaDepartamento;
    }

    public void setAreaDepartamento(String areaDepartamento) {
        this.areaDepartamento = areaDepartamento;
    }

    public String getPeriodoContrato() {
        return periodoContrato;
    }

    public void setPeriodoContrato(String periodoContrato) {
        this.periodoContrato = periodoContrato;
    }

    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public Date getFechaTimbreContrato() {
        return fechaTimbreContrato;
    }

    public void setFechaTimbreContrato(Date fechaTimbreContrato) {
        this.fechaTimbreContrato = fechaTimbreContrato;
    }

    public String getNumeroInfonavit() {
        return numeroInfonavit;
    }

    public void setNumeroInfonavit(String numeroInfonavit) {
        this.numeroInfonavit = numeroInfonavit;
    }

    public String getEmpresaAnterior() {
        return empresaAnterior;
    }

    public void setEmpresaAnterior(String empresaAnterior) {
        this.empresaAnterior = empresaAnterior;
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

    public TipoNomina getTipoNominaObj() {
        return tipoNominaObj;
    }

    public void setTipoNominaObj(TipoNomina tipoNominaObj) {
        this.tipoNominaObj = tipoNominaObj;
    }

    public Integer getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(Integer agremiado) {
        this.agremiado = agremiado;
    }

    public EsquemaPago getEsquemaPago() {
        return esquemaPago;
    }

    public void setEsquemaPago(EsquemaPago esquemaPago) {
        this.esquemaPago = esquemaPago;
    }
    
    /**
     * Metodo encargado de obtener el campo nombre_contrato
     * @return nombreContrato
     */
    public String getNombreContrato() {
        return nombreContrato;
    }
    
    /**
     * Meotodo encargado de ingresar al campo nombre_contrato
     * @param nombreContrato 
     */
    public void setNombreContrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
    }

    public Contratista getContratistaObj() {
        return contratistaObj;
    }

    public void setContratistaObj(Contratista contratistaObj) {
        this.contratistaObj = contratistaObj;
    }
    
   public Short getFondoDeAhorroActual() {
        return fondoDeAhorroActual;
    }

    public void setFondoDeAhorroActual(Short fondoDeAhorroActual) {
        this.fondoDeAhorroActual = fondoDeAhorroActual;
    }   
    
    public Boolean getFondoDeAhorroDisponible() {
        return fondoDeAhorroDisponible;
    }

    public void setFondoDeAhorroDisponible(Boolean fondoDeAhorroDisponible) {
        this.fondoDeAhorroDisponible = fondoDeAhorroDisponible;
    }

    public Date getFondoDeAhorroFechaSol() {
        return fondoDeAhorroFechaSol;
    }

    public void setFondoDeAhorroFechaSol(Date fondoDeAhorroFechaSol) {
        this.fondoDeAhorroFechaSol = fondoDeAhorroFechaSol;
    }
    
    public SalarioUnicoProfesional getSalarioUnicoProfesional() {
        return salarioUnicoProfesional;
    }

    public void setSalarioUnicoProfesional(SalarioUnicoProfesional salarioUnicoProfesional) {
        this.salarioUnicoProfesional = salarioUnicoProfesional;
    }

    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }
        
    public Boolean getAntiguedadReconocida() {
        return antiguedadReconocida;
    }

    public void setAntiguedadReconocida(Boolean antiguedadReconocida) {
        this.antiguedadReconocida = antiguedadReconocida;
    }

    public Date getAntiguedadDesde() {
        return antiguedadDesde;
    }

    public void setAntiguedadDesde(Date antiguedadDesde) {
        this.antiguedadDesde = antiguedadDesde;
    }

    public String getTiempoContrato() {
        return tiempoContrato;
    }

    public void setTiempoContrato(String tiempoContrato) {
        this.tiempoContrato = tiempoContrato;
    }

    public String getTiempoAntiguedad() {
        return tiempoAntiguedad;
    }

    public void setTiempoAntiguedad(String tiempoAntiguedad) {
        this.tiempoAntiguedad = tiempoAntiguedad;
    }

    public Boolean getPercepcionBasadaEnSUP() {
        return percepcionBasadaEnSUP;
    }

    public void setPercepcionBasadaEnSUP(Boolean percepcionBasadaEnSUP) {
        this.percepcionBasadaEnSUP = percepcionBasadaEnSUP;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.agremiado);
        hash = 67 * hash + Objects.hashCode(this.agremiadoObj);
        hash = 67 * hash + Objects.hashCode(this.clienteObj);
        hash = 67 * hash + Objects.hashCode(this.sindicatoObj);
        hash = 67 * hash + Objects.hashCode(this.tipoContratoObj);
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
        final DatosLaborales other = (DatosLaborales) obj;
        if (!Objects.equals(this.agremiado, other.agremiado)) {
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
        return "DatosLaborales{" + "agremiado=" + agremiado + ", fechaAltaImss=" + fechaAltaImss + ", salariosImss=" + salariosImss + ", salarioDiario=" + salarioDiario + ", salarioDiarioIntegrado=" + salarioDiarioIntegrado + ", sueldoMensual=" + sueldoMensual + ", numeroSeguro=" + numeroSeguro + ", reconocimientoAntiguedad=" + reconocimientoAntiguedad + ", tarjetaTdu=" + tarjetaTdu + ", credencialLaboral=" + credencialLaboral + ", lugarTrabajo=" + lugarTrabajo + ", actividadProfesional=" + actividadProfesional + ", areaDepartamento=" + areaDepartamento + ", periodoContrato=" + periodoContrato + ", fechaFinContrato=" + fechaFinContrato + ", fechaTimbreContrato=" + fechaTimbreContrato + ", numeroInfonavit=" + numeroInfonavit + ", empresaAnterior=" + empresaAnterior + ", nombreContrato=" + nombreContrato + ", fondoDeAhorroDisponible=" + fondoDeAhorroDisponible + ", fondoDeAhorroActual=" + fondoDeAhorroActual + ", fechaInicioContrato=" + fechaInicioContrato + ", antiguedadReconocida=" + antiguedadReconocida + ", antiguedadDesde=" + antiguedadDesde + ", tiempoContrato=" + tiempoContrato + ", tiempoAntiguedad=" + tiempoAntiguedad  + ", percepcionBasadaEnSUP=" + percepcionBasadaEnSUP + '}';
    }
    public String getDatosRelacionales(){
        return ">>>clienteObj>"+clienteObj+"\n<sindicatoObj>"+sindicatoObj+"\n<tipoContratoObj>"+tipoContratoObj+"\n<tipoNominaObj>"+tipoNominaObj+"\n<esquemaPago>"+esquemaPago+"\n<salarioUnicoProfesional>"+salarioUnicoProfesional+"\n<contratistaObj>"+contratistaObj+"<<<<";
    }
}
