/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.ZonaSm;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

/**
 * Clase encargada implementar la intefaz para el manejo de la ZonaSm dentro de
 * la base de datos
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Component
public class ZonaSmDaoImpl implements com.pradettisanti.payroll.dao.ZonaSmDao {

    private static final Logger LOGGER = Logger.getLogger(ZonaSmDaoImpl.class);

    /**
     * Metodo encargado de ingresar/actulizar una zona
     * @param zonaSm Instancia de ZonaSm
     */
    @Override
    public Short setZonaSM(ZonaSm zonaSm) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(zonaSm);
            tx.commit();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ZonaSmDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return zonaSm.getIdZonaSm();
    }

    /**
     * Metodo encargado de devolver una zona salarial con respecto a su id
     * @param i Numero entero corto que contiene el id de una ZonaSm
     * @return ZonaSm
     */
    @Override
    public ZonaSm getZonaSm(Short i) {
        ZonaSm zonaSm = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            zonaSm = (ZonaSm) session.get(ZonaSm.class, i);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ZonaSmDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return zonaSm;
    }

    /**
     * Metodo encargado de deolver una lista con las zonas salariales existentes
     * @return List ZonaSm
     */
    @Override
    public List<ZonaSm> getZonaSm() {
        List<ZonaSm> zonaSm = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            zonaSm = session.getNamedQuery("ZonaSm.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ZonaSmDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return zonaSm;
    }

    /**
     * Metodo encargado de devolver una lista con las zonas salariales que
     * correspondan con los datos ingresados en el objeto
     * @param zonaSm Instancia de una ZonaSm
     * @return List ZonaSm
     */
    @Override
    public List<ZonaSm> getZonaSm(ZonaSm zonaSm) {
        List<ZonaSm> zses = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Example e = Example.create(zonaSm).excludeZeroes().ignoreCase().enableLike();
            zses = session.createCriteria(ZonaSm.class).add(e).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ZonaSmDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return zses;
    }

}
