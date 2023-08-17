/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao.impl;

import com.pradettisanti.payroll.Utility.HibernateUtl;
import com.pradettisanti.payroll.dao.UsuarioDao;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Notificaciones;
import com.pradettisanti.payroll.pojoh.Permiso;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

/**
 * Clase que implementa los metodos de la interfaz UsuarioDao
 *
 * @author HEM 
 */
@Component
public class UsuarioDaoImpl implements UsuarioDao {

    private static final Logger LOGGER = Logger.getLogger(UsuarioDaoImpl.class);

    /**
     * Metodo que guarda/actualiza un usuario
     * @param usuario Instancia de Usuario
     */
    @Override
    public void setUsuario(Usuario usuario) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(usuario);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo encargado de ingresar/actualizar un Rol
     * @param rol Instancia de Rol
     */
    @Override
    public void setRol(Rol rol) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            session.saveOrUpdate(rol);
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo encargado de ingresar/actualizar las notificaciones
     * @param notificaciones  Lista de Intancias de notificaciones
     */
    @Override
    public void setNotificaciones(List<Notificaciones> notificaciones) {
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            for (Notificaciones notificacion : notificaciones) {
                session.saveOrUpdate(notificacion);
            }
            tx.commit();
        } catch (HibernateException he) {
            tx.rollback();
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
    }

    /**
     * Metodo que recibe un correo y lo busca dentro de la base de datos, devolviendo el usuario que corresponda con el correo
     * @param email Cadena de texto que contiene el correo electronico
     * @return Usuario
     */
    @Override
    public Usuario getUsuario(String email) {
        Usuario usu = new Usuario();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            usu = (Usuario) session.getNamedQuery("Usuario.findByCorreoElectronico").setString("correoElectronico", email).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return usu;
    }

    /**
     * Metodo que obtiene un usuario
     * @param usuario Intancia de Usuario
     * @return Usuario
     */
    @Override
    public Usuario getUsuario(Usuario usuario) {
        Usuario usu = new Usuario();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            usu = (Usuario) session.get(Usuario.class, usuario.getIdUsuario());
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return usu;
    }

    /**
     * Metodo que devuleve el Rol que corresponde con el id enviado
     * @param idRol Numero entero que corresponde con un id de Rol
     * @return Rol
     */
    @Override
    public Rol getRol(Integer idRol) {
        Rol rol = new Rol();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            rol = (Rol) session.getNamedQuery("Rol.findByIdRol").setInteger("idRol", idRol).setCacheMode(CacheMode.IGNORE).uniqueResult();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return rol;
    }

    /**
     * Metodo que obtiene una lista de usuarios
     * @param rol Instancia de Rol
     * @return List Usuario
     */
    @Override
    public List<Usuario> getUsuarios(Rol rol) {
        List<Usuario> usuarios = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            usuarios = session.getNamedQuery("Usuario.findByRol").setInteger("rol", rol.getIdRol()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return usuarios;
    }

    /**
     * Metodo que obtiene una lista de usuarios
     * @param cliente Instancia de Cliente
     * @return List Usuario
     */
    @Override
    public List<Usuario> getUsuarios(Cliente cliente) {
        List<Usuario> usuarios = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            usuarios = session.getNamedQuery("Usuario.findByCliente").setInteger("cliente", cliente.getIdCliente()).setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return usuarios;
    }

    /**
     * Metodo que devuelve todos los clientes que se tiene el la base de datos
     * @return List Usuario
     */
    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            usuarios = session.getNamedQuery("Usuario.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return usuarios;
    }

    /**
     * Metodo que obtiene una lista de permisos
     * @param rol Instancia de Rol
     * @return List Permiso
     */
    @Override
    public List<Permiso> getPermisos(Rol rol) {
        List<Permiso> permisos = new ArrayList<>();
        String sql = "select * FROM permiso "
                + "join rol_permiso on rol_permiso.permiso = permiso.id_permiso "
                + "where rol_permiso.rol = " + rol.getIdRol();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            permisos = session.createSQLQuery(sql).addEntity(Permiso.class).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return permisos;
    }

    /**
     * Metodo que obtiene una lista de notificaciones
     * @return List Notificaciones
     */
    @Override
    public List<Notificaciones> getNotificaciones() {
        List<Notificaciones> notificacioneses = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            notificacioneses = session.getNamedQuery("Notificaciones.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return notificacioneses;
    }

    /**
     * Metodo que obtiene una lista de roles
     * @return List Rol
     */
    @Override
    public List<Rol> getRoles() {
        List<Rol> roles = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            roles = session.getNamedQuery("Rol.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return roles;
    }

    /**
     * Metodo que obtiene una lista de permisos
     * @return List Permisos
     */
    @Override
    public List<Permiso> getPermisos() {
        List<Permiso> permisos = new ArrayList<>();
        HibernateUtl.buildSessionFactory();
        Session session = HibernateUtl.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            permisos = session.getNamedQuery("Permiso.findAll").setCacheMode(CacheMode.IGNORE).list();
        } catch (HibernateException he) {
            LOGGER.error("CLASS: UsuarioDaoImpl - Error en la capa de acceso a datos", he);
        } finally {
            HibernateUtl.closeSessionAndUnbindFromThread();
        }
        return permisos;
    }

}
