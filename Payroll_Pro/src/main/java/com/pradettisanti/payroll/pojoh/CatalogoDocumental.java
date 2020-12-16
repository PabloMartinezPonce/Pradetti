/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.pojoh;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *Entidad encargada de representar la tabla catalogo_documental
 * @author Gabriela Jaime Juárez
 * @version 1
 * @since 2017/12/26
 */
@Entity
@Table(name = "catalogo_documental_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoDocumental.findAll", query = "FROM CatalogoDocumental"),
    @NamedQuery(name = "CatalogoDocumental.findById", query = "FROM CatalogoDocumental c WHERE c.idCatalogo = :idCatalogoDocumental"),
    @NamedQuery(name = "CatalogoDocumental.findByNombreDocumento", query = "FROM CatalogoDocumental c WHERE c.nombreDocumento = :nombreDocumento"),
    @NamedQuery(name = "CatalogoDocumental.findByServicio", query = "FROM CatalogoDocumental c WHERE c.servicio = 1"),
    @NamedQuery(name = "CatalogoDocumental.findByNoServicio", query = "FROM CatalogoDocumental c WHERE c.servicio = 0")})

public class CatalogoDocumental implements Serializable{
    
    private static final long serialVersionUID = 270890L;
    
    // Mapea el campo id_catalogo_documental con las anotaciones @Id @GeneratedValue @Basic @Column asignandolo a una variable de tipo Integer
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id_catalogo")  
    private Integer idCatalogo;

    // Mapea el campo nombre_documento con las anotaciones @Size @Column, asignandolo a una variable de tipo String
    @Size(max = 100)
    @Column(name = "nombre_documento")
    private String nombreDocumento;
    
    // Mapea el campo servicio con las anotaciones @Basic @NotNull @Column, asignandolo a una variable de tipo boolean
    @Basic(optional = false)
    @NotNull
    @Column(name = "servicio")
    private boolean servicio;
    
    // Mapea el campo servicio_configurado con las anotaciones @Basic @Column, asignandolo a una variable de tipo boolean
    //Indica si se requiere configuración en el documento
    @Basic(optional = false)
    @Column(name = "servicio_configurado")
    private boolean servicioConfigurado;
    
    // Mapea el campo plantilla con las anotaciones @Basic @Column, asignandolo a una variable de tipo boolean
    @Basic(optional = false)
    @Column(name = "plantilla")
    private boolean plantilla;
    
    // Mapea el campo url con las anotaciones @Lob @Size @Column, asignandolo a una variable de tipo String
    @Lob
    @Size(max = 2147483647) //Es el max que tiene contrato de la tabla contrato
    @Column(name = "url")
    private String url;
    
    // Mapea el campo creado con las anotaciones @Basic @NotNull @Column y @Temporal, asignandolo a una variable de tipo Date
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    // Mapea el campo modificado con las anotaciones @Column @Temporal, asignandolo a una variable de tipo Date
    @Column(name = "modificado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    
    // Mapea la relacion de la tabla contrato_empresas y le asigna una Lista de tipo ContratoEmpresas
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "catalogoDocumentalList") 
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ContratoEmpresas> contratoEmpresasList;
    
    @JsonIgnore
    @OneToMany(mappedBy = "catalogoDocumentalObj", fetch=FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TipoDocumento> tipoDocumentosList;

    /**
     * Metodo encargado de obtener el Id del campo id_catalogo_documental
     * @return idCatalogoDocumental
     */
    public Integer getIdCatalogo() {
        return idCatalogo;
    }
    
    /**
     * Meotodo encargado de ingresar el Id del campo id_catalogo_documental
     * @param idCatalogo
     */
    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }
    
    /**
     * Metodo encargado de obtener el texto del campo nombre_documento
     * @return nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }
    
    /**
     * Meotodo encargado de ingresar el texto del campo id_catalogo_documental
     * @param nombreDocumento 
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    
    /**
     * Metodo encargado de obtener el campo servicio
     * @return servicio
     */
    public boolean getServicio() {
        return servicio;
    }
    
    /**
     * Meotodo encargado de ingresar al campo servicio
     * @param servicio 
     */
    public void setServicio(boolean servicio) {
        this.servicio = servicio;
    }
    
    /**
     * Metodo encargado de obtener el campo servicio_configurado
     * @return servicioConfigurado
     */
    public boolean getServicioConfigurado() {
        return servicioConfigurado;
    }
    
    /**
     *  Meotodo encargado de ingresar el campo servicio_configurado
     * @param servicioConfigurado 
     */
    public void setServicioConfigurado(boolean servicioConfigurado) {
        this.servicioConfigurado = servicioConfigurado;
    }
    
    /**
     * Metodo encargado de obtener el campo servicio_configurado
     * @return plantilla
     */
    public boolean getPlantilla() {
        return plantilla;
    }
    
    /**
     * Meotodo encargado de ingresar al campo plantilla
     * @param plantilla 
     */
    public void setPlantilla(boolean plantilla) {
        this.plantilla = plantilla;
    }
    
    /**
     * Metodo encargado de obtener el campo url
     * @return url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * Meotodo encargado de ingresar al campo url
     * @param url 
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * Metodo encargado de obtener el campo creado
     * @return creado
     */
    public Date getCreado() {
        return creado;
    }
    
    /**
     * Meotodo encargado de ingresar al campo creado
     * @param creado 
     */
    public void setCreado(Date creado) {
        this.creado = creado;
    }
    
    /**
     * Metodo encargado de obtener el campo modificado
     * @return modificado
     */
    public Date getModificado() {
        return modificado;
    }
    
    /**
     * Meotodo encargado de ingresar al campo modificado
     * @param modificado 
     */
    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    /**
     * Metodo encargado de obtener una lista de tipo ContratoEmpresas
     * @return contratoEmpresasList
     */
    public List<ContratoEmpresas> getContratoEmpresasList() {
        return contratoEmpresasList;
    }
    
    /**
     * Meotodo encargado de ingresar una lista de tipo ContratoEmpresas
     * @param contratoEmpresasList 
     */
    public void setContratoEmpresasList(List<ContratoEmpresas> contratoEmpresasList) {
        this.contratoEmpresasList = contratoEmpresasList;
    }

    public List<TipoDocumento> getTipoDocumentosList() {
        return tipoDocumentosList;
    }

    public void setTipoDocumentosList(List<TipoDocumento> tipoDocumentosList) {
        this.tipoDocumentosList = tipoDocumentosList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.idCatalogo);
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
        final CatalogoDocumental other = (CatalogoDocumental) obj;
        return Objects.equals(this.idCatalogo, other.idCatalogo);
    }

    @Override
    public String toString() {
        return "CatalogoDocumental{" + "idCatalogo=" + idCatalogo + ", nombreDocumento=" + nombreDocumento + ", servicio=" + servicio + ", servicioConfigurado=" + servicioConfigurado + ", plantilla=" + plantilla + ", url=" + url + ", creado=" + creado + ", modificado=" + modificado + '}';
    }
    
}
