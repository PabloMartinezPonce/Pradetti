/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.UsuarioDao;
import com.pradettisanti.payroll.pojoh.Rol;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.Collection;
import javax.xml.bind.DatatypeConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    private static final Logger logger = Logger.getLogger(AuthenticationServiceImpl.class);

    @Override
    @SuppressWarnings("null")
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario user = new Usuario();
        String contrasena = "";
        Collection<? extends GrantedAuthority> authorities = null;
        try {
            user = getUser(correo);
            if (user == null) {
                throw new UsernameNotFoundException("Invalid username/password.");
            } else if(user.getStatus()){
                Rol rol = getRolUser(user.getRol());
                StringBuilder roles = new StringBuilder(rol.getNombre());

                rol.getPermisoList().stream().forEach((permiso) -> {
                    roles.append(",");
                    roles.append(permiso.getNombre());
                });
                authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles.toString());
                contrasena = new String(DatatypeConverter.parseBase64Binary(user.getContrasena()));
            }else{
                throw new UsernameNotFoundException("Invalid username/password.");            
            }
        } catch (UsernameNotFoundException e) {
            logger.error("OCURRIO UN ERROR AL CARGAR EL USUARIO POR NOMBRE DE USUARIO", e);
        }
        return new User(user.getCorreoElectronico(), contrasena, authorities);
    }

    public Rol getRolUser(Integer idRol) {
        return usuarioDao.getRol(idRol);
    }

    private Usuario getUser(String correo) {
        return usuarioDao.getUsuario(correo);
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

}
