/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.controller.ClienteController;
import com.pradettisanti.payroll.dao.BitacoraDao;
import com.pradettisanti.payroll.pojoh.Bitacora;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.BitacoraService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitacoraServiceImpl implements BitacoraService{
    
    /**
     * Constante para el uso de los logs del sistema
     */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(BitacoraServiceImpl.class);    
    
    
    @Autowired
    private BitacoraDao bitacoraDao;

    @Override
    public void setBitacora(Bitacora bitacora) {
        try {
            bitacoraDao.setBitacora(bitacora);
        } catch (Exception e) {
            LOGGER.error("[Service,BitacoraService] Ocurrio un problema al intentar ingresar un registro dentro de la Bitacora -->  "+e.getMessage(), e);
        }
    }

    @Override
    public List<Bitacora> getBitacora() {
        return bitacoraDao.getBitacora();
    }

    @Override
    public List<Bitacora> getBitacora(Date desde, Date hasta) {
        return bitacoraDao.getBitacora(desde, hasta);
    }

    @Override
    public List<Bitacora> getBitacora(Usuario usuario, Date desde, Date hasta) {
        return bitacoraDao.getBitacora(usuario, desde, hasta);
    }
    
}
