<%-- 
    Document   : registroZonaSalarial
    Created on : 15/11/2016, 01:53:44 PM
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
<h2 class="selectAction">${(model.edicion)?"Edición de una zona salarial":"Nueva zona salarial"}</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <form name="frmZonasSalariales" method="post" id="formNuevaZonaSM"  modelAttribute="zonaSalarial" action="${pageContext.request.contextPath}/sistema/datos-zona-salarial.htm" >
        <div class="row">
            <div class="col-xs-12 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-0">
                <div class="input-group">
                    <input type="hidden" name="idZonaSm" value="${model.zona.idZonaSm}">
                    <span class="input-group-addon" id="nmbrZnSM">Nombre</span>
                    <input  value="${model.zona.zona}" type="text" class="form-control capitalizeText" placeholder="Campo requerido" aria-describedby="nmbrZnSM" name="zona" id="nmbrZnSMID" required title="Nombre de la zona salarial" maxlength="100" autocomplete="off" minlength="5"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
            <div class="col-xs-12 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-0">
                <div class="input-group">
                  <span class="input-group-addon" id="nmbrZnSM">Salario</span>
                  <input value="${model.zona.salario}" type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="nmbrZnSM" name="salario" id="nmbrZnSMID" required title="salario de la zona" autocomplete="off" pattern="\d+(\.\d{2})?" min="1">
                </div>                
            </div>
            <div class="col-xs-12 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-0">
                <div class="input-group">
                  <span class="input-group-addon" id="nmbrZnSM">Año</span>
                  <input value="${model.zona.anio}" type="number" class="form-control" placeholder="Campo requerido" aria-describedby="nmbrZnSM" min="2017" max="2100" name="anio" id="nmbrZnSMID" required title="Año de la zona salarial" autocomplete="off">
                </div>                   
            </div>
            <div class="col-xs-12 lnbrk"></div>            
            <div class="col-xs-12 col-md-4">
                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
            </div>
            <div class="col-xs-12 col-md-3 col-md-offset-1 ">
                     <sec:authorize access="hasAnyRole('Agregar_Zona_Salarial','Editar_Zona_Salarial')">
                <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                    <b>Guardar</b>
                </button>
                     </sec:authorize>
            </div>
            <div class="col-xs-12 col-md-3 col-md-offset-1">
                <c:choose>
                    <c:when test="${model.edicion}">
                        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/zonas-salariales.htm">  
                            <b>Cancelar</b>
                        </button>                        
                    </c:when>
                    <c:otherwise>
                        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/opciones-del-sistema.htm">  
                            <b>Cancelar</b>
                        </button>                        
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form>
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