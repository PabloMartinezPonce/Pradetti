/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.Utility;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase auxiliar encargada de generar una query mas especifica del contrato
 * @author PabloSagoz pablo.samperio@it-seekers.com
 * @version 1
 * @since 26/02/2018
 */
public class DelContrato implements  Serializable{
    private int idContratoEmpresas;
    private String nombreContrato;
    private Date fechaContrato;
    private int idContratista;
    private String contratista;
    private int idCliente;
    private String cliente;

    public int getIdContratoEmpresas() {
        return idContratoEmpresas;
    }

    public void setIdContratoEmpresas(int idContratoEmpresas) {
        this.idContratoEmpresas = idContratoEmpresas;
    }

    public String getNombreContrato() {
        return nombreContrato;
    }

    public void setNombreContrato(String nombreContrato) {
        this.nombreContrato = nombreContrato;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public int getIdContratista() {
        return idContratista;
    }

    public void setIdContratista(int idContratista) {
        this.idContratista = idContratista;
    }

    public String getContratista() {
        return contratista;
    }

    public void setContratista(String contratista) {
        this.contratista = contratista;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "DelContrato{" + "idContratoEmpresas=" + idContratoEmpresas + ", nombreContrato=" + nombreContrato + ", fechaContrato=" + fechaContrato + ", idContratista=" + idContratista + ", contratista=" + contratista + ", idCliente=" + idCliente + ", cliente=" + cliente + '}';
    }
}
