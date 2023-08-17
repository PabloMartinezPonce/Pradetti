/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import java.util.List;

/**
 * Interfaz encargada de definir los metodos que se deben implementar para poder trabajar con el catalogo de documentos para los servicios
 * @author Gabriela Jaime Jurárez
 */
public interface CatalogoDocumentosDao {
    //Create
    /**
     * Metodo encargado de ingresar/ actulizar un catalogo de documentos
     * @param catalogoDocumental 
     */
    public void setCatalogoDocumentos (CatalogoDocumental catalogoDocumental);
    //Read
    /**
     * Metodo encargado de devolver un catalago documental en base al id ingresado
     * @param id
     * @return CatalogoDocumental
     */
    public CatalogoDocumental getCatalogoDocumental(Integer id);
    /**
     * Metodo encargado de devolver todos los catalogos documentales
     * @return List CatalogoDocumental
     */
    public List<CatalogoDocumental> getCatalogoDocumental();
    /**
     * Metodo encargado de devolver todos los catalogos documentales que son servicio
     * @return List CatalogoDocumental
     */
    public List<CatalogoDocumental> getCatalagoDocumentalDeServicio();
    /**
     * Metodo encargado de devolver todos los catalogos documentales que no son servicios
     * @return List CatalogoDocumental
     */
    public List<CatalogoDocumental> getCatalogoDocumentalDeNoServicio();
    /**
     * Metodo encargado de devolver todos los catalagos documentales en base al nombre del documento
     * @param nombreDocumento
     * @return List CatalogoDocumental
     */
    public List<CatalogoDocumental> getCatalagoDocumentalNombreContrato(String nombreDocumento);
    /**
     * Metodo encargado de devolver una lista de catalagos documentales que peretenecen a un tipo de documento y modulo especifico
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
     * Metodo encargado de devolver una lista de TipoDocumento de un modulo x y que son obligatorios
     * @param idModulo
     * @return List TipoDocumento
     */
    public List<TipoDocumento> getTipoDocumentosObligatorios(Short idModulo);
    /**
     * Metodo encargado de devolver una lista de TipoDocumento de un modulo x que sea servicio
     * @param idModulo
     * @return List TipoDocumento
     */
    public List<TipoDocumento> getTipoDocumentosServicio(Short idModulo);
    /**
     * Metodo encargado de devolver una lista de TipoDocumento de un modulo x que no sea servicio
     * @param idModulo
     * @return List TipoDocumento
     */
    public List<TipoDocumento> getTipoDocumentoNoServicio(Short idModulo);
    /**
     * Metodo encargado de almacenar la información de un tipo de documento
     * @param tipoDocumento 
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento);
}
