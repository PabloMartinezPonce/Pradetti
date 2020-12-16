/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.service.PDFiTextService;
import com.pradettisanti.payroll.service.ZipService;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service encargado de manejar los archivos zip
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Service
public class ZipServiceImpl implements ZipService {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ZipServiceImpl.class);
    
    @Autowired
    private PDFiTextService pDFiTextService;
    public PDFiTextService getPDFiTextService(){
        return pDFiTextService;
    }
    public void setPDFiTextService(PDFiTextService pDFiTextService){
        this.pDFiTextService = pDFiTextService;
    }
    
    @Override
    public ByteArrayOutputStream crearArchivo(List<Recibo> recibos) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Map<String, ByteArrayOutputStream> recibosSindicales = pDFiTextService.getReciboSindical(recibos);
            
            if(recibos.isEmpty()){
                return null;
            }else{
                LOGGER.info("[Service, ZipServiceImpl] comenzando la generación del archivo zip con los recibos generados {"+recibosSindicales.size()+"}");
                ZipOutputStream zos = new ZipOutputStream(baos);
                zos.setLevel(ZipOutputStream.STORED);
                for (Map.Entry<String, ByteArrayOutputStream> recibo : recibosSindicales.entrySet()) {
                        ZipEntry zipEntry = new ZipEntry(recibo.getKey()+".pdf");
                        zos.putNextEntry(zipEntry);
                        zos.write(recibo.getValue().toByteArray());
                        zos.closeEntry();
                }
                zos.close();
                baos.close();
            }
        } catch (Exception e) {
            LOGGER.error("[Service, ZipServiceImpl] Ocurrio un error al momento de intentar almacenar los recibos dentro del archivo zip --> "+e);
        }
        return baos;
    }
    
}
