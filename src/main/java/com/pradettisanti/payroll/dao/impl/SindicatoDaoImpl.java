/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.Sindicato;
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
 * Clase encargada implementar la intefaz para el manejo de la Sindicato dentro
 * de la base de datos
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Component
public class SindicatoDaoImpl implements com.pradettisanti.payroll.dao.SindicatoDao {

    private static final Logger LOGGER = Logger.getLogger(SindicatoDaoImpl.class);

    /**
     * Metodo encargado de ingresar/actulizar un sindicato
     * @param sindicato Instancia de Sindicato
     * @return Integer
     */
    @Override
    public Integer setSindicato(Sindicato sindicato) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(sindicato);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: SindicatoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return sindicato.getIdSindicato();
    }

    /**
     * Metodo encargado de devolver un sindicato con respecto a un id
     * @param idSindicato Numero entero que contiene el id de un sindicato
     * @return Sindicato
     */
    @Override
    public Sindicato getSindicato(Integer idSindicato) {
        Sindicato sindicato = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            sindicato = (Sindicato) session.getNamedQuery("Sindicato.findByIdSindicato").setInteger("idSindicato", idSindicato).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SindicatoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return sindicato;
    }

    /**
     * Metodo encargado de devolver todos los sindicatos existentes
     * @return List Sindicato
     */
    @Override
    public List<Sindicato> getSindicatos() {
        List<Sindicato> sindicatos = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            sindicatos = session.getNamedQuery("Sindicato.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SindicatoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return sindicatos;
    }

    /**
     * Metodo encagado de devolver una lista de sindicatos que se relacionan con
     * la informacion ingresada dentro del objeto
     * @param sindicato Instancia de Sindicato
     * @return List Sindicato
     */
    @Override
    public List<Sindicato> getSindicatos(Sindicato sindicato) {
        List<Sindicato> sindicatos = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Sindicato.class);
            if (sindicato.getNombreSindicato() != null) {
                criteria.add(Restrictions.like("nombreSindicato", "%" + sindicato.getNombreSindicato() + "%"));
            } else if (sindicato.getNombreCorto() != null) {
                criteria.add(Restrictions.like("nombreCorto", "%" + sindicato.getNombreCorto() + "%"));
            } else {
                criteria.add(Restrictions.like("rfc", "%" + sindicato.getRfc() + "%"));
            }
            sindicatos = criteria.list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SindicatoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return sindicatos;
    }

    @Override
    public List<Sindicato> getSindicatosByNomCorto() {
        List<Sindicato> sindicatos = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            sindicatos = session.getNamedQuery("Sindicato.findAllByNombreCortoDesc").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SindicatoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return sindicatos;
    }

}
