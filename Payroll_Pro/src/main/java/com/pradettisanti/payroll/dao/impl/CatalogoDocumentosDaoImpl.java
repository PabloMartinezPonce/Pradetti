/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.dao.CatalogoDocumentosDao;
import com.pradettisanti.payroll.pojoh.CatalogoDocumental;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Entidad encargada de representar la tabla catalogo_documental
 * @author Gabriela Jaime Juárez
 * @since 2017/12/29
 * @version 1
 */
@Component
public class CatalogoDocumentosDaoImpl implements CatalogoDocumentosDao{

    private static final Logger LOGGER = Logger.getLogger(CatalogoDocumentosDaoImpl.class); //Variable que almacena los logs que se asignan en cada metodo
    
    /**
     * Metodo encargado de ingresar/ actulizar un catalogo de documentos
     * @param catalogoDocumental 
     */
    @Override
    public void setCatalogoDocumentos(CatalogoDocumental catalogoDocumental) { //Metodo que guarda/actualiza un catalogo de documentos recibe como parameto una varible de tipo CatalogoDocumental
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); // Se obtiene la sesión Hibernate
        Transaction tx = session.beginTransaction(); //Se iniicializa la transacción
        try {
            session.saveOrUpdate(catalogoDocumental);//Se solicita guardar el objeto catalogoDocumental
            tx.commit(); //Se guarda el objeto reciboSolicitado
        } catch (HibernateException he) { //Excpeción Hibernate
            tx.rollback(); //Se regresa la petición de guardar el objeto catalogoDocumental
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime en el log el msj de error + variable de error Hibernate
        } finally { // Si no pasa por el try ni el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
    }
    
    /**
     * Metodo encargado de devolver un catalago documental en base al id ingresado
     * @param id
     * @return CatalogoDocumental
     */
    @Override
    public CatalogoDocumental getCatalogoDocumental(Integer id) { // Recibe como parametro el id y regresa un objeto de tipo CatalogoDocumental
        CatalogoDocumental catDocumental = new CatalogoDocumental(); //Instancia del pojoh CatalogoDocumental
        HibernateUtl.buildSessionFactory(); // Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); // Se obtiene la sesión Hibernate
        Transaction tx = session.beginTransaction(); // Se inicia la petición
        try {
            // se le asigna la sessión la query CatalogoDocumental.findById perteneciente al pojoh CatalogoDocumental seteandole el parametro id al idCatalogoDocumental del pojoh
            catDocumental = (CatalogoDocumental) session.getNamedQuery("CatalogoDocumental.findById").setInteger("idCatalogoDocumental", id).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) { // Excepción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime en el log el msj de error + variable de error Hibernate
        } finally { // Si no pasa por el try ni el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); // Se cierra la sesió Hibernate
        }
        return catDocumental; // Regresa todo lo que traiga CatalogoDocumental 
    }

