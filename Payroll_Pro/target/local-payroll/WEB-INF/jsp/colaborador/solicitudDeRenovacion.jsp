<%-- 
<%-- 
    Document   : solicitudDeRenovacion
    Created on : 23/05/2017, 04:05:12 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
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
        <title>Renovar contrato</title>
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
                <%@include file="../fragmentos/menuColaborador.jsp" %>
                <div class="col-xs-12 lnbrk"></div>
                <h2 class="selectAction">Solicitud de renovación de contrato</h2> 
                <div class="row">
                <div class="col-xs-12 col-md-9 col-md-offset-1">    
                    <h3>
                        <span  title="colaborador" class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        ${model.datosPersonales.nombre.toUpperCase()}&nbsp;${model.datosPersonales.apellidoPaterno.toUpperCase()}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno.toUpperCase())}
                    </h3>     
                </div>
                <div class="col-xs-12 col-md-9 col-md-offset-1">    
                    <h4>
                        <span title="cliente" class="glyphicon glyphicon-queen" aria-hidden="true"></span>
                        ${model.datosLaborales.clienteObj.nombreEmpresa.toUpperCase()}&nbsp;
                    </h4>     
                </div>
                <div class="col-xs-12 col-md-9 col-md-offset-1">    
                    <h4>
                        <span title="contratista" class="glyphicon glyphicon-king" aria-hidden="true"></span>
                        ${model.datosLaborales.contratistaObj.nombreEmpresa.toUpperCase()}&nbsp;
                    </h4>     
                </div>
                <div class="col-xs-12 col-md-9 col-md-offset-1">    
                    <h4>
                        <span title="Fecha de termino del contrato actual" class="glyphicon glyphicon-time" aria-hidden="true"></span>
                        ${model.datosLaborales.fechaFinContrato}&nbsp;
                    </h4>     
                </div>
                    <div class="col-xs-12 col-md-10 col-md-offset-1">
                                       
                <div class="row">
                <div class="col-xs-12 lnbrk"></div>   
                    <form id="formSolicitudRenovacion"  action="${pageContext.request.contextPath}/colaborador/recibir-solicitud-renovacion-contrato.htm" method="post">
                        <input type="hidden" name="contextPathPage" id="contextPathPage" value="${pageContext.request.contextPath}">
                        <input type="hidden" name="selectClientes" id="selectClientes" value="${model.datosLaborales.clienteObj.idCliente}">
                        <input type="hidden" name="idagremiado" id="idagremiado" value="${model.agremiado.idAgremiado}"> 
                        <input type="hidden"  id="contratoId" value="${model.contratoEmpresasId}">            
                  <div class="col-xs-12">       
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <b>Del Contrato</b>
                    </div>
                    <div class="panel-body">
                        <div class="col-xs-12">    
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.fechaAlta'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="ltMSS">Fecha de inicio</span>
                                    <input value="" type="date" class="form-control" placeholder="Campo requerido" aria-describedby="ltMSS" name="fechaAlta" minlength="10" maxlength="10" required title="Fecha de alta del colaborador" id="fechaInicioContratoColaborador">
                                </div>
                            </div>
                        </c:if>             
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.tipoDeContrato'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="tpDCntrt">Tiempo de contrato</span>
                                    <select name="tpDCntrt" class="form-control" placeholder="Campo requerido" aria-describedby="tpDCntrts" id="tpDCntrtsID_AddColaborador"  required >
                                        <c:forEach items="${model.tiposDeContrato}" var="tipoDeContrato">
                                                     <option  value="${tipoDeContrato.idTipoContrato}" >${tipoDeContrato.descripcion}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">Fecha de termino</span>
                                    <input value="" class="form-control" name="fechaTermino" type="date" autocomplete="off" title="Fecha de termino del contrato"  id="prdDCntrtID_AddColaborador" disabled>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="prdDCntrt">Periodo de contrato</span>
                                        <input value="" name="periodoContrato" type="text" class="form-control toUpperText" maxlength="2" autocomplete="off" placeholder="" aria-describedby="prdDCntrt"  title="Tiempo de contrato" placeholder="Campo requerido" disabled  id="prdDCntrtTxt_AddColaborador">
                                        <input id="prdDCntrtTxt_AddColaboradorHddn" type="hidden" value="" name="tiempoContrato">
                                   </div>
                            </div>
                        </c:if>
                        </div>
                    </div>
                </div>
                  </div>   
                <div class="col-xs-12"  style="display: none;" id="div_ModfSalario">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <b>De Sueldo y Salarios</b>
                    </div>
                    <div class="panel-body">
                        <div class="col-xs-12">                                                    
                        <div class="input-group">
                            <span class="input-group-addon" >¿Percepción basada en SUP's?</span>
                            <input id="sueldoSupTButon"  value="Sí" type="checkbox" name="SueldoYSalario"  data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default"  class="form-control" ${model.datosLaborales.percepcionBasadaEnSUP?'checked':''}>
                            <input type="hidden" value="${model.datosLaborales.percepcionBasadaEnSUP}" id="sueldoSupTButonVal" name="percepcionBasadaEnSUP">
                        </div>
                        </div>
                        <div id="sueldoNoSup" ${model.datosLaborales.percepcionBasadaEnSUP?'hidden':''}>
                            
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.salarioDiarioNominal'))}" >
                                <div class="col-xs-12 col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="slrDr">Salario diario</span>
                                        <span class="input-group-addon" >$</span>
                                        <input id="salarioMensualQtyNml" value="${model.datosLaborales.salarioDiario}" type="number"  placeholder="Campo requerido" class="form-control toUpperText" maxlength="10" autocomplete="off" aria-describedby="slrDr" name="salarioDiario" title="Salario diario del colaborador" pattern="\d+(\.\d{2})?"  ${model.datosLaborales.percepcionBasadaEnSUP?'':'required'} min="0" value="0" step=".0001">
                                        <span class="input-group-addon" >MXN</span>
                                    </div>                            
                                </div>
                            </c:if>                            
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.sueldoMensual'))}" >
                                <div class="col-xs-12 col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="sldMnsl">Sueldo mensual</span>
                                        <span class="input-group-addon" >$</span>
                                        <input id="salarioMensualQty" value="${model.datosLaborales.sueldoMensual}" type="text" class="form-control toUpperText" maxlength="10" autocomplete="off"  placeholder="Campo requerido" aria-describedby="sldMnsl" name="sueldoMensual" title="Sueldo mensual del colaborador" pattern="\d+(\.\d{2})?"   ${model.datosLaborales.percepcionBasadaEnSUP?'':'required'}>
                                        <span class="input-group-addon" >MXN</span>
                                    </div>
                                </div>
                            </c:if>    
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.salarioDiario'))&&!(listpasg:existsInEsquemaDePago(model.campos,'dLa.salarioDiarioNominal'))}" >
                                <div class="col-xs-12 col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="slrDr">Salario diario</span>
                                        <span class="input-group-addon" >$</span>
                                        <input id="salarioDiario" value="${model.datosLaborales.salarioDiario}" type="text"  placeholder="Campo requerido" class="form-control toUpperText" maxlength="10" autocomplete="off" aria-describedby="slrDr" name="salarioDiario" title="Salario diario del colaborador" pattern="\d+(\.\d{2})?"  ${model.datosLaborales.percepcionBasadaEnSUP?'':'required'}>
                                        <span class="input-group-addon" >MXN</span>
                                    </div>                            
                                </div>
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.salariosImss'))&&!(listpasg:existsInEsquemaDePago(model.campos,'dLa.salarioDiarioNominal'))}" >
                                <div class="col-xs-12 col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="nmrSlrsMnms">Número de salarios minimos IMSS&nbsp;&nbsp;&nbsp;$</span>
                                        <input id="salariosImss" value="${model.datosLaborales.salariosImss}" type="text" class="form-control toUpperText" maxlength="50" autocomplete="off" placeholder="Campo calculado" aria-describedby="nmrSlrsMnms"  title="Número de salarios minimos IMSS del colaborador" disabled>
                                        <span class="input-group-addon" >MXN</span>
                                        <input type="hidden" value="${model.datosLaborales.salariosImss}" id="salariosImssHddn" name="salariosImss" >
                                    </div>
                                </div> 
                            </c:if>
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.salarioDiarioIntegrado'))&&!(listpasg:existsInEsquemaDePago(model.campos,'dLa.salarioDiarioNominal'))}" >
                                <div class="col-xs-12 col-md-6">
                                    <div class="input-group">
                                        <span class="input-group-addon" id="slrDrNtgrd">Salario diario integrado&nbsp;&nbsp;&nbsp;$</span>
                                        <input id="salarioDiarioIntegrado" value="${model.datosLaborales.salarioDiarioIntegrado}" type="text" class="form-control toUpperText" maxlength="50" autocomplete="off" placeholder="Campo calculado" aria-describedby="slrDrNtgrd" title="Salario diario integrado del colaborador" disabled>
                                        <span class="input-group-addon" >MXN</span>
                                        <input type="hidden" value="${model.datosLaborales.salarioDiarioIntegrado}"  id="salarioDiarioIntegradoHddn" name="salarioDiarioIntegrado">
                                    </div>                            
                                </div> 
                            </c:if>
                        </div>
                        <div id="sueldoSup" ${model.datosLaborales.percepcionBasadaEnSUP?'':'hidden'}>                            
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" >Seleccione un SUP</span>
                                    <select id ="supSelect" name="supSelect" class="form-control" placeholder="Campo requerido" >
                                        <option selected="selected"  title="${model.datosLaborales.salarioUnicoProfesional.pesosDiarios}" value="${model.datosLaborales.salarioUnicoProfesional.idSalarioUnicoProfesional}">${model.datosLaborales.salarioUnicoProfesional.oficio}</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="input-group">
                                        <span class="input-group-addon">Salario diario</span>
                                        <span class="input-group-addon" >$</span>
                                        <input id="salarioDiarioSup" value="${model.datosLaborales.salarioUnicoProfesional.pesosDiarios}" type="text" disabled placeholder="Campo requerido" class="form-control toUpperText" maxlength="10" aria-describedby="slrDr" name="salarioDiario" title="Salario diario del colaborador"  >
                                        <span class="input-group-addon" >MXN</span>  
                                </div>
                            </div>
                        </div>       
                        <div class="col-xs-12 col-md-6">
                            <p class="text-warning"><h6><sup>*</sup><b>SUP's</b>&nbsp;(Salarios únicos profesionales)</h6></p>
                        </div>
                    </div>
                </div>
            </div>
                <div class="col-xs-12" >
                    <div class="input-group">
                        <span class="input-group-addon" id="sgrDVd">¿Aplicar Modificación de Salario?</span>
                        <input  type="checkbox" id="sgrDVdID_AplModfSal" aria-describedby="sgrDVd"  data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default"  class="form-control">
                        <input type="hidden" name="newSalary" value="false" id="newSalary">
                    </div>
                </div>       
                <div class="col-xs-12 lnbrk"></div>   
                        <div class="col-xs-12 col-sm-4">
                            <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper" id="btnGrdrDtsLbrls">
                                <b>Guardar</b>
                            </button>
                        </div>
                        <div class="col-xs-12 col-sm-4">
                            <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/contratos-por-vencer.htm" >
                                <b>Cancelar</b>
                            </button>
                        </div>
                    </form>
                </div>
                    </div>
                </div>
            </div>
            <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->

            <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
            <%@include file="../fragmentos/pie.jsp" %>

    </body>
</html>
