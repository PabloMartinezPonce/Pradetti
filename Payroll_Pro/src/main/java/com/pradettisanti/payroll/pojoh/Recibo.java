/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editorecibo.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
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
 * Entidad encargada de representar la tabla recibo
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "recibo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibo.findAll", query = "SELECT r FROM Recibo r"),
    @NamedQuery(name = "Recibo.findByIdRecibo", query = "FROM Recibo r WHERE r.idRecibo = :idRecibo"),
    @NamedQuery(name = "Recibo.findByAgremiado", query = "FROM Recibo r WHERE r.agremiado = :agremiado"),
    @NamedQuery(name = "Recibo.findByCliente", query = "FROM Recibo r WHERE r.clientes = :cliente"),
    @NamedQuery(name = "Recibo.findBySindicato", query = "FROM Recibo r WHERE r.sindicato = :sindicato"),
    @NamedQuery(name = "Recibo.findByAgrClt", query = "FROM Recibo r WHERE r.agremiado = :agremiado and r.clientes = :cliente"),
    @NamedQuery(name = "Recibo.findByAgrSdo", query = "FROM Recibo r WHERE r.agremiado = :agremiado and r.sindicato = :sindicato"),
    @NamedQuery(name = "Recibo.findByClienteAndFechaRegistro", query = "FROM Recibo r WHERE r.clientes.idCliente = :idCliente and r.diaDeRegistro = :diaDeRegistro"),
    @NamedQuery(name = "Recibo.findByClienteAndPeriodoFechaRegistro", query = "FROM Recibo r WHERE r.clientes.idCliente = :idCliente and r.diaDeRegistro BETWEEN :diaDeRegistroDesde AND :diaDeRegistroHasta"),
    @NamedQuery(name = "Recibo.findByAgrFechainicialFechafinal", query = "FROM Recibo r WHERE r.agremiado = :agremiado and r.diaInicial = :fInicial and r.diaFinal = :fFinal"),
    @NamedQuery(name = "Recibo.findByCltFechainicialFechafinal", query = "FROM Recibo r WHERE r.clientes = :cliente and r.diaInicial = :fInicial and r.diaFinal = :fFinal"),
    @NamedQuery(name = "Recibo.findBySdoFechainicialFechafinal", query = "FROM Recibo r WHERE r.sindicato = :sindicato and r.diaInicial = :fInicial and r.diaFinal = :fFinal"),
    @NamedQuery(name = "Recibo.findByAgrCltFechainicialFechafinal", query = "FROM Recibo r WHERE r.agremiado = :agremiado and r.clientes = :cliente and r.diaInicial = :fInicial and r.diaFinal = :fFinal"),
    @NamedQuery(name = "Recibo.findByAgrSdoFechainicialFechafinal", query = "FROM Recibo r WHERE r.agremiado = :agremiado and r.sindicato = :sindicato and r.diaInicial = :fInicial and r.diaFinal = :fFinal")})
public class Recibo implements Serializable {

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recibo")
    private Integer idRecibo;
    
    @Column(name = "total_sindicato")
    private Double totalSindicato;
    
    @Column(name = "total_nomina")
    private Double totalNomina;
    
    @Size(max = 200)
    @Column(name = "deducciones_concepto")
    private String deduccionesConcepto;
    
    @Column(name = "deducciones_importe")
    private Double deduccionesImporte;
    
    @Column(name = "dias_trabajados")
    private Integer diasTrabajados;
    
    @JoinColumn(name = "agremiado", foreignKey = @ForeignKey(name = "recibo_idAgremiado"), referencedColumnName = "id_agremiado")
    @ManyToOne(optional = false)
    private Agremiado agremiado;
    
    @JoinColumn(name = "clientes", foreignKey = @ForeignKey(name = "recibo_idCliente"), referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente clientes;
    
    @JoinColumn(name = "sindicato", foreignKey = @ForeignKey(name = "recibo_idSindicato"), referencedColumnName = "id_sindicato")
    @ManyToOne(optional = false)
    private Sindicato sindicato; 
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_inicial")
    @Temporal(TemporalType.DATE)
    private Date diaInicial;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_final")
    @Temporal(TemporalType.DATE)
    private Date diaFinal;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "dia_de_registro")
    @Temporal(TemporalType.DATE)
    private Date diaDeRegistro;

    @Column(name = "dia_modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diaModificado;
    
    public Recibo() {
    }

    public Recibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }

    public Integer getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(Integer idRecibo) {
        this.idRecibo = idRecibo;
    }
 
    public Double getTotalSindicato() {
        return totalSindicato;
    }

    public void setTotalSindicato(Double totalSindicato) {
        this.totalSindicato = totalSindicato;
    }

    public Double getTotalNomina() {
        return totalNomina;
    }

    public void setTotalNomina(Double totalNomina) {
        this.totalNomina = totalNomina;
    }

    public String getDeduccionesConcepto() {
        return deduccionesConcepto;
    }

    public void setDeduccionesConcepto(String deduccionesConcepto) {
        this.deduccionesConcepto = deduccionesConcepto;
    }

    public Double getDeduccionesImporte() {
        return deduccionesImporte;
    }

    public void setDeduccionesImporte(Double deduccionesImporte) {
        this.deduccionesImporte = deduccionesImporte;
    }

    public Integer getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(Integer diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public Agremiado getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(Agremiado agremiado) {
        this.agremiado = agremiado;
    }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    public Sindicato getSindicato() {
        return sindicato;
    }

    public void setSindicato(Sindicato sindicato) {
        this.sindicato = sindicato;
    }

    public Date getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(Date diaInicial) {
        this.diaInicial = diaInicial;
    }

    public Date getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(Date diaFinal) {
        this.diaFinal = diaFinal;
    }

    public Date getDiaDeRegistro() {
        return diaDeRegistro;
    }

    public void setDiaDeRegistro(Date diaDeRegistro) {
        this.diaDeRegistro = diaDeRegistro;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecibo != null ? idRecibo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Recibo)) {
            return false;
        }
        Recibo other = (Recibo) object;
        return !((this.idRecibo == null && other.idRecibo != null) || (this.idRecibo != null && !this.idRecibo.equals(other.idRecibo)));
    }

    public Date getDiaModificado() {
        return diaModificado;
    }

    public void setDiaModificado(Date diaModificado) {
        this.diaModificado = diaModificado;
    }
    
    @Override
    public String toString() {
        return "Recibo{" + "idRecibo=" + idRecibo + ", totalSindicato=" + totalSindicato + ", totalNomina=" + totalNomina + ", deduccionesConcepto=" + deduccionesConcepto + ", deduccionesImporte=" + deduccionesImporte + ", diasTrabajados=" + diasTrabajados + ", agremiado=" + agremiado.getIdAgremiado() + ", diaInicial=" + diaInicial + ", diaFinal=" + diaFinal + ", diaDeRegistro=" + diaDeRegistro + ", diaModificado=" + diaModificado +", sindicato="+sindicato.getNombreCorto()+", cliente="+clientes.getNombreEmpresa()+'}';
    }
    
    
    
}
