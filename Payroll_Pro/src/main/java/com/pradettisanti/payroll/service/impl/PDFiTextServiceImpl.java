/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.pradettisanti.payroll.pojoh.ActaNotarial;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.DatosBancarios;
import com.pradettisanti.payroll.pojoh.DatosBeneficiario;
import com.pradettisanti.payroll.pojoh.DatosDomicilio;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.DatosPersonales;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.PoderLegal;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.pojoh.ZonaSm;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.FtpService;
import com.pradettisanti.payroll.service.PDFiTextService;
import com.pradettisanti.payroll.service.ZonasSmService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service encargado de crear los pdfs generados dentro del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Service
public class PDFiTextServiceImpl implements PDFiTextService {
    
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PDFiTextServiceImpl.class);
    
    @Autowired
    private ZonasSmService zonasSmService;
    public ZonasSmService getZonasSmService(){
        return zonasSmService;
    }
    public void setZonasSmService(ZonasSmService zonasSmService){
        this.zonasSmService = zonasSmService;
    }
    
    @Autowired
    private ServletContext servletContext;
    public ServletContext getServletContext(){
        return servletContext;
    }
    public void setServletContext(ServletContext servletContext){
        this.servletContext = servletContext;
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
    private AgremiadoService agremiadoService;
    public AgremiadoService getAgremiadoService(){
        return agremiadoService;
    }
    public void setAgremiadoService(AgremiadoService agremiadoService){
        this.agremiadoService = agremiadoService;
    }
    
    @Override
    public ByteArrayOutputStream createAgreement(Contratista contratista, Agremiado agremiado, String htmlCode) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();        
        try {
            // step 1
            Document document = new Document(PageSize.LEGAL, LEFT, RIGHT, TOP, BOTTOM);
            // step 2
             PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
             pdfWriter.setInitialLeading(12);
            // step 3
            document.open();
            // step 4
            document.addAuthor("Payroll");
            document.addCreationDate();
            document.addTitle("Contrato entre un contratista y un colaborador");
            document.addCreator("Payroll");
            document.addKeywords("Contrato,contratista,Colaborador,"+contratista.getNombreEmpresa()+"");
            document.addSubject("Contrato entre contratista y colaborador");
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new ByteArrayInputStream(htmlCode.getBytes("UTF-8")), new ByteArrayInputStream(CSS.getBytes("UTF-8")), Charset.forName("utf-8"));
            // step 5
            document.close();
 
         }catch(DocumentException | IOException e) {
            LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar el archivo pdf con el contrato entre un Contratista y un Colaborador");
        }
        return baos;
    }

    @Override
    public ByteArrayOutputStream createAgreement(Contratista contratista, Cliente cliente, String htmlCode) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();        
        try {
            // step 1
            Document document = new Document(PageSize.LEGAL, LEFT, RIGHT, TOP, BOTTOM);
            // step 2
             PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
             pdfWriter.setInitialLeading(12);
            // step 3
            document.open();
            // step 4
            document.addAuthor("Payroll");
            document.addCreationDate();
            document.addTitle("Contrato entre un cliente y un contratista");
            document.addCreator("Payroll");
            document.addKeywords("Contrato,Cliente,contratista,"+cliente.getNombreEmpresa()+"");
            document.addSubject("Contrato entre cliente y contratista");
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new ByteArrayInputStream(htmlCode.getBytes("UTF-8")), new ByteArrayInputStream(CSS.getBytes("UTF-8")), Charset.forName("utf-8"));
            // step 5
            document.close();
 
         }catch(DocumentException | IOException e) {
            LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar el archivo pdf con el contrato entre un Contratista y un cliente");
        }
        return baos;
    }
    
    @Override
    public ByteArrayOutputStream getKardex(Cliente cliente) {
        LOGGER.info("[Service, PDFiText] Iniciando la generación del kardex de un cliente {"+cliente.getNombreEmpresa().toUpperCase()+'}');
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       
           try {
               // step 1
                Document document = new Document(PageSize.LETTER, LEFT-10, RIGHT-10, TOP-20, BOTTOM-20);
                // step 2
                 PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
                // step 3
                document.open();
                // step 4
                document.addAuthor("Payroll");
                document.addCreationDate();
                document.addTitle("Kardex de \""+cliente.getNombreEmpresa()+"\"");
                document.addCreator("Payroll");
                document.addKeywords("Contrato,Cliente,"+cliente.getNombreEmpresa()+"");
                document.addSubject("Kardex del Cliente "+cliente.getNombreEmpresa());
                // step 6
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                //LOGO             
                InputStream is = servletContext.getResourceAsStream("/frontend/img/logo.png");
                BufferedImage bufferedImage = ImageIO.read(is);
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", arrayOutputStream);
                Image iTextImage = Image.getInstance(arrayOutputStream.toByteArray());
                iTextImage.scalePercent(50f);
               PdfPCell cell = new PdfPCell( new PdfPCell(iTextImage,false) );
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(4);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               //HEADER               
               cell = new PdfPCell(new Phrase("Kardex del cliente", FONT_TITULAR_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase(cliente.getNombreEmpresa().toUpperCase(), FONT_TITULAR));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(3);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);            
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DATOS PERSONALES
               cell = new PdfPCell(new Phrase("DATOS PERSONALES", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Representante legal", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(cliente.getRepresentanteLegal().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(cliente.getRfc().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("Fecha de Registro", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(formatearFecha(cliente.getFechaRegistroPublico()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Giro comercial", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(cliente.getGiro().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Tipo de sociedad", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(cliente.getTipoSociedad().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Objeto Social", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(cliente.getFinSocial().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
                ZonaSm zonaSalarial = zonasSmService.getZonaSM(cliente.getZonaSm());
               
               cell = new PdfPCell(new Phrase("Zona salarial", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(zonaSalarial.getZona().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("Año salarial", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(zonaSalarial.getAnio().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Salario mínimo", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("$ "+zonaSalarial.getSalario().toString()+" MXN", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("\n", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
                //DATOS NOTARIAL                    
                ActaNotarial actaNotarial = null;
                 if(!cliente.getActaNotarialList().isEmpty()){
                     actaNotarial = cliente.getActaNotarialList().get(0);
                }
                 if(actaNotarial == null){

                    cell = new PdfPCell(new Phrase("ACTA NOTARIAL", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(19);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase("NO ENCONTRADA", FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(5);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    table.addCell(cell);
                 
                 }else{
                    cell = new PdfPCell(new Phrase("DATOS NOTARIALES", FONT_SECTION));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBackgroundColor(RED);
                    cell.setRowspan(1);
                    cell.setColspan(6);
                    cell.setBorder(0);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("ACTA NOTARIAL", FONT_MAIN_TWO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(6);
                    cell.setBorder(0);
                    cell.setMinimumHeight(19);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 

                    cell = new PdfPCell(new Phrase("Instrumento", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getInstrumento().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(5);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Volumen", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getVolumen().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase("Fecha de Volumen", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(formatearFecha(actaNotarial.getFechaVolumen()), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Notario", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getNotario().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase("Notaria", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getNumeroNotarial().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);      

                    cell = new PdfPCell(new Phrase("Dirección", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getDireccionNotario().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(5);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    if(!actaNotarial.getTienePoderLegal()){  

                         cell = new PdfPCell(new Phrase("PODER LEGAL", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase("NO APLICA", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                        cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         table.addCell(cell);

                    }else if( actaNotarial.getTienePoderLegal() && actaNotarial.getPoderLegal() == null){  

                         cell = new PdfPCell(new Phrase("PODER LEGAL", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase("NO ENCONTRADO", FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                        cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         table.addCell(cell);

                    }else{

                         cell = new PdfPCell(new Phrase("PODER LEGAL", FONT_MAIN_TWO));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(6);
                         cell.setBorder(0);
                         cell.setMinimumHeight(19);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell); 

                         PoderLegal poderLegal = actaNotarial.getPoderLegal();

                         cell = new PdfPCell(new Phrase("Representante legal", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getRepresentanteLegal().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);

                         cell = new PdfPCell(new Phrase("Instrumento", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getInstrumento().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);

                         cell = new PdfPCell(new Phrase("Volumen", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getVolumen().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(2);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase("Fecha de Volumen", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(formatearFecha(poderLegal.getFechaVolumen()), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setRowspan(1);
                         cell.setColspan(2);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);

                         cell = new PdfPCell(new Phrase("Notario", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getNotario().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(3);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase("Notaria", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getNumeroNotarial().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);      

                         cell = new PdfPCell(new Phrase("Dirección", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getDireccionNotario().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);

                    }         
                 }
               EmpresasDomicilio empresasDomicilioFiscal = getDomicilioFiscal(cliente.getEmpresasDomicilioList());
               EmpresasDomicilio empresasDomicilioNotificacion = getDomicilioNotificacion(cliente.getEmpresasDomicilioList());
                           
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DOMICILIO FISCAL
               cell = new PdfPCell(new Phrase("DOMICILIO FISCAL", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setMinimumHeight(20);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               if(empresasDomicilioFiscal == null){              
                    cell = new PdfPCell(new Phrase("DESCONOCIDO", FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(6);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
               }else{
                  
                    cell = new PdfPCell(new Phrase("Calle", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getCalle().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("Número", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getNumero().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                  
                    cell = new PdfPCell(new Phrase("Colonia", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getColonia().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("C.P.", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getCodigoPostal().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);        
                  
                    cell = new PdfPCell(new Phrase("Ciudad", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getCiudad().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("Estado", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getEstado().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);       
               
               }               
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DOMICILIO NOTIFICACIÓN
               cell = new PdfPCell(new Phrase("DOMICILIO NOTIFICACIÓN", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setMinimumHeight(20);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               if(empresasDomicilioNotificacion == null){       
                    cell = new PdfPCell(new Phrase("DESCONOCIDO", FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(6);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
               }else{
                  
                    cell = new PdfPCell(new Phrase("Calle", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getCalle().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("Número", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getNumero().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                  
                    cell = new PdfPCell(new Phrase("Colonia", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getColonia().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("C.P.", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getCodigoPostal().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);    
                    cell = new PdfPCell(new Phrase("Ciudad", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getCiudad().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("Estado", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getEstado().toUpperCase(), FONT_PLAIN));                    
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);  
               }
                // step 7
                document.add(table);
                 // step 8
                document.close();     
                table = null;
                Runtime.getRuntime().gc();
               
           } catch (DocumentException | IOException me) {
            LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar el kardex pdf de un cliente -> "+me);
           }catch(NullPointerException npe){
               LOGGER.error("[Service, PDFiText] Algún valor consultado resultó ser nulo y no se puede completar la generación del kardex del cliente -> "+npe);
           }catch(Exception e){
               LOGGER.error("[Service, PDFiText] Ocurrió una excepción no esperada al momento de generar el kardex del cliente -> "+e);
           }
           return baos;
    }

    @Override
    public ByteArrayOutputStream getKardex(Contratista contratista) {
        LOGGER.info("[Service, PDFiText] Iniciando la generación del kardex de un contratista {"+contratista.getNombreEmpresa().toUpperCase()+'}');
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       
           try {
               // step 1
                Document document = new Document(PageSize.LETTER, LEFT-10, RIGHT-10, TOP-20, BOTTOM-20);
                // step 2
                 PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
                // step 3
                document.open();
                // step 4
                document.addAuthor("Payroll");
                document.addCreationDate();
                document.addTitle("Kardex de \""+contratista.getNombreEmpresa()+"\"");
                document.addCreator("Payroll");
                document.addKeywords("Contrato,contratista,"+contratista.getNombreEmpresa()+"");
                document.addSubject("Kardex del contratista "+contratista.getNombreEmpresa());
                // step 6
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                //LOGO             
                InputStream is = servletContext.getResourceAsStream("/frontend/img/logo.png");
                BufferedImage bufferedImage = ImageIO.read(is);
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", arrayOutputStream);
                Image iTextImage = Image.getInstance(arrayOutputStream.toByteArray());
                iTextImage.scalePercent(50f);
               PdfPCell cell = new PdfPCell( new PdfPCell(iTextImage,false) );
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(4);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               //HEADER               
               cell = new PdfPCell(new Phrase("Kardex del contratista", FONT_TITULAR_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase(contratista.getNombreEmpresa().toUpperCase(), FONT_TITULAR));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(3);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);            
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DATOS PERSONALES
               cell = new PdfPCell(new Phrase("DATOS PERSONALES", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Representante legal", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(contratista.getRepresentateLegal().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(contratista.getRfc().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Cláusula administrativa", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(contratista.getClausulaAdministrativa().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Descripción", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(contratista.getDescripcionClausula().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
                       
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
                //DATOS NOTARIAL                    
                ActaNotarial actaNotarial = null;
                 if(!contratista.getActaNotarialList().isEmpty()){
                     actaNotarial = contratista.getActaNotarialList().get(0);
                }
                 if(actaNotarial == null){

                    cell = new PdfPCell(new Phrase("ACTA NOTARIAL", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(19);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase("NO ENCONTRADA", FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(5);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    table.addCell(cell);
                 
                 }else{
                    cell = new PdfPCell(new Phrase("DATOS NOTARIALES", FONT_SECTION));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setBackgroundColor(RED);
                    cell.setRowspan(1);
                    cell.setColspan(6);
                    cell.setBorder(0);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("ACTA NOTARIAL", FONT_MAIN_TWO));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(6);
                    cell.setBorder(0);
                    cell.setMinimumHeight(19);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 

                    cell = new PdfPCell(new Phrase("Instrumento", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getInstrumento().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(5);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Volumen", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getVolumen().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase("Fecha de Volumen", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(formatearFecha(actaNotarial.getFechaVolumen()), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase("Notario", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getNotario().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase("Notaria", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getNumeroNotarial().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);      

                    cell = new PdfPCell(new Phrase("Dirección", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(actaNotarial.getDireccionNotario().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(5);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);

                    if(!actaNotarial.getTienePoderLegal()){  

                         cell = new PdfPCell(new Phrase("PODER LEGAL", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase("NO APLICA", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                        cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         table.addCell(cell);

                    }else if( actaNotarial.getTienePoderLegal() && actaNotarial.getPoderLegal() == null){  

                         cell = new PdfPCell(new Phrase("PODER LEGAL", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase("NO ENCONTRADO", FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                        cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         table.addCell(cell);

                    }else{

                         cell = new PdfPCell(new Phrase("PODER LEGAL", FONT_MAIN_TWO));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(6);
                         cell.setBorder(0);
                         cell.setMinimumHeight(19);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell); 

                         PoderLegal poderLegal = actaNotarial.getPoderLegal();

                         cell = new PdfPCell(new Phrase("Representante legal", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getRepresentanteLegal().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);

                         cell = new PdfPCell(new Phrase("Instrumento", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getInstrumento().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);

                         cell = new PdfPCell(new Phrase("Volumen", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getVolumen().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(2);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase("Fecha de Volumen", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(formatearFecha(poderLegal.getFechaVolumen()), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setRowspan(1);
                         cell.setColspan(2);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);

                         cell = new PdfPCell(new Phrase("Notario", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getNotario().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(3);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase("Notaria", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getNumeroNotarial().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);      

                         cell = new PdfPCell(new Phrase("Dirección", FONT_MAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setMinimumHeight(17);
                         cell.setRowspan(1);
                         cell.setColspan(1);
                         cell.setBorder(0);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);               
                         cell = new PdfPCell(new Phrase(poderLegal.getDireccionNotario().toUpperCase(), FONT_PLAIN));
                         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setRowspan(1);
                         cell.setColspan(5);
                         cell.setBorder(0);
                        cell.setBorderWidthBottom(0.5f);
                        cell.setMinimumHeight(17);
                         cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                         table.addCell(cell);

                    }         
                 }
               EmpresasDomicilio empresasDomicilioFiscal = getDomicilioFiscal(contratista.getEmpresasDomicilioList());
               EmpresasDomicilio empresasDomicilioNotificacion = getDomicilioNotificacion(contratista.getEmpresasDomicilioList());
                           
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DOMICILIO FISCAL
               cell = new PdfPCell(new Phrase("DOMICILIO FISCAL", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setMinimumHeight(20);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               if(empresasDomicilioFiscal == null){              
                    cell = new PdfPCell(new Phrase("DESCONOCIDO", FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(6);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
               }else{
                  
                    cell = new PdfPCell(new Phrase("Calle", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getCalle().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("Número", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getNumero().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                  
                    cell = new PdfPCell(new Phrase("Colonia", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getColonia().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("C.P.", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getCodigoPostal().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);        
                  
                    cell = new PdfPCell(new Phrase("Ciudad", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getCiudad().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("Estado", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioFiscal.getEstado().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);       
               
               }               
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DOMICILIO NOTIFICACIÓN
               cell = new PdfPCell(new Phrase("DOMICILIO NOTIFICACIÓN", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setMinimumHeight(20);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               if(empresasDomicilioNotificacion == null){       
                    cell = new PdfPCell(new Phrase("DESCONOCIDO", FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(6);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
               }else{
                  
                    cell = new PdfPCell(new Phrase("Calle", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getCalle().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("Número", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getNumero().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                  
                    cell = new PdfPCell(new Phrase("Colonia", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getColonia().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(3);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("C.P.", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getCodigoPostal().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);    
                    cell = new PdfPCell(new Phrase("Ciudad", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getCiudad().toUpperCase(), FONT_PLAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell); 
                    cell = new PdfPCell(new Phrase("Estado", FONT_MAIN));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setMinimumHeight(17);
                    cell.setRowspan(1);
                    cell.setColspan(1);
                    cell.setBorder(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);               
                    cell = new PdfPCell(new Phrase(empresasDomicilioNotificacion.getEstado().toUpperCase(), FONT_PLAIN));                    
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setRowspan(1);
                    cell.setColspan(2);
                    cell.setBorder(0);
                    cell.setBorderWidthBottom(0.5f);
                    cell.setMinimumHeight(17);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);  
               }
                // step 7
                document.add(table);
                 // step 8
                document.close();     
                table = null;
                Runtime.getRuntime().gc();
               
           } catch (DocumentException | IOException e) {
            LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar el kardex pdf de un contratista -> "+e);
           }catch(NullPointerException npe){
               LOGGER.error("[Service, PDFiText] Algún valor consultado resultó ser nulo y no se puede completar la generación del kardex del contratista -> "+npe);
           }catch(Exception e){
               LOGGER.error("[Service, PDFiText] Ocurrió una excepción no esperada al momento de generar el kardex del contratista -> "+e);
           }
           return baos;
    }

    @Override
    public ByteArrayOutputStream getKardex(Sindicato sindicato) {
        LOGGER.info("[Service, PDFiText] Iniciando la generación del kardex de un sindicato {"+sindicato.getNombreCorto().toUpperCase()+'}');
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       
           try {
               // step 1
                Document document = new Document(PageSize.LETTER, LEFT-10, RIGHT-10, TOP-20, BOTTOM-20);
                // step 2
                 PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
                // step 3
                document.open();
                // step 4
                document.addAuthor("Payroll");
                document.addCreationDate();
                document.addTitle("Kardex de \""+sindicato.getNombreCorto()+"\"");
                document.addCreator("Payroll");
                document.addKeywords("Contrato,sindicato,"+sindicato.getNombreCorto()+"");
                document.addSubject("Kardex del sindicato "+sindicato.getNombreSindicato());
                // step 6
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                //LOGO             
                InputStream is = servletContext.getResourceAsStream("/frontend/img/logo.png");
                BufferedImage bufferedImage = ImageIO.read(is);
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", arrayOutputStream);
                Image iTextImage = Image.getInstance(arrayOutputStream.toByteArray());
                iTextImage.scalePercent(50f);
               PdfPCell cell = new PdfPCell( new PdfPCell(iTextImage,false) );
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(4);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               //HEADER               
               cell = new PdfPCell(new Phrase("Kardex del sindicato", FONT_TITULAR_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase(sindicato.getNombreCorto().toUpperCase(), FONT_TITULAR));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(3);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);            
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DATOS PERSONALES
               cell = new PdfPCell(new Phrase("DATOS PERSONALES", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Nombre ", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(sindicato.getNombreSindicato().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(sindicato.getRfc().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("Percepciones", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(sindicato.getPercepciones()==null?"No aplica":sindicato.getPercepciones().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("Deducciones", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(sindicato.getDeducciones()==null?"No aplica":sindicato.getDeducciones().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(5);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               
                cell = new PdfPCell(new Phrase("LOGO IZQUIERDO", FONT_MAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(3);
                cell.setBorder(0);
                cell.setMinimumHeight(19);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("LOGO DERECHO", FONT_MAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(3);
                cell.setBorder(0);
                cell.setMinimumHeight(19);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
                
                File descargarFileFTP = ftpService.descargarFileFTP(sindicato.getUrlLogoIzquierda());
                
                InputStream fis = new FileInputStream(descargarFileFTP);
                BufferedImage bufferedImageIzq = ImageIO.read(fis);
                ByteArrayOutputStream arrayOutputStreamIzq = new ByteArrayOutputStream();
                ImageIO.write(bufferedImageIzq, "jpeg", arrayOutputStreamIzq);
                Image iTextImageIzq = Image.getInstance(arrayOutputStreamIzq.toByteArray());
                iTextImageIzq.scaleToFit(new Rectangle(150, 150));
               PdfPCell cellIZq = new PdfPCell( new PdfPCell(iTextImageIzq,false) );
               cellIZq.setHorizontalAlignment(Element.ALIGN_CENTER);
               cellIZq.setRowspan(1);
               cellIZq.setColspan(3);
               cellIZq.setBorder(0);
               cellIZq.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cellIZq);
                
                descargarFileFTP = ftpService.descargarFileFTP(sindicato.getUrlLogoDerecha());
                
                InputStream fist = new FileInputStream(descargarFileFTP);
                BufferedImage bufferedImageDer = ImageIO.read(fist);
                ByteArrayOutputStream arrayOutputStreamDer = new ByteArrayOutputStream();
                ImageIO.write(bufferedImageDer, "jpeg", arrayOutputStreamDer);
                Image iTextImageDer = Image.getInstance(arrayOutputStreamDer.toByteArray());
                iTextImageDer.scaleToFit(new Rectangle(150, 150));
               PdfPCell cellDer = new PdfPCell( new PdfPCell(iTextImageDer,false) );
               cellDer.setHorizontalAlignment(Element.ALIGN_CENTER);
               cellDer.setRowspan(1);
               cellDer.setColspan(3);
               cellDer.setBorder(0);
               cellDer.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cellDer);
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
                
                // step 7
                document.add(table);
                 // step 8
                document.close();     
                table = null;
                Runtime.getRuntime().gc();
               
           } catch (DocumentException | IOException me) {
                LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar el kardex pdf de un sindicato -> "+me);
           }catch(NullPointerException npe){
               LOGGER.error("[Service, PDFiText] Algún valor consultado resultó ser nulo y no se puede completar la generación del kardex del sindicato -> "+npe);
           }catch(Exception e){
               LOGGER.error("[Service, PDFiText] Ocurrió una excepción no esperada al momento de generar el kardex del sindicato -> "+e);
           }
           return baos;
    }

    @Override
    public ByteArrayOutputStream getKardex(Agremiado agremiado) {
        LOGGER.info("[Service, PDFiText] Iniciando la generación del kardex de un colaborador {"+agremiado.getDatosPersonales().getNombre().toUpperCase()+' '+agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase())+'}');
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
           try {
               // step 1
                Document document = new Document(PageSize.LETTER, LEFT-10, RIGHT-10, TOP-20, BOTTOM-20);
                // step 2
                 PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
                // step 3
                document.open();
                // step 4
                document.addAuthor("Payroll");
                document.addCreationDate();
                document.addTitle("Kardex de \""+agremiado.getDatosPersonales().getNombre().toUpperCase()+' '+agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase())+"\"");
                document.addCreator("Payroll");
                document.addKeywords("Contrato,colaborador,"+agremiado.getDatosPersonales().getNombre().toUpperCase()+' '+agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase())+"");
                document.addSubject("Kardex del colaborador "+agremiado.getDatosPersonales().getNombre().toUpperCase()+' '+agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase()+' '+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase()));
                // step 6
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                //LOGO             
                InputStream is = servletContext.getResourceAsStream("/frontend/img/logo.png");
                BufferedImage bufferedImage = ImageIO.read(is);
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", arrayOutputStream);
                Image iTextImage = Image.getInstance(arrayOutputStream.toByteArray());
                iTextImage.scalePercent(50f);
               PdfPCell cell = new PdfPCell( new PdfPCell(iTextImage,false) );
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(4);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               //HEADER                 
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);             
               cell = new PdfPCell(new Phrase("Kardex del colaborador", FONT_TITULAR_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(RED);
               cell.setRowspan(3);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DATOS PERSONALES
               cell = new PdfPCell(new Phrase("DATOS PERSONALES", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                            
                              
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getNombre().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getApellidoPaterno().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase(((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("Nombre (s)", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase("Apellido paterno", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);    
               cell = new PdfPCell(new Phrase("Apellido materno", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);  
               
               cell = new PdfPCell(new Phrase(formatearFecha(agremiado.getDatosPersonales().getFechaNacimiento()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getCurp().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getRfc().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("Fecha de nacimiento", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase("CURP", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);    
               cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);        
               
               cell = new PdfPCell(new Phrase("Edad", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getEdad().toUpperCase()+" AÑOS", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("Lugar de nacimiento", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getLugarNacimiento().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Sexo", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getSexo().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("Nacionalidad", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getNacionalidad().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Email", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getEmail().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("Teléfono", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(isNull(agremiado.getDatosPersonales().getTelefono()).toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("Escolaridad", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(agremiado.getDatosPersonales().getEscolaridad().toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("Estado civil", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               String estadoCivil = agremiado.getDatosPersonales().getEstadoCivil().toUpperCase();
               cell = new PdfPCell(new Phrase(estadoCivil, FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("Régimen matrimonial", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase((isNull(agremiado.getDatosPersonales().getRegimenMatrimonial()).toUpperCase()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);      
               cell = new PdfPCell(new Phrase("Hijos", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase((isNull(agremiado.getDatosPersonales().getHijos()).toUpperCase()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);        
               
               
               //DATOS DEL DOMICILIO
               cell = new PdfPCell(new Phrase("DATOS DEL DOMICILIO", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);  
            
            DatosDomicilio datosDomicilio = agremiado.getDatosDomicilio();
               
           if(datosDomicilio == null){         
                        cell = new PdfPCell(new Phrase("NO APLICA", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
               }else{               
                        cell = new PdfPCell(new Phrase("Calle", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getCalle()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("Número", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getNumero()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 

                        cell = new PdfPCell(new Phrase("Colonia", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getColonia()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("C.P.", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getCodigoPostal()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Ciudad", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getCiudad()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("Municipio", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getMunicipio()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 

                        cell = new PdfPCell(new Phrase("Estado", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getEstado()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(4);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Tipo de vivienda", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getTipoVivienda()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("Tipo de via", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getTipoVia()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Entre calles", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getEntreCalles()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(5);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Referencia", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosDomicilio.getReferencia()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(5);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
           }
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               
               //DATOS LABORALES
               cell = new PdfPCell(new Phrase("DATOS LABORALES", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);  
             
            DatosLaborales  datosLaborales = agremiado.getDatosLaborales();
               
            if(datosLaborales == null){         
                        cell = new PdfPCell(new Phrase("NO APLICA", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
               }else{
                            cell = new PdfPCell(new Phrase("Cliente", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(datosLaborales.getClienteObj().getNombreEmpresa().toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(5);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("Contratista", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(datosLaborales.getContratistaObj().getNombreEmpresa().toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(5);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("Sindicato", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(((datosLaborales.getSindicatoObj() == null)?"NO APLICA":datosLaborales.getSindicatoObj().getNombreCorto().toUpperCase()), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(5);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               

                            cell = new PdfPCell(new Phrase("NSS", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(isNull(datosLaborales.getNumeroSeguro()).toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell); 
                            cell = new PdfPCell(new Phrase("Fecha de ingreso", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(formatearFecha(datosLaborales.getFechaInicioContrato()), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);             
                            
                            if(datosLaborales.getPercepcionBasadaEnSUP() == true){
                                cell = new PdfPCell(new Phrase("SUP", FONT_MAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                 cell.setMinimumHeight(17);
                                cell.setRowspan(1);
                                cell.setColspan(1);
                                cell.setBorder(0);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);               
                                cell = new PdfPCell(new Phrase((datosLaborales.getSalarioUnicoProfesional().getOficio()), FONT_PLAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setRowspan(1);
                                cell.setColspan(2);
                                cell.setBorder(0);
                                 cell.setBorderWidthBottom(0.5f);
                                 cell.setMinimumHeight(17);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);
                                
                                cell = new PdfPCell(new Phrase("Sueldo diario", FONT_MAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                 cell.setMinimumHeight(17);
                                cell.setRowspan(1);
                                cell.setColspan(1);
                                cell.setBorder(0);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);
                                Double salarioDiario = datosLaborales.getSalarioUnicoProfesional().getPesosDiarios();
                                String salarioDiarioSUP = salarioDiario.toString();
                                cell = new PdfPCell(new Phrase(("$ "+ salarioDiarioSUP +" MXN"), FONT_PLAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setRowspan(1);
                                cell.setColspan(2);
                                cell.setBorder(0);
                                 cell.setBorderWidthBottom(0.5f);
                                 cell.setMinimumHeight(17);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell); 
                            }
                            else{
                                cell = new PdfPCell(new Phrase("Sueldo mensual", FONT_MAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                 cell.setMinimumHeight(17);
                                cell.setRowspan(1);
                                cell.setColspan(1);
                                cell.setBorder(0);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);               
                                cell = new PdfPCell(new Phrase("$ "+(datosLaborales.getSueldoMensual()==null?"0,000.00":datosLaborales.getSueldoMensual())+" MXN".toUpperCase(), FONT_PLAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setRowspan(1);
                                cell.setColspan(2);
                                cell.setBorder(0);
                                 cell.setBorderWidthBottom(0.5f);
                                 cell.setMinimumHeight(17);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell); 
                                cell = new PdfPCell(new Phrase("Salario diario", FONT_MAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                 cell.setMinimumHeight(17);
                                cell.setRowspan(1);
                                cell.setColspan(1);
                                cell.setBorder(0);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);               
                                cell = new PdfPCell(new Phrase("$ "+(datosLaborales.getSalarioDiario()==null?"0,000.00":datosLaborales.getSalarioDiario())+" MXN", FONT_PLAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setRowspan(1);
                                cell.setColspan(2);
                                cell.setBorder(0);
                                 cell.setBorderWidthBottom(0.5f);
                                 cell.setMinimumHeight(17);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);             

                                cell = new PdfPCell(new Phrase("NSM IMSS", FONT_MAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                 cell.setMinimumHeight(17);
                                cell.setRowspan(1);
                                cell.setColspan(1);
                                cell.setBorder(0);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);               
                                cell = new PdfPCell(new Phrase("$ "+(datosLaborales.getSalariosImss()==null?"0,000.00":datosLaborales.getSalariosImss())+" MXN".toUpperCase(), FONT_PLAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setRowspan(1);
                                cell.setColspan(2);
                                cell.setBorder(0);
                                 cell.setBorderWidthBottom(0.5f);
                                 cell.setMinimumHeight(17);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell); 
                                cell = new PdfPCell(new Phrase("SD Integrado", FONT_MAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                 cell.setMinimumHeight(17);
                                cell.setRowspan(1);
                                cell.setColspan(1);
                                cell.setBorder(0);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);               
                                cell = new PdfPCell(new Phrase("$ "+(datosLaborales.getSalarioDiarioIntegrado()==null?"0,000.00":datosLaborales.getSalarioDiarioIntegrado())+" MXN", FONT_PLAIN));
                                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                cell.setRowspan(1);
                                cell.setColspan(2);
                                cell.setBorder(0);
                                 cell.setBorderWidthBottom(0.5f);
                                 cell.setMinimumHeight(17);
                                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                                table.addCell(cell);             
                            }
                            cell = new PdfPCell(new Phrase("Periodo de pago", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell); 
                            cell = new PdfPCell(new Phrase((datosLaborales.getTipoNominaObj() == null) ? "" : datosLaborales.getTipoNominaObj().getTipoNomina().toUpperCase(), FONT_PLAIN) );
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                            
                            cell = new PdfPCell(new Phrase("Esquema de pago", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase((datosLaborales.getEsquemaPago() == null) ? "" : datosLaborales.getEsquemaPago().getDescripcion().toUpperCase(), FONT_PLAIN) );
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);           

                            cell = new PdfPCell(new Phrase("Tipo de contrato", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);  
                            cell = new PdfPCell(new Phrase( (datosLaborales.getTipoContratoObj() == null) ? "" : datosLaborales.getTipoContratoObj().getDescripcion().toUpperCase(), FONT_PLAIN) );
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell); 
                            cell = new PdfPCell(new Phrase("Periodo contratado", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(((datosLaborales.getPeriodoContrato() == null)?"NO APLICA":datosLaborales.getPeriodoContrato().toUpperCase()), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);           

                            cell = new PdfPCell(new Phrase("Actividad profesional", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell); 
                            cell = new PdfPCell(new Phrase(isNull(datosLaborales.getActividadProfesional()).toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell); 
                            cell = new PdfPCell(new Phrase("Departamento", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(isNull(datosLaborales.getAreaDepartamento()).toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("Lugar de trabajo", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(isNull(datosLaborales.getLugarTrabajo()).toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(5);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("Tarjeta TDU", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(isNull(datosLaborales.getTarjetaTdu()).toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("Gafete", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(isNull(datosLaborales.getCredencialLaboral()).toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("Antigüedad", FONT_MAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);               
                            cell = new PdfPCell(new Phrase(isNull(datosLaborales.getReconocimientoAntiguedad()).toUpperCase(), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase("Crédito infonavit", FONT_MAIN)); 
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                             cell.setMinimumHeight(17);
                            cell.setRowspan(1);
                            cell.setColspan(1);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);              
                            cell = new PdfPCell(new Phrase(((datosLaborales.getNumeroInfonavit() == null)?"N/A":datosLaborales.getNumeroInfonavit().toUpperCase()), FONT_PLAIN));
                            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cell.setRowspan(1);
                            cell.setColspan(3);
                            cell.setBorder(0);
                             cell.setBorderWidthBottom(0.5f);
                             cell.setMinimumHeight(17);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
                            cell = new PdfPCell(new Phrase("\n"));
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell.setRowspan(1);
                            cell.setColspan(2);
                            cell.setBorder(0);
                            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                            table.addCell(cell);
           }
           
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               
               //DATOS BANCARIOS
               cell = new PdfPCell(new Phrase("DATOS BANCARIOS", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
                
               DatosBancarios datosBancarios = agremiado.getDatosBancarios();
               if(datosBancarios == null){         
                        cell = new PdfPCell(new Phrase("NO APLICA", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
               }else{
                        cell = new PdfPCell(new Phrase("Banco", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBancarios.getNombreBanco()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(5);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Titular", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBancarios.getTitular()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(5);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("CLABE", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBancarios.getClabe()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Cuenta", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBancarios.getNumeroCuenta()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
           }
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               
               //DATOS DEL BENEFICIARIO
               cell = new PdfPCell(new Phrase("DATOS DEL BENEFICIARIO", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
                DatosBeneficiario datosBeneficiario = agremiado.getDatosBeneficiario();
               if(datosBeneficiario == null){          
                        cell = new PdfPCell(new Phrase("NO APLICA", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
               }else{
                        cell = new PdfPCell(new Phrase("Nombre", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getNombre()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(5);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase("Porcentaje", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getPorcentaje()).toUpperCase()+" %", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Fecha de Nacimiento", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(formatearFecha(datosBeneficiario.getFechaNacimiento()), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase("CURP", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getCurp()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Parentesco", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getParentesco()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase("Calle", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getCalle()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Número", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getNumero()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase("Colonia", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getColonia()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("C.P.", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getCodigoPostal()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase("Ciudad", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getCiudad()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Municipio", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getMunicipio()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        
                        cell = new PdfPCell(new Phrase("Estado", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosBeneficiario.getEstado()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                                      
               }
                        
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                
               
               //OBSERVACIONES
               cell = new PdfPCell(new Phrase("OBSERVACIONES", FONT_SECTION));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setBackgroundColor(RED);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
                
                String observaciones = getObservacionesSinHTML(agremiado.getObservaciones());
                cell = new PdfPCell(new Phrase( ((observaciones == null)?"SIN OBSERVACIONES":observaciones.toUpperCase()) , FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorderWidth(0.5f);
                cell.setBorderWidthTop(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);         
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
                // step 7
                document.add(table);
                 // step 8
                document.close();     
                table = null;
                Runtime.getRuntime().gc();
               
           } catch (DocumentException | IOException me) {
            LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar el kardex pdf de un colaborador -> "+me);
           }catch(NullPointerException npe){
               LOGGER.error("[Service, PDFiText] Algún valor consultado resultó ser nulo y no se puede completar la generación del kardex del colaborador -> "+npe.getMessage(),npe);
           }catch(Exception e){
               LOGGER.error("[Service, PDFiText] Ocurrió una excepción no esperada al momento de generar el kardex del colaborador -> "+e);
           }
           return baos;
    }
    
       @Override
    public ByteArrayOutputStream getReciboSindical(Recibo recibo) {
        LOGGER.info("[Service, PDFiText] Iniciando la generación de un recibo sindical {"+recibo.getIdRecibo()+','+recibo.getDiaDeRegistro()+'}');
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       
           try {
               String nombre = ""+isNull(recibo.getAgremiado().getDatosPersonales().getNombre()).toUpperCase()+' '+isNull(recibo.getAgremiado().getDatosPersonales().getApellidoPaterno()).toUpperCase()+' '+isNull(recibo.getAgremiado().getDatosPersonales().getApellidoMaterno()).toUpperCase();
               Sindicato sindicato = recibo.getSindicato();
               // step 1
                Document document = new Document(PageSize.LETTER, LEFT-10, RIGHT-10, TOP-40, BOTTOM-40);
                // step 2
                 PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
                // step 3
                document.open();
                // step 4
                document.addAuthor("Payroll");
                document.addCreationDate();
                document.addTitle("recibo sindical de \""+nombre+"\"");
                document.addCreator("Payroll");
                document.addKeywords("recibo,sindicato,"+nombre+"");
                document.addSubject("recibo del sindical "+nombre);
                // step 6
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                
               //HEADER               
               PdfPCell cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthRight(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               File descargarFileFTPIzq = ftpService.descargarFileFTP(sindicato.getUrlLogoIzquierda());
                
                InputStream fis = new FileInputStream(descargarFileFTPIzq);
                BufferedImage bufferedImageIzq = ImageIO.read(fis);
                ByteArrayOutputStream arrayOutputStreamIzq = new ByteArrayOutputStream();
                ImageIO.write(bufferedImageIzq, "jpeg", arrayOutputStreamIzq);
                Image iTextImageIzq = Image.getInstance(arrayOutputStreamIzq.toByteArray());
                iTextImageIzq.scaleToFit(new Rectangle(70, 80));
               PdfPCell cellIZq = new PdfPCell( new PdfPCell(iTextImageIzq,false) );
               cellIZq.setHorizontalAlignment(Element.ALIGN_RIGHT);
               cellIZq.setRowspan(4);
               cellIZq.setColspan(1);
               cellIZq.setBorder(0);
               cellIZq.setBorderWidthLeft(1.5f);
               cellIZq.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cellIZq);
               
               
               cell = new PdfPCell(new Phrase(sindicato.getNombreSindicato().toUpperCase(), FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(4);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                  
               
               
                File descargarFileFTPDer = ftpService.descargarFileFTP(sindicato.getUrlLogoDerecha());
                
                InputStream fist = new FileInputStream(descargarFileFTPDer);
                BufferedImage bufferedImageDer = ImageIO.read(fist);
                ByteArrayOutputStream arrayOutputStreamDer = new ByteArrayOutputStream();
                ImageIO.write(bufferedImageDer, "jpeg", arrayOutputStreamDer);
                Image iTextImageDer = Image.getInstance(arrayOutputStreamDer.toByteArray());
                iTextImageDer.scaleToFit(new Rectangle(70, 80));
               PdfPCell cellDer = new PdfPCell( new PdfPCell(iTextImageDer,false) );
               cellDer.setHorizontalAlignment(Element.ALIGN_LEFT);
               cellDer.setRowspan(4);
               cellDer.setColspan(1);
               cellDer.setBorder(0);
                cellDer.setBorderWidthRight(1.5f);
               cellDer.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cellDer); 
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setBorderWidthBottom(1.5f);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthRight(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DATOS RECIBO
               cell = new PdfPCell(new Phrase("RFC del sindicato", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthTop(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(isNull(sindicato.getRfc()).toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase("Fecha de Impresión", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(formatearFecha(new Date()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
               cell.setBorderWidthTop(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               
               cell = new PdfPCell(new Phrase("Agremiado", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(nombre.toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(isNull(recibo.getAgremiado().getDatosPersonales().getRfc()).toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               
               cell = new PdfPCell(new Phrase("Afiliación sindical", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(recibo.getAgremiado().getIdAgremiado().toString(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("Afiliación IMSS", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);      
                DatosLaborales datosLaborales = recibo.getAgremiado().getDatosLaborales();
               cell = new PdfPCell(new Phrase(isNull(datosLaborales.getNumeroSeguro()).toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               
                cell = new PdfPCell(new Phrase("Actividad profesional", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(isNull(datosLaborales.getActividadProfesional()).toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
                cell = new PdfPCell(new Phrase("Área / Departamento", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(isNull(datosLaborales.getAreaDepartamento()).toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               
                cell = new PdfPCell(new Phrase("Dias laborados", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
//               cell = new PdfPCell(new Phrase(calculoDeDias(recibo.getDiaInicial(),recibo.getDiaFinal()), FONT_PLAIN));
               cell = new PdfPCell(new Phrase(isNullInteger(recibo.getDiasTrabajados()).toString(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);              
                cell = new PdfPCell(new Phrase("Periodo", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("DEL "+formatoFechaCorto(recibo.getDiaInicial())+" AL "+formatoFechaCorto(recibo.getDiaFinal()), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
               cell.setBorderWidthBottom(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
                              
                cell = new PdfPCell(new Phrase("PERCEPCIONES SINDICALES", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthBottom(1f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);  
                cell = new PdfPCell(new Phrase("DEDUCCIONES SINDICALES", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
               cell.setBorderWidthBottom(1f);
               cell.setBorderWidthLeft(1f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);   
                                            
               cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1f);
                cell.setMinimumHeight(17);
                cell.setBorderWidthBottom(0.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               cell = new PdfPCell(new Phrase(sindicato.getPercepciones()==null?"FONDO PRIVADO DE PENSION DE SUPERVIVENCIA SINDICAL":sindicato.getPercepciones().toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getTotalSindicato()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               //cell = new PdfPCell(new Phrase(sindicato.getDeducciones()==null?"N/A":sindicato.getDeducciones().toUpperCase(), FONT_PLAIN_TWO));
               cell = new PdfPCell(new Phrase(recibo.getDeduccionesConcepto()==null?"N/A":recibo.getDeduccionesConcepto().toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getDeduccionesImporte()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
                cell = new PdfPCell(new Phrase("Recibí del Sindicato arriba mencionado, la cantidad neta que este documento se refiere, estando conforme con las percepciones y deducciones que en aparecen especificados.", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(3);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthTop(1f);
               cell.setBorderWidthRight(1f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("NETO A PAGAR", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthTop(1f);
               cell.setBorderWidthBottom(1f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                   
               cell = new PdfPCell(new Phrase("$ "+formatearDinero((recibo.getTotalSindicato()==null?0:recibo.getTotalSindicato())-(recibo.getDeduccionesImporte()==null?0:recibo.getDeduccionesImporte())), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthTop(1f);
               cell.setBorderWidthBottom(1f);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("Firma del agremiado", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                      
               cell = new PdfPCell(new Phrase("\n", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);          
                
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
                cell.setBorderWidthRight(1f);
                cell.setBorderWidthBottom(1.5f);
                cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(1.5f);
                cell.setBorderWidthRight(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               document.add(table);
               
               
               table = new PdfPTable(6);
                table.setWidthPercentage(100);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
               cell.setBorderColorTop(GREY);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               document.add(table);
               
               
                table = new PdfPTable(6);
                table.setWidthPercentage(100);                
               //HEADER               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthRight(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
                
                fis = new FileInputStream(descargarFileFTPIzq);
                bufferedImageIzq = ImageIO.read(fis);
                arrayOutputStreamIzq = new ByteArrayOutputStream();
                ImageIO.write(bufferedImageIzq, "jpeg", arrayOutputStreamIzq);
                iTextImageIzq = Image.getInstance(arrayOutputStreamIzq.toByteArray());
                iTextImageIzq.scaleToFit(new Rectangle(70, 80));
               cellIZq = new PdfPCell( new PdfPCell(iTextImageIzq,false) );
               cellIZq.setHorizontalAlignment(Element.ALIGN_RIGHT);
               cellIZq.setRowspan(4);
               cellIZq.setColspan(1);
               cellIZq.setBorder(0);
               cellIZq.setBorderWidthLeft(1.5f);
               cellIZq.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cellIZq);
               
               
               cell = new PdfPCell(new Phrase(sindicato.getNombreSindicato().toUpperCase(), FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(4);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                  
               
               
                
                fist = new FileInputStream(descargarFileFTPDer);
                bufferedImageDer = ImageIO.read(fist);
                arrayOutputStreamDer = new ByteArrayOutputStream();
                ImageIO.write(bufferedImageDer, "jpeg", arrayOutputStreamDer);
                iTextImageDer = Image.getInstance(arrayOutputStreamDer.toByteArray());
                iTextImageDer.scaleToFit(new Rectangle(70, 80));
               cellDer = new PdfPCell( new PdfPCell(iTextImageDer,false) );
               cellDer.setHorizontalAlignment(Element.ALIGN_LEFT);
               cellDer.setRowspan(4);
               cellDer.setColspan(1);
               cellDer.setBorder(0);
                cellDer.setBorderWidthRight(1.5f);
               cellDer.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cellDer); 
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setBorderWidthBottom(1.5f);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthRight(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               
               //DATOS RECIBO
               cell = new PdfPCell(new Phrase("RFC del sindicato", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthTop(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(sindicato.getRfc().toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase("Fecha de Impresión", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(formatearFecha(new Date()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
               cell.setBorderWidthTop(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               
               cell = new PdfPCell(new Phrase("Agremiado", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(nombre.toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(isNull(recibo.getAgremiado().getDatosPersonales().getRfc()).toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               
               cell = new PdfPCell(new Phrase("Afiliación sindical", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(recibo.getAgremiado().getIdAgremiado().toString(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("Afiliación IMSS", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);      
               cell = new PdfPCell(new Phrase(isNull(datosLaborales.getNumeroSeguro()).toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               
                cell = new PdfPCell(new Phrase("Actividad profesional", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(isNull(datosLaborales.getActividadProfesional()).toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
                cell = new PdfPCell(new Phrase("Área / Departamento", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase(isNull(datosLaborales.getAreaDepartamento()).toUpperCase(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               
                cell = new PdfPCell(new Phrase("Dias laborados", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
//               cell = new PdfPCell(new Phrase(calculoDeDias(recibo.getDiaInicial(),recibo.getDiaFinal()), FONT_PLAIN));
               cell = new PdfPCell(new Phrase(isNullInteger(recibo.getDiasTrabajados()).toString(), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);              
                cell = new PdfPCell(new Phrase("Periodo", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("DEL "+formatoFechaCorto(recibo.getDiaInicial())+" AL "+formatoFechaCorto(recibo.getDiaFinal()), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(6);
               cell.setBorder(0);
               cell.setBorderWidthTop(1.5f);
               cell.setBorderWidthBottom(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
                              
                cell = new PdfPCell(new Phrase("PERCEPCIONES SINDICALES", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthBottom(1f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);  
                cell = new PdfPCell(new Phrase("DEDUCCIONES SINDICALES", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(1);
               cell.setColspan(3);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
               cell.setBorderWidthBottom(1f);
               cell.setBorderWidthLeft(1f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);   
                                            
               cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1f);
                cell.setMinimumHeight(17);
                cell.setBorderWidthBottom(0.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
               cell = new PdfPCell(new Phrase(sindicato.getPercepciones()==null?"FONDO PRIVADO DE PENSION DE SUPERVIVENCIA SINDICAL":sindicato.getPercepciones().toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getTotalSindicato()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);               
               //cell = new PdfPCell(new Phrase(sindicato.getDeducciones()==null?"N/A":sindicato.getDeducciones().toUpperCase(), FONT_PLAIN_TWO));
               cell = new PdfPCell(new Phrase(recibo.getDeduccionesConcepto()==null?"N/A":recibo.getDeduccionesConcepto().toUpperCase(), FONT_PLAIN_TWO));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getDeduccionesImporte()), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell); 
               
                cell = new PdfPCell(new Phrase("Recibí del Sindicato arriba mencionado, la cantidad neta que este documento se refiere, estando conforme con las percepciones y deducciones que en aparecen especificados.", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setMinimumHeight(17);
               cell.setRowspan(3);
               cell.setColspan(4);
               cell.setBorder(0);
               cell.setBorderWidthLeft(1.5f);
               cell.setBorderWidthTop(1f);
               cell.setBorderWidthRight(1f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("NETO A PAGAR", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthTop(1f);
               cell.setBorderWidthBottom(1f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                   
               cell = new PdfPCell(new Phrase("$ "+formatearDinero((recibo.getTotalSindicato()==null?0:recibo.getTotalSindicato())-(recibo.getDeduccionesImporte()==null?0:recibo.getDeduccionesImporte())), FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthTop(1f);
               cell.setBorderWidthBottom(1f);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
               cell.setBorderWidthRight(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                
               cell = new PdfPCell(new Phrase("Firma del agremiado", FONT_MAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);                      
               cell = new PdfPCell(new Phrase("\n", FONT_PLAIN));
               cell.setHorizontalAlignment(Element.ALIGN_LEFT);
               cell.setRowspan(1);
               cell.setColspan(1);
               cell.setBorder(0);
                cell.setBorderWidthBottom(0.5f);
               cell.setBorderWidthRight(1.5f);
                cell.setMinimumHeight(17);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);          
                
               
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(4);
               cell.setBorder(0);
                cell.setBorderWidthRight(1f);
                cell.setBorderWidthBottom(1.5f);
                cell.setBorderWidthLeft(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);
               cell = new PdfPCell(new Phrase("\n"));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setRowspan(1);
               cell.setColspan(2);
               cell.setBorder(0);
                cell.setBorderWidthBottom(1.5f);
                cell.setBorderWidthRight(1.5f);
               cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
               table.addCell(cell);              
                
                
                document.add(table);
                 // step 7
                document.close();     
                table = null;
                Runtime.getRuntime().gc();
               
           } catch (DocumentException | IOException me) {
            LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar el recibo sindical de un colaborador --> "+me);
           }catch(NullPointerException npe){
               LOGGER.error("[Service, PDFiText] Algún valor consultado resultó ser nulo y no se puede completar la generación del recibo sindical de un colaborador -> "+npe,npe);
           }catch(Exception e){
               LOGGER.error("[Service, PDFiText] Ocurrió una excepción no esperada al momento de generar del recibo sindical de un colaborador -> "+e);
           }
           return baos;
    }
    
    @Override
    public ByteArrayOutputStream getReciboSindical(List<Recibo> recibos, Cliente cliente,  String Desde , String Hasta) {
        LOGGER.info("[Service, PDFiText] Iniciando la generación de los recibos sindical {"+recibos.size()+"} del cliente "+cliente.getNombreEmpresa().toLowerCase());
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       
           try {
               // step 1
                Document document = new Document(PageSize.LETTER, LEFT-10, RIGHT-10, TOP-40, BOTTOM-40);
                // step 2
                 PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
                // step 3
                document.open();
                // step 4
                String nombreDOC = "RECIBOS DE "+cliente.getNombreEmpresa().toUpperCase()+" DEL "+Desde+" AL "+Hasta;
                document.addAuthor("Payroll");
                document.addCreationDate();
                document.addTitle("recibo sindical de \""+nombreDOC+"\"");
                document.addCreator("Payroll");
                document.addKeywords("recibo,sindicato,"+nombreDOC+"");
                document.addSubject("recibos sindicales "+nombreDOC);
               
                Sindicato sindicato = null;
                File descargarFileFTPIzq = null, descargarFileFTPDer = null;
                
               for (Recibo recibo : recibos) {
                   
                        String nombre = ""+isNull(recibo.getAgremiado().getDatosPersonales().getNombre()).toUpperCase()+' '+isNull(recibo.getAgremiado().getDatosPersonales().getApellidoPaterno()).toUpperCase()+' '+isNull(recibo.getAgremiado().getDatosPersonales().getApellidoMaterno()).toUpperCase();
                        if(!recibo.getSindicato().equals(sindicato)){
                            sindicato = recibo.getSindicato();
                            descargarFileFTPIzq= ftpService.descargarFileFTP(sindicato.getUrlLogoIzquierda());
                            descargarFileFTPDer = ftpService.descargarFileFTP(sindicato.getUrlLogoDerecha());
                        }
                        
                         // step 6
                         PdfPTable table = new PdfPTable(6);
                         table.setWidthPercentage(100);

                        //HEADER               
                        PdfPCell cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthRight(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);


                         InputStream fis = new FileInputStream(descargarFileFTPIzq);
                         BufferedImage bufferedImageIzq = ImageIO.read(fis);
                         ByteArrayOutputStream arrayOutputStreamIzq = new ByteArrayOutputStream();
                         ImageIO.write(bufferedImageIzq, "jpeg", arrayOutputStreamIzq);
                         Image iTextImageIzq = Image.getInstance(arrayOutputStreamIzq.toByteArray());
                         iTextImageIzq.scaleToFit(new Rectangle(70, 80));
                        PdfPCell cellIZq = new PdfPCell( new PdfPCell(iTextImageIzq,false) );
                        cellIZq.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cellIZq.setRowspan(4);
                        cellIZq.setColspan(1);
                        cellIZq.setBorder(0);
                        cellIZq.setBorderWidthLeft(1.5f);
                        cellIZq.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cellIZq);


                        cell = new PdfPCell(new Phrase(isNull(sindicato.getNombreSindicato()).toUpperCase(), FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(4);
                        cell.setColspan(4);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                  



                         InputStream fist = new FileInputStream(descargarFileFTPDer);
                         BufferedImage bufferedImageDer = ImageIO.read(fist);
                         ByteArrayOutputStream arrayOutputStreamDer = new ByteArrayOutputStream();
                         ImageIO.write(bufferedImageDer, "jpeg", arrayOutputStreamDer);
                         Image iTextImageDer = Image.getInstance(arrayOutputStreamDer.toByteArray());
                         iTextImageDer.scaleToFit(new Rectangle(70, 80));
                        PdfPCell cellDer = new PdfPCell( new PdfPCell(iTextImageDer,false) );
                        cellDer.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cellDer.setRowspan(4);
                        cellDer.setColspan(1);
                        cellDer.setBorder(0);
                         cellDer.setBorderWidthRight(1.5f);
                        cellDer.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cellDer); 

                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setBorderWidthBottom(1.5f);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthRight(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        //DATOS RECIBO
                        cell = new PdfPCell(new Phrase("RFC del sindicato", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthTop(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(sindicato.getRfc()).toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("Fecha de Impresión", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(formatearFecha(new Date()), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                        cell.setBorderWidthTop(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 


                        cell = new PdfPCell(new Phrase("Agremiado", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(nombre.toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(recibo.getAgremiado().getDatosPersonales().getRfc()).toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 


                        cell = new PdfPCell(new Phrase("Afiliación sindical", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(recibo.getAgremiado().getIdAgremiado().toString(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Afiliación IMSS", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);      
                         DatosLaborales datosLaborales = recibo.getAgremiado().getDatosLaborales();
                        cell = new PdfPCell(new Phrase(isNull(datosLaborales.getNumeroSeguro()).toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 


                         cell = new PdfPCell(new Phrase("Actividad profesional", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosLaborales.getActividadProfesional()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                         cell = new PdfPCell(new Phrase("Área / Departamento", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosLaborales.getAreaDepartamento()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                

                         cell = new PdfPCell(new Phrase("Dias laborados", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
         //               cell = new PdfPCell(new Phrase(calculoDeDias(recibo.getDiaInicial(),recibo.getDiaFinal()), FONT_PLAIN));
                        cell = new PdfPCell(new Phrase(isNullInteger(recibo.getDiasTrabajados()).toString(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);              
                         cell = new PdfPCell(new Phrase("Periodo", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase("DEL "+formatoFechaCorto(recibo.getDiaInicial())+" AL "+formatoFechaCorto(recibo.getDiaFinal()), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 

                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                        cell.setBorderWidthBottom(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                         cell = new PdfPCell(new Phrase("PERCEPCIONES SINDICALES", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthBottom(1f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);  
                         cell = new PdfPCell(new Phrase("DEDUCCIONES SINDICALES", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                        cell.setBorderWidthBottom(1f);
                        cell.setBorderWidthLeft(1f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);   

                        cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1f);
                         cell.setMinimumHeight(17);
                         cell.setBorderWidthBottom(0.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 

                        cell = new PdfPCell(new Phrase(sindicato.getPercepciones()==null?"FONDO PRIVADO DE PENSION DE SUPERVIVENCIA SINDICAL":sindicato.getPercepciones().toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getTotalSindicato()), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        //cell = new PdfPCell(new Phrase(sindicato.getDeducciones()==null?"N/A":sindicato.getDeducciones().toUpperCase(), FONT_PLAIN_TWO));
                        cell = new PdfPCell(new Phrase(recibo.getDeduccionesConcepto()==null?"N/A":recibo.getDeduccionesConcepto().toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getDeduccionesImporte()), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 

                         cell = new PdfPCell(new Phrase("Recibí del Sindicato arriba mencionado, la cantidad neta que este documento se refiere, estando conforme con las percepciones y deducciones que en aparecen especificados.", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(3);
                        cell.setColspan(4);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthTop(1f);
                        cell.setBorderWidthRight(1f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("NETO A PAGAR", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1f);
                        cell.setBorderWidthBottom(1f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                   
                        cell = new PdfPCell(new Phrase("$ "+formatearDinero((recibo.getTotalSindicato()==null?0:recibo.getTotalSindicato())-(recibo.getDeduccionesImporte()==null?0:recibo.getDeduccionesImporte())), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1f);
                        cell.setBorderWidthBottom(1f);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("Firma del agremiado", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                      
                        cell = new PdfPCell(new Phrase("\n", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);          


                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(4);
                        cell.setBorder(0);
                         cell.setBorderWidthRight(1f);
                         cell.setBorderWidthBottom(1.5f);
                         cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(1.5f);
                         cell.setBorderWidthRight(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        document.add(table);


                        table = new PdfPTable(6);
                         table.setWidthPercentage(100);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(4);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                        cell.setBorderColorTop(GREY);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        document.add(table);


                         table = new PdfPTable(6);
                         table.setWidthPercentage(100);                
                        //HEADER               
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthRight(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                         fis = new FileInputStream(descargarFileFTPIzq);
                         bufferedImageIzq = ImageIO.read(fis);
                         arrayOutputStreamIzq = new ByteArrayOutputStream();
                         ImageIO.write(bufferedImageIzq, "jpeg", arrayOutputStreamIzq);
                         iTextImageIzq = Image.getInstance(arrayOutputStreamIzq.toByteArray());
                         iTextImageIzq.scaleToFit(new Rectangle(70, 80));
                        cellIZq = new PdfPCell( new PdfPCell(iTextImageIzq,false) );
                        cellIZq.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cellIZq.setRowspan(4);
                        cellIZq.setColspan(1);
                        cellIZq.setBorder(0);
                        cellIZq.setBorderWidthLeft(1.5f);
                        cellIZq.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cellIZq);


                        cell = new PdfPCell(new Phrase(isNull(sindicato.getNombreSindicato()).toUpperCase(), FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(4);
                        cell.setColspan(4);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                  



                         fist = new FileInputStream(descargarFileFTPDer);
                         bufferedImageDer = ImageIO.read(fist);
                         arrayOutputStreamDer = new ByteArrayOutputStream();
                         ImageIO.write(bufferedImageDer, "jpeg", arrayOutputStreamDer);
                         iTextImageDer = Image.getInstance(arrayOutputStreamDer.toByteArray());
                         iTextImageDer.scaleToFit(new Rectangle(70, 80));
                        cellDer = new PdfPCell( new PdfPCell(iTextImageDer,false) );
                        cellDer.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cellDer.setRowspan(4);
                        cellDer.setColspan(1);
                        cellDer.setBorder(0);
                         cellDer.setBorderWidthRight(1.5f);
                        cellDer.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cellDer); 

                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setBorderWidthBottom(1.5f);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthRight(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                        //DATOS RECIBO
                        cell = new PdfPCell(new Phrase("RFC del sindicato", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthTop(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(sindicato.getRfc()).toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("Fecha de Impresión", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(formatearFecha(new Date()), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                        cell.setBorderWidthTop(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 


                        cell = new PdfPCell(new Phrase("Agremiado", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(nombre.toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(recibo.getAgremiado().getDatosPersonales().getRfc()).toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 


                        cell = new PdfPCell(new Phrase("Afiliación sindical", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(recibo.getAgremiado().getIdAgremiado().toString(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Afiliación IMSS", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);      
                        cell = new PdfPCell(new Phrase(isNull(datosLaborales.getNumeroSeguro()).toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 


                         cell = new PdfPCell(new Phrase("Actividad profesional", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosLaborales.getActividadProfesional()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                         cell = new PdfPCell(new Phrase("Área / Departamento", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase(isNull(datosLaborales.getAreaDepartamento()).toUpperCase(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                

                         cell = new PdfPCell(new Phrase("Dias laborados", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
         //               cell = new PdfPCell(new Phrase(calculoDeDias(recibo.getDiaInicial(),recibo.getDiaFinal()), FONT_PLAIN));
                        cell = new PdfPCell(new Phrase(isNullInteger(recibo.getDiasTrabajados()).toString(), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);              
                         cell = new PdfPCell(new Phrase("Periodo", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase("DEL "+formatoFechaCorto(recibo.getDiaInicial())+" AL "+formatoFechaCorto(recibo.getDiaFinal()), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 

                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(6);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1.5f);
                        cell.setBorderWidthBottom(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);

                         cell = new PdfPCell(new Phrase("PERCEPCIONES SINDICALES", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthBottom(1f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);  
                         cell = new PdfPCell(new Phrase("DEDUCCIONES SINDICALES", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(1);
                        cell.setColspan(3);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                        cell.setBorderWidthBottom(1f);
                        cell.setBorderWidthLeft(1f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);   

                        cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1f);
                         cell.setMinimumHeight(17);
                         cell.setBorderWidthBottom(0.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 

                        cell = new PdfPCell(new Phrase(sindicato.getPercepciones()==null?"FONDO PRIVADO DE PENSION DE SUPERVIVENCIA SINDICAL":sindicato.getPercepciones().toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getTotalSindicato()), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);               
                        //cell = new PdfPCell(new Phrase(sindicato.getDeducciones()==null?"N/A":sindicato.getDeducciones().toUpperCase(), FONT_PLAIN_TWO));
                        cell = new PdfPCell(new Phrase(recibo.getDeduccionesConcepto()==null?"N/A":recibo.getDeduccionesConcepto().toUpperCase(), FONT_PLAIN_TWO));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getDeduccionesImporte()), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell); 

                         cell = new PdfPCell(new Phrase("Recibí del Sindicato arriba mencionado, la cantidad neta que este documento se refiere, estando conforme con las percepciones y deducciones que en aparecen especificados.", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                         cell.setMinimumHeight(17);
                        cell.setRowspan(3);
                        cell.setColspan(4);
                        cell.setBorder(0);
                        cell.setBorderWidthLeft(1.5f);
                        cell.setBorderWidthTop(1f);
                        cell.setBorderWidthRight(1f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("NETO A PAGAR", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1f);
                        cell.setBorderWidthBottom(1f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                   
                        cell = new PdfPCell(new Phrase("$ "+formatearDinero((recibo.getTotalSindicato()==null?0:recibo.getTotalSindicato())-(recibo.getDeduccionesImporte()==null?0:recibo.getDeduccionesImporte())), FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthTop(1f);
                        cell.setBorderWidthBottom(1f);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                        cell.setBorderWidthRight(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                
                        cell = new PdfPCell(new Phrase("Firma del agremiado", FONT_MAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);                      
                        cell = new PdfPCell(new Phrase("\n", FONT_PLAIN));
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setRowspan(1);
                        cell.setColspan(1);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(0.5f);
                        cell.setBorderWidthRight(1.5f);
                         cell.setMinimumHeight(17);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);          


                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(4);
                        cell.setBorder(0);
                         cell.setBorderWidthRight(1f);
                         cell.setBorderWidthBottom(1.5f);
                         cell.setBorderWidthLeft(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("\n"));
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cell.setRowspan(1);
                        cell.setColspan(2);
                        cell.setBorder(0);
                         cell.setBorderWidthBottom(1.5f);
                         cell.setBorderWidthRight(1.5f);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        table.addCell(cell);              


                         document.add(table);

                         document.newPage();                   
                   
               }               
                 // step 7
                document.close();     
                Runtime.getRuntime().gc();
               
           } catch (DocumentException | IOException me) {
            LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar el recibo sindical de colaboradores --> "+me);
           }catch(NullPointerException npe){
               LOGGER.error("[Service, PDFiText] Algún valor consultado resultó ser nulo y no se puede completar la generación de recibo sindical de colaboradores --> "+npe);
           }catch(Exception e){
               LOGGER.error("[Service, PDFiText] Ocurrió una excepción no esperada al momento de generar el recibo sindical de colaboradores --> "+e);
           }
           return baos;
    }
    
    @Override
    public Map<String, ByteArrayOutputStream> getReciboSindical(List<Recibo> recibos) {
        LOGGER.info("[Service, PDFiText] Iniciando la generación de los recibos sindicales {"+recibos.size()+'}');
        
        Map<String, ByteArrayOutputStream> map = new HashMap<>(recibos.size());
        int r = 0;
        Sindicato sindicato = null;
        File descargarFileFTPIzq = null, descargarFileFTPDer = null;
        for (Recibo recibo : recibos) {            
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                String nombre = ""+isNull(recibo.getAgremiado().getDatosPersonales().getNombre()).toUpperCase()+' '+isNull(recibo.getAgremiado().getDatosPersonales().getApellidoPaterno()).toUpperCase()+' '+isNull(recibo.getAgremiado().getDatosPersonales().getApellidoMaterno()).toUpperCase();
                 
                if(!recibo.getSindicato().equals(sindicato)){
                     sindicato = recibo.getSindicato();
                     descargarFileFTPIzq= ftpService.descargarFileFTP(sindicato.getUrlLogoIzquierda());
                     descargarFileFTPDer = ftpService.descargarFileFTP(sindicato.getUrlLogoDerecha());
                 }
                
                // step 1
                 Document document = new Document(PageSize.LETTER, LEFT-10, RIGHT-10, TOP-40, BOTTOM-40);
                 // step 2
                  PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
                 // step 3
                 document.open();
                 // step 4
                 document.addAuthor("Payroll");
                 document.addCreationDate();
                 document.addTitle("recibo sindical de \""+nombre+"\"");
                 document.addCreator("Payroll");
                 document.addKeywords("recibo,sindicato,"+nombre+"");
                 document.addSubject("recibo del sindical "+nombre);
                 // step 6
                 PdfPTable table = new PdfPTable(6);
                 table.setWidthPercentage(100);

                //HEADER               
                PdfPCell cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthRight(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                

                 InputStream fis = new FileInputStream(descargarFileFTPIzq);
                 BufferedImage bufferedImageIzq = ImageIO.read(fis);
                 ByteArrayOutputStream arrayOutputStreamIzq = new ByteArrayOutputStream();
                 ImageIO.write(bufferedImageIzq, "jpeg", arrayOutputStreamIzq);
                 Image iTextImageIzq = Image.getInstance(arrayOutputStreamIzq.toByteArray());
                 iTextImageIzq.scaleToFit(new Rectangle(70, 80));
                PdfPCell cellIZq = new PdfPCell( new PdfPCell(iTextImageIzq,false) );
                cellIZq.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellIZq.setRowspan(4);
                cellIZq.setColspan(1);
                cellIZq.setBorder(0);
                cellIZq.setBorderWidthLeft(1.5f);
                cellIZq.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cellIZq);


                cell = new PdfPCell(new Phrase(isNull(sindicato.getNombreSindicato()).toUpperCase(), FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(4);
                cell.setColspan(4);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                  


                 InputStream fist = new FileInputStream(descargarFileFTPDer);
                 BufferedImage bufferedImageDer = ImageIO.read(fist);
                 ByteArrayOutputStream arrayOutputStreamDer = new ByteArrayOutputStream();
                 ImageIO.write(bufferedImageDer, "jpeg", arrayOutputStreamDer);
                 Image iTextImageDer = Image.getInstance(arrayOutputStreamDer.toByteArray());
                 iTextImageDer.scaleToFit(new Rectangle(70, 80));
                PdfPCell cellDer = new PdfPCell( new PdfPCell(iTextImageDer,false) );
                cellDer.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellDer.setRowspan(4);
                cellDer.setColspan(1);
                cellDer.setBorder(0);
                 cellDer.setBorderWidthRight(1.5f);
                cellDer.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cellDer); 

                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setBorderWidthBottom(1.5f);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthRight(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                //DATOS RECIBO
                cell = new PdfPCell(new Phrase("RFC del sindicato", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthTop(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(isNull(sindicato.getRfc()).toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Fecha de Impresión", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(formatearFecha(new Date()), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                cell.setBorderWidthTop(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 


                cell = new PdfPCell(new Phrase("Agremiado", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(nombre.toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(3);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(isNull(recibo.getAgremiado().getDatosPersonales().getRfc()).toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 


                cell = new PdfPCell(new Phrase("Afiliación sindical", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(recibo.getAgremiado().getIdAgremiado().toString(), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Afiliación IMSS", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);      
                 DatosLaborales datosLaborales = recibo.getAgremiado().getDatosLaborales();
                cell = new PdfPCell(new Phrase(isNull(datosLaborales.getNumeroSeguro()).toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 


                 cell = new PdfPCell(new Phrase("Actividad profesional", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(isNull(datosLaborales.getActividadProfesional()).toUpperCase(), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                 cell = new PdfPCell(new Phrase("Área / Departamento", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(isNull(datosLaborales.getAreaDepartamento()).toUpperCase(), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                

                 cell = new PdfPCell(new Phrase("Dias laborados", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
 //               cell = new PdfPCell(new Phrase(calculoDeDias(recibo.getDiaInicial(),recibo.getDiaFinal()), FONT_PLAIN));
                cell = new PdfPCell(new Phrase(isNullInteger(recibo.getDiasTrabajados()).toString(), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);              
                 cell = new PdfPCell(new Phrase("Periodo", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase("DEL "+formatoFechaCorto(recibo.getDiaInicial())+" AL "+formatoFechaCorto(recibo.getDiaFinal()), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 

                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                cell.setBorderWidthBottom(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                 cell = new PdfPCell(new Phrase("PERCEPCIONES SINDICALES", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(3);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthBottom(1f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);  
                 cell = new PdfPCell(new Phrase("DEDUCCIONES SINDICALES", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(3);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                cell.setBorderWidthBottom(1f);
                cell.setBorderWidthLeft(1f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);   

                cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                 cell.setBorderWidthBottom(0.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setBorderWidthBottom(0.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1f);
                 cell.setMinimumHeight(17);
                 cell.setBorderWidthBottom(0.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setBorderWidthBottom(0.5f);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 

                cell = new PdfPCell(new Phrase(sindicato.getPercepciones()==null?"FONDO PRIVADO DE PENSION DE SUPERVIVENCIA SINDICAL":sindicato.getPercepciones().toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getTotalSindicato()), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                //cell = new PdfPCell(new Phrase(sindicato.getDeducciones()==null?"N/A":sindicato.getDeducciones().toUpperCase(), FONT_PLAIN_TWO));
                cell = new PdfPCell(new Phrase(recibo.getDeduccionesConcepto()==null?"N/A":recibo.getDeduccionesConcepto().toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getDeduccionesImporte()), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 

                 cell = new PdfPCell(new Phrase("Recibí del Sindicato arriba mencionado, la cantidad neta que este documento se refiere, estando conforme con las percepciones y deducciones que en aparecen especificados.", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(3);
                cell.setColspan(4);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthTop(1f);
                cell.setBorderWidthRight(1f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("NETO A PAGAR", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthTop(1f);
                cell.setBorderWidthBottom(1f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                   
                cell = new PdfPCell(new Phrase("$ "+formatearDinero((recibo.getTotalSindicato()==null?0:recibo.getTotalSindicato())-(recibo.getDeduccionesImporte()==null?0:recibo.getDeduccionesImporte())), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthTop(1f);
                cell.setBorderWidthBottom(1f);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("Firma del agremiado", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                      
                cell = new PdfPCell(new Phrase("\n", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setBorderWidthBottom(0.5f);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);          


                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(4);
                cell.setBorder(0);
                 cell.setBorderWidthRight(1f);
                 cell.setBorderWidthBottom(1.5f);
                 cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                 cell.setBorderWidthBottom(1.5f);
                 cell.setBorderWidthRight(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                document.add(table);


                table = new PdfPTable(6);
                 table.setWidthPercentage(100);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(4);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                cell.setBorderColorTop(GREY);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                document.add(table);


                 table = new PdfPTable(6);
                 table.setWidthPercentage(100);                
                //HEADER               
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthRight(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);


                 fis = new FileInputStream(descargarFileFTPIzq);
                 bufferedImageIzq = ImageIO.read(fis);
                 arrayOutputStreamIzq = new ByteArrayOutputStream();
                 ImageIO.write(bufferedImageIzq, "jpeg", arrayOutputStreamIzq);
                 iTextImageIzq = Image.getInstance(arrayOutputStreamIzq.toByteArray());
                 iTextImageIzq.scaleToFit(new Rectangle(70, 80));
                cellIZq = new PdfPCell( new PdfPCell(iTextImageIzq,false) );
                cellIZq.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellIZq.setRowspan(4);
                cellIZq.setColspan(1);
                cellIZq.setBorder(0);
                cellIZq.setBorderWidthLeft(1.5f);
                cellIZq.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cellIZq);


                cell = new PdfPCell(new Phrase(sindicato.getNombreSindicato().toUpperCase(), FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(4);
                cell.setColspan(4);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                  



                 fist = new FileInputStream(descargarFileFTPDer);
                 bufferedImageDer = ImageIO.read(fist);
                 arrayOutputStreamDer = new ByteArrayOutputStream();
                 ImageIO.write(bufferedImageDer, "jpeg", arrayOutputStreamDer);
                 iTextImageDer = Image.getInstance(arrayOutputStreamDer.toByteArray());
                 iTextImageDer.scaleToFit(new Rectangle(70, 80));
                cellDer = new PdfPCell( new PdfPCell(iTextImageDer,false) );
                cellDer.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellDer.setRowspan(4);
                cellDer.setColspan(1);
                cellDer.setBorder(0);
                 cellDer.setBorderWidthRight(1.5f);
                cellDer.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cellDer); 

                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setBorderWidthBottom(1.5f);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthRight(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                //DATOS RECIBO
                cell = new PdfPCell(new Phrase("RFC del sindicato", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthTop(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(isNull(sindicato.getRfc()).toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("Fecha de Impresión", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(formatearFecha(new Date()), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                cell.setBorderWidthTop(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 


                cell = new PdfPCell(new Phrase("Agremiado", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(nombre.toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(3);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("RFC", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(isNull(recibo.getAgremiado().getDatosPersonales().getRfc()).toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 


                cell = new PdfPCell(new Phrase("Afiliación sindical", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(recibo.getAgremiado().getIdAgremiado().toString(), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Afiliación IMSS", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);      
                cell = new PdfPCell(new Phrase(isNull(datosLaborales.getNumeroSeguro()).toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 


                 cell = new PdfPCell(new Phrase("Actividad profesional", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(isNull(datosLaborales.getActividadProfesional()).toUpperCase(), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                 cell = new PdfPCell(new Phrase("Área / Departamento", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase(isNull(datosLaborales.getAreaDepartamento()).toUpperCase(), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                

                 cell = new PdfPCell(new Phrase("Dias laborados", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
 //               cell = new PdfPCell(new Phrase(calculoDeDias(recibo.getDiaInicial(),recibo.getDiaFinal()), FONT_PLAIN));
                cell = new PdfPCell(new Phrase(isNullInteger(recibo.getDiasTrabajados()).toString(), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);              
                 cell = new PdfPCell(new Phrase("Periodo", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase("DEL "+formatoFechaCorto(recibo.getDiaInicial())+" AL "+formatoFechaCorto(recibo.getDiaFinal()), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 

                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(6);
                cell.setBorder(0);
                cell.setBorderWidthTop(1.5f);
                cell.setBorderWidthBottom(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);

                 cell = new PdfPCell(new Phrase("PERCEPCIONES SINDICALES", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(3);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthBottom(1f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);  
                 cell = new PdfPCell(new Phrase("DEDUCCIONES SINDICALES", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(1);
                cell.setColspan(3);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                cell.setBorderWidthBottom(1f);
                cell.setBorderWidthLeft(1f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);   

                cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                 cell.setBorderWidthBottom(0.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setBorderWidthBottom(0.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                cell = new PdfPCell(new Phrase("Concepto", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1f);
                 cell.setMinimumHeight(17);
                 cell.setBorderWidthBottom(0.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("Importe", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setBorderWidthBottom(0.5f);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 

                cell = new PdfPCell(new Phrase(sindicato.getPercepciones()==null?"FONDO PRIVADO DE PENSION DE SUPERVIVENCIA SINDICAL":sindicato.getPercepciones().toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getTotalSindicato()), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);               
                //cell = new PdfPCell(new Phrase(sindicato.getDeducciones()==null?"N/A":sindicato.getDeducciones().toUpperCase(), FONT_PLAIN_TWO));
                cell = new PdfPCell(new Phrase(recibo.getDeduccionesConcepto()==null?"N/A":recibo.getDeduccionesConcepto().toUpperCase(), FONT_PLAIN_TWO));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("$ "+formatearDinero(recibo.getDeduccionesImporte()), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell); 

                 cell = new PdfPCell(new Phrase("Recibí del Sindicato arriba mencionado, la cantidad neta que este documento se refiere, estando conforme con las percepciones y deducciones que en aparecen especificados. ", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setMinimumHeight(17);
                cell.setRowspan(3);
                cell.setColspan(4);
                cell.setBorder(0);
                cell.setBorderWidthLeft(1.5f);
                cell.setBorderWidthTop(1f);
                cell.setBorderWidthRight(1f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("NETO A PAGAR", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthTop(1f);
                cell.setBorderWidthBottom(1f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                   
                cell = new PdfPCell(new Phrase("$ "+formatearDinero((recibo.getTotalSindicato()==null?0:recibo.getTotalSindicato())-(recibo.getDeduccionesImporte()==null?0:recibo.getDeduccionesImporte())), FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthTop(1f);
                cell.setBorderWidthBottom(1f);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                cell.setBorderWidthRight(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                
                cell = new PdfPCell(new Phrase("Firma del agremiado", FONT_MAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);                      
                cell = new PdfPCell(new Phrase("\n", FONT_PLAIN));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setRowspan(1);
                cell.setColspan(1);
                cell.setBorder(0);
                 cell.setBorderWidthBottom(0.5f);
                cell.setBorderWidthRight(1.5f);
                 cell.setMinimumHeight(17);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);          


                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(4);
                cell.setBorder(0);
                 cell.setBorderWidthRight(1f);
                 cell.setBorderWidthBottom(1.5f);
                 cell.setBorderWidthLeft(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("\n"));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setRowspan(1);
                cell.setColspan(2);
                cell.setBorder(0);
                 cell.setBorderWidthBottom(1.5f);
                 cell.setBorderWidthRight(1.5f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);              


                 document.add(table);
                  // step 7
                 document.close();     
                 table = null;
                 String id = (++r)+" "+nombre+" DEL "+formatoFechaCorto(recibo.getDiaInicial())+" AL "+formatoFechaCorto(recibo.getDiaFinal());
                 id = id.replaceAll(" ", "_");
                 id = id.replaceAll("/", "-");
                map.put(id, baos);  
            } catch (DocumentException | IOException me) {
             LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de generar los recibos sindicales  --> "+me);
            }catch(NullPointerException npe){
               LOGGER.error("[Service, PDFiText] Algún valor consultado resultó ser nulo y no se puede completar la generación de los recibos sindicales -> "+npe.getMessage(),npe);
           }catch(Exception e){
               LOGGER.error("[Service, PDFiText] Ocurrió una excepción no esperada al momento de generar los recibos sindicales -> "+e);
           }                
            Runtime.getRuntime().gc();
        }    
            
         return map;
    }
    @Deprecated
    @Override
     public ByteArrayOutputStream getAdhesionSindical( Agremiado agremiado ){
         LOGGER.info("[Service, PDFiText] Iniciando la creación de la solicitud de adhesión sindical {"+agremiado.getIdAgremiado()+'}');
       ByteArrayOutputStream baos = new ByteArrayOutputStream();     
        try {
             DatosPersonales datosPersonales = agremiado.getDatosPersonales();
             DatosDomicilio datosDomicilio = agremiado.getDatosDomicilio();
             DatosLaborales datosLaborales = agremiado.getDatosLaborales();
             Sindicato sindicatoObj = datosLaborales.getSindicatoObj();
             Cliente clienteObj = datosLaborales.getClienteObj();
            
             String SINDICATOACRONIMO = sindicatoObj.getNombreSindicato().toUpperCase()+", "+sindicatoObj.getNombreCorto().toUpperCase()+".";
             String NombrePaternoMaterno = datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((agremiado.getDatosPersonales().getApellidoMaterno()==null)?"":agremiado.getDatosPersonales().getApellidoMaterno().toUpperCase());
             String NombreCliente = clienteObj.getNombreEmpresa().toUpperCase();
             String NSS = (datosLaborales.getNumeroSeguro()==null)?"0000000000000":datosLaborales.getNumeroSeguro().toUpperCase();
             String sindicatoACRONIMO = sindicatoObj.getNombreSindicato().toLowerCase()+", "+sindicatoObj.getNombreCorto().toUpperCase()+".";
             
             
             String adhesionSindicalTmp = adhesionSindical.replace("%SNDCTACRNMM", SINDICATOACRONIMO)
                     .replace("%NombrePaternoMaterno", NombrePaternoMaterno)
                     .replace("%NombreCliente", NombreCliente)
                     .replace("%NSS", NSS)
                     .replace("%Calle", datosDomicilio.getCalle().toUpperCase())
                     .replace(" %##", datosDomicilio.getNumero().toUpperCase())
                     .replace("%Colonia", datosDomicilio.getColonia().toUpperCase())
                     .replace("%CP", datosDomicilio.getCodigoPostal().toUpperCase())
                     .replace("%SNDCTACRNMN", sindicatoACRONIMO)
                     .replace("%Fch", formatoFechaTexto())
                     .replace("%NombrePaternoMaterno", NombrePaternoMaterno);
             
             
            // step 1
            Document document = new Document(PageSize.LEGAL, LEFT, RIGHT, TOP, BOTTOM);
            // step 2
             PdfWriter pdfWriter = PdfWriter.getInstance(document, baos);
             pdfWriter.setInitialLeading(12);
            // step 3
            document.open();
            // step 4
            document.addAuthor("Payroll");
            document.addCreationDate();
            document.addTitle("Adhesión sindical");
            document.addCreator("Payroll");
            document.addKeywords("Adhesión,sindical,sindicato,colaborador");
            document.addSubject("Adhesión sindical");
            XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new ByteArrayInputStream(adhesionSindicalTmp.getBytes("UTF-8")), new ByteArrayInputStream(CSS.getBytes("UTF-8")), Charset.forName("utf-8"));
            // step 5
            document.close();
 
         }catch(DocumentException | IOException e) {
            LOGGER.error("[Service, PDFiText] Ocurrio un error al momento de intentar crear un adhesión sidnical --> "+e.getMessage());
        }
        return baos;
     }
    
    private String formatearFecha(Date fechaVolumen) {
        if(fechaVolumen == null){
            return "";
        }
       String dd = "dd";
       String MM = "MM";
       String yy = "yyyy";
        DateFormat formatter = new SimpleDateFormat(dd);
        String dia =  formatter.format(fechaVolumen);
        formatter = new SimpleDateFormat(MM);
        String mes =  formatter.format(fechaVolumen);
        formatter = new SimpleDateFormat(yy);
        String anio =  formatter.format(fechaVolumen);
        String fecha = dia+" DE "+getMes(mes)+" DE "+anio;
        return fecha;
    }
    private String getMes(String mes) {
        int mesNumber = Integer.parseInt(mes);
        return MESES[mesNumber];
    }
    private EmpresasDomicilio getDomicilioFiscal(List<EmpresasDomicilio> empresasDomicilioList) {
        for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {
            if(empresasDomicilio.getTipoDomicilioObj() == 1){
                return empresasDomicilio;
            }//Domicilio fiscal{}
        }
        return null;
    }   
    private EmpresasDomicilio getDomicilioNotificacion(List<EmpresasDomicilio> empresasDomicilioList) {
        for (EmpresasDomicilio empresasDomicilio : empresasDomicilioList) {
            if(empresasDomicilio.getTipoDomicilioObj() == 2){
                return empresasDomicilio;
            }//Domicilio notificación{}
        }
        return null;
    }    
    private String calculoDeDias(Date diaInicial, Date diaFinal) {
        long days = (diaFinal.getTime()-diaFinal.getTime())/(86400000);
        return ""+((days<0)?(days*-1):days);
    }    
    private String formatoFechaCorto(Date fecha) {        
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return  formatter.format(fecha);
    }  
    private String formatoFechaTexto() {   
         LocalDate today=LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - MMMM - yyyy");
        return (today).format(formatter).replace("-", "de");
    }
    private String formatearDinero(Double totalSindicato) {
        if(totalSindicato instanceof Number && totalSindicato!=null){
        try {
                java.text.DecimalFormat formateador = new java.text.DecimalFormat("###,###.##");
                String format = formateador.format(totalSindicato);
                if(format.contains(".")){
                    int indexOf = format.indexOf(".");
                    String substring = format.substring(indexOf);
                    if(substring.length()==1){
                        format = format+"0";
                    }
                }else{
                    format = format+".00";
                }
                return format;
        } catch (IllegalArgumentException iae) {
            LOGGER.error("[Service, PDFiText] No se pudo formatear adecuadamente la cantidad monetaria  -- iae --> "+iae.getMessage(),iae);
            return "0.00";
        }catch (Exception e) {
            LOGGER.error("[Service, PDFiText] No se pudo formatear adecuadamente la cantidad monetaria  --> "+e.getMessage(),e);
            return "0.00";
        }
        }else{
            return "0.0";
        }
    }
    private final float LEFT = 40, RIGHT = 40, TOP = 60, BOTTOM = 60;    
    private final String CSS  = 
"        .text-left{\n" +
"            text-align: left;\n" +
"        }\n" +
"        .text-center{\n" +
"            text-align: center;\n" +
"        }\n" +
"        .text-right{\n" +
"            text-align: right;\n" +
"        }\n" +
"        .text-justify{\n" +
"            text-align: justify;\n" +
"        }\n" +
"        .specialComponentPlain{\n" +
"            color: black;\n" +
"            font-style: normal;\n" +
"            text-align: center;\n" +
"            padding-bottom: 55px;\n" +
"        }\n" +
"        .specialComponent{\n" +
"            color: black;\n" +
"            text-align: center;\n" +
"            border-top-color: black;\n" +
"            border-top-width: 2px;\n" +
"            border-top-style: solid;\n" +
"            font-size: 14px;\n" +
"            padding-top: 8px;\n" +
"        }\n" +
"        .inline{\n" +
"            margin: 0px;\n" +
"            padding: 0px;\n" +
"            position: relative;\n" +
"            float: left;\n" +
"        }\n" +
"        table{\n" +
"            width: 90%;\n" +
"            margin-top: 8px;\n" +
"            margin-bottom: 8px;\n" +
"            margin-left: 5%;\n" +
"            margin-right: 5%;\n" +
"            position: relative;\n" +
"            float: left;\n" +
"            padding-left: 5%;\n" +
"            padding-right: 5%;\n" +
"        }\n" +
"        th,td{\n" +
"            width: 33%;\n" +
"        }\n" +
"        u{font-size: 15px;}" ;
private final String adhesionSindical =    "<p class=\"text-right\">Oaxaca de Juárez, %Fch.</p>\n" +
   "        <br/>\n" +
   "        <p class=\"text-justify\"><b><h2>C. SECRETARIO(A) GENERAL DEL SINDICATO Y DEL COMITÉ EJECUTIVO DEL %SNDCTACRNMM </h2></b></p>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <p class=\"text-ustify\"><b>PRESENTE.&nbsp;</b></p>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <p class=\"text-justify\">\n" +
   "            La que suscribe <b>C. %NombrePaternoMaterno </b> promoviendo por mi propio derecho, declaro bajo protesta de decir verdad que cumplo con los requisitos de ingreso que señala el artículo 8 (ocho) de sus Estatutos, motivo por el cual solicito el ingreso a la asociación sindical que representa, expresando mi voluntad de sujetarme en todo a sus Estatutos y a las Estipulaciones del Contrato de Trabajo, así como de cumplir sus normas, y acatar las decisiones de los Congresos o Asambleas que se celebren con motivo de sus propias actividades de estudio, mejoramiento y defensa de los intereses de los trabajadores agremiados.\n" +
   "        </p>\n" +
   "        <br/>\n" +
   "        <p class=\"text-justify\">\n" +
   "            En este mismo acto declaro bajo protesta de decir verdad que no he sido expulsado de otra Agrupación Obrera por faltas graves, y que no pertenezco a ningún sindicato, así mismo que comparto sus ideales y sus formas de trabajo en beneficio de la clase obrera por lo que tengo interés en pertenecer a esta asociación.\n" +
   "        </p>\n" +
   "        <br/>\n" +
   "        <p class=\"text-justify\">\n" +
   "            Me avalan para realizar esta solicitud, los miembros que aparecen al calce de la presente, lo cual se acredita con el estampamiento de sus firmas.\n" +
   "        </p>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <p class=\"text-center\"><b>ATENTAMENTE</b></p>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <br/>\n" +
   "        <p class=\"text-center\"><b>C. %NombrePaternoMaterno </b></p>";
private final BaseColor RED = new BaseColor(210,3,44);
private final BaseColor GREY = new BaseColor(67,64,65);  
private final BaseColor WHITE = new BaseColor(255,255,255);      
private final Font FONT_PLAIN= new Font(FontFamily.HELVETICA, 9, Font.NORMAL);
private final Font FONT_PLAIN_TWO = new Font(FontFamily.HELVETICA, 9, Font.BOLD);
private final Font FONT_MAIN = new Font(FontFamily.HELVETICA, 10, Font.BOLD, GREY);
private final Font FONT_MAIN_TWO = new Font(FontFamily.HELVETICA, 12, Font.BOLD, RED);
private final Font FONT_TITULAR = new Font(FontFamily.HELVETICA, 16, Font.BOLDITALIC, RED);
private final Font FONT_TITULAR_TWO = new Font(FontFamily.HELVETICA, 15, Font.ITALIC, WHITE);
private final Font FONT_SECTION = new Font(FontFamily.HELVETICA, 12, Font.BOLD, WHITE);
private final String[] MESES = new String[]{"","ENERO","FEBRERO","MARZO","ABRIL","MAYO","JUNIO","JULIO","AGOSTO","SEPTIEMBRE","OCTUBRE","NOVIEMBRE","DICIEMBRE"};

    private String getObservacionesSinHTML(String observaciones) {
        if (observaciones == null) {
            return  "Sin Observaciones";
        }
        observaciones = new String(observaciones.toCharArray());
        observaciones = observaciones.replace("<B>", "");
        observaciones = observaciones.replace("</B>", "");
        observaciones = observaciones.replace("<BR>", "\n");
        observaciones = observaciones.replace("<b>", "");
        observaciones = observaciones.replace("</b>", "");
        observaciones = observaciones.replace("<br>", "\n");
        return observaciones.replace("[.]", "...");
    }

    private String isNull(String txt){
        return (txt == null)?"":txt;
    }
    
    private Integer isNullInteger(Integer o){
        return (o == null)?0:o;
    }
}
