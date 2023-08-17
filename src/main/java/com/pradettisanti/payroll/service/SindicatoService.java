/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Sindicato;
import java.util.List;

public interface SindicatoService {
    
    public Integer setSindicato( Sindicato sindicato );

    public Sindicato getSindicato( Integer idSindicato );

    public List<Sindicato> getSindicatos();
    
    public List<Sindicato> getSindicatosByNomCorto();

    public List<Sindicato> getSindicatos( Sindicato sindicato );
}
