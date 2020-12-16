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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entidad encargada de representar la tabla contratista 
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "contratista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratista.findAll", query = "SELECT c FROM Contratista c"),
    @NamedQuery(name = "Contratista.findByIdContratista", query = "SELECT c FROM Contratista c WHERE c.idContratista = :idContratista"),
    @NamedQuery(name = "Contratista.findByNombreEmpresa", query = "SELECT c FROM Contratista c WHERE c.nombreEmpresa = :nombreEmpresa"),
    @NamedQuery(name = "Contratista.findByRfc", query = "SELECT c FROM Contratista c WHERE c.rfc = :rfc")})
public class Contratista implements Serializable {
   
    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contratista")
    private Integer idContratista;
    
    @Size(max = 230)
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    
    @Size(max = 250)
    @Column(name = "representate_legal")
    private String representateLegal;
    
    @Size(max = 15)
    @Column(name = "rfc")
    private String rfc;
    
    @Size(max = 200)
    @Column(name = "clausula_administrativa")
    private String clausulaAdministrativa;
    
    @Lob
    @Size(max = 16777215)
    @Column(name = "descripcion_clausula")
    private String descripcionClausula;
    
    @Column(name = "status")
    private Boolean status;
    
    @Basic(optional = false)
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    @JoinTable(name = "relacion_contratista_acta", joinColumns = {
        @JoinColumn(name = "contratista", foreignKey = @ForeignKey(name = "rContratistaActa_idContratista"))}, inverseJoinColumns = {
        @JoinColumn(name = "acta_notarial", foreignKey = @ForeignKey(name = "rContratistaActa_idActaNotarial"))})
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ActaNotarial> actaNotarialList;    
    
    @OneToMany(mappedBy = "contratistaObj", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ContratoEmpresas> contratoEmpresasList;
    
    @JoinTable(name = "relacion_contratista_domicilio", joinColumns = {
        @JoinColumn(name = "contratista", foreignKey = @ForeignKey(name = "rContratistaDomicilio_idContratista"), referencedColumnName = "id_contratista")}, inverseJoinColumns = {
        @JoinColumn(name = "empresas_domicilio", referencedColumnName = "id_empresas_domicilio")})
    @ManyToMany
    private List<EmpresasDomicilio> empresasDomicilioList;
    
    @JoinTable(name = "contrato_contratista", joinColumns = {
	@JoinColumn(name = "contratista", foreignKey = @ForeignKey(name = "cContratista_idContratista"))}, inverseJoinColumns = {
	@JoinColumn(name = "contrato", foreignKey = @ForeignKey(name = "cContratista_idContrato"))})
@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
@Fetch(value = FetchMode.SUBSELECT)
private List<Contrato> contratoList; 
 
    public Contratista() {
    }

    public Contratista(Integer idContratista) {
        this.idContratista = idContratista;
    }

    public Contratista(Integer idContratista, Date creado) {
        this.idContratista = idContratista;
        this.creado = creado;
    }

    public Integer getIdContratista() {
        return idContratista;
    }

    public void setIdContratista(Integer idContratista) {
        this.idContratista = idContratista;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRepresentateLegal() {
        return representateLegal;
    }

    public void setRepresentateLegal(String representateLegal) {
        this.representateLegal = representateLegal;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getClausulaAdministrativa() {
        return clausulaAdministrativa;
    }

    public void setClausulaAdministrativa(String clausulaAdministrativa) {
        this.clausulaAdministrativa = clausulaAdministrativa;
    }

    public String getDescripcionClausula() {
        return descripcionClausula;
    }

    public void setDescripcionClausula(String descripcionClausula) {
        this.descripcionClausula = descripcionClausula;
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

    @XmlTransient
    public List<ActaNotarial> getActaNotarialList() {
        return actaNotarialList;
    }

    public void setActaNotarialList(List<ActaNotarial> actaNotarialList) {
        this.actaNotarialList = actaNotarialList;
    }

    @XmlTransient
    public List<EmpresasDomicilio> getEmpresasDomicilioList() {
        return empresasDomicilioList;
    }

    public void setEmpresasDomicilioList(List<EmpresasDomicilio> empresasDomicilioList) {
        this.empresasDomicilioList = empresasDomicilioList;
    }

    public List<ContratoEmpresas> getContratoEmpresasList() {
        return contratoEmpresasList;
    }

    public void setContratoEmpresasList(List<ContratoEmpresas> contratoEmpresasList) {
        this.contratoEmpresasList = contratoEmpresasList;
    }

@XmlTransient
public List<Contrato> getContratoList() {
	return contratoList;
}

public void setContratoList(List<Contrato> contratoList) {
	this.contratoList = contratoList;
}
    
    
  @Override
  public int hashCode() {
        int hash = 0;
        hash += (idContratista != null ? idContratista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contratista)) {
            return false;
        }
        Contratista other = (Contratista) object;
        return !((this.idContratista == null && other.idContratista != null) || (this.idContratista != null && !this.idContratista.equals(other.idContratista)));
    }

    @Override
    public String toString() {
        return "Contratista{" + "idContratista=" + idContratista + ", nombreEmpresa=" + nombreEmpresa + ", representateLegal=" + representateLegal + ", rfc=" + rfc + ", clausulaAdministrativa=" + clausulaAdministrativa + ", descripcionClausula=" + descripcionClausula + ", status=" + status + ", creado=" + creado + ", modificado=" + modificado + "}";
    }

}
