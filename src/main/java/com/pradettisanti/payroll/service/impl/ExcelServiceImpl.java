/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.ExcelService;
import com.pradettisanti.payroll.service.ReciboService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service encargado de administrar la carga del archivo de nómina (excel)
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Service
public class ExcelServiceImpl implements ExcelService {
    
    
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ExcelServiceImpl.class);
    
    @Autowired
    private AgremiadoService agremiadoService;
    public AgremiadoService getAgremiadoService(){
        return agremiadoService;
    }
    public void setAgremiadoService(AgremiadoService agremiadoService){
        this.agremiadoService = agremiadoService;
    }
    
    @Autowired
    private ReciboService reciboService;
    public ReciboService getReciboService(){
        return reciboService;
    }
    public void setReciboService(ReciboService reciboService){
        this.reciboService = reciboService;
    }

    /**
     * Metodo encargado de leer el archivo de nomina y devolverlo en recibos virtuales
     * @param cliente instancia de cliente
     * @param archivoDeNomina Archivo de nomina en xlsx
     * @param diaInicial Instancia Date
     * @param diaFinal Instancia Date
     * @return List Recibo
     */
    @Override
    public List<Recibo> archivoDeNomina(Cliente cliente, MultipartFile archivoDeNomina, Date diaInicial, Date diaFinal, Sindicato sindicato ){
        LOGGER.info("[Service, Excel] Se solicita el parceo de un archivo para nómina  {"+cliente.getNombreEmpresa()+'}');
        List<Recibo> recibos =  new ArrayList<>();
        List<ReciboExcel> reciboExcel = new ArrayList<>();
        try {
                // Se obtiene la instancia para un archivo XLSX
                XSSFWorkbook workbook = new XSSFWorkbook( archivoDeNomina.getInputStream() );
                // Se obtiene la hoja donde se almacena la nómina
                XSSFSheet sheet = workbook.getSheet("NÓMINA");
                // Si se encuentra la hoja dentro del libro se intenta ingresar a los datos de la misma
                if( sheet != null ){
                    Iterator<Row> iterator = sheet.iterator();
                    XSSFRow row;   
                    XSSFCell idColaboradorCell;      
                    XSSFCell totalSindicalCell ;
                    XSSFCell totalNominalCell;
                    XSSFCell deduccionesConceptoCell;
                    XSSFCell deducionesImporteCell ;
                    XSSFCell diasTrabajadosCell ;
                    LOGGER.info("[Service, Excel] Se comienza la lectura de la hoja de cálculo {"+cliente.getNombreEmpresa()+'}');
                    while (iterator.hasNext()) {
                        row = (XSSFRow) iterator.next();
                        // Desde la tercera fila se encuentras los agremiados dentro de la hoja de excel
                        if( row.getRowNum() > 3 ){
                             idColaboradorCell = row.getCell(3);//0
                            if(idColaboradorCell != null){                        
                                 totalSindicalCell = row.getCell(20);//1
//                                 totalNominalCell = row.getCell(2);
                                 deduccionesConceptoCell = row.getCell(39);
                                 deducionesImporteCell = row.getCell(40);
                                 diasTrabajadosCell = row.getCell(10);//5
                                try{                                    
                                    ReciboExcel re = new ReciboExcel();
                                    re.setIdAgremiado( (int) idColaboradorCell.getNumericCellValue() );
                                    re.setTotalSindical( ( totalSindicalCell != null ) ? totalSindicalCell.getNumericCellValue() : 0.0 );
//                                    re.setTotalNominal( ( totalNominalCell != null ) ? totalNominalCell.getNumericCellValue() : 0.0 );
                                    re.setDeduccionesConcepto( ( deduccionesConceptoCell != null ) ? deduccionesConceptoCell.getStringCellValue() : "DEDUCCIONES SINDICALES" );
                                    re.setDeducionesImporte( ( deducionesImporteCell != null ) ? deducionesImporteCell.getNumericCellValue() : 0.0  );
//                                    re.setDeduccionesConcepto("Aportacion");
//                                    re.setDeducionesImporte(25);
                                    re.setDiasTrabajados( ( diasTrabajadosCell != null ) ? (int) diasTrabajadosCell.getNumericCellValue()  : 0 );
                                    //OJO REVISAR ESTA VALIDACÓN POR QUE SE GENERA RECIBO SI ES 0.00001 -0000000 ETC. LO IDEAL SERÍA QUE FUERA MAYOR A 0
                                    if(re.getTotalSindical()>= 1){
                                        reciboExcel.add(re);
                                    }
                                } catch (Exception e) {
                                    LOGGER.error("[Service, Excel] Ocurrio un error en el parceo de la información del colaborador se omitirá su recibo  {"+cliente.getNombreEmpresa()+'}'+" --> "+e);
                                }finally{                                    
                                     idColaboradorCell = null;                     
                                    totalSindicalCell = null;
                                    totalNominalCell = null;
                                    deduccionesConceptoCell = null;
                                    deducionesImporteCell = null;
                                    diasTrabajadosCell = null;
                                    Runtime.getRuntime().gc(); 
                                }
                            }                       
                        }// Fin If >3
                    } // Fin Iterator    
                    row =null;
                    iterator = null;
                    Runtime.getRuntime().gc(); 
                    LOGGER.info("[Service, Excel] Se termina la lectura de la hoja de cálculo  {"+cliente.getNombreEmpresa()+'}');
                }else{
                    LOGGER.error("[Service, Excel] No se encontró una hoja dentro del libro con el nombre 'NÓMINA' {"+cliente.getNombreEmpresa()+'}');
                    throw new IOException("No se encontró la hoja con nombre dentro del libro de excel {"+cliente.getNombreEmpresa()+'}');
                } // Fin IF (sheet!=null)
        
        } catch (IOException ioe) {            
                    LOGGER.error("[Service, Excel] Ocurrio un problema al intentar acceder al archivo de nómina  {"+cliente.getNombreEmpresa()+'}'+" --> "+ioe);
        }catch (Exception e) {            
                    LOGGER.error("[Service, Excel] Ocurrio un problema al intentar acceder al archivo de nómina  {"+cliente.getNombreEmpresa()+'}'+" --> "+e);
        }
        LOGGER.info("[Service, Excel] Recibos virtuales encontrados en el excel "+reciboExcel.size() +" {"+cliente.getNombreEmpresa()+'}' );
        List<ReciboExcel> removeEmptyTickets = new ArrayList<>(reciboExcel.size());
        for (ReciboExcel re : reciboExcel) {
            if(re.getIdAgremiado()!=0){
                removeEmptyTickets.add(re);
            }
        }
        reciboExcel = removeEmptyTickets;
        removeEmptyTickets = null;
        LOGGER.info("[Service, Excel] Recibos reales encontrados en el excel "+reciboExcel.size() +" {"+cliente.getNombreEmpresa()+'}' );
        if( reciboExcel.size() > 0 ){                   
            LOGGER.info("[Service, Excel] Se comienza la petición de los agremiados del cliente con estatus activo  {"+cliente.getNombreEmpresa()+'}');
                List<Agremiado> agremiadosDelCliente =  getColaboradoresDelCliente("Activo", cliente.getIdCliente());
                agremiadosDelCliente.addAll( getColaboradoresDelCliente("Baja Pendiente", cliente.getIdCliente()) );
                agremiadosDelCliente.addAll( getColaboradoresDelCliente("Baja Por Finalizar", cliente.getIdCliente()) );
                agremiadosDelCliente.addAll( getColaboradoresDelCliente("Baja Solicitada", cliente.getIdCliente()) );
                agremiadosDelCliente.addAll( getColaboradoresDelCliente("Solicitud De Renovación", cliente.getIdCliente()) );
                agremiadosDelCliente.addAll( getColaboradoresDelCliente("Renovación Solicitada", cliente.getIdCliente()) );
                Map<Integer,Agremiado> agremiadosMap = new HashMap<>();
                agremiadosDelCliente.stream().forEach((agremiado) -> {
                    agremiadosMap.put(agremiado.getIdAgremiado(), agremiado);
                });   
                agremiadosDelCliente = null;
            LOGGER.info("[Service, Excel] Se termina la petición de los agremiados del cliente con estatus activo , se comienza la migración de objetos ["+agremiadosMap.size()+"/"+reciboExcel.size()+"] {"+cliente.getNombreEmpresa()+'}');
                for (ReciboExcel reciboXcl : reciboExcel) {
                    boolean reciboNoEncontrado = true;
                     // Se busca que el recibo no se encuentre previamente ya registrado
                    List<Recibo> recibosEncontrados = reciboService.getRecibos( cliente, diaInicial, diaFinal ); 
                    for (Recibo recibosEncontrado : recibosEncontrados) {
                        if( Objects.equals(recibosEncontrado.getAgremiado().getIdAgremiado(), reciboXcl.getIdAgremiado()) ){
                            reciboNoEncontrado = false;
                            break;
                        }
                    }
                    if(reciboNoEncontrado){
                        Agremiado agremiado = agremiadosMap.get( reciboXcl.getIdAgremiado() );                   
                        if( agremiado != null ){
                            DatosLaborales datosLaborales = agremiado.getDatosLaborales();
                            if(datosLaborales != null){                                                                           
                                // si el recibo no se encuentra registrado se ingresan los valores obtenidos a un nuevo recibo
                                Recibo recibo = new Recibo(null);
                                recibo.setAgremiado(agremiado);
                                recibo.setTotalSindicato(reciboXcl.getTotalSindical());
                                recibo.setTotalNomina(reciboXcl.getTotalNominal());
                                recibo.setDeduccionesConcepto(reciboXcl.getDeduccionesConcepto());
                                recibo.setDeduccionesImporte(reciboXcl.getDeducionesImporte());
                                recibo.setDiasTrabajados(reciboXcl.getDiasTrabajados());                                
                                recibo.setSindicato(sindicato);
                                recibo.setClientes(cliente);
                                recibo.setDiaInicial(diaInicial); 
                                recibo.setDiaFinal(diaFinal);
                                recibo.setDiaDeRegistro( new Date() );
                                recibos.add(recibo);
                                agremiado = null;
                                datosLaborales = null;
                                Runtime.getRuntime().gc(); 
                            }else{
                                LOGGER.error("[Service, Excel] El agremiado ["+agremiado.getIdAgremiado()+"] no tiene datos laborales registrados se omitirá el registro de su recibo, se omitirá el registro del recibo.  {"+cliente.getNombreEmpresa()+'}');                                           
                            }                                
                        }else{
                            LOGGER.error("[Service, Excel] El Map devolvió NULL el recibó del agremiado "+reciboXcl.getIdAgremiado()+", por lo cual el recibo será omitido.");
                        }
                    }else{
                            LOGGER.error("[Service, Excel] El recibo ya se encuetra registrado . {"+cliente.getNombreEmpresa()+", "+reciboXcl.getIdAgremiado()+"}");                                 
                    }

                }     
                LOGGER.info("[Service, Excel] Se termina la migración de los objetos ["+recibos.size()+'/'+reciboExcel.size()+"] {"+cliente.getNombreEmpresa()+'}');                
                reciboExcel = null;
            }
        Runtime.getRuntime().gc(); 
        return recibos;
    }
    
    /**
     * Metodo encargado de actualizar los recibos de un cliente que ya fueron ingresados previamente
     * @param cliente
     * @param archivoNomina
     * @param diaInicial
     * @param diaFinal
     * @param sindicato
     * @return List Recibo
     */
    @Override
    public List<Recibo> actualizarNomina(Cliente cliente, MultipartFile archivoNomina, Date diaInicial, Date diaFinal, Sindicato sindicato) {
        LOGGER.info("[Service, Excel] Se solicita el parceo de un archivo para actualizar la nómina  {"+cliente.getNombreEmpresa()+'}');
        List<Recibo> recibos =  new ArrayList<>();
        List<ReciboExcel> reciboExcel = new ArrayList<>();
        try {
                // Se obtiene la instancia para un archivo XLSX
                XSSFWorkbook workbook = new XSSFWorkbook( archivoNomina.getInputStream() );
                // Se obtiene la hoja donde se almacena la nómina
                XSSFSheet sheet = workbook.getSheet("NÓMINA");
                // Si se encuentra la hoja dentro del libro se intenta ingresar a los datos de la misma
                if( sheet != null ){
                    Iterator<Row> iterator = sheet.iterator();
                    XSSFRow row;   
                    XSSFCell idColaboradorCell;      
                    XSSFCell totalSindicalCell ;
                    XSSFCell diasTrabajadosCell ;
                    LOGGER.info("[Service, Excel] Se comienza la lectura de la hoja de cálculo {"+cliente.getNombreEmpresa()+'}');
                    while (iterator.hasNext()) {
                        row = (XSSFRow) iterator.next();
                        // Desde la tercera fila se encuentras los agremiados dentro de la hoja de excel
                        if( row.getRowNum() > 1 ){
                             idColaboradorCell = row.getCell(0);//0
                            if(idColaboradorCell != null){                        
                                 totalSindicalCell = row.getCell(2);//2
                                 diasTrabajadosCell = row.getCell(3);//3
                                try{                                    
                                    ReciboExcel re = new ReciboExcel();
                                    re.setIdAgremiado( (int) idColaboradorCell.getNumericCellValue() );
                                    re.setTotalSindical( ( totalSindicalCell != null ) ? totalSindicalCell.getNumericCellValue() : 0.0 );
                                    re.setDiasTrabajados( ( diasTrabajadosCell != null ) ? (int) diasTrabajadosCell.getNumericCellValue()  : 0 );
                                    reciboExcel.add(re);
                                } catch (Exception e) {
                                    LOGGER.error("[Service, Excel] Ocurrio un error en el parceo de la información del colaborador se omitirá su recibo  {"+cliente.getNombreEmpresa()+'}'+" --> "+e);
                                }finally{                                    
                                     idColaboradorCell = null;                     
                                    totalSindicalCell = null;
                                    diasTrabajadosCell = null;
                                    Runtime.getRuntime().gc(); 
                                }
                            }                       
                        }// Fin If >3
                    } // Fin Iterator    
                    row =null;
                    iterator = null;
                    Runtime.getRuntime().gc(); 
                    LOGGER.info("[Service, Excel] Se termina la lectura de la hoja de cálculo  {"+cliente.getNombreEmpresa()+'}');
                }else{
                    LOGGER.error("[Service, Excel] No se encontró una hoja dentro del libro con el nombre 'NÓMINA' {"+cliente.getNombreEmpresa()+'}');
                    throw new IOException("No se encontró la hoja con nombre dentro del libro de excel {"+cliente.getNombreEmpresa()+'}');
                } // Fin IF (sheet!=null)        
        } catch (IOException ioe) {            
                    LOGGER.error("[Service, Excel] Ocurrio un problema al intentar acceder al archivo de nómina  {"+cliente.getNombreEmpresa()+'}'+" --> "+ioe);
        }catch (Exception e) {            
                    LOGGER.error("[Service, Excel] Ocurrio un problema al intentar acceder al archivo de nómina  {"+cliente.getNombreEmpresa()+'}'+" --> "+e);
        }
        LOGGER.info("[Service, Excel] intentando actualizar "+reciboExcel.size()+" Recibos del cliente "+cliente.getNombreEmpresa()+".");
        reciboExcel.forEach((ReciboExcel reciboXCL) -> {
            Agremiado agremiado = agremiadoService.getAgremiado(reciboXCL.getIdAgremiado());
            if(agremiado == null){
                LOGGER.error("[Service, Excel] No se encontró un agremiado con el id "+reciboXCL.getIdAgremiado()+".");
            }else if(!Objects.equals(agremiado.getDatosLaborales().getClienteObj().getIdCliente(), cliente.getIdCliente())){
                LOGGER.error("[Service, Excel] El agremiado con el id "+agremiado.getIdAgremiado()+", no pertenecce al cliente "+cliente.getNombreEmpresa()+".");
            }else{
                Recibo recibo = reciboService.getRecibo(agremiado.getIdAgremiado(), cliente, diaInicial, diaFinal);
                if(recibo == null){
                    LOGGER.error("[Service, Excel] No se encontró un recibo con la información recibida {"+reciboXCL.getIdAgremiado()+", "+cliente.getNombreEmpresa()+", "+diaInicial+", "+diaFinal+"}.");
                }else{
                    recibo.setTotalSindicato( ( reciboXCL.getTotalSindical() != 0.0 ) ? reciboXCL.getTotalSindical():recibo.getTotalSindicato() );
                    recibo.setDiasTrabajados(( reciboXCL.getDiasTrabajados() != 0 ) ? reciboXCL.getDiasTrabajados():recibo.getDiasTrabajados() );
                    Date date = new Date();
                    recibo.setDiaModificado( date );
                    recibo.setSindicato(sindicato);
                    reciboService.setRecibo(recibo);
                    LOGGER.info("[Service, Excel] Recibo actualizado {"+recibo.getIdRecibo()+" - "+date+"}.");
                    recibos.add(recibo);
                }
            }
        });
        Runtime.getRuntime().gc(); 
        return recibos;
        
    }
    
    private List<Agremiado> getColaboradoresDelCliente(String statusAgremiado, Integer cliente){
       return agremiadoService.getAgremiado(statusAgremiado, cliente);
    }
}

