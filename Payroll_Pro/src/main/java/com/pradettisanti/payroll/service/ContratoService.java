/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import java.util.List;

public interface ContratoService {
  
    public Integer setContrato( Contrato contrato );
 
    public Contrato getContrato(Integer idContrato);

    public List<Contrato> getContratos();
    
    /**
     * Metodo encargado de regresar una lista de contratos que tienen relación con cierto contratista
     * @param idContratista
     * @return List Contrato
     */
    public List<Contrato> getContratosDelColaborador(Integer idContratista);
    
    public List<Contrato> getContratosDelCliente(List<Cliente> clienteList);
    
    public List<Contrato> getContratosDelContratista(List<Contratista> contratistaList);
}
