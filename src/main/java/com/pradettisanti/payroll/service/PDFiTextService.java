/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.Sindicato;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.List;

/**
 * Interfaz encargada de representar los servicios para la creación de PDF con iText dentro del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 2
 * @since 2018/01/23
 */
public interface PDFiTextService {
    public ByteArrayOutputStream createAgreement(Contratista contratista, Agremiado agremiado, String htmlCode);
    public ByteArrayOutputStream createAgreement(Contratista contratista, Cliente cliente, String htmlCode);
    public ByteArrayOutputStream getKardex(Cliente cliente);
    public ByteArrayOutputStream getKardex(Contratista contratista);
    public ByteArrayOutputStream getKardex(Sindicato sindicato);
    public ByteArrayOutputStream getKardex(Agremiado agremiado);
    public ByteArrayOutputStream getReciboSindical(Recibo recibo);
    public ByteArrayOutputStream getReciboSindical(List<Recibo> recibos, Cliente cliente,  String Desde , String Hasta);
    public Map<String,ByteArrayOutputStream> getReciboSindical(List<Recibo> recibos );
    /**
     * Interfaz deprecada ya que se comenzará a implementar el comportamieto en la DocumentosYServiciosServiceImpl
     * @param agremiado
     * @return ByteArrayOutputStream
     * @since 23/01/2018
     * @see DocumentosYServiciosService
     * @deprecated
     */
    @Deprecated
    public ByteArrayOutputStream getAdhesionSindical( Agremiado agremiado );
}
