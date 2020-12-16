/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.TipoDomicilio;
import java.util.List;

public interface ContratistaService {
    
    public Integer setContratista( Contratista contratista );
  
    public Integer setDomicilio(EmpresasDomicilio  empresasDomicilio);
    
    public List<Contratista> getContratistas();

    public Contratista getContratista( Integer idContratista );

    public List<Contratista> getContratista( Contratista contratista );
 
    public List<Contratista> getContratistas(Integer idCliente);

    public TipoDomicilio getTipoDomicilio( Short idTipoDomicilio );

    public List<TipoDomicilio> getTipoDomicilio();
   
}
