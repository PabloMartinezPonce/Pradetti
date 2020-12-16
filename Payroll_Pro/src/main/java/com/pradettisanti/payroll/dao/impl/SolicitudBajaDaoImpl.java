/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.dao.SolicitudBajaDao;
import com.pradettisanti.payroll.pojoh.DocumentosBaja;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.MotivoBaja;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoDocumentoPK;
import com.pradettisanti.payroll.pojoh.TipoFiniquito;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Clase que implementa los metodos de la interfaz SolicitudBajaDao
 *
 * @author HEM 
 */
@Component
public class SolicitudBajaDaoImpl implements SolicitudBajaDao {

    private static final Logger LOGGER = Logger.getLogger(AgremiadoDaoImpl.class);

    /**
     * Metodo que guarda/actualiza documentobaja
     * @param documentosBaja Instancia de DocumentosBaja
     */
    @Override
    public void setDocumentoBaja(DocumentosBaja documentosBaja) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(documentosBaja);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que Obtiene una lista de documentosBaja
     * @param solicitudBaja Instancia de SolicitudBaja
     * @param tipoDocumentoPK Instancia de TipoDocumentoPK
     * @return List DocumentosBaja
     */
    @Override
    public List<DocumentosBaja> getDocumentosBaja(SolicitudBaja solicitudBaja, TipoDocumentoPK tipoDocumentoPK) {

        List<DocumentosBaja> documentosBajas = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documentosBajas = session.getNamedQuery("DocumentosBaja.findBySolbjTpdoc").setInteger("solBaja", solicitudBaja.getIdSolicitudBaja()).setInteger("tpDoc", tipoDocumentoPK.getIdTipoDocumento()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentosBajas;
    }

    /**
     * Metodo que obtiene una lista de DocumentosBaja
     * @param solicitudBaja Instancia de SolicitudBaja
     * @return List DocumentosBaja 
     */
    @Override
    public List<DocumentosBaja> getDocumentosBaja(SolicitudBaja solicitudBaja) {
        List<DocumentosBaja> documentosBajas = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documentosBajas = session.getNamedQuery("DocumentosBaja.findBySolicitudBaja").setInteger("solicitudBaja", solicitudBaja.getIdSolicitudBaja()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentosBajas;
    }

    /**
     * Metodo encargado de devolver los documentos de una solicitud de baja
     * @param idSolicitudBaja Numero entero que contiene el id de una solicitud de baja
     * @param idModulo Numero entero corto que contiene el id de un Modulo
     * @return List DocumentosBaja
     */
    @Override
    public List<DocumentosBaja> getDocumentosBaja(Integer idSolicitudBaja, Short idModulo) {
        List<DocumentosBaja> documentosBaja = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria createCriteria = session.createCriteria(DocumentosBaja.class, "db") 
                    .setFetchMode("db.tipoDocumentoObj", org.hibernate.FetchMode.JOIN)
                    .createAlias("db.tipoDocumentoObj", "tp")
                    .setFetchMode("db.solicitudBajaObj", org.hibernate.FetchMode.JOIN)
                    .createAlias("db.solicitudBajaObj", "sd")
                    .add(Restrictions.eq("sd.idSolicitudBaja", idSolicitudBaja))
                    .add(Restrictions.eq("tp.moduloObj", idModulo));
                documentosBaja = createCriteria.list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentosBaja;
    }

    /**
     * Metodo que obtiene un modulo
     * @param idModulo Numero entero corto que contiene el id de un Modulo
     * @return Modulo
     */
    @Override
    public Modulo getModulo(Short idModulo) {
        Modulo modulo = new Modulo();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            modulo = (Modulo) session.get(Modulo.class, idModulo);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return modulo;
    }

    /**
     * Metodo que obtiene una lista de modulos
     * @return List Modulo
     */
    @Override
    public List<Modulo> getModulos() {
        List<Modulo> modulos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            modulos = session.getNamedQuery("Modulo.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return modulos;
    }

    /**
     * Metodo que obtiene un motivo de baja
     * @param idMotivoBaja Numero entero que contiene el id de un MotivoBaja
     * @return MotivoBaja
     */
    @Override
    public MotivoBaja getMotivoBaja(Short idMotivoBaja) {
        MotivoBaja motivoBaja = new MotivoBaja();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            motivoBaja = (MotivoBaja) session.get(MotivoBaja.class, idMotivoBaja);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return motivoBaja;

    }

    /**
     * Metodo que obtiene una lista de MotivoBaja
     * @return List MotivoBaja 
     */
    @Override
    public List<MotivoBaja> getMotivoBaja() {
        List<MotivoBaja> motivoBajas = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            motivoBajas = session.getNamedQuery("MotivoBaja.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return motivoBajas;
    }

    /**
     * Metodo que obtiene un TipoDocumento
     * @param tipoDocumentoPK Instancia de TipoDocumentoPK
     * @return TipoDocumento
     */
    @Override
    public TipoDocumento getTipoDocumento(TipoDocumentoPK tipoDocumentoPK) {
        TipoDocumento tipoDocumento = new TipoDocumento();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoDocumento = (TipoDocumento) session.get(TipoDocumento.class, tipoDocumentoPK);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDocumento;
    }

    /**
     * Metodo que obtiene una lista de TipoDocumento
     * @param modulo Instancia de Modulo
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumento(Modulo modulo) {
        List<TipoDocumento> documentos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documentos = session.getNamedQuery("TipoDocumento.findByModulo").setEntity("modulo", modulo).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentos;
    }

    /**
     * Metodo que obtiene una lista de TipoDocumento
     * @param modulo Lista de Instancias de Modulo
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumento(List<Modulo> modulo) {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(TipoDocumento.class);
            criteria.add(Restrictions.in("modulo", modulo));

            tipoDocumentos.addAll(criteria.list());
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDocumentos;
    }

    /**
     * Metodo encargado de devolver los tipos de documentos de Baja
     * @param idSolBaja Numero entero que contiene el id del la solicitud de baja
     * @param idModulo Numero entero corto que contiene el id de un modulo
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumentoSolicitudBaja(Integer idSolBaja, Short idModulo) {

        Properties prop = getPropertySql();
        String sqlProperty = prop.getProperty("query.findTipoDocBySolBaja");
        String query = MessageFormat.format(sqlProperty, idSolBaja, idModulo);
        List<TipoDocumento> documentos = new ArrayList<>();

        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documentos = session.createSQLQuery(query).addEntity(TipoDocumento.class).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentos;
    }

    /**
     * Metodo que obtiene TipoFiniquito
     * @param idTipoFiniquito Numero entero corto que contiene el id de un TipoFiniquito
     * @return TipoFiniquito
     */
    @Override
    public TipoFiniquito getTipoFiniquito(Short idTipoFiniquito) {
        TipoFiniquito tipoFiniquito = new TipoFiniquito();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoFiniquito = (TipoFiniquito) session.get(TipoFiniquito.class, idTipoFiniquito);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoFiniquito;
    }

    /**
     * Metodo que obtiene una lista de TipoFiniquito
     * @return List TipoFiniquito 
     */
    @Override
    public List<TipoFiniquito> getTipoFiniquito() {
        List<TipoFiniquito> tipoFiniquitos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoFiniquitos = session.getNamedQuery("TipoFiniquito.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoFiniquitos;
    }

    /**
     * Metodo que obtiene un documento de baja
     * @param idSolicitudBaja Numero entero que contiene el id de un SolicitudBaja
     * @param idTipoDocumento Numero entero que contiene el id de un TipoDocumento
     * @return DocumentosBaja
     */
    @Override
    public DocumentosBaja getDocumentoBaja(Integer idSolicitudBaja, Integer idTipoDocumento) {
        DocumentosBaja documentosBaja = new DocumentosBaja();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documentosBaja = (DocumentosBaja) session.getNamedQuery("DocumentosBaja.findByTipoDocumento")
                    .setParameter("idTipoDocumento", idTipoDocumento)
                    .setParameter("idSolicitudBaja", idSolicitudBaja).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentosBaja;
    }

    /**
     *Metodo encargado de borrar una solicitud de baja
     * @param solicitudBaja Instancia de SolicitudBaja
     */
    @Override
    public void deleteSolicitudBaja(SolicitudBaja solicitudBaja) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(solicitudBaja);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     *Metodo encargado de borrar un documento de baja
     * @param documentobaja Instancia de DocumentoBaja
     */
    @Override
    public void deleteDocumentoBaja(DocumentosBaja documentobaja) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(documentobaja);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SolicitudBajaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que obtiene el archivo sqlQueryProperties
     */
    private Properties getPropertySql() {

        Properties prop = new Properties();
        InputStream inputStream;
        String propFileName = "sqlQuery.properties";

        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            prop.load(inputStream);
        } catch (Exception e) {
            LOGGER.error("ERROR AL OBTENER EL ARCHIVO SQL PROPERTIES ");
        }

        return prop;
    }

}
