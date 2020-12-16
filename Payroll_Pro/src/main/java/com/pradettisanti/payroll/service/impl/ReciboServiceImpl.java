/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service.impl;

import com.pradettisanti.payroll.dao.ReciboDao;
import com.pradettisanti.payroll.pojoh.Agremiado;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Recibo;
import com.pradettisanti.payroll.pojoh.ReciboSolicitado;
import com.pradettisanti.payroll.pojoh.Sindicato;
import com.pradettisanti.payroll.service.ReciboService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HEM 
 */
@Service
public class ReciboServiceImpl implements ReciboService{
    
    @Autowired
    private ReciboDao reciboDao;
    
    @Override
    public void setRecibo(Recibo recibo) {
        reciboDao.setRecibo(recibo);
    }

    @Override
    public void setRecibo(List<Recibo> recibo) {
         reciboDao.setRecibo(recibo);
    }
    
    @Override
    public Recibo getRecibo( Integer idRecibo){
        return  reciboDao.getRecibo(idRecibo);
    }
            
    @Override
    public List<Recibo> getRecibos(Agremiado agremiado) {
        return reciboDao.getRecibos(agremiado);
    }

    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Date diaInicial, Date diaFinal) {
        return reciboDao.getRecibos(agremiado, diaInicial, diaFinal);
    }

    @Override
    public List<Recibo> getRecibos(Cliente cliente) {
        return reciboDao.getRecibos(cliente);
    }

    @Override
    public List<Recibo> getRecibos(Cliente cliente, Date diaDeRegistro) {
        return  reciboDao.getRecibos(cliente, diaDeRegistro);
    }
    
    @Override
    public List<Recibo> getRecibosPeriodo( Cliente cliente, Date diaDeRegistroDesde , Date diaDeRegistroHasta){
        return reciboDao.getRecibosPeriodo(cliente, diaDeRegistroDesde, diaDeRegistroHasta);
    }
    
    @Override
    public List<Recibo> getRecibos(Cliente cliente, Date diaInicial, Date diaFinal) {
        return reciboDao.getRecibos(cliente, diaInicial, diaFinal);
    }

    @Override
    public List<Recibo> getRecibos(Sindicato sindicato) {
        return reciboDao.getRecibos(sindicato);
    }

    @Override
    public List<Recibo> getRecibos(Sindicato sindicato, Date diaInicial, Date diaFinal) {
        return reciboDao.getRecibos(sindicato, diaInicial, diaFinal);
    }

    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Cliente cliente) {
        return reciboDao.getRecibos(agremiado, cliente);
    }

    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Cliente cliente, Date diaInicial, Date diaFinal) {
        return reciboDao.getRecibos(agremiado, cliente, diaInicial, diaFinal);
    }

    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Sindicato sindicato) {
        return reciboDao.getRecibos(agremiado, sindicato);
    }

    @Override
    public List<Recibo> getRecibos(Agremiado agremiado, Sindicato sindicato, Date diaInicial, Date diaFinal) {
        return reciboDao.getRecibos(agremiado, sindicato, diaInicial, diaFinal);
    }

    @Override
    public Recibo getRecibo(Integer agremiado, Cliente cliente, Date diaInicial, Date diaFinal) {
        return reciboDao.getRecibo(agremiado, cliente, diaInicial, diaFinal);
    }

    @Override
    public int getRecibosRegistrados(Cliente cliente, Date diaInicial, Date diaFinal) {
        return reciboDao.getRecibosRegistrados(cliente, diaInicial, diaFinal);
    }

    @Override
    public List<ReciboSolicitado> getReciboSolicitado(Cliente cliente, Date diaRegistroDesde, Date diaRegistroHasta) { // Metodo que recibe objeto cliente, diadesde, diahasta y regresa una lista de ReciboSolicitado
        return reciboDao.getRecibosSolicitados(cliente, diaRegistroDesde, diaRegistroHasta); // llama el metodo getRecibosSolicitados del reciboDao mandandole cliente, diaRdesde, diaRhasta
    }

    @Override
    public void setRegistrarEnvioDeRecibo(ReciboSolicitado reciboSolicitado) { //Metodo que recibe un objeto ReciboSolicitado
        reciboDao.setRegistrarEnvioDeRecibo(reciboSolicitado); // llama al metodo setRegistrarEnvioDeRecibo del reciboDao mandandole un objeto de tipo ReciboSolicitado
    }
    
}
