<%-- 
    Document   : crearContratoRenovado
    Created on : 25/05/2017, 04:48:13 PM
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
    <title>Contrato de renovación</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores   
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">
<c:choose>
    <c:when test="${model.subirContrato}">
    <h2 class="selectAction">Cargar el contrato de renovación</h2>
        <div class="row">
            <form action="${pageContext.request.contextPath}/colaborador/contrato-de-renovacion.htm" method="post" enctype="multipart/form-data">        
                <div class="col-xs-12 col-md-8">
                  <h4><span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>${model.datosPersonales.nombre}&nbsp;${model.datosPersonales.apellidoPaterno}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno)}</b></h4>
                  <input type="hidden"  name="idAgremiado" value="${model.colaborador.idAgremiado}">
                  <input type="hidden" name="ultimaSolicitud" value="${model.ultimaSolicitudRenovacion.idSolicitudRenovacion}">
              </div>
              <div class="col-xs-12 col-md-4">
                  <h4>RFC:&nbsp;<b>${model.datosPersonales.rfc}</b></h4>
              </div>
              <div class="col-xs-12 col-md-8">
                  <h4><span class="glyphicon glyphicon-king" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>${model.contratista.nombreEmpresa}</b></h4>
              </div>
              <div class="col-xs-12 col-md-8">
                  <h4><span class="glyphicon glyphicon-king" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>${model.cliente.nombreEmpresa}</b></h4>
                  <input type="hidden" name="idCliente" value="${model.cliente.idCliente}">
              </div>
              <div class="col-xs-12 col-md-4">
                  <h4>RFC:&nbsp;<b>${model.contratista.rfc}</b></h4>
              </div>
                <div class="col-xs-12 lnbrk"></div>    
                <div class="col-xs-12 col-md-4">
                    <div class="input-group">              
                                        <input  type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="file"  title="Archivos del colaborador" accept=".pdf"
                                               data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado" required>
                    </div>
                </div>
                <div class="col-xs-12 col-md-4">
                    <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper" title="Subir">
                        <b><span class="glyphicon glyphicon-open-file" aria-hidden="true"></span></b>
                    </button>
                </div>
                <div class="col-xs-12 col-md-4">
                    <button type="button" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/solicitudes-de-renovacion.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
        </div>
    </c:when>
    <c:otherwise>
