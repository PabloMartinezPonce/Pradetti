<%-- 
    Document   : miUsuario
    Created on : 1/02/2017, 11:22:42 AM
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
<h2 class="selectAction">Mi usuario</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <div class="col-xs-12 col-sm-8 col-sm-offset-2">
        <div class="row">
            <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon" id="">Nombre</span>
                        <label class="lblInputText">${model.usuario.nombre.toUpperCase()}</label>
                    </div>            
            </div>
            <div class="col-xs-12 lnbrk"></div>   
            <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon" id="">&nbsp;Correo&nbsp;</span>
                        <label class="lblInputText">${model.usuario.correoElectronico.toUpperCase()}</label>
                    </div>            
            </div>            
            <div class="col-xs-12 lnbrk"></div>   
            <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon" id="">&nbsp;&nbsp;&nbsp;&nbsp;Rol&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <label class="lblInputText">${model.rol.nombre.toUpperCase()}</label>
                    </div>            
            </div>
            <div class="col-xs-12 lnbrk"></div>   
        </div>
        <div class="row">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading myUser" role="tab" id="headingOne">
      <h4 class="panel-title ">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Clientes
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
          <ul class="list-group">
                <c:forEach items="${model.clientes}" var="cliente">
                      <li class="list-group-item ">${cliente.nombreEmpresa.toUpperCase()}</li>
                </c:forEach>
            </ul>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading myUser" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Permisos del rol
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
          <ul class="list-group">
                <c:forEach items="${model.rol.permisoList}" var="permiso">
                      <li class="list-group-item ">${permiso.nombre.toUpperCase()}</li>
                </c:forEach>    
            </ul>
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading myUser" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          Notificaciones activas
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
          <ul class="list-group">
                <c:forEach items="${model.usuario.notificacionesList}" var="notificacion">
                      <li class="list-group-item ">${notificacion.descripcion.toUpperCase()}</li>
                </c:forEach>    
            </ul>
      </div>
    </div>
  </div>
</div>
        </div>
    </div>
</div>
<div class="col-xs-12 lnbrk"></div>   
</div>
<!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
            
    </body>
</html>