<%-- 
    Document   : detalleDeNomina
    Created on : 15/11/2016, 12:34:15 PM
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
    <title>Detalles del recibo</title>
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
                    <h1 class="headland"><span class="glyphicon glyphicon-knight" aria-hidden="true"></span><b>&nbsp;&nbsp;&nbsp;Sistema</b></h1>
                </div>
                <div class="col-xs-12">
                    <h2 class="headland-two">
                        <i>Vista detallada del recibo</i>
                    </h2>
                </div>
            </div>
          </div>
        </div>
        <div class="row">
            <section class="section" >
                <div class="sec-header">Número de recibo&nbsp;:&nbsp;${model.recibo.idRecibo}
                    <div class="btn-group" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="descriptor">
                                <span class="title">Nombre</span>
                                <span class="content lengthText" id="nmbr" ><b>&nbsp;${model.recibo.agremiado.datosPersonales.nombre}&nbsp;${model.recibo.agremiado.datosPersonales.apellidoPaterno}&nbsp;${((model.recibo.agremiado.datosPersonales.apellidoMaterno==null)?"":model.recibo.agremiado.datosPersonales.apellidoMaterno)}&nbsp;</b></span>                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="descriptor">
                                <span class="title">RFC</span>
                                <span class="content lengthText" id="nmbr" >&nbsp;<b>${model.recibo.agremiado.datosPersonales.rfc}</b>&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="descriptor">
                                <span class="title">Día en que fúe registrado el recibo</span>
                                <span class="content lengthText" id="nmbr" >&nbsp;<b><fmt:formatDate value="${model.recibo.diaDeRegistro}" pattern="dd - MM - yyyy " /></b>&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Cliente</span>
                                <span class="content lengthText" id="nmbr" >&nbsp;<b>${model.recibo.clientes.nombreEmpresa}</b>&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="descriptor">
                                <span class="title">Sindicato</span>
                                <span class="content lengthText" >&nbsp;<b>${model.recibo.sindicato.nombreSindicato}</b>&nbsp;</span>                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="descriptor">
                                <span class="title">Periodo laboral</span>
                                <span class="content lengthText" >&nbsp;Del&nbsp;<b><fmt:formatDate value="${model.recibo.diaInicial}" pattern="dd - MM - yyyy " /></b>&nbsp;al&nbsp;<b><fmt:formatDate value="${model.recibo.diaFinal}" pattern="dd - MM - yyyy " /></b>&nbsp;</span>                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="descriptor">
                                <span class="title">Días laborados</span>
                                <span class="content lengthText" ><b>&nbsp;&nbsp;${model.recibo.diasTrabajados}&nbsp;&nbsp;</b></span>                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="descriptor">
                                <span class="title">Total nominal</span>
                                <span class="content lengthText" ><b>&nbsp;$&nbsp;${model.recibo.totalNomina}&nbsp;MXN&nbsp;</b></span>                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="descriptor">
                                <span class="title">Total Sindical</span>
                                <span class="content lengthText" ><b>&nbsp;$&nbsp;${model.recibo.totalSindicato}&nbsp;MXN&nbsp;</b></span>                                
                            </div>
                        </div>
                    <c:choose>
                        <c:when test="${model.recibo.deduccionesConcepto != null}">
                            <div class="col-xs-12 col-md-9">
                                <div class="descriptor">
                                    <span class="title">Deducción concepto</span>
                                    <span class="content lengthText" ><b>&nbsp;${model.recibo.deduccionesConcepto}&nbsp;</b></span>                                
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-3">
                                <div class="descriptor">
                                    <span class="title">Importe</span>
                                    <span class="content lengthText" ><b>&nbsp;$&nbsp;${model.recibo.deduccionesImporte}&nbsp;MXN&nbsp;</b></span>                                
                                </div>
                            </div>
                            <div class="col-xs-12 ">
                                <div class="descriptor">
                                    <span class="title">Neto a Pagar</span>
                                    <span class="content lengthText" ><b>&nbsp;&nbsp;$&nbsp;${((model.recibo.totalNomina+model.recibo.totalSindicato)-model.recibo.deduccionesImporte)}&nbsp;MXN&nbsp;&nbsp;</b></span>                                
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-xs-12 col-md-9">
                                <div class="descriptor">
                                    <span class="title">Deducción concepto</span>
                                    <span class="content lengthText" ><b>&nbsp;[Sin Deducciones Registradas]&nbsp;</b></span>                                
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-3">
                                <div class="descriptor">
                                    <span class="title">Importe</span>
                                    <span class="content lengthText" ><b>&nbsp;No Aplica&nbsp;</b></span>                                
                                </div>
                            </div>
                            <div class="col-xs-12 ">
                                <div class="descriptor">
                                    <span class="title">Neto a Pagar</span>
                                    <span class="content lengthText" ><b>&nbsp;&nbsp;$&nbsp;${((model.recibo.totalNomina+model.recibo.totalSindicato))}&nbsp;MXN&nbsp;&nbsp;</b></span>                                
                                </div>
                            </div>                            
                        </c:otherwise>
                    </c:choose>
                        
                        
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