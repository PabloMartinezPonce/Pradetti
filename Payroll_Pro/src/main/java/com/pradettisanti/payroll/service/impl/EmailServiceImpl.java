/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.pojoh.ReciboSolicitado;
import com.pradettisanti.payroll.service.ClienteService;
import com.pradettisanti.payroll.service.EmailService;
import com.pradettisanti.payroll.service.ReciboService;
import com.pradettisanti.payroll.service.UsuarioService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Service encargado de enviar los correos del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(EmailServiceImpl.class);
    
    @Autowired
    private ReciboService reciboService;
    
    public ReciboService getReciboService(){
        return reciboService;
    }
    public void setReciboService(ReciboService reciboService){
        this.reciboService = reciboService;
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
    private ClienteService clienteService;
    
    public ClienteService getClienteService() {
        return clienteService;
    }
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    /**
     * Metodo encargado de enviar los correos electronicos
     * @param emailAddress cadena de texto con el correo electronico
     * @param subject cadena de texto con el titulo del correo
     * @param msj  Cadena de texto con el mensaje del correo
     */
    @Override
    public void enviarCorreo(String emailAddress, String subject, String msj) {
        LOGGER.info("[Service, EmailServiceImpl] Se solicita el envío de un mensaje correo con el asunto {"+subject+"} a la dirección de correo "+emailAddress);
        try {
            Message message = header(subject, emailAddress);
            Multipart multipart = new MimeMultipart("alternative");
            MimeBodyPart bodyHTML = bodyHTML(msj);
            multipart.addBodyPart(bodyHTML);
           message.setContent(multipart);
           Transport.send(message);
        } catch (MessagingException me) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a la dirección "+emailAddress+" con el asunto "+subject+"  -->  "+me);
        }catch (Exception e) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a la dirección "+emailAddress+" con el asunto "+subject+"  -->  "+e);
        }
    }
    
    /**
     * Metodo encargado de enviar un correo a multiples direcciones de correo
     * @param emailAddress Arreglo  de cadena de texto con el correo electronico
     * @param subject cadena de texto con el titulo del correo
     * @param msj  Cadena de texto con el mensaje del correo
     */
    @Override
    public void enviarCorreo(String emailAddress[], String subject, String msj) {
        LOGGER.info("[Service, EmailServiceImpl] Se solicita el envío de un mensaje correo con el asunto {"+subject+"} a las dirección de correo "+Arrays.toString(emailAddress));
        try {
            MimeMessage message = header(subject, emailAddress);
            Multipart multipart = new MimeMultipart("alternative");
            MimeBodyPart bodyHTML = bodyHTML(msj);
            multipart.addBodyPart(bodyHTML);
           message.setContent(multipart);
           Transport.send(message);
        } catch (MessagingException me) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a las direcciones "+Arrays.toString(emailAddress)+" con el asunto "+subject+"  -->  "+me);
        }  catch (Exception e) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a las direcciones "+Arrays.toString(emailAddress)+" con el asunto "+subject+"  -->  "+e);
        }        
    }
    
    /**
     * Metodo encargado de enviar los correos electronicos
     * @param emailAddress cadena de texto con el correo electronico
     * @param subject cadena de texto con el titulo del correo
     * @param msj  Cadena de texto con el mensaje del correo
     * @param file Archivo a adjuntar
     */
    @Override
    public boolean enviarCorreo(String emailAddress, String subject, String msj, File file) { //Metodo que regresa un boolean y recibe como parametros el emailAddres, subject, msj, File
        boolean flag = false;
     LOGGER.info("[Service, EmailServiceImpl] Se solicita el envío de un mensaje correo con el asunto {"+subject+"} a la dirección de correo "+emailAddress);
        try {
            Message message = header(subject, emailAddress);
            Multipart multipart = new MimeMultipart("alternative");
            MimeBodyPart bodyHTML = bodyHTML(msj);
            multipart.addBodyPart(bodyHTML);
            MimeBodyPart attach = new MimeBodyPart();
            attach.attachFile(file);
            multipart.addBodyPart(attach);
            message.setContent(multipart);
            Transport.send(message);
            flag = true; //se manda el valor de true a la variable flag de tipo boolean
        } catch (MessagingException me) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a la dirección "+emailAddress+" con el asunto "+subject+"  -->  "+me);
        }catch (IOException ioe) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a la dirección "+emailAddress+" con el asunto "+subject+"  -->  "+ioe);
        } catch (Exception e) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a la dirección "+emailAddress+" con el asunto "+subject+"  -->  "+e);
        }finally{
            return flag; //regrsa la bandera sin importar si entra al try o al catch 
        }   
    }
    
    @Override
    public void enviarCorreo(String emailAddress, String subject, String msj, File file, ReciboSolicitado reciboSolicitado) { // se implementa el metodo con los parametros solicitados
        LOGGER.info("[Service, EmailServiceImpl] Se solicita el envío de un mensaje correo con el asunto {"+subject+"} a la dirección de correo "+emailAddress); //Se imprime en los logs la información de la petición
        try {
            Message message = header(subject, emailAddress); 
            Multipart multipart = new MimeMultipart("alternative");
            MimeBodyPart bodyHTML = bodyHTML(msj);
            multipart.addBodyPart(bodyHTML);
            MimeBodyPart attach = new MimeBodyPart();
            attach.attachFile(file);
            multipart.addBodyPart(attach);
            message.setContent(multipart);
            Transport.send(message);
            reciboService.setRegistrarEnvioDeRecibo(reciboSolicitado); //Se manda llamar al metodo setRegistrarEnvioDeRecibo con el objeto reciboSolicitado como parametro
        } catch (MessagingException me) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a la dirección "+emailAddress+" con el asunto "+subject+"  -->  "+me);
        }catch (IOException ioe) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a la dirección "+emailAddress+" con el asunto "+subject+"  -->  "+ioe);
        } catch (Exception e) {
            LOGGER.error("[Service, EmailServiceImpl] Ocurrio un error al intentar crear y enviar un correo electrónico a la dirección "+emailAddress+" con el asunto "+subject+"  -->  "+e);
        }   
    }
    
    private MimeMessage header(String subject, String... emailAddress) throws MessagingException{
        Properties p = new Properties();
        p.put(KEYS[0], VALUES[0]);
        p.put(KEYS[1], VALUES[1]);
        p.put(KEYS[2], VALUES[2]);
        p.put(KEYS[3], VALUES[3]);
        p.put(KEYS[4], VALUES[3]);
        p.put(KEYS[5], VALUES[3]);
        Authenticator authenticator = new SMTP_AUTHENTICATOR();
        Session session = Session.getInstance(p,authenticator);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL_ADDRESS));
        message.setRecipients(Message.RecipientType.TO, getInternetAddresses(emailAddress));
        message.setSubject(subject, "utf-8");
        message.setSentDate( new Date() );
        return message;
    }
    
    private MimeBodyPart bodyHTML(String msjHTML) throws MessagingException{
        MimeBodyPart mbp = new MimeBodyPart();
        mbp.setContent(msjHTML, "text/html; charset=utf-8");
        return mbp;
    }
        
    private  InternetAddress[] getInternetAddresses(String... emailAddress) throws AddressException{
        List<InternetAddress> ias = new ArrayList<>();
        for (String emailAddres : emailAddress) {
            ias.add( new InternetAddress(emailAddres));
        }
        InternetAddress[] addresses = new InternetAddress[ias.size()];
        addresses = ias.toArray(addresses);
        return addresses;
    }
    
    private final String[] KEYS = new String[]{"mail.transport.protocol","mail.smtp.host","mail.smtp.port","mail.smtp.auth","mail.smtp.starttls.enable","mail.smtp.ssl.enabled"};
    private final String[] VALUES = new String[]{"smtp","smtp.sendgrid.net","587","true"};
    private final String EMAIL_ADDRESS = "pradettisystem@pradettisanti.com.mx";
    private final String OWNER = "EmmanuelRoman-ITS";
    private final String PASSWORD = "itseekers00";

    private class SMTP_AUTHENTICATOR extends Authenticator{
        @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(OWNER, PASSWORD);
            }
    };
}
