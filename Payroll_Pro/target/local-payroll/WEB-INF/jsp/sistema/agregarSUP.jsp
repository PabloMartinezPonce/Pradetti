<%-- 
    Document   : agregarSUP
    Created on : 7/12/2017, 10:29:15 AM
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
<h2 class="selectAction">${(model.edicion)?"Editar":"Agregar"}&nbsp;salarios únicos profesionales</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer"> 
    <div class="row">
        <sec:authorize access="hasAnyRole('Agregar_Editar_SPU_TiposSPU')">
        <div class="col-xs-12 col-md-8 col-md-offset-2">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
              <li role="presentation" class="active"><a href="#tabOne" aria-controls="home" role="tab" data-toggle="tab">Salarios únicos profesionales</a></li>
              <sec:authorize access="hasAnyRole('Tipos_SPU')">
              <li role="presentation"><a href="#typeSUP" aria-controls="profile" role="tab" data-toggle="tab">Tipos de SUP</a></li>
              </sec:authorize>
            </ul>
            <div class="col-xs-12 lnbrk"></div>
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade in active" id="tabOne">
                    <form method="post" action="${pageContext.request.contextPath}/sistema/guardar-sup.htm">
                    <div class="row">
                        <div class="col-xs-12 col-md-6">
                            <input type="hidden" value="${model.sup.idSalarioUnicoProfesional}" id="id_sup" name="id_sup">
                            <div class="input-group">
                                <span class="input-group-addon" >Oficio</span>
                                <input value="${model.sup.oficio}" id="oficio" path="oficio" maxlength="300" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="oficio" name="oficio"  required title="Oficio" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="input-group">
                                <span class="input-group-addon" >Salario</span>
                                <input value="${model.sup.pesosDiarios}" id="salario" type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="salario" name="pesos_diarios" id="salario" required title="salario" autocomplete="off" pattern="\d+(\.\d{2})?" min="1">
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon" >Descripción</span>
                                <input value="${model.sup.descripcion}"  id="descripcion" path="oficio" maxlength="827" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="descripcion" name="descripcion"  required title="descripcion" minlength="3"    pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([.]*[,]*[\s]*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="input-group">
                                <span class="input-group-addon">Fecha de inicio</span>
                                <input value="${model.fI}" id="fchStart" type="date" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="fchStart" name="fchStart" id="fchStart" required title="Fecha de Inicio" autocomplete="off">
                            </div>
                        </div>
                        <div class="col-xs-12 col-md-6">
                            <div class="input-group">
                                <span class="input-group-addon">Fecha de Termino</span>
                                <input value="${model.fF}" id="fchEnd" type="date" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="fchEnd" name="fchEnd" id="fchEnd" required title="Fecha de termino" autocomplete="off" >
                            </div>
                        </div>
                        <div class="col-xs-12">                            
                            <div class="input-group">
                                <span class="input-group-addon">Sector&nbsp;</span>
                                <select name="tipoSup" class="form-control" id="tipoSup" placeholder="Campo requerido" aria-describedby="tipoSup" required title="Sector de Salario único profesional">                                    
                                        <option value=""></option>
                                    <c:forEach items="${model.tipossup}" var="tipoSUP">
                                        <c:choose>
                                            <c:when test="${model.sup.tipoSUP==tipoSUP.idTipoSalarioUnicoProfesional}">
                                                <option value="${tipoSUP.idTipoSalarioUnicoProfesional}" selected>${tipoSUP.sector}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${tipoSUP.idTipoSalarioUnicoProfesional}">${tipoSUP.sector}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 lnbrk"></div>
                        <div class="col-xs-12 col-md-6">
                            <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                <b>Guardar</b>
                            </button>
                        </div>
                        <div class="col-xs-12 col-md-6">                            
                            <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/opciones-del-sistema.htm">
                                <b>Cancelar</b>
                            </button>
                        </div>
                    </div>
                    </form>         
                </div>
                <div role="tabpanel" class="tab-pane fade" id="typeSUP">
                    <form method="post" action="${pageContext.request.contextPath}/sistema/guardar-tipo-sup.htm">
                    <div class="row">    
                        <c:if test="${model.edicion}">
                        <div class="col-xs-12">                            
                            <div class="input-group">
                                <span class="input-group-addon">Sectores Registrados&nbsp;</span>
                                <select id="tipoSupRegistrados" name="tipoSup" class="form-control" placeholder="Campo requerido" aria-describedby="tipoSup" required title="Sector de Salario único profesional">                                    
                                        <option value=""></option>
                                    <c:forEach items="${model.tipossup}" var="tipoSUPs">
                                         <option value="${tipoSUPs.idTipoSalarioUnicoProfesional}">${tipoSUPs.sector}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-xs-12 lnbrk"></div>
                        </c:if>
                        <div class="col-xs-12">
                            <input type="hidden" value="" id="tipo_id_sup" name="id_tipo_sup">
                            <div class="input-group">
                                <span class="input-group-addon" id="sector">Sector</span>
                                <input value="" path="sector" id ="tipoSupChng" maxlength="200" type="text" class="form-control firstLetterText trimTxt" autocomplete="off" placeholder="Campo requerido" aria-describedby="sector" name="sector"  required title="sector" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>                            
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 lnbrk"></div>
                        <div class="col-xs-12 col-md-6">
                            <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                <b>Guardar</b>
                            </button>
                        </div>
                        <div class="col-xs-12 col-md-6">                            
                            <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/opciones-del-sistema.htm">
                                <b>Cancelar</b>
                            </button>
                        </div>
                    </div>
                    </form>          
                </div>
            </div>
        </div>
        </sec:authorize>
</div>
    
    
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