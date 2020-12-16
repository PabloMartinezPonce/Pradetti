/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.Utility;

/**
 * Clase auxiliar empleada en AgremiadoDaoImpl
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public class StatusQuery {
    private int ID;
    private String ST;
    private int TTL;

    @Override
    public String toString() {
        return "StatusQuery{" + "ID=" + ID + ", ST=" + ST + ", TTL=" + TTL + '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public int getTTL() {
        return TTL;
    }

    public void setTTL(int TTL) {
        this.TTL = TTL;
    }

}
