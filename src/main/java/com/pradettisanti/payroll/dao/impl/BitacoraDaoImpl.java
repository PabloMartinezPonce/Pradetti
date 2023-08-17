/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.Bitacora;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * Clase encargada implementar la intefaz para el manejo de la bitacora dentro
 * de la base de datos
 * @author PabloSagoz  pablo.samperio@it-seekers.com
 */
@Component
public class BitacoraDaoImpl implements com.pradettisanti.payroll.dao.BitacoraDao {

    private static final Logger LOGGER = Logger.getLogger(BitacoraDaoImpl.class);

    /**
     * Metodo encargado de ingresar/actualizar un registro de bitacora
     * @param bitacora Intancia de Bitacora
     */
    @Override
    public void setBitacora(Bitacora bitacora) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(bitacora);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: BitacoraImpl - Error en la capa de acceso a datos  ", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo encargado de devolver toda la bitacora
     * @return List Bitacora 
     */
    @Override
    public List<Bitacora> getBitacora() {
        List<Bitacora> bitacoras = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            bitacoras = session.getNamedQuery("Bitacora.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: BitacoraImpl - Error en la capa de acceso a datos  ", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return bitacoras;
    }

    /**
     * Metodo encargado de devolver la bitacora de un cierto periodo
     * @param desde Instancia de Date
     * @param hasta Instancia de Date
     * @return List Bitacora 
     */
    @Override
    public List<Bitacora> getBitacora(Date desde, Date hasta) {
        List<Bitacora> bitacoras = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            bitacoras = session.getNamedQuery("Bitacora.findByFechas").setDate("fechaDesde", desde).setDate("fechaHasta", hasta).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: BitacoraImpl - Error en la capa de acceso a datos  ", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return bitacoras;
    }

    /**
     * Metodo encargaado de devolver la bitacora de un cierto periodo en base a
     * un usuario en especifico
     * @param usuario Instancia de Usuario
     * @param desde Instancia de Date
     * @param hasta Instancia de Date
     * @return List Bitacora 
     */
    @Override
    public List<Bitacora> getBitacora(Usuario usuario, Date desde, Date hasta) {
        List<Bitacora> bitacoras = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            bitacoras = session.getNamedQuery("Bitacora.findByUsuario").setDate("fechaDesde", desde).setDate("fechaHasta", hasta).setInteger("usuario", usuario.getIdUsuario()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: BitacoraImpl - Error en la capa de acceso a datos  ", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return bitacoras;
    }

}
