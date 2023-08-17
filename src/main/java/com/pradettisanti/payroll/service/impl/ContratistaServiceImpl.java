/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.ContratistaDao;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.TipoDomicilio;
import com.pradettisanti.payroll.service.ContratistaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratistaServiceImpl implements ContratistaService{
    
    @Autowired
    private ContratistaDao contratistaDao;

    @Override
    public Integer setContratista(Contratista contratista) {
        return contratistaDao.setContratista(contratista);
    }

    @Override
    public Integer setDomicilio(EmpresasDomicilio empresasDomicilio) {
        return contratistaDao.setDomicilio(empresasDomicilio);
    }
    
    @Override
    public List<Contratista> getContratistas() {
        return contratistaDao.getContratistas();
    }

    @Override
    public Contratista getContratista(Integer idContratista) {
        return contratistaDao.getContratista(idContratista);
    }

    @Override
    public List<Contratista> getContratista(Contratista contratista) {
        return contratistaDao.getContratista(contratista);
    }
    
    @Override
    public List<Contratista> getContratistas(Integer idCliente){
        return contratistaDao.getContratistas(idCliente);
    }

    @Override
    public TipoDomicilio getTipoDomicilio(Short idTipoDomicilio) {
        return contratistaDao.getTipoDomicilio(idTipoDomicilio);
    }

    @Override
    public List<TipoDomicilio> getTipoDomicilio() {
        return contratistaDao.getTipoDomicilio();
    }
    
    
    
}
