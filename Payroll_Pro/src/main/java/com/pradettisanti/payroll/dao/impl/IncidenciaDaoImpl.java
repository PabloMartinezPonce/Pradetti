/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.TipoIncidencia;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * Clase encargada implementar la intefaz para el manejo de la incidencia dentro
 * de la base de datos
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Component
public class IncidenciaDaoImpl implements com.pradettisanti.payroll.dao.IncidenciaDao {

    private static final Logger LOGGER = Logger.getLogger(IncidenciaDaoImpl.class);

    /**
     * Metodo encargado de ingresar/actulizar una incidencia
     * @param inicidencia Instancia de Incidencia
     * @return Integer
     */
    @Override
    public Integer setIncidencia(Incidencia inicidencia) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(inicidencia);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return inicidencia.getIdIncidencia();
    }

    /**
     * Metodo encargado de buscar una lista incidencias con respecto a un
     * agremiado y un periodo dado
     * @param agremiado Instancia de Agremiado
     * @param desdeElRegistro Instancia de Date
     * @param hastaElRegistro Instancia de Date
     * @return List Incidencia
     */
    @Override
    public List<Incidencia> getIncidencias(Agremiado agremiado, Date desdeElRegistro, Date hastaElRegistro) {
        List<Incidencia> incidencias = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            incidencias = session.getNamedQuery("Incidencia.findByAgremiado").setEntity("agremiado", agremiado).setDate("fechaRegistroDesde", desdeElRegistro).setDate("fechaRegistroHasta", hastaElRegistro).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return incidencias;
    }

    /**
     * Metodo encargado de buscar una lista incidencias con respecto a un
     * cliente y un periodo dado para la fecha de registro
     * @param cliente Instancia de cliente
     * @param desdeElRegistro Instancia de Date
     * @param hastaElRegistro Instancia de Date
     * @return List Incidencia
     */
    @Override
    public List<Incidencia> getIncidenciasFechaRegistro(Cliente cliente, Date desdeElRegistro, Date hastaElRegistro) {
        List<Incidencia> incidencias = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            incidencias = session.getNamedQuery("Incidencia.findByClienteFechaRegistro").setEntity("cliente", cliente).setDate("fechaRegistroDesde", desdeElRegistro).setDate("fechaRegistroHasta", hastaElRegistro).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return incidencias;
    }

    /**
     * Metodo encargado de buscar una lista incidencias con respecto a un
     * cliente y un periodo dado para la fecha de la incidencia
     * @param cliente Instancia de Cliente
     * @param desdeElRegistro Instacia de Date
     * @param hastaElRegistro Instancia de Date
     * @return List Incidencia
     */
    @Override
    public List<Incidencia> getIncidenciasFechaIncidencia(Cliente cliente, Date desdeElRegistro, Date hastaElRegistro) {
        List<Incidencia> incidencias = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            incidencias = session.getNamedQuery("Incidencia.findByClienteFechaIncidencia").setEntity("cliente", cliente).setDate("fechaIncidenciaDesde", desdeElRegistro).setDate("fechaIncidenciaHasta", hastaElRegistro).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return incidencias;
    }

    /**
     * Metodo encargado de devolver un incidencia
     * @param idCliente Numero entero que contiene el id de un Cliente
     * @param idAgremiado Numero entero que contiene el id de un Agremiado
     * @param IdIncidencia Numero entero que contiene el id de un Incidencia
     * @param tipoIncidencia Numero entero corto que contiene el id de un TipoIncidencia
     * @return Incidencia
     */
    @Override
    public Incidencia getInicidencia(Integer idCliente, Integer idAgremiado, Integer IdIncidencia, Short tipoIncidencia) {
        Incidencia incidencia = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            incidencia = (Incidencia) session.getNamedQuery("Incidencia.findByCAIT").setInteger("cliente", idCliente).setInteger("agremiado", idAgremiado).setInteger("incidencia", IdIncidencia).setShort("tipo", tipoIncidencia).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return incidencia;
    }

    /**
     * Metodo encargado de buscar u incidencia con respecto a un id
     * @param idTipoIncidencia Numero entero corto que contiene el id de un TipoIncidencia
     * @return TipoIncidencia
     */
    @Override
    public TipoIncidencia getTipoIncidencia(Short idTipoIncidencia) {
        TipoIncidencia tipoIncidencia = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoIncidencia = (TipoIncidencia) session.getNamedQuery("TipoIncidencia.findByIdTipoIncidencia").setShort("idTipoIncidencia", idTipoIncidencia).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoIncidencia;
    }

    /**
     * Metodo encardo de devolver todas las incidencias
     * @return List TipoIncidencia
     */
    @Override
    public List<TipoIncidencia> getTipoIncidencias() {
        List<TipoIncidencia> tipoIncidencias = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoIncidencias = session.getNamedQuery("TipoIncidencia.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoIncidencias;
    }

    /**
     * Metodo encargado de buscar una lista de incidencias con respecto al
     * cliente y al estado de la incidencia
     * @param cliente Instancia de Cliente
     * @param estado booleano en el cual se indica si se desean las incidencias ya revisadas o no
     * @return List Incidencia
     */
    @Override
    public List<Incidencia> getIncidencias(Cliente cliente, boolean estado) {
        List<Incidencia> incidencias = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            incidencias = session.getNamedQuery("Incidencia.findByClienteAndStatus").setInteger("cliente", cliente.getIdCliente()).setBoolean("estado", estado).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return incidencias;
    }

    /**
     * Metodo encargado de devolver un Lista de incidencias con respecto a un cliente, periodo de fecha de registro  y estado de las incidencias
     * @param cliente Instancia de Cliente
     * @param desdeElRegistro Instancia de Date 
     * @param hastaElRegistro Instancia de Date
     * @param estado booleano en el cual se indica si se desean las incidencias ya revisadas o no
     * @return List Incidencia
     */
    @Override
    public List<Incidencia> getIncidenciasFechaIncidencia(Cliente cliente, Date desdeElRegistro, Date hastaElRegistro, boolean estado) {
        List<Incidencia> incidencias = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            incidencias = session.getNamedQuery("Incidencia.findByClienteEstadoFechaIncidencia").setEntity("cliente", cliente).setBoolean("estado", estado).setDate("fechaIncidenciaDesde", desdeElRegistro).setDate("fechaIncidenciaHasta", hastaElRegistro).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: IncidenciaDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return incidencias;
    }
}
