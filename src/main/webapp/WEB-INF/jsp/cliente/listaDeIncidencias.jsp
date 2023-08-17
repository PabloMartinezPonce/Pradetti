<%-- 
    Document   : listaDeIncidencias
    Created on : 15/11/2016, 11:09:31 AM
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
 <title>Lista de incidencias</title>    
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
<h2 class="selectAction">Lista de incidencias</h2>
    <div class="col-xs-12">
        <span class="glyphicon glyphicon-queen" aria-hidden="true" title="Cliente"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.cliente.nombreEmpresa}</span>
    </div>
    <div class="col-xs-12">
        RFC&nbsp;&nbsp;&nbsp;<span id="rfcDlClnt">${model.cliente.rfc}</span>
    </div>
    <form action="${pageContext.request.contextPath}/cliente/ver-incidencias.htm" method="post" id="formListaDeIncidencias">
        <div class="row">
            <div class="col-xs-12 col-sm-4">
                <div class="input-group">
                    <input type="hidden" name="idCliente" value="${model.cliente.idCliente}">
                  <span class="input-group-addon" >Buscar incidencias por</span>
                    <select name="buscarPor" class="form-control" placeholder="Campo requerido" aria-describedby="prdDCntrt" id="prdDCntrtID_AddColaboradorBIP">
                      <option value="1">Fecha de registro</option>
                      <option value="2">Fecha de incidencia</option>
                    </select>
                </div>
            </div>
            <div class="col-xs-12 col-sm-4">
                <div class="input-group">
                  <span class="input-group-addon" >Desde</span>
                  <input type="date" value="${model.fechaDesde}" class="form-control" placeholder="Campo requerido" name="ncdncsDsd" id="" required title="Fecha inicial de la búsqueda">
                </div> 
            </div>
            <div class="col-xs-12 col-sm-4">
                <div class="input-group">
                  <span class="input-group-addon" >Hasta</span>
                  <input type="date" value="${model.fechaHasta}" class="form-control" placeholder="Campo requerido" name="ncdncsHst" id="" required title="Fecha final de la búsqueda">
                </div> 
            </div>
<div class="col-xs-12 lnbrk"></div>
                <div class="col-xs-12 col-sm-2 col-sm-offset-10">       
                    <sec:authorize access="hasAnyRole('Ver_Todo','Incidencias_Del_Cliente')">     
                        <button type="submit" class="form-control btn center-btn" id="btnBscrLstNcdncs">
                            <b>Buscar</b>
                        </button>
                    </sec:authorize>
                </div>         
            <div class="col-xs-12 col-sm-6"></div>
        </div>
    </form>
<div class="col-xs-12 lnbrk"></div>
<c:if test="${model.muestraTabla}">
<div class="row" id="divTblLstNcdncDump">        
    <h4 class="selectAction">Resultados de la búsqueda</h4>
    <div class="col-xs-12 lnbrk"></div>
    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>ID</th>
                <th>Colaborador</th>
                <th>Fecha de la incidencia</th>
                <th class="hidden-xs">Fecha de registro</th>
                <th class="hidden-xs">Cantidad</th>
                <th>Estado</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;&nbsp;</th>
            </tr>
        </thead>
        <tbody>
            <sec:authorize access="hasAnyRole('Ver_Todo','Incidencias_Del_Cliente')">
            <c:forEach items="${model.incidencias}" var="incidencia">
            <tr class="toolTip" title="${incidencia.comentarios}">
                <td>${incidencia.idIncidencia}</td>
                <td><b>${incidencia.agremiadoObj.datosPersonales.nombre}&nbsp;${incidencia.agremiadoObj.datosPersonales.apellidoPaterno}&nbsp;${((incidencia.agremiadoObj.datosPersonales.apellidoMaterno==null)?"":incidencia.agremiadoObj.datosPersonales.apellidoMaterno)}</b></td>
                <td><fmt:formatDate value="${incidencia.fechaIncidencia}" pattern="dd - MM - yyyy" /></td>
                <td class="hidden-xs"><fmt:formatDate value="${incidencia.fechaRegistro}" pattern="dd - MM - yyyy HH:mm" /></td>
                <c:forEach items="${model.tipoIncidencias}" var="tipoIncidencia">
                    <c:if test="${tipoIncidencia.idTipoIncidencia == incidencia.tipoIncidenciaObj.idTipoIncidencia}">
                        <td class="hidden-xs"><b>${(tipoIncidencia.criterio)?"":"-"}</b>&nbsp;${(tipoIncidencia.dias)?"":"$"}&nbsp;${incidencia.cantidad}&nbsp;${(tipoIncidencia.dias)?"Día(s)":""}</td>
                    </c:if>
                </c:forEach>
                <th><span class="${(incidencia.status)?"glyphicon glyphicon-registration-mark":"glyphicon glyphicon-unchecked"}" title="${(incidencia.status)?"La incidencia ya fue revisada":"La incidecia aun no es revisada"}" aria-hidden="true"></span></th>
                <td>
                    <div class="btn-group" role="group" aria-label="...">
                        <c:if test="${!incidencia.status}">
                    <sec:authorize access="hasAnyRole('Editar_Incidencias_Del_Cliente')">
                        <button type="button" class="btn btn-default editNcdnc hidden-xs redireccionar-td" value="${pageContext.request.contextPath}/cliente/editar-incidencia/${model.cliente.idCliente}/${incidencia.agremiadoObj.idAgremiado}/${incidencia.idIncidencia}/${incidencia.tipoIncidenciaObj.idTipoIncidencia}.htm" title="Editar" ${(incidencia.status)?"disabled":""}>
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </button>    
                    </sec:authorize>
                        </c:if>
                    <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/cliente/ver-incidencia/${model.cliente.idCliente}/${incidencia.agremiadoObj.idAgremiado}/${incidencia.idIncidencia}/${incidencia.tipoIncidenciaObj.idTipoIncidencia}.htm" title="Ver">
                      <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                  </button>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </sec:authorize>
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