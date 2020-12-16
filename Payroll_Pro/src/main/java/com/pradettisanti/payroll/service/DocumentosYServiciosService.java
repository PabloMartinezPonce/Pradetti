/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.enums.ContratistaTemplate;
import com.pradettisanti.payroll.pojoh.Agremiado;
import java.io.ByteArrayOutputStream;

/**
 * Interfaz encargada de definir como se van a devolver los documentos
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @since 2018/01/08
 * @version 1
 */
public interface DocumentosYServiciosService {
    public ByteArrayOutputStream getSolicitudTarjetas(Agremiado a,ContratistaTemplate template);
    public ByteArrayOutputStream getConfirmacionCorreoElectronico(Agremiado a,ContratistaTemplate template);
    public ByteArrayOutputStream getCartaRenunciaFA(Agremiado a,ContratistaTemplate template);
    public ByteArrayOutputStream getSolicitudDeFondoDeAhorroEstandar(Agremiado a,ContratistaTemplate template);
    public ByteArrayOutputStream getAdhesionSindical(Agremiado a);
    /**
     * Metodo encargado de devolver el documento a partir de su plantilla
     * @param a
     * @param template
     * @return ByteArrayOutputStream
     */
    public ByteArrayOutputStream getSolicitudDeAsimilables(Agremiado a,ContratistaTemplate template);
}
