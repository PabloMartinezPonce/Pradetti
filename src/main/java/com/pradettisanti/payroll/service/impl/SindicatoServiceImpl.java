/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.SindicatoDao;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.service.SindicatoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HEM 
 */
@Service
public class SindicatoServiceImpl implements SindicatoService{
    
    @Autowired
    SindicatoDao sindicatoDao;
    
    @Override
    public Integer setSindicato(Sindicato sindicato) {
        return sindicatoDao.setSindicato(sindicato);
    }

    @Override
    public Sindicato getSindicato(Integer idSindicato) {
        return sindicatoDao.getSindicato(idSindicato);
    }

    @Override
    public List<Sindicato> getSindicatos() {
        return sindicatoDao.getSindicatos();
    }

    @Override
    public List<Sindicato> getSindicatos(Sindicato sindicato) {
        return sindicatoDao.getSindicatos(sindicato);
    }

    @Override
    public List<Sindicato> getSindicatosByNomCorto() {
        return sindicatoDao.getSindicatosByNomCorto();
    }
    
}
