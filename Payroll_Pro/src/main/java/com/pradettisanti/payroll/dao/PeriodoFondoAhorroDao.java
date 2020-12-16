/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.PeriodoFA;
import java.util.Date;
import java.util.List;

/**
 *
 * @author IT Seekers
 */
public interface PeriodoFondoAhorroDao {
    
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
     *Metodo encargado de devolver una lista de periodos del fondo de ahorro en base a su bitacora de fondo de ahorro
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
