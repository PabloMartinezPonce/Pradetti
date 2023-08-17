/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

public interface FtpService {
    
    public FTPClient crearConexionFTP();
    
    public FTPClient cerrarConexionFTP(FTPClient ftpClient) throws IOException;
   
public File descargarFileFTP(String fullPath);
    
public String guardarArchivoFTP(MultipartFile file, String modulo, String actor, String directorio);

public void borrarArchivoFTP(String ruta);

public String guardarContratoPDF(String cliente, String colaborador, InputStream stream);
}
