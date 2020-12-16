/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entidad encargada de representar la tabla contrato_empresas
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "contrato_empresas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoEmpresas.findAll", query = "SELECT c FROM ContratoEmpresas c"),
    @NamedQuery(name = "ContratoEmpresas.findByCliente", query = "SELECT c FROM ContratoEmpresas c WHERE c.clienteObj = :cliente"),
    @NamedQuery(name = "ContratoEmpresas.findByContratista", query = "SELECT c FROM ContratoEmpresas c WHERE c.contratistaObj = :contratista"),
    @NamedQuery(name = "ContratoEmpresas.findByClienteAndContratista", query = "SELECT c FROM ContratoEmpresas c WHERE c.clienteObj = :cliente AND c.contratistaObj = :contratista"),
    @NamedQuery(name = "ContratoEmpresas.findByIdName", query = "SELECT c FROM ContratoEmpresas c WHERE c.nombreContrato = :nombreContrato")})
public class ContratoEmpresas implements Serializable {

    private static final long serialVersionUID = 270890L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato_empresas")
    private Integer idContratoEmpresas;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Size(max = 100)
    @Column(name = "periodo")
    private String periodo;
    
    @Size(max = 100)
    @Column(name = "comision")
    private String comision;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    @Size(max = 250)
    @Column(name = "tcontratista_nombre")
    private String tcontratistaNombre;
    
    @Size(max = 120)
    @Column(name = "tcontratista_ocupacion")
    private String tcontratistaOcupacion;
    
    @Size(max = 350)
    @Column(name = "tcontratista_origen")
    private String tcontratistaOrigen;
    
    @Size(max = 250)
    @Column(name = "tcliente_nombre")
    private String tclienteNombre;
    
    @Size(max = 120)
    @Column(name = "tcliente_ocupacion")
    private String tclienteOcupacion;
    
