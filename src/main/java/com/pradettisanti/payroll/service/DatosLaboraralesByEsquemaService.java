/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.DatosLaborales;
import javax.servlet.http.HttpServletRequest;

/**
 * Intefaz encarda de validar los campos del los datos laborales por esquema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface DatosLaboraralesByEsquemaService {
    public DatosLaborales getDatosLaborales(DatosLaborales dlBDD, DatosLaborales dlForm, HttpServletRequest request) throws Exception;
    public DatosLaborales getDatosLaborales(DatosLaborales laboralesAntiguos);
}
