/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.controller;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.ClienteService;
import com.pradettisanti.payroll.service.ContratistaService;
import com.pradettisanti.payroll.service.ExcelReportesService;
import com.pradettisanti.payroll.service.ReportesService;
import com.pradettisanti.payroll.service.UsuarioService;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador encargado de administrar todas las peticiones web correspondientes con el tema de resportes
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @since 14/02/2018
 * @version 1
 */
@SuppressWarnings("Convert2Diamond")
public class ReportesController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private ContratistaService contratistaService;
    
    @Autowired
    private AgremiadoService agremiadoService;
    
    @Autowired
    private ReportesService reportesService;
    
    @Autowired
    private ExcelReportesService excelReportesService;
    
    /**
     * Constantes para el uso en la clase
     */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ReportesController.class);  
    private static final String CLASS_NAME = "[ReportesController]";
    private final String MODULO = "Reportes";  
    
    @RequestMapping(value = "reporte/reportes.htm", method = RequestMethod.GET)
    public ModelAndView vistasPrincipal(HttpServletRequest request, HttpServletResponse response){
        writeLogger("Se soilicita la vista principal de los reportes");
        Map<String, Object> map = new HashMap<>();
        try {
            
        } catch (Exception e) {
            setInformacionEnVentana(map, 1, MODULO, "No fue posible generar la vista solicitada.");
        }
        return new ModelAndView("reportes/vistaPrincipal", "model", map);
    }
    
    @RequestMapping(value = "reporte/{url}.htm",method = RequestMethod.GET)
    public ModelAndView vistaPorURL(@PathVariable("url") String url, HttpServletRequest request, HttpServletResponse response){
        writeLogger("Se solicita la vista del reporte "+url.replace('-',' '));
        Map<String, Object> map = new HashMap<>();
        
        try {
            url = url.toLowerCase();
            switch(url){
                case "datos-del-colaborador":
                    map.put("clientes", getClientesDelUsuario());
                    map.put("contratistas", getContratistas());
                    map.put("estados", getEstadosDeAgremiados());
                    return new ModelAndView("reportes/datosDelColaborador", "model", map);
                case "fondo-de-ahorro":
                    map.put("clientes", getClientesDelUsuario());
                    map.put("contratistas", getContratistas());
                    map.put("estados", getEstadosDeAgremiados());
                    return new ModelAndView("reportes/datosDelFondoDeAhorro","model", map);
            }            
        } catch (Exception e) {
            setInformacionEnVentana(map, 1, MODULO, "No fue posible generar la vista solicitada.");
            writeLogger("Ocurrió un problema al intentar generar la vista de la url "+url, e);
        }
        return new ModelAndView("reportes/vistaPrincipal", "model", map);
        
    }
    
    @RequestMapping(value = "reporte/datos-del-colaborador.htm", method = RequestMethod.POST)
    public void getDatosDeLosColaboradores(HttpServletRequest request, HttpServletResponse response){
        writeLogger("Se solcita el reporte con todos los datos de los colaboradores");
        try {
            String estadoString = request.getParameter("estado");
            String clienteString = request.getParameter("cliente");
            String contratistaString = request.getParameter("contratista");
            
            short status = Short.parseShort(estadoString);
            StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(status);
            
            int cliente;
            if(clienteString == null || clienteString.isEmpty()){
                cliente = -1;
            }else{
                cliente =  Integer.parseInt(clienteString);
            }
            int contratista;
            if(contratistaString == null || contratistaString.isEmpty()){
                contratista = -1;
            }else{
                contratista =  Integer.parseInt(contratistaString);
            }
            
            Contratista  cstObj;
            Cliente cntObj;
            List<Agremiado> agremiados ;
                       
           if(cliente != -1 & contratista != -1){
               cstObj = contratistaService.getContratista(contratista);
               cntObj = clienteService.getCliente(cliente);
               agremiados = reportesService.getAgremiados(statusAgremiado, cstObj, cntObj);
           }else if(cliente != -1 & contratista == -1){
               cntObj = clienteService.getCliente(cliente);
               agremiados = reportesService.getAgremiados(statusAgremiado, cntObj);
           }else if(cliente == -1 & contratista != -1){
               agremiados = new ArrayList<>();
               cstObj = contratistaService.getContratista(contratista);               
                List<Cliente> clientesDelUsuario = getClientesDelUsuario();
                clientesDelUsuario.stream().map((c) -> reportesService.getAgremiados(statusAgremiado, cstObj, c)).forEachOrdered((as) -> {
                    agremiados.addAll(as);
                });
           }else{
               agremiados = new ArrayList<>();        
                List<Cliente> clientesDelUsuario = getClientesDelUsuario();
                clientesDelUsuario.stream().map((c) -> reportesService.getAgremiados(statusAgremiado, c)).forEachOrdered((as) -> {
                    agremiados.addAll(as);
                });
           }
            ByteArrayOutputStream reporteDeDatosDeLosColaboradores = excelReportesService.getReporteDeDatosDeLosColaboradores(agremiados);
            
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition", "attachment; filename=Datos_de_los_colaboradores_en__" + statusAgremiado.getStatus().replace(' ', '_')+"_.xlsx");
            
            response.setContentLength(reporteDeDatosDeLosColaboradores.size());
                try (OutputStream os = response.getOutputStream()) { 
                    reporteDeDatosDeLosColaboradores.writeTo(os);
                    os.flush();
                }
            
        } catch (Exception e) {
            writeLogger("Ocurrio un error con el reporte todos los datos de los colaboradores",e);
        }
    }
    
    @RequestMapping(value = "reporte/fondo-de-ahorro.htm", method = RequestMethod.POST)
    public void getDatosDelFondoDeAhorro(HttpServletRequest request, HttpServletResponse response){
        writeLogger("Se solcita el reporte con todos los datos del fondo de ahorro de los colaboradores");
        try {
            String estadoString = request.getParameter("estado");
            String clienteString = request.getParameter("cliente");
            String contratistaString = request.getParameter("contratista");
            String diaRegistroDesdeString = request.getParameter("diaRegistroDesde");
            String diaRegistroHastaString = request.getParameter("diaRegistroHasta");
            
            short status = Short.parseShort(estadoString);
            StatusAgremiado statusAgremiado = agremiadoService.getStatusAgremiado(status);
            
            int cliente;
            if(clienteString == null || clienteString.isEmpty()){
                cliente = -1;
            }else{
                cliente =  Integer.parseInt(clienteString);
            }
            int contratista;
            if(contratistaString == null || contratistaString.isEmpty()){
                contratista = -1;
            }else{
                contratista =  Integer.parseInt(contratistaString);
            }
            
            Contratista  cstObj;
            Cliente cntObj;
            List<Agremiado> agremiados ;
                       
           if(cliente != -1 & contratista != -1){
               cstObj = contratistaService.getContratista(contratista);
               cntObj = clienteService.getCliente(cliente);
               agremiados = reportesService.getAgremiados(statusAgremiado, cstObj, cntObj);
           }else if(cliente != -1 & contratista == -1){
               cntObj = clienteService.getCliente(cliente);
               agremiados = reportesService.getAgremiados(statusAgremiado, cntObj);
           }else if(cliente == -1 & contratista != -1){
               agremiados = new ArrayList<>();
               cstObj = contratistaService.getContratista(contratista);               
                List<Cliente> clientesDelUsuario = getClientesDelUsuario();
                clientesDelUsuario.stream().map((c) -> reportesService.getAgremiados(statusAgremiado, cstObj, c)).forEachOrdered((as) -> {
                    agremiados.addAll(as);
                });
           }else{
               agremiados = new ArrayList<>();        
                List<Cliente> clientesDelUsuario = getClientesDelUsuario();
                clientesDelUsuario.stream().map((c) -> reportesService.getAgremiados(statusAgremiado, c)).forEachOrdered((as) -> {
                    agremiados.addAll(as);
                });
           }
            ByteArrayOutputStream reporteDeDatosDeLosColaboradores = excelReportesService.getReporteDelFondoDeAhorro(agremiados);
            
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition", "attachment; filename=Datos_del_fondo_de_ahorro_en__" + statusAgremiado.getStatus().replace(' ', '_')+"_.xlsx");
            
            response.setContentLength(reporteDeDatosDeLosColaboradores.size());
                try (OutputStream os = response.getOutputStream()) { 
                    reporteDeDatosDeLosColaboradores.writeTo(os);
                    os.flush();
                }
            
        } catch (Exception e) {
            writeLogger("Ocurrio un error con el reporte todos los datos de los colaboradores",e);
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
    private List<Contratista> getContratistas(){
        try {
            List<Contratista> contratistas = contratistaService.getContratistas();
            contratistas.sort((c1, c2)->c1.getNombreEmpresa().compareTo(c2.getNombreEmpresa()));
            return contratistas;
        } catch (Exception e) {
            writeLogger("No fue posible obtener los contratistas ", e);
        }
        return new ArrayList<>();
    }
   private List<StatusAgremiado> getEstadosDeAgremiados(){
       try {
           List<StatusAgremiado> statusAgremiado = agremiadoService.getStatusAgremiado();
           statusAgremiado.sort((s1,s2)->s1.getStatus().compareTo(s2.getStatus()));
           return statusAgremiado;
       } catch (Exception e) {
           writeLogger("No fue posible obtener estados de los agremiados", e);
       }
       return new ArrayList<>();
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
}
