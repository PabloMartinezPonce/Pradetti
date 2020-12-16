/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.TipoSalarioUnicoProfesional;
import java.util.List;

/**
 * Interfaz encargada de definir los metodos que se deben de implementar para poder trabajar con los salarios unicos profesionales
 * @since 2017/12/06
 * @version 1
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface SUPDao {
    /**
     * Metodo encargado de ingresar/ actulizar un salario unico profesional
     * @param salarioUnicoProfesional 
     */
    public void setSalarioUnicoProfesional(SalarioUnicoProfesional salarioUnicoProfesional);
    /**
     * Metodo encargadi de ingresar/actualizar un tipo de salario unico profesional
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
    /**
     * Metodo encargado de devolver todos los salarios unicos profesionales
     * @return List SalarioUnicoProfesional
     */
    public List<SalarioUnicoProfesional> getSalariosUnicosProfesionales();
}
