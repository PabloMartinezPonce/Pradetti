/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.RelacionContrato;
import com.pradettisanti.payroll.pojoh.TipoDomicilio;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.List;

public interface ClienteService {

    public Integer setCliente(Cliente cliente);
  
    public Integer setDomicilio(EmpresasDomicilio  empresasDomicilio);

    public List<Cliente> getClientes();

    public Cliente getCliente( Integer idCliente );

    public List<Cliente> getCliente( Cliente cliente );

    public List<RelacionContrato> getRelacionContrato( Cliente cliente );

    public TipoDomicilio getTipoDomicilio( Short idTipoDomicilio );

    public List<TipoDomicilio> getTipoDomicilio();
 
    public Cliente getCliente(Usuario usuario);
}
