<%-- 
    Document   : zonasSalariales
    Created on : 15/11/2016, 01:55:14 PM
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
<h2 class="selectAction">Zonas salariales existentes</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Zona</th>
                <th>Salario</th>
                <th>Año</th>
                <sec:authorize access="hasAnyRole('Editar_Zona_Salarial')">
                <th class="hidden-xs">&nbsp;Opciones&nbsp;</th>
                </sec:authorize>
            </tr>
        </thead>
        <tbody>
                     <sec:authorize access="hasAnyRole('Consultar_Zona_Salarial')">
            <c:forEach items="${model.zonas}" var="zona">
                <tr>
                    <td><b>${zona.zona}</b></td>
                    <td>$&nbsp;<b>${zona.salario}</b>&nbsp;MXN</td>
                    <td>${zona.anio}</td>
                    <td>
                        <div class="btn-group" role="group" aria-label="...">
                     <sec:authorize access="hasAnyRole('Editar_Zona_Salarial')">
                          <button type="button" class="btn btn-default redireccionar-td hidden-xs" value="${pageContext.request.contextPath}/sistema/editar-zona-salarial/${zona.idZonaSm}.htm" title="Editar">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                          </button>
                     </sec:authorize>
                        </div>
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