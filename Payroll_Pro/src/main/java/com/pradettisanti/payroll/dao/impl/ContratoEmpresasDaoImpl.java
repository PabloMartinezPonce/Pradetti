/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.DelContrato;
import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.Utility.StatusQuery;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Component;

/**
 * Clase encargada implementar la intefaz para el manejo de la ContratoEmpresas
 * dentro de la base de datos
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Component
public class ContratoEmpresasDaoImpl implements com.pradettisanti.payroll.dao.ContratoEmpresasDao {

    private static final Logger LOGGER = Logger.getLogger(ContratoEmpresasDaoImpl.class);

    /**
     * Metodo encargado de ingresar/actulizar un ContratoEmpresa
     * @param contratoEmpresas Instancia de ContratoEmpresas
     */
    @Override
    public void setContratoEmpresas(ContratoEmpresas contratoEmpresas) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(contratoEmpresas);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ContratoEmpresasDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo encargado de devolver el contrato entre empresas segun el id enviado
     * @param idContratoEmpresas Numero entero que contiene el id de un ContratoEmpresas
     * @return ContratoEmpresas
     */
    @Override
    public ContratoEmpresas getContratoEmpresas(Integer idContratoEmpresas) {
        ContratoEmpresas ce = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ce = (ContratoEmpresas) session.get(ContratoEmpresas.class, idContratoEmpresas);

        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoEmpresasDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return ce;
    }

    /**
     * Metodo encargado de devolver todos los contratos para las empresas
     * existentes
     * @return List ContratoEmpresas
     */
    @Override
    public List<ContratoEmpresas> getContratoEmpresas() {
        List<ContratoEmpresas> contratoEmpresas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contratoEmpresas = session.getNamedQuery("ContratoEmpresas.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoEmpresasDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratoEmpresas;
    }

    /**
     * Metodo encargado de devolver todos los contratos para aun cliente en
     * especial
     * @param cliente Instancia de Cliente
     * @return List ContratoEmpresas
     */
    @Override
    public List<ContratoEmpresas> getContratoEmpresas(Cliente cliente) {
        List<ContratoEmpresas> contratoEmpresas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contratoEmpresas = session.getNamedQuery("ContratoEmpresas.findByCliente").setEntity("cliente", cliente).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoEmpresasDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratoEmpresas;
    }

    /**
     * Metodo encargado de devolver todos los contratos para aun constratista en
     * especial
     * @param contratista Instancia de Contratista
     * @return List ContratoEmpresas
     */
    @Override
    public List<ContratoEmpresas> getContratoEmpresas(Contratista contratista) {
        List<ContratoEmpresas> contratoEmpresas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contratoEmpresas = session.getNamedQuery("ContratoEmpresas.findByContratista").setEntity("contratista", contratista).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoEmpresasDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratoEmpresas;
    }

    /**
     * Metodo encargado de devolver todos los contratos para aun constratista y
     * un cliente en especifico
     * @param cliente Instancia de Cliente
     * @param contratista Intancia de Contratista
     * @return List ContratoEmpresas
     */
    @Override
    public List<ContratoEmpresas> getContratoEmpresas(Cliente cliente, Contratista contratista) {
        List<ContratoEmpresas> contratoEmpresas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            contratoEmpresas = session.getNamedQuery("ContratoEmpresas.findByClienteAndContratista").setEntity("cliente", cliente).setEntity("contratista", contratista).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoEmpresasDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return contratoEmpresas;
    }
    
    /**
     * Metodo encargado de regresar una lista de tipo DelContrato apartir de un cliente
     * @param idCliente
     * @return List DelContrato
     */
    @Override
    public List<DelContrato> getContratosDelCliente(Integer idCliente) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
         List<DelContrato> querys = null;
        try {
            // Cadena de consulta SQL
            String QuerySQL = "select ce.id_contrato_empresas as idContratoEmpresas, ce.nombre_contrato as nombreContrato,"
                    + "ce.fecha as fechaContrato, co.id_contratista as idContratista,co.nombre_empresa as contratista, "
                    + "cl.id_cliente as idCliente, cl.nombre_empresa as cliente\n" +
                    "from contrato_empresas ce\n" +
                    "INNER JOIN contratista co on co.id_contratista = ce.contratista\n" +
                    "INNER JOIN cliente cl on cl.id_cliente = ce.cliente\n" +
                    "where cl.id_cliente = :idCliente\n";
            //Se reempleza la cadena de texto :idCliente por el valor de idCliente
            QuerySQL = QuerySQL.replace(":idCliente", idCliente.toString()); 
            //Se ejecuta la cadena de consulta SQL con la sesión de hibernate
            Query setResultTransformer = session.createSQLQuery(QuerySQL)
             //Se especifican las columnas y tipos que se devuelen de la consulta SQL
            .addScalar("idContratoEmpresas",IntegerType.INSTANCE)
            .addScalar("nombreContrato",StringType.INSTANCE)
            .addScalar("fechaContrato",DateType.INSTANCE)
            .addScalar("idContratista",IntegerType.INSTANCE)
            .addScalar("contratista",StringType.INSTANCE)
            .addScalar("idCliente", IntegerType.INSTANCE)
            .addScalar("cliente",StringType.INSTANCE)
            //Tranformación de los resulados de la query SQL a el objeto de Java DelContrato 
            .setResultTransformer(Transformers.aliasToBean(DelContrato.class)); 
            querys = setResultTransformer.list(); //Se devuelven los resultados en una lista de tipo DelContrato
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoEmpresasDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return querys; //Devuelve los resultados en una lista de tipo DelContrato
    }

    /**
     * Metodo encargado de devolver un objeto ContratoEmpresas bajo un nombre de contrato
     * @param nombreContrato
     * @return ContratoEmpresas
     */
    @Override
    public ContratoEmpresas getContratoEmpresasByIdName(String nombreContrato) {
        ContratoEmpresas ce = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ce = (ContratoEmpresas) session.getNamedQuery("ContratoEmpresas.findByIdName").setString("nombreContrato", nombreContrato).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ContratoEmpresasDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return ce;
    }
}
