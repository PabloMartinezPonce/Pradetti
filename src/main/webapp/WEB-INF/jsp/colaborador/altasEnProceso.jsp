<%-- 
    Document   : altasEnProceso
    Created on : 16/05/2017, 11:52:16 AM
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
        <title>Altas en proceo</title>     
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
                <h2 class="selectAction">Altas en proceso</h2>
                <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Cliente</th>
                            <th>Contratista</th>
                            <th>Fecha de alta</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <sec:authorize access="hasAnyRole('Altas_En_Proceso')">
                        <c:forEach items="${model.Colaboradores}" var="colaborador"> 
                            <tr>
                                <td>${colaborador.idAgremiado}</td>
                                <td><b>${colaborador.datosPersonales.nombre} &nbsp;${colaborador.datosPersonales.apellidoPaterno} &nbsp;${((colaborador.datosPersonales.apellidoMaterno==null)?"":colaborador.datosPersonales.apellidoMaterno)}</b></td>
                                <td>${colaborador.datosLaborales.clienteObj.nombreEmpresa}</td>
                                <td>${colaborador.datosLaborales.contratistaObj.nombreEmpresa}</td>
                                <td><fmt:formatDate value="${colaborador.datosLaborales.fechaInicioContrato}" pattern="yyyy - MM - dd" /></td>
                                <td>
                                    <div class="btn-group" role="group" aria-label="...">
                                        <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/colaborador/kardex/${colaborador.idAgremiado}.htm" title="Ver colaborador">
                                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                        </button>
                        <sec:authorize access="hasAnyRole('Subir_Alta_Altas_En_Proceso')">
                                        <button type="button" class="btn btn-default redireccionar-td" value="${pageContext.request.contextPath}/colaborador/subir-alta-imss/${colaborador.idAgremiado}.htm" title="Subir alta">
                                            <span class="glyphicon glyphicon-upload" aria-hidden="true"></span>
                                        </button>
                        </sec:authorize>
                        <sec:authorize access="hasAnyRole('Rechazar_Altas_En_Proceso')">
                                            <form  class="inline" method="post" action="${pageContext.request.contextPath}/colaborador/rechazar-alta-en-proceso/${colaborador.idAgremiado}.htm">
                                                <button type="submit" class="btn btn-default" title="Rechazar alta en proceso">
                                                    <span class="glyphicon glyphicon-ban-circle" aria-hidden="true"></span>
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
