/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.AgremiadoDao;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import com.pradettisanti.payroll.service.ReportesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de implementar la conexión del servicio de reportes con el DAO
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 12/02/2018
 */
@Service
public class ReportesServiceImpl implements  ReportesService{

    @Autowired
    private AgremiadoDao agremiadoDao;
    
    
    @Override
    public List<Agremiado> getAgremiados(StatusAgremiado sa, Contratista contratista, Cliente cliente) {
        return agremiadoDao.getAgremiados(sa, contratista, cliente);
    }

    @Override
    public List<Agremiado> getAgremiados(StatusAgremiado sa, Cliente cliente) {
       return agremiadoDao.getAgremiados(sa, cliente);
    }

    public AgremiadoDao getAgremiadoDao() {
        return agremiadoDao;
    }

    public void setAgremiadoDao(AgremiadoDao agremiadoDao) {
        this.agremiadoDao = agremiadoDao;
    }
    
}
