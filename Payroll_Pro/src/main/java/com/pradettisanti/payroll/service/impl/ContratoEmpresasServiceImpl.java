/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.ContratoEmpresasDao;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import com.pradettisanti.payroll.service.ContratoEmpresasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoEmpresasServiceImpl implements ContratoEmpresasService{
    
    @Autowired
    private ContratoEmpresasDao contratoEmpresasDao;
    
    @Override
    public void setContratoEmpresas(ContratoEmpresas contratoEmpresas) {
        contratoEmpresasDao.setContratoEmpresas(contratoEmpresas);
    }
    
    @Override
    public ContratoEmpresas getContratoEmpresas(Integer idContratoEmpresas) {
       return contratoEmpresasDao.getContratoEmpresas(idContratoEmpresas);
    }

    @Override
    public List<ContratoEmpresas> getContratoEmpresas() {
        return contratoEmpresasDao.getContratoEmpresas();
    }

    @Override
    public List<ContratoEmpresas> getContratoEmpresas(Cliente cliente) {
        return contratoEmpresasDao.getContratoEmpresas(cliente);
    }

    @Override
    public List<ContratoEmpresas> getContratoEmpresas(Contratista contratista) {
        return contratoEmpresasDao.getContratoEmpresas(contratista);
    }

    @Override
    public List<ContratoEmpresas> getContratoEmpresas(Cliente cliente, Contratista contratista) {
        return contratoEmpresasDao.getContratoEmpresas(cliente, contratista);
    }
    
}
