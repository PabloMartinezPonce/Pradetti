/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.Sindicato;
import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interfaz encargada de representar los servicios para el uso de excel dentro del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ExcelService {

    /**
     * Metodo requerido de implementar para convertir el archivo de nómina a objetos del sistema
     * @param cliente Instanca de cliente
     * @param archivoDeNomina Archivo xlsx de nomina
     * @param diaInicial Instancia Date
     * @param diaFinal Instancia Date
     * @param sindicato
     * @return List Recibo
     */
    public List<Recibo> archivoDeNomina( Cliente cliente, MultipartFile archivoDeNomina, Date diaInicial, Date diaFinal, Sindicato sindicato );
    
    /**
     * Metodo requerido de implementar para convertir el archivo de nómina a objetos de sistema y actualizar los recibos de nómina
     * @param cliente
     * @param archivoNomina
     * @param diaInicial
     * @param diaFinal
     * @param sindicato
     * @return List Recibo
     */
    public List<Recibo> actualizarNomina( Cliente cliente, MultipartFile archivoNomina, Date diaInicial, Date diaFinal, Sindicato sindicato );
}
