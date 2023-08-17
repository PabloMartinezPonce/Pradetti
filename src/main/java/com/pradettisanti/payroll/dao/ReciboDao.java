/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.dao;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.ReciboSolicitado;
import com.pradettisanti.payroll.pojoh.Sindicato;
import java.util.Date;
import java.util.List;

/**
 *  Interfaz encargada de establecer el tipo de metodos de trabajo que debe de poseer la clase que requiera trabajar con  la clase  com.pradettisanti.payroll.pojoh.Recibo
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public interface ReciboDao {
    //Create
    public void setRecibo( Recibo recibo );
    public void setRecibo( List<Recibo> recibo );
    public void setRegistrarEnvioDeRecibo( ReciboSolicitado reciboSolicitado); // firma que recibe un objeto de tipo ReciboSolicitado
    //Read
    public Recibo getRecibo(Integer idRecibo);
    public List<Recibo> getRecibos( Agremiado agremiado );
    public List<Recibo> getRecibos( Agremiado agremiado, Date diaInicial, Date diaFinal );
    public List<Recibo> getRecibos( Cliente cliente );
    public List<Recibo> getRecibos( Cliente cliente, Date diaInicial, Date diaFinal );
    public List<Recibo> getRecibos( Cliente cliente, Date diaDeRegistro );
    public List<Recibo> getRecibosPeriodo( Cliente cliente, Date diaDeRegistroDesde , Date diaDeRegistroHasta );
    public List<Recibo> getRecibos( Sindicato sindicato );
    public List<Recibo> getRecibos( Sindicato sindicato, Date diaInicial, Date diaFinal );
    public List<Recibo> getRecibos( Agremiado agremiado, Cliente cliente );
    public List<Recibo> getRecibos( Agremiado agremiado, Cliente cliente, Date diaInicial, Date diaFinal );
    public List<Recibo> getRecibos( Agremiado agremiado, Sindicato sindicato );
    public List<Recibo> getRecibos( Agremiado agremiado, Sindicato sindicato, Date diaInicial, Date diaFinal );
    public Recibo getRecibo(Integer agremiado,  Cliente cliente, Date diaInicial, Date diaFinal );
    public int getRecibosRegistrados( Cliente cliente, Date diaInicial, Date diaFinal );
    public List<ReciboSolicitado> getRecibosSolicitados(Cliente cliente, Date diaRegistroDesde, Date diaRegistroHasta); //Firma que recibe como parametros un cliente, dia desde, dia hasta y regresa una Lista de tipo ReciboSolicitado
    //Update
    //Delete
}
