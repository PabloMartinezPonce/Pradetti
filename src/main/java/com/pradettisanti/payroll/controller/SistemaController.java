/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradettisanti.payroll.Utility.DelContrato;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Bitacora;
import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import com.pradettisanti.payroll.pojoh.DatosPersonales;
import com.pradettisanti.payroll.pojoh.EsquemaPago;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.Notificaciones;
import com.pradettisanti.payroll.pojoh.PeriodoFA;
import com.pradettisanti.payroll.pojoh.Permiso;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.ReciboSolicitado;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoIncidencia;
import com.pradettisanti.payroll.pojoh.TipoSalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.pojoh.ZonaSm;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.BitacoraService;
import com.pradettisanti.payroll.service.CatalogoDocumentalService;
import com.pradettisanti.payroll.service.ClienteService;
import com.pradettisanti.payroll.service.ContratistaService;
import com.pradettisanti.payroll.service.ContratoEmpresasService;
import com.pradettisanti.payroll.service.ContratoService;
import com.pradettisanti.payroll.service.ContratosService;
import com.pradettisanti.payroll.service.ExcelService;
import com.pradettisanti.payroll.service.FtpService;
import com.pradettisanti.payroll.service.IncidenciaService;
import com.pradettisanti.payroll.service.NotificacionesService;
import com.pradettisanti.payroll.service.PDFiTextService;
import com.pradettisanti.payroll.service.PeriodoFondoAhorroService;
import com.pradettisanti.payroll.service.ReciboService;
import com.pradettisanti.payroll.service.SUPService;
import com.pradettisanti.payroll.service.SindicatoService;
import com.pradettisanti.payroll.service.UsuarioService;
import com.pradettisanti.payroll.service.ZonasSmService;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Manager encargado de contener los controllers de la vista sistema y del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@SuppressWarnings("Convert2Diamond")
public class SistemaController {
   
    /**
     * Constante para el uso de los logs del sistema
     */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger( SistemaController.class );
    
    /**
     * Constante que contiene la ubicación ftp del archivo template para editar los recibos de nomina
     */
    private static final String URL_EXCEL_TEMPLATE_RECIBOS = "/home/ftp/Payroll/Payroll/Plantillas/Plantilla_Excel_Edicion_Recibos_Payroll.zip";

    @Autowired
    private ZonasSmService zsmService;
    public ZonasSmService getZonasSmService(){
        return zsmService;
    }
    public void setZonasSmService(ZonasSmService zsmService){
        this.zsmService = zsmService;
    }
    
//    @Autowired
//    private PeriodoFondoAhorroService pfaService;
//    public PeriodoFondoAhorroService getPeriodosFaService(){
//        return pfaService;
//    }
//    public void setPeriodosFaService(PeriodoFondoAhorroService pfaService){
//        this.pfaService = pfaService;
//    }
    
    @Autowired
    private UsuarioService usuarioService;
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @Autowired
    private ClienteService clienteService;
    public ClienteService getClienteService() {
        return clienteService;
    }
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @Autowired
    private ReciboService reciboService;
    public ReciboService getReciboService(){
        return reciboService;
    }
    public void setReciboService(ReciboService reciboService){
        this.reciboService = reciboService;
    }
    
