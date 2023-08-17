/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.UsuarioDao;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Notificaciones;
import com.pradettisanti.payroll.pojoh.Permiso;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.Usuario;
import com.pradettisanti.payroll.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HEM 
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    UsuarioDao usuarioDao;
    
    @Override
    public void setUsuario(Usuario usuario) {
        usuarioDao.setUsuario(usuario);
    }

    @Override
    public void setRol(Rol rol) {
        usuarioDao.setRol(rol);
    }
    
    
    @Override
    public void setNotificaciones(List<Notificaciones> notificaciones) {
        usuarioDao.setNotificaciones(notificaciones);
    }
    
    @Override
    public Usuario getUsuario(String email) {
        return usuarioDao.getUsuario(email);
    }
    
    @Override
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.getUsuario(usuario);
    }
    
    @Override
    public Rol getRol(Integer idRol){
        return usuarioDao.getRol(idRol);
    }
    
    @Override
    public List<Usuario> getUsuarios(Rol rol) {
        return usuarioDao.getUsuarios(rol);
    }

    @Override
    public List<Usuario> getUsuarios(Cliente cliente) {
        return usuarioDao.getUsuarios(cliente);
    }

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }
    
    @Override
    public List<Permiso> getPermisos(Rol rol) {
        return usuarioDao.getPermisos(rol);
    }

    @Override
    public List<Notificaciones> getNotificaciones() {
        return usuarioDao.getNotificaciones();
    }

    @Override
    public List<Rol> getRoles() {
        return usuarioDao.getRoles();
    }

    @Override
    public List<Permiso> getPermisos() {
        return usuarioDao.getPermisos();
    }
    
}
