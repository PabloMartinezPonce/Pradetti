/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.Usuario;
import java.util.Date;
import java.util.List;

/**
 * Interfaz encargada de representar los servicios para las notificaciones dentro del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface NotificacionesService {
    public void altasSolicitada(Agremiado colaborador,Date fechaAlta);
    public void altaEnProceso(Agremiado colaborador,Date fechaAlta);
    public void solicitudFondoAhorro(Agremiado colaborador,Date fechaAlta);
    public void bajasSolicitada(Agremiado colaborador, Date fechaBaja);
    public void expedienteConObservaciones(Agremiado  colaborador);
    public void expedientePorCompletar(Agremiado colaborador,Date fechaAlta);
    public void bajaSinFirmar(Agremiado colaborador,Date fechaAlta);
    public void altaExitosa(Agremiado colaborador,Date fechaAlta);
    public void correccionesRealizadas(Agremiado colaborador,Date fechaAlta);
    public void bajaPorFinalizar(Agremiado  colaborador);
    public void bajaPendiente(Agremiado  colaborador);
    public void bajaPendienteRechazada(Agremiado  colaborador);
    public void bajaPorFinalizarRechazada(Agremiado  colaborador);
    public void solicitudDeBajaRechazada(Agremiado  colaborador);
    public void solicitudDeBajaExitosa(Agremiado  colaborador, Date fechaBaja);
    public void solicitudDeRenovacion(Agremiado  colaborador, Date fechaRenovacion);
    public void solicitudDeRenovacionAceptada(Agremiado  colaborador);
    public void solicitudDeRenovacionRechazada(Agremiado  colaborador,String motivo);
    public void contratoPorVencer(Agremiado  colaborador);
    public void cambioDeContrasena(Usuario usuario, boolean propia);
    public void reciboDeNomina(Recibo recibo, Usuario usuario); //Se actualiza firma para que reciba usuario de tipo Usuario
    public void reciboDeNomina(List<Recibo> recibos, Usuario usuario); //Se actualiza firma para que reciba usuario de tipo Usuario
    public boolean reciboDeNominaZIp(String correo, List<Recibo> recibos, Cliente cliente,  String Desde , String Hasta); //Se actualiza firma para que devuelva un boolean
    public boolean reciboDeNomina(String correo, List<Recibo> recibos, Cliente cliente,  String Desde , String Hasta); //Se actualiza firma para que devuelva un boolean
    public void nuevoUsuario(Usuario usuario);
    public void rechazoDuranteAlta(Agremiado colaborador, String origenDeRechazo, String notificacion);
}
