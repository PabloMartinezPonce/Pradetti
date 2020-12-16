<%-- 
    Document   : kardexDeLaIncidencia
    Created on : 8/12/2016, 12:22:33 PM
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
    <title>Detalles de la incidencia</title>
  </head>

  <body id="home">

    <div class="blog-masthead hidden-print">
      <div class="container">
        <nav class="blog-nav">
          <a class="blog-nav-item active" href="#home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Inicio</a>
          <a class="blog-nav-item" href="#" onclick="window.close();"><span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>&nbsp;Salir</a>
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
                    <h1 class="headland"><span class="glyphicon glyphicon-flag" aria-hidden="true"></span><b>&nbsp;&nbsp;&nbsp;Incidencia</b></h1>
                </div>
                <div class="col-xs-12">
                    <h2 class="headland-two">                    
                            <span class="${(model.incidencia.status)?"glyphicon glyphicon-registration-mark":"glyphicon glyphicon-unchecked"}" title="${(model.incidencia.status)?"La incidencia ya fue revisada":"La incidecia aun no es revisada"}" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
                        <i><fmt:formatDate value="${model.incidencia.fechaIncidencia}" pattern="dd - MM - yyyy" /></i>
                    </h2>
                </div>
            </div>
          </div>
        </div>
        <div class="row">
            <section class="section" id="personales">
                <div class="sec-header">Datos de la incidencia
                    <div class="btn-group" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12  col-sm-6">
                            <div class="descriptor">
                                <span class="title"><span title="Cliente" class="glyphicon glyphicon-queen" aria-hidden="true"></span></span>
                                <span class="content lengthText"  >&nbsp;${model.incidencia.clienteObj.nombreEmpresa}&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">RFC</span>
                                <span class="content lengthText"  >&nbsp;${model.incidencia.clienteObj.rfc}&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12  col-sm-6">
                            <div class="descriptor">
                                <span class="title"><span title="Colaborador" class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
                                <span class="content lengthText"  >&nbsp;${model.incidencia.agremiadoObj.datosPersonales.nombre}&nbsp;${model.incidencia.agremiadoObj.datosPersonales.apellidoPaterno}&nbsp;${((model.incidencia.agremiadoObj.datosPersonales.apellidoMaterno==null)?"":model.incidencia.agremiadoObj.datosPersonales.apellidoMaterno)}&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">RFC</span>
                                <span class="content lengthText">&nbsp;${model.incidencia.agremiadoObj.datosPersonales.rfc}&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="descriptor">
                                <span class="title">Identificador de la incidencia</span>
                                <span class="content lengthText">#&nbsp;${model.incidencia.idIncidencia}&nbsp;</span>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">Tipo de incidencia</span>
                                <c:forEach items="${model.tipoIncidencias}" var="tipoIncidencia" >
                                    <c:if test="${tipoIncidencia.idTipoIncidencia == model.incidencia.tipoIncidenciaObj.idTipoIncidencia}">
                                        <span class="content lengthText">&nbsp;${tipoIncidencia.descripcion}&nbsp;</span>
                                    </c:if>                 
                            </c:forEach>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">Cantidad</span>
                                <c:forEach items="${model.tipoIncidencias}" var="tipoIncidencia" >
                                    <c:if test="${tipoIncidencia.idTipoIncidencia == model.incidencia.tipoIncidenciaObj.idTipoIncidencia}">                                        
                                        <span class="content lengthText" >&nbsp;${(tipoIncidencia.criterio)?"+":"-"} ${(tipoIncidencia.dias)?" ":" $"}&nbsp;${model.incidencia.cantidad}&nbsp;${(tipoIncidencia.dias)?" Día(s)":""}&nbsp;</span>
                                    </c:if>                 
                                </c:forEach>
                             </div>
                        </div>    
                        <div class="col-xs-12">
                            <div class="descriptor">
                                <span class="title">Comentarios</span>
                                <span class="content lengthText" >&nbsp;${model.incidencia.comentarios}&nbsp;</span>
                                
                            </div>
                        </div>    
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title" title="Fecha de registro de la incidencia">Fecha de registro</span>
                                <span class="content lengthText" >&nbsp;<fmt:formatDate value="${model.incidencia.fechaRegistro}" pattern="dd - MM - yyyy HH:mm" />&nbsp;</span>
                                
                            </div>
                        </div>    
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title" title="Última vez en la que se modificó la incidencia">Fecha de modificación</span>
                                <span class="content lengthText" >&nbsp;<fmt:formatDate value="${model.incidencia.fechaModificacion}" pattern="dd - MM - yyyy HH:mm" />&nbsp;</span>
                                
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