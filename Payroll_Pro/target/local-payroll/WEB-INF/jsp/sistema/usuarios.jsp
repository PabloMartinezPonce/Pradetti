<%-- 
    Document   : usuarios
    Created on : 15/11/2016, 01:48:23 PM
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
<h2 class="selectAction">Usuarios de sistema</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th class="hidden-xs">ID</th>
                <th>Nombre</th>
                <th>Correo</th>
                <th>Rol</th>
                <th>Usuario</th>
                <th class="hidden-xs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
            </tr>
        </thead>
        <tbody>
                <sec:authorize access="hasAnyRole('Consultar_Usuario')">
                        <c:forEach items="${model.nombreRoles}" var="rolConUsuarios">
                            <c:forEach items="${model.usuariosPorRol.get(rolConUsuarios)}" var="usuario">
                                <tr>
                                    <td class="hidden-xs">${usuario.idUsuario}</td>
                                    <td><b>${usuario.nombre}</b></td>
                                    <td>${usuario.correoElectronico}</td>
                                    <td>${rolConUsuarios}</td>
                                    <c:choose>
                                        <c:when test="${usuario.clienteList.size()==1}">
                                            <td>De Cliente</td>
                                        </c:when>
                                        <c:when test="${usuario.clienteList.size()>1}">
                                            <td>De ${usuario.clienteList.size()} Clientes</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>De Sistema</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td class="hidden-xs">
                                    <sec:authorize access="hasAnyRole('Editar_Usuario')">
                                        <div class="btn-group btn-group-sm" role="group" aria-label="...">
                                            <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/editar-usuario.htm">
                                                <input type="hidden" name="sr" value="${usuario.idUsuario}">
                                                <button type="submit" class="btn btn-default" title="Editar usuario">
                                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                                </button>
                                            </form>
                                                <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/cambiar-acceso.htm">
                                                <input type="hidden" name="sr" value="${usuario.idUsuario}">
                                                <button type="submit" class="btn btn-default" title="Renovar contraseña">
                                                    <span class="glyphicon glyphicon-copyright-mark" aria-hidden="true"></span>
                                                </button>
                                            </form>
                                            <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/activar-usuario.htm">
                                                <input type="hidden" name="sr" value="${usuario.idUsuario}">
                                                <button type="submit" class="btn btn-default" title="${ (usuario.status)?"Desactivar":"Activar"}">
                                                    <span class="${ (usuario.status)?"glyphicon glyphicon-check":"glyphicon glyphicon-unchecked"}" aria-hidden="true"></span>
                                                </button>
                                            </form>
                                        </div>
                                    </sec:authorize>
                                    </td>
                                </tr>
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