<%-- 
    Document   : registroPeriodoFA
    Created on : 02/09/2019, 05:53:44 PM
    Author     : Gabriela Jaime <gabrela.jaime@it-seekers.com>
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
<h2 class="selectAction">${(model.edicion)?"Edición de un período del fondo de ahorro":"Nuevo período del fondo de ahorro"}</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer">
    <form method="post" id="formNuevoPeriodoFA" action="${pageContext.request.contextPath}/sistema/datos-fondo-ahorro.htm" >
        <div class="col-xs-12">
                <input type="hidden" name="id" value="${model.periodo.idPeriodoFA}">               
        </div>
        <div class="row">
            <div class="row">
                    <div class="col-xs-6 col-md-8 col-md-offset-2">
                        <div class="input-group">

                                  <span class="input-group-addon" id="clnt">Contratista</span>
                                    <select id="slctClntsNvCntrt" name="idContratista" class="form-control" placeholder="Campo requerido" aria-describedby="clnt"  required>
                                        <option></option>
                                        <c:forEach items="${model.contratistas}" var="contratista">
                                            <c:if test="${contratista.status}">
                                                <option value="${contratista.idContratista}" id="${contratista.rfc}">${contratista.nombreEmpresa}</option>  
                                             </c:if>
                                        </c:forEach>
                                      </select>                      

                        </div>
        </div>
                    <div class="col-xs-6 col-md-4 col-md-offset-2">
                        <div class="input-group">
                            <span class="input-group-addon" >Fecha de inicio</span>
                            <input value="${model.fI}" type="date" class="form-control" placeholder="Campo requerido" name="diaRegistroDesde" required title="Día de inicio al fondo de ahorro" id="fechaInicioPeriodo">
                        </div>                
                    </div>
                    <div class="col-xs-6 col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon" > Fecha de Termino </span>
                            <input value="${model.fF}" type="date" class="form-control" placeholder="Campo requerido" id="fechaFinPeriodo" name="diaRegistroHasta" required title="Día fin al fondo de ahorro">
                        </div>                
                    </div>    
                    <div class="col-xs-6 col-md-8 col-md-offset-2">
                        <div class="input-group">
                            <span class="input-group-addon" id="prdDCntrt">Nombre</span>
                                <input value="${model.periodo.nombrePeriodo}" name="nombrePeriodo" type="text" class="form-control toUpperText" maxlength="50" autocomplete="off" placeholder="" aria-describedby="prdDCntrt"  title="Periodo del fondo de ahorro" placeholder="Campo requerido" id="prdDCntrtTxt_AddPeriod">
                               <!-- <input id="prdDCntrtTxt_AddPeriodHddn" type="hidden" value="${model.datosLaborales.tiempoContrato}" name="tiempoContrato">->
                        </div>
                    </div>
<!--                    <div class="col-sm-12 col-md-8 col-md-offset-2 infoContrato" hidden>              
                        <ul  class="list-group">
                            <li class="list-group-item list-group-item-success">
                                <span class="badge"><span class="glyphicon glyphicon-queen" title="Cliente" aria-hidden="true"></span>                                
                                </span>&nbsp;&nbsp;<span id="clienteName"></span>
                            </li>
                            <li class="list-group-item list-group-item-success">
                                <span class="badge"><span class="glyphicon glyphicon-king" title="Contratista" aria-hidden="true"></span>                                 
                                </span>&nbsp;&nbsp;<span id="contratistaName"></span>
                            </li>
                            <li class="list-group-item list-group-item-success">
                                <span class="badge"><span class="glyphicon glyphicon-file" title="Contrato" aria-hidden="true"></span>                                    
                                </span>&nbsp;&nbsp;<span id="contratoName"></span>
                            </li>
                            <li class="list-group-item list-group-item-success">
                                <span class="badge"><span class="glyphicon glyphicon-calendar" title="Fecha del contrato" aria-hidden="true"></span>                                    
                                </span>&nbsp;&nbsp;<span id="fechaContrato"></span>
                            </li>
                        </ul>
                    </div>                      
                    <div class="col-sm-12 col-md-4 col-md-offset-2 infoContrato" hidden>
                        <h4 class="text-center"><b>Salarios Unicos Profesionales</b></h4>
                        <ul class="list-group" id="listSUPS"></ul>
                    </div>         
                    <div class="col-sm-12 col-md-4 infoContrato" hidden>
                        <h4  class="text-center"><b>Catalogo documental</b></h4>
                        <ul class="list-group" id="listCDs"></ul>
                    </div>-->
                </div>
            
            <div class="col-xs-12 lnbrk"></div>            
            <div class="col-xs-12 col-sm-4 col-sm-offset-2">
                                                    <button id="btnGrdr" type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                                        <b>Guardar</b>
                                                    </button>
                                                </div>
                                                <div class="col-xs-12 col-sm-4">
                                                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/reporte/reportes.htm">
                                                        <b>Cancelar</b>
                                                    </button>
                                                </div>
        </div>
    </form>
</div>  
<div class="col-xs-12 lnbrk"></div>   
</div>
<div class="col-xs-12 lnbrk"><input type="hidden" value="${pageContext.request.contextPath}" id="contextPathPage"></div>                                                        
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