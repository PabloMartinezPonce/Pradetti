/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.controller;

import com.pradettisanti.payroll.pojoh.ActaNotarial;
import com.pradettisanti.payroll.pojoh.Bitacora;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.Notificaciones;
import com.pradettisanti.payroll.pojoh.PoderLegal;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.pojoh.TipoActa;
import com.pradettisanti.payroll.pojoh.TipoIncidencia;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.pojoh.ZonaSm;
import com.pradettisanti.payroll.service.ActaNotarialService;
import com.pradettisanti.payroll.service.BitacoraService;
import com.pradettisanti.payroll.service.ClienteService;
import com.pradettisanti.payroll.service.ContratistaService;
import com.pradettisanti.payroll.service.ContratoEmpresasService;
import com.pradettisanti.payroll.service.ExcelService;
import com.pradettisanti.payroll.service.FtpService;
import com.pradettisanti.payroll.service.IncidenciaService;
import com.pradettisanti.payroll.service.NotificacionesService;
import com.pradettisanti.payroll.service.PDFiTextService;
import com.pradettisanti.payroll.service.ReciboService;
import com.pradettisanti.payroll.service.SindicatoService;
import com.pradettisanti.payroll.service.UsuarioService;
import com.pradettisanti.payroll.service.ZonasSmService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Manager encargador de contener los controlles de la vista del modulo de clientes
 * @author PabloSagoz  pablo.samperio@it-seekers.com 
 */
@SuppressWarnings("Convert2Diamond")
public class ClienteController {

