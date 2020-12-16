<%-- 
    Document   : fdc_dbancarios
    Created on : 2/03/2018, 01:12:40 PM
    Author     : PabloSagoz pablo.samperio@it-seekers.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
    <body>
        <form id="formDatosBancarios" action="${pageContext.request.contextPath}/colaborador/datosBancarios.htm" method="post"  modelAttribute="datosBancarios" >
            <div class="col-xs-12">
                    <input type="hidden" value="${model.datosBancarios.agremiado}" id="agremiado" name="agremiado">
                    <input type="hidden" value="${model.agremiado.idAgremiado}" id="idagremiado" name="idAgremiado">
                    <input type="hidden" value="${model.esquema.idEsquemaPago}" id="schema" name="schemaId">
                    <input type="hidden" value="${model.contratoID}" id="contratoId" name="contratoId">
            </div>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.nombreBanco'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="bnc">Banco de la tarjeta</span>
                    <input value="${model.datosBancarios.nombreBanco}" type="text" class="form-control firstLetterText trimTxt" maxlength="250" autocomplete="off" placeholder="Campo no requerido" aria-describedby="bnc" name="nombreBanco" required title="Banco de la tarjeta del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.titular'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="ttlr">Titular de la tarjeta</span>
                        <input value="${model.datosBancarios.titular}" type="text" class="form-control firstLetterText trimTxt" maxlength="251" autocomplete="off"  placeholder="Campo no requerido" aria-describedby="ttlr" name="titular" required title="Titular de la tarjeta del colaborador" minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                    </div>
                </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.clabe'))}" >
            <div class="col-xs-12 col-md-6">
                <div class="input-group">
                    <span class="input-group-addon" id="clbBncr">Clabe bancaria</span>
                    <input value="${model.datosBancarios.clabe}" type="text" class="form-control toUpperText" maxlength="21" autocomplete="off" placeholder="Campo no requerido" aria-describedby="clbBncr" name="clabe" required title="Clabe bancaria de la tarjeta del colaborador" minlength="21"   pattern="[0-9]+">
                </div>                            
            </div>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.numeroCuenta'))}" >
                <div class="col-xs-12 col-md-6">
                    <div class="input-group">
                        <span class="input-group-addon" id="nmrDCnt">Número de cuenta</span>
                        <input value="${model.datosBancarios.numeroCuenta}" type="text" class="form-control toUpperText" maxlength="21" autocomplete="off" placeholder="Campo no requerido" aria-describedby="nmrDCnt" name="numeroCuenta" title="Número de cuenta del colaborador" required minlength="21"   pattern="[0-9]+">
                    </div>
                </div>
            </c:if>
                <div class="col-xs-12 lnbrk"></div>
            <div class="col-xs-12 col-md-6">
                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
            </div>
            <div class="col-xs-12 col-md-2 col-md-offset-1">
                <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador','Editar_Colaborador_Activo')">     
                    <button type="submit"  class="form-control btn btn-success btn-lg center-btn btn-helper">
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
