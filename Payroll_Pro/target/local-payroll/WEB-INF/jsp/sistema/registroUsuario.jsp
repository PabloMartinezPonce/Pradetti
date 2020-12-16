<%-- 
    Document   : registroUsuario
    Created on : 15/11/2016, 01:46:27 PM
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
<h2 class="selectAction">${(model.edicion)?"Edición de un usuario":"Nuevo usuario de sistema"}</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <form method="post" id="formNuevoUsuarioDeSistema" modelAttribute="usuario" action="${pageContext.request.contextPath}/sistema/datos-de-usuario.htm">
        <div class="row">
        <div class="col-xs-12">
            <div class="input-group">
                <input type="hidden" name="idUsuario" value="${model.usuario.idUsuario}">
              <span class="input-group-addon" id="nmbrCmplt">Nombre completo</span>
              <input value="${model.usuario.nombre}" type="text" class="form-control capitalizeText" minlength="3" placeholder="Campo requerido" aria-describedby="nmbrCmplt" name="nombre" required title="Nombre completo del usuario sin acentos" maxlength="100" autocomplete="off"  pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
            </div>
        </div>
        <div class="col-xs-12 col-sm-5">
            <div class="input-group">
              <span class="input-group-addon" id="crrLctrnc">Correo electrónico</span>
              <input value="${model.usuario.correoElectronico}" type="email" class="form-control toLowerText" placeholder="Campo requerido" aria-describedby="crrLctrnc" name="correoElectronico" required title="Correo electrónico del usuario" maxlength="250" autocomplete="off"  pattern="[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*[.][a-zA-Z]{1,10}" minlength="5">
            </div>
        </div>
        <div class="col-xs-12 col-sm-5 col-sm-offset-2">
            <div class="input-group">
              <span class="input-group-addon" id="rls">Rol</span>
                <select name="rol" class="form-control" placeholder="Campo requerido" aria-describedby="rls"  required>
                  <option value=""></option>
                  <c:forEach items="${model.roles}" var="rol">
                      <option value="${rol.idRol}" ${(rol.idRol==model.usuario.rol)?"selected":""} >${rol.nombre}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
            <div class="col-xs-12 lnbrk">
                <input type="hidden" id="clntsPrGrgr" value="" name="clntsPrGrgr">
            </div>
        <div class="col-xs-12 col-sm-4">
                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
            </div>
            <div class="col-xs-12 col-sm-4">
                     <sec:authorize access="hasAnyRole('Agregar_Usuario','Editar_Usuario')">
                <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                    <b>Guardar</b>
                </button>
                     </sec:authorize>
            </div>
            <div class="col-xs-12 col-sm-4">
                <c:choose>
                    <c:when test="${model.edicion}">
                        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/todos-los-usuarios.htm">
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
    <form method="post" action="">
        <div class="col-xs-8 col-xs-offset-2">
            <h3>Agregar clientes al usuario</h3>
            <div class="row">
                <div class="col-xs-8">
                    <div class="input-group">
                       <input type="hidden" name="idUsuario" value="${model.usuario.idUsuario}">
                      <span class="input-group-addon" id="nmbrCmplt">Cliente</span>
                      <select id="optionClntsPrGrgr" name="cliente" class="form-control" placeholder="Campo requerido"  required>
                        <option value=""></option>
                        <c:forEach items="${model.clientes}" var="cliente">
                            <option value="${cliente.idCliente}">${cliente.nombreEmpresa}</option>
                          </c:forEach>
                      </select>                     
                    </div>
                </div>
                <div class="col-xs-4">
                    <button type="button" class="btn btn-success" title="Agregar acceso al cliente" id="buttonClntsPrGrgr">
                          <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Agregar cliente
                      </button>
                </div>
                <div id="divClntsPrGrgr">
                    <div class="col-xs-12 lnbrk"></div>
                    <div class="col-xs-12">
                         <h4>&nbsp;&nbsp;&nbsp;Clientes por agregar al usuario</h4>
                    </div>
                    <div class="col-xs-12">
                           <div class="list-group" id="listClntsPrGrgr" >
                                
                          </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
            
    <c:if test="${model.edicion}">
        <div class="col-xs-10 col-xs-offset-1">
            <h3>Clientes asociados al usuario</h3>
        </div>
      <div class="col-xs-8 col-xs-offset-2" >
              <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th class="hidden-xs">ID</th>
                            <th>Cliente</th>
                            <th class="hidden-xs">&nbsp;&nbsp;Opciones&nbsp;&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${model.usuario.clienteList}" var="cliente">
                            <tr>
                                <td class="hidden-xs">${cliente.idCliente}</td>
                                <td><b>${cliente.nombreEmpresa}</b></td>
                                <td class="hidden-xs">
                                    <form method="post" action="${pageContext.request.contextPath}/sistema/quitar-un-cliente-al-usuario.htm">
                                        <input type="hidden" value="${cliente.idCliente}" name="idCliente">
                                        <input type="hidden" value="${model.usuario.idUsuario}" name="idUsuario">
                                        <button  type="submit" class="btn btn-default" title="Eliminar acceso al cliente">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </button>
                                   </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
            </table> 
      </div>
    </c:if>
            
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