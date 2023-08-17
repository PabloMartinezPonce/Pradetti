<%-- 
    Document   : nuevoRol
    Created on : 15/11/2016, 12:38:40 PM
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
<h2 class="selectAction">${(model.edicion)?"Edición de un rol":"Crear un nuevo rol"}</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer"> 
    <form method="post" id="formNuevoRol" action="${pageContext.request.contextPath}/sistema/datos-del-rol.htm">
        <div class="row">
            <div class="col-xs-12 col-sm-8 col-sm-offset-2">
                <div class="input-group">
                    <input type="hidden" name="id" value="${model.rol.idRol}">
                  <span class="input-group-addon" id="nmbrDlRl">Nombre del rol</span>
                  <input value="${model.rol.nombre}" type="text" class="form-control capitalizeText" placeholder="Campo requerido" aria-describedby="nmbrDlRl" name="nombre" required title="Nombre del nuevo rol" maxlength="45" autocomplete="off" minlength="5"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>                        
            </div>
            <div class="col-xs-12 col-sm-9 col-sm-offset-3">
                <h3>Selecciona los permisos a asignar sobre el nuevo rol</h3>   
            </div>
        </div>
        <div class="row">
            <div class="col-xs-6"><h4 class="titulo-h4">Permisos existentes</h4></div>
            <div class="col-xs-6"><h4 class="titulo-h4">Permisos del rol</h4></div>
            <div class="col-xs-5">
                <div class="containerListPermisos">
                    <select name="origenPermisos" id="origenPermisos" multiple="multiple" size="${model.permisos.size()}">
                        <c:forEach items="${model.permisos}"  var="permiso">
                            <option value="${permiso.idPermiso}">${permiso.nombre}</option>                            
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="col-xs-2 centerMiddle" title="Pasar todos los permisos">
                <h2><span class="glyphicon glyphicon-transfer" aria-hidden="true"></span></h2> 
            </div>
            <div class="col-xs-5">
                <div class="containerListPermisos">   
                    <select required name="destinoPermisos" id="destinoPermisos" multiple="multiple" size="7">
                        <c:forEach items="${model.rol.permisoList}"  var="permiso">
                            <option value="${permiso.idPermiso}">${permiso.nombre}</option>                            
                        </c:forEach></select>
                </div>
            </div>
        </div>

        <div class="row">                                                
            <div class="col-xs-12 col-sm-4 col-sm-offset-1">
                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
            </div>
            <div class="col-xs-12 col-sm-3 col-sm-offset-1">
                     <sec:authorize access="hasAnyRole('Agregar_Rol','Editar_Rol')">
                <button id="selectAllOptions" type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                    <b>Guardar</b>
                </button>
                     </sec:authorize>
            </div>
            <div class="col-xs-12 col-sm-3">
                <c:choose>
                    <c:when test="${(model.edicion)}">
                        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/todos-los-roles.htm">
                            <b>Cancelar</b>
                        </button>                        
                    </c:when>
                    <c:otherwise>
                        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/opciones-del-sistema.htm">
                            <b>Cancelar</b>
                        </button>                           
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form>
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