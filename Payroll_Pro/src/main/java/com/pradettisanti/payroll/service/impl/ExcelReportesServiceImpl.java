/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.DatosBancarios;
import com.pradettisanti.payroll.pojoh.DatosBeneficiario;
import com.pradettisanti.payroll.pojoh.DatosDomicilio;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.DatosPersonales;
import com.pradettisanti.payroll.pojoh.EsquemaPago;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.ExcelReportesService;
import com.pradettisanti.payroll.service.PeriodoFondoAhorroService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de servicio encargada de implementar el comportamiento de la interfaz ExcelReportesService
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @since 2018-02-06
 * @version 1
 */
@Service
public class ExcelReportesServiceImpl implements ExcelReportesService{

    private static final Logger LOGGER = Logger.getLogger(ExcelReportesServiceImpl.class);
    
    @Autowired
    private AgremiadoService agremiadoService;    
    public AgremiadoService getAgremiadoService(){
        return  agremiadoService;
    }    
    public void setAgremiadoService(AgremiadoService agremiadoService){
        this.agremiadoService = agremiadoService;
    }
    
     @Autowired
    private PeriodoFondoAhorroService pFAService;
    public PeriodoFondoAhorroService getPeriodosFaService(){
        return pFAService;
    }
    public void setPeriodosFaService(PeriodoFondoAhorroService pfaService){
        this.pFAService = pfaService;
    }
    
    @Override
    public ByteArrayOutputStream getReporteDeDatosDeLosColaboradores(List<Agremiado> agremiados) throws IOException{
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reporte Payroll");
        
        Row header = sheet.createRow(0);
        
        Cell cellHeader = header.createCell(0);
        cellHeader.setCellValue("Datos de los colaboradores");
        cellHeader.setCellStyle(getHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 61));
        
