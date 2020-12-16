/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import java.util.List;

/**
 * Service encargado de conectarse a las diversas fuentes de datos para obtener la información requerida por los reportes
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @since 2018/02/02
 * @version 1
 */
public interface ReportesService {
    /**
     * Metodo encargado de devolver un conjunto colaboradores que tienen relación al estar en el mismo status, y con mismo cliente y/o contratista
     * @param sa
     * @param contratista
     * @param cliente
     * @return List Agremiado
     */
    public List<Agremiado> getAgremiados(StatusAgremiado sa, Contratista contratista, Cliente cliente);
    /**
     * Metodo encargado de devolver un conjunto colaboradores que tienen relación al estar en el mismo status, y con mismo cliente
     * @param sa
     * @param cliente
     * @return List Agremiado
     */
    public List<Agremiado> getAgremiados(StatusAgremiado sa, Cliente cliente);
    
}
