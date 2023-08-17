/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.enums;

/**
 * enum encargado de representar la url de la tabla catalogo documental en constantes
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public enum DocumentByMethod {
    DOCUMENTO_NO_ENCONTRADO,
    SOLICITUD_TARJETAS,
    CORREO_ELECTRONICO,
    SOLICITUD_FONDO_AHORRO,
    ADHESION_SINDICAL, 
    SOLICITUD_ASIMILABLES; 
    
    public static DocumentByMethod getByURL(String url){
        try {
                switch(url){
                         case  "SolicitudTarjetas":
                             return DocumentByMethod.SOLICITUD_TARJETAS;
                         case  "ConfirmacionCorreoElectronico":
                             return DocumentByMethod.CORREO_ELECTRONICO;
                         case  "SolicitudDeFondoDeAhorroEstandar":
                             return DocumentByMethod.SOLICITUD_FONDO_AHORRO;
                         case  "AdhesionSindical":
                             return DocumentByMethod.ADHESION_SINDICAL;
                         case "SolicitudAsimilables":
                             return DocumentByMethod.SOLICITUD_ASIMILABLES;
                         default:
                              return DocumentByMethod.DOCUMENTO_NO_ENCONTRADO;
            }
        } catch (Exception e) {
            return DocumentByMethod.DOCUMENTO_NO_ENCONTRADO;
        }
    }
    
}