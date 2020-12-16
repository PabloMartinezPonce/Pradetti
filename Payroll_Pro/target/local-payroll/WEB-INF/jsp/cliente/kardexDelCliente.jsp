<%-- 
    Document   : kardexDelCliente
    Created on : 15/11/2016, 11:04:41 AM
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
    <title>Detalles de cliente</title>

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
          <a class="blog-nav-item hidden-xs" href="#users">Usuarios</a>
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
                    <h1 class="headland"><span class="glyphicon glyphicon-queen" aria-hidden="true"></span><b>&nbsp;&nbsp;&nbsp;Cliente</b></h1>
                </div>
                <div class="col-xs-12">
                    <h2 class="headland-two">
                        <c:if test="${!(model.cliente.status)}">                            
                            <span class="glyphicon glyphicon-info-sign" title="El cliente se encuentra desactivado" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;
                        </c:if>
                        <i>${model.cliente.nombreEmpresa}</i>
                    </h2>
                </div>
            </div>
          </div>
        </div>
        <div class="row">
            <section class="section" id="personales">
                <div class="sec-header">Datos personales
                    <div class="btn-group" role="group" aria-label="...">
                        <a type="button" class="btn" href="${pageContext.request.contextPath}/cliente/${model.cliente.idCliente}/kardex/pdf.htm" target="_blank">
                          <span class="glyphicon glyphicon-print hidden-print" aria-hidden="true" title="Imprimir Kardex"></span>
                      </a>
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up hidden-print" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Representante legal</span>
                                <span class="content" id="rpsntntLgl" >${model.cliente.representanteLegal}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">RFC</span>
                                <span class="content" id="rfc">${model.cliente.rfc}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-5 col-md-5">
                            <div class="descriptor">
                                <span class="title">Fecha de Registro</span>
                                <span class="content" id="fchDRgstr"><fmt:formatDate value="${model.cliente.fechaRegistroPublico}" pattern=" dd - MM - yyyy " /></span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7 col-md-7">
                            <div class="descriptor">
                                <span class="title">Salario minimo establecido</span>
                                <span class="content" id="slrMnmStblcd">$&nbsp; ${model.zonasm.salario}&nbsp;MXN</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class="descriptor">
                                <span class="title">Zona salarial</span>
                                <span class="content" id="slrMnmStblcd">&nbsp; ${model.zonasm.zona}&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-6">
                            <div class="descriptor">
                                <span class="title">Año Salarial</span>
                                <span class="content" id="slrMnmStblcd">&nbsp; ${model.zonasm.anio}&nbsp;</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Giro comercial</span>
                                <span class="content" id="grCmrcl">${model.cliente.giro}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Tipo de sociedad</span>
                                <span class="content" id="tpDScdd">${model.cliente.tipoSociedad}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Objeto Social</span>
                                <span class="content" id="fScl">${model.cliente.finSocial}</span>
                                
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
                          <span class="slideUpDown glyphicon glyphicon-menu-up hidden-print" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Notario</span>
                                <span class="content" id="nmbrDlNtr">${model.actanotarial.notario}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Notaria</span>
                                <span class="content" id="nmrDNtr">${model.actanotarial.numeroNotarial}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Dirección de la notaria</span>
                                <span class="content lengthText" id="drccndlNtr">${model.actanotarial.direccionNotario}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Instrumento</span>
                                <span class="content" id="nstrmnt">${model.actanotarial.instrumento}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Volumen</span>
                                <span class="content" id="vlmn">${model.actanotarial.volumen}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Fecha volumen</span>
                                <span class="content" id="fchVlmn"><fmt:formatDate value="${model.actanotarial.fechaVolumen}" pattern=" dd - MM - yyyy " /></span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8">
                            <div class="descriptor">
                                <span class="title">Documento Legal</span>
                                <span class="content">${(model.actanotarial.urlDocumento == null)?"N/D":model.actanotarial.urlDocumento.substring(30)}</span>
                                <c:if test="${(  model.actanotarial.urlDocumento != null )}">
                                    <a class="downloader hidden-print"  target="_blank" href="${pageContext.request.contextPath}/cliente/${model.cliente.idCliente}/ActaNotarial.htm"  title="Ver Acta Notarial"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></a>
                                </c:if>
                            </div>
                        </div>                        
                        <div class="col-xs-12 col-sm-4">
                            <div class="descriptor">
                                <span class="title">Poder legal</span>                                      
                                <button class="btn btn-defaul" id="btnPdrLgl" ${ (model.actanotarial.tienePoderLegal )?"":"disabled" }><b>${ (model.actanotarial.tienePoderLegal )?"Ver":"N/A" }</b></button>                                
                            </div>
                        </div> 
                    </div>
                    <c:if test="${ model.actanotarial.tienePoderLegal}">
                    <div class="row sec-body" id="poderLegal">
                        <div class="col-xs-12">
                            <h2>Poder Legal</h2>
                        </div>                        
                        <div class="col-xs-12 col-sm-8">
                            <div class="descriptor">
                                <span class="title">Representante</span>
                                <span class="content">${model.poderlegal.representanteLegal}</span>
                            </div>
                        </div>                         
                        <div class="col-xs-12 col-sm-4">
                            <div class="descriptor">
                                <span class="title">RFC</span>
                                <span class="content">${model.poderlegal.rfc}</span>
                            </div>
                        </div> 
                        
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Notario</span>
                                <span class="content" id="nmbrDlNtr">${model.poderlegal.notario}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Notaria</span>
                                <span class="content" id="nmrDNtr">${model.poderlegal.numeroNotarial}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Dirección de la notaria</span>
                                <span class="content lengthText" id="drccndlNtr">${model.poderlegal.direccionNotario}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-12 col-md-12">
                            <div class="descriptor">
                                <span class="title">Instrumento</span>
                                <span class="content" id="nstrmnt">${model.poderlegal.instrumento}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Volumen</span>
                                <span class="content" id="vlmn">${model.poderlegal.volumen}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Fecha volumen</span>
                                <span class="content" id="fchVlmn"><fmt:formatDate value="${model.poderlegal.fechaVolumen}" pattern=" dd - MM - yyyy " /></span>
                                
                            </div>
                        </div>                        
                        <div class="col-xs-12 col-sm-8">
                            <div class="descriptor">
                                <span class="title">Documento Legal</span>
                                <span class="content">${(model.poderlegal.urlDocumento == null )?"N/D":model.poderlegal.urlDocumento.substring(30)}</span> 
                                <c:if test="${model.poderlegal.urlDocumento != null}">
                                    <a class="downloader hidden-print" target="_blank"  href="${pageContext.request.contextPath}/cliente/${model.cliente.idCliente}/PoderLegal.htm" title="Ver Poder Legal"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    </c:if>                           
                </div>
                <div class="sec-footer"></div>
            </section>
             <c:forEach items="${model.empresasDomicilioList}" var="objEmpresaDomicilio">
                 <c:if test="${objEmpresaDomicilio.tipoDomicilioObj == 1}">
                <section class="section" id="domicilioF">
                   <div class="sec-header">Domicilio fiscal
                       <div class="btn-group" role="group" aria-label="...">
                         <button type="button" class="btn">
                             <span class="slideUpDown glyphicon glyphicon-menu-up hidden-print" aria-hidden="true"></span>
                         </button>
                       </div>
                   </div>
                   <div class="sec-body">
                       <div class="row">
                           <div class="col-xs-12 col-sm-8 col-md-8">
                               <div class="descriptor">
                                   <span class="title">Calle</span>
                                   <span class="content" id="cllFscl">${objEmpresaDomicilio.calle}</span>
                                   
                               </div>
                           </div>
                           <div class="col-xs-12 col-sm-4 col-md-4">
                               <div class="descriptor">
                                   <span class="title">Número</span>
                                   <span class="content" id="nmrXtrrNtrrFscl">${objEmpresaDomicilio.numero}</span>
                                   
                               </div>
                           </div>
                           <div class="col-xs-12 col-sm-8 col-md-8">
                               <div class="descriptor">
                                   <span class="title">Colonia</span>
                                   <span class="content" id="clnFscl">${objEmpresaDomicilio.colonia}</span>
                                   <a class="isOk" title="Valor establecido correctamente"></a>
                               </div>
                           </div>
                           <div class="col-xs-12 col-sm-4 col-md-4">
                               <div class="descriptor">
                                   <span class="title">Codigo Postal</span>
                                   <span class="content" id="cdgPstlFscl" >${objEmpresaDomicilio.codigoPostal}</span>

                               </div>
                           </div>
                           <div class="col-xs-12 col-sm-7 col-md-7">
                               <div class="descriptor">
                                   <span class="title">Ciudad</span>
                                   <span class="content" id="cddFscl">${objEmpresaDomicilio.ciudad}</span>

                               </div>
                           </div>
                           <div class="col-xs-12 col-sm-7 col-md-5">
                               <div class="descriptor">
                                   <span class="title">Estado</span>
                                   <span class="content" id="stdFscl">${objEmpresaDomicilio.estado}</span>
                                   <a class="isOk" title="Valor establecido correctamente"></a>
                               </div>
                           </div>
                       </div>
                   </div>
                   <div class="sec-footer"></div>
            </section>                                   
            </c:if>
            <c:if test="${objEmpresaDomicilio.tipoDomicilioObj == 2}">
            <section class="section" id="domicilioN">
                <div class="sec-header">Domicilio de notificación
                    <div class="btn-group" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up hidden-print" aria-hidden="true"></span>
                      </button>
                    </div>
                </div>
                <div class="sec-body">
                    <div class="row">
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Calle</span>
                                <span class="content" id="cllNtfccn">${objEmpresaDomicilio.calle}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Número</span>
                                <span class="content" id="nmrXtrrNtrrNtfccn">${objEmpresaDomicilio.numero}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-8 col-md-8">
                            <div class="descriptor">
                                <span class="title">Colonia</span>
                                <span class="content" id="clnNtfccn">${objEmpresaDomicilio.colonia}</span>
                                <a class="isOk" title="Valor establecido correctamente"></a>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4 col-md-4">
                            <div class="descriptor">
                                <span class="title">Codigo Postal</span>
                                <span class="content" id="cdgPstlNtfccn" >${objEmpresaDomicilio.codigoPostal}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7 col-md-7">
                            <div class="descriptor">
                                <span class="title">Ciudad</span>
                                <span class="content" id="cddNtfccn">${objEmpresaDomicilio.ciudad}</span>
                                
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7 col-md-5">
                            <div class="descriptor">
                                <span class="title">Estado</span>
                                <span class="content" id="stdNtfccn">${objEmpresaDomicilio.estado}</span>
                                <a class="isOk" title="Valor establecido correctamente"></a>
                            </div>
                        </div>                        
                    </div>
                </div>
                <div class="sec-footer"></div>
            </section>            
             </c:if>
             </c:forEach>
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
                                <span class="title">&nbsp;Contratista</span>
                               <c:forEach var="contratista" items="${model.contratistas}">
                                    <c:if test="${(objContratoEmpresas.contratistaObj==contratista.key)}">
                                        <span class="content" >${contratista.value}</span>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 p-detalles-contratos-realizados">
                            <div class="descriptor">
                                <span class="title">Fecha del Contrato</span>
                                <span class="content"><fmt:formatDate value="${objContratoEmpresas.fecha}" pattern=" dd/MM/yyyy " /></span>
                                <a class="downloader hidden-print" target="_blank" title="Descargar Archivo" href="${pageContext.request.contextPath}/sistema/contrato-contratista-cliente/${objContratoEmpresas.idContratoEmpresas}/pdf.htm"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span></a>
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
            <section class="section" id="users">
                <div class="sec-header">Usuarios del cliente
                    <div class="btn-group hidden-print" role="group" aria-label="...">
                      <button type="button" class="btn">
                          <span class="slideUpDown glyphicon glyphicon-menu-up hidden-print" aria-hidden="true"></span>
                      </button>
            <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Usuarios_De_Cliente')">
                      <c:if test="${(model.cliente.status)}">
                              <button id="addUserBtn" type="button" class="btn btn-default hidden-xs hidden-sm" title="Agregar usuario de cliente">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            </button>               
                      </c:if>
            </sec:authorize>
                    </div>
                </div>
                <div class="sec-body">
                    <form id="formUsuarioCliente" method="post" action="${pageContext.request.contextPath}/" class="hidden-xs hidden-sm">
                    <div class="row hidden-print" id="addUser">
                        <div class="col-xs-12">
                            <h3 id="dscptrUsuario">Agregar un nuevo usuario al cliente</h3>
                        </div>
                        <div class="col-xs-12" hidden="hidden">
                            <div class="input-group">
                              <span class="input-group-addon" id="clnt">Rol</span>
                              <input type="hidden" value="" name="vlr" id="vlrID" required>
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="input-group">
                              <span class="input-group-addon" id="nmbrCmplt">Nombre completo</span>
                              <input type="text" class="form-control" id="nmbrCmpltID" placeholder="Campo requerido" aria-describedby="nmbrCmplt" name="nmbrCmplt" required title="Nombre completo del usuario" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7">
                            <div class="input-group">
                              <span class="input-group-addon" id="crrLctrnc">Correo electrónico</span>
                              <input type="email" class="form-control" id="crrLctrncID"  placeholder="Campo requerido" aria-describedby="crrLctrnc" name="crrLctrnc" required title="Correo electrónico del usuario"  pattern="[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)*[.][a-zA-Z]{1,10}" minlength="5">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-5">
                            <div class="input-group">
                              <span class="input-group-addon" id="rls">Rol</span>
                              <select id="selectUsuario"name="rls" class="form-control" placeholder="Campo requerido" aria-describedby="rls"  required>
                                  <c:forEach items="${model.roles}" var="rol">
                                      <option value="${rol.idRol}">${rol.nombre}</option>
                                  </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12" hidden="hidden">
                            <div class="input-group">
                              <span class="input-group-addon" id="clnt">Rol</span>
                              <input type="hidden" value="${model.cliente.idCliente}" name="clnt" id="clntID" required>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                            </div>
                                <div class="col-xs-12 col-sm-4">
            <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Usuarios_De_Cliente')">
                                    <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                        <b>Guardar</b>
                                    </button>
            </sec:authorize>
                                </div>                         
                            <div class="col-xs-12 col-sm-4">
                                <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper">
                                    <b>Cancelar</b>
                                </button>
                            </div>
                    </div>
                </form>
                    <c:forEach items="${model.usuarioList}" var="usuarioObj">
                    <div class="row usersRow">
                        <div class="col-xs-12 col-sm-7">
                            <div class="descriptor">
                                <span class="title vlr">${usuarioObj.idUsuario}</span>
                                <span class="title">Nombre</span>
                                <span class="content nmbrUsuario" >${usuarioObj.nombre}</span>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-5">
                            <div class="descriptor">
                                <span class="title">Rol</span>
                                <span class="content">${model.mapRoles.get(usuarioObj.rol)}</span>
                                <span class="rlUsuario" hidden="hidden">${usuarioObj.rol}</span>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-9">
                            <div class="descriptor">
                                <span class="title">Correo</span>
                                <span class="content crrUsuario">${usuarioObj.correoElectronico}</span>
                               
                            </div>
                        </div>
            <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Usuarios_De_Cliente')">
                    <c:if test="${(model.cliente.status)}">
                        <div class="col-xs-12 col-sm-3 btnGrpUser hidden-print hidden-xs hidden-sm">
                            <div class="col-xs-1 col-sm-2  inline">
                                <form name="formCtvr" method="post" action="${pageContext.request.contextPath}/cliente/activar-usuario.htm">
                                     <input type="hidden" value="${usuarioObj.idUsuario}" name="vlr" required>
                                    <input type="hidden" value="${model.cliente.idCliente}" name="clnt" required>
                                    <button type="submit" class="btn btnUser btnUserActiv" title="${ (usuarioObj.status)?"Desactivar":"Activar"}">
                                        <span class="${ (usuarioObj.status)?"glyphicon glyphicon-check":"glyphicon glyphicon-unchecked"}" aria-hidden="true"></span>
                                    </button>
                                </form>
                            </div>
                            <div class="col-xs-1 col-sm-2  inline">
                                <form name="formCmbrCntrsn" method="post" action="${pageContext.request.contextPath}/cliente/acceso-usuario.htm">
                                    <input type="hidden" value="${usuarioObj.idUsuario}" name="vlr" required>
                                    <input type="hidden" value="${model.cliente.idCliente}" name="clnt"  required>
                                    <button type="submit" class="btn btnUser btnUserPwd" title="Cambiar contraseña del usuario">
                                        <span class="glyphicon glyphicon-copyright-mark" aria-hidden="true"></span>
                                    </button>
                                </form>
                            </div>
                            <div class="col-xs-1 col-sm-2  inline">
                                <button type="button" class="btn btnUser btnUserEdit" title="Editar usuario">
                                  <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                              </button>
                            </div>
                        </div> 
                      </c:if>
            </sec:authorize>
                    </div>
                   </c:forEach>
                </div>
                    <div class="sec-footer">
                       
                    </div>
            </section>
                       
        </div>
    </div>
    <!-- ==================================================== Sección de  footer ================================================================== -->
           <%@include file="../fragmentos/pieKardex.jsp" %>
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
  </body>
</html>