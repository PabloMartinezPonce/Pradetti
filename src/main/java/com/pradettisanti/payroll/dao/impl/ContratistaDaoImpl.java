/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.TipoDomicilio;
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
 * Clase encargada implementar la intefaz para el manejo de la Contratista
 * dentro de la base de datos
 *
 * @author PabloSagoz  pablo.samperio@it-seekers.com
 */
@Component
public class ContratistaDaoImpl implements com.pradettisanti.payroll.dao.ContratistaDao {

    private static final Logger LOGGER = Logger.getLogger(ContratistaDaoImpl.class);

    /**
     * Metodo encargado de ingresar/actualizar a un contratista
     * @param contratista Instancia de Contratista
     * @return Integer
     */
    @Override
    public Integer setContratista(Contratista contratista) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(contratista);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ContratistaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratista.getIdContratista();
    }

    /**
     * Metodo encargado de ingresar o actualizar el domicilio de una empresa
     * @param empresasDomicilio Instancia de EmpresasDomicilio
     * @return Integer
     */
    @Override
    public Integer setDomicilio(EmpresasDomicilio empresasDomicilio) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(empresasDomicilio);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ContratistaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return empresasDomicilio.getIdEmpresasDomicilio();
    }

    /**
     * Metodo encargado de deolver todos los contratistas existentes
     * @return List Contratista
     */
    @Override
    public List<Contratista> getContratistas() {
        List<Contratista> contratistas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contratistas = session.getNamedQuery("Contratista.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratistaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratistas;
    }

    /**
     * Metodo encargado de devolver un contratista con respecto a id
     * @param idContratista Numero entero que contiene el id de un contratista
     * @return Contratista
     */
    @Override
    public Contratista getContratista(Integer idContratista) {
        Contratista contratista = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contratista = (Contratista) session.get(Contratista.class, idContratista);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratistaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratista;
    }

    /**
     * Metodo encargado de devolver una lista de contratista que empaten con los
     * criterios ingresados dentro del objeto
     * @param contratista Instancia de un Contratista
     * @return List Contratista
     */
    @Override
    public List<Contratista> getContratista(Contratista contratista) {
        List<Contratista> contratistas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Contratista.class);
            if (contratista.getNombreEmpresa() != null) {
                criteria.add(Restrictions.like("nombreEmpresa", "%" + contratista.getNombreEmpresa() + "%"));
            } else if (contratista.getRepresentateLegal() != null) {
                criteria.add(Restrictions.like("representateLegal", "%" + contratista.getRepresentateLegal() + "%"));
            } else {
                criteria.add(Restrictions.like("rfc", "%" + contratista.getRfc() + "%"));
            }
            contratistas = criteria.list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratistaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratistas;

    }

    /**
     * Metodo encargado de deolver los contratistas con los cuales un cliente tiene relacion
     * @param idCliente Numero entero que contiene el id de un cliente
     * @return List Contratista
     */
    @Override
    public List<Contratista> getContratistas(Integer idCliente) {
        List<Contratista> contratistas = new ArrayList<>();
        Properties prop = getPropertySql();
        String sqlProperty = prop.getProperty("query.findContratistasByCliente");
        String query = MessageFormat.format(sqlProperty, idCliente);

        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contratistas = session.createSQLQuery(query).addEntity(Contratista.class).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratistaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratistas;
    }

    /**
     * Metodo encargado de devolver un tipo de domicilio con respecto a un id
     * @param idTipoDomicilio Numero entero corto que contiene el id de un TipoDomicilio
     * @return TipoDomicilio
     */
    @Override
    public TipoDomicilio getTipoDomicilio(Short idTipoDomicilio) {
        TipoDomicilio tipoDomicilio = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoDomicilio = (TipoDomicilio) session.getNamedQuery("TipoDomicilio.findByIdTipoDomicilio").setShort("idTipoDomicilio", idTipoDomicilio).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratistaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDomicilio;
    }

    /**
     * Metodo encargado de devolver un lista con los tipos de domiclio
     * existentes
     * @return List TipoDomicilio
     */
    @Override
    public List<TipoDomicilio> getTipoDomicilio() {
        List<TipoDomicilio> tipoDomicilio = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoDomicilio = session.getNamedQuery("TipoDomicilio.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratistaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDomicilio;
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
