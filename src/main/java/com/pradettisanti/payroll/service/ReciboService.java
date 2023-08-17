/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.ReciboSolicitado;
import com.pradettisanti.payroll.pojoh.Sindicato;
import java.util.Date;
import java.util.List;

public interface ReciboService {
 
    public void setRecibo( Recibo recibo );

    public void setRecibo( List<Recibo> recibo );
    
    public void setRegistrarEnvioDeRecibo(ReciboSolicitado reciboSolicitado); //firma que recibe un objeto de tipo ReciboSolicitado

    public Recibo getRecibo(Integer idRecibo);

    public List<Recibo> getRecibos( Cliente cliente, Date diaDeRegistro );

    public List<Recibo> getRecibosPeriodo( Cliente cliente, Date diaDeRegistroDesde , Date diaDeRegistroHasta );
 
    public List<Recibo> getRecibos( Agremiado agremiado );

    public List<Recibo> getRecibos( Agremiado agremiado, Date diaInicial, Date diaFinal );

    public List<Recibo> getRecibos( Cliente cliente );

    public List<Recibo> getRecibos( Cliente cliente, Date diaInicial, Date diaFinal );
  
    public List<Recibo> getRecibos( Sindicato sindicato );

    public List<Recibo> getRecibos( Sindicato sindicato, Date diaInicial, Date diaFinal );

    public List<Recibo> getRecibos( Agremiado agremiado, Cliente cliente );

    public List<Recibo> getRecibos( Agremiado agremiado, Cliente cliente, Date diaInicial, Date diaFinal );

    public List<Recibo> getRecibos( Agremiado agremiado, Sindicato sindicato );

    public List<Recibo> getRecibos( Agremiado agremiado, Sindicato sindicato, Date diaInicial, Date diaFinal );
    
    public Recibo getRecibo(Integer agremiado,  Cliente cliente, Date diaInicial, Date diaFinal );
    
    public int getRecibosRegistrados( Cliente cliente, Date diaInicial, Date diaFinal );
    
    public List<ReciboSolicitado> getReciboSolicitado (Cliente cliente, Date diaRegistroDesde, Date diaRegistroHasta); //firma que recibe cliente, diaRegistroDesde, diaRegistroHasta y devuelve una lista de tipo ReciboSolicitado
}
