/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Agremiado;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Servicio encargado de la generación de archivos en fotmato de Excel a modo de reporte
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 14/02/2018
 */
public interface ExcelReportesService {
    /**
     * Metodo encargado de devolver los el archivo en excel con toda la información correspondiente a los datos de los colaboradores recibidos
     * @param agremiados
     * @return ByteArrayOutputStream
     */
        public ByteArrayOutputStream getReporteDeDatosDeLosColaboradores(List<Agremiado> agremiados) throws IOException;
    /**
     * Metodo encargado de devolver el archivo en excel con toda la infromación correspondiente a el fondo de ahorro de los colaboradores
     * @param agremiados
     * @return
     * @throws IOException 
     */
        public ByteArrayOutputStream getReporteDelFondoDeAhorro(List<Agremiado> agremiados) throws IOException;
      /**
       * Metodo encargado de devolver el archivo excel con los calculos del fondo de ahorro de un colaborador segun su esquema de pago y tipo de nomina
       * @param idAgremiado
       * @return
       * @throws IOException 
       */
        public ByteArrayOutputStream getReporteFAMixtoSemanal(Integer idAgremiado) throws IOException;

        public ByteArrayOutputStream getReporteFAMixtoCatorcenal(Integer idAgremiado) throws IOException;

        public ByteArrayOutputStream getReporteFAMixtoQuincenal(Integer idAgremiado, Integer idPeriodo) throws IOException;

        public ByteArrayOutputStream getReporteFAMixtoMensual(Integer idAgremiado) throws IOException;
        
        public ByteArrayOutputStream getReporteFANominalSemanal(Integer idAgremiado) throws IOException;
        
        public ByteArrayOutputStream getReporteFANominalCatorcenal(Integer idAgremiado) throws IOException;
        
        public ByteArrayOutputStream getReporteFANominalQuincenal(Integer idAgremiado) throws IOException;
        
        public ByteArrayOutputStream getReporteFANominalMensual(Integer idAgremiado) throws IOException;
}
