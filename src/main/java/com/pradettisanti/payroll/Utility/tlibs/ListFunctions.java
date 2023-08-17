/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.Utility.tlibs;

import com.pradettisanti.payroll.pojoh.EsquemaCampo;
import java.util.List;

/**
 * Clase encargada de apoyar la definición y comportamiento de listLibrary.tld
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public class ListFunctions {
    public static boolean existsInEsquemaDePago( List<EsquemaCampo> collection, String keyword){
        try {            
            return collection.stream().anyMatch(ec -> ec.getCamposFormulario().getNombre().equalsIgnoreCase(keyword));
        } catch (Exception e) {
            return false;
        }
    }
}
