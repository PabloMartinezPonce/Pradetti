/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.dao.impl.AgremiadoDaoImpl;
import com.pradettisanti.payroll.dao.impl.CatalogoDocumentosDaoImpl;
import com.pradettisanti.payroll.dao.impl.ContratoDaoImpl;
import com.pradettisanti.payroll.dao.impl.ContratoEmpresasDaoImpl;
import com.pradettisanti.payroll.dao.impl.PeriodoFondoAhorroDaoImpl;
import com.pradettisanti.payroll.dao.impl.SUPDaoImpl;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import com.pradettisanti.payroll.pojoh.DatosPersonales;
import com.pradettisanti.payroll.pojoh.Documento;
import com.pradettisanti.payroll.pojoh.EsquemaPago;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.PeriodoFA;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoSalarioUnicoProfesional;
import com.pradettisanti.payroll.service.AgremiadoService;
import com.pradettisanti.payroll.service.ContratistaService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author IT Seekers
 */
public class UnitTests {
     
    private static final Logger LOGGER = Logger.getLogger(SUPDaoImpl.class);
    

//    @Test
//    public void testGetSalariosUnicosProfesionales()
//    {
//        SUPDaoImpl supDAO = new SUPDaoImpl();
//        
//        TipoSalarioUnicoProfesional tsup = null; 
//        //supDAO.getSalariosUnicosProfesionales(tsup);
//        tsup = setTipoSalarioUnicoProfesional(9,"Productivo");
//        List<SalarioUnicoProfesional> list = supDAO.getSalariosUnicosProfesionales(tsup);
//        LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos");
//        System.out.println(list);
//    
//    }
//
//    private TipoSalarioUnicoProfesional setTipoSalarioUnicoProfesional(Integer idTipoSalarioUnicoProfesional, String sector) {
//        TipoSalarioUnicoProfesional tSUP = new TipoSalarioUnicoProfesional();
//           tSUP.setIdTipoSalarioUnicoProfesional(idTipoSalarioUnicoProfesional);
//           tSUP.setSector(sector);
//        return tSUP;
//    }
//    
//    private List<SalarioUnicoProfesional> getSalariosUnicosProfesionales(Cliente cliente){
//        List<SalarioUnicoProfesional> sup = (List<SalarioUnicoProfesional>) new SalarioUnicoProfesional();
//        return sup;
//    }
//    
//    @Test
//    public void testGetCatalogoDocumentalModulo(Integer idCatalogoDocumental, Short idModulo)
//    {
//        List<Modulo> modulo = (List<Modulo>) new Modulo();
//        
//        CatalogoDocumentosDaoImpl CatDocDAO = new CatalogoDocumentosDaoImpl();
//        
//        CatalogoDocumental catdoc = null; 
//        catdoc.setIdCatalogoDocumental(1);
//        
//        
//        LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos");
//       // System.out.println(list);
//    
//    }
    
//    @Test
//    public void testGetTipoDocumentosObgligatorios (){
//        List<TipoDocumento> tipoDoc = new ArrayList<>();
//        
//        CatalogoDocumentosDaoImpl CatDocDAO = new CatalogoDocumentosDaoImpl();
//        Short idModulo = 1;
//        tipoDoc = CatDocDAO.getTipoDocumentosObligatorios(idModulo);
//        System.out.println(tipoDoc);
//    }
    
//    @Test
//    public void testGetTipoDocumentosServicio (){
//        List<TipoDocumento> tipoDoc = new ArrayList<>();
//        
//        CatalogoDocumentosDaoImpl CatDocDAO = new CatalogoDocumentosDaoImpl();
//        Short idModulo = 6;
//        tipoDoc = CatDocDAO.getTipoDocumentosServicio(idModulo);
//        System.out.println("*****LISTAAA TIPO DE DOCUMENTO DE SERVICIO" + tipoDoc);
//    }
    
//    @Test
//    public void testGetTipoDocumentosNoServicio (){
//        List<TipoDocumento> tipoDoc = new ArrayList<>();
//        
//        CatalogoDocumentosDaoImpl CatDocDAO = new CatalogoDocumentosDaoImpl();
//        Short idModulo = 6;
//        tipoDoc = CatDocDAO.getTipoDocumentoNoServicio(idModulo);
//        System.out.println("*****LISTAAA TIPO DE DOCUMENTO DE NO SERVICIO" + tipoDoc);
//    }
  /********** AGREMIADO
     * @throws java.text.ParseException ***********/
    
//    @Test
//    public void testGetAgremiadosPorVencer(){
//        List<Agremiado> agremiados = new ArrayList<>();
//        
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//         String sh = "Activo";
//         Integer cl = 11;
//        agremiados = agremiado.getAgremiados(sh, cl);
//        System.out.println("++++++++++++++++++++++++++++++++++TESTING" + agremiados);
//       
//    }


