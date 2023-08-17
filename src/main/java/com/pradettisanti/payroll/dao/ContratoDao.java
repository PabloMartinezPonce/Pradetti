/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import java.util.List;
/**
 * Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Contrato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ContratoDao {
    //Create
    public Integer setContrato( Contrato contrato );
    //Read
    public Contrato getContrato(Integer idContrato);
    public List<Contrato> getContratos();
    /**
     * Metodo que regresa una los contratos filtrados por el contratista que tiene ligado el colaborador
     * @param idContratista
     * @return List Contrato
     */
    public List<Contrato> getContratosDelColaborador(Integer idContratista);
    public List<Contrato> getContratosDelCliente(List<Cliente> clienteList);
    public List<Contrato> getContratosDelContratista(List<Contratista> contratistaList);
    //Update
    //Delete
}
