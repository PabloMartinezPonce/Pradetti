<%-- 
    Document   : agregarContratista
    Created on : 15/11/2016, 11:18:42 AM
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
<title>Payroll - Contratistas</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Contratistas      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<%@include file="../fragmentos/menuContratistas.jsp" %>
<h2 class="selectAction">${(model.edicion)?"Edición de un contratista":"Registrar un contratista"}</h2>
<c:if test="${(model.edicion)}">
    <div class="col-xs-12 col-md-6" >
        <h3 class="selectAction">${model.contratista.nombreEmpresa}</h3>
    </div>
</c:if>
<div class="row">
    <div class="col-xs-12">
        <ul class="nav nav-tabs">
          <li role="presentation" class="active">
              <a href="#" id="tabDatosContratista">
                  <span title="${(model.contratista!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.contratista!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                  Datos del contratista
              </a>
          </li>
          <li role="presentation" class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}">
              <a href="#" id="tabInstrumentoNotarialRgstrCntrtst">
                  <span title="${(model.actaNotarial!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.actaNotarial!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                  Instrumento notarial
              </a>
          </li>
          <li role="presentation" class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}">
              <a href="#" id="tabDomicilioFiscalRgstrCntrtst">
                  <span title="${(model.domicilioFiscal!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.domicilioFiscal!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                  Domicilio físcal
              </a>
          </li>
          <li role="presentation" class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}">
              <a href="#" id="tabDomicilioNotificacionRgstrCntrtst">
                  <span title="${(model.domicilioNotificaciones!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.domicilioNotificaciones!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                  Domicilio de notificación
              </a>
          </li>
        </ul>
    </div>
    <div class="col-xs-12" id="divFormDatosContratista">
        <div class="row" >
            <form action="${pageContext.request.contextPath}/constratista/datos-contratista.htm" method="post" id="formDatosContratista" modelAttribute="contratista" >
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                        <input type="hidden" name="idContratista" value="${model.contratista.idContratista}">
                      <span class="input-group-addon" id="nmbrDLMprs">Nombre</span>
                      <input  value="${model.contratista.nombreEmpresa}" type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmbrDLMprs" name="nombreEmpresa" id="nmbrDLMprsID" required title="Nombre de la Empresa" maxlength="230" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="rprsntnt">Representante</span>
                      <input   value="${model.contratista.representateLegal}" id="rprsntntID"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="rprsntnt" name="representateLegal" required title="Representante legal de la empresa" maxlength="250" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="rfc">RFC</span>
                      <input  value="${model.contratista.rfc}" type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="rfc" name="rfc" id="rfcID" required title="RFC de la empresa" maxlength="15" autocomplete="off" minlength="3"   pattern="[A-Z]+([0-9A-Z]+)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="clslDmnstrtv">Cláusula administrativa</span>
                      <input  value="${model.contratista.clausulaAdministrativa}" type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="clslDmnstrtv" name="clausulaAdministrativa" required title="Cláusula administrativa de la empresa" maxlength="200" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
                <div class="col-xs-12">
                    <div class="input-group">
                      <span class="input-group-addon" id="dscrpcn">Descripción</span>
                      <input  value="${model.contratista.descripcionClausula}" type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="dscrpcn" name="descripcionClausula" id="dscrpcn" required title="Descripción de la cláusula administrativa" maxlength="890" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                      <c:choose>
                          <c:when test="${model.edicion}">
                              <div class="col-xs-12">
                                <div class="input-group">
                                  <span class="input-group-addon" id="status">El contratista se encuentra activo dentro del sistema</span>
                                  <input ${(model.contratista.status) ? 'checked="checked"' : ''} value='Sí' name="statusContratista" path="status" id="status" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                                </div>
                           </div>
                          </c:when>
                          <c:otherwise>
                                <div class="col-xs-12">
                                <div class="input-group">
                                  <input value='Sí' name="statusContratista"  type="hidden"  class="form-control">
                                </div>
                           </div>
                          </c:otherwise>
                      </c:choose>
                <div class="col-xs-12 lnbrk"></div>                         
                <div class="col-xs-12 col-md-4">
                    <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                </div>
                    <div class="col-xs-12 col-md-4">
                    <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Contratistas')">  
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                        <b>Guardar</b>
                        </button>
                    </sec:authorize>
                    </div>       
                <div class="col-xs-12 col-md-4">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/contratista/contratistas.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="col-xs-12" id="divFormInstrumentoNotarialRgstrCntrtst">
        <div class="row" >
            <form action="${pageContext.request.contextPath}/contratista/instrumento-notarial.htm" method="post" modelAttribute="actanotarial"  enctype="multipart/form-data" >                   
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="tpDCt">Tipo de acta</span>
                    <select name="tipoActa" class="form-control" placeholder="Campo requerido" aria-describedby="tpDCt"  required id="znSlrlID">
                        <c:forEach items="${model.tiposDeActa}" var="tipoActa">
                             <c:choose>
                                <c:when test="${tipoActa.idTipoActa == model.actaNotarial.tipoActa}">
                                     <option value="${tipoActa.idTipoActa}" selected="selected">${tipoActa.descripcion}</option>
                                </c:when>    
                                <c:otherwise>
                                    <option value="${tipoActa.idTipoActa}">${tipoActa.descripcion}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    </div>
               </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="nstrmnt">Instrumento</span>
                      <input value="${model.actaNotarial.instrumento}" type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="nstrmnt" name="instrumento" id="nstrmntID" required title="Instrumento" maxlength="100" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*"> 
                      <input value="${model.contratista.idContratista}" type="hidden" name="idContratista" >
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="vlmn">Volumen</span>
                      <input value="${model.actaNotarial.volumen}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="vlmn" name="volumen" id="vlmnID" required title="Volumen" maxlength="100" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="fchVlmn">Fecha volumen</span>
                      <input value="${model.actaNotarial.fechaVolumen}"  type="date" class="form-control" placeholder="Campo requerido" aria-describedby="fchVlmn" id="fchVlmnID" name="fchVolumen" required title="Fecha volumen">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmbrNtr">Nombre del notario</span>
                      <input value="${model.actaNotarial.notario}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmbrNtr" name="notario" id="nmbrNtrID" required title="Nombre del notario" maxlength="100" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="drccnNtr">Dirección de la notaria</span>
                      <input value="${model.actaNotarial.direccionNotario}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="drccnNtr" id="drccnNtrID" name="direccionNotario" required title="Dirección del notario" maxlength="100" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmrDNtr">Número de notaria</span>
                      <input value="${model.actaNotarial.numeroNotarial}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmrDNtr" id="nmrDNtrID" name="numeroNotarial" required title="Número de notaria" maxlength="100" autocomplete="off" minlength="1"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <input  type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="actaNotarialPDF" ${(model.actaNotarial.urlDocumento == null )?"required":""} title="Adjuntar archivo" id="rchvID" accept=".pdf"
                            data-buttonText="&nbsp;&nbsp;Documento legal" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="${(model.actaNotarial.urlDocumento == null )?"Sin archivo seleccionado":model.actaNotarial.urlDocumento.substring(35)}">
                      <input value="${model.actaNotarial.urlDocumento}" type="hidden" class="form-control" placeholder="Campo requerido" name="urlDocumento" >
                      <c:if test="${model.edicion && model.actaNotarial.urlDocumento != null}">
                          <span class="input-group-addon"><a target="_blank" href="${pageContext.request.contextPath}/contratista/${model.contratista.idContratista}/ActaNotarial.htm" title="Ver Acta Notarial">&nbsp;<span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>&nbsp;</a></span>
                      </c:if>
                    </div>
                </div>
                <div class="col-xs-8 col-sm-4">
                    <div class="input-group">
                      <span class="input-group-addon" id="PdrLgl">¿Cuenta con poder legal?</span>
                      <input ${(model.actaNotarial.tienePoderLegal) ? 'checked="checked"' : ''} value='Sí' name="tnPoderLegal" path="pdrLgl" id="tnPdrLgl" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                    </div>
                </div>  
                <div class="col-xs-4 col-sm-8">
                    <div class="input-group">
                        <button title="Copiar los datos del acta notarial en el poder legal" type="button" id="cprDtsPdrLgl" class="btn" style="display:${(model.actaNotarial.tienePoderLegal)?"inherit":"none"}">
                                <span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span>
                        </button>
                    </div>
                </div>                    
                <div class="col-xs-12 lnbrk"></div>
                <div class="col-xs-12">
                    <div class="row" id="secPoderLegal" style="display:${(model.actaNotarial.tienePoderLegal)?"inherit":"none"}">
                        <div class="col-xs-12">
                            <h4>Datos del poder legal</h4>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="rpsntntPL">Representante</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""} value="${model.poderLegal.representanteLegal}" type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="rpsntntPL" name="rpsntntPL" id="rpsntntPLID"  title="Representante legal" maxlength="250" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*"> 
                              <input value="${model.actaNotarial.idActaNotarial}" type="hidden" class="form-control capitalizeText" placeholder="Campo requerido" aria-describedby="nstrmnt" name="idActaNotarial" >
                            </div>                            
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="rfcPL">RFC</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}  value="${model.poderLegal.rfc}" maxlength="15"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="rfcPL" name="rfcPL" id="rfcPLID"  title="RFC del representante legal" autocomplete="off" minlength="15"   pattern="[A-Z]+(\s[0-9A-Z]+)*"> 
                            </div>                            
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="nstrmntPL">Instrumento</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}  value="${model.poderLegal.instrumento}" type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="nstrmntPL" name="instrumentoPL" id="nstrmntPLID"  title="Instrumento" maxlength="100" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*"> 
                            </div>                            
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="vlmnPL">Volumen</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}  value="${model.poderLegal.volumen}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="vlmn" name="volumenPL" id="vlmnPLID"  title="Volumen" maxlength="100" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>                            
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="fchVlmnPL">Fecha volumen</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}  value="${model.poderLegal.fechaVolumen}"  type="date" class="form-control" placeholder="Campo requerido" aria-describedby="fchVlmn" name="fechaVolumenPL" id="fechaVolumenPLID"  title="Fecha volumen">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="nmbrNtrPL">Nombre del notario</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}  value="${model.poderLegal.notario}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmbrNtrPL" name="notarioPL" id="nmbrNtrPLID"  title="Nombre del notario" maxlength="200" autocomplete="off" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="drccnNtrPL">Dirección de la notaria</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}  value="${model.poderLegal.direccionNotario}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="drccnNtrPL" id="drccnNtrPLID" name="direccionNotarioPL"  title="Dirección del notario" maxlength="250" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="nmrDNtrPL">Número de notaria</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}  value="${model.poderLegal.numeroNotarial}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmrDNtrPLL" id="nmrDNtrPLID" name="numeroNotarialPL"  title="Número de notaria" maxlength="100" autocomplete="off" minlength="1"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7">
                            <div class="input-group">
                              <input ${(model.actaNotarial.tienePoderLegal && model.poderLegal.urlDocumento == null ) ? "required":""}    type="file" class="filestyle" class="form-control" placeholder="Campo requerido" name="poderLegalPDF"  title="Adjuntar archivo" id="rchvPLID" accept=".pdf"
                                    data-buttonText="&nbsp;&nbsp;Documento legal" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="${(model.poderLegal.urlDocumento == null )?"Sin archivo seleccionado":model.poderLegal.urlDocumento.substring(35)}">
                                <input value="${model.poderLegal.urlDocumento}" type="hidden" class="form-control" placeholder="Campo requerido" name="urlDocumentoPL" >
                              <c:if test="${model.edicion && model.poderLegal.urlDocumento != null}">
                                  <span class="input-group-addon"><a target="_blank" href="${pageContext.request.contextPath}/contratista/${model.contratista.idContratista}/PoderLegal.htm" title="Ver Poder Legal">&nbsp;<span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>&nbsp;</a></span>
                              </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 lnbrk"></div>                 
                <div class="col-xs-12 col-md-4">
                    <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                </div>
                    <div class="col-xs-12 col-md-4">                        
                    <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Contratistas')">  
                    <button type="submit" class="form-control btn btn-success btn-lg center-btn  btn-helper">
                        <b>Guardar</b>
                    </button>
                    </sec:authorize>
                    </div>         
                <div class="col-xs-12 col-md-4">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn  btn-helper redireccionar" value="${pageContext.request.contextPath}/contratista/contratistas.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="col-xs-12" id="divDomicilioFiscal" >
    <div class="col-xs-12" id="divFormDomicilioFiscalRgstrCntrtst"  >
        <div class="row" >
            <form action="${pageContext.request.contextPath}/contratista/domicilio-fiscal.htm" method="post" modelAttribute="empresasdomicilio" >
                     <input value="${model.contratista.idContratista}" type="hidden"  name="idContratista" >
                     <input value="${model.domicilioFiscal.idEmpresasDomicilio}" type="hidden"  name="idEmpresasDomicilio" >
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cll">Calle</span>
                      <input value="${model.domicilioFiscal.calle}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="cll" name="calle" id="cllID" required title="Calle del domicilio físcal" maxlength="100" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-3">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmr">Número</span>
                      <input value="${model.domicilioFiscal.numero}" type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmr" name="numero" required title="Número del domicilio físcal" maxlength="25" autocomplete="off" minlength="1"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-3">
                    <div class="input-group">
                      <span class="input-group-addon" id="cdgPstl">C.P.</span>
                      <input value="${model.domicilioFiscal.codigoPostal}"  type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="cdgPstl" name="codigoPostal" required title="Código Postal del Domicilio Físcal" maxlength="8" autocomplete="off" minlength="5" minlength="3"   pattern="[0-9]+">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cln">Colonia</span>
                      <input value="${model.domicilioFiscal.colonia}"   type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="cln" name="colonia" id="clnID" required title="Colonia del domicilio físcal" maxlength="100" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cdd">Ciudad</span>
                      <input value="${model.domicilioFiscal.ciudad}"   type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="cdd" name="ciudad" id="cddID" required title="Ciudad de domicilio físcal" maxlength="100" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="std">Estado</span>
                      <input value="${model.domicilioFiscal.estado}"   type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="std" name="estado" required title="Estado del domicilio físcal" maxlength="100" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div> 
                <div class="col-xs-12 lnbrk"></div>                               
                <div class="col-xs-12 col-md-4">
                    <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                </div>                
                    <div class="col-xs-12 col-md-4">
                    <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Contratistas')">  
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Guardar</b>
                        </button>
                    </sec:authorize>
                    </div>                              
                <div class="col-xs-12 col-md-4">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/contratista/contratistas.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
    </div>
    <div class="col-xs-12" id="divDomicilioNotificacion" >
    <div class="col-xs-12" id="divFormDomicilioNotificacionRgstrCntrtst">
        <div class="row" >
            <form action="${pageContext.request.contextPath}/contratista/domicilio-de-notificacion.htm" method="post" modelAttribute="empresasdomicilio" >
                     <input value="${model.contratista.idContratista}" type="hidden"  name="idContratista" >
                     <input value="${model.domicilioNotificaciones.idEmpresasDomicilio}" type="hidden" name="idEmpresasDomicilio" >                   
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cll">Calle</span>
                      <input value="${model.domicilioNotificaciones.calle}"  type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="cll" name="calle" id="cllIDN" required title="Calle del domicilio de notificación" maxlength="100" autocomplete="off" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-3">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmr">Número</span>
                      <input value="${model.domicilioNotificaciones.numero}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmr" name="numero" required title="Número del domicilio de notificación" maxlength="25" autocomplete="off" minlength="1"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-3">
                    <div class="input-group">
                      <span class="input-group-addon" id="cdgPstl">C.P.</span>
                      <input value="${model.domicilioNotificaciones.codigoPostal}" type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="cdgPstl" name="codigoPostal" required title="Código Postal del Domicilio de notificación" maxlength="8" autocomplete="off" minlength="5"   pattern="[0-9]+">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cln">Colonia</span>
                      <input  value="${model.domicilioNotificaciones.colonia}" type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="cln" name="colonia" id="clnID" required title="Colonia del domicilio de notificación" maxlength="100" autocomplete="off" minlength="5"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cdd">Ciudad</span>
                      <input value="${model.domicilioNotificaciones.ciudad}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="cdd" name="ciudad" id="cddID" required title="Ciudad de domicilio de notificación" maxlength="100" autocomplete="off" minlength="5"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="std">Estado</span>
                      <input value="${model.domicilioNotificaciones.estado}" type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="std" name="estado" required title="Estado del domicilio de notificación" maxlength="100" autocomplete="off" minlength="5"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 lnbrk"></div>                            
                <div class="col-xs-12 col-md-4">
                    <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                </div>
                    <div class="col-xs-12 col-md-4">
                    <sec:authorize access="hasAnyRole('Ver_Todo','Agregar_Editar_Contratistas')">  
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Guardar</b>
                        </button>
                    </sec:authorize>
                    </div>             
                <div class="col-xs-12 col-md-4">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/contratista/contratistas.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
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
