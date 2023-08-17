/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.ReciboSolicitado;
import java.io.File;

/**
 * Interfaz encargada de representar los servicios para enviar correos electrónicos dentro del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface EmailService {
    public void enviarCorreo(String emailAddress, String subject, String msj);
    public void enviarCorreo(String[] emailAddress, String subject, String msj);
    public boolean enviarCorreo(String emailAddress, String subject, String msj, File file); //Se actualizá la firma para que regrese un boolean
    public void enviarCorreo(String emailAddress, String subject, String msj, File file, ReciboSolicitado reciboSolicitado); // metodo que recibe 3 String 1 File y un ojeto ReciboSolicitado
}
