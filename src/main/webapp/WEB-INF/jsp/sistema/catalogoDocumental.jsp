<%-- 
    Document   : catalogoDocumental
    Created on : 28/12/2017, 01:42:04 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
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
<h2 class="selectAction">Catálogo Documental</h2>
<div class="col-xs-12 lnbrk"></div>
<sec:authorize access="hasAnyRole('Registrar_Editar_Catalogo_Documental')">
    <div class="row">
        <div class="col-xs-12">
            <a href="${pageContext.request.contextPath}/sistema/registro-documental.htm" class="btn btn-info" role="button">
                <span class="glyphicon glyphicon-file" aria-hidden="true"></span>&nbsp;Registrar un nuevo documento base
            </a>
        </div>
    </div>
</sec:authorize>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer"> 
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Documentos<br>Asociados</th>
                <th>Servicio</th>
                <th>Configurado</th>
                <th>Editar</th>
            </tr>
        </thead>
        <tbody>
            <sec:authorize access="hasAnyRole('Catalogo_Documental')">
                <c:forEach var="cd" items="${model.catalogoDocumental}">
                    <tr>
                        <th>${cd.idCatalogo}</th>
                        <th>${cd.nombreDocumento}</th>
                        <th>${cd.tipoDocumentosList.size()}</th>
                        <th><span class="glyphicon ${(cd.servicio)?"glyphicon glyphicon-ok":"glyphicon glyphicon-remove"}" aria-hidden="true"></span></th>
                        <th><span class="glyphicon ${(cd.servicioConfigurado)?"glyphicon glyphicon-ok":"glyphicon glyphicon-remove"}" aria-hidden="true"></span></th>
                        <th>
                        <sec:authorize access="hasAnyRole('Registrar_Editar_Catalogo_Documental')">
                            <form method="post" action="${pageContext.request.contextPath}/sistema/editar-registro-documental.htm">
                              <input type="hidden" name="tipoDoc" value="${cd.idCatalogo}">
                              <button type="submit" class="btn btn-default" title="Editar documento">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                              </button>
                            </form>
                        </th>
                        </sec:authorize>
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