<%-- 
    Document   : solicitudDeBaja
    Created on : 16/05/2017, 06:45:10 PM
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
        <title>Solicitud de baja</title>    
    </head>
    <body>
        <!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
        <%@include file="../fragmentos/menu.jsp" %>
        <!-- ==================================================== cuerpo de la página ================================================================== -->
        <div class="col-xs-12 main" id="sidebody">
            <h1 class="page-header" id="titlePage">
                <span class=" glyphicon glyphicon-pawn" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Colaboradores       
            </h1>
            <!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
            <div class="col-lg-10 col-lg-offset-1" id="frameContainer">
                <%@include file="../fragmentos/menuColaborador.jsp" %>
                <div class="col-xs-12 lnbrk"></div>
                <h2 class="selectAction">Solicitud de baja de un colaborador</h2>
                <div class="col-xs-12">
                    <h3>
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        ${model.datosPersonales.nombre.toUpperCase()}&nbsp;${model.datosPersonales.apellidoPaterno.toUpperCase()}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno.toUpperCase())}
                    </h3>
                </div>
                    <form id="formBajaSol" action="${pageContext.request.contextPath}/colaborador/solicitud-de-baja.htm" method="post">
                    <div class="col-xs-12 col-md-4">
                        <div class="input-group">
                            <input type="hidden" value="${model.agremiado.idAgremiado}" name="idAgremiado">
                            <span class="input-group-addon" id="fchDBj">Fecha de baja</span>
                            <input type="date" class="form-control" placeholder="Campo requerido" aria-describedby="fchDBj" name="fSolBaja" required title="Fecha en la que causará baja el colaborador">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-3">
                        <div class="input-group">
                            <span class="input-group-addon" id="sld">Sueldo</span>
                            <input value="Si" name="sueldo" id="sldID" type="checkbox" data-toggle="toggle" data-on="Si" data-off="No" data-onstyle="success"  data-offstyle="default" class="form-control">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon" id="tpDFnqt">Tipo de finiquito</span>
                            <select name="tipoFiniquito" class="form-control" placeholder="Campo requerido" aria-describedby="tpDFnqt"  required title="Tipo de finiquito a entregar">
                                <c:forEach items="${model.tiposFiniquito}" var="tipoFiniquito">
                                    <option value="${tipoFiniquito.idTipoFiniquito}">${tipoFiniquito.descripcion}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-5">
                        <div class="input-group">
                            <span class="input-group-addon" id="mtvDBj">Motivo de baja</span>
                            <select name="motivoBaja" class="form-control" placeholder="Campo requerido" aria-describedby="mtvDBj" required title="Motivo por el cual el colaborador causa baja">
                                <c:forEach items="${model.motivosBaja}" var="motivoBaja">
                                    <option value="${motivoBaja.idMotivoBaja}">${motivoBaja.motivo}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-7">
                        <div class="input-group">
                            <span class="input-group-addon" id="mtv">Motivo</span>
                            <input type="text" class="form-control trimTxt" placeholder="Campo requerido" aria-describedby="mtv" name="motivo" title="Motivo de la baja del colaborador" required minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([.]*[,]*[\s]*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                        </div>
                    </div>
                    <div class="col-xs-12 col-md-12">
                        <div class="input-group">
                            <span class="input-group-addon" id="mtv">Observaciones</span>
                            <input id="obs"  type="text" class="form-control trimTxt" placeholder="Campo requerido" aria-describedby="mtv" name="observaciones" title="Observaciones de la baja del colaborador" required minlength="3"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+([.]*[,]*[\s]*[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                        </div>
                    </div>
                    <div class="text-center panel-body col-xs-12 col-md-12">

                        <div id="pickList">
                            <div class='col-xs-12 col-md-5'>
                                <h4>Documentos disponibles</h4>
                                <select class='form-control pickListSelect pickData' multiple>
                                    <c:forEach items="${model.tiposDocumento}" var="tipoDocumento">
                                        <option value="${tipoDocumento.idTipoDocumento}">${tipoDocumento.nombreDocumento}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class='col-xs-2 col-md-2 pickListButtons'>
                                <br/>
                                <h5>Acciones </h5>
                                <button type="button" class='pAdd btn btn-default'>
                                    <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
                                </button>
                                <button type="button" class='pAddAll btn btn-default'>
                                    <span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
                                </button>
                                <br/>
                                <br/>
                                <button type="button" class='pRemove btn btn-default'>
                                    <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
                                </button>
                                <button type="button" class='pRemoveAll btn btn-default'>
                                    <span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
                                </button>
                            </div>
                            <div class='col-xs-12 col-md-5'>
                                <h4>Documentos que se deben de elaborar </h4>
                                <select class='form-control pickListSelect pickListResult' multiple id="destinoDoctos" required name="documentos"></select>
                            </div>
                        </div>
                        <div class="col-xs-12 lnbrk"></div>
                    </div>
                    <div class="col-xs-12 lnbrk"></div>
                    <div class="col-xs-12 col-sm-4 col-sm-offset-1">
                        <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
                    </div>
                    <div class="col-xs-12 col-sm-3 col-sm-offset-1">
        <sec:authorize access="hasAnyRole('Generar_Solicitud_De_Baja')">    
                        <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper" id="selectAllOptions">
                            <b>Guardar</b>
                        </button>
        </sec:authorize>
                    </div>
                    <div class="col-xs-12 col-sm-3">
                            <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm">
                                <b>Cancelar</b>
                            </button>
                    </div>
                </form>  
                <div class="col-xs-12 lnbrk"></div>   
            </div>
            <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->

            <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
            <%@include file="../fragmentos/pie.jsp" %>

    </body>
</html>