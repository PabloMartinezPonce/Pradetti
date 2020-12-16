/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.SUPDao;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.TipoSalarioUnicoProfesional;
import com.pradettisanti.payroll.service.SUPService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * clase encargada de responder a la implemenación de SUPService
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 2017/12/07
 */
@Service
public class SUPServiceImpl implements SUPService{

    @Autowired
    private SUPDao sUPService;
    public SUPDao getPService(){
        return sUPService;
    }
    public void setSUPService(SUPDao sUPService){
        this.sUPService = sUPService;
    }
    
    @Override
    public void setSalarioUnicoProfesional(SalarioUnicoProfesional salarioUnicoProfesional) {
        sUPService.setSalarioUnicoProfesional(salarioUnicoProfesional);
    }

    @Override
    public void setTipoSalarioUnicoProfesional(TipoSalarioUnicoProfesional tipoSalarioUnicoProfesional) {
        sUPService.setTipoSalarioUnicoProfesional(tipoSalarioUnicoProfesional);
    }

    @Override
    public TipoSalarioUnicoProfesional getTipoSalariUnicoProfesional(Integer id) {
        return sUPService.getTipoSalariUnicoProfesional(id);
    }

    @Override
    public List<TipoSalarioUnicoProfesional> getTiposSalariosUnicosProfesionales() {
        return sUPService.getTiposSalariosUnicosProfesionales();
    }

    @Override
    public List<SalarioUnicoProfesional> getSalariosUnicosProfesionales(TipoSalarioUnicoProfesional tsup) {
        return sUPService.getSalariosUnicosProfesionales(tsup);
    }

    @Override
    public SalarioUnicoProfesional getSalarioUnicoProfesional(Integer integer) {
        return sUPService.getSalarioUnicoProfesional(integer);
    }

    @Override
    public List<SalarioUnicoProfesional> getSalariosUnicosProfesionales() {
        return sUPService.getSalariosUnicosProfesionales();
    }
    
}
