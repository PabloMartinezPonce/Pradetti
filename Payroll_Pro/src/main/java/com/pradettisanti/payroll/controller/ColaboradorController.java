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
import com.pradettisanti.payroll.pojoh.DatosBancarios;
import com.pradettisanti.payroll.pojoh.DatosBeneficiario;
import com.pradettisanti.payroll.pojoh.DatosDomicilio;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.DatosPersonales;
import com.pradettisanti.payroll.pojoh.Documento;
import com.pradettisanti.payroll.pojoh.DocumentoPK;
import com.pradettisanti.payroll.pojoh.DocumentosBaja;
import com.pradettisanti.payroll.pojoh.EsquemaPago;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.MotivoBaja;
import com.pradettisanti.payroll.pojoh.PeriodoFA;
import com.pradettisanti.payroll.pojoh.Permiso;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoFiniquito;
import com.pradettisanti.payroll.pojoh.TipoIncidencia;
import com.pradettisanti.payroll.pojoh.TipoNomina;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.pojoh.ZonaSm;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.BitacoraService;
import com.pradettisanti.payroll.service.ClienteService;
import com.pradettisanti.payroll.service.ContratistaService;
import com.pradettisanti.payroll.service.ContratoEmpresasService;
import com.pradettisanti.payroll.service.ContratoService;
import com.pradettisanti.payroll.service.ContratosService;
import com.pradettisanti.payroll.service.DatosLaboraralesByEsquemaService;
import com.pradettisanti.payroll.service.ExcelReportesService;
import com.pradettisanti.payroll.service.FtpService;
import com.pradettisanti.payroll.service.IncidenciaService;
import com.pradettisanti.payroll.service.NotificacionesService;
import com.pradettisanti.payroll.service.PDFiTextService;
import com.pradettisanti.payroll.service.PeriodoFondoAhorroService;
import com.pradettisanti.payroll.service.SindicatoService;
import com.pradettisanti.payroll.service.SolicitudBajaService;
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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.YEARS;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
 * Manager encargador de contener los controlles de la vista del modulo de colaboradores
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@SuppressWarnings("Convert2Diamond")
public class ColaboradorController {
    
