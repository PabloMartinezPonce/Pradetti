/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import java.util.List;

public interface ContratoEmpresasService {
  
    public void setContratoEmpresas( ContratoEmpresas contratoEmpresas );

    public ContratoEmpresas getContratoEmpresas(Integer idContratoEmpresas);
    
    public List<ContratoEmpresas> getContratoEmpresas();

    public List<ContratoEmpresas> getContratoEmpresas( Cliente cliente );

    public List<ContratoEmpresas> getContratoEmpresas( Contratista contratista );

    public List<ContratoEmpresas> getContratoEmpresas( Cliente cliente, Contratista contratista );
}
