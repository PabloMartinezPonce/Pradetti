/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.Sindicato;
import java.util.List;

/**
 *  Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Sindicato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface SindicatoDao {
    
    //Create
    public Integer setSindicato( Sindicato sindicato );
    //Read
    public Sindicato getSindicato( Integer idSindicato );
    public List<Sindicato> getSindicatosByNomCorto();
    public List<Sindicato> getSindicatos();
    public List<Sindicato> getSindicatos( Sindicato sindicato );
    //Update
    //Delete
}
