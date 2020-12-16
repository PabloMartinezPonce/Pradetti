<%-- 
    Document   : cargarNomina
    Created on : 15/11/2016, 11:07:11 AM
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
<title>Cargar nómina</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Clientes  
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">
<%@include file="../fragmentos/menuClientes.jsp" %>
<h2 class="selectAction">${(model.carga)?"Cargar archivo de nómina":"Detalles de la nómina ingresada"}</h2>
    <div class="col-xs-12">
        <span class="glyphicon glyphicon-queen" aria-hidden="true" title="Cliente"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.cliente.nombreEmpresa}</span>
    </div>
    <div class="col-xs-12">
        RFC&nbsp;&nbsp;&nbsp;<span id="rfcDlClnt">${model.cliente.rfc}</span>
    </div>
        <c:if test="${model.recibos.size()>0}">
        <div class="col-xs-12">
            Sindicato&nbsp;&nbsp;&nbsp;<span id="rfcDlClnt">${model.sindicato.nombreCorto}</span>
        </div>
            <div class="col-xs-12">
            Fecha de Registro&nbsp;&nbsp;&nbsp;<span id="rfcDlClnt"><fmt:formatDate value="${model.hoy}" pattern="dd - MM - yyyy HH:mm" /></span>
        </div>
        </c:if>
    <c:if test="${(model.carga)}">
        <form action="${pageContext.request.contextPath}/cliente/detalles-de-nomina-ingresada.htm" method="post" id="formCargarArchivoDeNomina" enctype="multipart/form-data">
            <div class="col-xs-6">                    
                <div class="input-group">
                  <span class="input-group-addon">Periodo</span>
                  <span class="input-group-addon">Del</span>
                  <input type="date" class="form-control" placeholder="Campo requerido" required name="fchDsd" id="fchDsdID"   title="Fecha inicial del periodo de la nómina">
                  <input type="hidden" name="clienteId" value="${model.cliente.idCliente}">
                </div>  
            </div>
            <div class="col-xs-6">                  
                <div class="input-group">
                  <span class="input-group-addon">al</span>
                  <input type="date" class="form-control" placeholder="Campo requerido"  required name="fchHst" id="fchHstID"   title="Fecha final del periodo de la nómina">
                </div>                    
            </div>
                <div class="col-xs-6">
                <div class="input-group">
                    <span class="input-group-addon">Sindicato</span>
                      <select  name="sindicatoId" class="form-control" placeholder="Campo requerido"  required>
                        <c:forEach items="${model.sindicatos}" var="sindicatos">
                            <option value="${sindicatos.idSindicato}">${sindicatos.nombreCorto}</option>
                          </c:forEach>
                      </select>                       
                </div>         
             </div>
            <div class="col-xs-6">
                <input type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="rchvDNmn" required title="Archivo de nómina" id="rchvID" accept=".xlsx"
                                data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado">
            </div>
            <div class="col-xs-12 lnbrk"></div>
                <div class="col-xs-4 col-xs-offset-2">                    
                    <sec:authorize access="hasAnyRole('Ver_Todo','Archivo_De_Nomina')">
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper" onclick="validarCargaNomina()">
                            <b>Cargar Nómina</b>
                        </button>
                    </sec:authorize>
                </div>            
            <div class="col-xs-4 col-xs-offset-1">
                <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/cliente/clientes.htm">
                    <b>Salir</b>
                </button>
            </div>
        </form>    
    </c:if>
    <c:if test="${!(model.carga)}">
            <c:if test="${model.recibos.size()>0}">
        <div class="col-xs-12 col-md-4 col-md-offset-1">
                    <input type="hidden" name="cliente" value="${model.cliente.idCliente}" id="cliente">
                    <input type="hidden" name="diaRegistroDesde" value="${model.fechaAString}" id="diaRegistroDesde">
                    <input type="hidden" name="diaRegistroHasta" value="${model.fechaBString}" id="diaRegistroHasta">     
                    <div class="input-group">
                      <span class="input-group-addon">Enviar por correo electrónico</span>
                      <button type="button" class="btn btn-default" title="Enviar por correo electrónico los colaboradores" id="btnNvrRcbsLsClbrdrs" value="${pageContext.request.contextPath}/cliente/enviar-recibos-email.htm">
                            <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                        </button>
                    </div>  
            </div>
            <div class="col-xs-12 col-md-4">                              
                    <div class="input-group">
                      <span class="input-group-addon">Para toda la nómina </span>
                      <button type="button" class="btn btn-default" title="Enviarme todos los recibos (PDF) en un .zip" id="btnGnrrRcbsZp" value="${pageContext.request.contextPath}/cliente/generar-recibos-pdf-zip.htm">
                            <span class="glyphicon glyphicon-compressed" aria-hidden="true"></span>
                        </button>
                      <button type="button" class="btn btn-default" title="Enviarme todos los recibos en un archivo (PDF)" id="btnNvrmRcbsPDF" value="${pageContext.request.contextPath}/cliente/generar-recibos-pdf.htm">
                            <span class="glyphicon glyphicon-file" aria-hidden="true"></span>
                        </button>
                    </div>  
            </div>
        </c:if>
    <div class="col-xs-12 lnbrk"></div>      
    <h4 class="selectAction">Colaboradores encontrados dentro de la nómina ingresada</h4>
    <div class="col-xs-12 lnbrk"></div>
            <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Recibo</th>
                        <th>ID</th>
                        <th>Colaborador</th>
                        <th>Dias trabajados</th>
                        <th>Total sindical</th>
                        <!--<th>Total nominal</th>-->
                        <th >Ver</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${model.recibos}" var="recibo">
                        <tr>
                            <td>${recibo.idRecibo}</td>
                            <td><b>&nbsp;${recibo.agremiado.idAgremiado}&nbsp;</b></td>
                            <td><b>&nbsp;${recibo.agremiado.datosPersonales.nombre}&nbsp;${recibo.agremiado.datosPersonales.apellidoPaterno}&nbsp;${((recibo.agremiado.datosPersonales.apellidoMaterno==null)?"":recibo.agremiado.datosPersonales.apellidoMaterno)}&nbsp;</b></td>
                            <td>${recibo.diasTrabajados}</td>
                            <td>$&nbsp;${recibo.totalSindicato}</td>
                            <!--<td>$&nbsp;${recibo.totalNomina}</td>-->
                            <td>
                                    <button type="button" class="btn btn-default redireccionarVentana-td" title="Ver" value="${pageContext.request.contextPath}/sistema/detalle-del-recibo/${recibo.idRecibo}.htm">
                                    <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                  </button>      
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table> 
        </div>
    </c:if>
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
    </body>
</html>