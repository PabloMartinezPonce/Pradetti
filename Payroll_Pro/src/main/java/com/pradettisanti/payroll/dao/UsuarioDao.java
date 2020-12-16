/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Notificaciones;
import com.pradettisanti.payroll.pojoh.Permiso;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.List;

/**
 *  Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Usuario
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface UsuarioDao {
    //Create
    public void setUsuario( Usuario usuario );
    public void setRol(Rol rol);
    public void setNotificaciones(List<Notificaciones> notificaciones);
    //Read
    public Usuario getUsuario(String email);
    public Usuario getUsuario( Usuario usuario );
    public Rol getRol( Integer idRol );
    public List<Usuario> getUsuarios( Rol rol );
    public List<Usuario> getUsuarios( Cliente cliente );
    public List<Usuario> getUsuarios();
    public List<Permiso> getPermisos( Rol rol );
    public List<Notificaciones> getNotificaciones();
    public List<Rol> getRoles();
    public List<Permiso> getPermisos();
    //Update
    //Delete
}
