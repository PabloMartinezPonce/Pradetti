<%-- 
    Document   : registrarDocumentoBase
    Created on : 28/12/2017, 06:32:54 PM
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
<h2 class="selectAction">${(model.edicion)?"Edición de un servicio o documento base":"Registro del servicio o documento base"}</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer"> 
<sec:authorize access="hasAnyRole('Registrar_Editar_Catalogo_Documental')">
    <div class="row">
        <div class="col-xs-12 col-md-8 col-md-offset-2">
            <form id="formCrearNuevoDocumentoBase" action="${pageContext.request.contextPath}/sistema/guardar-cambios-documental.htm" method="post">
            <div class="tab-content">
              <div id="home" class="tab-pane fade in active">
                <div class="row">
                    <div class="col-xs-12">
                            <div class="input-group">
                                <input type="hidden" value="${model.catalogoDocumental.idCatalogo}" id="idCD" name="idCD">
                                <span class="input-group-addon" id="nmbrs">Nombre del servicio o documento base</span>
                                <input id="nombre" value="${model.catalogoDocumental.nombreDocumento}" path="nombre" maxlength="99" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="nmbrs" name="nombre"  required title="Nombre del documento" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>
                        </div>
                </div>
            <div class="col-xs-12 lnbrk"></div>
                <div class="row">        
                        <div class="col-xs-6">
                            <div class="input-group">
                                <span class="input-group-addon">Es un servicio</span>
                                <input  ${(model.catalogoDocumental.servicio)?'checked="checked"' : ''} name="servicio" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                            </div>
                        </div>    
                    <div class="col-xs-6" id="isAConfigurable">
                            <div class="input-group">
                                <span class="input-group-addon">Es un configurable</span>
                                <input ${(model.catalogoDocumental.servicioConfigurado)?'checked="checked"' : ''} name="servicioConfigurado" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                            </div>
                        </div>       
                </div>            
                <div class="col-xs-12 lnbrk"></div>
                    <div class="row">    
                    <div class="col-xs-6">
                        <button type="submit" id="btnGrdRegDocum" class="form-control btn btn-success btn-lg center-btn btn-helper">Guardar</button>
                    </div>  
                    <div class="col-xs-6">
                        <button type="reset" id="btnCnlRegDocum" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/opciones-del-sistema.htm">Cancelar</button>
                    </div> 
                    </div>
              </div>
                    
              </div>
            </div>
            </form>
        </div>                    
</sec:authorize>
    </div>
<div class="col-xs-12 lnbrk"></div>     
<!-- all code here -->
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