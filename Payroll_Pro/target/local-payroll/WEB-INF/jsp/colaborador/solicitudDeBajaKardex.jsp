<%-- 
    Document   : solicitudDeBajaKardex
    Created on : 17/05/2017, 02:00:03 PM
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
        <%@include file="../fragmentos/cabeceraKardex.jsp" %>  
        <title>Detalles de baja</title>

    </head>

    <body id="home">

        <div class="blog-masthead">
            <div class="container">
                <nav class="blog-nav">
                    <a class="blog-nav-item active" href="#home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Inicio</a>
                    <c:if test="${!model.rechazo}">
                        <a class="blog-nav-item" onclick="window.close()"href="#"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>&nbsp;Salir</a>
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
                    <div class="sec-header">Solicitud de Baja
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn" title="Contraer">
                                <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                            </button>

                        </div>
                    </div>
                    <div class="sec-body">
                        <div class="row">
                            <div class="col-xs-12 col-sm-4 col-md-4">
                                <div class="descriptor">
                                    <span class="title">Fecha de Alta</span>
                                    <span class="content" id="fchDLt"><fmt:formatDate value="${model.datosLaborales.fechaAltaImss}" pattern="dd/MM/yyyy " />
                                    </span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4">
                                <div class="descriptor">
                                    <span class="title">RFC</span>
                                    <span class="content" id="rfc">${model.datosPersonales.rfc}</span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4">
                                <div class="descriptor">
                                    <span class="title">N.S.S.</span>
                                    <span class="content" title="Número de Seguro Social" id="nss">${(model.datosLaborales.numeroSeguro==null)?"No aplica":model.datosLaborales.numeroSeguro}</span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">Cliente</span>
                                    <span class="content" id="clnt">${model.datosLaborales.clienteObj.nombreEmpresa}</span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Puesto</span>
                                    <span class="content" id="pst">${model.datosLaborales.actividadProfesional}</span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="descriptor">
                                    <span class="title">Sueldo Mensual</span>
                                    <span class="content" id="sldMnsl">$&nbsp;${model.datosLaborales.sueldoMensual}&nbsp;MXN</span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-4 col-md-4">
                                <div class="descriptor">
                                    <span class="title">Fecha de Baja</span>
                                    <span class="content" id="fchBj"><fmt:formatDate value="${model.solicitudBaja.fechaBaja}" pattern="dd/MM/yyyy " /> </td></span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-3 col-md-3">
                                <div class="descriptor">
                                    <span class="title">Sueldo</span>
                                    <span class="content" id="sld">${(model.solicitudBaja.sueldo==null)?"NO":model.solicitudBaja.sueldo}</span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-5 col-md-5">
                                <div class="descriptor">
                                    <span class="title">Tipo de finiquito</span>
                                    <span class="content" id="tpDFnqt">${model.solicitudBaja.tipoFiniquitoObj.descripcion}</span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">Motivo de baja</span>
                                    <span class="content" id="mtvDBj">${model.solicitudBaja.motivoBaja.motivo}</span>
                                    
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 col-md-12">
                                <div class="descriptor">
                                    <span class="title">Detalles</span>
                                    <span class="content" id="mtvDBj">${model.solicitudBaja.motivo}</span>
                                    
                                </div>
                            </div>
                                <div class="col-xs-12 col-sm-12 col-md-12">
                                    <div class="descriptor">
                                        <span class="title">Observaciones</span>
                                        <span class="content" id="dscrpcnDBj">${model.solicitudBaja.observaciones}</span>
                                        
                                    </div>
                                </div>
                                <c:if test="${(model.solicitudBaja.motivoDeRechazo!=null)}">
                                    <div class="col-xs-12 col-sm-12 col-md-12">
                                        <div class="descriptor">
                                            <span class="title">Motivo de Rechazo</span>
                                            <span class="content">${model.solicitudBaja.motivoDeRechazo}</span>

                                        </div>
                                    </div>   
                                </c:if>
                                <div class="col-md-12">
                                    <c:if test="${model.rechazo}">
                                    <form action="${pageContext.request.contextPath}/colaborador/rechazar-baja.htm" method="post">
                                        <div class="row">
                                            <div class="col-xs-12 lnbrk"   ></div>
                                                <div class="col-xs-12">                                                
                                                <div class="input-group">
                                                    <input type="hidden" name="idSolicitud" value="${model.solicitudBaja.idSolicitudBaja}">
                                                    <input type="hidden" name="idAgremiado" value="${model.agremiado.idAgremiado}">
                                                  <span class="input-group-addon" id="nmbrCmplt">Motivo de Rechazo</span>
                                                  <input type="text" class="form-control" id="nmbrCmpltID" placeholder="Campo requerido" aria-describedby="nmbrCmplt" name="motivo" required title="Motivo del rechazo de la solicitud" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                                                </div>
                                                </div>
                                            <div class="col-xs-12 lnbrk"   ></div>
                                            <div class="col-xs-6 col-xs-offset-6 col-md-3 col-md-offset-9">
                                                <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                                    <b>Guardar</b>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </c:if>             
                                </div>
                                <div class="col-xs-12 lnbrk"   ></div>
                                <div class="col-md-12">
                             <div class="row">
                            <div class="list-group col-xs-12 col-sm-12 col-md-12">
                                <li href="#" class=" text-center list-group-item disabled"><h3>Documentos a realizar</h3></li>

                                <table id="tableDocs" class="table table-hover">
                                        <thead>
                                        <tr>
                                            <td>Documento</td>
                                            <td>
                                                    Pradetti
                                            </td>
                                            <td>
                                                    Cliente
                                            </td>
                                        </tr>    
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${model.documentosBaja}" var="documentoBaja">
                                                <tr>
                                                    <td>${documentoBaja.tipoDocumentoObj.nombreDocumento}</td>
                                                    <td>
                                                        <c:if test="${documentoBaja.cargaGuardadaPra}">
                                                            <button type="button" class="btn btn-default redireccionarVentana" title="Ver documento" value="${pageContext.request.contextPath}/colaborador/ver-documento-de-baja/Pradetti/${model.agremiado.idAgremiado}/${model.solicitudBaja.idSolicitudBaja}/${documentoBaja.tipoDocumentoObj.idTipoDocumento}.htm">
                                                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                                            </button>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${documentoBaja.cargaGuardadaUsu}">
                                                            <button type="button" class="btn btn-default redireccionarVentana" title="Ver documento" value="${pageContext.request.contextPath}/colaborador/ver-documento-de-baja/Cliente/${model.agremiado.idAgremiado}/${model.solicitudBaja.idSolicitudBaja}/${documentoBaja.tipoDocumentoObj.idTipoDocumento}.htm">
                                                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                                            </button>
                                                        </c:if>
                                                    </td>
                                                </tr>  
                                            </c:forEach>
                                        </tbody>
                                </table>
                            </div>
                            </div>
                                </div>
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
