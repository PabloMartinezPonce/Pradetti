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
import javax.persistence.ConstraintMode;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Entidad encargada de representar la tabla cliente
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 2
 * @since   2017/12/06
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "FROM Cliente"),
    @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cliente.findByUsuario", query = "FROM Cliente where :usuario in usuarioList")})
public class Cliente implements Serializable {

        
    private static final long serialVersionUID = 270890L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;
    
    @Size(max = 230)
    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    
    @Size(max = 250)
    @Column(name = "representante_legal")
    private String representanteLegal;
    
    @Size(max = 15)
    @Column(name = "rfc")
    private String rfc;
    
    @Size(max = 200)
    @Column(name = "tipo_sociedad")
    private String tipoSociedad;
    
    @Size(max = 200)
    @Column(name = "giro")
    private String giro;
    
    @Lob
    @Size(max = 16777215)
    @Column(name = "fin_social")
    private String finSocial;
    
    @Column(name = "fecha_registro_publico")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistroPublico;
    
    @Column(name = "status")
    private Boolean status;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    @Column(name = "zona_sm")
    @JoinColumn(name = "zona_sm", foreignKey = @ForeignKey(name = "cliente_idZonaSm"), referencedColumnName = "id_zona_sm")
    private Short zonaSm;
    
    @OneToMany(mappedBy = "clienteObj", fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<SolicitudBaja> solicitudBajaList;
    
    @OneToMany(mappedBy = "clientes", fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Recibo> reciboList;
    
    @OneToMany(mappedBy = "clienteObj", fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<RelacionContrato> relacionContratoList;
    
    @JoinTable(name = "relacion_cliente_acta", joinColumns = {
        @JoinColumn(name = "cliente",  foreignKey = @ForeignKey(name = "id_cliente"))}, inverseJoinColumns = {
        @JoinColumn(name = "acta_notarial", foreignKey = @ForeignKey(name = "id_acta_notarial"))})
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ActaNotarial> actaNotarialList;

    @JoinTable(name = "relacion_cliente_domicilio", joinColumns = {
        @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "id_cliente"))}, inverseJoinColumns = {
        @JoinColumn(name = "empresas_domicilio", referencedColumnName = "id_empresas_domicilio")})
    @ManyToMany
    private List<EmpresasDomicilio> empresasDomicilioList;
      
    @JoinTable(name = "usuario_clientes", joinColumns = {
        @JoinColumn(name = "cliente", foreignKey = @ForeignKey(name = "uClientes_idCliente"), referencedColumnName = "id_cliente")}, inverseJoinColumns = {
        @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "uClientes_idUsuario"), referencedColumnName = "id_usuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    
    @OneToMany(mappedBy = "clienteObj", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ContratoEmpresas> contratoEmpresasList;
    
      

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, Date creado) {
        this.idCliente = idCliente;
        this.creado = creado;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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

    public String getTipoSociedad() {
        return tipoSociedad;
    }

    public void setTipoSociedad(String tipoSociedad) {
        this.tipoSociedad = tipoSociedad;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getFinSocial() {
        return finSocial;
    }

    public void setFinSocial(String finSocial) {
        this.finSocial = finSocial;
    }

    public Date getFechaRegistroPublico() {
        return fechaRegistroPublico;
    }

    public void setFechaRegistroPublico(Date fechaRegistroPublico) {
        this.fechaRegistroPublico = fechaRegistroPublico;
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

    public Short getZonaSm() {
        return zonaSm;
    }

    public void setZonaSm(Short zonaSm) {
        this.zonaSm = zonaSm;
    }

    @XmlTransient
    public List<SolicitudBaja> getSolicitudBajaList() {
        return solicitudBajaList;
    }

    public void setSolicitudBajaList(List<SolicitudBaja> solicitudBajaList) {
        this.solicitudBajaList = solicitudBajaList;
    }

    @XmlTransient
    public List<Recibo> getReciboList() {
        return reciboList;
    }

    public void setReciboList(List<Recibo> reciboList) {
        this.reciboList = reciboList;
    }

    @XmlTransient
    public List<ActaNotarial> getActaNotarialList() {
        return actaNotarialList;
    }

    public void setActaNotarialList(List<ActaNotarial> actaNotarialList) {
        this.actaNotarialList = actaNotarialList;
    }

    public List<EmpresasDomicilio> getEmpresasDomicilioList() {
        return empresasDomicilioList;
    }

    public void setEmpresasDomicilioList(List<EmpresasDomicilio> empresasDomicilioList) {
        this.empresasDomicilioList = empresasDomicilioList;
    }

    public List<RelacionContrato> getRelacionContratoList() {
        return relacionContratoList;
    }

    public void setRelacionContratoList(List<RelacionContrato> relacionContratoList) {
        this.relacionContratoList = relacionContratoList;
    }
    
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public List<ContratoEmpresas> getContratoEmpresasList() {
        return contratoEmpresasList;
    }

    public void setContratoEmpresasList(List<ContratoEmpresas> contratoEmpresasList) {
        this.contratoEmpresasList = contratoEmpresasList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        return !((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente)));
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nombreEmpresa=" + nombreEmpresa + ", representanteLegal=" + representanteLegal + ", rfc=" + rfc + ", tipoSociedad=" + tipoSociedad + ", giro=" + giro + ", finSocial=" + finSocial + ", fechaRegistroPublico=" + fechaRegistroPublico + ", status=" + status + ", creado=" + creado + ", modificado=" + modificado + ", zonaSm=" + zonaSm + " }";
    }
}
