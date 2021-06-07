/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.Utility.StatusQuery;
import com.pradettisanti.payroll.dao.AgremiadoDao;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.DatosBancarios;
import com.pradettisanti.payroll.pojoh.DatosBeneficiario;
import com.pradettisanti.payroll.pojoh.DatosDomicilio;
import com.pradettisanti.payroll.pojoh.DatosLaborales;
import com.pradettisanti.payroll.pojoh.DatosPersonales;
import com.pradettisanti.payroll.pojoh.Documento;
import com.pradettisanti.payroll.pojoh.EsquemaCampo;
import com.pradettisanti.payroll.pojoh.EsquemaPago;
import com.pradettisanti.payroll.pojoh.Incidencia;
import com.pradettisanti.payroll.pojoh.Modulo;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.RelacionContrato;
import com.pradettisanti.payroll.pojoh.SolicitudBaja;
import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import com.pradettisanti.payroll.pojoh.TipoContrato;
import com.pradettisanti.payroll.pojoh.TipoDocumento;
import com.pradettisanti.payroll.pojoh.TipoNomina;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase encargada de implementar los metodos pertenecientes a la interfaz de
 * AgremiadoDao
 *
 
 */
@Component
public class AgremiadoDaoImpl implements AgremiadoDao {

    private static final Logger LOGGER = Logger.getLogger(AgremiadoDaoImpl.class);

    private final String CONSOLE_MESSAGE = "[DAO,Agremiado] ";
    
