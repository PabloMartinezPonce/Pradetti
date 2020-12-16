/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.Contrato;
import com.pradettisanti.payroll.pojoh.ContratoEmpresas;
import com.pradettisanti.payroll.pojoh.SolicitudRenovacionContrato;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Map;

/**
 * Interfaz encargada de representar los servicios para el uso de los contratos generados dentro del sistema
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ContratosService {
    public ByteArrayOutputStream getNuevoContratoContratistaColaborador(Contratista contratista, Agremiado agremiado, Contrato contrato, String testigoColaborador, String OcupacionAg, String OrigenAg, String testigoCliente, String OcupacionCl, String OrigenCl, Date fechaDelContrato);
    public ByteArrayOutputStream getNuevoContratoContratistaColaborador(SolicitudRenovacionContrato renovacionContrato, Contratista contratista, Agremiado agremiado, Contrato contrato, String testigoColaborador, String OcupacionAg, String OrigenAg, String testigoCliente, String OcupacionCl, String OrigenCl);
    public ByteArrayOutputStream getNuevoContratoContratistaCliente(ContratoEmpresas contratoEmpresas);
    public Map<String,String> getDatosDeContratista();
    public Map<String,String> getDatosDelColaborador();
    public Map<String,String> getDatosContratoContratistaColaborador();
    public Map<String,String> getCamposEspecialesContratistaColaborador();
    public Map<String,String> getDatosDelContratista();
    public Map<String,String> getDatosDelContratante();
    public Map<String,String> getDatosDelContratoEntreEmpresas();
}
