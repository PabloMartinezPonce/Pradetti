<%-- 
    Document   : expedientesPorCompletar
    Created on : 4/05/2017, 04:52:20 PM
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
        <title>Payroll - Colaboradores</title>
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
                <h2 class="selectAction">Expedientes Por Completar</h2>
                <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>RFC</th>
                            <th>CURP</th>
                            <th>&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;&nbsp;</th>
        <sec:authorize access="hasAnyRole('Descartar_Expediente')">  
                            <th>Descartar</th>
        </sec:authorize>
                        </tr>
                    </thead>
                    <tbody>
        <sec:authorize access="hasAnyRole('Expedientes_Por_Completar')">  
                        <c:forEach var="colaborador" items="${model.Colaboradores}">
                            <tr>
                                <td>${colaborador.idAgremiado}</td>
                                <td><b>${colaborador.datosPersonales.nombre}&nbsp;${colaborador.datosPersonales.apellidoPaterno}&nbsp;${((colaborador.datosPersonales.apellidoMaterno==null)?"":colaborador.datosPersonales.apellidoMaterno)}</b></td>
                                <td>${colaborador.datosPersonales.rfc}</td>
                                <td>${colaborador.datosPersonales.curp}</td>
                                <td>
                                    <div class="btn-group" role="group" aria-label="...">
                                        <button type="button" class="btn btn-default redireccionarVentana-td" value="${pageContext.request.contextPath}/colaborador/kardex/${colaborador.idAgremiado}.htm" title="Ver colaborador">
                                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                        </button>
        <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador')"> 
                                        <button type="button" class="btn btn-default redireccionar-td" value="${pageContext.request.contextPath}/colaboradores/editar-registro/${colaborador.idAgremiado}.htm" title="Editar registro">
                                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                        </button>
        </sec:authorize>
                                    </div>
                                </td>
        <sec:authorize access="hasAnyRole('Descartar_Expediente')">  
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/colaborador/descartar-expediente/${colaborador.idAgremiado}.htm">
                                        <button type="button" class="btn btn-descartar" title="Descartar Expediente">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </button>
                                    </form>
                                </td>
        </sec:authorize>
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
