/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.RelacionContrato;
import com.pradettisanti.payroll.pojoh.TipoDomicilio;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.List;

/**
 * Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Cliente
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ClienteDao {
    //Create
    public Integer setCliente(Cliente cliente);
    public Integer setDomicilio(EmpresasDomicilio  empresasDomicilio);
    //Read
    public List<Cliente> getClientes();
    public Cliente getCliente( Integer idCliente );
    public List<Cliente> getCliente( Cliente cliente );
    public List<RelacionContrato> getRelacionContrato( Cliente cliente );
    //--TipoDomicilio
    public TipoDomicilio getTipoDomicilio( Short idTipoDomicilio );
    public List<TipoDomicilio> getTipoDomicilio();
    public Cliente getCliente(Usuario usuario);
    //Update
    //Delete
}
