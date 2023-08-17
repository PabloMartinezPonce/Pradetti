/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.SalarioUnicoProfesional;
import com.pradettisanti.payroll.pojoh.TipoSalarioUnicoProfesional;
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
 * Clase encargada implementar la intefaz para el manejo de SalarioUnicoProfesional dentro de la base de datos
 * @author Gabriela Jaime gabriela.jaime@it-seekers.com
 * @version  1
 * @since  2017/12/07
 */
@Component
public class SUPDaoImpl implements com.pradettisanti.payroll.dao.SUPDao{

    private static final Logger LOGGER = Logger.getLogger(SUPDaoImpl.class);
    
    /**
     * Metodo encargado de ingresar/ actulizar un salario unico profesional
     * @param salarioUnicoProfesional 
     */
    @Override
    public void setSalarioUnicoProfesional(SalarioUnicoProfesional salarioUnicoProfesional) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(salarioUnicoProfesional);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }
    
    /**
     * Metodo encargadi de ingresar/actualizar un tipo de salario unico profesional
     * @param tipoSalarioUnicoProfesional 
     */
    @Override
    public void setTipoSalarioUnicoProfesional(TipoSalarioUnicoProfesional tipoSalarioUnicoProfesional) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(tipoSalarioUnicoProfesional);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }
    
    /**
     * Metodo encargado de devolver un tipo de salario unico profesional en base al id ingresado
     * @param id
     * @return TipoSalarioUnicoProfesional
     */
    @Override
    public TipoSalarioUnicoProfesional getTipoSalariUnicoProfesional(Integer id) {
        TipoSalarioUnicoProfesional tipoSUP = new TipoSalarioUnicoProfesional();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoSUP = (TipoSalarioUnicoProfesional) session.getNamedQuery("TipoSalarioUnicoProfesional.findById").setInteger("idTipoSalarioUnicoProfesional", id).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoSUP;
    }

    /**
     * Metodo encargado de devolver todos los tipos de salarios profesionales
     * @return List TipoSalarioUnicoProfesional
     */
    @Override
    public List<TipoSalarioUnicoProfesional> getTiposSalariosUnicosProfesionales() {
        List<TipoSalarioUnicoProfesional> tiposSUP = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tiposSUP = session.getNamedQuery("TipoSalarioUnicoProfesional.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tiposSUP;
    }

    /**
     * Metodo encargado de devolver una lista de salarios unicos profesionales que corresponden a un tipo de salario unico profesional
     * @param tsup
     * @return List SalarioUnicoProfesional
     */
    @Override
    public List<SalarioUnicoProfesional> getSalariosUnicosProfesionales(TipoSalarioUnicoProfesional tsup) {
        List<SalarioUnicoProfesional> sups = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try 
        {
            Criteria createCriteria = session.createCriteria(SalarioUnicoProfesional.class);
            createCriteria.add(Restrictions.eq("tipoSUP", tsup.getIdTipoSalarioUnicoProfesional()));            
            sups = createCriteria.list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return sups;
    }
    /**
     * Metodo encargado de devolver el salario unico profesional con respecto a su id
     * @param integer
     * @return SalarioUnicoProfesional
     */
    @Override
    public SalarioUnicoProfesional getSalarioUnicoProfesional(Integer integer) {
        SalarioUnicoProfesional sup = new SalarioUnicoProfesional();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            sup = (SalarioUnicoProfesional) session.getNamedQuery("SalarioUnicoProfesional.findById").setInteger("idSalarioUnicoProfesional", integer).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return sup;
    }

    /**
     * Metodo encargado de devolver todos los salarios unicos profesionales
     * @return List SalarioUnicoProfesional
     */
    @Override
    public List<SalarioUnicoProfesional> getSalariosUnicosProfesionales() {
        List<SalarioUnicoProfesional> sups = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            sups = session.getNamedQuery("SalarioUnicoProfesional.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: SUPDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return sups;
    }
    
}