        Row subheader = sheet.createRow(1);
        Cell cellSubDP = subheader.createCell(0);
            cellSubDP.setCellValue("Datos Personales");
            cellSubDP.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 14));        
        Cell cellDL = subheader.createCell(15);
            cellDL.setCellValue("Datos Laborales");
            cellDL.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 15, 32));
        Cell cell2D = subheader.createCell(33);
            cell2D.setCellValue("Datos Domicilio");
            cell2D.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 33, 45));
        Cell cellDF = subheader.createCell(46);
            cellDF.setCellValue("Datos Beneficiario");
            cellDF.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 46, 57));
        Cell cellDB = subheader.createCell(58);
            cellDB.setCellValue("Datos Bancarios");
            cellDB.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 58, 61));
        
        Row columns = sheet.createRow(2);
            columns.createCell(0).setCellValue("ID");
            columns.createCell(1).setCellValue("Nombre");
            columns.createCell(2).setCellValue("Fecha de Alta en Imss");
            columns.createCell(3).setCellValue("Teléfono");
            columns.createCell(4).setCellValue("Correo");
            columns.createCell(5).setCellValue("Escolaridad");
            columns.createCell(6).setCellValue("Fecha de Nacimiento");
            columns.createCell(7).setCellValue("Edad");
            columns.createCell(8).setCellValue("Lugar de Nacimiento");
            columns.createCell(9).setCellValue("Nacionalidad");
            columns.createCell(10).setCellValue("Estado civil");
            columns.createCell(11).setCellValue("Hijos");
            columns.createCell(12).setCellValue("RFC");
            columns.createCell(13).setCellValue("CURP");
            columns.createCell(14).setCellValue("Regimen Matrimonial");
            
            columns.createCell(15).setCellValue("Cliente");
            columns.createCell(16).setCellValue("Contratista");
            columns.createCell(17).setCellValue("Sindicato");
            columns.createCell(18).setCellValue("Salarios ante IMSS");
            columns.createCell(19).setCellValue("Salario Diario");
            columns.createCell(20).setCellValue("Salario Diario Integrado");
            columns.createCell(21).setCellValue("Sueldo Mesual");
            columns.createCell(22).setCellValue("Periodo de Pago");
            columns.createCell(23).setCellValue("Numero de SS");
            columns.createCell(24).setCellValue("Reconocimiento de antiguedad");
            columns.createCell(25).setCellValue("TDU");
            columns.createCell(26).setCellValue("Credecial Laboral");
            columns.createCell(27).setCellValue("Lugar de trabajo");
            columns.createCell(28).setCellValue("Actividad Profesional");
            columns.createCell(29).setCellValue("Area/Departamento");
            columns.createCell(30).setCellValue("Tipo de contrato");
            columns.createCell(31).setCellValue("Fecha de termino");
            columns.createCell(32).setCellValue("INFONAVIT");
            
            columns.createCell(33).setCellValue("Estado");
            columns.createCell(34).setCellValue("Municipio");
            columns.createCell(35).setCellValue("Ciudad");
            columns.createCell(36).setCellValue("Colonia");
            columns.createCell(37).setCellValue("Calle");
            columns.createCell(38).setCellValue("Número");
            columns.createCell(39).setCellValue("Código Postal");
            columns.createCell(40).setCellValue("Entre Calles");
            columns.createCell(41).setCellValue("Tipo de Vivienda");
            columns.createCell(42).setCellValue("Fachada");
            columns.createCell(43).setCellValue("Color Fachada");
            columns.createCell(44).setCellValue("Tipo de Vía");
            columns.createCell(45).setCellValue("Referencía");
            
            columns.createCell(46).setCellValue("Nombre");
            columns.createCell(47).setCellValue("Fecha de Nacimiento");
            columns.createCell(48).setCellValue("Parentesco");
            columns.createCell(49).setCellValue("Porcentaje");
            columns.createCell(50).setCellValue("CURP");
            columns.createCell(51).setCellValue("Estado");
            columns.createCell(52).setCellValue("Ciudad");
            columns.createCell(53).setCellValue("Municipio");
            columns.createCell(54).setCellValue("Colonia");
            columns.createCell(55).setCellValue("Calle");
            columns.createCell(56).setCellValue("Número");
            columns.createCell(57).setCellValue("Código Postal");            
            
            columns.createCell(58).setCellValue("Banco");
            columns.createCell(59).setCellValue("Titular");
            columns.createCell(60).setCellValue("Clabe");
            columns.createCell(61).setCellValue("Cuenta");
            
            processList(agremiados,sheet);
            
            for (int i = 0; i < 62; i++) {
                sheet.autoSizeColumn(i);
            }
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        workbook.write(stream);
        stream.close();
        
        return stream;
    }
    private void processList(List<Agremiado> agremiados,XSSFSheet sheet){
        int i = 2;
        for (Agremiado agremiado : agremiados) {
                Row columns = sheet.createRow(++i);
                    columns.createCell(0).setCellValue( getValueInString(agremiado.getIdAgremiado()));
                    DatosPersonales datosPersonales = (agremiado.getDatosPersonales() == null)? new DatosPersonales() : agremiado.getDatosPersonales();
                    DatosLaborales datosLaborales = (agremiado.getDatosLaborales() == null)? new DatosLaborales() : agremiado.getDatosLaborales();
                    columns.createCell(1).setCellValue( getValueInString(datosPersonales.getNombre()).toUpperCase()+" "+getValueInString(datosPersonales.getApellidoPaterno()).toUpperCase()+" "+getValueInString(datosPersonales.getApellidoMaterno()).toUpperCase());
                    columns.createCell(2).setCellValue( getValueInString(datosLaborales.getFechaAltaImss()));
                    columns.createCell(3).setCellValue( getValueInString(datosPersonales.getTelefono()));
                    columns.createCell(4).setCellValue(getValueInString(datosPersonales.getEmail()));
                    columns.createCell(5).setCellValue(getValueInString(datosPersonales.getEscolaridad()));
                    columns.createCell(6).setCellValue(getValueInString(datosPersonales.getFechaNacimiento()));
                    columns.createCell(7).setCellValue(getEdad(datosPersonales.getFechaNacimiento()));
                    columns.createCell(8).setCellValue(getValueInString(datosPersonales.getLugarNacimiento()));
                    columns.createCell(9).setCellValue(getValueInString(datosPersonales.getNacionalidad()));
                    columns.createCell(10).setCellValue(getValueInString(datosPersonales.getEstadoCivil()));
                    columns.createCell(11).setCellValue(getValueInString(datosPersonales.getHijos()));
                    columns.createCell(12).setCellValue(getValueInString(datosPersonales.getRfc()));
                    columns.createCell(13).setCellValue(getValueInString(datosPersonales.getCurp()));
                    columns.createCell(14).setCellValue(getValueInString(datosPersonales.getRegimenMatrimonial()));
                    
                    columns.createCell(15).setCellValue(getValueInString(datosLaborales.getClienteObj().getNombreEmpresa()));
                    columns.createCell(16).setCellValue(getValueInString(datosLaborales.getContratistaObj().getNombreEmpresa()));
                    columns.createCell(17).setCellValue(sindicatoDisponible(datosLaborales.getEsquemaPago(), datosLaborales.getSindicatoObj()));
                    columns.createCell(18).setCellValue(getValueInString(datosLaborales.getSalariosImss()));
                    columns.createCell(19).setCellValue(getValueInString(datosLaborales.getSalarioDiario()));
                    columns.createCell(20).setCellValue(getValueInString(datosLaborales.getSalarioDiarioIntegrado()));
                    columns.createCell(21).setCellValue(getValueInString(datosLaborales.getSueldoMensual()));
                    columns.createCell(22).setCellValue(getValueInString( (datosLaborales.getTipoNominaObj() == null) ? "" : datosLaborales.getTipoNominaObj().getTipoNomina() ));
                    columns.createCell(23).setCellValue(getValueInString(datosLaborales.getNumeroSeguro()));
                    columns.createCell(24).setCellValue(getValueInString(datosLaborales.getReconocimientoAntiguedad()));
                    columns.createCell(25).setCellValue(getValueInString(datosLaborales.getTarjetaTdu()));
                    columns.createCell(26).setCellValue(getValueInString(datosLaborales.getCredencialLaboral()));
                    columns.createCell(27).setCellValue(getValueInString(datosLaborales.getLugarTrabajo()));
                    columns.createCell(28).setCellValue(getValueInString(datosLaborales.getActividadProfesional()));
                    columns.createCell(29).setCellValue(getValueInString(datosLaborales.getAreaDepartamento()));
                    columns.createCell(30).setCellValue(getValueInString( (datosLaborales.getTipoContratoObj() == null) ? "" : datosLaborales.getTipoContratoObj().getDescripcion() ));
                    columns.createCell(31).setCellValue(getValueInString(datosLaborales.getFechaFinContrato()));
                    columns.createCell(32).setCellValue(getValueInString(datosLaborales.getNumeroInfonavit()));
                    DatosDomicilio datosDomicilio = (agremiado.getDatosDomicilio() == null) ? new DatosDomicilio() : agremiado.getDatosDomicilio();
                    columns.createCell(33).setCellValue(getValueInString(datosDomicilio.getEstado()));
                    columns.createCell(34).setCellValue(getValueInString(datosDomicilio.getMunicipio()));
                    columns.createCell(35).setCellValue(getValueInString(datosDomicilio.getCiudad()));
                    columns.createCell(36).setCellValue(getValueInString(datosDomicilio.getColonia()));
                    columns.createCell(37).setCellValue(getValueInString(datosDomicilio.getCalle()));
                    columns.createCell(38).setCellValue(getValueInString(datosDomicilio.getNumero()));
                    columns.createCell(39).setCellValue(getValueInString(datosDomicilio.getCodigoPostal()));
                    columns.createCell(40).setCellValue(getValueInString(datosDomicilio.getEntreCalles()));
                    columns.createCell(41).setCellValue(getValueInString(datosDomicilio.getTipoVivienda()));
                    columns.createCell(42).setCellValue(getValueInString(datosDomicilio.getFachada()));
                    columns.createCell(43).setCellValue(getValueInString(datosDomicilio.getColorFachada()));
                    columns.createCell(44).setCellValue(getValueInString(datosDomicilio.getTipoVia()));
                    columns.createCell(45).setCellValue(getValueInString(datosDomicilio.getReferencia()));
                    DatosBeneficiario datosBeneficiario = (agremiado.getDatosBeneficiario() == null) ? new DatosBeneficiario() : agremiado.getDatosBeneficiario();
                    columns.createCell(46).setCellValue(getValueInString(datosBeneficiario.getNombre()));
                    columns.createCell(47).setCellValue(getValueInString(datosBeneficiario.getFechaNacimiento()));
                    columns.createCell(48).setCellValue(getValueInString(datosBeneficiario.getParentesco()));
                    columns.createCell(49).setCellValue(getValueInString(datosBeneficiario.getPorcentaje()));
                    columns.createCell(50).setCellValue(getValueInString(datosBeneficiario.getCurp()));
                    columns.createCell(51).setCellValue(getValueInString(datosBeneficiario.getEstado()));
                    columns.createCell(52).setCellValue(getValueInString(datosBeneficiario.getCiudad()));
                    columns.createCell(53).setCellValue(getValueInString(datosBeneficiario.getMunicipio()));
                    columns.createCell(54).setCellValue(getValueInString(datosBeneficiario.getColonia()));
                    columns.createCell(55).setCellValue(getValueInString(datosBeneficiario.getCalle()));
                    columns.createCell(56).setCellValue(getValueInString(datosBeneficiario.getNumero()));
                    columns.createCell(57).setCellValue(getValueInString(datosBeneficiario.getCodigoPostal()));            
                    DatosBancarios datosBancarios = (agremiado.getDatosBancarios() == null) ? new DatosBancarios() : agremiado.getDatosBancarios();
                    columns.createCell(58).setCellValue(getValueInString(datosBancarios.getNombreBanco()));
                    columns.createCell(59).setCellValue(getValueInString(datosBancarios.getTitular()));
                    columns.createCell(60).setCellValue(getValueInString(datosBancarios.getClabe()));
                    columns.createCell(61).setCellValue(getValueInString(datosBancarios.getNumeroCuenta()));
        }
    }
    
    private void processListReportFA(List<Agremiado> agremiados,XSSFSheet sheet){
        int i = 2;
        for (Agremiado agremiado : agremiados) {
                Row columns = sheet.createRow(++i);
                    columns.createCell(0).setCellValue( getValueInString(agremiado.getIdAgremiado()));
                    
                    DatosPersonales datosPersonales = (agremiado.getDatosPersonales() == null)? new DatosPersonales() : agremiado.getDatosPersonales();
                    DatosLaborales datosLaborales = (agremiado.getDatosLaborales() == null)? new DatosLaborales() : agremiado.getDatosLaborales();
                    
                    Date fecha_inicio = agremiado.getDatosLaborales().getFondoDeAhorroFechaSol();
//                    
//                    int anio = fecha_inicio.getYear()+1900;
//                    int mes = fecha_inicio.getMonth();
//                    int dia = fecha_inicio.getDay();
//                    
//                    Calendar fecha = new GregorianCalendar(anio, mes, dia);
                    
                    columns.createCell(1).setCellValue( getValueInString(datosLaborales.getFondoDeAhorroActual()));
                    //columns.createCell(1).setCellValue( getValueInString(datosPersonales.getNombre()).toUpperCase()+" "+getValueInString(datosPersonales.getApellidoPaterno()).toUpperCase()+" "+getValueInString(datosPersonales.getApellidoMaterno()).toUpperCase());
                    if(/**datosLaborales.getFondoDeAhorroActual()!=0 || */datosLaborales.getFondoDeAhorroActual()!=null){
                        if(datosLaborales.getSalarioDiario() != null){
                        //Double     
                        Double descuentoQuincenal = (Double.parseDouble(datosLaborales.getSalarioDiario())*15)*(datosLaborales.getFondoDeAhorroActual().doubleValue()/100);
                        columns.createCell(2).setCellValue( getValueInString(descuentoQuincenal));
                        System.out.println("SALARIO DIARIO: " + Double.parseDouble(datosLaborales.getSalarioDiario()));
                        System.out.println("MULTI SD X DIAS TRABAJADOS: " + Double.parseDouble(datosLaborales.getSalarioDiario())*15);
                        System.out.println("PORCENTAJE: " + datosLaborales.getFondoDeAhorroActual());
                        System.out.println("PORCENTAJE DECIMAL: " + datosLaborales.getFondoDeAhorroActual().doubleValue()/100);
                        }else{
                            columns.createCell(2).setCellValue("sin descuento");
                        }
                    }else{
                        columns.createCell(2).setCellValue("0");
                    }
                    //columns.createCell(2).setCellValue( getValueInString(datosLaborales.getFechaAltaImss()));
                    
        }
    }
    
    private void processColaboradorReportFA(Integer idAgremiado,XSSFSheet sheet, Integer idPeriodo){
        int i = 2;
        //for (Agremiado agremiado : agremiados) {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = (agremiado.getDatosPersonales() == null)? new DatosPersonales() : agremiado.getDatosPersonales();
                Row columns = sheet.createRow(++i);
                   columns.createCell(0).setCellValue( getValueInString(idAgremiado));
                   columns.createCell(1).setCellValue(getValueInString(datosPersonales.getNombre() + " " + datosPersonales.getApellidoPaterno() + " " +  datosPersonales.getApellidoMaterno()));
                    
                    
                   DatosLaborales datosLaborales = (agremiado.getDatosLaborales() == null)? new DatosLaborales() : agremiado.getDatosLaborales();
                    
                  Date fechaIniPiodoSelect = pFAService.getFechaInicioPeriodoFA(idPeriodo);
                  Date fechaFinPiodoSelect = pFAService.getFechaFinPeriodoFA(idPeriodo);
                  Short porcentaje = pFAService.getPorcentajePeriodo(idAgremiado, fechaIniPiodoSelect, fechaFinPiodoSelect);
                  LOGGER.error("porcentaje: " + porcentaje);
                  Date fechaIniPeriodo = pFAService.getFechaIniPeriodo(idAgremiado, fechaIniPiodoSelect, fechaFinPiodoSelect);
                  Date fechaFinPeriodo = pFAService.getFechaFinPeriodo(idPeriodo);
                          
                   Date fechaInicio = agremiado.getDatosLaborales().getFondoDeAhorroFechaSol();
                   SolicitudBaja lastSolicitudBaja = agremiadoService.getLastSolicitudBaja(agremiado);
                   Date fechaBaja = lastSolicitudBaja.getFechaBaja();
                    
                   String fechaIni = formatoFechaCorto(fechaIniPeriodo);
                   //String fechaFi = null;
                   String fechaBajaString = formatoFechaCorto(fechaBaja);
                   String fechaFinPeriodoString = formatoFechaCorto(fechaFinPeriodo);
                   
                   String[] fechaBajaFA = fechaBajaString.split("/");
//                   String diaBaja = fechaBajaFA[0];
//                   String mesBaja = fechaBajaFA[1];
                   String anioBaja = fechaBajaFA[2]; 
                   
                   int anioBajaFA = Integer.parseInt(anioBaja);
                //   String fechaFi = formatoFechaCorto(fechaFin);
//                    if(fechaBaja.compareTo(fechaIniPiodoSelect)){// fechaBaja.compareTo(fechaFinPiodoSelect)){
//                    }
                 
                   
                   String[] fechaIniFA = fechaIni.split("/");
                   String diaIni = fechaIniFA[0];
                   String mesIni = fechaIniFA[1];
                   String anioIni = fechaIniFA[2]; 
                   
                   int mesInicial = Integer.parseInt(mesIni);
                   int diaInicial = Integer.parseInt(diaIni);
                   int anioInicial = Integer.parseInt(anioIni);
                   
                  String [] fechaFinFA = fechaBajaString.split("/");
                   String diaFin = null;
                   String mesFin = null;
                   String anioFin = fechaBajaFA[2];
                   
                   int anioFinal = Integer.parseInt(anioFin);
                   
                     if(anioBajaFA == anioInicial || anioBajaFA == anioFinal){
//                        diaFin = fechaFinFA[0];
//                        mesFin = fechaFinFA[1];
//                        anioFin = fechaFinFA[2];
                        diaFin = fechaBajaFA[0];
                        mesFin = fechaBajaFA[1];
                        anioFin = fechaBajaFA[2]; 
                     }else{
                        String [] fechaFinPFA = fechaFinPeriodoString.split("/");
                        diaFin = fechaFinPFA[0];
                        mesFin = fechaFinPFA[1];
                        anioFin = fechaFinPFA[2]; 
                     }
                   
                    int mesFinal = Integer.parseInt(mesFin);
                    int diaFinal = Integer.parseInt(diaFin);
                    anioFinal = Integer.parseInt(anioFin);
    
                    String porcentString = String.valueOf(porcentaje);
                    LOGGER.error("porcentajeString: " +porcentString );
                    int porcent = Short.parseShort(porcentString);
                    LOGGER.error("porcent int: " +porcent );
                    
                   Double descuentoGralQnal = (Double.parseDouble(datosLaborales.getSalarioDiario())*15)*(porcent/100);
                   Double descuentoQnal13 = (Double.parseDouble(datosLaborales.getSalarioDiario())*13)*(porcent/100);
                   Double descuentoQnal16 = (Double.parseDouble(datosLaborales.getSalarioDiario())*16)*(porcent/100);
                   
                   while(mesInicial!=mesFinal){
                        switch (mesInicial){
                            case 07:
                              columns.createCell(2).setCellValue(porcent);
                              columns.createCell(3).setCellValue(descuentoGralQnal);
                              columns.createCell(4).setCellValue(porcent);
                              columns.createCell(5).setCellValue(descuentoQnal16);
                              LOGGER.error("descuentoGralQnal" + descuentoGralQnal);
                              LOGGER.error("descuentoQnal16" + descuentoQnal16);
                            break;
                            case 010:
                                columns.createCell(6).setCellValue(porcent);
                                columns.createCell(7).setCellValue(descuentoGralQnal);
                                columns.createCell(8).setCellValue(porcent);
                                columns.createCell(9).setCellValue(descuentoQnal16);
                            break;
                            case 011:
                                 columns.createCell(10).setCellValue(porcent);
                                 columns.createCell(11).setCellValue(descuentoGralQnal);
                                 columns.createCell(12).setCellValue(datosLaborales.getFondoDeAhorroActual());
                                 columns.createCell(13).setCellValue(descuentoGralQnal);
                            break;
                            case 10:
                                 columns.createCell(14).setCellValue(porcent);
                                 columns.createCell(15).setCellValue(descuentoGralQnal);
                                 columns.createCell(16).setCellValue(porcent);
                                 columns.createCell(17).setCellValue(descuentoQnal16);
                            break;
                            case 11:
                                 columns.createCell(18).setCellValue(porcent);
                                 columns.createCell(19).setCellValue(descuentoGralQnal);
                                 columns.createCell(20).setCellValue(porcent);
                                 columns.createCell(21).setCellValue(descuentoGralQnal);
                            break;
                            case 12:
                                 columns.createCell(22).setCellValue(porcent);
                                 columns.createCell(23).setCellValue(descuentoGralQnal);
                                 columns.createCell(24).setCellValue(porcent);
                                 columns.createCell(25).setCellValue(descuentoQnal16);
                            break;
                            case 01:
                                 columns.createCell(26).setCellValue(porcent);
                                 columns.createCell(27).setCellValue(descuentoGralQnal);
                                 columns.createCell(28).setCellValue(porcent);
                                 columns.createCell(29).setCellValue(descuentoQnal16);
                            break;
                            case 02:
                                 columns.createCell(30).setCellValue(porcent);
                                 columns.createCell(31).setCellValue(descuentoGralQnal);
                                 columns.createCell(32).setCellValue(porcent);
                                 columns.createCell(33).setCellValue(descuentoQnal13);
                            break;
                            case 03:
                                 columns.createCell(34).setCellValue(porcent);
                                 columns.createCell(35).setCellValue(descuentoGralQnal);
                                 columns.createCell(36).setCellValue(datosLaborales.getFondoDeAhorroActual());
                                 columns.createCell(37).setCellValue(descuentoQnal16);
                            break;
                            case 04:
                                 columns.createCell(38).setCellValue(porcent);
                                 columns.createCell(39).setCellValue(descuentoGralQnal);
                                 columns.createCell(40).setCellValue(porcent);
                                 columns.createCell(41).setCellValue(descuentoGralQnal);
                            break;
                            case 05:
                                 columns.createCell(42).setCellValue(porcent);
                                 columns.createCell(43).setCellValue(descuentoGralQnal);
                                 columns.createCell(44).setCellValue(porcent);
                                 columns.createCell(45).setCellValue(descuentoQnal16);
                            break;
                            case 06:
                                 columns.createCell(46).setCellValue(porcent);
                                 columns.createCell(47).setCellValue(descuentoGralQnal);
                                 columns.createCell(48).setCellValue(porcent);
                                 columns.createCell(49).setCellValue(descuentoGralQnal);
                            break;
                        }
                        mesInicial++;
                   }
                   
                   int mesInicialReal = Integer.parseInt(mesIni);
                   if(diaInicial == 15){
                       switch(mesInicialReal){
                           case 07:
                               columns.createCell(2).setCellValue("");
                               columns.createCell(3).setCellValue("");
                           break;
                           case 010:
                               columns.createCell(6).setCellValue("");
                               columns.createCell(7).setCellValue("");
                           break;
                           case 011:
                               columns.createCell(18).setCellValue("");
                               columns.createCell(19).setCellValue("");
                           break;
                           case 10:
                               columns.createCell(14).setCellValue("");
                               columns.createCell(15).setCellValue("");
                           break;
                           case 11:
                               columns.createCell(18).setCellValue("");
                               columns.createCell(19).setCellValue("");
                           break;
                           case 12:
                               columns.createCell(22).setCellValue("");
                               columns.createCell(23).setCellValue("");
                           break;
                           case 01:
                               columns.createCell(26).setCellValue("");
                               columns.createCell(27).setCellValue("");
                           break;
                           case 02:
                               columns.createCell(30).setCellValue("");
                               columns.createCell(31).setCellValue("");
                           break;
                           case 03:
                               columns.createCell(34).setCellValue("");
                               columns.createCell(35).setCellValue("");
                           break;
                           case 04:
                               columns.createCell(38).setCellValue("");
                               columns.createCell(39).setCellValue("");
                           break;
                           case 05:
                               columns.createCell(42).setCellValue("");
                               columns.createCell(43).setCellValue("");
                           break;
                           case 06:
                               columns.createCell(46).setCellValue("");
                               columns.createCell(47).setCellValue("");
                           break;
                       }
                   }
                   
                   if(diaFinal >= 15){
                       switch(mesFinal){
                           case 07:
                               columns.createCell(2).setCellValue(porcent);
                               columns.createCell(3).setCellValue(descuentoGralQnal);
                           break;
                           case 010:
                               columns.createCell(6).setCellValue(porcent);
                               columns.createCell(7).setCellValue(descuentoGralQnal);
                           break;
                           case 011:
                               columns.createCell(10).setCellValue(porcent);
                               columns.createCell(11).setCellValue(descuentoGralQnal);
                           break;
                           case 10:
                               columns.createCell(14).setCellValue(porcent);
                               columns.createCell(15).setCellValue(descuentoGralQnal);
                           break;
                           case 11:
                               columns.createCell(18).setCellValue(porcent);
                               columns.createCell(19).setCellValue(descuentoGralQnal);
                           break;
                           case 12:
                               columns.createCell(22).setCellValue(porcent);
                               columns.createCell(23).setCellValue(descuentoGralQnal);
                           break;
                           case 01:
                               columns.createCell(26).setCellValue(porcent);
                               columns.createCell(27).setCellValue(descuentoGralQnal);
                           break;
                           case 02:
                               columns.createCell(30).setCellValue(porcent);
                               columns.createCell(31).setCellValue(descuentoGralQnal);
                           break;
                           case 03:
                               columns.createCell(34).setCellValue(porcent);
                               columns.createCell(35).setCellValue(descuentoGralQnal);
                           break;
                           case 04:
                               columns.createCell(38).setCellValue(porcent);
                               columns.createCell(39).setCellValue(descuentoGralQnal);
                           break;
                           case 05:
                               columns.createCell(42).setCellValue(porcent);
                               columns.createCell(43).setCellValue(descuentoGralQnal);
                           break;
                           case 06:
                              columns.createCell(46).setCellValue(porcent);
                              columns.createCell(47).setCellValue(descuentoGralQnal); 
                           break; 
                       }
                   }
                    
                    columns.createCell(50).setCellValue("=SUMA(D4,F4,H4,J4,L4,N4,P4,R4,T4,V4,X4,Z4,AB4,AD4,AF4,AH4,AJ4,AL4,AN4,AP4,AR4,AT4,AV4,AX4)"); 
    }
    
    @Override
    public ByteArrayOutputStream getReporteDelFondoDeAhorro(List<Agremiado> agremiados) throws IOException{
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reporte Fondo de Ahorro");
        
        Row header = sheet.createRow(0);
        
        Cell cellHeader = header.createCell(0);
        cellHeader.setCellValue("Datos del fondo de ahorro");
        cellHeader.setCellStyle(getHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 48));
        
        Row subheader = sheet.createRow(1);
        Cell cellidagremiado = subheader.createCell(0);
            cellidagremiado.setCellValue("    ");
            cellidagremiado.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 0));
