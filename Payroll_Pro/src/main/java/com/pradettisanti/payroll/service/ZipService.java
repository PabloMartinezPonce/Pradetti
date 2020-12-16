/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Recibo;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Interfaz encarga de establecer los metodos para la genereación de archivos ZIP
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ZipService {
    public ByteArrayOutputStream crearArchivo(List<Recibo> recibos);
}
