/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.dao.PeriodoFondoAhorroDao;
import com.pradettisanti.payroll.pojoh.BitacoraFondoAhorro;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import com.pradettisanti.payroll.pojoh.PeriodoFA;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 *
 * @author IT Seekers
 */

@Component
public class PeriodoFondoAhorroDaoImpl implements PeriodoFondoAhorroDao{
    
     private static final Logger LOGGER = Logger.getLogger(PeriodoFondoAhorroDaoImpl.class);

     /**
     * Metodo encargadi de ingresar/actualizar un tipo de periodo fondo ahorro
     * @param periodoFondoAhorro 
     */
    @Override
    public Integer setPeriodoFondoAhorro(PeriodoFA periodoFondoAhorro) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(periodoFondoAhorro);
            tx.commit();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return periodoFondoAhorro.getIdPeriodoFA();
    }

     /**
     * Metodo encargado de devolver un tipo de salario unico profesional en base al id ingresado
     * @param id
     * @return TipoSalarioUnicoProfesional
     */
    @Override
    public PeriodoFA getPeriodoFondoAhorro(Integer id) {
        PeriodoFA periodoFA = new PeriodoFA();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            periodoFA = (PeriodoFA) session.getNamedQuery("PeriodoFA.findByIdPeriodoFA").setInteger("idPeriodoFA", id).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return periodoFA;
    }
    
    /**
     * Metodo encargado de devolver todos los periodos de fondo de ahorro 
     * @return List PeriodoFA
     */
    @Override
    public List<PeriodoFA> getPeriodosFondoAhorro() {
       List<PeriodoFA> periodoFA = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            periodoFA = session.getNamedQuery("PeriodoFA.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return periodoFA;
    }

    /**
     * Metodo encargado de devolver una lista de periodos del fondo de ahorro en base a su bitacora de fondo de ahorro
     * @param fechaInicioFA
     * @param fechaFinFA
     * @param idAgremiado
     * @return 
     */
    @Override
    public List<PeriodoFA> getPeriodosByDates(Date fechaInicioFA, Date fechaFinFA, Integer idAgremiado) {
        List<PeriodoFA> periodoFA = null; 
        
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            periodoFA =  session.getNamedQuery("PeriodoFA.findByAgrFechainicialFechafinal").setDate("diaRegistroDesde", fechaInicioFA).setDate("diaRegistroHasta", fechaFinFA).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return periodoFA; 
    }

    @Override
    public Date getPrimerFechaFA(Integer idAgremiado) {
        Date fechaPrimerDiaFA = null; 
        
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fechaPrimerDiaFA =  (Date) session.getNamedQuery("BitacoraFondoAhorro.findFirstDateByAgremiado").setInteger("agremiado", idAgremiado).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return fechaPrimerDiaFA;
    }

    @Override
    public Date getUltimaFechaFA(Integer idAgremiado) {
        Date fechaultimoDiaFA = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fechaultimoDiaFA =  (Date) session.getNamedQuery("SolicitudBaja.findLastDateByAgremiado").setInteger("agremiado", idAgremiado).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return fechaultimoDiaFA;
    }

    @Override
    public Date getFechaInicioPeriodoFA(Integer idPeriodoFA) {
        Date fechaInicioPeriodoFA = null; 
        
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fechaInicioPeriodoFA =  (Date) session.getNamedQuery("PeriodoFA.findDateStartByIdPeriodo").setInteger("idPeriodoFA", idPeriodoFA).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return fechaInicioPeriodoFA;
    }

    @Override
    public Date getFechaFinPeriodoFA(Integer idPeriodoFA) {
        Date fechaFinPeriodoFA = null; 
        
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fechaFinPeriodoFA =  (Date) session.getNamedQuery("PeriodoFA.findDateEndByIdPeriodo").setInteger("idPeriodoFA", idPeriodoFA).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return fechaFinPeriodoFA;
    }

    @Override
    public Short getPorcentajePeriodo(Integer idAgremiado, Date fechaIni, Date fechaFin) {
        Short porcentajeDelPeriodo = null; 
        
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            porcentajeDelPeriodo =  (Short) session.getNamedQuery("BitacoraFondoAhorro.findPorcentByAgremiado").setInteger("agremiado", idAgremiado).setDate("diaRegistroDesde", fechaIni).setDate("diaRegistroHasta", fechaFin).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return porcentajeDelPeriodo;
    }

    @Override
    public Date getFechaIniPeriodo(Integer idAgremiado, Date fechaIni, Date fechaFin) {
        Date fechaIniPeriodo = null; 
        
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fechaIniPeriodo =  (Date) session.getNamedQuery("BitacoraFondoAhorro.findDateIniByPeriodo").setInteger("agremiado", idAgremiado).setDate("diaRegistroDesde", fechaIni).setDate("diaRegistroHasta", fechaFin).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return fechaIniPeriodo;
    }

    @Override
    public Date getFechaFinPeriodo(Integer idPeriodo) {
        Date fechaIniPeriodo = null; 
        
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            fechaIniPeriodo =  (Date) session.getNamedQuery("PeriodoFA.findDateEndByIdPeriodo").setInteger("idPeriodoFA", idPeriodo).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: PeriodoFondoAhorroDao - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return fechaIniPeriodo;
    }
    
}
