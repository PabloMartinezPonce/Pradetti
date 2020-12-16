/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import java.util.List;

public interface SolicitudRenovacionContratoService {
  
    public SolicitudRenovacionContrato getSolicitudRenovacionContrato( SolicitudRenovacionContrato solicitudRenovacionContrato );

    public TipoContrato getTipoContrato( Short idTipoContrato );

    public List<TipoContrato> getTipoContrato();

    public void setSolicitudRenovacionContrato( SolicitudRenovacionContrato solicitudRenovacionContrato );
}
