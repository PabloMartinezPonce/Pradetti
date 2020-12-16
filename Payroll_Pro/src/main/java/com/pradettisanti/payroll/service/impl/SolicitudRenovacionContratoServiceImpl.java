/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.SolicitudRenovacionContratoDao;
import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import com.pradettisanti.payroll.service.SolicitudRenovacionContratoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HEM 
 */
@Service
public class SolicitudRenovacionContratoServiceImpl  implements SolicitudRenovacionContratoService{
    
    @Autowired
    SolicitudRenovacionContratoDao solicitudRenovacionContratoDao;
    
    @Override
    public SolicitudRenovacionContrato getSolicitudRenovacionContrato(SolicitudRenovacionContrato solicitudRenovacionContrato) {
        return solicitudRenovacionContratoDao.getSolicitudRenovacionContrato(solicitudRenovacionContrato);
    }

    @Override
    public TipoContrato getTipoContrato(Short idTipoContrato) {
        return solicitudRenovacionContratoDao.getTipoContrato(idTipoContrato);
    }

    @Override
    public List<TipoContrato> getTipoContrato() {
        return solicitudRenovacionContratoDao.getTipoContrato();
    }

    @Override
    public void setSolicitudRenovacionContrato(SolicitudRenovacionContrato solicitudRenovacionContrato) {
        solicitudRenovacionContratoDao.setSolicitudRenovacionContrato(solicitudRenovacionContrato);
    }
    
}
