/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import java.util.List;

/**
 * Intefaz de definición de comportamiento de los servicios con conexión al DAO
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 03/01/2018
 */
public interface CatalogoDocumentalService {
   
    /* Metodo encargado de ingresar/ actualizar un objeto del tipo catalogo documental
     * @param catalogoDocumental 
     */
    public void setCatalogoDocumentos (CatalogoDocumental catalogoDocumental);
    /**
     * Metodo encargado de devolver el catalago documental relacionado con el id
     * @param id
     * @return CatalogoDocumental
     */
    public CatalogoDocumental getCatalogoDocumental(Integer id);
    /**
     * Metodo encargado de devolver todos los catalogos documentales registrados en el sistema
     * @return List CatalogoDocumental
     */
    public List<CatalogoDocumental> getCatalogoDocumental();
    /**
     * Metodo encargado de devolver todos los catalogos documentales que son del tipo servicio
     * @return List CatalogoDocumental
     */
    public List<CatalogoDocumental> getCatalagoDocumentalDeServicio();
    /**
     * Metodo encargado de devolver todos los catalogos documentales que no son del tipo servicios
     * @return List CatalogoDocumental
     */
    public List<CatalogoDocumental> getCatalogoDocumentalDeNoServicio();
    /**
     * Metodo encargado de devolver todos los catalagos documentales en base al nombre del catalogo documental
     * @param nombreDocumento
     * @return List CatalogoDocumental
     */
    public List<CatalogoDocumental> getCatalagoDocumentalNombreContrato(String nombreDocumento);
    /**
     * Metodo encargado de devolver una lista de catalagos documentales que pertenecen a un modulo en especifico
     * @param idModulo
     * @return List CatalogoDocumental
     */
    public List<TipoDocumento> getTipoDocumentoModulo(Short idModulo);
    /**
     * Metodo encargado de devolver todos los modulos registrados
     * @return List Modulo
     */
    public List<Modulo> getModulos();    
      /**
     * Metodo engargado de almacenar la información de un tipo de documento
     * @param tipoDocumento 
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento);
    /**
     * Metodo encargado de buscar un catalogo documental con respecto a su url
     * @param url
     * @return CatalogoDocumental
     */
    public CatalogoDocumental getCatalogoDocumental(String url);
}
