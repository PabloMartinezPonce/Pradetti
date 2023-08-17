<%-- 
    Document   : plantillaFormFdeA
    Created on : 21/03/2018, 09:54:07 AM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
--%>
<%response.setHeader("pragma", "no-cache");              
 response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");             
 response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="../fragmentos/cabecera.jsp" %>
<title>Elegir plantilla</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    &nbsp;&nbsp;&nbsp;  
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">
<h3 class="selectAction">Por favor ingrese la información requerida</h3>
    <div class="col-xs-12 col-md-8 col-lg-offset-2">
        <span class="glyphicon glyphicon-pawn" aria-hidden="true" title="Agremiado"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.agremiado.datosPersonales.nombre}&nbsp;${model.agremiado.datosPersonales.apellidoPaterno}&nbsp;${model.agremiado.datosPersonales.apellidoMaterno}</span>
    </div>
    <div class="col-xs-12 col-md-8 col-lg-offset-2">
        <span class="glyphicon glyphicon-queen" aria-hidden="true" title="Cliente"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.agremiado.datosLaborales.clienteObj.nombreEmpresa}</span>
    </div>
    <div class="col-xs-12 col-md-8 col-lg-offset-2">
        <span class="glyphicon glyphicon-king" aria-hidden="true" title="Contratista"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.agremiado.datosLaborales.contratistaObj.nombreEmpresa}</span>
    </div>
    <div class="col-xs-12 col-md-8 col-lg-offset-2">
        <span class="glyphicon glyphicon-save-file" aria-hidden="true" title="Documento"></span>&nbsp;&nbsp;&nbsp;<span id="nmbrDlClnt">${model.tDoc.nombreDocumento.toUpperCase()}</span>
    </div>
     <c:if test="${(listpasg:existsInEsquemaDePago(model.view,'SOLICITUD_FONDO_AHORRO'))}" >
    <div class="col-xs-12 col-md-8 col-lg-offset-2">
        <span class="glyphicon glyphicon-usd" aria-hidden="true" title="Sueldo mensual"></span>&nbsp;&nbsp;&nbsp;<span id="sueldoAhorro">${model.sueldoAhorro}</span>
        <sup><span class="glyphicon glyphicon-certificate" aria-hidden="true" title="En esquema mixto y SUP el cálculo es basado en el salario díario multiplicado por 30.4 días."></sup>
    </div>
     </c:if>
    <div class="col-xs-12 col-md-8 col-lg-offset-2">
        <form method="post" action="${pageContext.request.contextPath}/${model.url}">
        <div class="row">
            <input type="hidden" name="ida" value="${model.agremiado.idAgremiado}">
        </div>
        <div class="row">            
            <c:if test="${(listpasg:existsInEsquemaDePago(model.view,'SOLICITUD_FONDO_AHORRO'))}" >
            <div class="col-xs-12 col-md-8">
                <div class="input-group">
                      <span class="input-group-addon">Porcentaje para el fondo de ahorro</span>
                      <input name="percent" step="1" type="number" min="0" max="13" class="form-control" placeholder="campo requerido" required id="percentFA">
                      <span class="input-group-addon">%</span>
                </div>    
            </div>
            <div class="col-xs-12 col-md-4">
                <div class="input-group">
                      <span class="input-group-addon">$</span>
                      <input value="" name="resultFA" type="text" class="form-control" disabled id="resultFA">
                      <input id="resultFAHddn" type="hidden" value="" name="resultFA">
                      <span class="input-group-addon">MXN</span>
                </div>    
            </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.view,'SOLICITUD_TARJETAS'))}">                
            <div class="col-xs-12 col-md-8">
                <div class="input-group">
                      <span class="input-group-addon">Registrar Solicitud de Tarjetas</span>
                            <input id="trjtTDTgglBtn" ${model.datosLaborales.tarjetaTdu.equalsIgnoreCase("No") ? '' : 'checked="checked"'} value='Sí' type="checkbox"  aria-describedby="trjtTD"   data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default"  class="form-control" >
                            <input type="hidden" id="trjtTDTTButonVal" value="${model.datosLaborales.tarjetaTdu.equalsIgnoreCase("") ? 'No' : 'Sí'}" name="tarjetaTdu">
                </div>    
            </div>
            <div class="col-xs-12 col-md-12">
                    <div class="input-group">
                          <span class="input-group-addon" id="tpDNcdnc_ncdnc">Plantilla</span>
                          <select name="template" class="form-control" placeholder="Campo requerido" aria-describedby="tpDNcdnc"  required >
                                <c:forEach items="${model.templates}" var="template" >
                                      <option value="${template}">${template}</option> 
                                </c:forEach>
                            </select>
                    </div> 
            </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.view,'SOLICITUD_FONDO_AHORRO'))}">
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="ltMSS">Fecha de inicio</span>
                            <input value="" type="date" class="form-control" placeholder="Campo requerido" aria-describedby="ltMSS" name="fechaAltaFA" minlength="10" maxlength="10" required title="Fecha de alta del fondo de ahorro" id="fechaInicioContratoColaborador">
                    </div>
                </div> 
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                          <span class="input-group-addon" id="tpDNcdnc_ncdnc">Plantilla</span>
                          <select name="template" class="form-control" placeholder="Campo requerido" aria-describedby="tpDNcdnc"  required >
                                <c:forEach items="${model.templates}" var="template" >
                                      <option value="${template}">${template}</option> 
                                </c:forEach>
                            </select>
                    </div> 
                 </div>
            </c:if>
            <div class="col-xs-12 lnbrk">
                <input type="hidden" name="idColaborador" value="${model.agremiado.idAgremiado}">
            </div>            
            <div class="col-xs-4 col-xs-offset-2">
                <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" onclick="window.close();">
                    <b>Salir</b>
                </button>
            </div>         
                <div class="col-xs-4 col-xs-offset-1">                    
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Continuar</b>
                        </button>
                </div>
        </div>
        </form>
    </div>
</div>
 <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->
                      
           <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
           <%@include file="../fragmentos/pie.jsp" %>
           <c:if test="${model.mostrarVentana}">
               <script>
                   getModalView("${model.tipoVentana}","${model.tituloVentana}","${model.descripcionVentana}");
               </script>
           </c:if>
                   
    <script>
        function backInView() {
            window.history.back();
        }
    </script>
    </body>
</html>