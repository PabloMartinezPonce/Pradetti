<%-- 
    Document   : listaDeIncidencias
    Created on : 4/01/2017, 06:59:29 PM
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
<title>Lista de incidencias&nbsp;${(model.revisadas)?"revisadas":"sin revisar"}</title>    
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
<h2 class="selectAction">Lista de incidencias&nbsp;${(model.revisadas)?"revisadas":"sin revisar"}</h2>
            <sec:authorize access="hasAnyRole('Incidencias_Revisadas','Incidencias_Sin_Revisar')">        
                <c:choose>
                    <c:when test="${(model.revisadas)}">
                            <form method="post" action="${pageContext.request.contextPath}/sistema/incidencias-revisadas-del-cliente.htm">        
                    </c:when>
                    <c:otherwise>        
                            <form method="post" action="${pageContext.request.contextPath}/sistema/incidencias-sin-revisar-del-cliente.htm">
                    </c:otherwise>
                </c:choose>
            </sec:authorize>
    <div class="col-xs-6 col-xs-offset-2">
        <div class="input-group">
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
        <div class="btn-group " role="group" aria-label="...">     
                     <sec:authorize access="hasAnyRole('Incidencias_Revisadas','Incidencias_Sin_Revisar')">                             
                            <button type="submit" class="btn btn-success btn-group btn-group-lg toolTip" title="Buscar">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                            </button>
                     </sec:authorize>
        </div>
    </div>
    <c:if test="${model.revisadas}">
        <div class="col-xs-6 col-xs-offset-2">
            <div class="input-group">
                <span class="input-group-addon">Fecha de la incidencia&nbsp;desde&nbsp;</span>
                <input type="date" name="fchDsd" class="form-control" required>
                <span class="input-group-addon">&nbsp;al&nbsp;</span>  
                <input type="date" name="fchHst" class="form-control" required>              
            </div>
        </div>        
    </c:if>
</form>
<c:if test="${model.muestraTabla}">
<div class="row" id="divTblLstNcdncDump">
    <div class="col-xs-12 lnbrk"></div>
    <div class="col-xs-12">
        <h3><span class="label label-default">Cliente</span>&nbsp;${model.cliente.nombreEmpresa}</h3>
    </div>
    <c:if test="${model.revisadas}">
        <div class="col-xs-12">
            <h3><span class="label label-default">Periodo del</span>&nbsp;${model.desde}&nbsp;<span class="label label-default">al</span>&nbsp;${model.hasta}</h3>
        </div>        
    </c:if>
    <h3 class="selectAction">Resultados de la búsqueda</h3>
    <div class="col-xs-12 lnbrk"></div>
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Agremiado</th>
                <th>Fecha de la incidencia</th>
                <th class="hidden-xs">Cantidad</th>
                <th>Fecha de registro</th>
                <th></th>
                <c:choose>
                    <c:when test="${model.revisadas}">
                        <th>&nbsp;Fecha de revisión&nbsp;</th>                    
                    </c:when>
                    <c:otherwise>                        
                        <th>&nbsp;Revisar&nbsp;</th>
                    </c:otherwise>
                </c:choose>
                <th>&nbsp;Ver&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${model.incidencias}" var="incidencia">
            <tr>
                <td>${incidencia.idIncidencia}</td>
                <td><b>${incidencia.agremiadoObj.datosPersonales.nombre}&nbsp;${incidencia.agremiadoObj.datosPersonales.apellidoPaterno}&nbsp;${((incidencia.agremiadoObj.datosPersonales.apellidoMaterno==null)?"":incidencia.agremiadoObj.datosPersonales.apellidoMaterno)}</b></td>
                <td><fmt:formatDate value="${incidencia.fechaIncidencia}" pattern="yyyy - MM - dd" /></td>
                <c:forEach items="${model.tipoIncidencias}" var="tipoIncidencia">
                    <c:if test="${tipoIncidencia.idTipoIncidencia == incidencia.tipoIncidenciaObj.idTipoIncidencia}">
                        <td class="hidden-xs"><b>${(tipoIncidencia.criterio)?"":"-"}</b>&nbsp;${(tipoIncidencia.dias)?"":"$"}&nbsp;${incidencia.cantidad}&nbsp;${(tipoIncidencia.dias)?"Día(s)":""}</td>
                    </c:if>
                </c:forEach>
                <td><fmt:formatDate value="${incidencia.fechaRegistro}" pattern="dd/MM/yyyy HH:mm" /></td>
                <th>                    
                    <button type="button" class="btn btn-default btn-popover" data-container="body" data-toggle="popover" data-placement="left" data-content="${incidencia.comentarios}">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    </button>
                </th>
                <c:choose>
                    <c:when test="${model.revisadas}">
                        <td><fmt:formatDate value="${incidencia.fechaModificacion}" pattern="dd/MM/yyyy HH:mm" /></td>
                    </c:when>
                    <c:otherwise>
                        <td>
                            <form class="inline" method="post" action="${pageContext.request.contextPath}/sistema/recoger-incidencia.htm">
                                    <input type="hidden" name="idCliente" value="${model.cliente.idCliente}">
                                    <input type="hidden" name="idAgremiado" value="${incidencia.agremiadoObj.idAgremiado}">
                                    <input type="hidden" name="idIncidencia" value="${incidencia.idIncidencia}">
                                    <input type="hidden" name="tipoIncidenciaObj" value="${incidencia.tipoIncidenciaObj.idTipoIncidencia}">
                                    <button type="button" class="btn btn-default editNcdnc hidden-xs btn-revisar-incidencia">
                                        <span class="${(incidencia.status)?"glyphicon glyphicon-registration-mark":"glyphicon glyphicon-unchecked"}" aria-hidden="true"></span>
                                      </button>
                            </form>    
                        </td>                        
                    </c:otherwise>
                </c:choose>                
                <td>
                    <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/cliente/ver-incidencia/${model.cliente.idCliente}/${incidencia.agremiadoObj.idAgremiado}/${incidencia.idIncidencia}/${incidencia.tipoIncidenciaObj.idTipoIncidencia}.htm" title="Ver">
                      <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table> 
</div>
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