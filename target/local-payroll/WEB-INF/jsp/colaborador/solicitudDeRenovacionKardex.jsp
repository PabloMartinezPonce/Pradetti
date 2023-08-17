<%-- 
    Document   : solicitudDeRenovacionKardex
    Created on : 24/05/2017, 05:37:29 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>
<%response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../fragmentos/cabeceraKardex.jsp" %>  
        <title>Solicitud De Renovación</title>

    </head>

    <body id="home">

        <div class="blog-masthead">
            <div class="container">
                <nav class="blog-nav">
                    <a class="blog-nav-item active" href="#home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Inicio</a>
                    <c:if test="${model.cRespuesta==0}">
                        <a class="blog-nav-item" onclick="window.close()" href="#"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>&nbsp;Salir</a>
                    </c:if>
                </nav>
            </div>
        </div>
        <div class="container bodie">
            <div class="row">
                <div class="col-xs-2">
                    <div class="row">
                        <img alt="logo pradetti" src="${pageContext.request.contextPath}/frontend/img/logo.png" id="logoPradetti"/>
                    </div>
                </div>
                <div class="col-xs-10">
                    <div class="row">
                        <div class="col-xs-12">
                            <h1 class="headland"><span class="glyphicon glyphicon-pawn" aria-hidden="true"></span><b>&nbsp;&nbsp;&nbsp;Colaborador</b></h1>
                        </div>
                        <div class="col-xs-12">
                            <h2 class="headland-two"><i>${model.datosPersonales.nombre}&nbsp;${model.datosPersonales.apellidoPaterno}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno)}</i></h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <section class="section" id="home">
                    <div class="sec-header">Solicitud de Renovación
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn" title="Contraer">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>

                        </div>
                    </div>
                    <div class="sec-body">
                        <div class="row">                         
                                <div class="col-xs-12">
                                    <div class="descriptor">
                                        <span class="title">Cliente</span>
                                        <span class="content">${model.colaborador.datosLaborales.clienteObj.nombreEmpresa}</span>                                    
                                    </div>
                                </div>
                                <div class="col-xs-12">
                                    <div class="descriptor">
                                        <span class="title">Fecha de renovación de contrato</span>
                                        <span class="content"><fmt:formatDate value="${model.ultimaSolicitudRenovacion.fechaDeSolicitud}" pattern="dd/MM/yyyy " /></span>                                    
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="descriptor">
                                        <span class="title">Tipo de contrato</span>
                                        <span class="content">${model.ultimaSolicitudRenovacion.tipoContratoObj.descripcion}</span>                                    
                                    </div>
                                </div>
                            <div class="col-xs-6">
                                <div class="descriptor">
                                    <span class="title">Tiempo</span>
                                    <span class="content">${(model.ultimaSolicitudRenovacion.periodoContrato==null)?"N/A":model.ultimaSolicitudRenovacion.periodoContrato}</span>                                    
                                </div>
                            </div>
                                <div class="col-xs-6">
                                    <div class="descriptor">
                                        <c:if test="${model.ultimaSolicitudRenovacion.SUPObj == null}">
                                            <span class="title">Sueldo mensual</span>
                                            <span class="content">${(model.ultimaSolicitudRenovacion.sueldoMensual==null)?"Sin modificación":model.ultimaSolicitudRenovacion.sueldoMensual}</span>                                    
                                        </c:if>
                                        <c:if test="${model.ultimaSolicitudRenovacion.SUPObj != null}">
                                            <span class="title">SUP</span>
                                            <span class="content" id="sldMnsl">${model.ultimaSolicitudRenovacion.SUPObj.oficio} ($ ${model.ultimaSolicitudRenovacion.SUPObj.pesosDiarios} MXN)</span>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="descriptor">
                                        <span class="title">Salario diario</span>
                                        <span class="content">${(model.ultimaSolicitudRenovacion.salarioDiario==null)?"Sin modificación":model.ultimaSolicitudRenovacion.salarioDiario}</span>                                    
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="descriptor">
                                        <span class="title">Salarios minimos</span>
                                        <span class="content">${(model.ultimaSolicitudRenovacion.salariosImss==null)?"Sin modificación":model.ultimaSolicitudRenovacion.salariosImss}</span>                                    
                                    </div>
                                </div>
                                <div class="col-xs-6">
                                    <div class="descriptor">
                                        <span class="title">Salario diario integrado</span>
                                        <span class="content">${(model.ultimaSolicitudRenovacion.salarioDiarioIntegrado==null)?"Sin modificación":model.ultimaSolicitudRenovacion.salarioDiarioIntegrado}</span>                                    
                                    </div>
                                </div>
                                <div class="col-xs-12 lnbrk"></div>
                                <c:if test="${model.cRespuesta==2}">
                                <div class="col-xs-12 col-md-10 col-md-offset-1">
                                    <div class="descriptor">
                                        <div class="row">
                                            <span class="title">Agregar motivo de rechazo</span>
                                            <div class="col-xs-12 lnbrk"></div>
                                            <form id="formObservacionesColaborador" action="${pageContext.request.contextPath}/colaborador/rechazo-de-solicitud.htm" method="post" >
                                                <div class="col-xs-12">
                                                    <div class="input-group">
                                                        <input type="hidden" name="idAgremiado" value="${model.colaborador.idAgremiado}">
                                                        <input type="hidden" name="idSolicitudRenovacion" value="${model.ultimaSolicitudRenovacion.idSolicitudRenovacion}">
                                                        <span class="input-group-addon" id="nmbrs">Motivo de rechazo</span>
                                                        <input autocomplete="off" type="text" class="form-control toUpperText" minlength="10" maxlength="290"placeholder="Campo requerido" aria-describedby="nmbrs" name="motivoDeRechazo" required title="Obsrvación del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                                                    </div>
                                                </div>
                                            <div class="col-xs-12 lnbrk"></div>
                                                <div class="col-xs-2 col-xs-offset-8">
                                                    <button type="submit" class=" btn btn-success btn-lg center-btn btn-helper">
                                                        <b>Rechazar</b>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="col-xs-12 lnbrk"></div>
                                    </div>
                                </div>    
                                </c:if>        
                                <c:if test="${model.cRespuesta==1}">
                                <div class="col-xs-12 col-md-10 col-md-offset-1">                                                    
                                    <div class="descriptor">
                                        <div class="row">
                                            <div class="col-xs-12 lnbrk"></div>
                                            <form id="formObservacionesColaborador" action="${pageContext.request.contextPath}/colaborador/aprobacion-de-solicitud.htm" method="post" >
                                            <div class="col-xs-12 lnbrk">
                                                        <input type="hidden" name="idAgremiado" value="${model.colaborador.idAgremiado}">
                                                        <input type="hidden" name="idSolicitudRenovacion" value="${model.ultimaSolicitudRenovacion.idSolicitudRenovacion}">
                                            </div>
                                                <div class="col-xs-2 col-xs-offset-8">
                                                    <button type="submit" class=" btn btn-success btn-lg center-btn btn-helper">
                                                        <b>Aprobar</b>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="col-xs-12 lnbrk"></div>
                                    </div>
                                    </div>
                                </c:if>    
                        </div>
                    </div>
                    <div class="sec-footer"></div>
                </section>
            </div>
        </div>
        <!-- ==================================================== Sección de  footer ================================================================== -->
        <%@include file="../fragmentos/pieKardex.jsp" %>
    </body>
</html>