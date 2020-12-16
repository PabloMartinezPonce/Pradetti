/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.TipoSalarioUnicoProfesional;
import java.util.List;

/**
 * Intefaz que declara los servicios del SUP 
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 2017/12/07
 */
public interface SUPService {
    /**
     * Metodo encargado de ingresar/actualizar un salario unico profesional
     * @param salarioUnicoProfesional 
     */
        public void setSalarioUnicoProfesional(SalarioUnicoProfesional salarioUnicoProfesional);
    /**
     * Metodo encargado de ingresar/actualizar un tipo de salario unico profesional
     * @param tipoSalarioUnicoProfesional 
     */
    public void setTipoSalarioUnicoProfesional(TipoSalarioUnicoProfesional tipoSalarioUnicoProfesional);
    /**
     * Metodo encargado de devolver un tipo de salario unico profesional en base al id ingresado
     * @param id
     * @return TipoSalarioUnicoProfesional
     */
    public TipoSalarioUnicoProfesional getTipoSalariUnicoProfesional(Integer id);
    /**
     * Metodo encargado de devolver todos los tipos de salarios profesionales
     * @return List TipoSalarioUnicoProfesional
     */
    public List<TipoSalarioUnicoProfesional> getTiposSalariosUnicosProfesionales();
    /**
     * Metodo encargado de devolver todos los salarios unicos profesionales registrados
     * @return List SalarioUnicoProfesional
     */
    public List<SalarioUnicoProfesional> getSalariosUnicosProfesionales();
    /**
     * Metodo encargado de devolver una lista de salarios unicos profesionales que corresponden a un tipo de salario unico profesional
     * @param tsup
     * @return List SalarioUnicoProfesional
     */
    public List<SalarioUnicoProfesional> getSalariosUnicosProfesionales(TipoSalarioUnicoProfesional tsup);
    /**
     * Metodo encargado de devolver el salario unico profesional con respecto a su id
     * @param integer
     * @return SalarioUnicoProfesional
     */
    public SalarioUnicoProfesional getSalarioUnicoProfesional(Integer integer);
}
