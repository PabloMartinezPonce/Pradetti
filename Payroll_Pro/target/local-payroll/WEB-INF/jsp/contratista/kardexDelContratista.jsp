<%-- 
    Document   : kardexDelContratista
    Created on : 15/11/2016, 11:29:27 AM
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
    <title>Detalles de contratista</title>
  </head>

  <body id="home">

    <div class="blog-masthead hidden-print">
      <div class="container">
        <nav class="blog-nav">
          <a class="blog-nav-item active" href="#home"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>&nbsp;Inicio</a>
          <a class="blog-nav-item hidden-xs" href="#personales">Personales</a>
          <a class="blog-nav-item hidden-xs" href="#instrumentoN">Instrumento notarial</a>
          <a class="blog-nav-item hidden-xs" href="#domicilioF">Domicilio fiscal</a>
          <a class="blog-nav-item hidden-xs" href="#domicilioN">Domicilio notificación</a>
          <a class="blog-nav-item hidden-xs" href="#contratos">Contratos</a>
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
                    <h1 class="headland"><span class="glyphicon glyphicon-king" aria-hidden="true"></span><b>&nbsp;&nbsp;&nbsp;Contratista</b></h1>
                </div>
                <div class="col-xs-12">
                    <h2 class="headland-two">
                        <c:if test="${!(model.contratista.status)}">                            
                            <span class="glyphicon glyphicon-info-sign" title="El contratista se encuentra desactivado" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
                        </c:if>
                        <i>${model.contratista.nombreEmpresa}</i>
                    </h2>
                </div>
            </div>
          </div>
        </div>
        <div class="row">
            <section class="section" id="personales">
                <div class="sec-header">Datos personales
                    <div class="btn-group" role="group" aria-label="...">
                        <a type="button" class="btn" href="${pageContext.request.contextPath}/contratista/${model.contratista.idContratista}/kardex/pdf.htm" target="_blank">
                          <span class="glyphicon glyphicon-print hidden-print" aria-hidden="true" title="Imprimir Kardex"></span>
                      </a>
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Representante legal</span>
                                <span class="content" id="rpsntntLgl" >${model.contratista.representateLegal}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">RFC</span>
                                <span class="content" id="rfc">${model.contratista.rfc}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Cláusula adminsitrativa</span>
                                <span class="content lengthText" id="clslDmnstrtv">${model.contratista.clausulaAdministrativa}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Descripción de la cláusula</span>
                                <span class="content lengthText" id="dscpcinDLClsl">${model.contratista.descripcionClausula}</span>
                                
                            </div>
                        </div>
                        
                        
                    </div>
                </div>
                <div class="sec-footer"></div>
            </section>
            <section class="section" id="instrumentoN">
                <div class="sec-header">Instrumento notarial
                    <div class="btn-group" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                 <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Notario</span>
                                <span class="content" id="nmbrDlNtr">${model.actaNotarial.notario}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Notaria</span>
                                <span class="content" id="nmrDNtr">${model.actaNotarial.numeroNotarial}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Dirección de la notaria</span>
                                <span class="content lengthText" id="drccndlNtr">${model.actaNotarial.direccionNotario}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Instrumento</span>
                                <span class="content" id="nstrmnt">${model.actaNotarial.instrumento}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Volumen</span>
                                <span class="content" id="vlmn">${model.actaNotarial.volumen}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Fecha volumen</span>
                                <span class="content" id="fchVlmn"><fmt:formatDate value="${model.actaNotarial.fechaVolumen}" pattern="dd - MM - yyyy " /></span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8">
                            <div class="descriptor">
                                <span class="title">Documento Legal</span>
                                <span class="content">${(model.actaNotarial.urlDocumento == null)?"N/D":model.actaNotarial.urlDocumento.substring(35)}</span>
                                <c:if test="${(  model.actaNotarial.urlDocumento != null )}">
                                    <a class="downloader hidden-print"  target="_blank" href="${pageContext.request.contextPath}/contratista/${model.contratista.idContratista}/ActaNotarial.htm"  title="Ver Acta Notarial"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></a>
                                </c:if>
                            </div>
                        </div>                        
                        <div class="col-xs-12 col-sm-4">
                            <div class="descriptor">
                                <span class="title">Poder legal</span>                                      
                                <button class="btn btn-defaul" id="btnPdrLgl" ${ (model.actaNotarial.tienePoderLegal )?"":"disabled" }><b>${ (model.actaNotarial.tienePoderLegal )?"Ver":"N/A" }</b></button>                                
                            </div>
                        </div> 
                    </div>
                    <c:if test="${ model.actaNotarial.tienePoderLegal}">
                    <div class="row sec-body" id="poderLegal">
                        <div class="col-xs-12">
                            <h2>Poder Legal</h2>
                        </div>                        
                        <div class="col-xs-12 col-sm-8">
                            <div class="descriptor">
                                <span class="title">Representante</span>
                                <span class="content">${model.poderLegal.representanteLegal}</span>
                            </div>
                        </div>                         
                        <div class="col-xs-12 col-sm-4">
                            <div class="descriptor">
                                <span class="title">RFC</span>
                                <span class="content">${model.poderLegal.rfc}</span>
                            </div>
                        </div> 
                        
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Notario</span>
                                <span class="content" id="nmbrDlNtr">${model.poderLegal.notario}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Notaria</span>
                                <span class="content" id="nmrDNtr">${model.poderLegal.numeroNotarial}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Dirección de la notaria</span>
                                <span class="content lengthText" id="drccndlNtr">${model.poderLegal.direccionNotario}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Instrumento</span>
                                <span class="content" id="nstrmnt">${model.poderLegal.instrumento}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Volumen</span>
                                <span class="content" id="vlmn">${model.poderLegal.volumen}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Fecha volumen</span>
                                <span class="content" id="fchVlmn"><fmt:formatDate value="${model.poderLegal.fechaVolumen}" pattern="dd - MM - yyyy " /></span>
                                
                            </div>
                        </div>                        
                        <div class="col-xs-12 col-sm-8">
                            <div class="descriptor">
                                <span class="title">Documento Legal</span>
                                <span class="content">${(model.poderLegal.urlDocumento == null )?"N/D":model.poderLegal.urlDocumento.substring(35)}</span> 
                                <c:if test="${model.poderLegal.urlDocumento != null}">
                                    <a class="downloader hidden-print" target="_blank"  href="${pageContext.request.contextPath}/contratista/${model.contratista.idContratista}/PoderLegal.htm" title="Ver Poder Legal"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    </c:if>                           
                </div>
                <div class="sec-footer"></div>
            </section>
            <section class="section" id="domicilioF">
                <div class="sec-header">Domicilio fiscal
                    <div class="btn-group" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Calle</span>
                                <span class="content lengthText" id="cllFscl">${model.domicilioFiscal.calle}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Número</span>
                                <span class="content lengthText" id="nmrXtrrNtrrFscl">${model.domicilioFiscal.numero}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Colonia</span>
                                <span class="content lengthText" id="clnFscl">${model.domicilioFiscal.codigoPostal}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Codigo Postal</span>
                                <span class="content" id="cdgPstlFscl" >${model.domicilioFiscal.codigoPostal}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7 col-md-7">
                            <div class="descriptor">
                                <span class="title">Ciudad</span>
                                <span class="content lengthText" id="cddFscl">${model.domicilioFiscal.ciudad}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7 col-md-5">
                            <div class="descriptor">
                                <span class="title">Estado</span>
                                <span class="content lengthText" id="stdFscl">${model.domicilioFiscal.estado}</span>
                                
                            </div>
                        </div>
                        
                        
                    </div>
                </div>
                <div class="sec-footer"></div>
            </section>
            <section class="section" id="domicilioN">
                <div class="sec-header">Domicilio de notificación
                    <div class="btn-group" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Calle</span>
                                <span class="content lengthText" id="cllNtfccn">${model.domicilioNotificaciones.calle}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Número</span>
                                <span class="content lengthText" id="nmrXtrrNtrrNtfccn">${model.domicilioNotificaciones.numero}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Colonia</span>
                                <span class="content lengthText" id="clnNtfccn">${model.domicilioNotificaciones.colonia}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Codigo Postal</span>
                                <span class="content" id="cdgPstlNtfccn" >${model.domicilioNotificaciones.codigoPostal}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7 col-md-7">
                            <div class="descriptor">
                                <span class="title">Ciudad</span>
                                <span class="content lengthText" id="cddNtfccn">${model.domicilioNotificaciones.ciudad}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7 col-md-5">
                            <div class="descriptor">
                                <span class="title">Estado</span>
                                <span class="content lengthText" id="stdNtfccn">${model.domicilioNotificaciones.estado}</span>
                                
                            </div>
                        </div>                        
                    </div>
                </div>
                <div class="sec-footer"></div>
            </section>            
            <section class="section" id="contratos">
                <div class="sec-header">Contratos Realizados
                    <div class="btn-group" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up hidden-print" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <c:forEach items="${model.contratoEmpresas}" var="objContratoEmpresas">
                    <div class="row">
                        <div class="col-xs-12 col-sm-6">
                            <div class="descriptor">
                                <span class="title">&nbsp;Cliente</span>                                
                                <c:forEach var="cliente" items="${model.clientes}">
                                    <c:if test="${(objContratoEmpresas.clienteObj==cliente.key)}">
                                        <span class="content" >${cliente.value}</span>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 p-detalles-contratos-realizados">
                            <div class="descriptor">
                                <span class="title">Fecha del Contrato</span>
                                <span class="content"><fmt:formatDate value="${objContratoEmpresas.fecha}" pattern="dd/MM/yyyy " /></span>
                                <a class="downloader hidden-print" target="_blank" title="Descargar Archivo"  href="${pageContext.request.contextPath}/sistema/contrato-contratista-cliente/${objContratoEmpresas.idContratoEmpresas}/pdf.htm"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></a>
                                <a class="downloader ver-detalles-contratos-realizados hidden-print"  title="Ver detalles"><span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span></a>
                            </div>
                        </div>
                        <div class="col-xs-12 detalles-contratos-realizados hidden-print">
                                    <div class="col-xs-12">
                                        <h4>Detalles</h4>
                                    </div>
                                    <div class="col-xs-12 col-sm-6">
                                        <div class="descriptor">
                                            <span class="title">Periodo</span>
                                            <span class="content" >${objContratoEmpresas.periodo}</span>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-6">
                                        <div class="descriptor">
                                            <span class="title">Comisión</span>
                                            <span class="content" >${objContratoEmpresas.comision}</span>
                                        </div>
                                    </div>   
                                    <div class="col-xs-12 col-sm-6">
                                        <div class="descriptor">
                                            <span class="title">Informes</span>
                                            <span class="content" >${objContratoEmpresas.informes}</span>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-6">
                                        <div class="descriptor">
                                            <span class="title">Evaluaciones</span>
                                            <span class="content" >${objContratoEmpresas.evaluaciones}</span>
                                        </div>
                                    </div>   
                                    <div class="col-xs-12 col-sm-6">
                                        <div class="descriptor">
                                            <span class="title">Deposito</span>
                                            <span class="content" >${objContratoEmpresas.deposito}</span>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-6">
                                        <div class="descriptor">
                                            <span class="title">Tiempo del contrato</span>
                                            <span class="content" >${objContratoEmpresas.tiempoDelContrato}</span>
                                        </div>
                                    </div>                                        
                                <div class="col-xs-12">
                                        <h4>Testigos</h4>
                                </div>
                                <div class="col-xs-12">                                    
                                            <div class="col-xs-12 col-sm-6">                                        
                                                <div class="col-xs-12">
                                                    <div class="descriptor">
                                                        <span class="title">Del contratista</span>
                                                    </div>
                                                </div>                                          
                                                <div class="col-xs-12">
                                                    <div class="descriptor">
                                                        <span class="title">Nombre</span>
                                                        <span class="content" >${objContratoEmpresas.tcontratistaNombre}</span>
                                                    </div>
                                                </div>                                       
                                                <div class="col-xs-12 ">
                                                    <div class="descriptor">
                                                        <span class="title">Ocupación</span>
                                                        <span class="content" >${objContratoEmpresas.tcontratistaOcupacion}</span>
                                                    </div>
                                                </div>                                       
                                                <div class="col-xs-12">
                                                    <div class="descriptor">
                                                        <span class="title">Originario de</span>
                                                        <span class="content" >${objContratoEmpresas.tcontratistaOrigen}</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xs-12 col-sm-6">                                        
                                                <div class="col-xs-12">
                                                    <div class="descriptor">
                                                        <span class="title">Del cliente</span>
                                                    </div>
                                                </div>                                          
                                                <div class="col-xs-12">
                                                    <div class="descriptor">
                                                        <span class="title">Nombre</span>
                                                        <span class="content" >${objContratoEmpresas.tclienteNombre}</span>
                                                    </div>
                                                </div>                                       
                                                <div class="col-xs-12 ">
                                                    <div class="descriptor">
                                                        <span class="title">Ocupación</span>
                                                        <span class="content" >${objContratoEmpresas.tclienteOcupacion}</span>
                                                    </div>
                                                </div>                                       
                                                <div class="col-xs-12">
                                                    <div class="descriptor">
                                                        <span class="title">Originario de</span>
                                                        <span class="content" >${objContratoEmpresas.tclienteOrigen}</span>
                                                    </div>
                                                </div>
                                            </div>
                                </div>
                                 <div class="col-xs-12">
                                     <h4>${(objContratoEmpresas.salarioUnicoProfesionalList.size()>0)?"S":"Sin s"}alarios únicos profesionales</h4>
                                </div>
                                <c:forEach items="${objContratoEmpresas.salarioUnicoProfesionalList}" var="sups">
                                <div class="col-xs-12 col-sm-6">
                                    <div class="descriptor">
                                        <span class="title">${sups.oficio}&nbsp;</span>
                                        <span class="content" >$&nbsp;${sups.pesosDiarios}&nbsp;MXN&nbsp;</span>
                                    </div>
                                </div>  
                                </c:forEach>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <div class="sec-footer"></div>
            </section>   
                       
        </div>
    </div>
    <!-- ==================================================== Sección de  footer ================================================================== -->
           <%@include file="../fragmentos/pieKardex.jsp" %>
  </body>
</html>