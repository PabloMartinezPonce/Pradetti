<%-- 
    Document   : delClienteYDelContratoOlders
    Created on : 13/03/2018, 02:01:33 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
--%>
<%response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../fragmentos/cabecera.jsp" %>
        <title>Payroll - Colaboradores</title>    
    </head>
    <body onload="getByClienteMyContratos()">
        <!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
        <%@include file="../fragmentos/menu.jsp" %>
        <!-- ==================================================== cuerpo de la página ================================================================== -->
        <div class="col-xs-12 main" id="sidebody">
            <h1 class="page-header" id="titlePage">
                <span class="glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores      
            </h1>
            <!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
            <div class="col-xs-12 col-lg-10 col-lg-offset-1" id="frameContainer">   
                <form method="post" action="${pageContext.request.contextPath}/colaborador/registrar-adecuacion-colaborador.htm">
                <%@include file="../fragmentos/menuColaborador.jsp" %>
                <h2 class="selectAction">Adecuación de nuevo colaborador</h2>
                <div class="row">
                    <div class="col-sm-12 col-md-8 col-md-offset-2">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="clnt" >&nbsp;&nbsp;&nbsp;&nbsp;Colaborador&nbsp;&nbsp;&nbsp;</span>
                                        <input type="text" disabled class="form-control" value="${model.agremiado.datosPersonales.nombre}&nbsp;${model.agremiado.datosPersonales.apellidoPaterno}&nbsp;${model.agremiado.datosPersonales.apellidoMaterno}">
                                        <input type="hidden" name="ida"  value="${model.agremiado.idAgremiado}">
                                        <input type="hidden" id="cnt"  value="${model.agremiado.datosLaborales.contratistaObj.idContratista}">
                                        <input type="hidden" name="rspns"  value="${model.viewResponse}">                                        
                                    </div>
                    </div>
                    <div class="col-sm-12 col-md-8 col-md-offset-2">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="clnt" >&nbsp;&nbsp;&nbsp;&nbsp;Cliente&nbsp;&nbsp;&nbsp;</span>
                                        <select  id="selectClienteOption" name="cliente" class="form-control" placeholder="Campo requerido" aria-describedby="clnt" disabled >                            
                                                        <option  value="${model.agremiado.datosLaborales.clienteObj.idCliente}">${model.agremiado.datosLaborales.clienteObj.nombreEmpresa}</option>      
                                        </select>
                                    </div>
                    </div>
                    <div class="col-sm-12 col-md-8 col-md-offset-2">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="cntst">Contratista</span>
                                        <select id="selectBoxContratistasWithDocument" name="contrato" class="form-control" placeholder="Campo requerido" aria-describedby="cntst"  required >
                                            <option value=""></option>    
                                        </select>
                                    </div>
                    </div>              
                    <div class="col-sm-12 col-md-8 col-md-offset-2">
                            <div class="input-group">
                                <span class="input-group-addon" id="sqm">Esquema de Pago</span>
                                <select id="selectBoxContratistas" name="esquema" class="form-control" placeholder="Campo requerido" aria-describedby="sqm"  required >
                                    <c:forEach items="${model.esquemas}" var="sqm">
                                        <c:choose>
                                            <c:when test="${sqm.idEsquemaPago==model.agremiado.datosLaborales.esquemaPago.idEsquemaPago}">
                                                <option selected  value="${sqm.idEsquemaPago}">${sqm.descripcion}</option>    
                                            </c:when>
                                            <c:otherwise>
                                        <option  value="${sqm.idEsquemaPago}">${sqm.descripcion}</option>    
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </div>
                    </div>                        
                    <div class="col-sm-12 col-md-8 col-md-offset-2 infoContrato" hidden>              
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
                    </div>
                </div>
                <div class="col-xs-12 lnbrk"></div>                
                <div class="col-xs-12 col-md-3 col-md-offset-2">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
                <div class="col-xs-12 col-md-3 col-md-offset-1">  
                <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador','Editar_Colaborador_Activo')">      
                    <button id="btnGrdr" type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                        <b>Continuar</b>
                    </button>
                </sec:authorize>
                </div>
            </form>
            </div>
            <div class="col-xs-12 lnbrk"><input type="hidden" value="${pageContext.request.contextPath}" id="contextPathPage"></div>    
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