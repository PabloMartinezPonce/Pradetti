<%-- 
    Document   : notificaciones
    Created on : 15/11/2016, 04:37:00 PM
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
    <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Payroll      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<h2 class="selectAction">Notificaciones</h2>
        <div class="row">
            <div class="col-xs-12"></div>
        </div>
<div class="col-xs-12 lnbrk"></div>
    
<div class="div">
<form name="" method="post" id="formActivarNotificaciones">
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="ltsSlcltds">Altas solicitadas</span>
          <input name="ltsSlcltds" id="ltsSlcltdsID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="bjsSlctds">Bajas solicitadas</span>
          <input name="bjsSlctds" id="bjsSlctdsID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="xpdntsCnBsrvcns">Expediente con observaciones</span>
          <input name="xpdntsCnBsrvcns" id="xpdntsCnBsrvcnsID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="xpdntsPrCmpltr">Expedientes por completar</span>
          <input name="xpdntsPrCmpltr" id="xpdntsPrCmpltrID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="ltXts">Alta exitosa</span>
          <input name="ltXts" id="ltXtsID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="crrncnsRlzds">Correciones realizadas</span>
          <input name="crrncnsRlzds" id="crrncnsRlzdsID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="bjsPrFnlizr">Bajas por finalizar</span>
          <input name="bjsPrFnlizr" id="bjsPrFnlizrID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="bjsPndnts">Bajas pendientes</span>
          <input name="bjsPndnts" id="bjsPndntsID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="bjPndntRchzd">Baja pendiente rechazada</span>
          <input name="bjPndntRchzd" id="bjPndntRchzdID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="slctdDBjRchzd">Solicitud de baja rechazada</span>
          <input name="slctdDBjRchzd" id="slctdDBjRchzdID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="slctdDBjXts">Solicitud de baja exitosa</span>
          <input name="slctdDBjXts" id="slctdDBjXtsID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="slctdDRnvcn">Solicitud de renovación</span>
          <input name="slctdDRnvcn" id="slctdDRnvcnID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="slctdRnvcnCptd">Solicitud renovación aceptada</span>
          <input name="slctdRnvcnCptd" id="slctdRnvcnCptdID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="ntfcacnsCntrtprVncr">Notificación contratos por vencer</span>
          <input name="ntfcacnsCntrtprVncr" id="ntfcacnsCntrtprVncrID" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
        </div>
    </div>
    <div class="col-xs-12 col-sm-6 col-md-4">
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