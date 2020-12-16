<%-- 
    Document   : seleccionarPeriodo
    Created on : 21/10/2019, 12:23:53 PM
    Author     : GJJ <gabriela.jaime@it-seekers.com>
--%>
<%response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <div class="col-xs-12 col-lg-8 col-lg-offset-2" id="frameContainer">    
                <%@include file="../fragmentos/menuColaborador.jsp" %>
                <h2 class="selectAction">Seleccionar período de fondo de Ahorro</h2>
                <div class="col-xs-12 col-md-9 col-md-offset-1">
                    <h3>                                 
                <c:choose>
                    <c:when test="${model.datosPersonales.nombre!=null}">          
                        <h2>
                            <span title= "Agremiado" class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            ${model.datosPersonales.nombre.toUpperCase()}&nbsp;${model.datosPersonales.apellidoPaterno.toUpperCase()}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno.toUpperCase())}
                        </h2>
                       <!-- <div class="col-xs-12 col-md-12">
                            <span class="glyphicon glyphicon-user" aria-hidden="true" title="Agremiado"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.datosPersonales.nombre}&nbsp;${model.datosPersonales.apellidoPaterno}&nbsp;${model.datosPersonales.apellidoMaterno}</span>
                        </div>-->
                        <div class="col-xs-12 col-md-12">
                            <span class="glyphicon glyphicon-queen" aria-hidden="true" title="Cliente"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.agremiado.datosLaborales.clienteObj.nombreEmpresa}</span>
                        </div>
                        <div class="col-xs-12 col-md-12">
                            <span class="glyphicon glyphicon-king" aria-hidden="true" title="Contratista"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.agremiado.datosLaborales.contratistaObj.nombreEmpresa}</span>
                        </div>
                        <div class="col-xs-12 col-md-12">
                            <span class="glyphicon glyphicon-piggy-bank" aria-hidden="true" title="Porcentaje del fondo de ahorro"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.agremiado.datosLaborales.fondoDeAhorroActual}&nbsp;%</span>
                        </div>
                    </c:when>
                    <c:otherwise>                                          
                        <h3>
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                Desconocido
                        </h3>
                    </c:otherwise>
                </c:choose>    
                    </h3>
                </div>
                <div class="lnbrk"><br></div>
                <div class="row" id="addColaboradorNew">

                    <div class="col-xs-12 col-md-9 col-md-offset-1" id="formControlsColaborador">
                        <div class="row" >
                            <form id="formSindicato" action="${pageContext.request.contextPath}/colaborador/reporte-fondo-ahorro/${model.agremiado.idAgremiado}.htm" method="post">
                                <div class="input-group">
                                    <span class="input-group-addon" id="clnt">Periodo</span>
                                    <select name="idPeriodo" class="form-control" placeholder="Campo requerido" aria-describedby="clnt"  required>
                                        <c:forEach items="${model.periodos}" var="periodo">
                                            <option value="${periodo.idPeriodoFA}"><c:out value="${periodo.nombrePeriodo}" /></option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <input type="hidden" name="idAgremiado" value="${model.agremiado.idAgremiado}">
                                <div class="lnbrk"><br></div>
                                <div class="col-xs-12 col-sm-3 col-sm-offset-3">
        <sec:authorize access="hasAnyRole('VoBo_Altas_Solicitadas')"> 
                                    <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                        <b>Guardar</b>
                                    </button>
        </sec:authorize>
                                </div>
                                <div class="col-xs-12 col-sm-3 col-sm-offset-1">
                                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/altas-solicitadas.htm">     
                                        <b>Cancelar</b>
                                    </button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-12 lnbrk"></div>    
        </div>

        <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->

        <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
        <%@include file="../fragmentos/pie.jsp" %>

    </body>
</html>