<%-- 
    Document   : fdc_dlaborales
    Created on : 21/02/2018, 01:05:03 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
    <body>
        <form id="formDatosLaborales" action="${pageContext.request.contextPath}/colaborador/datosLaborales.htm" method="post" modelAttribute="datosLaborales" >
            <div class="col-xs-12">                
                    <input type="hidden" value="${model.agremiado.idAgremiado}" id="idagremiado" name="idAgremiado">
                    <input type="hidden" value="${model.esquema.idEsquemaPago}" id="schema" name="schemaId">
                    <input type="hidden" value="${model.contratoID}" id="contratoId" name="contratoId">
            </div>
            <div class="col-xs-12">
                <div class="panel panel-success">
                    <div class="panel-heading">
                    <b>Del Contrato</b>
                    </div>
                    <div class="panel-body">
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.cliente'))}" >
                            <div class="col-xs-12">
                                <div class="input-group">
                                    <span class="input-group-addon" id="clnt" >Cliente</span>
                                    <select  id="selectClientes" name="cliente" class="form-control" placeholder="Campo requerido" aria-describedby="clnt"  disabled >
                                                    <option  value="${model.agremiado.datosLaborales.clienteObj.idCliente}" selected="selected">${model.agremiado.datosLaborales.clienteObj.nombreEmpresa}</option>     
                                    </select>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.contratista'))}" >
                            <div class="col-xs-12 col-md-8">
                                <div class="input-group">
                                    <span class="input-group-addon" id="clnt">Contratista</span>
                                    <select id="selectBoxContratistas" name="contratista" class="form-control" placeholder="Campo requerido" aria-describedby="clnt"  disabled >
                                        <option  value="${model.agremiado.datosLaborales.contratistaObj.idContratista}" selected="selected">${model.agremiado.datosLaborales.contratistaObj.nombreEmpresa}</option>    
                                      </select>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.sqmDPg'))}" >
                            <div class="col-xs-12 col-md-4">
                                <div class="input-group">
                                    <span class="input-group-addon">Esquema de pago</span>
                                    <select name="sqmDPg" class="form-control" placeholder="Campo requerido" aria-describedby="tpDCntrts" id="squmDPgID_AddColaborador"  disabled >
                                        <option selected="selected" value="${model.datosLaborales.esquemaPago.idEsquemaPago}">${model.datosLaborales.esquemaPago.descripcion}</option>
                                    </select>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.fechaAlta'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="ltMSS">Fecha de ingreso</span>
                                    <input value="${model.datosLaborales.fechaInicioContrato}" type="date" class="form-control" placeholder="Campo requerido" aria-describedby="ltMSS" name="fechaAlta" minlength="10" maxlength="10" required title="Fecha de alta del colaborador" id="fechaInicioContratoColaborador">
                                </div>
                            </div>
                        </c:if>             
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.tipoDeContrato'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="tpDCntrt">Tipo de contrato</span>
                                    <select name="tpDCntrt" class="form-control" placeholder="Campo requerido" aria-describedby="tpDCntrts" id="tpDCntrtsID_AddColaborador"  required >
                                        <c:forEach items="${model.tiposDeContrato}" var="tipoDeContrato">
                                            <c:choose>
                                                <c:when test="${tipoDeContrato.idTipoContrato==model.datosLaborales.tipoContratoObj.idTipoContrato}">
                                                     <option  value="${tipoDeContrato.idTipoContrato}" selected="selected">${tipoDeContrato.descripcion}</option>
                                                </c:when>
                                                <c:otherwise>
                                                     <option  value="${tipoDeContrato.idTipoContrato}" >${tipoDeContrato.descripcion}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon">Fecha de termino</span>
                                    <input value="${model.datosLaborales.fechaFinContrato}" class="form-control" name="fechaTermino" type="date" autocomplete="off" title="Fecha de termino del contrato"  id="prdDCntrtID_AddColaborador" ${(model.datosLaborales.fechaFinContrato==null)?'disabled':''}>
                                </div>
                            </div>
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="prdDCntrt">Periodo de contrato</span>
                                        <input value="${model.datosLaborales.tiempoContrato}" name="periodoContrato" type="text" class="form-control toUpperText" maxlength="2" autocomplete="off" placeholder="" aria-describedby="prdDCntrt"  title="Tiempo de contrato" placeholder="Campo requerido" disabled  id="prdDCntrtTxt_AddColaborador">
                                        <input id="prdDCntrtTxt_AddColaboradorHddn" type="hidden" value="${model.datosLaborales.tiempoContrato}" name="tiempoContrato">
                                   </div>
                            </div>
                        </c:if>   
                    </div>
                </div>
            </div>
            <div class="col-xs-12">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <b>Del Colaborador</b>
                    </div>
                    <div class="panel-body">
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.actividadProfesional'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="ctvddPrfsnl">Actividad profesional</span>
                                    <input value="${model.datosLaborales.actividadProfesional}" type="text" class="form-control firstLetterText trimTxt" maxlength="190" autocomplete="off" placeholder="Campo requerido" aria-describedby="ctvddPrfsnl" name="actividadProfesional" required title="Actividad profesional del colaborador"  minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.areaDepartamento'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="dprtmnt">Departamento</span>
                                    <input value="${model.datosLaborales.areaDepartamento}" type="text" class="form-control firstLetterText trimTxt" maxlength="190" autocomplete="off" placeholder="Campo requerido" aria-describedby="dprtmnt" name="areaDepartamento" required title="Departamento del colaborador"  minlength="3"  pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([_]*[-]*[.]*[,]*[\s]*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.lugarTrabajo'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="lgrTrbj">Lugar de trabajo</span>
                                    <input value="${model.datosLaborales.lugarTrabajo}" type="text" class="form-control firstLetterText trimTxt" maxlength="190" autocomplete="off" placeholder="Campo requerido" aria-describedby="lgrTrbj" name="lugarTrabajo" required title="Lugar de trabajo del colaborador"  minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([.]*[,]*[\s]*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                                </div>
                            </div>
                        </c:if> 
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.credencialLaboral'))}" >
                            <div class=" col-xs-6 col-md-3">
                                <div class="input-group">
                                    <span class="input-group-addon" id="crdnclLbrl">Gafete</span>
                                    <input id="crdnclLbrlTgglBtn" ${model.datosLaborales.credencialLaboral.equalsIgnoreCase("Sí") ? 'checked="checked"' : ''} value="Sí" type="checkbox" aria-describedby="crdnclLbrl"   data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default"  class="form-control" >
                                    <input type="hidden" id="crdnclLbrlButonVal" value="${model.datosLaborales.credencialLaboral.equalsIgnoreCase("Sí") ? 'Sí' : 'No'}"  name="credencialLaboral" >
                                </div>
                            </div>
                        </c:if>                     
                    </div>           
                </div>
             </div>             
            <div class="col-xs-12">
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
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.prdDPg'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="prdDPg">Periodo de pago</span>                                
                                    <select name="prdDPg" class="form-control" placeholder="Campo requerido" aria-describedby="prdDPg"  required >
                                        <c:forEach items="${model.periodosDePago}" var="periodoDePago">
                                            <c:choose>
                                                <c:when test="${periodoDePago.idTipoNomina==model.datosLaborales.tipoNominaObj.idTipoNomina}">
                                                     <option  value="${periodoDePago.idTipoNomina}" selected="selected">${periodoDePago.tipoNomina}</option>
                                                </c:when>
                                                <c:otherwise>
                                                     <option  value="${periodoDePago.idTipoNomina}" >${periodoDePago.tipoNomina}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </c:if>
                        <div class="col-xs-12 col-md-6">
                            <p class="text-warning"><h6><sup>*</sup><b>SUP's</b>&nbsp;(Salarios únicos profesionales)</h6></p>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.numeroSeguro'))}" >
             <div class="col-xs-12">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <b>Del IMSS</b>
                    </div>
                    <div class="panel-body">
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.numeroSeguro'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="nss">N.S.S.</span>
                                    <input id="nssDlClbrdr" value="${model.datosLaborales.numeroSeguro}" type="text" class="form-control toUpperText" maxlength="13" minlength="11" autocomplete="off" placeholder="Campo requerido" aria-describedby="nss" name="numeroSeguro" required title="Número de seguro social del colaborador"   pattern="[0-9]+">
                                </div>
                            </div> 
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.numeroInfonavit'))}" >
                            <div class="col-xs-12 col-md-6">
                                <div class="input-group">
                                    <span class="input-group-addon" id="nmrCrdtNfnvt">Número de credito infonavit</span>
                                    <input value="${model.datosLaborales.numeroInfonavit}" type="text" class="form-control toUpperText"minlength="12" maxlength="15" autocomplete="off" placeholder="" placeholder="Campo no requerido" aria-describedby="nmrCrdtNfnvt" name="numeroInfonavit" title="Número de credito infonavit del colaborador"  minlength="3"   pattern="[0-9A-Z]+">
                                </div>
                            </div>
                        </c:if>   
                    </div>           
                </div>
             </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.reconocimientoAntiguedad'))}" >
             <div class="col-xs-12">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <b>De Antiguedad Laboral</b>
                    </div>
                    <div class="panel-body">
                            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.reconocimientoAntiguedad'))}" >
                                <div class="col-xs-12 col-md-4">
                                    <div class="input-group">
                                        <span class="input-group-addon" >Reconocimiento de antiguedad</span>
                                        <input id="rcncmtDNtgddToggleBtn" ${model.datosLaborales.antiguedadReconocida? 'checked="checked"' : ''} value="Sí" type="checkbox"  aria-describedby="rcncmtDNtgdd"   data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default"  class="form-control" >
                                        <input type="hidden" value="false" id="rcncmtDNtgddTButonVal" name="antiguedadReconocida">
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="input-group">
                                        <span class="input-group-addon">Antiguedad desde el</span>
                                        <input id="rcncmtDNtgddDate" class="form-control" name="fechaDesdeAntiguedad" type="date" autocomplete="off" title="Fecha desde la que se cuenta la antiguedad" value="${model.datosLaborales.antiguedadDesde}" ${model.datosLaborales.antiguedadReconocida? '' : 'disabled'} >
                                    </div>
                                </div>
                                <div class="col-xs-12 col-md-4">
                                    <div class="input-group">
                                        <span class="input-group-addon">Tiempo</span>
                                        <input id="rcncmtDNtgddText"  class="form-control"  type="text" autocomplete="off" title="Tiempo que se reconoce de antiguedad" disabled value="${model.datosLaborales.tiempoAntiguedad}">
                                        <input id="rcncmtDNtgddTextHddn" type="hidden" value="${model.datosLaborales.tiempoAntiguedad}" name="tiempoAntiguedad">
                                    </div>
                                </div>
                            </c:if>                           
                    </div>        
                </div>
             </div>
            </c:if>                        
            <div class="col-xs-12 lnbrk"></div> 
            <div class="col-xs-12 col-md-6">
                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp; ${(model.datoFijo)?"Toda edición de colaborador será registrada en la bitacora":"Todos los campos son requeridos"}</div>
            </div>
            <div class="col-xs-12 col-md-2 col-md-offset-1">
                <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador','Editar_Colaborador_Activo')">     
                        <button id="btnGrdrDtsLbrls" type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper" >
                            <b>Guardar</b>
                        </button>
                </sec:authorize>
            </div>
            <div class="col-xs-12 col-md-2 col-md-offset-1">
                <c:choose>
                    <c:when test="${model.estatus == 1}">      
                            <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                <b>Cancelar</b>
                            </button>
                    </c:when>
                    <c:otherwise> 
                            <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/expedientes-por-completar.htm">
                                <b>Cancelar</b>
                            </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>
    </body>
</html>
