<%-- 
    Document   : fdc_dbeneficiario
    Created on : 21/02/2018, 01:05:14 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
    <body>
        <div class="row" id="divFormDatosBeneficiario" >
            <div class="col-xs-12">
                <div class="input-group">
                    <span class="input-group-addon" id="sgrDVd">¿Cuenta con seguro de vida?</span>
                    <input value="Sí" ${model.datosBeneficiario != null ? 'checked="checked"' : ''}  type="checkbox" name="sgrDVd" id="sgrDVdID_AddColaborador" aria-describedby="sgrDVd" required  data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default"  class="form-control">
                </div>
            </div>
                <form style="display:${model.datosBeneficiario != null ? 'inherit' : 'none'}" method="post" modelAttribute="datosBeneficiario" id="formDatosBeneficiario" hidden="hidden" action="${pageContext.request.contextPath}/colaborador/datosBeneficiario.htm">
                    <div class="col-xs-12">                        
                        <input type="hidden" value="${model.datosBeneficiario.agremiado}" id="agremiado" name="agremiado">
                        <input type="hidden" value="${model.agremiado.idAgremiado}" id="idagremiado" name="idAgremiado">
                        <input type="hidden" value="${model.esquema.idEsquemaPago}" id="schema" name="schemaId">
                        <input type="hidden" value="${model.contratoID}" id="contratoId" name="contratoId">
                    </div>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.nombre'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="nmbrDlBnfcr">Nombre del beneficiario</span>
                        <input value="${model.datosBeneficiario.nombre}" type="text" class="form-control firstLetterText trimTxt" maxlength="240" autocomplete="off" placeholder="Campo requerido" aria-describedby="nombre" name="nombre" required title="Nombre del beneficiario del colaborador" id="nmbrDlBnfcrID_AddColaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.porcentaje'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="prcntj">Porcentaje</span>
                        <input type="text" required placeholder="Campo requerido" class="form-control" maxlength="3" autocomplete="off" aria-describedby="porcentaje" name="porcentaje" title="Porcentaje del beneficiario" value="100" disabled>
                        <span class="input-group-addon" >%</span>
                    </div>                            
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.parentesco'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="prntsc">Parentesco</span>
                        <input value="${model.datosBeneficiario.parentesco}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off"  placeholder="Campo requerido" aria-describedby="parentesco" name="parentesco" required title="Parentesco del colaborador" id="prntscID_AddColaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.fechaNaBen'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="fchNcmnt">Fecha de nacimiento</span>
                        <input value="${model.fechaNaBen}" type="date" class="form-control"minlength="10"maxlength="10" placeholder="Campo requerido" aria-describedby="fchNcmnt" name="fechaNaBen" required title="Fecha de nacimiento del beneficiario"  id="fchNcmntID_AddColaborador">
                    </div>
                </div> 
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.curp'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="crp">CURP</span>
                        <input value="${model.datosBeneficiario.curp}" type="text" class="form-control toUpperText trimTxt" minlength="18" maxlength="18" autocomplete="off" placeholder="Campo requerido" aria-describedby="crp" name="curp" required title="CURP del beneficiario"  id="crpID_AddColaborador"    pattern="[A-Z]+([0-9A-Z]+)*">
                    </div>                            
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.calle'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="cll">Calle</span>
                        <input value="${model.datosBeneficiario.calle}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="cll" name="calle" required title="Calle del beneficiario"  id="cllID_AddColaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>                            
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.numero'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="nmr">Número</span>
                        <input value="${model.datosBeneficiario.numero}" type="text" class="form-control toUpperText trimTxt" maxlength="15" autocomplete="off" placeholder="Campo requerido" aria-describedby="nmr" name="numero" required title="Número ext/int del beneficiario"  id="nmrID_AddColaborador" pattern="[A-Z0-9]+([.]*\s*[A-Z0-9]+)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.codigoPostal'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="cdgPstl">C.P.</span>
                        <input value="${model.datosBeneficiario.codigoPostal}" type="text" class="form-control toUpperText" maxlength="8" autocomplete="off" placeholder="Campo requerido" aria-describedby="cdgPstl" name="codigoPostal" required title="Código postal del beneficiario"  id="cdgPstlID_AddColaborador" minlength="5"   pattern="[0-9]+">
                    </div>                            
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.colonia'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="cln">Colonia</span>
                        <input value="${model.datosBeneficiario.colonia}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="cln" name="colonia" required title="Colonia del beneficiario"  id="clnID_AddColaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div> 
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.ciudad'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="cdd">Ciudad</span>
                        <input value="${model.datosBeneficiario.ciudad}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="cdd" name="ciudad" required title="Ciudad del beneficiario" id="cddID_AddColaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>                            
                </div> 
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.municipio'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="mncp">Municipio</span>
                        <input value="${model.datosBeneficiario.municipio}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="mncp" name="municipio" required title="Municipio del beneficiario" id="mncpID_AddColaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>                            
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.estado'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="std">Estado</span>
                        <input value="${model.datosBeneficiario.estado}" type="text" class="form-control firstLetterText trimTxt" maxlength="90" autocomplete="off" placeholder="Campo requerido" aria-describedby="std" name="estado" title="Estado del colaborador" required id="stdID_AddColaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
            </c:if>
                <div class=" col-xs-12 lnbrk"></div>
                <div class="col-xs-12 col-md-6">
                    <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                </div>
                <div class="col-xs-12 col-md-2 col-md-offset-1">
                    <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador','Editar_Colaborador_Activo')">     
                            <button type="submit"  class="form-control btn btn-success btn-lg center-btn btn-helper" id="btnGrdrID_AddColaborador">
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
        </div>
    </body>
</html>
