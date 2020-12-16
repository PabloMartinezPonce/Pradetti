/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.SolicitudBajaDao;
import com.pradettisanti.payroll.pojoh.DocumentosBaja;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.MotivoBaja;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoDocumentoPK;
import com.pradettisanti.payroll.pojoh.TipoFiniquito;
import com.pradettisanti.payroll.service.SolicitudBajaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HEM 
 */
@Service
public class SolicitudBajaServiceImpl implements SolicitudBajaService{
    
    @Autowired
    SolicitudBajaDao solicitudBajaDao;
    
    @Override
    public void setDocumentoBaja(DocumentosBaja documentosBaja) {
        solicitudBajaDao.setDocumentoBaja(documentosBaja);
    }

    @Override
    public List<DocumentosBaja> getDocumentosBaja(SolicitudBaja solicitudBaja, TipoDocumentoPK tipoDocumentoPK) {
        return solicitudBajaDao.getDocumentosBaja(solicitudBaja, tipoDocumentoPK);
    }

    @Override
    public List<DocumentosBaja> getDocumentosBaja(SolicitudBaja solicitudBaja) {
        return solicitudBajaDao.getDocumentosBaja(solicitudBaja);
    }

    @Override
    public Modulo getModulo(Short idModulo) {
        return solicitudBajaDao.getModulo(idModulo);
    }

    @Override
    public List<Modulo> getModulos() {
        return solicitudBajaDao.getModulos();
    }

    @Override
    public MotivoBaja getMotivoBaja(Short idMotivoBaja) {
        return solicitudBajaDao.getMotivoBaja(idMotivoBaja);
    }

    @Override
    public List<MotivoBaja> getMotivoBaja() {
        return solicitudBajaDao.getMotivoBaja();
    }

    @Override
    public TipoDocumento getTipoDocumento(TipoDocumentoPK tipoDocumentoPK) {
        return solicitudBajaDao.getTipoDocumento(tipoDocumentoPK);
    }

    @Override
    public List<TipoDocumento> getTipoDocumento(Modulo modulo) {
        return solicitudBajaDao.getTipoDocumento(modulo);
    }

    @Override
    public List<TipoDocumento> getTipoDocumento(List<Modulo> modulo) {
        return solicitudBajaDao.getTipoDocumento(modulo);
    }

    @Override
    public TipoFiniquito getTipoFiniquito(Short idTipoFiniquito) {
        return solicitudBajaDao.getTipoFiniquito(idTipoFiniquito);
    }

    @Override
    public List<TipoFiniquito> getTipoFiniquito() {
        return solicitudBajaDao.getTipoFiniquito();
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoSolicitudBaja(Integer idSolBaja, Short idModulo) {
        return solicitudBajaDao.getTipoDocumentoSolicitudBaja(idSolBaja, idModulo);
    }
    
    @Override
    public DocumentosBaja getDocumentoBaja(Integer idSolicitudBaja, Integer idTipoDocumento){
        return solicitudBajaDao.getDocumentoBaja(idSolicitudBaja, idTipoDocumento);
    }
    
    @Override
    public void deleteSolicitudBaja(SolicitudBaja solicitudBaja){
        solicitudBajaDao.deleteSolicitudBaja(solicitudBaja);
    }
    
    @Override
    public void deleteDocumentoBaja(DocumentosBaja documentobaja){
        solicitudBajaDao.deleteDocumentoBaja(documentobaja);
    }

    @Override
    public List<DocumentosBaja> getDocumentosBaja(Integer idSolicitudBaja, Short idModulo) {
       return solicitudBajaDao.getDocumentosBaja(idSolicitudBaja, idModulo);
    }
}
