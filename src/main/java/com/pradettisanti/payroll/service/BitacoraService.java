/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Bitacora;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.Date;
import java.util.List;

public interface BitacoraService {
  
    public void setBitacora(Bitacora bitacora);

    public List<Bitacora> getBitacora(); 

    public List<Bitacora> getBitacora( Date desde, Date hasta );

    public List<Bitacora> getBitacora( Usuario usuario, Date desde, Date hasta );
}
