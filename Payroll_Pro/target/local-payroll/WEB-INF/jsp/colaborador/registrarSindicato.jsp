<%-- 
    Document   : registrarSindicato
    Created on : 12/05/2017, 12:23:53 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
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
                <h2 class="selectAction">Agregar Sindicato</h2>
                <div class="col-xs-12 col-md-9 col-md-offset-1">
                    <h3>                                 
                <c:choose>
                    <c:when test="${model.datosPersonales.nombre!=null}">          
                        <h3>
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            ${model.datosPersonales.nombre.toUpperCase()}&nbsp;${model.datosPersonales.apellidoPaterno.toUpperCase()}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno.toUpperCase())}
                        </h3>                                
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
                            <form id="formSindicato" action="${pageContext.request.contextPath}/colaborador/sidicato-del-colaborador.htm" method="post">
                                <div class="input-group">
                                    <span class="input-group-addon" id="clnt">Sindicato</span>
                                    <select name="idSindicato" class="form-control" placeholder="Campo requerido" aria-describedby="clnt"  required>
                                        <c:forEach items="${model.sindicatos}" var="sindicato">
                                            <option value="${sindicato.idSindicato}"><c:out value="${sindicato.nombreCorto}" /></option>
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