<%-- 
    Document   : contratistas
    Created on : 15/11/2016, 11:22:41 AM
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
 <title>Payroll - Contratistas</title></head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ================================================================== cuerpo de la página ========================================================================= -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-king" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Contratistas      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<%@include file="../fragmentos/menuContratistas.jsp" %>
<c:if test="${model.muestraTabla}" >
<h2 class="selectAction">Contratistas encontrados</h2>
<table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Id</th>
            <th>Contratista</th>
            <th class="hidden-xs">Representante legal</th>
            <th>RFC</th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
        </tr>
    </thead>
    <tbody>
        <sec:authorize access="hasAnyRole('Ver_Todo','Contratistas')">
        <c:forEach items="${model.constratistasEncontrados}" var="contratista">
        <tr>
            <td>${contratista.idContratista}</td>
            <td><b>${contratista.nombreEmpresa}</b></td>
            <td class="hidden-xs">${contratista.representateLegal}</td>
            <td>${contratista.rfc}</td>
            <td style="min-width: 80px;">
                <div class="btn-group btn-group-sm" role="group" aria-label="...">
                    <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Contratistas')">  
                        <button type="button" class="btn btn-default  redireccionar-td hidden-xs" value="${pageContext.request.contextPath}/contratista/editar-contratista/${contratista.idContratista}.htm" title="Editar">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </button>
                    </sec:authorize>
                        <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/contratista/${contratista.idContratista}/kardex.htm" title="Ver">
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                        </button>
                    <sec:authorize access="hasAnyRole('Ver_Todo','Contrato_Contratista_Cliente')">                     
                        <button  ${(contratista.status)?"":"disabled"} type="button" class="btn btn-default redireccionar-td hidden-xs" value="${pageContext.request.contextPath}/contratista/generar-nuevo-contrato/${contratista.idContratista}.htm" title="Generar nuevo contrato">
                            <span class="glyphicon glyphicon-book" aria-hidden="true"></span>
                        </button>   
                    </sec:authorize>
                </div>
            </td>
        </tr>
        </c:forEach>
        </sec:authorize>
    </tbody>
</table> 
</c:if>
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