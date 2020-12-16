<%-- 
    Document   : fdc_ddomicilio
    Created on : 21/02/2018, 01:04:52 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
    <body>
        <form id="formDatosDomicilio" data-toggle="validator" action="${pageContext.request.contextPath}/colaborador/datosDomicilio.htm" method="post" modelAttribute="datosDomicilio">
            <div class="col-xs-12">
                    <input type="hidden" value="${model.datosDomicilio.agremiado}" id="agremiado" name="agremiado">
                    <input type="hidden" value="${model.agremiado.idAgremiado}" id="idagremiado" name="idAgremiado">
                    <input type="hidden" value="${model.esquema.idEsquemaPago}" id="schema" name="schemaId">
                    <input type="hidden" value="${model.contratoID}" id="contratoId" name="contratoId">
            </div>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.calle'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="cll">Calle</span>
                        <input value="${model.datosDomicilio.calle}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="cll" name="calle" required title="Calle del colaborador" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.numero'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="nmr">Número</span>
                        <input value="${model.datosDomicilio.numero}" type="text" class="form-control toUpperText" maxlength="15" autocomplete="off" placeholder="Campo requerido" aria-describedby="nmr" name="numero" required title="Número exterior/interior del colaborador" minlength="1"   pattern="[A-Z0-9]+([.]*\s[A-Z0-9]+)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.colonia'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="cln">Colonia</span>
                        <input value="${model.datosDomicilio.colonia}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="cln" name="colonia" required title="Colonia del colaborador" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.codigoPostal'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="cp">C.P.</span>
                        <input value="${model.datosDomicilio.codigoPostal}" type="text" maxlength="8" autocomplete="off" required placeholder="Campo requerido" class="form-control toUpperText" aria-describedby="cp" name="codigoPostal" title="Código postal del colaborador" minlength="5"  minlength="3"   pattern="[0-9]+">
                    </div>                            
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.ciudad'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="cdd">Ciudad</span>
                        <input value="${model.datosDomicilio.ciudad}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="cdd" name="ciudad" required title="Ciudad del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div> 
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.municipio'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="mncp">Municipio</span>
                        <input value="${model.datosDomicilio.municipio}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="mncp" name="municipio" required title="Municipio del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>                            
                </div>            
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.estado'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="std">Estado</span>
                        <input value="${model.datosDomicilio.estado}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="std" name="estado" required title="Estado del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.tipoVivienda'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="tpVvnd">Tipo de vivienda</span>
                        <input value="${model.datosDomicilio.tipoVivienda}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="tpVvnd" name="tipoVivienda" required title="Tipo de vivienda del colaborador" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.fachada'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="fchd">Fachada</span>
                        <input value="${model.datosDomicilio.fachada}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="fchd" name="fachada" required title="Fachada del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.colorFachada'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="clr">Color</span>
                    <input value="${model.datosDomicilio.colorFachada}" type="text" class="form-control firstLetterText trimTxt" maxlength="25" autocomplete="off" placeholder="Campo requerido" aria-describedby="clr" name="colorFachada" required title="Color de la fachada del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.entreCalles'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="ntrClls">Entre calles</span>
                        <input value="${model.datosDomicilio.entreCalles}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="ntrClls" name="entreCalles" required title="Entre calles del colaborador" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.tipoVia'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="tpDV">Tipo de vía</span>
                        <input value="${model.datosDomicilio.tipoVia}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="tpDV" name="tipoVia" required title="Tipo de vía del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.referencia'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="rfrnc">Referencia</span>
                        <input value="${model.datosDomicilio.referencia}" type="text" class="form-control firstLetterText trimTxt" maxlength="190" autocomplete="off" placeholder="Campo requerido" aria-describedby="rfrnc" name="referencia" required title="Referencia del colaborador" minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+[-]*[.]*[,]*)*">
                    </div>
                </div>
            </c:if>
            <div class="col-xs-12 lnbrk"></div>      
            <div class="col-xs-12 col-md-6">
                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
            </div>
            <div class="col-xs-12 col-md-2 col-md-offset-1">
            <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador','Editar_Colaborador_Activo')">     
                    <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
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