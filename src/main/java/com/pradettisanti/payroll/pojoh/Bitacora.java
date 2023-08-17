/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entidad encargada de representar la tabla bitacora
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "bitacora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bitacora.findAll", query = "SELECT b FROM Bitacora b"),
    @NamedQuery(name = "Bitacora.findByFechas", query = "SELECT b FROM Bitacora b WHERE b.bitacoraPK.idFecha BETWEEN  :fechaDesde AND  :fechaHasta"),
    @NamedQuery(name = "Bitacora.findByUsuario", query = "SELECT b FROM Bitacora b WHERE  b.bitacoraPK.usuario = :usuario AND b.bitacoraPK.idFecha BETWEEN  :fechaDesde AND  :fechaHasta")})
public class Bitacora implements Serializable {

    private static final long serialVersionUID = 270890L;

    /**
     * EmbeddedId BitacoraPK
     */
    @EmbeddedId
    protected BitacoraPK bitacoraPK;
    
    @Size(max = 255)
    @Column(name = "modulo")
    private String modulo;
    
    @Size(max = 255)
    @Column(name = "movimiento")
    private String movimiento;
    
    @Size(max = 255)
    @Column(name = "detalles")
    private String detalles;
    
    @Size(max = 255)
    @Column(name = "ip_acceso")
    private String ipAcceso;
    
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "bitacora_idUsuario"), referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuarioObj;

    public Bitacora() {
    }

    public Bitacora(BitacoraPK bitacoraPK) {
        this.bitacoraPK = bitacoraPK;
    }

    public Bitacora(Date idFecha, int usuario) {
        this.bitacoraPK = new BitacoraPK(idFecha, usuario);
    }

    public BitacoraPK getBitacoraPK() {
        return bitacoraPK;
    }

    public void setBitacoraPK(BitacoraPK bitacoraPK) {
        this.bitacoraPK = bitacoraPK;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getIpAcceso() {
        return ipAcceso;
    }

    public void setIpAcceso(String ipAcceso) {
        this.ipAcceso = ipAcceso;
    }

    public Usuario getUsuarioObj() {
        return usuarioObj;
    }

    public void setUsuarioObj(Usuario usuarioObj) {
        this.usuarioObj = usuarioObj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bitacoraPK != null ? bitacoraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Bitacora)) {
            return false;
        }
        Bitacora other = (Bitacora) object;
        return !((this.bitacoraPK == null && other.bitacoraPK != null) || (this.bitacoraPK != null && !this.bitacoraPK.equals(other.bitacoraPK)));
    }

    @Override
    public String toString() {
        return "com.pradettisanti.payroll.pojoh.Bitacora[ bitacoraPK=" + bitacoraPK + " ]";
    }
    
}