    /**
     * Metodo encargado de devolver todos los catalogos documentales
     * @return List CatalogoDocumental
     */
    @Override
    public List<CatalogoDocumental> getCatalogoDocumental() {
        List<CatalogoDocumental> catDocumental = new ArrayList<>(); // Se declara una Lista de tipo CatalogoDocumental
        HibernateUtl.buildSessionFactory(); // Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); // Se obtiene la sesión hibernate
        Transaction tx = session.beginTransaction(); // Se inicia la petición
        try {
            //se le asigna ala variable catDocumental la namedQuery CatalogoDocumental.findAll del pojoh CatalogoDocumental sin parametros
            catDocumental = session.getNamedQuery("CatalogoDocumental.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) { // Excepción hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime en el log el msj de error + variable 
        } finally { // Si no pasa por el try ni el catch 
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return catDocumental; // Regresa el valor de catDocumental
    }
    
    /**
     * Metodo encargado de devolver todos los catalogos documentales que son servicio
     * @return List CatalogoDocumental
     */
    @Override
    public List<CatalogoDocumental> getCatalagoDocumentalDeServicio() {
        List<CatalogoDocumental> catDocumental = new ArrayList<>(); // Se declara una Lista de tipo CatalogoDocumental
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); // Se obtiene la sesión hibernate
        Transaction tx = session.beginTransaction(); // Se inicia la petición
        try {
            //se le asigna ala variable catDocumental la namedQuery CatalogoDocumental.findByServicio del pojoh CatalogoDocumental sin parametros ya que la namedQuery tiene la condición
            catDocumental = session.getNamedQuery("CatalogoDocumental.findByServicio").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) { // Excepción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime en el log el msj de error + variable Hibernate
        } finally { // si no pasa por el try ni l catch
            HibernateUtl.closeSessionAndUnbindFromThread(); // Se cierra la sesión Hibernate
        }
        return catDocumental; // Regresa el valor de catDocumental
    }
    /**
     * Metodo encargado de devolver todos los catalogos documentales que no son servicios
     * @return List CatalogoDocumental
     */
    @Override
    public List<CatalogoDocumental> getCatalogoDocumentalDeNoServicio() {
        List<CatalogoDocumental> catDocumental = new ArrayList<>(); // Se declará una Lista de tipo CatalogoDocumental
        HibernateUtl.buildSessionFactory(); // Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); // Se obtiene la sesión hibernate 
        Transaction tx = session.beginTransaction(); // Se inicia la petición
        try {
            //se le asigna a la cariable catDocumental la namedQuery CatalogoDocumental.findByNoServicio del pojoh CatalogoDocumental sin parametro ya que la named 
            catDocumental = session.getNamedQuery("CatalogoDocumental.findByNoServicio").setCacheMode(CacheMode.IGNORE).list(); 
        } catch (HibernateException he) { // Expeción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime en el log el msj de error + variable Hibernate
        } finally { // si no pasa por el try ni el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return catDocumental; //Regresa el valor de catDocumental
    }
    
    /**
     * Metodo encargado de devolver todos los catalagos documentales en base al nombre del documento
     * @param nombreDocumento
     * @return List CatalogoDocumental
     */
    @Override
    public List<CatalogoDocumental> getCatalagoDocumentalNombreContrato(String nombreDocumento) {
        List<CatalogoDocumental> catDocumental = new ArrayList<>(); // Se declará una Lista de tipo CatalogoDocumental
        HibernateUtl.buildSessionFactory(); // Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); // Se obtiene la sesión hibernate 
        Transaction tx = session.beginTransaction(); // Se inicia la petición
        try {
            //se le asigna a la cariable catDocumental la namedQuery CatalogoDocumental.findByNombreDocumento del pojoh CatalogoDocumental con el parametro nombreDocumento
            catDocumental = session.getNamedQuery("CatalogoDocumental.findByNombreDocumento").setString("nombreDocumento",nombreDocumento).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) { // Expeción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime en el log el msj de error + variable Hibernate
        } finally { // si no pasa por el try ni el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return catDocumental; //se regresa el valor de catDocumental
    }
    
    /**
     * Metodo encargado de devolver una lista de catalagos documentales que peretenecen a un modulo especifico
     * @param idModulo
     * @return List CatalogoDocumental
     */
    @Override
    public List<TipoDocumento> getTipoDocumentoModulo(Short idModulo) {
        List<TipoDocumento> tipoDoc = new ArrayList<>(); // Se declará una Lista de tipo TipoDocumento
        HibernateUtl.buildSessionFactory(); // Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); // Se obtiene la sesión hibernate 
        Transaction tx = session.beginTransaction(); // Se inicia la petición
        try 
        {
            //Se crea una variable de tipo Criteria y se le asigna el valor del pojoh TipoDocumento
            Criteria createCriteria = session.createCriteria(TipoDocumento.class);
            createCriteria.add(Restrictions.eq("moduloObj", idModulo)); // Se agrega una restricción para que el parametro requerido sea igual al idModulo del pojoh           
            tipoDoc = createCriteria.list(); // Se le asigna el valor en forma de lista a la variable tipoDoc
        } catch (HibernateException he) { // Expeción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime en el log el msj de error + variable Hibernate
        } finally { // si no pasa por el try ni el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return tipoDoc; //Se regresa el valor de tipoDoc
    }
    
    /**
     * Metodo encargado de devolver todos los modulos registrados
     * @return List Modulo
     */
    @Override
    public List<Modulo> getModulos() {
        List<Modulo> modulo = new ArrayList<>(); // Se declará una Lista de tipo Modulo
        HibernateUtl.buildSessionFactory(); // Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); // Se obtiene la sesión hibernate
        Transaction tx = session.beginTransaction(); // Se inicia la petición
        try {
            //se le asigna a la variable catDocumental la namedQuery Modulo.findAll del pojoh Modulo
            modulo = session.getNamedQuery("Modulo.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) { // Expeción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime en el log el msj de error + variable Hibernate
        } finally { // si no pasa por el try ni el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return modulo; //Se regresa el valor de la variable modulo
    }
    
    /**
     * Metodo encargado de devolver una lista de TipoDocumento de un modulo x y que son obligatorios
     * @param idModulo
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumentosObligatorios(Short idModulo) { 
        List<TipoDocumento> tipoDoc = new ArrayList<>(); // Se declará una Lista de tipo TipoDocumento
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //se obtiene la sesión hibernate
        Transaction tx = session.beginTransaction(); // Se inicia la petición
        try{
            //Se le asigna a la variable tipoDoc la clase Criteria en base el pojo TipoDocumento
           Criteria createCriteria = session.createCriteria(TipoDocumento.class);
           createCriteria.add(Restrictions.eq("moduloObj", idModulo)) //Se le agrega la restricción de que el dato moduloObj sea igual al parametro que esta recibiendo en la firma
                         .add(Restrictions.eq("obligatorio", true)); //Se le agrega la restricción de que el dato obligatorio sea igual a true para que valide que sean obligatorios
           tipoDoc = createCriteria.list(); //Se enlistan los criterios en la variable tipoDoc
        } catch (HibernateException he){ //Excpeción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime el log del msj de error + variable Hibernate
        } finally { // si no pasa por el try ni el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return tipoDoc; //Se regresa el valor de la variable tipoDoc
    }
    
    /**
     * Metodo encargado de devolver una lista de TipoDocumento de un modulo x que sea servicio
     * @param idModulo
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumentosServicio(Short idModulo) {
        List<TipoDocumento> tipoDoc = new ArrayList<>(); //Se instancia una lista de tipo TipoDocumento
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //Se obtiene la sesión Hibernate
        Transaction tx = session.beginTransaction(); // Se incia la petición
        try{  
           List<CatalogoDocumental> catalogoDoc = new ArrayList<>(); //Se instancia una lista de tipo CatalogoDocumental
            //Se realizan los criterios empezando desde la relación mapeada en CatalogoDocumental y se le asigna un alias "cd"
            Criteria createCriteria = session.createCriteria(CatalogoDocumental.class, "cd")
                    // Se mapea la relación con TipoDocumental por la propiedad manejada en el pojoh Catalago Documental = "tipoDocumentosList"
                    .setFetchMode("cd.tipoDocumentosList", org.hibernate.FetchMode.JOIN) 
                    .createAlias("cd.tipoDocumentosList", "tp") //Se crea un alias para la relación de CatalagoDocumental y TipoDocumento = "tp"
                    //Se agrega la restricción del moduloObj del pojoh TipoDocumento sea igual al parametro idModulo
                    .add(Restrictions.eq("tp.moduloObj", idModulo)) 
                    //Se agrega la restricción del servicio del pojoh CatalogoDocumental sea igual a true
                    .add(Restrictions.eq("cd.servicio", true));
                catalogoDoc = createCriteria.list(); // Se enlistan los criterios encontrados en la variable catalogoDoc
            
            for(CatalogoDocumental catalogoDocumental : catalogoDoc){ // Se recorre lalista catalogoDoc, e inicio de for
                // Se le asigna una variable de tipo List<TipoDocumento> la list obtenida de catalogoDocumental
                List<TipoDocumento> tp = catalogoDocumental.getTipoDocumentosList();
                tipoDoc.addAll(tp); //Se agrega la lista previamente guardada en la variable tp a la variable tipoDoc
            } //termino de for
        } catch (HibernateException he){ // Si captura algo entra la Excepción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime el log del msj de error + variable Hibernate
        } finally{ //Si no pasa por el try ni por el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return tipoDoc; //Se regresan los Tipos de Documentos que son un servicio de un modulo x
        
    }
    
    /**
     * Metodo encargado de devolver una lista de TipoDocumento de un modulo x que no sean un servicio
     * @param idModulo
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumentoNoServicio(Short idModulo) {
        List<TipoDocumento> tipoDoc = new ArrayList<>(); //Se instancia una lista de tipo TipoDocumento
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //Se obtiene la sesión Hibernate
        Transaction tx = session.beginTransaction(); // Se incia la petición
        try{
           List<CatalogoDocumental> catalogoDoc = new ArrayList<>(); //Se instancia una lista de tipo CatalogoDocumental
           //Se realizan los criterios empezando desde la relación mapeada en CatalogoDocumental y se le asigna un alias "cd"
            Criteria createCriteria = session.createCriteria(CatalogoDocumental.class, "cd")
                    // Se mapea la relación con TipoDocumental por la propiedad manejada en el pojoh Catalago Documental = "tipoDocumentosList"
                    .setFetchMode("cd.tipoDocumentosList", org.hibernate.FetchMode.JOIN)
                    .createAlias("cd.tipoDocumentosList", "tp") //Se crea un alias para la relación de CatalagoDocumental y TipoDocumento = "tp"
                    //Se agrega la restricción del moduloObj del pojoh TipoDocumento sea igual al parametro idModulo
                    .add(Restrictions.eq("tp.moduloObj", idModulo))
                    //Se agrega la restricción del servicio del pojoh CatalogoDocumental sea igual a false
                    .add(Restrictions.eq("cd.servicio", false));
                catalogoDoc = createCriteria.list(); // Se ejecuta la sentencia con los criterios ingresados, y se almacena el resultado en la variable catalogoDoc
            
            for(CatalogoDocumental catalogoDocumental : catalogoDoc){ // Se recorre la lista catalogoDoc, e inicio de for
                // Se le asigna una variable de tipo List<TipoDocumento> la list obtenida de catalogoDocumental
                    List<TipoDocumento> tp = catalogoDocumental.getTipoDocumentosList();
                    tipoDoc.addAll(tp); //Se agrega la lista previamente guardada en la variable tp a la variable tipoDoc
            } //termino de for
        } catch (HibernateException he){ // Si captura algo entra a la Excepción Hibernate
            LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos", he); // Se imprime el log del msj de error + variable Hibernate
        } finally { //De cualquier manera entra
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return tipoDoc; //Se regresan los Tipos de Documentos que no son un servicio de un modulo x
    }

    @Override
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
            HibernateUtl.buildSessionFactory(); 
            Session session = HibernateUtl.getSessionFactory().getCurrentSession(); 
            Transaction tx = session.beginTransaction(); 
            try {
                session.saveOrUpdate(tipoDocumento);
                tx.commit(); 
            } catch (HibernateException he) { 
                tx.rollback(); 
                LOGGER.error("CLASS: CatalogoDocumentosDaoImpl - Error en la capa de acceso a datos  --> "+he.getMessage(), he); 
            } finally { 
                HibernateUtl.closeSessionAndUnbindFromThread(); 
        }
    }
}
