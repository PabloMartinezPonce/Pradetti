/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.controller;

import com.pradettisanti.payroll.enums.ContratistaTemplate;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Bitacora;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.BitacoraService;
import com.pradettisanti.payroll.service.DocumentosYServiciosService;
import com.pradettisanti.payroll.service.FtpService;
import com.pradettisanti.payroll.service.PDFiTextService;
import com.pradettisanti.payroll.service.SindicatoService;
import com.pradettisanti.payroll.service.UsuarioService;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * Manager encargado de contener los controllers de la vista de sindicato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@SuppressWarnings("Convert2Diamond")
public class SinidicatoController {
        
    /**
    * Constante para el uso de los logs del sistema
    */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SinidicatoController.class);    
    
    @Autowired
    private SindicatoService sindicatoService;
    public SindicatoService getSindicatoService() {
        return sindicatoService;
    }
    public void setSindicatoService(SindicatoService sindicatoService) {
        this.sindicatoService = sindicatoService;
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
    private UsuarioService usuarioService;
    public UsuarioService getUsuarioService(){
        return usuarioService;
    }
    public void setUsuarioService( UsuarioService usuarioService ){
        this.usuarioService = usuarioService;
    }
    
    @Autowired
    private BitacoraService bitacoraService;
    public BitacoraService getBitacoraService(){
        return  bitacoraService;
    }
    public void setBitacoraService(BitacoraService bitacoraService){
        this.bitacoraService =  bitacoraService;
    }
    
    
    @Autowired
    private PDFiTextService pDFiTextService;
    public PDFiTextService getPDFiTextService(){
        return pDFiTextService;
    }
    public void setPDFiTextService(PDFiTextService pDFiTextService){
        this.pDFiTextService = pDFiTextService;
    }
    
    // >===================================== QUITAR ==========================================<
    @Autowired
    private AgremiadoService agremiadoService;
    public AgremiadoService getAgremiadoService(){
        return agremiadoService;
    }
    public void setAgremiadoService(AgremiadoService agremiadoService){
        this.agremiadoService = agremiadoService;
    }    
    
    /**
     * Controller encargado de devolver la vista con todos los sindicatos existentes dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value = "sindicato/sindicatos.htm", method = RequestMethod.GET)
    public ModelAndView getModuloSindicatos(){
        LOGGER.info("[Controller, Sindicato] Se solicita la vista del módulo de sindicatos");
        
       Map<String,Object> map = new HashMap<String,Object>();
        try {
                getAllSidicatos(map);
        }  catch (Exception e) {            
                LOGGER.error("[Controller,Sinidicatos] Ocurrio un error al momento de crear la vista con los sindicatos  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de sindicatos", "Ocurrio un error al momento de crear la vista con los sindicatos");
                map.put("muestraTabla", true);           
        }
         return new ModelAndView("sindicato/sindicatos", "model", map);
    }
    
    /**
     * Controller encargado de devolver la vista para registrar un sindicato
     * @return ModelAndView
     */
    @RequestMapping(value = "sindicato/registro-sindicato.htm", method = RequestMethod.GET)
    public ModelAndView getNuevoSindicato(){
        LOGGER.info("[Controller, Sindicato] Se solicita la vista para agregar un nuevo sindicato");
        
       Map<String,Object> map = new HashMap<String,Object>();
        try {
            map.put("edicion", false);
            return new ModelAndView("sindicato/registroSindicato", "model", map);
        } catch (Exception e) {            
            LOGGER.error("[Controller,Sindicato] Ocurrio un error al momento de crear la vista para registrar un nuevo sindicato  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de sindicatos", "Ocurrio un error al momento de crear la vista para el registro de un sindicato");    
        }
         return new ModelAndView("sindicato/sindicatos", "model", map);
        
    }
    
    /**
     * Controller encargado de devolver la vista con todos los sindicatos existentes de todos los sindicatos
     * @return ModelAndView
     */
    @RequestMapping(value = "sindicato/todos-los-sindicatos.htm",method = RequestMethod.GET)
    public ModelAndView getTodosLosSindicatos(){
        LOGGER.info("[Controller, Sindicato] Se solicita la vista de todos los sindicatos");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            getAllSidicatos(map);
        }  catch (Exception e) {            
                LOGGER.error("[Controller,Sindicatos] Ocurrio un error al momento de crear la vista con los sindicatos  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de sindicatos", "Ocurrio un error al momento de crear la vista con los sindicatos");
                map.put("muestraTabla", true);           
        }
         return new ModelAndView("sindicato/sindicatos", "model", map);
    }
    
    /**
     * Controller encargado de devolver la vista de los sindicatos encontrados con los parametros enviados
     * @param criterio Cadena de texto que contiene el criterio por el cual se va a realizar la busqueda
     * @param valor Cadena de texto que contiene el valor del campo de texto de busqueda
     * @return ModelAndView
     */
    @RequestMapping(value = "sindicato/buscar-por/{param}/dato/{value}.htm",method = RequestMethod.GET)
    public ModelAndView getSindicatosPorFiltro(@PathVariable("param") String criterio,  @PathVariable("value") String valor){
        LOGGER.info("[Controller, Sindicato] Se solicita la vista de todos los sindicatos que cumplan con el criterio de la búsqueda\nCriterio: "+criterio+", Palabra(s): "+valor);
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String crtr;
            Sindicato sindicatoABuscar = new Sindicato();
            switch(criterio){
                case "Nombre":
                    sindicatoABuscar.setNombreSindicato(valor.trim());
                    crtr = "Nombre del sindicato";
                break;
                case "Acronimo":
                    sindicatoABuscar.setNombreCorto(valor.trim());
                    crtr = "Acrónimo";
                break;
                case "RFC":
                    sindicatoABuscar.setRfc(valor.trim());
                    crtr = "RFC";
                break;
                default:
                    LOGGER.error("[Controller,Sindicato] Cirterio de búsqueda incorrecto");
                    setInformacionEnVentana(map,1, "Módulo de sindicatos", "El criterio de la búsqueda es incorrecto");
                    getAllSidicatos(map);
                    return new ModelAndView("sindicato/sindicatos", "model", map);
            }
            List<Sindicato> sindicatos = sindicatoService.getSindicatos(sindicatoABuscar);
            if(sindicatos.isEmpty()){
                setInformacionEnVentana(map,2, "Módulo de sindicatos", "Ningún sindicato cumple con en criterio de búsqueda<br/><br/><b>"+crtr+"</b>: <b>"+valor+"</b>");
                map.put("muestraTabla", true);
            }else{
            map.put("sindicatosEncontrados", sindicatos);
            map.put("muestraTabla", true);
            }
        } catch(Exception e){            
            LOGGER.error("[Controller,Sindicato] Ocurrio un error al momento de crear la vista con todos los sindicatos que cumplan con el criterio de la búsqueda  -> "+e);
            setInformacionEnVentana(map,1, "Módulo de sindicatos", "Ocurrio un error al momento de crear la vista con todos los sindicatos que cumplan con el criterio de la búsqueda");
            getAllSidicatos(map);
        }
        return new ModelAndView("sindicato/sindicatos", "model", map);
        
    }

    /**
     * Controller encargado de mostrar la vista para editar un sindicato en especifico
     * @param idSindicato Numero entero que contiene el id de un sindicato
     * @return ModelAndView
     */
    @RequestMapping(value = "sindicato/editar-sindicato/{idSindicato}.htm",method = RequestMethod.GET)
    public ModelAndView getEditarSindicatos(@PathVariable("idSindicato") int idSindicato){
        LOGGER.info("[Controller, Sindicato] Se solicita la vista para la edición de un sindicato ["+idSindicato+"]");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            
            map = getInformacionSindicato(idSindicato);
            map.put("edicion", true);
            return new ModelAndView("sindicato/registroSindicato", "model", map);
            
        }catch(Exception e){           
                LOGGER.error("[Controller,Sindicato] Ocurrio un error al momento de crear la vista para la edición de un sindicato  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de sindicatos", "Ocurrio un error al momento de crear la vista para la edición de un sindicato");
                getAllSidicatos(map);
           }
           return new ModelAndView("sindicato/sindicatos", "model", map);
    }
    
    /**
     * Controller encargado de ingresar los datos de un sindicato
     * @param archivoIzquierdo Imagen del logo izquierdo del sindicato
     * @param archivoDerecho Imagen del logo derecho del sindicato
     * @param sindicato Instancia de Sindicato
     * @param request Variable que maneja las peticiones 
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sindicato/datos-sindicato.htm",method = RequestMethod.POST)
    public ModelAndView setDatosSindicato(@RequestParam("rchvIzquierdo") MultipartFile archivoIzquierdo,@RequestParam("rchvDerecho") MultipartFile archivoDerecho, @ModelAttribute("sindicato") Sindicato sindicato, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Sindicato] Se reciben los datos de un sindicato "+sindicato.getNombreCorto());
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String statusString = request.getParameter("statusSindicato");
            boolean status;
            if(statusString != null){
                status = getEstado(statusString);
            }else{
                status = false;
            }
            String movimiento;
            if(sindicato.getIdSindicato()==null){
                sindicato.setCreado( new Date() );
                movimiento = "Inserción";
            }else{
                sindicato.setCreado(sindicatoService.getSindicato(sindicato.getIdSindicato()).getCreado());
                movimiento = "Actualización";
            }            
            sindicato.setStatus(status);
            sindicato.setModificado( new Date() );
                        
            Integer setSindicato = sindicatoService.setSindicato(sindicato);
            if(setSindicato!=null){
                LOGGER.info("[Controller,Sindicato] Se ingresaron correctamente los datos del sindicato "+sindicato.getNombreCorto());
                boolean cargarLogos = false;
                if(archivoIzquierdo !=null && archivoDerecho != null)
                   cargarLogos =  cargarLogos(sindicato, archivoIzquierdo, archivoDerecho);
                if(cargarLogos){
                    LOGGER.info("[Controller,Sindicato] Se registraron correctamente los logos del sindicato "+sindicato.getNombreCorto());
                    setInformacionEnVentana(map,0, "Datos del sindicato", "Los datos del sindicato fueron ingresados correctamente");
                    setBitacora(movimiento, "Ingresó correctamente todos los datos del sindicato "+sindicato.getNombreCorto().toUpperCase()+".", request);
                }else{
                    setInformacionEnVentana(map,2, "Datos del sindicato", "Los datos del sindicato fueron ingresados correctamente pero NO los losgos del sindicato, ocurrio un problema al intentar guardarlos");
                }
                
            }else{
                setInformacionEnVentana(map,1, "Módulo de sindicatos", "Ocurrio un error al momento de ingresar los datos de un sindicato");            
            }
        } catch(Exception e){           
                LOGGER.error("[Controller,Sindicato] Ocurrio un error al momento de ingresar los datos de un sindicato  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de sindicatos", "Ocurrio un error al momento de ingresar los datos de un sindicato");
           }
            getAllSidicatos(map);
           return new ModelAndView("sindicato/sindicatos", "model", map);
    }
    
    /**
     * voidDatosSindicato
     * @return ModelAndView
     */
    @RequestMapping(value = "sindicato/datos-sindicato.htm",method = RequestMethod.GET)
    public String voidDatosSindicato(){
        LOGGER.info("[Controller, Sindicato] Se solicita un vista dispoible solo en metodo post");
         return "redirect:sindicatos.htm";
    }
    
    /**
     * Controller encargado de mostrar los logos de un sindicato
     * @param idSindicato Numero entero que contiene el id de un sindicato
     * @param logo Cadena de texto que almacena el lado de la imagen que se desea vizualizar
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value = "sindicato/{sindicato}/logo/{logo}.htm", method = RequestMethod.GET)
    public void getLogo(@PathVariable("sindicato") Integer idSindicato, @PathVariable("logo") String logo, HttpServletRequest request, HttpServletResponse response){
            Sindicato sindicato = sindicatoService.getSindicato(idSindicato);
            String urlLogo;
            if(logo.equalsIgnoreCase("Derecho")){
                 urlLogo = sindicato.getUrlLogoDerecha();
            }else{
                urlLogo = sindicato.getUrlLogoIzquierda();
            }
            if(urlLogo != null){
                File descargarFileFTP = ftpService.descargarFileFTP(urlLogo);
                try {
                    
                        response.setContentType("image/jpeg");
                        FileInputStream fis = new FileInputStream(descargarFileFTP);
                        BufferedInputStream bis =  new BufferedInputStream(fis);
                        
                        ServletOutputStream  out = response.getOutputStream();
                        BufferedOutputStream bos =  new BufferedOutputStream(out);
                        int ch;   
                        while((ch=bis.read())!=-1)  
                            response.getOutputStream().write(ch);
                        bis.close();  
                        fis.close();  
                        bos.close();  
                        out.close();  
                } catch (FileNotFoundException fnfe) {
                    Logger.getLogger(SinidicatoController.class.getName()).log(Level.SEVERE, "[Controller,Sindicato,fnfe] El archivo no puede ser generado para su visualización ", fnfe);
                } catch (IOException ioe) {
                    Logger.getLogger(SinidicatoController.class.getName()).log(Level.SEVERE, "[Controller,Sindicato,ioe] El archivo no puede ser generado para su visualización ", ioe);
                }
            }else{
                LOGGER.error("[Controller,Sindicato]El sindicato no cuenta con el logo ["+idSindicato+", "+logo+"]");
            }
    }
    
    /**
     * Controller encargado de mostrar el kardex de un sindicato
     * @param idSindicato Numero entero que contiene el id de un sindicato
     * @return ModelAndView
     */
    @RequestMapping(value = "sindicato/{idSindicato}/kardex.htm",method = RequestMethod.GET)
    public ModelAndView getKardexSindicato(@PathVariable("idSindicato") int idSindicato){
        LOGGER.info("[Controller, Sindicato] Se solicita la vista del kardex de un sindicato ["+idSindicato+"]");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            map = getInformacionSindicato(idSindicato);
            return new ModelAndView("sindicato/kardexDelSindicato", "model", map);
        } catch(Exception e){           
                LOGGER.error("[Controller,Sindicato] Ocurrio un error al momento de generar la vista del kardex del sindicato  -> "+e);
                setInformacionEnVentana(map,1, "Módulo de sindicatos", "UPS! Algo no salió como debía<br/> Ocurrio un error al momento de generar el kardex del sindicato");
                getAllSidicatos(map);
           }
           return new ModelAndView("sindicato/sindicatos", "model", map);
    }
    
    /**
     * Controller encargado de presentar el kardex de un sindicato en pdf
     * @param idSindicato Numero entero que contiene el id de un sindicato
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value = "sindicato/{idSindicato}/kardex/pdf.htm", method = RequestMethod.GET)
    public void getPDFDelKardex(@PathVariable("idSindicato") Integer idSindicato,HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Sindicato] Se solicita la vista del kardex de un sindicato en pdf.");     
        try{            
            Sindicato sindicato = sindicatoService.getSindicato(idSindicato);
            if(sindicato != null){
                ByteArrayOutputStream kardex = pDFiTextService.getKardex(sindicato);// setting some response headers
                response.setHeader("Content-Disposition", "inline;filename=\"kardex del sindicato "+sindicato.getNombreCorto().toUpperCase()+".pdf\"");
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
                throw new Exception("Sindicato no encontrado para la generación de la vista en pdf");
            }
        }catch(Exception e){
            LOGGER.error("[Controller,Sindicato]  Ocurrio un error al momento de generar el kardex en pdf del Sindicato  -> "+e);
        }        
    }
    
    /**
     * Controller encargado de presentar el kardex de un colaborador en pdf
     * @param idColaborador Numero entero que contiene el id del agremiado
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value = "colaboradores/{idColaborador}/kardex/pdf.htm", method = RequestMethod.GET)
    public void getPDFDelKardexColaborador(@PathVariable("idColaborador") Integer idColaborador,HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Sindicato] Se solicita la vista del kardex de un Colaborador en pdf.");     
        try{            
            // AgremiadoService                   < ============================ borrar del @auto y colaboradores/{idColaborador}/kardex/pdf.htm dispatcher & sindicato kardex
            Agremiado agremiado = agremiadoService.getAgremiado(idColaborador);
            if(agremiado != null){
                ByteArrayOutputStream kardex = pDFiTextService.getKardex(agremiado);// setting some response headers
                response.setHeader("Content-Disposition", "inline;filename=\"kardex del Colaborador "+agremiado.getDatosPersonales().getNombre().toUpperCase()+' '+agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase())+".pdf\"");
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
                throw new Exception("Colaborador no encontrado para la generación de la vista en pdf");
            }
        }catch(Exception e){
            LOGGER.error("[Controller,Sindicato]  Ocurrio un error al momento de generar el kardex en pdf del Colaborador  -> "+e);
        }        
    }
    
    private void getAllSidicatos(Map<String,Object> map) {
        if(map != null){
            List<Sindicato> sindicatos = sindicatoService.getSindicatos();
            map.put("sindicatosEncontrados", sindicatos);
            map.put("muestraTabla", true);
        }
    }
    private Map<String,Object> getInformacionSindicato(Integer idSindicato){
        Map<String,Object> map = new HashMap<String,Object>();
        
        Sindicato sindicato = sindicatoService.getSindicato(idSindicato);
        if(sindicato != null){
            map.put("sindicato", sindicato);
        }
        
        return map;
    }
    private boolean cargarLogos(Sindicato sindicato, MultipartFile iFile, MultipartFile dFile){
            LOGGER.info("[Controller,Sindicato] Se intentan cargar los logos del sindicato "+sindicato.getNombreCorto());
           try {
               int logosActualizados  = 0;
                    if( ! iFile.getOriginalFilename().isEmpty() ){
                                 if( ( sindicato.getUrlLogoIzquierda() != null )  ){
                                     String ruta = sindicato.getUrlLogoIzquierda();
                                     String fileString = (ruta.length() > 27 )?"..."+ruta.substring((ruta.length()-27)):"...";
                                     LOGGER.info("[Controller,Sindicato] El sindicato ya cuenta con un logo izquierdo ["+fileString+"] se procede a su eliminación");
                                     ftpService.borrarArchivoFTP( sindicato.getUrlLogoIzquierda() );
                                 }
                                String direccionArchivoFTP = ftpService.guardarArchivoFTP(iFile, "Sindicatos",sindicato.getIdSindicato().toString(),"logos");
                                sindicato.setUrlLogoIzquierda(direccionArchivoFTP);
                                logosActualizados++;
                        }
                         if(  ! dFile.getOriginalFilename().isEmpty() ){
                                 if( ( sindicato.getUrlLogoDerecha() != null)  ){
                                     String ruta = sindicato.getUrlLogoDerecha();
                                     String fileString = (ruta.length() > 27 )?"..."+ruta.substring((ruta.length()-27)):"...";
                                     LOGGER.info("[Controller,Sindicato] El sindicato ya cuenta con un logo derecho ["+fileString+"] se procede a su eliminación");
                                     ftpService.borrarArchivoFTP( sindicato.getUrlLogoDerecha() );
                                 }
                                 String direccionArchivoFTP = ftpService.guardarArchivoFTP(dFile, "Sindicatos",sindicato.getIdSindicato().toString(),"logos");
                                 sindicato.setUrlLogoDerecha(direccionArchivoFTP);
                                logosActualizados++;
                        }
                        LOGGER.info("[Controller,Sindicato] Se intentan registrar "+logosActualizados+" logos del sindicato "+sindicato.getNombreCorto()+" dentro de la base de datos");                
                        Integer setSindicato = sindicatoService.setSindicato(sindicato);                
                        return setSindicato != null;
        } catch (Exception e) {
            LOGGER.error("[Controller,Sindicato] Ocurrio un error al momento de intentar almacenar los logos del sindicato  -> "+e);
            return false;
        }
    }
    private void setInformacionEnVentana(Map<String,Object> map, int estado, String titulo, String descripcion){
        if(map!=null){
                map.put("mostrarVentana", true);
                map.put("tipoVentana", estado); 
                map.put("tituloVentana", titulo);
                map.put("descripcionVentana", descripcion);
        }
    }
    private boolean getEstado(String statusString) {
        return statusString.equalsIgnoreCase("Sí");
    }
    private void setBitacora(String movimiento, String detalles,HttpServletRequest request){
       String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(correo);
        if(usuario!=null){
            Bitacora bitacora = new Bitacora(new Date(), usuario.getIdUsuario());
            bitacora.setModulo("Sindicatos");
            bitacora.setMovimiento(movimiento);
            bitacora.setDetalles(detalles);
            bitacora.setIpAcceso(request.getRemoteAddr());
            bitacoraService.setBitacora(bitacora);
        }else{
            LOGGER.error("[Controller, Sindicato] Bitacora.- Se busco al usuario {"+correo+"} pero no se encontró registro alguno.");
        }
   }
    @Override
    public String toString() {
        return "SindicatoController{Controlador Del Sindicato, Versión 1.0}";
    }
}
