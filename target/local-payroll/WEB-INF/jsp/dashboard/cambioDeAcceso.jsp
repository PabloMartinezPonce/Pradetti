<%-- 
    Document   : cambioDeAcceso
    Created on : 15/11/2016, 04:34:03 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>
<%response.setHeader("pragma", "no-cache");              
 response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
 response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../fragmentos/cabecera.jsp" %>
 <title>Payroll</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-copyright-mark" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Payroll 
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">
<h2 class="selectAction">Cambio de contraseña</h2>
        <div class="row">
            <div class="col-xs-12"></div>
        </div>
<div class="col-xs-12 lnbrk"></div>
    
<div class="div">
<form name="" method="post" id="formActivarNotificaciones">
    <div class="col-xs-12 col-sm-4 col-sm-offset-4">
        <div class="input-group">
          <span class="input-group-addon">Contraseña actual</span>
          <input name="cntrsnCtl" required id="cntrsnCtlID" type="password"  class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-4 col-sm-offset-4">
        <div class="input-group">
          <span class="input-group-addon" id="hjs">Nueva contraseña</span>
          <input name="nvCntrsn" required id="nvCntrsnID" type="password"  class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-4 col-sm-offset-4">
        <div class="input-group">
          <span class="input-group-addon" id="hjs">Confirmar contraseña</span>
          <input name="cnfmrCntrs" required id="cnfmrCntrsID" type="password"  class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-4 col-sm-offset-4">
        <button type="submit" class="btn btn-block">Guardar</button>
    </div>
</form>
</div> 
<div class="col-xs-12 lnbrk"></div>    
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
            
    </body>
</html>