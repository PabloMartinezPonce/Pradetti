/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.pojoh.ActaNotarial;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.ZonaSm;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.ClienteService;
import com.pradettisanti.payroll.service.ContratistaService;
import com.pradettisanti.payroll.service.ContratoService;
import com.pradettisanti.payroll.service.ContratosService;
import com.pradettisanti.payroll.service.PDFiTextService;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service encargado de parsear los contratos generador dentro del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Service
public class ContratosServiceImpl implements ContratosService {
    
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContratosServiceImpl.class);
    
    @Autowired
    private AgremiadoService agremiadoService;
    public AgremiadoService getAgremiadoService(){
        return agremiadoService;
    }
    public void setAgremiadoService(AgremiadoService agremiadoService){
        this.agremiadoService = agremiadoService;
    }
    
    @Autowired
    private ContratistaService contratistaService;
    public ContratistaService getContratistaService(){
        return contratistaService;
    }
    public void setContratistaService(ContratistaService contratistaService){
        this.contratistaService = contratistaService;
    }
    
    @Autowired
    private ClienteService clienteService;
    public ClienteService getClienteService(){
        return clienteService;
    }
    public void setClienteService(ClienteService clienteService){
        this.clienteService = clienteService;
    }
    
    @Autowired
    private ContratoService contratoService;
    public ContratoService getContratoService(){
        return contratoService;
    }
    public void setContratoService(ContratoService contratoService){
        this.contratoService = contratoService;
    }
    
    @Autowired
    private PDFiTextService  pDFiTextService;
    public PDFiTextService getDFiTextService(){
        return  pDFiTextService;
    }
    public void setPDFiTextService(PDFiTextService pDFiTextService){
        this.pDFiTextService = pDFiTextService;
    }
    
    @Autowired
    private ZonasSmServiceImpl zonasSmServiceImpl;
    public ZonasSmServiceImpl getZonasSmServiceImpl(){
        return zonasSmServiceImpl;
    }
    public void setZonasSmServiceImpl(ZonasSmServiceImpl zonasSmServiceImpl){
        this.zonasSmServiceImpl = zonasSmServiceImpl;
    }

    /**
     * Metodo encargado de generar un nuevo contrato entre un contratista y un colaborador
     * @param contratista Instancia de contratista
     * @param agremiado Instancia de agremiado
     * @param contrato instancia de contrato
     * @param testigoColaborador Cadena de texto
     * @param OcupacionAg cadena de texto
     * @param OrigenAg Cadena de texto
     * @param testigoCliente Cadena de texto
     * @param OcupacionCl Cadena de texto
     * @param OrigenCl Cadena de texto
     * @param fechaDelContrato Instancia Date
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream getNuevoContratoContratistaColaborador(Contratista contratista, Agremiado agremiado, Contrato contrato, String testigoColaborador, String OcupacionAg, String OrigenAg, String testigoCliente, String OcupacionCl, String OrigenCl, Date fechaDelContrato) {
        LOGGER.info("[Service, ContratosServiceImpl] Se solicita la creación de un contrato de contratista - colaborador {"+contratista.getNombreEmpresa()+','+agremiado.getDatosPersonales().getNombre()+' '+agremiado.getDatosPersonales().getApellidoPaterno()+' '+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno())+','+contrato.getNombre()+'}');
        boolean datosContratista, datosColaborador, datosContrato, datosEspeciales;
        datosContratista = datosColaborador = datosContrato = datosEspeciales = false;
        if( contrato.getContratoDeColaborador()  && contrato.getActivo() ){
            String contratoTexto = contrato.getContrato();
            for (String tokenConstratista : DATOS_DE_CONTRATISTA) {
                String token ="{{"+tokenConstratista+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokenContratista(tokenConstratista, contratista);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosContratista = true;
                }
            }
            for (String tokenColaborador : DATOS_DEL_COLABORADOR) {
                String token ="{{"+tokenColaborador+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokenColaborador(tokenColaborador, agremiado, fechaDelContrato);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosColaborador = true;
                }
            }
            for (String tokenCCC : DATOS_CONTRATO_CONTRATISTA_COLABORADOR) {
                String token = "{{"+tokenCCC+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokenContratoClienteColaborador( tokenCCC, fechaDelContrato,  testigoColaborador,  testigoCliente,  OcupacionAg,  OcupacionCl,  OrigenAg, OrigenCl);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosContrato = true;
                }
            }
            if(contratoTexto.contains("<span class='specialComponent'>{{FIRMA_DEL_COLABORADOR_Y_DEL_CONTRATISTA}}</span>")){
                contratoTexto =  contratoTexto.replace("<span class='specialComponent'>{{FIRMA_DEL_COLABORADOR_Y_DEL_CONTRATISTA}}</span>", FIRMA_DEL_COLABORADOR_Y_DEL_CONTRATISTA);
                if(contratoTexto.contains("<span class='specialComponent'>{{FIRMA_DE_LOS_TESTIGOS}}</span>")){
                      contratoTexto =  contratoTexto.replace("<span class='specialComponent'>{{FIRMA_DE_LOS_TESTIGOS}}</span>", FIRMA_DE_LOS_TESTIGOS);
                }
                for (String tokenFCCC : FIRMAS_CONTRATO_CONTRATISTA_COLABORADOR) {
                    String token = "{{"+tokenFCCC+"}}";
                    if(contratoTexto.contains(token)){
                        String dato = getDatoToTokenFirmasContratoContratistaColaborador( tokenFCCC, agremiado, contratista, testigoColaborador, testigoCliente );
                        contratoTexto = contratoTexto.replace(token, dato);
                        datosEspeciales = true;
                    }
                }
            }
            if( datosContratista && datosColaborador && datosContrato && datosEspeciales){
                return  pDFiTextService.createAgreement(contratista, agremiado, contratoTexto);
            }else{
                LOGGER.error("[Service, ContratosServiceImpl] El contrato contratista - colaborador {"+contrato.getIdContrato()+"} no puede ser creado debido a que  no cumple con todos los campos requeridos para ser generado.");
                return null;
            }
        }else{
            LOGGER.error("[Service, ContratosServiceImpl] El contrato contratista - colaborador {"+contrato.getIdContrato()+"} no puede ser accedido debido a que  no pertenece a contratistas para contrato de colaboradores o no se encuentra activo.");
            throw new UnsupportedOperationException("El contrato contratista - colaborador {"+contrato.getIdContrato()+"} no puede ser accedido debido a que  no pertenece a contratista para contrato de colaboradores o no se encuentra activo.");
        }
    }

    /**
     * Metodo para generar un contrato de renovacion entre un contratita y un colaborador
     * @param renovacionContrato Instancia de RenovacionContrato
     * @param contratista Instancia de contratista
     * @param agremiado Instancia de agremiado
     * @param contrato instancia de contrato
     * @param testigoColaborador Cadena de texto
     * @param OcupacionAg cadena de texto
     * @param OrigenAg Cadena de texto
     * @param testigoCliente Cadena de texto
     * @param OcupacionCl Cadena de texto
     * @param OrigenCl Cadena de texto
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream getNuevoContratoContratistaColaborador(SolicitudRenovacionContrato renovacionContrato, Contratista contratista, Agremiado agremiado, Contrato contrato, String testigoColaborador, String OcupacionAg, String OrigenAg, String testigoCliente, String OcupacionCl, String OrigenCl) {
        LOGGER.info("[Service, ContratosServiceImpl] Se solicita la creación de una renovación de  contrato de contratista - colaborador {"+contratista.getNombreEmpresa()+','+agremiado.getDatosPersonales().getNombre()+' '+agremiado.getDatosPersonales().getApellidoPaterno()+' '+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno())+','+contrato.getNombre()+'}');
        boolean datosContratista, datosColaborador, datosContrato, datosEspeciales;
        datosContratista = datosColaborador = datosContrato = datosEspeciales = false;
        if( contrato.getContratoDeColaborador()  && contrato.getActivo() ){
            String contratoTexto = contrato.getContrato();
            for (String tokenConstratista : DATOS_DE_CONTRATISTA) {
                String token ="{{"+tokenConstratista+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokenContratista(tokenConstratista, contratista);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosContratista = true;
                }
            }
            for (String tokenColaborador : DATOS_DEL_COLABORADOR) {
                String token ="{{"+tokenColaborador+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokenColaboradorRenovacion(tokenColaborador, agremiado,renovacionContrato);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosColaborador = true;
                }
            }
            for (String tokenCCC : DATOS_CONTRATO_CONTRATISTA_COLABORADOR) {
                String token = "{{"+tokenCCC+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokenContratoClienteColaboradorRenovacion( tokenCCC,  testigoColaborador,  testigoCliente,  OcupacionAg,  OcupacionCl,  OrigenAg, OrigenCl,renovacionContrato);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosContrato = true;
                }
            }
            if(contratoTexto.contains("<span class='specialComponent'>{{FIRMA_DEL_COLABORADOR_Y_DEL_CONTRATISTA}}</span>")){
                contratoTexto =  contratoTexto.replace("<span class='specialComponent'>{{FIRMA_DEL_COLABORADOR_Y_DEL_CONTRATISTA}}</span>", FIRMA_DEL_COLABORADOR_Y_DEL_CONTRATISTA);
                if(contratoTexto.contains("<span class='specialComponent'>{{FIRMA_DE_LOS_TESTIGOS}}</span>")){
                      contratoTexto =  contratoTexto.replace("<span class='specialComponent'>{{FIRMA_DE_LOS_TESTIGOS}}</span>", FIRMA_DE_LOS_TESTIGOS);
                }
                for (String tokenFCCC : FIRMAS_CONTRATO_CONTRATISTA_COLABORADOR) {
                    String token = "{{"+tokenFCCC+"}}";
                    if(contratoTexto.contains(token)){
                        String dato = getDatoToTokenFirmasContratoContratistaColaborador( tokenFCCC, agremiado, contratista, testigoColaborador, testigoCliente );
                        contratoTexto = contratoTexto.replace(token, dato);
                        datosEspeciales = true;
                    }
                }
            }
            if( datosContratista && datosColaborador && datosContrato && datosEspeciales){
                return  pDFiTextService.createAgreement(contratista, agremiado, contratoTexto);
            }else{
                LOGGER.error("[Service, ContratosServiceImpl] El contrato contratista - colaborador {"+contrato.getIdContrato()+"} no puede ser creado debido a que  no cumple con todos los campos requeridos para ser generado.");
                return null;
            }
        }else{
            LOGGER.error("[Service, ContratosServiceImpl] El contrato contratista - colaborador {"+contrato.getIdContrato()+"} no puede ser accedido debido a que  no pertenece a contratistas para contrato de colaboradores o no se encuentra activo.");
            throw new UnsupportedOperationException("El contrato contratista - colaborador {"+contrato.getIdContrato()+"} no puede ser accedido debido a que  no pertenece a contratista para contrato de colaboradores o no se encuentra activo.");
        }
    }

    /**
     * Metodo para generar un contrato entre un contratista y un cliente
     * @param contratoEmpresas Intancia de ContratoEmpresas
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream getNuevoContratoContratistaCliente(ContratoEmpresas contratoEmpresas) {
        LOGGER.info("[Service, ContratosServiceImpl] Se solicita la creación de un contrato de contratista - cliente {"+contratoEmpresas.getContratistaObj()+','+contratoEmpresas.getClienteObj()+','+contratoEmpresas.getIdPlantillaContrato()+'}');
        Contratista contratista = contratistaService.getContratista(contratoEmpresas.getContratistaObj());
        Cliente cliente = clienteService.getCliente(contratoEmpresas.getClienteObj());
        Contrato contrato = contratoService.getContrato(contratoEmpresas.getIdPlantillaContrato());
        boolean  datosContratista, datosContratante, datosContrato, datosEspeciales;
         datosContratista = datosContratante = datosContrato = datosEspeciales = false;
        if( !contrato.getContratoDeColaborador() && contrato.getActivo() ){          
            String contratoTexto = contrato.getContrato();
            for (String tokenContratista : DATOS_DEL_CONTRATISTA) {
                String token ="{{"+tokenContratista+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokentokenContratista(tokenContratista, contratista);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosContratista = true;
                }
            }
            for (String tokenContratante : DATOS_DEL_CONTRATANTE) {
                String token ="{{"+tokenContratante+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokentokenContratante(tokenContratante, cliente);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosContratante = true;
                }
            }      
            for (String tokenContratoEmpresas : DATOS_CONTRATO_EMPRESAS) {
                String token ="{{"+tokenContratoEmpresas+"}}";
                if(contratoTexto.contains(token)){
                    String dato = getDatoToTokentokenContratoEmpresas(tokenContratoEmpresas, contratoEmpresas);
                    contratoTexto = contratoTexto.replace(token, dato);
                    datosContrato = true;
                }
            }            
            if(contratoTexto.contains("<span class='specialComponent'>{{FRIMA_DEL_CONTRATISTA_Y_DEL_CONTRATANTE}}</span>")){
                contratoTexto =  contratoTexto.replace("<span class='specialComponent'>{{FRIMA_DEL_CONTRATISTA_Y_DEL_CONTRATANTE}}</span>", FIRMA_DEL_CONTRATISTA_Y_DEL_CONTRATANTE);
                if(contratoTexto.contains("<span class='specialComponent'>{{FIRMA_DEL_TESTIGO_DEL_CONTRATISTA}}</span>")){
                      contratoTexto =  contratoTexto.replace("<span class='specialComponent'>{{FIRMA_DEL_TESTIGO_DEL_CONTRATISTA}}</span>", FIRMA_DEL_TESTIGO_DEL_CONTRATISTA);
                }
                if(contratoTexto.contains("<span class='specialComponent'>{{FIRMA_DEL_TESTIGO_DEL_CONTRATANTE}}</span>")){
                      contratoTexto =  contratoTexto.replace("<span class='specialComponent'>{{FIRMA_DEL_TESTIGO_DEL_CONTRATANTE}}</span>", FIRMA_DEL_TESTIGO_DEL_CONTRATANTE);
                }
                for (String tokenFCCC : FIRMAS_CONTRATO_ENTRE_EMPRESAS) {
                    String token = "{{"+tokenFCCC+"}}";
                    if(contratoTexto.contains(token)){
                        String dato = getDatoToTokenFirmasContratoEntreEmpresas( tokenFCCC, contratista, cliente, contratoEmpresas );
                        contratoTexto = contratoTexto.replace(token, dato);
                        datosEspeciales = true;
                    }
                }
            }
            if( datosContratista && datosContratante &&  datosContrato && datosEspeciales){
                   return  pDFiTextService.createAgreement(contratista, cliente, contratoTexto);
               }else{
                    LOGGER.error("[Service, ContratosServiceImpl] El contrato entre empresas {"+contrato.getIdContrato()+"} no puede ser creado debido a que  no cumple con todos los campos requeridos para ser generado.");
                   return null;
            }
        }else{
            LOGGER.error("[Service, ContratosServiceImpl] El contrato entre empresas {"+contrato.getIdContrato()+"} no puede ser accedido debido a que  no pertenece a contratistas o no se encuentra activo.");
            throw new UnsupportedOperationException("El contrato entre empresas {"+contrato.getIdContrato()+"} no puede ser accedido debido a que  no pertenece a contratistas o no se encuentra activo.");
        }
    }
    
    /**
     * Metodo encargado de deolver los campos dinamicos de un contratista
     * @return Map de Strings
     */
    @Override
    public Map<String,String> getDatosDeContratista(){
        Map<String,String> datosDelCliente =  new HashMap<>();
        for (String string : DATOS_DE_CONTRATISTA) {
            String value = string.replace('_', ' ');
            datosDelCliente.put(string, value.toLowerCase());
        }
        return datosDelCliente;
    }
    
    /**
     * Metodo encargado de deolver los campos dinamicos de un colaborador
     * @return Map de Strings
     */
    @Override
    public Map<String,String> getDatosDelColaborador(){
        Map<String,String> datosDelColaborador = new HashMap<>();
        for (String string : DATOS_DEL_COLABORADOR) {
            String value = string.replace('_', ' ');
            datosDelColaborador.put(string, value.toLowerCase());
        }
        return datosDelColaborador;
    }
    
    /**
     * Metodo encargado de deolver los campos dinamicos de un contrato entre colaborador y contratista
     * @return Map de Strings
     */
    @Override
    public Map<String,String> getDatosContratoContratistaColaborador(){
        Map<String,String> datosDelContrato = new HashMap<>();
        for (String string : DATOS_CONTRATO_CONTRATISTA_COLABORADOR) {
            String value = string.replace('_', ' ');
            datosDelContrato.put(string, value.toLowerCase());
        }
        return datosDelContrato;
    }
    
    /**
     * Metodo encargado de deolver los campos dinamicos especiales de un contratista y un colaborador
     * @return Map de Strings
     */
    @Override
    public Map<String,String> getCamposEspecialesContratistaColaborador(){
        Map<String,String> camposEspeciales = new HashMap<>();
        for (String string : FIRMAS_CONTRATO_CONTRATISTA_COLABORADOR) {
            String value = string.replace('_', ' ');
            camposEspeciales.put(string, value.toLowerCase());
        }
        return camposEspeciales;
    }
    
    /**
     * Metodo encargado de deolver los campos dinamicos de un contratista
     * @return Map de Strings
     */
    @Override
     public Map<String,String> getDatosDelContratista(){
        Map<String,String> datosDelContratista = new HashMap<>();
        for (String string : DATOS_DEL_CONTRATISTA) {
            String value = string.replace('_', ' ');
            datosDelContratista.put(string, value.toLowerCase());
        }
        return datosDelContratista;
     }
     
     /**
      * Metodo encargado de deolver los campos dinamicos de un contratante
     * @return Map de Strings
      */
     @Override
     public Map<String,String> getDatosDelContratante(){
        Map<String,String> datosDelContratante = new HashMap<>();
        for (String string : DATOS_DEL_CONTRATANTE) {
            String value = string.replace('_', ' ');
            datosDelContratante.put(string, value.toLowerCase());
        }
        return datosDelContratante;
     }
   
     /**
      * Metodo encargado de deolver los campos dinamicos de un contrato entre empresas
     * @return Map de Strings
      */
     @Override
     public Map<String,String> getDatosDelContratoEntreEmpresas(){
        Map<String,String> datosDelContratoEntreEmpresas = new HashMap<>();
        for (String string : DATOS_CONTRATO_EMPRESAS) {
            String value = string.replace('_', ' ');
            datosDelContratoEntreEmpresas.put(string, value.toLowerCase());
        }
        return datosDelContratoEntreEmpresas;
     }
    
    private String getDatoToTokenContratista(String datoCONTRATISTA, Contratista c) {
        try {
                ActaNotarial actaNotarial = c.getActaNotarialList().get(0);
                EmpresasDomicilio  empresasDomicilio = null;
                switch(datoCONTRATISTA){
                    case "NOMBRE_DEL_CONTRATISTA":
                        return c.getNombreEmpresa().toUpperCase();
                    case "REPRESENTANTE_DEL_CONTRATISTA":
                        return c.getRepresentateLegal().toUpperCase();
                    case "TESTIMONIO_NOTARIAL_DEL_CONTRATISTA":
                        return actaNotarial.getInstrumento().toUpperCase();
                    case "VOLUMEN_NOTARIAL_DEL_CONTRATISTA":
                        if(actaNotarial != null){
                            return actaNotarial.getVolumen().toUpperCase();
                        }
                        break;
                    case "INSTRUMENTO_NOTARIAL_DEL_CONTRATISTA":
                        if(actaNotarial != null){
                            return actaNotarial.getInstrumento().toUpperCase();
                        }
                        break;
                    case "NUMERO_NOTARIA_DEL_CONTRATISTA":
                        if(actaNotarial != null){
                            return actaNotarial.getNumeroNotarial().toUpperCase();
                        }
                        break;
                    case "NOTARIO_DEL_CONTRATISTA":
                        if(actaNotarial != null){
                            return actaNotarial.getNotario();
                        }
                        break;
                    case "FECHA_NOTARIAL_DEL_CONTRATISTA":
                        if(actaNotarial != null){
                            return formatearFecha( actaNotarial.getFechaVolumen() );
                        }
                        break;
                    case "DOMICILIO_FISCAL_DEL_CONTRATISTA":                
                        if( c.getEmpresasDomicilioList().get(0).getTipoDomicilioObj() == 1){ //Domicilio Fiscal 
                            empresasDomicilio = c.getEmpresasDomicilioList().get(0);
                            return "CALLE "+empresasDomicilio.getCalle().toUpperCase()+" NÚMERO "+empresasDomicilio.getNumero().toUpperCase()+", COLONIA "+empresasDomicilio.getColonia().toUpperCase()+", "+empresasDomicilio.getCiudad().toUpperCase()+", "+empresasDomicilio.getEstado().toUpperCase()+", C.P. "+empresasDomicilio.getCodigoPostal().toUpperCase()+"";
                        }
                        break;
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+datoCONTRATISTA+"} CONTRATISTA "+c.getNombreEmpresa().toUpperCase()+" no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokenColaborador(String tokenColaborador, Agremiado agremiado, Date FechaContrato) {
        try{
                switch(tokenColaborador){
                    case "NOMBRE_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getNombre().toUpperCase()+" "+agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase()+" "+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase())+"";
                    case "NACIONALIDAD_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getNacionalidad().toUpperCase();
                    case "ESTADO_CIVIL_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getEstadoCivil();
                    case "EDAD_DEL_COLABORADOR":
                        return getEdadTextual(agremiado.getDatosPersonales().getEdad(), agremiado.getDatosPersonales().getFechaNacimiento());
                    case "CURP_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getCurp().toUpperCase();
                    case "RFC_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getRfc().toUpperCase();
                    case "SEXO_COLABORADOR":
                        return agremiado.getDatosPersonales().getSexo().toUpperCase();
                    case "EMPRESA_A_TRABAJAR":
                        return agremiado.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase();
                    case "FECHA_DE_NACIMIENTO_DEL_COLABORADOR":
                        return formatearFecha(agremiado.getDatosPersonales().getFechaNacimiento());
                    case "DOMICILIO_DEL_COLABORADOR":
                        return "CALLE "+agremiado.getDatosDomicilio().getCalle().toUpperCase()+" NÚMERO "+agremiado.getDatosDomicilio().getNumero().toUpperCase()+", COLONIA "+agremiado.getDatosDomicilio().getColonia().toUpperCase()+", "+agremiado.getDatosDomicilio().getCiudad().toUpperCase()+", "+agremiado.getDatosDomicilio().getEstado().toUpperCase()+", C.P. "+agremiado.getDatosDomicilio().getCodigoPostal().toUpperCase()+"";
                    case "CREDITO_INFONAVIT_DEL_COLABORADOR":
                        if(agremiado.getDatosLaborales().getNumeroInfonavit() != null){
                            return "Tener un crédito de INFONAVIT contratado con número "+agremiado.getDatosLaborales().getNumeroInfonavit().toUpperCase();
                        }else{
                            return "No tener créditos contratados con el Instituto del Fondo Nacional de la Vivienda para los Trabajadores (INFONAVIT); así como no tener créditos contratados con el Instituto del Fondo Nacional para el Consumo de los Trabajadores (FONACOT).";
                        }
                    case "TIPO_NOMINA":
                        return agremiado.getDatosLaborales().getTipoNominaObj().getTipoNomina().toLowerCase();
                    case "DIAS_TIPO_NOMINA":
                        String tipoNomina = agremiado.getDatosLaborales().getTipoNominaObj().getTipoNomina();
                        switch(tipoNomina){
                            case "Semanal":
                                return "los días siete de cada semana";
                            case "Catorcenal":
                                return "cada catorce días según corresponda el calendario de pago de <b>\"EL PATRÓN\"</b>";
                            case "Quincenal":
                                return "los días quince, treinta o treinta y uno según corresponda a cada quincena";
                            case "Mensual": 
                                return "los días treinta o treinta y uno según corresponda a cada mes";
                        }
                    case "NSS_DEL_COLABORADOR":
                        return agremiado.getDatosLaborales().getNumeroSeguro().toUpperCase();
                    case "PUESTO_DEL_COLABORADOR":
                        return agremiado.getDatosLaborales().getActividadProfesional().toUpperCase();
                    case "SALARIO_DIARIO_DEL_COLABORADOR":
                        return getMoney(agremiado.getDatosLaborales());
                    case "TIPO_DE_CONTRATO_DEL_COLABORADOR":
                        return agremiadoService.getTipoContrato( agremiado.getDatosLaborales().getTipoContratoObj().getIdTipoContrato() ).getDescripcion().toUpperCase();
                    case "TIEMPO_DE_CONTRATO":
                        return getTiempoExactly(agremiado.getDatosLaborales().getTiempoContrato());
                    case "FIN_DE_CONTRATO":
                        return formatearFecha(agremiado.getDatosLaborales().getFechaFinContrato());
                    case "TOTAL_DIAS_CONTADOS":
                        return getBetweenDay(agremiado.getDatosLaborales());
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+tokenColaborador+"} colabordor "+agremiado.getIdAgremiado()+" no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokenColaboradorRenovacion(String tokenColaborador, Agremiado agremiado, SolicitudRenovacionContrato renovacionContrato) {
        try{
                switch(tokenColaborador){
                    case "NOMBRE_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getNombre().toUpperCase()+" "+agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase()+" "+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase())+"";
                    case "NACIONALIDAD_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getNacionalidad().toUpperCase();
                    case "ESTADO_CIVIL_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getEstadoCivil();
                    case "EDAD_DEL_COLABORADOR":
                        return getEdadTextual(agremiado.getDatosPersonales().getEdad(), agremiado.getDatosPersonales().getFechaNacimiento());
                    case "CURP_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getCurp().toUpperCase();
                    case "RFC_DEL_COLABORADOR":
                        return agremiado.getDatosPersonales().getRfc().toUpperCase();
                    case "SEXO_COLABORADOR":
                        return agremiado.getDatosPersonales().getSexo().toUpperCase();
                    case "EMPRESA_A_TRABAJAR":
                        return agremiado.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase();
                    case "FECHA_DE_NACIMIENTO_DEL_COLABORADOR":
                        return formatearFecha(agremiado.getDatosPersonales().getFechaNacimiento());
                    case "DOMICILIO_DEL_COLABORADOR":
                        return "CALLE "+agremiado.getDatosDomicilio().getCalle().toUpperCase()+" NÚMERO "+agremiado.getDatosDomicilio().getNumero().toUpperCase()+", COLONIA "+agremiado.getDatosDomicilio().getColonia().toUpperCase()+", "+agremiado.getDatosDomicilio().getCiudad().toUpperCase()+", "+agremiado.getDatosDomicilio().getEstado().toUpperCase()+", C.P. "+agremiado.getDatosDomicilio().getCodigoPostal().toUpperCase()+"";
                    case "CREDITO_INFONAVIT_DEL_COLABORADOR":
                        if(agremiado.getDatosLaborales().getNumeroInfonavit() != null){
                            return "Tener un crédito de INFONAVIT contratado con número "+agremiado.getDatosLaborales().getNumeroInfonavit().toUpperCase();
                        }else{
                            return "No tener créditos contratados con el INFONAVIT";
                        }
                    case "TIPO_NOMINA":
                        return agremiado.getDatosLaborales().getTipoNominaObj().getTipoNomina().toLowerCase();
                    case "DIAS_TIPO_NOMINA":
                        String tipoNomina = agremiado.getDatosLaborales().getTipoNominaObj().getTipoNomina();
                        switch(tipoNomina){
                            case "Semanal":
                                return "los días siete de cada semana";
                            case "Catorcenal":
                                return "cada catorce días según corresponda el calendario de pago de <b>\"EL PATRÓN\"</b>";
                            case "Quincenal":
                                return "los días quince, treinta o treinta y uno según corresponda a cada quincena";
                            case "Mensual": 
                                return "los días treinta o treinta y uno según corresponda a cada mes";
                        }
                    case "NSS_DEL_COLABORADOR":
                        return agremiado.getDatosLaborales().getNumeroSeguro().toUpperCase();
                    case "PUESTO_DEL_COLABORADOR":
                        return agremiado.getDatosLaborales().getActividadProfesional().toUpperCase();
                    case "SALARIO_DIARIO_DEL_COLABORADOR":
                        if (renovacionContrato.getSalarioDiario()!=null) {                            
                            return getMoney(agremiado.getDatosLaborales(),renovacionContrato);
                        } else {                            
                            return getMoney(agremiado.getDatosLaborales());
                        }
                    case "TIPO_DE_CONTRATO_DEL_COLABORADOR":
                        return renovacionContrato.getTipoContratoObj().getDescripcion().toUpperCase();
                    case "TIEMPO_DE_CONTRATO":
                        return  getTiempoExactly(renovacionContrato.getPeriodoContrato());
                    case "FIN_DE_CONTRATO":
                        return formatearFecha(renovacionContrato.getFechaFinal());
                    case "TOTAL_DIAS_CONTADOS":
                        return getBetweenDay(renovacionContrato);
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+tokenColaborador+"} colabordor "+agremiado.getIdAgremiado()+" no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokenContratoClienteColaborador(String token, Date fechaContrato, String testigoAgremiado, String testigoCliente, String OcupacionAg, String OcupacionCl, String OrigenAg, String OrigenCl) {
        try{
                switch(token){
                    case "FECHA_DEL_CONTRATO":
                        return formatearFechaLarga(fechaContrato);
                    case "TESTIGO_UNO":
                        return testigoAgremiado.toUpperCase();
                    case "OCUPACION_TESTIGO_UNO":
                        return OcupacionAg.toUpperCase();
                    case "ORIGEN_TESTIGO_UNO":
                        return OrigenAg;
                    case "TESTIGO_DOS":
                        return testigoCliente.toUpperCase();
                    case "OCUPACION_TESTIGO_DOS":
                        return OcupacionCl.toUpperCase();
                    case "ORIGEN_TESTIGO_DOS":
                        return OrigenCl;
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+token+"} del contrato cliente - colaborador no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokenContratoClienteColaboradorRenovacion(String token, String testigoAgremiado, String testigoCliente, String OcupacionAg, String OcupacionCl, String OrigenAg, String OrigenCl, SolicitudRenovacionContrato renovacionContrato) {
        try{
                switch(token){
                    case "FECHA_DEL_CONTRATO":
                        // Se cambia getFechaAplicacion() por  getFechaInicial()
                        return formatearFechaLarga(renovacionContrato.getFechaInicial());
                    case "TESTIGO_UNO":
                        return testigoAgremiado.toUpperCase();
                    case "OCUPACION_TESTIGO_UNO":
                        return OcupacionAg.toUpperCase();
                    case "ORIGEN_TESTIGO_UNO":           
                        return OrigenAg;
                    case "TESTIGO_DOS":
                        return testigoCliente.toUpperCase();
                    case "OCUPACION_TESTIGO_DOS":
                        return OcupacionCl.toUpperCase();
                    case "ORIGEN_TESTIGO_DOS":
                        return OrigenCl;
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+token+"} del contrato cliente - colaborador no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokenFirmasContratoContratistaColaborador(String tokenFCCC, Agremiado agremiado, Contratista contratista, String testigoAgremiado, String testigoCliente) {
        try{
                switch(tokenFCCC){
                    case "EL_COLABORADOR":
                        return "C. "+agremiado.getDatosPersonales().getNombre().toUpperCase()+" "+agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase()+" "+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase());
                    case "REPRESENTANTE_LEGAL_DEL_CONTRATISTA":
                        return "C. "+contratista.getRepresentateLegal().toUpperCase()+"<br/>REPRESENTANTE LEGAL";
                    case "TESTIGO_UNO_FIRMA":
                        return "C. "+testigoAgremiado.toUpperCase();
                    case "TESTIGO_DOS_FIRMA":
                        return "C. "+testigoCliente.toUpperCase();
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+tokenFCCC+"} contrato cliente - colaborador no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokentokenContratista(String tokenContratista, Contratista contratista) {
        try{
                switch(tokenContratista){
                    case "NOMBRE_DEL_CONTRATISTA":
                        return contratista.getNombreEmpresa().toUpperCase();
                    case "REPRESENTATE_DEL_CONTRATISTA":
                        return contratista.getRepresentateLegal().toUpperCase();
                    case "TESTIMONIO_NOTARIAL_DEL_CONTRATISTA":
                        return contratista.getActaNotarialList().get(0).getInstrumento().toUpperCase();
                    case "VOLUMEN_NOTARIAL_DEL_CONTRATISTA":
                        return contratista.getActaNotarialList().get(0).getVolumen().toUpperCase();
                    case "FECHA_NOTARIAL_DEL_CONTRATISTA":
                        return formatearFecha(contratista.getActaNotarialList().get(0).getFechaVolumen());
                    case "NUMERO_NOTARIA_DEL_CONTRATISTA":
                        return contratista.getActaNotarialList().get(0).getNumeroNotarial().toUpperCase();
                    case "NOTARIO_DEL_CONTRATISTA":
                        return contratista.getActaNotarialList().get(0).getNotario().toUpperCase();
                    case "DOMICILIO_NOTARIO_DEL_CONTRATISTA":
                        return contratista.getActaNotarialList().get(0).getDireccionNotario().toUpperCase();
                    case "DOMICILIO_FISCAL_DEL_CONTRATISTA":
                        return getDomicilioFiscal(contratista.getEmpresasDomicilioList());
                    case "RFC_CONTRATISTA":
                        return contratista.getRfc().toUpperCase();
                    case "DOMICILIO_DE_NOTIFICACION_DEL_CONTRATISTA":
                        return getDomicilioNotificacion(contratista.getEmpresasDomicilioList());
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+tokenContratista+"} contratista "+contratista.getNombreEmpresa().toUpperCase()+" no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokentokenContratante(String tokenContratante, Cliente contratante) {
        try{
                switch(tokenContratante){
                    case "NOMBRE_DEL_CONTRANTE":
                        return contratante.getNombreEmpresa().toUpperCase();
                    case "REPRESENTANTE_DEL_CONTRATANTE":
                        return contratante.getRepresentanteLegal().toUpperCase();
                    case "TESTIMONIO_NOTARIAL_DEL_CONTRATANTE":
                        return contratante.getActaNotarialList().get(0).getInstrumento().toUpperCase();
                    case "VOLUMEN_DEL_CONTRATANTE":
                        return contratante.getActaNotarialList().get(0).getVolumen().toUpperCase();
                    case "FECHA_NOTARIAL_DEL_CONTRATANTE":
                        return formatearFecha(contratante.getActaNotarialList().get(0).getFechaVolumen());
                    case "NUMERO_NOTARIA_DEL_CONTRATANTE":
                        return contratante.getActaNotarialList().get(0).getNumeroNotarial().toUpperCase();
                    case "NOTARIO_DEL_CONTRATANTE":
                        return contratante.getActaNotarialList().get(0).getNotario().toUpperCase();
                    case "DOMICILIO_NOTARIO_DEL_CONTRATANTE":
                        return contratante.getActaNotarialList().get(0).getDireccionNotario().toUpperCase();
                    case "DOMICILIO_FISCAL_DEL_CONTRATANTE":
                        return getDomicilioFiscal(contratante.getEmpresasDomicilioList());
                    case "RFC_CONTRATANTE":
                        return contratante.getRfc().toUpperCase();
                    case "DOMICILIO_DE_NOTIFICACION_DEL_CONTRATANTE":
                        return getDomicilioNotificacion(contratante.getEmpresasDomicilioList());
                }
       } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+tokenContratante+"} contratante "+contratante.getNombreEmpresa().toUpperCase()+" no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokentokenContratoEmpresas(String tokenContratoEmpresas, ContratoEmpresas contratoEmpresas) {
        try{
                switch(tokenContratoEmpresas){
                    case "FECHA_DEL_CONTRATO":
                        return formatearFecha(contratoEmpresas.getFecha());
                    case "PERIODO_DE_PAGO":
                        return contratoEmpresas.getPeriodo().toUpperCase();
                    case "COMISION":
                        return ""+contratoEmpresas.getComision()+"% ("+getCDU(contratoEmpresas.getComision()) +" POR CIENTO)";
                    case "INFORMES":
                        return contratoEmpresas.getInformes().toLowerCase();
                    case "EVALUACIONES":
                        return contratoEmpresas.getEvaluaciones().toLowerCase();
                    case "DEPOSITO":
                        return contratoEmpresas.getDeposito().toLowerCase()+" horas";
                    case "TIEMPO_DEL_CONTRATO":
                        return contratoEmpresas.getTiempoDelContrato().toLowerCase();
                    case "NOMBRE_TESTIGO_CONTRATISTA":
                        return contratoEmpresas.getTcontratistaNombre().toUpperCase();
                    case "OCUPACION_TESTIGO_CONTRATISTA":
                        return contratoEmpresas.getTcontratistaOcupacion().toLowerCase();
                    case "ORIGEN_TESTIGO_CONTRATISTA":
                        return contratoEmpresas.getTcontratistaOrigen().toLowerCase();
                    case "NOMBRE_TESTIGO_CONTRATANTE":
                        return contratoEmpresas.getTclienteNombre().toUpperCase();
                    case "OCUPACION_TESTIGO_CONTRATANTE":
                        return contratoEmpresas.getTclienteOcupacion().toLowerCase();
                    case "ORIGEN_TESTIGO_CONTRATANTE":
                        return contratoEmpresas.getTclienteOrigen().toLowerCase();
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+tokenContratoEmpresas+"} contrato empresas "+contratoEmpresas.getIdContratoEmpresas()+" no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String getDatoToTokenFirmasContratoEntreEmpresas( String tokenFCCC,  Contratista contratista, Cliente contratante, ContratoEmpresas contratoEmpresas ){
         try{
                switch(tokenFCCC){
                    case "EL_CONTRATISTA":
                        return contratista.getRepresentateLegal().toUpperCase();
                    case "NOMBRE_CONTRATISTA":
                        return contratista.getNombreEmpresa().toUpperCase();
                    case "EL_CONTRATANTE":
                        return contratante.getRepresentanteLegal().toUpperCase();
                    case "NOMBRE_CONTRATANTE":
                        return contratante.getNombreEmpresa().toUpperCase();
                    case "FIRMA_DEL_TESTIGO_DEL_CONTRATISTA":
                        return contratoEmpresas.getTcontratistaNombre().toUpperCase();
                    case "FIRMA_DEL_TESTIGO_DEL_CONTRATANTE":
                        return contratoEmpresas.getTclienteNombre().toUpperCase();
                }
        } catch (Exception e) {
            LOGGER.error("[Service, ContratosServiceImpl] Token {"+tokenFCCC+"} contrato empresas firmas "+contratoEmpresas.getIdContratoEmpresas()+" no encontrado. --> "+e);
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private String formatearFecha(Date fechaVolumen) {
        if(fechaVolumen==null){
            fechaVolumen = new Date();
        }
       String dd = "dd";
       String MM = "MM";
       String yy = "yyyy";
        DateFormat formatter = new SimpleDateFormat(dd);
        String dia =  formatter.format(fechaVolumen);
        formatter = new SimpleDateFormat(MM);
        String mes =  formatter.format(fechaVolumen);
        formatter = new SimpleDateFormat(yy);
        String anio =  formatter.format(fechaVolumen);
        String fecha = dia+" de "+getMes(mes)+" de "+anio;
        return fecha;
    }
    
    private String formatearFechaLarga(Date fechaVolumen){
        if(fechaVolumen==null){
            fechaVolumen = new Date();
        }
        String dd = "dd";
       String MM = "MM";
       String yy = "yyyy";
        DateFormat formatter = new SimpleDateFormat(dd);
        String dia =  formatter.format(fechaVolumen);
        formatter = new SimpleDateFormat(MM);
        String mes =  formatter.format(fechaVolumen);
        formatter = new SimpleDateFormat(yy);
        String anio =  formatter.format(fechaVolumen);
        String fecha = getDia( dia ) +" DE "+getMes(mes)+" DE "+getAnio( anio );
        return fecha;
    }
    
    private String getDia(String dia) {
        int diaNumber = Integer.parseInt(dia);
        if(diaNumber< UNIDADES.length){
            if(diaNumber==1){
                return "PRIMERO";
            }
            return UNIDADES[diaNumber];
        }else if(diaNumber < 30){            
            return "VEINTI" + UNIDADES[diaNumber-20];
        }else {
            diaNumber = diaNumber-30;
            if(diaNumber == 0)
                return "TREINTA";
            return "TREINTA Y " + UNIDADES[diaNumber];
        }
    }

    private String getMes(String mes) {
        int mesNumber = Integer.parseInt(mes);
        return MESES[mesNumber];
    }

    private String getAnio(String anio) {
        int anioNumber = Integer.parseInt(anio);
        anioNumber = anioNumber - 2000;
        if(anioNumber< UNIDADES.length){
            return "DOS MIL "+UNIDADES[anioNumber];
        }else if(anioNumber < 30){            
            return "DOS MIL VEINTI" + UNIDADES[anioNumber-20];
        }else{
            int decena = anioNumber/10;
            int unidad = anioNumber%10;
            String number = DECENAS[decena];
            if(unidad != 0){
                number += " Y "+UNIDADES[unidad];
            }
            return "DOS MIL "+number;
        }
    }
    
    private String  getMoney(DatosLaborales datosLaborales, SolicitudRenovacionContrato renovacionContrato){ 
        double  sueldoDiarioString;
        if(renovacionContrato.getModificacionSalario()){
            if(renovacionContrato.getSUPObj()!=null){
                sueldoDiarioString = renovacionContrato.getSUPObj().getPesosDiarios();
            }else{
                switch(datosLaborales.getEsquemaPago().getDescripcion()){
                    default:                    
                    case "Mixto":
                        sueldoDiarioString = Double.parseDouble(renovacionContrato.getSalarioDiario());
                        break;
                    case "Nominal":
                        sueldoDiarioString = Double.parseDouble(renovacionContrato.getSalarioDiario());
                        break;
                    case "Sindical":
                        sueldoDiarioString = Double.parseDouble(renovacionContrato.getSueldoMensual())/30.4;
                        break;
                    case "Asimilados":
                        sueldoDiarioString = 0.0;

                }
            }
            return getMoneyFormat(sueldoDiarioString);        
        }else{
            if(datosLaborales.getPercepcionBasadaEnSUP()){
                sueldoDiarioString = datosLaborales.getSalarioUnicoProfesional().getPesosDiarios();
            }else{
                switch(datosLaborales.getEsquemaPago().getDescripcion()){
                    default:                    
                    case "Mixto":
                        sueldoDiarioString = Double.parseDouble(datosLaborales.getSalarioDiario());
                        break;
                    case "Nominal":
                        sueldoDiarioString = Double.parseDouble(datosLaborales.getSalarioDiario());
                        break;
                    case "Sindical":
                        sueldoDiarioString = Double.parseDouble(datosLaborales.getSueldoMensual())/30.4;
                        break;
                    case "Asimilados":
                        sueldoDiarioString = 0.0;

                }
            }
            return getMoneyFormat(sueldoDiarioString);
        }
    }
    
    private String getMoney(DatosLaborales datosLaborales){
        double  sueldoDiarioString;
        if(datosLaborales.getPercepcionBasadaEnSUP()){
            sueldoDiarioString = datosLaborales.getSalarioUnicoProfesional().getPesosDiarios();
        }else{
            switch(datosLaborales.getEsquemaPago().getDescripcion()){
                default:                    
                case "Mixto":
                    sueldoDiarioString = Double.parseDouble(datosLaborales.getSalarioDiario());
                    break;
                case "Nominal":
                    sueldoDiarioString = Double.parseDouble(datosLaborales.getSalarioDiario());
                    break;
                case "Sindical":
                    sueldoDiarioString = Double.parseDouble(datosLaborales.getSueldoMensual())/30.4;
                    break;
                case "Asimilados":
                    sueldoDiarioString = 0.0;

            }
        }
        return getMoneyFormat(sueldoDiarioString);
    }
    private String getMoneyFormat(double sueldoDiario){
        DecimalFormat myFormat = new DecimalFormat("###,###.##");
        String format = myFormat.format(sueldoDiario);
        String val = "$ "+format+" (";
        if(format.contains(",")){
            String miles = format.substring(0, format.indexOf(','));
            val += getMiles(miles);            
            if(format.contains(".")){
                String cdu = format.substring((format.indexOf(',')+1),format.indexOf('.'));
                val += getCDU(cdu) +" PESOS ";
                String substring = format.substring( (format.indexOf('.')+1) );
                substring = (substring.length()<2)?substring+'0':substring;
                int centavosInt = Integer.parseInt(substring);
                String centavos = getCentavos(centavosInt);
                val += centavos;
            }else{
                val += getCDU(format.substring((format.indexOf(',')+1)))+" PESOS 00/100";
            }
        }else{
             if(format.contains(".")){
                String cdu = format.substring(0,format.indexOf('.'));
                val += getCDU(cdu) +" PESOS ";
                String substring = format.substring( (format.indexOf('.')+1) );
                substring = (substring.length()<2)?substring+'0':substring;
                int centavosInt = Integer.parseInt(substring);
                String centavos = getCentavos(centavosInt);
                val += centavos;
            }else{
                 val += getCDU(format)+" PESOS 00/100";
            }
        }
        return val+" M.N.)";
    }
    
    private String getMiles(String mil){
        String tmp = "";
        int milD = Integer.parseInt(mil);
        int cien = milD/100;
        int diez = (milD - (cien*100))/10;
        int uno = (milD - (cien*100))%10;
        if(cien == 1 && diez == 0 && uno == 0){
            tmp = "CIEN";
        }else{
            if(cien != 0){
                tmp += CENTENAS[cien]+" ";
            }
            if(diez>0 && diez <=2 && uno == 0){
                diez = 10 * diez+uno;
                tmp += UNIDADES[diez]+" ";
            }else if(diez > 2 && uno == 0){
                tmp += DECENAS[diez];
            }else{
                tmp += DECENAS[diez]+" Y "+UNIDADES[uno];
            }
        }
        return tmp+" MIL";
    }
    
    private String getCDU(String cdu){
        String tmp = "";
        int milD = Integer.parseInt(cdu);
        int cien = milD/100;
        int diez = (milD - (cien*100))/10;
        int uno = (milD - (cien*100))%10;
        if(cien == 1 && diez == 0 && uno == 0){
            tmp = "CIEN";
        }else{
            if(cien != 0){
                tmp += CENTENAS[cien]+" ";
            }
            if(diez>0 && diez <2 && uno != 0){
                diez = 10 * diez+uno;
                tmp += UNIDADES[diez]+" ";
            }else if(diez > 2 && uno == 0){
                tmp += DECENAS[diez];
            }else if(diez == 2 && uno > 0){
                if(uno == 1){
                     tmp += DECENAS[diez]+UNIDADES[0];
                }else{                    
                    tmp += DECENAS[diez]+UNIDADES[uno];
                }
            }else if(diez == 2 && uno == 0){
                tmp += "VEINTE";
            }else if(diez==0){
                tmp += UNIDADES[uno];
            }else{
                tmp += DECENAS[diez]+" Y "+UNIDADES[uno];
            }
        }
        return tmp;
    }
    
    private String getCentavos(int decD){
        String tmp = " CON ";
            int diez = (decD)/10;
            int uno = (decD)%10;
            if(diez>0 && diez <2 && uno != 0){
                diez = 10 * diez+uno;
                tmp += UNIDADES[diez];
            }else if(diez > 2 && uno == 0){
                tmp += DECENAS[diez];
            }else if(diez == 2 && uno > 0){
                if(uno == 1){
                     tmp += DECENAS[diez]+UNIDADES[0];
                }else{                    
                    tmp += DECENAS[diez]+UNIDADES[uno];
                }
            }else if(diez == 2 && uno == 0){
                tmp += "VEINTE";
            }else if(diez==0){
                tmp += UNIDADES[uno];
            }else{
                if(uno == 1){
                     tmp += DECENAS[diez]+"IUN";
                }else{                    
                    tmp += DECENAS[diez]+" Y "+UNIDADES[uno];
                }
                
            }
        return tmp +" CENTAVOS "+decD+"/100";
    }
    
    private String getEdadTextual(String edad, Date fechaNac) {   
        int comoHanPasadoLosAnios = comoHanPasadoLosAnios(fechaNac);
        int edadInt = Integer.parseInt(edad);
        edadInt = (edadInt>=comoHanPasadoLosAnios)? edadInt:comoHanPasadoLosAnios;
        if(edadInt < UNIDADES.length){
            return UNIDADES[edadInt];
        }else if(edadInt < 30){      
            edadInt = edadInt-20;
            if(edadInt==1){
                return "VEINTIUN";
            }else{
                return "VEINTI" + UNIDADES[edadInt];
            }
        }else{
            int decena = edadInt/10;
            int unidad = edadInt%10;
            String number = DECENAS[decena];
            switch (unidad) {
                case 0:
                    number +="";
                    break;
                case 1:
                    number += "IUN";
                    break;
                default:
                    number += " Y "+UNIDADES[unidad];
                    break;
            }
            return number;
        }
    }
    private int comoHanPasadoLosAnios(Date fechaDeNacimiento){
        try {            
            Calendar cal = Calendar.getInstance(); 
            int currYear = cal.get(Calendar.YEAR);
            int currMonth = cal.get(Calendar.MONTH);
            int currDay = cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(fechaDeNacimiento); 
            int years = currYear - cal.get(Calendar.YEAR);
            int bornMonth = cal.get(Calendar.MONTH);
            if (bornMonth == currMonth) { 
                return  (cal.get(Calendar.DAY_OF_MONTH) <= currDay ? years: years - 1);
            } else {
              return ( bornMonth < currMonth ? years : years - 1);
            }
        } catch (Exception e) {
            return -1;
        }
    }
    
    private String getDomicilioFiscal(List<EmpresasDomicilio> empresasDomicilioList) {
        for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {
            if(empresasDomicilio.getTipoDomicilioObj() == 1){
                return "CALLE "+empresasDomicilio.getCalle().toUpperCase()+" NÚMERO "+empresasDomicilio.getNumero().toUpperCase()+", COLONIA "+empresasDomicilio.getColonia().toUpperCase()+", "+empresasDomicilio.getCiudad().toUpperCase()+", "+empresasDomicilio.getEstado().toUpperCase()+", C.P. "+empresasDomicilio.getCodigoPostal().toUpperCase()+"";
            }//Domicilio fiscal{}
        }
        return CAMPO_NO_ENCONTRADO;
    }  
    
    private String getDomicilioNotificacion(List<EmpresasDomicilio> empresasDomicilioList) {
        for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {
            if(empresasDomicilio.getTipoDomicilioObj() == 2){
                return "CALLE "+empresasDomicilio.getCalle().toUpperCase()+" NÚMERO "+empresasDomicilio.getNumero().toUpperCase()+", COLONIA "+empresasDomicilio.getColonia().toUpperCase()+", "+empresasDomicilio.getCiudad().toUpperCase()+", "+empresasDomicilio.getEstado().toUpperCase()+", C.P. "+empresasDomicilio.getCodigoPostal().toUpperCase()+"";
            }//Domicilio notificación{}
        }
        return CAMPO_NO_ENCONTRADO;
    }
    
    private int getTotalEnDias(Date fechaInicia, String meses){
        try {
                int mesesInt = Integer.parseInt(meses);
                Calendar cal = Calendar.getInstance(); 
                 cal.setTime(fechaInicia); 
                 cal.add(Calendar.MONTH, mesesInt);
                 Date fechaConMeses = cal.getTime();
                int diasReales=(int) ((fechaConMeses.getTime()-fechaInicia.getTime())/86400000);
                return diasReales;
        } catch (NumberFormatException nfe) {
            LOGGER.error("[Service, ContratosServiceImpl] No fue posible convertir los meses del contrato del trabajador en una cantidad entera -- nfe --> "+nfe.getMessage(),nfe);
            return 0;
        }catch(Exception e){
            LOGGER.error("[Service, ContratosServiceImpl] No fue posible convertir los meses del contrato del trabajador en una cantidad entera -- e --> "+e.getMessage(),e);
            return 0;
        }
    }
    
    private String diasConTexto(int dias){
        String texto = dias+", (";
            String number = "";
            int centena = dias/100;
            int decena = (dias%100);
            int unidad = (dias%100)%10;
            if(dias<100){
               if(decena<21){
                   number += UNIDADES[decena];
               }else{
                   decena = (dias%100)/10;
                   number += DECENAS[decena];
                   switch(unidad){
                       case 0:
                        number +="";
                        break;
                    case 1:
                        number += "IUN";
                        break;
                    default:
                        number += " Y "+UNIDADES[unidad];
                        break;
                   }
               }
            }else if(dias==100){
                number += CENTENAS[0];
            }
            else{
                number += CENTENAS[centena]+" ";
                if(decena<21){
                    if(decena == 1){
                        number += UNIDADES[0];
                    }else{
                        number += UNIDADES[decena];
                    }
               }else{
                   decena = (dias%100)/10;
                   number += DECENAS[decena];
                   switch(unidad){
                       case 0:
                        number +="";
                        break;
                    case 1:
                        number += "IUN";
                        break;
                    default:
                        number += " Y "+UNIDADES[unidad];
                        break;
                   }
                }
            }
        return texto + number + ") días";
    }
    
    private final String[] DATOS_DE_CONTRATISTA = new String[]{"NOMBRE_DEL_CONTRATISTA","REPRESENTANTE_DEL_CONTRATISTA","TESTIMONIO_NOTARIAL_DEL_CONTRATISTA","VOLUMEN_NOTARIAL_DEL_CONTRATISTA","FECHA_NOTARIAL_DEL_CONTRATISTA","DOMICILIO_FISCAL_DEL_CONTRATISTA","INSTRUMENTO_NOTARIAL_DEL_CONTRATISTA","NUMERO_NOTARIA_DEL_CONTRATISTA","NOTARIO_DEL_CONTRATISTA"};
    private final String[] DATOS_DEL_COLABORADOR = new String[]{"NOMBRE_DEL_COLABORADOR","NACIONALIDAD_DEL_COLABORADOR","ESTADO_CIVIL_DEL_COLABORADOR","EDAD_DEL_COLABORADOR","CURP_DEL_COLABORADOR","RFC_DEL_COLABORADOR","FECHA_DE_NACIMIENTO_DEL_COLABORADOR","DOMICILIO_DEL_COLABORADOR","CREDITO_INFONAVIT_DEL_COLABORADOR","TIPO_NOMINA","DIAS_TIPO_NOMINA","NSS_DEL_COLABORADOR","PUESTO_DEL_COLABORADOR","SALARIO_DIARIO_DEL_COLABORADOR","TIPO_DE_CONTRATO_DEL_COLABORADOR","TIEMPO_DE_CONTRATO","FIN_DE_CONTRATO","SEXO_COLABORADOR","EMPRESA_A_TRABAJAR","TOTAL_DIAS_CONTADOS"};
    private final String[] DATOS_DEL_CONTRATISTA = new String[]{"NOMBRE_DEL_CONTRATISTA","REPRESENTATE_DEL_CONTRATISTA","TESTIMONIO_NOTARIAL_DEL_CONTRATISTA","VOLUMEN_NOTARIAL_DEL_CONTRATISTA","FECHA_NOTARIAL_DEL_CONTRATISTA","NUMERO_NOTARIA_DEL_CONTRATISTA","NOTARIO_DEL_CONTRATISTA","DOMICILIO_NOTARIO_DEL_CONTRATISTA","DOMICILIO_FISCAL_DEL_CONTRATISTA","RFC_CONTRATISTA","DOMICILIO_DE_NOTIFICACION_DEL_CONTRATISTA"};
    private final String[] DATOS_DEL_CONTRATANTE = new String[]{"NOMBRE_DEL_CONTRANTE","REPRESENTANTE_DEL_CONTRATANTE","TESTIMONIO_NOTARIAL_DEL_CONTRATANTE","VOLUMEN_DEL_CONTRATANTE","FECHA_NOTARIAL_DEL_CONTRATANTE","NUMERO_NOTARIA_DEL_CONTRATANTE","NOTARIO_DEL_CONTRATANTE","DOMICILIO_NOTARIO_DEL_CONTRATANTE","DOMICILIO_FISCAL_DEL_CONTRATANTE","RFC_CONTRATANTE","DOMICILIO_DE_NOTIFICACION_DEL_CONTRATANTE"};
    private final String[] DATOS_CONTRATO_EMPRESAS = new String[]{"FECHA_DEL_CONTRATO","PERIODO_DE_PAGO","COMISION","INFORMES","EVALUACIONES","DEPOSITO","TIEMPO_DEL_CONTRATO","NOMBRE_TESTIGO_CONTRATISTA","OCUPACION_TESTIGO_CONTRATISTA","ORIGEN_TESTIGO_CONTRATISTA","NOMBRE_TESTIGO_CONTRATANTE","OCUPACION_TESTIGO_CONTRATANTE","ORIGEN_TESTIGO_CONTRATANTE"};
    private final String[] DATOS_CONTRATO_CONTRATISTA_COLABORADOR = new String[]{"FECHA_DEL_CONTRATO","TESTIGO_UNO","TESTIGO_DOS","OCUPACION_TESTIGO_UNO","OCUPACION_TESTIGO_DOS","ORIGEN_TESTIGO_UNO","ORIGEN_TESTIGO_DOS"};
    private final String[] FIRMAS_CONTRATO_CONTRATISTA_COLABORADOR = new String[]{"EL_COLABORADOR","REPRESENTANTE_LEGAL_DEL_CONTRATISTA","TESTIGO_DOS_FIRMA","TESTIGO_UNO_FIRMA"};
    private final String[] FIRMAS_CONTRATO_ENTRE_EMPRESAS = new String[]{"EL_CONTRATISTA","NOMBRE_CONTRATISTA","EL_CONTRATANTE","NOMBRE_CONTRATANTE","FIRMA_DEL_TESTIGO_DEL_CONTRATISTA","FIRMA_DEL_TESTIGO_DEL_CONTRATANTE"};
    private final String[] UNIDADES = new String[]{"UN","UNO","DOS","TRES","CUATRO","CINCO","SEIS","SIETE","OCHO","NUEVE","DIEZ","ONCE","DOCE","TRECE","CATORCE","QUINCE","DIECISEIS","DIECISIETE","DIECIOCHO","DIECINUEVE","VEINTE"};
    private final String[] DECENAS = new String[]{ "","","VEINTI", "TREINTA", "CUARENTA","CINCUENTA", "SESENTA", "SETENTA", "OCHENTA", "NOVENTA","CIENTO" };
    private final String[] CENTENAS = new String[]{ "CIEN","CIENTO","DOSCIENTOS", "TRECIENTOS", "CUATROCIENTOS","QUINIENTOS", "SEISIENTOS", "SETECIENTOS", "OCHOCIENTOS", "NOVECIENTOS" };
    private final String[] MESES = new String[]{"","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};
    private final String FIRMA_DEL_COLABORADOR_Y_DEL_CONTRATISTA = "\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <td class=\"specialComponentPlain\"><b>\"EL COLABORADOR\"</b></td>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponentPlain\"><b>\"EL PATRON\"</b></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td class=\"specialComponent\"><b>{{EL_COLABORADOR}}</b></td>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponent\"><b>{{REPRESENTANTE_LEGAL_DEL_CONTRATISTA}}</b></td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "";
    private final String FIRMA_DE_LOS_TESTIGOS = "\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <td class=\"specialComponentPlain\">TESTIGO</td>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponentPlain\">TESTIGO</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td class=\"specialComponent\">{{TESTIGO_UNO_FIRMA}}</td>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponent\">{{TESTIGO_DOS_FIRMA}}</td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "";
    private final String FIRMA_DEL_CONTRATISTA_Y_DEL_CONTRATANTE ="\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <td class=\"specialComponentPlain\"><b>\"EL CONTRATISTA\"</b></td>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponentPlain\"><b>\"EL CONTRATANTE\"</b></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td class=\"specialComponent\"><b>{{EL_CONTRATISTA}}<br/>\"{{NOMBRE_CONTRATISTA}}\"</b></td>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponent\"><b>{{EL_CONTRATANTE}}<br/>\"{{NOMBRE_CONTRATANTE}}\"</b></td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "";
    private final String FIRMA_DEL_TESTIGO_DEL_CONTRATISTA = "\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponentPlain\">TESTIGO</td>\n" +
            "            <td></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponent\">{{FIRMA_DEL_TESTIGO_DEL_CONTRATISTA}}</td>\n" +
            "            <td></td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "";
    private final String FIRMA_DEL_TESTIGO_DEL_CONTRATANTE = "\n" +
            "    <table>\n" +
            "        <tr>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponentPlain\">TESTIGO</td>\n" +
            "            <td></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td></td>\n" +
            "            <td class=\"specialComponent\">{{FIRMA_DEL_TESTIGO_DEL_CONTRATANTE}}</td>\n" +
            "            <td></td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "";
    private final String CAMPO_NO_ENCONTRADO = " =%CAMPO NO ENCONTRADO%= ";

    private String getTiempoExactlyP(String tiempoContrato) {
        String resulStrings[] = tiempoContrato.split(",");
        String result[] = tiempoContrato.split(",");
        for (String resulString : resulStrings) {
            resulString = resulString.replace("año", " ").replace("años", " ").replace("mes", " ").replace("meses", " ").replace("días", " ").replace("día", " ").replace("y", " ").trim();
        }
        boolean hasYear, hasMonth, hasDay;
        hasDay = hasMonth = hasYear = false;
        if(result.length>0&&!result[0].equalsIgnoreCase("0")){
            hasYear = true;
        }
        if(result.length>1&&!result[1].equalsIgnoreCase("0")){
            hasMonth = true;
        }
        if(result.length>2&&!result[2].equalsIgnoreCase("0")){
            hasDay = true;
        }
        if(hasDay&&!hasYear&&!hasMonth){
            return result[2].replace("Y", " ").trim();
        }
        String response ="";
        if(hasYear){
            response = result[0] ;
        }
        if(hasYear&&hasMonth){
            response += ", ";
        }
        if(hasMonth){
            response = result[1];
        }
        if((hasYear||hasMonth)&&hasDay){
            response += ", ";
        }
        if(hasDay){
            response += result[2];
        }
        return response;
    }
    /**
     * Metodo encargado de devolver el tiempo exacto de una cadena de texto
     * @param tiempoContrato
     * @return String
     */
   private String getTiempoExactly(String tiempoContrato) {
       
       // se toma el parametro y si le aplica el split por su , y un espacio
        String result[] = tiempoContrato.split(", ");
        
        String response =""; // string vacio
        if(!"0 años".equals(result[0])){ //valida su el valor de result en su posición 0 es diferente de 0 años
            response = result[0]; //se le asigna el valor a response
        }
        if(!"0 meses".equals(result[1])) //valida su el valor de result en su posición 1 es diferente de 0 meses
        {
            if("".equals(response)){ //valida si response es igual a vacio
                response = result[1]; //entonces se asigna soló el valor result en su posición 1
            }else{ // si no es igual a vacio
                response += ", " + result[1]; //entonces a lo que traiga response agregale el string ", " mas result en su posición 1
            }
        }
        
        if(!"y 0 días".equals(result[2])) //valida su el valor de result en su posición 2 es diferente de "y 0 días"
        {
            if("".equals(response)){ //valida si response es igual a vacio
                response = result[2].replace("y ", ""); // se manda el valor de result en su posición 2 y se reemplaza y por un blanco
            }else{ // si no es igual a vacio
                response += ", " + result[2]; // entonces a lo que traiga response agregale el  string ", " mas result en su posición 2
            }
        }
        return response+","; // devuelve el valor de response final + el string ","
    }
    private String getBetweenDay(DatosLaborales datosLaborales) {
                return ""+(int)( (datosLaborales.getFechaFinContrato().getTime() - datosLaborales.getFechaInicioContrato().getTime()) / (1000 * 60 * 60 * 24))+" días";
    }  
    private String getBetweenDay(SolicitudRenovacionContrato renovacionContrato) {
                return ""+(int)( (renovacionContrato.getFechaFinal().getTime() - renovacionContrato.getFechaInicial().getTime()) / (1000 * 60 * 60 * 24))+" días";
    }  
   private String procesayyyyMMdd(Date date){
       if(date != null){
            String yyyyMMdd = "yyyy-MM-dd";
            DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
           return formatter.format(date);
       }
       return "";
   }  

}