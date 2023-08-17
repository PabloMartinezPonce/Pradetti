/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.controller;

import com.pradettisanti.payroll.enums.ContratistaTemplate;
import com.pradettisanti.payroll.enums.DocumentByMethod;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.CamposFomulario;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.EsquemaCampo;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.BitacoraService;
import com.pradettisanti.payroll.service.CatalogoDocumentalService;
import com.pradettisanti.payroll.service.DocumentosYServiciosService;
import com.pradettisanti.payroll.service.UsuarioService;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller encargado de administrar la generación de documentos con respecto al catálogo documental
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 23/01/2018
 */
@SuppressWarnings("Convert2Diamond")
public class DocumentalController {
    
    
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger( DocumentalController.class );
    private static final String CLASS_NAME = "[DocumentalController]";
    private final String MODULO = "Documental";
    
    @Autowired
    private AgremiadoService agremiadoService;
    
    @Autowired
    private CatalogoDocumentalService catalogoDocumentalService;
    
    @Autowired
    private DocumentosYServiciosService documentosYServiciosService;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private BitacoraService bitacoraService;
    
    /**
     * Controlador que devulve la vista solicitada para el levantamiento de datos que se requieren para la genreación de un documento en PDF
     * @param idDoc
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value = "documental/{idDoc}/request.htm", method = RequestMethod.GET)
    public ModelAndView documentFilter( @PathVariable("idDoc")Integer idDoc, HttpServletRequest request, HttpServletResponse response){
        writeLogger("Se solicita la vista de un formulario para la configuración de un tipo de documento registrado {"+idDoc+'}');
        Map<String, Object> map = new HashMap<>();
        try {
             TipoDocumento tipoDocumento = agremiadoService.getTipoDocumento(idDoc);              
                if( tipoDocumento == null ){
                    throw new Exception("Tipo de documento no encontrado {"+idDoc+'}');
                }            
                String idColaboradorString = request.getParameter("idColaborador");
                Agremiado agremiado = agremiadoService.getAgremiado(Integer.parseInt(idColaboradorString));
                DocumentByMethod documentByMethod = DocumentByMethod.getByURL(tipoDocumento.getCatalogoDocumentalObj().getUrl());
                map.put("agremiado", agremiado);
                map.put("tDoc",tipoDocumento );
                map.put("url",tipoDocumento.getCatalogoDocumentalObj().getUrl() );
                return chooseYourView(map, documentByMethod, request,agremiado);
        } catch (Exception e) {
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un error al intentar generar el formulario para la cargado de la información documental");
            writeLogger("No fue posible cargar la vista de un formulario documental {"+idDoc+"}", e);
        }
       return new ModelAndView("errores/informacionIncompleta");
    }
    /**
     * Controllador que devulve el PDF solicitado
     * @param idDoc
     * @param request
     * @param response 
     */
    @RequestMapping(value = "documental/{idDoc}/request.htm", method = RequestMethod.POST)
    public void documentView(@PathVariable("idDoc")Integer idDoc, HttpServletRequest request, HttpServletResponse response){
        writeLogger("Se solicita la generación de un documento desde URL {"+idDoc+'}');
            try {
                TipoDocumento tipoDocumento = agremiadoService.getTipoDocumento(idDoc);     
                if( tipoDocumento == null ){
                    throw new Exception("Tipo de documento no encontrado {"+idDoc+'}');
                }
                switch(DocumentByMethod.getByURL(tipoDocumento.getCatalogoDocumentalObj().getUrl())){
                    case SOLICITUD_FONDO_AHORRO:
                        saveFondoDeAhorro(request); 
                        break;
                    case SOLICITUD_TARJETAS:
                        // Se guarda el valor de saveTDU con el paramero request
                        String respuesta = saveTDU(request);
                        // Condición si respuesta es igual a NO entonces ejecuta toda la sentencia dentro de los parentesis
                        if(respuesta == "No"){
                            // se asigna los parametros para el titulo del documento generado
                            response.setHeader("Content-Disposition", "inline;filename=\""+tipoDocumento.getCatalogoDocumentalObj().getUrl()+".pdf\"");
                            // Se asigna el tipo de cotenido del documento generado
                            response.setContentType("text/html;charset=UTF-8");
                            // se declará varibale para escribir el cuerpo del doucmento html
                            PrintWriter pw = response.getWriter();
                            /**
                             * Se escribe el código html mediante lineas por separa imprimiendolas cada una de ella con la instrucción
                             * pw.println(); básicamente ejectua la función MyFunction cierra la página completa de html y envía un mensaje 
                             * emergente con la función alert para notificarle al usuario que su selección fue un No y por lo tanto no hay 
                             * plantilla que generar.
                             */
                                pw.println("<html>");
                                pw.println("<body onload=\"myFunction()\">");
                                pw.println("<script>");
                                pw.println("function myFunction() {");
                                pw.println("alert(\"Se ha registrado que el colaborador no requiere solicitud de tarjetas\");");
                                pw.println("javascript:parent.window.close(); }");
                                pw.println("</script>");
                                pw.println("</body></html>");
                            return;
                        }
                        break;
                }
                
                String idColaboradorString = request.getParameter("idColaborador");
                Agremiado agremiado = agremiadoService.getAgremiado(Integer.parseInt(idColaboradorString));
                String templateString = request.getParameter("template");
                ContratistaTemplate contratistaTemplate = ContratistaTemplate.getContratistaTemplateByName(templateString);
                DocumentByMethod documentByMethod = DocumentByMethod.getByURL(tipoDocumento.getCatalogoDocumentalObj().getUrl());
                ByteArrayOutputStream baos = chooseYourMethod(documentByMethod,agremiado,contratistaTemplate,request);            
                response.setHeader("Content-Disposition", "inline;filename=\""+tipoDocumento.getCatalogoDocumentalObj().getUrl()+".pdf\"");
                response.setContentType("application/pdf");
                response.setContentLength(baos.size());
                try (OutputStream os = response.getOutputStream()) { 
                    baos.writeTo(os);
                    os.flush();
                }            
            } catch (Exception e) {
                writeLogger("Ocurrio un problema al intentar establecer el documento solicitado", e);
            }
    } 
    private ModelAndView chooseYourView(Map m, DocumentByMethod method,  HttpServletRequest request,Agremiado agremiado) throws Exception{
         List<EsquemaCampo> collection = new ArrayList<EsquemaCampo> (1);
         String url = "";
        switch(method){
            case SOLICITUD_FONDO_AHORRO:
                url = "documental/"+((TipoDocumento)m.get("tDoc")).getIdTipoDocumento()+"/request.htm";
                m.put("templates", getContratistaTemplateList());
                m.put("url", url);
                m.put("sueldoAhorro", getSueldoAhorro(agremiado.getDatosLaborales()));
                collection.add(new EsquemaCampo(new CamposFomulario("SOLICITUD_FONDO_AHORRO")));
                m.put("view", collection);
                setInformacionEnVentana(m, 2, MODULO, "El documento que solicita requeire la selección de una plantilla.");
                return new ModelAndView("documental/plantillaFormFdeA", "model", m);
            case CORREO_ELECTRONICO:
                url = "documental/"+((TipoDocumento)m.get("tDoc")).getIdTipoDocumento()+"/request.htm";
                m.put("templates", getContratistaTemplateList());
                m.put("url", url);
                setInformacionEnVentana(m, 2, MODULO, "El documento que solicita requeire la selección de una plantilla.");
                return new ModelAndView("documental/plantillaForm", "model", m);
            case SOLICITUD_TARJETAS:
                url = "documental/"+((TipoDocumento)m.get("tDoc")).getIdTipoDocumento()+"/request.htm";
                m.put("templates", getContratistaTemplateList());
                m.put("url", url);
                collection.add(new EsquemaCampo(new CamposFomulario("SOLICITUD_TARJETAS")));
                m.put("view", collection);
                setInformacionEnVentana(m, 2, MODULO, "El documento que solicita requeire la selección de una plantilla.");
                return new ModelAndView("documental/plantillaFormFdeA", "model", m);
            case ADHESION_SINDICAL:
                url = "documental/"+((TipoDocumento)m.get("tDoc")).getIdTipoDocumento()+"/request.htm";
                m.put("url", url);
                m.put("agremiado", ((Agremiado)m.get("agremiado")).getIdAgremiado());
                return new ModelAndView("documental/redireccionar", "model", m);
            case SOLICITUD_ASIMILABLES: // en caso de la constante SOLICITUD_ASIMILABLES
                url = "documental/"+((TipoDocumento)m.get("tDoc")).getIdTipoDocumento()+"/request.htm"; // se construye la url 
                m.put("templates", getContratistaTemplateList()); // Se obitene las plantillas por contratista
                m.put("url", url); //se pasa la url como para parametro
                collection.add(new EsquemaCampo(new CamposFomulario("SOLICITUD_ASIMILABLES"))); //Se agrega un nuevo objeto para los campos de la plantilla
                m.put("view", collection); // devuelve la vista creada previamente
                setInformacionEnVentana(m, 2, MODULO, "El documento que solicita requeire la selección de una plantilla."); //Se notifica al usuario que debe seleccionar una plantilla
                return new ModelAndView("documental/plantillaForm", "model", m); //devuelve la vista de selección de plantilla
            case DOCUMENTO_NO_ENCONTRADO:
            default:
             throw new Exception("Ninguno documento coincide con el solicitado");
        }
    }   
    /**
     * Metodo encargado de devolver el PDF generado que se solicito con respecto a enumerado que se paso como parametro
     * @param method
     * @param agremiado
     * @param template
     * @param request
     * @return ByteArrayOutputStream
     * @throws Exception 
     */
    private ByteArrayOutputStream chooseYourMethod(DocumentByMethod method, Agremiado agremiado, ContratistaTemplate template, HttpServletRequest request) throws Exception{ 
        switch(method){
            case SOLICITUD_TARJETAS:
                return  documentosYServiciosService.getSolicitudTarjetas(agremiado,template);
            case CORREO_ELECTRONICO:
                return documentosYServiciosService.getConfirmacionCorreoElectronico(agremiado, template);
            case SOLICITUD_FONDO_AHORRO:
                String percentString = request.getParameter("percent");
                short percent = Short.parseShort(percentString);
                if(percent == 0){
                    return documentosYServiciosService.getCartaRenunciaFA(agremiado, template);
                }else{
                    return documentosYServiciosService.getSolicitudDeFondoDeAhorroEstandar(agremiado, template);
                }
            case ADHESION_SINDICAL:
                return documentosYServiciosService.getAdhesionSindical(agremiado);
            case SOLICITUD_ASIMILABLES: //si se selecciona la constante SOLICITUD_ASIMILABLES
                return documentosYServiciosService.getSolicitudDeAsimilables(agremiado, template); // devuelve el docmuneto odf mediante el metodo getSolicitudDeAsimilables
            case DOCUMENTO_NO_ENCONTRADO:
            default:
             throw new Exception("Ninguno documento coincide con el solicitado");
        }
    }
    private List<ContratistaTemplate> getContratistaTemplateList(){
        return Arrays.asList(ContratistaTemplate.values());        
    }
    private void writeLogger(String txt){
        try {
            LOGGER.info(CLASS_NAME+" "+txt.toLowerCase());
        } catch (Exception e) {
            writeLogger("Houston -->  "+txt, e);
        }
    }
    private void writeLogger(String txt, Exception e){
        try {
            LOGGER.error(CLASS_NAME+" "+txt+"  -->  "+e.getMessage(), e);
        } catch (Exception ex) {
        }
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
   
    private String getSueldoAhorro(DatosLaborales datosLaborales) {
        try {           
                NumberFormat formatter = new DecimalFormat("#0.00");
                if(datosLaborales.getPercepcionBasadaEnSUP()){
                    return formatter.format(30.4 * datosLaborales.getSalarioUnicoProfesional().getPesosDiarios());
                }else{
                    switch(datosLaborales.getEsquemaPago().getDescripcion()){
                        case "Mixto":
                            return formatter.format(30.4 * Double.parseDouble(datosLaborales.getSalarioDiario()));
                        case "Sindical":
                        case "Nominal":
                        case "Asimilados":
                            return formatter.format(Double.parseDouble(datosLaborales.getSueldoMensual()));
                        default:
                            return formatter.format(0.0);
                    }
                }            
        } catch (NumberFormatException e) {
            LOGGER.error(CLASS_NAME+" Ocurrio un problema al intentar obtener el salario para el fondo de ahorro -> "+e.getMessage(),e);
            return "0.00";
        }
    }
    
    private void saveFondoDeAhorro(HttpServletRequest request) throws ParseException {
        String idaString = request.getParameter("ida");
        String percentString = request.getParameter("percent");
        String fechaInicioFAString = request.getParameter("fechaAltaFA");
        int ida = Integer.parseInt(idaString);
        short percent = Short.parseShort(percentString);
        Date fechaInicio = procesayyyyMMdd(fechaInicioFAString);
        Agremiado agremiado = agremiadoService.getAgremiado(ida);
        DatosLaborales datosLaborales = agremiado.getDatosLaborales();
        datosLaborales.setFondoDeAhorroActual(percent);
        datosLaborales.setFondoDeAhorroFechaSol(fechaInicio); 
        datosLaborales.setFondoDeAhorroDisponible(Boolean.TRUE);
        agremiadoService.setDatosLaborales(datosLaborales);
    }    
    
    // metodo que devuelve un string en base al HttpServletRequest
    private String saveTDU(HttpServletRequest request) {
        String idaString = request.getParameter("ida");
        String tarjetaTduString = request.getParameter("tarjetaTdu");
        int ida = Integer.parseInt(idaString);
        Agremiado agremiado = agremiadoService.getAgremiado(ida);
        DatosLaborales datosLaborales = agremiado.getDatosLaborales();
        if(tarjetaTduString==null||tarjetaTduString.equalsIgnoreCase("No")){
            tarjetaTduString = "No";
        }else{
            tarjetaTduString = "Sí";
        }
        datosLaborales.setTarjetaTdu(tarjetaTduString);
        agremiadoService.setDatosLaborales(datosLaborales);
        // regresa el valor de tarjetaTduString
        return tarjetaTduString;
    }
    
    private Date procesayyyyMMdd(String fechaString) throws ParseException {    
        String yyyyMMdd = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        return formatter.parse(fechaString);
    }  
    /**
     * @return the catalogoDocumentalService
     */
    public CatalogoDocumentalService getCatalogoDocumentalService() {
        return catalogoDocumentalService;
    }
    /**
     * @param catalogoDocumentalService the catalogoDocumentalService to set
     */
    public void setCatalogoDocumentalService(CatalogoDocumentalService catalogoDocumentalService) {
        this.catalogoDocumentalService = catalogoDocumentalService;
    }
    /**
     * @return the AgremiadoService
     */
    public AgremiadoService getAgremiadoService() {
        return agremiadoService;
    }
    /**
     * @param agremiadoService the agremiadoService to set
     */
    public void setAgremiadoService(AgremiadoService agremiadoService) {
        this.agremiadoService = agremiadoService;
    }
    /**
     * @return the DocumentosYServiciosService
     */
    public DocumentosYServiciosService getDocumentosYServiciosService() {
        return documentosYServiciosService;
    }
    /**
     * @param documentosYServiciosService  the documentosYServiciosService to set
     */
    public void setDocumentosYServiciosService(DocumentosYServiciosService documentosYServiciosService) {
        this.documentosYServiciosService = documentosYServiciosService;
    }

}