    /**
     * Metodo que se encarga de guardar un agremiado
     * @param agremiado Instancia de agremiado Instancia de agremiado
     * @return Integer
     */
    @Override
    public Integer setAgremiado(Agremiado agremiado) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(agremiado);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return agremiado.getIdAgremiado();
    }

    /**
     * Metodo que se encargar de guardar datos bancarios
     * @param datosBancarios Instancia de DatosBancarios
     */
    @Override
    public void setDatosBancarios(DatosBancarios datosBancarios) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(datosBancarios);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar datos de un beneficiario
     * @param datosBeneficiario Instancia de DatosBeneficiario
     */
    @Override
    public void setDatosBeneficiario(DatosBeneficiario datosBeneficiario) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(datosBeneficiario);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar datos laborales
     * @param datosLaborales Instancia de DatosLaborales
     */
    @Override
    public void setDatosLaborales(DatosLaborales datosLaborales) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(datosLaborales);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("[CLASS, AgremiadoImpl ]  Error en la capa de acceso a datos --> "+he.getMessage());
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar datos personales
     * @param datosPersonales Instancia de DatosPersonales
     */
    @Override
    public void setDatosPersonales(DatosPersonales datosPersonales) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(datosPersonales);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar un documento
     * @param documento Instancia de documento
     */
    @Override
    public void setDocumento(Documento documento) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(documento);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar una lista de documentos
     * @param documentos Lista de Instancias de documento
     */
    @Override
    public void setDocumento(List<Documento> documentos) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documentos.stream().forEach((doc) -> {
                if (doc.getDocumentoPK() != null) {
                    session.update(doc);
                } else {
                    session.save(doc);
                }
            });

            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardart datos de un domicilio
     * @param datosDomicilio Instancia de DatosDomicilio
     */
    @Override
    public void setDatosDomicilio(DatosDomicilio datosDomicilio) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(datosDomicilio);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar un recibo
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
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar una relación de contrato     
     * @param relacionContrato Instancia de RelacionContrato
     */
    @Override
    @Transactional
    public void setRelacionContrato(RelacionContrato relacionContrato) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(relacionContrato);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar una solicitud de baja
     * @param solicitudBaja Instancia de SolicitudBaja
     */
    @Override
    public void setSolicitudBaja(SolicitudBaja solicitudBaja) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(solicitudBaja);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que se encarga de guardar una solicitud de renovación
     * @param solicitudRenovacionContrato Instancia de SolicitudDeRenovacionContrato
     */
    @Override
    public void setSolicitudRenovacionContrato(SolicitudRenovacionContrato solicitudRenovacionContrato) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(solicitudRenovacionContrato);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo encargdo de ingresar una incidencia en la base de datos
     * @param incidencia Instancia de Incidencia
     */
    @Override
    public void setIncidencia(Incidencia incidencia) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(incidencia);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo encargado de devolver la ultima solicitud de la renvación de un contrato de un agremiado
     * @param agremiado Instancia de agremiado
     * @return SolicitudRenovacionContrato
     */
    @Override
    public SolicitudRenovacionContrato getLastSolRenovacion(Agremiado agremiado) {
        SolicitudRenovacionContrato renovacionContrato = new SolicitudRenovacionContrato();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            renovacionContrato = (SolicitudRenovacionContrato) session.getNamedQuery("SolicitudRenovacionContrato.findLastByAgremmiado")
                    .setCacheMode(CacheMode.IGNORE)
                    .setParameter("agremiado", agremiado).uniqueResult();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return renovacionContrato;
    }
    
    /**
     * Metodo encargado de devolver la ultima de la relación de un contrato
     * @param agremiado  Instancia de agremiado
     * @return RelacionContrato
     */
    @Override
    public RelacionContrato getLastRelacionContrato(Agremiado agremiado) {
        RelacionContrato relacionContrato = new RelacionContrato();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            relacionContrato = (RelacionContrato) session.getNamedQuery("RelacionContrato.findLastByAgremmiado").setCacheMode(CacheMode.IGNORE)
                    .setParameter("agremiado", agremiado).uniqueResult();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return relacionContrato;
    }

    /**
     * Metodo encargado de devolver la ultima solicitud de baja
     * @param agremiado Instancia de agremiado
     * @return SolicitudBaja
     */
    @Override
    public SolicitudBaja getLastSolicitudBaja(Agremiado agremiado) {
        SolicitudBaja solicitudBaja = new SolicitudBaja();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            solicitudBaja = (SolicitudBaja) session.getNamedQuery("SolicitudBaja.findLastByAgremmiado").setCacheMode(CacheMode.IGNORE).setParameter("agremiado", agremiado).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return solicitudBaja;
    }

    /**
     * Metodo encargado de devolver los datos domicilio de un agremiado
     * @param idAgremiado Numero entero que contiene el id de un agremiado
     * @return DatosDomicilio
     */
    @Override
    public DatosDomicilio getDatosDomicilio(Integer idAgremiado) {
        DatosDomicilio datosDomicilio = new DatosDomicilio();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            datosDomicilio = (DatosDomicilio) session.get(DatosDomicilio.class, idAgremiado);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return datosDomicilio;
    }

    /**
     * Metodo encargado de devolver los datos beneficiario de un agremiado
     * @param idAgremiado Numero entero que contiene el id de un agremiado
     * @return DatosBeneficiario
     */
    @Override
    public DatosBeneficiario getDatosBeneficiario(Integer idAgremiado) {
        DatosBeneficiario datosBeneficiario = new DatosBeneficiario();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            datosBeneficiario = (DatosBeneficiario) session.get(DatosBeneficiario.class, idAgremiado);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return datosBeneficiario;
    }

    /**
     * Metodo encargado de devolver los datos bancarios de un agremiado
     * @param idAgremiado Numero entero que contiene el id de un agremiado
     * @return DatosBancarios
     */
    @Override
    public DatosBancarios getDatosBancarios(Integer idAgremiado) {
        DatosBancarios datosBancarios = new DatosBancarios();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            datosBancarios = (DatosBancarios) session.get(DatosBancarios.class, idAgremiado);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return datosBancarios;
    }

    /**
     * Metodo que se encarga de obtener un agremiado
     * @param idAgremiado Numero entero que contiene el id de un agremiado
     * @return Agremiado
     */
    @Override
    public Agremiado getAgremiado(Integer idAgremiado) {
        Agremiado ag = new Agremiado();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ag = (Agremiado) session.get(Agremiado.class, idAgremiado);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return ag;
    }

    /**
     * Metodo que se encarga de obtener un agremiado
     * @param agremiado Instancia de agremiado
     * @return Agremiado
     */
    @Override
    public Agremiado getAgremiado(Agremiado agremiado) {
        Agremiado ag = new Agremiado();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            ag = (Agremiado) session.get(Agremiado.class, agremiado.getIdAgremiado());
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return ag;
    }

    /**
     * Metodo que se encarga de obtener una lista de agremiados dependiendo del estatus y del cliente; 
     * El metodo se volvio obsoleto debido a mal uso de las técnologias, se reemplaza por un metodo con los mismo tipos de entrada y de resultado.
     * @param statusAgremiado Instancia de StatusAgremiado
     * @param cliente Instancia de Cliente
     * @return List Agremiado
     * @deprecated 
     * @since 12/02/2018
     * @see getAgremiados(StatusAgremiado statusAgremiado, Cliente cliente)
     */
    @Deprecated
    @Override
    public List<Agremiado> getAgremiado(StatusAgremiado statusAgremiado, Cliente cliente) {
        List<Agremiado> agremiados = new ArrayList<>();
        String sql = "SELECT ag.* FROM agremiado ag"
                + "join status_agremiado st on st.id_status_agremiado = ag.status_agremiado "
                + "join datos_laborales dl on dl.agremiado = ag.id_agremiado "
                + "join cliente cl on cl.id_cliente = dl.cliente "
                + "where ag.status_agremiado =" + statusAgremiado.getIdStatusAgremiado()
                + "and cl.id_cliente = " + cliente.getIdCliente();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            agremiados = session.createSQLQuery(sql).addEntity(Agremiado.class).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return agremiados;
    }

    /**
     * Metodo que se encarga de obtener una lista de agremiados con respecto a su Estatus textual y el id del cliente
     * @param status Cadena de texto que contiene la leyeda del status que se desea buscar
     * @param idCliente Numero entero que contiene el id del cliente
     * @return List Agremiado 
     */
    @Override
    public List<Agremiado> getAgremiados(String status, Integer idCliente) {
        List<Agremiado> agremiados = new ArrayList<>();
        String sql = "SELECT ag.* FROM agremiado ag "
                + "join status_agremiado st on st.id_status_agremiado = ag.status_agremiado "
                + "join datos_laborales dl on dl.agremiado = ag.id_agremiado "
                + "join cliente cl on cl.id_cliente = dl.cliente "
                + "where st.status = '" + status
                + "' and cl.id_cliente = " + idCliente;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            agremiados = session.createSQLQuery(sql).addEntity(Agremiado.class).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos --> "+he.getMessage(), he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return agremiados;
    }
    
    /**
     * Metodo encargado de contar cuantos agremiados estan en un status en especifico para un cliente dado
     * @param status Cadena de texto que contiene el nombre del status
     * @param idCliente Numero entero que contiene el id de un cliente
     * @return Integer
     */
    @Override
    public Integer getAgremiadosCount( String status, Integer idCliente){
        int agremiados = 0;
        String sql = "SELECT count(*) FROM agremiado ag "
                + "join status_agremiado st on st.id_status_agremiado = ag.status_agremiado "
                + "join datos_laborales dl on dl.agremiado = ag.id_agremiado "
                + "join cliente cl on cl.id_cliente = dl.cliente "
                + "where st.status = '" + status
                + "' and cl.id_cliente = " + idCliente;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
              agremiados =  ((Number) session.createSQLQuery(sql).uniqueResult()).intValue();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return agremiados;
    }

    /**
     * Metodo encargado de deolver una lista de agremiados los cuales su contrato esta por vencer
     * @param idCliente Numero entero que contiene el id de un cliente
     * @return List Agremiado
     */
    @Override
    public List<Agremiado> getAgremiadosContratosPorVencer(Integer idCliente) {
        Properties prop = getPropertySql();
        String sqlProperty = prop.getProperty("query.findAgreByFchTerm");
        String query = MessageFormat.format(sqlProperty, idCliente);
        List<Agremiado> agremiados = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            agremiados = session.createSQLQuery(query).addEntity(Agremiado.class).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }

        return agremiados;
    }

    /**
     * Metodo que se encarga de obtener un documento con respecto a un agremiado y a un tipo de documento
     * @param agremiado Instancia de agremiado
     * @param tipoDocumento Instancia de TipoDocumento
     * @return Documento
     */
    @Override
    public Documento getDocumento(Agremiado agremiado, TipoDocumento tipoDocumento) {
        Documento documento = new Documento();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Documento.class);
            criteria.add(Restrictions.eq("agremiadoObj", agremiado));
            criteria.add(Restrictions.eq("tipoDocumentoObj", tipoDocumento));
            documento = (Documento) criteria.uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documento;
    }

    /**
     * Metodo que se encarga de obtener una lista de documentos con respecto al modulo y al agremiado
     * @param agremiado Instancia de agremiado
     * @param modulo Instancia de Modulo
     * @return List Documento
     */
    @Override
    public List<Documento> getDocumentos(Agremiado agremiado, Modulo modulo) {
        List<Documento> documentos = new ArrayList<>();
        String sql = "select * from documento "
                + "join tipo_documento on tipo_documento.id_tipo_documento = documento.tipo_documento "
                + "join modulo on modulo.id_modulo = tipo_documento.modulo "
                + "where documento.agremiado = " + agremiado.getIdAgremiado()
                + " and modulo.id_modulo = " + modulo.getIdModulo();

        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documentos = session.createSQLQuery(sql).addEntity(Documento.class).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentos;
    }

    /**
     * Metodo encargado de devolver una lista con id de los documentos obligatorios que tiene un agremiado por un id de modulo
     * @param agremiado Instancia de agremiado
     * @param modulo Numero corto de un modulo
     * @return List Integer
     */
    @Override
    public List<Integer> getDocumentosOblg(Agremiado agremiado, Short modulo) {
        List<Integer> documentos = new ArrayList<>();
        String sql = "select documento.tipo_documento from documento "
                + "join tipo_documento on tipo_documento.id_tipo_documento = documento.tipo_documento "
                + "join modulo on modulo.id_modulo = tipo_documento.modulo "
                + "where documento.agremiado = " + agremiado.getIdAgremiado()
                + " and modulo.id_modulo = " + modulo
                + " and tipo_documento.obligatorio = 1";

        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documentos = session.createSQLQuery(sql).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentos;
    }

    /**
     * Metodo que se encarga de obtener una lista de docuemntos con respecto a un agremiado y a una lista de modulo
     * @param agremiado Instancia de agremiado
     * @param modulos Lista de Intancias de Modulo
     * @return List Documento
     */
    @Override
    public List<Documento> getDocumentos(Agremiado agremiado, List<Modulo> modulos) {
        List<Documento> documentos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(Documento.class);

            criteria.createAlias("tipoDocumentoObj", "td", JoinType.INNER_JOIN);
            criteria.createAlias("td.moduloObj", "modulo", JoinType.INNER_JOIN);

            criteria.add(Restrictions.in("modulo", modulos));
            criteria.add(Restrictions.eq("agremiadoObj", agremiado));

            documentos.addAll(criteria.list());
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documentos;

    }

    /**
     * Metodo que te devuelve la Instancia de Esquema de Pago del id enviado
     * @param idEsquemaPago Numero entero corto que contiene el id de un EsquemaPago
     * @return EsquemaPago
     */
    @Override
    public EsquemaPago getEsquemaPago(Short idEsquemaPago) {
        EsquemaPago esquemaPago = new EsquemaPago();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            esquemaPago = (EsquemaPago) session.get(EsquemaPago.class, idEsquemaPago);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return esquemaPago;
    }
    
    /**
     * Metodo encargado de deolver los agremados que se ajustan a los parametros enviados
     * @param nombre Cadena de texto que contiene un nombre
     * @param curp Cadena de texto que contiene un CURP
     * @param rfc Cadena de texto que contiene un RFC
     * @param IdCliente Numero entero que contiene el id de un cliente
     * @return List Agremiado
     */
    @Override
    public List<Agremiado> getAgremiadosBusqueda(String nombre, String curp, String rfc, Integer IdCliente) {
        List<Agremiado> agremiados = new ArrayList<>();
        Properties prop = getPropertySql();
        String sqlProperty = prop.getProperty("query.findAgremiadoBusqueda");
//        System.out.println("criterio ::::: " + criterio);
//        System.out.println("valor :::: "  + valor);
        
        String query = MessageFormat.format(sqlProperty, nombre, curp, rfc, IdCliente);
        System.out.println("query === " + query);
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            System.out.println("nombreeee :::: " + nombre);
            System.out.println("clienteee :::: " + IdCliente);
            Criteria e = session.createCriteria(Agremiado.class);
            e.createAlias("datosLaborales", "dtl", JoinType.INNER_JOIN);
            e.createAlias("dtl.clienteObj", "cliente", JoinType.INNER_JOIN);
            e.createAlias("datosPersonales", "dp", JoinType.INNER_JOIN);
            System.out.println("nombreee  + " + nombre);
            e.add(Restrictions.like("dp.nombre", "%" + nombre + "%"));

            e.add(Restrictions.eq("cliente.idCliente", IdCliente));
            agremiados = e.list();
            System.out.println("agremiados2 ==== " + agremiados.size());
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return agremiados;
    }
    

    /**
     * Metodo encargado de obtener un modulo
     * @param idModulo Numero entero corto que contiene el id de un modulo
     * @return Modulo
     */
    @Override
    public Modulo getModulo(Short idModulo) {
        Modulo modulo = new Modulo();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            modulo = (Modulo) session.get(Modulo.class, idModulo);
            tx.commit();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return modulo;
    }

    /**
     * Metodo encargado de obtener una lista de modulos
     * @return List Modulo
     */
    @Override
    public List<Modulo> getModulos() {
        List<Modulo> actas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            actas = session.getNamedQuery("Modulo.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return actas;
    }

    /**
     * Metodo encargado de obtener un tipo de documento
     * @param tipoDocumento numero entero que contiene el id de un TipoDocumento
     * @return TipoDocumento
     */
    @Override
    public TipoDocumento getTipoDocumento(Integer tipoDocumento) {
        TipoDocumento tDocumento = new TipoDocumento();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tDocumento = (TipoDocumento) session.get(TipoDocumento.class, tipoDocumento);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tDocumento;
    }

    /**
     * Metodo encargado de devolver una lista de id de tipos de documentos obligatorios
     * @param idModulo Numero entero corto que contiene el id de un modulo
     * @return List Integer
     */
    @Override
    public List<Integer> getTipoDocumentosOblg(Short idModulo) {
        List<Integer> tipoDocumentos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoDocumentos = session.getNamedQuery("TipoDocumento.findByidMdulo").setShort("modulo", idModulo).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDocumentos;
    }

    /**
     * Metodo encargado de obtener una lista de tipo de documento
     * @param modulo Instancia de Modulo
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumento(Modulo modulo) {

        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoDocumentos = session.getNamedQuery("TipoDocumento.findByModulo").setShort("modulo", modulo.getIdModulo()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDocumentos;
    }

    /**
     * Metodo encargado de obtener una lista de tipo de documentos     
     * @param modulo Lista de instancias de Modulo
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumento(List<Modulo> modulo) {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            Criteria criteria = session.createCriteria(TipoDocumento.class);
            criteria.add(Restrictions.in("moduloObj", modulo));
            tipoDocumentos = criteria.list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDocumentos;
    }

    /**
     * Metodo que se encarga de obtener un tipo de contrato
     * @param idTipoContrato Numero entero corto que contiene el id de un TipoContrato
     * @return TipoContrato
     */
    @Override
    public TipoContrato getTipoContrato(Short idTipoContrato) {
        TipoContrato tipoContrato = new TipoContrato();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoContrato = (TipoContrato) session.get(TipoContrato.class, idTipoContrato);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoContrato;
    }

    /**
     * Metodo que se encarga de obtener una lista de los tipos de contratos
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
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoContratos;
    }

    /**
     * Metodo que se encarga de obtener un tipo de nomina
     * @param idTipoNomina Numero entero corto que contien el id de un TipoNomina
     * @return TipoNomina
     */
    @Override
    public TipoNomina getTipoNomina(Short idTipoNomina) {
        TipoNomina tipoNomina = new TipoNomina();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoNomina = (TipoNomina) session.get(TipoNomina.class, idTipoNomina);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoNomina;
    }

    /**
     * Metodo que se encarga de obtener una lista de tipos de nomina
     * @return List TipoNomina
     */
    @Override
    public List<TipoNomina> getTipoNomina() {
        List<TipoNomina> tipoNominas = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            tipoNominas = session.getNamedQuery("TipoNomina.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoNominas;
    }

    /**
     * Metodo que se encarga de obtener el estatus de un agremiado
     * @param idStatusAgremiado Numero entero corto que contiene el id de un StatusAgremiado
     * @return StatusAgremiado
     */
    @Override
    public StatusAgremiado getStatusAgremiado(Short idStatusAgremiado) {
        StatusAgremiado statusAgremiado = new StatusAgremiado();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            statusAgremiado = (StatusAgremiado) session.get(StatusAgremiado.class, idStatusAgremiado);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return statusAgremiado;
    }

    /**
     * Metodo que se encarga de obtener una lista de estatus de agremiados
     * @return List StatusAgremiado
     */
    @Override
    public List<StatusAgremiado> getStatusAgremiado() {
        List<StatusAgremiado> statusAgremiados = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        try {
            statusAgremiados = session.getNamedQuery("StatusAgremiado.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return statusAgremiados;

    }

    /**
     * Metodo que se encarga de borrar un documento
     * @param documento Instancia de Documento
     */
    @Override
    public void borrarDocumento(Documento documento) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            LOGGER.info(documento);
            session.delete(documento);
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }
    private Properties getPropertySql() {

        Properties prop = new Properties();
        InputStream inputStream;
        String propFileName = "sqlQuery.properties";
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            prop.load(inputStream);
        } catch (Exception e) {
            LOGGER.error("ERROR AL OBTENER EL ARCHIVO SQL PROPERTIES ");
        }

        return prop;
    }

    /**
     * Metodo encargado de devolver una lista de el numero de agremiados que se encuentran en un status 
     * @param status Lista de numeros cortos que representan  id de status 
     * @return List StatusQuery
     */
    @Override
    public List<StatusQuery> getAgremiadoPorStatus(List<Short> status) {        
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
         List<StatusQuery> querys = null;
        try {
            String inning = "";
            inning = status.stream().map((idStatus) -> idStatus+",").reduce(inning, String::concat);
            inning = inning.substring(0,inning.length()-1);
            String QuerySQL = "select sa.id_status_agremiado as ID, sa.status as ST, count(a.id_agremiado) as TTL\n" +
                    "from agremiado a\n" +
                    "RIGHT JOIN status_agremiado sa on sa.id_status_agremiado = a.status_agremiado\n" +
                    "where sa.id_status_agremiado in ( :inning )\n" +
                    "group by sa.id_status_agremiado, sa.status";
            QuerySQL = QuerySQL.replace(":inning", inning);
            Query setResultTransformer = session.createSQLQuery(QuerySQL)
            .addScalar("ID",IntegerType.INSTANCE)
            .addScalar("ST",StringType.INSTANCE)
            .addScalar("TTL",IntegerType.INSTANCE).setResultTransformer(Transformers.aliasToBean(StatusQuery.class));
            querys = setResultTransformer.list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }        
        return querys;
    }

    /**
     * Metodo encargado de devolver los agremiados que se encuentran en un determinado status
     * @param status Lista de numeros enteros cortos que representan ids de status
     * @param idUsuario Numero entero que representa el id de un usuario
     * @return List StatusQuery
     */
    @Override
    public List<StatusQuery> getAgremiadoPorStatus(List<Short> status, int idUsuario) {
                
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
         List<StatusQuery> querys = null;
        try {
            String inning = "";
            inning = status.stream().map((idStatus) -> idStatus+",").reduce(inning, String::concat);
            inning = inning.substring(0,inning.length()-1);
            String QuerySQL = "select sa.id_status_agremiado as ID, sa.status as ST, count(a.id_agremiado) as TTL\n" +
                        "from agremiado a\n" +
                        "RIGHT JOIN status_agremiado sa on sa.id_status_agremiado = a.status_agremiado\n" +
                        "inner join datos_laborales dl on dl.agremiado = a.id_agremiado\n" +
                        "inner join cliente c on c.id_cliente = dl.cliente\n" +
                        "inner join usuario_clientes uc on uc.cliente = c.id_cliente\n" +
                        "where uc.usuario = :user and sa.id_status_agremiado in ( :inning )\n" +
                        "group by sa.id_status_agremiado, sa.status";
            QuerySQL = QuerySQL.replace(":user", idUsuario+"").replace(":inning", inning);
            Query setResultTransformer = session.createSQLQuery(QuerySQL)
            .addScalar("ID",IntegerType.INSTANCE)
            .addScalar("ST",StringType.INSTANCE)
            .addScalar("TTL",IntegerType.INSTANCE).setResultTransformer(Transformers.aliasToBean(StatusQuery.class));              
            querys = setResultTransformer.list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }        
        return querys;
    }

    /**
     * Metodo encargado de devolver un lista de los agremiado que tienen un contrato a punto de vencer
     * @param statusAgremiado numero entero que contiene un id de status de agremiado
     * @param idUsuario numero entero que contiene el id de un usuario
     * @return List Agremiado
     */
    @Override
    public List<Agremiado> getAgremiadosPorVencer(int statusAgremiado, int idUsuario) {
           
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
          List<Agremiado> Result  = new ArrayList<>();
          
        try {
            String QuerySQL = "select a.*\n" +
                                                "from datos_laborales as dl \n" +
                                                "inner join agremiado as a on a.id_agremiado = dl.agremiado\n" +
                                                "inner join cliente c on c.id_cliente = dl.cliente\n" +
                                                "inner join usuario_clientes uc on uc.cliente = c.id_cliente\n" +
                                                "where dl.fecha_fin_contrato is not null \n" +
                                                " and dl.fecha_fin_contrato between date(date_add(now(), INTERVAL -10 Day)) and date(date_add(now(), INTERVAL 10 Day))\n" +
                                                "    and a.status_agremiado = :status \n" +
                                                "    and uc.usuario = :idUsuario";
            QuerySQL = QuerySQL.replace(":status", statusAgremiado+"").replace(":idUsuario", idUsuario+"");
            Query setResultTransformer = session.createSQLQuery(QuerySQL).addEntity(Agremiado.class);  
            Result = setResultTransformer.list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }        
        return Result;
        
    }

    /**
     * Metodo encargado de devolver la cantidad de agremiados que tienen un contrato por vencer
     * @param statusAgremiado numero entero que contiene el id de un status
     * @param idUsuario numero entero que contiene el id de un usuario
     * @return int
     */
    @Override
    public int getTotalAgremiadosPorVencer(int statusAgremiado, int idUsuario) {      
                
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
          Integer Result  = 0;
        try {
            String QuerySQL = "select count(dl.fecha_fin_contrato) as TTL\n" +
                                                    "from datos_laborales as dl \n" +
                                                    "inner join agremiado as a on a.id_agremiado = dl.agremiado\n" +
                                                    "inner join cliente c on c.id_cliente = dl.cliente\n" +
                                                    "inner join usuario_clientes uc on uc.cliente = c.id_cliente\n" +
                                                    "where dl.fecha_fin_contrato is not null \n" +
                                                    " and dl.fecha_fin_contrato between date(date_add(now(), INTERVAL -10 Day)) and date(date_add(now(), INTERVAL 10 Day))\n" +
                                                    "    and a.status_agremiado = :status \n" +
                                                    "    and uc.usuario = :idUsuario";
            QuerySQL = QuerySQL.replace(":status", statusAgremiado+"").replace(":idUsuario", idUsuario+"");
            Query setResultTransformer = session.createSQLQuery(QuerySQL)
                                                                                     .addScalar("TTL",IntegerType.INSTANCE);  
            Result = (Integer) setResultTransformer.uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }        
        return Result;
        
    }
    /**
     * Metodo encargado de regresar una lista de agremiados segun su estatus  y su contratista
     * @param statusAgremiado
     * @param contratista
     * @return List Agremiado
     */
    @Override
    public List<Agremiado> getAgremiados(StatusAgremiado statusAgremiado, Contratista contratista) { 
    //Se instancia una lista de tipo Agremiado
    List<Agremiado> agremiados = new ArrayList<>();
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //Se obtiene la sesión hibernate
        Transaction tx = session.beginTransaction(); //Se inicia la petición
        try{
            //Se realizan los criterios empezando desde la relación mapeada en Agremiado y se le asigna un alias "ag"
            Criteria createCriteria = session.createCriteria(Agremiado.class, "ag")
            // Se mapea la relación con DatosLaborales por la propiedad manejada en el pojoh Agremiado = "datosLaborales"
            .setFetchMode("ag.datosLaborales", FetchMode.JOIN)
            //Se crea un alias para la relación de Agremiado y DatosLaborales = "dl"
            .createAlias("ag.datosLaborales", "dl")
            //Se mapea la relación con Contratista desde la relación mapeada en DatosLaborales 
            .setFetchMode("dl.contratistaObj", FetchMode.JOIN)
            //Se crea el alias para la relación DatosLaborales y Contratista = "cn"
            .createAlias("dl.contratistaObj", "cn")
            //Se agrega la restricción del statusAgremiado del pojoh Agremiado que sea igual al parametro statusAgremiado
            .add(Restrictions.eq("ag.statusAgremiado", statusAgremiado.getIdStatusAgremiado()))
             //Se agrega la restricción del idContratista del pojoh Contratista que sea igual al parametro idContratista
            .add(Restrictions.eq("cn.idContratista", contratista.getIdContratista()));
            //Se orden los criterios encontrados por el idAgremiado por pojoh Agremiado
            createCriteria.addOrder(Order.asc("ag.idAgremiado"));
            //Se ejecuta la sentencia con los criterios ingresados, y se almacena el resultado en la variable agremiados
            agremiados = createCriteria.list();
        } catch (HibernateException he){ // Si captura algo entra a la Excepción Hibernate
            LOGGER.error("CLASS: AgremiadoDaoImpl - Error en la capa de acceso a datos", he);  // Se imprime el log del msj de error + variable Hibernate
        } finally{ //De cualquier manera entra
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return agremiados; //Se regresan los Agremiados que cumplen con los criterios establecidos
    }
    /**
     * Metodo encargado de regresas una lista de agremiados segun su estatus, cliente y contratista
     * @param statusAgremiado
     * @param idCliente
     * @param idContratista
     * @return List Agremiado
     */
    @Override
    public List<Agremiado> getAgremiados(StatusAgremiado statusAgremiado, Contratista contratista, Cliente cliente) {
        List<Agremiado> agremiados = new ArrayList<>();//Se instancia una lista de tipo Agremiado
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //Se obtiene la sesión hibernate
        Transaction tx = session.beginTransaction(); //Se inicia la petición
        try{
            //Se realizan los criterios empezando desde la relación mapeada en Agremiado y se le asigna un alias "ag"
            Criteria createCriteria = session.createCriteria(Agremiado.class, "ag")
            // Se mapea la relación con DatosLaborales por la propiedad manejada en el pojoh Agremiado = "datosLaborales"
            .setFetchMode("ag.datosLaborales", FetchMode.JOIN) 
            //Se crea un alias para la relación de Agremiado y DatosLaborales = "dl"
            .createAlias("ag.datosLaborales", "dl") 
            //Se mapea la relación con Contratista desde la relación mapeada en DatosLaborales 
            .setFetchMode("dl.contratistaObj", FetchMode.JOIN)
            //Se crea el alias para la relación DatosLaborales y Contratista = "cn"
            .createAlias("dl.contratistaObj", "cn")
            //Se mapea la relación con Cliente desde la relación mapeada en DatosLaborales 
            .setFetchMode("dl.clienteObj", FetchMode.JOIN)
            //Se crea el alias para la relación DatosLaborales y Contratista = "cn"
            .createAlias("dl.clienteObj", "cl")
            //Se agrega la restricción del statusAgremiado del pojoh Agremiado que sea igual al parametro statusAgremiado
            .add(Restrictions.eq("ag.statusAgremiado", statusAgremiado.getIdStatusAgremiado()))
            //Se agrega la restricción del idCliente del pojoh Cliente que sea igual al parametro idCliente
            .add(Restrictions.eq("cl.idCliente", cliente.getIdCliente()))
            //Se agrega la restricción del idContratista del pojoh Contratista que sea igual al parametro idContratista
            .add(Restrictions.eq("cn.idContratista", contratista.getIdContratista()));
            //Se orden los criterios encontrados por el idAgremiado por pojoh Agremiado
            createCriteria.addOrder(Order.asc("ag.idAgremiado"));
            //Se ejecuta la sentencia con los criterios ingresados, y se almacena el resultado en la variable agremiados
            agremiados = createCriteria.list();
        } catch (HibernateException he){ // Si captura algo entra a la Excepción Hibernate
            LOGGER.error("CLASS: AgremiadoDaoImpl - Error en la capa de acceso a datos", he); // Se imprime el log del msj de error + variable Hibernate
        } finally{ //De cualquier manera entra
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return agremiados; //Se regresan los Agremiados que cumplen con los criterios establecidos
    }
    /**
     * Metodo encargado de regresas una lista de agremiados segun su estatus y cliente
     * @param statusAgremiado
     * @param cliente
     * @return List Agremiado
     */
    @Override
    public List<Agremiado> getAgremiados(StatusAgremiado statusAgremiado, Cliente cliente) {
        List<Agremiado> agremiados = new ArrayList<>();//Se instancia una lista de tipo Agremiado
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //Se obtiene la sesión hibernate
        Transaction tx = session.beginTransaction(); //Se inicia la petición
        try{
            //Se realizan los criterios empezando desde la relación mapeada en Agremiado y se le asigna un alias "ag"
            Criteria createCriteria = session.createCriteria(Agremiado.class, "ag")
            // Se mapea la relación con DatosLaborales por la propiedad manejada en el pojoh Agremiado = "datosLaborales"
            .setFetchMode("ag.datosLaborales", FetchMode.JOIN) 
            //Se crea un alias para la relación de Agremiado y DatosLaborales = "dl"
            .createAlias("ag.datosLaborales", "dl")
            //Se mapea la relación con Cliente desde la relación mapeada en DatosLaborales 
            .setFetchMode("dl.clienteObj", FetchMode.JOIN)
            //Se crea el alias para la relación DatosLaborales y Contratista = "cn"
            .createAlias("dl.clienteObj", "cl")
            //Se agrega la restricción del statusAgremiado del pojoh Agremiado que sea igual al parametro statusAgremiado
            .add(Restrictions.eq("ag.statusAgremiado", statusAgremiado.getIdStatusAgremiado()))
            //Se agrega la restricción del idCliente del pojoh Cliente que sea igual al parametro idCliente
            .add(Restrictions.eq("cl.idCliente", cliente.getIdCliente()));
            //Se orden los criterios encontrados por el idAgremiado por pojoh Agremiado
            createCriteria.addOrder(Order.asc("ag.idAgremiado"));
            //Se ejecuta la sentencia con los criterios ingresados, y se almacena el resultado en la variable agremiados
            agremiados = createCriteria.list();
        } catch (HibernateException he){ // Si captura algo entra a la Excepción Hibernate
            LOGGER.error("CLASS: AgremiadoDaoImpl - Error en la capa de acceso a datos", he); // Se imprime el log del msj de error + variable Hibernate
        } finally{ //De cualquier manera entra
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return agremiados; //Se regresan los Agremiados que cumplen con los criterios establecidos
    }
/**
     * Metodo encargado de regresar una lista de tipo EsquemaCampo y recibe como parametro una dato de tipo EsquemaPago
     * @param esquemaPago
     * @return List EsquemaCampo
     */
    @Override
    public List<EsquemaCampo> getCamposDelEsquema(EsquemaPago esquemaPago) { 
        List<EsquemaCampo> esquemaCampo = null; //Se declara una lista de tipo EsquemaCampo
        HibernateUtl.buildSessionFactory(); //Se construye la sesión Hibernate
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); //Se obtiene la sesión hibernate
        Transaction tx = session.beginTransaction(); //Se inicia la petición
        try {
            // Se ejecuta la NamedQuery del pojoh EsquemaCampo llamada findByEsquemaPago asignandole el parametro solicitado para después elistarlo
            esquemaCampo = session.getNamedQuery("EsquemaCampo.findByEsquemaPago").setEntity("esquemaPago", esquemaPago).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) { // Si captura algo entra a la Excepción Hibernate
            LOGGER.error("CLASS: AgremiadoDaoImpl - Error en la capa de acceso a datos", he); // Se imprime el log del msj de error + variable Hibernate
        } finally { //De cualquier manera entra
            HibernateUtl.closeSessionAndUnbindFromThread(); //Se cierra la sesión Hibernate
        }
        return esquemaCampo; //Se regresan los esquemas de campo que cumplen con los criterios establecidos
    }
    /**
    * Metodo encargado de regresar todos los esquemas de pago
    * @return List EsquemaPago
    */
    @Override
    public List<EsquemaPago> getEsquemaDePagos() {
        List<EsquemaPago> esquemaPago = new ArrayList<>(); 
        HibernateUtl.buildSessionFactory(); 
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); 
        Transaction tx = session.beginTransaction(); 
        try {
            //Se toma la NamedQuery para ejecutarla
            esquemaPago = session.getNamedQuery("EsquemaPago.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) { 
            LOGGER.error("CLASS: AgremiadoDaoImpl - Error en la capa de acceso a datos", he);
        } finally { 
            HibernateUtl.closeSessionAndUnbindFromThread(); 
        }
        return esquemaPago; //Se devuelven los resultados en una lista de tipo EsquemaPago
    }
    /**
     * Metodo encargado de regresar una lista de TipoDocumento en base a su modulo y esquema de pago
     * @param modulo
     * @param ep
     * @return List TipoDocumento
     */
    @Override
    public List<TipoDocumento> getTipoDocumentoNoServicios(Short modulo, Short ep) {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>(); //Se declará una lista de tipo TipoDocumento
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            //Se declara una variable de tipo Criteria iniciando en el pojoh TipoDocumento
            Criteria createCriteria = session.createCriteria(TipoDocumento.class, "td") 
                    // Se mapea la relación de TipoDocumento con EsquemaDePago
                    .setFetchMode("td.esquemaPagos", FetchMode.JOIN)
                    // Se crea un alias para la relación de Agremiado y DatosLaborales = "dl"
                    .createAlias("td.esquemaPagos", "ep")
                    .setFetchMode("td.catalogoDocumentalObj", FetchMode.JOIN)
                    .createAlias("td.catalogoDocumentalObj", "cdo")
                    //Se agrega la restricción moduloObj del pojoh TipoDocumento, sea igual al parametro modulo
                    .add(Restrictions.eq("td.moduloObj", modulo))
                    //Se agrega la restricción idEsquemaPago del pojoh EsquemaDePago, sea igual al parametro ep
                    .add(Restrictions.eq("ep.idEsquemaPago", ep))
                    .add(Restrictions.eq("cdo.servicio", false))
                    //Muestra los distintos de los resultados
                    .setResultTransformer (Criteria.DISTINCT_ROOT_ENTITY);
                tipoDocumentos = createCriteria.list(); // Se ejecuta la sentencia con los criterios ingresados, y se almacena el resultado en la variable tipoDocumentos
        } catch (HibernateException he) {
            LOGGER.error("CLASS: AgremiadoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDocumentos; //Se devuelven los TipoDocumento que cumplen con los criterios establecidos
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoServiciosByContrato(Short modulo, Short ep, Integer idCE) {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>(); //Se declará una lista de tipo TipoDocumento
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            //Se declara una variable de tipo Criteria iniciando en el pojoh TipoDocumento
            Criteria createCriteria = session.createCriteria(TipoDocumento.class, "td") 
                    // Se mapea la relación de TipoDocumento con EsquemaDePago
                    .setFetchMode("td.esquemaPagos", FetchMode.JOIN)
                    // Se crea un alias para la relación de Agremiado y DatosLaborales = "dl"
                    .createAlias("td.esquemaPagos", "ep")
                    .setFetchMode("td.catalogoDocumentalObj", FetchMode.JOIN)
                    .createAlias("td.catalogoDocumentalObj", "cdo")
                    .setFetchMode("cdo.contratoEmpresasList", FetchMode.JOIN)
                    .createAlias("cdo.contratoEmpresasList", "ce")
                    //Se agrega la restricción moduloObj del pojoh TipoDocumento, sea igual al parametro modulo
                    .add(Restrictions.eq("td.moduloObj", modulo))
                    //Se agrega la restricción idEsquemaPago del pojoh EsquemaDePago, sea igual al parametro ep
                    .add(Restrictions.eq("ep.idEsquemaPago", ep))
                    .add(Restrictions.eq("cdo.servicio", true))
                    .add(Restrictions.eq("ce.idContratoEmpresas", idCE))
                    //Muestra los distintos de los resultados
                    .setResultTransformer (Criteria.DISTINCT_ROOT_ENTITY);      
                tipoDocumentos = createCriteria.list(); // Se ejecuta la sentencia con los criterios ingresados, y se almacena el resultado en la variable tipoDocumentos
        } catch (HibernateException he) {
            LOGGER.error("CLASS: AgremiadoDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return tipoDocumentos; //Se devuelven los TipoDocumento que cumplen con los criterios establecidos
    
    }

    @Override
    public List<TipoDocumento> getTipoDocumentoFA(String nombreDocumento) {
        List<TipoDocumento> tipoDocumento = null; 
        HibernateUtl.buildSessionFactory(); 
        Session session = HibernateUtl.getSessionFactory().getCurrentSession(); 
        Transaction tx = session.beginTransaction(); 
        try {
            tipoDocumento = session.getNamedQuery("TipoDocumento.findByNombreDocumento").setString("nombreDocumento", nombreDocumento).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) { 
            LOGGER.error("CLASS: AgremiadoDaoImpl - Error en la capa de acceso a datos", he);
        } finally { 
            HibernateUtl.closeSessionAndUnbindFromThread(); 
        }
        return tipoDocumento; 
    }

    @Override
    public List<Documento> getDocumentoFA(Agremiado agremiado, TipoDocumento tipoDocumento ) {
        List<Documento> documento = null;
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            documento = session.getNamedQuery("Documento.findByAgremiadoTipoDocumento").setInteger("agremiado", agremiado.getIdAgremiado()).setInteger("tipoDocumento", tipoDocumento.getIdTipoDocumento()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return documento;
    }

    @Override
    public Integer getFondoAhorroDisponible(String nombreContrato) {
        int flag = 0;
        String sql = "SELECT if(rcc.catalogo_documental is null, 0, 1) from contrato_empresas ce "
                + "left join relacion_catalogo_contrato rcc on rcc.id_contrato_empresas = ce.id_contrato_empresas and rcc.catalogo_documental=5 "
                + "where ce.nombre_contrato = '" + nombreContrato
                + "'";
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            flag = ((Number) session.createSQLQuery(sql).uniqueResult()).intValue();
            
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos --> "+he.getMessage(), he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return flag;
    }

    @Override
    public DatosPersonales getifExistsCurp(String curp) {
        DatosPersonales datosPersonales = new DatosPersonales();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Short idStatus = 8;
        try {
            Criteria criteria = session.createCriteria(DatosPersonales.class, "dp")
                    .setFetchMode("dp.agremiadoObj", FetchMode.JOIN)
                    .createAlias("dp.agremiadoObj", "ag");
            criteria.add(Restrictions.eq("curp", curp))
                    .add(Restrictions.ne("ag.statusAgremiado", idStatus));
            datosPersonales = (DatosPersonales) criteria.uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return datosPersonales;
    }

    @Override
    public DatosPersonales getifExistsRFC(String rfc) {
        DatosPersonales datosPersonales = new DatosPersonales();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        Short idStatus = 8;
        try {
            Criteria criteria = session.createCriteria(DatosPersonales.class, "dp")
                    .setFetchMode("dp.agremiadoObj", FetchMode.JOIN)
                    .createAlias("dp.agremiadoObj", "ag");
            criteria.add(Restrictions.eq("rfc", rfc))
                    .add(Restrictions.ne("ag.statusAgremiado", idStatus));
            datosPersonales = (DatosPersonales) criteria.uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error(CONSOLE_MESSAGE+" - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return datosPersonales;
    }

}
