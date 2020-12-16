/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Clase encargada implementar la intefaz para el manejo de la contrato dentro
 * de la base de datos
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Component
public class ContratoDaoImpl implements com.pradettisanti.payroll.dao.ContratoDao {

    private static final Logger LOGGER = Logger.getLogger(ContratoDaoImpl.class);

    /**
     * Metodo encargado de ingresar/actulizar un contrato
     * @param contrato Instancia de Contrato
     * @return Integer
     */
    @Override
    public Integer setContrato(Contrato contrato) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(contrato);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contrato.getIdContrato();
    }

    /**
     * Metodo encrgado de devolver el contrato asociado al id recibido
     * @param idContrato Numero entero que contiene el id de un contrato
     * @return Contrato
     */
    @Override
    public Contrato getContrato(Integer idContrato) {
        Contrato contrato = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contrato = (Contrato) session.get(Contrato.class, idContrato);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contrato;
    }

    /**
     * Metodo encargado de devolver todo los contratos disponibles para su
     * generación via sistema
     * @return List Contrato
     */
    @Override
    public List<Contrato> getContratos() {
        List<Contrato> contratos = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contratos = session.getNamedQuery("Contrato.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratos;
    }

    /**
     * Metodo encargado de devolver los contrato de una lista de cliente
     * @param clienteList Lista de instancia de Cliente
     * @return List Contrato
     */
    @Override
    public List<Contrato> getContratosDelCliente(List<Cliente> clienteList) {
        List<Contrato> contratos = new LinkedList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Contrato.class);
            criteria.add(Restrictions.in("clienteList", clienteList));
            contratos = criteria.list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratos;
    }

    /**
     * Metodo encargado de devolver los contrato de una lista de contratista
     * @param contratistaList Lista de instancia de Contratista
     * @return List Contrato
     */
    @Override
    public List<Contrato> getContratosDelContratista(List<Contratista> contratistaList) {
        List<Contrato> contratos = new LinkedList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Contrato.class);
            criteria.add(Restrictions.in("contratistaList", contratistaList));
            contratos = criteria.list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratos;
    }
    /**
    * Metodo que regresa una los contratos filtrados por el contratista que tiene ligado el colaborador
    * @param idContratista
    * @return List Contrato
    */
    @Override
    public List<Contrato> getContratosDelColaborador(Integer idContratista) {
        List<Contrato> contrato = new ArrayList<>(); 
        HibernateUtl.buildSessionFactory(); 
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); 
        Transaction tx = session.beginTransaction(); 
        try{
           Criteria createCriteria = session.createCriteria(Contrato.class, "cn")
                    .setFetchMode("cn.contratistaList", org.hibernate.FetchMode.JOIN)
                   .createAlias("cn.contratistaList", "cnt")
                    .add(Restrictions.eq("cnt.idContratista", idContratista))
                    .add(Restrictions.eq("contratoDeColaborador", true))
                     .setResultTransformer (Criteria.DISTINCT_ROOT_ENTITY);
           contrato = createCriteria.list(); 
        } catch (HibernateException he){ 
            LOGGER.error("CLASS: ContratoDaoImpl - Error en la capa de acceso a datos", he); 
        } finally { 
            HibernateUtl.closeSessionAndUnbindFromThread(); 
        }
        return contrato; 
    }

}
