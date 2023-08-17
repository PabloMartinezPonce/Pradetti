<%-- 
    Document   : asociarContrato
    Created on : 2/01/2017, 06:51:30 PM
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
<h2 class="selectAction">Relacionar contrato a contratistas</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
                    <form method="post" id="formNuevoUsuarioDeSistema"  action="${pageContext.request.contextPath}/sistema/agregar-contratistas-al-contrato.htm">       
        <div class="row">
        <div class="col-xs-12">
            <div class="input-group">
                <input type="hidden" name="contrato" value="${model.contrato.idContrato}">
            </div>
        </div>
            <div class="col-xs-8 col-xs-offset-2">
                <h4><span class="label label-default">Nombre del contrato</span>&nbsp;&nbsp;<b><u>&nbsp;&nbsp;${model.contrato.nombre}&nbsp;&nbsp;</u></b></h4>
            </div>
            <div class="col-xs-8 col-xs-offset-2">
            <h3>Agregar&nbsp;Contratistas&nbsp;al&nbsp;contrato</h3>
            <div class="row">
                <div class="col-xs-8">
                    <div class="input-group">
                       <input type="hidden" name="idUsuario" value="${model.usuario.idUsuario}">
                      <span class="input-group-addon" id="nmbrCmplt">&nbsp;Contratista&nbsp;</span>
                      <select id="optionClntsCntrstsPrGrgr" name="actores" class="form-control" placeholder="Campo requerido" >
                        <option value=""></option>
                                <c:forEach items="${model.actores}" var="contratista">
                                    <option value="${contratista.idContratista}">${contratista.nombreEmpresa}</option>
                                </c:forEach> 
                      </select>                     
                    </div>
                </div>
                <div class="col-xs-4">
                    <button type="button" class="btn btn-success" title="Agregar acceso al Contratista" id="buttonClntsCntrstsPrGrgr">
                          <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;Agregar&nbsp;Contratista&nbsp;
                      </button>
                </div>
                <div id="divClntsPrGrgr">
                    <div class="col-xs-12 lnbrk"></div>
                    <div class="col-xs-12">
                        <h4>&nbsp;&nbsp;&nbsp;Contratistas&nbsp;por agregar al contrato</h4>
                    </div>
                    <div class="col-xs-12">
                           <div class="list-group" id="listClntsCntrstsPrGrgr" >
                                
                          </div>
                    </div>
                </div>
            </div>
        </div>
            <div class="col-xs-12 lnbrk">
                <input type="hidden" id="clntsCntrstsPrGrgr" value="" name="clntsCntrstsPrGrgr" required>
            </div>
        <div class="col-xs-12 col-sm-3 col-sm-offset-1">
                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
            </div>
            <div class="col-xs-12 col-sm-4">
                    <sec:authorize access="hasAnyRole('Relacionar_Contratos_Creados')">
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Guardar</b>
                        </button>
                    </sec:authorize>
            </div>
            <div class="col-xs-12 col-sm-4">
                <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/contratos-creados.htm">
                    <b>Cancelar</b>
                </button>
            </div>
        </div>
    </form>
            
        <div class="col-xs-10 col-xs-offset-1">
            <h3>&nbsp;Contratistas&nbsp; asociados al contrato</h3>
        </div>
      <div class="col-xs-8 col-xs-offset-2" >
              <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th class="hidden-xs">ID</th>
                            <th>&nbsp;Contratista&nbsp;</th>
                            <th class="hidden-xs">&nbsp;&nbsp;Opciones&nbsp;&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>                              
                                <c:forEach items="${model.contrato.contratistaList}" var="contratista">
                                    <tr>
                                        <td class="hidden-xs">${contratista.idContratista}</td>
                                        <td><b>${contratista.nombreEmpresa}</b></td>
                                        <td class="hidden-xs">
                                            <form method="post" action="${pageContext.request.contextPath}/sistema/quitar-un-contratista-del-contrato.htm">
                                                <input type="hidden" value="${contratista.idContratista}" name="contratista">
                                                <input type="hidden" value="${model.contrato.idContrato}" name="contrato">
                                                <button  type="submit" class="btn btn-default" title="Eliminar acceso al contratista">
                                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                                </button>
                                           </form>
                                        </td>
                                    </tr>
                                </c:forEach>       
                    </tbody>
            </table> 
      </div>
            
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