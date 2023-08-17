/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.Bitacora;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.Date;
import java.util.List;

/**
 *  Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Bitacora
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface BitacoraDao {
    
    //Create
    public void setBitacora(Bitacora bitacora);
    //Read
    public List<Bitacora> getBitacora(); 
    public List<Bitacora> getBitacora( Date desde, Date hasta );
    public List<Bitacora> getBitacora( Usuario usuario, Date desde, Date hasta );
    //Update
    //Delete
    
}
