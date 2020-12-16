<%-- 
    Document   : plantillaForm
    Created on : 25/01/2018, 12:07:05 PM
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
<h2 class="selectAction">Por favor seleccione una plantilla</h2>
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
    <div class="col-xs-12 col-md-8 col-lg-offset-2">
        <form method="post" action="${pageContext.request.contextPath}/${model.url}">
        <div class="row">
            <div class="col-xs-12">
                    <div class="input-group">
                      <span class="input-group-addon" id="tpDNcdnc_ncdnc">Plantilla</span>
                      <select name="template" class="form-control" placeholder="Campo requerido" aria-describedby="tpDNcdnc"  required >
                            <c:forEach items="${model.templates}" var="template" >
                                  <option value="${template}">${template}</option> 
                            </c:forEach>
                        </select>
                </div>                   
            </div>
            <div class="col-xs-12 lnbrk">
                <input type="hidden" name="idColaborador" value="${model.agremiado.idAgremiado}">
            </div>            
                <div class="col-xs-4 col-xs-offset-2">                    
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Continuar</b>
                        </button>
                </div>            
            <div class="col-xs-4 col-xs-offset-1">
                <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" onclick="backInView">
                    <b>Volver</b>
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