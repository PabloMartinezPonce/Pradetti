/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.TipoIncidencia;
import java.util.Date;
import java.util.List;

public interface IncidenciaService {
  
    public Integer setIncidencia( Incidencia inicidencia );
  
    public List<Incidencia> getIncidencias( Cliente cliente, boolean estado );

    public List<Incidencia> getIncidencias(  Agremiado agremiado, Date desdeElRegistro, Date hastaElRegistro );
  
        public List<Incidencia> getIncidenciasFechaRegistro(  Cliente cliente, Date desdeElRegistro, Date hastaElRegistro );
 
        public List<Incidencia> getIncidenciasFechaIncidencia(  Cliente cliente, Date desdeElRegistro, Date hastaElRegistro );

        public List<Incidencia> getIncidenciasFechaIncidencia( Cliente cliente, Date desdeElRegistro, Date hastaElRegistro, boolean estado );

        public Incidencia getInicidencia(Integer idCliente, Integer idAgremiado, Integer IdIncidencia, Short tipoIncidencia);

    public TipoIncidencia getTipoIncidencia(  Short idTipoIncidencia );

    public List<TipoIncidencia> getTipoIncidencias();
}