    /**
     * Constante para el uso de los logs del sistema
     */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ColaboradorController.class);    
    
    private final String MODULO = "Colaboradores";
    private final String LOGGER_PREFIX = "[Controller, "+MODULO+"] ";
    
    
    @Autowired
    private AgremiadoService agremiadoService;    
    public AgremiadoService getAgremiadoService(){
        return  agremiadoService;
    }    
    public void setAgremiadoService(AgremiadoService agremiadoService){
        this.agremiadoService = agremiadoService;
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
    private BitacoraService bitacoraService;
    public BitacoraService getBitacoraService(){
        return bitacoraService;
    }
    public void setBitacoraService(BitacoraService bitacoraService){
        this.bitacoraService = bitacoraService;
                
    }
    
    @Autowired
    private ClienteService clienteService;
    public ClienteService getClienteService(){
        return clienteService;
    }
    private void setClienteService(ClienteService clienteService){
        this.clienteService = clienteService;
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
    private SindicatoService sindicatoService;
    public SindicatoService getSindicatoService(){
        return sindicatoService;
    }
    public void setSindicatoService(SindicatoService sindicatoService){
        this.sindicatoService = sindicatoService;
    }
    
    @Autowired
    private ZonasSmService zonasSmService;
    public ZonasSmService getZonasSmService(){
        return zonasSmService;
    }
    public void setZonasSmService(ZonasSmService zonasSmService){
        this.zonasSmService = zonasSmService;
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
    private ContratoService contratoService;
    public ContratoService getContratoService(){
        return contratoService;
    }
    public void setContratoService(ContratoService contratoService){
        this.contratoService = contratoService;
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
    private SolicitudBajaService solicitudBajaService;
    public SolicitudBajaService getSolicitudBajaService(){
        return solicitudBajaService;
    }
    public void setSolicitudBajaService(SolicitudBajaService solicitudBajaService){
        this.solicitudBajaService = solicitudBajaService;
    }
    
    @Autowired
    private PeriodoFondoAhorroService pFAService;
    public PeriodoFondoAhorroService getPeriodosFaService(){
        return pFAService;
    }
    public void setPeriodosFaService(PeriodoFondoAhorroService pfaService){
        this.pFAService = pfaService;
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
    private ContratosService contratosService;
    public ContratosService getContratosService(){
        return contratosService;
    }
    public void setContratosService( ContratosService contratosService ){
        this.contratosService = contratosService;
    }
    
    @Autowired
    private DatosLaboraralesByEsquemaService datosLaboraralesByEsquemaService;

    public DatosLaboraralesByEsquemaService getDatosLaboraralesByEsquemaService() {
        return datosLaboraralesByEsquemaService;
    }

    public void setDatosLaboraralesByEsquemaService(DatosLaboraralesByEsquemaService datosLaboraralesByEsquemaService) {
        this.datosLaboraralesByEsquemaService = datosLaboraralesByEsquemaService;
    }
    
    @Autowired
    private ExcelReportesService excelReportesService;
    
    /**
     * Controlador encargado de devolver la vista de los colaboradores en el status solicitado
     * @param estatus Cadena de texto que contiene el nombre de la vista de colaboradores que se desea consultar
     * @return ModelAndView
     */
    @RequestMapping(value = "colaboradores/{estatus}.htm",method = RequestMethod.GET)
    public ModelAndView getColaboradoresActivos(@PathVariable("estatus") String estatus){
        LOGGER.info(LOGGER_PREFIX+"Se solicita la vista con los colaboradores en el estatus de "+estatus);
        Map<String,Object> map = new HashMap<>();
        estatus = estatus.trim().replace('/', '-').replace('\\', '-').replace('-', ' ').toLowerCase();
        try {
            if(isAccesible("apartado "+estatus)||true){
                switch(estatus){
                    case "expedientes por completar":
                        map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
                        return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
                    case "altas solicitadas":
                        map.put("Colaboradores", getAgremiadosByStatus("V.OB.O"));
                        return new ModelAndView("colaborador/vistoBueno", "model", map);
                    case "expedientes sin contrato":
                        map.put("Colaboradores", getAgremiadosByStatus("Expediente Sin Contrato"));
                        return new ModelAndView("colaborador/expedientesSinContrato", "model", map);
                    case "altas en proceso":
                        map.put("Colaboradores", getAgremiadosByStatus("Alta En Proceso"));
                        return new ModelAndView("colaborador/altasEnProceso", "model", map);
                    case "activos":
                        map.put("Colaboradores", getAgremiadosActivos());
                        return new ModelAndView("colaborador/colaboradores", "model", map);
                    case "bajas solicitadas":
                        List<Agremiado> BajasSolicitadas = getAgremiadosByStatus("Baja Solicitada");
                        Map<Integer,SolicitudBaja> ultimaBajasSolicitada = getUltimaBaja(BajasSolicitadas);
                        map.put("Colaboradores", BajasSolicitadas);
                        map.put("ultimaBajasSolicitada", ultimaBajasSolicitada);
                        return new ModelAndView("colaborador/bajasSolicitadas", "model", map);
                    case "bajas pendientes":
                        List<Agremiado> BajasPendientes = getAgremiadosByStatus("Baja Pendiente");
                        Map<Integer,SolicitudBaja> ultimaBajasPendientes = getUltimaBaja(BajasPendientes);
                        map.put("Colaboradores", BajasPendientes);
                        map.put("ultimaBajasSolicitada", ultimaBajasPendientes);
                        return new ModelAndView("colaborador/bajasPendientes", "model", map);
                    case "bajas por finalizar":
                        List<Agremiado> BajasPorFinalizar = getAgremiadosByStatus("Baja Por Finalizar");
                        Map<Integer,SolicitudBaja> ultimaBajasPorFinalizar = getUltimaBaja(BajasPorFinalizar);
                        map.put("Colaboradores", BajasPorFinalizar);
                        map.put("ultimaBajasSolicitada", ultimaBajasPorFinalizar);
                        return new ModelAndView("colaborador/bajasPorFinalizar", "model", map);
                    case "colaboradores dados de baja":
                        List<Agremiado> Bajas = getAgremiadosQueCausaronBaja();
                        Map<Integer,SolicitudBaja> ultimaBajas = getUltimaBaja(Bajas);
                        map.put("Colaboradores", Bajas);
                        map.put("ultimaBajasSolicitada", ultimaBajas);
                        return new ModelAndView("colaborador/bajasFinalizadas", "model", map);
                    case "contratos por vencer":
                        map.put("Colaboradores", getColaboradoresPorVencer());
                        return new ModelAndView("colaborador/contratosPorVencer", "model", map);
                    case "renovaciones solicitadas":
                        List<Agremiado> renovaciónContratoPradetti = getAgremiadosByStatus("Renovación Solicitada");
                        Map<Integer, SolicitudRenovacionContrato> ultimasRenovacionesPradetti = getUltimaRenovacion(renovaciónContratoPradetti);
                        map.put("Colaboradores", renovaciónContratoPradetti);
                        map.put("ultimaRenovacion", ultimasRenovacionesPradetti);
                        return new ModelAndView("colaborador/renovacionesSolicitadas", "model", map);
                    case "solicitudes de renovacion":
                        List<Agremiado> renovaciónContratoCliente = getAgremiadosByStatus("Solicitud De Renovación");
                        Map<Integer, SolicitudRenovacionContrato> ultimasRenovacionesCliente = getUltimaRenovacion(renovaciónContratoCliente);
                        map.put("Colaboradores", renovaciónContratoCliente);
                        map.put("ultimaRenovacion", ultimasRenovacionesCliente);
                        return new ModelAndView("colaborador/solicitudesDeRenovacion", "model", map);
                    case "todos los colaboradores":
                        map.put("Colaboradores", getAgremiadosActivos());
                        return new ModelAndView("colaborador/colaboradores", "model", map);
                    case "baja sin firmar":
                        List<Agremiado> BajasSinFirmar = getAgremiadosByStatus("Baja Sin Firmar");
                        Map<Integer,SolicitudBaja> ultimaBajasPorFinalizarSinFirma = getUltimaBaja(BajasSinFirmar);
                        map.put("Colaboradores", BajasSinFirmar);
                        map.put("ultimaBajasPorFinalizarSinFirma", ultimaBajasPorFinalizarSinFirma);
                        return new ModelAndView("colaborador/bajasSinFirmar", "model", map);
                    default:
                        throw new Exception("Estatus desconocido --> "+estatus);
                }
            }else{
            LOGGER.error(LOGGER_PREFIX+"No cuenta con los permisos necesarios para acceder a esta parte del sistema");
            setInformacionEnVentana(map, 2, MODULO, "No cuenta con los permisos necesarios para acceder a esta parte del sistema.<br>Redireccionando...");
            return new ModelAndView("index","model",map);
            }
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar generar la vista con los colaboradores en el estado "+estatus+" --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un error al intentar generar la vista con los colaboradores en el estado "+estatus);
            return new ModelAndView("index","model",map);
        }
    }
    
    
    /**
     * Controlador encargado de devolver la vista pra agregar los datos personales de un nuevo colaborador
     * @return ModelAndView
     */
    @RequestMapping(value = "colaboradores/nuevo-colaborador.htm",method = RequestMethod.GET)
    public ModelAndView getNuevoColaborador(){
        LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para agregar un nuevo colaborador");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            map.put("edicion", false);
            
            return new ModelAndView("colaborador/formularioColaborador", "model", map);
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar generar la vista para agregar un nuevo colaborador --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar generar la vista para agregar un nuevo colaborador");
            return new ModelAndView("index","model",map);
        }
    }
    
    /**
     * Controlador encargado de ingresar o actualizar los datos personales de un colaborador
     * @param datosPersonales Instancia de DatosPersonales
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/datosPersonales.htm",method = RequestMethod.POST)
    public ModelAndView setDatosLaborales(@ModelAttribute("datosPersonales") DatosPersonales datosPersonales, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se reciben los datos personales de un colaborador {"+datosPersonales.getAgremiado()+"}");
        
        Map<String,Object> map = new HashMap<>();
        try { 
            String curpString = request.getParameter("curp");
            String rfcString = request.getParameter("rfc");
            
            DatosPersonales curp = new DatosPersonales();
            DatosPersonales rfc = new DatosPersonales();
            
            curp = agremiadoService.getifExistsCurp(curpString);
            rfc = agremiadoService.getifExistsRFC(rfcString);
            
            if(curp ==null && rfc ==null){
                Agremiado agremiado;
                String movimiento;
                String schemaString = request.getParameter("schemaId");
                String contratoIdString = request.getParameter("contratoId");

                Short schemaId = Short.parseShort(schemaString);
                Integer contratoId = Integer.parseInt(contratoIdString);

                ContratoEmpresas contratoEmpresasById = agremiadoService.getContratoEmpresasById(contratoId);
                EsquemaPago esquemaPago = agremiadoService.getEsquemaPago(schemaId);

                if(datosPersonales.getAgremiado()==null){
                //Primero se debe de almacenar información laboral del colaborador debido a que si no se hace el colaborador
                // podría quedar en el limbo si el usuario se sale del ingreso de los datos antes de llegar a los datos laborales
                    Cliente cliente = clienteService.getCliente(contratoEmpresasById.getClienteObj());
                    Contratista contratista = contratistaService.getContratista(contratoEmpresasById.getContratistaObj());

                    if(cliente == null || contratista == null){
                         throw new Exception("El cliente o el contratista son nulos y no se puede persistir el colaborador");
                    }else{   
                        agremiado = new Agremiado();
                        short status = getStatus("Expediente Incompleto");
                        agremiado.setStatusAgremiado(status);
                        agremiado.setCreado(new Date());
                        agremiadoService.setAgremiado(agremiado);

                        DatosLaborales datosLaborales = new DatosLaborales();
                        datosLaborales.setEsquemaPago( esquemaPago);
                        datosLaborales.setClienteObj(cliente);
                        datosLaborales.setContratistaObj(contratista);
                        datosLaborales.setAgremiadoObj(agremiado);
                        String FADisponibleString = String.valueOf(agremiadoService.getFondoAhorroDisponible(getNombreDeContrato(contratoEmpresasById)));
                        LOGGER.error("FADisponibleString: " + FADisponibleString);
                        boolean ifExistServiceFA = Boolean.parseBoolean(FADisponibleString);
                        LOGGER.error("ifExistServiceFA: " + ifExistServiceFA);
                        datosLaborales.setFondoDeAhorroDisponible(ifExistServiceFA);
                        datosLaborales.setAntiguedadReconocida(Boolean.FALSE);
                        datosLaborales.setNombreContrato(getNombreDeContrato(contratoEmpresasById));
                        datosLaborales.setPercepcionBasadaEnSUP(Boolean.FALSE);
                        agremiadoService.setDatosLaborales(datosLaborales);
                        Agremiado agremiadoTemp= agremiadoService.getAgremiado(agremiado);
                        agremiado.setDatosLaborales(agremiadoTemp.getDatosLaborales());
                        datosPersonales.setAgremiadoObj(agremiado);
                        movimiento = "Inserción";
                    }
                }
                else{
                    agremiado = agremiadoService.getAgremiado(datosPersonales.getAgremiado());
                    datosPersonales.setAgremiadoObj(agremiado);
                    datosPersonales.setAgremiado(agremiado.getIdAgremiado());
                    movimiento = "Actualización";
                }
                String parameter = request.getParameter("fechaNa");
                Date procesayyyyMMdd = procesayyyyMMdd(parameter);
                datosPersonales.setFechaNacimiento(procesayyyyMMdd);
                parameter = request.getParameter("hijos");
                 String hijos;
                if(parameter != null){               
                     hijos = parameter;
                }else{
                    hijos = "NO";
                }
                datosPersonales.setHijos(hijos);
                agremiadoService.setDatosPersonales(datosPersonales);
                agremiado.setModificado(new Date());
                agremiado.setDatosPersonales(datosPersonales);
                agremiadoService.setAgremiado(agremiado);
                datosPersonales = agremiado.getDatosPersonales();
                setInformacionEnVentana(map, 0, MODULO, "Los datos personales de <b>"+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" </b> se han guardado exitosamente.");
                getDatosAgremiado(map, agremiado.getIdAgremiado());
                map.put("edicion", true);
                map.put("estatus", agremiado.getStatusAgremiado());
                setBitacora(movimiento, "Ingresó correctamente todos los datos personales de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
                setCamposDispoiblesAndContratoEsquema(map, esquemaPago, contratoId);
                return new ModelAndView("colaborador/formularioColaborador", "model", map);
            }else if (curp != null && rfc != null){
                Integer idAgremiado = curp.getAgremiado();
                Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                Short idStatusAgremiado = agremiado.getStatusAgremiado();
                StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(idStatusAgremiado);
                String statusAgremiadoString = statusAgremiado.getStatus();
                LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos personales de un colaborador --> CURP Y RFC existentes");
                setInformacionEnVentana(map, 1, MODULO, "Ya existe un colaborador registrado con el mismo CURP y RFC en el estado de " + statusAgremiadoString);
                map.put("Colaboradores", getAgremiadosActivos());
                return new ModelAndView("index", "model", map);
            }else if(rfc != null){
                Integer idAgremiado = rfc.getAgremiado();
                Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                Short idStatusAgremiado = agremiado.getStatusAgremiado();
                StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(idStatusAgremiado);
                String statusAgremiadoString = statusAgremiado.getStatus();
                LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos personales de un colaborador --> RFC existente");
                setInformacionEnVentana(map, 1, MODULO, "Ya existe un colaborador registrado con el mismo RFC en el estado de " + statusAgremiadoString);
                map.put("Colaboradores", getAgremiadosActivos());
                return new ModelAndView("index", "model", map);
            }else if(curp != null){
                Integer idAgremiado = curp.getAgremiado();
                Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                Short idStatusAgremiado = agremiado.getStatusAgremiado();
                StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(idStatusAgremiado);
                String statusAgremiadoString = statusAgremiado.getStatus();
                LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos personales de un colaborador --> CURP existente");
                setInformacionEnVentana(map, 1, MODULO, "Ya existe un colaborador registrado con el mismo CURP en el estado de " + statusAgremiadoString);
                map.put("Colaboradores", getAgremiadosActivos());
                return new ModelAndView("index", "model", map);
            }
            //hasta aquí 
        } catch (ParseException pe) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos personales de un colaborador la fecha tiene un formato incorrecto --> "+pe.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar almacenar los datos personales de un colaborador<br>La fecha de nacimiento tiene un formato incorrecto");
            map.put("Colaboradores", getAgremiadosActivos());
            return new ModelAndView("colaborador/colaboradores", "model", map);
        }catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos personales de un colaborador --> "+e.getMessage(),e);
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar almacenar los datos de un colaborador");
            map.put("Colaboradores", getAgremiadosActivos());
            return new ModelAndView("colaborador/colaboradores", "model", map);
        }
        return new ModelAndView("colaborador/formularioColaborador", "model", map);
    }
    
    /**
     * Controlador encargado de devolver la vista para la edición de todos los datos de un colaborador
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @return ModelAndView
     */
    @RequestMapping(value="colaboradores/editar-registro/{idAgremiado}.htm", method = RequestMethod.GET)
    public ModelAndView getRegistroIncompleto(@PathVariable("idAgremiado") Integer idAgremiado,HttpServletRequest request){
        LOGGER.info(LOGGER_PREFIX+"Se Solicita la edición del registro de colaborador--- {"+idAgremiado+'}');
        
        Map<String, Object> map = new HashMap<>();        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);       
            if(agremiado==null){
                throw new Exception("No existe ningun registro con el idAgremiado "+idAgremiado);
            }else if(agremiado.getDatosLaborales().getNombreContrato()==null){
                map.put("agremiado", agremiado);           
                List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(agremiado.getDatosLaborales().getClienteObj().getIdCliente());   
                 List<DelContrato> contratosDelClienteTemp = new ArrayList<>(contratosDelCliente.size());
                 contratosDelCliente.stream().filter((delContrato) -> (delContrato.getIdContratista() == agremiado.getDatosLaborales().getContratistaObj().getIdContratista())).forEachOrdered((delContrato) -> {
                     contratosDelClienteTemp.add(delContrato);
                });
                map.put("contratos", contratosDelClienteTemp);
                map.put("esquemas", agremiadoService.getEsquemaDePagos());  
                map.put("viewResponse", "form");         
                return new ModelAndView("colaborador/delClienteYDelContratoOlders", "model", map);
            }else{
            if(agremiado.getStatusAgremiado() == getStatus("Expediente Incompleto")){
                getDatosAgremiado(map, agremiado.getIdAgremiado());
                map.put("edicion", true);                    
                map.put("estatus", agremiado.getStatusAgremiado());
                setCamposDispoiblesAndContratoEsquema(map, agremiado.getDatosLaborales().getEsquemaPago(), agremiado.getDatosLaborales().getNombreContrato());
                return new ModelAndView("colaborador/formularioColaborador", "model", map);
            }else{
                throw new Exception("El estatus {"+agremiado.getStatusAgremiado() +"} del agremiado {"+idAgremiado+"} no permite su modificación desde esta vista.");
            }
        }            
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar devolver el registro de colaborador "+idAgremiado+" --> "+e.getMessage(),e);
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar obtener dicho registro, favor de verificar su existencia o el estado del mismo con el administrador");            
            map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
            return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
        }
    }
    
    /**
     * Controlado encargado de ingresar o actualizar los datos del domicilio de un colaborador especifico
     * @param datosDomicilio Instancia de DatosDomicilio
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="colaborador/datosDomicilio.htm",method = RequestMethod.POST)
    public ModelAndView SetDatosDomicilio(@ModelAttribute("datosDomicilio") DatosDomicilio datosDomicilio, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se reciben los datos del domicilio del colaborador {"+request.getParameter("idAgremiado")+'}');
        
        Map<String,Object> map = new HashMap<>();
        Integer idAgremiado = Integer.parseInt(request.getParameter("idAgremiado"));
        
        try {
            String schemaString = request.getParameter("schemaId");
            String contratoIdString = request.getParameter("contratoId");
            
            Short schemaId = Short.parseShort(schemaString);
            Integer contratoId = Integer.parseInt(contratoIdString);
            
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            if(agremiado==null){
                throw new Exception("No existe ningun registro con el idAgremiado "+idAgremiado);
            }else{
                
                String movimiento;
                if(datosDomicilio.getAgremiado() == null){
                    movimiento = "Inserción";
                }else{
                    movimiento = "Actualización";
                }
                
                datosDomicilio.setAgremiadoObj(agremiado);
                agremiadoService.setDatosDomicilio(datosDomicilio);   
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                
                setInformacionEnVentana(map, 0, MODULO, "Los datos del domicilio de <b>"+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" </b> se han guardado exitosamente.");
                getDatosAgremiado(map, agremiado.getIdAgremiado());
                map.put("edicion", true);                
                map.put("estatus", agremiado.getStatusAgremiado());
                setBitacora(movimiento, "Ingresó correctamente todos los datos del domicilio de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
                setCamposDispoiblesAndContratoEsquema(map, schemaId, contratoId);
                return new ModelAndView("colaborador/formularioColaborador", "model", map);
            }            
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos del domicilio del colaborador "+idAgremiado+" --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar almacenar los datos del domicilio del colaborador ");            
            map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
            return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
        }
    }
    
    /**
     * Controlador encargado de devolver los contratistas de un cliente especificado en la lista de clientes de los datos laborales
     * @param idCliente Numero entero que almacena el id de un cliente
     * @return String
     */
    @RequestMapping(value="colaborador/contratistas-del-cliente/{idCliente}.htm",method = RequestMethod.POST)
    @ResponseBody
    public String getContratistasDelCliente(@PathVariable("idCliente") Integer idCliente){
        LOGGER.info(LOGGER_PREFIX+"Se solicitan los contratistas con los cuales tiene contrato un cliente {"+idCliente+'}');
        List<Contratista> contratistas = contratistaService.getContratistas(idCliente);
        ObjectMapper mapper = new ObjectMapper();
        try {
           Map<Integer,String> map = new HashMap<>(contratistas.size());
           contratistas.stream().forEach((contratista) -> {
               map.put(contratista.getIdContratista(), contratista.getNombreEmpresa());
            });
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException jpe) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al momento parser las infomarción de lso contratistas a formato JSON  -> "+jpe.getMessage());
            return "";
        }
    }
    
    /**
     * Controlador encargado de devolver el calculo de los salarios minimos del campo de salario diario
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return String
     */
    @RequestMapping(value="colaborador/contratistas-del-cliente.htm",method = RequestMethod.POST)
    @ResponseBody
    public String getSalarios(HttpServletRequest request, HttpServletResponse response){
                String cliente = request.getParameter("cliente");
                String sd = request.getParameter("sd");
                String idAgremiado = request.getParameter("idagremiado");        
                LOGGER.info(LOGGER_PREFIX+"Calculando los salarios del colaborador {"+idAgremiado+','+sd+"}");
        try {    
            double sd_double = Double.parseDouble(sd);
            int idCliente = Integer.parseInt(cliente);
            Cliente clienteObj = clienteService.getCliente(idCliente);
            ZonaSm zonaSM = zonasSmService.getZonaSM(clienteObj.getZonaSm());
            double salarioDiarioIntegrado = sd_double * getFI(Integer.parseInt(idAgremiado));
            double salariosImss = sd_double / zonaSM.getSalario();
            DecimalFormat formateador = new DecimalFormat("###,###.##");
            Map<String,String> map = new HashMap<>(2);
            map.put("salarioDiarioIntegrado", formateador.format (salarioDiarioIntegrado));
            map.put("salariosImss", formateador.format (salariosImss));
                
            ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(map);
            
        } catch (NumberFormatException nfe) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de parsear la información recibida para el calculo de los salarios {"+idAgremiado+','+sd+','+cliente+"}  --> "+nfe.getMessage());
            return "";
        }catch (JsonProcessingException jpe) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al calcular los salarios del colaborador --JsonProcessingException-- {"+idAgremiado+','+sd+"}  --> "+jpe.getMessage());
            return "";
        }catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al calcular los salarios del colaborador {"+idAgremiado+','+sd+"}  --> "+e.getMessage());
            return "";
        }
    }
    
    /**
     * Controlador encargado de ingresar o actualizar los datos laborales de un colaborador
     * @param datosLaborales Instancia de DatosLaborales
     * @param request Variables que maneja las peticiones
     * @param response Variables que maneja las respuestas 
     * @return ModelAndView
     * @deprecated metodo reemplazado por el metodo saveDatosLaborales
     */
    @Deprecated
    public ModelAndView setDatosLaborales(@ModelAttribute("datosLaborales") DatosLaborales datosLaborales, HttpServletRequest request, HttpServletResponse response){
            LOGGER.info(LOGGER_PREFIX+" {OLD} Se reciben los datos laborales del colaborador {"+request.getParameter("idAgremiado")+'}');
        
        throw new IllegalStateException("solcitud de metodo obsoleto setDatosLaborales");
//        
//        Map<String,Object> map = new HashMap<>();
//        Integer idAgremiado = Integer.parseInt(request.getParameter("idAgremiado"));
//        try {
//            String schemaString = request.getParameter("schemaId");
//            String contratoIdString = request.getParameter("contratoId");
//            
//            Short schemaId = Short.parseShort(schemaString);
//            Integer contratoId = Integer.parseInt(contratoIdString);
//            
//            EsquemaPago esquemaPago = agremiadoService.getEsquemaPago(schemaId);
//            
//            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
//            if(agremiado==null){
//                throw new Exception("No existe ningun registro con el idAgremiado "+idAgremiado);
//            }else{
//                DatosLaborales dl = agremiado.getDatosLaborales();
//                String movimiento;
//                if(dl.getFechaAltaImss() == null){
//                    movimiento = "Inserción";
//                }else{
//                    movimiento = "Actualización";
//                }
//                
//                Date fechaAlta = procesayyyyMMdd(request.getParameter("fechaAlta"));
//                short periodo = Short.parseShort(request.getParameter("prdDPg"));
//                TipoNomina tipoNomina = agremiadoService.getTipoNomina(periodo);
//                String tarjetaTdu = request.getParameter("tarjetaTdu");
//                String credencialLaboral = request.getParameter("credencialLaboral");
//                String reconocimientoAntiguedad = request.getParameter("reconocimientoAntiguedad");
//                short contrato = Short.parseShort(request.getParameter("tpDCntrt"));
//                
//                if(tarjetaTdu==null){
//                    tarjetaTdu = "NO";
//                }
//                if(credencialLaboral==null){
//                    credencialLaboral = "NO";
//                }
//                if(reconocimientoAntiguedad==null){
//                    reconocimientoAntiguedad = "NO";
//                }
//                TipoContrato tipoContrato = agremiadoService.getTipoContrato(contrato);
//                
//                dl.setFechaAltaImss(fechaAlta);
//                dl.setSueldoMensual(datosLaborales.getSueldoMensual());
//                String salarioDario = "0.0";
//                if(datosLaborales.getSalarioDiario()!=null){
//                    if(!datosLaborales.getSalarioDiario().isEmpty()){
//                        salarioDario = datosLaborales.getSalarioDiario();
//                    }
//                }
//                dl.setSalarioDiario(salarioDario);
//                double sd_double = Double.parseDouble(salarioDario);
//                double salarioDiarioIntegrado = sd_double * getFI(agremiado.getIdAgremiado());
//                ZonaSm zonaSM = zonasSmService.getZonaSM(dl.getClienteObj().getZonaSm());
//                double salariosImss = sd_double / zonaSM.getSalario();
//                DecimalFormat formateador = new DecimalFormat("###,###.##");
//                dl.setSalariosImss( formateador.format (salariosImss));
//                dl.setSalarioDiarioIntegrado(formateador.format(salarioDiarioIntegrado));
//                dl.setTipoNominaObj(tipoNomina);
//                dl.setTarjetaTdu(tarjetaTdu);
//                dl.setCredencialLaboral(credencialLaboral);
//                dl.setReconocimientoAntiguedad(reconocimientoAntiguedad);
//                dl.setNumeroSeguro(datosLaborales.getNumeroSeguro());
//                dl.setActividadProfesional(datosLaborales.getActividadProfesional());
//                dl.setAreaDepartamento(datosLaborales.getAreaDepartamento());
//                dl.setLugarTrabajo(datosLaborales.getLugarTrabajo());
//                dl.setNumeroInfonavit((datosLaborales.getNumeroInfonavit().isEmpty())?null:datosLaborales.getNumeroInfonavit());
//                dl.setTipoContratoObj(tipoContrato);
//                if(tipoContrato.getDescripcion().equalsIgnoreCase("indeterminado")){       
//                    dl.setPeriodoContrato(null);
//                    dl.setFechaFinContrato(null);
//                    dl.setFechaTimbreContrato(null);
//                }else{
//                    String periodoReal = dl.getPeriodoContrato();
//                    if(datosLaborales.getPeriodoContrato() != null){
//                        if(!datosLaborales.getPeriodoContrato().isEmpty()){
//                            periodoReal = datosLaborales.getPeriodoContrato();
//                        }
//                    }
//                    if(periodoReal != null){
//                            if(periodoReal.isEmpty()){
//                                periodoReal = "1";
//                            }
//                    }                
//                    dl.setPeriodoContrato(periodoReal);
//                    int intPeriodo = Integer.parseInt(periodoReal);
//                   intPeriodo = Math.abs(intPeriodo);
//                    Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(fechaAlta);
//                    calendar.add(Calendar.MONTH, intPeriodo); 
//                    Date fechaFin = calendar.getTime();
//                    dl.setFechaFinContrato(fechaFin);
//                    
//                    calendar = Calendar.getInstance();
//                    calendar.setTime(fechaFin);
//                    calendar.add(Calendar.DATE, -7);
//                    Date fechaTimbre = calendar.getTime();
//                    dl.setFechaTimbreContrato(fechaTimbre);
//                }
//                                            
//                agremiadoService.setDatosLaborales(dl);   
//                
//                agremiado.setModificado(new Date());
//                agremiadoService.setAgremiado(agremiado);
//                
//                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
//                
//                setInformacionEnVentana(map, 0, MODULO, "Los datos laborales de <b>"+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" </b> se han guardado exitosamente.");
//                getDatosAgremiado(map, agremiado.getIdAgremiado());
//                map.put("edicion", true);
//                map.put("estatus", agremiado.getStatusAgremiado());
//                setCamposDispoiblesAndContratoEsquema(map, esquemaPago, contratoId);
//                setBitacora(movimiento, "Ingresó correctamente todos los datos laborales de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
//                return new ModelAndView("colaborador/formularioColaborador", "model", map);
//            }            
//        } catch (Exception e) {
//            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos laborales del colaborador "+idAgremiado+" --> "+e.getMessage(),e);
//            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar almacenar los datos laborales del colaborador ");            
//            map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
//            return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
//        }
    }
    
    /**
     * Controlador encargado de ingresar o actualizar los datos laborales de un colaborador
     * @param datosLaborales
     * @param request
     * @param response
     * @return 
     */
    @RequestMapping(value="colaborador/datosLaborales.htm",method = RequestMethod.POST)
    public ModelAndView saveDatosLaborales(@ModelAttribute("datosLaborales") DatosLaborales datosLaborales, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita la persistencia de los datos Laborales "+datosLaborales.getAgremiado());
          Agremiado agremiado = agremiadoService.getAgremiado(Integer.parseInt(request.getParameter("idAgremiado")));
          Map<String,Object> map = new HashMap<>();
        try {
            String schemaString = request.getParameter("schemaId");
            String contratoIdString = request.getParameter("contratoId");
            
            Short schemaId = Short.parseShort(schemaString);
            Integer contratoId = Integer.parseInt(contratoIdString);
            
            EsquemaPago esquemaPago = agremiadoService.getEsquemaPago(schemaId);

            DatosLaborales dl = agremiado.getDatosLaborales();
            String movimiento;                
            if(agremiado.getDatosLaborales().getTipoContratoObj()==null){
                movimiento = "Inserción";
            }else{
                movimiento = "Actulización";
            }
            dl = datosLaboraralesByEsquemaService.getDatosLaborales(dl, datosLaborales, request);
            agremiadoService.setDatosLaborales(dl);   

            agremiado.setModificado(new Date());
            agremiadoService.setAgremiado(agremiado);

            DatosPersonales datosPersonales = agremiado.getDatosPersonales();

            setInformacionEnVentana(map, 0, MODULO, "Los datos laborales de <b>"+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" </b> se han guardado exitosamente.");
            getDatosAgremiado(map, agremiado.getIdAgremiado());
            map.put("edicion", true);
            map.put("estatus", agremiado.getStatusAgremiado());
            setCamposDispoiblesAndContratoEsquema(map, esquemaPago, contratoId);
            setBitacora(movimiento, "Ingresó correctamente todos los datos laborales de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
            return new ModelAndView("colaborador/formularioColaborador", "model", map);
                     
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos laborales del colaborador "+agremiado.getIdAgremiado()+" --> "+e.getMessage(),e);
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar almacenar los datos laborales del colaborador ");            
            map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
            return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
        }
    }
    
    /**
     * Controlador encargado de ingresar o actualizar los datos del beneficiaro de un colaborador 
     * @param beneficiario Intancia de DatosBeneficiario
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="colaborador/datosBeneficiario.htm",method = RequestMethod.POST)
    public ModelAndView setDatosBeneficiario(@ModelAttribute("datosBeneficiario") DatosBeneficiario beneficiario, HttpServletRequest request, HttpServletResponse response){
            LOGGER.info(LOGGER_PREFIX+"Se reciben los datos del beneficiario del colaborador {"+request.getParameter("idAgremiado")+'}');
            
            Map<String,Object> map = new HashMap<>();
            Integer idAgremiado = Integer.parseInt(request.getParameter("idAgremiado"));
            String schemaString = request.getParameter("schemaId");
            String contratoIdString = request.getParameter("contratoId");
            
            Short schemaId = Short.parseShort(schemaString);
            Integer contratoId = Integer.parseInt(contratoIdString);                       
            
             Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
             
            try {
                 if(agremiado==null){
                throw new Exception("No existe ningun registro con el idAgremiado "+idAgremiado);
            }else{
                     String movimiento;
                    if(agremiado.getDatosBeneficiario() == null){
                        movimiento = "Inserción";
                        beneficiario.setAgremiadoObj(agremiado);
                    }else{
                        movimiento = "Actualización";
                        beneficiario.setAgremiado(idAgremiado);
                        beneficiario.setAgremiadoObj(agremiado);
                    }
                     
                     String nacimiento = request.getParameter("fechaNaBen");
                     Date nacimientoBeneficiario = procesayyyyMMdd(nacimiento);
                     beneficiario.setPorcentaje("100");
                     beneficiario.setFechaNacimiento(nacimientoBeneficiario);
                     agremiadoService.setDatosBeneficiario(beneficiario);
                     
                    DatosPersonales datosPersonales = agremiado.getDatosPersonales();

                    setInformacionEnVentana(map, 0, MODULO, "Los datos del benficiario de <b>"+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" </b> se han guardado exitosamente.");
                    getDatosAgremiado(map, agremiado.getIdAgremiado());
                    map.put("edicion", true);
                    map.put("estatus", agremiado.getStatusAgremiado());
                    setBitacora(movimiento, "Ingresó correctamente todos los datos del beneficiario de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
                     setCamposDispoiblesAndContratoEsquema(map, schemaId, contratoId);
                    return new ModelAndView("colaborador/formularioColaborador", "model", map);
            }
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos del beneficiario del colaborador "+idAgremiado+" --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar almacenar los datos del beneficiario del colaborador ");            
            map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
            return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
        }
    }
    
    /**
     * Controlador encargado de ingresar o actualizar los datos bancarios de un colaborador
     * @param datosBancarios Instancia de DatosBancarios
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/datosBancarios.htm",method = RequestMethod.POST)
    public ModelAndView setDatosBancarios(@ModelAttribute("datosBancarios") DatosBancarios datosBancarios, HttpServletRequest request, HttpServletResponse response){
            LOGGER.info(LOGGER_PREFIX+"Se reciben los datos bancarios del colaborador {"+request.getParameter("idAgremiado")+'}');
            
            Map<String,Object> map = new HashMap<>();
            Integer idAgremiado = Integer.parseInt(request.getParameter("idAgremiado"));
            String schemaString = request.getParameter("schemaId");
            String contratoIdString = request.getParameter("contratoId");
            
            Short schemaId = Short.parseShort(schemaString);
            Integer contratoId = Integer.parseInt(contratoIdString);
                       
             Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
             
            try {
                 if(agremiado==null){
                    throw new Exception("No existe ningun registro con el idAgremiado "+idAgremiado);
                }else{

                     String movimiento;
                    if(agremiado.getDatosBancarios()== null){
                        movimiento = "Inserción";
                        datosBancarios.setAgremiadoObj(agremiado);
                    }else{
                        movimiento = "Actualización";
                        datosBancarios.setAgremiado(idAgremiado);
                        datosBancarios.setAgremiadoObj(agremiado);
                    }
                    
                     agremiadoService.setDatosBancarios(datosBancarios);
                     
                    DatosPersonales datosPersonales = agremiado.getDatosPersonales();

                    setInformacionEnVentana(map, 0, MODULO, "Los datos bancarios de <b>"+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" </b> se han guardado exitosamente.");
                    getDatosAgremiado(map, agremiado.getIdAgremiado());
                    map.put("edicion", true);
                    map.put("estatus", agremiado.getStatusAgremiado());
                    
                    setBitacora(movimiento, "Ingresó correctamente todos los datos bancarios de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
                     setCamposDispoiblesAndContratoEsquema(map, schemaId, contratoId);
                    return new ModelAndView("colaborador/formularioColaborador", "model", map);                    
                }
            }catch (Exception e) {
                LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar los datos bancarios del colaborador "+idAgremiado+" --> "+e.getMessage());
                setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar almacenar los datos bancarios del colaborador ");            
                map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
                return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
        }
    }
    
    /**
     * Controlador encargado de ingresar o actualizar el documento enviado desde la vista de documentos
     * @param archivo Archivos con extension pdf, jpeg
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     */
    @RequestMapping(value = "colaborador/documento.htm",method = RequestMethod.POST)
    public void setDocumento(@RequestParam("archivo") MultipartFile archivo,HttpServletRequest request, HttpServletResponse response){
        String tipo = request.getParameter("tipo");
        String idAgremiado = request.getParameter("idAgremiado");
        String nombreArchivo = request.getParameter("name");
        LOGGER.info(LOGGER_PREFIX+"Se recibe el documento {"+nombreArchivo+"} "+tipo+" para el colaborador "+idAgremiado);
        
        try {
            int tipoInt = Integer.parseInt(tipo);
            int tipoidAgremiado= Integer.parseInt(idAgremiado);
            
            TipoDocumento tipoDocumento = agremiadoService.getTipoDocumento(tipoInt);
            Agremiado agremiado = agremiadoService.getAgremiado(tipoidAgremiado);
            Modulo modulo = agremiadoService.getModulo( tipoDocumento.getModuloObj());
                                    
            if(!archivo.isEmpty()){
                
                DocumentoPK dpk = new DocumentoPK();
                dpk.setAgremiado(agremiado.getIdAgremiado());
                dpk.setTipoDocumento(tipoDocumento.getIdTipoDocumento());
                
                Documento docTemp = null;
                List<Documento> documentoList = agremiado.getDocumentoList();
                for (Documento documento : documentoList) {
                    if(Objects.equals(documento.getTipoDocumentoObj().getIdTipoDocumento(), tipoDocumento.getIdTipoDocumento())){
                        if(documento.getUrlDocumento() != null){
                            ftpService.borrarArchivoFTP(documento.getUrlDocumento());
                            documento.setUrlDocumento(null);
                        }
                        String guardarArchivoFTP = ftpService.guardarArchivoFTP(archivo, MODULO, idAgremiado,modulo.getNombre().toUpperCase());
                        documento.setUrlDocumento(guardarArchivoFTP);
                        documento.setNombreDocumento(nombreArchivo);
                        documento.setModificado(new Date());
                        docTemp = documento;
                        break;
                    }
                }
                String movimiento ;
                if(docTemp == null){
                    docTemp = new Documento();
                    docTemp.setAgremiadoObj(agremiado);
                    docTemp.setCreado(new Date());
                    docTemp.setModificado(new Date());
                    docTemp.setNombreDocumento(nombreArchivo);
                    docTemp.setTipoDocumentoObj(tipoDocumento);
                    String guardarArchivoFTP = ftpService.guardarArchivoFTP(archivo, MODULO, idAgremiado,modulo.getNombre().toUpperCase());
                    docTemp.setUrlDocumento(guardarArchivoFTP);
                    docTemp.setDocumentoPK(dpk);
                    documentoList.add(docTemp);
                    movimiento = "Inserción de documento";
                }else{
                    movimiento = "Edición de documento";
                }
                
                agremiadoService.setDocumento(docTemp);
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                setBitacora(movimiento, "Guardó correctamente el documento '"+tipoDocumento.getNombreDocumento()+"' de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
            }else{
                throw new Exception("El archivo viene vacío para el documento");
            }            
        } catch (Exception e) {
                LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar almacenar el documento "+tipo+" para el colaborador "+idAgremiado+" --> "+e.getMessage());
        }
    }
    
    /**
     * Controlador encargado de devolver los documentos que se han cargado en un apartado especifico para un colaborador dado
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return String
     */
    @RequestMapping(value = "colaborador/documentos-del-colaborador.htm",method = RequestMethod.POST)
    @ResponseBody
    public String getDocumentosDelColaborador(HttpServletRequest request, HttpServletResponse response){
                String tipoDoc = request.getParameter("doc");
                String idAgremiado = request.getParameter("idAgremiado");        
                LOGGER.info(LOGGER_PREFIX+"Se solicitan los documentos del agremiedo "+idAgremiado+", similares a "+tipoDoc+"}");
                
                try {
                        int idAgremiadoInt = Integer.parseInt(idAgremiado);
                        int tipoDocInt = Integer.parseInt(tipoDoc);

                        TipoDocumento tipoDocumento = agremiadoService.getTipoDocumento(tipoDocInt);
                        Modulo modulo = agremiadoService.getModulo(tipoDocumento.getModuloObj());
                        Agremiado agremiado = agremiadoService.getAgremiado(idAgremiadoInt);
                        List<Documento> documentos = agremiadoService.getDocumentos(agremiado, modulo);
                         Map<Integer,String> documentosDelColaborador = new HashMap<>(documentos.size());
                         documentos.forEach((Documento documento) -> {
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosDelColaborador.put(documento.getTipoDocumentoObj().getIdTipoDocumento(), documento.getTipoDocumentoObj().getNombreDocumento());
                             }
                         });
                                                  
                        Map<String,Object> map = new HashMap<>(2);
                        map.put("documentos", documentosDelColaborador);

                        return new ObjectMapper().writeValueAsString(map);
                } catch (NumberFormatException nfe) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de parsear la información recibida par obtener los documentos{"+idAgremiado+','+tipoDoc+"}  --> "+nfe.getMessage());
                    return "";
                }catch (JsonProcessingException jpe) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al obtener los documentos del colaborador --JsonProcessingException-- {"+idAgremiado+','+tipoDoc+"}  --> "+jpe.getMessage());
                    return "";
                }catch (Exception e) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al obtener los documentos del colaborador {"+idAgremiado+','+tipoDoc+"}  --> "+e.getMessage());
                    return "";
                }
    }
    
    /**
     * Controlador especifico encargado de devolver un documento solicitado
     * @param tipoDoc Numero entero que contiene un el id de un tipo de documento
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     */
    @RequestMapping(value = "colaborador/ver-documentos/{tipoDocumento}/{idAgremiado}.htm", method = RequestMethod.GET)
    public void getDocumento(@PathVariable("tipoDocumento") Integer tipoDoc, @PathVariable("idAgremiado") Integer idAgremiado,HttpServletRequest request, HttpServletResponse response){
                LOGGER.info(LOGGER_PREFIX+"Se solicitan el documento "+tipoDoc+" del agremiedo "+idAgremiado);
                
                try {
                    TipoDocumento tipoDocumento = agremiadoService.getTipoDocumento(tipoDoc);
                    Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                    DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                    Documento documento = agremiadoService.getDocumento(agremiado, tipoDocumento);
                    if((documento!=null)&&(documento.getUrlDocumento()!=null)){
                        String urlDocumento = documento.getUrlDocumento();                        
                    File descargarFileFTP = ftpService.descargarFileFTP(urlDocumento);
                        if(urlDocumento.toLowerCase().endsWith(".pdf")){                                   
                                        InputStream is = new FileInputStream(descargarFileFTP);
                                        response.setHeader("Content-Disposition", "inline;filename=\"" + tipoDocumento.getNombreDocumento().toUpperCase()+"__"+datosPersonales.getApellidoPaterno().toUpperCase()+"_"+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"_"+datosPersonales.getNombre().toUpperCase() + ".pdf\"");
                                        OutputStream sos = response.getOutputStream();
                                        response.setContentType("application/pdf");
                                        IOUtils.copy(is, sos);
                                        sos.flush();  
                        }else if(urlDocumento.toLowerCase().endsWith(".jpg")||urlDocumento.toLowerCase().endsWith(".jpeg")){                            
                            response.setContentType("image/jpeg");
                        }else{
                            String fileName = "";
                            if(urlDocumento.contains("\\")){
                                int indx = urlDocumento.lastIndexOf("\\");
                                fileName = urlDocumento.substring(indx);
                            }else{
                                int indx = urlDocumento.lastIndexOf("/");
                                fileName = urlDocumento.substring(indx);
                            }
                            InputStream is = new FileInputStream(descargarFileFTP);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
                            OutputStream sos = response.getOutputStream();
                            IOUtils.copy(is, sos);
                            sos.flush();
                        }
                        
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
                    }
                }catch (NumberFormatException nfe) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar obtener el documento --NumberFormatException-- {"+idAgremiado+','+tipoDoc+"}  --> "+nfe.getMessage());
                }catch (FileNotFoundException fnfe) {
                    LOGGER.error(LOGGER_PREFIX+"El archivo no puede ser generado para su visualización --FileNotFoundException-- --> "+ fnfe.getMessage());
                } catch (IOException ioe) {
                    LOGGER.error(LOGGER_PREFIX+"El archivo no puede ser generado para su visualización --IOException-- --> "+ ioe.getMessage());
                }catch (Exception e) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar obtener el documento {"+idAgremiado+','+tipoDoc+"}  --> "+e.getMessage());
                }
        
    }
    
    /**
     * Controlador encargado de borrar un documento dde un colaborador especifico
     * @param tipoDoc Numero entero que contiene el id de un tipo de documento
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que manejar las respuestas
     */
    @RequestMapping(value = "colaborador/borrar-documento/{tipoDocumento}/{idAgremiado}.htm", method = RequestMethod.POST)
    public void deleteDocumento(@PathVariable("tipoDocumento") Integer tipoDoc, @PathVariable("idAgremiado") Integer idAgremiado,HttpServletRequest request, HttpServletResponse response){
                LOGGER.info(LOGGER_PREFIX+"Se solicitan el borrado del documento "+tipoDoc+" del agremiedo"+idAgremiado);
                
                try {
                        TipoDocumento tipoDocumento = agremiadoService.getTipoDocumento(tipoDoc);
                        Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                        Documento documento = agremiadoService.getDocumento(agremiado, tipoDocumento);
                        if((documento!=null)&&(documento.getUrlDocumento()!=null)){
                           Modulo modulo =  agremiadoService.getModulo( documento.getTipoDocumentoObj().getModuloObj());
                            List<Documento> documentos = agremiadoService.getDocumentos(agremiado, modulo);
                            if(documentos.contains(documento)){
                               boolean remove = documentos.remove(documento);
                               if(remove){
                                   if(documento.getUrlDocumento() != null){
                                       ftpService.borrarArchivoFTP(documento.getUrlDocumento());
                                   }
                                   documento.setUrlDocumento(null);
                                   documento.setNombreDocumento("---DOCUMENTO BORRADO DESDE EL SISTEMA---");
                                   documento.setModificado(new Date());
                                   agremiadoService.setDocumento(documento);
                                   DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                                    setBitacora("Eliminación", "Eliminó correctamente el documento "+tipoDocumento.getNombreDocumento()+" de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
                                   LOGGER.info(LOGGER_PREFIX+"El documento fue eliminado con exito {"+tipoDoc+','+idAgremiado+'}');
                               }else{
                                   throw new Exception("El documento no pudo ser eliminado de la lista de documento {"+tipoDoc+','+idAgremiado+'}');
                               }
                            }else{
                                throw new FileNotFoundException("Documento no encontrado dentro de la lista de documentos recogidos de la base de datos {"+tipoDoc+','+idAgremiado+'}');
                            }
                        }else{
                            throw new Exception("No se encontró ningún doumento para se eliminado con las caracteristicas recibidas {"+tipoDoc+','+idAgremiado+'}');
                        }
                } catch (Exception e) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar borrar el documento {"+idAgremiado+','+tipoDoc+"}  --> "+e.getMessage());
                }
    }
    
    /**
     * Contolador encargado de de verificar si el colaborador cumple con los requisitos minimos para ser movido de status
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/verificar-expediente-incompleto.htm",method = RequestMethod.POST)
    public ModelAndView verificarExpedienteIncompleto(HttpServletRequest request, HttpServletResponse response){
        String idAgremiadoString = request.getParameter("idAgremiado");
        LOGGER.info(LOGGER_PREFIX+"Se solicita la validación del expediente para el colaborador "+idAgremiadoString);        
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            int idAgremiado = Integer.parseInt(idAgremiadoString);
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            Short statusAgremiadoShort = agremiado.getStatusAgremiado();
            StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(statusAgremiadoShort);
            Modulo modulo = getModulo(statusAgremiado.getStatus());
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            DatosDomicilio datosDomicilio = agremiado.getDatosDomicilio();
            DatosLaborales datosLaborales = agremiado.getDatosLaborales();
            DatosBancarios datosBancarios = agremiado.getDatosBancarios();
            if( (datosPersonales != null) && (datosDomicilio != null) && (datosLaborales != null && datosLaborales.getFechaInicioContrato()!=null )){
                List<TipoDocumento> documentosPorSubirParaSolicitarUnAlta = getDocumentosPorSubirParaSolicitarUnAlta(agremiado.getDatosLaborales().getEsquemaPago(),agremiado.getDatosLaborales().getNombreContrato());
                List<Documento> documentos = agremiadoService.getDocumentos(agremiado, getModulo("Expediente Incompleto"));
                List<Documento> documentosConURL = new ArrayList<>(documentos.size());
                    documentos.forEach((Documento documento) -> {
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         });
                List<TipoDocumento> documentosObligatorios = new ArrayList<>();
                documentosPorSubirParaSolicitarUnAlta.stream().filter((tipoDocumento) -> (tipoDocumento.getObligatorio())).forEachOrdered((tipoDocumento) -> {
                    documentosObligatorios.add(tipoDocumento);
                });
                List<TipoDocumento> documentosDelColaborador = new ArrayList<>();
                documentosConURL.stream().filter((documento) -> (documento.getTipoDocumentoObj().getObligatorio())).forEachOrdered((documento) -> {
                    documentosDelColaborador.add(documento.getTipoDocumentoObj());
                });
                if(documentosDelColaborador.containsAll(documentosObligatorios)){
                    short status = getStatus("V.OB.O");
                    agremiado.setStatusAgremiado(status);
                    agremiado.setModificado(new Date());
                    agremiadoService.setAgremiado(agremiado);
                     setInformacionEnVentana(map, 0, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> fue enviado para su V.°B.°.");   
                    map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
                    setBitacora("Cambio de estado", "Se envio a V.°B.° el expediente de "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".",request);
                    notificacionesService.altasSolicitada(agremiado, datosLaborales.getFechaInicioContrato());
                    return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
                }else{
                    LOGGER.error(LOGGER_PREFIX+"El colaborador no cumple con todos los documentos requeridos para cambiar su estado de expediente incompleto  {"+idAgremiadoString+"}  "); 
                     setInformacionEnVentana(map, 2, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> no cuenta con todos los documentos obligatorios para su alta.");
                }
            }else{
                    LOGGER.error(LOGGER_PREFIX+"El colaborador no cumple con los aspecto requeridos para se cambiar su estado de expediente incompleto  {"+idAgremiadoString+"}  "); 
                     setInformacionEnVentana(map, 2, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> no cuenta con toda la información requerida para su alta.");               
            }         
        } catch (NumberFormatException nfe) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar verificar el expediente del agremiado --NumberFormatException-- {"+idAgremiadoString+"}  --> "+nfe.getMessage());
                     setInformacionEnVentana(map, 2, MODULO, "Ocurrio un problema durante la verificación del colaborador");    
        }catch (Exception e) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar verificar el expediente del agremiado {"+idAgremiadoString+"}  --> "+e.getMessage());
        }
        map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
        return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
    }
    
    /**
     * Controlador encargado de devolver la vista del kardex de un colaborador
     * @param idAgremiado Numero entero que almacena el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/kardex/{idAgremiado}.htm",method = RequestMethod.GET)
    public ModelAndView getKardexDelColaborador(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita la vista del kardex del colaborador {"+idAgremiado+'}');
        
            Map<String,Object> map = new HashMap<String, Object>();
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            if(agremiado.getDatosLaborales().getNombreContrato()==null||agremiado.getDatosLaborales().getNombreContrato().isEmpty()){
                map = new HashMap<>();
                map.put("agremiado", agremiado);      
                Integer idContratista = agremiado.getDatosLaborales().getContratistaObj().getIdContratista();
                List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(agremiado.getDatosLaborales().getClienteObj().getIdCliente());   
                 List<DelContrato> contratosDelClienteTemp = new ArrayList<>(contratosDelCliente.size());
                 contratosDelCliente.stream().filter((delContrato) -> (delContrato.getIdContratista() == idContratista)).forEachOrdered((delContrato) -> {
                     contratosDelClienteTemp.add(delContrato);
                });
                map.put("contratos", contratosDelClienteTemp);
                map.put("esquemas", agremiadoService.getEsquemaDePagos()); 
                map.put("viewResponse", "kardex");  
                return new ModelAndView("colaborador/delClienteYDelContratoOlders", "model", map);
            }
            getDatosAgremiado(map, idAgremiado);
            DatosLaborales getDatosLaborales = (DatosLaborales)map.get("datosLaborales");
            if(getDatosLaborales!=null){
                //Si ya no es infromación heredada  y ya cuenta con su nombre de contrato
                if(getDatosLaborales.getNombreContrato()!=null&&!getDatosLaborales.getNombreContrato().isEmpty()){
                        setCamposDispoiblesAndContratoEsquema(map, getDatosLaborales.getEsquemaPago(), getDatosLaborales.getNombreContrato());
                }
            }
            return new ModelAndView("colaborador/kardex","model",map);
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar mostrar el kardex del  colaborador {"+idAgremiado+"}  --> "+e.getMessage());
        }
        throw new RuntimeException("Ocurrio un problema al momento de intentar generar el kardex de un colaborador "+idAgremiado);
    }
    
    /**
     * Controlador encargado de devolver la vista con los detalles para dar el visto bueno de un colaborador
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/vobo-del-colaborador/{idAgremiado}.htm",method =RequestMethod.GET)
    public ModelAndView setVoBo(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita el visto bueno del colaborador {"+idAgremiado+'}');
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            DatosLaborales datosLaborales = agremiado.getDatosLaborales();
            
            if(datosLaborales.getEsquemaPago().getDescripcion().equalsIgnoreCase("Nominal")||datosLaborales.getEsquemaPago().getDescripcion().equalsIgnoreCase("Asimilados")){
                short status = getStatus("Expediente sin contrato");
                agremiado.setStatusAgremiado(status);
                agremiado.setModificado(new Date());
                agremiadoService.setAgremiado(agremiado);
                setInformacionEnVentana(map, 0, MODULO, "Se le ha dado el V.°B.° a <b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b>.");   
                setBitacora("Cambio de estado", "Se le ha dado el V.°B.° a "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".",request);
                notificacionesService.expedientePorCompletar(agremiado, datosLaborales.getFechaInicioContrato());
            }else{
                List<Sindicato> sindicatos = sindicatoService.getSindicatosByNomCorto();
                map.put("sindicatos", sindicatos);
                map.put("agremiado", agremiado);
                map.put("datosPersonales", datosPersonales);
                return new ModelAndView("colaborador/registrarSindicato", "model", map);                
            }
        } catch (Exception e) {
            LOGGER.info(LOGGER_PREFIX+"Ocurrio un problema al intentar dar el visto bueno al colaborador {"+idAgremiado+"} --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar dar el <b>V.°B.°</b> al colaborador.");
        }
        
        map.put("Colaboradores", getAgremiadosByStatus("V.OB.O"));
        return new ModelAndView("colaborador/vistoBueno", "model", map);
    }
    
    /**
     * Controlador encargado de devolver la vista con los detalles para seleccionar el periodo de fondo de ahorro de un colaborador
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/seleccionar-periodo-fa/{idAgremiado}.htm",method =RequestMethod.GET)
    public ModelAndView setSelectPeriodo(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita la seleccion de periodo del fondo de ahorro del colaborador con id {"+idAgremiado+'}');
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            DatosLaborales datosLaborales = agremiado.getDatosLaborales();
            
//            if(datosLaborales.getEsquemaPago().getDescripcion().equalsIgnoreCase("Nominal")||datosLaborales.getEsquemaPago().getDescripcion().equalsIgnoreCase("Asimilados")){
//                short status = getStatus("Expediente sin contrato");
//                agremiado.setStatusAgremiado(status);
//                agremiado.setModificado(new Date());
//                agremiadoService.setAgremiado(agremiado);
//                setInformacionEnVentana(map, 0, MODULO, "Se le ha dado el V.°B.° a <b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b>.");   
//                setBitacora("Cambio de estado", "Se le ha dado el V.°B.° a "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".",request);
//                notificacionesService.expedientePorCompletar(agremiado, datosLaborales.getFechaInicioContrato());
//            }else{
                //List<PeriodoFA> periodos = pFAService.getPeriodosFondoAhorro(); //aquí entran los periodos del contrato entre empresas
                ContratoEmpresas contratoEmpresa;
                contratoEmpresa = agremiadoService.getContratoEmpresasByIdName(datosLaborales.getNombreContrato());
                
              //  List<PeriodoFA> periodos =  pFAService.getPeriodosFondoAhorro(); // contratoEmpresa.getPeriodoFA();
              
                Date fechaPrimerDiaFA = pFAService.getPrimerFechaFA(idAgremiado);
                Date fechaUltimoDiaFA = pFAService.getUltimaFechaFA(idAgremiado);
                
                List<PeriodoFA> periodos =  pFAService.getPeriodosByDates(fechaPrimerDiaFA, fechaUltimoDiaFA, idAgremiado);
                map.put("periodos", periodos);
                map.put("agremiado", agremiado);
                map.put("datosPersonales", datosPersonales);
                return new ModelAndView("colaborador/seleccionarPeriodo", "model", map);                
         //   }
        } catch (Exception e) {
            LOGGER.info(LOGGER_PREFIX+"Ocurrio un problema al intentar dar el visto bueno al colaborador {"+idAgremiado+"} --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar dar el <b>V.°B.°</b> al colaborador.");
        }
        
        map.put("Colaboradores", getAgremiadosByStatus("Baja Solicitada"));
        return new ModelAndView("colaborador/seleccionarPeriodo", "model", map);
    }
    
    /**
     * Controlador encargado de devolver la vista con los detalles de los colaboradores con pendientes de firmas
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/baja-sin-firmar/{idAgremiado}.htm",method =RequestMethod.GET)
    public ModelAndView setBajaSinFirma(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita cambiar de status al colaborador {"+idAgremiado+'}');
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            DatosLaborales datosLaborales = agremiado.getDatosLaborales();
            
                short status = getStatus("Baja Sin Firmar");
                agremiado.setStatusAgremiado(status);
                agremiado.setModificado(new Date());
                agremiadoService.setAgremiado(agremiado);
                setInformacionEnVentana(map, 0, MODULO, "El colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b> ha cambiado exitosamente de estado");
                setBitacora("Cambio de estado", "Cambio el estado del colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+" a bajas sin firmar.", request);
                notificacionesService.bajaSinFirmar(agremiado, datosLaborales.getFechaInicioContrato());
            
        } catch (Exception e) {
            LOGGER.info(LOGGER_PREFIX+"Ocurrio un problema al intentar dar el visto bueno al colaborador {"+idAgremiado+"} --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar pasar a <b>Bajas sin firmar</b> al colaborador.");
        }
        
        map.put("Colaboradores", getAgremiadosByStatus("Baja Pendiente"));
        return new ModelAndView("colaborador/bajasPendientes", "model", map);
    }
    
    @RequestMapping(value = "colaborador/reporte-fondo-ahorro/{idAgremiado}.htm",method =RequestMethod.POST)
    public ModelAndView getDatosDelColaborador(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita cambiar de status al colaborador {"+idAgremiado+'}');
        
        Map<String,Object> map = new HashMap<>();
        String idPeriodo = request.getParameter("idPeriodo");
        
        try {
            int idPeriodoInt = Integer.parseInt(idPeriodo);
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            DatosLaborales datosLaborales = agremiado.getDatosLaborales();
            
            String nombreEsquemaPago = datosLaborales.getEsquemaPago().getDescripcion();
            String tipoNomina = datosLaborales.getTipoNominaObj().getTipoNomina();
           
            
            switch(nombreEsquemaPago){
                case "Mixto": 
                    switch(tipoNomina){
                        case "Semanal":
                            ByteArrayOutputStream reporteFAMixtoSemanal = excelReportesService.getReporteFAMixtoSemanal(idAgremiado);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=Fondo_de_ahorro__" + datosPersonales.getNombre().replace(' ', '_')+ "_" + datosPersonales.getApellidoPaterno().replace(' ', '_')+"_.xlsx");

                            response.setContentLength(reporteFAMixtoSemanal.size());
                                try (OutputStream os = response.getOutputStream()) { 
                                    reporteFAMixtoSemanal.writeTo(os);
                                    os.flush();
                                }
                        break;
                        case "Catorcenal":
                            ByteArrayOutputStream reporteFAMixtoCatorcenal = excelReportesService.getReporteFAMixtoCatorcenal(idAgremiado);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=Fondo_de_ahorro__" + datosPersonales.getNombre().replace(' ', '_')+ "_" + datosPersonales.getApellidoPaterno().replace(' ', '_')+"_.xlsx");

                            response.setContentLength(reporteFAMixtoCatorcenal.size());
                                try (OutputStream os = response.getOutputStream()) { 
                                    reporteFAMixtoCatorcenal.writeTo(os);
                                    os.flush();
                                }
                        break;
                        case "Quincenal":
                            ByteArrayOutputStream reporteFAMixtoQuincenal = excelReportesService.getReporteFAMixtoQuincenal(idAgremiado, idPeriodoInt);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=Fondo_de_ahorro__" + datosPersonales.getNombre().replace(' ', '_')+ "_" + datosPersonales.getApellidoPaterno().replace(' ', '_')+"_.xlsx");

                            response.setContentLength(reporteFAMixtoQuincenal.size());
                                try (OutputStream os = response.getOutputStream()) { 
                                    reporteFAMixtoQuincenal.writeTo(os);
                                    os.flush();
                                }
                            break;
                        case "Mensual":
                            ByteArrayOutputStream reporteFAMixtoMensual = excelReportesService.getReporteFAMixtoMensual(idAgremiado);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=Fondo_de_ahorro__" + datosPersonales.getNombre().replace(' ', '_')+ "_" + datosPersonales.getApellidoPaterno().replace(' ', '_')+"_.xlsx");

                            response.setContentLength(reporteFAMixtoMensual.size());
                                try (OutputStream os = response.getOutputStream()) { 
                                    reporteFAMixtoMensual.writeTo(os);
                                    os.flush();
                                }
                        break;
                        default:
                        throw new Exception("No se reconoce el tipo de nomina "+tipoNomina+" para poder generar el reporte.");
                    }
                break;
                case "Nominal":
                    switch(tipoNomina){
                        case "Semanal":
                            ByteArrayOutputStream reporteFANominalSemanal = excelReportesService.getReporteFANominalSemanal(idAgremiado);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=Fondo_de_ahorro__" + datosPersonales.getNombre().replace(' ', '_')+ "_" + datosPersonales.getApellidoPaterno().replace(' ', '_')+"_.xlsx");

                            response.setContentLength(reporteFANominalSemanal.size());
                                try (OutputStream os = response.getOutputStream()) { 
                                    reporteFANominalSemanal.writeTo(os);
                                    os.flush();
                                }
                        break;
                        case "Catorcenal":
                            ByteArrayOutputStream reporteFANominalCatorcenal = excelReportesService.getReporteFANominalCatorcenal(idAgremiado);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=Fondo_de_ahorro__" + datosPersonales.getNombre().replace(' ', '_')+ "_" + datosPersonales.getApellidoPaterno().replace(' ', '_')+"_.xlsx");

                            response.setContentLength(reporteFANominalCatorcenal.size());
                                try (OutputStream os = response.getOutputStream()) { 
                                    reporteFANominalCatorcenal.writeTo(os);
                                    os.flush();
                                }
                        break;
                        case "Quincenal":
                            ByteArrayOutputStream reporteFANominalQuincenal = excelReportesService.getReporteFANominalQuincenal(idAgremiado);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=Fondo_de_ahorro__" + datosPersonales.getNombre().replace(' ', '_')+ "_" + datosPersonales.getApellidoPaterno().replace(' ', '_')+"_.xlsx");

                            response.setContentLength(reporteFANominalQuincenal.size());
                                try (OutputStream os = response.getOutputStream()) { 
                                    reporteFANominalQuincenal.writeTo(os);
                                    os.flush();
                                }
                            break;
                        case "Mensual":
                            ByteArrayOutputStream reporteFANominalMensual = excelReportesService.getReporteFANominalMensual(idAgremiado);
                            response.setContentType("application/x-download");
                            response.setHeader("Content-disposition", "attachment; filename=Fondo_de_ahorro__" + datosPersonales.getNombre().replace(' ', '_')+ "_" + datosPersonales.getApellidoPaterno().replace(' ', '_')+"_.xlsx");

                            response.setContentLength(reporteFANominalMensual.size());
                                try (OutputStream os = response.getOutputStream()) { 
                                    reporteFANominalMensual.writeTo(os);
                                    os.flush();
                                }
                            break;
                        default:
                       throw new Exception("No se reconoce el tipo de nomina "+tipoNomina+" para poder generar el reporte.");
                    }
                break;
                default:
                    throw new Exception("No se reconoce el esquema de pago "+nombreEsquemaPago+" para poder generar el reporte.");
            }
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error con el reporte todos los datos de los colaboradores",e);
        }
        
        map.put("Colaboradores", getAgremiadosByStatus("Baja Solicitada"));
        return new ModelAndView("colaborador/bajasSolicitadas", "model", map);
    }
    
    /**
     * Controller encargado de asignar el sindicato a un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que manejas las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="colaborador/sidicato-del-colaborador.htm",method = RequestMethod.POST)
    public ModelAndView setSindicato(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se recibe un colaborador para ingresar su sindicato y dar su visto bueno.");
        
        String idAgremiadoString = request.getParameter("idAgremiado");
        String idSindicatoString = request.getParameter("idSindicato");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            
            int idAgremiado = Integer.parseInt(idAgremiadoString);
            int idSindicato = Integer.parseInt(idSindicatoString);
            
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            Sindicato sindicato = sindicatoService.getSindicato(idSindicato);
            
            DatosLaborales datosLaborales = agremiado.getDatosLaborales();
            datosLaborales.setSindicatoObj(sindicato);
            
            agremiadoService.setDatosLaborales(datosLaborales);
            
            short status = getStatus("Expediente sin contrato");
            agremiado.setStatusAgremiado(status);
            agremiado.setModificado(new Date());
            agremiadoService.setAgremiado(agremiado);
            
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            
            setInformacionEnVentana(map, 0, MODULO, "Se le ha asignado a <b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> <br> El sindicato <b>"+sindicato.getNombreCorto()+"</b>.");
            setBitacora("Cambio de estado", "Se le ha dado el V.°B.° a "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".",request);
            notificacionesService.expedientePorCompletar(agremiado, datosLaborales.getFechaInicioContrato());
            
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar dar el visto bueno al colaborador {"+idAgremiadoString+"} --> "+e.getMessage(),e);
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar dar el <b>V.°B.°</b> al colaborador.");
        }
        map.put("Colaboradores", getAgremiadosByStatus("V.OB.O"));
        return new ModelAndView("colaborador/vistoBueno", "model", map);        
    }
    
    /**
     * Controller encargado de rechazar el alta solicitada de un colaborador
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/rechazar-alta-solicitada/{idAgremiado}.htm",method = RequestMethod.POST)
    public ModelAndView rechazarAltaSolicitada(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita el rechazo de la solicitud de alta del colaborador {"+idAgremiado+'}');

        Map<String,Object> map = new HashMap<>();
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(agremiado.getStatusAgremiado());
            if(statusAgremiado.getStatus().equalsIgnoreCase("V.OB.O")){
                short status = getStatus("Expediente Incompleto");
                agremiado.setStatusAgremiado(status);
                agremiado.setModificado(new Date());
                agremiado.setObservaciones(getObservaciones(agremiado.getObservaciones(),"El usuarios con correo %U rechazó el alta solicitada."));
                agremiadoService.setAgremiado(agremiado);
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                setBitacora("Cambio de status", "Rechazó el alta solicitada de "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" "+datosPersonales.getNombre().toUpperCase() +".", request);          
                setInformacionEnVentana(map, 0, MODULO, "El alta del colabordor se rechazó con exito.");
                notificacionesService.rechazoDuranteAlta(agremiado, "Alta Solicitada", "V.°B.° DE EXPEDIENTE");
            }else{
                LOGGER.error(LOGGER_PREFIX+"No se puede rechazar el alta debido a que no tiene el status de colaborador correcto {"+idAgremiado+"}.");
                setInformacionEnVentana(map, 1, MODULO, "No se puede rechazar el alta del colaborador.");
            }
            
        } catch (Exception e) {
                LOGGER.error(LOGGER_PREFIX+"Ocurrió un problema al momento de rechazar una alta solicitada {"+idAgremiado+"} --> "+e.getMessage());
                setInformacionEnVentana(map, 1, MODULO, "Hubo un problema al momento de intentar rechazar la solicitud de alta.");
        }
        map.put("Colaboradores", getAgremiadosByStatus("V.OB.O"));
        return new ModelAndView("colaborador/vistoBueno", "model", map);
    }
    
//    /**
//     * Controller encargado de reactivar el colaborador
//     * @param idAgremiado Numero entero que contiene el id de un colaborador
//     * @param request Variable que maneja las peticiones
//     * @param response Variable que maneja las respuestas
//     * @return ModelAndView
//     */
//    @RequestMapping(value = "colaborador/reactivar-colaborador/{idAgremiado}.htm",method = RequestMethod.POST)
//    public ModelAndView reactivarColaborador(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
//        LOGGER.info(LOGGER_PREFIX+"Se solicita la reactivación del colaborador {"+idAgremiado+'}');
//
//        Map<String,Object> map = new HashMap<>();
//        
//        try {
//            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
//            StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(agremiado.getStatusAgremiado());
//            if(statusAgremiado.getStatus().equalsIgnoreCase("Baja") || statusAgremiado.getStatus().equalsIgnoreCase("Expediente Descartado")){
//                short status = getStatus("Expediente Incompleto");
//                agremiado.setStatusAgremiado(status);
//                agremiado.setModificado(new Date());
//                agremiado.setObservaciones(getObservaciones(agremiado.getObservaciones(),"El usuarios con correo %U rechazó el alta solicitada."));
//                agremiadoService.setAgremiado(agremiado);
//                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
//                setBitacora("Cambio de status", "Rechazó el alta solicitada de "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" "+datosPersonales.getNombre().toUpperCase() +".", request);          
//                setInformacionEnVentana(map, 0, MODULO, "El alta del colabordor se rechazó con exito.");
//                notificacionesService.rechazoDuranteAlta(agremiado, "Alta Solicitada", "V.°B.° DE EXPEDIENTE");
//            }else{
//                LOGGER.error(LOGGER_PREFIX+"No se puede rechazar el alta debido a que no tiene el status de colaborador correcto {"+idAgremiado+"}.");
//                setInformacionEnVentana(map, 1, MODULO, "No se puede rechazar el alta del colaborador.");
//            }
//            
//        } catch (Exception e) {
//                LOGGER.error(LOGGER_PREFIX+"Ocurrió un problema al momento de rechazar una alta solicitada {"+idAgremiado+"} --> "+e.getMessage());
//                setInformacionEnVentana(map, 1, MODULO, "Hubo un problema al momento de intentar rechazar la solicitud de alta.");
//        }
//        map.put("Colaboradores", getAgremiadosByStatus("V.OB.O"));
//        return new ModelAndView("colaborador/vistoBueno", "model", map);
//    }
    
    /**
     * Controller encargado de presentar la vista con los documentos que se cargan junto con el contrato
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las solicitudes
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/documentos-del-contrato/{IdAgremiado}.htm", method = RequestMethod.GET)
    public ModelAndView getDocumentosConElContrato(@PathVariable("IdAgremiado") Integer idAgremiado , HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicitan los documentos del colaborador {"+idAgremiado+"} para el ingreso de su contrato");
                
        Map<String,Object> map =  new HashMap<>();
    
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            
            if(agremiado.getDatosLaborales().getNombreContrato()==null||agremiado.getDatosLaborales().getNombreContrato().isEmpty()){
                map = new HashMap<>();
                map.put("agremiado", agremiado);      
                Integer idContratista = agremiado.getDatosLaborales().getContratistaObj().getIdContratista();
                List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(agremiado.getDatosLaborales().getClienteObj().getIdCliente());   
                 List<DelContrato> contratosDelClienteTemp = new ArrayList<>(contratosDelCliente.size());
                 contratosDelCliente.stream().filter((delContrato) -> (delContrato.getIdContratista() == idContratista)).forEachOrdered((delContrato) -> {
                     contratosDelClienteTemp.add(delContrato);
                });
                map.put("contratos", contratosDelClienteTemp);
                map.put("esquemas", agremiadoService.getEsquemaDePagos()); 
                map.put("viewResponse", "contrato");  
                return new ModelAndView("colaborador/delClienteYDelContratoOlders", "model", map);
            }
       
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            ContratoEmpresas contratoEmpresasByIdName = agremiadoService.getContratoEmpresasByIdName(agremiado.getDatosLaborales().getNombreContrato());
            List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumentos(getModulo("De contrato"),agremiado.getDatosLaborales().getEsquemaPago(),contratoEmpresasByIdName);
            map.put("agremiado", agremiado);
            map.put("datosPersonales", datosPersonales);
            map.put("tipoDocumento", tipoDocumento) ; 
            List<Documento> documentos  = agremiadoService.getDocumentos(agremiado, getModulo("De contrato"));
            List<Documento> documentosConURL =  new ArrayList<>(documentos.size());
            documentos.stream().filter((documento) -> (documento.getUrlDocumento()!=null)).forEachOrdered((documento) -> {
                documentosConURL.add(documento);
            });
            map.put("documentosDelColaborador", documentosConURL);
            
            return new ModelAndView("colaborador/documentosYContrato", "model", map);
            
        } catch (Exception e) {
                LOGGER.error(LOGGER_PREFIX+"Ocurrió un problema al momento de crear la vista con los documentos que se requieren junto con el contrato {"+idAgremiado+"} --> "+e.getMessage(),e);
                setInformacionEnVentana(map, 1, MODULO, "Ocurrió un problema al momento de crear la vista con los documentos que se requieren junto con el contrato.");            
        }
        map.put("Colaboradores", getAgremiadosByStatus("Expediente Sin Contrato"));
        return new ModelAndView("colaborador/expedientesSinContrato", "model", map);
    }
    
    /**
     * Controller encargado de presentar la vista con los documentos que se cargan junto con el fondo de ahorro
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las solicitudes
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/documentos-del-fondo-ahorro/{IdAgremiado}.htm", method = RequestMethod.GET)
    public ModelAndView getDocumentosDelFondoDeAhorro(@PathVariable("IdAgremiado") Integer idAgremiado , HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicitan los documentos del colaborador {"+idAgremiado+"} para el ingreso de su fondo de ahorro");
                
        Map<String,Object> map =  new HashMap<>();
    
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            
//            if(agremiado.getDatosLaborales().getNombreContrato()==null||agremiado.getDatosLaborales().getNombreContrato().isEmpty()){
//                map = new HashMap<>();
//                map.put("agremiado", agremiado);      
//                Integer idContratista = agremiado.getDatosLaborales().getContratistaObj().getIdContratista();
//                List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(agremiado.getDatosLaborales().getClienteObj().getIdCliente());   
//                 List<DelContrato> contratosDelClienteTemp = new ArrayList<>(contratosDelCliente.size());
//                 contratosDelCliente.stream().filter((delContrato) -> (delContrato.getIdContratista() == idContratista)).forEachOrdered((delContrato) -> {
//                     contratosDelClienteTemp.add(delContrato);
//                });
//                map.put("contratos", contratosDelClienteTemp);
//                map.put("esquemas", agremiadoService.getEsquemaDePagos()); 
//                map.put("viewResponse", "contrato");  
//                return new ModelAndView("colaborador/delClienteYDelContratoOlders", "model", map);
//            }
            //if(agremiado.getDatosLaborales().getNombreContrato()==null){

            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
           // ContratoEmpresas contratoEmpresasByIdName = agremiadoService.getContratoEmpresasByIdName(agremiado.getDatosLaborales().getNombreContrato());
           // List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumentos(getModulo("De contrato"),agremiado.getDatosLaborales().getEsquemaPago(),contratoEmpresasByIdName);
            List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumentoFA("Fondo de ahorro");
            map.put("agremiado", agremiado);
            map.put("datosPersonales", datosPersonales);
            map.put("tipoDocumento", tipoDocumento) ; 
            TipoDocumento tipoDocumentoPer = new TipoDocumento();
            tipoDocumentoPer = agremiadoService.getTipoDocumento(24);
            List<Documento> documentos  = agremiadoService.getDocumentoFA(agremiado, tipoDocumentoPer);
            List<Documento> documentosConURL =  new ArrayList<>(documentos.size());
            documentos.stream().filter((documento) -> (documento.getUrlDocumento()!=null)).forEachOrdered((documento) -> {
                documentosConURL.add(documento);
            });
            LOGGER.error("Documento" + documentos)  ;
            map.put("documentosDelColaborador", documentos);
            
            return new ModelAndView("colaborador/documentosYFondoDeAhorro", "model", map);
            
        } catch (Exception e) {
                LOGGER.error(LOGGER_PREFIX+"Ocurrió un problema al momento de crear la vista con los documentos que se requieren junto con el fondo de ahorro {"+idAgremiado+"} --> "+e.getMessage(),e);
                setInformacionEnVentana(map, 1, MODULO, "Ocurrió un problema al momento de crear la vista con los documentos que se requieren junto con el fondo de ahorro.");            
        }
        map.put("Colaboradores", getAgremiadosByStatus("Activo"));
        return new ModelAndView("colaborador/colaboradores", "model", map);
    }
    
    /**
     * Controlles encargado de generar dinamicamente la adhesion sindical de un colaborador
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     */
    @Deprecated
    @RequestMapping(value = "colaborador/crear-adhesion/{idAgremiado}.htm",method = RequestMethod.GET)
    public void getAdhesionSindical(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        
        LOGGER.info(LOGGER_PREFIX+"Se solicitan la creación de la adhesión sindical para un colaborador {"+idAgremiado+"} ");
        
        try{            
            
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            if(agremiado != null){
                ByteArrayOutputStream adhesionSindical = pDFiTextService.getAdhesionSindical(agremiado);
                // setting some response headers
                response.setHeader("Content-Disposition", "inline;filename=\"Adhesion sindical"+"__"+datosPersonales.getApellidoPaterno().toUpperCase()+"_"+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"_"+datosPersonales.getNombre().toUpperCase() +".pdf\"");
                // setting the content type
                response.setContentType("application/pdf");
                // the contentlength
                response.setContentLength(adhesionSindical.size());
                try ( // write ByteArrayOutputStream to the ServletOutputStream
                    OutputStream os = response.getOutputStream()) { 
                    adhesionSindical.writeTo(os);
                    os.flush();
                }
            }else{
                throw new Exception("colaborador no encontrado para la generación de la adhesión en pdf");
            }
        }catch(Exception e){
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al momento de generar la adhesión sindical  --> "+e.getMessage(),e);
        } 
        
    }
    
    /**
     * Controller encargado de mostrar la vista para la generación de un contrato de colaborador
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/datos-del-contrato/{idAgremiado}.htm",method = RequestMethod.GET)
    public ModelAndView getFormContrato(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){    
        LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para crear un nuevo contrato para un colaborados {"+idAgremiado+'}');
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            Contratista contratistaObj = agremiado.getDatosLaborales().getContratistaObj();
            // Obtenemos el nombre del contrato del colaborador de la tabla datos_laborales
            String nombreContrato = agremiado.getDatosLaborales().getNombreContrato();
            // Se obtiene el objeto ContratoEmpresas mediante el nombre del contrato
            ContratoEmpresas contratoEmpresas = agremiadoService.getContratoEmpresasByIdName(nombreContrato);
            // Se obtiene la lista de los contratos que tiene asignado ese contrato entre empresas
            List<Contrato> contratosDelColaborador = contratoEmpresas.getContratosList();
            // Se delcara un nuevo array con los valores de la lista anterior
            List<Contrato> contratos = new ArrayList<>(contratosDelColaborador.size());
            // Se recorre la lista de los contratos encontrados y se agrega a la nueva lista declarada
            contratosDelColaborador.stream().filter((contrato) -> (contrato.getContratoDeColaborador())).forEachOrdered((contrato) -> {
                contratos.add(contrato);
            });
            
            map.put("agremiado", agremiado);
            map.put("datosPersonales", datosPersonales);
            map.put("contratos", contratos);
            map.put("contratista", contratistaObj);
            map.put("hoy", procesayyyyMMdd(new Date()));
                       
            return new ModelAndView("colaborador/crearContrato", "model", map);
                        
        } catch (Exception e) {
            LOGGER.info(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar mostrar la vista para la generación de un contrato --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "No fue posible obtener la vista para la generación de un contrato");
        }        
        map.put("Colaboradores", getAgremiadosByStatus("Expediente Sin Contrato"));
        return new ModelAndView("colaborador/expedientesSinContrato", "model", map);
    }
    
    /**
     * Controller encargado verificar que todos los documentos obligatorios hayan sido cargados
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="colaborador/verificar-expediente-contrato.htm",method = RequestMethod.POST)
    public ModelAndView verificarDocumentosYContrato(HttpServletRequest request, HttpServletResponse response){
        String idAgremiadoString = request.getParameter("idAgremiado");
        LOGGER.info(LOGGER_PREFIX+"Se solicita la validación del expediente con contrato para el colaborador "+idAgremiadoString);     
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            int idAgremiado = Integer.parseInt(idAgremiadoString);
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                ContratoEmpresas contratoEmpresasByIdName = agremiadoService.getContratoEmpresasByIdName(agremiado.getDatosLaborales().getNombreContrato());
                List<TipoDocumento> documentosPorSubirJuntoConElcontrato = agremiadoService.getTipoDocumentos(getModulo("De contrato"),agremiado.getDatosLaborales().getEsquemaPago(), contratoEmpresasByIdName);
                List<Documento> documentos = agremiadoService.getDocumentos(agremiado, getModulo("De contrato"));
                List<Documento> documentosConURL = new ArrayList<>(documentos.size());
                    documentos.forEach((Documento documento) -> {
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         });
                List<TipoDocumento> documentosObligatorios = new ArrayList<>();
                documentosPorSubirJuntoConElcontrato.stream().filter((tipoDocumento) -> (tipoDocumento.getObligatorio())).forEachOrdered((tipoDocumento) -> {
                    documentosObligatorios.add(tipoDocumento);
                });
                boolean hasFondoDeAhorro = false;
                TipoDocumento tdFA = null;
                for (TipoDocumento documentosContratado : documentosPorSubirJuntoConElcontrato) {          
                    if(documentosContratado.getCatalogoDocumentalObj().getUrl()!=null&&documentosContratado.getCatalogoDocumentalObj().getUrl().equalsIgnoreCase("SolicitudDeFondoDeAhorroEstandar")){
                        hasFondoDeAhorro = true;
                        tdFA = documentosContratado;
                        break;
                    }
                }
                if(hasFondoDeAhorro&&(tdFA.getObligatorio()||agremiado.getDatosLaborales().getFondoDeAhorroDisponible())){
                    documentosObligatorios.add(tdFA);
                }else{
                    documentosObligatorios.remove(tdFA);
                }
                boolean hasConstanciaCreditoInfonavit = false;
                TipoDocumento tdCCI = null;
                for (TipoDocumento documentosContratado : documentosPorSubirJuntoConElcontrato) {          
                    if(documentosContratado.getCatalogoDocumentalObj().getUrl()!=null&&documentosContratado.getCatalogoDocumentalObj().getUrl().equalsIgnoreCase("ConstanciaCreditoInfonavit")){
                        hasConstanciaCreditoInfonavit = true;
                        tdCCI = documentosContratado;
                        break;
                    }
                }
                if(hasConstanciaCreditoInfonavit&&(tdCCI.getObligatorio()||agremiado.getDatosLaborales().getFondoDeAhorroDisponible())){
                    documentosObligatorios.add(tdCCI);
                }else{
                    documentosObligatorios.remove(tdCCI);
                }
                boolean hasTarjetasTDU = false;
                TipoDocumento tdTDU = null;
                for (TipoDocumento tipoDocumento : documentosPorSubirJuntoConElcontrato) {         
                    if(tipoDocumento.getCatalogoDocumentalObj().getUrl()!=null&&tipoDocumento.getCatalogoDocumentalObj().getUrl().equalsIgnoreCase("SolicitudTarjetas")){
                        hasTarjetasTDU = true;
                        tdTDU = tipoDocumento;
                        break;
                    }                
                }
                // Se quita tdTDU.getObligatorio() Para que no valide que el documento sea obligatorio o no
                if(hasTarjetasTDU&&(agremiado.getDatosLaborales().getTarjetaTdu()==null||agremiado.getDatosLaborales().getTarjetaTdu().equalsIgnoreCase("Sí"))){
                    documentosObligatorios.add(tdTDU);
                }else{
                    documentosObligatorios.remove(tdTDU);
                }
                boolean hasSolicitudAsimilables = false;
                TipoDocumento tdSolAsim = null;
                for (TipoDocumento tipoDocumento : documentosPorSubirJuntoConElcontrato) {         
                    if(tipoDocumento.getCatalogoDocumentalObj().getUrl()!=null&&tipoDocumento.getCatalogoDocumentalObj().getUrl().equalsIgnoreCase("SolicitudAsimilables")){
                        hasSolicitudAsimilables = true;
                        tdSolAsim = tipoDocumento;
                        break;
                    }                
                }
                if(hasSolicitudAsimilables&&(tdSolAsim.getObligatorio()||(agremiado.getDatosLaborales().getEsquemaPago().equals(4)))){
                    documentosObligatorios.add(tdSolAsim);
                }else{
                    documentosObligatorios.remove(tdSolAsim);
                }
                
                List<TipoDocumento> documentosDelColaborador = new ArrayList<>();
                documentosConURL.stream().filter((documento) -> (documento.getTipoDocumentoObj().getObligatorio())).forEachOrdered((documento) -> {
                    documentosDelColaborador.add(documento.getTipoDocumentoObj());
                });
                for (Documento documento : documentosConURL) {
                    if(documento.getTipoDocumentoObj().getCatalogoDocumentalObj()!=null&&documento.getTipoDocumentoObj().getCatalogoDocumentalObj().getUrl()!=null&&documento.getTipoDocumentoObj().getCatalogoDocumentalObj().getUrl().equalsIgnoreCase("SolicitudDeFondoDeAhorroEstandar")){
                        documentosDelColaborador.add(documento.getTipoDocumentoObj());
                        break;
                    }
                }
                // Se declara variable boolan y se le asigna el valor false
                boolean isIncompleteFA = false;
                /* Condiciona que la lista documentosObligatorios contenga el tipo de documento de Fondo de ahorro 
                * si cumple con la condición se asigna false a la variable isIncompleteFA
                */
                if(documentosObligatorios.contains(tdFA) == false && documentosDelColaborador.contains(tdFA)){
                    isIncompleteFA = true;
                }
                /* Condicion si la lista documentosDelColaborador  contiene la lista documentosObligatorios y la variable 
                * isIncompleteFA es igual a falsa
                */
                if(documentosDelColaborador.containsAll(documentosObligatorios) && isIncompleteFA == false){
                    short status = getStatus("Alta En Proceso");
                        agremiado.setStatusAgremiado(status);
                        agremiado.setModificado(new Date());
                        agremiadoService.setAgremiado(agremiado);
                        setInformacionEnVentana(map, 0, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> fue enviado a Alta En Proceso.");   
                        setBitacora("Cambio de estado", "Se envio a Alta En Proceso el expediente de "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".",request);
                        notificacionesService.altaEnProceso(agremiado, agremiado.getDatosLaborales().getFechaInicioContrato());  
                }else{
                // Condición si la variable isIncompleteFA tiene el valor de true entonces lanza mensaje de error y asigna variables en el map
                    if(isIncompleteFA == true){
                        LOGGER.error(LOGGER_PREFIX+"El colaborador no cumple con todos los documentos requeridos para cambiar su estado de expediente sin contrato, es necesario descargar la plantilla de Fondo de Ahorro.  {"+idAgremiadoString+"}  "); 
                        map = new HashMap<>();
                        setInformacionEnVentana(map, 2, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> no cumple con todos los documentos requeridos para cambiar su estado de expediente sin contrato, es necesario descargar la plantilla de Fondo de Ahorro."); 
                        List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumentos(getModulo("De contrato"),agremiado.getDatosLaborales().getEsquemaPago(), contratoEmpresasByIdName);
                        map.put("agremiado", agremiado);
                        map.put("datosPersonales", datosPersonales);
                        map.put("tipoDocumento", tipoDocumento) ;
                    }else{ // Si la variable isIncompleteFA es diferente de true, entonces lanza mensaje de error y asigna variables en el map
                        LOGGER.error(LOGGER_PREFIX+"El colaborador no cumple con todos los documentos requeridos para cambiar su estado de expediente sin contrato  {"+idAgremiadoString+"}  "); 
                        map = new HashMap<>(); 
                        setInformacionEnVentana(map, 2, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> no cumple con todos los documentos requeridos para cambiar su estado de expediente sin contrato.");
                        List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumentos(getModulo("De contrato"),agremiado.getDatosLaborales().getEsquemaPago(), contratoEmpresasByIdName);
                        map.put("agremiado", agremiado);
                        map.put("datosPersonales", datosPersonales);
                        map.put("tipoDocumento", tipoDocumento) ;
                    }
                    documentos  = agremiadoService.getDocumentos(agremiado, getModulo("De contrato"));
                    List<Documento> documentosConURL2 =  new ArrayList<>(documentos.size());
                    documentos.stream().filter((documento) -> (documento.getUrlDocumento()!=null)).forEachOrdered((documento) -> {
                        documentosConURL2.add(documento);
                    });
                    map.put("documentosDelColaborador", documentosConURL2);

                    return new ModelAndView("colaborador/documentosYContrato", "model", map);
                }           
        }catch(Exception e){
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar verificar los documentos del contrato --> "+e.getMessage(),e);
            setInformacionEnVentana(map, 2, MODULO, "Ocurrio un problema al momento de intentar cambiar el colaborador a Altas en proceso.");
        }
        
        map.put("Colaboradores", getAgremiadosByStatus("Expediente Sin Contrato"));
        return new ModelAndView("colaborador/expedientesSinContrato", "model", map);
    }

    @RequestMapping(value="colaborador/verificar-documentos-fondo-ahorro.htm",method = RequestMethod.POST)
    public ModelAndView verificarDocumentoFondoDeAhorro(HttpServletRequest request, HttpServletResponse response){
        String idAgremiadoString = request.getParameter("idAgremiado");
        LOGGER.info(LOGGER_PREFIX+"Se solicita la validación del expediente con contrato para el colaborador "+idAgremiadoString);     
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            int idAgremiado = Integer.parseInt(idAgremiadoString);
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                //ContratoEmpresas contratoEmpresasByIdName = agremiadoService.getContratoEmpresasByIdName(agremiado.getDatosLaborales().getNombreContrato());
                List<TipoDocumento> documentosPorSubirJuntoConElcontrato = agremiadoService.getTipoDocumentoFA("Fondo de ahorro");
                TipoDocumento tipoDocumentoPer = new TipoDocumento();
                tipoDocumentoPer = agremiadoService.getTipoDocumento(24);
                List<Documento> documentos = agremiadoService.getDocumentoFA(agremiado, tipoDocumentoPer);
                List<Documento> documentosConURL = new ArrayList<>(documentos.size());
                    documentos.forEach((Documento documento) -> {
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         });
                List<TipoDocumento> documentosObligatorios = new ArrayList<>();
                documentosPorSubirJuntoConElcontrato.stream().filter((tipoDocumento) -> (tipoDocumento.getObligatorio())).forEachOrdered((tipoDocumento) -> {
                    documentosObligatorios.add(tipoDocumento);
                });
                boolean hasFondoDeAhorro = false;
                TipoDocumento tdFA = null;
                for (TipoDocumento documentosContratado : documentosPorSubirJuntoConElcontrato) {          
                    if(documentosContratado.getCatalogoDocumentalObj().getUrl()!=null&&documentosContratado.getCatalogoDocumentalObj().getUrl().equalsIgnoreCase("SolicitudDeFondoDeAhorroEstandar")){
                        hasFondoDeAhorro = true;
                        tdFA = documentosContratado;
                        break;
                    }
                }
                if(hasFondoDeAhorro&&(tdFA.getObligatorio()||agremiado.getDatosLaborales().getFondoDeAhorroDisponible())){
                    documentosObligatorios.add(tdFA);
                }else{
                    documentosObligatorios.remove(tdFA);
                }
                
                List<TipoDocumento> documentosDelColaborador = new ArrayList<>();
                documentosConURL.stream().filter((documento) -> (documento.getTipoDocumentoObj().getObligatorio())).forEachOrdered((documento) -> {
                    documentosDelColaborador.add(documento.getTipoDocumentoObj());
                });
                for (Documento documento : documentosConURL) {
                    if(documento.getTipoDocumentoObj().getCatalogoDocumentalObj()!=null&&documento.getTipoDocumentoObj().getCatalogoDocumentalObj().getUrl()!=null&&documento.getTipoDocumentoObj().getCatalogoDocumentalObj().getUrl().equalsIgnoreCase("SolicitudDeFondoDeAhorroEstandar")){
                        documentosDelColaborador.add(documento.getTipoDocumentoObj());
                        break;
                    }
                }
                // Se declara variable boolan y se le asigna el valor false
                boolean isIncompleteFA = false;
                /* Condiciona que la lista documentosObligatorios contenga el tipo de documento de Fondo de ahorro 
                * si cumple con la condición se asigna false a la variable isIncompleteFA
                */
                if(documentosObligatorios.contains(tdFA) == false && documentosDelColaborador.contains(tdFA)){
                    isIncompleteFA = true;
                }
                /* Condicion si la lista documentosDelColaborador  contiene la lista documentosObligatorios y la variable 
                * isIncompleteFA es igual a falsa
                */
                if(documentosDelColaborador.containsAll(documentosObligatorios) && isIncompleteFA == false){
                    short status = getStatus("Activo");
                        agremiado.setStatusAgremiado(status);
                        agremiado.setModificado(new Date());
                        agremiadoService.setAgremiado(agremiado);
                        setInformacionEnVentana(map, 0, MODULO, "<b> "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b>. Modificación de fondo de ahorro.");   
                        setBitacora("Modificación de fondo de ahorro", "Se modificó el fondo de ahorro de "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".",request);
                        notificacionesService.solicitudFondoAhorro(agremiado, agremiado.getDatosLaborales().getFondoDeAhorroFechaSol());  
                }else{
                // Condición si la variable isIncompleteFA tiene el valor de true entonces lanza mensaje de error y asigna variables en el map
                    if(isIncompleteFA == true){
                        LOGGER.error(LOGGER_PREFIX+"El colaborador no cumple con todos los documentos requeridos para cambiar su fondo de ahorro, es necesario descargar la plantilla de Fondo de Ahorro y subir el documento.  {"+idAgremiadoString+"}  "); 
                        map = new HashMap<>();
                        setInformacionEnVentana(map, 2, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> no cumple con todos los documentos requeridos para modificar su fondo de ahorro, es necesario descargar la plantilla de Fondo de Ahorro."); 
                        List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumentoFA("Fondo de ahorro");
                        map.put("agremiado", agremiado);
                        map.put("datosPersonales", datosPersonales);
                        map.put("tipoDocumento", tipoDocumento) ;
                    }else{ // Si la variable isIncompleteFA es diferente de true, entonces lanza mensaje de error y asigna variables en el map
                        LOGGER.error(LOGGER_PREFIX+"El colaborador no cumple con todos los documentos requeridos para modificarsu fondo de ahorro  {"+idAgremiadoString+"}  "); 
                        map = new HashMap<>(); 
                        setInformacionEnVentana(map, 2, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> no cumple con todos los documentos requeridos para cambiar su fondo de ahorro.");
                        List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumentoFA("Fondo de ahorro");
                        map.put("agremiado", agremiado);
                        map.put("datosPersonales", datosPersonales);
                        map.put("tipoDocumento", tipoDocumento) ;
                    }
                    documentos  = agremiadoService.getDocumentoFA(agremiado, tipoDocumentoPer);
                    List<Documento> documentosConURL2 =  new ArrayList<>(documentos.size());
                    documentos.stream().filter((documento) -> (documento.getUrlDocumento()!=null)).forEachOrdered((documento) -> {
                        documentosConURL2.add(documento);
                    });
                    map.put("documentosDelColaborador", documentosConURL2);

                    return new ModelAndView("colaborador/colaboradores", "model", map);
                }           
        }catch(Exception e){
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar verificar el documento del fondo de ahorro --> "+e.getMessage(),e);
            setInformacionEnVentana(map, 2, MODULO, "Ocurrio un problema al momento de intentar modificar el fondo de ahorro del colaborador.");
        }
        
        map.put("Colaboradores", getAgremiadosByStatus("Activo"));
        return new ModelAndView("colaborador/colaboradores", "model", map);
    }
    /**
     * Controller encargado de mostrar la vista con los documentos que se cargan junto con el alta del imss
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/subir-alta-imss/{IdAgremiado}.htm",method = RequestMethod.GET)
    public ModelAndView getFormDocumentosImss(@PathVariable("IdAgremiado") Integer idAgremiado , HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicitan los documentos del colaborador {"+idAgremiado+"} para el ingreso de su alta en el imss");
                
        Map<String,Object> map =  new HashMap<>();
    
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);           
            
            if(agremiado.getDatosLaborales().getNombreContrato()==null||agremiado.getDatosLaborales().getNombreContrato().isEmpty()){
                map = new HashMap<>();
                map.put("agremiado", agremiado);      
                Integer idContratista = agremiado.getDatosLaborales().getContratistaObj().getIdContratista();
                List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(agremiado.getDatosLaborales().getClienteObj().getIdCliente());   
                 List<DelContrato> contratosDelClienteTemp = new ArrayList<>(contratosDelCliente.size());
                 contratosDelCliente.stream().filter((delContrato) -> (delContrato.getIdContratista() == idContratista)).forEachOrdered((delContrato) -> {
                     contratosDelClienteTemp.add(delContrato);
                });
                map.put("contratos", contratosDelClienteTemp);
                map.put("esquemas", agremiadoService.getEsquemaDePagos()); 
                map.put("viewResponse", "imss");  
                return new ModelAndView("colaborador/delClienteYDelContratoOlders", "model", map);
            }
            
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();            
                ContratoEmpresas contratoEmpresasByIdName = agremiadoService.getContratoEmpresasByIdName(agremiado.getDatosLaborales().getNombreContrato());
              List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumentos(getModulo("Alta En Proceso"),agremiado.getDatosLaborales().getEsquemaPago(), contratoEmpresasByIdName);
               map.put("agremiado", agremiado);
            map.put("datosPersonales", datosPersonales);
            map.put("tipoDocumento", tipoDocumento) ; 
            
            List<Documento> documentos  = agremiadoService.getDocumentos(agremiado, getModulo("Alta En Proceso"));
            List<Documento> documentosConURL =  new ArrayList<>(documentos.size());
            documentos.stream().filter((documento) -> (documento.getUrlDocumento()!=null)).forEachOrdered((documento) -> {
                documentosConURL.add(documento);
            });
            map.put("documentosDelColaborador", documentosConURL);
            
            return new ModelAndView("colaborador/documentosImss", "model", map);
            
        } catch (Exception e) {
                LOGGER.error(LOGGER_PREFIX+"Ocurrió un problema al momento de crear la vista con los documentos que se requieren junto con el contrato {"+idAgremiado+"} --> "+e.getMessage(),e);
                setInformacionEnVentana(map, 1, MODULO, "Ocurrió un problema al momento de crear la vista con los documentos que se requieren junto con el contrato.");            
        }
        map.put("Colaboradores", getAgremiadosByStatus("Alta En Proceso"));
        return new ModelAndView("colaborador/altasEnProceso", "model", map);
    }
    
    /**
     * Controller encargado de verificar que todos los documentos obligatorios se ecuentre cargados junto con el alta del IMSS
     * @param request Variable encargada de manejar las peticiones
     * @param response Variable encargada de manejar las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/verificar-expediente-imss.htm",method = RequestMethod.POST)
    public ModelAndView verificarDocumentosImss(HttpServletRequest request, HttpServletResponse response){
        String idAgremiadoString = request.getParameter("idAgremiado");
        LOGGER.info(LOGGER_PREFIX+"Se solicita la validación del expediente con alta de IMSS para el colaborador "+idAgremiadoString);     
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            int idAgremiado = Integer.parseInt(idAgremiadoString);
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            
                ContratoEmpresas contratoEmpresasByIdName = agremiadoService.getContratoEmpresasByIdName(agremiado.getDatosLaborales().getNombreContrato());
                List<TipoDocumento> documentosPorSubirJuntoConElcontrato = agremiadoService.getTipoDocumentos(getModulo("Alta En Proceso"),agremiado.getDatosLaborales().getEsquemaPago(), contratoEmpresasByIdName);
                List<Documento> documentos = agremiadoService.getDocumentos(agremiado, getModulo("Alta En Proceso"));
                List<Documento> documentosConURL = new ArrayList<>(documentos.size());
                    documentos.forEach((Documento documento) -> {
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         });
                List<TipoDocumento> documentosObligatorios = new ArrayList<>();
                documentosPorSubirJuntoConElcontrato.stream().filter((tipoDocumento) -> (tipoDocumento.getObligatorio())).forEachOrdered((tipoDocumento) -> {
                    documentosObligatorios.add(tipoDocumento);
                });
                List<TipoDocumento> documentosDelColaborador = new ArrayList<>();
                documentosConURL.stream().filter((documento) -> (documento.getTipoDocumentoObj().getObligatorio())).forEachOrdered((documento) -> {
                    documentosDelColaborador.add(documento.getTipoDocumentoObj());
                });
                if(documentosDelColaborador.containsAll(documentosObligatorios)){
                    short status = getStatus("Activo");
                    agremiado.setStatusAgremiado(status);
                    agremiado.setModificado(new Date());
                    agremiadoService.setAgremiado(agremiado);
                     setInformacionEnVentana(map, 0, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> se encuentra activo.");   
                    setBitacora("Cambio de estado", "El colaborador "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" quedó activo.",request);
                    notificacionesService.altaExitosa(agremiado, agremiado.getDatosLaborales().getFechaInicioContrato());
                }else{
                    LOGGER.error(LOGGER_PREFIX+"El colaborador no cumple con todos los documentos requeridos para cambiar su estado de Alta En Proceso  {"+idAgremiadoString+"}  "); 
                    map = new HashMap<>(); 
                    setInformacionEnVentana(map, 2, MODULO, "<b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> no cumple con todos los documentos requeridos para cambiar su estado de Alta En Proceso.");
                     List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumento(getModulo("Alta En Proceso"));                     
                     map.put("agremiado", agremiado);
                    map.put("datosPersonales", datosPersonales);
                    map.put("tipoDocumento", tipoDocumento) ; 

                    documentos  = agremiadoService.getDocumentos(agremiado, getModulo("Alta En Proceso"));
                    List<Documento> documentosConURL2 =  new ArrayList<>(documentos.size());
                    documentos.stream().filter((documento) -> (documento.getUrlDocumento()!=null)).forEachOrdered((documento) -> {
                        documentosConURL2.add(documento);
                    });
                    map.put("documentosDelColaborador", documentosConURL2);

                    return new ModelAndView("colaborador/documentosImss", "model", map);
                }           
        }catch(Exception e){
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar verificar los documentos de la alta en el IMSS --> "+e.getMessage());
            setInformacionEnVentana(map, 2, MODULO, "Ocurrio un problema al momento de intentar cambiar el colaborador al status Activo.");
        }        
        
        map.put("Colaboradores", getAgremiadosByStatus("Alta En Proceso"));
        return new ModelAndView("colaborador/altasEnProceso", "model", map);
    }
    
    /**
     * Controller encargado de rechazar una alta en proceso
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/rechazar-alta-en-proceso/{idAgremiado}.htm",method = RequestMethod.POST)
    public ModelAndView rechazarAltaEnProceso(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita el rechazo de un alta en proceso del colaborador {"+idAgremiado+'}');

        Map<String,Object> map = new HashMap<>();
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(agremiado.getStatusAgremiado());
            if(statusAgremiado.getStatus().equalsIgnoreCase("Alta En Proceso")){
                short status = getStatus("Expediente sin contrato");
                agremiado.setStatusAgremiado(status);
                agremiado.setModificado(new Date());
                agremiado.setObservaciones(getObservaciones(agremiado.getObservaciones(),"El usuarios con correo %U rechazó el alta en proceso."));
                agremiadoService.setAgremiado(agremiado);
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                setBitacora("Cambio de status", "Rechazó el alta en proceso de "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+" "+datosPersonales.getNombre().toUpperCase() +".", request);          
                setInformacionEnVentana(map, 0, MODULO, "El alta en proceso del colabordor se rechazó con exito.");
                notificacionesService.rechazoDuranteAlta(agremiado, "Alta En Proceso", "Alta En Proceso");
            }else{
                LOGGER.error(LOGGER_PREFIX+"No se puede rechazar el alta en proceso debido a que no tiene el status de colaborador correcto {"+idAgremiado+"}.");
                setInformacionEnVentana(map, 1, MODULO, "No se puede rechazar el alta en proceso del colaborador.");
            }
            
        } catch (Exception e) {
                LOGGER.error(LOGGER_PREFIX+"Ocurrió un problema al momento de rechazar una alta en rpoceso solicitada {"+idAgremiado+"} --> "+e.getMessage());
                setInformacionEnVentana(map, 1, MODULO, "Hubo un problema al momento de intentar rechazar la solicitud de alta en proceso.");
        }
        map.put("Colaboradores", getAgremiadosByStatus("Alta En Proceso"));
        return new ModelAndView("colaborador/altasEnProceso", "model", map);
    }
    
    /**
     * Controller encargado de mostrar la vista con todos los datos del colaborador para poderlo editar, siempre y cuando se encuentre en el status de activo
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request   Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="colaboradores/editar-colaborador/{idAgremiado}.htm", method = RequestMethod.GET)
    public ModelAndView getFormEditarColaborador(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se Solicita la edición del registro de colaborador {"+idAgremiado+'}');
        
        Map<String, Object> map = new HashMap<>();
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            if(agremiado==null){
                throw new Exception("No existe ningun registro con el idAgremiado "+idAgremiado);
            }else{
                if(agremiado.getStatusAgremiado() == getStatus("Activo")){
                    getDatosAgremiado(map, agremiado.getIdAgremiado());
                    if(agremiado.getDatosLaborales().getNombreContrato()==null){
                        agremiado = agremiadoService.getAgremiado(agremiado.getIdAgremiado());
                    }
                    if(agremiado.getDatosLaborales().getNombreContrato().isEmpty()){
                        map = new HashMap<>();
                        map.put("agremiado", agremiado);      
                        Integer idContratista = agremiado.getDatosLaborales().getContratistaObj().getIdContratista();
                        List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(agremiado.getDatosLaborales().getClienteObj().getIdCliente());   
                         List<DelContrato> contratosDelClienteTemp = new ArrayList<>(contratosDelCliente.size());
                         contratosDelCliente.stream().filter((delContrato) -> (delContrato.getIdContratista() == idContratista)).forEachOrdered((delContrato) -> {
                             contratosDelClienteTemp.add(delContrato);
                        });
                        map.put("contratos", contratosDelClienteTemp);
                        map.put("esquemas", agremiadoService.getEsquemaDePagos());  
                        map.put("viewResponse", "form");  
                        return new ModelAndView("colaborador/delClienteYDelContratoOlders", "model", map);
                    }
                    map.put("datoFijo", true);                    
                    map.put("edicion", true);                    
                    map.put("estatus", agremiado.getStatusAgremiado());
                    setCamposDispoiblesAndContratoEsquema(map, agremiado.getDatosLaborales().getEsquemaPago(), agremiado.getDatosLaborales().getNombreContrato());
                    return new ModelAndView("colaborador/formularioColaborador", "model", map);
                }else{
                    throw new Exception("El estatus {"+agremiado.getStatusAgremiado() +"} del agremiado {"+idAgremiado+"} no permite su modificación desde esta vista.");
                }
            }            
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar devolver el registro de colaborador "+idAgremiado+" --> "+e.getMessage(),e);
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar obtener dicho registro, favor de verificar su existencia o el estado del mismo con el administrador");            
            map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
            return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
        }
    }
    
    /**
     * Controller encargado de presentar la vista para el registro de un incidencia de un colaborador en especifico
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value="colaborador/registrar-incidencia/{idAgremiado}.htm",method = RequestMethod.GET)
    public ModelAndView getFormRegistroIncidencia(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){        
        LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para el registro de una incidencia del colaborador {"+idAgremiado+'}');
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            List<TipoIncidencia> tipoIncidencias = incidenciaService.getTipoIncidencias();
            map.put("agremiado", agremiado);
            map.put("datosPersonales", datosPersonales);
            map.put("tipoIncidencias", tipoIncidencias);
            return new ModelAndView("colaborador/registrarIncidencia", "model", map);           
            
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de generar la vista para el registro de una incidecia {"+idAgremiado+"}  --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "No fue posible crear la vista para el registro de un incidencia.");
            map.put("Colaboradores", getAgremiadosActivos());
            return new ModelAndView("colaborador/colaboradores", "model", map);
        }
    }
    
    /**
     * Controlador encargado de registrar un indicencia de un colaborador en especifico
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/registrar-incidencia/{idAgremiado}.htm",method = RequestMethod.POST)
    public ModelAndView setIncidencia(@PathVariable("idAgremiado") Integer idAgremiado,HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se reciben los datos para el registro de una incidencia del colaborador {"+idAgremiado+'}');
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            String idAgremiadoParameter = request.getParameter("idAgremiado");
            String fIncidenciaParameter = request.getParameter("fIncidencia");
            String tipoIncidenciaParameter = request.getParameter("tipoIncidencia");
            String cantidadParameter = request.getParameter("cantidad");
            String cmntrsParameter = request.getParameter("cmntrs");
            
            Date procesayyyyMMdd = procesayyyyMMdd(fIncidenciaParameter);
            int idAgremiadoInt = Integer.parseInt(idAgremiadoParameter);
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiadoInt);
            short tipoIncidenciaShort = Short.parseShort(tipoIncidenciaParameter);
            TipoIncidencia tipoIncidencia = incidenciaService.getTipoIncidencia(tipoIncidenciaShort);
            Cliente clienteObj = agremiado.getDatosLaborales().getClienteObj();
            
            Incidencia incidencia = new Incidencia();
            incidencia.setFechaIncidencia(procesayyyyMMdd);
            incidencia.setFechaRegistro(new Date());
            incidencia.setComentarios(cmntrsParameter);
            incidencia.setCantidad(cantidadParameter);
            incidencia.setStatus(false);
            incidencia.setAgremiadoObj(agremiado);
            incidencia.setTipoIncidenciaObj(tipoIncidencia);
            incidencia.setClienteObj(clienteObj);
            
            Integer setIncidencia = incidenciaService.setIncidencia(incidencia);
            
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            
            if(setIncidencia==null){                
                    setInformacionEnVentana(map, 1, MODULO, "No fue posible crear la llevar acabo el registro de la incidencia.");
            }else{
                    setInformacionEnVentana(map, 0, MODULO, "La incidencia para <b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> se realizó con exito.");
                    setBitacora("Inserción", "Realizó el registro de una incidencia para el colaborador "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
            }           
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar el registro de una incidecia {"+idAgremiado+"}  --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "No fue posible crear la llevar acabo el registro de la incidencia.");
        }
        map.put("Colaboradores", getAgremiadosActivos());
        return new ModelAndView("colaborador/colaboradores", "model", map);        
    }
    
    /**
     * Controlador encargado de presentar la vista con el formulario de la solicitud de baja
     * @param idAgremiado Numero entero de contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
    @RequestMapping(value = "colaborador/solicitud-de-baja/{idAgremiado}.htm",method = RequestMethod.GET)
    public ModelAndView getFormSolicitudDeBaja(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para generar la solicitud de baja del colaborador {"+idAgremiado+'}');
        
        Map<String,Object> map = new HashMap<>();
    
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            List<MotivoBaja> motivosBaja = solicitudBajaService.getMotivoBaja();
            List<TipoFiniquito> tipoFiniquito = solicitudBajaService.getTipoFiniquito();
            Modulo modulo = getModulo("Solicitud baja");
            List<TipoDocumento> tipoDocumento = agremiadoService.getTipoDocumento(modulo);
            List<TipoDocumento> docsActivos = new ArrayList<>(tipoDocumento.size());
            tipoDocumento.stream().filter((td) -> (td.getStatus())).forEachOrdered((td) -> {
                docsActivos.add(td);
            });
            
            map.put("agremiado", agremiado);
            map.put("datosPersonales", datosPersonales);
            map.put("motivosBaja", motivosBaja);
            map.put("tiposFiniquito", tipoFiniquito);
            map.put("tiposDocumento", docsActivos);
            
            return new ModelAndView("colaborador/solicitudDeBaja", "model", map); 
            
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar generar la vista de la solicitud de baja {"+idAgremiado+"}  --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "No fue posible crear la solicitud de baja.");
        }
        map.put("Colaboradores", getAgremiadosActivos());
        return new ModelAndView("colaborador/colaboradores", "model", map); 
    }
    
    /**
     * Controlador encargado de procesar la solicitud de baja de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */
   @RequestMapping(value = "colaborador/solicitud-de-baja.htm",method = RequestMethod.POST)
   public ModelAndView setSolicitudDeBaja(HttpServletRequest request, HttpServletResponse response){   
        LOGGER.info(LOGGER_PREFIX+"Se recibe una solicitud de baja.");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            String idAgremiadoString = request.getParameter("idAgremiado");
            String fSolBajaString = request.getParameter("fSolBaja");
            String sueldoString = request.getParameter("sueldo");
            String tipoFiniquitoString = request.getParameter("tipoFiniquito");
            String motivoBajaString = request.getParameter("motivoBaja");
            String motivoString = request.getParameter("motivo");
            String observacionesString = request.getParameter("observaciones");
            String[] parameterValues = request.getParameterValues("documentos");
            
            int idAgremiado = Integer.parseInt(idAgremiadoString);
            Date fechaBaja = procesayyyyMMdd(fSolBajaString);
            short tipoFiniquito = Short.parseShort(tipoFiniquitoString);
            short motivoBaja = Short.parseShort(motivoBajaString);
            
            if(sueldoString!=null){
                if(sueldoString.equalsIgnoreCase("Si")){
                    sueldoString = "Si";
                }else{
                    sueldoString = "NO";
                }
            }else{
                sueldoString = "NO";
            }
            
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            Cliente clienteObj = agremiado.getDatosLaborales().getClienteObj();
            TipoFiniquito tipoFiniquitoObj = solicitudBajaService.getTipoFiniquito(tipoFiniquito);
            MotivoBaja motivoBajaObj = solicitudBajaService.getMotivoBaja(motivoBaja);
            
            List<TipoDocumento> tipoDocumentos = new ArrayList<>(parameterValues.length);
            for (String parameterValue : parameterValues) {
                int parse = Integer.parseInt(parameterValue);
                tipoDocumentos.add( agremiadoService.getTipoDocumento(parse) );
            }
            
            SolicitudBaja solicitudBaja = new SolicitudBaja();
            solicitudBaja.setAgremiadoObj(agremiado);
            solicitudBaja.setClienteObj(clienteObj);
            solicitudBaja.setDMotivo(motivoString);
            solicitudBaja.setFechaBaja(fechaBaja);
            solicitudBaja.setMotivoBaja(motivoBajaObj);
            solicitudBaja.setMotivoDeRechazo(null);
            solicitudBaja.setObservaciones(observacionesString);
            solicitudBaja.setSueldo(sueldoString);
            solicitudBaja.setTipoFiniquitoObj(tipoFiniquitoObj);
            
            agremiadoService.setSolicitudBaja(solicitudBaja);
            if(solicitudBaja.getIdSolicitudBaja()>0){
                DocumentosBaja documentosBaja;
                for (TipoDocumento tipoDocumento : tipoDocumentos) {
                    documentosBaja = new DocumentosBaja();
                    documentosBaja.setCargaGuardadaPra(Boolean.FALSE);
                    documentosBaja.setCargaGuardadaUsu(Boolean.FALSE);
                    documentosBaja.setUrlDocumentoCargarPra(null);
                    documentosBaja.setUrlDocumentoCargarUsu(null);
                    documentosBaja.setSolicitudBajaObj(solicitudBaja);
                    documentosBaja.setTipoDocumentoObj(tipoDocumento);
                    solicitudBajaService.setDocumentoBaja(documentosBaja);
                    if(documentosBaja.getIdBajaTerminada()>0){
                       LOGGER.info(LOGGER_PREFIX+"Documento guardado correctamente {"+tipoDocumento.getNombreDocumento()+", "+agremiado.getIdAgremiado()+", "+solicitudBaja.getIdSolicitudBaja()+", "+documentosBaja.getIdBajaTerminada()+"}");                     
                    }else{
                       LOGGER.error(LOGGER_PREFIX+"Documento no guardado {"+tipoDocumento.getNombreDocumento()+", "+agremiado.getIdAgremiado()+", "+solicitudBaja.getIdSolicitudBaja()+"}"); 
                    }
                }
                short status = getStatus("Baja Solicitada");
                agremiado.setStatusAgremiado(status);
                agremiado.setModificado(new Date());
                agremiadoService.setAgremiado(agremiado);   
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                setBitacora("Baja", "Solicitó la baja del colaborador "+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);          
               setInformacionEnVentana(map, 0, MODULO, "La solicitud de baja para <b>"+datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"</b> se realizó con exito.");                
                notificacionesService.bajasSolicitada(agremiado, fechaBaja);
            }else{
                throw new Exception("La solicitud de baja no fue insertada ya que no tiene un id "+solicitudBaja.getIdSolicitudBaja());
            }           
       } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar ingresar la solicitud de baja --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "No fue posible ingresar la solicitud de baja.");
        }
        map.put("Colaboradores", getAgremiadosActivos());
        return new ModelAndView("colaborador/colaboradores", "model", map); 
   }
   
   /**
    * Controlador encargado de presentar el detalle de una solicitud de baja
    * @param idAgremiado Numero entero que contiene el id de un colaborador
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value = "colaborador/solicitud-de-baja-detalles/{idAgremiado}.htm",method = RequestMethod.GET)
   public ModelAndView getSolicitudDeBaja(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita el detalle de la baja del colaborador {"+idAgremiado+'}');
       
       Map<String,Object> map = new HashMap<>();
       
       try {
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           DatosPersonales datosPersonales = agremiado.getDatosPersonales();
           DatosLaborales datosLaborales = agremiado.getDatosLaborales();
           SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);           
           List<DocumentosBaja> documentosBaja =  solicitudBajaService.getDocumentosBaja(ultimaSolicitudBaja);
           map.put("agremiado", agremiado);
           map.put("datosPersonales", datosPersonales);
           map.put("datosLaborales", datosLaborales);
           map.put("solicitudBaja", ultimaSolicitudBaja);
           map.put("documentosBaja", documentosBaja);
               map.put("rechazo", false);
           
       } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar generar el kardex de una solicitud de baja {"+idAgremiado+"}  --> "+e.getMessage());
            return null;
       }
       
        return new ModelAndView("colaborador/solicitudDeBajaKardex", "model", map);  
       
   }
   
    /**
     * Controlador encargado de mostrar la vista para continuar con la solicitud de baja
     * @param idAgremiado Numero entero que contiene el id de un colaborador
     * @param request Variable que maneja las peticiones
     * @param response Variable que maneja las respuestas
     * @return ModelAndView
     */       
    @RequestMapping(value = "colaborador/continuar-solicitud/{idAgremiado}.htm",method = RequestMethod.GET)        
   public ModelAndView getFormDocsPradetti(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para continuar con la solicitud de baja {docsPradetti, "+idAgremiado+'}');
       
       Map<String,Object> map = new HashMap<>();
       
       try {
           
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           DatosPersonales datosPersonales = agremiado.getDatosPersonales();
           SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);
           Modulo modulo = getModulo("Solicitud baja");
           List<DocumentosBaja> documentosBaja = solicitudBajaService.getDocumentosBaja(ultimaSolicitudBaja.getIdSolicitudBaja(),modulo.getIdModulo());
           
           map.put("agremiado", agremiado);
           map.put("datosPersonales", datosPersonales);
           map.put("ultimaSolicitudBaja", ultimaSolicitudBaja);
           map.put("documentosBaja", documentosBaja);
           map.put("posicion", "Pradetti");
           map.put("documentosFinales", false);
           map.put("modulo", modulo.getNombre());
           
            return new ModelAndView("colaborador/docsPradetti", "model", map);
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento intentar generar la vista con los documentos que se requieren subir -->"+e.getMessage());
           setInformacionEnVentana(map, 1, MODULO, "No es posible continuar con la solicitud de baja.");
            List<Agremiado> BajasSolicitadas = getAgremiadosByStatus("Baja Solicitada");
            Map<Integer,SolicitudBaja> ultimaBajasSolicitada = getUltimaBaja(BajasSolicitadas);
            map.put("Colaboradores", BajasSolicitadas);
            map.put("ultimaBajasSolicitada", ultimaBajasSolicitada);
            return new ModelAndView("colaborador/bajasSolicitadas", "model", map);
       }
   }
   
   /**
    * Controller encargado de subir un documento de baja del lado que se indica en la posición
    * @param archivo Archivos con formato pdf y xlsx
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    */
   @RequestMapping(value = "colaborador/documento-baja.htm",method = RequestMethod.POST)
   public void setDocumentoDeBaja(@RequestParam("archivo") MultipartFile archivo,HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se recibe un documento de baja.");
       
       try {
                String tipoString = request.getParameter("tipo");
                String idAgremiadoString = request.getParameter("idAgremiado");
                String name = request.getParameter("name");
                String idSolicitudBajaString = request.getParameter("idSolicitudBaja");
                String posicion = request.getParameter("posicion");
                
                int tipoDocumento = Integer.parseInt(tipoString);
                int idAgremiado = Integer.parseInt(idAgremiadoString);
                int idSolicitudBaja = Integer.parseInt(idSolicitudBajaString);
                
                Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);
                if((ultimaSolicitudBaja.getIdSolicitudBaja() == idSolicitudBaja)&&(agremiado.getDatosLaborales().getClienteObj().equals(ultimaSolicitudBaja.getClienteObj()))){
                    DocumentosBaja documentoBaja = solicitudBajaService.getDocumentoBaja(idSolicitudBaja, tipoDocumento);
                    if(documentoBaja != null){
                        switch(posicion){
                            case "Pradetti":
                                if(documentoBaja.getCargaGuardadaPra()){
                                    ftpService.borrarArchivoFTP(documentoBaja.getUrlDocumentoCargarPra());
                                    documentoBaja.setCargaGuardadaPra(Boolean.FALSE);
                                    documentoBaja.setUrlDocumentoCargarPra(null);
                                }
                                String guardarArchivoFTPPra = ftpService.guardarArchivoFTP(archivo, MODULO, idAgremiadoString, "SOLICITUD_DE_BAJA_"+idSolicitudBajaString+"_DE_Pradetti");
                                documentoBaja.setUrlDocumentoCargarPra(guardarArchivoFTPPra);
                                documentoBaja.setCargaGuardadaPra(Boolean.TRUE);
                                break;
                            case "Cliente":
                                if(documentoBaja.getCargaGuardadaUsu()){
                                    ftpService.borrarArchivoFTP(documentoBaja.getUrlDocumentoCargarUsu());
                                    documentoBaja.setCargaGuardadaUsu(Boolean.FALSE);
                                    documentoBaja.setUrlDocumentoCargarUsu(null);
                                }
                                String guardarArchivoFTPCli = ftpService.guardarArchivoFTP(archivo, MODULO, idAgremiadoString, "SOLICITUD_DE_BAJA_"+idSolicitudBajaString+"_DE_Cliente");
                                documentoBaja.setUrlDocumentoCargarUsu(guardarArchivoFTPCli);
                                documentoBaja.setCargaGuardadaUsu(Boolean.TRUE);
                                break;
                            default:
                                    throw new Exception("No se reconoce la posición "+posicion+" para documentos de baja.");
                      }
                      solicitudBajaService.setDocumentoBaja(documentoBaja);
                      LOGGER.info(LOGGER_PREFIX+"Se guardo correctamente el documento de baja {"+posicion+", "+idAgremiadoString+", "+idSolicitudBajaString+", "+tipoDocumento+"}.");
                      
                        TipoDocumento tipoDocumentoFinal = agremiadoService.getTipoDocumento(tipoDocumento);
                        if(tipoDocumentoFinal!=null){
                            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                            setBitacora("Edición de documento", "Guardó correctamente el documento '"+tipoDocumentoFinal.getNombreDocumento()+"' de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
                        }
                    }else{
                        LOGGER.info(LOGGER_PREFIX+"No se encontro ningun documento con las caracteristicas solicitadas {"+idSolicitudBaja+", "+tipoDocumento+"} se procede a verificar si es un documento final");
                        TipoDocumento tipoDocumentoFinal = agremiadoService.getTipoDocumento(tipoDocumento);
                        if(tipoDocumentoFinal!=null){
                            Modulo modulo = getModulo("Documento final de solicitud de baja");
                            if(Objects.equals(tipoDocumentoFinal.getModuloObj(), modulo.getIdModulo())){
                                
                                DocumentosBaja documentoDeBajaFinal = new DocumentosBaja();
                                String guardarArchivoFTPPra = ftpService.guardarArchivoFTP(archivo, MODULO, idAgremiadoString, "SOLICITUD_DE_BAJA_"+idSolicitudBajaString+"_DE_Pradetti");
                                documentoDeBajaFinal.setUrlDocumentoCargarPra(guardarArchivoFTPPra);
                                documentoDeBajaFinal.setCargaGuardadaPra(Boolean.TRUE);
                                SolicitudBaja ultimaSolicitudBajaRegistrada = getUltimaSolicitudBaja(agremiado);
                                
                                if(ultimaSolicitudBajaRegistrada.getIdSolicitudBaja() == idSolicitudBaja){
                                    
                                    documentoDeBajaFinal.setSolicitudBajaObj(ultimaSolicitudBajaRegistrada);
                                    documentoDeBajaFinal.setTipoDocumentoObj(tipoDocumentoFinal);
                                    solicitudBajaService.setDocumentoBaja(documentoDeBajaFinal);
                                    
                                    if(documentoDeBajaFinal.getIdBajaTerminada()>0){
                                       LOGGER.info(LOGGER_PREFIX+"Documento guardado correctamente {"+tipoDocumentoFinal.getNombreDocumento()+", "+agremiado.getIdAgremiado()+", "+ultimaSolicitudBajaRegistrada.getIdSolicitudBaja()+", "+documentoDeBajaFinal.getIdBajaTerminada()+"}");                     
                                        DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                                        setBitacora("Inserción de documento", "Guardó correctamente el documento '"+tipoDocumentoFinal.getNombreDocumento()+"' de  "+datosPersonales.getNombre().toUpperCase()+' '+datosPersonales.getApellidoPaterno().toUpperCase()+' '+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+".", request);
                                    }else{
                                       LOGGER.error(LOGGER_PREFIX+"Documento no guardado {"+tipoDocumentoFinal.getNombreDocumento()+", "+agremiado.getIdAgremiado()+", "+ultimaSolicitudBajaRegistrada.getIdSolicitudBaja()+"}"); 
                                    }
                                }else{
                                    throw new Exception(LOGGER_PREFIX+"El tipo de documento recibido no corresponde a con la ultima solicitud de baja registrada del colaborador {"+idAgremiadoString+", "+idSolicitudBaja+"}.");
                                }
                            }else{
                                throw new Exception("El tipo de documento encontrado no coresponde con las caracteristicas de documento final de solicitud de baja {"+idSolicitudBaja+", "+tipoDocumento+"}.");
                            }
                        }else{
                            throw new Exception("No se encontro ningun tipo de documento final con las caracteristicas solicitadas {"+idSolicitudBaja+", "+tipoDocumento+"}.");
                        }
                    }
                }else{
                    throw  new Exception("La solicitud de baja recibida no empata con la ultima solicitud de baja registrada para el colaborador {"+idAgremiado+", "+idSolicitudBaja+", "+ultimaSolicitudBaja.getIdSolicitudBaja()+"}.");
                }           
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento intentar guardar un documento de baja -->"+e.getMessage());
       }
       
   }
   
   /**
    * Controller encargado de devolver los documentos que ya se tienen cargados en la solicitud de baja
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value = "colaborador/documentos-de-la-solicitud-de-baja.htm",method = RequestMethod.POST)
   @ResponseBody
   public String getDocsSolictudDeBaja(HttpServletRequest request, HttpServletResponse response){
                String posicion = request.getParameter("posicion");
                String solicitudBajaString = request.getParameter("solicitudBaja");        
                String idAgremiadoString = request.getParameter("idAgremiado");       
                String moduloString = request.getParameter("modulo");        
                LOGGER.info(LOGGER_PREFIX+"Se solicitan los documentos de la solicitud de baja "+solicitudBajaString+" del agremiado "+idAgremiadoString+", modulo "+moduloString+", en la posición de "+posicion+".");
                
                try {
                    int idSolicitudBaja = Integer.parseInt(solicitudBajaString);
                    int idAgremiado = Integer.parseInt(idAgremiadoString);
                    
                    Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                    SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);
                    if((ultimaSolicitudBaja.getIdSolicitudBaja() == idSolicitudBaja)&&(agremiado.getDatosLaborales().getClienteObj().equals(ultimaSolicitudBaja.getClienteObj()))){                        
                        Modulo modulo = getModulo(moduloString);
                        List<DocumentosBaja> documentosBaja =  solicitudBajaService.getDocumentosBaja(ultimaSolicitudBaja.getIdSolicitudBaja(),modulo.getIdModulo());
                        List<DocumentosBaja> documentosBajaCargados = new ArrayList<>(documentosBaja.size());
                        switch(posicion){
                            case "Pradetti":
                                documentosBaja.stream().filter((db) -> (db.getCargaGuardadaPra())).forEachOrdered((db) -> {
                                    documentosBajaCargados.add(db);
                            });
                                break;
                            case "Cliente":
                                documentosBaja.stream().filter((db) -> (db.getCargaGuardadaUsu())).forEachOrdered((db) -> {
                                    documentosBajaCargados.add(db);
                            });
                                break;
                            default:
                                    throw new Exception("No se reconoce la posición "+posicion+" para documentos de baja.");
                        }
                         Map<Integer,String> documentosDelColaborador = new HashMap<>(documentosBajaCargados.size());
                         TipoDocumento tipoDocumento = null;
                        for (DocumentosBaja documentosBajaCargado : documentosBajaCargados) {
                            tipoDocumento = documentosBajaCargado.getTipoDocumentoObj();
                            documentosDelColaborador.put(tipoDocumento.getIdTipoDocumento(), tipoDocumento.getNombreDocumento());
                        }
                        Map<String,Object> map = new HashMap<>(2);
                        map.put("documentos", documentosDelColaborador);

                        return new ObjectMapper().writeValueAsString(map);
                    }else{
                        throw  new Exception("La solicitud de baja recibida no empata con la ultima solicitud de baja registrada para el colaborador {"+idAgremiado+", "+idSolicitudBaja+", "+ultimaSolicitudBaja.getIdSolicitudBaja()+"}.");
                    } 
                    
                } catch (NumberFormatException nfe) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de parsear la información recibida par obtener los documentos de la solicitud de baja {"+idAgremiadoString+','+solicitudBajaString+"}  --> "+nfe.getMessage());
                    return "";
                }catch (JsonProcessingException jpe) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al obtener los documentos de la solicitud de baja --JsonProcessingException-- {"+idAgremiadoString+','+solicitudBajaString+"}  --> "+jpe.getMessage());
                    return "";
                }catch (Exception e) {
                    LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al obtener los documentos de la solicitud de baja del colaborador {"+idAgremiadoString+','+solicitudBajaString+"}  --> "+e.getMessage());
                    return "";
                }
   }
   
   /**
    * Controller encargado de mostrar un documento de baja solicitado
    * @param posicion Cadena de texto que que indica la versión del documento que se desea mostrar
    * @param idAgremiado Numero entero que contiene el id de un colaborador
    * @param idSolicitud Numero entero que contiene el id de la solicitud de baja
    * @param tipoDoc Numero entero que contiene la el id del tipo de documento
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    */
   @RequestMapping(value = "colaborador/ver-documento-de-baja/{posicion}/{idAgremiado}/{idSolicitud}/{tipoDoc}.htm",method = RequestMethod.GET)
   public void getDocumentoDeBaja(@PathVariable("posicion")String posicion,@PathVariable("idAgremiado")Integer idAgremiado,@PathVariable("idSolicitud")Integer idSolicitud,@PathVariable("tipoDoc")Integer tipoDoc, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita la vista del documento de baja de "+posicion+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
       try {
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           DatosPersonales datosPersonales = agremiado.getDatosPersonales();
           List<SolicitudBaja> solicitudBajaList = agremiado.getSolicitudBajaList();
           SolicitudBaja solicitudBaja = null;
           for (SolicitudBaja sb : solicitudBajaList) {
               if(sb.getIdSolicitudBaja() == idSolicitud){
                   solicitudBaja = sb;
                   break;
               }
           }
           if(solicitudBaja != null){
               DocumentosBaja documentoBaja = solicitudBajaService.getDocumentoBaja(idSolicitud, tipoDoc);
               if(documentoBaja != null){
                   File descargarFileFTP = null;
                   String url = null;
                   switch(posicion){
                       case "Pradetti":
                           if(documentoBaja.getCargaGuardadaPra()){
                               url = documentoBaja.getUrlDocumentoCargarPra();
                           }
                           break;
                       case "Cliente":
                           if(documentoBaja.getCargaGuardadaUsu()){
                               url = documentoBaja.getUrlDocumentoCargarUsu();
                           }
                           break;
                      default:
                          throw new Exception("Posición  "+posicion+" no encontrada"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
                   }
                   if(url!=null){
                       descargarFileFTP = ftpService.descargarFileFTP(url);
                   }else{
                   throw new Exception("URL de Documento de baja no encontrada"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
                   }   
                   
                   TipoDocumento tipoDocumento = agremiadoService.getTipoDocumento(tipoDoc);
                    InputStream is = new FileInputStream(descargarFileFTP);
                    if(url.endsWith(".pdf")){
                        response.setHeader("Content-Disposition", "inline;filename=\"" + tipoDocumento.getNombreDocumento().toUpperCase()+"__"+datosPersonales.getApellidoPaterno().toUpperCase()+"_"+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"_"+datosPersonales.getNombre().toUpperCase()  + ".pdf\"");
                        OutputStream sos = response.getOutputStream();
                        response.setContentType("application/pdf");
                        IOUtils.copy(is, sos);
                        sos.flush();  
                    }else{
                        String fileName = "";
                        if(url.contains("\\")){
                            int indx = url.lastIndexOf("\\");
                            fileName = url.substring(indx);
                        }else{
                            int indx = url.lastIndexOf("/");
                            fileName = url.substring(indx);
                        }
                        response.setContentType("application/x-download");
                        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
                        OutputStream sos = response.getOutputStream();
                        IOUtils.copy(is, sos);
                        sos.flush();
                    }                  
               }else{
                   throw new Exception("Documento de baja no encontrado"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
               }
           }else{
               throw new Exception("Solicitud de baja no encontrada"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
           }
       } catch (Exception e) {
           LOGGER.error("Ocurrio un porblema al intentar mostrar el documento de baja"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}  --> "+e.getMessage());
       }
   }
   
   /**
    * Controller encargado de borrar un documento de una solicitud de baja 
    * @param posicion Cadena de texto que que indica la versión del documento que se desea mostrar
    * @param idAgremiado Numero entero que contiene el id de un colaborador
    * @param idSolicitud Numero entero que contiene el id de la solicitud de baja
    * @param tipoDoc Numero entero que contiene la el id del tipo de documento
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    */
   @RequestMapping(value = "colaborador/borrar-documento-de-baja/{posicion}/{idAgremiado}/{idSolicitud}/{tipoDoc}.htm",method = RequestMethod.POST)
   public void borrarDocumentoDeBaja(@PathVariable("posicion")String posicion,@PathVariable("idAgremiado")Integer idAgremiado,@PathVariable("idSolicitud")Integer idSolicitud,@PathVariable("tipoDoc")Integer tipoDoc, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita el borrado del documento de baja de "+posicion+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
try {
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           List<SolicitudBaja> solicitudBajaList = agremiado.getSolicitudBajaList();
           SolicitudBaja solicitudBaja = null;
           for (SolicitudBaja sb : solicitudBajaList) {
               if(sb.getIdSolicitudBaja() == idSolicitud){
                   solicitudBaja = sb;
                   break;
               }
           }
           if(solicitudBaja != null){
               DocumentosBaja documentoBaja = solicitudBajaService.getDocumentoBaja(idSolicitud, tipoDoc);
               if(documentoBaja != null){
                   String url = null;
                   switch(posicion){
                       case "Pradetti":
                           if(documentoBaja.getCargaGuardadaPra()){
                               url = documentoBaja.getUrlDocumentoCargarPra();
                               documentoBaja.setCargaGuardadaPra(Boolean.FALSE);
                               documentoBaja.setUrlDocumentoCargarPra(null);
                           }
                           break;
                       case "Cliente":
                           if(documentoBaja.getCargaGuardadaUsu()){
                               url = documentoBaja.getUrlDocumentoCargarUsu();
                               documentoBaja.setCargaGuardadaUsu(Boolean.FALSE);
                               documentoBaja.setUrlDocumentoCargarUsu(null);
                           }
                           break;
                      default:
                          throw new Exception("Posición  "+posicion+" no encontrada"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
                   }
                   if(url!=null){
                       ftpService.borrarArchivoFTP(url);
                   }else{
                   throw new Exception("URL de Documento de baja no encontrada"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
                   }   
                                     
                   solicitudBajaService.setDocumentoBaja(documentoBaja);                   
                   LOGGER.info(LOGGER_PREFIX+"El documento de baja de borró exitosamente"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
               }else{
                   throw new Exception("Documento de baja no encontrado"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
               }
           }else{
               throw new Exception("Solicitud de baja no encontrada"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}.");
           }
       } catch (Exception e) {
           LOGGER.error("Ocurrio un porblema al intentar borrar el documento de baja"+" {"+idAgremiado+", "+idSolicitud+", "+tipoDoc+"}  --> "+e.getMessage());
       }
   }
   
   /**
    * Controller encargado de verificar que los requrimientos de la solicitud de baja se encuentren correctos 
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value = "colaborador/continuar-proceso-de-baja.htm",method = RequestMethod.POST)
   public ModelAndView continuarProcesoDeBajaDeColaborador(HttpServletRequest request, HttpServletResponse response){
        String idAgremiadoString = request.getParameter("idAgremiado");
        String idSolicitudBajaString = request.getParameter("idSolicitudBaja");
        String posicion = request.getParameter("posicion");
        LOGGER.info(LOGGER_PREFIX+"Se solicita la validadción de documento de "+posicion+" {"+idAgremiadoString+", "+idSolicitudBajaString+"}.");
        Map<String,Object> map = new HashMap<>();
        try {
                int idAgremiado = Integer.parseInt(idAgremiadoString);
                int idSolicitudBaja = Integer.parseInt(idSolicitudBajaString);
                
                Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                List<SolicitudBaja> solicitudBajaList = agremiado.getSolicitudBajaList();
                SolicitudBaja solicitudBaja = null;
                for (SolicitudBaja sb : solicitudBajaList) {
                         if((sb.getIdSolicitudBaja() == idSolicitudBaja)&&(agremiado.getDatosLaborales().getClienteObj().equals(sb.getClienteObj()))){
                             solicitudBaja = sb;
                             break;
                         }
                }
                Modulo modulo = getModulo("Solicitud baja");
                List<DocumentosBaja> documentosBaja = solicitudBajaService.getDocumentosBaja(solicitudBaja.getIdSolicitudBaja(), modulo.getIdModulo()); 
                int totalDeDocumentosCargados = 0;
                switch(posicion){
                    case "Pradetti":
                        totalDeDocumentosCargados = documentosBaja.stream().filter((db) -> (db.getCargaGuardadaPra()&&(db.getUrlDocumentoCargarPra()!=null)&&db.getTipoDocumentoObj().getObligatorio())).map((_item) -> 1).reduce(totalDeDocumentosCargados, Integer::sum);
                        break;
                    case "Cliente":
                        totalDeDocumentosCargados = documentosBaja.stream().filter((db) -> (db.getCargaGuardadaUsu()!=null)).filter((db) -> ( db.getCargaGuardadaUsu()&&(db.getUrlDocumentoCargarUsu()!=null)&&db.getTipoDocumentoObj().getObligatorio() )).map((_item) -> 1).reduce(totalDeDocumentosCargados, Integer::sum);
                        break;
                    default:
                        throw new RuntimeException("Validación de proceso de baja en la posición "+posicion+" no se encuentra una vista definidad para este tipo de posición.");
                }
                List<DocumentosBaja> dbs = new ArrayList<>();
                for (DocumentosBaja db : documentosBaja) {
                    if(db.getTipoDocumentoObj().getObligatorio()){
                        dbs.add(db);
                    }
               }
                documentosBaja = null;
                if(totalDeDocumentosCargados == dbs.size()){
                    short status;
                        switch(posicion){
                        case "Pradetti":
                            status = getStatus("Baja Pendiente");
                            agremiado.setStatusAgremiado(status);
                            agremiado.setModificado(new Date());
                            agremiadoService.setAgremiado(agremiado);
                            LOGGER.info(LOGGER_PREFIX+"El colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+" fue movido con exito de estatus {"+status+"}.");
                            setInformacionEnVentana(map, 0, MODULO, "El colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b> ha cambiado exitosamente de estado");
                            setBitacora("Cambio de estado", "Cambio el estado del colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+" a bajas pendientes.", request);
                            notificacionesService.bajaPendiente(agremiado);
                            break;
                        case "Cliente":
                            status = getStatus("Baja Por Finalizar");
                            agremiado.setStatusAgremiado(status);
                            agremiado.setModificado(new Date());
                            agremiadoService.setAgremiado(agremiado);
                            LOGGER.info(LOGGER_PREFIX+"El colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+" fue movido con exito de estatus {"+status+"}.");
                            setInformacionEnVentana(map, 0, MODULO, "El colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b> ha cambiado exitosamente de estado");
                            setBitacora("Cambio de estado", "Cambio el estado del colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+" a bajas por finalizar.", request);
                            notificacionesService.bajaPorFinalizar(agremiado);
                            break;
                    }
                }else{                    
                    setInformacionEnVentana(map, 2, MODULO, "El colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b> no cuenta con todos los documentos requeridos para ser movido de estatus.");
                }
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al momento de valida el proceso de baja en la posición de "+posicion+" {"+idAgremiadoString+", "+idSolicitudBajaString+"}  --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "No fue posible llevar a cabo el proceso de validación de documentos");
       }
        switch(posicion){
            case "Pradetti":
                List<Agremiado> BajasSolicitadas = getAgremiadosByStatus("Baja Solicitada");
                Map<Integer,SolicitudBaja> ultimaBajasSolicitada = getUltimaBaja(BajasSolicitadas);
                map.put("Colaboradores", BajasSolicitadas);
                map.put("ultimaBajasSolicitada", ultimaBajasSolicitada);
                return new ModelAndView("colaborador/bajasSolicitadas", "model", map);
            case "Cliente":
                List<Agremiado> BajasPendientes = getAgremiadosByStatus("Baja Pendiente");
                Map<Integer,SolicitudBaja> ultimaBajasPendientes = getUltimaBaja(BajasPendientes);
                map.put("Colaboradores", BajasPendientes);
                map.put("ultimaBajasSolicitada", ultimaBajasPendientes);
                return new ModelAndView("colaborador/bajasPendientes", "model", map);
            default:
                throw new RuntimeException("Validación de proceso de baja en la posición "+posicion+" no se encuentra una vista definidad para este tipo de posición.");
        }
   }
   
   /**
    * Controlador encargado mostrar el formulario para rechazar la solicitud de baja de un colaborador
    * @param idAgremiado Numero entero que contiene el id del colaborador
    * @param request Variable que maneja las peticiones
    * @param response Varible que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value = "colaborador/rechazar-baja/{idAgremiado}.htm",method = RequestMethod.GET)
   public ModelAndView getFormRechazoDeBaja(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para el rechazo de una solicitud de baja {"+idAgremiado+"}.");
       
       Map<String,Object> map = new HashMap<>();
       
       try {
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);           
           Modulo modulo = getModulo("Solicitud baja");
           List<DocumentosBaja> documentosBaja =  solicitudBajaService.getDocumentosBaja(ultimaSolicitudBaja.getIdSolicitudBaja(),modulo.getIdModulo());
           if(ultimaSolicitudBaja != null){
               map.put("agremiado", agremiado);
               map.put("solicitudBaja", ultimaSolicitudBaja);
               map.put("documentosBaja", documentosBaja);              
               DatosPersonales datosPersonales = agremiado.getDatosPersonales();
               DatosLaborales datosLaborales = agremiado.getDatosLaborales();
                map.put("datosPersonales", datosPersonales);
                map.put("datosLaborales", datosLaborales);             
               map.put("rechazo", true);
               return new ModelAndView("colaborador/solicitudDeBajaKardex", "model", map);  
           }else{
               throw new Exception("No se encontro la solicitud de baja solictada {"+idAgremiado+", "+ultimaSolicitudBaja.getIdSolicitudBaja()+"}.");
           }
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar generar la vista para el rechazo de un solicitud de baja  --> "+e.getMessage());
           setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al momento de generar la vista, para el rechazo de un solicitud.");
       }
    List<Agremiado> BajasSolicitadas = getAgremiadosByStatus("Baja Solicitada");
    Map<Integer,SolicitudBaja> ultimaBajasSolicitada = getUltimaBaja(BajasSolicitadas);
    map.put("Colaboradores", BajasSolicitadas);
    map.put("ultimaBajasSolicitada", ultimaBajasSolicitada);
    return new ModelAndView("colaborador/bajasSolicitadas", "model", map);
   }
   
   /**
    * Controlador encargado de rechazar la solciitud de baja de un colaborador
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value = "colaborador/rechazar-baja.htm",method = RequestMethod.POST)
   public ModelAndView setRechazarBaja(HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita el rechazo de una baja");
       
       Map<String,Object> map = new HashMap<>();
       
       try {
           String idSolicitudString = request.getParameter("idSolicitud");
           String idAgremiadoString = request.getParameter("idAgremiado");
           String motivo = request.getParameter("motivo");
           int idSolicitudBaja = Integer.parseInt(idSolicitudString);
           int idAgremiado = Integer.parseInt(idAgremiadoString);
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(agremiado.getStatusAgremiado());
           StatusAgremiado statusAgremiadoOrigen = statusAgremiado;
           List<SolicitudBaja> solicitudBajaList = agremiado.getSolicitudBajaList();
           SolicitudBaja solicitudBaja = null;
           for (SolicitudBaja sb : solicitudBajaList) {
               if((sb.getIdSolicitudBaja() == idSolicitudBaja)&&(agremiado.getDatosLaborales().getClienteObj().equals(sb.getClienteObj()))){
                    solicitudBaja = sb;
                    break;
                }
           }
            if(solicitudBaja!=null){
                solicitudBaja.setMotivoDeRechazo(motivo);
                agremiadoService.setSolicitudBaja(solicitudBaja);
                agremiado.setModificado(new Date());
                String newObservaciones = "El Usuario %U rechazo la solicitud de baja en el estado de "+statusAgremiado.getStatus()+" por el siguiente motivo: "+motivo+".";
                agremiado.setObservaciones(getObservaciones(agremiado.getObservaciones(), newObservaciones));
                short status;
                switch(statusAgremiado.getStatus()){
                    case "Baja Solicitada":
                        status = getStatus("Activo");
                        break;
                    case "Baja Pendiente":
                        status = getStatus("Baja Solicitada");
                        break;
                    case "Baja Por Finalizar":
                        status = getStatus("Baja Pendiente");
                        break;
                    default:
                        status = getStatus("Activo");
                }
                agremiado.setStatusAgremiado(status);
                agremiadoService.setAgremiado(agremiado);
                LOGGER.info(LOGGER_PREFIX+"El rechazo de la solicitud de baja del colaborador {"+idAgremiadoString+"} se realizó con exito.");
               DatosPersonales datosPersonales = agremiado.getDatosPersonales(); 
               setInformacionEnVentana(map, 0, MODULO, "El rechazo de la solicitud de baja del colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b> se realizó correctamente.");
               setBitacora("Rechazo de solicitud de baja", "Rechazó la solicitud de baja número "+idSolicitudString+" relacionada con el colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b>", request);
                statusAgremiado = agremiadoService.getStatusAgremiado(agremiado.getStatusAgremiado());
               switch(statusAgremiadoOrigen.getStatus()){
                    case "Baja Solicitada":
                        notificacionesService.solicitudDeBajaRechazada(agremiado);
                        break;
                    case "Baja Pendiente":
                        notificacionesService.bajaPendienteRechazada(agremiado);
                        break;
                    case "Baja Por Finalizar":
                        notificacionesService.bajaPorFinalizarRechazada(agremiado);
                        break;
                }
               switch(statusAgremiadoOrigen.getStatus()){
                    case "Baja Solicitada":                        
                        List<Agremiado> BajasSolicitadas = getAgremiadosByStatus("Baja Solicitada");
                        Map<Integer,SolicitudBaja> ultimaBajasSolicitada = getUltimaBaja(BajasSolicitadas);
                        map.put("Colaboradores", BajasSolicitadas);
                        map.put("ultimaBajasSolicitada", ultimaBajasSolicitada);
                        return new ModelAndView("colaborador/bajasSolicitadas", "model", map);
                    case "Baja Pendiente":                        
                        List<Agremiado> BajasPendientes = getAgremiadosByStatus("Baja Pendiente");
                        Map<Integer,SolicitudBaja> ultimaBajasPendientes = getUltimaBaja(BajasPendientes);
                        map.put("Colaboradores", BajasPendientes);
                        map.put("ultimaBajasSolicitada", ultimaBajasPendientes);
                        return new ModelAndView("colaborador/bajasPendientes", "model", map);
                    case "Baja Por Finalizar":                        
                        List<Agremiado> BajasPorFinalizar = getAgremiadosByStatus("Baja Por Finalizar");
                        Map<Integer,SolicitudBaja> ultimaBajasPorFinalizar = getUltimaBaja(BajasPorFinalizar);
                        map.put("Colaboradores", BajasPorFinalizar);
                        map.put("ultimaBajasSolicitada", ultimaBajasPorFinalizar);
                        return new ModelAndView("colaborador/bajasPorFinalizar", "model", map);
                }
               
               
            }else{
                throw  new Exception("No se encontro un solicitud de baja con las propiedades enviadas {"+idAgremiadoString+", "+idSolicitudString+"}.");
            }   
       } catch (Exception e) {
           LOGGER.info(LOGGER_PREFIX+"Ocurrio un problema al momento de rechazar un baja solicitada --> "+e.getMessage());
       }
       throw new RuntimeException(LOGGER_PREFIX+"Excepción deliverada en setRechazarBaja(...) .");
   }
   
   /**
    * Controller encargado de mostrar la vista para subir los documentos firmados de la solicitud de baja
    * @param idAgremiado Numero entero que contiene el id de un colaborador
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value="colaborador/subir-documentos-baja-firmados/{idAgremiado}.htm",method = RequestMethod.GET)
   public ModelAndView getFormSubirDocumentosFirmados(@PathVariable("idAgremiado")Integer idAgremiado,HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita la vista del formulario para subir lo documentos firmados de baja del colaborador {"+idAgremiado+"}.");
       Map<String, Object> map = new HashMap<>();
       
       try {
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           DatosPersonales datosPersonales = agremiado.getDatosPersonales();
           SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);           
           Modulo modulo = getModulo("Solicitud baja");
           List<DocumentosBaja> documentosBaja =  solicitudBajaService.getDocumentosBaja(ultimaSolicitudBaja.getIdSolicitudBaja(),modulo.getIdModulo());
          
           map.put("agremiado", agremiado);
           map.put("datosPersonales", datosPersonales);
           map.put("ultimaSolicitudBaja", ultimaSolicitudBaja);
           map.put("documentosBaja", documentosBaja);
           map.put("posicion", "Cliente");
           map.put("modulo", modulo.getNombre());
            return new ModelAndView("colaborador/docsCliente", "model", map);
       } catch (Exception e) {
           LOGGER.info(LOGGER_PREFIX+"Ocurrio un problema al momento de generar la vista para subri los documentos firmados por el colaborador {"+idAgremiado+"} --> "+e.getMessage());
           setInformacionEnVentana(map, 1, MODULO, "No fue posible generar la vista para la carga de los archivo de baja");
       }       
        List<Agremiado> BajasPendientes = getAgremiadosByStatus("Baja Pendiente");
        Map<Integer,SolicitudBaja> ultimaBajasPendientes = getUltimaBaja(BajasPendientes);
        map.put("Colaboradores", BajasPendientes);
        map.put("ultimaBajasSolicitada", ultimaBajasPendientes);
        return new ModelAndView("colaborador/bajasPendientes", "model", map);
   }
     
   /**
    * Controlador encargado de mostrar la vista para subir los documentos finales de la solicitud de baja de un colaborador
    * @param idAgremiado Numero que contiene el id de un colaborador
    * @param request Variable que maneja las peticiones
    * @param response Variable que manejas las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value = "colaborador/subir-documentos-finales/{idAgremiado}.htm",method = RequestMethod.GET)        
   public ModelAndView getFormDocsFinales(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para continuar subir los documentos finales {"+idAgremiado+'}');
       
       Map<String,Object> map = new HashMap<>();
       
       try {
           
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           DatosPersonales datosPersonales = agremiado.getDatosPersonales();
           SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);
           Modulo modulo = getModulo("Documento final de solicitud de baja");
           List<DocumentosBaja> documentosBaja = solicitudBajaService.getDocumentosBaja(ultimaSolicitudBaja.getIdSolicitudBaja(),modulo.getIdModulo());
           List<TipoDocumento> tiposDocumento = agremiadoService.getTipoDocumento(modulo);
           
           map.put("agremiado", agremiado);
           map.put("datosPersonales", datosPersonales);
           map.put("ultimaSolicitudBaja", ultimaSolicitudBaja);
           map.put("documentosBaja", documentosBaja);
           map.put("posicion", "Pradetti");
           map.put("documentosFinales", true);
           map.put("documentosBajaFinales", tiposDocumento);
           map.put("modulo", modulo.getNombre());
           
            return new ModelAndView("colaborador/docsPradetti", "model", map);
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento intentar generar la vista con los documentos finales que se requieren subir -->"+e.getMessage());
           setInformacionEnVentana(map, 1, MODULO, "No es posible continuar con la finalizacón de la baja.");            
            List<Agremiado> BajasPorFinalizar = getAgremiadosByStatus("Baja Por Finalizar");
            Map<Integer,SolicitudBaja> ultimaBajasPorFinalizar = getUltimaBaja(BajasPorFinalizar);
            map.put("Colaboradores", BajasPorFinalizar);
            map.put("ultimaBajasSolicitada", ultimaBajasPorFinalizar);
            return new ModelAndView("colaborador/bajasPorFinalizar", "model", map);
       }
   }
   
   /**
    * Controlador encargado de subir los documentos finales de la solicitud de baja
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value="colaborador/documentos-finales.htm",method = RequestMethod.POST)
   public ModelAndView verificarDocumentosParaFinalizarProcesoDeBaja(HttpServletRequest request, HttpServletResponse response){
        String idAgremiadoString = request.getParameter("idAgremiado");
        String idSolicitudBajaString = request.getParameter("idSolicitudBaja");
        String posicion = request.getParameter("posicion");
        LOGGER.info(LOGGER_PREFIX+"Se solicita la validación de documento de "+posicion+" {"+idAgremiadoString+", "+idSolicitudBajaString+"}.");
        Map<String,Object> map = new HashMap<>();
        try {
                int idAgremiado = Integer.parseInt(idAgremiadoString);
                int idSolicitudBaja = Integer.parseInt(idSolicitudBajaString);
                Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                short status = getStatus("Baja Por Finalizar");
                //Modulo modulo = getModulo("Documento final de solicitud de baja"); 
                
                if(status == agremiado.getStatusAgremiado()){
                        SolicitudBaja solicitudBaja = getUltimaSolicitudBaja(agremiado);
                        Modulo modulo = getModulo("Documento final de solicitud de baja");
                        List<TipoDocumento> documentosFinales = agremiadoService.getTipoDocumento(modulo);
                        if((solicitudBaja.getIdSolicitudBaja() == idSolicitudBaja)&&(agremiado.getDatosLaborales().getClienteObj().equals(solicitudBaja.getClienteObj()))){
                            List<DocumentosBaja> documentosDeBajaEnLaSolicitud = solicitudBajaService.getDocumentosBaja(idSolicitudBaja, modulo.getIdModulo());
                            int totalDeDocumentosEnLaSolicitud = documentosDeBajaEnLaSolicitud.size();
                            
                            int totalDeDocumentosFinales = documentosFinales.size();
                            
                             int totalDeDocumentosCargadosPorPradetti = 0;
                            totalDeDocumentosCargadosPorPradetti = documentosDeBajaEnLaSolicitud.stream().filter((db) -> (db.getCargaGuardadaPra()&&(db.getUrlDocumentoCargarPra()!=null))).map((_item) -> 1).reduce(totalDeDocumentosCargadosPorPradetti, Integer::sum);
                            
//                            int totalDeDocumentosCargadosPorElCliente= 0;
//                            totalDeDocumentosCargadosPorElCliente = documentosDeBajaEnLaSolicitud.stream().filter((documentosBaja) -> (documentosBaja.getCargaGuardadaUsu()!=null)).filter((documentosBaja) -> ( documentosBaja.getCargaGuardadaUsu()&&(documentosBaja.getUrlDocumentoCargarUsu()!=null) )).map((_item) -> 1).reduce(totalDeDocumentosCargadosPorElCliente, Integer::sum);
                            
                            //int totalDocumentosRequeridosParaElCliente = totalDeDocumentosEnLaSolicitud - totalDeDocumentosFinales;
                            if((totalDeDocumentosFinales == totalDeDocumentosCargadosPorPradetti))/*&&(totalDocumentosRequeridosParaElCliente == totalDeDocumentosCargadosPorElCliente))*/{   
                                short statusNew = getStatus("Baja");
                                agremiado.setModificado(new Date());
                                agremiado.setStatusAgremiado(statusNew);
                                agremiadoService.setAgremiado(agremiado);
                                LOGGER.info(LOGGER_PREFIX+"Se cambia el estatus del colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+" a Baja definitiva.");
                                setBitacora("Cambio de status", "Realizó la baja del colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+".", request);
                                setInformacionEnVentana(map, 0, MODULO, "La baja del colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+" se realizó correctamente.");
                                notificacionesService.solicitudDeBajaExitosa(agremiado, solicitudBaja.getFechaBaja());
                            }else{
                                LOGGER.info(LOGGER_PREFIX+"La solicitud de baja se encuentra con los documentos incompletos {"+idSolicitudBajaString+", ("+totalDeDocumentosEnLaSolicitud+", "+totalDeDocumentosCargadosPorPradetti+"), ("+/*totalDocumentosRequeridosParaElCliente+", "+totalDeDocumentosCargadosPorElCliente+")"+*/"}.");
                                setInformacionEnVentana(map, 2, MODULO, "La solicitud de baja de <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b> no cuenta con todos los documentos solicitados.");
                            }
                        }        
                }else{
                    LOGGER.error(LOGGER_PREFIX+"El colaborador no se encuentra en el estado correcto {"+agremiado.getStatusAgremiado()+", "+status+"}.");
                    setInformacionEnVentana(map, 1, MODULO, "No fue posible llevar a cabo el proceso de validación de documentos finales ya que el colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b> no se encuentra en el estado requerido para dicho proceso.") ;
                }
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al momento de valida el proceso de baja en la posición de "+posicion+" {"+idAgremiadoString+", "+idSolicitudBajaString+"}  --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "No fue posible llevar a cabo el proceso de validación de documentos finales.");
       }
        
        List<Agremiado> BajasPorFinalizar = getAgremiadosByStatus("Baja Por Finalizar");
        Map<Integer,SolicitudBaja> ultimaBajasPorFinalizar = getUltimaBaja(BajasPorFinalizar);
        map.put("Colaboradores", BajasPorFinalizar);
        map.put("ultimaBajasSolicitada", ultimaBajasPorFinalizar);
        return new ModelAndView("colaborador/bajasPorFinalizar", "model", map);
   }
   
   /**
    * Controlador encargado de mostrar la vista para rechazar definitivamente una solicitud de baja de un colaborador y reactivarlo completamente
    * @param idAgremiado Numero entero que contiene el id de un colaborador
    * @param request Variable encargada de manjeras las peticiones
    * @param response Variable encargada de manejar las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value = "colaborador/rechazar-baja-definitivamente/{idAgremiado}.htm",method = RequestMethod.GET)
   public ModelAndView getFormRechazarBajaDefinitivamente(@PathVariable("idAgremiado") Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para rechazar una solicitud de baja defiitivamente {"+idAgremiado+"}.");
       
       Map<String,Object> map = new HashMap<>();
       
      try {
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           DatosPersonales datosPersonales = agremiado.getDatosPersonales();
           DatosLaborales datosLaborales = agremiado.getDatosLaborales();
           SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);           
           List<DocumentosBaja> documentosBaja =  solicitudBajaService.getDocumentosBaja(ultimaSolicitudBaja);
           map.put("agremiado", agremiado);
           map.put("stts", agremiado.getStatusAgremiado());
           map.put("datosPersonales", datosPersonales);
           map.put("datosLaborales", datosLaborales);
           map.put("solicitudBaja", ultimaSolicitudBaja);
           map.put("documentosBaja", documentosBaja);
           
       } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar generar el formulario de cancelación de solicitud de baja {"+idAgremiado+"}  --> "+e.getMessage());
            return null;
       }       
        return new ModelAndView("colaborador/rechazarBajaDefinitivamnte", "model", map);         
   }
   
   /**
    * Controller encargado de rechazar definitivamente la solicitu de baja de un colaborador y reactivarlo completamente
    * @param request Variable encargada de manejar las peticiones
    * @param response Variable encargada de manejar las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value = "colaborador/rechazar-baja-definitivamente.htm",method = RequestMethod.POST)
   public ModelAndView rechazarSolicitudDeBajaDefinitiva(HttpServletRequest request, HttpServletResponse response){
        String idSolicitudString = request.getParameter("idSolicitud");
        String idAgremiadoString = request.getParameter("idAgremiado");
        String sttsString = request.getParameter("stts");
        String motivo = request.getParameter("motivo");
        LOGGER.info(LOGGER_PREFIX+"Se recibe la petición para cancelar la solicitud de baja "+idSolicitudString+" del colaborador "+idAgremiadoString);
        
        Map<String,Object> map = new HashMap<>();        
        
        short status = 0;
        try {
           status = Short.parseShort(sttsString);
           int idSolicitud = Integer.parseInt(idSolicitudString);
           int idAgremiado = Integer.parseInt(idAgremiadoString);
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            SolicitudBaja ultimaSolicitudBaja = getUltimaSolicitudBaja(agremiado);
            if(ultimaSolicitudBaja.getIdSolicitudBaja() == idSolicitud){
               List<DocumentosBaja> documentosBaja = solicitudBajaService.getDocumentosBaja(ultimaSolicitudBaja);
               documentosBaja.stream().map((db) -> {
                   db.setCargaGuardadaPra(Boolean.FALSE);
                   return db;
               }).map((db) -> {
                   db.setCargaGuardadaUsu(Boolean.FALSE);
                   return db;
               }).map((db) -> {
                   if(db.getUrlDocumentoCargarPra()!=null){
                       ftpService.borrarArchivoFTP(db.getUrlDocumentoCargarPra());
                       db.setUrlDocumentoCargarPra(null);
                   }
                   return db;
               }).filter((db) -> (db.getUrlDocumentoCargarUsu()!=null)).map((db) -> {
                   ftpService.borrarArchivoFTP(db.getUrlDocumentoCargarUsu());
                   return db;
               }).forEachOrdered((db) -> {
                   db.setUrlDocumentoCargarUsu(null);
               });
               ultimaSolicitudBaja.setObservaciones("Se regresa al colaborador a status de Activo.");
               ultimaSolicitudBaja.setMotivoDeRechazo(motivo);
               agremiadoService.setSolicitudBaja(ultimaSolicitudBaja);
               short statusActivo = getStatus("Activo");
               agremiado.setStatusAgremiado(statusActivo);
               agremiado.setModificado(new Date());
               agremiado.setObservaciones(getObservaciones("", "Se regresa al colaborador a status de Activo."));
               agremiadoService.setAgremiado(agremiado);
               
               LOGGER.info(LOGGER_PREFIX+"Se devuelve al colaborador a su status de Activo "+idSolicitudString+" del colaborador "+idAgremiadoString+"."); 
               DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                setBitacora("Cambio de status", "Devolvió al colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+" al status de activo.", request);
                setInformacionEnVentana(map, 0, MODULO, "Devolvió al colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"</b> al status de <b>activo</b> correctamente.");
                notificacionesService.solicitudDeBajaRechazada(agremiado);
            }else{
                throw new Exception("El id de la última solicitud de baja y el id recibido no corresponden.");
            }           
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar rechazar la solicitud de baja "+idSolicitudString+" del agremiado "+idAgremiadoString+"  --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "No fue posible rechazar definitivamente la solicitud de baja.");
       }
        StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(status);
        switch(statusAgremiado.getStatus()){
                    case "Baja Solicitada":                        
                        List<Agremiado> BajasSolicitadas = getAgremiadosByStatus("Baja Solicitada");
                        Map<Integer,SolicitudBaja> ultimaBajasSolicitada = getUltimaBaja(BajasSolicitadas);
                        map.put("Colaboradores", BajasSolicitadas);
                        map.put("ultimaBajasSolicitada", ultimaBajasSolicitada);
                        return new ModelAndView("colaborador/bajasSolicitadas", "model", map);
                    case "Baja Pendiente":                        
                        List<Agremiado> BajasPendientes = getAgremiadosByStatus("Baja Pendiente");
                        Map<Integer,SolicitudBaja> ultimaBajasPendientes = getUltimaBaja(BajasPendientes);
                        map.put("Colaboradores", BajasPendientes);
                        map.put("ultimaBajasSolicitada", ultimaBajasPendientes);
                        return new ModelAndView("colaborador/bajasPendientes", "model", map);
                    case "Baja Por Finalizar":  
                    default:
                        List<Agremiado> BajasPorFinalizar = getAgremiadosByStatus("Baja Por Finalizar");
                        Map<Integer,SolicitudBaja> ultimaBajasPorFinalizar = getUltimaBaja(BajasPorFinalizar);
                        map.put("Colaboradores", BajasPorFinalizar);
                        map.put("ultimaBajasSolicitada", ultimaBajasPorFinalizar);
                        return new ModelAndView("colaborador/bajasPorFinalizar", "model", map);
                }
        
   }
   
   /**
    * Controller encargado de presentar la vista para crear la solicitud de renovación de un contrato
    * @param idAgremiado Numero entero que contiene el id de un colaborador
    * @param request Variable encargada de manejar las peticiones
    * @param response Variable encargada de manejar las repuestas
    * @return ModelAndView
    */
  @RequestMapping(value = "colaborador/crear-solicitud-renovacion-contrato/{idAgremiado}.htm")
  public ModelAndView getFormSolicitudDeRenovacion(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
      LOGGER.info(LOGGER_PREFIX+"Se solicita solicitud de renovación de {"+idAgremiado+"}.");
      
      Map<String, Object> map = new HashMap<>();
      
      try {
          Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            
            if(agremiado.getDatosLaborales().getNombreContrato()==null||agremiado.getDatosLaborales().getNombreContrato().isEmpty()){
                map = new HashMap<>();
                map.put("agremiado", agremiado);      
                Integer idContratista = agremiado.getDatosLaborales().getContratistaObj().getIdContratista();
                List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(agremiado.getDatosLaborales().getClienteObj().getIdCliente());   
                 List<DelContrato> contratosDelClienteTemp = new ArrayList<>(contratosDelCliente.size());
                 contratosDelCliente.stream().filter((delContrato) -> (delContrato.getIdContratista() == idContratista)).forEachOrdered((delContrato) -> {
                     contratosDelClienteTemp.add(delContrato);
                });
                map.put("contratos", contratosDelClienteTemp);
                map.put("esquemas", agremiadoService.getEsquemaDePagos()); 
                map.put("viewResponse", "renovacion");  
                return new ModelAndView("colaborador/delClienteYDelContratoOlders", "model", map);
            }
            
          DatosPersonales datosPersonales = agremiado.getDatosPersonales();
          DatosLaborales datosLaborales = agremiado.getDatosLaborales();
          short status = getStatus("Activo");
          if((status == agremiado.getStatusAgremiado())&&(datosLaborales.getFechaFinContrato()!=null)){
                map.put("agremiado", agremiado);
                map.put("datosPersonales", datosPersonales);
                map.put("datosLaborales", datosLaborales);          
                  map.put("tiposDeContrato", agremiadoService.getTipoContrato());
                  map.put("contratoEmpresasId", agremiadoService.getContratoEmpresasByIdName(datosLaborales.getNombreContrato()).getIdContratoEmpresas());
                setCamposDispoiblesAndContratoEsquema(map, datosLaborales.getEsquemaPago(), datosLaborales.getNombreContrato());
              return new ModelAndView("colaborador/solicitudDeRenovacion", "model", map);
          }else{
              setInformacionEnVentana(map, 2, MODULO, "el colaborador no posee las caracteristicas necesarias para que se le realizace un renovación de contrato.");
                map.put("Colaboradores", getColaboradoresPorVencer());
                return new ModelAndView("colaborador/contratosPorVencer", "model", map);
          }
      } catch (Exception e) {
          LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar generar la vista para un solicitud de renovación de contrato -- > "+e.getMessage());
          setInformacionEnVentana(map, 1, MODULO, "No fue posible generar la vista para la solicitud de renovación");
      }
        map.put("Colaboradores", getColaboradoresPorVencer());
        return new ModelAndView("colaborador/contratosPorVencer", "model", map);
  }
  
  /**
   * Controlador encargado de procesar la solicitud de renovación de un colaborador
   * @param request Variable encargada de manejar las peticiones
   * @param response Variable encargada de manejar las respuestas
   * @return ModelAndView
   */
  @RequestMapping(value = "colaborador/recibir-solicitud-renovacion-contrato.htm",method = RequestMethod.POST)
  public ModelAndView solicitudRenovacionContrato(HttpServletRequest request, HttpServletResponse response){
      LOGGER.info(LOGGER_PREFIX+"Se reciben los datos de una solicitud de renovación");
      Map<String,Object> map = new HashMap<>();
      
      try {
          String idAgremiadoString = request.getParameter("idagremiado");
          String fechaInicioString = request.getParameter("fechaAlta");
          String tipoContratoString = request.getParameter("tpDCntrt");
          String aplicaModSalarial =  request.getParameter("newSalary");
          
          int idAgremiado =  Integer.parseInt(idAgremiadoString);
          Date fechaInicio = procesayyyyMMdd(fechaInicioString);
          short tipoContratoShort = Short.parseShort(tipoContratoString);
          Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
          TipoContrato tipoDeContrato = agremiadoService.getTipoContrato(tipoContratoShort);
          DatosLaborales  laborales = agremiado.getDatosLaborales();
          SolicitudRenovacionContrato renovacionContrato = new SolicitudRenovacionContrato();
          renovacionContrato.setAgremiadoObj(agremiado);
          renovacionContrato.setClienteObj(laborales.getClienteObj());
          renovacionContrato.setSindicatoObj(laborales.getSindicatoObj());
          renovacionContrato.setTipoContratoObj(tipoDeContrato);
          renovacionContrato.setFechaInicial(fechaInicio);        
           renovacionContrato.setModificacionSalario(Boolean.parseBoolean(aplicaModSalarial));
          if(tipoDeContrato.getDescripcion().equalsIgnoreCase("Indeterminado")){
              renovacionContrato.setPeriodoContrato(null);
              renovacionContrato.setFechaFinal(null);
          }else{
            String fechaTerminoString = request.getParameter("fechaTermino");
            String tiempoContratoString = request.getParameter("tiempoContrato");
            Date fechaTermino = procesayyyyMMdd(fechaTerminoString);
            renovacionContrato.setPeriodoContrato(tiempoContratoString);
            renovacionContrato.setFechaFinal(fechaTermino);
          }
          if(renovacionContrato.getModificacionSalario()){
              String percepcionBasadaEnSUPString = request.getParameter("percepcionBasadaEnSUP");
              if(Boolean.parseBoolean(percepcionBasadaEnSUPString)){
                  SalarioUnicoProfesional  salarioUnicoProfesional= agremiadoService.getSalarioUnicoProfesional(new Integer(request.getParameter("supSelect")));
                    renovacionContrato.setSUPObj(salarioUnicoProfesional);
                    renovacionContrato.setSalariosImss(null);
                    renovacionContrato.setSalarioDiario(null);
                    renovacionContrato.setSalarioDiarioIntegrado(null);    
                    renovacionContrato.setSueldoMensual(null);            
              }else{
                    renovacionContrato.setSUPObj(null);
                    renovacionContrato.setSalariosImss(request.getParameter("salariosImss"));
                    renovacionContrato.setSalarioDiario(request.getParameter("salarioDiario"));
                    renovacionContrato.setSalarioDiarioIntegrado(request.getParameter("salarioDiarioIntegrado"));    
                    renovacionContrato.setSueldoMensual(request.getParameter("sueldoMensual"));              
              }
          }else{
              renovacionContrato.setSUPObj(null);
              renovacionContrato.setSalariosImss(null);
              renovacionContrato.setSalarioDiario(null);
              renovacionContrato.setSalarioDiarioIntegrado(null);    
              renovacionContrato.setSueldoMensual(null);
          }
          renovacionContrato.setFechaAplicacion(null);
          renovacionContrato.setMotivoRechazo(null);
          renovacionContrato.setUrlContratoRenovado(null);
          renovacionContrato.setPeriodo(null);
          renovacionContrato.setFechaDeSolicitud(new Date());
          
          agremiadoService.setSolicitudRenovacionContrato(renovacionContrato);
          LOGGER.warn(">>>>>>>>>>>>>>>>>>>>>>>>>"+renovacionContrato.getIdSolicitudRenovacion());
          if(renovacionContrato.getIdSolicitudRenovacion()>0){
              short status = getStatus("Renovación Solicitada");
              agremiado.setStatusAgremiado(status);
              agremiado.setModificado(new Date());
              agremiadoService.setAgremiado(agremiado);
              DatosPersonales datosPersonales = agremiado.getDatosPersonales();
              setInformacionEnVentana(map, 0, MODULO, "La solicitud de renovación del colaborador fue enviada con exito.");
              setBitacora("Renovación de contrato", "Envió la solicitud de renovación del colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+".", request);
              notificacionesService.solicitudDeRenovacion(agremiado, renovacionContrato.getFechaInicial());
          }else{
              setInformacionEnVentana(map, 1, MODULO, "La solicitud de renovación <b>no</b> fue enviada.");
          }
      } catch (Exception e) {
          LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar crear una nueva solcitud de renovación del colaborador --> "+e.getMessage());
          setInformacionEnVentana(map, 1, MODULO, "No fue posible crear la solicitud de renovación");
      }
      
        map.put("Colaboradores", getColaboradoresPorVencer());
        return new ModelAndView("colaborador/contratosPorVencer", "model", map);
  }
  
  /**
   * Controlador encargado mostrar en distintos estados una solicitud de renovación de contrato
   * @param tipoDeVista Cadena de texto que contiene el nombre de la vista a la que se desea acceder
   * @param idAgremiado Numero entero que contiene el id de un colaborador
   * @param request Variable que maneja las peticiones
   * @param response Variable que maneja las respuestas
   * @return ModelAndView
   */
 @RequestMapping(value = "colaborador/{tipoDeVista}/solicitud-de-renovacion/{idAgremiado}.htm",method = RequestMethod.GET)
 public ModelAndView getSolicitudDeRenovacion(@PathVariable("tipoDeVista")String tipoDeVista, @PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
     LOGGER.info(LOGGER_PREFIX+"Se solicita la vista de una solicitud de renovación {"+idAgremiado+"}.");
     
     Map<String,Object> map = new HashMap<>();
     
     try {
         Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
         SolicitudRenovacionContrato ultimaSolicitudRenovacion = getUltimaSolicitudRenovacion(agremiado);
         byte cRespuesta;
         switch(tipoDeVista){
             case "aprobacion":
                 cRespuesta = 1;
                 break;
             case "rechazo":
                 cRespuesta = 2;
                 break;
            default:                
            case "kardex":
                 cRespuesta = 0;
                 break;
         }
         
         map.put("colaborador", agremiado);
         map.put("datosPersonales", agremiado.getDatosPersonales());
         map.put("ultimaSolicitudRenovacion", ultimaSolicitudRenovacion);
         map.put("cRespuesta", cRespuesta);
    
        getDatosAgremiado(map, idAgremiado); // se obtienen los datos del agremiado
            DatosLaborales getDatosLaborales = (DatosLaborales)map.get("datosLaborales"); //se toma la infomación de datosLaborales de la vista
            if(getDatosLaborales!=null){ // si los datosLaborales sin diferente de null
                //Si ya no es infromación heredada  y ya cuenta con su nombre de contrato
                if(getDatosLaborales.getNombreContrato()!=null&&!getDatosLaborales.getNombreContrato().isEmpty()){
                    // se envian los datos de esquema de pago y de nombre contrato
                    setCamposDispoiblesAndContratoEsquema(map, getDatosLaborales.getEsquemaPago(), getDatosLaborales.getNombreContrato());
                }
            }
            return new ModelAndView("colaborador/solicitudDeRenovacionKardex","model",map); // Devuelve la vista de renovación kardex
         
     } catch (Exception e) {
         LOGGER.error("Ocurrio un problema para generar la vista "+tipoDeVista+" para una solicitud de baja {"+idAgremiado+"}  --> "+e.getMessage());
         setInformacionEnVentana(map, 1, MODULO, "No su pudo generar la vista de la solicitud de baja.");
     }     
    List<Agremiado> renovaciónContratoPradetti = getAgremiadosByStatus("Renovación Solicitada");
    Map<Integer, SolicitudRenovacionContrato> ultimasRenovaciones = getUltimaRenovacion(renovaciónContratoPradetti);
    map.put("Colaboradores", renovaciónContratoPradetti);
    map.put("ultimaRenovacion", ultimasRenovaciones);
    return new ModelAndView("colaborador/renovacionesSolicitadas", "model", map);
 }
  
 /**
  * Controlador encargado de aprobar una solicitud de renovacion
  * @param request Variable que maneja las peticiones
  * @param response Variable que maneja las respuestas
  * @return ModelAndView
  */
 @RequestMapping(value = "colaborador/aprobacion-de-solicitud.htm",method = RequestMethod.POST)
 public ModelAndView setAprovacionDeRenovacion(HttpServletRequest request, HttpServletResponse response){
     LOGGER.info(LOGGER_PREFIX+"Se recibe una aprobacion de solicitud de renovación");
     
     Map<String,Object> map = new HashMap<>();
     
     try {
         String idAgremiadoString = request.getParameter("idAgremiado");
         String idSolicitudRenovacionString = request.getParameter("idSolicitudRenovacion");
         
         int idAgremiado = Integer.parseInt(idAgremiadoString);
         int idSolicitudRenovacion = Integer.parseInt(idSolicitudRenovacionString);
         
         Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
         SolicitudRenovacionContrato ultimaSolicitudRenovacion = getUltimaSolicitudRenovacion(agremiado);
         short status = getStatus("Renovación Solicitada");         
         
         if((idSolicitudRenovacion == ultimaSolicitudRenovacion.getIdSolicitudRenovacion())&&(status==agremiado.getStatusAgremiado())){
             
             short statusNew = getStatus("Solicitud De Renovación");
             agremiado.setStatusAgremiado(statusNew);
             agremiado.setModificado( new Date());
             
             agremiadoService.setAgremiado(agremiado);
             DatosPersonales datosPersonales = agremiado.getDatosPersonales();
             setInformacionEnVentana(map, 0, MODULO, "Se aprobó con exito la solicitud de renovación del colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"<b>.");
             setBitacora("Aprovación de solicitud de renovación", "Aprobó la solicitud de renovación de "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+".", request);
             notificacionesService.solicitudDeRenovacionAceptada(agremiado);
         
         }else{
             LOGGER.error(LOGGER_PREFIX+"No se puede aprobar la solicitud de renovación ya que el colaborador no posee las caracteristicas requeridas "+(idSolicitudRenovacion == ultimaSolicitudRenovacion.getIdSolicitudRenovacion())+" y "+(status==agremiado.getStatusAgremiado())+".");
             setInformacionEnVentana(map, 1, MODULO, "No se puede aprobar la solicitud de renovación ya que el colaborador no posee las caracteristicas requeridas.");
         }
         
     } catch (Exception e) {
         LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar aprobar una solicitud de renovación");
         setInformacionEnVentana(map, 1, MODULO, "No fue posible llevar a cabo la aprobación");
     } 
    List<Agremiado> renovaciónContratoPradetti = getAgremiadosByStatus("Renovación Solicitada");
    Map<Integer, SolicitudRenovacionContrato> ultimasRenovaciones = getUltimaRenovacion(renovaciónContratoPradetti);
    map.put("Colaboradores", renovaciónContratoPradetti);
    map.put("ultimaRenovacion", ultimasRenovaciones);
    return new ModelAndView("colaborador/renovacionesSolicitadas", "model", map);
 }
 
 /**
  * Controlador encargado de rechazar la solicitud de renovación de un contrato
  * @param request Variable que maneja las peticiones
  * @param response Variable que maneja las respuestas
  * @return ModelAndView
  */
 @RequestMapping(value = "colaborador/rechazo-de-solicitud.htm",method = RequestMethod.POST)
 public ModelAndView setRechazoDesolicitudDeRenovacion(HttpServletRequest request, HttpServletResponse response){
      LOGGER.info(LOGGER_PREFIX+"Se recibe un rechazo a una solicitud de renovación");
     
     Map<String,Object> map = new HashMap<>();
     
     try {
         String idAgremiadoString = request.getParameter("idAgremiado");
         String idSolicitudRenovacionString = request.getParameter("idSolicitudRenovacion");
         String motivoDeRechazo = request.getParameter("motivoDeRechazo");
         
         int idAgremiado = Integer.parseInt(idAgremiadoString);
         int idSolicitudRenovacion = Integer.parseInt(idSolicitudRenovacionString);
         
         Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
         SolicitudRenovacionContrato ultimaSolicitudRenovacion = getUltimaSolicitudRenovacion(agremiado);
         
         short status = getStatus("Renovación Solicitada");         
         
         if((idSolicitudRenovacion == ultimaSolicitudRenovacion.getIdSolicitudRenovacion())&&(status==agremiado.getStatusAgremiado())){
             
             ultimaSolicitudRenovacion.setMotivoRechazo(motivoDeRechazo);
             agremiadoService.setSolicitudRenovacionContrato(ultimaSolicitudRenovacion);
             
             short statusNew = getStatus("Activo");
             agremiado.setStatusAgremiado(statusNew);
             agremiado.setModificado( new Date());
             
             agremiadoService.setAgremiado(agremiado);
             DatosPersonales datosPersonales = agremiado.getDatosPersonales();
             setInformacionEnVentana(map, 2, MODULO, "Se rechazó  la solicitud de renovación del colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"<b>.");
             setBitacora("Rechazo de solicitud de renovación", "Rechazó la solicitud de renovación de "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+".", request);
             notificacionesService.solicitudDeRenovacionRechazada(agremiado,motivoDeRechazo);
         
         }else{
             LOGGER.error(LOGGER_PREFIX+"No se puede rechazar la solicitud de renovación ya que el colaborador no posee las caracteristicas requeridas "+(idSolicitudRenovacion == ultimaSolicitudRenovacion.getIdSolicitudRenovacion())+" y "+(status==agremiado.getStatusAgremiado())+".");
             setInformacionEnVentana(map, 1, MODULO, "No se puede rechazar la solicitud de renovación ya que el colaborador no posee las caracteristicas requeridas.");
         }
         
     } catch (Exception e) {
         LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar rechazar una solicitud de renovación");
         setInformacionEnVentana(map, 1, MODULO, "No fue posible llevar a cabo el rechazo de la solicitud de renovación");
     } 
    List<Agremiado> renovaciónContratoPradetti = getAgremiadosByStatus("Renovación Solicitada");
    Map<Integer, SolicitudRenovacionContrato> ultimasRenovaciones = getUltimaRenovacion(renovaciónContratoPradetti);
    map.put("Colaboradores", renovaciónContratoPradetti);
    map.put("ultimaRenovacion", ultimasRenovaciones);
    return new ModelAndView("colaborador/renovacionesSolicitadas", "model", map);
 }
 
 /**
  * Controlador encargado de mostrar la vista para generar un nuevo contrato
  * @param idAgremiado Numero entero que cotiene el id de un colaborador
  * @param request Variable que maneja las peticiones
  * @param response Variable que maneja las respuestas
  * @return ModelAndView
  */
 @RequestMapping(value = "colaborador/crear-contrato-renovacion/{idAgremiado}.htm",method = RequestMethod.GET)
 public ModelAndView getFormularioNuevoContrato(@PathVariable("idAgremiado")Integer idAgremiado,HttpServletRequest request, HttpServletResponse response){
     LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para crear un nuevo contrato de renovacion");
     
     Map<String,Object> map = new HashMap<>();
     
     try {
         
         Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
         SolicitudRenovacionContrato ultimaSolicitudRenovacion = getUltimaSolicitudRenovacion(agremiado);
         
         map.put("colaborador", agremiado);
         map.put("ultimaSolicitudRenovacion", ultimaSolicitudRenovacion);
         map.put("subirContrato", false);
         DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            Contratista contratistaObj = agremiado.getDatosLaborales().getContratistaObj();
            // se obtiene el nombre del contrato del colaborador de sus datos_laborales
            String nombreContrato = agremiado.getDatosLaborales().getNombreContrato();
            // Se obtiene un objeto de tipo ContratoEmpresas mediante el nombre contrato del colaborador
            ContratoEmpresas contratoEmpresas = agremiadoService.getContratoEmpresasByIdName(nombreContrato);
            // Se obtiene una lista de tipo Contrato
            List<Contrato> contratosDelColaborador = contratoEmpresas.getContratosList();
            // Se declara una lista
            List<Contrato> contratos = new ArrayList<>(contratosDelColaborador.size());
            // Se recorre la lista obtenida de los contratosempresas y se agrega a la nueva lista declarada
            contratosDelColaborador.stream().filter((contrato) -> (contrato.getContratoDeColaborador())).forEachOrdered((contrato) -> {
                contratos.add(contrato);
            });
            
            map.put("agremiado", agremiado);
            map.put("datosPersonales", datosPersonales);
            map.put("contratos", contratos);
            map.put("contratista", contratistaObj);
            map.put("fechaAplicación", procesayyyyMMdd(ultimaSolicitudRenovacion.getFechaAplicacion()));
            
         
        return new ModelAndView("colaborador/crearContratoRenovado", "model", map);
        
     } catch (Exception e) {
         LOGGER.warn(LOGGER_PREFIX+"Ocurrio un problema al intentar generar la vista para crear un contrato de renovación --> "+e.getMessage());
         setInformacionEnVentana(map, 1, MODULO, "No fue posible generar la vista para crear un contrato de renovación");
     }
        List<Agremiado> renovaciónContratoCliente = getAgremiadosByStatus("Solicitud De Renovación");
        Map<Integer, SolicitudRenovacionContrato> ultimasRenovacionesCliente = getUltimaRenovacion(renovaciónContratoCliente);
        map.put("Colaboradores", renovaciónContratoCliente);
        map.put("ultimaRenovacion", ultimasRenovacionesCliente);
        return new ModelAndView("colaborador/solicitudesDeRenovacion", "model", map);
 }
 
 /**
  *  Controlador encargado de generar el contrato en PDF con la información enviada mediante el formulario
  * @param request Variable que maneja las peticiones
  * @param response  Variable que maneja las respuestas
  */
 @RequestMapping(value = "colaborador/ver-contrato-de-renovacion.htm",method = RequestMethod.POST)
 public void getContratoDeRenovacion(HttpServletRequest request, HttpServletResponse response){
     LOGGER.info(LOGGER_PREFIX+"Se solicita la generación de un contrato {PDF} de renovación");
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
            
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            DatosPersonales datosPersonales = agremiado.getDatosPersonales();
            Contratista contratista = contratistaService.getContratista(idContratista);
            Contrato contrato = contratoService.getContrato(idContrato);
           SolicitudRenovacionContrato ultimaSolicitudRenovacion = getUltimaSolicitudRenovacion(agremiado);
            ByteArrayOutputStream nuevoContratoClienteColaborador = contratosService.getNuevoContratoContratistaColaborador(ultimaSolicitudRenovacion, contratista, agremiado, contrato, nombreTUno,ocupacionTUno, origenTUno, nombreTDos,ocupacionTDos,origenTDos);
 
            // setting some response headers
            response.setHeader("Content-Disposition", "inline; filename=contrato_renovacion_cliente_colaborador"+"__"+datosPersonales.getApellidoPaterno().toUpperCase()+"_"+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno().toUpperCase())+"_"+datosPersonales.getNombre().toUpperCase() +".pdf");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(nuevoContratoClienteColaborador.size());
            try ( // write ByteArrayOutputStream to the ServletOutputStream
                OutputStream os = response.getOutputStream()) { 
                nuevoContratoClienteColaborador.writeTo(os);
                os.flush();
            }
            
        } catch (NumberFormatException | IOException me) {
            LOGGER.error("[Controller, Colaborador] Ocurrio un error durante la creación de un contrato de renovación {PDF} entre un cliente y un colaborador --> "+me.getMessage());
        }catch (Exception e) {
            LOGGER.error("[Controller, Colaborador] Ocurrio un error durante la creación de un contrato de renovación {PDF} entre un cliente y un colaborador --> "+e.getMessage());
        }
 }
 
 /**
  * Controlador encargado de mostrar la vista para la carga del nuevo contrato de renovacion
  * @param idAgremiado Numero entero que contiene el id de un colaborador
  * @param request Variable que maneja las peticiones
  * @param response Variable que manejas las respuestas
  * @return ModelAndView
  */
 @RequestMapping(value = "colaborador/subir-contrato-renovacion/{idAgremiado}.htm",method = RequestMethod.GET)
 public ModelAndView getFormularioSubirContrato(@PathVariable("idAgremiado")Integer idAgremiado,HttpServletRequest request, HttpServletResponse response){
     LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para crear un nuevo contrato de renovacion");
     
     Map<String,Object> map = new HashMap<>();
     
     try {
         
         Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
         SolicitudRenovacionContrato ultimaSolicitudRenovacion = getUltimaSolicitudRenovacion(agremiado);
         
         map.put("colaborador", agremiado);
         map.put("ultimaSolicitudRenovacion", ultimaSolicitudRenovacion);
         map.put("subirContrato", true);
         DatosPersonales datosPersonales = agremiado.getDatosPersonales();
        Contratista contratistaObj = agremiado.getDatosLaborales().getContratistaObj();
         Cliente clienteObj = agremiado.getDatosLaborales().getClienteObj();
        map.put("datosPersonales", datosPersonales);
        map.put("contratista", contratistaObj);
        map.put("cliente", clienteObj);
            
         
        return new ModelAndView("colaborador/crearContratoRenovado", "model", map);
        
     } catch (Exception e) {
         LOGGER.warn(LOGGER_PREFIX+"Ocurrio un problema al intentar generar la vista para subir un contrato de renovación --> "+e.getMessage());
         setInformacionEnVentana(map, 1, MODULO, "No fue posible generar la vista para subir un contrato de renovación");
     }
        List<Agremiado> renovaciónContratoCliente = getAgremiadosByStatus("Solicitud De Renovación");
        Map<Integer, SolicitudRenovacionContrato> ultimasRenovacionesCliente = getUltimaRenovacion(renovaciónContratoCliente);
        map.put("Colaboradores", renovaciónContratoCliente);
        map.put("ultimaRenovacion", ultimasRenovacionesCliente);
        return new ModelAndView("colaborador/solicitudesDeRenovacion", "model", map);
 }
 
 /**
  * Controlador encargado de almacenar el contrato de renovacion
  * @param archivo Archivo pdf
  * @param request Variable que maneja las peticiones
  * @param response Variable que maneja las respuestas
  * @return ModelAndView
  */
 @RequestMapping(value = "colaborador/contrato-de-renovacion.htm", method = RequestMethod.POST)
 public ModelAndView setContratoDeRenovacion(@RequestParam("file") MultipartFile archivo,HttpServletRequest request, HttpServletResponse response){
        String idAgremiadoString = request.getParameter("idAgremiado");
        String ultimaSolicitudString = request.getParameter("ultimaSolicitud");
        String idClienteString = request.getParameter("idCliente");
        
        LOGGER.info(LOGGER_PREFIX+"Se recibe el contrato de renovación para el colaborador {"+idAgremiadoString+"}.");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            int idAgremiado = Integer.parseInt(idAgremiadoString);
            int idUltimaSRecibida = Integer.parseInt(ultimaSolicitudString);
            int idCliente = Integer.parseInt(idClienteString);
            
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            SolicitudRenovacionContrato ultimaSolicitudRenovacion = getUltimaSolicitudRenovacion(agremiado);
            Cliente cliente = clienteService.getCliente(idCliente);
            
            if((ultimaSolicitudRenovacion.getIdSolicitudRenovacion()==idUltimaSRecibida)&&(ultimaSolicitudRenovacion.getClienteObj().equals(cliente))&&agremiado.getIdAgremiado()==ultimaSolicitudRenovacion.getAgremiadoObj().getIdAgremiado()){
                    if(!archivo.isEmpty()){
                    String urlContratoDeRenovacion = ftpService.guardarArchivoFTP(archivo, "Renovacion", cliente.getIdCliente().toString(), agremiado.getIdAgremiado().toString());
                    
                    DatosLaborales datosLaborales = agremiado.getDatosLaborales();
                    
                    datosLaborales.setTipoContratoObj(ultimaSolicitudRenovacion.getTipoContratoObj());
                    // Se cambia el setteo de tiempo contrato a periodo contrato de la tabla datos laborales
                    datosLaborales.setTiempoContrato(ultimaSolicitudRenovacion.getPeriodoContrato());
                   /* datosLaborales.setSalariosImss(ultimaSolicitudRenovacion.getSalariosImss());
                    datosLaborales.setSalarioDiario(ultimaSolicitudRenovacion.getSalarioDiario());
                    datosLaborales.setSalarioDiarioIntegrado(ultimaSolicitudRenovacion.getSalarioDiarioIntegrado());
                    datosLaborales.setSueldoMensual(ultimaSolicitudRenovacion.getSueldoMensual()); 
                    */
                   // Se valida que la el salarioImss de la ultima renovación sea diferente a null para poder asignar el valor
                    if(ultimaSolicitudRenovacion.getSalariosImss()!=null){
                        datosLaborales.setSalariosImss(ultimaSolicitudRenovacion.getSalariosImss());
                    }
                    if(ultimaSolicitudRenovacion.getSalarioDiario()!=null){
                        datosLaborales.setSalarioDiario(ultimaSolicitudRenovacion.getSalarioDiario());
                    }
                    if(ultimaSolicitudRenovacion.getSalarioDiarioIntegrado()!=null){
                        datosLaborales.setSalarioDiario(ultimaSolicitudRenovacion.getSalarioDiarioIntegrado());
                    }
                    if(ultimaSolicitudRenovacion.getSueldoMensual()!=null){
                        datosLaborales.setSueldoMensual(ultimaSolicitudRenovacion.getSueldoMensual());
                    }
                    // Se omite asignar a la tabla datos laborales debido a que este campo hará referencia a la fecha de ingresó del colaborador
                   // datosLaborales.setFechaInicioContrato(ultimaSolicitudRenovacion.getFechaInicial());
                    datosLaborales.setFechaFinContrato(ultimaSolicitudRenovacion.getFechaFinal());
                    datosLaborales.setPercepcionBasadaEnSUP(ultimaSolicitudRenovacion.getSUPObj()!=null);
                    datosLaborales.setSalarioUnicoProfesional(ultimaSolicitudRenovacion.getSUPObj());
                    if(!ultimaSolicitudRenovacion.getTipoContratoObj().getDescripcion().equals("Indeterminado")){
                           Calendar calendar = Calendar.getInstance();
                           calendar.setTime(ultimaSolicitudRenovacion.getFechaFinal());
                           calendar.add(Calendar.DATE, -7);
                           Date fechaTimbre = calendar.getTime();
                           datosLaborales.setFechaTimbreContrato(fechaTimbre);             
                      }
                    
                    Modulo modulo = getModulo("Renovacion contrato");
                    TipoDocumento tp = null;
                    List<TipoDocumento> tipoDocumentos = agremiadoService.getTipoDocumento(modulo);
                    for (TipoDocumento tipoDocumento : tipoDocumentos) {
                        if(tipoDocumento.getNombreDocumento().equalsIgnoreCase("Renovación contrato")){
                            tp = tipoDocumento;
                            break;
                        }
                    }
                    
                    DocumentoPK dpk = new DocumentoPK();
                    dpk.setAgremiado(agremiado.getIdAgremiado());
                    dpk.setTipoDocumento(tp.getIdTipoDocumento());
                    Documento documento = null;
                    
                    List<Documento> documentoList = agremiado.getDocumentoList();
                    for (Documento docInner : documentoList) {
                        if(Objects.equals(docInner.getTipoDocumentoObj().getIdTipoDocumento(), tp.getIdTipoDocumento())){
                            if(docInner.getUrlDocumento() != null){
                                ftpService.borrarArchivoFTP(docInner.getUrlDocumento());
                                docInner.setUrlDocumento(null);
                            }
                            docInner.setUrlDocumento(urlContratoDeRenovacion);
                            docInner.setNombreDocumento(tp.getNombreDocumento());
                            docInner.setModificado(new Date());
                            documento = docInner;
                            break;
                        }
                    }
                    
                    if(documento==null){
                        documento = new Documento();
                        documento.setAgremiadoObj(agremiado);
                        documento.setCreado(new Date());
                        documento.setDocumentoPK(dpk);
                        documento.setNombreDocumento(tp.getNombreDocumento());
                        documento.setTipoDocumentoObj(tp);
                        documento.setUrlDocumento(urlContratoDeRenovacion);
                    }              
                    
                    agremiadoService.setDocumento(documento);                  

                    agremiadoService.setDatosLaborales(datosLaborales);   
                    ultimaSolicitudRenovacion.setUrlContratoRenovado(urlContratoDeRenovacion);
                    ultimaSolicitudRenovacion.setFechaAplicacion(new Date());
                    agremiadoService.setSolicitudRenovacionContrato(ultimaSolicitudRenovacion);
                    
                    short status = getStatus("Activo");
                    agremiado.setModificado(new Date());
                    agremiado.setStatusAgremiado(status);
                    agremiadoService.setAgremiado(agremiado);
                    
                    LOGGER.info(LOGGER_PREFIX+"Se renovó el contrato del trabajador {"+idAgremiadoString+"} con la solicitud {"+ultimaSolicitudString+"}.");
                    DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                    setInformacionEnVentana(map, 0, MODULO, "Se renovó el contrato el contrato del colaborador <b>"+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno())+"<b>.");
                    setBitacora("Renovación de contrato", "Renovó el contrato del colaborador "+datosPersonales.getNombre()+" "+datosPersonales.getApellidoPaterno()+" "+((datosPersonales.getApellidoMaterno()==null)?"":datosPersonales.getApellidoMaterno()), request);
                }else{
                    LOGGER.error(LOGGER_PREFIX+"El archivo recibido para la renovación de contrato se encuentra vacío {"+idAgremiadoString+", "+ultimaSolicitudString+"}.");
                setInformacionEnVentana(map, 2, MODULO, "El archivo enviado no cuenta con las caracteristicas de la solicitud de renovacion.");
                }            
            }else{
                LOGGER.error(LOGGER_PREFIX+"El contrato recibido no cuenta con las caracteristicas esperadas con la ultima solicitud de renovación.");
                setInformacionEnVentana(map, 2, MODULO, "El contrato enviado no cuenta con las caracteristicas de la solicitud de renovacion.");
            }           
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Hubo un problema al momento de intentar subir el contrato de renovación --> "+e.getMessage());
               setInformacionEnVentana(map, 1, MODULO, "No fue posible terminar el proceso de subir el contrato de renovación.");
        }
        List<Agremiado> renovaciónContratoCliente = getAgremiadosByStatus("Solicitud De Renovación");
        Map<Integer, SolicitudRenovacionContrato> ultimasRenovacionesCliente = getUltimaRenovacion(renovaciónContratoCliente);
        map.put("Colaboradores", renovaciónContratoCliente);
        map.put("ultimaRenovacion", ultimasRenovacionesCliente);
        return new ModelAndView("colaborador/solicitudesDeRenovacion", "model", map);
 }
    
 /**
  * Controlador encargado de descartar un expediente que no se complento en el proceso de alta
  * @param idAgremiado Numero entero que contiene el ide de un colaborador
  * @param request Variable que maneja las peticiones
  * @param response Variables que maneja las respuestas
  * @return ModelAndView
  */
   @RequestMapping(value="colaborador/descartar-expediente/{idAgremiado}.htm",method = RequestMethod.POST)
   public ModelAndView descartarExpediente(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita el descarte de un expediente {"+idAgremiado+"}.");
       
       Map<String, Object> map = new HashMap<>();
        String statusAntiguoDelAgremiado = "";
       try {
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           short statusAnteriorDelAgremiado = agremiado.getStatusAgremiado();
           StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(statusAnteriorDelAgremiado);
           statusAntiguoDelAgremiado = statusAgremiado.getStatus();
           short status = getStatus("Expediente Descartado");
           agremiado.setModificado(new Date());
           agremiado.setObservaciones("Expediente Descartado desde sistema");
           agremiado.setStatusAgremiado(status);
           agremiadoService.setAgremiado(agremiado);
           setInformacionEnVentana(map, 0, MODULO, "El expediente se descarto exitosamente.");
           setBitacora("Expediente Descartado", "Descartó un expediente incompleto exitosamente.", request);
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de descartar un expediente --> "+e.getMessage());
           setInformacionEnVentana(map, 0, MODULO, "No fue posible descartar el expediente correctamente.");
       }
       switch(statusAntiguoDelAgremiado){
           case "Baja":
                List<Agremiado> Bajas = getAgremiadosQueCausaronBaja();
                        Map<Integer,SolicitudBaja> ultimaBajas = getUltimaBaja(Bajas);
                        map.put("Colaboradores", Bajas);
                        map.put("ultimaBajasSolicitada", ultimaBajas);
                        return new ModelAndView("colaborador/bajasFinalizadas", "model", map);
           case "Expediente Incompleto":
           case "":
           default:               
                map.put("Colaboradores", getAgremiadosByStatus("Expediente Incompleto"));
                return new ModelAndView("colaborador/expedientesPorCompletar", "model", map);
       }
   }
   
   /**
    * Controlador encargado de agregar observaciones al un colaborador en especifico
    * @param idAgremiado Numero entero que contiene el id de un agremiado
    * @param request Variable que maneja las peticiones
    * @param response Variable que maneja las respuestas
    * @return ModelAndView
    */
   @RequestMapping(value="colaborador/agregar-observaciones/{idAgremiado}.htm",method = RequestMethod.POST)
   public ModelAndView setObservacionesDelColaborador(@PathVariable("idAgremiado")Integer idAgremiado, HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se reciben comentarios del colaborador {"+idAgremiado+"}.");
       
       Map<String,Object> map = new HashMap<>();
       
       try {
           String observaciones = request.getParameter("observaciones");           
           
           Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
           agremiado.setObservaciones(getObservaciones(agremiado.getObservaciones(), observaciones+"."));
           agremiado.setModificado(new Date());
           agremiadoService.setAgremiado(agremiado);
            getDatosAgremiado(map, idAgremiado);
            return new ModelAndView("colaborador/kardex","model",map);
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de intentar mostrar el kardex del  colaborador {"+idAgremiado+"} despues de insertar observaciones --> "+e.getMessage());
        }
        throw new RuntimeException("Ocurrio un problema al momento de intentar generar el kardex de un colaborador despues de ingresar observaciones "+idAgremiado);
   }
   
   @RequestMapping(value="colaborador/registrar-colaborador.htm", method = RequestMethod.GET)
   public ModelAndView registroPorEsquema(HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para el registro de un nuevo colaborador por esquema");
       Map<String,Object> map = new HashMap<>();
       try {
           map.put("clientes", getClientesDelUsuario());
           map.put("esquemas", agremiadoService.getEsquemaDePagos());           
          return new ModelAndView("colaborador/delClienteYDelContrato","model",map);
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al momento de obtener la información necesaria para presentar la vista de registro de un colaborador --> "+e.getMessage(),e);
           setInformacionEnVentana(map, 1, MODULO, "No fue posible obtener la información necesaria para procesar la solicitud.");
       }       
    return new ModelAndView("index","model",map);
   }
       
    @RequestMapping(value="colaborador/contratos-cliente/{cliente}.htm", method = RequestMethod.POST)
    public @ResponseBody String getContratos(@PathVariable("cliente")int id,HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita los contratos del cliente ["+id+"]");
        try {            
            List<DelContrato> contratosDelCliente = agremiadoService.getContratosDelCliente(id);            
            if(contratosDelCliente == null){
                contratosDelCliente = new ArrayList<>();
            }
            return new ObjectMapper().writeValueAsString(contratosDelCliente);
        }catch (JsonProcessingException me) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición de los contratos del cliente --> "+me.getMessage());
       }catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición de los contratos del cliente -Exception- --> "+e.getMessage());
       }
        return "";
    }
    
    @RequestMapping(value="colaborador/sups-contrato/{contrato}.htm", method = RequestMethod.POST)
    public @ResponseBody String getSUPs(@PathVariable("contrato")int id,HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita los sups del contratos ["+id+"]");
        try {            
            List<SalarioUnicoProfesional> salarioUnicoProfesionals = agremiadoService.getSalariosDelContrato(id);
            if(salarioUnicoProfesionals == null){
                salarioUnicoProfesionals = new ArrayList<>();
            }
            return new ObjectMapper().writeValueAsString(salarioUnicoProfesionals);
        }catch (JsonProcessingException me) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición de sups del contrato --> "+me.getMessage());
       }catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición de sups del contrato -Exception- --> "+e.getMessage());
       }
        return "";
    }
    
    @RequestMapping(value="colaborador/cd-contrato/{contrato}.htm", method = RequestMethod.POST)
    public @ResponseBody String getCatalgoDocumental(@PathVariable("contrato") int id,HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita los cds del contratos ["+id+"]");
        try {            
            List<CatalogoDocumental> catalogoDocumentals = agremiadoService.getDocumentosDelContrato(id);
            if(catalogoDocumentals == null){
                catalogoDocumentals = new ArrayList<>();
            }
            return new ObjectMapper().writeValueAsString(catalogoDocumentals);
        }catch (JsonProcessingException me) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición de cds del contrato --> "+me.getMessage());
       }catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición de cds del contrato -Exception- --> "+e.getMessage());
       }
        return "";
    }
 
    /**
     * Controlador encargado de devolver la vista pra agregar los datos personales de un nuevo colaborador, una vez proporcionados los datos del paso 1
     * @param request
     * @return ModelAndView
     * @since 28/02/2018
     */
    @RequestMapping(value = "colaborador/registrar-colaborador.htm",method = RequestMethod.POST)
    public ModelAndView getNuevoColaborador(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se solicita la vista para agregar un nuevo colaborador");
        
        Map<String,Object> map = new HashMap<>();
        
        try {
            String contratoIdString = request.getParameter("contrato");
            String esquemaIdString = request.getParameter("esquema");
            String clienteIdString = request.getParameter("cliente");
            
            int contratoId = Integer.parseInt(contratoIdString);
            short esquemaId = Short.parseShort(esquemaIdString);
            int clienteId = Integer.parseInt(clienteIdString);
            
            ContratoEmpresas contratoEmpresasById = agremiadoService.getContratoEmpresasById(contratoId);
            EsquemaPago esquemaPago = agremiadoService.getEsquemaPago(esquemaId);     
            
            if(contratoEmpresasById != null && esquemaPago != null && (contratoEmpresasById.getClienteObj().equals(clienteId))){
                map.put("esquema", esquemaPago);
                map.put("contratoID", contratoId);
                map.put("contratoName", contratoEmpresasById.getNombreContrato());
                map.put("contratistaName", contratistaService.getContratista(contratoEmpresasById.getContratistaObj()).getNombreEmpresa());
                map.put("clienteName", clienteService.getCliente(contratoEmpresasById.getClienteObj()).getNombreEmpresa());                
                map.put("edicion", false);
                setCamposDispoiblesAndContratoEsquema(map, esquemaPago, contratoId);
                return new ModelAndView("colaborador/formularioColaborador", "model", map);
            }
             setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar generar la vista para registrar un nuevo colaborador<br>No fue posible obtener la información requerida para continuar con el proceso.");
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al intentar generar la vista para agregar un nuevo colaborador --> "+e.getMessage());
            setInformacionEnVentana(map, 1, MODULO, "Ocurrio un problema al intentar generar la vista para agregar un nuevo colaborador");
        }
            return new ModelAndView("index","model",map);
    }
    @RequestMapping(value = "colaborador/registrar-adecuacion-colaborador.htm",method = RequestMethod.POST)
    public ModelAndView setAdecuacionesDeColaborador(HttpServletRequest request, HttpServletResponse response){
        LOGGER.info(LOGGER_PREFIX+"Se reciben las adecuaciones de colaborador");
        String ida = request.getParameter("ida");
        String contrato = request.getParameter("contrato");
        String esquema = request.getParameter("esquema");
        String rspnsDeContrato = request.getParameter("rspns");
        Agremiado agremiado = agremiadoService.getAgremiado(new Integer(ida));
        ContratoEmpresas contratoEmpresasById = agremiadoService.getContratoEmpresasById(new Integer(contrato));
        DatosLaborales datosLaborales = agremiado.getDatosLaborales();
        datosLaborales.setNombreContrato(contratoEmpresasById.getNombreContrato());
        EsquemaPago esquemaPago = agremiadoService.getEsquemaPago(new Short(esquema));
        datosLaborales.setEsquemaPago(esquemaPago);
        agremiadoService.setDatosLaborales(datosLaborales);        
        agremiado.setModificado(new Date());
        agremiadoService.setAgremiado(agremiado);
        setBitacora("Actualización", "Adecuó la información de <b>"+agremiado.getDatosPersonales().getNombre()+" "+agremiado.getDatosPersonales().getApellidoPaterno()+" "+agremiado.getDatosPersonales().getApellidoMaterno()+"</b>.", request);
        Map<String,Object> map = new HashMap<>();
        switch(rspnsDeContrato){
            case "contrato":
                map.put("Colaboradores", getAgremiadosByStatus("Expediente Sin Contrato"));
                return new ModelAndView("colaborador/expedientesSinContrato", "model", map);
            case "imss":                
                map.put("Colaboradores", getAgremiadosByStatus("Alta En Proceso"));
                return new ModelAndView("colaborador/altasEnProceso", "model", map);
            case "form":
                getDatosAgremiado(map, agremiado.getIdAgremiado());
                map.put("edicion", true);                    
                map.put("estatus", agremiado.getStatusAgremiado());
                setCamposDispoiblesAndContratoEsquema(map, agremiado.getDatosLaborales().getEsquemaPago(), agremiado.getDatosLaborales().getNombreContrato());
                return new ModelAndView("colaborador/formularioColaborador", "model", map);
            case "kardex":                
                getDatosAgremiado(map, agremiado.getIdAgremiado());
                setCamposDispoiblesAndContratoEsquema(map, agremiado.getDatosLaborales().getEsquemaPago(), agremiado.getDatosLaborales().getNombreContrato());
                return new ModelAndView("colaborador/kardex","model",map);
            case "renovacion":
                DatosPersonales datosPersonales = agremiado.getDatosPersonales();
                datosLaborales = agremiado.getDatosLaborales();
                short status = getStatus("Activo");
                if((status == agremiado.getStatusAgremiado())&&(datosLaborales.getFechaFinContrato()!=null)){
                      map.put("agremiado", agremiado);
                      map.put("datosPersonales", datosPersonales);
                      map.put("datosLaborales", datosLaborales);          
                        map.put("tiposDeContrato", agremiadoService.getTipoContrato());
                        map.put("contratoEmpresasId", agremiadoService.getContratoEmpresasByIdName(datosLaborales.getNombreContrato()).getIdContratoEmpresas());
                    setCamposDispoiblesAndContratoEsquema(map, datosLaborales.getEsquemaPago(), datosLaborales.getNombreContrato());
                    return new ModelAndView("colaborador/solicitudDeRenovacion", "model", map);
                }else{
                    setInformacionEnVentana(map, 2, MODULO, "el colaborador no posee las caracteristicas necesarias para que se le realizace un renovación de contrato.");
                      map.put("Colaboradores", getColaboradoresPorVencer());
                      return new ModelAndView("colaborador/contratosPorVencer", "model", map);
                }
            default:      
                map.put("Colaboradores", getAgremiadosByStatus("Expediente Sin Contrato"));
                return new ModelAndView("colaborador/expedientesSinContrato", "model", map);
        }
    }
