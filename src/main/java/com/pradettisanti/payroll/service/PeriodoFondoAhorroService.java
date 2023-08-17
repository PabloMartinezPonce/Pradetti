/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import java.util.List;
import com.pradettisanti.payroll.pojoh.PeriodoFA;
import java.util.Date;

/**
 * Intefaz que declara los servicios del Periodo de Fondo de Ahorro 
 * @author Gabriela Jaiem gabriela.jaime@it-seekers.com
 * @version 1
 * @since 2019/09/06
 */
public interface PeriodoFondoAhorroService {
    
    /**
     * Metodo encargado de ingresar/actualizar un periodo del fondo de ahorro
     * @param periodoFondoAhorro 
     */
    public Integer setPeriodoFondoAhorro(PeriodoFA periodoFondoAhorro);
    
    /**
     * Metodo encargado de devolver un periodo de fondo de ahorro en base al id ingresado
     * @param id
     * @return PeriodoFondoAhorro
     */
    public PeriodoFA getPeriodoFondoAhorro(Integer id);
    
    /**
     * Metodo encargado de devolver todos los periodos del fondo de ahorro
     * @return List PeriodoFA
     */
    public List<PeriodoFA> getPeriodosFondoAhorro();
    
    /**
     * Metodo encargado de devolver una lista de periodos del fondo de ahorro en base a su bitacora de fondo de ahorro
     * @param fechaInicioFA
     * @param fechaFinFA
     * @param idAgremiado
     * @return 
     */
    public List<PeriodoFA> getPeriodosByDates(Date fechaInicioFA, Date fechaFinFA, Integer idAgremiado);
    
    public Date getPrimerFechaFA(Integer idAgremiado);
    
    public Date getUltimaFechaFA(Integer idAgremiado);
    
    public Date getFechaInicioPeriodoFA(Integer idPeriodoFA);
    
    public Date getFechaFinPeriodoFA(Integer idPeriodoFA);
    
    public Short getPorcentajePeriodo(Integer idAgremiado, Date fechaIni, Date fechaFin);
    
    public Date getFechaIniPeriodo(Integer idAgremiado, Date fechaIni, Date fechaFin);
    
    public Date getFechaFinPeriodo(Integer idPeriodo);
}
