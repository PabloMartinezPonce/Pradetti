<%-- 
    Document   : kardexDelSindicato
    Created on : 15/11/2016, 11:52:05 AM
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
    <title>Detalles de sindicato</title>
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
                    <h1 class="headland"><span class="glyphicon glyphicon-bishop" aria-hidden="true"></span><b>&nbsp;&nbsp;&nbsp;Sindicato</b></h1>
                </div>
                <div class="col-xs-12">
                    <h2 class="headland-two">
                        <c:if test="${!(model.sindicato.status)}">                            
                            <span class="glyphicon glyphicon-info-sign" title="El sindicato se encuentra desactivado" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
                        </c:if>
                        <i>${model.sindicato.nombreCorto}</i>
                    </h2>
                </div>
            </div>
          </div>
        </div>
        <div class="row">
            <section class="section" id="personales">
                <div class="sec-header">Datos personales
                    <div class="btn-group" role="group" aria-label="...">
<!--                        <a type="button" class="btn" href="${pageContext.request.contextPath}/colaboradores/1/kardex/pdf.htm" target="_blank">
                          <span class="glyphicon glyphicon-user hidden-print" aria-hidden="true" title="Imprimir Kardex"></span>
                      </a>-->
                        <a type="button" class="btn" href="${pageContext.request.contextPath}/sindicato/${model.sindicato.idSindicato}/kardex/pdf.htm" target="_blank">
                          <span class="glyphicon glyphicon-print hidden-print" aria-hidden="true" title="Imprimir Kardex"></span>
                      </a>
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Nombre</span>
                                <span class="content lengthText" id="nmbr" >${model.sindicato.nombreSindicato}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="descriptor">
                                <span class="title">RFC</span>
                                <span class="content lengthText" id="rfc">${model.sindicato.rfc}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">Percepciones</span>
                                <span class="content lengthText" id="prcpcns">${model.sindicato.percepciones}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">Deducciones</span>
                                <span class="content lengthText" id="ddcns">${model.sindicato.deducciones}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">Logo Izquierda</span>
                                <span class="content lengthText">${(model.sindicato.urlLogoIzquierda == null )?"Sin logo":model.sindicato.urlLogoIzquierda.substring(22)}</span>
                                <c:if test="${(model.sindicato.urlLogoIzquierda != null )}">
                                    <a class="downloader" target="_blank" title="ver logo" href="${pageContext.request.contextPath}/sindicato/${model.sindicato.idSindicato}/logo/Izquierdo.htm"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></a>
                                </c:if>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">Logo Derecha</span>
                                <span class="content lengthText">${(model.sindicato.urlLogoDerecha == null )?"Sin logo":model.sindicato.urlLogoDerecha.substring(22)}</span>
                            <c:if test="${(model.sindicato.urlLogoDerecha != null )}">
                                <a class="downloader" target="_blank" title="ver logo" href="${pageContext.request.contextPath}/sindicato/${model.sindicato.idSindicato}/logo/Derecho.htm"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></a>
                            </c:if>
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