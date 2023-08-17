/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.ActaNotarial;
import com.pradettisanti.payroll.pojoh.Cliente;
import com.pradettisanti.payroll.pojoh.Contratista;
import com.pradettisanti.payroll.pojoh.PoderLegal;
import com.pradettisanti.payroll.pojoh.TipoActa;
import java.util.List;

public interface ActaNotarialService {

    public Integer setActaNotarial( ActaNotarial actaNotarial );

    public void setActaNotarial( PoderLegal poderLegal );

    public ActaNotarial getActaNotarial( Integer idActaNotarial );

    public List<ActaNotarial> getActanotarial( ActaNotarial actaNotarial );

    public ActaNotarial getActanotarial( Cliente cliente );

    public ActaNotarial getActanotarial( Contratista contratista );

    public List<ActaNotarial> getActasNotariales();

    public List<ActaNotarial> getActasNotariales( TipoActa tipoActa );

    public TipoActa getTipoActa( Short idTipoActa );

    public List<TipoActa> getTipoActa();

}
