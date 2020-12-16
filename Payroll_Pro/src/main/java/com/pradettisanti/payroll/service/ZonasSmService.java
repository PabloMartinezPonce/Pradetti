/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.service;

import com.pradettisanti.payroll.pojoh.ZonaSm;
import java.util.List;

public interface ZonasSmService {

    public Short setZonaSM( ZonaSm zonaSm );
    
    public ZonaSm getZonaSM(Short id);

    public List<ZonaSm> getZonaSm();

    public List<ZonaSm> getZonaSm( ZonaSm zonaSm ); 
}
