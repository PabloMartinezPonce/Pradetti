<%-- 
    Document   : editarRecibosRegistrados
    Created on : 12/10/2017, 10:27:35 AM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
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
<title>Editar recibos</title>
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
<h2 class="selectAction">Editar Recibos Registrados</h2>
<div class="sistemaContainer">       
    <sec:authorize access="hasAnyRole('Editar_Recibos_Registrados')">
    <c:choose>
        <c:when test="${model.actualizacion}">
            <div class="col-xs-6 col-xs-offset-2">
                    <a href="${pageContext.request.contextPath}/sistema/file/editar-recibos-nomina.htm" target="_blank" class="btn"> <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>
                        Descargar plantilla para la edición de recibos
                    </a>    
            </div>
            <div class="col-xs-12 lnbrk"></div>
            <form action="${pageContext.request.contextPath}/sistema/editar-recibos-nomina.htm" method="post" id="formCargarArchivoDeNomina" enctype="multipart/form-data">        
                <div class="col-xs-7 col-xs-offset-2 col-lg-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmbrCmplt">Cliente</span>
                      <select id="optionClntsPrGrgr" name="clienteId" class="form-control" placeholder="Campo requerido"  required>
                        <option value=""></option>
                        <c:forEach items="${model.clientes}" var="cliente">
                            <option value="${cliente.idCliente}">${cliente.nombreEmpresa}</option>
                          </c:forEach>
                      </select>                     
                    </div> 
                    </div>
                    <div class="col-xs-2">                             
                        <button type="submit" class="btn btn-success btn-group btn-group-lg toolTip" title="Cargar archivo de edición de recibos">
                            <span class="glyphicon glyphicon-open-file" aria-hidden="true"></span>
                        </button>
                    </div>        
                <div class="col-xs-7 col-xs-offset-2 col-lg-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmbrCmplt">Sindicato</span>
                      <select id="optionClntsPrGrgr" name="sindicatoId" class="form-control" placeholder="Campo requerido"  required>
                        <option value=""></option>
                        <c:forEach items="${model.sindicatos}" var="sindicato">
                            <option value="${sindicato.idSindicato}">${sindicato.nombreCorto}&nbsp;-&nbsp;${sindicato.rfc}</option>
                          </c:forEach>
                      </select>                     
                    </div> 
                    </div>
                    <div class="col-xs-5 col-xs-offset-2">
                        <div class="input-group">
                            <span class="input-group-addon">Recibos&nbsp;desde&nbsp;el&nbsp;</span>
                            <input type="date" name="fchDsd" class="form-control" required>
                            <span class="input-group-addon">&nbsp;al&nbsp;</span>  
                            <input type="date" name="fchHst" class="form-control" required>              
                        </div>
                    </div>        
                    <div class="col-xs-6 col-xs-offset-2">
                        <input type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="rchvDNmn" required title="Archivo de nómina" id="rchvID" accept=".xlsx"
                                        data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado">
                    </div>
            </form>
        </c:when>
        <c:otherwise>
            <div class="row">  
            <div class="col-xs-12 col-md-6">
                <div class="row">
                        <div class="row">            
                            <div class="col-xs-12">
                                <h4><span class="label label-default">Cliente</span>&nbsp;${model.cliente.nombreEmpresa}</h4>
                            </div>
                            <div class="col-xs-12">
                                <h4><span class="label label-default">Sindicato</span>&nbsp;${model.sindicato.nombreCorto}</h4>
                            </div>
                            <div class="col-xs-12">
                                <h4><span class="label label-default">Recibos Editados </span>&nbsp;${model.recibos.size()}</h4>
                            </div>
                        </div>
                </div>  
            </div>
        </div> 
        <c:if  test="${model.recibos.size()>0}">
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
                                <th >Dias</th>
                                <th >Cantidad MXN</th>
                                <th >Periodo</th>
                            </tr>
                        </thead>
                        <tbody>
                     <sec:authorize access="hasAnyRole('Recibos_Almacenados')">
                            <c:forEach items="${model.recibos}" var="recibo">
                                    <tr>
                                        <td>${recibo.agremiado.idAgremiado}</td>
                                        <td><b>${recibo.agremiado.datosPersonales.nombre}&nbsp;${recibo.agremiado.datosPersonales.apellidoPaterno}&nbsp;${((recibo.agremiado.datosPersonales.apellidoMaterno==null)?"":recibo.agremiado.datosPersonales.apellidoMaterno)}</b></td>
                                        <td>${recibo.agremiado.datosPersonales.rfc}</td>
                                        <td>${recibo.sindicato.nombreCorto}</td> <td>${recibo.diasTrabajados}</td>
                                        <td>$&nbsp;${recibo.totalSindicato}</td>
                                        <td><b><fmt:formatDate value="${recibo.diaInicial}" pattern="dd-MM-yyyy" /> &nbsp;al&nbsp; <fmt:formatDate value="${recibo.diaFinal}" pattern="dd-MM-yyyy" /> </b></td>                                       
                                    </tr>
                            </c:forEach>
                     </sec:authorize>
                        </tbody>
                    </table>
                </div>
        </div>
        </c:if>
        </c:otherwise>
    </c:choose>
    </sec:authorize>
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