/**
 * Clase auxiliar internar encargada de auxiliar en el procesamiento de los recibos
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
class ReciboExcel{
    private Integer idAgremiado = 0;
    private double totalSindical = 0.0;
    private double totalNominal =  0.0;
    private String deduccionesConcepto =  null;
    private double deducionesImporte = 0.0;
    private int diasTrabajados =   0;

    public Integer getIdAgremiado() {
        return idAgremiado;
    }

    public void setIdAgremiado(Integer idAgremiado) {
        this.idAgremiado = idAgremiado;
    }

    public double getTotalSindical() {
        return totalSindical;
    }

    public void setTotalSindical(double totalSindical) {
        this.totalSindical = totalSindical;
    }

    public double getTotalNominal() {
        return totalNominal;
    }

    public void setTotalNominal(double totalNominal) {
        this.totalNominal = totalNominal;
    }

    public String getDeduccionesConcepto() {
        return deduccionesConcepto;
    }

    public void setDeduccionesConcepto(String deduccionesConcepto) {        
        if(deduccionesConcepto != null && deduccionesConcepto.length() > 199){
            deduccionesConcepto = deduccionesConcepto.substring(0, 200);
        }
        this.deduccionesConcepto = deduccionesConcepto;
    }

    public double getDeducionesImporte() {
        return deducionesImporte;
    }

    public void setDeducionesImporte(double deducionesImporte) {
        this.deducionesImporte = deducionesImporte;
    }

    public int getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(int diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }    
    
}