<h2 class="selectAction">Generar un contrato de renovación</h2>
        <div class="row">
            <form action="${pageContext.request.contextPath}/colaborador/ver-contrato-de-renovacion.htm" method="post">
              <div class="col-xs-12">
                  <h3 class="subheading">Datos del Contrato</h3>
              </div> 
              <div class="col-xs-12 col-md-8">
                  <h4><span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>${model.datosPersonales.nombre}&nbsp;${model.datosPersonales.apellidoPaterno}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno)}</b></h4>
                  <input type="hidden"  name="idAgremiado" value="${model.colaborador.idAgremiado}">
                  <input type="hidden" name="ultimaSolicitud" value="${model.ultimaSolicitudRenovacion.idSolicitudRenovacion}">
              </div>
              <div class="col-xs-12 col-md-4">
                  <h4>RFC:&nbsp;<b>${model.datosPersonales.rfc}</b></h4>
              </div>
              <div class="col-xs-12 col-md-8">
                  <h4><span class="glyphicon glyphicon-king" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;&nbsp;<b>${model.contratista.nombreEmpresa}</b></h4>
                  <input type="hidden" name="idContratista" value="${model.contratista.idContratista}">
              </div>
              <div class="col-xs-12 col-md-4">
                  <h4>RFC:&nbsp;<b>${model.contratista.rfc}</b></h4>
              </div>
              <div class="col-xs-12 col-md-8">
                  <div class="input-group">
                    <span class="input-group-addon" id="clnt">Contratos disponibles</span>
                          <select id="slctClntsNvCntrt" name="idContrato" class="form-control" placeholder="Campo requerido" aria-describedby="clnt"  required>
                              <c:forEach items="${model.contratos}" var="con">
                                  <option value="${con.idContrato}">${con.nombre}</option>
                              </c:forEach>

                            </select>  
                  </div>
              </div>               
              <div class="col-xs-12 col-md-4">
                  <div class="input-group">
                    <span class="input-group-addon" id="fch">Fecha</span>
                    <input value="${model.ultimaSolicitudRenovacion.fechaInicial}" type="date" minlength="10" maxlength="10" class="form-control" placeholder="Campo requerido" aria-describedby="fch" name="fechaContrato" required disabled title="Fecha del contrato">
                  </div>
              </div>
              <div class="col-xs-12">
                  <h3 class="subheading">Primer Testigo</h3>
              </div>
              <div class="col-xs-12">
                  <div class="input-group">
                    <span class="input-group-addon" id="nombreTUno">Nombre</span>
                    <input value="" type="text" class="form-control toUpperText trimTxt" minlength="5"  placeholder="Campo requerido" aria-describedby="nombreTUno" name="nombreTUno" required title="Nombre completo del testigo del contratista" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                  </div>
              </div>
              <div class="col-xs-12 col-sm-6">
                  <div class="input-group">
                    <span class="input-group-addon" id="ocupacionTUno">Ocupación</span>
                    <input value="" type="text" class="form-control firstLetterText trimTxt" minlength="5" placeholder="Campo requerido" aria-describedby="ocupacionTUno" name="ocupacionTUno" required title="Ocupación del testigo del contratista" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([,]*\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                  </div>
              </div> 
              <div class="col-xs-12  col-sm-6">
                  <div class="input-group">
                    <span class="input-group-addon" id="origenTUno">Originario de</span>
                    <input value="" type="text" class="form-control firstLetterText trimTxt" minlength="5" placeholder="Campo requerido" aria-describedby="origenTUno" name="origenTUno" required title="Domicilio completo del testigo del contratista" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([,]*\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                  </div>
              </div> 
              <div class="col-xs-12">
                  <h3 class="subheading">Segundo Testigo</h3>
              </div>
              <div class="col-xs-12">
                  <div class="input-group">
                    <span class="input-group-addon" id="nombreTDos">Nombre</span>
                    <input value="" type="text" class="form-control toUpperText trimTxt" minlength="5"  placeholder="Campo requerido" aria-describedby="nombreTDos" name="nombreTDos" required title="Nombre completo del testigo del cliente" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                  </div>
              </div>
              <div class="col-xs-12 col-sm-6">
                  <div class="input-group">
                    <span class="input-group-addon" id="ocupacionTDos">Ocupación</span>
                    <input value="" type="text" class="form-control firstLetterText trimTxt" minlength="5" placeholder="Campo requerido" aria-describedby="ocupacionTDos" name="ocupacionTDos" required title="Ocupación del testigo del cliente" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                  </div>
              </div> 
              <div class="col-xs-12  col-sm-6">
                  <div class="input-group">
                    <span class="input-group-addon" id="origenTDos">Originario de</span>
                    <input value="" type="text" class="form-control firstLetterText trimTxt" minlength="5" placeholder="Campo requerido" aria-describedby="origenTDos" name="origenTDos" required title="Domicilio completo del testigo del cliente" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([,]*\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                  </div>
              </div>
      <div class="col-xs-12 lnbrk"></div>
              <div class="col-xs-12 col-sm-4">
                  <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
              </div>
              <div class="col-xs-12 col-sm-4">
                  <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                      <b>Guardar</b>
                  </button>
              </div>
              <div class="col-xs-12 col-sm-4">
                  <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper" onclick="window.close()">
                          <b>Cancelar</b>
                      </button>    
              </div> 
          </form>
          </div>
    </c:otherwise>
</c:choose>
  
<div class="col-xs-12 lnbrk"></div>    
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
                       
    </body>
</html>
