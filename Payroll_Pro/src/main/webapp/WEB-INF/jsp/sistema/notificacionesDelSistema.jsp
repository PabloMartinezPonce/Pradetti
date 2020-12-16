<%-- 
    Document   : notificacionesDelSistema
    Created on : 1/02/2017, 01:17:58 PM
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
<title>Payroll - Sistema</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-knight" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sistema      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">  
<%@include file="../fragmentos/menuSistema.jsp" %>
<div class="col-xs-12 lnbrk"></div>
<h2 class="selectAction">Notificaciones del sistema</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
  <div class="col-xs-12 lnbrk"></div>
  <div class="row">
      <div class="col-xs-12 col-sm-6 col-sm-offset-3">
          <form name="" method="post" id="formActivarNotificaciones" action="${pageContext.request.contextPath}/sistema/mis-notificaciones.htm">
                            <div class="row">
                                <div class="col-xs-6"><h4 class="titulo-h4">Notificaciones disponibles</h4></div>
                                <div class="col-xs-6"><h4 class="titulo-h4">Notificaciones activas</h4></div>
                                <div class="col-xs-5">
                                    <div class="containerListPermisos">
                                        <select name="notificacionesDisponibles" id="origenPermisos" multiple="multiple" size="${model.permisos.size()}">
                                            <c:forEach items="${model.notificacionesDisponibles}"  var="notificacionD">
                                                <option value="${notificacionD.idNotificaciones}">${notificacionD.descripcion}</option>                            
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-xs-2 centerMiddle" title="Pasar todas las notificaciones">
                                    <h2><span class="glyphicon glyphicon-transfer" aria-hidden="true"></span></h2> 
                                </div>
                                <div class="col-xs-5">
                                    <div class="containerListPermisos">   
                                        <select required name="notificacionesActivas" id="destinoPermisos" multiple="multiple" size="7">
                                            <c:forEach items="${model.notificacionesActivas}"  var="notificacionA">
                                                <option value="${notificacionA.idNotificaciones}">${notificacionA.descripcion}</option>                            
                                            </c:forEach></select>
                                    </div>
                                </div>
                            </div>
                        <div class="col-xs-12 lnbrk"></div>  
                        <div class="col-xs-5 col-xs-offset-3">
                           <button id="selectAllOptions" type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                <b>Guardar</b>
                            </button>
                        </div>
                    </form>
          </div>                    
</div>
<div class="col-xs-12 lnbrk"></div>  
</div>
<div class="col-xs-12 lnbrk"></div>   
</div>
<!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
            
    </body>
</html>