//        Row subheader = sheet.createRow(1);
//        Cell cell1aqnaene = subheader.createCell(0);
//            cell1aqnaene.setCellValue("1ª qna enero");
//            cell1aqnaene.setCellStyle(getSubHeaderStyleOdd(workbook));
//        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));        
        Cell cell1aqnaago = subheader.createCell(1);
            cell1aqnaago.setCellValue("1ª qna ago");
            cell1aqnaago.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 2));
        Cell cell2aqnaago = subheader.createCell(3);
            cell2aqnaago.setCellValue("2ª qna ago");
            cell2aqnaago.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 4));
        Cell cell1aqnasep = subheader.createCell(5);
            cell1aqnasep.setCellValue("1ª qna sep");
            cell1aqnasep.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 6));
        Cell cell2aqnasep = subheader.createCell(7);
            cell2aqnasep.setCellValue("2ª qna sep");
            cell2aqnasep.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 7, 8));
        Cell cell1aqnaoct = subheader.createCell(9);
            cell1aqnaoct.setCellValue("1ª qna oct");
            cell1aqnaoct.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 9, 10));
        Cell cell2aqnaoct = subheader.createCell(11);
            cell2aqnaoct.setCellValue("2ª qna oct");
            cell2aqnaoct.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 11, 12));
        Cell cell1aqnanov = subheader.createCell(13);
            cell1aqnanov.setCellValue("1ª qna nov");
            cell1aqnanov.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 14));
        Cell cell2aqnanov = subheader.createCell(15);
            cell2aqnanov.setCellValue("2ª qna nov");
            cell2aqnanov.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 15, 16));
        Cell cell1aqnadic = subheader.createCell(17);
            cell1aqnadic.setCellValue("1ª qna dic");
            cell1aqnadic.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 17, 18));
         Cell cell2aqnadic = subheader.createCell(19);
            cell2aqnadic.setCellValue("2ª qna dic");
            cell2aqnadic.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 19, 20));
        Cell cell1aqnaene = subheader.createCell(21);
            cell1aqnaene.setCellValue("1ª qna ene");
            cell1aqnaene.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 21, 22));
        Cell cell2aqnaene = subheader.createCell(23);
            cell2aqnaene.setCellValue("2ª qna ene");
            cell2aqnaene.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 23, 24));
        Cell cell1aqnafeb = subheader.createCell(25);
            cell1aqnafeb.setCellValue("1ª qna feb");
            cell1aqnafeb.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 25, 26));
        Cell cell2aqnafeb = subheader.createCell(27);
            cell2aqnafeb.setCellValue("2ª qna feb");
            cell2aqnafeb.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 27, 28));
        Cell cell1aqnamar = subheader.createCell(29);
            cell1aqnamar.setCellValue("1ª qna mar");
            cell1aqnamar.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 29, 30));
        Cell cell2aqnamar = subheader.createCell(31);
            cell2aqnamar.setCellValue("2ª qna mar");
            cell2aqnamar.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 31, 32));
        Cell cell1aqnaabr = subheader.createCell(33);
            cell1aqnaabr.setCellValue("1ª qna abr");
            cell1aqnaabr.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 33, 34));
        Cell cell2aqnaabr = subheader.createCell(35);
            cell2aqnaabr.setCellValue("2ª qna abr");
            cell2aqnaabr.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 35, 36));
        Cell cell1aqnamay = subheader.createCell(37);
            cell1aqnamay.setCellValue("1ª qna may");
            cell1aqnamay.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 37, 38));
        Cell cell2aqnamay = subheader.createCell(39);
            cell2aqnamay.setCellValue("2ª qna may");
            cell2aqnamay.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 39, 40));
        Cell cell1aqnajun = subheader.createCell(41);
            cell1aqnajun.setCellValue("1ª qna jun");
            cell1aqnajun.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 41, 42));
        Cell cell2aqnajun = subheader.createCell(43);
            cell2aqnajun.setCellValue("2ª qna jun");
            cell2aqnajun.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 43, 44));
        Cell cell1aqnajul = subheader.createCell(45);
            cell1aqnajul.setCellValue("1ª qna jul");
            cell1aqnajul.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 45, 46));
        Cell cell2aqnajul = subheader.createCell(47);
            cell2aqnajul.setCellValue("2ª qna jul");
            cell2aqnajul.setCellStyle(getSubHeaderStyleOdd(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 47, 48));
        Cell celltotal = subheader.createCell(49);
            celltotal.setCellValue("  ");
            celltotal.setCellStyle(getSubHeaderStylePair(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 49, 49));
        
        Row columns = sheet.createRow(2);
            columns.createCell(0).setCellValue(" ID");
        
            columns.createCell(1).setCellValue(" Porcentaje ");
            columns.createCell(2).setCellValue(" Monto ");
            
            columns.createCell(3).setCellValue(" Porcentaje ");
            columns.createCell(4).setCellValue("  Monto ");
            
            columns.createCell(5).setCellValue(" Porcentaje ");
            columns.createCell(6).setCellValue(" Monto ");
            
            columns.createCell(7).setCellValue(" Porcentaje ");
            columns.createCell(8).setCellValue(" Monto ");          
            
            columns.createCell(9).setCellValue(" Porcentaje ");
            columns.createCell(10).setCellValue(" Monto ");
            
            columns.createCell(11).setCellValue(" Porcentaje ");
            columns.createCell(12).setCellValue(" Monto ");
            
            columns.createCell(13).setCellValue(" Porcentaje ");
            columns.createCell(14).setCellValue(" Monto ");
            
            columns.createCell(15).setCellValue(" Porcentaje ");
            columns.createCell(16).setCellValue(" Monto ");
            
            columns.createCell(17).setCellValue(" Porcentaje ");
            columns.createCell(18).setCellValue(" Monto ");
            
            columns.createCell(19).setCellValue(" Porcentaje ");
            columns.createCell(20).setCellValue(" Monto ");
            
            columns.createCell(21).setCellValue(" Porcentaje ");
            columns.createCell(22).setCellValue(" Monto ");
            
            columns.createCell(23).setCellValue(" Porcentaje ");
            columns.createCell(24).setCellValue(" Monto ");
            
            columns.createCell(25).setCellValue(" Porcentaje ");
            columns.createCell(26).setCellValue(" Monto ");
            
            columns.createCell(27).setCellValue(" Porcentaje ");
            columns.createCell(28).setCellValue(" Monto ");
            
            columns.createCell(29).setCellValue(" Porcentaje ");
            columns.createCell(30).setCellValue(" Monto ");
            
            columns.createCell(31).setCellValue(" Porcentaje ");
            columns.createCell(32).setCellValue(" Monto ");
            
            columns.createCell(33).setCellValue(" Porcentaje ");
            columns.createCell(34).setCellValue(" Monto ");
            
            columns.createCell(35).setCellValue(" Porcentaje ");
            columns.createCell(36).setCellValue(" Monto ");
            
            columns.createCell(37).setCellValue(" Porcentaje ");
            columns.createCell(38).setCellValue(" Monto ");
            
            columns.createCell(39).setCellValue(" Porcentaje ");
            columns.createCell(40).setCellValue(" Monto ");
            
            columns.createCell(41).setCellValue(" Porcentaje ");
            columns.createCell(42).setCellValue(" Monto ");
            
            columns.createCell(43).setCellValue(" Porcentaje ");
            columns.createCell(44).setCellValue(" Monto ");
            
            columns.createCell(45).setCellValue(" Porcentaje ");
            columns.createCell(46).setCellValue(" Monto ");
            
            columns.createCell(47).setCellValue(" Porcentaje ");
            columns.createCell(48).setCellValue(" Monto ");
            
            columns.createCell(48).setCellValue(" Total ");
            processListReportFA(agremiados,sheet);
            
            for (int i = 0; i < 48; i++) {
                sheet.autoSizeColumn(i);
            }
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        workbook.write(stream);
        stream.close();
        
        return stream;
    }
    
    private String getValueInString(Integer dato){
        try {
            return dato.toString();
        } catch (Exception e) {
            return "";
        }
    }
    private String getValueInString(Short dato){
        try {
            return dato.toString();
        } catch (Exception e) {
            return "";
        }
    }
    private String getValueInString(String dato){
        try {
            return dato;
        } catch (Exception e) {
            return "";
        }
    }
    private String getValueInString(Boolean dato){
        try {
            return dato ? "Sí":"No";
        } catch (Exception e) {
            return "";
        }
    }
    private String getValueInString(Date dato){
        try {
            return new SimpleDateFormat("dd - MMMM - yyyy").format(dato).replace("-", "de");
        } catch (Exception e) {
            return "";
        }
    }
    private String getValueInString(Double dato){
        try {
            return dato.toString();
        } catch (Exception e) {
            return "";
        }
    }
    private String getEdad(Date d){    
        try {            
            Calendar inicio = new GregorianCalendar();
            Calendar fin = new GregorianCalendar();            
            inicio.setTime(d);
            fin.setTime(new Date());            
            int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
            int difM =(difA * 12 + fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH))/12;
            return  ""+difM;
        } catch (Exception e) {
            return "";
        }            
    }
   private String sindicatoDisponible(EsquemaPago ep, Sindicato s){
       try {
           if(ep.getDescripcion().equalsIgnoreCase("Nominal")){
               return "";
           }else{
               return getValueInString(s.getNombreCorto());
           }
       } catch (Exception e) {
           return "";
       }
   }
   private CellStyle getHeaderStyle(Workbook workbook){
       CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short)21);
        style.setFont(font);
        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
   }
   private CellStyle getSubHeaderStyleOdd(Workbook workbook){
       CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short)18);
        style.setFont(font);
        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
   }
   
   private CellStyle getSubHeaderStyleOddFA(Workbook workbook){
       CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short)14);
        style.setFont(font);
        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
   }
   
   private CellStyle getSubHeaderStylePair(Workbook workbook){
       CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short)18);
        style.setFont(font);
        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
   }
   
   private CellStyle getSubHeaderStylePairFA(Workbook workbook){
       CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font font = workbook.createFont();
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setFontHeightInPoints((short)14);
        style.setFont(font);
        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        return style;
   }

    @Override
    public ByteArrayOutputStream getReporteFAMixtoSemanal(Integer idAgremiado) throws IOException {
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reporte Fondo de Ahorro");
        
        Row header = sheet.createRow(0);
        
        Cell cellHeader = header.createCell(0);
        cellHeader.setCellValue("Fondo de ahorro");
        cellHeader.setCellStyle(getHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 50));
        
        Row subheader = sheet.createRow(1);
        Cell cellidagremiado = subheader.createCell(0);
            cellidagremiado.setCellValue("    ");
            cellidagremiado.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
