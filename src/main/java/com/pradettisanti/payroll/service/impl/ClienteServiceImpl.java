/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.ClienteDao;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.RelacionContrato;
import com.pradettisanti.payroll.pojoh.TipoDomicilio;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl  implements ClienteService{
    
    @Autowired
    private ClienteDao clienteDao;

    @Override
    public Integer setCliente(Cliente cliente) {
        return clienteDao.setCliente(cliente);
    }
    
    @Override
    public Integer setDomicilio(EmpresasDomicilio empresasDomicilio) {
        return clienteDao.setDomicilio(empresasDomicilio);
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteDao.getClientes();
    }

    @Override
    public Cliente getCliente(Integer idCliente) {
        return clienteDao.getCliente(idCliente);
    }

    @Override
    public List<Cliente> getCliente(Cliente cliente) {
        return clienteDao.getCliente(cliente);
    }

    @Override
    public List<RelacionContrato> getRelacionContrato(Cliente cliente) {
        return clienteDao.getRelacionContrato(cliente);
    }

    @Override
    public TipoDomicilio getTipoDomicilio(Short idTipoDomicilio) {
        return clienteDao.getTipoDomicilio(idTipoDomicilio);
    }

    @Override
    public List<TipoDomicilio> getTipoDomicilio() {
        return clienteDao.getTipoDomicilio();
    }
    
    @Override
    public Cliente getCliente(Usuario usuario){
        return clienteDao.getCliente(usuario);
    }
}
