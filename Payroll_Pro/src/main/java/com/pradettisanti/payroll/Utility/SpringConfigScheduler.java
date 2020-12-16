/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradettisanti.payroll.Utility;

import com.pradettisanti.payroll.dao.AgremiadoDao;
import com.pradettisanti.payroll.pojoh.StatusAgremiado;
import java.util.List;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 *
 * @author HEM 
 */
@Configuration
@EnableScheduling
public class SpringConfigScheduler {
    @Autowired
    private AgremiadoDao agremiadoDao;
    
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SpringConfigScheduler.class);

    @Scheduled(cron = "0 0 1,5,10,15,20 * * ?")
    public void scheduleTaskUsingCronExpression() {
        try {
            long now = System.currentTimeMillis() / 1000;
            LOGGER.info("[Scheduler] Ejecutando proceso de conexión permanente {"+now+"}.");
            List<StatusAgremiado> statusAgremiado = agremiadoDao.getStatusAgremiado();
            LOGGER.info("[Scheduler] Ejecutando proceso de conexión finalizado en "+statusAgremiado+" estados.");
        } catch (JDBCConnectionException jdbcce) {
            LOGGER.error("[Scheduler] Ocurrio un problema durante la ejecución del proceso de conexión permanete -- jdbcce --> "+jdbcce.getMessage());
        } catch (Exception e) {
            LOGGER.error("[Scheduler] Ocurrio un problema durante la ejecución del proceso de conexión permanete --> "+e.getMessage(),e);
        }
        
    }

    /**
     * @return the agremiadoDao
     */
    public AgremiadoDao getAgremiadoDao() {
        return agremiadoDao;
    }

    /**
     * @param agremiadoDao the agremiadoDao to set
     */
    public void setAgremiadoDao(AgremiadoDao agremiadoDao) {
        this.agremiadoDao = agremiadoDao;
    }
}