//        Row subheader = sheet.createRow(1);
//        Cell cell1aqnaene = subheader.createCell(0);
//            cell1aqnaene.setCellValue("1ª qna enero");
//            cell1aqnaene.setCellStyle(getSubHeaderStyleOdd(workbook));
//        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));        
        Cell cell1asem = subheader.createCell(2);
            cell1asem.setCellValue("1ª sem");
            cell1asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
        Cell cell2dasem = subheader.createCell(4);
            cell2dasem.setCellValue("2ª sem");
            cell2dasem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 5));
        Cell cell3rasem = subheader.createCell(6);
            cell3rasem.setCellValue("3ª sem");
            cell3rasem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 7));
        Cell cell4asem = subheader.createCell(8);
            cell4asem.setCellValue("4ª sem");
            cell4asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 9));
        Cell cell5asem = subheader.createCell(10);
            cell5asem.setCellValue("5ª sem");
            cell5asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 11));
        Cell cell6asem = subheader.createCell(12);
            cell6asem.setCellValue("6ª sem");
            cell6asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 12, 13));
        Cell cell7asem = subheader.createCell(14);
            cell7asem.setCellValue("7ª sem");
            cell7asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 14, 15));
        Cell cell8asem = subheader.createCell(16);
            cell8asem.setCellValue("8ª sem");
            cell8asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 17));
        Cell cell9asem = subheader.createCell(18);
            cell9asem.setCellValue("9ª sem");
            cell9asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 18, 19));
         Cell cell10asem = subheader.createCell(20);
            cell10asem.setCellValue("10ª sem");
            cell10asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 20, 21));
        Cell cell11asem = subheader.createCell(22);
            cell11asem.setCellValue("11ª sem");
            cell11asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 22, 23));
        Cell cell12asem = subheader.createCell(24);
            cell12asem.setCellValue("12ª sem");
            cell12asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 24, 25));
        Cell cell13asem = subheader.createCell(26);
            cell13asem.setCellValue("13ª sem");
            cell13asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 26, 27));
        Cell cell14asem = subheader.createCell(28);
            cell14asem.setCellValue("14ª sem");
            cell14asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 28, 29));
        Cell cell15asem = subheader.createCell(30);
            cell15asem.setCellValue("15ª sem");
            cell15asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 30, 31));
        Cell cell16asem = subheader.createCell(32);
            cell16asem.setCellValue("16ª sem");
            cell16asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 32, 33));
        Cell cell17asem = subheader.createCell(34);
            cell17asem.setCellValue("17ª sem");
            cell17asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 34, 35));
        Cell cell18asem = subheader.createCell(36);
            cell18asem.setCellValue("18ª sem");
            cell18asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 36, 37));
        Cell cell19asem = subheader.createCell(38);
            cell19asem.setCellValue("19ª sem");
            cell19asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 38, 39));
        Cell cell20asem = subheader.createCell(40);
            cell20asem.setCellValue("20ª sem");
            cell20asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 40, 41));
        Cell cell21asem = subheader.createCell(42);
            cell21asem.setCellValue("21ª qna sem");
            cell21asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 42, 43));
        Cell cell22asem = subheader.createCell(44);
            cell22asem.setCellValue("22ª sem");
            cell22asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 44, 45));
        Cell cell23asem = subheader.createCell(46);
            cell23asem.setCellValue("23ª sem");
            cell23asem.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 46, 47));
        Cell cell24asem = subheader.createCell(48);
            cell24asem.setCellValue("24ª sem");
            cell24asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 48, 49));
        Cell cell25asem = subheader.createCell(48);
            cell25asem.setCellValue("25ª sem");
            cell25asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 48, 49));
        Cell cell26asem = subheader.createCell(50);
            cell26asem.setCellValue("26ª sem");
            cell26asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 50, 51));
        Cell cell27asem = subheader.createCell(52);
            cell27asem.setCellValue("27ª sem");
            cell27asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 52, 53));
        Cell cell28asem = subheader.createCell(54);
            cell28asem.setCellValue("28ª sem");
            cell28asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 54, 55));
        Cell cell29asem = subheader.createCell(56);
            cell29asem.setCellValue("29ª sem");
            cell29asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 56, 57));
        Cell cell30asem = subheader.createCell(58);
            cell30asem.setCellValue("30ª sem");
            cell30asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 59, 60));
        Cell cell31asem = subheader.createCell(61);
            cell31asem.setCellValue("31ª sem");
            cell31asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 61, 62));
        Cell cell32asem = subheader.createCell(63);
            cell32asem.setCellValue("32ª sem");
            cell32asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 63, 64));
        Cell cell33asem = subheader.createCell(65);
            cell33asem.setCellValue("33ª sem");
            cell33asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 65, 66));
        Cell cell34asem = subheader.createCell(67);
            cell34asem.setCellValue("34ª sem");
            cell34asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 67, 68));
        Cell cell35asem = subheader.createCell(69);
            cell35asem.setCellValue("35ª sem");
            cell35asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 69, 70));
        Cell cell36asem = subheader.createCell(71);
            cell36asem.setCellValue("36ª sem");
            cell36asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 71, 72));
        Cell cell37asem = subheader.createCell(73);
            cell37asem.setCellValue("37ª sem");
            cell37asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 73, 74));
        Cell cell38asem = subheader.createCell(75);
            cell38asem.setCellValue("38ª sem");
            cell38asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 75, 76));
        Cell cell39asem = subheader.createCell(77);
            cell39asem.setCellValue("39ª sem");
            cell39asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 77, 78));
        Cell cell40asem = subheader.createCell(79);
            cell40asem.setCellValue("40ª sem");
            cell40asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 79, 80));
        Cell cell41asem = subheader.createCell(81);
            cell41asem.setCellValue("41ª sem");
            cell41asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 81, 82));
        Cell cell42asem = subheader.createCell(83);
            cell42asem.setCellValue("42ª sem");
            cell42asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 83, 84));
        Cell cell43asem = subheader.createCell(85);
            cell43asem.setCellValue("43ª sem");
            cell43asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 85, 86));
        Cell cell44asem = subheader.createCell(87);
            cell44asem.setCellValue("44ª sem");
            cell44asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 87, 88));
        Cell cell45asem = subheader.createCell(89);
            cell45asem.setCellValue("45ª sem");
            cell45asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 89, 90));
        Cell cell46asem = subheader.createCell(91);
            cell46asem.setCellValue("46ª sem");
            cell46asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 91, 92));
        Cell cell47asem = subheader.createCell(93);
            cell47asem.setCellValue("47ª sem");
            cell47asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 93, 94));
        Cell cell48asem = subheader.createCell(95);
            cell48asem.setCellValue("48ª sem");
            cell48asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 95, 96));
        Cell cell49asem = subheader.createCell(97);
            cell49asem.setCellValue("49ª sem");
            cell49asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 97, 98));
        Cell cell50asem = subheader.createCell(99);
            cell50asem.setCellValue("50ª sem");
            cell50asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 99, 100));
        Cell cell51asem = subheader.createCell(101);
            cell51asem.setCellValue("51ª sem");
            cell51asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 101, 102));
        Cell cell52asem = subheader.createCell(103);
            cell52asem.setCellValue("52ª sem");
            cell52asem.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 103, 104));
        //
        Cell celltotal = subheader.createCell(105);
            celltotal.setCellValue("Total");
            celltotal.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 105, 106));
        
        Row columns = sheet.createRow(2);
            columns.createCell(0).setCellValue(" ID");
            columns.createCell(1).setCellValue(" Nombre ");
        
            columns.createCell(2).setCellValue(" Porcentaje ");
            columns.createCell(3).setCellValue(" Monto ");
            
            columns.createCell(4).setCellValue(" Porcentaje ");
            columns.createCell(5).setCellValue(" Monto ");
            
            columns.createCell(6).setCellValue(" Porcentaje ");
            columns.createCell(7).setCellValue(" Monto ");
            
            columns.createCell(8).setCellValue(" Porcentaje ");
            columns.createCell(9).setCellValue(" Monto ");          
            
            columns.createCell(10).setCellValue(" Porcentaje ");
            columns.createCell(11).setCellValue(" Monto ");
            
            columns.createCell(12).setCellValue(" Porcentaje ");
            columns.createCell(13).setCellValue(" Monto ");
            
            columns.createCell(14).setCellValue(" Porcentaje ");
            columns.createCell(15).setCellValue(" Monto ");
            
            columns.createCell(16).setCellValue(" Porcentaje ");
            columns.createCell(17).setCellValue(" Monto ");
            
            columns.createCell(18).setCellValue(" Porcentaje ");
            columns.createCell(19).setCellValue(" Monto ");
            
            columns.createCell(20).setCellValue(" Porcentaje ");
            columns.createCell(21).setCellValue(" Monto ");
            
            columns.createCell(22).setCellValue(" Porcentaje ");
            columns.createCell(23).setCellValue(" Monto ");
            
            columns.createCell(24).setCellValue(" Porcentaje ");
            columns.createCell(25).setCellValue(" Monto ");
            
            columns.createCell(26).setCellValue(" Porcentaje ");
            columns.createCell(27).setCellValue(" Monto ");
            
            columns.createCell(28).setCellValue(" Porcentaje ");
            columns.createCell(29).setCellValue(" Monto ");
            
            columns.createCell(30).setCellValue(" Porcentaje ");
            columns.createCell(31).setCellValue(" Monto ");
            
            columns.createCell(32).setCellValue(" Porcentaje ");
            columns.createCell(33).setCellValue(" Monto ");
            
            columns.createCell(34).setCellValue(" Porcentaje ");
            columns.createCell(35).setCellValue(" Monto ");
            
            columns.createCell(36).setCellValue(" Porcentaje ");
            columns.createCell(37).setCellValue(" Monto ");
            
            columns.createCell(38).setCellValue(" Porcentaje ");
            columns.createCell(39).setCellValue(" Monto ");
            
            columns.createCell(40).setCellValue(" Porcentaje ");
            columns.createCell(41).setCellValue(" Monto ");
            
            columns.createCell(42).setCellValue(" Porcentaje ");
            columns.createCell(43).setCellValue(" Monto ");
            
            columns.createCell(44).setCellValue(" Porcentaje ");
            columns.createCell(45).setCellValue(" Monto ");
            
            columns.createCell(46).setCellValue(" Porcentaje ");
            columns.createCell(47).setCellValue(" Monto ");
            
            columns.createCell(48).setCellValue(" Porcentaje ");
            columns.createCell(49).setCellValue(" Monto ");
            //26
            columns.createCell(50).setCellValue(" Porcentaje ");
            columns.createCell(51).setCellValue(" Monto ");
            
            columns.createCell(52).setCellValue(" Porcentaje ");
            columns.createCell(53).setCellValue(" Monto ");
            
            columns.createCell(54).setCellValue(" Porcentaje ");
            columns.createCell(55).setCellValue(" Monto ");
            
            columns.createCell(56).setCellValue(" Porcentaje ");
            columns.createCell(57).setCellValue(" Monto ");
            
            columns.createCell(58).setCellValue(" Porcentaje ");
            columns.createCell(60).setCellValue(" Monto ");
            
            columns.createCell(61).setCellValue(" Porcentaje ");
            columns.createCell(62).setCellValue(" Monto ");
            
            columns.createCell(63).setCellValue(" Porcentaje ");
            columns.createCell(64).setCellValue(" Monto ");
            
            columns.createCell(65).setCellValue(" Porcentaje ");
            columns.createCell(66).setCellValue(" Monto ");
            
            columns.createCell(67).setCellValue(" Porcentaje ");
            columns.createCell(68).setCellValue(" Monto ");
            
            columns.createCell(69).setCellValue(" Porcentaje ");
            columns.createCell(70).setCellValue(" Monto ");
            
            columns.createCell(71).setCellValue(" Porcentaje ");
            columns.createCell(72).setCellValue(" Monto ");
            
            columns.createCell(73).setCellValue(" Porcentaje ");
            columns.createCell(74).setCellValue(" Monto ");
            
            columns.createCell(75).setCellValue(" Porcentaje ");
            columns.createCell(76).setCellValue(" Monto ");
            
            columns.createCell(77).setCellValue(" Porcentaje ");
            columns.createCell(78).setCellValue(" Monto ");
           
            columns.createCell(79).setCellValue(" Porcentaje ");
            columns.createCell(80).setCellValue(" Monto ");
            
            columns.createCell(81).setCellValue(" Porcentaje ");
            columns.createCell(82).setCellValue(" Monto ");
            
            columns.createCell(83).setCellValue(" Porcentaje ");
            columns.createCell(84).setCellValue(" Monto ");
           
            columns.createCell(85).setCellValue(" Porcentaje ");
            columns.createCell(86).setCellValue(" Monto ");
            
            columns.createCell(87).setCellValue(" Porcentaje ");
            columns.createCell(88).setCellValue(" Monto ");
            
            columns.createCell(89).setCellValue(" Porcentaje ");
            columns.createCell(90).setCellValue(" Monto ");
            
            columns.createCell(91).setCellValue(" Porcentaje ");
            columns.createCell(92).setCellValue(" Monto ");
            
            columns.createCell(93).setCellValue(" Porcentaje ");
            columns.createCell(94).setCellValue(" Monto ");
            
            columns.createCell(95).setCellValue(" Porcentaje ");
            columns.createCell(96).setCellValue(" Monto ");
            
            columns.createCell(97).setCellValue(" Porcentaje ");
            columns.createCell(98).setCellValue(" Monto ");
            
            columns.createCell(99).setCellValue(" Porcentaje ");
            columns.createCell(100).setCellValue(" Monto ");
            
            columns.createCell(101).setCellValue(" Porcentaje ");
            columns.createCell(102).setCellValue(" Monto ");
            
            columns.createCell(103).setCellValue(" Porcentaje ");
            columns.createCell(104).setCellValue(" Monto ");
            
           // columns.createCell(48).setCellValue(" Total ");
          //  processColaboradorReportFA(idAgremiado,sheet,idPeriodo);
            
            for (int i = 0; i < 48; i++) {
                sheet.autoSizeColumn(i);
            }
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        workbook.write(stream);
        stream.close();
        
        return stream;
    }
    
    @Override
    public ByteArrayOutputStream getReporteFAMixtoQuincenal(Integer idAgremiado, Integer idPeriodo) throws IOException {
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reporte Fondo de Ahorro");
        
        Row header = sheet.createRow(0);
        
        Cell cellHeader = header.createCell(0);
        cellHeader.setCellValue("Fondo de ahorro");
        cellHeader.setCellStyle(getHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 50));
        
        Row subheader = sheet.createRow(1);
        Cell cellidagremiado = subheader.createCell(0);
            cellidagremiado.setCellValue("    ");
            cellidagremiado.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));        
        Cell cell1aqnajul = subheader.createCell(2);
            cell1aqnajul.setCellValue("1ª qna Jul");
            cell1aqnajul.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 3));
        Cell cell2aqnajul = subheader.createCell(4);
            cell2aqnajul.setCellValue("2ª qna Jul");
            cell2aqnajul.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 5));
        Cell cell1aqnaago = subheader.createCell(6);
            cell1aqnaago.setCellValue("1ª qna Ago");
            cell1aqnaago.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 7));
        Cell cell2aqnasago = subheader.createCell(8);
            cell2aqnasago.setCellValue("2ª qna Ago");
            cell2aqnasago.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 9));
        Cell cell1aqnasep = subheader.createCell(10);
            cell1aqnasep.setCellValue("1ª qna Sep");
            cell1aqnasep.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 10, 11));
        Cell cell2aqnasep = subheader.createCell(12);
            cell2aqnasep.setCellValue("2ª qna Sep");
            cell2aqnasep.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 12, 13));
        Cell cell1aqnanoct = subheader.createCell(14);
            cell1aqnanoct.setCellValue("1ª qna Oct");
            cell1aqnanoct.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 14, 15));
        Cell cell2aqnaoct = subheader.createCell(16);
            cell2aqnaoct.setCellValue("2ª qna Oct");
            cell2aqnaoct.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 16, 17));
        Cell cell1aqnanov = subheader.createCell(18);
            cell1aqnanov.setCellValue("1ª qna Nov");
            cell1aqnanov.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 18, 19));
         Cell cell2aqnanov = subheader.createCell(20);
            cell2aqnanov.setCellValue("2ª qna Nov");
            cell2aqnanov.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 20, 21));
        Cell cell1aqnadic = subheader.createCell(22);
            cell1aqnadic.setCellValue("1ª qna Dic");
            cell1aqnadic.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 22, 23));
        Cell cell2aqnadic = subheader.createCell(24);
            cell2aqnadic.setCellValue("2ª qna Dic");
            cell2aqnadic.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 24, 25));
        Cell cell1aqnaene = subheader.createCell(26);
            cell1aqnaene.setCellValue("1ª qna Ene");
            cell1aqnaene.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 26, 27));
        Cell cell2aqnaene = subheader.createCell(28);
            cell2aqnaene.setCellValue("2ª qna Ene");
            cell2aqnaene.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 28, 29));
        Cell cell1aqnafeb = subheader.createCell(30);
            cell1aqnafeb.setCellValue("1ª qna Feb");
            cell1aqnafeb.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 30, 31));
        Cell cell2aqnafeb = subheader.createCell(32);
            cell2aqnafeb.setCellValue("2ª qna Feb");
            cell2aqnafeb.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 32, 33));
        Cell cell1aqnamar = subheader.createCell(34);
            cell1aqnamar.setCellValue("1ª qna Mar");
            cell1aqnamar.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 34, 35));
        Cell cell2aqnamar = subheader.createCell(36);
            cell2aqnamar.setCellValue("2ª qna Mar");
            cell2aqnamar.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 36, 37));
        Cell cell1aqnaabr = subheader.createCell(38);
            cell1aqnaabr.setCellValue("1ª qna Abr");
            cell1aqnaabr.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 38, 39));
        Cell cell2aqnaabr = subheader.createCell(40);
            cell2aqnaabr.setCellValue("2ª qna Abr");
            cell2aqnaabr.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 40, 41));
        Cell cell1aqnamay = subheader.createCell(42);
            cell1aqnamay.setCellValue("1ª qna May");
            cell1aqnamay.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 42, 43));
        Cell cell2aqnamay = subheader.createCell(44);
            cell2aqnamay.setCellValue("2ª qna May");
            cell2aqnamay.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 44, 45));
        Cell cell1aqnajun = subheader.createCell(46);
            cell1aqnajun.setCellValue("1ª qna Jun");
            cell1aqnajun.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 46, 47));
        Cell cell2aqnajun = subheader.createCell(48);
            cell2aqnajun.setCellValue("2ª qna Jun");
            cell2aqnajun.setCellStyle(getSubHeaderStyleOddFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 48, 49));
        Cell celltotal = subheader.createCell(50);
            celltotal.setCellValue("Total");
            celltotal.setCellStyle(getSubHeaderStylePairFA(workbook));
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 50, 50));
        
        Row columns = sheet.createRow(2);
            columns.createCell(0).setCellValue(" ID");
            columns.createCell(1).setCellValue(" Nombre ");
        
            columns.createCell(2).setCellValue(" Porcentaje ");
            columns.createCell(3).setCellValue(" Monto ");
            
            columns.createCell(4).setCellValue(" Porcentaje ");
            columns.createCell(5).setCellValue(" Monto ");
            
            columns.createCell(6).setCellValue(" Porcentaje ");
            columns.createCell(7).setCellValue(" Monto ");
            
            columns.createCell(8).setCellValue(" Porcentaje ");
            columns.createCell(9).setCellValue(" Monto ");          
            
            columns.createCell(10).setCellValue(" Porcentaje ");
            columns.createCell(11).setCellValue(" Monto ");
            
            columns.createCell(12).setCellValue(" Porcentaje ");
            columns.createCell(13).setCellValue(" Monto ");
            
            columns.createCell(14).setCellValue(" Porcentaje ");
            columns.createCell(15).setCellValue(" Monto ");
            
            columns.createCell(16).setCellValue(" Porcentaje ");
            columns.createCell(17).setCellValue(" Monto ");
            
            columns.createCell(18).setCellValue(" Porcentaje ");
            columns.createCell(19).setCellValue(" Monto ");
            
            columns.createCell(20).setCellValue(" Porcentaje ");
            columns.createCell(21).setCellValue(" Monto ");
            
            columns.createCell(22).setCellValue(" Porcentaje ");
            columns.createCell(23).setCellValue(" Monto ");
            
            columns.createCell(24).setCellValue(" Porcentaje ");
            columns.createCell(25).setCellValue(" Monto ");
            
            columns.createCell(26).setCellValue(" Porcentaje ");
            columns.createCell(27).setCellValue(" Monto ");
            
            columns.createCell(28).setCellValue(" Porcentaje ");
            columns.createCell(29).setCellValue(" Monto ");
            
            columns.createCell(30).setCellValue(" Porcentaje ");
            columns.createCell(31).setCellValue(" Monto ");
            
            columns.createCell(32).setCellValue(" Porcentaje ");
            columns.createCell(33).setCellValue(" Monto ");
            
            columns.createCell(34).setCellValue(" Porcentaje ");
            columns.createCell(35).setCellValue(" Monto ");
            
            columns.createCell(36).setCellValue(" Porcentaje ");
            columns.createCell(37).setCellValue(" Monto ");
            
            columns.createCell(38).setCellValue(" Porcentaje ");
            columns.createCell(39).setCellValue(" Monto ");
            
            columns.createCell(40).setCellValue(" Porcentaje ");
            columns.createCell(41).setCellValue(" Monto ");
            
            columns.createCell(42).setCellValue(" Porcentaje ");
            columns.createCell(43).setCellValue(" Monto ");
            
            columns.createCell(44).setCellValue(" Porcentaje ");
            columns.createCell(45).setCellValue(" Monto ");
            
            columns.createCell(46).setCellValue(" Porcentaje ");
            columns.createCell(47).setCellValue(" Monto ");
            
            columns.createCell(48).setCellValue(" Porcentaje ");
            columns.createCell(49).setCellValue(" Monto ");
            
            processColaboradorReportFA(idAgremiado,sheet,idPeriodo);
            
            for (int i = 0; i < 48; i++) {
                sheet.autoSizeColumn(i);
            }
        
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        workbook.write(stream);
        stream.close();
        
        return stream;
    }
    
    private String formatoFechaCorto(Date fecha) {        
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return  formatter.format(fecha);
    }

    @Override
    public ByteArrayOutputStream getReporteFAMixtoCatorcenal(Integer idAgremiado) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ByteArrayOutputStream getReporteFAMixtoMensual(Integer idAgremiado) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ByteArrayOutputStream getReporteFANominalSemanal(Integer idAgremiado) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ByteArrayOutputStream getReporteFANominalCatorcenal(Integer idAgremiado) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ByteArrayOutputStream getReporteFANominalQuincenal(Integer idAgremiado) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ByteArrayOutputStream getReporteFANominalMensual(Integer idAgremiado) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}