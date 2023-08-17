/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.dao.ReciboDao;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.ReciboSolicitado;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.ArrayList;
import java.util.Date;
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
 * Clase encargada de implementar los metodos de la interfaz ReciboDao
 *
 * @author HEM 
 */
@Component
public class ReciboDaoImpl implements ReciboDao {

    private static final Logger LOGGER = Logger.getLogger(ReciboDaoImpl.class);

    /**
     * Metodo que guarda un recibo
     * @param recibo Instancia de Recibo
     */
    @Override
    public void setRecibo(Recibo recibo) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(recibo);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que guarda una lista de recibos
     * @param recibo Lista de Instancias de Recibo
     */
    @Override
    public void setRecibo(List<Recibo> recibo) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibo.stream().forEach((Recibo rec) -> {
                session.saveOrUpdate(rec);
            });
            tx.commit();

        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo encargado de obtener un solo recibo con respecto a su ID
     * @param idRecibo Numero entero que contiene el id de un recibo
     * @return Recibo
     */
    @Override
    public Recibo getRecibo(Integer idRecibo) {
        Recibo recibo = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibo = (Recibo) session.get(Recibo.class, idRecibo);
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibo;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param agremiado Instancia de agremiado
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Agremiado agremiado) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByAgremiado").setInteger("agremiado", agremiado.getIdAgremiado()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param agremiado Instancia de agremiado
     * @param diaInicial Instancia de Date
     * @param diaFinal Instancia de Date
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Date diaInicial, Date diaFinal) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByAgrFechainicialFechafinal").setInteger("agremiado", agremiado.getIdAgremiado()).setDate("fInicial", diaInicial).setDate("fFinal", diaFinal).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param cliente Instancia de Cliente
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Cliente cliente) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByCliente").setInteger("cliente", cliente.getIdCliente()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param cliente Instancia de Cliente
     * @param diaDeRegistro Instancia de Date
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Cliente cliente, Date diaDeRegistro) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByClienteAndFechaRegistro").setInteger("idCliente", cliente.getIdCliente()).setDate("diaDeRegistro", diaDeRegistro).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos con base al cliente y a un
     * periodo de registro
     * @param cliente Instancia de Cliente
     * @param diaDeRegistroDesde Instancia de Date
     * @param diaDeRegistroHasta Instancia de Date
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibosPeriodo(Cliente cliente, Date diaDeRegistroDesde, Date diaDeRegistroHasta) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByClienteAndPeriodoFechaRegistro").setInteger("idCliente", cliente.getIdCliente()).setDate("diaDeRegistroDesde", diaDeRegistroDesde).setDate("diaDeRegistroHasta", diaDeRegistroHasta).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param cliente Instancia de Cliente
     * @param diaInicial Instancia de Date
     * @param diaFinal Instancia de Date
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Cliente cliente, Date diaInicial, Date diaFinal) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByCltFechainicialFechafinal").setInteger("cliente", cliente.getIdCliente()).setDate("fInicial", diaInicial).setDate("fFinal", diaFinal).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param sindicato Instancia de Sindicato
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Sindicato sindicato) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findBySindicato").setInteger("sindicato", sindicato.getIdSindicato()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param sindicato Instancia de Sindicato
     * @param diaInicial Instancia de Date
     * @param diaFinal Instancia de Date
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Sindicato sindicato, Date diaInicial, Date diaFinal) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findBySdoFechainicialFechafinal").setInteger("sindicato", sindicato.getIdSindicato()).setDate("fInicial", diaInicial).setDate("fFinal", diaFinal).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param agremiado Instancia de Agremiado
     * @param cliente Instancia de Cliente
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Cliente cliente) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByAgrClt").setInteger("agremiado", agremiado.getIdAgremiado()).setInteger("cliente", cliente.getIdCliente()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param agremiado Instancia de Agremiado
     * @param cliente Instancia de Cliente
     * @param diaInicial Instancia Date
     * @param diaFinal Instancia Date
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Cliente cliente, Date diaInicial, Date diaFinal) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByAgrCltFechainicialFechafinal").setInteger("agremiado", agremiado.getIdAgremiado()).setInteger("cliente", cliente.getIdCliente()).setDate("fInicial", diaInicial).setDate("fFinal", diaFinal).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param agremiado Instancia de Agremiado
     * @param sindicato Instancia de Sindicato
     * @return lista de recibos filtrados por agremiado y sindicato
     */
    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Sindicato sindicato) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByAgrSdo").setInteger("agremiado", agremiado.getIdAgremiado()).setInteger("sindicato", sindicato.getIdSindicato()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo que obtiene una lista de recibos
     * @param agremiado Instancia de Agremiado
     * @param sindicato Instancia de Sindicato
     * @param diaInicial Instancia Date
     * @param diaFinal Instancia Date
     * @return List Recibo
     */
    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Sindicato sindicato, Date diaInicial, Date diaFinal) {
        List<Recibo> recibos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibos = session.getNamedQuery("Recibo.findByAgrSdoFechainicialFechafinal").setInteger("agremiado", agremiado.getIdAgremiado()).setInteger("sindicato", sindicato.getIdSindicato()).setDate("fInicial", diaInicial).setDate("fFinal", diaFinal).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibos;
    }

    /**
     * Metodo encargado de contar el numero de recibos ingresados en la nomina de un cliente
     * @param cliente
     * @param diaInicial
     * @param diaFinal
     * @return int
     */
    @Override
    public int getRecibosRegistrados(Cliente cliente, Date diaInicial, Date diaFinal) {
        int total = 0 ;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            total = session.getNamedQuery("Recibo.findByCltFechainicialFechafinal").setInteger("cliente", cliente.getIdCliente()).setDate("fInicial", diaInicial).setDate("fFinal", diaFinal).setCacheMode(CacheMode.IGNORE).list().size();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return total;
    }

    /**
     * Metodo encargado de devolver un recibo ya ingresado
     * @param agremiado
     * @param cliente
     * @param diaInicial
     * @param diaFinal
     * @return Recibo
     */
    @Override
    public Recibo getRecibo(Integer agremiado, Cliente cliente, Date diaInicial, Date diaFinal) {
        Recibo recibo = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            recibo = (Recibo)session.getNamedQuery("Recibo.findByAgrCltFechainicialFechafinal").setInteger("agremiado", agremiado).setInteger("cliente", cliente.getIdCliente()).setDate("fInicial", diaInicial).setDate("fFinal", diaFinal).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return recibo;
    }
     /**
     * Metodo que obtiene una lista de recibos solicitados
     * @param cliente
     * @param diaRegistroDesde
     * @param diaRegistroHasta
     * @return int
     */
    @Override
    public List<ReciboSolicitado> getRecibosSolicitados(Cliente cliente, Date diaRegistroDesde, Date diaRegistroHasta) { //Metodo que obtiene una lista de recibos solicitados bajo el criterio de cliente dia registro desde y dia registro hasta
        List<ReciboSolicitado> recibos = new ArrayList<>(); //Se declara una variable como un vector de tipo ReciboSolicitado
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernete
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //Se obtiene la sesión Hibernate
        Transaction tx = session.beginTransaction();
        try {
            Criteria createCriteria = session.createCriteria(ReciboSolicitado.class); //Se declara una variable de tipo Criteria con el pojoh ReciboSolicitado
            createCriteria.add(Restrictions.eq("idCliente", cliente)) //se agrega la restricción de que el campo "idCliente" del pojoh sea igual al parametro cliente.getIdCliente
                          .add(Restrictions.between("fechaSolicitado", diaRegistroDesde, diaRegistroHasta)); //se agrega la restricción de que el campo "fechaSolicitada" del ppojoh sea entre los parametros de: diaRegistroDesde y diaRegistroHasta 
            recibos = createCriteria.list(); //Se le asigna el calor de la lista de criterios a la lista recibos
        } catch (HibernateException he) { //Se cachá la escpeción Hibernate
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he); //Se imprime en los logs el error con su descripción y variable de error
        } finally { //Si no pasa por el try ni el catch 
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return recibos; //Se regresa la lista recibos
    }

     /**
     * Metodo que guarda una lista de recibos solicitados
     * @param reciboSolicitado Lista de Instancias de ReciboSolicitado
     */
    @Override
    public void setRegistrarEnvioDeRecibo(ReciboSolicitado reciboSolicitado) { //Metodo que guarda una lista de recibos solicitados que recibe como parametros un objeto ReciboSolicitado
        HibernateUtl.buildSessionFactory(); // Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //Se obtiena la sesión Hibernate
        Transaction tx = session.beginTransaction(); // Se iniicializa la transacción
        try {
            session.saveOrUpdate(reciboSolicitado); //Se solicita guardar el objeto recibo solicitado
            tx.commit(); //Se guarda el objeto reciboSolicitado
        } catch (HibernateException he) { //Excpeción Hibernate
            tx.rollback(); //Se regresa la petición de guardar el objeto recibosolicitado
            LOGGER.error("CLASS: ReciboDaoImpl - Error en la capa de acceso a datos", he); //Se imprime en el log el msj de error + variable de error Hibernate
        }catch (Exception e) { //Se cacha la excpeción 
            tx.rollback(); //Se regresa la petición de guardar el objeto recibosolicitado
            LOGGER.error("[DAO, Recibo] Ocurrió un error al intentar guardar la solcitud de envio de un recibo   -> "+e.getMessage(), e); //Se imprime en el log el error, notificando con mensaje y variable de error 
        } finally { // Si no pasa por el try ni el catch
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
    }

}
