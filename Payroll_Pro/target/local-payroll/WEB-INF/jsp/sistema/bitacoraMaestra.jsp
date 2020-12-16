<%-- 
    Document   : bitacoraMaestra
    Created on : 23/01/2017, 06:26:03 PM
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
<h2 class="selectAction">Bitacora maestra</h2>
<div class="col-xs-12 lnbrk"></div>
<form method="get" action="${pageContext.request.contextPath}/sistema/ver-bitacora.htm">
    <div class="col-xs-12 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="nmbrCmplt">Fecha desde</span>
          <input type="date" name="fchDsd" value="${model.desde}" required>
        </div>
    </div>
    <div class="col-xs-12 col-md-4">
        <div class="input-group">
          <span class="input-group-addon" id="nmbrCmplt">hasta</span>
          <input type="date" name="fchHst" value="${model.hasta}" required>
        </div>
    </div>
    <div class="col-xs-12 col-md-4">     
        <div class="btn-group " role="group" aria-label="...">                                  
            <button type="submit" class="btn btn-success btn-group btn-group-lg toolTip" title="Buscar">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
            </button>
        </div>
    </div>
</form>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Fecha del Movimiento</th>
                <th>Detalles</th>
                <th>Módulo</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${model.bitacora}" var="bitacora">
                    <tr>
                        <td><b><fmt:formatDate value="${bitacora.bitacoraPK.idFecha}" pattern="dd/MM/yyyy HH:mm" /></b></td>
                        <td><b>${bitacora.usuarioObj.nombre}.-</b>&nbsp;&nbsp;&nbsp;${bitacora.detalles}</td>
                        <td>${bitacora.modulo}</td>
                    </tr>
            </c:forEach>
        </tbody>
    </table> 
</div>  
<div class="col-xs-12 lnbrk"></div>   
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
            
    </body>
</html>