    @Size(max = 350)
    @Column(name = "tcliente_origen")
    private String tclienteOrigen;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "url_documento")
    private String urlDocumento;
    
    @Column(name = "id_plantilla_contrato")
    private Integer idPlantillaContrato;
    
    @Size(max = 100)
    @Column(name = "informes")
    private String informes;
    
    @Size(max = 100)
    @Column(name = "evaluaciones")
    private String evaluaciones;
    
    @Size(max = 100)
    @Column(name = "deposito")
    private String deposito;
    
    @Size(max = 100)
    @Column(name = "tiempo_del_contrato")
    private String tiempoDelContrato;
    
    /**
     * Mapea el campo nombre_contrato con las anotaciones @Size @Column, asignandolo a una variable de tipo String
     * Se va a utilizar el campo para almacenar el nombre de un contrato
     */
    @Size(max = 100)
    @Column(name = "nombre_contrato")
    private String nombreContrato;
    
   @Column(name = "cliente")
    @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "cEmpresas_idCliente"), referencedColumnName = "id_cliente", insertable = false, updatable = false)
    private Integer clienteObj;
    
    @Column(name = "contratista")
    @JoinColumn(name = "contratista", foreignKey = @ForeignKey(name = "cEmpresas_idContratista"), referencedColumnName = "id_contratista", insertable = false, updatable = false)
    private Integer contratistaObj;
    
    @JoinTable(name = "relacion_sup_contrato_emp", joinColumns = {
        @JoinColumn(name = "contrato", foreignKey = @ForeignKey(name = "rSupContrato_idContrato"), referencedColumnName = "id_contrato_empresas")}, inverseJoinColumns = {
        @JoinColumn(name = "sup", foreignKey = @ForeignKey(name = "rSupCliente_idSup"), referencedColumnName = "id_sup")})
    @ManyToMany
    private List<SalarioUnicoProfesional> salarioUnicoProfesionalList;
    
    // Representa la tabla contrato_asignado con sus respectivas relaciones y le asigna una lista de tipo Contrato
    @JoinTable(name = "contrato_asignado", joinColumns = {
        @JoinColumn(name = "contrato_empresa", foreignKey = @ForeignKey(name = "cAsContratoEmpresas_idContratoEmpresas"), referencedColumnName = "id_contrato_empresas")}, inverseJoinColumns = {
        @JoinColumn(name = "contrato", foreignKey = @ForeignKey(name = "cAsContrato_idContrato"), referencedColumnName = "id_contrato")})
    @ManyToMany
    private List<Contrato> contratosList;
    
    //Representa la tabla relacion_catalogo_contrato con sus respectivas relaciones y le asigna una lista de tipo CatalogoDocumental
    @JoinTable(name = "relacion_catalogo_contrato", joinColumns = {
        @JoinColumn(name = "id_contrato_empresas", foreignKey = @ForeignKey(name = "rCatalogoContrato_idContratoEmp"), referencedColumnName = "id_contrato_empresas")}, inverseJoinColumns = {
        @JoinColumn(name = "catalogo_documental", foreignKey = @ForeignKey(name = "rCatalogoContrato_idCatDoc"), referencedColumnName = "id_catalogo")})
    @ManyToMany
    private List<CatalogoDocumental> catalogoDocumentalList; 
    
    //Representa la tabla relacion_periodo_contrato con sus respectivas relaciones y le asigna una lista de tipo PeriodoFA
    @JoinTable(name = "relacion_periodo_contrato", joinColumns = {
        @JoinColumn(name = "id_contrato_empresas", foreignKey = @ForeignKey(name = "rPeCnContratoEmpresas_idContratoEmpresas"), referencedColumnName = "id_contrato_empresas")}, inverseJoinColumns = {
        @JoinColumn(name = "id_periodo_fa", foreignKey = @ForeignKey(name = "rPeCnPeriodoFA"), referencedColumnName = "id_periodo_fa")})
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL) 
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PeriodoFA> periodoFA; 
    
    /**
     @JoinTable(name="relacion_esquema_tipo_documentos",joinColumns = {
        @JoinColumn(name = "tipo_documento", foreignKey = @ForeignKey(name = "rEsquemaPagoTipoDocumento_idTipoDocumento"))},inverseJoinColumns = {
        @JoinColumn(name="esquema",foreignKey = @ForeignKey(name="rEquemaPagoTipoDocumento_idEsquemaPago"))})
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<EsquemaPago> esquemaPagos;
     */
        
    public ContratoEmpresas() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
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

    public String getTcontratistaNombre() {
        return tcontratistaNombre;
    }

    public void setTcontratistaNombre(String tcontratistaNombre) {
        this.tcontratistaNombre = tcontratistaNombre;
    }

    public String getTcontratistaOcupacion() {
        return tcontratistaOcupacion;
    }

    public void setTcontratistaOcupacion(String tcontratistaOcupacion) {
        this.tcontratistaOcupacion = tcontratistaOcupacion;
    }

    public String getTcontratistaOrigen() {
        return tcontratistaOrigen;
    }

    public void setTcontratistaOrigen(String tcontratistaOrigen) {
        this.tcontratistaOrigen = tcontratistaOrigen;
    }

    public String getTclienteNombre() {
        return tclienteNombre;
    }

    public void setTclienteNombre(String tclienteNombre) {
        this.tclienteNombre = tclienteNombre;
    }

    public String getTclienteOcupacion() {
        return tclienteOcupacion;
    }

    public void setTclienteOcupacion(String tclienteOcupacion) {
        this.tclienteOcupacion = tclienteOcupacion;
    }

    public String getTclienteOrigen() {
        return tclienteOrigen;
    }

    public void setTclienteOrigen(String tclienteOrigen) {
        this.tclienteOrigen = tclienteOrigen;
    }

    public String getUrlDocumento() {
        return urlDocumento;
    }

    public void setUrlDocumento(String urlDocumento) {
        this.urlDocumento = urlDocumento;
    }

    public Integer getIdPlantillaContrato() {
        return idPlantillaContrato;
    }

    public void setIdPlantillaContrato(Integer idPlantillaContrato) {
        this.idPlantillaContrato = idPlantillaContrato;
    }

    public String getInformes() {
        return informes;
    }

    public void setInformes(String informes) {
        this.informes = informes;
    }

    public String getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(String evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getTiempoDelContrato() {
        return tiempoDelContrato;
    }

    public void setTiempoDelContrato(String tiempoDelContrato) {
        this.tiempoDelContrato = tiempoDelContrato;
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
    
    public Integer getClienteObj() {
        return clienteObj;
    }

    public void setClienteObj(Integer clienteObj) {
        this.clienteObj = clienteObj;
    }

    public Integer getContratistaObj() {
        return contratistaObj;
    }

    public void setContratistaObj(Integer contratistaObj) {
        this.contratistaObj = contratistaObj;
    }

    public Integer getIdContratoEmpresas() {
        return idContratoEmpresas;
    }

    public void setIdContratoEmpresas(Integer idContratoEmpresas) {
        this.idContratoEmpresas = idContratoEmpresas;
    }

    public List<SalarioUnicoProfesional> getSalarioUnicoProfesionalList() {
        return salarioUnicoProfesionalList;
    }

    public void setSalarioUnicoProfesionalList(List<SalarioUnicoProfesional> salarioUnicoProfesionalList) {
        this.salarioUnicoProfesionalList = salarioUnicoProfesionalList;
    }

    /**
     * Metodo encargado de obtener una lista de tipo CatalogoDocumental
     * @return catalogoDocumentalList
     */
    public List<CatalogoDocumental> getCatalogoDocumentalList() {
        return catalogoDocumentalList;
    }
    
    /**
     * Meotodo encargado de ingresar una lista de tipo CatalogoDocumental
     * @param catalogoDocumentalList 
     */
    public void setCatalogoDocumentalList(List<CatalogoDocumental> catalogoDocumentalList) {
        this.catalogoDocumentalList = catalogoDocumentalList;
    }
    /**
     * Metodo encargado de obtener  una lista de tipo Contrato
     * @return 
     */
    public List<Contrato> getContratosList() {
        return contratosList;
    }
    /**
     * Metdodo encargado de ingresar una lista de tipo Contrato
     * @param contratosList 
     */
    public void setContratosList(List<Contrato> contratosList) {
        this.contratosList = contratosList;
    }

    public List<PeriodoFA> getPeriodoFA() {
        return periodoFA;
    }

    public void setPeriodoFA(List<PeriodoFA> periodoFA) {
        this.periodoFA = periodoFA;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.idContratoEmpresas);
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
        final ContratoEmpresas other = (ContratoEmpresas) obj;
        if (!Objects.equals(this.idContratoEmpresas, other.idContratoEmpresas)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContratoEmpresas{" + "idContratoEmpresas=" + idContratoEmpresas + ", fecha=" + fecha + ", periodo=" + periodo + ", comision=" + comision + ", creado=" + creado + ", modificado=" + modificado + ", tcontratistaNombre=" + tcontratistaNombre + ", tcontratistaOcupacion=" + tcontratistaOcupacion + ", tcontratistaOrigen=" + tcontratistaOrigen + ", tclienteNombre=" + tclienteNombre + ", tclienteOcupacion=" + tclienteOcupacion + ", tclienteOrigen=" + tclienteOrigen + ", urlDocumento=" + urlDocumento + ", clienteObj=" + clienteObj + ", contratistaObj=" + contratistaObj + '}';
    }
}
