/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.IncidenciaDao;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.TipoIncidencia;
import com.pradettisanti.payroll.service.IncidenciaService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HEM 
 */
@Service
public class IncidenciaServiceImpl  implements IncidenciaService{
    
    @Autowired
    IncidenciaDao incidenciaDao;
    
    @Override
    public Integer setIncidencia(Incidencia inicidencia) {
       return incidenciaDao.setIncidencia(inicidencia);
    }

    @Override
    public List<Incidencia> getIncidencias(Cliente cliente, boolean estado) {
        return incidenciaDao.getIncidencias(cliente, estado);
    }
    
    @Override
    public List<Incidencia> getIncidencias(Agremiado agremiado, Date desdeElRegistro, Date hastaElRegistro) {
        return incidenciaDao.getIncidencias(agremiado, desdeElRegistro, hastaElRegistro);
    }
    
    @Override
    public List<Incidencia> getIncidenciasFechaRegistro(Cliente cliente, Date desdeElRegistro, Date hastaElRegistro) {
       return incidenciaDao.getIncidenciasFechaRegistro(cliente, desdeElRegistro, hastaElRegistro);
    }
    
    @Override
    public List<Incidencia> getIncidenciasFechaIncidencia(Cliente cliente, Date desdeElRegistro, Date hastaElRegistro) {
       return incidenciaDao.getIncidenciasFechaIncidencia(cliente, desdeElRegistro, hastaElRegistro);
    }
    
    @Override
    public List<Incidencia> getIncidenciasFechaIncidencia(Cliente cliente, Date desdeElRegistro, Date hastaElRegistro, boolean estado) {
        return incidenciaDao.getIncidenciasFechaIncidencia(cliente, desdeElRegistro, hastaElRegistro, estado);
    }
    
    @Override
    public Incidencia getInicidencia(Integer idCliente, Integer idAgremiado, Integer IdIncidencia, Short tipoIncidencia){
        return incidenciaDao.getInicidencia(idCliente, idAgremiado, IdIncidencia, tipoIncidencia);
    }
            
    @Override
    public TipoIncidencia getTipoIncidencia(Short idTipoIncidencia) {
        return incidenciaDao.getTipoIncidencia(idTipoIncidencia);
    }

    @Override
    public List<TipoIncidencia> getTipoIncidencias() {
        return incidenciaDao.getTipoIncidencias();
    }
    
}
