<%-- 
    Document   : roles
    Created on : 15/11/2016, 12:40:32 PM
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
<h2 class="selectAction">Roles existentes</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer"> 
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>&nbsp;&nbsp;Nombre&nbsp;del&nbsp;Rol&nbsp;&nbsp;</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Permisos&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                <th class="hidden-xs">&nbsp;&nbsp;Opciones&nbsp;&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <sec:authorize access="hasAnyRole('Consultar_Rol')">
               <c:forEach var="rol" items="${model.roles}">
               <tr>
                   <td>${rol.idRol}</td>
                   <td><b>${rol.nombre}</b></td>
                   <c:choose>
                       <c:when test="${rol.permisoList.isEmpty()}">
                           <td>Sin Permisos</td>
                       </c:when>
                       <c:otherwise>                        
                           <td>&#8226;
                               <c:forEach items="${rol.permisoList}" var="permiso">
                                   <span>${permiso.nombre}&nbsp;&#8226;</span>
                               </c:forEach>
                           </td>
                       </c:otherwise>                        
                   </c:choose>
                   <td class="hidden-xs">
            <sec:authorize access="hasAnyRole('Editar_Rol')">
                       <form method="post" action="${pageContext.request.contextPath}/sistema/edicion-de-rol.htm">
                         <input type="hidden" name="rol" value="${rol.idRol}">
                         <button type="submit" class="btn btn-default" title="Editar rol">
                           <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                         </button>
                       </form>
            </sec:authorize>
                   </td>
               </tr>
              </c:forEach>
            </sec:authorize>
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