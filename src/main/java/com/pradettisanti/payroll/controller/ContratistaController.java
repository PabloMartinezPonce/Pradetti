/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.controller;

import com.pradettisanti.payroll.pojoh.ActaNotarial;
import com.pradettisanti.payroll.pojoh.Bitacora;
import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.PoderLegal;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.TipoActa;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.ActaNotarialService;
import com.pradettisanti.payroll.service.BitacoraService;
import com.pradettisanti.payroll.service.CatalogoDocumentalService;
import com.pradettisanti.payroll.service.ClienteService;
import com.pradettisanti.payroll.service.ContratistaService;
import com.pradettisanti.payroll.service.ContratoEmpresasService;
import com.pradettisanti.payroll.service.ContratoService;
import com.pradettisanti.payroll.service.FtpService;
import com.pradettisanti.payroll.service.PDFiTextService;
import com.pradettisanti.payroll.service.PeriodoFondoAhorroService;
import com.pradettisanti.payroll.service.SUPService;
import com.pradettisanti.payroll.service.UsuarioService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * Manager encargador de contener los controllers de la vista del modulo de contratistas
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@SuppressWarnings("Convert2Diamond")
public class ContratistaController {
    
     /**
     * Constante para el uso de los logs del sistema
     */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ContratistaController.class);    
   
    @Autowired
    private ContratistaService contratistaService;
    public ContratistaService getContratistaService() {
        return contratistaService;
    }
    public void setContratistaService(ContratistaService contratistaService) {
        this.contratistaService = contratistaService;
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
    private ClienteService clienteService;
    public ClienteService getClienteService(){
        return clienteService;
    }
    public void setClienteService(ClienteService clienteService){
        this.clienteService = clienteService;
    }
    
    @Autowired
    private ContratoEmpresasService contratoEmpresasService;
    public ContratoEmpresasService getContratoEmpresasService(){
        return contratoEmpresasService;
    }
    public void setContratoEmpresasService(ContratoEmpresasService contratoEmpresasService){
        this.contratoEmpresasService = contratoEmpresasService;
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
    private BitacoraService bitacoraService;
    public BitacoraService getBitacoraService(){
        return bitacoraService;
    }
    public void setBitacoraService(BitacoraService bitacoraService){
        this.bitacoraService = bitacoraService;
    }
    
    @Autowired
    private UsuarioService usuarioService;
    public UsuarioService getUsuarioService(){
        return usuarioService;
    }
    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    
    @Autowired
    private PDFiTextService pDFiTextService;
    public PDFiTextService getPDFiTextService(){
        return  pDFiTextService;
    }
    public void setPDFiTextService(PDFiTextService pDFiTextService){
        this.pDFiTextService = pDFiTextService;
    }
    @Autowired
    private SUPService sUPService;
    public  SUPService getSUPService(){
        return sUPService;
    }
    public void setSUPService(SUPService sUPService){
        this.sUPService = sUPService;
    }
    @Autowired //Anotación para la inyección de dependencias
    private CatalogoDocumentalService catalogoDocumentalService; //Variable CatalogoDocumentalService de tipo catalogoDocumentalService
    //Método que regresa la dependencia catalogoDocumentalService
    public CatalogoDocumentalService getCatalogoDocumentalService(){
        return catalogoDocumentalService; 
    }
    //Método que recibe un recibe una variable de tipo CatalogoDocumentalService
    public void setCatalogoDocumentalService(CatalogoDocumentalService catalogoDocumentalService){
        this.catalogoDocumentalService = catalogoDocumentalService;
    }
    @Autowired
    private ContratoService contratoService;
    public ContratoService getContratoService(){
        return contratoService;
    }
    public void setContratoService(ContratoService ContratoService){
        this.contratoService = contratoService;
    }
    /**
     * Controller encargado de devolver la vista del modulo de contratistas
     * @return ModelAndView
     */
    @RequestMapping(value = "contratista/contratistas.htm",method = RequestMethod.GET)
    public ModelAndView getModuloContratistas(){
        LOGGER.info("[Controller,Contratista] Se solicita la vista del módulo de contratistas");
        
        Map<String,Object> map = new HashMap<String, Object>();
        try {            
                getAllContratistas(map);
        } catch (Exception e) {            
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de crear la vista con los contratistas  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de crear la vista con los contratistas");
            map.put("muestraTabla", false);           
        }
         return new ModelAndView("contratista/contratistas", "model", map);
    }
    
    /**
     * Controller encargado de devolver la vista del para el registro de un nuevo contratista
     * @return ModelAndView
     */
    @RequestMapping(value="contratista/registro-contratista.htm",method = RequestMethod.GET)
    public ModelAndView getNuevoCliente(){
        LOGGER.info("[Controller,Contratista] Se solicita la vista para agregar un nuevo contratista");
        
         Map<String,Object> map = new HashMap<String, Object>();
        try {            
            map.put("edicion", false);
            return new ModelAndView("contratista/registroContratista", "model", map);
        } catch (Exception e) {            
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de crear la vista para registrar un nuevo contratista  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de crear la vista para el registro de un contratista");    
        }
         return new ModelAndView("contratista/contratistas", "model", map);
    }
    
    /**
     * Crontroller encargado de devolver la vista de que muestra a todos los contratistas 
     * @return ModelAndView
     */
    @RequestMapping(value="contratista/todos-los-contratistas.htm",method = RequestMethod.GET)
    public ModelAndView getTodosLosContratistas(){
        LOGGER.info("[Controller,Contratista] Se solicita la vista de todos los contratistas");
        
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            getAllContratistas(map);
        }catch(Exception e) {            
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de crear la vista con todos los contratistas  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de crear la vista con todos los contratistas");    
        }
         return new ModelAndView("contratista/contratistas", "model", map);
    }
    
    /**
     * Controller encargado de devolver todos los contratistas que cumplen con el criterio de busqueda ingresada
     * @param criterio Cadena de texto que contiene el critero por el cual se va a ejecutar la busqueda
     * @param valor Cadena de texto que contiene el valor de campo de texto de busqueda
     * @return ModelAndView
     */
    @RequestMapping(value = "contratista/buscar-por/{param}/dato/{value}.htm",method = RequestMethod.GET)
    public ModelAndView getContratistasPorFiltro(@PathVariable("param") String criterio, @PathVariable("value") String valor){
           LOGGER.info("[Controller,Contratista] Se solicita la vista de todos los contratistas que cumplan con el criterio de la búsqueda: "+criterio+", Palabra(s): "+valor);
            
            Map<String,Object> map = new HashMap<String, Object>();
            try{
                       String crtr;
                        Contratista contratistaABuscar = new Contratista();
                        switch(criterio){
                            case "Nombre":
                                contratistaABuscar.setNombreEmpresa(valor.trim());
                                crtr = "Nombre";
                            break;
                            case "Represetante":
                                contratistaABuscar.setRepresentateLegal(valor.trim());
                                crtr = "Represetante";
                            break;
                            case "RFC":
                                contratistaABuscar.setRfc(valor.trim());
                                crtr = "RFC";
                            break;
                            default:
                                LOGGER.error("[Controller,Contratista] Criterio de la búsqueda incorrecto");
                                setInformacionEnVentana(map,1, "Módulo de contratistas", "El criterio de la búsqueda es incorrecto");
                                getAllContratistas(map);
                                return new ModelAndView("contratista/contratistas", "model", map);
                        }
                        List<Contratista> contratistas = contratistaService.getContratista(contratistaABuscar);
                        if(contratistas != null){                            
                            if(contratistas.isEmpty()){
                                setInformacionEnVentana(map,2, "Módulo de contratistas", "Ningún contratista cumple con el criterio de búsqueda<br/><br/><b>"+crtr+"</b>: <b>"+valor+"</b>");
                                map.put("muestraTabla", true);
                            }else{
                                map.put("constratistasEncontrados", contratistas);
                                map.put("muestraTabla", true);
                            }
                        }
            }catch(Exception e){            
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de crear la vista con todos los contratistas que cumplan con el criterio de la búsqueda  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de crear la vista con todos los contratistas que cumplan con el criterio de la búsqueda");
            getAllContratistas(map);
        }
         return new ModelAndView("contratista/contratistas", "model", map);
    }
    
    /**
     * Controller encargado de devolver la vista para la edición de todos los datos de un contratista
     * @param idContratista Numero entero que contiene el id de un contratista Numero entero que contiene el id de un contratista
     * @return ModelAndView
     */
    @RequestMapping(value = "contratista/editar-contratista/{idContratista}.htm",method = RequestMethod.GET)
    public ModelAndView getEditarContratista(@PathVariable("idContratista") int idContratista){
           LOGGER.info("[Controller,Contratista] Se solicita la vista para la edición de un contratista ["+idContratista+"]");
            
           Map<String,Object> map = new HashMap<String, Object>();
           try{
            
                map = getInformacionContratista(idContratista);
                map.put("edicion", true);
                return new ModelAndView("contratista/registroContratista", "model", map);
                
            }catch(Exception e){           
                LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de crear la vista para la edición de un contratista  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de crear la vista para la edición de un contratista");
                getAllContratistas(map);
           }
           return new ModelAndView("contratista/contratistas", "model", map);
    }
    
    /**
     * Controller encargado de ingresar/actualizar los principales datos de un contratista
     * @param contratista Instancia de Contratista
     * @param request Variable que maneja las peticiones
     * @param response Varibale que maneja las respuestas
     * @return ModelAndView
     */
  @RequestMapping(value = "constratista/datos-contratista.htm",method = RequestMethod.POST)
  public ModelAndView setDatosContratista(@ModelAttribute("contratista") Contratista contratista, HttpServletRequest request, HttpServletResponse response){
      LOGGER.info("[Controller,Contratista] Se reciben los principales datos de un contratista Nombre: "+contratista.getNombreEmpresa());
      
      Map<String,Object> map = new HashMap<String, Object>();      
      try {
          
            String statusString = request.getParameter("statusContratista");
            boolean status;
            if(statusString != null){
                status = getEstado(statusString);
            }else{
                status = false;
            }
            String movimiento;
            contratista.setStatus(status);
            if(contratista.getIdContratista() == null){
                movimiento = "Inserción";
                contratista.setCreado( new Date() );
            }else{
                movimiento = "Actualización";
                Contratista cntr= contratistaService.getContratista(contratista.getIdContratista());
                contratista.setActaNotarialList(cntr.getActaNotarialList());
                contratista.setContratoEmpresasList(cntr.getContratoEmpresasList());
                contratista.setEmpresasDomicilioList(cntr.getEmpresasDomicilioList());
                contratista.setCreado(cntr.getCreado());
                contratista.setContratoList(cntr.getContratoList());
            }
            contratista.setModificado(new Date());
           
            Integer setContratista = contratistaService.setContratista(contratista);
           map = getInformacionContratista(setContratista);
           map.put("edicion", true);
           
            LOGGER.info("[Controller,Contratista] Se ingresaron correctamente los datos personales del contratista > "+contratista.getNombreEmpresa());
            setInformacionEnVentana(map,0, "Datos del contratista", "Los principales datos del contratista fueron ingresados correctamente");
            setBitacora(movimiento, "Ingresó correctamente los datos personales del contratista "+contratista.getNombreEmpresa().toUpperCase()+".", request);
      } catch (Exception e) {       
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de ingresar los datos del contratista -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar los datos del contratista");    
        }
         return new ModelAndView("contratista/registroContratista", "model", map);
  }
  
  /**
   * Controller encargado de ingresar/actulizar los datos del intrumento notarial de un contratista
   * @param actaNotarialPDF Archivo del actanotarial en PDF
   * @param poderLegalPDF  Archivo del poderlegal en PDF
   * @param actaNotarial Instancia de ActaNotarial
   * @param request Variable que maneja las peticiones
   * @param response Varibale que maneja las respuestas
   * @return ModelAndView
   */
  @RequestMapping(value = "contratista/instrumento-notarial.htm", method = RequestMethod.POST)
  public ModelAndView setInstrumentoNotarial(@RequestParam("actaNotarialPDF") MultipartFile actaNotarialPDF, @RequestParam("poderLegalPDF") MultipartFile poderLegalPDF,@ModelAttribute("actanotarial") ActaNotarial actaNotarial, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info("[Controller,Contratista] Se reciben los datos del instrumento notarial de un contratista");
      
      Map<String,Object> map = new HashMap<String, Object>();    
      try {
          
                String fechaString = request.getParameter("fchVolumen");
                String poderLegalString = request.getParameter("tnPoderLegal");
                Integer contratistaInt = Integer.parseInt(request.getParameter("idContratista"));
                
                Date fechaVolumen = procesayyyyMMdd(fechaString);                
               boolean tienePoderLegal = false;
               
               if(poderLegalString != null){
                   tienePoderLegal = getEstado(poderLegalString);
               }
               
                Contratista contratista = contratistaService.getContratista(contratistaInt);

                actaNotarial.setFechaVolumen(fechaVolumen);
                actaNotarial.setTienePoderLegal(tienePoderLegal);

                Integer idActaNotarial = actaNotarialService.setActaNotarial(actaNotarial);

                List<ActaNotarial> actaNotarialList = new LinkedList<>();
                actaNotarialList.add(actaNotarial);
                contratista.setActaNotarialList(actaNotarialList);
                contratista.setModificado( new Date());
                contratistaService.setContratista(contratista);
                
                if( ! actaNotarialPDF.getOriginalFilename().isEmpty() ){
                    cargarDocumento(contratista, "ActaNotarial",actaNotarial ,actaNotarialPDF);
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
                            cargarDocumento(contratista, "PoderLegal",poderLegalTemp ,poderLegalPDF);
                        }
                }else{
                         actaNotarial.setPoderLegal(null);
                         actaNotarial.setTienePoderLegal(false);
                        idActaNotarial = actaNotarialService.setActaNotarial(actaNotarial);
                }
                 if(idActaNotarial  != null){
                                 LOGGER.info("[Controller,Contratista] Se ingresaron correctamente los datos del  instrumento notarial del contratista ["+contratista.getIdContratista()+", "+contratista.getNombreEmpresa()+", "+actaNotarial.getIdActaNotarial()+"]");
                                 map = getInformacionContratista(contratistaInt);
                                 map.put("edicion", true);                            
                                 setInformacionEnVentana(map,0, "Datos del instrumento notarial ", "Los principales datos del instrumento notarial del contratista fueron ingresados correctamente");    
                                 setBitacora("Actualización", "Ingresó los principales datos del instrumento notarial del contratista "+contratista.getNombreEmpresa().toUpperCase()+" con el id del acta notarial "+actaNotarial.getIdActaNotarial()+".", request);
                 }
      }catch (ParseException ex) {
                    Logger.getLogger(ContratistaController.class.getName()).log(Level.SEVERE, "[Controller,Contratista] Ocurrio un error al momento de ingresar los datos del instrumento notarial del contratista - Error al procesar una fecha de texto a Date ");
            setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de parsear los datos del instrumento notarial del contratista");    
      }catch(NumberFormatException nfe){
                    LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de ingresar los datos del instrumento notarial del contratista - Error en el parseo de una fecha --> "+nfe);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de formatear los datos del instrumento notarial del contratista");    
      }catch(Exception e){       
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de ingresar los datos del instrumento notarial del contratista  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar los datos del instrumento notarial del contratista");    
        }
         return new ModelAndView("contratista/registroContratista", "model", map);
  }
   
  /**
   * Controller encargado de ingresar/actulizar los datos del domicilio fiscal de un contratista
   * @param ed Instancia de EmpresasDomicilio
   * @param request Variable que maneja las peticiones
   * @param response Varibale que maneja las respuestas
   * @return ModelAndView
   */
  @RequestMapping(value = "contratista/domicilio-fiscal.htm",method = RequestMethod.POST)
  public ModelAndView setDomiclioFiscal(@ModelAttribute("empresasdomicilio") EmpresasDomicilio ed, HttpServletRequest request, HttpServletResponse response){
      LOGGER.info("[Controller,Contratista] Se reciben los datos del domicilio fiscal de un contratista");
      
      Map<String,Object> map = new HashMap<String, Object>();      
     try {
                Integer contratistaInt = Integer.parseInt(request.getParameter("idContratista"));
                Contratista contratista = contratistaService.getContratista(contratistaInt);
                String movimiento = "";
                Integer setDomicilioFiscal = 0;
                List<EmpresasDomicilio> empresasDomicilioList = contratista.getEmpresasDomicilioList();
                if(!empresasDomicilioList.isEmpty()){
                    for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {
                            if(empresasDomicilio.getTipoDomicilioObj() == 1){//Domicilio Fiscal
                                ed.setTipoDomicilioObj(empresasDomicilio.getTipoDomicilioObj());
                                ed.setIdEmpresasDomicilio(empresasDomicilio.getIdEmpresasDomicilio());
                                setDomicilioFiscal = contratistaService.setDomicilio(ed);
                                movimiento = "Actualización";
                                LOGGER.info("[Controller,Contratista] edición del domicilio fiscal ")  ;
                            }
                    }
                }
                
                if(setDomicilioFiscal == 0){
                    ed.setTipoDomicilioObj((short)1);// Domicilio Fiscal
                    List<Contratista> cs = new LinkedList<>();
                    cs.add(contratista);
                    ed.setContratistaList(cs);
                    contratistaService.setDomicilio(ed);
                    empresasDomicilioList.add(ed);
                    contratista.setEmpresasDomicilioList(empresasDomicilioList);
                    movimiento = "Inserción";
                    LOGGER.info("[Controller,Contratista] nuevo domicilio fiscal ")  ;
                }
                
                contratista.setModificado( new Date());
                Integer setContratista = contratistaService.setContratista(contratista);
                
                if(setContratista != null){
                        LOGGER.info("[Controller,Contratista] Se ingresaron correctamente los datos del domicilio fiscal del contratista  ["+contratista.getIdContratista()+", "+contratista.getNombreEmpresa()+",  -> "+ed.getTipoDomicilioObj()+"]");
                            map = getInformacionContratista(contratistaInt);
                            map.put("edicion", true);
                            setInformacionEnVentana(map,0, "Datos del domicilio fiscal", "Los principales datos del domicilio fiscal del contratista fueron ingresados correctamente");
                            setBitacora(movimiento, "Ingresó el domicilio fiscal del contratista  "+contratista.getNombreEmpresa().toUpperCase()+" con el id "+ed.getIdEmpresasDomicilio()+".", request);
                }
      }catch(NumberFormatException nfe){
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de ingresar los datos del domicilio fiscal  del contratista - Error en el parseo de una fecha --> "+nfe);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de formatear los datos del domicilio fiscal  del contratista");    
      }catch(Exception e){       
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de ingresar los datos del domicilio fiscal del contratista  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar los datos del domicilio fiscal del contratista");    
        }
         return new ModelAndView("contratista/registroContratista", "model", map);
  }
  
  /**
   * Controller encargado de ingresar/actualizar los datos del domicilio de notificación de un contratista
   * @param ed Instancia de EmpresaDomicilio
   * @param request Variable que maneja las peticiones
   * @param response Varibale que maneja las respuestas
   * @return ModelAndView
   */
  @RequestMapping(value = "contratista/domicilio-de-notificacion.htm",method = RequestMethod.POST)
  public ModelAndView setDomiclioNotificacion(@ModelAttribute("empresasdomicilio") EmpresasDomicilio ed, HttpServletRequest request, HttpServletResponse response){
      LOGGER.info("[Controller,Contratista] Se reciben los datos del domicilio de notificación de un contratista");
      
      Map<String,Object> map = new HashMap<String, Object>();      
     try {
                Integer contratistaInt = Integer.parseInt(request.getParameter("idContratista"));
                Contratista contratista = contratistaService.getContratista(contratistaInt);
                String movimiento ="";
                Integer setDomicilioFiscal = 0;
                List<EmpresasDomicilio> empresasDomicilioList = contratista.getEmpresasDomicilioList();
                if(!empresasDomicilioList.isEmpty()){
                    for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {
                            if(empresasDomicilio.getTipoDomicilioObj() == 2){//Domicilio de notificación
                                ed.setTipoDomicilioObj(empresasDomicilio.getTipoDomicilioObj());
                                ed.setIdEmpresasDomicilio(empresasDomicilio.getIdEmpresasDomicilio());
                                setDomicilioFiscal = contratistaService.setDomicilio(ed);
                                movimiento = "Actualización";
                                LOGGER.info("[Controller,Contratista] edición del domicilio de notificación ")  ;
                            }
                    }
                }
                
                if(setDomicilioFiscal == 0){
                    ed.setTipoDomicilioObj((short)2);// Domicilio de notificación
                    List<Contratista> cs = new LinkedList<>();
                    cs.add(contratista);
                    ed.setContratistaList(cs);
                    contratistaService.setDomicilio(ed);
                    empresasDomicilioList.add(ed);
                    contratista.setEmpresasDomicilioList(empresasDomicilioList);
                    movimiento = "Inserción";
                    LOGGER.info("[Controller,Contratista] nuevo domicilio de notificación ")  ;
                }
                
                contratista.setModificado( new Date());
                Integer setContratista = contratistaService.setContratista(contratista);
                
                if(setContratista != null){
                        LOGGER.info("[Controller,Contratista] Se ingresaron correctamente los datos del domicilio de notificación del contratista   ["+contratista.getIdContratista()+", "+contratista.getNombreEmpresa()+",  -> "+ed.getTipoDomicilioObj()+"]");
                        map = getInformacionContratista(contratistaInt);
                        map.put("edicion", true); 
                        setInformacionEnVentana(map,0, "Datos del domicilio de notificación", "Los principales datos del domicilio de notificación del contratista fueron ingresados correctamente");          
                        setBitacora(movimiento, "Ingresó el domicilio de notificación del contratista "+contratista.getNombreEmpresa().toUpperCase()+" con el id "+ed.getIdEmpresasDomicilio()+".", request);
                }
      }catch(NumberFormatException nfe){
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de ingresar los datos del domicilio de notificación del contratista - Error en el parseo de una fecha --> "+nfe);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de formatear los datos del domicilio de notificación del contratista");    
      }catch(Exception e){       
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de ingresar los datos del domicilio de notificación del contratista  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de ingresar los datos del domicilio de notificación del contratista");    
        }
         return new ModelAndView("contratista/registroContratista", "model", map);
  }
  
    /**
     * Controller encargado de mostrar el kardex de un contratista 
     * @param idContratista Numero entero que contiene el id de un contratista
     * @return ModelAndView
     */
    @RequestMapping(value = "contratista/{idContratista}/kardex.htm",method = RequestMethod.GET)
    public ModelAndView gerKardexConstratista(@PathVariable("idContratista") int idContratista){
        LOGGER.info("[Controller,Contratista] Se solicita la vista del kardex de un contratista ["+idContratista+"]");
        
      Map<String,Object> map = new HashMap<String, Object>();     
     try{
            map = getInformacionContratista(idContratista);
         return new ModelAndView("contratista/kardexDelContratista", "model", map);
        }catch(Exception e){       
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de generar el kardex del contratista  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de generar el kardex del contratista");
            getAllContratistas(map);
        }
         return new ModelAndView("contratista/contratistas", "model", map);
    }
    
    /**
     *  Controller encargado de devolver el kardex de un contratista en pdf
     * @param idContratista Numero entero que contiene el id de un contratista
     * @param request Variable que maneja las peticiones
     * @param response Varibale que maneja las respuestas 
     */
    @RequestMapping(value = "contratista/{idContratista}/kardex/pdf.htm", method = RequestMethod.GET)
    public void getPDFDelKardex(@PathVariable("idContratista") Integer idContratista,HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Contratista] Se solicita la vista del kardex de un Contratista en pdf.");     
        try{            
            Contratista contratista = contratistaService.getContratista(idContratista);
            if(contratista != null){
                ByteArrayOutputStream kardex = pDFiTextService.getKardex(contratista);// setting some response headers
                response.setHeader("Content-Disposition", "inline;filename=\"kardex del contratista "+contratista.getNombreEmpresa().toUpperCase()+".pdf\"");
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
                throw new Exception("Contratista no encontrado para la generación de la vista en pdf");
            }
        }catch(Exception e){
            LOGGER.error("[Controller,Contratista]  Ocurrio un error al momento de generar el kardex en pdf del Contratista  -> "+e);
        }        
    }
    
    /**
     * Controller encargado de devolver el documento solicitado de un Contratista
     * @param idContratista Numero entero que contiene el id de un contratista
     * @param documento Campo de texto que indica el nombre del documento que se esta solicitando
     * @param request Variable que maneja las peticiones
     * @param response Varibale que maneja las respuestas 
     */
    @RequestMapping(value="contratista/{idContratista}/{documento}.htm",method = RequestMethod.GET)
    public void verDocumento(@PathVariable("idContratista") Integer idContratista, @PathVariable("documento") String documento,  HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Contratista] Se solicita la vista del documento legal ["+documento+"] del contratista ["+idContratista+"]");
        Contratista contratista = contratistaService.getContratista(idContratista);
        if(contratista != null){
            try {
                if( documento.equalsIgnoreCase("ActaNotarial") ){
                    ActaNotarial an = contratista.getActaNotarialList().get(0);
                    if(an != null ){
                        File descargarFileFTP = ftpService.descargarFileFTP(an.getUrlDocumento());
                        InputStream is = new FileInputStream(descargarFileFTP);
                        response.setHeader("Content-Disposition", "inline;filename=\"" + documento+ ".pdf\"");
                        OutputStream sos = response.getOutputStream();
                        response.setContentType("application/pdf");
                        IOUtils.copy(is, sos);
                        sos.flush();
                    }else{
                        LOGGER.error("[Controller, Contratista] No se encontró el acta notarial del Contratista "+contratista.getNombreEmpresa());
                    }
                }else if( documento.equalsIgnoreCase("PoderLegal") ){
                    ActaNotarial an = contratista.getActaNotarialList().get(0);
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
                            LOGGER.error("[Controller, Contratista] No se encontró el poder legal del Contratista "+contratista.getNombreEmpresa());
                        }
                    }else{
                        LOGGER.error("[controller, Contratista] Se esta solicitando el poder legal del Contratista "+contratista.getNombreEmpresa()+" pero no cuenta con dicho documento ["+an.getTienePoderLegal()+"]");
                    }                
                }else{
                    LOGGER.error("[Controller, Contratista] No existe ningún documento legal ["+documento+"] correspondiente para el Contratista ["+contratista.getNombreEmpresa()+"]");
                }
            } catch (FileNotFoundException fnfe) {
                Logger.getLogger(SinidicatoController.class.getName()).log(Level.SEVERE, "[Controller,Contratista,fnfe] El archivo no puede ser generado para su visualización ", fnfe);
            } catch (IOException ioe) {
                Logger.getLogger(SinidicatoController.class.getName()).log(Level.SEVERE, "[Controller,Contratista,ioe] El archivo no puede ser generado para su visualización ", ioe);
            }
        }else{
            LOGGER.error("[Controller, Contratista] No se encuentró ningún Contratista ["+idContratista+"]");
        }
    }
    
    /**
     * Controller encargado de presentar la vista para generar un nuevo contrato entre un contratista y un cliente
     * @param idContratista Numero entero que contiene el id de un contratista
     * @return ModelAndView
     */
    @RequestMapping(value = "contratista/generar-nuevo-contrato/{idContratista}.htm",method = RequestMethod.GET)
    public ModelAndView getGenerarNuevoContrato(@PathVariable("idContratista")  Integer idContratista){
        LOGGER.info("[Controller,Contratista] Se solicita la vista para la generación de un nuevo contrato entre empresas [c"+idContratista+"]");
         
      Map<String,Object> map = new HashMap<String, Object>();     
      map.put("edicion", false);     
        try{
            
            Contratista contratista = contratistaService.getContratista(idContratista);
            
            if(contratista.getStatus()){
                map.put("contratista", contratista);
            
                List<Cliente> clientes = clienteService.getClientes();
                clientes.sort( (Cliente c1, Cliente c2) -> c1.getNombreEmpresa().toUpperCase().compareTo( c2.getNombreEmpresa().toUpperCase() ) );
                map.put("clientes",clientes);
                map.put("edicion", false);
                map.put("spus", sUPService.getSalariosUnicosProfesionales());
                //le asigna el valor del método getCatalagoDocumentalDeServicio() a la clave servicios
                map.put("servicios", catalogoDocumentalService.getCatalagoDocumentalDeServicio());
                // se obtiene objeto de tipo Contratista 
                Contratista contratistaObj = contratistaService.getContratista(idContratista);
                // Se obtiene una lista de tipo Contrato mediante el objeto Contratista
                List<Contrato> contratosDelContratista = contratistaObj.getContratoList();
                // Se declará una lista de tipo Contrato
                List<Contrato> contratos = new ArrayList<>(contratosDelContratista.size());
                // Se recorre la lista obtenida y se agrega a la lista previamente declarada
                contratosDelContratista.stream().filter((contrato) -> (contrato.getContratoDeColaborador())).forEachOrdered((contrato) -> {
                contratos.add(contrato);
                //Se manda el valor a la clave "contratos" 
                map.put("contratos", contratos);
            });
                return new ModelAndView("contratista/generarContrato", "model", map);
            }else{
                LOGGER.info("[Controller,Contratista] El contratista "+contratista.getNombreEmpresa()+" se encuentra desactivado");
                setInformacionEnVentana(map,2, "Módulo de contratistas", "UPS! <br/>El contratista <b>"+contratista.getNombreEmpresa()+"</b> se encuentra desactivado");                
                getAllContratistas(map);
            }                          
        }catch(Exception e){       
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de generar la vista para un nuevo contrato  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de contratistas", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de generar la vista para un nuevo contrato");
            getAllContratistas(map);
        }
         return new ModelAndView("contratista/contratistas", "model", map);
    }
    
    /**
     * Controller encargado de recibir y persistir los datos de un contrato entre un contratista y un cliente
     * @param contratoEntreEmpresas Instancia de ContratoEntreEmpresas
     * @param request Variable que maneja las peticiones
     * @param response Varibale que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "contratista/contrato.htm", method = RequestMethod.POST)
    public ModelAndView setContrato(@ModelAttribute("contratoempresas") ContratoEmpresas contratoEntreEmpresas, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Contratista] Se reciben los datos de un contrato entre un contratista y un cliente");
        Map<String,Object> map = new HashMap<String, Object>();
        try {
                Integer idContratista = Integer.parseInt(request.getParameter("idContratista"));
                Integer idCliente = Integer.parseInt(request.getParameter("idCliente"));
                String fechaString = request.getParameter("fechaDelContrato");
                LOGGER.info("[Controller,Contratista] Se ingresan los datos correspondientes a un contrato entre las empresas [c"+idContratista+", c"+idCliente+", date"+fechaString+"]");
               Date fecha = procesayyyyMMdd(fechaString);
               contratoEntreEmpresas.setFecha(fecha);

            Contratista contratista = contratistaService.getContratista(idContratista);
            Cliente cliente = clienteService.getCliente(idCliente);
             
            if(contratoEntreEmpresas.getIdContratoEmpresas() == null){

                if((contratista!=null) && (cliente!=null)){
                      contratoEntreEmpresas.setCreado( new Date() );
                }else{
                      LOGGER.error("[Controller,Contratista] No se obtuvo la información necesaria para ingresar el nuevo contrato ");
                    return new ModelAndView("contratista/contratistas");
                }
            }else{
                ContratoEmpresas contratoEmpresas = contratoEmpresasService.getContratoEmpresas(contratoEntreEmpresas.getIdContratoEmpresas());
                contratoEntreEmpresas.setCreado(contratoEmpresas.getCreado());
            }

            contratoEntreEmpresas.setModificado( new Date() );             
           
            contratoEntreEmpresas.setContratistaObj(contratista.getIdContratista());
            contratoEntreEmpresas.setClienteObj(cliente.getIdCliente());
            String sUPs[] = request.getParameterValues("destinoSPU");
                List<SalarioUnicoProfesional> salarioUnicoProfesionals = new ArrayList<>();
                    if(sUPs!=null&&sUPs.length>0){
                        for (String sUP : sUPs) {
                         try {
                                SalarioUnicoProfesional salarioUnicoProfesional = sUPService.getSalarioUnicoProfesional(Integer.parseInt(sUP));
                                salarioUnicoProfesionals.add(salarioUnicoProfesional);
                            } catch (NumberFormatException nfe ){
                                LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de convertir unos de los SUP ("+sUP+") del JSP al controller se omitirá  --> "+nfe,nfe);
                            }
                        }
                    }         
           contratoEntreEmpresas.setSalarioUnicoProfesionalList(salarioUnicoProfesionals);
          //Arreglo de string que guarda los datos del id de la vista destinoServicios
          String catalogoDeServicios[] = request.getParameterValues("destinoServicios");
           List<CatalogoDocumental> catalogosDocumentales = new ArrayList<>(); //Se declara una arreglo de tipo CatalogoDocumental
                //Valida que de lo que se esta recibiendo de parametro sea diferente a null y que su longitud sea mayor a 0
                if(catalogoDeServicios!=null&&catalogoDeServicios.length>0){
                    //Recorre cada elemento de catalogoDeServicios
                    for (String catalogoDocumental : catalogoDeServicios) {
                        try {
                            //Se le asigna a la variable catDocumental de tipo CatalogoDocumental el valor del método getCatalogoDocumental 
                            CatalogoDocumental catDocumental = catalogoDocumentalService.getCatalogoDocumental(Integer.parseInt(catalogoDocumental));
                            //Se agrega a al arreglo catalogosDocumentales el valor de catDocumental
                            catalogosDocumentales.add(catDocumental);
                        } catch (NumberFormatException nfe ){ //Si ocurre algun error en el proceso se cacha la excepción y se le asigna un nombre de variable
                            //Se imprime el error un los logs con un mensaje el cual podamos identificar anexandole la variable catalogoDocumental + el mensaje del error + el error
                            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de convertir uno de los Catálogos Documentales '"+catalogoDocumental+"' --> "+nfe.getMessage(),nfe);
                        } //Fin del catch
                    }//Fin del for
                }//Fin del if
            //Se manda el valor de catalogosDocumentales al método setCatalogoDocumentalList del pojoh ContratoEmpresas 
            contratoEntreEmpresas.setCatalogoDocumentalList(catalogosDocumentales);
            //Se obtiene los destinos de los contratos
            String contratosAsignados[] = request.getParameterValues("destinoContratos");
            // se declara objeto de tipo Contrato
                List<Contrato> contratos = new ArrayList<>();
                    //Valida si la lista es diferente de nullo o es mayor a 0
                    if(contratosAsignados!=null&&contratosAsignados.length>0){
                        //Recorre la lista de contratoAsignado
                        for (String contratosAsignado : contratosAsignados) {
                         try {
                             // Se obtiene el contrato y se agrega a la una nueva variable declarada
                             Contrato contrato = contratoService.getContrato(Integer.parseInt(contratosAsignado));
                                contratos.add(contrato);
                            } catch (NumberFormatException nfe ){
                                LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de convertir unos de los contratos ("+contratos+") del JSP al controller se omitirá  --> "+nfe,nfe);
                            }
                        }
                    }  
           // Se settean los valores encontrados a contratoEntreEmpresas
           contratoEntreEmpresas.setContratosList(contratos);
            contratoEmpresasService.setContratoEmpresas(contratoEntreEmpresas);
            if(contratoEntreEmpresas.getNombreContrato()==null || "".equals(contratoEntreEmpresas.getNombreContrato())){
                setNombreDeContrato(contratoEntreEmpresas);
            }
            contratoEmpresasService.setContratoEmpresas(contratoEntreEmpresas);
            LOGGER.info("[Controller,Contratista] Se ingresaron correctamente los datos correspondientes a un contrato entre las empresas "+contratista.getNombreEmpresa()+" y "+cliente.getNombreEmpresa());
            
            if(contratoEntreEmpresas.getIdContratoEmpresas() != null){
                setInformacionEnVentana(map,0, "Generación de un nuevo contrato", "Se ingresaron correctamente los datos correspondientes a un contrato entre las empresas <b>"+contratista.getNombreEmpresa()+"</b> "
                    + "y <b>"+cliente.getNombreEmpresa()+"</b>"
                    + "<br>Con el nombre de contrato: <b>"+contratoEntreEmpresas.getNombreContrato()+"</b>"
                    + "<br><a target='_blank' href='../sistema/contrato-contratista-cliente/"+contratoEntreEmpresas.getIdContratoEmpresas()+"/pdf.htm'>Descargar</a>");
                    setBitacora("Inserción", "Ingresó los datos entre el contratista "+contratista.getNombreEmpresa().toUpperCase()+" y el cliente "+cliente.getNombreEmpresa().toUpperCase()+".", request);
            }else{
                setInformacionEnVentana(map, 1, "Generación de un nuevo contrato", "No fue posible almacenar el contrato!");
            }
                
        } catch (NumberFormatException nfe) {
                    LOGGER.error("[Controller,Constratista] Ocurrio un error al momento de intentar convertir una cadena en entero --> "+nfe);                                   
                    setInformacionEnVentana(map,1, "Generación de un nuevo contrato", "Ocurrio un error al intentar generar un nuevo contrato<br/> por favor revisa los datos ingresados");
        }catch (ParseException pe) {
                    LOGGER.error("[Controller,Constratista] Ocurrio un error al momento de intentar convertir una cadena en una fecha --> "+pe);                      
                    setInformacionEnVentana(map,1, "Generación de un nuevo contrato", "Ocurrio un error al intentar generar un nuevo contrato<br/> por favor revisa los datos ingresados");
        }catch (Exception e) {
                      LOGGER.error("[Controller,Constratista] Ocurrio un error al momento de generar la vista   -> "+e);                       
                    setInformacionEnVentana(map,1, "Generación de un nuevo contrato", "Ocurrio un error al intentar generar un nuevo contrato<br/> por favor revisa los datos ingresados");
        }
        getAllContratistas(map);
        return new ModelAndView("contratista/contratistas", "model", map);
    }
    
    /**
     * Controller de respuesta de peteciones get sobre url´s post
     * @return ModelAndView
     */
    @RequestMapping(value = { "contratista/contrato.htm","contratista/domicilio-de-notificacion.htm","contratista/domicilio-fiscal.htm","contratista/instrumento-notarial.htm","constratista/datos-contratista.htm"}, method = RequestMethod.GET)
        public ModelAndView vistasSoloConAccesoPost(){
        LOGGER.info("[Controller,Contratista] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando... ");

            Map<String,Object> map = new HashMap<String, Object>();
            try {            
                    getAllContratistas(map);
            } catch (Exception e) {            
                LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de crear la vista con los contratistas  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de contratistas", "Ocurrio un error al momento de crear la vista con los contratistas");
                map.put("muestraTabla", false);           
            }
         return new ModelAndView("contratista/contratistas", "model", map);
    }
    
     /**
     * Metodo encargado de obtener toda la información relacionada con  un contratista
     * @param id Id del contratista
     * @return  Map String,Object
     */
    private Map<String,Object> getInformacionContratista(Integer id){
         Map<String,Object> map = new HashMap<String, Object>();
            
            Contratista contratista = contratistaService.getContratista(id);
            if(contratista != null){
                map.put("contratista",contratista);
            }
            List<ActaNotarial> actaNotariaList = contratista.getActaNotarialList();
            if(actaNotariaList != null ){
                if(!actaNotariaList.isEmpty()){
                    ActaNotarial actaNotarial = actaNotariaList.get(0);
                    if(actaNotarial!=null){
                        map.put("actaNotarial",actaNotarial);
                        if(actaNotarial.getTienePoderLegal()){
                            PoderLegal poderLegal = actaNotarial.getPoderLegal();
                            if(poderLegal != null){
                                map.put("poderLegal", poderLegal);
                            }
                        }
                    }
                }
            }
            
            List<EmpresasDomicilio> empresasDomicilioList = contratista.getEmpresasDomicilioList();
            if(empresasDomicilioList!=null){
                for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {
                        switch(empresasDomicilio.getTipoDomicilioObj() ){
                            //Domicilio Fiscal
                            case 1:
                                map.put("domicilioFiscal", empresasDomicilio);
                            break;
                            //Domicilio de Notificación
                            case 2:
                                map.put("domicilioNotificaciones", empresasDomicilio);
                            break;
                            default:
                                LOGGER.error("[Controller,Contratista] el tipo de domicilio [ -> "+empresasDomicilio.getTipoDomicilioObj()+"] no se reconoce ");
                        } 
                }
            }
            
            List<ContratoEmpresas> contratoEmpresasList = contratista.getContratoEmpresasList();
            if(contratoEmpresasList != null){
                map.put("contratoEmpresas", contratoEmpresasList);
                Map<Integer,String> clientesMap = new HashMap<>();
                contratoEmpresasList.stream().map((contratoEmpresas) -> clienteService.getCliente(contratoEmpresas.getClienteObj())).forEach((cliente) -> {
                    clientesMap.put(cliente.getIdCliente(), cliente.getNombreEmpresa());
             });
             map.put("clientes", clientesMap);
            }
            
            List<TipoActa> tipoActa = actaNotarialService.getTipoActa();            
            map.put("tiposDeActa", tipoActa);
            return map;
    }
    private Date procesayyyyMMdd(String fechaString) throws ParseException {        
        String yyyyMMdd = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        return formatter.parse(fechaString);
    }
    private boolean getEstado(String statusString) {
        return statusString.equalsIgnoreCase("Sí");
    }
    private void getAllContratistas(Map<String,Object> map){
        if(map!=null){
            List<Contratista> contratistas = contratistaService.getContratistas();
            if(contratistas != null){
                map.put("constratistasEncontrados", contratistas);
                map.put("muestraTabla", true);
            }
        }
    }    
    private boolean cargarDocumento(Contratista contratista, String doc, Object obj, MultipartFile archivoPDF){
            LOGGER.info("[Controller,Contratista] Se intenta cargar el documento ["+doc+"] del Contratista "+contratista.getNombreEmpresa());
           try {
                        
                    if(obj instanceof ActaNotarial){
                        ActaNotarial an = (ActaNotarial) obj;
                        if( an.getUrlDocumento() != null ){
                            String ruta = an.getUrlDocumento();
                            String fileString= (ruta.length() > 27 )?"..."+ruta.substring((ruta.length()-27)):"...";
                            LOGGER.info("[Controller,Contratista] El Contratista ya cuenta con un documento  ["+fileString+", "+doc+"] se procede a su eliminación");
                            ftpService.borrarArchivoFTP( ruta );
                        }
                        String direccionArchivoFTP = ftpService.guardarArchivoFTP(archivoPDF, "Contratistas", contratista.getIdContratista().toString(), "documento_"+doc);
                        an.setUrlDocumento(direccionArchivoFTP);
                        LOGGER.info("[Controller, Contratista] Se intentan registrar el documento ["+doc+"] del Contratista "+contratista.getNombreEmpresa()+" dentro de la base de datos");
                        Integer setActaNotarial = actaNotarialService.setActaNotarial( an );
                        return setActaNotarial != null;
                    }else if(obj instanceof PoderLegal){
                        PoderLegal pl = (PoderLegal) obj;
                        if(  pl.getUrlDocumento() != null ){
                            String ruta = pl.getUrlDocumento();
                            String fileString= (ruta.length() > 27 )?"..."+ruta.substring((ruta.length()-27)):"...";
                            LOGGER.info("[Controller, Contratista] El Contratista ya cuenta con un documento ["+fileString+", "+doc+"] se procede a su eliminación");
                            ftpService.borrarArchivoFTP( ruta );
                        }
                        String direccionArchivoFTP = ftpService.guardarArchivoFTP(archivoPDF, "Contratistas", contratista.getIdContratista().toString(), "documento_"+doc);
                        pl.setUrlDocumento(direccionArchivoFTP);
                        LOGGER.info("[Controller, Contratista] Se intentan registrar el documento ["+doc+"] del Contratista "+contratista.getNombreEmpresa()+" dentro de la base de datos");
                        actaNotarialService.setActaNotarial(pl);
                        return direccionArchivoFTP.equalsIgnoreCase( pl.getUrlDocumento() );
                    }else{
                        LOGGER.error("[Controller, Contratista] Se recibe un objeto que no corresponde a ningún tipo de documento legal para el Contratista "+contratista.getNombreEmpresa());
                        return false;
                    }
        } catch (Exception e) {
            LOGGER.error("[Controller,Contratista] Ocurrio un error al momento de intentar almacenar el documento ["+doc+"] del Contratista  -> "+e);
            return false;
        }
    }
    private void setInformacionEnVentana(Map<String,Object> map,int estado, String titulo, String descripcion){
        if(map!=null){
                map.put("mostrarVentana", true);
                map.put("tipoVentana", estado); 
                map.put("tituloVentana", titulo);
                map.put("descripcionVentana", descripcion);
        }
    }
    private void setBitacora(String movimiento, String detalles,HttpServletRequest request){
       String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(correo);
        if(usuario!=null){
            Bitacora bitacora = new Bitacora(new Date(), usuario.getIdUsuario());
            bitacora.setModulo("Contratistas");
            bitacora.setMovimiento(movimiento);
            bitacora.setDetalles(detalles);
            bitacora.setIpAcceso(request.getRemoteAddr());
            bitacoraService.setBitacora(bitacora);
        }else{
            LOGGER.error("[Controller, Contratista] Bitacora.- Se busco al usuario {"+correo+"} pero no se encontró registro alguno.");
        }
   }
    @Override
    public String toString() {
        return "ContratistaController{Controlador Del Contratista, Versión 1.0}";
    }
    private void setNombreDeContrato(ContratoEmpresas contratoEntreEmpresas) {
            String nombreEmpresaCliente = clienteService.getCliente(contratoEntreEmpresas.getClienteObj()).getNombreEmpresa();
            String nombreEmpresaContratista = contratistaService.getContratista(contratoEntreEmpresas.getContratistaObj()).getNombreEmpresa();
            nombreEmpresaContratista = nombreCorto(nombreEmpresaContratista);
            nombreEmpresaCliente = nombreCorto(nombreEmpresaCliente);
            String nombreContrato = "SVC-"+nombreEmpresaContratista+"-"+nombreEmpresaCliente+"-"+contratoEntreEmpresas.getIdContratoEmpresas().toString()+"";
            contratoEntreEmpresas.setNombreContrato(nombreContrato);
        }
    private void setNombreDelPeriodo(ContratoEmpresas contratoEntreEmpresas) { //PENDIENTE
            String nombreEmpresaCliente = clienteService.getCliente(contratoEntreEmpresas.getClienteObj()).getNombreEmpresa();
            String nombreEmpresaContratista = contratistaService.getContratista(contratoEntreEmpresas.getContratistaObj()).getNombreEmpresa();
            nombreEmpresaContratista = nombreCorto(nombreEmpresaContratista);
            nombreEmpresaCliente = nombreCorto(nombreEmpresaCliente);
            String nombreContrato = "SVC-"+nombreEmpresaContratista+"-"+nombreEmpresaCliente+"-"+contratoEntreEmpresas.getIdContratoEmpresas().toString()+"";
            contratoEntreEmpresas.setNombreContrato(nombreContrato);
        }
        private String nombreCorto(String nombre){
            String nickname = "";
            if(nombre.length()>12){
                int i = 0;
                do{
                    char charAt = nombre.charAt(i++);
                    if(noVocal(charAt)&&noEspecial(charAt)){
                        nickname += ""+charAt;
                    }
                }while (nickname.length()<12&&i<nombre.length());
            }else{
                nickname = nombre;
            }
            return nickname;
        }
        private boolean noVocal(char charAt) {
           return (charAt != 'A') &&(charAt != 'E') &&(charAt != 'I') &&(charAt != 'O') &&(charAt != 'U') &&
                   (charAt != 'a') &&(charAt != 'e') &&(charAt != 'i') &&(charAt != 'o') &&(charAt != 'u') &&
                   (charAt != 'Á') &&(charAt != 'É') &&(charAt != 'Í') &&(charAt != 'Ó') &&(charAt != 'Ú') &&
                   (charAt != 'á') &&(charAt != 'é') &&(charAt != 'í') &&(charAt != 'ó') &&(charAt != 'ú') ;
        }    

    private boolean noEspecial(char charAt) {
       return (charAt != ' ') &&(charAt != '-') &&(charAt != '_') &&(charAt != '.') &&(charAt != ',')&&(charAt != '$')&&(charAt != '/')&&(charAt != '\\');
    }
}
