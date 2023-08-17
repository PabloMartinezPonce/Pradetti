/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;
import com.pradettisanti.payroll.Utility.DelContrato;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import java.util.List;

/**
 * Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.ContratoEmpresas
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ContratoEmpresasDao {
    //Create
    public void setContratoEmpresas( ContratoEmpresas contratoEmpresas );
    //Read
    public ContratoEmpresas getContratoEmpresas(Integer idContratoEmpresas);
    public List<ContratoEmpresas> getContratoEmpresas();
    public List<ContratoEmpresas> getContratoEmpresas( Cliente cliente );
    public List<ContratoEmpresas> getContratoEmpresas( Contratista contratista );
    public List<ContratoEmpresas> getContratoEmpresas( Cliente cliente, Contratista contratista );
    /**
     * Metodo encargado de regresar una lista det tipo DelContrato apartir de un cliente
     * @param idCliente
     * @return List DelContrato
     */
    public List<DelContrato> getContratosDelCliente( Integer idCliente );
    /**
     * Metodo encargado de devolver un objeto ContratoEmpresas bajo un nombre de contrato
     * @param nombreContrato
     * @return ContratoEmpresas
     */
    public ContratoEmpresas getContratoEmpresasByIdName(String nombreContrato);
    //Update
    //Delete
}
