/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *  Manager encargado de contener las primeras peticiones web
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@SuppressWarnings("Convert2Diamond")
public class SessionController {

    @RequestMapping(value = "accesoDenegado.htm", method = RequestMethod.GET)
    public ModelAndView showDeniedLoggin() {
        return new ModelAndView("accesoDenegado");
    }
    
    @RequestMapping(value = "inicioSesion.htm", method = RequestMethod.GET)
    public ModelAndView showLoggin() {
        return new ModelAndView("inicioSesion");
    }

    @SuppressWarnings("UnnecessaryUnboxing")
    @RequestMapping(value = "index.htm", method = RequestMethod.GET)
    public ModelAndView show(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("index");      
    }
}
