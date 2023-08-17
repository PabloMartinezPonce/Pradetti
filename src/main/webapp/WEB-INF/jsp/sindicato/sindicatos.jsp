<%-- 
    Document   : sindicatos
    Created on : 15/11/2016, 11:50:04 AM
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
 <title>Payroll - Sindicatos</title>
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-bishop" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sindicatos       
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<%@include file="../fragmentos/menuSindicatos.jsp" %>
<div class="col-xs-12 lnbrk"></div>
<c:if test="${model.muestraTabla}">
<h2 class="selectAction">Sindicatos encontrados</h2>             
<table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>ID</th>
            <th>Acrónimo</th>
            <th>RFC</th>
            <th>&nbsp;&nbsp;Opciones&nbsp;&nbsp;</th>
        </tr>
    </thead>
    <tbody>
        <sec:authorize access="hasAnyRole('Ver_Todo','Sindicatos')">
        <c:forEach items="${model.sindicatosEncontrados}" var="sindicatoObj">
            <tr>
                <td>${sindicatoObj.idSindicato}</td>
                <td><b>${sindicatoObj.nombreCorto}</b></td>
                <td>${sindicatoObj.rfc}</td>
                <td>
                    <div class="btn-group btn-group-sm" role="group" aria-label="...">
                        <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Sindicatos')">    
                            <button type="button" class="btn btn-default redireccionar-td hidden-xs" value="${pageContext.request.contextPath}/sindicato/editar-sindicato/${sindicatoObj.idSindicato}.htm" title="Editar">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </button>
                        </sec:authorize>
                            <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/sindicato/${sindicatoObj.idSindicato}/kardex.htm" title="Ver">
                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                            </button>                                              
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