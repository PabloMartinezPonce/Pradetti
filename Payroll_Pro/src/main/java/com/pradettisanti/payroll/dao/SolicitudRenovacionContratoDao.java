/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import java.util.List;

/**
 *  Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface SolicitudRenovacionContratoDao {
    //Create
    //Read
    public SolicitudRenovacionContrato getSolicitudRenovacionContrato( SolicitudRenovacionContrato solicitudRenovacionContrato );
    //--TipoContrato
    public TipoContrato getTipoContrato( Short idTipoContrato );
    public List<TipoContrato> getTipoContrato();
    //Update
    public void setSolicitudRenovacionContrato( SolicitudRenovacionContrato solicitudRenovacionContrato );
    //Delete
}
