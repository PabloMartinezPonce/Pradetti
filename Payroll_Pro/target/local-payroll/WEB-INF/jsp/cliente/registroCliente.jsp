<%-- 
    Document   : registroCliente
    Created on : 15/11/2016, 11:00:36 AM
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
<title>Payroll - Clientes</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Clientes      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">    
<%@include file="../fragmentos/menuClientes.jsp" %>
<h2 class="selectAction">${(model.edicion)?"Edición de un cliente":"Registro de un cliente"}</h2>
<c:if test="${(model.edicion)}">
    <div class="col-xs-12 col-md-6" >
        <h3 class="selectAction"><span class="glyphicon glyphicon-queen" aria-hidden="true"></span>&nbsp;&nbsp;${model.cliente.nombreEmpresa}&nbsp;&nbsp;</h3>
    </div>
</c:if>
    <div class="col-xs-12">
        <ul class="nav nav-tabs">
          <li role="presentation" class="active" >              
              <a href="#" id="tabDatosCliente">
                  <span title="${(model.cliente!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.cliente!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                  Datos del cliente
              </a>
          </li>
          <li role="presentation" class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}">
              <a  href="#" id="tabInstrumentoNotarial">
                  <span title="${(model.actaNotarial!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.actaNotarial!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                  Instrumento notarial
              </a>
          </li>
          <li role="presentation" class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}">
              <a href="#" id="tabDomicilioFiscal">
                  <span title="${(model.domicilioFiscal!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.domicilioFiscal!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                  Domicilio físcal
              </a>
          </li>
          <li role="presentation" class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}">
              <a href="#" id="tabDomicilioNorificacion">
                  <span title="${(model.domicilioNotificacion!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.domicilioNotificacion!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                  Domicilio de notificación
              </a>
          </li>
        </ul>
    </div>
    <div class="col-xs-12" id="divFormDatosCliente">
        <div class="row" >
            <form action="${pageContext.request.contextPath}/cliente/datos-cliente.htm" method="post" modelAttribute="cliente" >            
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                        <input value="${model.cliente.idCliente}" type="hidden" name="idCliente" >
                      <span class="input-group-addon" id="nmbrDLMprs">Nombre</span>
                      <input value="${model.cliente.nombreEmpresa}" type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmbrDLMprs" name="nombreEmpresa" id="nmbrDLMprsID" required title="Nombre de la Empresa" maxlength="230" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="rprsntnt">Representante</span>
                      <input value="${model.cliente.representanteLegal}" id="rprsntntID"   type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="rprsntnt" name="representanteLegal" required title="Representante legal de la empresa" maxlength="250" autocomplete="off" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="rfc">RFC</span>
                      <input value="${model.cliente.rfc}" maxlength="15"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="rfc" name="rfc" id="rfcID" required title="RFC de la empresa" autocomplete="off" minlength="15"   pattern="[A-Z]+([0-9A-Z]+)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="tpDScdd">Tipo de sociedad</span>
                      <input value="${model.cliente.tipoSociedad}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="tpDScdd" name="tipoSociedad" required title="Tipo de sociedad de la empresa" maxlength="200" autocomplete="off" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="znSlrl">Zona Salarial</span>
                    <select name="zonaSm" class="form-control" placeholder="Campo requerido" aria-describedby="zonaSm"  required id="znSlrlID">
                        <c:forEach items="${model.zonasSalariales}" var="zona">
                             <c:choose>
                                <c:when test="${zona.idZonaSm == model.cliente.zonaSm}">
                                     <option value="${zona.idZonaSm}" selected="selected">${zona.zona}</option>
                                </c:when>    
                                <c:otherwise>
                                    <option value="${zona.idZonaSm}">${zona.zona}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                    </div>
               </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="fchDRgstr">Fecha de registro</span>
                      <input value="${model.cliente.fechaRegistroPublico}"  type="date" class="form-control" placeholder="Campo requerido" aria-describedby="fchDRgstr" name="fchRegistroPublico" required title="Fecha de Registro Público">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" >Giro</span>
                      <input value="${model.cliente.giro}"   type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="giro" name="giro" id="giroID" required title="Giro de la empresa" maxlength="200" autocomplete="off" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>                            
                </div> 
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="fnScl">Objeto Social</span>
                      <input value="${model.cliente.finSocial}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="fnScl" name="finSocial" id="fnSclID" required title="Objeto Social de la empresa" maxlength="800" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <c:choose>
                    <c:when test="${model.edicion}">
                        <div class="col-xs-12">
                            <div class="input-group">
                              <span class="input-group-addon" id="status">El cliente se encuentra activo dentro del sistema</span>
                              <input ${(model.cliente.status) ? 'checked="checked"' : ''} value='Sí' name="statusCliente" path="status" id="status" type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                            </div>
                       </div>                        
                    </c:when>
                    <c:otherwise>
                          <div class="col-xs-12">
                          <div class="input-group">
                            <input value='Sí' name="statusCliente"  type="hidden"  class="form-control">
                          </div>
                     </div>
                    </c:otherwise>
                </c:choose>
                <div class="col-xs-12 lnbrk"></div>                  
                <div class="col-xs-12 col-md-4">
                    <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                </div>                
                    <div class="col-xs-12 col-md-4">
            <sec:authorize access="hasAnyRole('Ver_Todo','[Agregar,Editar] Clientes')">
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Guardar</b>
                        </button>
            </sec:authorize>
                    </div>                         
                <div class="col-xs-12 col-md-4">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/cliente/clientes.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="col-xs-12" id="divFormInstrumentoNotarial" style="display:none">
        <div class="row" >
            <form action="${pageContext.request.contextPath}/cliente/instrumento-notarial.htm" method="post" modelAttribute="actanotarial"  enctype="multipart/form-data">                   
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
                      <input value="${model.actaNotarial.instrumento}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="nstrmnt" name="instrumento" id="nstrmntID" required title="Instrumento" maxlength="100" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*"> 
                      <input value="${model.cliente.idCliente}" type="hidden" class="form-control" placeholder="Campo requerido" name="clienteID" >
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="vlmn">Volumen</span>
                      <input value="${model.actaNotarial.volumen}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="vlmn" name="volumen" id="vlmnID" required title="Volumen" maxlength="100" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
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
                      <input value="${model.actaNotarial.notario}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmbrNtr" name="notario" id="nmbrNtrID" required title="Nombre del notario" maxlength="100" autocomplete="off" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="drccnNtr">Dirección de la notaria</span>
                      <input value="${model.actaNotarial.direccionNotario}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="drccnNtr" id="drccnNtrID" name="direccionNotario" required title="Dirección del notario" maxlength="100" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmrDNtr">Número de notaria</span>
                      <input value="${model.actaNotarial.numeroNotarial}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmrDNtr" id="nmrDNtrID" name="numeroNotarial" required title="Número de notaria" maxlength="100" autocomplete="off" maxlength="8"   pattern="[0-9]+">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <input  type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="actaNotarialPDF" ${(model.actaNotarial.urlDocumento == null )?"required":""} title="Adjuntar archivo" id="rchvID" accept=".pdf"
                            data-buttonText="&nbsp;&nbsp;Documento legal" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="${(model.actaNotarial.urlDocumento == null )?"Sin archivo seleccionado":model.actaNotarial.urlDocumento.substring(30)}">
                      <input value="${model.actaNotarial.urlDocumento}" type="hidden" class="form-control" placeholder="Campo requerido" name="urlDocumento" >
                      <c:if test="${model.edicion && model.actaNotarial.urlDocumento != null}">
                          <span class="input-group-addon"><a target="_blank" href="${pageContext.request.contextPath}/cliente/${model.cliente.idCliente}/ActaNotarial.htm" title="Ver Acta Notarial">&nbsp;<span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>&nbsp;</a></span>
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
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}   value="${model.poderLegal.representanteLegal}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="rpsntntPL" name="rpsntntPL" id="rpsntntPLID"  title="Representante legal" maxlength="250" autocomplete="off" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*"> 
                              <input value="${model.actaNotarial.idActaNotarial}" type="hidden" class="form-control" placeholder="Campo requerido" aria-describedby="nstrmnt" name="idActaNotarial" >
                            </div>                            
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="rfcPL">RFC</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}   value="${model.poderLegal.rfc}" maxlength="15"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="rfcPL" name="rfcPL" id="rfcPLID"  title="RFC del representante legal" autocomplete="off" minlength="15"   pattern="[A-Z]+([0-9A-Z]+)*"> 
                            </div>                            
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="nstrmntPL">Instrumento</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}   value="${model.poderLegal.instrumento}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="nstrmntPL" name="instrumentoPL" id="nstrmntPLID"  title="Instrumento" maxlength="100" autocomplete="off" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*"> 
                            </div>                            
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="vlmnPL">Volumen</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}   value="${model.poderLegal.volumen}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="vlmn" name="volumenPL" id="vlmnPLID"  title="Volumen" maxlength="100" autocomplete="off" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>                            
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="fchVlmnPL">Fecha volumen</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}   value="${model.poderLegal.fechaVolumen}"  type="date" class="form-control" placeholder="Campo requerido" aria-describedby="fchVlmn" name="fechaVolumenPL" id="fechaVolumenPLID"  title="Fecha volumen">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="nmbrNtrPL">Nombre del notario</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}   value="${model.poderLegal.notario}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmbrNtrPL" name="notarioPL" id="nmbrNtrPLID"  title="Nombre del notario" maxlength="200" autocomplete="off" minlength="8"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="drccnNtrPL">Dirección de la notaria</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}   value="${model.poderLegal.direccionNotario}"  type="text" class="form-control firstLetterText trimTxt" placeholder="Campo requerido" aria-describedby="drccnNtrPL" id="drccnNtrPLID" name="direccionNotarioPL"  title="Dirección del notario" maxlength="250" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                              <span class="input-group-addon" id="nmrDNtrPL">Número de notaria</span>
                              <input ${(model.actaNotarial.tienePoderLegal) ? "required":""}   value="${model.poderLegal.numeroNotarial}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmrDNtrPLL" id="nmrDNtrPLID" name="numeroNotarialPL"  title="Número de notaria" maxlength="100" autocomplete="off" maxlength="8"   pattern="[0-9]+">
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-7">
                            <div class="input-group">
                              <input ${(model.actaNotarial.tienePoderLegal && model.poderLegal.urlDocumento == null ) ? "required":""}    type="file" class="filestyle" class="form-control" placeholder="Campo requerido" name="poderLegalPDF"  title="Adjuntar archivo" id="rchvPLID" accept=".pdf"
                                    data-buttonText="&nbsp;&nbsp;Documento legal" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="${(model.poderLegal.urlDocumento == null )?"Sin archivo seleccionado":model.poderLegal.urlDocumento.substring(30)}">
                                <input value="${model.poderLegal.urlDocumento}" type="hidden" class="form-control" placeholder="Campo requerido" name="urlDocumentoPL" >
                              <c:if test="${model.edicion && model.poderLegal.urlDocumento != null}">
                                  <span class="input-group-addon"><a target="_blank" href="${pageContext.request.contextPath}/cliente/${model.cliente.idCliente}/PoderLegal.htm" title="Ver Poder Legal">&nbsp;<span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>&nbsp;</a></span>
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
            <sec:authorize access="hasAnyRole('Ver_Todo','[Agregar,Editar] Clientes')">
                    <button type="submit" class="form-control btn btn-success btn-lg center-btn  btn-helper">
                        <b>Guardar</b>
                    </button>
            </sec:authorize>
                    </div>                            
                <div class="col-xs-12 col-md-4">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn  btn-helper redireccionar" value="${pageContext.request.contextPath}/cliente/clientes.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="col-xs-12" id="divDomicilioFiscal" style="display:none">
        <div class="row" >
            <form action="${pageContext.request.contextPath}/cliente/domicilio-fiscal.htm" method="post" modelAttribute="empresasdomicilio" >                   
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <input value="${model.cliente.idCliente}" type="hidden" name="idCliente" >
                      <span class="input-group-addon" id="cll">Calle</span>
                      <input value="${model.domicilioFiscal.calle}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="cll" name="calle" id="calle" required title="Calle del domicilio físcal" maxlength="100" autocomplete="off" minlength="4"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-3">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmr">Número</span>
                      <input value="${model.domicilioFiscal.numero}" type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmr" name="numero" required title="Número del domicilio físcal" maxlength="25" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-3">
                    <div class="input-group">
                      <span class="input-group-addon" id="cdgPstl">C.P.</span>
                      <input value="${model.domicilioFiscal.codigoPostal}" type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="codigoPostal" name="codigoPostal" required title="Código Postal del Domicilio Físcal" maxlength="10" autocomplete="off" minlength="5"   pattern="[0-9]+">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cln">Colonia</span>
                      <input value="${model.domicilioFiscal.colonia}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="cln" name="colonia" id="clnID" required title="Colonia del domicilio físcal" maxlength="100" autocomplete="off" minlength="4"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cdd">Ciudad</span>
                      <input value="${model.domicilioFiscal.ciudad}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="cdd" name="ciudad" id="cddID" required title="Ciudad de domicilio físcal" maxlength="100" autocomplete="off" minlength="4"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="std">Estado</span>
                      <input value="${model.domicilioFiscal.estado}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="std" name="estado" required title="Estado del domicilio físcal" maxlength="100" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 lnbrk"></div>
                <div class="col-xs-12 col-md-4">
                    <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                </div>                
                    <div class="col-xs-12 col-md-4">
            <sec:authorize access="hasAnyRole('Ver_Todo','[Agregar,Editar] Clientes')">
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn  btn-helper">
                            <b>Guardar</b>
                        </button>
            </sec:authorize>
                    </div>                                   
                <div class="col-xs-12 col-md-4">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn  btn-helper redireccionar" value="${pageContext.request.contextPath}/cliente/clientes.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="col-xs-12" id="divDomicilioNotificacion" style="display:none">
        <div class="row" >
            <form action="${pageContext.request.contextPath}/cliente/domicilio-de-notificacion.htm" method="post" modelAttribute="empresasdomicilio" >                   
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <input value="${model.cliente.idCliente}" type="hidden" name="idCliente" >
                      <span class="input-group-addon" id="cll">Calle</span>
                      <input value="${model.domicilioNotificacion.calle}"  type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="cll" name="calle" id="cllID" required title="Calle del domicilio de notificación" maxlength="100" autocomplete="off" minlength="4"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-3">
                    <div class="input-group">
                      <span class="input-group-addon" id="nmr">Número</span>
                      <input value="${model.domicilioNotificacion.numero}"  type="text" class="form-control toUpperText trimTxt" placeholder="Campo requerido" aria-describedby="nmr" name="numero" required title="Número del domicilio de notificación" maxlength="25" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-3">
                    <div class="input-group">
                      <span class="input-group-addon" id="cdgPstl">C.P.</span>
                      <input value="${model.domicilioNotificacion.codigoPostal}" type="text" class="form-control toUpperText" placeholder="Campo requerido" aria-describedby="cdgPstl" name="codigoPostal" required title="Código Postal del Domicilio de notificación"  maxlength="10" autocomplete="off" minlength="5"   pattern="[0-9]+">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cln">Colonia</span>
                      <input  value="${model.domicilioNotificacion.colonia}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="cln" name="colonia" id="clnID" required title="Colonia del domicilio de notificación" maxlength="100" autocomplete="off" minlength="4"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="cdd">Ciudad</span>
                      <input value="${model.domicilioNotificacion.ciudad}"  type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="cdd" name="ciudad" id="cddID" required title="Ciudad de domicilio de notificación" maxlength="100" autocomplete="off" minlength="4"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>                            
                </div>
                <div class="col-xs-12 col-sm-6">
                    <div class="input-group">
                      <span class="input-group-addon" id="std">Estado</span>
                      <input value="${model.domicilioNotificacion.estado}" type="text" class="form-control capitalizeText trimTxt" placeholder="Campo requerido" aria-describedby="std" name="estado" required title="Estado del domicilio de notificación" maxlength="100" autocomplete="off" minlength="8"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
                <div class="col-xs-12 lnbrk"></div>                            
                <div class="col-xs-12 col-md-4">
                    <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                </div>                
                    <div class="col-xs-12 col-md-4">
            <sec:authorize access="hasAnyRole('Ver_Todo','[Agregar,Editar] Clientes')">
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                            <b>Guardar</b>
                        </button>
            </sec:authorize>
                    </div>                                
                <div class="col-xs-12 col-md-4">
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/cliente/clientes.htm">
                        <b>Cancelar</b>
                    </button>
                </div>
            </form>
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