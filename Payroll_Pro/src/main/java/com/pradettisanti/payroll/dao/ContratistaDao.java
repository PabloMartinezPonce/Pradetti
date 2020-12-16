/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.TipoDomicilio;
import java.util.List;

/**
 * Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Contratista
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ContratistaDao {
    //Create
    public Integer setContratista( Contratista contratista );
    public Integer setDomicilio(EmpresasDomicilio  empresasDomicilio);
    //Read
    public List<Contratista> getContratistas();
    public Contratista getContratista( Integer idContratista );
    public List<Contratista> getContratista( Contratista contratista );
    public List<Contratista> getContratistas( Integer idCliente );
    //--TipoDomicilio
    public TipoDomicilio getTipoDomicilio( Short idTipoDomicilio );
    public List<TipoDomicilio> getTipoDomicilio();
    //Update
    //Delete
}
