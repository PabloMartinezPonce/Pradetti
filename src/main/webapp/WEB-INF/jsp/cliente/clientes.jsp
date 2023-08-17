<%-- 
    Document   : clientes
    Created on : 15/11/2016, 10:59:18 AM
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
 <title>Payroll - Clientes</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Clientes    
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">
 <%@include file="../fragmentos/menuClientes.jsp" %>
<h2 class="selectAction">
    Módulo Clientes
</h2>
<c:if test="${model.muestraTrabla}">
<table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Id</th>
            <th>Cliente</th>
            <th>Representante legal</th>
            <th class="hidden-xs">RFC</th>
            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
        </tr>
    </thead>
    <tbody>
<sec:authorize access="hasAnyRole('Ver_Todo','Clientes')">
 <c:forEach items="${model.clientesEncontrados}" var="cliente">  
        <tr>
            <td><b>${cliente.idCliente}</b></td>
            <td><b>${cliente.nombreEmpresa}</b></td>
            <td>${cliente.representanteLegal}</td>
            <td class="hidden-xs"><b>${cliente.rfc}</b></td>
            <td>
                <div class="btn-group btn-group-sm" role="group" aria-label="...">
                    <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Clientes')">
                        <button type="button" class="btn btn-default redireccionar-td hidden-xs" value="${pageContext.request.contextPath}/cliente/editar-cliente/${cliente.idCliente}.htm" title="Editar">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </button>
                    </sec:authorize>
                        <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/cliente/${cliente.idCliente}/kardex.htm" title="Ver">
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                        </button>                            
                    <sec:authorize access="hasAnyRole('Ver_Todo','Archivo_De_Nomina')">
                        <button type="button" class="btn btn-default redireccionar-td hidden-xs" value="${pageContext.request.contextPath}/cliente/cargar-nomina/${cliente.idCliente}.htm" title="Cargar archivo de nómina">
                            <span class="glyphicon glyphicon-open-file" aria-hidden="true"></span>
                        </button>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('Incidencias_Del_Cliente')">
                        <button type="button" class="btn btn-default redireccionar-td hidden-xs" value="${pageContext.request.contextPath}/cliente/incidencias/${cliente.idCliente}.htm" title="Lista de incidencias">
                            <span class="glyphicon glyphicon-flag" aria-hidden="true"></span>
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