/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradettisanti.payroll.Utility.DelContrato;
import com.pradettisanti.payroll.Utility.StatusQuery;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Permiso;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.ClienteService;
import com.pradettisanti.payroll.service.NotificacionesService;
import com.pradettisanti.payroll.service.UsuarioService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Manager encargado de administrar la peticiones REST de Collaboradores
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@RestController
@RequestMapping("colaboradores/rest")
public class RestColaboradorController {
    
    /**
     * Constante para el uso de los logs del sistema
     */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(RestColaboradorController.class);    
    
    private final String MODULO = "Colaboradores";
    private final String LOGGER_PREFIX = "[RestController, "+MODULO+"] ";
    
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
    private ClienteService clienteService;
    public ClienteService getClienteService(){
        return clienteService;
    }
    private void setClienteService(ClienteService clienteService){
        this.clienteService = clienteService;
    }
       
    @Autowired
    private NotificacionesService notificacionesService;
    public NotificacionesService getNotificacionesService(){
        return notificacionesService;
    }
    public void setNotificacionesService(NotificacionesService notificacionesService){
        this.notificacionesService = notificacionesService;
    }
    
    /**
     * Controller encargado de devolver todos los pentendientes que tiene el usuario logeado en el sistema
     * @param request Varibale que maneja las peticiones
     * @param response Varibale que maneja las respuestas
     * @return String
     */
    @RequestMapping(value="/pendientes-totales.json",method = RequestMethod.GET)
   public String getPendientesTotales(HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicitan los pendientes totales del colaboradores.");
       try {
           
           String correo = SecurityContextHolder.getContext().getAuthentication().getName();
           Usuario usuario = usuarioService.getUsuario(correo);
           int rolId = usuario.getRol();
           Rol rol = usuarioService.getRol(rolId);
        
            List<StatusAgremiado> statusAgremiado = agremiadoService.getStatusAgremiado();
            
            List<Short> statusPermitidosIDs = new ArrayList<>(statusAgremiado.size());
            statusAgremiado.stream().filter((StatusAgremiado status) -> {
               return tienePermiso(status.getStatus(),rol);
           }).forEachOrdered((status) -> {
                statusPermitidosIDs.add(status.getIdStatusAgremiado());
            });
            
            List<StatusQuery> agremiadoPorStatus;
            if(usuario.getClienteList().isEmpty()){
                agremiadoPorStatus = agremiadoService.getAgremiadoPorStatus(statusPermitidosIDs);
            }else{
                agremiadoPorStatus = agremiadoService.getAgremiadoPorStatus(statusPermitidosIDs,usuario.getIdUsuario());
            }
               
            int pendientesTotales = 0;
           Map<String,Integer> totalesDisponibles = new HashMap<>(agremiadoPorStatus.size());
           
           pendientesTotales = agremiadoPorStatus.stream().map((agremiadoPorStat) -> {
               String statusNombre = agremiadoPorStat.getST().toLowerCase().replace(' ', '.');
               totalesDisponibles.put(statusNombre, agremiadoPorStat.getTTL());
               return agremiadoPorStat;
           }).map((agremiadoPorStat) -> agremiadoPorStat.getTTL()).reduce(pendientesTotales, Integer::sum);
           
           int activos;
           try {
                activos = totalesDisponibles.remove("activo");
           } catch (Exception e) {
               activos = 0;
           }
           int bajas;
           try {
               bajas = totalesDisponibles.get("baja");
           } catch (Exception e) {
               bajas = 0;
           }

            pendientesTotales = pendientesTotales - activos;
            pendientesTotales = pendientesTotales - bajas;
           totalesDisponibles.put("pendientes.totales", pendientesTotales);
           
            return new ObjectMapper().writeValueAsString(totalesDisponibles);
            
            
       } catch (JsonProcessingException  me) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición con los pendientes totales de usuario --> "+me.getMessage());
       }catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio una excepción al intentar procesar la petición con los pendientes totales de usuario --> "+e.getMessage());
       }
       return "";
   }

   /**
    * Controller encargado de regresar los contratos que estan apunto de vencer
    * @param request Varibale que maneja las peticiones
    * @param response Varibale que maneja las respuestas
    * @return String
    */
   @RequestMapping(value="/contratos-por-terminar.json",method = RequestMethod.POST)
   public String getContratosPorTerminar(HttpServletRequest request, HttpServletResponse response){
       LOGGER.info(LOGGER_PREFIX+"Se solicita la cantidad de contratos por vencer");
        try {            
            
                    String correo = SecurityContextHolder.getContext().getAuthentication().getName();
                    Usuario usuario = usuarioService.getUsuario(correo);
                    if(!usuario.getClienteList().isEmpty()){
                        
                        short status = getStatus("Activo");
                        List<Agremiado> agremiadosPorVencer = agremiadoService.getAgremiadosPorVencer(status, usuario.getIdUsuario());
                        List<Agremiado> agremiadosParaNotificacion = new ArrayList<>(agremiadosPorVencer.size());                   
            
                        String hoyString = procesayyyyMMdd(new Date());
                       Date hoy = procesayyyyMMdd(hoyString);
                       agremiadosPorVencer.stream().filter((agremiado) -> (agremiado.getDatosLaborales().getFechaTimbreContrato().before(hoy)&&(agremiado.getDatosLaborales().getFechaFinContrato().after(hoy)))).forEachOrdered((agremiado) -> {
                           agremiadosParaNotificacion.add(agremiado);
                       });

                       agremiadosParaNotificacion.stream().map((agremiado) -> {
                            notificacionesService.contratoPorVencer(agremiado);
                            return agremiado;
                        }).map((agremiado) -> agremiado.getDatosLaborales()).map((datosLaborales) -> {
                            Date fechaTimbre =  getAjusteFechaTimbre(datosLaborales.getFechaFinContrato(),datosLaborales.getFechaTimbreContrato());
                            datosLaborales.setFechaTimbreContrato(fechaTimbre);
                            return datosLaborales;
                        }).forEachOrdered((datosLaborales) -> {
                            agremiadoService.setDatosLaborales(datosLaborales);
                        });
                        Map<String,Object> map = new HashMap<>(2);
                        map.put("paraVencimiento", agremiadosPorVencer.size());
                        return new ObjectMapper().writeValueAsString(map);
                    }else{                    
                        Map<String,Object> map = new HashMap<>(2);
                        map.put("paraVencimiento", 0);
                        return new ObjectMapper().writeValueAsString(map);
                    }
            
       } catch (JsonProcessingException | ParseException me) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición de contratos por vencer --> "+me.getMessage());
       }catch (Exception e) {
           LOGGER.error(LOGGER_PREFIX+"Ocurrio un problema al intentar procesar la petición de contratos por vencer -Exception- --> "+e.getMessage());
       }
        return "";
   }
   
   /**
    * Controller de prueba para peticiones con la firma indicada
    * @param nombre Nombre que sera vizualizado en la pagina de la petición
    * @return String
    */
    @RequestMapping(value = "/{name}.json",method = RequestMethod.GET)
    public String getAuthor(@PathVariable("name") String nombre){
        return "Hola "+nombre.toUpperCase();
    }

   private boolean tienePermiso(String p, Rol rol){
        String permisoReal = "";
        switch(p){
            case "Activo":
                permisoReal = "Colaboradores";
            break;
            case "Alta En Proceso":
                permisoReal = "Altas_En_Proceso";
            break;
            case "V.OB.O":
                permisoReal = "Altas_Solicitadas";
            break;
            case "Baja Pendiente":
                permisoReal = "Bajas_Pendientes";
            break;
            case "Baja Por Finalizar":
                permisoReal = "Bajas_Por_Finalizar";
            break;
            case "Baja Solicitada":
                permisoReal = "Bajas_Solicitadas";
            break;
            case "Renovación Solicitada":
                permisoReal = "Renovaciones_Solicitadas";
            break;
            case "Solicitud De Renovación":
                permisoReal = "Solicitudes_De_Renovacion";
            break;
            case "Expediente Sin Contrato":
                permisoReal = "Expedientes_Sin_Contrato";
            break;
            case "Expediente Incompleto":
                permisoReal = "Expedientes_Por_Completar";
            break;
            case "Baja":
                permisoReal = "Bajas";
            case "Baja Sin Firmar":
                permisoReal = "Bajas_Sin_Firmar";
            break;
        }
        for (Permiso permiso : rol.getPermisoList()) {
            if(permisoReal.equals(permiso.getNombre())){
                return true;
            }
        }
        return false;
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
   private Date getAjusteFechaTimbre(Date fechaFinContrato, Date fechaTimbreContrato) {
        if(fechaTimbreContrato.before(fechaFinContrato)){
            Calendar calendar = Calendar.getInstance(); 
                 calendar.setTime(fechaTimbreContrato); 
                 calendar.add(Calendar.DATE, 1);
                 return  calendar.getTime();
        }
        return fechaTimbreContrato;
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
}
