/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.ZonaSmDao;
import com.pradettisanti.payroll.pojoh.ZonaSm;
import com.pradettisanti.payroll.service.ZonasSmService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HEM 
 */
@Service
public class ZonasSmServiceImpl implements ZonasSmService{
    
    @Autowired
    ZonaSmDao zonaSmDao; //PeriodoFondoAhorroDao
    
    @Override
    public Short setZonaSM(ZonaSm zonaSm) {
        return zonaSmDao.setZonaSM(zonaSm);
    }
    
    
    @Override
    public ZonaSm getZonaSM(Short id) {
       return zonaSmDao.getZonaSm(id);
    }

    @Override
    public List<ZonaSm> getZonaSm() {
        return zonaSmDao.getZonaSm();
    }

    @Override
    public List<ZonaSm> getZonaSm(ZonaSm zonaSm) {
        return zonaSmDao.getZonaSm(zonaSm);
    }

    
    
}
