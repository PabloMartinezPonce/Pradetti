/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Component;

/**
 * Clase encargada implementar la intefaz para el manejo de la
 * SolicitudRenovacionContrato dentro de la base de datos
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Component
public class SolicitudRenovacionContratoDaoImpl implements com.pradettisanti.payroll.dao.SolicitudRenovacionContratoDao {

    private static final Logger LOGGER = Logger.getLogger(SolicitudRenovacionContratoDaoImpl.class);

    /**
     * Metodo encargado de devolver una solictud renovacion en base a los
     * parametros ingresados dentro del objeto
     * @param solicitudRenovacionContrato Instancia de SolicitudRenovacionContrato
     * @return SolicitudRenovacionContrato
     */
    @Override
    public SolicitudRenovacionContrato getSolicitudRenovacionContrato(SolicitudRenovacionContrato solicitudRenovacionContrato) {
        SolicitudRenovacionContrato src = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Example e = Example.create(solicitudRenovacionContrato).excludeZeroes().ignoreCase().enableLike();
            src = (SolicitudRenovacionContrato) session.createCriteria(SolicitudRenovacionContrato.class).add(e).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS:SolicitudRenovacionContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return src;
    }

    /**
     * Metodo encargado de devolver un tipo de contrato en relacion con un id
     * @param idTipoContrato Numero entero corto que contiene el id del tipo de contrato
     * @return TipoContrato
     */
    @Override
    public TipoContrato getTipoContrato(Short idTipoContrato) {
        TipoContrato tipoContrato = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoContrato = (TipoContrato) session.getNamedQuery("TipoContrato.findByIdTipoContrato").setInteger("idTipoContrato", idTipoContrato).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS:SolicitudRenovacionContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoContrato;
    }

    /**
     * Metodo encrargado de devolver todos los tipos de contrato existentes
     * @return List TipoContrato
     */
    @Override
    public List<TipoContrato> getTipoContrato() {
        List<TipoContrato> tipoContratos = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoContratos = session.getNamedQuery("TipoContrato.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS:SolicitudRenovacionContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoContratos;
    }

    /**
     * Metodo encargado de actualizar una SolicitudRenovacionContrato
     * @param solicitudRenovacionContrato Instacia de SolicitudRenovacionContrato
     */
    @Override
    public void setSolicitudRenovacionContrato(SolicitudRenovacionContrato solicitudRenovacionContrato) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(solicitudRenovacionContrato);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS:SolicitudRenovacionContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

}
