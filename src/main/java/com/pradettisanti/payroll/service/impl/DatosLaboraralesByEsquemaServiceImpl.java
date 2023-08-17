/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.EsquemaCampo;
import com.pradettisanti.payroll.pojoh.EsquemaPago;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import com.pradettisanti.payroll.pojoh.TipoNomina;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.DatosLaboraralesByEsquemaService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *Clase encarda de definir el comportamiento de la interfaz de validación de datos
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */

@Service
public class DatosLaboraralesByEsquemaServiceImpl implements DatosLaboraralesByEsquemaService{
    
     private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DatosLaboraralesByEsquemaServiceImpl.class);   
    
    private final String MODULO = "DatosLaboraralesByEsquem";
    private final String LOGGER_PREFIX = "[Service, "+MODULO+"] ";  
     
    @Autowired
    private AgremiadoService agremiadoService;
    
    @Override
    public DatosLaborales getDatosLaborales(DatosLaborales dlBDD, DatosLaborales dlForm, HttpServletRequest request) throws Exception {
        
        EsquemaPago ep = dlBDD.getEsquemaPago();
         List<EsquemaCampo> esquemaCampo = agremiadoService.getCamposDelEsquema(ep);
         boolean isNuevo = dlBDD.getTipoContratoObj()==null;
         
         String fechaAlta = request.getParameter("fechaAlta");
         try {
             dlBDD.setFechaInicioContrato(procesayyyyMMdd(fechaAlta));
         } catch (ParseException pe) {
             throw new Exception("No fue posible encontrar la fecha de ingreso --> "+pe.getMessage() , pe);
         }
         
         String tpDCntrt = request.getParameter("tpDCntrt");
         short tipoDeContratoId = Short.parseShort(tpDCntrt);         
         TipoContrato tipoContrato = agremiadoService.getTipoContrato(tipoDeContratoId);
         dlBDD.setTipoContratoObj(tipoContrato);
         
         if(!dlBDD.getTipoContratoObj().getDescripcion().equals("Indeterminado")){
            String fechaTermino = request.getParameter("fechaTermino");
             try {                 
                 dlBDD.setFechaFinContrato(procesayyyyMMdd(fechaTermino));
                 Calendar calendar = Calendar.getInstance();
                calendar.setTime(dlBDD.getFechaFinContrato());
                calendar.add(Calendar.DATE, -7);
                Date fechaTimbre = calendar.getTime();
                dlBDD.setFechaTimbreContrato(fechaTimbre);
                dlBDD.setTiempoContrato(dlForm.getTiempoContrato());
             } catch (ParseException pe) {
                    throw new Exception("No fue posible encontrar la fecha de termino para el tipo de contrato "+dlBDD.getTipoContratoObj().getDescripcion()+" --> "+pe.getMessage(), pe);
             }                
           }else{
             dlBDD.setFechaFinContrato(null);
             dlBDD.setFechaTimbreContrato(null);
             dlBDD.setPeriodoContrato(null);
             dlBDD.setTiempoContrato(null);
         }
         
         if(existsInEsquemaDePago(esquemaCampo, "dLa.actividadProfesional")){
             dlBDD.setActividadProfesional(dlForm.getActividadProfesional());
         }else{
             dlBDD.setActividadProfesional(null);
         }
         
         if(existsInEsquemaDePago(esquemaCampo, "dLa.areaDepartamento")){
             dlBDD.setAreaDepartamento(dlForm.getAreaDepartamento());
         }else{
             dlBDD.setAreaDepartamento(null);
         }
         
         if(existsInEsquemaDePago(esquemaCampo, "dLa.lugarTrabajo")){
             dlBDD.setLugarTrabajo(dlForm.getLugarTrabajo());
         }else{
            dlBDD.setLugarTrabajo(null);
         }
         
         if(existsInEsquemaDePago(esquemaCampo, "dLa.credencialLaboral")){
             dlBDD.setCredencialLaboral(dlForm.getCredencialLaboral());
         }else{
             dlBDD.setCredencialLaboral("No");
         }
         
         dlBDD.setPercepcionBasadaEnSUP(dlForm.getPercepcionBasadaEnSUP());
         if(dlBDD.getPercepcionBasadaEnSUP()){
            String  supSelect = request.getParameter("supSelect");
            int supSelectId= Integer.parseInt(supSelect);
             SalarioUnicoProfesional salarioUnicoProfesional = agremiadoService.getSalarioUnicoProfesional(supSelectId);
             dlBDD.setSalarioUnicoProfesional(salarioUnicoProfesional);
             dlBDD.setSueldoMensual(null);
             dlBDD.setSalarioDiario(null);
             dlBDD.setSalariosImss(null);
             dlBDD.setSalarioDiarioIntegrado(null);
         }else{
             dlBDD.setSueldoMensual(dlForm.getSueldoMensual());
             dlBDD.setSalarioDiario(dlForm.getSalarioDiario());
             dlBDD.setSalariosImss(dlForm.getSalariosImss());
             dlBDD.setSalarioDiarioIntegrado(dlForm.getSalarioDiarioIntegrado());
             dlBDD.setSalarioUnicoProfesional(null);
         }
         
         if(existsInEsquemaDePago(esquemaCampo, "dLa.prdDPg")){
            String prdDPg = request.getParameter("prdDPg");
            short prdDPgId = Short.parseShort(prdDPg);
            TipoNomina tipoNomina = agremiadoService.getTipoNomina(prdDPgId);
             dlBDD.setTipoNominaObj(tipoNomina);         
         }else{
             dlBDD.setTipoNominaObj(null);
         }
         
         if(existsInEsquemaDePago(esquemaCampo, "dLa.numeroSeguro")&&isNuevo){
             dlBDD.setFechaAltaImss(dlBDD.getFechaInicioContrato());
             dlBDD.setNumeroSeguro(dlForm.getNumeroSeguro());
         }else if(existsInEsquemaDePago(esquemaCampo, "dLa.numeroSeguro")){
             dlBDD.setNumeroSeguro(dlForm.getNumeroSeguro());
         }else{
             dlBDD.setNumeroSeguro(null);
         }
         
         if(existsInEsquemaDePago(esquemaCampo, "dLa.numeroInfonavit")){
             dlBDD.setNumeroInfonavit(isNotEmpty(dlForm.getNumeroInfonavit()));
         }else{
             dlBDD.setNumeroInfonavit(null);
         }
         
         if(existsInEsquemaDePago(esquemaCampo, "dLa.reconocimientoAntiguedad")){
             dlBDD.setAntiguedadReconocida(dlForm.getAntiguedadReconocida());
             if(dlBDD.getAntiguedadReconocida()){
                 dlBDD.setAntiguedadDesde(procesayyyyMMdd(request.getParameter("fechaDesdeAntiguedad")));
                 dlBDD.setTiempoAntiguedad(request.getParameter("tiempoAntiguedad"));
             }else{
                 dlBDD.setAntiguedadDesde(null);
                 dlBDD.setTiempoAntiguedad(null);             
             }
         }         
         
         return dlBDD;
    }
    
    @Override
    public DatosLaborales getDatosLaborales(DatosLaborales laboralesAntiguos) {
        laboralesAntiguos.setPercepcionBasadaEnSUP(Boolean.FALSE);
        laboralesAntiguos.setSalarioUnicoProfesional(null);
        laboralesAntiguos.setActividadProfesional(laboralesAntiguos.getActividadProfesional());
        laboralesAntiguos.setAreaDepartamento(laboralesAntiguos.getAreaDepartamento());
        laboralesAntiguos.setCredencialLaboral(laboralesAntiguos.getCredencialLaboral());
        laboralesAntiguos.setTarjetaTdu(laboralesAntiguos.getTarjetaTdu());
        laboralesAntiguos.setSueldoMensual(laboralesAntiguos.getSueldoMensual());
        laboralesAntiguos.setSalarioDiario(laboralesAntiguos.getSalarioDiario());
        laboralesAntiguos.setSalarioDiarioIntegrado(laboralesAntiguos.getSalarioDiarioIntegrado());
        laboralesAntiguos.setSalariosImss(laboralesAntiguos.getSalariosImss());
        laboralesAntiguos.setFechaInicioContrato(laboralesAntiguos.getFechaAltaImss());
        laboralesAntiguos.setFechaFinContrato(laboralesAntiguos.getFechaFinContrato());
        laboralesAntiguos.setFechaTimbreContrato(laboralesAntiguos.getFechaTimbreContrato());
        laboralesAntiguos.setNumeroInfonavit(laboralesAntiguos.getNumeroInfonavit());
        laboralesAntiguos.setFechaAltaImss(getFechaAltaImss(laboralesAntiguos.getEsquemaPago(), laboralesAntiguos.getFechaAltaImss()));
        laboralesAntiguos.setTiempoContrato(getTiempoAB(laboralesAntiguos.getFechaInicioContrato(), laboralesAntiguos.getFechaFinContrato()));
        //laboralesAntiguos.setFondoDeAhorroDisponible(Boolean.TRUE);
        laboralesAntiguos.setFondoDeAhorroActual((short)0);
        if(laboralesAntiguos.getReconocimientoAntiguedad()!=null && laboralesAntiguos.getReconocimientoAntiguedad().equalsIgnoreCase("Sí")){
            laboralesAntiguos.setAntiguedadReconocida(Boolean.TRUE);
            laboralesAntiguos.setAntiguedadDesde(laboralesAntiguos.getFechaInicioContrato());
            laboralesAntiguos.setTiempoAntiguedad(getTiempoAB(laboralesAntiguos.getAntiguedadDesde(), laboralesAntiguos.getFechaInicioContrato()));
        }else{
            laboralesAntiguos.setAntiguedadReconocida(Boolean.FALSE);
            laboralesAntiguos.setAntiguedadDesde(null);
            laboralesAntiguos.setTiempoAntiguedad(null);
        }        
        laboralesAntiguos.setNumeroSeguro(laboralesAntiguos.getNumeroSeguro());
        laboralesAntiguos.setLugarTrabajo(laboralesAntiguos.getLugarTrabajo());
        laboralesAntiguos.setEmpresaAnterior(null);
        laboralesAntiguos.setNombreContrato("");
        return laboralesAntiguos;
    }
    
   private Date getFechaAltaImss(EsquemaPago esquemaPago, Date fechaImss){
       if(esquemaPago.getDescripcion().equalsIgnoreCase("Mixto")||esquemaPago.getDescripcion().equalsIgnoreCase("Nominal")){
           return  fechaImss;
       }else{
           return null;
       }
   }
   
   private String getTiempoAB(Date from, Date to){
       if(from == null || to == null) return null;
       String format = "dd/MM/yyyy";
       DateFormat df = new SimpleDateFormat(format);
       DateTimeFormatter fmt = DateTimeFormatter.ofPattern(format);
       String fromString = df.format(from);
       String toString = df.format(to);       
        LocalDate fromLocalDate = LocalDate.parse(fromString, fmt);
        LocalDate toLocalDate = LocalDate.parse(toString, fmt);
        Period periodo = Period.between(fromLocalDate, toLocalDate);
        String result;
        if(periodo.getYears()==1){
            result = periodo.getYears()+" año,";
        }else{
            result = periodo.getYears()+" años,";
        }
        if(periodo.getMonths()==1){
            result +=periodo.getMonths()+" mes,";
        }else{
            result +=periodo.getMonths()+" meses,";
        }
        if(periodo.getDays()==1){
            result +=periodo.getDays()+" día";
        }else{
            result +=periodo.getDays()+" días";
        }        
        return result;
   }
   
    private String isNotEmpty(String string){
        return (string.isEmpty())?null:string;
    }
    
     private Date procesayyyyMMdd(String fechaString) throws ParseException {        
        String yyyyMMdd = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        return formatter.parse(fechaString);
    } 
     
    private boolean existsInEsquemaDePago( List<EsquemaCampo> camposDelEsquema, String keyword){
        try {           
            return camposDelEsquema.stream().anyMatch(ec -> ec.getCamposFormulario().getNombre().equalsIgnoreCase(keyword));
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intertar validar un campo {"+keyword+"} del esquema  --> "+e.getMessage(),e);
            return false;
        }
    }
}