    @Autowired
    private AgremiadoService agremiadoService;
    public AgremiadoService getAgremiadoService(){
        return agremiadoService;
    }
    public void setAgremiadoService(AgremiadoService agremiadoService){
        this.agremiadoService = agremiadoService;
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
    private ContratistaService contratistaService;
    public ContratistaService getContratistaService(){
        return contratistaService;
    }
    public void setContratistaService(ContratistaService contratistaService){
        this.contratistaService = contratistaService;
    }
    
    @Autowired
    private ContratoEmpresasService  contratoEmpresasService;
    public ContratoEmpresasService getContratoEmpresasService(){
        return contratoEmpresasService;
    }
    public void setContratoEmpresasService(ContratoEmpresasService contratoEmpresasService){
        this.contratoEmpresasService = contratoEmpresasService;
    }
    
    @Autowired
    private FtpService ftpService;
    public FtpService getFtpService(){
        return  ftpService;
    }
    public void setFtpService(FtpService ftpService){
        this.ftpService = ftpService;
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
    private ContratosService contratosService;
    public ContratosService getContratosService(){
        return contratosService;
    }
    public void setContratosService( ContratosService contratosService ){
        this.contratosService = contratosService;
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
    private NotificacionesService notificacionesService;
    public NotificacionesService getNotificacionesService(){
        return notificacionesService;
    }
    public void setNotificacionesService(NotificacionesService notificacionesService){
        this.notificacionesService = notificacionesService;
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
    private ExcelService excelService;
    public ExcelService getExcelService(ExcelService excelService){
        return excelService;
    }
    public void setExcelService(ExcelService excelService){
        this.excelService = excelService;
    }   
    
    @Autowired
    private SindicatoService sindicatoService;
    public SindicatoService getSindicatoService(){
        return sindicatoService;
    }
    public void setSindicatoService(SindicatoService sindicatoService){
        this.sindicatoService = sindicatoService;
    }
    
    @Autowired
    private SUPService sUPService;
    public  SUPService getSUPService(){
        return sUPService;
    }
    public void setSUPService(SUPService sUPService){
        this.sUPService = sUPService;
    }
 
    @Autowired
    private CatalogoDocumentalService catalogoDocumentalService;
    public CatalogoDocumentalService getCatalogoDocumentalService(){
        return catalogoDocumentalService;
    }
    public void setCatalogoDocumentalService(CatalogoDocumentalService catalogoDocumentalService){
        this.catalogoDocumentalService = catalogoDocumentalService;
    }
    
    @Autowired
    private PeriodoFondoAhorroService pFAService;
    public PeriodoFondoAhorroService getPeriodosFaService(){
        return pFAService;
    }
    public void setPeriodosFaService(PeriodoFondoAhorroService pfaService){
        this.pFAService = pfaService;
    }
    
    /**
     * Controller encargado de presentar la vista general del modulo sistema.
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/opciones-del-sistema.htm", method = RequestMethod.GET)
    public ModelAndView getModuloSistema(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista de las opciones del sistema");
        
        Map<String,Object> map =  new HashMap<String, Object>();
        try {
             
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con las opciones del sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con las opciones del sistema");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la bitacora personal del usuario logeado dentro del sistema
     * solo los últimos 3 días.
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/mi-bitacora.htm",method = RequestMethod.GET)
    public ModelAndView getMiBitacora(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista de la bitacora personal");
        
        Map<String, Object> map = new HashMap<>();
        
        try {
            String correo = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = usuarioService.getUsuario(correo);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date()); 
            calendar.add(Calendar.DAY_OF_YEAR, -3);  
            Date desde = calendar.getTime();
            calendar.setTime(new Date()); 
            calendar.add(Calendar.DAY_OF_YEAR, 1);              
            Date hasta = calendar.getTime();
            List<Bitacora> bitacora = bitacoraService.getBitacora(usuario, desde, hasta);
            Collections.reverse(bitacora);
            map.put("bitacora", bitacora);            
            return new ModelAndView("sistema/bitacoraDelUsuario", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con la bitacora personal  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con la bitacora personal");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista la la consulta de la bitacora general del sistema.
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/ver-bitacora.htm",method = RequestMethod.GET)
    public ModelAndView getBitacora(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para poder cosultar la bitacora maestra.");
        
        Map<String,Object> map =  new HashMap<String, Object>();
        try {
            String fchDesde = request.getParameter("fchDsd");
            String fchHasta = request.getParameter("fchHst");
            Calendar calendar = Calendar.getInstance();            
            Date desde = null;
            Date hasta = null;
            if(fchDesde == null ){
                calendar.setTime(new Date()); 
                calendar.add(Calendar.DAY_OF_YEAR, -1);  
                desde = calendar.getTime();
            }else{
                Date tmp = procesayyyyMMdd(fchDesde);
                calendar.setTime( tmp ); 
                calendar.add(Calendar.DAY_OF_YEAR, -1);  
                desde = calendar.getTime();
            }
            if(fchHasta == null){
                calendar.setTime(new Date()); 
                calendar.add(Calendar.DAY_OF_YEAR, 1);  
                hasta = calendar.getTime();
            }else{
                Date tmp = procesayyyyMMdd(fchHasta);
                calendar.setTime( tmp ); 
                calendar.add(Calendar.DAY_OF_YEAR, 1);  
                hasta = calendar.getTime();
            }
            if(desde.before(hasta)){
                String correo = SecurityContextHolder.getContext().getAuthentication().getName();            
                Usuario usuario = usuarioService.getUsuario(correo);
                List<Permiso> permisos = usuarioService.getPermisos( new Rol(usuario.getRol()));
                boolean acceso = false;
                for (Permiso permiso : permisos) {
                    if(permiso.getNombre().equalsIgnoreCase("Bitacora")){
                        acceso = true;
                        break;
                    }
                }
                if(acceso){ 
                    List<Bitacora> bitacora = bitacoraService.getBitacora(desde, hasta);
                    map.put("bitacora", bitacora); 
                    map.put("desde", desde); 
                    map.put("hasta", hasta); 
                    return new ModelAndView("sistema/bitacoraMaestra", "model", map);  
                }else{
                     setInformacionEnVentana( map,1,"Sistema","No es posible acceder a la bitacora maestra del sistema<br>El Usuario <b>"+usuario.getCorreoElectronico()+"</b> no cuenta con los permisos para ver la bitacora maestra.");
                     LOGGER.error("[Controller, Sistema] El Usuario "+usuario.getCorreoElectronico()+" no cuenta con los permisos para ver la bitacora maestra.");
                }     
            }else{
                 setInformacionEnVentana( map,1,"Sistema","La fecha hasta no puede ser menor que la fecha desde.");
            }           
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la consulta de la bitacora del sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la consulta de la bitacora del sistema");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista con los contratos establecidos mediante el sistema entre un contratista y un cliente.
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/contratos-generados-contratista-cliente.htm",method = RequestMethod.GET)
    public ModelAndView getVistaContratosGeneradosContratistaCliente(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con todos los contratos generados entre un contratista y un cliente");
        
        Map<String, Object> map = new HashMap<>();
        
        try {
                List<ContratoEmpresas> contratoEmpresas = new LinkedList<>();
                getClientesDelUsuario().stream().map((c) -> contratoEmpresasService.getContratoEmpresas(c)).forEach((contratoEmpresasPorCliente) -> {
                    contratoEmpresasPorCliente.stream().forEach((contrato) -> {
                        contratoEmpresas.add(contrato);
                    });
                });
                map.put("contratos", contratoEmpresas);
                List<Contratista> contratistas = contratistaService.getContratistas();
                map.put("contratistas", contratistas);
                List<Cliente> clientes = getClientesDelUsuario();
                map.put("clientes", clientes);
                return new ModelAndView("sistema/contratosGenerados", "model", map);
            } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con todos los contratos generados entre un contratista y un cliente  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los contratos generados");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista con los datos de un contrato creado entre un contratista y un cliente, para su edición.
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/editar-datos-del-contrato-generado.htm", method = RequestMethod.POST)
    public ModelAndView editarDatosDeUnContratoGenerado(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la edición de un contrato ya generado");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                Integer idContratoEmpresas = Integer.parseInt( request.getParameter("contratoEmpresas") );
                ContratoEmpresas contratoEmpresas = contratoEmpresasService.getContratoEmpresas(idContratoEmpresas);
                if(contratoEmpresas.getNombreContrato()==null){
                    setNombreDeContrato(contratoEmpresas);
                    contratoEmpresasService.setContratoEmpresas(contratoEmpresas);
                }
                Contratista contratista = contratistaService.getContratista(contratoEmpresas.getContratistaObj());
                Cliente cliente = clienteService.getCliente(contratoEmpresas.getClienteObj());
                map.put("contrato", contratoEmpresas);
                map.put("contratista", contratista);
                map.put("cliente", cliente);
                map.put("edicion", true);
                List<SalarioUnicoProfesional> salariosUnicosProfesionales = sUPService.getSalariosUnicosProfesionales();
                salariosUnicosProfesionales.removeAll(contratoEmpresas.getSalarioUnicoProfesionalList());
                map.put("spus",salariosUnicosProfesionales);
                    List<CatalogoDocumental> catalogosDocumentales = catalogoDocumentalService.getCatalagoDocumentalDeServicio();
                    catalogosDocumentales.removeAll(contratoEmpresas.getCatalogoDocumentalList());
                    map.put("servicios",catalogosDocumentales);
                // Se obtiene una lista de tipo Contrato a partir del id del Contratista del colaborador
                List<Contrato> contratosColaborador = contratoService.getContratosDelColaborador(contratista.getIdContratista());
                // Se remueve de la lista el contrato seleccionado
                contratosColaborador.removeAll(contratoEmpresas.getContratosList());
                // Se envia el valor contratosColaborador a la clave "contratos"
                map.put("contratos",contratosColaborador);
                
                return new ModelAndView("contratista/generarContrato", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para editar un contrato generado entre un contratista y un cliente  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la edición de un contrato generado");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }

    /**
     * Controller encargado de recibir los datos de un contrato y almacenarlo dentro de la base de datos.
     * @param contratoEmpresa Instancia de ContratoEmpresas
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
   @RequestMapping(value="sistema/contrato.htm",method = RequestMethod.POST)
   public ModelAndView setDatosContratoContratistaCliente(@ModelAttribute("contratoempresas") ContratoEmpresas contratoEmpresa, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info("[Controller, Sistema] Se reciben los datos de un contrato realizado entre empresas ["+contratoEmpresa.getIdContratoEmpresas()+", "+contratoEmpresa.getContratistaObj()+", "+contratoEmpresa.getClienteObj()+"]");
       
       Map<String,Object> map =  new HashMap<>();
       
       try {
           String fechaDelContratoString = request.getParameter("fechaDelContrato");
           ContratoEmpresas contratoEmpresasBDD = contratoEmpresasService.getContratoEmpresas(contratoEmpresa.getIdContratoEmpresas());
           if(!fechaDelContratoString.isEmpty()){
                Date fechaDelContrato = procesayyyyMMdd(fechaDelContratoString);                
                contratoEmpresa.setFecha(fechaDelContrato);
           }else{
               contratoEmpresa.setFecha(contratoEmpresasBDD.getFecha());
           }
           
           contratoEmpresa.setCreado(contratoEmpresasBDD.getCreado());
           String urlDocumento = contratoEmpresa.getUrlDocumento();
            if(urlDocumento != null){
                ftpService.borrarArchivoFTP(urlDocumento);                
                contratoEmpresa.setUrlDocumento(null);
            }
           contratoEmpresa.setModificado( new Date() );           
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
           contratoEmpresa.setSalarioUnicoProfesionalList(salarioUnicoProfesionals);
           //Arreglo de string que guarda los datos del id de la vista destinoServicios
           String catalogoDeServicios[] = request.getParameterValues("destinoServicios"); 
                //Valida que de lo que se esta recibiendo de parametro sea diferente a null y que su longitud sea mayor a 0
                if(catalogoDeServicios!=null&&catalogoDeServicios.length>0){
                List<CatalogoDocumental> catalogosDocumentales = new ArrayList<>(); //Se declara una arreglo de tipo CatalogoDocumental
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
                        }//Fin del catch
                    }//Fin del for
                //Se manda el valor de catalogosDocumentales al método setCatalogoDocumentalList del pojoh ContratoEmpresas
                contratoEmpresa.setCatalogoDocumentalList(catalogosDocumentales);
                }//Fin del if
            // Se obtiene el valor de los contratos distinados se recorren y se agregan a la lista
            String contratos[] = request.getParameterValues("destinoContratos");
            List<Contrato> Contratos = new ArrayList<>();
            if(contratos!=null&&contratos.length>0){
               for (String contratoCntr : contratos) {
                try {
                    Contrato contratoContratista = contratoService.getContrato(Integer.parseInt(contratoCntr));
                    Contratos.add(contratoContratista);
                } catch (NumberFormatException nfe ){
                    LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de convertir unos de los contrato contratista ("+contratoCntr+") del JSP al controller se omitirá  --> "+nfe,nfe);
                }
               }
           } 
            // Se settea el valot de la lista a ContratoList del pojoh ContratoEmpresas
           contratoEmpresa.setContratosList(Contratos);
           
           contratoEmpresasService.setContratoEmpresas( contratoEmpresa );
           
           if(contratoEmpresa.getModificado() != contratoEmpresasBDD.getModificado() ){
               setInformacionEnVentana(map, 0, "Sistema", "El contrato se actualizó correctamente.");
               setBitacora("Actualización", "Actualizó el contrato entre empresas con ID "+contratoEmpresa.getIdContratoEmpresas()+".", request);
           }else{
               setInformacionEnVentana(map, 1, "Sistema", "El contrato <b>no</b> se actulizó.");           
           }
                
            
            List<ContratoEmpresas> contratoEmpresas = new LinkedList<>();
            getClientesDelUsuario().stream().map((c) -> contratoEmpresasService.getContratoEmpresas(c)).forEach((contratoEmpresasPorCliente) -> {
                contratoEmpresasPorCliente.stream().forEach((contrato) -> {
                    contratoEmpresas.add(contrato);
                });
            });
            map.put("contratos", contratoEmpresas);
            List<Contratista> contratistas = contratistaService.getContratistas();
            map.put("contratistas", contratistas);
            List<Cliente> clientes = getClientesDelUsuario();
            map.put("clientes", clientes);           
            
            return new ModelAndView("sistema/contratosGenerados", "model", map);
                
                
       } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de actualizar los datos de un contrato generado entre un contratista y un cliente  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de actualizar los datos de un contrato generado");
        }
        return new ModelAndView("sistema/sistema", "model", map);
   }
   
   /**
    * Controller encargado de mostrar el contrato en formato pdf de un contratista con un colaborador.
    * @param idContrato Numero entero que contiene el id del contrato
    * @param request Variable que maneja las respuestas
    * @param response Variable que maneja las respuestas 
    */
   @RequestMapping(value="sistema/contrato-contratista-cliente/{idContrato}/pdf.htm",method = RequestMethod.GET)
   public void getContratoContratistaCliente(@PathVariable("idContrato") Integer idContrato,  HttpServletRequest request, HttpServletResponse response){
       LOGGER.info("[Controller, Sistema] Se solicita la vista de un contrato generado entre un contratista y un cliente ["+idContrato+']');
       
       try {
           
           ContratoEmpresas contratoEmpresas = contratoEmpresasService.getContratoEmpresas(idContrato);
           if(contratoEmpresas != null){
                if(contratoEmpresas.getUrlDocumento() != null){
                    String direccion  = contratoEmpresas.getUrlDocumento() ;               
                     File descargarFileFTP = ftpService.descargarFileFTP(direccion);
                     InputStream is = new FileInputStream(descargarFileFTP);
                     response.setHeader("Content-Disposition", "inline;filename=\"ContratoEntreEmpresas\"");
                     ServletOutputStream os = response.getOutputStream();
                     response.setContentType("application/pdf");
                     IOUtils.copy(is, os);
                     os.flush();    
                }else{
                    ByteArrayOutputStream nuevoContratoContratistaCliente = contratosService.getNuevoContratoContratistaCliente(contratoEmpresas);
                    // setting some response headers
                     response.setHeader("Content-Disposition", "inline;filename=\"ContratoEntreEmpresas.pdf\"");
                     // setting the content type
                     response.setContentType("application/pdf");
                     // the contentlength
                     response.setContentLength(nuevoContratoContratistaCliente.size());
                     try ( // write ByteArrayOutputStream to the ServletOutputStream
                         OutputStream os = response.getOutputStream()) { 
                         nuevoContratoContratistaCliente.writeTo(os);
                         os.flush();
                     }
                }   
           }else{
               LOGGER.error("[Controller, Sistema] No se encontró el contrato entre empresas con id "+idContrato);
           }
           
       } catch (FileNotFoundException fnfe) {
                LOGGER.error("[Controller, Sistema, fnfe] El archivo no puede ser generado para su visualización --> "+fnfe.getMessage());
            } catch (IOException ioe) {
                LOGGER.error("[Controller, Sistema, ioe] El archivo no puede ser generado para su visualización --> "+ioe.getMessage());
            } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de obtener los datos de un contrato generado entre un contratista y un cliente, imposible crear PDF  -> "+e.getMessage());
       }       
   }
    
   /**
    * Controller encargado de mostrar el formulario para cargar el contrato firmado de un contrato creado en el sistema.
    * @param idContratoEmpresas Numero entero que contiene el id de un contrato entre empresas
    * @param request Variable que maneja las respuestas
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value="sistema/cargar-contrato-entre-empresas/{idContratoEmpresas}/file.htm",method = RequestMethod.GET)
   public ModelAndView getCargarContratoFirmado(@PathVariable("idContratoEmpresas")Integer idContratoEmpresas, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para cargar del contrato firmado de un contrato ya generado");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                ContratoEmpresas contratoEmpresas = contratoEmpresasService.getContratoEmpresas(idContratoEmpresas);
                if(contratoEmpresas.getNombreContrato()==null){
                    setNombreDeContrato(contratoEmpresas);
                    contratoEmpresasService.setContratoEmpresas(contratoEmpresas);
                }
                Contratista contratista = contratistaService.getContratista(contratoEmpresas.getContratistaObj());
                Cliente cliente = clienteService.getCliente(contratoEmpresas.getClienteObj());
                map.put("contrato", contratoEmpresas);
                map.put("contratista", contratista);
                map.put("cliente", cliente);
                map.put("edicion", true);
                return new ModelAndView("sistema/contratoEntreEmpresasFirmado", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para subir un contrato entre un contratista y un contratante  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para subir un contrato de un contrato entre empresas");
        }
        return new ModelAndView("sistema/sistema", "model", map);
   }
   
   /**
    * Controller encargado de almacenar el archivo firmado de un contrato entre empresas.
    * @param contratoPDF Archivo del contrato firmado en PDF
    * @param request Variable que maneja las respuestas
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value="sistema/contrato-entre-empresas-firmado.htm",method = RequestMethod.POST)
   public ModelAndView setContratoFirmado(@RequestParam("contratoPDF") MultipartFile contratoPDF, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la carga del contrato firmado de un contrato ya generado");
        Map<String,Object> map = new HashMap<>();
        
        try {
                Integer idContratoEmpresas = Integer.parseInt( request.getParameter("idContratoEmpresas"));
                ContratoEmpresas contratoEmpresa = contratoEmpresasService.getContratoEmpresas(idContratoEmpresas);
                if(contratoEmpresa != null ){
                    Contratista contratista = contratistaService.getContratista(contratoEmpresa.getContratistaObj());
                    Cliente cliente = clienteService.getCliente(contratoEmpresa.getClienteObj());
                    String urlDocumento = contratoEmpresa.getUrlDocumento();
                    if(urlDocumento != null){
                        ftpService.borrarArchivoFTP(urlDocumento);                            
                        contratoEmpresa.setUrlDocumento(null);
                    }
                    String guardarArchivoFTP = ftpService.guardarArchivoFTP(contratoPDF,  "Sistema",contratista.getNombreEmpresa().toUpperCase(), cliente.getNombreEmpresa().toUpperCase());
                    contratoEmpresa.setUrlDocumento(guardarArchivoFTP);
                    contratoEmpresa.setModificado(new Date());
                     contratoEmpresasService.setContratoEmpresas( contratoEmpresa );
           
           if(contratoEmpresa.getUrlDocumento() != null ){
               setInformacionEnVentana(map, 0, "Sistema", "El contrato se actualizó correctamente con el contrato firmado.");
               setBitacora("Actualización", "Actualizó el contrato entre empresas con ID "+contratoEmpresa.getIdContratoEmpresas()+" con el contrato firmado.", request);
           }else{
               setInformacionEnVentana(map, 1, "Sistema", "El contrato <b>no</b> se actulizó.");           
           }
                
            
            List<ContratoEmpresas> contratoEmpresas = new LinkedList<>();
            getClientesDelUsuario().stream().map((c) -> contratoEmpresasService.getContratoEmpresas(c)).forEach((contratoEmpresasPorCliente) -> {
                contratoEmpresasPorCliente.stream().forEach((contrato) -> {
                    contratoEmpresas.add(contrato);
                });
            });
            map.put("contratos", contratoEmpresas);
            List<Contratista> contratistas = contratistaService.getContratistas();
            map.put("contratistas", contratistas);
            List<Cliente> clientes = getClientesDelUsuario();
            map.put("clientes", clientes);           
            
            return new ModelAndView("sistema/contratosGenerados", "model", map);
                }
        }catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de subir un contrato entre un contratista y un contratante  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de subir un contrato de un contrato entre empresas");
        }
        return new ModelAndView("sistema/sistema", "model", map);        
   }
   
   /**
    * Controller encargado de presentar el contrato entre empresas en pdf
    * @param idContratoEmpresas Numero entero que contiene el id de un contrato entre empresa
    * @param request Variable que maneja las respuestas
    * @param response Variable que maneja las respuestas 
    */
  @RequestMapping(value="sistema/contrato-entre-empresas/{idContratoEmpresas}/pdf.htm",method = RequestMethod.GET)
   public void getContratoFirmadoEntreEmpresas(@PathVariable("idContratoEmpresas") Integer idContratoEmpresas, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita el archivo firmado de un contrato entre empresas");
       try {          
            ContratoEmpresas contratoEmpresas = contratoEmpresasService.getContratoEmpresas(idContratoEmpresas);
            if(contratoEmpresas!=null && contratoEmpresas.getUrlDocumento()!=null){
                File descargarFileFTP = ftpService.descargarFileFTP(contratoEmpresas.getUrlDocumento());
                InputStream is = new FileInputStream(descargarFileFTP);
                response.setHeader("Content-Disposition", "inline;filename=\"Contrato Entre Empresas Firmado\"");
                OutputStream sos = response.getOutputStream();
                response.setContentType("application/pdf");
                IOUtils.copy(is, sos);
                sos.flush();
            }
      } catch (Exception e) {
          LOGGER.error("[Controller, Sistema] No se pudo obtener el archivo firmado de un contrato entre empresas");
      }                        
   }
   
   /**
    * Controller encargado de presentar la vista para crear un nuevo contrato plantilla entre contratistas y colaboradores
    * @param request Variable que maneja las respuestas
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
    @RequestMapping(value="sistema/nuevo-contrato-colaboradores.htm",method = RequestMethod.GET)
    public ModelAndView getVistaCrearNuevoContratoClientes(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistemas] Se solicita la vista para crear un nuevo contrato entre contratista y colaboradores");
        
        Map<String, Object> map = new HashMap<>();
        
        try {            
                map.put("DatosDelContratista",contratosService.getDatosDeContratista());
                map.put("DatosDelColaborador",contratosService.getDatosDelColaborador());
                map.put("DatosContratoContratistaColaborador",contratosService.getDatosContratoContratistaColaborador());
                map.put("edicion", false);
                
                return new ModelAndView("sistema/crearNuevoContrato", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de generar la vista para crear un nuevo contrato entre contratista y colaboradores  -> "+e.getMessage());
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de generar la vista para crear un nuevo contrato entre contratista y colaboradores");
        }
        return new ModelAndView("sistema/sistema", "model", map);
        
    }
    
    /**
     * Controller encargado de presentar la vista para crear un nuevo contrato plantilla entre contratistas y clientes
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/nuevo-contrato-contratistas.htm",method = RequestMethod.GET)
    public ModelAndView getVistaCrearNuevoContratoContratistas(){
        LOGGER.info("[Controller, Sistemas] Se solicita la vista para crear un nuevo contrato entre contratistas y clientes");
        
        Map<String, Object> map = new HashMap<>();
        
        try {            
                map.put("DatosDelContratista", contratosService.getDatosDelContratista());
                map.put("DatosDelContratante", contratosService.getDatosDelContratante());
                map.put("DatosDelContratoEntreEmpresas", contratosService.getDatosDelContratoEntreEmpresas());
                map.put("edicion", false);
                return new ModelAndView("sistema/crearNuevoContratoContratistas", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de generar la vista para crear un nuevo contrato entre contratistas y clientes  -> "+e.getMessage());
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de generar la vista para crear un nuevo contrato entre contratistas y clientes");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar los contratos plantilla creados para contratistas y sus relaciones con clientes y colaboradores
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/contratos-creados.htm",method = RequestMethod.GET)
    public ModelAndView getContratosCreados(){
        LOGGER.info("[Controller, Sistema] Se solicita la información con todos los contratos creados dentro del sistema");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            List<Contrato> contratos = contratoService.getContratos();
            map.put("contratos", contratos);
        return new ModelAndView("sistema/contratosCreados", "model", map);
            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con los contratos creados dentro del sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los contratos dentro del sistema");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista para la edición de los contratos plantilla creados dentro del sistema
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/editar-contrato.htm", method = RequestMethod.POST)
    public ModelAndView getVistaEditarContrato(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la edición de un contrato ya almecenado dentro del sistema");
        
        Map<String,Object> map = new HashMap<>();
        try {
            Integer idContrato = Integer.parseInt(request.getParameter("contrato"));
            Contrato contrato = contratoService.getContrato(idContrato);
            if(contrato.getIdContrato() != null){
                map.put("contrato", contrato);
                map.put("edicion", true);
                if(contrato.getContratoDeColaborador()){                                
                        map.put("DatosDelContratista",contratosService.getDatosDeContratista());
                        map.put("DatosDelColaborador",contratosService.getDatosDelColaborador());
                        map.put("DatosContratoContratistaColaborador",contratosService.getDatosContratoContratistaColaborador());
                        return new ModelAndView("sistema/crearNuevoContrato", "model", map);
                }else{                          
                        map.put("DatosDelContratista", contratosService.getDatosDelContratista());
                        map.put("DatosDelContratante", contratosService.getDatosDelContratante());
                        map.put("DatosDelContratoEntreEmpresas", contratosService.getDatosDelContratoEntreEmpresas());
                        return new ModelAndView("sistema/crearNuevoContratoContratistas", "model", map);
                }
            }else{                
                setInformacionEnVentana( map,2,"Sistema","No se encuentró información del contrato solcitado");
            }            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la edición de un contrato  --> "+e.getMessage());
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la edición de un contrato");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de almacenar un contrato plantilla entre contratistas y colaboradores
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/datos-contrato-colaboradores.htm",method = RequestMethod.POST)
    public ModelAndView setDatosContratoCliente(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se reciben los datos de un contrato del contratista - colaborador");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                String movimiento;
                String idString = request.getParameter("idContrato");
                String nombre = request.getParameter("nmbrCntrt");
                String activo = request.getParameter("estatus");
                String contratoHTML = request.getParameter("contrato");
                Contrato contrato;
                if(idString.isEmpty()){
                    movimiento = "Inserción";
                    contrato =  new Contrato();
                    contrato.setCreado( new java.util.Date() );
                }else{  
                    movimiento = "Actualización";                  
                    Integer idContrato = Integer.parseInt( idString );
                    contrato = contratoService.getContrato( idContrato );
                }
                contrato.setNombre(nombre);
                contrato.setActivo(activo != null);
                contrato.setContratoDeColaborador(true);
                contrato.setContrato(contratoHTML);
                contrato.setModificado( new java.util.Date() );
                Integer setContrato = contratoService.setContrato(contrato);
                if(setContrato != null){ 
                    String msj = "Los datos del contrato se guardaron correctamente<br>"
                            + "<b>ID</b>: "+contrato.getIdContrato()
                            + "<br><b>Nombre </b>: "+contrato.getNombre()
                            + "<br><b>Estado del contrato </b>: "+((contrato.getActivo())?"Activo":"Innactivo")
                            + "<br><b>Contratos disponible para </b>: "+(contrato.getContratoDeColaborador()?"Colaboradores":"Contratistas")
                            + "";
                    setInformacionEnVentana( map,0,"Sistema",msj);  
                    setBitacora(movimiento, "Ingresó los datos del contrato "+contrato.getNombre().toUpperCase()+".", request);
                }else{
                    setInformacionEnVentana(map, 1, "Sistema", "<b>UPS!!!</b><br>No fue posible ingresar los datos del contrato");
                }                
                List<Contrato> contratos = contratoService.getContratos();
                map.put("contratos", contratos);
                return new ModelAndView("sistema/contratosCreados", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento ingresar los datos de un contrato de cliente - colaborador  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de ingresar los datos del contrato entre un cliente y un colaborador");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de almacenar un contrato plantilla entre contratistas y clientes
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/datos-contrato-contratista.htm",method = RequestMethod.POST)
    public ModelAndView setDatosContratoContratista(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se reciben los datos de un contrato del contratista - cliente");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                String movimiento;
                String idString = request.getParameter("idContrato");
                String nombre = request.getParameter("nmbrCntrt");
                String activo = request.getParameter("estatus");
                String contratoHTML = request.getParameter("contrato");
                Contrato contrato;
                if(idString.isEmpty()){
                    movimiento = "Inserción";
                    contrato =  new Contrato();
                    contrato.setCreado( new java.util.Date() );
                }else{    
                    movimiento = "Actualización";
                    Integer idContrato = Integer.parseInt( idString );
                    contrato = contratoService.getContrato( idContrato );
                }
                contrato.setNombre(nombre);
                contrato.setActivo(activo != null);
                contrato.setContratoDeColaborador(false);
                contrato.setContrato(contratoHTML);
                contrato.setModificado( new java.util.Date() );
                Integer setContrato = contratoService.setContrato(contrato);
                if(setContrato != null){       
                    String msj = "Los datos del contrato se guardaron correctamente<br>"
                            + "<b>ID</b>: "+contrato.getIdContrato()
                            + "<br><b>Nombre </b>: "+contrato.getNombre()
                            + "<br><b>Estado del contrato </b>: "+((contrato.getActivo())?"Activo":"Innactivo")
                            + "<br><b>Contratos disponible para </b>: "+(contrato.getContratoDeColaborador()?"Colaboradores":"Clientes")
                            + "";
                    setInformacionEnVentana( map,0,"Sistema",msj);  
                    setBitacora(movimiento, "Ingresó los datos del contrato "+contrato.getNombre().toUpperCase()+".", request);
                }else{
                    setInformacionEnVentana(map, 1, "Sistema", "<b>UPS!!!</b><br>No fue posible ingresar los datos del contrato");
                }                
                List<Contrato> contratos = contratoService.getContratos();
                map.put("contratos", contratos);
                return new ModelAndView("sistema/contratosCreados", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento ingresar los datos de un contrato de contratista - cliente  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de ingresar los datos del contrato entre un contratista y un cliente");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
     
    /**
     * Controller encargado de cambiar la disponibilidad de cualquier contrato plantilla
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/cambiar-estado-del-contrato.htm", method = RequestMethod.POST)
    public ModelAndView setEstadoDelContrato(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] se solicita el cambio de estado de un contrato creado en el sistema");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Integer idContrato = Integer.parseInt(request.getParameter("contrato"));
            Contrato contrato = contratoService.getContrato(idContrato);
            contrato.setActivo(!contrato.getActivo());
            contrato.setModificado( new java.util.Date() );
            Integer setContrato = contratoService.setContrato(contrato);
            if(setContrato != null){
                String msj = "El contrato <b>"+contrato.getNombre()+"</b> fue cambiado de su estado."
                        + "<br><br>"
                        + "Estado Actual : <b>"+((contrato.getActivo())?"Activo":"Inactivo")+"</b>";
                setInformacionEnVentana(map, 0, "Sistema", msj);
                setBitacora("Actualización", "Actualizó el estado del contrato "+contrato.getNombre().toUpperCase()+" al estado "+((contrato.getActivo())?"Activo":"Inactivo")+".", request);
            }else{
                setInformacionEnVentana(map, 1, "Sistema", "No fue posible actulizar el estado del contrato");
            } 
                List<Contrato> contratos = contratoService.getContratos();
                map.put("contratos", contratos);
                return new ModelAndView("sistema/contratosCreados", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de intentar cambiar el estado de un contrato  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de cambiar el estado de un contrato.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista con los contratistas registrados dentro del sistema, disponibles para ser relacionados con un contrato plantilla
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/relacionar-contrato.htm",method = RequestMethod.POST)
    public ModelAndView setRelacionarContrato(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con las relaciones de un contrato");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Integer idContrato = Integer.parseInt(request.getParameter("contrato"));
            
            Contrato contrato = contratoService.getContrato(idContrato);
            if(contrato != null){
                map.put("contrato", contrato);
                    List<Contratista> contratistasActive = new LinkedList<>();
                    contratistaService.getContratistas().stream().filter((contratista) -> (contratista.getStatus())).forEach((contratista) -> {
                        contratistasActive.add(contratista);
                });
                    contratistasActive.sort(  (Contratista c1, Contratista c2) -> c1.getNombreEmpresa().toUpperCase().compareTo( c2.getNombreEmpresa().toUpperCase() )  );
                    map.put("actores", contratistasActive);
                return new ModelAndView("sistema/asociarContrato","model",map);
            }else{                
                setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento crear la vista con las relaciones de un contrato<br>No se encontró el contrato");
                List<Contrato> contratos = contratoService.getContratos();
                map.put("contratos", contratos);
                return new ModelAndView("sistema/contratosCreados", "model", map);
            }
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de generar la vista con las relaciones de un contrato  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento crear la vista con las relaciones de un contrato.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
        
    /**
     * Controller encargado de agregar contratistas al contrato
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/agregar-contratistas-al-contrato.htm", method = RequestMethod.POST)
    public ModelAndView setConstratistasAlContrato(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la adición/eliminación de constratistas a un contrato");
        
        Map<String, Object> map = new HashMap<>();
        
        try {
                Integer idContrato = Integer.parseInt(request.getParameter("contrato"));
                String parameters = request.getParameter("clntsCntrstsPrGrgr");
                
                Contrato contrato = contratoService.getContrato(idContrato);
                if(contrato != null){
                    Set<Contratista> contratistaSet = new HashSet<>();
                    contratistaSet.addAll(contrato.getContratistaList());
                    String[] split = parameters.split(",");
                    for (String string : split) {
                        int idContratista = Integer.parseInt(string);
                        Contratista contratista= contratistaService.getContratista(idContratista);
                        contratistaSet.add(contratista);
                    }
                    
                    List<Contratista> contratistas = new LinkedList<>();
                    contratistaSet.stream().forEach((c) -> {
                        if(!c.getContratoList().contains(contrato)){
                            c.getContratoList().add(contrato);
                        }
                        contratistas.add(c);
                    });
                    
                    contratistas.stream().forEach((contratista) -> {
                        contratistaService.setContratista(contratista);
                    });
                    
                    contrato.setContratistaList(contratistas);                    
                    contrato.setModificado( new java.util.Date() );
                    
                    
                    contratoService.setContrato(contrato);
                    
                    String msj = "El contrato <b>"+contrato.getNombre()+"</b> está disponible para <b>"+contrato.getContratistaList().size()+"</b> constratistas.";
                    setInformacionEnVentana( map,0,"Sistema",msj);
                    map.put("contrato", contrato);
                    List<Contratista> contratistaList = contratistaService.getContratistas();
                    map.put("actores", contratistaList);
                    setBitacora("Actualización", "Actualizó el contrato "+contrato.getNombre().toUpperCase()+" ahora se encuentra disponible para "+contrato.getContratistaList().size()+" contratistas.", request);
                    return new ModelAndView("sistema/asociarContrato","model",map);
                }
                
            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento añadir contratistas a un contrato  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento contratistas a un contrato.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
      
    /**
     * Controller encargado de quitarle a un contratista la disponibilidad de un contrato
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/quitar-un-contratista-del-contrato.htm",method = RequestMethod.POST)
    public ModelAndView quitarContratistaDelContrato(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistemas] Se solicita la eliminación de un contratista del contrato");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            int idContratista = Integer.parseInt( request.getParameter("contratista") );
            int idContrato = Integer.parseInt( request.getParameter("contrato") );
            
            Contrato contrato = contratoService.getContrato(idContrato);
            Contratista contratista = contratistaService.getContratista(idContratista);
            
            List<Contrato> contratoList = contratista.getContratoList();
            boolean remove = contratoList.remove(contrato);
            
            contratista.setContratoList(contratoList);
            contratistaService.setContratista(contratista);
            
            List<Contratista> contratistaList = contrato.getContratistaList();
            
            if(remove){
                contrato.setContratistaList(contratistaList);
                contratoService.setContrato(contrato);
                contrato = contratoService.getContrato(contrato.getIdContrato());
                
                String msj = "El contratista <b>"+contratista.getNombreEmpresa()+"</b> fue removido con éxito del contrato."
                        + "<br><br>"
                        + "El contrato <b>"+contrato.getNombre()+"</b> está disponible para <b>"+contrato.getContratistaList().size()+"</b> contratistas.";
                setInformacionEnVentana( map,0,"Sistema",msj);                 
            }else{
                setInformacionEnVentana( map,1,"Sistema","El contratista <b>"+contratista.getNombreEmpresa()+"</b> no fue removido del contrato.");            
            }
            
            map.put("contrato", contrato);            
            List<Contratista> contratistas = contratistaService.getContratistas();
            map.put("actores", contratistas);
            return new ModelAndView("sistema/asociarContrato","model",map);
            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento eliminar un contratista de un contrato  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar quitar al contratista del contrato.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller para ver el demo de un contrato plantilla creado desde el sistema
     * @param idContrato Numero entero que contiene el id de un contrato
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/contrato-demo/{idContrato}.htm", method = RequestMethod.GET)
    public ModelAndView getContratoDemo(@PathVariable("idContrato")Integer idContrato){
        LOGGER.info("[Controller, Sistema] Se solicita la vista DEMO de un contrato creado por el sistema["+idContrato+']');
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Contrato contrato = contratoService.getContrato(idContrato);
            if(contrato!=null){
                map.put("contrato", contrato);
            setInformacionEnVentana( map,2,"Sistema","Esto es una vista <b>DEMO</b> del contrato '"+contrato.getNombre()+"'");
            return new ModelAndView("sistema/vistaContratoCreado", "model", map);                
            }
            throw  new Exception("El contrato no fue encontrado");
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de tratar de obtener los datos para motrar el DEMO del contrato  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de generar la vista del contrato DEMO.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la lista de catalogo documental
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/catalogo-documental.htm",method = RequestMethod.GET)
    public ModelAndView getCatalogoDocumental(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con todos los documentos disponibles dentro del sistema");
        
        Map<String, Object> map = new HashMap<>();
        
        try {
            List<CatalogoDocumental> catalogoDocumental = catalogoDocumentalService.getCatalogoDocumental();
            map.put("catalogoDocumental", catalogoDocumental);
            return new ModelAndView("sistema/catalogoDocumental", "model", map); 
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Osurrio un problema al crear la vista para la consulta del catálogo documental  --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para el registro de un documento");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar el formulario para un registro de catalogo documental
     * @return ModelAndView
     */
   @RequestMapping(value="sistema/registro-documental.htm",method = RequestMethod.GET)
    public ModelAndView getFormCatalogoDocumental(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con todos los documentos disponibles dentro del sistema");
        
        Map<String, Object> map = new HashMap<>();
        
        try {
            map.put("edicion", false);
            return new ModelAndView("sistema/registrarDocumentoBase", "model", map); 
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un problema al crear la vista para la consulta del catálogo documental  --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para el registro de un catalogo documental");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de devolver el formulario para la edición un catalogo documental
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/editar-registro-documental.htm",method = RequestMethod.POST)
    public ModelAndView getFormCatalogoDocumetalEdit(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solcita la vista para la edición de un catálogo documental.");
        
        Map<String, Object> map = new HashMap<>();
        
        try {
            String parameter = request.getParameter("tipoDoc");
            if(parameter != null && !parameter.isEmpty()){
                int parseInt = Integer.parseInt(parameter);
                CatalogoDocumental catalogoDocumental = catalogoDocumentalService.getCatalogoDocumental(parseInt);
                map.put("edicion", true);
                map.put("catalogoDocumental", catalogoDocumental);
            return new ModelAndView("sistema/registrarDocumentoBase", "model", map); 
            }            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un problema al crear la vista para la edición del catálogo documental  --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la edición de un catalogo documental.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    
    /**
     * Controller encargado de almacenar la información de un catalogo documental
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/guardar-cambios-documental.htm",method = RequestMethod.POST)
    public ModelAndView saveDocumentalInformation(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita almancenar la informción de un catálogo documental.");
        
        Map<String, Object> map = new HashMap<>();
        
        try {
            String id = request.getParameter("idCD");
            String nombre = request.getParameter("nombre");
            String servicio = request.getParameter("servicio");
            String configurable = request.getParameter("servicioConfigurado");
            
            boolean isServicio = false, isConfig = false;
            if( (servicio != null && !servicio.isEmpty()) && (servicio.equalsIgnoreCase("on"))){
                isServicio = true;
            }
            if( (configurable != null && !configurable.isEmpty()) && (configurable.equalsIgnoreCase("on"))){
                isConfig = true;
            }
            
            CatalogoDocumental catalogoDocumental = null;
            if(id != null && !id.isEmpty()){
                int parseInt = Integer.parseInt(id);
                catalogoDocumental = catalogoDocumentalService.getCatalogoDocumental(parseInt);
            }
            
            if(catalogoDocumental == null){
                catalogoDocumental = new CatalogoDocumental();
                catalogoDocumental.setCreado(new Date());
                catalogoDocumental.setPlantilla(false);
                catalogoDocumental.setUrl(null);
            }else{
                catalogoDocumental.setModificado(new Date());
            }
            catalogoDocumental.setNombreDocumento(nombre);
            catalogoDocumental.setServicio(isServicio);
            catalogoDocumental.setServicioConfigurado(isConfig);
            
            catalogoDocumentalService.setCatalogoDocumentos(catalogoDocumental);
            setInformacionEnVentana( map,0,"Sistema","El catalogo documetal se guardo con éxito.");
            setBitacora("Alta", "Registró con éxtio la información de un catalogo documental", request);
            
            List<CatalogoDocumental> catalogoDocumentals = catalogoDocumentalService.getCatalogoDocumental();
            map.put("catalogoDocumental", catalogoDocumentals);
            return new ModelAndView("sistema/catalogoDocumental", "model", map); 
            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un problema al ingresar la información del catálogo documental  --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al  ingresar la información del catálogo documental");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista para el registro de un nuevo documento
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/registrar-documento.htm",method = RequestMethod.GET)
    public ModelAndView getVistaRegistrarDocumento(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para el registro de un nuevo documento");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                List<Modulo> modulos = agremiadoService.getModulos();
                map.put("modulos", modulos);
                map.put("edicion", false);
                map.put("catalogoDocumental",catalogoDocumentalService.getCatalogoDocumental());
                map.put("esquemas", agremiadoService.getEsquemaDePagos()); //Se obtienen toso los Esquemas de Pago
                return new ModelAndView("sistema/registrarNuevoDocumento", "model", map);            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista par el registro de un documento  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para el registro de un documento");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar los documentos registrados dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/documentos-por-modulo.htm",method = RequestMethod.GET)
    public ModelAndView getDocumentosPorModulo(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con todos los documentos que el sistema presenta por módulo en colaboradores");
        
        Map<String, Object> map = new HashMap<>();
        
            try {
                List<Modulo> modulos = agremiadoService.getModulos();            
                Map<String,List<TipoDocumento>> mapDocsPorModulo = new HashMap<>();
                modulos.stream().forEach((modulo) -> {
                    mapDocsPorModulo.put(modulo.getNombre(), agremiadoService.getTipoDocumento(modulo));
            });
            map.put("modulos", modulos);
            map.put("docPorModulos", mapDocsPorModulo);
            return new ModelAndView("sistema/documentosDisponibles", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con los documentos del sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los documentos del sistema");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar los datos de un documento
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/editar-documento.htm",method = RequestMethod.POST)
    public ModelAndView getVistaEditarDocumento(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la edición de un documento");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Integer idDocumento = Integer.parseInt( request.getParameter("tipoDoc") );
            TipoDocumento tipoDocumento = agremiadoService.getTipoDocumento(idDocumento);
            if(tipoDocumento != null){
                map.put("documento", tipoDocumento);
                List<Modulo> modulos = agremiadoService.getModulos();
                map.put("modulos", modulos);
                map.put("catalogoDocumental",catalogoDocumentalService.getCatalogoDocumental());
                map.put("edicion", true);
                //Se obtienen toso los esquemas de pago y se asignan a una variable de tipo List<EsquemaPago>
                List<EsquemaPago> esquemasDePago = agremiadoService.getEsquemaDePagos();
                //Remueve todos los esqumas seleccionados
                esquemasDePago.removeAll(tipoDocumento.getEsquemaPagos());
                //Mapea los resultados de esquemasDePago
                map.put("esquemas",esquemasDePago);
                return new ModelAndView("sistema/registrarNuevoDocumento", "model", map);
            }            
            setInformacionEnVentana( map,2,"Sistema","No se encontró el documento solicitado");
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la edición de un documento  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la edición de un documento");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de recibir los datos de un documento ya sea para ser ingresado o actualizado
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/datos-del-documento.htm",method = RequestMethod.POST)
    public ModelAndView setDatosDocumento(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se reciben los datos de un documento");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                    String id = request.getParameter("id");
                    String nombre = request.getParameter("nmbrDlDcmnt");
                    String req = request.getParameter("requerido");
                    String sta = request.getParameter("estatus");
                    Short modulo = Short.parseShort( request.getParameter("bccndlRchv") );
                    Integer cat = Integer.parseInt(request.getParameter("catDocumental"));
                    
                    TipoDocumento tipoDocumento;
                    
                    String movimiento;
                    if(id.isEmpty()){
                        movimiento = "Inserción";
                        tipoDocumento = new TipoDocumento();
                        tipoDocumento.setCreado( new java.util.Date() );
                    }else{
                        movimiento = "Actualización";
                        Integer idTipo = Integer.parseInt(id);
                        tipoDocumento = agremiadoService.getTipoDocumento(idTipo);
                    }
                    tipoDocumento.setNombreDocumento(nombre);
                    tipoDocumento.setModuloObj(modulo);
                    CatalogoDocumental catalogoDocumental = catalogoDocumentalService.getCatalogoDocumental(cat);
                    tipoDocumento.setObligatorio( req!=null );
                    tipoDocumento.setStatus(  sta != null );
                    tipoDocumento.setModificado( new java.util.Date() );
                    tipoDocumento.setCatalogoDocumentalObj(catalogoDocumental);
                    //Arreglo de string que guarda los datos del id de la vista destinoEsquemas
                    String esquemasPago[] = request.getParameterValues("destinoEsquemas");
                    List<EsquemaPago> esquemasDePago = new ArrayList<>(); //Se declará arreglo de tipo EsquemaPago
                    //Valida que de lo que se esta recibiendo de parametro sea diferente a null y que su longitud sea mayor a 0
                    if(esquemasPago!=null&&esquemasPago.length>0){
                        //Recorre cada elemento de esquemasPago
                        for (String esqPago : esquemasPago) {
                         try {
                             //Se le asigna a la variable esquemaPago de tipo EsquemaPago el valor del método getEsquemaPago 
                                EsquemaPago esquemaPago = agremiadoService.getEsquemaPago(Short.parseShort(esqPago));
                                //Se agrega a al arreglo esquemasDePago el valor de esquemaPago
                                esquemasDePago.add(esquemaPago);
                            } catch (NumberFormatException nfe ){
                                LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de convertir uno de los Esquemas de pago '"+esqPago+"' --> "+nfe.getMessage(),nfe);
                            }
                        }
                    }
                    //Se manda el valor de esquemasDePago al método setEsquemaPagos del pojoh EsquemaPago
                    tipoDocumento.setEsquemaPagos(esquemasDePago);
          catalogoDocumentalService.setTipoDocumento(tipoDocumento);
                    setInformacionEnVentana( map,0,"Sistema","La información del documento se guardo con éxito.");
                    setBitacora(movimiento, "Registró con éxtio la información de un documento", request);        
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de ingresar los datos de un documento  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de ingresar los datos de un documento");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
      
    /**
     * Controller encargado de mostrar la vista para la consulta de las incidencias revisadas de un cliente en especifico
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/incidencias-revisadas.htm", method = RequestMethod.GET)
    public ModelAndView getVistaIncidenciasRevisadas(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista de las incidencias revisadas");
        
            Map<String,Object> map = new HashMap<>();
        
            try {
                    List<Cliente> clientes = getClientesDelUsuario();
                    map.put("clientes", clientes);
                    map.put("muestraTabla", false);
                    map.put("revisadas", true);
                    return new ModelAndView("sistema/listaDeIncidencias", "model", map);
                
            } catch (Exception e) {
                LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar obtener las incidencias revisadas  -> "+e);
                setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar obtener las incidencias revisadas.");
            }
            return new ModelAndView("sistema/sistema", "model", map);    
    }
    
    /**
     * Controller encargado de mostrar la incidencias revisadas de un cliente
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/incidencias-revisadas-del-cliente.htm", method = RequestMethod.POST)
    public ModelAndView getIncidenciasRevisadasDelCliente(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicitan las incidencias revisadas de un cliente ");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                Integer idCliente = Integer.parseInt(request.getParameter("cliente"));
                String DesdeString = request.getParameter("fchDsd");
                String HastaString = request.getParameter("fchHst");
                Date Desde = procesayyyyMMdd(DesdeString);
                Date Hasta = procesayyyyMMdd(HastaString);
                if(Desde.equals(Hasta) || Desde.before(Hasta)){
                    Cliente cliente = clienteService.getCliente(idCliente);
                    List<Incidencia> incidencias = incidenciaService.getIncidenciasFechaIncidencia(cliente, Desde, Hasta, true);
                    List<TipoIncidencia> tipoIncidencias = incidenciaService.getTipoIncidencias();
                    map.put("cliente", cliente);
                    map.put("incidencias", incidencias);
                    map.put("tipoIncidencias", tipoIncidencias);
                    map.put("muestraTabla", true);
                    map.put("revisadas", true);
                }else{                    
                    setInformacionEnVentana( map,1,"Sistema","El periodo final de busqueda es menor al perido inicial de busqueda.");
                    map.put("muestraTabla", false);
                    map.put("revisadas", true);
                }
                map.put("desde", DesdeString);
                map.put("hasta", HastaString);
                List<Cliente> clientes = getClientesDelUsuario();
                map.put("clientes", clientes);
                return new ModelAndView("sistema/listaDeIncidencias", "model", map);
        } catch (NumberFormatException | ParseException e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar obtener las incidencias revisadas -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar obtener las incidencias revisadas.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista para consultar las incidencias sin revisar de un cliente en especifico
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/incidencias-sin-revisar.htm", method = RequestMethod.GET)
    public ModelAndView getVistaIncidenciasSinRevisar(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista de las incidencias sin revisar");
        
            Map<String,Object> map = new HashMap<>();
        
            try {
                    List<Cliente> clientes = getClientesDelUsuario();
                    map.put("clientes", clientes);
                    map.put("muestraTabla", false);
                    map.put("revisadas", false);
                    return new ModelAndView("sistema/listaDeIncidencias", "model", map);
                
            } catch (Exception e) {
                LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar obtener las incidencias sin revisión  -> "+e);
                setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar obtener las incidencias.");
            }
            return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar las incidencias sin revisar de un cliente en especifico
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/incidencias-sin-revisar-del-cliente.htm", method = RequestMethod.POST)
    public ModelAndView getIncidenciasSinRevisar(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicitan las incidencias sin revisar de un cliente ");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Integer idCliente = Integer.parseInt(request.getParameter("cliente"));
            Cliente cliente = clienteService.getCliente(idCliente);
            List<Incidencia> incidencias = incidenciaService.getIncidencias(cliente, false);
            List<TipoIncidencia> tipoIncidencias = incidenciaService.getTipoIncidencias();
            map.put("cliente", cliente);
            map.put("incidencias", incidencias);
            map.put("tipoIncidencias", tipoIncidencias);
            List<Cliente> clientes = getClientesDelUsuario();
            map.put("clientes", clientes);
            map.put("muestraTabla", true);
            map.put("revisadas", false);
            return new ModelAndView("sistema/listaDeIncidencias", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar obtener las incidencias sin revisión  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar obtener las incidencias.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de recoger la incidencia sin revisar de un cliente
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/recoger-incidencia.htm",method = RequestMethod.POST)
    public ModelAndView recogerIncidencia(HttpServletRequest request, HttpServletResponse response){ 
                LOGGER.info("[Controller, Sistema] Se recoge una incidencia ");      
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                Integer idCliente = Integer.parseInt(request.getParameter("idCliente"));
                Integer idAgremiado = Integer.parseInt(request.getParameter("idAgremiado"));
                Integer idIncidencia = Integer.parseInt(request.getParameter("idIncidencia"));
                Short tipoIncidenciaObj = Short.parseShort(request.getParameter("tipoIncidenciaObj"));
                LOGGER.info("[Controller, Sistema] Se recoge la incidencia ["+idCliente+','+idAgremiado+','+idIncidencia+','+tipoIncidenciaObj+']');
                Incidencia inicidencia = incidenciaService.getInicidencia(idCliente, idAgremiado, idIncidencia, tipoIncidenciaObj);
                if(inicidencia != null){
                    if(!inicidencia.getStatus()){
                        inicidencia.setStatus(true);
                        inicidencia.setFechaModificacion( new Date() );
                        Integer setIncidencia = incidenciaService.setIncidencia(inicidencia);
                        if(setIncidencia != null ){                            
                            setInformacionEnVentana( map,0,"Sistema","La incidencia fue revisada exitosamente.");
                            setBitacora("Actuallización", "Recogío la incidencia "+inicidencia.getIdIncidencia()+" exitosamente.", request);
                        }else{                            
                            setInformacionEnVentana( map,1,"Sistema","La incidencia <b>NO</b> fue revisada.");
                        }
                    }else{                        
                        setInformacionEnVentana( map,2,"Sistema","La incidencia ["+inicidencia.getIdIncidencia()+"] no puede ser recogida"
                                + "<br>Estado : <b>"+inicidencia.getStatus()+"</b>.");
                    }
                }else{                    
                    setInformacionEnVentana( map,1,"Sistema","UPS!!!<br>La incidencia no fue encontrada.");
                }                
                Cliente cliente = clienteService.getCliente(idCliente);
                List<Incidencia> incidencias = incidenciaService.getIncidencias(cliente, false);
                List<TipoIncidencia> tipoIncidencias = incidenciaService.getTipoIncidencias();
                map.put("cliente", cliente);
                map.put("incidencias", incidencias);
                map.put("tipoIncidencias", tipoIncidencias);
                List<Cliente> clientes = getClientesDelUsuario();
                map.put("clientes", clientes);
                map.put("muestraTabla", true);
                map.put("revisadas", false);
                return new ModelAndView("sistema/listaDeIncidencias", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar establecer la revisión de la incidencia  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar establecer la revisión de la incidencia.");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista para consultar las nóminas registradas de dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/nominas-registradas.htm",method = RequestMethod.GET)
    public ModelAndView getVistaNominasRegistradas(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para consultar las nominas registradas");
        
        Map<String,Object> map = new HashMap<String, Object>();
        
        try {
            List<Cliente> clientes = getClientesDelUsuario();
            map.put("clientes", clientes);
            map.put("verDetallesDeNomina", false);
            return new ModelAndView("sistema/nominasRegistradas", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la consulta de nominas registradas  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la consulta de nóminas registradas");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
       
    /**
     * Controller encargado de mostrar la nómina registrada de un cliente 
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/vista-general-de-nomina.htm",method = RequestMethod.POST)
    public ModelAndView getVistaGeneralDeNomina(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la vista detallada de una nómina");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                Integer idCliente = Integer.parseInt(request.getParameter("cliente"));
                String diaRegistroDesdeString = request.getParameter("diaRegistroDesde");
                String diaRegistroHastaString = request.getParameter("diaRegistroHasta");
                
                Date diaRegistroDesde = procesayyyyMMdd(diaRegistroDesdeString);
                Date diaRegistroHasta = procesayyyyMMdd(diaRegistroHastaString);
                Cliente cliente = clienteService.getCliente(idCliente);
                if(cliente != null){
                    List<Recibo> recibos = reciboService.getRecibosPeriodo(cliente, diaRegistroDesde,diaRegistroHasta);
                    if(recibos.isEmpty()){                          
                        map.put("verDetallesDeNomina", false);
                        setInformacionEnVentana( map,2,"Sistema","No se encontrarón recibos registrados para el cliente <b>"+cliente.getNombreEmpresa()+"</b> en el periódo <b>"+diaRegistroDesdeString+"<b/> al <b>"+diaRegistroHastaString+"</b>.");    
                    }else{
                            map.put("recibos", recibos);
                            map.put("cliente", cliente);
                            map.put("fechaA", diaRegistroDesde);
                            map.put("fechaB", diaRegistroHasta);
                            map.put("fechaAString", procesayyyyMMdd(diaRegistroDesde));
                            map.put("fechaBString", procesayyyyMMdd(diaRegistroHasta));
                            map.put("verDetallesDeNomina", true);
                            setInformacionEnVentana( map,2,"Sistema","Recibos registrados para el cliente <b>"+cliente.getNombreEmpresa()+"</b> <br><br><b>Toma en cuenta que esto puede demorar un poco. <b/> "); 
                    }
                    List<Cliente> clientes = getClientesDelUsuario();
                    map.put("clientes", clientes);
                    return new ModelAndView("sistema/nominasRegistradas", "model", map);
                }else{
                    LOGGER.error("[Controller, Sistema] No se encontró ningun cliente [ "+idCliente+']');
                    setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de buscar el cliente ");                    
                }
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con el detalle de una nomina registrada  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con el detalle de una nomina registrada ");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de enviar los recibos por correo electrónico de una nómina registrada 
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value="sistema/enviar-recibos-email.htm",method = RequestMethod.POST)
    public void enviarRecibosPorCorreo(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la generación de los recibos en formato PDF");        
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
                         LOGGER.error("[Controller, Sistema] No se encontró la información de los recibos {"+recibos.size()+"} solicitados, Imposible generar archivo");
                    }
                } 
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de generar el PDF con los recibos   -> "+e);
        }
    }
    
    /**
     * Controller encargado crear los recibos de nómina registrados de un cliente y empaquetarlos en un archivo zip
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value="sistema/generar-recibos-pdf-zip.htm",method = RequestMethod.POST)
    public void generarPDFDeLosRecibosEnZip(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la generación de los recibos en formato PDF");
        
        try {
             Integer idCliente = Integer.parseInt(request.getParameter("cliente"));
                String diaRegistroDesdeString = request.getParameter("diaRegistroDesde");
                String diaRegistroHastaString = request.getParameter("diaRegistroHasta");
        
                Date diaRegistroDesde = procesayyyyMMdd(diaRegistroDesdeString);
                Date diaRegistroHasta = procesayyyyMMdd(diaRegistroHastaString);
                
                Cliente cliente = clienteService.getCliente(idCliente);
                if(cliente != null){
                    List<Recibo> recibos =  reciboService.getRecibosPeriodo(cliente, diaRegistroDesde,diaRegistroHasta);
                     if(recibos.isEmpty()){                          
                        LOGGER.error("[Controller, Sistema] No se pueden generar recibos en PDF ya que no fue encontrado ningún recibo");
                    }else{                        
                        String correo = SecurityContextHolder.getContext().getAuthentication().getName(); //
                        String del = formatoFechaCorto(diaRegistroDesde); //Se formatea la fecha diaRegistroDesde por dd/MM/yyyy 
                        String al = formatoFechaCorto(diaRegistroHasta); //Se formatea la fecha diaRegistroHasta por dd/MM/yyyy
                        boolean reciboDeNominaZIp = notificacionesService.reciboDeNominaZIp(correo, recibos, cliente, diaRegistroDesdeString, diaRegistroHastaString); //Se guarda en una variable boolean si el recibo de nomina a sido procesado y enviado
                        if(reciboDeNominaZIp){ //Se valida que reciboDeNominaZIp regrese true
                            String subject ="RECIBOS ZIP DE "+cliente.getNombreEmpresa().toUpperCase()+" DEL "+del+" AL "+al+'.'; //Se construye el detalle con el nombre de la empresa y el rango de fechas con formato simple
                            setBitacora("Envío de correo", subject, request); //Se inserta en la tabla Bitacora el moviemnto de envio de correo 
                        }
                    }
                } 
        } catch (NumberFormatException | ParseException e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de generar el PDF con los recibos en zip   -> "+e);
        }
    }
    
    /**
     * Controller encargado de crear un solo archivo pdf con los recibos de una nómina registrada en el sistema
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value="sistema/generar-recibos-pdf.htm",method = RequestMethod.POST)
    public void generarPDFDeLosRecibos(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la generación de los recibos en formato PDF");
        
        try {
                Integer idCliente = Integer.parseInt(request.getParameter("cliente"));
                String diaRegistroDesdeString = request.getParameter("diaRegistroDesde");
                String diaRegistroHastaString = request.getParameter("diaRegistroHasta");
                        
                Date diaRegistroDesde = procesayyyyMMdd(diaRegistroDesdeString);
                Date diaRegistroHasta = procesayyyyMMdd(diaRegistroHastaString);
                
                Cliente cliente = clienteService.getCliente(idCliente);
                if(cliente != null){
                    List<Recibo> recibos = reciboService.getRecibosPeriodo(cliente, diaRegistroDesde,diaRegistroHasta);
                    if(recibos != null){    
                        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                        String del = formatoFechaCorto(diaRegistroDesde); //Se formatea la fecha diaRegistroDesde por dd/MM/yyyy
                        String al = formatoFechaCorto(diaRegistroHasta); //Se formatea la fecha diaRegistroHasta por dd/MM/yyyy
                        boolean reciboDeNomina = notificacionesService.reciboDeNomina(correo, recibos, cliente, diaRegistroDesdeString, diaRegistroHastaString); //Se guarda en una variable boolean si el recibo de nomina a sido procesado
                        if(reciboDeNomina){ //Se valida que reciboDeNominaZIp regrese true
                            String subject ="RECIBOS PDF DE "+cliente.getNombreEmpresa().toUpperCase()+" DEL "+del+" AL "+al+'.'; //Se construye el detalle con el nombre de la empresa y el rango de fechas con formato simple
                            setBitacora("Envío de correo", subject, request); //Se inserta en la tabla Bitacora el moviemnto de envio de correo
                        }
                    }else{            
                         LOGGER.error("[Controller, Sistema] No se encontró la información de los recibos {"+recibos.size()+"} solicitados, Imposible generar archivo");
                    }
                } 
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de generar el PDF con los recibos   -> "+e);
        }
    }
    
    /**
     * Controller encargado de mostrar la vista con el detalle de un recibo de nómina registrado dentro del sistema
     * @param idRecibo Numero entero que contiene el id de un recibo
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/detalle-del-recibo/{idRecibo}.htm",method = RequestMethod.GET)
    public ModelAndView getDetallesDelRecibo(@PathVariable("idRecibo") Integer idRecibo){
        LOGGER.info("[Controller, Sistema] Se solicita la vista detallada de un recibo ["+idRecibo+"]");
        
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
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de generar la vista detallada de un recibo ["+idRecibo+"]  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de generar la vista detallada de un recibo");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargardo de enviar un solo recibo de nómina por pdf
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value="sistema/enviar-recibo-por-email.htm",method = RequestMethod.POST)
    public void enviarReciboPorCorreo(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita el envío de un solo recibo por correo electrónico");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Integer IdRecibo = Integer.parseInt(request.getParameter("idRecibo"));
            Recibo recibo = reciboService.getRecibo(IdRecibo);
            String correo = SecurityContextHolder.getContext().getAuthentication().getName(); // Obtiene el correo de la sesión actual
            Usuario usuario = usuarioService.getUsuario(correo); //Obtiene el Usuario a partir del correo de la sesión actual
            
            if(recibo != null){                
                notificacionesService.reciboDeNomina(recibo, usuario); //Se reajusta la firma de reciboDeNomina para que reciba el parametro de usuario
            }            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de enviar un recibo por correo electrónico -> "+e);
        }
    }
    
    /**
     * Controller encargado de mostrar el archivo pdf de un recibo de nómina registado en el sistema
     * @param idRecibo Numero entero que contiene el id de un recibo
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value="sistema/ver-recibo-pdf/{idRecibo}.htm", method = RequestMethod.GET)
    public void getReciboPDF(@PathVariable("idRecibo")Integer idRecibo, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la generación del archivo(PDF) de un solo recibo");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Recibo recibo = reciboService.getRecibo(idRecibo);
            if(recibo != null){                
                ByteArrayOutputStream reciboSindical = pDFiTextService.getReciboSindical(recibo);
                response.setHeader("Content-Disposition", "inline;filename=\"Recibo "+recibo.getIdRecibo()+".pdf\"");
                // setting the content type
                response.setContentType("application/pdf");
                // the contentlength
                response.setContentLength(reciboSindical.size());
                try ( // write ByteArrayOutputStream to the ServletOutputStream
                    OutputStream os = response.getOutputStream()) { 
                    reciboSindical.writeTo(os);
                    os.flush();
                }
            }else{            
                 LOGGER.error("[Controller, Sistema] No se encontró la información del recibo{"+idRecibo+"} solicitado Imposible generar archivo");
            }
            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de generar el archivo de un recibo{"+idRecibo+"}  -> "+e);
        }
    }
    
    /**
     * Controller encargado de devolver la vista para la edición de los recibos ingresados previamente
     * @return ModelAndView
     */
     @RequestMapping(value = {"sistema/editar-recibos-registrados.htm","sistema/editar-recibos-nomina.htm"}, method = RequestMethod.GET)
     public ModelAndView getEditarRecibos(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para editar una nomina registrada");
        
        Map<String,Object> map = new HashMap<String, Object>();
        
        try {
            List<Cliente> clientes = getClientesDelUsuario();
            map.put("clientes", clientes);      
            List<Sindicato> sindicatos = sindicatoService.getSindicatos();
            map.put("sindicatos", sindicatos);                              
            map.put("actualizacion", true);
            return new ModelAndView("sistema/editarRecibosRegistrados", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la editar una nómina registrada  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la edición de nómina registrada");
        }
        return new ModelAndView("sistema/sistema", "model", map);
     }
    
    /**
     * Controller encargado de mostrar la vista para registrar un nuevo rol dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/nuevo-rol.htm",method = RequestMethod.GET)
    public ModelAndView getVistaNuevoRol(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para agregar un nuevo rol");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            map.put("edicion", false);
            List<Permiso> permisosBDD = usuarioService.getPermisos();    
            map.put("permisos", permisosBDD);
            return new ModelAndView("sistema/nuevoRol", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para ingresar un nuevo rol  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para ingresar un nuevo rol");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar todos lo roles registrados dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/todos-los-roles.htm", method = RequestMethod.GET)
    public ModelAndView getTodosLosRoles(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con todos los roles existentes dentro del sistema");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                List<Rol> roles = usuarioService.getRoles();
                map.put("roles", roles);                
                return new ModelAndView("sistema/roles", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con todos los roles  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con con todos los roles que se encuentran registrados dentro del sistema");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar el formulario para la edición de un rol de sistema
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/edicion-de-rol.htm",method = RequestMethod.POST)
    public ModelAndView getEditarRol(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistemas] Se solicita la vista para la edición de un rol");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
                Integer idRol = Integer.parseInt(request.getParameter("rol"));
                
            Rol rol = usuarioService.getRol(idRol);
            map.put("rol", rol);
            map.put("edicion", true);
            List<Permiso> permisosBDD = usuarioService.getPermisos();
            for (Permiso permisoRol : rol.getPermisoList()) {
                for (Permiso permiso : permisosBDD) {
                    if(permisoRol.getIdPermiso().equals(permiso.getIdPermiso())){
                        permisosBDD.remove(permiso);
                        break;
                    }
                }
            }                
            map.put("permisos", permisosBDD);
            
            return new ModelAndView("sistema/nuevoRol", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la edición de un rol  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista  para la edición de un rol ");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    
    }
    
    /**
     * Controller encargado de recibir los datos de un nuevo rol o de la edción de rol para almacenarlos dentro de la base de datos
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/datos-del-rol.htm",method = RequestMethod.POST)
    public ModelAndView setDatosDelRol(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller,Sistema] Se reciben los datos de un rol");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            String id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String permisos[] = request.getParameterValues("destinoPermisos");
            
            Rol rol;
            if(id.isEmpty()){
                rol = new Rol();
            }else{
                Integer idRol = Integer.parseInt(id);
                rol = usuarioService.getRol(idRol);                
            }
            rol.setNombre(nombre);
            List<Permiso> permisosBDD = usuarioService.getPermisos();
            List<Permiso> permisosDelRol = new LinkedList<>();
            for (String permiso : permisos) {
                Short p =  Short.parseShort(permiso);
                permisosBDD.stream().filter((permisoBDD) -> (p.equals(permisoBDD.getIdPermiso()))).forEach((permisoBDD) -> {
                    permisosDelRol.add(permisoBDD);
                });
            }
            rol.setPermisoList(permisosDelRol);
            usuarioService.setRol(rol);
            LOGGER.info("[Controller,Sistema] Datos del rol registrado "+rol);            
            List<Rol> roles = usuarioService.getRoles();
            map.put("roles", roles); 
            String msj = "Se ingresaron correctamente los datos del Rol"
                    +"<br><b>Nombre</b>: "+rol.getNombre()
                    +"<br><b>Número de permisos</b>: "+rol.getPermisoList().size();
            setInformacionEnVentana( map,0,"Sistema",msj);       
            setBitacora("Actualización", "Ingreso los datos del rol "+rol.getNombre().toUpperCase()+".", request);
            return new ModelAndView("sistema/roles", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de ingresar los datos de un rol  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de ingresar los datos de un rol ");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista para el registro de un nuevo usuario dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/registro-de-un-usuario.htm",method = RequestMethod.GET)
    public ModelAndView getNuevoUsuario(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para el registro de un nuevo usuario");
        
        Map<String, Object> map = new HashMap<>();
        
        try {
                List<Rol> roles = usuarioService.getRoles();
                map.put("roles", roles);
                map.put("edicion", false);
                List<Cliente> clientes = getClientesDelUsuario();
                map.put("clientes", clientes);
                return new ModelAndView("sistema/registroUsuario", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para el registro de un usuario  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para el registro de un usuario");
        }
        getAllUsuarios(map);
        return new ModelAndView("sistema/usuarios", "model", map);
    }
    
    /**
     * Controller encargado de mostrar todos los usuarios registrados dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/todos-los-usuarios.htm",method = RequestMethod.GET)
    public ModelAndView getUsuarios(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con todos los usuarios registrados dentro del sistema");
        
        Map<String,Object> map =  new HashMap<String,Object>();
        
        try {
            getAllUsuarios(map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con los usuarios del sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los usuarios del sistema");
            return new ModelAndView("sistema/sistema", "model", map);
        }
        return new ModelAndView("sistema/usuarios", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista para editar las propiedades de un usuario
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/editar-usuario.htm", method = RequestMethod.POST)
    public ModelAndView getVistaEditarUsuario(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para editar un usuario");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            
            Integer idUsuario = Integer.parseInt( request.getParameter("sr") );
            Usuario usuario = usuarioService.getUsuario( new Usuario(idUsuario));
            if(usuario != null ){
                List<Rol> roles = usuarioService.getRoles();
                map.put("roles", roles);
                map.put("usuario", usuario);
                map.put("edicion", true);
                List<Cliente> clientes = getClientesDelUsuario();
                map.put("clientes", clientes);
                return new ModelAndView("sistema/registroUsuario", "model", map);
            }else{
                LOGGER.info("[Controller, Sistema] No se encontró ninguna información sobre algún usuario ["+idUsuario+"]");
                setInformacionEnVentana( map,2,"Sistema","No se encontró información sobre el usuario");
            }            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la edición de un usuario  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la edición del usuario");
        }
        getAllUsuarios(map);
        return new ModelAndView("sistema/usuarios", "model", map);
    }
    
    /**
     * Controller encargado de almacenar los datos de un usuario
     * @param usuario Instancia de usuario
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/datos-de-usuario.htm", method = RequestMethod.POST)
    public ModelAndView setDatosDelUsuario(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] se reciben los datos del usuario {"+usuario.getIdUsuario()+", "+usuario.getNombre()+", "+usuario.getCorreoElectronico()+", "+usuario.getRol()+'}');
        
        Map<String,Object> map = new HashMap<>();
        try {
            
            String idClientes = request.getParameter("clntsPrGrgr");
            String movimiento;
            if(usuario.getIdUsuario() != null){
                movimiento = "Actualización";
                Usuario usuarioBDD = usuarioService.getUsuario( usuario );
                usuario.setContrasena( usuarioBDD.getContrasena() );
                usuario.setBitacoraList(usuarioBDD.getBitacoraList());
                usuario.setCreado( usuarioBDD.getCreado() );
                usuario.setNotificacionesList( usuarioBDD.getNotificacionesList() );
                usuario.setStatus(usuarioBDD.getStatus()); 
                
                usuario.setClienteList(usuarioBDD.getClienteList());
            }else{
                Usuario usuarioCorreoNoExiste = usuarioService.getUsuario(usuario.getCorreoElectronico());
                if(usuarioCorreoNoExiste == null ){
                    movimiento = "Inserción";
                    usuario.setCreado( new java.util.Date());
                    List<Notificaciones> notificaciones = usuarioService.getNotificaciones();
                    usuario.setNotificacionesList( notificaciones );
                    usuario.setStatus(true);
                    usuario.setContrasena(nuevaContrasena());
                }else{
                    setInformacionEnVentana( map,2,"Sistema","El <b>correo electrónico</b> ya se encuentra registrado, no es posible volver a utilizarlo en esta opción del sistema.");
                    getAllUsuarios(map);
                    return new ModelAndView("sistema/usuarios", "model", map);
                }                
            }
            List<Cliente> clientes;
            if(usuario.getClienteList() != null){
                clientes = usuario.getClienteList();
            }else{
                clientes = new LinkedList<Cliente>();
            }
            
            if(!idClientes.isEmpty()){
                String[] split = idClientes.split(",");
                for (String idCliente : split) {
                    int idClienteInt = Integer.parseInt(idCliente);
                    Cliente clienteTemp = clienteService.getCliente(idClienteInt);
                    for (Cliente temp : clientes) {
                        if(Objects.equals(clienteTemp.getIdCliente(), temp.getIdCliente())){
                            clienteTemp = null;
                            break;
                        }
                    }
                    if(clienteTemp!=null){
                        clientes.add(clienteTemp);
                    }
                }
            }
            usuario.setClienteList(clientes);
            usuario.setModificado( new java.util.Date() );
            usuarioService.setUsuario( usuario );
            if(usuario.getIdUsuario() != null){
                LOGGER.info("[Controller, Sistema] Se ingresó correctamente la información de un usuario "+usuario+" Se procede a relacionar el usuario con los clientes indicados en caso de ser requerido");
                for (Cliente cliente : clientes) {
                    List<Usuario> usuarioList = cliente.getUsuarioList();
                    boolean usuarioEncontrado =  false;
                    for ( Usuario usuariofor : usuarioList) {
                        if(Objects.equals(usuario.getIdUsuario(), usuariofor.getIdUsuario())){
                            usuarioEncontrado = true;
                            break;
                        }
                    }
                    if (!usuarioEncontrado) {
                        usuarioList.add(usuario);
                        cliente.setUsuarioList(usuarioList);
                        LOGGER.info("[Controller, Sistema] Intentando actualizar la lista de usuarios del cliente "+cliente.getNombreEmpresa()+" con el usuario "+usuario);
                        Integer setCliente = clienteService.setCliente(cliente);
                        if(setCliente != null){
                            LOGGER.info("[Controller, Sistema] Se actualizó la lista de usuarios del cliente "+cliente);
                        }
                    }
                }
                String stt = (usuario.getStatus())?"Activo":"Inactivo";
                String msj = "Se Ingresó correctamente la información del usuario"
                        + "<br><b>Nombre</b>: "+usuario.getNombre()
                        + "<br><b>Correo Electrónico</b>: "+usuario.getCorreoElectronico()
                        + "<br><b>Status</b>: "+stt;
                setInformacionEnVentana( map,0,"Sistema",msj);
                setBitacora(movimiento, "Ingresó la información del usuario "+usuario.getNombre().toUpperCase()+".", request);
                if(movimiento.equalsIgnoreCase("Inserción")){
                    notificacionesService.nuevoUsuario(usuario);
                }
            }else{
                LOGGER.error("[Controller, Sistema] No fue posible ingresar la información correspondiente al usuario "+usuario);
                setInformacionEnVentana( map,1,"Sistema","No fue posible ingresar la información correspondiente al usuario ");
            }
        } catch (Exception e) { 
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de ingresar los datos de un usuario {"+usuario+"} -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de ingresar los datos del usuario");
        }
        getAllUsuarios(map);
        return new ModelAndView("sistema/usuarios", "model", map);
    }
    
    /**
     * Controller encargado de quitar un cliente a un usuario de sistema.
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/quitar-un-cliente-al-usuario.htm",method = RequestMethod.POST)
    public ModelAndView removerCliente(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita remover un cliente de un usuario");
        
        Map<String,Object> map = new HashMap<>();
        Integer idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
        
        try {
            Usuario usuario = usuarioService.getUsuario( new Usuario(idUsuario));
            Cliente cliente = clienteService.getCliente(idCliente);
            List<Cliente> clienteList = usuario.getClienteList();
            for (Cliente clienteTemp: clienteList) {
                if( Objects.equals(clienteTemp.getIdCliente(), idCliente) ){
                    boolean remove = clienteList.remove(clienteTemp);
                    if(remove){
                        LOGGER.info("[Controller, Sistema] El cliente "+clienteTemp.getNombreEmpresa()+" se esta removiendo del usuario ["+usuario.getIdUsuario()+", "+usuario.getCorreoElectronico()+"]");
                        break;
                    }
                }
            }
            usuario.setClienteList(clienteList);
            List<Usuario> usuarioList = cliente.getUsuarioList();
            for (Usuario usuarioTemp : usuarioList) {
                if(Objects.equals(usuarioTemp.getIdUsuario(), idUsuario)){
                    boolean remove = usuarioList.remove(usuarioTemp);
                    if(remove){
                        LOGGER.info("[Controller, Sistema] El Usuario["+usuario.getIdUsuario()+", "+usuario.getCorreoElectronico()+"] se esta removiendo del cliente "+cliente.getNombreEmpresa());
                        break;
                    }
                }
            }
            cliente.setUsuarioList(usuarioList);
            clienteService.setCliente(cliente);
            usuarioService.setUsuario(usuario);
            if(usuario!=null){
                List<Rol> roles = usuarioService.getRoles();
                map.put("roles", roles);
                map.put("usuario", usuario);
                map.put("edicion", true);
                List<Cliente> clientes = getClientesDelUsuario();
                map.put("clientes", clientes);
                setInformacionEnVentana(map, 0, "Sistema", "El cliente "+cliente.getNombreEmpresa()+" fue removido con exito del usuario "+usuario.getCorreoElectronico());
                setBitacora("Eliminación", "Removió el cliente "+cliente.getNombreEmpresa()+" del usuario "+usuario.getCorreoElectronico()+".", request);
                return new ModelAndView("sistema/registroUsuario", "model", map);
        }else{
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de obtener los datos del usuario despues de haberle quitado un cliente");
            setInformacionEnVentana( map,1,"Sistema","No fue posible recuperar la vista anterior para continuar con la edición de un usuario");
        }
        getAllUsuarios(map);
        return new ModelAndView("sistema/usuarios", "model", map);
        } catch (Exception e) {            
                    LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de quitarle un cliente["+idCliente+"] al usuario ["+idUsuario+"] --> "+e);
                    setInformacionEnVentana( map,1,"Sistema","No fue posible recuperar correctamente el usuario durante la eliminación del cliente al usuario");
        }        
        getAllUsuarios(map);
        return new ModelAndView("sistema/usuarios", "model", map);
    }
    
    /**
     * Controller encargado de cambiar la contraseña de acceso al sistema de un usuario
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/cambiar-acceso.htm", method = RequestMethod.POST)
    public ModelAndView cambiarAcceso(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita el cambio de contraseña de un usuario");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Integer idUsuario = Integer.parseInt( request.getParameter("sr") );
            Usuario usuario = usuarioService.getUsuario( new Usuario(idUsuario) );
            if(usuario != null ){
                usuario.setContrasena(nuevaContrasena());
                usuario.setModificado( new java.util.Date() );
                usuarioService.setUsuario(usuario);
                setInformacionEnVentana( map,0,"Sistema","Se actualizó la contraseña del usuario"
                        + "<br><b>Nombre</b>: "+usuario.getNombre()
                        + "<br><b>Correo</b>: "+usuario.getCorreoElectronico());
                setBitacora("Actualización", "Solicitó la actualización del la contraseña del usuario "+usuario.getCorreoElectronico().toUpperCase()+".", request);
                notificacionesService.cambioDeContrasena(usuario, false);
            }else{
                LOGGER.error("[Controller, Sistema] No se encontro al usuario ["+idUsuario+"]");
            }            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de intentar actulizar la contraseña de un usuario en el sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los usuarios del sistema");
        }
        getAllUsuarios(map);
        return new ModelAndView("sistema/usuarios", "model", map);
    }
    
    /**
     * Controller encargado de activar o descativar el estatus de un usuario del sistema
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/activar-usuario.htm", method = RequestMethod.POST)
    public ModelAndView cambiarStatus(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita el cambio de status de un usuario");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Integer idUsuario = Integer.parseInt( request.getParameter("sr") );
            Usuario usuario = usuarioService.getUsuario( new Usuario(idUsuario) );
            if(usuario != null ){
                usuario.setStatus(! usuario.getStatus() );
                usuario.setModificado( new java.util.Date() );
                usuarioService.setUsuario(usuario);   
                String stat = (usuario.getStatus() ) ?"Activo":"Inactivo";
                setInformacionEnVentana( map,0,"Sistema","Se Actulizo el estado del Usuario"
                        + "<br><b>Nombre</b>: "+usuario.getNombre()
                        + "<br><b>Correo</b>: "+usuario.getCorreoElectronico()
                        + "<br><b>Status</b>: "+stat);
                setBitacora("Actualización", "Actualizó el estado del usuario "+usuario.getCorreoElectronico().toUpperCase()+" a "+stat+".", request);
            }else{
                LOGGER.error("[Controller, Sistema] No se encontro al usuario ["+idUsuario+"]");
            }            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de intentar actulizar el estado de un usuario en el sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los usuarios del sistema");
        }
        getAllUsuarios(map);
        return new ModelAndView("sistema/usuarios", "model", map);
    }
    
    /**
     * Controller encargado de mostrar el detalle del usuario que se encuentra logueado dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/mi-usuario.htm", method = RequestMethod.GET)
    public ModelAndView getMiUsuario(){
        LOGGER.info("[Controller, Sistema] Se solicita la información del usuario que se encuentra activo en el sistema");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try { 
                 String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                Usuario usuario = usuarioService.getUsuario(correo);
                List<Cliente> clientesDelUsuario = getClientesDelUsuario();
                Rol rol = usuarioService.getRol(usuario.getRol());
                map.put("usuario", usuario);
                map.put("clientes", clientesDelUsuario);
                map.put("rol", rol);
                return new ModelAndView("sistema/miUsuario", "model", map);
        } catch (Exception e){
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con las opciones del sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los detalles del usuario ...");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller ecargado de mostrar la vista para el registro de un nueva zona salarial
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/registrar-zona-salarial.htm", method = RequestMethod.GET)
    public ModelAndView ingresarZonaSalarial(){
           LOGGER.info("[Controller, Sistema] Se solicita la vista para el ingreso de una nueva zona salarial");
        
           Map<String,Object> map = new HashMap<String,Object>();
           try {
                map.put("edicion", false);
           } catch (Exception e) {
               LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de establecer las propiedades dentro de la vista para una nueva zona salarial  -> "+e);
                setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible crear la vista para ingresar una nueva zona salarial");
                return new ModelAndView("sistema/sistema", "model", map);              
           }
           return new ModelAndView("sistema/registroZonaSalarial", "model", map);
    }
    
    /**
     * Controller ecargado de mostrar la vista para el registro de un nuevo período al fondo de ahorro
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/registrar-periodo-fondo-ahorro.htm", method = RequestMethod.GET)
    public ModelAndView ingresarPeriodoFondoAhorro(){
           LOGGER.info("[Controller, Sistema] Se solicita la vista para el ingreso de un nuevo período al fondo de ahorro");
        
           Map<String,Object> map = new HashMap<String,Object>();
           try {
                List<Cliente> clientes = getClientesDelUsuario();
                List<Contratista> contratistas = contratistaService.getContratistas() ;
                map.put("clientes", clientes);
                map.put("contratistas", contratistas);
                map.put("edicion", false);
           } catch (Exception e) {
               LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de establecer las propiedades dentro de la vista para un nuevo período al fondo de ahorro  -> "+e);
                setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible crear la vista para ingresar un nuevo período al fondo de ahorro");
                return new ModelAndView("sistema/sistema", "model", map);              
           }
           return new ModelAndView("sistema/registroPeriodoFA", "model", map);
    }
    
    /**
     * Controller encargado de mostrar todas la zonas salariales registradas dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/zonas-salariales.htm", method = RequestMethod.GET)
    public ModelAndView getTodasLasZonasSalariales(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con todas las zonas salariales registradas dentro del sistema");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            getAllZonas(map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un error al momento de crear la vista con todas las zonas salariales  -> "+e);
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible recuperar las zonas salariales del sistema");
            return new ModelAndView("sistema/sistema", "model", map);
        }
        return new ModelAndView("sistema/zonasSalariales", "model", map);
    }
    
    /**
     * Controller encargado de mostrar todas la zonas salariales registradas dentro del sistema
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/periodos-fondo-ahorro.htm", method = RequestMethod.GET)
    public ModelAndView getTodosLosPeriodosDelFondoAhorro(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista con todos los periodos de fondo de ahorro registradas dentro del sistema");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            getAllPeriodos(map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un error al momento de crear la vista con todos los periodo del fondo de ahorro  -> "+e);
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible recuperar los periodos del fondo de ahorro del sistema");
            return new ModelAndView("sistema/sistema", "model", map);
        }
        return new ModelAndView("sistema/periodosFondoAhorro", "model", map);
    }
    
    /**
     * Controller encargado de mostrar las propiedades editables de una zona salarial
     * @param idZona Numero entero corto que contiene el id de un zona salarial
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/editar-zona-salarial/{idZona}.htm",method = RequestMethod.GET)
    public ModelAndView editarZonaSalarial(@PathVariable("idZona") Short idZona){
        LOGGER.info("[Controller,Sistema] Se solicita la vista para editar una zona salarial ["+idZona+"]");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            ZonaSm zonaSM = zsmService.getZonaSM(idZona);
            if(zonaSM != null){
                map.put("zona", zonaSM);
                map.put("edicion", true);
            }else{
                LOGGER.error("[Controller,Sistema] No se encontró zona salarial ["+idZona+"]");
                getAllZonas(map);
                setInformacionEnVentana(map, 1, "Sistema", "No fue posible recuperar la zonas salarial del sistema");
                return new ModelAndView("sistema/zonasSalariales", "model", map);
            }
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un error al momento de buscar las zona salarial ["+idZona+"]   -> "+e);
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible recuperar la zona salariale del sistema");
            return new ModelAndView("sistema/sistema", "model", map);
        }
        return new ModelAndView("sistema/registroZonaSalarial", "model", map);
    }
    
    /**
     * Controller encargado de mostrar las propiedades editables de un periodo de fondo de ahorro
     * @param idPeriodo Numero entero corto que contiene el id de un periodo de fondo de ahorro
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/editar-periodo-fa/{idPeriodo}.htm",method = RequestMethod.GET)
    public ModelAndView editarPeriodoFA(@PathVariable("idPeriodo") Integer idPeriodo){
        LOGGER.info("[Controller,Sistema] Se solicita la vista para editar un periodo de fondo de ahorro ["+idPeriodo+"]");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            PeriodoFA periodoFA = pFAService.getPeriodoFondoAhorro(idPeriodo);
            Integer idContratista = periodoFA.getContratistaObj().getIdContratista();
//            Date fechaInicio = periodoFA.getFechaIniPeriodo();
//            String fechaInicio = procesayyyyMMdd(fechaInicio);
            if(periodoFA != null){
                List<Cliente> clientes = getClientesDelUsuario();
                List<Contratista> contratistas = contratistaService.getContratistas();
                map.put("clientes", clientes);
                map.put("contratistas", contratistas);
                map.put("periodo", periodoFA);
                map.put("fI", procesayyyyMMdd(periodoFA.getFechaIniPeriodo()));
                map.put("fF", procesayyyyMMdd(periodoFA.getFechaFinPeriodo()));
                map.put("edicion", true);
            }else{
                LOGGER.error("[Controller,Sistema] No se encontró periodo de fondo de ahorro ["+idPeriodo+"]");
                getAllPeriodos(map);
                setInformacionEnVentana(map, 1, "Sistema", "No fue posible recuperar el periodo de fondo de ahorro del sistema");
                return new ModelAndView("sistema/periodosFondoAhorro", "model", map);
            }
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un error al momento de buscar el periodo de fondo de ahorro ["+idPeriodo+"]   -> "+e);
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible recuperar el periodo de fondo de ahorro del sistema");
            return new ModelAndView("sistema/sistema", "model", map);
        }
        return new ModelAndView("sistema/registroPeriodoFA", "model", map);
    }
    
    /**
     * Controller encargado de almacenar las propiedades de una zona salarial 
     * @param zsm Instancia de zona salarial
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/datos-zona-salarial.htm", method = RequestMethod.POST)
    public ModelAndView setDatosZonaSalarial(@ModelAttribute("zonaSalarial") ZonaSm zsm,HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se reciben los datos de una zona salarial {"+zsm.getIdZonaSm()+", "+zsm.getZona()+", "+zsm.getAnio()+", "+zsm.getSalario()+"}");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String movimiento;
            if(zsm.getIdZonaSm() == null){
                movimiento = "Inserción";
            }else{
                movimiento = "Actualización";
            }            
            Short setZonaSM = zsmService.setZonaSM(zsm);
            if(setZonaSM != null){
                setInformacionEnVentana(map, 0, "Sistema", "Se ingreso correctamente los datos de la zona salarial"
                                                                                                    + "<br><b>Nombre de zona</b>: "+zsm.getZona()
                                                                                                    +"<br><b>Año de zona</b>: "+zsm.getAnio()
                                                                                                    +"<br><b>Salario</b>: $"+zsm.getSalario());
                setBitacora(movimiento, "Ingresó correctamente los datos de la zona salarial "+zsm.getZona().toUpperCase()+".", request);
            }else{
                setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible ingresar la información de la zona salarial dentro del sistema");
            }
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un problema al momento de intentar ingresar los datos de la zona salarial {"+zsm.getIdZonaSm()+", "+zsm.getZona()+"}  -> "+e);
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible ingresar la información de la zona salarial dentro del sistema");
            return new ModelAndView("sistema/sistema", "model", map);            
        }
        getAllZonas(map);
        return new ModelAndView("sistema/zonasSalariales", "model", map);
    }
    
    /**
     * Controller encargado de almacenar las propiedades de una periodo al fondo de ahorro
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/datos-fondo-ahorro.htm", method = RequestMethod.POST)
    public ModelAndView setDatosPeriodoFondoAhorro(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se reciben los datos de un periodo al fondo de ahorro");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            String movimiento = "";
            String id = request.getParameter("id");
            String  fechaInicioString = request.getParameter("diaRegistroDesde");
            String fechaFinString = request.getParameter("diaRegistroHasta");
            String contratistaIdString = request.getParameter("idContratista");
           // String contratistaIdStringEditar = request.getParameter("idContratistaEditar");
            String nombrePeriodoString = request.getParameter("nombrePeriodo");
               
            int contratistaId = Integer.parseInt(contratistaIdString);
//            int contratistaIdEditar = Integer.parseInt(contratistaIdStringEditar);
            Contratista contratista = contratistaService.getContratista(contratistaId); 
//            Contratista contratistaEditar = contratistaService.getContratista(contratistaIdEditar); 
            Date fechaInicioP = procesayyyyMMdd(fechaInicioString);
            Date fechaFinP = procesayyyyMMdd(fechaFinString);
            
            PeriodoFA periodoFA = new PeriodoFA();
            List<PeriodoFA> periodosDeFA = new ArrayList<>(); 
            
            if(id.isEmpty()){
               movimiento = "Inserción";
             }else{
                movimiento = "Actualización";
                Integer idPeriodoFA = Integer.parseInt(id);
                periodoFA = pFAService.getPeriodoFondoAhorro(idPeriodoFA);
              //  periodoFA.setContratistaObj(contratistaEditar);
            }

            periodoFA.setNombrePeriodo(nombrePeriodoString);
            periodoFA.setFechaIniPeriodo(fechaInicioP);
            periodoFA.setFechaFinPeriodo(fechaFinP);
            periodoFA.setContratistaObj(contratista);
            periodosDeFA.add(periodoFA);
            pFAService.setPeriodoFondoAhorro(periodoFA);
            LOGGER.error("IdPeriodoFA" + periodoFA.getIdPeriodoFA());
            
            setInformacionEnVentana( map,0,"Sistema","La información del periodo de fondo de ahorro se guardo con éxito.");
            setBitacora(movimiento, "Registró con éxtio la información de un periodo de fondo de ahorro", request);  
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un problema al momento de intentar ingresar los datos del período del fondo de ahorro  -> "+e);
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible ingresar la información del período del fondo de ahorro dentro del sistema");
            return new ModelAndView("sistema/sistema", "model", map);            
        }
        getAllPeriodos(map);
        return new ModelAndView("sistema/periodosFondoAhorro", "model", map);
    }
    
    /**
     * Controller encargado de escuchar las pecitiones GET de url´s  POST 
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/editar-datos-del-contrato-generado.htm","sistema/contrato.htm","sistema/contrato-entre-empresas-firmado.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostContratosGenerados(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a contratos generados ... ");
            
           Map<String, Object> map = new HashMap<>();
        
        try {                
                return new ModelAndView("sistema/sistema", "model", map);
            } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con todos los contratos generados entre un contratista y un cliente  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los contratos generados");
        }
        return new ModelAndView("sistema/sistema", "model", map);
     }
    
    /**
     * Controller encargado de escuchar las peticiones GET de las url´s POST 
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/editar-contrato.htm","sistema/datos-contrato-colaboradores.htm","sistema/datos-contrato-contratista.htm","sistema/cambiar-estado-del-contrato.htm","sistema/relacionar-contrato.htm","sistema/agregar-contratistas-al-contrato.htm","sistema/quitar-un-contratista-del-contrato.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostContratosCreados(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a contratos creados ... ");
            
            Map<String,Object> map = new HashMap<>();
        
            try {
                List<Contrato> contratos = contratoService.getContratos();
                map.put("contratos", contratos);
            return new ModelAndView("sistema/contratosCreados", "model", map);

            } catch (Exception e) {
                LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con los contratos creados dentro del sistema  -> "+e);
                setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los contratos dentro del sistema");
            }
            return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de escuchar la peticiones GET de las url´s POST
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/editar-documento.htm","sistema/datos-del-documento.htm","sistema/editar-registro-documental.htm","sistema/guardar-cambios-documental.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostDocumentos(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a documentos ... ");
            
            Map<String, Object> map = new HashMap<>();
        
            try {
                List<Modulo> modulos = agremiadoService.getModulos();            
                Map<String,List<TipoDocumento>> mapDocsPorModulo = new HashMap<>();
                modulos.stream().forEach((modulo) -> {
                    mapDocsPorModulo.put(modulo.getNombre(), agremiadoService.getTipoDocumento(modulo));
            });
            map.put("modulos", modulos);
            map.put("docPorModulos", mapDocsPorModulo);
            return new ModelAndView("sistema/documentosDisponibles", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con los documentos del sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los documentos del sistema");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de escuchar las peticiones GET de las url´s POST
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/incidencias-revisadas-del-cliente.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostIncidenciasRevisadas(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a incidencias revisadas ... ");
            
            Map<String,Object> map = new HashMap<>();
        
            try {
                    List<Cliente> clientes = getClientesDelUsuario();
                    map.put("clientes", clientes);
                    map.put("muestraTabla", false);
                    map.put("revisadas", true);
                    return new ModelAndView("sistema/listaDeIncidencias", "model", map);
                
            } catch (Exception e) {
                LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar obtener las incidencias revisadas  -> "+e);
                setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar obtener las incidencias revisadas.");
            }
            return new ModelAndView("sistema/sistema", "model", map);   
    }
    
    /**
     * Controller encargado de escuchar las peticiones GET de las url´s POST
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/incidencias-sin-revisar-del-cliente.htm","sistema/recoger-incidencia.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostIncidenciasSinRevisar(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a incidencias sin revisar ... ");
            
            Map<String,Object> map = new HashMap<>();
        
            try {
                    List<Cliente> clientes = getClientesDelUsuario();
                    map.put("clientes", clientes);
                    map.put("muestraTabla", false);
                    map.put("revisadas", false);
                    return new ModelAndView("sistema/listaDeIncidencias", "model", map);
                
            } catch (Exception e) {
                LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar obtener las incidencias sin revisión  -> "+e);
                setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar obtener las incidencias.");
            }
            return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de escuchar las peticiones GET de las url´s POST
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/vista-general-de-nomina.htm","sistema/enviar-recibos-email.htm","sistema/generar-recibos-pdf.htm","sistema/generar-recibos-pdf-zip.htm","sistema/enviar-recibo-por-email.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostNominasRegistradas(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a nominas registradas ... ");
            
            Map<String,Object> map = new HashMap<String, Object>();
        
        try {
            List<Cliente> clientes = getClientesDelUsuario();
            map.put("clientes", clientes);
            map.put("verDetallesDeNomina", false);
            return new ModelAndView("sistema/nominasRegistradas", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la consulta de nominas registradas  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la consulta de nóminas registradas");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de escuchar las peticiones GET de las url´s POST
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/edicion-de-rol.htm","sistema/datos-del-rol.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostRoles(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a roles ... ");
            
            Map<String,Object> map = new HashMap<>();

            try {
                    List<Rol> roles = usuarioService.getRoles();
                    map.put("roles", roles);                
                    return new ModelAndView("sistema/roles", "model", map);
            } catch (Exception e) {
                LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con todos los roles  -> "+e);
                setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con con todos los roles que se encuentran registrados dentro del sistema");
            }
            return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de de escuchar las peticiones GET de las url´s POST
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/editar-usuario.htm","sistema/datos-de-usuario.htm","sistema/quitar-un-cliente-al-usuario.htm","sistema/cambiar-acceso.htm","sistema/activar-usuario.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostUsuarios(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a usuarios ... ");
            
            Map<String,Object> map =  new HashMap<String,Object>();

            try {
                getAllUsuarios(map);
            } catch (Exception e) {
                LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con los usuarios del sistema  -> "+e);
                setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con los usuarios del sistema");
                return new ModelAndView("sistema/sistema", "model", map);
            }
            return new ModelAndView("sistema/usuarios", "model", map);
    }
    
    /**
     * Controller encargado de escuchar las peticiones GET de las url´s POST
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/datos-zona-salarial.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostZonaSalarial(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a zonas salariales  ... ");
            
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            getAllZonas(map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un error al momento de crear la vista con todas las zonas salariales  -> "+e);
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible recuperar las zonas salariales del sistema");
            return new ModelAndView("sistema/sistema", "model", map);
        }
        return new ModelAndView("sistema/zonasSalariales", "model", map);
    } 
    
    @RequestMapping(value={"sistema/datos-fondo-ahorro.htm"},method = RequestMethod.GET)
    public ModelAndView vistasSoloConAccesoPostPeriodoFA(){
            LOGGER.info("[Controller,Sistema] Se solicita la vista de un controller que solo se tiene acceso mediante método POST, redireccionando a periodos al fondo de ahorro  ... ");
            
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            getAllPeriodos(map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrio un error al momento de crear la vista con todas las zonas salariales  -> "+e);
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible recuperar los periodos de fondo de ahorrosalariales del sistema");
            return new ModelAndView("sistema/sistema", "model", map);
        }
        return new ModelAndView("sistema/zonasSalariales", "model", map);
    } 
    
    /**
     * Controller encargado de generar el archivo pdf de un contrato creado entre un contratista y un colaborador
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas 
     */
    @RequestMapping(value={"sistema/contrato-contratista-colaborador.htm"},method = RequestMethod.POST)
    public void agreementClienteColaborador(HttpServletRequest request, HttpServletResponse response){        
        LOGGER.info("[Controller, Sistema] Se solicita la creación de un contrato {PDF} entre un contratista y un colaborador");
        
        try {
                        
            Integer idAgremiado = Integer.parseInt( request.getParameter("idAgremiado"));
            Integer idContratista = Integer.parseInt( request.getParameter("idContratista"));
            Integer idContrato = Integer.parseInt( request.getParameter("idContrato"));
            String nombreTUno = request.getParameter("nombreTUno");
            String ocupacionTUno = request.getParameter("ocupacionTUno");
            String origenTUno = request.getParameter("origenTUno");
            String nombreTDos = request.getParameter("nombreTDos");
            String ocupacionTDos = request.getParameter("ocupacionTDos");
            String origenTDos = request.getParameter("origenTDos");
            String fechaString = request.getParameter("fechaContrato");
            Date fechaContrato = procesayyyyMMdd(fechaString);
            
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            Contratista contratista = contratistaService.getContratista(idContratista);
            Contrato contrato = contratoService.getContrato(idContrato);
            ByteArrayOutputStream nuevoContratoClienteColaborador = contratosService.getNuevoContratoContratistaColaborador(contratista, agremiado, contrato, nombreTUno,ocupacionTUno, origenTUno, nombreTDos,ocupacionTDos,origenTDos, fechaContrato);
            // setting some response headers
            response.setHeader("Content-Disposition", "inline;filename=\"contrato_cliente_colaborador"+"__"+datosPersonales.getApellidoPaterno().toUpperCase()+"_"+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"_"+datosPersonales.getNombre().toUpperCase() +".pdf\"");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(nuevoContratoClienteColaborador.size());
            try ( // write ByteArrayOutputStream to the ServletOutputStream
                OutputStream os = response.getOutputStream()) { 
                nuevoContratoClienteColaborador.writeTo(os);
                os.flush();
            }
            
        } catch (NumberFormatException | ParseException | IOException e) {
            LOGGER.error("[Controller, Sistemas] Ocurrio un error durante la creación de un contrato {PDF} entre un cliente y un colaborador --> "+e.getMessage());
        }
    }
    
    /**
     * Controller encargado de mostrar las notificaciones que el usuario puede activar o  descativar para si mismo
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/mis-notificaciones.htm"},method = RequestMethod.GET)
    public ModelAndView notificacionesDelUsuario(){
         LOGGER.info("[Controller, Sistemas] Se solicitan las notificaciones disponibles dentro del sistema");
         
         Map<String,Object> map =  new HashMap<String,Object>();
         
        try {            
            String correo = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = usuarioService.getUsuario(correo);
            map.put("notificacionesActivas", usuario.getNotificacionesList());
            List<Notificaciones> notificaciones = usuarioService.getNotificaciones();
            notificaciones.removeAll( usuario.getNotificacionesList());
            map.put("notificacionesDisponibles", notificaciones);
            return new ModelAndView("sistema/notificacionesDelSistema", "model", map);
        } catch (Exception e) {
            setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible recuperar las notificaciones del sistema.");
             LOGGER.error("[Controller, Sistemas] Ocurrio un error durante la vista de las notificaciones del usuario --> "+e);
        }
            return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de almacenar las notificaciones que el usuario desea tener activas
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = {"sistema/mis-notificaciones.htm"}, method = RequestMethod.POST)
    public ModelAndView setNotificacionesDelUsuario(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistemas] Se reciben las notificaciones seleccionadas por el usuario.");
        
        Map<String,Object> map =  new HashMap<String,Object>();
        
        try {            
            String notificaciones[] = request.getParameterValues("notificacionesActivas");
            List<Notificaciones> notificacionesDelSistema = usuarioService.getNotificaciones();
            List<Notificaciones> notificacionesActivas = new ArrayList<>();
            String correo = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = usuarioService.getUsuario(correo);        
            
            for (String notificacionString : notificaciones) {
                Short idn = Short.parseShort(notificacionString);
                for (Notificaciones notificacionSistema : notificacionesDelSistema) {
                    if(notificacionSistema.equals(new Notificaciones(idn))){
                        notificacionesActivas.add(notificacionSistema);
                        break;
                    }
                }
            }
             
            notificacionesDelSistema.removeAll(notificacionesActivas);
            notificacionesDelSistema.stream().filter((notificacionSistema) -> (notificacionSistema.getUsuarioList().contains(usuario))).forEach((notificacionSistema) -> {
                notificacionSistema.getUsuarioList().remove(usuario);
            });
           
            notificacionesActivas.stream().filter((notificacionesActiva) -> (!notificacionesActiva.getUsuarioList().contains(usuario))).forEach((notificacionesActiva) -> {
                notificacionesActiva.getUsuarioList().add(usuario);
            });
            
            
            notificacionesDelSistema.addAll(notificacionesActivas);
            usuarioService.setNotificaciones(notificacionesDelSistema);
            
            usuario.setNotificacionesList(notificacionesActivas);
            usuario.setModificado( new java.util.Date() );
            usuarioService.setUsuario(usuario);
            
            
            if(usuario.getNotificacionesList().size() == notificacionesActivas.size()){                
                setInformacionEnVentana(map, 0, "Sistema", "Fueron establecidas <b>"+usuario.getNotificacionesList().size()+"</b> notificaciones para el usuario <b>"+usuario.getCorreoElectronico()+"</b>.");
                setBitacora("Actualización", "Estableció "+usuario.getNotificacionesList().size()+" notificaciones.", request);
                return new ModelAndView("sistema/sistema", "model", map);
            }
        } catch (Exception e) {
             LOGGER.error("[Controller, Sistemas] Ocurrio un error durante el setteo de las notificaciones del usuario --> "+e);
        }
        setInformacionEnVentana(map, 1, "Sistema", "<b>Ups!!!</b> No fue posible establecer las notificaciones.");
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de solcitar la vista para cambiar la contraseña de acceso del usuario logueado en el sistema
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/cambiar-mi-acceso.htm"}, method = RequestMethod.GET)
    public ModelAndView getViewCambiarAcceso(){
        LOGGER.info("[Controller, Sistema] Se solicita la vista para cambiar la contraseña del usuario en el sistema");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try { 
                String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                Usuario usuario = usuarioService.getUsuario(correo);
                Rol rol = usuarioService.getRol(usuario.getRol());
                map.put("usuario", usuario);
                map.put("rol", rol);
                return new ModelAndView("sistema/cambiarAcceso", "model", map);
        } catch (Exception e){
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para cambiar la contraseña del usuario en el sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para cambiar la contraseña");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    /**
     * Controller encargado de cambiar la contraseña de acceso del usuario logueado dentro del sistema por una contraseña seleccionada por dicho usuario
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/mi--acceso.htm"}, method = RequestMethod.POST)
    public ModelAndView setNuevoAcceso(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita el cambio de la contraseña del usuario en el sistema.");
        
        Map<String,Object> map = new HashMap<String,Object>();
        try { 
                
                String valueOne = request.getParameter("valueOne");
                String valueTwo = request.getParameter("valueTwo");
                
                String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                Usuario usuario = usuarioService.getUsuario(correo);
                
                if(Arrays.equals(valueOne.toCharArray(), valueTwo.toCharArray())){
                    String encriptarAcceso = encriptarAcceso(valueTwo);
                    usuario.setContrasena(encriptarAcceso);
                    usuario.setModificado( new Date());
                    usuarioService.setUsuario(usuario);
                    if(usuario.getContrasena().equalsIgnoreCase(encriptarAcceso)){    
                        valueOne = null;
                        valueTwo = null;
                        encriptarAcceso = null;
                        Runtime.getRuntime().gc();
                        setInformacionEnVentana( map,0,"Sistema","Tu contraseña de acceso fue cambiada de manera exitosa!");                        
                        setBitacora("Actualización", "Actualizó su contraseña de acceso.", request);
                        notificacionesService.cambioDeContrasena(usuario, true);
                        usuario = null;
                        return new ModelAndView("sistema/sistema", "model", map);
                    }else{
                        Rol rol = usuarioService.getRol(usuario.getRol());
                        map.put("usuario", usuario);
                        map.put("rol", rol);
                        setInformacionEnVentana( map,1,"Sistema","Las contraseñas no coinciden.");
                        return new ModelAndView("sistema/cambiarAcceso", "model", map);                    
                    }
                }else{  
                    Rol rol = usuarioService.getRol(usuario.getRol());
                    map.put("usuario", usuario);
                    map.put("rol", rol);
                    setInformacionEnVentana( map,1,"Sistema","Las contraseñas no coinciden.");
                    return new ModelAndView("sistema/cambiarAcceso", "model", map);
                }
            
                
        } catch (Exception e){
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de cambiar la contraseña del usuario en el sistema  -> "+e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de cambiar la contraseña.");
        }
        return new ModelAndView("sistema/sistema", "model", map);       
    } 
    
    /**
     * Controller encargado de recibir el archivo y actulizar los recibos de nomina
     * @param archivoDeNomina
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping(value="sistema/editar-recibos-nomina.htm",method = RequestMethod.POST)
    public ModelAndView actualizarRecibos(@RequestParam("rchvDNmn") MultipartFile archivoDeNomina, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la actulización de recibos de nómina");
        
        Map<String,Object> map = new HashMap<String, Object>(); 
         
         try{
             
                    Integer clienteInt = Integer.parseInt(request.getParameter("clienteId"));
                    String fechaDesdeString = request.getParameter("fchDsd");
                    String fechaHastaString = request.getParameter("fchHst");
                    Integer sindicatoId = Integer.parseInt(request.getParameter("sindicatoId"));
                
                    Date fechaInicial = procesayyyyMMdd(fechaDesdeString);
                    Date fechaHasta = procesayyyyMMdd(fechaHastaString);      
                    
                    Cliente cliente = clienteService.getCliente(clienteInt);
                    Sindicato sindicato = sindicatoService.getSindicato(sindicatoId);
                        
                    if(fechaHasta.before(fechaInicial)){ 
                            map.put("actualizacion", true);
                            setInformacionEnVentana(map,2, "Módulo de Sistema", "La fecha posterior no pude ser mayor a la fecha inicial."); 
                            List<Cliente> clientes = getClientesDelUsuario();
                            map.put("clientes", clientes);  
                            List<Sindicato> sindicatos = sindicatoService.getSindicatos();
                            map.put("sindicatos", sindicatos);                        
                    }else if(cliente == null){
                            map.put("actualizacion", true);
                            setInformacionEnVentana(map,2, "Módulo de Sistema", "Ocurrió un problema con el cliente por favor intentelo de nuevo."); 
                            List<Cliente> clientes = getClientesDelUsuario();
                            map.put("clientes", clientes);    
                            List<Sindicato> sindicatos = sindicatoService.getSindicatos();
                            map.put("sindicatos", sindicatos); 
                    }else if(sindicato == null){
                            map.put("actualizacion", true);
                            setInformacionEnVentana(map,2, "Módulo de Sistema", "Ocurrió un problema con el sidnicato por favor intentelo de nuevo."); 
                            List<Cliente> clientes = getClientesDelUsuario();
                            map.put("clientes", clientes);    
                            List<Sindicato> sindicatos = sindicatoService.getSindicatos();
                            map.put("sindicatos", sindicatos); 
                    }else{
                            LOGGER.info("[Controller, Sistema] Se solicita la actulización de recibos de nómina");              
                            List<Recibo> recibos = excelService.actualizarNomina(cliente, archivoDeNomina, fechaInicial, fechaHasta,sindicato);
                            Runtime.getRuntime().gc();   
                            map.put("recibos", recibos);
                            setInformacionEnVentana(map,0, "Sistema", "Se actualizaron "+recibos.size()+" recibos.");        
                            map.put("actualizacion", false);
                            map.put("cliente", cliente);
                            map.put("sindicato", sindicato);
                            if(recibos.size()>0){
                                setBitacora("Actualización", "Actualizó "+recibos.size()+" recibos del cliente "+cliente.getNombreEmpresa()+", con periodo del "+fechaDesdeString+" al "+fechaHastaString+".", request);
                            }
                    }              
            }catch (ParseException ex) {
                       Logger.getLogger(SistemaController.class.getName()).log(Level.SEVERE, "[Controller,Sistema] Error al procesar una fecha de texto a Date ");
            }catch(NumberFormatException nfe){
                LOGGER.error("[Controller, Sistema] Ocurrio un error al momento de crear la vista -->"+nfe);
        }         
        return new ModelAndView("sistema/editarRecibosRegistrados", "model", map); 
    } 
    /**
     * Controller encargado de mostrar la vista para consultar las solicitudes de envio de correos electronicos dentro del sistema sobre el recibo nominal
     * @return ModelAndView
     */
    @RequestMapping(value={"sistema/bitacora-correos.htm","sistema/vista-general-bitacora-correos.htm"},method = RequestMethod.GET) //Anotación con valor en bitacora-correos.htm de sistema con metodo GET
    public ModelAndView getVistaBitacoraCorreos(){ //Metodo encargado de regresar las vista bitacoraSolicitudEnvioCorreo/sistema dentro del apartado sistema
        LOGGER.info("[Controller, Sistema] Se solicita la vista para consultar las solicitudes de envio de reciboos por correo"); //log que informa de la petición de la vista
        
        Map<String,Object> map = new HashMap<String, Object>(); //Instancia de la clase Map sobre un HasMap
        
        try {
            List<Cliente> clientes = getClientesDelUsuario(); //Se obtienen el o los clientes del usuario y se guardan en una Lista de tipo Cliente
            map.put("clientes", clientes); //Se le asigna a la clave "clientes" los valores de la Lista tipo Cliente
            map.put("verDetallesDeEnvio", false); //Se le asigna a la clave "verDetallesDeEnvio" el valor de false
            return new ModelAndView("sistema/bitacoraSolicitudEnvioCorreo", "model", map); //Regresa la vista con su clave y su valor
        } catch (Exception e) { //Se cacha la excepción 
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista para la consulta de solicitud de envio de recibos por correo  -> "+e); //Se imprime en el log el error con descripción y variable de error
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista para la consulta de solicitudes de envio de recibos por correo"); //Se envia mensaje de error por medio de una ventana informativa
        }
        return new ModelAndView("sistema/sistema", "model", map); //Regresá la vista de sistema
    }
    
     /**
     * Controller encargado de mostrar la solicitud de envio de nómina de los colaboradores
     * @param request Variable que maneja las respuestas
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "sistema/vista-general-bitacora-correos.htm",method = RequestMethod.POST) //Anotación con valor en vista-general-bitacora-correos.htm de sistema con metodo POST
    public ModelAndView getVistaGeneralDeEnvioCorreos(HttpServletRequest request, HttpServletResponse response){ //Metodo encargado de regresar las vista General de envio de correos
        LOGGER.info("[Controller, Sistema] Se solicita la vista detallada de la solicitud de envio de recibos por correo"); //log que informa de la petición de la vista
        
        Map<String,Object> map = new HashMap<>(); //Instancia de la clase Map sobre un HasMap
        
        try {
                Integer idCliente = Integer.parseInt(request.getParameter("cliente")); //Se obtiene el idCliente de la vista mediante parametro
                String diaRegistroDesdeString = request.getParameter("diaRegistroDesde"); //Se obtiene diaRegistroDesde de la vista mediante parametro
                String diaRegistroHastaString = request.getParameter("diaRegistroHasta"); //Se obtiene diaRegistroHasta de la vista mediante parametro
                
                Date diaRegistroDesde = procesayyyyMMddHHmmss(diaRegistroDesdeString + " 00:00:00"); //Se cambia el formato de diaRegistroDesdeString y se guarda en variable tipo Date
                Date diaRegistroHasta = procesayyyyMMddHHmmss(diaRegistroHastaString + " 23:59:59"); //Se cambia el formato de diaRegistroHastaString y se guarda en variable tipo Date
                Cliente cliente = clienteService.getCliente(idCliente); //Se obtiene la referencia del registro del cliente mediante idCliente
                if(cliente != null){ //Se valida que el cliente no este nulo
                    List<ReciboSolicitado> recibos = reciboService.getReciboSolicitado(cliente, diaRegistroDesde,diaRegistroHasta); //Se obtienen los reciibos solicitados del service y se asignan a una variable tipo Lista de RecibosSolcitados
                    if(recibos.isEmpty()){ //Si la lista de recibos esta vacia
                        map.put("verDetallesDeEnvio", false); //Se le asigna false  la clave verDetallesDeEnvio utilizada en la ista
                        setInformacionEnVentana( map,2,"Sistema","No se encontrarón solicitudes de envio de recibos para el cliente <b>"+cliente.getNombreEmpresa()+"</b> en el periódo <b>"+diaRegistroDesdeString+"<b/> al <b>"+diaRegistroHastaString+"</b>."); //Se muestra el mensaje en la vista de que no hay envios solicitados para el cliente y el rango de fechas  
                    }else{ //Si la lista de recibos no esta vacia 
                            map.put("recibos", recibos); //Se le asigna a la clave recibos la lista recibos de tipo ReciboSolicitado
                            map.put("cliente", cliente); //Se le asigna a la clave cliente la variable tipo Cliente que obtiene el idCliente
                            map.put("fechaA", diaRegistroDesde); //Se asigna a la clave fechaA  el valor de la variable diaRegistroDesde de tipo Date
                            map.put("fechaB", diaRegistroHasta); //Se asigna a la clave fechaA  el valor de la variable diaRegistroDesde de tipo Date
                            map.put("verDetallesDeEnvio", true); // Se asigna a la clave verDetallesDeEnvio el valor de true
                            setInformacionEnVentana( map,2,"Sistema","Recibos enviados para el cliente <b>"+cliente.getNombreEmpresa()+"</b> <br><br><b>Toma en cuenta que esto puede demorar un poco. <b/> "); //Se envia la ventana con el mensaje de los recibos encontrados para el cliente seleccionado
                    }
                    List<Cliente> clientes = getClientesDelUsuario(); //Obtiene los clientes del usario logeado y los asigna a una variable de tipo Lista de Cliente
                    map.put("clientes", clientes); //Se asigna a la clave "clientes" el valor de la lista de Cliente
                    return new ModelAndView("sistema/bitacoraSolicitudEnvioCorreo", "model", map); //Regresa a la vista bitacoraSolicitudEnvioCorreo clave y valor
                }else{
                    LOGGER.error("[Controller, Sistema] No se encontró ningun cliente [ "+idCliente+']'); // log que imprime el error de que no se encontro ningun cliente
                    setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de buscar el cliente ");  // Ventana que informa que ocurrio un error al momento de buscar le cliente                   
                }
        } catch (Exception e) { //Se cacha la excepción
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de crear la vista con el detalle de solicitud de envio de recibo por correo  -> "+e); //log que imprime el error con su varibale
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista con el detalle de envio de recibo por correo "); // Ventana que informa del error
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
     /**
     * Controller encargado de solicitar el archivo alojado en el FTP que contiene la plantilla de excel para la modifcación de los recibos de nomina
     * @param request
     * @param response
     */
    @RequestMapping(value={"sistema/file/editar-recibos-nomina.htm"}, method = RequestMethod.GET)
    public void getExcelEditRecibos(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita la plantilla excel para el cambio de recibos");
        
        try { 
            File descargarFileFTP = ftpService.descargarFileFTP(URL_EXCEL_TEMPLATE_RECIBOS);
            InputStream is = new FileInputStream(descargarFileFTP);
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition", "attachment; filename=Plantilla excel.zip" );
            OutputStream sos = response.getOutputStream();
            IOUtils.copy(is, sos);
            sos.flush();
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
            LOGGER.error("[Controller, Sistema] El archivo no puede ser generado para su visualización --FileNotFoundException-- --> "+ fnfe.getMessage(),fnfe);
        } catch (IOException ioe) {
            LOGGER.error("[Controller, Sistema] El archivo no puede ser generado para su visualización --IOException-- --> "+ ioe.getMessage(),ioe);
        }catch (Exception e){
            LOGGER.error("[Controller, Sistema] Ocurrió un error al momento de intentar devolver la plantilla de excel para la modificación de recibos sindicales  -> "+e.getMessage(),e);
        }
    }
    
    @RequestMapping(value = {"sistema/registrar-sup.htm"},method = RequestMethod.GET)
    public ModelAndView vistaAgregarNuevoSUP(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] se solicita la vista para agregar un nuevo Salario unico profesional");
       Map<String,Object> map = new HashMap<>();
       
        try {
            List<TipoSalarioUnicoProfesional> tiposSalariosUnicosProfesionales = sUPService.getTiposSalariosUnicosProfesionales();
        LOGGER.info("[Controller, Sistema] =================>>>>>>>>>>>>>>>>>"+tiposSalariosUnicosProfesionales.size());
            map.put("tipossup", tiposSalariosUnicosProfesionales);
            map.put("edicion", false);
             return new ModelAndView("sistema/agregarSUP", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un problema al intentar mostrar la vista para agregar un nuevo SUP --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista  para agregar un nuevo SUP ");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    @RequestMapping(value={"sistema/sup-registrados.htm","sistema/edicion-de-sup.htm","sistema/guardar-sup.htm"},method = RequestMethod.GET)
    public ModelAndView vistaSUPExistentes(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] se solicita la vista para consultar los salario unico profesionales registrados");
        Map<String, Object> map = new HashMap<>();
        
        try {
                List<TipoSalarioUnicoProfesional> tiposSalariosUnicosProfesionales = sUPService.getTiposSalariosUnicosProfesionales();
                map.put("tipossup", tiposSalariosUnicosProfesionales);
                List<SalarioUnicoProfesional> salariosUnicosProfesionales = sUPService.getSalariosUnicosProfesionales();
                map.put("sups", salariosUnicosProfesionales);
                return new ModelAndView("sistema/sups", "model", map);
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un problema al intentar mostrar la vista para consultar los  SUPs existentes --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista  para consultar los  SUPs existentes ");
        }
        return new ModelAndView("sistema/sistema", "model", map);
    }
    
    @RequestMapping(value = {"sistema/edicion-de-sup.htm"},method = RequestMethod.POST)
    public ModelAndView vistaEdicionSUP(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] se solicita la vista para editar un salario unico profesional");
        
        Map<String, Object> map = new HashMap<>();       
        try {
            String parameter = request.getParameter("salarioUnicoProfesional");
            int idSalarioUnicoProfesional = Integer.parseInt(parameter);
            SalarioUnicoProfesional salarioUnicoProfesional = sUPService.getSalarioUnicoProfesional(idSalarioUnicoProfesional);
            if(salarioUnicoProfesional != null){                
                List<TipoSalarioUnicoProfesional> tiposSalariosUnicosProfesionales = sUPService.getTiposSalariosUnicosProfesionales();
        LOGGER.info("[Controller, Sistema] se ================================> "+salarioUnicoProfesional.getFechaInicio());
        LOGGER.info("[Controller, Sistema] se ================================> "+salarioUnicoProfesional.getFechaFin());
                map.put("tipossup", tiposSalariosUnicosProfesionales);
                map.put("sup", salarioUnicoProfesional);
                map.put("fI", procesayyyyMMdd(salarioUnicoProfesional.getFechaInicio()));
                map.put("fF", procesayyyyMMdd(salarioUnicoProfesional.getFechaFin()));
                map.put("edicion",true );
                return new ModelAndView("sistema/agregarSUP", "model", map);
            }
            setInformacionEnVentana( map,2,"Sistema","No fue encontrado ninguno SUPs con la infromación recibida.");
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un problema al intentar mostrar la vista para editar un  SUP --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de crear la vista  para editar un SUP ");
        }
        return new ModelAndView("sistema/sistema", "model", map);    
    }
    
    @RequestMapping(value={"sistema/guardar-sup.htm"},method = RequestMethod.POST)
    public ModelAndView guardarSUP(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita almacenar/ajustar un salario unico profesional");
        Map<String, Object> map = new HashMap<>();
        
        try {
            String idSUPString = request.getParameter("id_sup");
            String oficio = request.getParameter("oficio");
            String descripcion = request.getParameter("descripcion");
            String pesosDiariosString = request.getParameter("pesos_diarios");
            String fechaInicioString = request.getParameter("fchStart");
            String fechaFinString = request.getParameter("fchEnd");
            String tipoSUPString = request.getParameter("tipoSup");
            
            int tipoSUP = 0;
            if( !( tipoSUPString == null || tipoSUPString.isEmpty() ) ){
                tipoSUP = Integer.parseInt(tipoSUPString);
                TipoSalarioUnicoProfesional tipoSalariUnicoProfesional = sUPService.getTipoSalariUnicoProfesional(tipoSUP);
                if(tipoSalariUnicoProfesional != null){
                    tipoSUP = tipoSalariUnicoProfesional.getIdTipoSalarioUnicoProfesional();
                }
            }
            boolean update = true;
            if(tipoSUP > 0){
                    SalarioUnicoProfesional salarioUnicoProfesional = null;
                    if( !( idSUPString == null || idSUPString.isEmpty() ) ){
                        int idSUP = Integer.parseInt(idSUPString);
                        salarioUnicoProfesional = sUPService.getSalarioUnicoProfesional(idSUP);
                    }
                    if(salarioUnicoProfesional == null){
                        salarioUnicoProfesional = new SalarioUnicoProfesional();
                        update = false;
                    }
                    salarioUnicoProfesional.setOficio(oficio);
                    salarioUnicoProfesional.setDescripcion(descripcion);
                    double pesos = Double.parseDouble(pesosDiariosString);
                    salarioUnicoProfesional.setPesosDiarios(pesos);
                    salarioUnicoProfesional.setTipoSUP(tipoSUP);
                    Date fechaInicio = procesayyyyMMdd(fechaInicioString);
                    Date fechaFin =  procesayyyyMMdd(fechaFinString);
                    salarioUnicoProfesional.setFechaInicio(fechaInicio);
                    salarioUnicoProfesional.setFechaFin(fechaFin);
                    if(salarioUnicoProfesional.getIdSalarioUnicoProfesional()!=null){
                        salarioUnicoProfesional.setFechaModificacion(new Date());
                    }
                    sUPService.setSalarioUnicoProfesional(salarioUnicoProfesional);
                    if(salarioUnicoProfesional.getIdSalarioUnicoProfesional()!=null){
                        setBitacora((update)?"Actualización":"Inserción", 
                               ((update)?"Actualizó":"Almacenó") +" El salario único profesional \""+salarioUnicoProfesional.getOficio().toUpperCase()+"\".", 
                                request);
                        List<TipoSalarioUnicoProfesional> tiposSalariosUnicosProfesionales = sUPService.getTiposSalariosUnicosProfesionales();
                        map.put("tipossup", tiposSalariosUnicosProfesionales);
                        List<SalarioUnicoProfesional> salariosUnicosProfesionales = sUPService.getSalariosUnicosProfesionales();
                        map.put("sups", salariosUnicosProfesionales);
                        setInformacionEnVentana( map,0,"Sistema","Los datos de SUP se guardaron correctamente. ");
                        return new ModelAndView("sistema/sups", "model", map);
                    }
            }
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de obtener el tipo de SUP");
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un problema al intentar guardar un  SUP --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar guardar un SUP.");
        }
        return new ModelAndView("sistema/sistema", "model", map);    
    }
    
    @RequestMapping(value = {"sistema/guardar-tipo-sup.htm"},method = RequestMethod.POST)
    public ModelAndView guardarTipoSUP(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info("[Controller, Sistema] Se solicita almacenar/ajustar un tipo de salario unico profesional");
        Map<String, Object> map = new HashMap<>();
    
        try {
            String tipoSupString = request.getParameter("id_tipo_sup");
            String sector = request.getParameter("sector");
            boolean update = true;
            int tipoSUP = 0;
        TipoSalarioUnicoProfesional  tipoSalariUnicoProfesional;
        if( !( tipoSupString == null || tipoSupString.isEmpty() ) ){
                tipoSUP = Integer.parseInt(tipoSupString);
                tipoSalariUnicoProfesional = sUPService.getTipoSalariUnicoProfesional(tipoSUP);
                if(tipoSalariUnicoProfesional == null){
                    tipoSalariUnicoProfesional = new TipoSalarioUnicoProfesional();
                    update = false;
                }
            }else{
                tipoSalariUnicoProfesional = new TipoSalarioUnicoProfesional();   
                update = false;
            }
            tipoSalariUnicoProfesional.setSector(sector);
            sUPService.setTipoSalarioUnicoProfesional(tipoSalariUnicoProfesional);
            if(tipoSalariUnicoProfesional.getIdTipoSalarioUnicoProfesional()!=null){
                    setBitacora((update)?"Actualización":"Inserción", 
                           ((update)?"Actualizó":"Almacenó") +" El tipo de salario único profesional \""+tipoSalariUnicoProfesional.getSector().toUpperCase()+"\".", 
                            request);
                    List<TipoSalarioUnicoProfesional> tiposSalariosUnicosProfesionales = sUPService.getTiposSalariosUnicosProfesionales();
                    map.put("tipossup", tiposSalariosUnicosProfesionales);
                    List<SalarioUnicoProfesional> salariosUnicosProfesionales = sUPService.getSalariosUnicosProfesionales();
                    map.put("sups", salariosUnicosProfesionales);
                    setInformacionEnVentana( map,0,"Sistema","Los datos de tipo SUP se guardaron correctamente. ");
                    return new ModelAndView("sistema/sups", "model", map);
            }
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de obtener el tipo de SUP");
            
        } catch (Exception e) {
            LOGGER.error("[Controller, Sistema] Ocurrió un problema al intentar guardar un  tipo SUP --> "+e.getMessage(),e);
            setInformacionEnVentana( map,1,"Sistema","Ocurrio un error al momento de intentar guardar un tipo SUP.");
        }
        return new ModelAndView("sistema/sistema", "model", map);    
    }
    
    @RequestMapping(value="sistema/contratos-cliente/{cliente}.htm", method = RequestMethod.POST)
    public @ResponseBody String getContratos(@PathVariable("cliente")int id,HttpServletRequest request, HttpServletResponse response){
       LOGGER.info("[Controller, Sistema] Se solicita los contratos del cliente ["+id+"]");
        try {            
            List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(id);            
            if(contratosDelCliente == null){
                contratosDelCliente = new ArrayList<>();
            }
            return new ObjectMapper().writeValueAsString(contratosDelCliente);
        }catch (JsonProcessingException me) {
           LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar procesar la petición de los contratos del cliente --> "+me.getMessage());
       }catch (Exception e) {
           LOGGER.error("[Controller, Sistema] Ocurrio un problema al intentar procesar la petición de los contratos del cliente -Exception- --> "+e.getMessage());
       }
        return "";
    }
    // * ====================================================================================================================================================
    // * ====================================================================================================================================================
    // * =============================================================METODOS======PRIVADOS===============================================================
    // * ====================================================================================================================================================
    // * ====================================================================================================================================================
    private void getAllUsuarios(Map<String,Object> map){
        List<Rol> roles = usuarioService.getRoles();
            List<String> nombreRoles =  new LinkedList<>();
            Map<String,List<Usuario>> usuariosPorRol =  new HashMap<>();
            roles.stream().forEach((rol) -> {
                List<Usuario> usuarios = usuarioService.getUsuarios(rol);
                if( ! usuarios.isEmpty() ){
                    usuariosPorRol.put(rol.getNombre(),usuarios);
                    nombreRoles.add(rol.getNombre());
                }
            });
            map.put("usuariosPorRol", usuariosPorRol);
            map.put("nombreRoles", nombreRoles);
    }
    private void getAllZonas(Map<String,Object> map){
        if(map != null){
            List<ZonaSm> zonaSms = zsmService.getZonaSm();
            if (zonaSms.isEmpty()) {
                LOGGER.error("[Controller, Sistema] No se recuperó ninguna zona salarial de la base de datos ");
            }          
            map.put("zonas", zonaSms);
        }
    }
    private void getAllPeriodos(Map<String,Object> map){
        if(map != null){
            List<PeriodoFA> PeriodoFa = pFAService.getPeriodosFondoAhorro();
            if (PeriodoFa.isEmpty()) {
                LOGGER.error("[Controller, Sistema] No se recuperó ningun periodo del fondo de ahorro de la base de datos ");
            }          
            map.put("periodos", PeriodoFa);
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
            Logger.getLogger(SistemaController.class.getName()).log(Level.SEVERE, "[Controller, Sistema] Error al inentar encriptar una contraseña --> ", uee);
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
   private String procesayyyyMMdd(Date date){
        String yyyyMMdd = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
       return formatter.format(date);
   }
   private Date procesayyyyMMddHHmmss(String fechaString) throws ParseException {        
        String yyyyMMdd = "yyyy-MM-dd HH:mm:ss";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        return formatter.parse(fechaString);
    }
   private final int[][] rangos = {{65,90},{97,122},{48,57},{35,38}};
   private void setBitacora(String movimiento, String detalles,HttpServletRequest request){
       String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(correo);
        if(usuario!=null){
            Bitacora bitacora = new Bitacora(new Date(), usuario.getIdUsuario());
            bitacora.setModulo("Sistema");
            bitacora.setMovimiento(movimiento);
            bitacora.setDetalles(detalles);
            bitacora.setIpAcceso(request.getRemoteAddr());
            bitacoraService.setBitacora(bitacora);
        }else{
            LOGGER.error("[Controller, Sistema] Bitacora.- Se busco al usuario {"+correo+"} pero no se encontró registro alguno.");
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
        List<Cliente> clientesBack = new LinkedList<>();
        for (Cliente cliente : clientes) {
            if(cliente.getStatus()){
                clientesBack.add(cliente);
            }
        }
        clientes = null;
        clientesBack.sort( (Cliente c1, Cliente c2) -> c1.getNombreEmpresa().toUpperCase().compareTo( c2.getNombreEmpresa().toUpperCase() ) );
        return clientesBack;
    }
    private String formatoFechaCorto(Date fecha) {        
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return  formatter.format(fecha);
    }
   @Override
    public String toString() {
        return "SistemaController{Controlador Del Sistema, Versión 1.0}";
    }
    private String getNombreDeContrato(ContratoEmpresas contratoEntreEmpresas) {
            if(contratoEntreEmpresas.getNombreContrato()!=null){
                return contratoEntreEmpresas.getNombreContrato();
            }
            String nombreEmpresaCliente = clienteService.getCliente(contratoEntreEmpresas.getClienteObj()).getNombreEmpresa();
            String nombreEmpresaContratista = contratistaService.getContratista(contratoEntreEmpresas.getContratistaObj()).getNombreEmpresa();
            nombreEmpresaContratista = nombreCorto(nombreEmpresaContratista);
            nombreEmpresaCliente = nombreCorto(nombreEmpresaCliente);
            return "SVC-"+nombreEmpresaContratista+"-"+nombreEmpresaCliente+"-"+contratoEntreEmpresas.getIdContratoEmpresas().toString()+"";
        }
    private void setNombreDeContrato(ContratoEmpresas contratoEntreEmpresas) {
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
