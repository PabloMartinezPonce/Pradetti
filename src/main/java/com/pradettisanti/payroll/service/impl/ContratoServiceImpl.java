/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.ContratoDao;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import com.pradettisanti.payroll.service.ContratoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoServiceImpl implements ContratoService{
    
    @Autowired
    private ContratoDao contratoDao;
    
    @Override
    public Integer setContrato(Contrato contrato) {
        return  contratoDao.setContrato(contrato);
    }

    @Override
    public Contrato getContrato(Integer idContrato) {
        return contratoDao.getContrato(idContrato);
    }
    
    @Override
    public List<Contrato> getContratos() {
        return contratoDao.getContratos();
    }

    @Override
    public List<Contrato> getContratosDelCliente(List<Cliente> clienteList) {
        return contratoDao.getContratosDelCliente(clienteList);
    }

    @Override
    public List<Contrato> getContratosDelContratista(List<Contratista> contratistaList) {
        return contratoDao.getContratosDelContratista(contratistaList);
    }    
    /**
    * Metodo encargado de regresar una lista de contratos que tienen relación con cierto contratista
    * @param idContratista
    * @return List Contrato
    */
    @Override
    public List<Contrato> getContratosDelColaborador(Integer idContratista) {
        return contratoDao.getContratosDelColaborador(idContratista);
    }
}
