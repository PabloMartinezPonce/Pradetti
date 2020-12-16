<%-- 
    Document   : contratosCreados
    Created on : 15/11/2016, 12:13:37 PM
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
<h2 class="selectAction">Contratos creados</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
<table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
    <thead>
        <tr>            
            <th>ID</th>
            <th>Nombre</th>
            <th>Creado</th>
            <th>Disponible para</th>
            <th>Contrato para</th>
            <th class="hidden-xs">&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;&nbsp;</th>
        </tr>
    </thead>
    <tbody>
    <sec:authorize access="hasAnyRole('Ver_Contratos_Creados')">
        <c:forEach items="${model.contratos}" var="contrato">
            <tr>
                <td>${contrato.idContrato}</td>
                <td><b>${contrato.nombre}</b></td>
                <td><fmt:formatDate value="${contrato.creado}" pattern="yyyy - MM - dd" /> </td>
                <td>${contrato.contratistaList.size()}&nbsp;${(contrato.contratistaList.size()==1)?"contratista":"contratistas"}</td>        
                <td><b>&nbsp;${(contrato.contratoDeColaborador==true)?"Colaboradores":"Clientes"}</b></td>        
                <td class="hidden-xs">
                    <div class="btn-group" role="group" aria-label="...">
                    <sec:authorize access="hasAnyRole('Editar_Contrato_Creado')">
                        <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/editar-contrato.htm">
                            <input type="hidden" name="contrato" value="${contrato.idContrato}">
                            <button type="submit" class="btn btn-default" title="Editar contrato">
                              <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </button>
                        </form>
                    </sec:authorize>
                        <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/sistema/contrato-demo/${contrato.idContrato}.htm" title="Ver">
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                        </button>
                    <sec:authorize access="hasAnyRole('Editar_Contrato_Creado')">
                        <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/cambiar-estado-del-contrato.htm">
                            <input type="hidden" name="contrato" value="${contrato.idContrato}">
                            <button type="submit" class="btn btn-default" title="${ (contrato.activo)?"Desactivar Contrato":"Activar Contrato"}">
                                <span class="${ (contrato.activo)?"glyphicon glyphicon-check":"glyphicon glyphicon-unchecked"}" aria-hidden="true"></span>
                            </button>
                        </form>
                    </sec:authorize>
                    <sec:authorize access="hasAnyRole('Relacionar_Contratos_Creados')">
                        <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/relacionar-contrato.htm">
                            <input type="hidden" name="contrato" value="${contrato.idContrato}">
                            <button type="submit" class="btn btn-default" title="Relacionar contrato">
                                <span class="glyphicon glyphicon-level-up" aria-hidden="true"></span>
                            </button>
                        </form>    
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