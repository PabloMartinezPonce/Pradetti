<%-- 
    Document   : documentosDisponibles
    Created on : 28/12/2017, 12:26:39 PM
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
<h2 class="selectAction">Documentos registrados</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer"> 
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Módulo</th>
                <th>Catalogo documental</th>
                <th>Requerido</th>
                <th>Status</th>
                <th>&nbsp;Opciones&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <sec:authorize access="hasAnyRole('Documentos')">
                    <c:forEach items="${model.modulos}" var="modulo">
                        <c:forEach items="${model.docPorModulos}" var="documentosDelModulo">
                            <c:if test="${modulo.nombre.equalsIgnoreCase(documentosDelModulo.key)}">
                                <c:forEach items="${documentosDelModulo.value}" var="tipoDocumentos">
                                    <tr>
                                        <td><b>${tipoDocumentos.nombreDocumento}</b></td>
                                        <td>${modulo.nombre}</td>
                                        <td>${tipoDocumentos.catalogoDocumentalObj.nombreDocumento}</td>
                                        <td>${(tipoDocumentos.obligatorio)?"Sí":"No"}</td>
                                        <td>${(tipoDocumentos.status)?"Activo":"Inactivo"}</td>
                                        <td>
                                        <sec:authorize access="hasAnyRole('Registrar_Editar_Documentos')">
                                                    <div class="btn-group" role="group" aria-label="...">
                                                      <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/editar-documento.htm">
                                                            <input type="hidden" name="tipoDoc" value="${tipoDocumentos.idTipoDocumento}">
                                                            <button type="submit" class="btn btn-default" title="Editar usuario">
                                                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                            </button>
                                                        </form>
                                                    </div>
                                        </sec:authorize>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
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