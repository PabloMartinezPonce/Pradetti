/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.ZonaSm;
import java.util.List;

/**
 * Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.ZonaSm
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ZonaSmDao {
    //Create
    public Short setZonaSM( ZonaSm zonaSm );
    //Read
    public List<ZonaSm> getZonaSm();
    public ZonaSm getZonaSm(Short i);
    public List<ZonaSm> getZonaSm( ZonaSm zonaSm );    
    //Update
    //Delete
}
