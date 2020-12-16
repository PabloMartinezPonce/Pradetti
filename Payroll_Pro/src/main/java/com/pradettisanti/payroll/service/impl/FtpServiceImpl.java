/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.service.FtpService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service encargado de manejar el FTP SERVICE
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */

@Service
public class FtpServiceImpl implements FtpService {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(FtpServiceImpl.class);

    /**
     * Metodo encargado de crear la conexion del FTP
     * @return FTPClient
     */
    @Override
    public FTPClient crearConexionFTP() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(InetAddress.getByName(SERVER));
            ftpClient.login(USER, PASS);
            //Verificar conexión con el servidor.
            int reply = ftpClient.getReplyCode();

            //Activar que se envie cualquier tipo de archivo
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            LOGGER.info("Respuesta recibida de conexión FTP:" + reply);
            if (FTPReply.isPositiveCompletion(reply)) {
                System.out.println("Conectado Satisfactoriamente");
            } else {
                System.out.println("Imposible conectarse al servidor");
            }
        } catch (Exception e) {
        }

        return ftpClient;
    }

    /**
     * Metodo encargado de cerrar la conexion
     * @param ftpClient Insdtancia de FTPClient
     * @return FTPClient
     * @throws IOException La excepcion puede ocurrir debido a diversos problemas que se tengas en la entrar y salida de bytes con el servidor
     */
    @Override
    public FTPClient cerrarConexionFTP(FTPClient ftpClient) throws IOException {
        ftpClient.logout(); //Cerrar sesión
        ftpClient.disconnect();//Desconectarse del servidor
        return ftpClient;
    }

    /**
     * Metodo encargado de devolver un archivo desde el FTP 
     * @param fullPath Cadena de texto que contiene la direccion del archivo
     * @return File
     */
    @Override
    public File descargarFileFTP(String fullPath) {
        LOGGER.info("[Service, FTP] Se solicita la descarga del archivo [" + fullPath + "] del FTP");
        FTPClient FTPClient = new FTPClient();
        File downloadFile = null;
        try {
            FTPClient.connect(SERVER);
            FTPClient.login(USER, PASS);
            FTPClient.enterLocalPassiveMode();
            FTPClient.setFileType(FTP.BINARY_FILE_TYPE);
            char separator = fullPath.charAt(0);
            int lastSeparator = fullPath.lastIndexOf(separator);
            String nombreArchivo = fullPath.substring(lastSeparator + 1);
            downloadFile = new File(nombreArchivo);
            fullPath = fullPath.substring(1);
            try (OutputStream os = new BufferedOutputStream(new FileOutputStream(downloadFile))) {
                fullPath = "/"+fullPath;
                boolean retrieveFile = FTPClient.retrieveFile(fullPath, os);
                if (retrieveFile) {
                    LOGGER.info("[Service, FTP] El archivo [" + nombreArchivo + "] se generó correctamente desde el FTP");
                } else {
                    LOGGER.warn("[Service, FTP] El archivo [" + nombreArchivo + "] NO se generó correctamente desde el FTP ¬> "+fullPath);
                }
            } catch (Exception e) {
                Logger.getLogger(FtpServiceImpl.class.getName()).log(Level.SEVERE, "[Service, FTP]Ocurrio un error al intentar generar el archivo [" + nombreArchivo + "] desde el FTP --> ", e.getMessage());
            }
            FTPClient.logout();
            FTPClient.disconnect();

        } catch (Exception e) {
            Logger.getLogger(FtpServiceImpl.class.getName()).log(Level.SEVERE, "[Service, FTP]Ocurrio un error durante la descarga el archivo [" + fullPath + "] desde el FTP --> ", e.getMessage());
        }
        return downloadFile;
    }

    /**
     * Metodo encargado de almacenar un archivo en el servidor FTP
     * @param file Archivo a guardar
     * @param modulo cadena de texto con el modulo donde se trabaja con el documento
     * @param actor Cadena de texto de actor que trabaja con el documento
     * @param directorio cadena de texto con el nombre del directorio final para almacenar un archivo
     * @return Cadena de texto con la url del archivo almacenado
     */
    @Override
    public String guardarArchivoFTP(MultipartFile file, String modulo, String actor, String directorio) {
        LOGGER.info("[Service, FTP] Se solicita la carga del archivo [" + modulo + ", " + actor + ", " + directorio + ", " + file.getOriginalFilename() + "] dentro del FTP");
        FTPClient FTPClient = null;
        try {
            FTPClient = crearConexionFTP();
            FTPClient.setFileType(FTP.BINARY_FILE_TYPE);
            String path = PATH_SAVE;
            if (!FTPClient.changeWorkingDirectory(path + "/" + modulo)) {
                FTPClient.makeDirectory(path + "/" + modulo);
            }
            if (!FTPClient.changeWorkingDirectory(path + "/" + modulo + "/" + actor)) {
                FTPClient.makeDirectory(path + "/" + modulo + "/" + actor);
            }
            if (!FTPClient.changeWorkingDirectory(path + "/" + modulo + "/" + actor + "/" + directorio)) {
                FTPClient.makeDirectory(path + "/" + modulo + "/" + actor + "/" + directorio);
            }
            String firstRemoteFile = path + "/" + modulo + "/" + actor + "/" + directorio + "/" + file.getOriginalFilename();
            boolean cargaCompleta = false;
            try (InputStream inputStream = file.getInputStream()) {
                cargaCompleta = FTPClient.storeFile(firstRemoteFile, inputStream);
                LOGGER.info("[Service, FTP] El archivo [" + modulo + ", " + actor + ", " + directorio + ", " + file.getOriginalFilename() + "] fue subido exitosamente dentro del FTP");
                inputStream.close();
            } catch (IOException ioe) {
                LOGGER.error("[Service, FTP] Ocurrio un error al momento de intentar subir el archivo  [" + modulo + ", " + actor + ", " + directorio + ", " + file.getOriginalFilename() + "] dentro del FTP --> " + ioe.getMessage());
                
            } finally {
                LOGGER.warn("[Service, FTP] ======================>  [" + firstRemoteFile+"]");
                return cargaCompleta == false ? null : firstRemoteFile;
            }
        } catch (IOException ioe) {
            Logger.getLogger(FtpServiceImpl.class.getName()).log(Level.SEVERE, "[Service, FTP] Error en el FTP --> ", ioe.getMessage());
        } finally {
            try {
                if (FTPClient != null) {
                    cerrarConexionFTP(FTPClient);
                } else {
                    LOGGER.error("[Service, FTP] El cliente FTP no fue inicializado");
                }
            } catch (IOException ioe) {
                Logger.getLogger(FtpServiceImpl.class.getName()).log(Level.SEVERE, "[Service, FTP] Ocurrio un error al intentar cerrar la conexión dentro del FTP --> ", ioe.getMessage());
                return null;
            }
        }
        return null;
    }

    /**
     * Metodo encargado de borrar un archivo del servidor FTP
     * @param ruta cadena de texto con la ruta del archivo a borrar
     */
    @Override
    public void borrarArchivoFTP(String ruta) {
        FTPClient FTPClient = crearConexionFTP();
        String fileString = (ruta.length() > 27) ? "..." + ruta.substring((ruta.length() - 27)) : "...";
        try {
            System.out.println("[Service, FTP] Comienza el proceso de borrado del archivo " + fileString);
            boolean delete = FTPClient.deleteFile(ruta);
            if (delete) {
                System.out.println("[Service, FTP] El archivo [" + fileString + "] fue borrado correctamente");
            } else {
                System.out.println("[Service, FTP] El archivo [" + fileString + "] NO fue borrado");
            }
        } catch (IOException ex) {
            LOGGER.error("[Service, FTP] Error del FTP al momento de intentar borrar el archivo [" + fileString + "] --->\n" + ex.getMessage());
        } finally {
            try {
                cerrarConexionFTP(FTPClient);
            } catch (IOException ex) {
                Logger.getLogger(FtpServiceImpl.class.getName()).log(Level.SEVERE, "[Service, FTP] Ocurrió un error al intentar cerrar la conexión del FTP durante el borrado del archivo [" + fileString + "]", ex.getMessage());
            }
        }
    }

    /**
     * Metodo encargado de guardar un contrato en PDF
     * @param cliente Cadena de texto con el nombre del cliente
     * @param colaborador Cadena de texto con el nombre del colaborador
     * @param stream contrato a almacenar
     * @return Cadena de texto con la url del contrato almacenado dentro del servidor FTP
     */
    @Override
    public String guardarContratoPDF(String cliente, String colaborador, InputStream stream) {
        String modulo = "Contratos";
        String fileName = "Contrato_Entre_" + cliente + "_Y_" + colaborador;
        LOGGER.info("[Service, FTP] Se solicita la carga de un contrato entre un cliente y un colaborador [" + modulo + ", " + cliente + ", " + colaborador + "] dentro del FTP");
        FTPClient FTPClient = null;
        try {
            FTPClient = crearConexionFTP();
            FTPClient.setFileType(FTP.BINARY_FILE_TYPE);
            String path = PATH_SAVE;
            if (!FTPClient.changeWorkingDirectory(path + "/" + modulo)) {
                FTPClient.makeDirectory(path + "/" + modulo);
            }
            if (!FTPClient.changeWorkingDirectory(path + "/" + modulo + "/" + cliente)) {
                FTPClient.makeDirectory(path + "/" + modulo + "/" + cliente);
            }
            if (!FTPClient.changeWorkingDirectory(path + "/" + modulo + "/" + cliente + "/" + colaborador)) {
                FTPClient.makeDirectory(path + "/" + modulo + "/" + cliente + "/" + colaborador);
            }
            String firstRemoteFile = path + "/" + modulo + "/" + cliente + "/" + colaborador + "/" + fileName + ".pdf";
            boolean cargaCompleta = false;
            try (InputStream inputStream = stream) {
                LOGGER.info("[Service, FTP] El archivo  dentro del FTP " + inputStream.read());
                cargaCompleta = FTPClient.storeFile(firstRemoteFile, stream);
                LOGGER.info("[Service, FTP] El archivo [" + modulo + ", " + cliente + ", " + colaborador + ", " + fileName + "] fue subido exitosamente dentro del FTP " + inputStream.available());
                inputStream.close();
            } catch (IOException ioe) {
                LOGGER.error("[Service, FTP] Ocurrio un error al momento de intentar subir el archivo  [" + modulo + ", " + cliente + ", " + colaborador + "] dentro del FTP --> " + ioe.getMessage());
            } finally {
                return cargaCompleta == false ? null : firstRemoteFile;
            }
        } catch (IOException ioe) {
            Logger.getLogger(FtpServiceImpl.class.getName()).log(Level.SEVERE, "[Service, FTP] Error en el FTP --> ", ioe.getMessage());
        } finally {
            try {
                if (FTPClient != null) {
                    cerrarConexionFTP(FTPClient);
                } else {
                    LOGGER.error("[Service, FTP] El cliente FTP no fue inicializado");
                }
            } catch (IOException ioe) {
                Logger.getLogger(FtpServiceImpl.class.getName()).log(Level.SEVERE, "[Service, FTP] Ocurrio un error al intentar cerrar la conexión dentro del FTP --> "+ioe.getMessage(), ioe);
                return null;
            }
        }
        return null;
    }

    private final String SERVER = "10.2.0.29";
    private final String USER = "Payroll";
    private final String PASS = "p4yr0ll";
// private final String PATH_SAVE = "/home/ftp/Payroll/Payroll";           // 4 Production
 private final String PATH_SAVE = "/home/ftp/Payroll/PayRoll_TEST_FILES";           // 4 LOCAL
// private final String PATH_SAVE = "/home/ftp/Payroll/PayRoll_UAT";           // 4 UAT
// private final String PATH_SAVE = "/home/ftp/Payroll/PayRoll_DEV";           // 4 Dev
}