    /**
     * Constante para el uso de los logs del sistema
     */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ClienteController.class);    
    
    @Autowired
    private ClienteService clienteService;
    public ClienteService getClienteService() {
        return clienteService;
    }
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @Autowired
    private ZonasSmService zonasSmService;
    public ZonasSmService getZonasSmService() {
        return zonasSmService;
    }
    public void setZonasSmService(ZonasSmService zonasSmService) {
        this.zonasSmService = zonasSmService;
    }
    
    @Autowired
    private ActaNotarialService actaNotarialService;
    public ActaNotarialService getActaNotarialService() {
        return actaNotarialService;
    }
    public void setActaNotarialService(ActaNotarialService actaNotarialService) {
        this.actaNotarialService = actaNotarialService;
    }
    
    @Autowired
    private ContratoEmpresasService contratoEmpresasService;    
    public ContratoEmpresasService getContratoEmpresasService() {
        return contratoEmpresasService;
    }
    public void setContratoEmpresasService(ContratoEmpresasService contratoEmpresasService) {
        this.contratoEmpresasService = contratoEmpresasService;
    }
    
    @Autowired
    private UsuarioService usuarioService;
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
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
    private IncidenciaService incidenciaService;
    public IncidenciaService getIncidenciaService(){
        return incidenciaService;
    }
    public void setIncidenciaService(IncidenciaService incidenciaService){
        this.incidenciaService = incidenciaService;
    }
    
    @Autowired
    private FtpService ftpService;
    public FtpService getFtpService(){
        return ftpService;
    }
    public void setFtpService(FtpService ftpService){
        this.ftpService = ftpService;
    }
    
    @Autowired 
    private ExcelService excelService;
    public ExcelService getExcelService(ExcelService excelService){
        return excelService;
    }
    public void setExcelService(ExcelService excelService){
        this.excelService = excelService;
    }
    
    @Autowired
    private ReciboService reciboService;
    public ReciboService getReciboService(){
        return reciboService;
    }
    public void setReciboService( ReciboService reciboService ){
        this.reciboService = reciboService;
    }
    
    @Autowired
    private BitacoraService bitacoraService;
    private BitacoraService getBitacoraService(){
        return bitacoraService;
    }
    private void setBitacoraService(BitacoraService bitacoraService){
        this.bitacoraService = bitacoraService;
    }
    
    @Autowired
    private PDFiTextService pDFiTextService;
    public PDFiTextService getPDFiTextService(){
        return pDFiTextService;
    }
    public void setPDFiTextService(PDFiTextService pDFiTextService){
        this.pDFiTextService = pDFiTextService;
    }
    
    @Autowired
    private NotificacionesService notificacionesService;
    public NotificacionesService getNotificacionesService(){
        return notificacionesService;
    }
    public void setNotificacionesService(NotificacionesService notificacionesService){
        this.notificacionesService = notificacionesService;
    }
    
    @Autowired
    private SindicatoService sindicatoService;
    public SindicatoService getSindicatoService(){
        return sindicatoService;
    }
    public void setSindicatoService(SindicatoService sindicatoService){
        this.sindicatoService = sindicatoService;
    }
    
    /**
     * Controller encargado de devolver la vista del modulo cliente
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/clientes.htm", method = RequestMethod.GET)
    public ModelAndView getModuloClientes(){
            LOGGER.info("[Controller,Cliente] Se solicita la vista del módulo de clientes");
            
           Map<String,Object> map = new HashMap<String, Object>();
            try {
                getAllClientes(map);
            } catch (Exception e) {
                LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de crear la vista con los clientes  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de crear la vista con los clientes");
                map.put("muestraTabla", false);          
            }
            return new ModelAndView("cliente/clientes", "model",map);
    }
   
    /**
     * Controller encargado de devolver la vista para registrar un nuevo cliente
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/registro-cliente.htm",method = RequestMethod.GET)
    public ModelAndView getNuevoCliente(){
            LOGGER.info("[Controller,Cliente] Se solicita la vista para agregar un nuevo cliente");
            
            Map<String,Object> map = new HashMap<String, Object>();
            try {
                map.put("edicion", false);
                List<ZonaSm> zonaSm = zonasSmService.getZonaSm();
                map.put("zonasSalariales", zonaSm);
                List<TipoActa> tipoActa = actaNotarialService.getTipoActa();            
                map.put("tiposDeActa", tipoActa);
                return new ModelAndView("cliente/registroCliente","model", map);             
            } catch (Exception e) {            
            LOGGER.error("[Controller,Clientes] Ocurrio un error al momento de crear la vista para registrar un nuevo cliente  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de crear la vista para el registro de un cliente");    
        }
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de devolver la vista de todos los clientes registrados dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/todos-los-clientes.htm", method = RequestMethod.GET)
    public ModelAndView getTodosLosClientes(){        
            LOGGER.info("[Controller,Cliente] Se solicita la vista de todos los clientes");
            
           Map<String,Object> map = new HashMap<String, Object>();
            try{
                    getAllClientes(map);
            }catch(Exception e){
                        LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de crear la vista con todos los clientes  -> "+e);
                        setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de crear la vista con todos los clientes");   
            }
            return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de devolver una vista con los clientes que empatan con los parametros enviados
     * @param criterio Valor del criterio por que cual se desea realizar la busqueda
     * @param valor Valor del campo de texto que se ingresa para realizar la busqueda
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/buscar-por/{param}/dato/{value}.htm",method = RequestMethod.GET)
    public ModelAndView getClientesPorFiltro(@PathVariable ("param") String criterio, @PathVariable("value") String valor){
        LOGGER.info("[Controller,Cliente] Se solicita la vista de  los clientes que cumplan con cierto criterio de búsqueda\nCriterio: "+criterio+", Palabra(s): "+valor);
            
           Map<String,Object> map = new HashMap<String, Object>();
            try{
                       String crtr;
                        Cliente clienteABuscar = new Cliente();
                        switch(criterio){
                            case "Nombre":
                                clienteABuscar.setNombreEmpresa(valor.trim());
                                crtr = "Nombre";
                            break;
                            case "Represetante":
                                clienteABuscar.setRepresentanteLegal(valor.trim());
                                crtr = "Represetante";
                            break;
                            case  "RFC":
                                clienteABuscar.setRfc(valor.trim());
                                crtr = "RFC";
                            break;
                            default:
                                 LOGGER.error("[Controller,Cliente] Cirterio de la búsqueda incorrecto");
                                setInformacionEnVentana(map,1, "Módulo de clientes", "El criterio de la búsqueda es incorrecto");
                                getAllClientes(map);
                                return new ModelAndView("cliente/clientes", "model", map);
                        }
                        List<Cliente> clientes = clienteService.getCliente(clienteABuscar);
                    if(clientes.isEmpty()){
                        setInformacionEnVentana(map,2, "Módulo de clientes", "Ningún cliente cumple con el criterio de búsqueda<br/><br/><b>"+crtr+"</b>: <b>"+valor+"</b>");
                        map.put("muestraTrabla", true);                   
                    }else{
                        map.put("clientesEncontrados",clientes);
                        map.put("muestraTrabla", true);                            
                    }
            }catch(Exception e){
                LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de crear la vista con todos los clientes que cumplan con el criterio de búsqueda -> "+e);
                setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de crear la vista con todos los clientes que cumplan con el criterio de la búsqueda");
                getAllClientes(map);
            }
            return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de devolver la vista para la edición de un cliente
     * @param idCliente Numero entero que representa el Id del cliente a editar
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/editar-cliente/{idCliente}.htm",method = RequestMethod.GET)
    public ModelAndView getEditarCliente(@PathVariable("idCliente") int idCliente){
           LOGGER.info("[Controller,Cliente] Se solicita la vista para la edición de un cliente ["+idCliente+"]");
        
           Map<String,Object> map = new HashMap<String, Object>();          
           try{ 
                    map = getInformacionCliente(idCliente);
                    map.put("edicion", true);                       
                    return new ModelAndView("cliente/registroCliente","model", map);
            }catch(Exception e){
                LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de crear la vista para la edición de un cliente  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de crear la vista para la edición de un cliente");
                getAllClientes(map);
            }
           return new ModelAndView("cliente/clientes","model", map);
    }
    
    /**
     * Controller encargado de ingresar o actualizar los datos de un cliente
     * @param cliente Instancia de Cliente
     * @param request Variable de manejo de peticiones 
     * @param response Variable de manejo de respuestas 
     * @return  ModelAndView
     */
    @RequestMapping(value = "cliente/datos-cliente.htm",method = RequestMethod.POST)
    public ModelAndView setDatosDelCliente(@ModelAttribute("cliente") Cliente cliente, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se reciben los pricipales datos de un cliente "+cliente.getNombreEmpresa());
        
        Map<String,Object> map = new HashMap<String, Object>();
        try {            
            
                        String fechaString = request.getParameter("fchRegistroPublico");
                        String statusString = request.getParameter("statusCliente");

                         Date fechaRegistroPublico = procesayyyyMMdd(fechaString);

                         boolean status;
                         if(statusString != null){               
                              status = getEstado(statusString);
                         }else{
                             status = false;
                         }

                         cliente.setFechaRegistroPublico(fechaRegistroPublico);
                         cliente.setStatus(status);
                         String moviemiento;
                         Cliente clienteTemp;
                         if(cliente.getIdCliente()!= null){
                             moviemiento = "Actualización";
                              clienteTemp = clienteService.getCliente(cliente.getIdCliente());
                         }else{
                             moviemiento = "Inserción";
                             clienteTemp = new Cliente();
                             clienteTemp.setCreado(new Date()); 
                             // Se obtiene el correo de la sesión iniciada
                            String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                            Usuario usuario = usuarioService.getUsuario(correo);
                            // Se agrega el usuario a la tabla usuario_cliente
                            List<Usuario> usuarioList = new LinkedList<Usuario>();
                            usuarioList.add(usuario);
                            clienteTemp.setUsuarioList(usuarioList);
                         }

                         clienteTemp.setZonaSm(cliente.getZonaSm());
                         clienteTemp.setNombreEmpresa(cliente.getNombreEmpresa());
                         clienteTemp.setRepresentanteLegal(cliente.getRepresentanteLegal());
                         clienteTemp.setRfc(cliente.getRfc());
                         clienteTemp.setTipoSociedad(cliente.getTipoSociedad());
                         clienteTemp.setGiro(cliente.getGiro());
                         clienteTemp.setFinSocial(cliente.getFinSocial());
                         clienteTemp.setFechaRegistroPublico(cliente.getFechaRegistroPublico());
                         clienteTemp.setStatus(cliente.getStatus());
                         clienteTemp.setModificado( new Date() );
                          
                         Integer setCliente = clienteService.setCliente(clienteTemp);        
                         map = getInformacionCliente(setCliente);
                         map.put("edicion", true);
           
                        LOGGER.info("[Controller,Cliente] Se ingresaron correctamente los datos del cliente "+cliente.getNombreEmpresa());
                        setInformacionEnVentana(map,0, "Datos del cliente", "Los principales datos del cliente fueron ingresados correctamente");
                        setBitacora(moviemiento, "Ingreso correctamente los datos del cliente "+cliente.getNombreEmpresa().toUpperCase()+".", request);
           
        } catch (ParseException ex) {
                Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "[Controller,Cliente] Error al procesar una fecha de texto a Date ");
                setInformacionEnVentana(map,1, "Módulo de cliente", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de procesar alguna de las fechas ingresadas");  
        }catch(Exception e){
                LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de ingresar los datos de un cliente  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar los datos del cliente");  
        }        
        return new ModelAndView("cliente/registroCliente","model", map);
    }
    
    /**
     * Controller encargado de  ingresar o actualizar los datos del instrumento notatial de un cliente
     * @param actaNotarialPDF Archivo del acta notarial
     * @param poderLegalPDF Archivo del poder legal
     * @param actaNotarial Intancia de Acta notarial
     * @param request Variable de manejo de peticiones
     * @param response Varibale de manejo de respuestas
     * @return  ModelAndView
     */
    @RequestMapping(value = "cliente/instrumento-notarial.htm",method = RequestMethod.POST)
    public ModelAndView setInstrumentoNotarial(@RequestParam("actaNotarialPDF") MultipartFile actaNotarialPDF, @RequestParam("poderLegalPDF") MultipartFile poderLegalPDF,@ModelAttribute("actanotarial") ActaNotarial actaNotarial, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se reciben los datos del instrumento notarial de un cliente Instrumento: "+actaNotarial.getInstrumento()+", Volumen: "+actaNotarial.getVolumen()+", Notario: "+actaNotarial.getNotario());
        
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            
                        String fechaString = request.getParameter("fchVolumen");
                        Integer clienteInt = Integer.parseInt( request.getParameter("clienteID") );
                        String poderLegalString = request.getParameter("tnPoderLegal");

                        Date fechaVolumen = procesayyyyMMdd(fechaString);
                        boolean tienePoderLegal = false;

                        if(poderLegalString != null){               
                             tienePoderLegal = getEstado(poderLegalString);
                        }           

                        Cliente cliente = clienteService.getCliente(clienteInt);

                        actaNotarial.setFechaVolumen(fechaVolumen);
                        actaNotarial.setTienePoderLegal(tienePoderLegal);


                        Integer idActaNotarial = actaNotarialService.setActaNotarial(actaNotarial);

                        List<ActaNotarial> actaNotarialList = new LinkedList<>();
                        actaNotarialList.add(actaNotarial);
                        cliente.setActaNotarialList(actaNotarialList);
                        cliente.setModificado(new Date() );            
                        clienteService.setCliente(cliente);
                        if( ! actaNotarialPDF.getOriginalFilename().isEmpty() ){
                            cargarDocumento(cliente, "ActaNotarial",actaNotarial ,actaNotarialPDF);
                        }

                        if( tienePoderLegal ){

                               String representanteString = request.getParameter("rpsntntPL");
                               String rfcString = request.getParameter("rfcPL");
                               String instrumentoString = request.getParameter("instrumentoPL");
                               String volumenString = request.getParameter("volumenPL");
                               String fechaVolumenString = request.getParameter("fechaVolumenPL");
                               String numeroNotariaString = request.getParameter("numeroNotarialPL");
                               String notarioString = request.getParameter("notarioPL");
                               String direccionNotarioString = request.getParameter("direccionNotarioPL");
                               String urlDocumentoPLString = request.getParameter("urlDocumentoPL");
                               

                              Date fechaVolumenPL = procesayyyyMMdd(fechaVolumenString);

                                PoderLegal poderLegalTemp = new PoderLegal(idActaNotarial);

                                poderLegalTemp.setRepresentanteLegal(representanteString);
                                poderLegalTemp.setRfc(rfcString);
                                poderLegalTemp.setInstrumento(instrumentoString);
                                poderLegalTemp.setVolumen(volumenString);
                                poderLegalTemp.setFechaVolumen(fechaVolumenPL);
                                poderLegalTemp.setNumeroNotarial(numeroNotariaString);
                                poderLegalTemp.setNotario(notarioString);
                                poderLegalTemp.setDireccionNotario(direccionNotarioString);
                                poderLegalTemp.setUrlDocumento(urlDocumentoPLString);
                                
                                poderLegalTemp.setActaNotarialObj(actaNotarial);

                                actaNotarialService.setActaNotarial(poderLegalTemp);

                                 actaNotarial.setPoderLegal(poderLegalTemp);

                                idActaNotarial = actaNotarialService.setActaNotarial(actaNotarial);
                                
                                if( ! poderLegalPDF.getOriginalFilename().isEmpty() ){
                                    cargarDocumento(cliente, "PoderLegal",poderLegalTemp ,poderLegalPDF);
                                }

                        }else{
                                 actaNotarial.setPoderLegal(null);
                                 actaNotarial.setTienePoderLegal(false);
                                idActaNotarial = actaNotarialService.setActaNotarial(actaNotarial);
                        }
                         if(idActaNotarial  != null){
                            LOGGER.info("[Controller,Cliente] Se ingresaron correctamente los datos del  instrumento notarial del cliente ["+cliente.getIdCliente()+", "+cliente.getNombreEmpresa()+", "+actaNotarial.getIdActaNotarial()+"]");
                            map = getInformacionCliente(clienteInt);
                            map.put("edicion", true);
                            setInformacionEnVentana(map,0, "Datos del instrumento notarial ", "Los principales datos del instrumento notarial del cliente fueron ingresados correctamente"); 
                             setBitacora("Actualización", "Ingreso correctamente los datos notariales del cliente "+cliente.getNombreEmpresa().toUpperCase()+" acta notarial id "+actaNotarial.getIdActaNotarial()+".", request);
                         }
           } catch (ParseException ex) {
                       Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "[Controller,Cliente] Error al procesar una fecha de texto a Date ");
                       setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de parsear los datos del instrumento notarial del cliente"); 
            }catch(NumberFormatException nfe){
                      LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de ingresar los datos del instrumento notarial del cliente - Error en el parseo de una fecha   --> "+nfe);
                       setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de formatear los datos del instrumento notarial del cliente");    
            }catch(Exception e){
                      LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de ingresar los datos del instrumento notarial del cliente  -> "+e);
                       setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar los datos del instrumento notarial del cliente");    
            }
            return new ModelAndView("cliente/registroCliente","model", map);
    }
    
    /**
     * Controller encargado de ingresar o actualizar el domiclio fiscal de un cliente
     * @param ed Instancia de EmpresasDomicilio
     * @param request Variable de manejo de peticiones
     * @param response Variable de manejo de respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/domicilio-fiscal.htm",method = RequestMethod.POST)
    public ModelAndView setDomicilioFiscal(@ModelAttribute("empresasdomicilio") EmpresasDomicilio ed, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se reciben los datos del domicilio fiscal de un cliente ");
         
        Map<String,Object> map = new HashMap<String, Object>();
         try{
             
                    Integer clienteInt = Integer.parseInt( request.getParameter("idCliente") );
                    Cliente cliente = clienteService.getCliente(clienteInt);
                    String movimiento  ="";
                    Integer setDomicilioFiscal = 0;
                    List<EmpresasDomicilio> empresasDomicilioList = cliente.getEmpresasDomicilioList();
                      if(!empresasDomicilioList.isEmpty()){
                          for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {                   
                              if(empresasDomicilio.getTipoDomicilioObj() == 1) {// Domicilio Fiscal
                                  ed.setIdEmpresasDomicilio(empresasDomicilio.getIdEmpresasDomicilio());
                                  ed.setTipoDomicilioObj(empresasDomicilio.getTipoDomicilioObj());
                                  setDomicilioFiscal = clienteService.setDomicilio(ed);
                                  movimiento = "Actualización";
                                  LOGGER.info("[Controller,Cliente] edición del domicilio fiscal del cliente "+cliente.getNombreEmpresa());                              
                              }
                          }
                    }

                   if(setDomicilioFiscal == 0){
                        ed.setTipoDomicilioObj((short) 1);// Domicilio Fiscal
                        List<Cliente> cs = new LinkedList<>();
                        cs.add(cliente);
                        ed.setClienteList(cs);
                        clienteService.setDomicilio(ed);
                        empresasDomicilioList.add(ed);
                        cliente.setEmpresasDomicilioList(empresasDomicilioList);
                                  movimiento = "Inserción";
                        LOGGER.info("[Controller,Cliente] nuevo domicilio fiscal  del cliente "+cliente.getNombreEmpresa());     
                   } 
                   
                    cliente.setModificado(new Date() );       
                    Integer setCliente = clienteService.setCliente(cliente);
                    
                    if(setCliente != null){
                            LOGGER.info("[Controller,Cliente] Se ingresaron correctamente los datos del  domicilio fiscal del cliente ["+cliente.getIdCliente()+", "+cliente.getNombreEmpresa()+",  -> "+ed.getIdEmpresasDomicilio()+"]");
                            map = getInformacionCliente(setCliente);
                            map.put("edicion", true);
                            setInformacionEnVentana(map,0, "Datos del domicilio fiscal", "Los principales datos del domicilio fiscal del cliente fueron ingresados correctamente");
                            setBitacora(movimiento, "Ingresó correctamente el domicilio fiscal del cliente "+cliente.getNombreEmpresa().toUpperCase()+", domicilio fiscal id "+ed.getIdEmpresasDomicilio()+".", request);
                    }
           }catch(NumberFormatException nfe){
                    LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de ingresar los datos del domicilio fiscal  del cliente - Error en el parseo de una fecha --> "+nfe);
                    setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de formatear los datos del domicilio fiscal  del cliente");    
            }catch(Exception e){       
                  LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de ingresar los datos del domicilio fiscal del cliente  -> "+e);
                  setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar los datos del domicilio fiscal del cliente");    
              }
            return new ModelAndView("cliente/registroCliente","model", map);
           
    }
    
    /**
     * Controller encargado de ingresar o actualizar el domicilio de notificación de un cliente
     * @param ed Instancia de EmpresasDomicilo
     * @param request Variable de manejo de peticiones
     * @param response Variable de manejo de respuestas
     * @return ModelAndView 
     */
    @RequestMapping(value = "cliente/domicilio-de-notificacion.htm",method = RequestMethod.POST)
    public ModelAndView setDomicilioNotificacion(@ModelAttribute("empresasdomicilio") EmpresasDomicilio ed, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se reciben los datos del domicilio de notificacion de un cliente");
        
        Map<String,Object> map = new HashMap<String, Object>();
         try{
             
                        Integer clienteInt = Integer.parseInt( request.getParameter("idCliente") );
                         Cliente cliente = clienteService.getCliente(clienteInt);
                         String movimiento = "";
                           Integer setDomicilioFiscal = 0;
                           List<EmpresasDomicilio> empresasDomicilioList = cliente.getEmpresasDomicilioList();
                             if(empresasDomicilioList.size()>0){
                                 for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {                   
                                     if(empresasDomicilio.getTipoDomicilioObj() == 2) {// Domicilio de notificacion
                                         ed.setIdEmpresasDomicilio(empresasDomicilio.getIdEmpresasDomicilio());
                                         ed.setTipoDomicilioObj(empresasDomicilio.getTipoDomicilioObj());
                                         setDomicilioFiscal = clienteService.setDomicilio(ed);
                                         movimiento = "Actualización";
                                         LOGGER.info("[Controller,Cliente] edición del domicilio de notificacion del cliente "+cliente.getNombreEmpresa());                              
                                     }
                                 }
                           }

                           if(setDomicilioFiscal == 0){
                                ed.setTipoDomicilioObj((short) 2); // Domicilio de notificacion
                                List<Cliente> cs = new LinkedList<>();
                                cs.add(cliente);
                                ed.setClienteList(cs);
                                clienteService.setDomicilio(ed);
                                empresasDomicilioList.add(ed);
                                cliente.setEmpresasDomicilioList(empresasDomicilioList);
                                movimiento = "Inserción";
                                LOGGER.info("[Controller,Cliente] nuevo domicilio de notificacion del cliente"+cliente.getNombreEmpresa());     
                           }
                           
                            cliente.setCreado( new Date() );        
                            Integer setCliente = clienteService.setCliente(cliente);
                            if(setCliente!=null){
                                LOGGER.info("[Controller,Cliente] Se ingresaron correctamente los datos del  domicilio de notificación del cliente  ["+cliente.getIdCliente()+", "+cliente.getNombreEmpresa()+",  -> "+ed.getIdEmpresasDomicilio()+"]");
                                map = getInformacionCliente(setCliente);
                                map.put("edicion", true);  
                                setInformacionEnVentana(map,0, "Datos del domicilio de notificación", "Los principales datos del domicilio de notificación del cliente fueron ingresados correctamente");          
                                setBitacora(movimiento, "Ingresó correctamente el domicilio de notificación del cliente "+cliente.getNombreEmpresa().toUpperCase()+", domicilio notificación id "+ed.getIdEmpresasDomicilio()+".", request);
                }
            }catch(NumberFormatException nfe){
                  LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de ingresar los datos del domicilio de notificación del cliente - Error en el parseo de una fecha --> "+nfe);
                  setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de formatear los datos del domicilio de notificación del cliente");    
            }catch(Exception e){       
                  LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de ingresar los datos del domicilio de notificación del cliente  -> "+e);
                  setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar los datos del domicilio de notificación del cliente");    
              }
            return new ModelAndView("cliente/registroCliente","model", map);     
    }
    
    /**
     * Controller encargado de deolver la vista del kardex de un cliente
     * @param idCliente Numero entero que almacena el id de un cliente
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/{idCliente}/kardex.htm", method = RequestMethod.GET)
    public ModelAndView getKardexCliente(@PathVariable("idCliente") Integer idCliente){
        LOGGER.info("[Controller,Cliente] Se solicita la vista del kardex de un cliente");     
        
        Map<String,Object> map = new HashMap<String, Object>();
        try{            
                map = getInformacionCompletaDelCliente(idCliente);
                return new ModelAndView("cliente/kardexDelCliente","model",map);
        }catch(IndexOutOfBoundsException ioobe){
            LOGGER.error("[Controller,Cliente] Algunas de las consultas no devolvieron la información que se requiere para poder generar el kardex de manera adecuada --> "+ioobe);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de generar el kardex del cliente");
            getAllClientes(map);
        }catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de generar el kardex del cliente  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de generar el kardex del cliente");
            getAllClientes(map);
        }        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de devolver el kardex en pdf del cliente
     * @param idCliente Numero entero que almacena el id de un cliente
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     */
    @RequestMapping(value = "cliente/{idCliente}/kardex/pdf.htm", method = RequestMethod.GET)
    public void getPDFDelKardex(@PathVariable("idCliente") Integer idCliente,HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se solicita la vista del kardex de un cliente en pdf.");     
        try{            
            Cliente cliente = clienteService.getCliente(idCliente);
            if(cliente != null){
                ByteArrayOutputStream kardex = pDFiTextService.getKardex(cliente);// setting some response headers
                response.setHeader("Content-Disposition", "inline;filename=\"kardex del cliente "+cliente.getNombreEmpresa().toUpperCase()+".pdf\"");
                // setting the content type
                response.setContentType("application/pdf");
                // the contentlength
                response.setContentLength(kardex.size());
                try ( // write ByteArrayOutputStream to the ServletOutputStream
                    OutputStream os = response.getOutputStream()) { 
                    kardex.writeTo(os);
                    os.flush();
                }
            }else{
                throw new Exception("Cliente no encontrado para la generación de la vista en pdf");
            }
        }catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de generar el kardex en pdf del cliente  -> "+e);
        }        
    }
    
    /**
     * Controller encargado de registrar un nuevo usuario del cliente
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable ecargada de manejar las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/registrar-usuario.htm",method = RequestMethod.POST)
    public ModelAndView setNuevoUsuario(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se solicita el registro de un nuevo usuario de un cliente");
            
           Map<String,Object> map = new HashMap<String, Object>();
            try {
                
                    String nombre = request.getParameter("nmbrCmplt");
                    String correo = request.getParameter("crrLctrnc");
                    Integer rol = Integer.parseInt(request.getParameter("rls"));
                    Integer clienteInt = Integer.parseInt(request.getParameter("clnt"));
                    Usuario usuarioBDD = usuarioService.getUsuario(correo);
                    if(usuarioBDD !=null ){
                         Cliente cliente = clienteService.getCliente(clienteInt);
                        List<Cliente> tmp =  usuarioBDD.getClienteList();
                        tmp.add(cliente);
                        usuarioBDD.setClienteList(tmp);
                        usuarioBDD.setModificado( new java.util.Date()  );
                        
                        usuarioService.setUsuario(usuarioBDD);
                        cliente.getUsuarioList().add(usuarioBDD);
                        cliente.setModificado(new Date() );
                        clienteService.setCliente(cliente);
                        
                        LOGGER.info("[Controller,Cliente] Se registró el usuario "+usuarioBDD.getCorreoElectronico()+" para el cliente "+cliente.getNombreEmpresa()+"");
                        map = getInformacionCompletaDelCliente(clienteInt);
                        setInformacionEnVentana(map,0, "Registro de un usuario", "Se Registro el usuario exitente <b>"+usuarioBDD.getCorreoElectronico()+"</b> correctamente.");    
                        setBitacora("Inserción", "Registró al usuario exitente "+usuarioBDD.getCorreoElectronico().toUpperCase()+", el cliente "+cliente.getNombreEmpresa().toUpperCase()+" correctamente.", request);
                        return new ModelAndView("cliente/kardexDelCliente","model",map);
                    }else{
                        Usuario usuario = new Usuario();
                        usuario.setNombre(nombre);
                        usuario.setCorreoElectronico(correo);
                        usuario.setRol(rol);
                        Cliente cliente = clienteService.getCliente(clienteInt);
                        List<Cliente> tmp =  new LinkedList<>();
                        tmp.add(cliente);
                        usuario.setClienteList(tmp);
                        usuario.setCreado( new java.util.Date() );
                        usuario.setModificado( new java.util.Date()  );
                        List<Notificaciones> notificaciones = usuarioService.getNotificaciones();
                        usuario.setNotificacionesList(notificaciones);
                        usuario.setContrasena( getAcceso() );
                        usuario.setStatus(true);
                        
                        usuarioService.setUsuario(usuario);

                        cliente.getUsuarioList().add(usuario);
                        cliente.setModificado(new Date() );
                        clienteService.setCliente(cliente);

                        notificaciones.stream().forEach((n) -> {
                            n.getUsuarioList().add(usuario);
                        });   
                        
                        usuarioService.setNotificaciones(notificaciones);
                        LOGGER.info("[Controller,Cliente] Se ingresó un nuevo usuario para el cliente  ["+cliente.getIdCliente()+", "+cliente.getNombreEmpresa()+", "+usuario.getIdUsuario()+", "+usuario.getNombre()+"]");
                        map = getInformacionCompletaDelCliente(clienteInt);
                        setInformacionEnVentana(map,0, "Ingreso de un nuevo usuario", "Se ingresó correctamente el usuario<br>Nombre: <b>"+usuario.getNombre()+"</b><br>Correo: <b>"+usuario.getCorreoElectronico()+"</b>");    
                        setBitacora("Inserción", "Ingresó el usuario "+usuario.getCorreoElectronico().toLowerCase()+", al cliente "+cliente.getNombreEmpresa().toUpperCase()+".", request);
                        notificacionesService.nuevoUsuario(usuario);
                        return new ModelAndView("cliente/kardexDelCliente","model",map);
                    }
                        
        }catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de ingresar un usuario en el kardex del cliente  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar un usuario del cliente");
            getAllClientes(map);
        }        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de actualizar el usuario de un cliente dentro del sistema
     * @param request Varibale encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/editar-usuario.htm",method = RequestMethod.POST)
    public ModelAndView setUsuario(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se solicita la edición del usuario de un cliente");
        
        Map<String,Object> map = new HashMap<String, Object>(); 
        try {
            
                        Integer idUsuario = Integer.parseInt(request.getParameter("vlr"));
                        String nombre = request.getParameter("nmbrCmplt");
                        String correo = request.getParameter("crrLctrnc");
                        Integer rol = Integer.parseInt(request.getParameter("rls"));
                        Integer clienteInt = Integer.parseInt(request.getParameter("clnt"));

                        Usuario usuario = usuarioService.getUsuario( new Usuario(idUsuario) );
                        usuario.setNombre(nombre);
                        usuario.setCorreoElectronico(correo);
                        usuario.setRol(rol);
                        usuario.setModificado( new java.util.Date()  );
                        usuario.setStatus(true);
                        
                        usuarioService.setUsuario(usuario);                    
                        LOGGER.info("[Controller,Cliente] Se editó el  usuario de un cliente ["+usuario.getIdUsuario()+", "+usuario.getNombre()+"]");
                        map = getInformacionCompletaDelCliente(clienteInt);
                        setInformacionEnVentana(map,0, "Edición de un  usuario", "Se editó correctamente el usuario<br>Nombre: <b>"+usuario.getNombre()+"</b><br>Correo: <b>"+usuario.getCorreoElectronico()+"</b>");    
                        setBitacora("Actualización", "Actualizó correctamente la información del usuario "+usuario.getCorreoElectronico().toUpperCase()+".", request);
                        return new ModelAndView("cliente/kardexDelCliente","model",map);
                        
        }catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de editar un usuario en el kardex del cliente  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de editar el usuario del cliente");
            getAllClientes(map);
        }        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de actualizar la contraseña de un usuario
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     * @return  ModelAndView
     */
    @RequestMapping(value="cliente/acceso-usuario.htm",method = RequestMethod.POST)
    public ModelAndView setNuevoAcceso(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se solicita el cambio de contraseña del usuario de un cliente");
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            
                    Integer idUsuario = Integer.parseInt(request.getParameter("vlr"));       
                    Integer clienteInt = Integer.parseInt(request.getParameter("clnt"));

                    Usuario usuario = usuarioService.getUsuario( new Usuario(idUsuario) );
                    usuario.setContrasena(getNuevoAcceso());
                    usuario.setStatus(true);
                    usuario.setModificado( new java.util.Date()  );
                    
                    usuarioService.setUsuario(usuario);
                    LOGGER.info("[Controller,Cliente] Se cambió la contraseña de un usuario para un cliente ["+usuario.getIdUsuario()+", "+usuario.getNombre()+"]");
                    map = getInformacionCompletaDelCliente(clienteInt);
                    setInformacionEnVentana(map,0, "Edición de un  usuario", "Se cambio correctamente la contraseña de acceso del usuario con <br>Nombre: <b>"+usuario.getNombre()+"</b> y <br>Correo: <b>"+usuario.getCorreoElectronico()+"</b>");    
                    setBitacora("Actualización", "Cambió correctamente la contraseña del usuario "+usuario.getCorreoElectronico().toUpperCase()+".", request);
                    notificacionesService.cambioDeContrasena(usuario, false);
                    return new ModelAndView("cliente/kardexDelCliente","model",map);
        
        }catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de cambiar la contraseña de acceso de un usuario en el kardex del cliente  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de cambiar la contraseña del usuario del cliente");
            getAllClientes(map);
        }        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de activar/desactivar un usuario
     * @param request Variable encargada manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/activar-usuario.htm",method = RequestMethod.POST)
    public ModelAndView activarUsuario(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se solicita el cambio de estado del usuario de un cliente");        
         Map<String,Object> map = new HashMap<String, Object>();       
        try {
                        Integer idUsuario = Integer.parseInt(request.getParameter("vlr"));        
                        Integer clienteInt = Integer.parseInt(request.getParameter("clnt"));
                        Usuario usuario = usuarioService.getUsuario( new Usuario(idUsuario) );
                        boolean status = !usuario.getStatus();
                        usuario.setStatus(status);
                        usuario.setModificado( new java.util.Date()  );

                        usuarioService.setUsuario(usuario); 
                         LOGGER.info("[Controller,Cliente] Se cambió el estatus de un usuario en el kardex de un cliente ["+usuario.getIdUsuario()+", "+usuario.getNombre()+"]");
                        map = getInformacionCompletaDelCliente(clienteInt);
                        if(status){
                            setBitacora("Actualización", "Activó el usuario "+usuario.getCorreoElectronico().toUpperCase()+".", request);
                            setInformacionEnVentana(map,0, "Edición de un  usuario", "Se activo el usuario con <br>Nombre: <b>"+usuario.getNombre()+"</b> y <br>Correo: <b>"+usuario.getCorreoElectronico()+"</b>"); 
                        }else{
                            setBitacora("Actualización", "Desactivó el usuario "+usuario.getCorreoElectronico().toUpperCase()+".", request);
                            setInformacionEnVentana(map,0, "Edición de un  usuario", "Se desactivo el usuario con <br>Nombre: <b>"+usuario.getNombre()+"</b> y <br>Correo: <b>"+usuario.getCorreoElectronico()+"</b>"); 
                        }   
                        return new ModelAndView("cliente/kardexDelCliente","model",map);        
        }catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de cambiar la contraseña de acceso de un usuario en el kardex del cliente  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de cambiar la contraseña del usuario del cliente");
            getAllClientes(map);
        }        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargador de mostrar la vista para cargar la nomina de un cliente
     * @param idCliente Numero entero que almacena el id de un cliente
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/cargar-nomina/{idCliente}.htm",method = RequestMethod.GET)
    public ModelAndView getCargarNomina(@PathVariable("idCliente") int idCliente){
        LOGGER.info("[Controller,Cliente] Se solicita la vista para cargar el archivo de nómina de un cliente ["+idCliente+"]");
        
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("carga", true);
        
        try{            
                Cliente cliente = clienteService.getCliente(idCliente);
                List<Sindicato> sindicatos = sindicatoService.getSindicatos();
                if(cliente!=null){ 
                    if(cliente.getStatus()){
                        map.put("cliente", cliente);
                        map.put("sindicatos", sindicatos);
                        return new ModelAndView("cliente/cargarNomina", "model", map);
                    }else{
                       LOGGER.info("[Controller,Cliente] El cliente "+cliente.getNombreEmpresa()+" se encuentra desactivado, no es posible registrar la nómina de un cliente desactivado");
                        setInformacionEnVentana(map,2, "Módulo de clientes", "El cliente <b>"+cliente.getNombreEmpresa()+"</b> se encuentra desactivado, no es posible registrar la nómina de un cliente desactivado.");
                        getAllClientes(map);
                    }
                }
        }catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar crear la vista para cargar la nómina  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar crear la vista para cargar la nómina");
            getAllClientes(map);
        }        
        return new ModelAndView("cliente/clientes", "model",map);
        
    }
    
    /**
     * Controller encargado de mostrar los detalles de la nómina ingresada
     * @param archivoDeNomina Archivo de nomina xlsx
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/detalles-de-nomina-ingresada.htm",method = RequestMethod.POST)
    public ModelAndView setCargarNomina(@RequestParam("rchvDNmn") MultipartFile archivoDeNomina, HttpServletRequest request, HttpServletResponse response){
            LOGGER.info("[Controller,Cliente] Se solicita la vista para cargar el archivo de nómina de un cliente");
        
           Map<String,Object> map = new HashMap<String, Object>(); 
         
         try{
             
                    Integer clienteInt = Integer.parseInt(request.getParameter("clienteId"));
                    String fechaDesdeString = request.getParameter("fchDsd");
                    String fechaHastaString = request.getParameter("fchHst");
                    Integer sindicatoInt = Integer.parseInt(request.getParameter("sindicatoId"));
                
                    Date fechaInicial = procesayyyyMMdd(fechaDesdeString);
                    Date fechaHasta = procesayyyyMMdd(fechaHastaString);      
                    
                    Cliente cliente = clienteService.getCliente(clienteInt);
                    Sindicato sindicato = sindicatoService.getSindicato(sindicatoInt);
                    if(cliente!=null){ 
                        
                            if(fechaHasta.before(fechaInicial)){
                                    map.put("carga", true);  
                                    setInformacionEnVentana(map,0, "Módulo de clientes", "La fecha inicial del periodo debe ser menor a la fecha final.");
                                    List<Sindicato> sindicatos = sindicatoService.getSindicatos();
                                    map.put("sindicatos", sindicatos);
                            }else{              
                                    List<Recibo> recibos = excelService.archivoDeNomina(cliente, archivoDeNomina, fechaInicial, fechaHasta, sindicato);
                                    reciboService.setRecibo(recibos);
                                    map.put("recibos", recibos);
                                    setInformacionEnVentana(map,0, "Módulo de clientes", "Se ingresaron "+recibos.size()+" recibos.");        
                                    map.put("hoy", new Date() );
                                    map.put("carga", false);
                                    map.put("fechaAString", procesayyyyMMdd(new Date()));
                                    map.put("fechaBString", procesayyyyMMdd(new Date()));  
                                    map.put("cliente", cliente);
                                    map.put("sindicato", sindicato);
                                    setBitacora("Inserción", "Ingresó "+recibos.size()+" recibos al cliente "+cliente.getNombreEmpresa()+".", request);
                            }                 
                             map.put("cliente", cliente);  
                            Runtime.getRuntime().gc();           
                        
                    }
            }catch (ParseException ex) {
                       Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "[Controller,Cliente] Error al procesar una fecha de texto a Date ");
            }catch(NumberFormatException nfe){
                LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de crear la vista -->"+nfe);
        } 
        
        return new ModelAndView("cliente/cargarNomina", "model", map);
    }
    
    /**
     * Controller encargado de enviar los recibos por email de una nómina
     * @param request Varibale encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     */
    @RequestMapping(value="cliente/enviar-recibos-email.htm",method = RequestMethod.POST)
    public void enviarRecibosPorCorreo(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Cliente] Se solicita la generación de los recibos en formato PDF");        
        try {
                Integer idCliente = Integer.parseInt(request.getParameter("cliente"));
                String diaRegistroDesdeString = request.getParameter("diaRegistroDesde");
                String diaRegistroHastaString = request.getParameter("diaRegistroHasta");
                        
                Date diaRegistroDesde = procesayyyyMMdd(diaRegistroDesdeString);
                Date diaRegistroHasta = procesayyyyMMdd(diaRegistroHastaString);
                
                String correo = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el correo de la sesión actual
                Usuario usuario = usuarioService.getUsuario(correo); //Obtiene el Usuario a partir del correo de la sesión actual
                
                Cliente cliente = clienteService.getCliente(idCliente);
                if(cliente != null){
                    List<Recibo> recibos = reciboService.getRecibosPeriodo(cliente, diaRegistroDesde,diaRegistroHasta);
                    if(recibos != null){    
                        notificacionesService.reciboDeNomina(recibos, usuario); //Se reajusta la firma de reciboDeNomina para que reciba el parametro de usuario
                    }else{            
                         LOGGER.error("[Controller, Cliente] No se encontró la información de los recibos {"+recibos.size()+"} solicitados, Imposible generar archivo");
                    }
                } 
        } catch (Exception e) {
            LOGGER.error("[Controller, Cliente] Ocurrió un error al momento de generar el PDF con los recibos   -> "+e);
        }
    }
    
    /**
     * Controller encargado de generar los pdf y almacenarlos en un solo archivo zip
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     */
    @RequestMapping(value = "cliente/generar-recibos-pdf-zip.htm",method = RequestMethod.POST)
    public void generarRecibosZIP(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Cliente] Se solicita la generación de los recibos en formato PDF");
        
        try {
             Integer idCliente = Integer.parseInt(request.getParameter("cliente"));
                String diaRegistroDesdeString = request.getParameter("diaRegistroDesde");
                String diaRegistroHastaString = request.getParameter("diaRegistroHasta");
        
                Date diaRegistroDesde = procesayyyyMMdd(diaRegistroDesdeString);
                Date diaRegistroHasta = procesayyyyMMdd(diaRegistroHastaString);
                
                Cliente cliente = clienteService.getCliente(idCliente);
                LOGGER.warn("[Controller, Cliente] Se solicita la generación de los recibos en formato PDF "+cliente.getNombreEmpresa().toUpperCase()+"[>"+diaRegistroDesdeString+"<>"+diaRegistroHastaString+"<]");
                if(cliente != null){
                    List<Recibo> recibos =  reciboService.getRecibosPeriodo(cliente, diaRegistroDesde,diaRegistroHasta);
                     if(recibos.isEmpty()){                          
                        LOGGER.error("[Controller, Cliente] No se pueden generar recibos en PDF ya que no fue encontrado ningún recibo");
                    }else{                        
                        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                        String del = formatoFechaCorto(diaRegistroDesde); //Se formatea la fecha diaRegistroDesde por dd/MM/yyyy 
                        String al = formatoFechaCorto(diaRegistroHasta); // Se formatea la fecha diaRegistroHasta dd/MM/yyyy
                        boolean reciboDeNominaZIp = notificacionesService.reciboDeNominaZIp(correo, recibos, cliente, diaRegistroDesdeString, diaRegistroHastaString); //Se guarda en una variable boolean si el recibo de nomina a sido procesado 
                        if(reciboDeNominaZIp){ //Se valida que reciboDeNominaZIp regrese true
                            String subject ="RECIBOS ZIP DE "+cliente.getNombreEmpresa().toUpperCase()+" DEL "+del+" AL "+al+'.'; //Se construye el detalle con el nombre de la empresa y el rango de fechas con formato simple
                            setBitacora("Envío de correo", subject, request); //Se inserta en la tabla Bitacora el moviemnto de envio de correo
                        }
                    }
                } 
        } catch (NumberFormatException | ParseException e) {
            LOGGER.error("[Controller, Cliente] Ocurrió un error al momento de generar el PDF con los recibos en zip   -> "+e);
        }
    }
    
    /**
     * Controller encargado de generar los recibos en pdf
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     */
    @RequestMapping(value = "cliente/generar-recibos-pdf.htm",method = RequestMethod.POST)
    public void generarRecibosPDF(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Cliente] Se solicita la generación de los recibos en formato PDF");
        
        try {
                Integer idCliente = Integer.parseInt(request.getParameter("cliente"));
                String diaRegistroDesdeString = request.getParameter("diaRegistroDesde");
                String diaRegistroHastaString = request.getParameter("diaRegistroHasta");
                        
                Date diaRegistroDesde = procesayyyyMMdd(diaRegistroDesdeString);
                Date diaRegistroHasta = procesayyyyMMdd(diaRegistroHastaString);
                
                Cliente cliente = clienteService.getCliente(idCliente);
                LOGGER.warn("[Controller, Cliente] Se solicita la generación de los recibos en formato PDF "+cliente.getNombreEmpresa().toUpperCase()+"[>"+diaRegistroDesdeString+"<>"+diaRegistroHastaString+"<]");
                if(cliente != null){
                    List<Recibo> recibos = reciboService.getRecibosPeriodo(cliente, diaRegistroDesde,diaRegistroHasta);
                    if(recibos != null){    
                        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                        String del = formatoFechaCorto(diaRegistroDesde); //Se formatea la fecha diaRegistroDesde por dd/MM/yyyy 
                        String al = formatoFechaCorto(diaRegistroHasta); // Se formatea la fecha diaRegistroHasta dd/MM/yyyy
                        boolean reciboDeNomina = notificacionesService.reciboDeNomina(correo, recibos, cliente, diaRegistroDesdeString, diaRegistroHastaString); //Se guarda en una variable boolean si el recibo de nomina a sido procesado
                        if(reciboDeNomina){ //Se valida que reciboDeNominaZIp regrese true
                            String subject ="RECIBOS PDF DE "+cliente.getNombreEmpresa().toUpperCase()+" DEL "+del+" AL "+al+'.'; //Se construye el detalle con el nombre de la empresa y el rango de fechas con formato simple
                            setBitacora("Envío de correo", subject, request); //Se inserta en la tabla Bitacora el moviemnto de envio de correo
                        }
                    }else{            
                         LOGGER.error("[Controller, Cliente] No se encontró la información de los recibos {"+recibos.size()+"} solicitados, Imposible generar archivo");
                    }
                } 
        } catch (Exception e) {
            LOGGER.error("[Controller, Cliente] Ocurrió un error al momento de generar el PDF con los recibos   -> "+e);
        }
    }
    
    /**
     * Controller encargado de mostrar los detalles del recibo en el sistema
     * @param idRecibo Numero entero que almacena el id de un recibo
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/detalle-del-recibo/{idRecibo}.htm",method = RequestMethod.GET)
    public ModelAndView getDetallesDelRecibo(@PathVariable("idRecibo") Integer idRecibo){
        LOGGER.info("[Controller, Cliente] Se solicita la vista detallada de un recibo ["+idRecibo+"]");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Recibo recibo = reciboService.getRecibo(idRecibo);
            if(recibo != null){
                map.put("recibo", recibo);
                return new ModelAndView("sistema/detalleDeNomina", "model", map);                
            }else{                
                setInformacionEnVentana( map,2,"Sistema","Ocurrio un error al momento de generar la vista detallada de un recibo");
            }
        } catch (Exception e) {
            LOGGER.error("[Controller, Cliente] Ocurrió un error al momento de generar la vista detallada de un recibo ["+idRecibo+"]  -> "+e);
            setInformacionEnVentana( map,1,"Módulo Clientes","Ocurrio un error al momento de generar la vista detallada de un recibo");
        }
        getAllClientes(map);
        return new ModelAndView("cliente/clientes", "model",map);
    }
     
    /**
     * Controller encargado de mostrar la vista para obtener la la lista de incidencias del cliente
     * @param idCliente Numero entero que almacena un id de un cliente
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/incidencias/{idCliente}.htm", method = RequestMethod.GET)
    public ModelAndView getListaDeIncidencias(@PathVariable("idCliente") int idCliente){
        LOGGER.info("[Controller,Cliente] Se solicita la vista para listar las incidencias de un cliente ["+idCliente+"]");
        
         Map<String,Object> map = new HashMap<String, Object>();
        
        try{            
                Cliente cliente = clienteService.getCliente(idCliente);
                if(cliente!=null){
                        map.put("cliente", cliente);
                        map.put("muestraTabla", false);
                        return new ModelAndView("cliente/listaDeIncidencias", "model", map);
              }
        }catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar crear la vista con la lista de incidencias  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar crear la vista con la lista de incidencias");
            getAllClientes(map);
        }        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de mostrar la incidencias con respecto a un filtro dado
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las repuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/ver-incidencias.htm", method = RequestMethod.POST)
    public ModelAndView getInicidencias(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Cliente] Se solicita la vista con los filtros establecidos para la visualización de las incidencias");
        
        Map<String,Object> map = new HashMap<String, Object>();
        
        try {
            Integer idCliente = Integer.parseInt( request.getParameter("idCliente") );
            Integer opc = Integer.parseInt( request.getParameter("buscarPor") );
            String desdeString = request.getParameter("ncdncsDsd");
            String hastaString = request.getParameter("ncdncsHst");
            
            Date desde = procesayyyyMMddHHmmss(desdeString+" 00:00:00.000");
            Date hasta = procesayyyyMMddHHmmss(hastaString+ " 23:59:59.999");
            
            Cliente cliente = clienteService.getCliente(idCliente);
            if(desde.after(hasta)){                    
                    if(cliente!=null){
                            map.put("cliente", cliente);
                            map.put("muestraTabla", false);
                            setInformacionEnVentana(map,2, "Módulo de clientes", "UPS! <br/>La fecha 'Hasta' no puede ser menor que la la fecha 'Desde'<br/>Desde: <b>"+desdeString+"</b><br/>Hasta: <b>"+hastaString+"</b>");
                  }else{                        
                        setInformacionEnVentana(map,2, "Módulo de clientes", "No se encontró ningún cliente con el identificador "+idCliente);
                        getAllClientes(map);  
                        return new ModelAndView("cliente/clientes", "model",map);
                 }
            } else{                
                    map.put("cliente", cliente);
                    List<Incidencia> incidencias ;
                    if(opc==1){ //Fecha de registro
                        hasta = sumarRestarDias(hasta, 1);                        
                        incidencias = incidenciaService.getIncidenciasFechaRegistro(cliente, desde, hasta);
                    }else{ //Fecha de la incidencia
                        incidencias = incidenciaService.getIncidenciasFechaIncidencia(cliente, desde, hasta);
                    }
                    map.put("incidencias", incidencias);
                    map.put("muestraTabla", true);
                    List<TipoIncidencia> tipoIncidencias = incidenciaService.getTipoIncidencias();
                    map.put("tipoIncidencias", tipoIncidencias);
                    map.put("fechaDesde", desdeString);
                    map.put("fechaHasta", hastaString);
            }           
        }catch(NumberFormatException  nfe){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar formatear los datos para la consulta de la lista de incidencias  --> "+nfe);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar formatear los datos para la consulta de la lista de incidencias ");
            map.put("muestraTabla", false);
        } catch(ParseException pe){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar parsear los datos para la consulta de la lista de incidencias --> "+pe);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar parsear los datos para la consulta de la lista de incidencias ");
            map.put("muestraTabla", false);
        } catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar generar la consulta para crear la lista de incidencias  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar crear la vista con la lista de incidencias consultadas");
            map.put("muestraTabla", false);
        }        
        return new ModelAndView("cliente/listaDeIncidencias", "model", map);        
    }
    
    /**
     * Controller encargado de mostrar los datos para actualizar una incidencia
     * @param idC Numero entero que almacena el id de un cliente
     * @param idA Numero entero que almacena el id de un colaborador
     * @param idI Numero entero que alamcena el id de un incidencia
     * @param idTI Numero entero que alamcena el id de un tipo de incidencia
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/editar-incidencia/{idCliente}/{idAgremiado}/{idIncidencia}/{idTipoIncidencia}.htm", method = RequestMethod.GET)
    public ModelAndView setDatosIncidencia(@PathVariable("idCliente")int idC ,@PathVariable("idAgremiado")int idA,@PathVariable("idIncidencia") int idI, @PathVariable("idTipoIncidencia") short idTI){
        LOGGER.info("[Controller,Cliente] Se solicita la vista para editar un incidencias desde un cliente ["+idC+","+idA+","+idI+","+idTI+"]");
        
         Map<String,Object> map = new HashMap<String, Object>();
            
         try {
            Incidencia inicidencia = incidenciaService.getInicidencia(idC, idA, idI, idTI);
            if(inicidencia != null){
                if(!inicidencia.getStatus()){
                    map.put("incidencia", inicidencia);
                    List<TipoIncidencia> tipoIncidencias = incidenciaService.getTipoIncidencias();
                    map.put("tipoIncidencias", tipoIncidencias);
                     return new ModelAndView("cliente/editarIncidencia", "model",map);
                }else{                
                    setInformacionEnVentana(map,2, "Módulo de clientes", "Incidencia no puede ser editada ya que se encuentra en estado de revisada [c"+idC+",a"+idA+", i"+idI+"t"+idTI+"]");                   
                }
            }else{
                LOGGER.info("[Controller,Cliente]  No se encontró ninguna incidencia con los valores ingresados ["+idC+",a"+idA+", i"+idI+"t"+idTI+"]");               
                setInformacionEnVentana(map,2, "Módulo de clientes", "<b>UPS!</b> Algo no salió como debía<br/> No se encontró ninguna incidencia con los valores enviados");
            }
        } catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar crear la vista con la lista de incidencias  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar crear la vista con la lista de incidencias");
        }
        getAllClientes(map);        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de actualizar una incidencia 
     * @param incidencia Instancia de Incidencia
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="cliente/actualizar-incidencia.htm", method = RequestMethod.POST)
    public ModelAndView updateIncidencia(@ModelAttribute("incidencia") Incidencia incidencia,HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Cliente] Se reciben los datos para actualizar una incidencia ["+incidencia+"]");
        
        Map<String,Object> map = new HashMap<String, Object>();
        
        try {
                    Integer idCliente = Integer.parseInt(request.getParameter("idCliente"));
                    Integer idAgremiado = Integer.parseInt(request.getParameter("idAgremiado"));
                    Short tipoIncidenciaOld = Short.parseShort( request.getParameter("tipoInc"));
                    String fechaIncidenciaString = request.getParameter("fchDLNcdnc");           
                    Date fechaIncidencia = procesayyyyMMdd(fechaIncidenciaString);
                    
                    Incidencia inicidencia = incidenciaService.getInicidencia(idCliente, idAgremiado, incidencia.getIdIncidencia(), tipoIncidenciaOld);
                    
        
                    if(inicidencia != null){
                            incidencia.setFechaRegistro(inicidencia.getFechaRegistro());
                            incidencia.setAgremiadoObj(inicidencia.getAgremiadoObj());
                            incidencia.setClienteObj(inicidencia.getClienteObj());
                            incidencia.setFechaIncidencia(fechaIncidencia);

                            incidencia.setFechaModificacion( new Date() );
                            
                            TipoIncidencia tipoIncidencia = incidenciaService.getTipoIncidencia(tipoIncidenciaOld);
                            
                            incidencia.setTipoIncidenciaObj(tipoIncidencia);
                            
                            Integer setIncidencia = incidenciaService.setIncidencia( incidencia );
                            if(setIncidencia>0){                
                                    List<Incidencia> incidencias ;
                                    LOGGER.info("[Controller,Cliente] La incidencia se actualizó correctamente");  
                                    setInformacionEnVentana(map,0, "Módulo de clientes", "La incidencia se actualizó correctamente");
                                    incidencias = incidenciaService.getIncidenciasFechaIncidencia(incidencia.getClienteObj(), fechaIncidencia, fechaIncidencia);
                                    map.put("cliente", incidencia.getClienteObj());
                                    map.put("incidencias", incidencias);
                                    map.put("muestraTabla", true);
                                    List<TipoIncidencia> tipoIncidencias = incidenciaService.getTipoIncidencias();
                                    map.put("tipoIncidencias", tipoIncidencias);
                                    map.put("fechaDesde", fechaIncidenciaString);
                                    map.put("fechaHasta", fechaIncidenciaString); 
                                    setBitacora("Actualización", "Actualizó correctamente la información de la incidencia con Id "+incidencia.getIdIncidencia()+".", request);
                                    return new ModelAndView("cliente/listaDeIncidencias", "model", map); 
                            }
                    }
                    
                    LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar actualizar una incidencia ["+incidencia.getTipoIncidenciaObj()+"]");
                    setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar actualizar la inciencia");
        } catch(ParseException pe){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar parsear los datos para el ingreso de la incidencia --> "+pe);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar parsear los datos para el ingreso de la incidencia");
            map.put("muestraTabla", false);
        } catch(NumberFormatException nfe){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar formatear una  incidencia --> "+nfe);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar formatear la inciencia");
        } catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar actualizar una  incidencia --> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar actualizar la inciencia");
        }
        getAllClientes(map);        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de manejar las peticiones GET de url´s POST
     * @param idC Numero entero que almacena un id de cliente
     * @param idA Numero entero que almacena un id de colaborador
     * @param idI Numero entero que almacena un id de incidencia
     * @param idTI Numero entero que almacena un id de tipo de incidencia
     * @return ModelAndView
     */
    @RequestMapping(value = "cliente/ver-incidencia/{idCliente}/{idAgremiado}/{idIncidencia}/{idTipoIncidencia}.htm", method = RequestMethod.GET)
    public ModelAndView getIncidencia(@PathVariable("idCliente")int idC ,@PathVariable("idAgremiado")int idA,@PathVariable("idIncidencia") int idI, @PathVariable("idTipoIncidencia") short idTI){
         LOGGER.info("[Controller,Cliente] Se solicita la vista del kardex de la incidencia [c"+idC+",a"+idA+", i"+idI+"t"+idTI+"]");   
        
         Map<String,Object> map = new HashMap<String, Object>();
         
         try {
            Incidencia inicidencia = incidenciaService.getInicidencia(idC, idA, idI, idTI);
            if(inicidencia != null){
                        map.put("incidencia", inicidencia);
                        List<TipoIncidencia> tipoIncidencias = incidenciaService.getTipoIncidencias();
                        map.put("tipoIncidencias", tipoIncidencias);
                     return new ModelAndView("cliente/kardexDeLaIncidencia", "model",map);               
            }else{
                LOGGER.info("[Controller,Cliente]  No se encontró ninguna incidencia con los valores ingresados [c"+idC+",a"+idA+", i"+idI+"t"+idTI+"]");                  
                setInformacionEnVentana(map,2, "Módulo de clientes", "<b>UPS!</b> Algo no salió como debía<br/> No se encontró ninguna incidencia con los valores enviados");
            }
        } catch(Exception e){
            LOGGER.error("[Controller,Cliente]  Ocurrio un error al momento de intentar crear la vista con la lista de incidencias  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de clientes", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de intentar crear la vista con la lista de incidencias");
        }
        getAllClientes(map);        
        return new ModelAndView("cliente/clientes", "model",map);
    }
    
    /**
     * Controller encargado de devolver el documento solicitado de un cliente
     * @param idCliente Numero entero que representa el id de un cliente
     * @param documento Nombre del documento legal
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     */
    @RequestMapping(value="cliente/{idCliente}/{documento}.htm",method = RequestMethod.GET)
    public void verDocumento(@PathVariable("idCliente") Integer idCliente, @PathVariable("documento") String documento,  HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Cliente] Se solicita la vista del documento legal ["+documento+"] del cliente ["+idCliente+"]");
        Cliente cliente = clienteService.getCliente(idCliente);
        if(cliente != null){
            try {
                if( documento.equalsIgnoreCase("ActaNotarial") ){
                    ActaNotarial an = cliente.getActaNotarialList().get(0);
                    if(an != null ){
                        File descargarFileFTP = ftpService.descargarFileFTP(an.getUrlDocumento());
                        InputStream is = new FileInputStream(descargarFileFTP);
                        response.setHeader("Content-Disposition", "inline;filename=\"" + documento + ".pdf\"");
                        OutputStream sos = response.getOutputStream();
                        response.setContentType("application/pdf");
                        IOUtils.copy(is, sos);
                        sos.flush();
                    }else{
                        LOGGER.error("[Controller, Cliente] No se encontró el acta notarial del cliente "+cliente.getNombreEmpresa());
                    }
                }else if( documento.equalsIgnoreCase("PoderLegal") ){
                    ActaNotarial an = cliente.getActaNotarialList().get(0);
                    if( an.getTienePoderLegal() ){
                        PoderLegal pl = an.getPoderLegal();
                        if( pl != null ){
                            File descargarFileFTP = ftpService.descargarFileFTP(pl.getUrlDocumento());
                            InputStream is = new FileInputStream(descargarFileFTP);
                            response.setHeader("Content-Disposition", "inline;filename=\"" + documento + ".pdf\"");
                            OutputStream sos = response.getOutputStream();
                            response.setContentType("application/pdf");
                            IOUtils.copy(is, sos);
                            sos.flush();                            
                        }else{
                            LOGGER.error("[Controller, Cliente] No se encontró el poder legal del cliente "+cliente.getNombreEmpresa());
                        }
                    }else{
                        LOGGER.error("[controller, Cliente] Se esta solicitando el poder legal del cliente "+cliente.getNombreEmpresa()+" pero no cuenta con dicho documento ["+an.getTienePoderLegal()+"]");
                    }                
                }else{
                    LOGGER.error("[Controller, Cliente] No existe ningún documento legal ["+documento+"] correspondiente para el cliente ["+cliente.getNombreEmpresa()+"]");
                }
            } catch (FileNotFoundException fnfe) {
                Logger.getLogger(SinidicatoController.class.getName()).log(Level.SEVERE, "[Controller,Cliente,fnfe] El archivo no puede ser generado para su visualización ", fnfe);
            } catch (IOException ioe) {
                Logger.getLogger(SinidicatoController.class.getName()).log(Level.SEVERE, "[Controller,Cliente,ioe] El archivo no puede ser generado para su visualización ", ioe);
            }
        }else{
            LOGGER.error("[Controller, Cliente] No se encuentró ningún cliente ["+idCliente+"]");
        }
    }
    
    /**
     * Controller encargado de administrar las peticiones GET de url´s POST
     * @return ModelAndView
     */
    @RequestMapping(value={"cliente/actualizar-incidencia.htm","cliente/ver-incidencias.htm","cliente/detalles-de-nomina-ingresada.htm","cliente/activar-usuario.htm","cliente/acceso-usuario.htm","cliente/editar-usuario.htm","cliente/registrar-usuario.htm","cliente/domicilio-de-notificacion.htm","cliente/domicilio-fiscal.htm","cliente/instrumento-notarial.htm","cliente/datos-cliente.htm"},method = RequestMethod.GET)
     public ModelAndView vistasSoloConAccesoPost(){
            LOGGER.info("[Controller,Cliente] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando... ");
            
           Map<String,Object> map = new HashMap<String, Object>();
            try{
                    getAllClientes(map);
            }catch(Exception e){
                        LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de crear la vista con todos los clientes  -> "+e);
                        setInformacionEnVentana(map,1, "Módulo de clientes", "Ocurrio un error al momento de crear la vista con todos los clientes");   
            }
            return new ModelAndView("cliente/clientes", "model",map);
     }
    
     /**
     * Metodo encargado de devolver la información parcial de un cliente
     * @param idCliente Numero entero que representa el id de un cliente
     * @return Map String,Object
     */
    private Map<String,Object> getInformacionCliente(Integer idCliente){
        Map<String,Object> map = new HashMap<String, Object>();
        Cliente cliente = clienteService.getCliente(idCliente);
        if( cliente != null){
             map.put("cliente",cliente);
            List<ActaNotarial> actaNotarialList = cliente.getActaNotarialList();
             if(actaNotarialList.size()>0){
                 ActaNotarial actaNotarial = actaNotarialList.get(0);
                 map.put("actaNotarial", actaNotarialList.get(0));
                 if(actaNotarial.getTienePoderLegal()){
                        PoderLegal poderLegal = actaNotarial.getPoderLegal();
                        if(poderLegal != null){
                            map.put("poderLegal", poderLegal);
                        }
                   }
             }
            List<EmpresasDomicilio> empresasDomicilioList = cliente.getEmpresasDomicilioList();
             if(empresasDomicilioList.size()>0){
                 empresasDomicilioList.stream().forEach((empresasDomicilio) -> {
                     if(empresasDomicilio.getTipoDomicilioObj() == 1) {// Domicilio Fiscal
                         map.put("domicilioFiscal", empresasDomicilio);
                     }else{//Domicilio de notificación
                         map.put("domicilioNotificacion", empresasDomicilio);                     
                     }
                 });
             }
            List<ZonaSm> zonaSm = zonasSmService.getZonaSm();
            map.put("zonasSalariales", zonaSm);
            List<TipoActa> tipoActa = actaNotarialService.getTipoActa();            
            map.put("tiposDeActa", tipoActa);

        }
        return map;
    }
    
    /**
     * Metodo encargado de devolver la información completa de un cliente
     * @param idCliente Numero entero que representa el id de un cliente
     * @return Map String,Object 
     */
    private Map<String,Object> getInformacionCompletaDelCliente(Integer idCliente){
        Map<String,Object> map = new HashMap<String, Object>();
        Cliente cliente = clienteService.getCliente(idCliente);
                if(cliente!=null){ 
                    map.put("cliente", cliente);
                    ZonaSm zonaSM = zonasSmService.getZonaSM(cliente.getZonaSm());
                    if(zonaSM != null ){
                        map.put("zonasm",zonaSM);
                    }

                    List<ActaNotarial> actaNotarialList = cliente.getActaNotarialList();
                    if(actaNotarialList != null ){
                       if(!actaNotarialList.isEmpty()){
                           ActaNotarial actaNotarial = actaNotarialList.get(0);
                            if(actaNotarial != null ){
                                 map.put("actanotarial",actaNotarial);
                                if(actaNotarial.getTienePoderLegal()){
                                     PoderLegal poderLegal = actaNotarial.getPoderLegal();
                                     if(poderLegal != null){
                                         map.put("poderlegal", poderLegal);
                                     }
                                }
                             }
                       }
                    }

                  List<EmpresasDomicilio> empresasDomicilioList = cliente.getEmpresasDomicilioList();
                  if(empresasDomicilioList != null ){
                     map.put("empresasDomicilioList", empresasDomicilioList);
                  }

                  List<ContratoEmpresas> contratoEmpresasList = cliente.getContratoEmpresasList();
                  if( contratoEmpresasList != null ){
                     map.put("contratoEmpresas", contratoEmpresasList);
                        Map<Integer,String> cotratistasMap = new HashMap<>();
                           contratoEmpresasList.stream().map((contratoEmpresas) -> contratistaService.getContratista(contratoEmpresas.getContratistaObj())).forEach((contratista) -> {
                       cotratistasMap.put(contratista.getIdContratista(), contratista.getNombreEmpresa());
                    });
                    map.put("contratistas", cotratistasMap);
                  }

                  List<Rol> roles = usuarioService.getRoles();
                  if(roles != null){
                      map.put("roles", roles);
                  }

                  List<Usuario> usuarioList = cliente.getUsuarioList();
                  if(usuarioList != null ){
                      map.put("usuarioList",usuarioList);
                      Map<Integer,String> mapRoles = new HashMap<>();
                      usuarioList.stream().forEach((usuario) -> {
                          String rolString = usuarioService.getRol(usuario.getRol()).getNombre();
                          mapRoles.put(usuario.getRol(), rolString);
                      });
                      map.put("mapRoles", mapRoles);
                  }                     
                }
        return map;
    }
    
    /**
     * Metodo encargado de establecer todos los clientes que existen dentro del sistema
     * @param map
     */
    private void getAllClientes(Map<String,Object> map){
        if(map != null){
            List<Cliente> clientes = getClientesDelUsuario();
            if(clientes != null){
                    map.put("clientesEncontrados",clientes);
                    map.put("muestraTrabla", true);
            }
        }
    }
   private List<Cliente> getClientesDelUsuario() {
        List<Cliente> clientes =  new LinkedList<>();
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(correo);
        if(usuario!=null){
            clientes = usuario.getClienteList();
            if(clientes.isEmpty()){
                clientes = clienteService.getClientes();
            }
        }
        return clientes;
//        List<Cliente> clientesBack = new LinkedList<>();
//        for (Cliente cliente : clientes) {
//            //if(cliente.getStatus()){
//                clientesBack.add(cliente);
//            //}
//        }
//        clientes = null;
//        return clientesBack;
    }
    private void setInformacionEnVentana(Map<String,Object> map, int estado, String titulo, String descripcion){
        if(map!=null){
                map.put("mostrarVentana", true);
                map.put("tipoVentana", estado); 
                map.put("tituloVentana", titulo);
                map.put("descripcionVentana", descripcion);
        }
        Runtime.getRuntime().gc(); 
    }
    private String getAcceso() {
       return nuevaContrasena();
    }
    private String getNuevoAcceso() {
        return nuevaContrasena();
    }
    /**
     * Metodo encargado de generar un nueva contraseña 
     * @return String
     */
    private String nuevaContrasena(){
          String rest = "";
           int length = 8;
           int counter = 0;
        do{
            int rndmNt =  (int) (( Math.floor( Math.random() * 4 ) + 1 ) % 4) ;
            int min = rangos[rndmNt][0];
            int max = rangos[rndmNt][1];
            int btwn = betweenInt(min,max);
            char character = ( (char) btwn );
            if( rest.indexOf( character ) == -1 ){                
                rest += character;
                counter++;
            }            
        }while(counter < length);
        String enc;
        try {
            enc = encriptarAcceso(rest);
        } catch (UnsupportedEncodingException uee) {
            enc = null;
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, "[Controller, Cliente] Error al inentar encriptar una contraseña", uee);
        }
        return  enc;
    }
    private String encriptarAcceso(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
    private String desencriptarAcceso(String s) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }
    private int betweenInt(int min, int max) {
            return  (int) ( Math.random() * ( max - min ) + min );
    }
    private Date procesayyyyMMdd(String fechaString) throws ParseException {        
        String yyyyMMdd = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        return formatter.parse(fechaString);
    }
    private Date procesayyyyMMddHHmmss(String fechaString) throws ParseException {        
        String yyyyMMdd = "yyyy-MM-dd hh:mm:ss";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        return formatter.parse(fechaString);
    }    
   private String procesayyyyMMdd(Date date){
        String yyyyMMdd = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
       return formatter.format(date);
   }
    private Date sumarRestarDias(Date fecha, int dias){	
         Calendar calendar = Calendar.getInstance();	
         calendar.setTime(fecha); 	
         calendar.add(Calendar.DAY_OF_YEAR, dias); 	
         return calendar.getTime(); 	
    }
    private boolean getEstado(String statusString) {
        return statusString.equalsIgnoreCase("Sí");
    }
    private boolean cargarDocumento(Cliente cliente, String doc, Object obj, MultipartFile archivoPDF){
            LOGGER.info("[Controller,Cliente] Se intenta cargar el documento ["+doc+"] del cliente "+cliente.getNombreEmpresa());
           try {
                        
                    if(obj instanceof ActaNotarial){
                        ActaNotarial an = (ActaNotarial) obj;
                        if( an.getUrlDocumento() != null ){
                            String ruta = an.getUrlDocumento();
                            String fileString= (ruta.length() > 27 )?"..."+ruta.substring((ruta.length()-27)):"...";
                            LOGGER.info("[Controller,Cliente] El cliente ya cuenta con un documento  ["+fileString+", "+doc+"] se procede a su eliminación");
                            ftpService.borrarArchivoFTP( ruta );
                        }
                        String direccionArchivoFTP = ftpService.guardarArchivoFTP(archivoPDF, "Clientes", cliente.getIdCliente().toString(), "documento_"+doc);
                        an.setUrlDocumento(direccionArchivoFTP);
                        LOGGER.info("[Controller, Cliente] Se intentan registrar el documento ["+doc+"] del cliente "+cliente.getNombreEmpresa()+" dentro de la base de datos {"+direccionArchivoFTP+"}");
                        Integer setActaNotarial = actaNotarialService.setActaNotarial( an );
                        return setActaNotarial != null;
                    }else if(obj instanceof PoderLegal){
                        PoderLegal pl = (PoderLegal) obj;
                        if(  pl.getUrlDocumento() != null ){
                            String ruta = pl.getUrlDocumento();
                            String fileString= (ruta.length() > 27 )?"..."+ruta.substring((ruta.length()-27)):"...";
                            LOGGER.info("[Controller, Cliente] El cliente ya cuenta con un documento ["+fileString+", "+doc+"] se procede a su eliminación");
                            ftpService.borrarArchivoFTP( ruta );
                        }
                        String direccionArchivoFTP = ftpService.guardarArchivoFTP(archivoPDF, "Clientes", cliente.getIdCliente().toString(), "documento_"+doc);
                        pl.setUrlDocumento(direccionArchivoFTP);
                        LOGGER.info("[Controller, Cliente] Se intentan registrar el documento ["+doc+"] del cliente "+cliente.getNombreEmpresa()+" dentro de la base de datos");
                        actaNotarialService.setActaNotarial(pl);
                        return direccionArchivoFTP.equalsIgnoreCase( pl.getUrlDocumento() );
                    }else{
                        LOGGER.error("[Controller, Cliente] Se recibe un objeto que no corresponde a ningún tipo de documento legal para el cliente "+cliente.getNombreEmpresa());
                        return false;
                    }
        } catch (Exception e) {
            LOGGER.error("[Controller,Cliente] Ocurrio un error al momento de intentar almacenar el documento ["+doc+"] del cliente  -> "+e);
            return false;
        }
    }
    private void setBitacora(String movimiento, String detalles,HttpServletRequest request){
       String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(correo);
        if(usuario!=null){
            Bitacora bitacora = new Bitacora(new Date(), usuario.getIdUsuario());
            bitacora.setModulo("Clientes");
            bitacora.setMovimiento(movimiento);
            bitacora.setDetalles(detalles);
            bitacora.setIpAcceso(request.getRemoteAddr());
            bitacoraService.setBitacora(bitacora);
        }else{
            LOGGER.error("[Controller, Cliente] Bitacora.- Se busco al usuario {"+correo+"} pero no se encontró registro alguno.");
        }
   }
    private String formatoFechaCorto(Date fecha) { //Se creo el metódo formatoFechaCorto de tipo String recibiendo como parametro una variable tipo Date      
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //Se instancia la clase DateFormat con la configuración dd/MM/yyyy
        return  formatter.format(fecha); //regresa fecha formateada
    }
    private final int[][] rangos = {{65,90},{97,122},{48,57},{35,38}};
    @Override
    public String toString() {
        return "ClienteController{Controlador Del Cliente, Versión 1.0}";
    }
}