// ================================================================================== Private Methods ============================================================
// ================================================================================== Private Methods ============================================================
// ================================================================================== Private Methods ============================================================
// ================================================================================== Private Methods ============================================================   
// ================================================================================== Private Methods ============================================================
// ================================================================================== Private Methods ============================================================
// ================================================================================== Private Methods ============================================================
// ================================================================================== Private Methods ============================================================
    
   private void getDatosAgremiado(Map<String,Object> map, Integer idagremiado){
        if(map!=null&&idagremiado!=null){
            Agremiado agremiado = agremiadoService.getAgremiado(idagremiado);
            map.put("agremiado", agremiado);
            map.put("datosPersonales", agremiado.getDatosPersonales());
            map.put("fechaNacimiento", procesayyyyMMdd(agremiado.getDatosPersonales().getFechaNacimiento()));
            map.put("datosDomicilio", agremiado.getDatosDomicilio());
            map.put("datosBancarios", agremiado.getDatosBancarios());
            if(agremiado.getDatosLaborales().getNombreContrato()==null){
                DatosLaborales datosLaborales = datosLaboraralesByEsquemaService.getDatosLaborales(agremiado.getDatosLaborales());
                agremiadoService.setDatosLaborales(datosLaborales);
                agremiado.setModificado(new Date());
                agremiadoService.setAgremiado(agremiado);
                LOGGER.info(LOGGER_PREFIX+"Se adaptaron los datos laborales del colaborador {"+datosLaborales.getAgremiado()+"}.");
                map.put("datosLaborales", datosLaborales);
                agremiado.setDatosLaborales(datosLaborales);
            }else{
                map.put("datosLaborales", agremiado.getDatosLaborales());
            }
            
            map.put("datosBeneficiario", agremiado.getDatosBeneficiario());
            if(agremiado.getDatosBeneficiario()!=null){
                map.put("fechaNaBen", procesayyyyMMdd(agremiado.getDatosBeneficiario().getFechaNacimiento()));
            }
            map.put("datosBancarios", agremiado.getDatosBancarios());
            map.put("periodosDePago", agremiadoService.getTipoNomina());
            map.put("tiposDeContrato", agremiadoService.getTipoContrato());
            StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(agremiado.getStatusAgremiado());
            List<Documento> documentos = null;
            List<Documento> documentosConURL = null;
            
            switch(statusAgremiado.getStatus()){
//                case "":
//                    break;
                case "Expediente Incompleto":
                    map.put("datoFijo", false);
                    map.put("DocumentosRequeridos", getDocumentosPorSubirParaSolicitarUnAlta(agremiado.getDatosLaborales().getEsquemaPago(),agremiado.getDatosLaborales().getNombreContrato())); 
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Expediente Incompleto"));
                    documentosConURL = new ArrayList<>(documentos.size());
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }
                    map.put("documentosDelColaborador", documentosConURL);
                    break;
                case "V.OB.O":          
                    map.put("datoFijo", true);
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Expediente Incompleto"));
                   documentosConURL = new ArrayList<>(documentos.size());
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }
                    map.put("documentosDelColaborador", documentosConURL);
                    break;
                case "Expediente Sin Contrato":  
                    map.put("datoFijo", true);    
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Expediente Incompleto"));
                   documentosConURL = new ArrayList<>(documentos.size());    
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }      
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("De contrato"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }
                    map.put("documentosDelColaborador", documentosConURL);
                    break;
                case "Alta En Proceso":  
                    map.put("datoFijo", true);
                   documentosConURL = new ArrayList<>();        
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Expediente Incompleto"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }      
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("De contrato"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }
                    map.put("documentosDelColaborador", documentosConURL);
                    break;
                case "Activo":  
                    map.put("datoFijo", true);
                   documentosConURL = new ArrayList<>();        
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Expediente Incompleto"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }      
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("De contrato"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }      
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Alta En Proceso"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }     
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Renovacion contrato"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }
                    map.put("documentosDelColaborador", documentosConURL);
                    break;
                case "Baja Solicitada":  
                    map.put("datoFijo", true);
                   documentosConURL = new ArrayList<>();        
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Expediente Incompleto"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }      
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("De contrato"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }      
                    documentos = agremiadoService.getDocumentos(agremiado, getModulo("Alta En Proceso"));
                    for(Documento documento : documentos){
                             if(documento.getUrlDocumento()!=null){                                 
                                documentosConURL.add(documento);
                             }
                         }
                    map.put("documentosDelColaborador", documentosConURL);
                    break;
                default:
                    break;
            }
        }
    }   
   private List<TipoDocumento> getDocumentosPorSubirParaSolicitarUnAlta(EsquemaPago ep,String ce){
        Modulo modulo = getModulo("Expediente Incompleto");
        ContratoEmpresas contratoEmpresas = agremiadoService.getContratoEmpresasByIdName(ce);
        return agremiadoService.getTipoDocumentos(modulo, ep, contratoEmpresas);
    }
   private Modulo getModulo(String nombre){
        Modulo m;
        for (Modulo modulo : agremiadoService.getModulos()) {
            if(modulo.getNombre().equals(nombre)){
                return modulo;
            }
        }
        return null;
    }
   private List<Agremiado> getAgremiadosByStatus(String status){
        List<Agremiado> agremiados = new ArrayList<>();
        List<Cliente> clientes = getClientesDelUsuario();
        short statusShort = getStatus(status);
        StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(statusShort);
        clientes.stream().forEach((Cliente cliente) -> {
            agremiados.addAll(agremiadoService.getAgremiado(statusAgremiado.getStatus(), cliente.getIdCliente()));
        });
        return agremiados;
    }
   private Integer getAgremiadosByStatusCounter(String status){
        List<Integer> agremiados = new ArrayList<>();
        List<Cliente> clientes ;
        if(status.equalsIgnoreCase("activo")||status.equalsIgnoreCase("Expediente Descartado")){
            clientes = new ArrayList<>();
        }else if(status.equalsIgnoreCase("baja")){
            String correo = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = usuarioService.getUsuario(correo);
            clientes = usuario.getClienteList();
        }else{
            clientes = getClientesDelUsuario();
        }
        short statusShort = getStatus(status);
        
        StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(statusShort);
        
        clientes.stream().forEach((Cliente cliente) -> {
            agremiados.add(agremiadoService.getAgremiadosCount(statusAgremiado.getStatus(), cliente.getIdCliente()));
        });
        
        clientes = null;
        Integer total = 0;
        total = agremiados.stream().map((a) -> a).reduce(total, Integer::sum);
        Runtime.getRuntime().gc();
        return total;
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
   private List<Agremiado> getAgremiadosActivos(){
       List<Cliente> clientes =  new LinkedList<>();
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(correo);
        if(usuario!=null){
            clientes = usuario.getClienteList();
        }
        List<Cliente> clientesBack = new LinkedList<>();
        for (Cliente cliente : clientes) {
            if(cliente.getStatus()){
                clientesBack.add(cliente);
            }
        }
        clientes = null;        
        List<Agremiado> agremiados = new ArrayList<>();
        short statusShort = getStatus("Activo");
        StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(statusShort);
        clientesBack.stream().forEach((Cliente cliente) -> {
            agremiados.addAll(agremiadoService.getAgremiado(statusAgremiado.getStatus(), cliente.getIdCliente()));
        });     
        return agremiados;
   }
   private List<Agremiado> getAgremiadosQueCausaronBaja(){
       List<Cliente> clientes =  new LinkedList<>();
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(correo);
        if(usuario!=null){
            clientes = usuario.getClienteList();
        }
        List<Cliente> clientesBack = new LinkedList<>();
        for (Cliente cliente : clientes) {
            if(cliente.getStatus()){
                clientesBack.add(cliente);
            }
        }
        clientes = null;        
        List<Agremiado> agremiados = new ArrayList<>();
        short statusShort = getStatus("Baja");
        StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(statusShort);
        clientesBack.stream().forEach((Cliente cliente) -> {
            agremiados.addAll(agremiadoService.getAgremiado(statusAgremiado.getStatus(), cliente.getIdCliente()));
        });
        return agremiados;
   }
   private void setBitacora(String movimiento, String detalles,HttpServletRequest request){
       String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuario(correo);
        if(usuario!=null){
            Bitacora bitacora = new Bitacora(new Date(), usuario.getIdUsuario());
            bitacora.setModulo(MODULO);
            bitacora.setMovimiento(movimiento);
            bitacora.setDetalles(detalles);
            bitacora.setIpAcceso(request.getRemoteAddr());
            bitacoraService.setBitacora(bitacora);
        }else{
            LOGGER.error(LOGGER_PREFIX+"Bitacora.- Se busco al usuario {"+correo+"} pero no se encontró registro alguno.");
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
   private boolean isAccesible(String p){       
        try {             
                String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                Usuario usuario = usuarioService.getUsuario(correo);
                List<Permiso> permisos = usuarioService.getPermisos( new Rol(usuario.getRol()));
                boolean acceso = false;
                for (Permiso permiso : permisos) {
                    if(permiso.getNombre().equals(p)){
                        acceso = true;
                        break;
                    }
                }
                return acceso;
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema durante la busqueda del permiso --> "+e.getMessage());
            return false;
        }
    }    
   private short getStatus(String txt){
        List<StatusAgremiado> statusAgremiado = agremiadoService.getStatusAgremiado();
        for (StatusAgremiado status : statusAgremiado) {
            if(status.getStatus().equalsIgnoreCase(txt)){
                return status.getIdStatusAgremiado();
            }
        }
        return 0;
    }
   private Date procesayyyyMMdd(String fechaString) throws ParseException {    
        String yyyyMMdd = "yyyy-MM-dd";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        return formatter.parse(fechaString);
    }    
   private String procesayyyyMMdd(Date date){
       if(date != null){
            String yyyyMMdd = "yyyy-MM-dd";
            DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
           return formatter.format(date);
       }
       return "";
   }  
   private String procesaddMMyyyy(Date date){
        String yyyyMMdd = "dd-MM-yyyy";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
       return formatter.format(date);
   }
   private Map<Integer, SolicitudBaja> getUltimaBaja(List<Agremiado> BajasSolicitadas) {
        Map<Integer, SolicitudBaja> map = new LinkedHashMap<>(BajasSolicitadas.size());
        BajasSolicitadas.forEach((a) -> {
            map.put(a.getIdAgremiado(), getUltimaSolicitudBaja(a));
        });
        return map;
    }
   private SolicitudBaja getUltimaSolicitudBaja(Agremiado a){
       SolicitudBaja solicitudBaja = null;
        Cliente clienteObj = a.getDatosLaborales().getClienteObj();
        List<SolicitudBaja> solicitudBajaList = a.getSolicitudBajaList();
       for (SolicitudBaja sb : solicitudBajaList) {
                if(clienteObj.equals(sb.getClienteObj())){
                     if(solicitudBaja != null){
                     if(solicitudBaja.getIdSolicitudBaja()<sb.getIdSolicitudBaja()){
                         solicitudBaja =sb;
                     }
                 }else{
                     solicitudBaja = sb;
                 }
               }
       }
       return solicitudBaja;
   }
   private double getFI(Integer idAgremiado) {
        try {
            Agremiado agremiado = agremiadoService.getAgremiado(idAgremiado);
            Date fechaAltaImss = agremiado.getDatosLaborales().getFechaAltaImss();
            if(fechaAltaImss == null){
                return 1.0452;
            }
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             String alta = procesayyyyMMdd(fechaAltaImss);
             String hoy = procesayyyyMMdd(new Date());
            ChronoLocalDate from = ChronoLocalDate.from(formatter.parse(alta));
            ChronoLocalDate to = ChronoLocalDate.from(formatter.parse(hoy));
            ChronoPeriod period = ChronoPeriod.between(from, to);
            long get = period.get(YEARS);
            switch((int) get){
                case 2:
                    return 1.0466;
                case 3:
                    return 1.0479;
                case 4: 
                    return 1.0493;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    return 1.0507;
                case 1:
                default:
                    return 1.0452;
            }
        } catch (Exception e) {
            LOGGER.error(LOGGER_PREFIX+"Ocurrio un error al momento de calcular el factor de integración --> "+e.getMessage());
            return -1.270890d;
        }
    }
   private String getObservaciones(String observaciones, String string) {
        String procesaddMMyyyy = procesaddMMyyyy(new Date());
        String correo = SecurityContextHolder.getContext().getAuthentication().getName();
        
        string = string.replace("%U", "<b>"+correo+"</b>");
        string = "<b>"+procesaddMMyyyy+"</b> - "+((string==null)?"":string);
        String rslt = string+"<br>"+((observaciones==null)?"":observaciones);
        if(rslt.length() > 2497){
            rslt = rslt.substring(0, 2497);
            rslt = rslt+"[.]";
        }
        return rslt;
        
    }
   private List<Agremiado> getColaboradoresPorVencer() {
        List<Agremiado> agremiadosPorVencer = new ArrayList<>();   
        try {           
            String correo = SecurityContextHolder.getContext().getAuthentication().getName();
            Usuario usuario = usuarioService.getUsuario(correo);
            if(!usuario.getClienteList().isEmpty()){

                short status = getStatus("Activo");
                agremiadosPorVencer = agremiadoService.getAgremiadosPorVencer(status, usuario.getIdUsuario());      
            }
       } catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al obtener la lista de colaboradores con contrato por vencer --> "+e.getMessage(),e);
       }
        return agremiadosPorVencer;
    }
   private Map<Integer, SolicitudRenovacionContrato> getUltimaRenovacion(List<Agremiado> BajasSolicitadas) {
        Map<Integer, SolicitudRenovacionContrato> map = new LinkedHashMap<>(BajasSolicitadas.size());
        BajasSolicitadas.forEach((a) -> {
            map.put(a.getIdAgremiado(), getUltimaSolicitudRenovacion(a));
        });
        return map;
    }
   private SolicitudRenovacionContrato getUltimaSolicitudRenovacion(Agremiado a){
       SolicitudRenovacionContrato renovacionContrato = null;
        Cliente clienteObj = a.getDatosLaborales().getClienteObj();
        List<SolicitudRenovacionContrato> renovacionContratos = a.getSolicitudRenovacionContratoList();
       for (SolicitudRenovacionContrato src : renovacionContratos) {
                if(clienteObj.equals(src.getClienteObj())){
                     if(renovacionContrato != null){
                        if(renovacionContrato.getIdSolicitudRenovacion()<src.getIdSolicitudRenovacion()){
                            renovacionContrato =src;
                        }
                    }else{
                        renovacionContrato = src;
                    }
               }
       }
       return renovacionContrato;
   }   
   private void setCamposDispoiblesAndContratoEsquema(Map<String,Object>  map, EsquemaPago esquemaPago, Integer contrato){
                map.put("esquema", esquemaPago);
                map.put("contratoID", contrato);           
                map.put("campos", agremiadoService.getCamposDelEsquema(esquemaPago));  
   }
   private void setCamposDispoiblesAndContratoEsquema(Map<String,Object>  map, EsquemaPago esquemaPago, String contrato){
        ContratoEmpresas contratoEmpresasByIdName = agremiadoService.getContratoEmpresasByIdName(contrato);
       setCamposDispoiblesAndContratoEsquema(map, esquemaPago, contratoEmpresasByIdName.getIdContratoEmpresas());
   }
   private void setCamposDispoiblesAndContratoEsquema(Map<String,Object>  map, Short s, Integer contrato){
       setCamposDispoiblesAndContratoEsquema(map, agremiadoService.getEsquemaPago(s),contrato);
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
    @Override
    public String toString() {
        return "ColaboradorController{Controlador Del Colaborador, Versión 2.0}";
    }

}
