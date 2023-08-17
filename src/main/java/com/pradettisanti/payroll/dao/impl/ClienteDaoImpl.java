/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.EmpresasDomicilio;
import com.pradettisanti.payroll.pojoh.RelacionContrato;
import com.pradettisanti.payroll.pojoh.TipoDomicilio;
import com.pradettisanti.payroll.pojoh.Usuario;
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
 * Clase encargada implementar la intefaz para el manejo de la cliente dentro de
 * la base de datos
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@Component
public class ClienteDaoImpl implements com.pradettisanti.payroll.dao.ClienteDao {

    private static final Logger LOGGER = Logger.getLogger(ClienteDaoImpl.class);

    /**
     * Metodo encargado de ingresar/actualizar un nuevo cliente devolviendo en
     * ID del cliente dentro de la base de datos
     * @param cliente Instancia de Cliente
     * @return Integer
     */
    @Override
    public Integer setCliente(Cliente cliente) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(cliente);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return cliente.getIdCliente();
    }

    /**
     * Metodo encargado de ingresar o actualizar el domicilio de una empresa
     * @param empresasDomicilio Instancia de empresas domicilio
     * @return Integer
     */
    @Override
    public Integer setDomicilio(EmpresasDomicilio empresasDomicilio) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(empresasDomicilio);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return empresasDomicilio.getIdEmpresasDomicilio();
    }

    /**
     * Metodo encargado de devolver todos los clientes registrados dentro de la
     * base de datos
     * @return List Cliente 
     */
    @Override
    public List<Cliente> getClientes() {
        List<Cliente> clientes = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            clientes = session.getNamedQuery("Cliente.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return clientes;
    }

    /**
     * Metodo encargado de devolver un cliente con respecto a un id ingresado
     * @param idCliente Numero entero que contiene el id de un cliente
     * @return Cliente
     */
    @Override
    public Cliente getCliente(Integer idCliente) {
        Cliente cliente = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            cliente = (Cliente) session.get(Cliente.class, idCliente);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return cliente;
    }

    /**
     * Metodo encargado de devolver una lista de cliente en base a los
     * parametros ingresados dentro del objeto cliente
     * @param cliente Instancia de Cliente
     * @return List Cliente 
     */
    @Override
    public List<Cliente> getCliente(Cliente cliente) {
        List<Cliente> clientes = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria e = session.createCriteria(Cliente.class);
            if (cliente.getNombreEmpresa() != null) {
                e.add(Restrictions.like("nombreEmpresa", "%" + cliente.getNombreEmpresa() + "%"));
            } else if (cliente.getRepresentanteLegal() != null) {
                e.add(Restrictions.like("representanteLegal", "%" + cliente.getRepresentanteLegal() + "%"));
            } else {
                e.add(Restrictions.like("rfc", "%" + cliente.getRfc() + "%"));
            }
            clientes = e.list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return clientes;
    }

    /**
     * Metodo encargado de devolver las relaciones de contrato con respecto a un
     * cliente
     * @param cliente Instancia de Cliente
     * @return List RelacionContrato
     */
    @Override
    public List<RelacionContrato> getRelacionContrato(Cliente cliente) {
        List<RelacionContrato> relacionContratos = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            relacionContratos = session.getNamedQuery("RelacionContrato.findByCliente").setEntity("cliente", cliente).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return relacionContratos;
    }

    /**
     * Metodo encargado de devolver un tipo de domicilio con respecto a un id
     * @param idTipoDomicilio Numero entero corto que contiene el id de un TipoDomicilio
     * @return TipoDomicilio
     */
    @Override
    public TipoDomicilio getTipoDomicilio(Short idTipoDomicilio) {
        TipoDomicilio tipoDomicilio = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoDomicilio = (TipoDomicilio) session.getNamedQuery("TipoDomicilio.findByIdTipoDomicilio").setShort("idTipoDomicilio", idTipoDomicilio).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDomicilio;
    }

    /**
     * Metodo encargado de devolver todos los tipo de domicilio
     * @return List TipoDomicilio
     */
    @Override
    public List<TipoDomicilio> getTipoDomicilio() {
        List<TipoDomicilio> tipoDomicilio = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoDomicilio = session.getNamedQuery("TipoDomicilio.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDomicilio;
    }

    /**
     * Metodo encargado de devolver el cliente de un usuario
     * @param usuario Instancia de usuario
     * @return Cliente
     */
    @Override
    public Cliente getCliente(Usuario usuario) {
        Cliente cliente = new Cliente();
        String sql = "select * from cliente "
                + "join usuario_clientes on usuario_clientes.cliente = cliente.id_cliente "
                + "join usuario on usuario.id_usuario = usuario_clientes.usuario "
                + "where usuario = " + usuario.getIdUsuario();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            cliente = (Cliente) session.createSQLQuery(sql).addEntity(Cliente.class).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ClienteDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return cliente;
    }

}
