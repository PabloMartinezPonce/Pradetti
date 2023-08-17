/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Entidad encargada de representar la tabla recibo_solicitado
 * @author Gabriela Jaime gabriela.jaime@it-seekers.com
 */

@Entity
@Table(name = "recibo_solicitado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReciboSolicitado.findAll", query = "FROM ReciboSolicitado r"),
    @NamedQuery(name = "ReciboSolicitado.findByIdReciboSolicitado", query = "FROM ReciboSolicitado r WHERE r.idSolicitud = :idSolicitud"),
    @NamedQuery(name = "ReciboSolicitado.findByReciboUsClt", query = "FROM ReciboSolicitado r WHERE r.recibo = :recibo and r.usuario = :usuario and r.idCliente = :cliente"),
    @NamedQuery(name = "ReciboSolicitado.findByCltFinicialFfinal", query = "FROM ReciboSolicitado r WHERE r.fechaSolicitado BETWEEN :diaRegistroDesde AND :diaRegistroHasta")})

public class ReciboSolicitado implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;
    
    @JoinColumn(name = "recibo", foreignKey = @ForeignKey(name = "reciboSolicitado_idRecibo"), referencedColumnName = "id_recibo")
    @ManyToOne(optional = false)
    private Recibo recibo;
    
    @JoinColumn(name = "usuario", foreignKey = @ForeignKey(name = "reciboSolicitado_idUsario"), referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_solicitado")
    private Date fechaSolicitado;
    
    @Size(max = 250)
    @Column(name = "observaciones")
    private String observaciones;
    
    //@NotNull
    //@Column(name = "relacion_cliente")
    @JoinColumn( name = "relacion_cliente", foreignKey = @ForeignKey(name = "reciboSolicitado_relacion_cliente"), referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;

    public ReciboSolicitado(Recibo recibo, Usuario usuario, Date fechaSolicitado, String observaciones, Cliente idCliente) {
        this.recibo = recibo;
        this.usuario = usuario;
        this.fechaSolicitado = fechaSolicitado;
        this.observaciones = observaciones;
        this.idCliente = idCliente;
    }
        
    
    public ReciboSolicitado() {
    }

    public ReciboSolicitado(Integer reciboSolicitado) {
        this.idSolicitud = reciboSolicitado;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    
    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Date getFechaSolicitado() {
        return fechaSolicitado;
    }

    public void setFechaSolicitado(Date fechaSolicitado) {
        this.fechaSolicitado = fechaSolicitado;
    }
    
    public String getObservaciones(){
        return observaciones;
    }
    
    public void setObservaciones(String observaciones){
        this.observaciones = observaciones;
    }
    
    public Cliente getCliente(){
        return idCliente;
    }
    
    public void setCliente(Cliente idCliente){
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "ReciboSolicitado{" + "idSolicitud=" + idSolicitud + ", recibo=" + recibo + ", usuario=" + usuario + ", fechaSolicitado=" + fechaSolicitado + ", observaciones=" + observaciones + ", idCliente=" + idCliente + '}';
    }
    
    
}

