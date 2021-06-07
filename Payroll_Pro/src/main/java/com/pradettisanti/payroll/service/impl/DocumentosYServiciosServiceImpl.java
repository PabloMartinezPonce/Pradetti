/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.pradettisanti.payroll.enums.ContratistaTemplate;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.DatosDomicilio;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.DatosPersonales;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.service.DocumentosYServiciosService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de generar los documentos que el documento puede generar
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @since 08/01/2018
 * @version 1
 */
@Service
public class DocumentosYServiciosServiceImpl implements DocumentosYServiciosService{
    
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DocumentosYServiciosServiceImpl.class);
    private final float LEFT = 40, RIGHT = 40, TOP = 50, BOTTOM = 50;  
    private static final String CLASS_NAME = "[DocumentosYServiciosServiceImpl]";
    
    private final BaseColor BLACK = new BaseColor(0,0,0);
    private final BaseColor RED = new BaseColor(210,3,44);
    private final BaseColor GREY = new BaseColor(67,64,65);  
    private final BaseColor WHITE = new BaseColor(255,255,255);      
    private final Font FONT_PLAIN= new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL,BLACK);
    private final Font FONT_PLAIN_TINY= new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL,BLACK);
    private final Font FONT_PLAIN_DTINY= new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL,BLACK);
    private final Font FONT_PLAIN_BOLD = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD,BLACK);
    private final Font FONT_PLAIN_FANCY= new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD|Font.UNDERLINE,BLACK);
    private final Font FONT_MAIN = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BLACK);
    private final Font FONT_TITULAR = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLDITALIC, BLACK);    
    private final Font FONT_SUPER_TITULAR = new Font(Font.FontFamily.HELVETICA, 13, Font.BOLD|Font.UNDERLINE, BLACK);
      
        
    @Autowired
    private ServletContext servletContext;
    public ServletContext getServletContext(){
        return servletContext;
    }
    public void setServletContext(ServletContext servletContext){
        this.servletContext = servletContext;
    }
    
    
    /**
     * Metodo encargado de devolver la solicitud de tarjetas
     * @param a
     * @param template
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream getSolicitudTarjetas(Agremiado a, ContratistaTemplate template) {
        printLogInnerClass("comienza la generación de la solictud de tarjetas del colaborador "+getDetallesAgremiado(a));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            // Se cambia el tamapo del documento Solicitud de Tarjetas por tamapo Oficcio PageSize.LEGAL
            Document solicitud = new Document(PageSize.LEGAL, LEFT, RIGHT, TOP, BOTTOM);
            PdfWriter writer = PdfWriter.getInstance(solicitud, baos);
            solicitud.open();
            solicitud.addAuthor("Payroll");
            solicitud.addTitle("Solicitud de tarjetas");
            solicitud.addCreator("Payroll");
            solicitud.addKeywords("Payroll,solicitud,tarjetas");
            solicitud.addSubject("Solicitud de tarjetas");
            
            documentStamper(solicitud.getPageSize(),writer, template);
            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("SOLICITUD DE TARJETAS",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            // Se cambia la redacción de la carta de Solicitud de tarjetas
            cell = new PdfPCell(new Phrase("Por medio de la presente, solicito el trámite de una tarjeta de nómina del banco que asigne la empresa, en la cual recibiré los pagos correspondientes a mis salarios o algún otro concepto que se me adeude. Así mismo el trámite de una Tarjeta TDC con descuentos inmediatos en establecimientos afiliados, cupones de descuentos, tarifas especiales y Red Médica VRIM. Para lo cual anexo mi INE.",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            // Se agregan espacios para que la firma quede al final del documento
            cell = new PdfPCell(new Phrase("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(9);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(getNombre(a.getDatosPersonales()),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            solicitud.add(table);
            solicitud.close();
            
            
        } catch (DocumentException  me) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar el documento de la solicitud de tarjetas", me);            
        }catch (Exception e) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar la solicitud de tarjetas", e);
        }
        
        return baos;
    }

    @Override
    public ByteArrayOutputStream getConfirmacionCorreoElectronico(Agremiado a,ContratistaTemplate template) { 
        printLogInnerClass("comienza la generación de la confirmación de correo de un colaborador "+getDetallesAgremiado(a));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            
            Document solicitud = new Document(PageSize.LETTER, LEFT, RIGHT, TOP, BOTTOM);
            PdfWriter writer = PdfWriter.getInstance(solicitud, baos);
            solicitud.open();
            solicitud.addAuthor("Payroll");
            solicitud.addTitle("Solicitud de tarjetas");
            solicitud.addCreator("Payroll");
            solicitud.addKeywords("Payroll,solicitud,tarjetas");
            solicitud.addSubject("Solicitud de tarjetas");
            
            documentStamper(solicitud.getPageSize(),writer, template);
            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(getFechaLarga(a.getDatosLaborales().getFechaInicioContrato()),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("CARTA DE RECONOCIMIENTO DE CORREO PERSONAL",FONT_SUPER_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("Representante Legal de la Empresa \n" +
            getContratista(a.getDatosLaborales())+"\n" +
            "P r e s e n t e.",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
              Paragraph paragraph = new Paragraph("El que suscribe la presente ", FONT_PLAIN);
              paragraph.add(new Phrase(getNombre(a.getDatosPersonales()), FONT_PLAIN_FANCY));
             paragraph.add(new Phrase(" declaro bajo protesta de decir verdad que mi correo personal es ",FONT_PLAIN));
             paragraph.add(new Phrase( getEmail(a.getDatosPersonales()) ,FONT_PLAIN_FANCY));
             paragraph.add(new Phrase(", mismo en el que requiero me sean enviados los recibos CFDI´S  (XML y PDF) que amparen los salarios que me sean pagados. Motivo por el cual él envió al citado correo constituye el más amplio acuse de recibo que en derecho proceda respecto a mi persona.\n\nAsimismo me obligo a informar de manera inmediata el cambio de correo electrónico personal que pudiese existir durante la relación de trabajo que me une con la empresa. ",FONT_PLAIN));
             
             cell = new PdfPCell(paragraph);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(getNombre(a.getDatosPersonales()),FONT_PLAIN_TINY));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            solicitud.add(table);
                        
            solicitud.close();
            
        }  catch (IOException | DocumentException  me) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar el documento de la confirmación de correo electrónico", me);            
        }catch (Exception e) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar la confirmación de correo electrónico", e);
        }
        return baos;
    }

    @Override
    public ByteArrayOutputStream getSolicitudDeFondoDeAhorroEstandar(Agremiado a,ContratistaTemplate template) {
         printLogInnerClass("comienza la generación de la solicitud del fondo de ahorro de un colaborador "+getDetallesAgremiado(a));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
             
            Document solicitud = new Document(PageSize.LETTER, LEFT, RIGHT, TOP, BOTTOM);
            PdfWriter writer = PdfWriter.getInstance(solicitud, baos);
            solicitud.open();
            solicitud.addAuthor("Payroll");
            solicitud.addTitle("Solicitud de fondo de ahorro");
            solicitud.addCreator("Payroll");
            solicitud.addKeywords("Payroll,solicitud,tarjetas");
            solicitud.addSubject("Solicitud de fondo de ahorro");
            
            documentStamper(solicitud.getPageSize(),writer, template);
            
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            
            
            PdfPCell cell = new PdfPCell(new Phrase("\n\n SOLICITUD DE INGRESO AL FONDO DE AHORRO DE LA EMPRESA",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\""+getContratista(a.getDatosLaborales())+"\"",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n SOLICITUD DE AFILIACION No. _______",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
            cell = new PdfPCell(new Phrase("Por favor diligenciar la siguiente información con los datos personales:",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Nombre:",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getNombre(a.getDatosPersonales()),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Dirección:",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getDireccion(a.getDatosDomicilio()), FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Ciudad:",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(a.getDatosDomicilio().getCiudad(),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            
            cell = new PdfPCell(new Phrase("e-mail:",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getEmail(a.getDatosPersonales()),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Celular:",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(a.getDatosPersonales().getTelefono(),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
            
            cell = new PdfPCell(new Phrase("Teléfono fijo:",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Teléfonos familiares o conocidos:",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
                        
            cell = new PdfPCell(new Phrase("AUTORIZACION DESCUENTOS DE NOMINA",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            Paragraph paragraph = new Paragraph(new Phrase("Por medio de la presente solicito a ustedes como cuentahabiente del Fondo de ahorro ", FONT_PLAIN));
            paragraph.add(new Phrase("\""+getContratista(a.getDatosLaborales())+"\" ",FONT_PLAIN_BOLD));
            paragraph = new Paragraph(new Phrase("al que autorizo para descontarme la cuota del ", FONT_PLAIN));
            paragraph.add(new Phrase(" "+a.getDatosLaborales().getFondoDeAhorroActual()+" %",FONT_PLAIN_FANCY));
            paragraph.add(new Phrase(" sobre mi salario base mensual, el cual se aplicará cada quincena por concepto de mi aportación al Fondo de ahorro.\n" +
                                                                    "Al firmar la presente me doy por enterado del Reglamento que rige a este fondo, quedando de acuerdo con el mismo. De igual forma autorizo que dicho descuento sea efectuado por la empresa vía nómina.",FONT_PLAIN));
            paragraph.setLeading(5f);
            cell = new PdfPCell(paragraph);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
                        
            cell = new PdfPCell(new Phrase("Así mismo me permito designar para el caso de mi fallecimiento y/o ausencia a las siguientes personas en los porcentajes que a continuación se detallan.",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            
            cell = new PdfPCell(new Phrase("DATOS DE BENEFICIARIOS\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
                        
            cell = new PdfPCell(new Phrase("NOMBRE COMPLETO",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("PARENTESCO",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("PORCENTAJE",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
                        
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
           cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("*USTED PODRA LIBREMENTE ACTUALIZAR  EL LISTADO DE BENEFICIARIOS DEL FONDO DE AHORRO.",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            
            cell = new PdfPCell(new Phrase("LUGAR Y FECHA",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(1f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("FIRMA DEL TRABAJADOR",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(1f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
         
            solicitud.add(table);
                        
            solicitud.close();
            
        }  catch (IOException | DocumentException  me) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar el documento de fondo de ahorro", me);            
        }catch (Exception e) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar la confirmación de fondo de ahorro", e);
        }
        return baos;
    }
    
    private void printLogInnerClass(String string){
        try {
            LOGGER.info(CLASS_NAME+" "+string.toLowerCase());
        } catch (Exception e) {
            printLogInnerClass("Houston! --> "+string, e);
        }
    }
    private void printLogInnerClass(String string, Exception exception){
        try {
             LOGGER.error(CLASS_NAME+" "+string.toUpperCase()+". --> "+exception.getMessage(),exception);
        } catch (Exception e) {
        }
    }
    private String getDetallesAgremiado(Agremiado a){
        String s = "{Sin Dato";
        try {
            s = "{"+a.getIdAgremiado().toString();
            s+= ","+a.getDatosPersonales().getNombre().toUpperCase();
            s+= ","+a.getDatosPersonales().getApellidoPaterno().toUpperCase();
            s+= ","+a.getDatosPersonales().getApellidoMaterno().toLowerCase();
        } catch (Exception e) {
            printLogInnerClass("Ocurrio un problema al intentar obtener la información del colaborador", e);
        }
            return s+"}";
    }

    private String getNombre(DatosPersonales datosPersonales) {
        String s = "";
        try {
            s += datosPersonales.getNombre().toUpperCase();
            s += " "+datosPersonales.getApellidoPaterno().toUpperCase();
            s += " "+datosPersonales.getApellidoMaterno().toUpperCase();
        } catch (Exception e) {
            printLogInnerClass("Ocurrio un problema al intentar obtener el nombre del colaborador", e);
            s += " ";
        }
        return s;
    }

    private String getEmail(DatosPersonales datosPersonales) {
        try {
            return datosPersonales.getEmail().toLowerCase();
        } catch (Exception e) {
            printLogInnerClass("No fue posible obtener el correo electrónico", e);
            return "sin correo";
        }
    }
    
    private String getContratista(DatosLaborales datosLaborales){
        return datosLaborales.getContratistaObj().getNombreEmpresa();
    }
    
    private String getRepresentanteLegal(DatosLaborales datosLaborales){
        return datosLaborales.getContratistaObj().getRepresentateLegal();
    }

    private String getFechaLarga(Date date){
        Locale locMX = new Locale("es","MX");
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, locMX);        
        return  df.format(date);
    }
    
    private String getDireccion(DatosDomicilio datosDomicilio) {
        try {
            return "Calle "+datosDomicilio.getCalle()+"  Número "+datosDomicilio.getNumero()+", "+datosDomicilio.getColonia()+", CP "+datosDomicilio.getCodigoPostal()+".";
        } catch (Exception e) {
            printLogInnerClass("No fue posible obtener el domicilio del trabajador", e);
            return "";
        }
    }
    
    private void documentStamper(Rectangle r,PdfWriter pw, ContratistaTemplate template) throws DocumentException, BadElementException, IOException{
        PdfContentByte cb = pw.getDirectContentUnder();
        Image image =  getStamp(template);
        image.setRotationDegrees(0);
        image.setSpacingAfter(0);
        image.setSpacingBefore(0);
        image.setAbsolutePosition(0, 0);
        image.setScaleToFitLineWhenOverflow(true);
        image.scaleAbsolute(r);
        cb.addImage(image); 
    }
    
    private Image getStamp(ContratistaTemplate template) throws FileNotFoundException, IOException, BadElementException{             
        InputStream fis = servletContext.getResourceAsStream(ContratistaTemplate.getURL(template));
        BufferedImage bufferedImageIzq = ImageIO.read(fis);
        ByteArrayOutputStream arrayOutputStreamIzq = new ByteArrayOutputStream();
        ImageIO.write(bufferedImageIzq, "jpeg", arrayOutputStreamIzq);
        return Image.getInstance(arrayOutputStreamIzq.toByteArray());
    }

    @Override
    public ByteArrayOutputStream getAdhesionSindical(Agremiado a) {
        LOGGER.info("[Service, DocumentosYServicios] Iniciando la creación de la solicitud de adhesión sindical {"+a.getIdAgremiado()+'}');
       ByteArrayOutputStream baos = new ByteArrayOutputStream();     
        try {
             DatosPersonales datosPersonales = a.getDatosPersonales();
             DatosDomicilio datosDomicilio = a.getDatosDomicilio();
             DatosLaborales datosLaborales = a.getDatosLaborales();
             Sindicato sindicatoObj = datosLaborales.getSindicatoObj();
             if(sindicatoObj == null){
                 sindicatoObj = new Sindicato();
                 sindicatoObj.setNombreCorto("_NO_APLICA_");
                 sindicatoObj.setNombreSindicato("_NO_APLICA_EL_DOCUMENTO_");
             }
             Cliente clienteObj = datosLaborales.getClienteObj();
            
             String SINDICATOACRONIMO = sindicatoObj.getNombreSindicato().toUpperCase()+", "+sindicatoObj.getNombreCorto().toUpperCase()+".";
             String NombrePaternoMaterno = datosPersonales.getNombre().toUpperCase()+" "+datosPersonales.getApellidoPaterno().toUpperCase()+" "+((a.getDatosPersonales().getApellidoMaterno()==null)?"":a.getDatosPersonales().getApellidoMaterno().toUpperCase());
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
                     .replace("%Fch", getFechaLarga(a.getDatosLaborales().getFechaInicioContrato()))
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
            LOGGER.error("[Service, DocumentosYServicios] Ocurrio un error al momento de intentar crear un adhesión sidnical --> "+e.getMessage());
        }
        return baos;
    }
    /**
     * Metodo encargado de devolver el documento a partir de su plantilla
     * @param a
     * @param template
     * @return ByteArrayOutputStream
     */
    @Override
    public ByteArrayOutputStream getSolicitudDeAsimilables(Agremiado a, ContratistaTemplate template) {
        printLogInnerClass("comienza la generación de la solicitud asimilables "+getDetallesAgremiado(a));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            
            Document solicitud = new Document(PageSize.LETTER, LEFT, RIGHT, TOP, BOTTOM);
            PdfWriter writer = PdfWriter.getInstance(solicitud, baos);
            solicitud.open();
            solicitud.addAuthor("Payroll");
            solicitud.addTitle("Solicitud de asimilables");
            solicitud.addCreator("Payroll");
            solicitud.addKeywords("Payroll,solicitud,asimilables");
            solicitud.addSubject("Solicitud de asimilables");
            
            documentStamper(solicitud.getPageSize(),writer, template);
            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
            cell = new PdfPCell(new Phrase(getFechaLarga(a.getDatosLaborales().getFechaInicioContrato()),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("SOLICITUD DE ASIMILABLES",FONT_SUPER_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("REPRESENTANTE LEGAL DE LA EMPRESA DENOMINADA \n" +
            getContratista(a.getDatosLaborales())+"\n" +
            "P R E S E N T E.",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             Paragraph paragraph = new Paragraph("El que suscribe la presente ", FONT_PLAIN);
             paragraph.add(new Phrase(getNombre(a.getDatosPersonales()), FONT_PLAIN_FANCY));
             paragraph.add(new Phrase(" declaro bajo protesta de decir la verdad que de conformidad con lo estipulado en la fracción V del artículo 94 de la ley del impuesto sobre la renta, opto porque el pago de mis servicios sea por concepto honorarios y me sea retenida la parte del impuesto para que sea enterado a las autoridades fiscales respectivas.",FONT_PLAIN));             
             cell = new PdfPCell(paragraph);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(getNombre(a.getDatosPersonales()),FONT_PLAIN_TINY));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            solicitud.add(table);
                        
            solicitud.close();
            
        }  catch (IOException | DocumentException  me) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar el documento de la solicitud de asimilados", me);            
        }catch (Exception e) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar el documento de la solicitud de asimilados", e);
        }
        return baos;
    }
    private String formatoFechaTexto() {   
         LocalDate today=LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - MMMM - yyyy");
        return (today).format(formatter).replace("-", "de");
    }
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
   "            El que suscribe <b>C. %NombrePaternoMaterno </b> promoviendo por mi propio derecho, declaro bajo protesta de decir verdad que cumplo con los requisitos de ingreso que señala el artículo 8 (ocho) de sus Estatutos, motivo por el cual solicito el ingreso a la asociación sindical que representa, expresando mi voluntad de sujetarme en todo a sus Estatutos y a las Estipulaciones del Contrato de Trabajo, así como de cumplir sus normas, y acatar las decisiones de los Congresos o Asambleas que se celebren con motivo de sus propias actividades de estudio, mejoramiento y defensa de los intereses de los trabajadores agremiados.\n" +
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

    @Override
    public ByteArrayOutputStream getCartaRenunciaFA(Agremiado a, ContratistaTemplate template) {
        printLogInnerClass("comienza la generación de la carta renuncia al fonod de ahorro "+getDetallesAgremiado(a));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            
            Document solicitud = new Document(PageSize.LETTER, LEFT, RIGHT, TOP, BOTTOM);
            PdfWriter writer = PdfWriter.getInstance(solicitud, baos);
            solicitud.open();
            solicitud.addAuthor("Payroll");
            solicitud.addTitle("Carta renuncia al fondo de ahorro");
            solicitud.addCreator("Payroll");
            solicitud.addKeywords("Payroll,renuncia,fondo,ahorro");
            solicitud.addSubject("Carta renuncia al fondo de ahorro");
            
            documentStamper(solicitud.getPageSize(),writer, template);
            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
            cell = new PdfPCell(new Phrase(getFechaLarga(a.getDatosLaborales().getFondoDeAhorroFechaSol()),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(getRepresentanteLegal(a.getDatosLaborales())+"\n" +
            "REPRESENTANTE LEGAL DE \n" +
            getContratista(a.getDatosLaborales())+"\n" +
            "P R E S E N T E.",FONT_PLAIN_BOLD));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             Paragraph paragraph = new Paragraph("El que suscribe ", FONT_PLAIN);
             paragraph.add(new Phrase(getNombre(a.getDatosPersonales()), FONT_PLAIN_BOLD));
             paragraph.add(new Phrase(", por este medio solicito mi pago del fondo de ahorro que se me venía reteniendo, en el cual fue descontado del pago nóminal, toda vez que he dado por terminada voluntariamente la relación laboral que me unía con usted ", FONT_PLAIN));
             paragraph.add(new Phrase(getRepresentanteLegal(a.getDatosLaborales()), FONT_PLAIN_BOLD));         
             paragraph.add(new Phrase(", por lo que solicito gire sus instrucciones a personal a su cargo para efecto de que realice los trámites correspondientes.",FONT_PLAIN));             
             cell = new PdfPCell(paragraph);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            Paragraph paragraph2 = new Paragraph("Por lo anterior expuesto a Usted, antemano pido:  ", FONT_PLAIN);
            cell = new PdfPCell(paragraph2);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            Paragraph paragraph3 = new Paragraph("PRIMERO: Tenerme por presentado el presente escrito y acordar de conformidad a lo solicitado, para todos los efectos legales y administrativos correspondientes.", FONT_PLAIN);
            cell = new PdfPCell(paragraph3);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(getNombre(a.getDatosPersonales()),FONT_PLAIN_TINY));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            solicitud.add(table);
                        
            solicitud.close();
            
        }  catch (IOException | DocumentException  me) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar el documento de la solicitud de asimilados", me);            
        }catch (Exception e) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar el documento de la solicitud de asimilados", e);
        }
        return baos;
    }

    /*@Override
    public ByteArrayOutputStream getConstanciaCreditoInfonavit(Agremiado a, ContratistaTemplate template) {
        printLogInnerClass("comienza la generación de la constancia de credito infonavit de un colaborador "+getDetallesAgremiado(a));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            
            Document solicitud = new Document(PageSize.LETTER, LEFT, RIGHT, TOP, BOTTOM);
            PdfWriter writer = PdfWriter.getInstance(solicitud, baos);
            solicitud.open();
            solicitud.addAuthor("Payroll");
            solicitud.addTitle("Constancia de credito infonavit");
            solicitud.addCreator("Payroll");
            solicitud.addKeywords("Payroll,constancia,creditoinfonavit");
            solicitud.addSubject("Constancia de credito infonavit");
            
            documentStamper(solicitud.getPageSize(),writer, template);
            
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
            cell = new PdfPCell(new Phrase(getFechaLarga(a.getDatosLaborales().getFechaInicioContrato()),FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("CARTA DE RECONOCIMIENTO DE ADEUDO A INFONAVIT",FONT_SUPER_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase(getContratista(a.getDatosLaborales())+"\n" + 
             "Atención área de administración de personal \n" +
            "P R E S E N T E.",FONT_PLAIN));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
              Paragraph paragraph = new Paragraph("A quien corresponda:  ", FONT_PLAIN);
              paragraph.add(new Phrase("Por este medio les informo que ", FONT_PLAIN));
              paragraph.add(new Phrase(" (  SI  /  NO  )", FONT_PLAIN_FANCY));
             paragraph.add(new Phrase(" cuento con crédito INFONAVIT actualmente. ",FONT_PLAIN));
            // paragraph.add(new Phrase( getEmail(a.getDatosPersonales()) ,FONT_PLAIN_FANCY));
             paragraph.add(new Phrase(", Los datos de mi crédito son: ",FONT_PLAIN));
             
             cell = new PdfPCell(paragraph);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            /**********
            cell = new PdfPCell(new Phrase("Número de crédito*",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Factor de descuento*",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(2);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Tipo de descuento*",FONT_MAIN));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setBorderWidthLeft(0.5f);
            cell.setBorderWidthRight(0.5f);
            cell.setBorderWidthBottom(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            /***************
            /**
            cell = new PdfPCell(new Phrase("*En caso de NO contar con crédito INFONAVIT, colcocar N/A en estos campos.",FONT_PLAIN_DTINY));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(5);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            /****/
            
            /*****
            Paragraph paragraph2 = new Paragraph("Acepto que en caso de contar con un crédito Infonavit y no reportarlo me sean descontados vía nómina en una sola exhibición, los montos elegibles por dicha institución, una vez que que la empresa que me contrata reciba la notificación correspondientes ", FONT_PLAIN);
             paragraph2.add(new Phrase(";e comprometo con la empresa" + getNombre(a.getDatosPersonales()) + "en que em casp de adquirir algún crédito INFONAVIT, haré de su conocimiento en un término máximo de 5 días hábiles posteriores a su autorización.", FONT_PLAIN_FANCY));
             paragraph2.add(new Phrase(" Además acepto la sansión administrativa correspondiente en caso de omitir información con respecto de tener o adquirir algún crédito INFONAVIT.",FONT_PLAIN));             
             cell = new PdfPCell(paragraph);
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            /******
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
             cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(3);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(getNombre(a.getDatosPersonales()),FONT_PLAIN_TINY));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setBorderWidthTop(0.5f);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n",FONT_TITULAR));
            cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell.setColspan(1);
            cell.setRowspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            
            solicitud.add(table);
                        
            solicitud.close();
            
        }  catch (IOException | DocumentException  me) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar el documento de la confirmación de correo electrónico", me);            
        }catch (Exception e) {
            printLogInnerClass("Ocurrio un problema al momento de intentar generar la confirmación de correo electrónico", e);
        }
        return baos;
    }
    * */
    
}
