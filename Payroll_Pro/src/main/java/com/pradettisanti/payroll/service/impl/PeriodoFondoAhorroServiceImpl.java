/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.PeriodoFondoAhorroDao;
import com.pradettisanti.payroll.pojoh.PeriodoFA;
import com.pradettisanti.payroll.service.PeriodoFondoAhorroService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author IT Seekers
 */
@Service
public class PeriodoFondoAhorroServiceImpl implements PeriodoFondoAhorroService{
    
    @Autowired
    private PeriodoFondoAhorroDao periodoFADao;
    
//    public PeriodoFondoAhorroDao getPFAService(){
//        return periodoFADao;
//    }
//    
//    
//    public void setPFAService(PeriodoFondoAhorroDao periodoFAService){
//        this.periodoFADao = periodoFAService;
//    }

    @Override
    public Integer setPeriodoFondoAhorro(PeriodoFA periodoFondoAhorro) {
        return periodoFADao.setPeriodoFondoAhorro(periodoFondoAhorro);
    }

    @Override
    public PeriodoFA getPeriodoFondoAhorro(Integer id) {
        return periodoFADao.getPeriodoFondoAhorro(id);
    }

    @Override
    public List<PeriodoFA> getPeriodosFondoAhorro() {
        return periodoFADao.getPeriodosFondoAhorro();
    }

    @Override
    public List<PeriodoFA> getPeriodosByDates(Date fechaInicioFA, Date fechaFinFA, Integer idAgremiado) {
        return periodoFADao.getPeriodosByDates(fechaInicioFA, fechaFinFA, idAgremiado);
    }
     
    @Override
    public Date getPrimerFechaFA(Integer idAgremiado){
        return periodoFADao.getPrimerFechaFA(idAgremiado);
    }
    
    @Override
    public Date getUltimaFechaFA(Integer idAgremiado){
        return periodoFADao.getUltimaFechaFA(idAgremiado);
    }

    @Override
    public Date getFechaInicioPeriodoFA(Integer idPeriodoFA) {
        return periodoFADao.getFechaInicioPeriodoFA(idPeriodoFA);
    }

    @Override
    public Date getFechaFinPeriodoFA(Integer idPeriodoFA) {
       return periodoFADao.getFechaFinPeriodoFA(idPeriodoFA);
    }

    @Override
    public Short getPorcentajePeriodo(Integer idAgremiado, Date fechaIni, Date fechaFin) {
        return periodoFADao.getPorcentajePeriodo(idAgremiado, fechaIni, fechaFin);
    }

    @Override
    public Date getFechaIniPeriodo(Integer idAgremiado, Date fechaIni, Date fechaFin) {
        return periodoFADao.getFechaIniPeriodo(idAgremiado, fechaIni, fechaFin);
    }

    @Override
    public Date getFechaFinPeriodo(Integer idPeriodo) {
        return periodoFADao.getFechaFinPeriodo(idPeriodo);
    }
    
}
