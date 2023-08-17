/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Notificaciones;
import com.pradettisanti.payroll.pojoh.Permiso;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.List;

public interface UsuarioService {
   
    public void setUsuario( Usuario usuario );

    public void setRol(Rol rol);
    
    public void setNotificaciones(List<Notificaciones> notificaciones);
   
    public Usuario getUsuario(String email);
    
    public Usuario getUsuario( Usuario usuario );

    public List<Usuario> getUsuarios();

    public Rol getRol( Integer idRol);

    public List<Usuario> getUsuarios( Rol rol );

    public List<Usuario> getUsuarios( Cliente cliente );

    public List<Permiso> getPermisos( Rol rol );

    public List<Notificaciones> getNotificaciones();

    public List<Rol> getRoles();

    public List<Permiso> getPermisos();
}
