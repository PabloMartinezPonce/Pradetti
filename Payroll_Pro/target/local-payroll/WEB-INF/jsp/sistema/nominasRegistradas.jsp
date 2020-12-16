<%-- 
    Document   : nominasRegistradas
    Created on : 15/11/2016, 12:33:09 PM
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
<title>Payroll - Sistema</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-knight" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sistema      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<%@include file="../fragmentos/menuSistema.jsp" %>
<div class="col-xs-12 lnbrk"></div>
<h2 class="selectAction">Recibos registrados</h2>       
<div class="sistemaContainer"> 
    <div class="row">
        <div class="col-xs-12 lnbrk"></div>
        <form method="post" action="${pageContext.request.contextPath}/sistema/vista-general-de-nomina.htm">
            <div class="col-xs-12 col-md-4">
                <div class="input-group">
                    <span class="input-group-addon" >Cliente</span>
                      <select  name="cliente" class="form-control" placeholder="Campo requerido"  required>
                        <option value="" ></option>
                        <c:forEach items="${model.clientes}" var="cliente">
                            <option value="${cliente.idCliente}">${cliente.nombreEmpresa}</option>
                          </c:forEach>
                      </select>                       
                </div>                
            </div>
            <div class="col-xs-12 col-md-4">
                <div class="input-group">
                    <span class="input-group-addon" >Registro entre el</span>
                    <input   type="date" class="form-control" placeholder="Campo requerido" name="diaRegistroDesde" required title="Día en que se registró el recibo">
                </div>                
            </div>
            <div class="col-xs-12 col-md-3">
                <div class="input-group">
                    <span class="input-group-addon" > Y el </span>
                    <input   type="date" class="form-control" placeholder="Campo requerido" name="diaRegistroHasta" required title="Día en que se registró el recibo">
                </div>                
            </div>
            <div class="col-xs-12 col-md-1">
                <button type="submit" class="btn btn-success btn-helper">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 
                </button>         
            </div>
        </form>
    </div>
    <c:if test="${model.verDetallesDeNomina}">
        <c:if test="${(model.cliente != null)}">
            <div class="row">
            <div class="col-xs-12 lnbrk"></div>    
            <div class="col-xs-12 col-md-6">
                <div class="row">
                        <div class="row">            
                            <div class="col-xs-12">
                                <h4><span class="label label-default">Cliente</span>&nbsp;${model.cliente.nombreEmpresa}</h4>
                            </div>
                            <div class="col-xs-12">
                                <h4><span class="label label-default">RFC</span>&nbsp;${model.cliente.rfc}</h4>
                            </div>
                            <div class="col-xs-12">
                                <h4><span class="label label-default">Recibos registrados entre el</span>&nbsp;<fmt:formatDate value="${model.fechaA}" pattern="dd/MM/yyyy " />&nbsp;<span class="label label-default">Y el</span>&nbsp;&nbsp;<fmt:formatDate value="${model.fechaB}" pattern="dd/MM/yyyy " /></h4>
                            </div>
                        </div>
                </div>  
            </div>
            <div class="col-xs-12 col-md-6">
            <div class="row">  
                     <sec:authorize access="hasAnyRole('Recibos_Almacenados')">                          
                <div class="col-xs-12">            
                    <div class="input-group">
                        <span class="input-group-addon" >Opciones generales del los recibos</span>
                        <div class="btn-group btn-group-lg  hidden-btn-action" role="group" aria-label="..." style="display:none">
                                <input type="hidden" name="cliente" value="${model.cliente.idCliente}" id="cliente">
                                <input type="hidden" name="diaRegistroDesde" value="${model.fechaAString}" id="diaRegistroDesde">
                                <input type="hidden" name="diaRegistroHasta" value="${model.fechaBString}" id="diaRegistroHasta">
                                <button id="btnNvrRcbsLsClbrdrs" type="button" class="btn btn-default btn-lg " title="Enviar todos los recibos por correo electrónico a los colaboradores" value="${pageContext.request.contextPath}/sistema/enviar-recibos-email.htm">
                                              <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                </button>
                                <button id="btnGnrrRcbsZp" type="button" class="btn btn-default btn-lg " title="Enviarme todos los recibos (PDF) en un .zip" value="${pageContext.request.contextPath}/sistema/generar-recibos-pdf-zip.htm">
                                              <span class="glyphicon glyphicon-compressed" aria-hidden="true"></span>
                                </button>
                                <button id="btnNvrmRcbsPDF" type="button" class="btn btn-default btn-lg " title="Enviarme todos los recibos en un archivo (PDF)" value="${pageContext.request.contextPath}/sistema/generar-recibos-pdf.htm">
                                              <span class="glyphicon glyphicon-file" aria-hidden="true"></span>
                                </button>
                        </div>                
                    </div>   
                </div>
                     </sec:authorize>                      
              </div>
            </div>
        </div>
        </c:if>     
        <div class="row">
            <div class="col-xs-12 lnbrk"></div>
            <div class="col-xs-12">
                    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Colaborador</th>
                                <th>RFC</th>
                                <th>Sindicato</th>
                                <th >Periodo</th>
                                <th class="hidden-xs">&nbsp;&nbsp;&nbsp;&nbsp;Opciones&nbsp;&nbsp;&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                     <sec:authorize access="hasAnyRole('Recibos_Almacenados')">
                            <c:forEach items="${model.recibos}" var="recibo">
                                    <tr>
                                        <td>${recibo.agremiado.idAgremiado}</td>
                                        <td><b>${recibo.agremiado.datosPersonales.nombre}&nbsp;${recibo.agremiado.datosPersonales.apellidoPaterno}&nbsp;${((recibo.agremiado.datosPersonales.apellidoMaterno==null)?"":recibo.agremiado.datosPersonales.apellidoMaterno)}</b></td>
                                        <td>${recibo.agremiado.datosPersonales.rfc}</td>
                                        <td>${recibo.sindicato.nombreCorto}</td>
                                        <td><b><fmt:formatDate value="${recibo.diaInicial}" pattern="yyyy - MM - dd" /> &nbsp;-&nbsp; <fmt:formatDate value="${recibo.diaFinal}" pattern="yyyy - MM - dd" /> </b></td>
                                        <td class="hidden-xs">
                                            <div class="btn-group hidden-btn-action" role="group" aria-label="..." style="display:none">
                                            <button type="button" class="btn btn-default redireccionarVentana-td" title="Ver" value="${pageContext.request.contextPath}/sistema/detalle-del-recibo/${recibo.idRecibo}.htm">
                                                <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                              </button>                                          
                                            <button type="button" class="btn btn-default btnFrmNvrRcbPrCrr btnFrmNvrRcbPrCrr-td" title="Enviar por correo electrónico" itemid="${recibo.idRecibo}" value="${pageContext.request.contextPath}/sistema/enviar-recibo-por-email.htm"> 
                                                  <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                                              </button>                                     
                                                <button type="button" class="btn btn-default redireccionarVentana-td" title="Descargar recibo (PDF)" value="${pageContext.request.contextPath}/sistema/ver-recibo-pdf/${recibo.idRecibo}.htm">
                                                  <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>
                                              </button>
                                            </div>
                                        </td>
                                    </tr>
                            </c:forEach>
                     </sec:authorize>
                        </tbody>
                    </table>
                </div>
        </div>
      </c:if>                  
</div>
<div class="col-xs-12 lnbrk"></div>    
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>   
        <c:if test="${model.mostrarVentana}">
            <script>
                getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
                muestaOcultos();
            </script>
        </c:if>
            
    </body>
</html>
