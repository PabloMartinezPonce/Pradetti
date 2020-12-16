/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.enums;

/**
 * enumerado que define las plantillas de fondos disponibles en la creación de documentos
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public enum ContratistaTemplate {
    Vacia,Casuisticas,Andares, TandT, Technologies;

    public static ContratistaTemplate getEmpty() {
        return Vacia;
    }

    public static ContratistaTemplate getCasuisticas() {
        return Casuisticas;
    }

    public static ContratistaTemplate getAndares() {
        return Andares;
    }
    public static ContratistaTemplate getTyT() {
        return TandT;
    }
    
    public static ContratistaTemplate getTechnologies(){
        return Technologies;
    }
    
    public static String getURL(ContratistaTemplate contratistaTemplate){
        String url = "/frontend/img/template/";
        switch(contratistaTemplate){
            case Casuisticas:
               return url += "casuisticas.jpg";
            case Andares:
                return url += "andares.jpg";
            case TandT:
                return url += "tandt.jpg";
            case Technologies:
                return url += "technologies.jpg";
            case Vacia:
                default:
                return url += "blank.jpg";
        }   
    }
    
    public static ContratistaTemplate getContratistaTemplateByName(String name){
        try {
            name = name.toLowerCase();
                switch(name){
                         case  "andares":
                             return ContratistaTemplate.Andares;
                         case  "casuisticas":
                             return ContratistaTemplate.Casuisticas;
                         case "tandt":
                             return ContratistaTemplate.TandT;
                         case "technologies":
                             return ContratistaTemplate.Technologies;
                         default:
                              return ContratistaTemplate.Vacia;
            }
        } catch (Exception e) {
            return ContratistaTemplate.Vacia;
        }
    }
}
