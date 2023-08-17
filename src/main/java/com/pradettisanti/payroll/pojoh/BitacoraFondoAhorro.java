/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

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
 * Entidad encargada de representar la tabla bitacora_fondo_ahorro
 * @author Gabriela Jaime
 * @since 13/05/2019
 */
@Entity
@Table(name = "bitacora_fondo_ahorro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BitacoraFondoAhorro.findAll", query = "SELECT b FROM BitacoraFondoAhorro b"),
    @NamedQuery(name = "BitacoraFondoAhorro.findByIdBitacoraFA", query = "FROM BitacoraFondoAhorro b WHERE b.idBitacoraFA = :idBitacoraFA"),
    @NamedQuery(name = "BitacoraFondoAhorro.findByAgremiado", query = "FROM BitacoraFondoAhorro b WHERE b.agremiado = :agremiado"),
    @NamedQuery(name = "BitacoraFondoAhorro.findFirstDateByAgremiado", query = "SELECT MIN(b.fondoDeAhorroFecha) FROM BitacoraFondoAhorro b WHERE b.agremiado = :agremiado"),
    @NamedQuery(name = "BitacoraFondoAhorro.findPorcentByAgremiado", query = "SELECT b.porcentaje FROM BitacoraFondoAhorro b WHERE b.agremiado = :agremiado AND b.fondoDeAhorroFecha BETWEEN :diaRegistroDesde AND :diaRegistroHasta"),
    @NamedQuery(name = "BitacoraFondoAhorro.findDateIniByPeriodo", query = "SELECT b.fondoDeAhorroFecha FROM BitacoraFondoAhorro b WHERE b.agremiado = :agremiado AND b.fondoDeAhorroFecha BETWEEN :diaRegistroDesde AND :diaRegistroHasta")})

public class BitacoraFondoAhorro {
    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bitacora_fa")
    private Integer idBitacoraFA;
    
    @JoinColumn(name = "agremiado", foreignKey = @ForeignKey(name = "bitacoraFA_idAgremiado"), referencedColumnName = "id_agremiado")
    @ManyToOne(optional = false)
    private Agremiado agremiado;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "fa_fecha")
    @Temporal(TemporalType.DATE)
    private Date fondoDeAhorroFecha;
    
    @Size(max = 50)
    @Column(name = "salario_diario")
    private String salarioDiario;
    
    @Size(max = 50)
    @Column(name = "salario_diario_integrado")
    private String salarioDiarioIntegrado;
    
    @Size(max = 50)
    @Column(name = "salarios_imss")
    private String salariosImss;
   
    @Size(max = 50)
    @Column(name = "sueldo_mensual")
    private String sueldoMensual;
    
    @Column(name = "porcentaje")    
    private Short porcentaje;
    
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "bitacoraFA_idUsuario"), referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioObj;
    
    @Size(max = 200)
    @Column(name = "observaciones")
    private String observaciones;
    
    public BitacoraFondoAhorro() {
    }

    public Integer getIdBitacoraFA() {
        return idBitacoraFA;
    }

    public void setIdBitacoraFA(Integer idBitacoraFA) {
        this.idBitacoraFA = idBitacoraFA;
    }

    public Agremiado getAgremiado() {
        return agremiado;
    }

    public void setAgremiado(Agremiado agremiado) {
        this.agremiado = agremiado;
    }

    public Date getFondoDeAhorroFecha() {
        return fondoDeAhorroFecha;
    }

    public void setFondoDeAhorroFecha(Date fondoDeAhorroFecha) {
        this.fondoDeAhorroFecha = fondoDeAhorroFecha;
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

    public String getSalariosImss() {
        return salariosImss;
    }

    public void setSalariosImss(String salariosImss) {
        this.salariosImss = salariosImss;
    }

    public String getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(String sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public Short getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Short porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Usuario getUsuarioObj() {
        return usuarioObj;
    }

    public void setUsuarioObj(Usuario usuarioObj) {
        this.usuarioObj = usuarioObj;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BitacoraFondoAhorro)) {
            return false;
        }
        BitacoraFondoAhorro other = (BitacoraFondoAhorro) object;
        return !((this.idBitacoraFA == null && other.idBitacoraFA != null) || (this.idBitacoraFA != null && !this.idBitacoraFA.equals(other.idBitacoraFA)));
    }
    
    @Override
    public String toString() {
        return "BitacoraFondoAhorro{" + "idBitacoraFA=" + idBitacoraFA + ", agremiado=" + agremiado + ", fondoDeAhorroFecha=" + fondoDeAhorroFecha + ", salarioDiario=" + salarioDiario + ", salarioDiarioIntegrado=" + salarioDiarioIntegrado + ", salariosImss=" + salariosImss + ", sueldoMensual=" + sueldoMensual + ", porcentaje=" + porcentaje + ", usuarioObj=" + usuarioObj + ", agremiado=" + agremiado.getIdAgremiado() + ", observaciones=" + observaciones + '}';
    }
}
