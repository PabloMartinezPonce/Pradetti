/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.CatalogoDocumentosDao;
import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.service.CatalogoDocumentalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Calse encargada de definir el comportamiento del servico CatalogoDocumentalService
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 03/01/2018 
 */
@Service
public class CatalogoDocumentalServiceImpl implements CatalogoDocumentalService{

@Autowired
private CatalogoDocumentosDao catalogoDocumentalDao;

    @Override
    public void setCatalogoDocumentos(CatalogoDocumental catalogoDocumental) {
        catalogoDocumentalDao.setCatalogoDocumentos(catalogoDocumental);
    }

    @Override
    public CatalogoDocumental getCatalogoDocumental(Integer id) {
        return  catalogoDocumentalDao.getCatalogoDocumental(id);
    }

    @Override
    public List<CatalogoDocumental> getCatalogoDocumental() {
        return catalogoDocumentalDao.getCatalogoDocumental();
    }

    @Override
    public List<CatalogoDocumental> getCatalagoDocumentalDeServicio() {
        return catalogoDocumentalDao.getCatalagoDocumentalDeServicio();
    }

    @Override
    public List<CatalogoDocumental> getCatalogoDocumentalDeNoServicio() {
        return catalogoDocumentalDao.getCatalogoDocumentalDeNoServicio();
    }

    @Override
    public List<CatalogoDocumental> getCatalagoDocumentalNombreContrato(String nombreDocumento) {
        return catalogoDocumentalDao.getCatalagoDocumentalNombreContrato(nombreDocumento);
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoModulo(Short idModulo) {
        return catalogoDocumentalDao.getTipoDocumentoModulo(idModulo);
    }

    @Override
    public List<Modulo> getModulos() {
        return catalogoDocumentalDao.getModulos();
    }

    public CatalogoDocumentosDao getCatalogoDocumentalDao() {
        return catalogoDocumentalDao;
    }

    public void setCatalogoDocumentalDao(CatalogoDocumentosDao catalogoDocumentalDao) {
        this.catalogoDocumentalDao = catalogoDocumentalDao;
    }

    @Override
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.catalogoDocumentalDao.setTipoDocumento(tipoDocumento);
    }

    @Override
    public CatalogoDocumental getCatalogoDocumental(String url) {
        return null;
    }
    
}
