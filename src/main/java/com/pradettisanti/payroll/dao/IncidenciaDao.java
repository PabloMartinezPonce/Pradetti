/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.TipoIncidencia;
import java.util.Date;
import java.util.List;

/**
 *  Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Incidencia
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface IncidenciaDao {
    //Create
    public Integer setIncidencia( Incidencia inicidencia );
    //Read
    public List<Incidencia> getIncidencias(  Agremiado agremiado, Date desdeElRegistro, Date hastaElRegistro );
    public List<Incidencia> getIncidencias( Cliente cliente, boolean estado );
    public List<Incidencia> getIncidenciasFechaRegistro(  Cliente cliente, Date desdeElRegistro, Date hastaElRegistro );
    public List<Incidencia> getIncidenciasFechaIncidencia(  Cliente cliente, Date desdeElRegistro, Date hastaElRegistro );
    public List<Incidencia> getIncidenciasFechaIncidencia( Cliente cliente, Date desdeElRegistro, Date hastaElRegistro, boolean estado );
    public Incidencia getInicidencia(Integer idCliente, Integer idAgremiado, Integer IdIncidencia, Short tipoIncidencia);
    //--TipoInicidencia
    public TipoIncidencia getTipoIncidencia(  Short idTipoIncidencia );
    public List<TipoIncidencia> getTipoIncidencias();
    //Update
    //Delete
}
