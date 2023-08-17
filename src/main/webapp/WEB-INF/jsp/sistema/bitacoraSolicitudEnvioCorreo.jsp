<%-- 
    Document   : bitacoraSolicitudEnvioCorreo
    Created on : 12/10/2017, 01:20:21 PM
    Author     : Gabriela Jaime Juarez
--%>
<%response.setHeader("pragma", "no-cache");              
 response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
 response.setHeader("Expires", "0");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../fragmentos/cabecera.jsp" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<h2 class="selectAction">Bitacora envio correos</h2>       
<div class="sistemaContainer"> 
    <div class="row">
        <div class="col-xs-12 lnbrk"></div>        
        <sec:authorize access="hasAnyRole('Bitacora_Correos_Enviados')">
        <form method="post" action="${pageContext.request.contextPath}/sistema/vista-general-bitacora-correos.htm">
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
                    <span class="input-group-addon" >Enviados entre el</span>
                    <input   type="date" class="form-control" placeholder="Campo requerido" name="diaRegistroDesde" required title="Día en que se registró el recibo">
                </div>                
            </div>
            <div class="col-xs-12 col-md-3">
                <div class="input-group">
                    <span class="input-group-addon" > Y el </span>
                    <input   type="date" class="form-control" placeholder="Campo requerido" name="diaRegistroHasta" required title="Día en que se registó el recibo">
                </div>                
            </div>
            <div class="col-xs-12 col-md-1">
                <button type="submit" class="btn btn-success btn-helper">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 
                </button>         
            </div>
        </form>
        </sec:authorize>            
    </div>
    <c:if test="${model.verDetallesDeEnvio}">
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
                                <h4><span class="label label-default">Recibos enviados entre el</span>&nbsp;<fmt:formatDate value="${model.fechaA}" pattern="dd/MM/yyyy " />&nbsp;<span class="label label-default">Y el</span>&nbsp;&nbsp;<fmt:formatDate value="${model.fechaB}" pattern="dd/MM/yyyy " /></h4>
                            </div>
                        </div>
                </div>  
            </div>
        </div>
        </c:if>     
        <div class="row">
            <div class="col-xs-12 lnbrk"></div>
            <div class="col-xs-12">
        <sec:authorize access="hasAnyRole('Bitacora_Correos_Enviados')">
                    <table id="tblLtsPrcs" class="table" cellspacing="0" width="100%">
                        <thead>
                            <tr>
                                <th>ID Recibo</th>
                                <th>Colaborador</th>
                                <th>Fecha</th>
                                <th>Observaciones</th>
                                <th>Solicitante</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${model.recibos}" var="recibo">
                                    <tr>
                                        <td>${recibo.recibo.idRecibo}</td>
                                        <td><b>${recibo.recibo.agremiado.datosPersonales.nombre}&nbsp;${recibo.recibo.agremiado.datosPersonales.apellidoPaterno}&nbsp;${((recibo.recibo.agremiado.datosPersonales.apellidoMaterno==null)?"":recibo.recibo.agremiado.datosPersonales.apellidoMaterno)}</b></td>
                                        <td><fmt:formatDate value="${recibo.fechaSolicitado}" pattern="dd/MM/yyyy HH:mm:ss " /></td>
                                        <td>${recibo.observaciones}</td>
                                        <td>${recibo.usuario.nombre}</td>
                                    </tr>
                            </c:forEach>
                        </tbody>
                    </table>
        </sec:authorize>
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
                       </script>
                   </c:if>

        </body>
</html>
