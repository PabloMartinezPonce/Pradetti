/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.pojoh.ActaNotarial;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.PoderLegal;
import com.pradettisanti.payroll.pojoh.TipoActa;
import com.pradettisanti.payroll.service.ActaNotarialService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.pradettisanti.payroll.dao.ActaNotarialDao;
import org.springframework.stereotype.Service;

@Service
public class ActaNotarialServiceImpl  implements ActaNotarialService{
    
    @Autowired
    private ActaNotarialDao actaNotarialDao;

    @Override
    public Integer setActaNotarial(ActaNotarial actaNotarial) {
        return actaNotarialDao.setActaNotarial(actaNotarial);
    }

    @Override
    public void setActaNotarial(PoderLegal poderLegal) {
        actaNotarialDao.setActaNotarial(poderLegal);
    }

    @Override
    public ActaNotarial getActaNotarial(Integer idActaNotarial) {
        return actaNotarialDao.getActaNotarial(idActaNotarial);
    }

    @Override
    public List<ActaNotarial> getActanotarial(ActaNotarial actaNotarial) {
        return actaNotarialDao.getActanotarial(actaNotarial);
    }

    @Override
    public ActaNotarial getActanotarial(Cliente cliente) {
        return actaNotarialDao.getActanotarial(cliente);
    }

    @Override
    public ActaNotarial getActanotarial(Contratista contratista) {
        return actaNotarialDao.getActanotarial(contratista);
    }

    @Override
    public List<ActaNotarial> getActasNotariales() {
        return actaNotarialDao.getActasNotariales();
    }

    @Override
    public List<ActaNotarial> getActasNotariales(TipoActa tipoActa) {
        return actaNotarialDao.getActasNotariales();
    }

    @Override
    public TipoActa getTipoActa(Short idTipoActa) {
        return actaNotarialDao.getTipoActa(idTipoActa);
    }

    @Override
    public List<TipoActa> getTipoActa() {
        return actaNotarialDao.getTipoActa();
    }
    
}
