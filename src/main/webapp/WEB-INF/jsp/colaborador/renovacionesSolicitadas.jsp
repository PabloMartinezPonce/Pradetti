<%-- 
    Document   : renovacionesSolicitadas
    Created on : 24/05/2017, 04:33:36 PM
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
        <title>Renovaciones solicitadas</title>    
    </head>
    <body>
        <!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
        <%@include file="../fragmentos/menu.jsp" %>
        <!-- ==================================================== cuerpo de la página ================================================================== -->
        <div class="col-xs-12 main" id="sidebody">
            <h1 class="page-header" id="titlePage">
                <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores      
            </h1>
            <!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
            <div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
                <%@include file="../fragmentos/menuColaborador.jsp" %>
                <h2 class="selectAction">Renovaciones Solicitadas</h2>
                <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Cliente</th>
                            <th>Contratista</th>
                            <th>Fecha de vencimiento</th>
                            <th>Fecha de renovación</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
        <sec:authorize access="hasAnyRole('Renovaciones_Solicitadas')">     
                            <c:forEach var="colaborador" items="${model.Colaboradores}">
                            <tr>
                                <td>${colaborador.idAgremiado}</td>
                                <td><b>${colaborador.datosPersonales.nombre}&nbsp;${colaborador.datosPersonales.apellidoPaterno}&nbsp;${((colaborador.datosPersonales.apellidoMaterno==null)?"":colaborador.datosPersonales.apellidoMaterno)}</b></td>
                                <td>${colaborador.datosLaborales.clienteObj.nombreEmpresa}</td>
                                <td>${colaborador.datosLaborales.contratistaObj.nombreEmpresa}</td>
                                <td><fmt:formatDate value="${colaborador.datosLaborales.fechaFinContrato}" pattern="yyyy - MM - dd" /> </td>
                                <td>
                                    <c:forEach items="${model.ultimaRenovacion}" var="ultimaRenovacion">
                                        <c:if test="${(ultimaRenovacion.key == colaborador.idAgremiado)}">
                                            <fmt:formatDate value="${ultimaRenovacion.value.fechaDeSolicitud}" pattern="yyyy - MM - dd" /> </td>
                                        </c:if>
                                    </c:forEach>                                    
                                </td>
                                <td>
                                    <div class="btn-group" role="group" aria-label="...">
                                        <button type="button" class="btn btn-default redireccionarVentana-td"  value="${pageContext.request.contextPath}/colaborador/kardex/solicitud-de-renovacion/${colaborador.idAgremiado}.htm" title="Ver solicitud de renovación">
                                            <span class="glyphicon glyphicon-file" aria-hidden="true"></span>
                                        </button> 
        <sec:authorize access="hasAnyRole('Aprobar_Solicitud_Renovaciones_Solicitadas')">   
                                        <button type="button" class="btn btn-default redireccionar-td" value="${pageContext.request.contextPath}/colaborador/aprobacion/solicitud-de-renovacion/${colaborador.idAgremiado}.htm" title="Aprobar solicitud de renovación">
                                            <span class="glyphicon glyphicon-check" aria-hidden="true"></span>
                                        </button>          
        </sec:authorize>
        <sec:authorize access="hasAnyRole('Rechazar_Solicitud_Renovaciones_Solicitadas')">  
                                        <button type="button" class="btn btn-default redireccionar-td" value="${pageContext.request.contextPath}/colaborador/rechazo/solicitud-de-renovacion/${colaborador.idAgremiado}.htm" title="Rechazar solicitud de renovación">
                                            <span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
                                        </button>    
        </sec:authorize>
                                        <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/colaborador/kardex/${colaborador.idAgremiado}.htm" title="Ver colaborador">
                                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                            </c:forEach>
        </sec:authorize>
                    </tbody>
                </table>
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
