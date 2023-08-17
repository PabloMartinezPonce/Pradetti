/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Documento;
import com.pradettisanti.payroll.pojoh.Notificaciones;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.ReciboSolicitado;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.EmailService;
import com.pradettisanti.payroll.service.NotificacionesService;
import com.pradettisanti.payroll.service.PDFiTextService;
import com.pradettisanti.payroll.service.UsuarioService;
import com.pradettisanti.payroll.service.ZipService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service encargado de adminitrar la notificaciones del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Service
public class NotificacionesServiceImpl implements NotificacionesService {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(NotificacionesServiceImpl.class);
    
    @Autowired
    private UsuarioService usuarioService;
    public UsuarioService getUsuarioService(){
        return usuarioService;
    }
    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }
    
    @Autowired
    private EmailService emailService;
    public EmailService getEmailService(){
        return emailService;
    }
    public void setEmailService(EmailService emailService){
        this.emailService = emailService;
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
    private ZipService zipService;
    public ZipService getZipService(){
        return zipService;
    }
    public void setZipService(ZipService zipService){
        this.zipService = zipService;
    }
    
    @Override
    public void altasSolicitada(Agremiado colaborador, Date fechaAlta) {
        String subject = "ALTA SOLICITADA - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaAlta)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que el colaborador \""  +colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", ha pasado al apartado de <b>'Altas Solicitadas'</b>. Con fecha de alta: "+getFechaCorta(fechaAlta)+'.'
                                                                                    +"<br>Favor de estar al tanto del sistema para seguir con el proceso de alta.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "ALTA SOLICITADA");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }
    
    @Override
    public void altaEnProceso(Agremiado colaborador, Date fechaAlta) {
        String subject = "ALTA EN PROCESO - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaAlta)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que el colaborador \""  +colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", ha pasado al apartado de <b>'Altas En Proceso'</b>. Con fecha de alta: "+getFechaCorta(fechaAlta)+'.'
                                                                                    +"<br>Favor de estar al tanto del sistema para seguir con el proceso de alta.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "ALTA EN PROCESO");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    public void solicitudFondoAhorro(Agremiado colaborador,Date fechaAlta){
        String subject = "FONDO DE AHORRO SOLICITADO - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaAlta)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que el colaborador \""  +colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", ha solicitado su <b>'Fondo de ahorro'</b>. Con fecha de alta: "+getFechaCorta(fechaAlta)+'.'
                                                                                    +"<br>Favor de estar al tanto del sistema para seguir con el proceso de revisión.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "FONDO DE AHORRO SOLICITADO");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }
    @Override
    public void bajasSolicitada(Agremiado colaborador, Date fechaBaja) {
        
        String subject = "BAJA SOLICITADA - "     +colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaBaja)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que el colaborador \""  +colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", ha pasado al apartado de <b>'Bajas Solicitadas'</b>. Con fecha de baja: "+getFechaCorta(fechaBaja)+'.'
                                                                                    +"<br>Favor de revisar dicho apartado en el sistema.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "BAJA SOLICITADA");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void expedienteConObservaciones(Agremiado colaborador) {
        String subject = "EXPEDIENTE CON OBSERVACIONES - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se notifica que al colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+"\" del cliente \""
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", se le han agregado observaciones y ahora se encuentra en el apartado de <b>'Expedientes con Observaciones'</b>."
                                                                                    +"<br>Favor de revisar dichas observaciones para seguir con el proceso de alta.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "EXPEDIENTE CON OBSERVACIONES");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void expedienteSinContrato(Agremiado colaborador, Date fechaAlta) {
        String subject = "V.°B.° DE EXPEDIENTE - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaAlta)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que el colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", se le ha dado el V.°B.° y ahora se encuentra en el apartado de <b>'Expedientes sin contrato'</b>. Con fecha de alta: "+getFechaCorta(fechaAlta)+'.'
                                                                                    +"<br>Favor de revisar el sistema para seguir con el proceso.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "V.°B.° DE EXPEDIENTE");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }
    
    @Override
    public void altaExitosa(Agremiado colaborador, Date fechaAlta) {
        String subject = "PROCESO DE ALTA EXITOSA - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaAlta)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que el colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", ha concluido exitosamente con su proceso de alta y ahora se encuentra en el apartado de <b>'Lista de Colaboradores'</b>. Con fecha de alta: "+getFechaCorta(fechaAlta)+'.'
                                                                                    +"<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "PROCESO DE ALTA EXITOSA");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void correccionesRealizadas(Agremiado colaborador, Date fechaAlta) {
        String subject = "CORRECIONES REALIZADAS – "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaAlta)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que al colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+"\" del cliente \""
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", se le han realizado los cambios indicados en las observaciones y ahora se encuentra en el apartado de <b>'Altas Solicitadas'</b>."
                                                                                    + "<br>Favor de revisar la solicitud para seguir con el proceso de alta.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "CORRECIONES REALIZADAS");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void bajaPorFinalizar(Agremiado colaborador) {
        String subject ="BAJA POR FINALIZAR - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se notifica que para el colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+"\" del cliente \""
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", ya se han subido los documentos indicados para continuar con su proceso de baja y se encuentra en el apartado <b>'Bajas por Finalizar'</b>."
                                                                                    + "<br>Favor de revisar los documentos que se subieron y finalizar su baja.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "BAJA POR FINALIZAR");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void bajaPendiente(Agremiado colaborador) {
        String subject = "BAJA PENDIENTE - "  +colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se notifica que al colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+"\" del cliente \""
                                                                                +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                +"\", ya se le han cargado los documentos para poder proseguir con su proceso de baja y se encuentra en el apartado <b>'Bajas Pendientes'</b>."
                                                                                + "<br>Favor de revisar los documentos.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "BAJA PENDIENTE");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void bajaPendienteRechazada(Agremiado colaborador) {
        String subject = "BAJA PENDIENTE RECHAZADA - " +colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se notifica que se ha regresado al colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+"\" del cliente \""
                                                                                +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                +"\", debido a que se ha indicado que alguno de los documentos es incorrecto y/o no pertenece a dicho colaborador por lo que ahora se encuentra el apartado de <b>'Bajas Solicitadas'</b>."
                                                                                + "<br>Favor de revisar los documentos.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "BAJA PENDIENTE RECHAZADA");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }
        
    @Override
    public void bajaPorFinalizarRechazada(Agremiado colaborador) {
        String subject = "BAJA POR FINALIZAR RECHAZADA - " +colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se notifica que se ha regresado al colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+"\" del cliente \""
                                                                                +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                +"\", debido a que se ha indicado que alguno de los documentos es incorrecto y/o no pertenece a dicho colaborador por lo que ahora se encuentra el apartado de <b>'Bajas Pendientes'</b>."
                                                                                + "<br>Favor de revisar los documentos.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "BAJA POR FINALIZAR RECHAZADA");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }
    
    
    @Override
    public void solicitudDeBajaRechazada(Agremiado colaborador) {
        String subject = "SOLICITUD DE BAJA RECHAZADA - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se notifica que la solicitud de baja del colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+"\" del cliente \""
                                                                                +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                +"\", fue rechazada por lo que ha regresado al apartado de <b>'Lista de Colaboradores'</b>."
                                                                                + "<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "SOLICITUD DE BAJA RECHAZADA");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void solicitudDeBajaExitosa(Agremiado colaborador, Date fechaBaja) {
        String subject = "SOLICITUD DE BAJA EXITOSA – "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaBaja)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que la solicitud de baja del colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", finalizo con éxito y ha pasado al apartado de <b>'Colaboradores Dados de Baja'</b>."
                                                                                    + "<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "SOLICITUD DE BAJA EXITOSA");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void solicitudDeRenovacion(Agremiado colaborador, Date fechaRenovacion) {
        String subject = "SOLICITUD DE RENOVACIÓN – "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaRenovacion)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se ha solicitado la renovación del contrato del colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", y ha pasado al apartado de <b>'Renovaciones Solicitadas'</b>. Fecha de renovacion: "+getFechaCorta(fechaRenovacion)+'.'
                                                                                    + "<br>Favor de revisar el sistema para seguir con la renovación.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "SOLICITUD DE RENOVACIÓN");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void solicitudDeRenovacionAceptada(Agremiado colaborador) {
        String subject = "SOLICITUD DE RENOVACIÓN ACEPTADA - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se ha aceptado la solicitado de renovación del contrato del colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", ahora el colaborador se encuentra en el apartado de <b>'Solicitudes de Renovación'</b> con los datos solicitados."
                                                                                    + "<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "SOLICITUD DE RENOVACIÓN ACEPTADA");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void solicitudDeRenovacionRechazada(Agremiado colaborador,String motivo) {
        String subject = "SOLICITUD DE RENOVACIÓN RECHAZADA - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se ha rechazado la solicitado de renovación del contrato del colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", por el siguiente motivo '"+motivo.toUpperCase()+"'"
                                                                                    +"\", el colaborador se encuentra en el apartado de <b>'Colaboradores Activos'</b>."
                                                                                    + "<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "SOLICITUD DE RENOVACIÓN");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void contratoPorVencer(Agremiado colaborador) {
        String subject = "CONTRATOS POR VENCER - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se notifica que el contrato del colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", está a punto de vencer favor de revisarlo."
                                                                                    + "<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "CONTRATOS POR VENCER");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void cambioDeContrasena(Usuario usuario, boolean propia) {
        String subject;
        String msj;
        if(propia){
            subject = "CAMBIO DE CONTRASEÑA";
            msj = "Buen día,<br>El cambio de tu contraseña fue exitoso"
                    + "<br><br>NUEVA CONTRASEÑA =>&nbsp;&nbsp;<b>"+getAcceso(usuario.getContrasena().trim())+"</b>."
                    + "<br><br>Saludos,";
        }else{
            subject = "SOLICITUD DE NUEVA CONTRASEÑA";
            msj = "Se ha solicitado que se le restablezca su contraseña la cual es la siguiente:"
                    + "<br>NUEVA CONTRASEÑA =>&nbsp;&nbsp;<b>"+getAcceso(usuario.getContrasena().trim())+"</b>."
                    + "<br><br>Saludos,";
        }
        emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
    }

    @Override
    public void reciboDeNomina(Recibo recibo, Usuario usuario) { //Se agrega el parametro usuario de tipo objeto Usuario
        String subject = "RECIBO DEL PERIODO - "+getFechaCorta( recibo.getDiaInicial() )+" al "+getFechaCorta( recibo.getDiaFinal() )+".";
        String msj = "Que tenga un excelente día.";
        ByteArrayOutputStream reciboSindical = pDFiTextService.getReciboSindical(recibo);
        try {
            File reciboSindicalPDF = new File(subject+"pdf");
            FileOutputStream fos = new FileOutputStream(reciboSindicalPDF);
            fos.write(reciboSindical.toByteArray());
            Integer cliente = recibo.getClientes().getIdCliente(); // se obitene el idCliente
            Date diaRegistrado = new Date(); //se obtiene el día en que fue ejecutada la petición
            fos.flush();
            String correo = recibo.getAgremiado().getDatosPersonales().getEmail().trim().toLowerCase();
            ReciboSolicitado reciboSolicitado = new ReciboSolicitado(recibo, usuario, diaRegistrado, subject +"pdf al correo "+recibo.getAgremiado().getDatosPersonales().getEmail()+".", recibo.getClientes()); //Se manda llamar al constructor del pojoh Recibo Solicitaado con las parametros que se solicitan
            emailService.enviarCorreo(correo, subject, msj, reciboSindicalPDF, reciboSolicitado); // se agrega nuevo parametro de tipo objeto ReciboSolicitado
        } catch (Exception e) {
            LOGGER.error("[Service, NotificacionesServiceImpl] Ocurrio un error al momento de converir el flujo de salida de datos a un archivo --> "+e);
        }
        
    }

    @Override
    public void reciboDeNomina(List<Recibo> recibos, Usuario usuario) { //Se agrega el parametro usuario de tipo objeto Usuario
        recibos.stream().forEach((recibo) -> {
            String subject = "RECIBO DEL PERIODO - "+getFechaCorta( recibo.getDiaInicial() )+" al "+getFechaCorta( recibo.getDiaFinal() )+".";
            String msj = "Que tenga un excelente día.";
            ByteArrayOutputStream reciboSindical = pDFiTextService.getReciboSindical(recibo);
            try {
                File reciboSindicalPDF = new File(subject+"pdf");
                FileOutputStream fos = new FileOutputStream(reciboSindicalPDF);
                fos.write(reciboSindical.toByteArray()); 
                Integer cliente = recibo.getClientes().getIdCliente(); //se obtiene el idCliente
                Date diaRegistrado = new Date(); //se obtiene el dia en que fue ejecutada la petición
                fos.flush();
                String correo = recibo.getAgremiado().getDatosPersonales().getEmail().trim().toLowerCase();
                ReciboSolicitado reciboSolicitado = new ReciboSolicitado(recibo, usuario, diaRegistrado, subject +"pdf al correo "+recibo.getAgremiado().getDatosPersonales().getEmail()+".", recibo.getClientes()); //Se manda llamar al constructor del pojoh Recibo Solicitaado con las parametros que se solicitan
                emailService.enviarCorreo(correo, subject, msj, reciboSindicalPDF, reciboSolicitado); // se agrega nuevo parametro de tipo objeto ReciboSolicitado
            } catch (Exception e) {
                LOGGER.error("[Service, NotificacionesServiceImpl] Ocurrio un error al momento de converir el flujo de salida de datos a un archivo --> "+e);
            }
        });
    }
    
     @Override
    public boolean reciboDeNominaZIp(String correo, List<Recibo> recibos, Cliente cliente, String Desde, String Hasta) { // Metodo que devuelve un boolean
        String subject ="RECIBOS DE "+cliente.getNombreEmpresa().toUpperCase()+" DEL "+Desde+" AL "+Hasta+'.'; 
        String msj = "Que tenga un excelente día.";
        ByteArrayOutputStream crearArchivo = zipService.crearArchivo(recibos);
        try {
            File recibosSindicalesZIP = new File((subject.trim().replaceAll(" ", "_").replaceAll("/", "-"))+"zip");
            FileOutputStream fos = new FileOutputStream(recibosSindicalesZIP);
            fos.write(crearArchivo.toByteArray());
            fos.flush();
            return emailService.enviarCorreo(correo.trim().toLowerCase(), subject, msj, recibosSindicalesZIP); //regresa la petición del metodo del emailService
        } catch (Exception e) {
            LOGGER.error("[Service, NotificacionesServiceImpl] Ocurrio un error al momento de converir el flujo de salida de datos a un archivo --> "+e);
            return false; //regresa falso si hay alguna excepción
        }
    }
    
    @Override
    public boolean reciboDeNomina(String correo, List<Recibo> recibos, Cliente cliente,  String Desde , String Hasta) { // Metodo que devuelve un boolean
        String subject ="RECIBOS DE "+cliente.getNombreEmpresa().toUpperCase()+" DEL "+Desde+" AL "+Hasta+'.';
        String msj = "Que tenga un excelente día.";
        ByteArrayOutputStream reciboSindical = pDFiTextService.getReciboSindical(recibos, cliente, Desde, Hasta);
        try {
            File reciboSindicalPDF = new File(subject+"pdf");
            FileOutputStream fos = new FileOutputStream(reciboSindicalPDF);
            fos.write(reciboSindical.toByteArray());
            fos.flush();
            return emailService.enviarCorreo(correo.trim().toLowerCase(), subject, msj, reciboSindicalPDF); //regresa la petición del metodo del emailService
        } catch (Exception e) {
            LOGGER.error("[Service, NotificacionesServiceImpl] Ocurrio un error al momento de converir el flujo de salida de datos a un archivo --> "+e);
            return false; //regresa falso si hay alguna excepción
        }
    }
    
    @Override
    public void nuevoUsuario(Usuario usuario) {
        String subject = "ACCESO A PAYROLL";
        String msj = "Buen día,<br>Se te acaba de dar acceso al sistema de payroll con las siguientes credenciales:<br>Nombre de Usuario: <b>"
                +usuario.getNombre().toUpperCase()+ "</b><br>Correo electrónico: <b>"
                +usuario.getCorreoElectronico().toLowerCase()+ "</b><br>Contraseña: <b>"
                +getAcceso( usuario.getContrasena())+"</b>.<br>Saludos,";
        emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
    }
    
    private String getFechaCorta(Date fechaAlta) {
         String yyyyMMdd = "dd-MM-yyyy";
        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        return formatter.format(fechaAlta);
    }

    private String getAcceso(String contrasena) {
       byte[] decode = Base64.getDecoder().decode(contrasena.getBytes());
       String txt = "";
        try {
            String sensible = new String(decode, "utf-8");
           char[] toCharArray = sensible.toCharArray();
           sensible = null;
            int length = toCharArray.length;
            int count = 0;
            for (char character : toCharArray ) {
                switch(character){
                    case '0':
                        txt += "CERO";
                        count++;
                    break;
                    case '1':
                        txt += "UNO";
                        count++;
                    break;
                    case '2':
                        txt += "DOS";
                        count++;
                    break;
                    case '3':
                        txt += "TRES";
                        count++;
                    break;
                    case '4':
                        txt += "CUATRO";
                        count++;
                    break;
                    case '5':
                        txt += "CINCO";
                        count++;
                    break;
                    case '6':
                        txt += "SEIS";
                        count++;
                    break;
                    case '7':
                        txt += "SIETE";
                        count++;
                    break;
                    case '8':
                        txt += "OCHO";
                        count++;
                    break;
                    case '9':
                        txt += "NUEVE";
                        count++;
                    break;
                    default:
                        txt += character;
                        count++;
                }
                if(count<length){
                    txt += ", ";
                }
            }
            String txxt = ""; 
            for (char c : toCharArray) {
                txxt+=""+c;
            }
            txt = ""+txxt+"                           ("+txt+")";
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error("[Service, NotificacionesServiceImpl] Ocurrió un problema al intentar desencriptiar la contraseña del usuario. --> "+ex);
            return "CONTRASEÑA NO ENCONTRADA, POR FAVOR NOTIFICA AL ADMINISTRADOR DEL SISTEMA.";
        }
        return  txt;
    }
    
    private List<Usuario> getUsuariosParaCorreo(Cliente cliente, String notificacionString){
        List<Usuario> usuariosCorreo = new ArrayList<>();
        List<Usuario> usuarios = usuarioService.getUsuarios();
        for (Usuario usuario : usuarios) {
            if(usuario.getClienteList().isEmpty()){
                usuariosCorreo.add(usuario);
            }else{
                for (Cliente clienteTmp : usuario.getClienteList()) {
                    if(Objects.equals(clienteTmp.getIdCliente(), cliente.getIdCliente())){                        
                        usuariosCorreo.add(usuario);
                        break;
                    }
                }
            }
        }
        Map<Usuario,Boolean> booleans = new HashMap<>(usuariosCorreo.size());
        for (Usuario usuario : usuariosCorreo) {
            booleans.put(usuario, Boolean.FALSE);
            for (Notificaciones notificacion : usuario.getNotificacionesList()) {
                if(notificacion.getDescripcion().equalsIgnoreCase(notificacionString)){                    
                    booleans.put(usuario, Boolean.TRUE);
                    break;
                }
                
            }
        }
        booleans.entrySet().stream().filter((b) -> (Objects.equals(b.getValue(), Boolean.FALSE))).forEach((b) -> {
            usuariosCorreo.remove(b.getKey());
        });
        return usuariosCorreo;
    }    

    @Override
    public void rechazoDuranteAlta(Agremiado colaborador, String origenDeRechazo, String notificacion) {
        String subject = "ALTA RECHAZADA - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase());
        String msj = "Buen día,<br>Se ha rechazado el alta en el apartado de <b>"+origenDeRechazo.toLowerCase()+"</b> del colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", para mas información revisa las observaciones del expediente."
                                                                                    + "<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), notificacion);
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void bajaSinFirmar(Agremiado colaborador, Date fechaAlta) {
        String subject = "BAJA SIN FRIMAR - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaAlta)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que el colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", se paso al apartado de <b>'Bajas sin firma'</b> debido a que no se cuenta con las firmas para poder procesar su baja."
                                                                                    +"<br>Favor de revisar el sistema para seguir con el proceso.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "BAJA SIN FRIMAR");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

    @Override
    public void expedienteIncompleto(Agremiado colaborador, Date fechaAlta) {
        String subject = "REACTIVACIÓN DEL EXPEDIENTE - "+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())+" ("
                                                                                    +getFechaCorta(fechaAlta)+" - "
                                                                                    +colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()+")";
        String msj = "Buen día,<br>Se notifica que el colaborador \""+colaborador.getDatosPersonales().getNombre().toUpperCase()+' '
                                                                                    +colaborador.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '
                                                                                    +((colaborador.getDatosPersonales().getApellidoMaterno()==null)?"":colaborador.getDatosPersonales().getApellidoMaterno().toUpperCase())
                                                                                    +"\" del cliente \""+colaborador.getDatosLaborales().getClienteObj().getNombreEmpresa().toUpperCase()
                                                                                    +"\", se ha reactivado y ahora se encuentra en el apartado de <b>'Expedientes por completar'</b>. Con fecha de alta: "+getFechaCorta(fechaAlta)+'.'
                                                                                    +"<br>Favor de revisar el sistema para seguir con el proceso.<br>Gracias,";
        List<Usuario> usuariosParaCorreo = getUsuariosParaCorreo(colaborador.getDatosLaborales().getClienteObj(), "REACTIVACIÓN DE EXPEDIENTE");
        usuariosParaCorreo.stream().forEach((usuario) -> {
            emailService.enviarCorreo(usuario.getCorreoElectronico(), subject, msj);
        });
    }

   

}