    //*****************************************************************// 
    
//    @Test
//    public void testGetAgremiado(){
//        List<Agremiado> agremiados = new ArrayList<>();
//        
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//         Short statusAgremiado = 3;
//         Integer contratista = 1;
//        agremiados = agremiado.getAgremiado(statusAgremiado, contratista);
//        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO " + agremiados.size());
//            for (Agremiado agremiado1 : agremiados) {
//              System.out.println(">>>>>>>>>>>>>>>" + agremiado1);
//            }
//    }
//    @Test
//    public void testGetAgremiado(){
//        List<Agremiado> agremiados = new ArrayList<>();
//        
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//         Short statusAgremiado = 2;
//         Integer idContratista = 3;
//        agremiados = agremiado.getAgremiado(statusAgremiado, idContratista);
//        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO" + agremiados.size());
//            for (Agremiado agremiado1 : agremiados) {
//              System.out.println(">>>>>>>>>>>>>>>" + agremiado1);
//            }
//    }
    
//    @Test
//    public void testGetAgremiado(){
//        List<Agremiado> agremiados = new ArrayList<>();
//        
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//         Short statusAgremiado = 8;
//         Integer contratista = 17;
//        agremiados = agremiado.getAgremiado(statusAgremiado, contratista);
//        Integer count = 0;
//        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO" + agremiados.size());
//            for (Agremiado agremiado1 : agremiados) {
//                count = count + 1;
//                System.out.println(">>>>>>>>>>>>>>>" + count + " " + agremiado1);
//            }
//    }
    
    //********************************************************************//
    
//    @Test
//        public void testGetAgremiado(){
//        List<Agremiado> agremiados = new ArrayList<>();
//        
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//         Short sh = 1;
//         Integer cn = 3;
//         Integer cl = 11;
//        agremiados = agremiado.getAgremiado(sh, cl, cn);
//        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO: " + agremiados.size());
//            for (Agremiado agremiado1 : agremiados) {
//                System.out.println(">>>>>>>>>>>>>>>" + agremiado1);
//            }
//    }
    
//    @Test
//    public void testGetAgremiados(){
//        List<Agremiado> agremiados = new ArrayList<>();
//        
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//         Short sh = 1;
//         Integer cl = 2;
//     //   agremiados = agremiado.getAgremiados(sh, cl);
//        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO" + agremiados.size());
//            for (Agremiado agremiado1 : agremiados) {
//                System.out.println(">>>>>>>>>>>>>>>" + agremiado1);
//            }
//    }
//    @Test
//    public void testGetCamposDelEsquema(){
//        List<EsquemaCampo> esqCampo = new ArrayList<>();
//        
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//         Short sh = 1;
//         
//        EsquemaPago esquemaPago = new EsquemaPago(sh, "Mixto");
//        esqCampo = agremiado.getCamposDelEsquema(esquemaPago);
//        System.out.println(">>>>>>>>>>>>>>>ESQUEMA CAMPO: " + esqCampo);
////        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO" + agremiados.size());
////            for (EsquemaCampo esqCampo : agremiados) {
////                System.out.println(">>>>>>>>>>>>>>>" + esqCampo);
////            }
//    }
    
