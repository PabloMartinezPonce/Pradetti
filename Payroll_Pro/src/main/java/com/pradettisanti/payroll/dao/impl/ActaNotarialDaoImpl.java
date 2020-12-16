/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.dao.ActaNotarialDao;
import com.pradettisanti.payroll.pojoh.ActaNotarial;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.PoderLegal;
import com.pradettisanti.payroll.pojoh.TipoActa;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Clase encarga de implementar los metodos de la intefaz ActaNotarial
 *
 * @author HEM 
 */
@Component
public class ActaNotarialDaoImpl implements ActaNotarialDao {

    private static final Logger LOGGER = Logger.getLogger(AgremiadoDaoImpl.class);

    /**
     * set ActaNotarial
     * @param actaNotarial Instancia de ActaNotarial
     * @return Integer
     */
    @Override
    public Integer setActaNotarial(ActaNotarial actaNotarial) {
        int result = 0;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(actaNotarial);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return actaNotarial.getIdActaNotarial();
    }

    /**
     * set ActaNotarial
     * @param poderLegal Instancia de poder legal
     */
    @Override
    public void setActaNotarial(PoderLegal poderLegal) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(poderLegal);
            tx.commit();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * get ActaNotarial
     * @param idActaNotarial Numero entero que contiene el id de una acta notarial
     * @return ActaNotarial
     */
    @Override
    public ActaNotarial getActaNotarial(Integer idActaNotarial) {
        ActaNotarial acta = new ActaNotarial();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            acta = (ActaNotarial) session.get(ActaNotarial.class, idActaNotarial);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return acta;

    }

    /**
     * getActanotarial
     * @param actaNotarial Instancia de ActaNotarial
     * @return List
     */
    @Override
    public List<ActaNotarial> getActanotarial(ActaNotarial actaNotarial) {
        List<ActaNotarial> actas = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(ActaNotarial.class);
            Disjunction objDisjunction = Restrictions.disjunction();
            objDisjunction.add(Restrictions.eq("tipoActa", actaNotarial.getTipoActa()));
            objDisjunction.add(Restrictions.eq("tienePoderLegal", actaNotarial.getTienePoderLegal()));
            criteria.add(objDisjunction);

            actas.addAll(criteria.list());
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return actas;
    }

    /**
     * get Actanotarial
     * @param cliente Instancia de Cliente
     * @return ActaNotarial
     */
    @Override
    public ActaNotarial getActanotarial(Cliente cliente) {
        ActaNotarial actaNotarial = new ActaNotarial();
        String sql = "SELECT acta_notarial.* FROM acta_notarial "
                + "join relacion_cliente_acta on relacion_cliente_acta.acta_notarial = acta_notarial.id_acta_notarial "
                + "join cliente on cliente.id_cliente = relacion_cliente_acta.cliente "
                + "WHERE cliente.id_cliente = " + cliente.getIdCliente();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            actaNotarial = (ActaNotarial) session.createSQLQuery(sql).addEntity(ActaNotarial.class).list().get(0);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return actaNotarial;
    }

    /**
     * getActanotarial
     * @param contratista Instancia de contratista
     * @return ActaNotarial
     */
    @Override
    public ActaNotarial getActanotarial(Contratista contratista) {
        ActaNotarial actaNotarial = new ActaNotarial();
        String sql = "FROM acta_notarial "
                + "join relacion_contratista_acta on relacion_contratista_acta.acta_notarial = acta_notarial.id_acta_notarial "
                + "join contratista on contratista.id_contratista = relacion_contratista_acta.contratista "
                + "WHERE contratista.id_contratista = " + contratista.getIdContratista();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            actaNotarial = (ActaNotarial) session.createSQLQuery(sql).addEntity(ActaNotarial.class).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return actaNotarial;

    }

    /**
     * get ActasNotariales
     * @return List Instancia de ActaNotarial
     */
    @Override
    public List<ActaNotarial> getActasNotariales() {
        String consulta = "From acta_notarial";
        List<ActaNotarial> actas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            actas = session.createQuery(consulta).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return actas;

    }

    /**
     * get ActasNotariales
     * @param tipoActa Instancia de TipoActa
     * @return List Instancia de ActaNotarial
     */
    @Override
    public List<ActaNotarial> getActasNotariales(TipoActa tipoActa) {
        List<ActaNotarial> actaNotariales = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(ActaNotarial.class);
            criteria.add(Restrictions.eq("tipo_acta", tipoActa.getIdTipoActa()));
            actaNotariales = criteria.list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return actaNotariales;
    }

    /**
     * get TipoActa
     * @param idTipoActa Numero entero corto que contiene el id de un tipo de acta
     * @return TipoActa
     */
    @Override
    public TipoActa getTipoActa(Short idTipoActa) {
        TipoActa tipoActa = new TipoActa();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoActa = (TipoActa) session.get(TipoActa.class, idTipoActa);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoActa;
    }

    /**
     * get TipoActa
     * @return List Instancia de TipoActa
     */
    @Override
    public List<TipoActa> getTipoActa() {
        String consulta = "select *  From tipo_acta";
        List<TipoActa> tipoactas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoactas = session.createSQLQuery(consulta).addEntity(TipoActa.class).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ActaNotarialDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoactas;
    }

}
