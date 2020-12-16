/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Pojo encargado de representar la tabla con el mismo nombre, permitiendo la administración de los campos segun su esquema de contratación
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 22/02/2018
 */
@Entity
@Table(name = "esquema_campo")
@XmlRootElement

@NamedQueries({
    @NamedQuery(name = "EsquemaCampo.findAll", query = "SELECT e FROM EsquemaCampo e"),
    @NamedQuery(name = "EsquemaCampo.findByEsquemaCampo", query = "SELECT e FROM EsquemaCampo e WHERE e.camposFormulario = :camposFormulario"),
    @NamedQuery(name = "EsquemaCampo.findByEsquemaPago", query = "SELECT e FROM EsquemaCampo e WHERE e.esquemaPago = :esquemaPago")})

public class EsquemaCampo implements Serializable {
    
    private static final long serialVersionUID = 270890L;
    
    @Id
    @NotNull
    @JoinColumn(name = "id_esquema_pago", foreignKey = @ForeignKey(name = "esqCampo_idEsquemaPago"), referencedColumnName = "idesquema_pago")
    @ManyToOne(optional = false)
    private EsquemaPago esquemaPago;
    
    @Id
    @JoinColumn(name = "campos_formulario", foreignKey = @ForeignKey(name = "esqCampo_idCamposFormulario"), referencedColumnName = "id_nombre_campo")
    @ManyToOne(optional = false)
    @NotNull
    private CamposFomulario camposFormulario;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "requerido")
    private boolean requerido;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "obligatorio")
    private boolean obligatorio;

    public EsquemaPago getEsquemaPago() {
        return esquemaPago;
    }

    public void setEsquemaPago(EsquemaPago esquemaPago) {
        this.esquemaPago = esquemaPago;
    }

    public CamposFomulario getCamposFormulario() {
        return camposFormulario;
    }

    public void setCamposFormulario(CamposFomulario camposFormulario) {
        this.camposFormulario = camposFormulario;
    }

    public boolean isRequerido() {
        return requerido;
    }

    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    public boolean isObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.esquemaPago);
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
        final EsquemaCampo other = (EsquemaCampo) obj;
        return Objects.equals(this.esquemaPago, other.esquemaPago);
    }

    @Override
    public String toString() {
        return "EsquemaCampo{" + "esquemaPago=" + esquemaPago + ", camposFormulario=" + camposFormulario + ", requerido=" + requerido + ", obligatorio=" + obligatorio + '}';
    }

    public EsquemaCampo() {
    }

    public EsquemaCampo(CamposFomulario camposFormulario) {
        this.camposFormulario = camposFormulario;
    }

}