    //getContratosDelCliente
    
//    @Test
//    public void testGetContratosDelCliente(){
//        List<DelContrato> delContrato = new ArrayList<>();
//        
//        ContratoEmpresasDaoImpl contratoEmpresas = new ContratoEmpresasDaoImpl();
//         Integer cliente = 11;
//         
//        //EsquemaPago esquemaPago = new EsquemaPago(sh, "Mixto");
//        delContrato = contratoEmpresas.getContratosDelCliente(cliente);
//        System.out.println(">>>>>>>>>>>>>>>Contratos del cliente: " + delContrato);
//    }
//    @Test
//    public void testGetEsquemaDePagos(){
//        List<EsquemaPago> esquemaPago = new ArrayList<>();
//        
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//
//        esquemaPago = agremiado.getEsquemaDePagos();
//        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO" + esquemaPago.size());
//            for (EsquemaPago esqPago : esquemaPago) {
//                System.out.println(">>>>>>>>>>>>>>>: " + esqPago);
//            }
//    }
    
    //SVC-SSTMSMPRSRLS-TSKRSSDCV-37
    
//    @Test
//    public void testGetContratoEmpresasByIdName(){
//        ContratoEmpresas cemp = new ContratoEmpresas();
//        
//        ContratoEmpresasDaoImpl ce = new ContratoEmpresasDaoImpl();
//         String nombreContrato = "SVC-SSTMSMPRSRLS-TSKRSSDCV-37";
//        cemp = ce.getContratoEmpresasByIdName(nombreContrato);
//        System.out.println("+++++++ OBJETO CONTRATO EMPRESAS CON EL CRITERIO DE NOMBRE: " + cemp);
//    } getTipoDocumento
    
//    @Test
//    public void testGetContratoEmpresasByIdName(){
//        List<TipoDocumento> tipoDoc = new ArrayList<>();
//        AgremiadoDaoImpl agremiado = new AgremiadoDaoImpl();
//        
//        Short modulo = 1;        
//        Short ep = 4;
//       // tipoDoc = agremiado.getTipoDocumento(modulo, ep);
//        
//        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO" + tipoDoc.size());
//        for (TipoDocumento tipoDocu : tipoDoc) {
//                System.out.println(">>>>>>>>>>>>>>>: " + tipoDocu);
//            }
//    }
    
//    @Test
//    public void testGetPorcentFA(){
//        List<Agremiado> agremiados = new ArrayList<>();
//        AgremiadoDaoImpl AgremiadoDao = new AgremiadoDaoImpl();
//        
//        Integer idAgremiado = 1125;        
//        agremiados = AgremiadoDao.getPorcentFA(idAgremiado);
//        
//        System.out.println("++++++++++++++++++++++++++++++++++TAMAÑO" + agremiados.size());
//        for (Agremiado ag : agremiados) {
//                System.out.println(">>>>>>>>>>>>>>>: " + ag);
//            }
//    }
    
//    @Test
//    public void testGetPeriodosByDates() throws ParseException{
//        String fechaultimoDiaFAString = "2019-10-18";
//        Date fechaultimoDiaFA = procesayyyyMMdd(fechaultimoDiaFAString);
//        PeriodoFondoAhorroDaoImpl PeriodoFADao = new PeriodoFondoAhorroDaoImpl();
//        List<PeriodoFA> periodoFA = null;
//        Integer idAgremiado = 1125;
//       
//        Date fechaPrimerDiaFA = PeriodoFADao.getPrimerFechaFA(idAgremiado);
//        //fechaultimoDiaFA = PeriodoFADao.getUltimaFechaFA(idAgremiado);
//        
//        periodoFA = PeriodoFADao.getPeriodosByDates(fechaPrimerDiaFA, fechaultimoDiaFA, idAgremiado);
//        
//        System.out.println("++++++++++++++++++++++++++++++++++Primer fecha de FONDO DE AHORRO " + fechaPrimerDiaFA);
//        System.out.println("++++++++++++++++++++++++++++++++++Ultimo fecha de FONDO DE AHORRO " + fechaultimoDiaFA);
//
//        for (PeriodoFA perFA : periodoFA) {
//                System.out.println(">>>>>>>>>>>>>>> PERIODOS : " + perFA);
//            }
//        
//    }

//    @Test
//    public void testGetFechaFinPeriodo() throws ParseException{
//        Integer idAgremiado = 1125;
//        Integer idPeriodo = 12;
//        String fechaIniString = "2018-07-01";
//        String fechaFinString = "2019-06-30";
//        
//        Date fechaIni = procesayyyyMMdd(fechaIniString);
//        Date fechaFin = procesayyyyMMdd(fechaFinString);
//        
//        PeriodoFondoAhorroDaoImpl PeriodoFADao = new PeriodoFondoAhorroDaoImpl();
//        Date porcentaje = PeriodoFADao.getFechaFinPeriodo(idPeriodo);
//        
//        System.out.println("++++++++++++++++++++++++++++++++++FECHA FIN DEL PERIODO SELECCIONADO: " + porcentaje);
//    }
//    
//private Date procesayyyyMMdd(String fechaString) throws ParseException {    
//        String yyyyMMdd = "yyyy-MM-dd";
//        DateFormat formatter = new SimpleDateFormat(yyyyMMdd);
//        return formatter.parse(fechaString);
//    }
//}
//    
    
// private EntityManagerFactory emf;
//    
// @Before
// public void init(){
//     emf = Persistence.createEntityManagerFactory("my-persistence-unit");
// }
// 
// @After
// public void close(){
//        emf.close();
//}
//    
// @Test
//    public void fondoAhorro(){
//        
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        
//        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("SP_calculoFondoDeAhorro");
//        query.setParameter("idAgremiado", 1125);
//        query.execute();
//        String porcent = (String) query.getOutputParameterValue("fa_actual");
//        Date fa_fecha = (Date) query.getOutputParameterValue("fa_fecha");
//        
//        System.out.println("Porcentaje: " + porcent + "Fecha: " + fa_fecha);
//        
//        em.getTransaction().commit();
//        em.close();
//    }
 
