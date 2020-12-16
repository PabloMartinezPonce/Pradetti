/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad encargada de represnetar a la tabla notificaciones
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Entity
@Table(name = "notificaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificaciones.findAll", query = "SELECT n FROM Notificaciones n"),
    @NamedQuery(name = "Notificaciones.findByIdNotificaciones", query = "SELECT n FROM Notificaciones n WHERE n.idNotificaciones = :idNotificaciones"),
    @NamedQuery(name = "Notificaciones.findByDescripcion", query = "SELECT n FROM Notificaciones n WHERE n.descripcion = :descripcion")})
public class Notificaciones implements Serializable {

    @JoinTable(name = "usuario_notificacion", joinColumns = {
        @JoinColumn(name = "notificaciones", foreignKey = @ForeignKey(name = "uNotificacion_idNotificaciones"), referencedColumnName = "id_notificaciones")}, inverseJoinColumns = {
        @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "uNotificacion_idUsuario"), referencedColumnName = "id_usuario")})
    @ManyToMany
    private List<Usuario> usuarioList;

    private static final long serialVersionUID = 270890L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_notificaciones")
    private Short idNotificaciones;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;

    public Notificaciones() {
    }

    public Notificaciones(Short idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public Notificaciones(Short idNotificaciones, String descripcion) {
        this.idNotificaciones = idNotificaciones;
        this.descripcion = descripcion;
    }

    public Short getIdNotificaciones() {
        return idNotificaciones;
    }

    public void setIdNotificaciones(Short idNotificaciones) {
        this.idNotificaciones = idNotificaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNotificaciones != null ? idNotificaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Notificaciones)) {
            return false;
        }
        Notificaciones other = (Notificaciones) object;
        return !((this.idNotificaciones == null && other.idNotificaciones != null) || (this.idNotificaciones != null && !this.idNotificaciones.equals(other.idNotificaciones)));
    }

    @Override
    public String toString() {
        return "Notificaciones{" + "usuarioList=" + usuarioList + ", idNotificaciones=" + idNotificaciones + ", descripcion=" + descripcion + '}';
    }
    
}