    @Test
    public void testGetifExistsCurp(){
        DatosPersonales datosPersonales = new DatosPersonales();
        DatosPersonales datosPersonales2 = new DatosPersonales();
        DatosPersonales datosPersonales3 = new DatosPersonales();
        
        AgremiadoDaoImpl a = new AgremiadoDaoImpl();
         String curp = "GASF910312HOCRNR04";
         String rfc = "GASF910312HT6";
        datosPersonales = a.getifExistsCurp(curp);
        datosPersonales2 = a.getifExistsRFC(rfc);
      //  datosPersonales3 = a.getifExistsCurpRFC(curp, rfc);
        
        
        System.out.println("CURP: "+ datosPersonales);
        System.out.println("RFC: "+ datosPersonales2);
        System.out.println("CURP Y RFC: "+ datosPersonales3);
        
         if(datosPersonales ==null && datosPersonales2 ==null){
             System.out.println("EL COLABORADOR HA SIDO INGRESO CORRECTAMENTE");
         }else if (datosPersonales != null && datosPersonales2 != null){
            System.out.println("+++++++ LA CURP " + curp+ " Y EL RFC " + rfc + " YA ESTAN REGISTRADOS/n"+"EL COLABORADOR SE ENCUENTRA EN EL STATUS: " +datosPersonales3.getAgremiadoObj().getStatusAgremiado());
         }else if(datosPersonales2 != null){
             System.out.println("+++++++ EL RFC " + rfc + " YA ESTA REGISTRADA/n"+"EL COLABORADOR SE ENCUENTRA EN EL STATUS: " +datosPersonales2.getAgremiadoObj().getStatusAgremiado());
         }else if(datosPersonales != null){
             System.out.println("+++++++ LA CURP" + curp+ " YA ESTA REGISTRADA/n"+"EL COLABORADOR SE ENCUENTRA EN EL STATUS: " +datosPersonales.getAgremiadoObj().getStatusAgremiado());
         }
        
    }
}
    

