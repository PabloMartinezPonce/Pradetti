<%-- 
    Document   : registrarIncidencia
    Created on : 16/05/2017, 04:56:02 PM
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
        <title>Registrar incidencia</title>    
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
                <h2 class="selectAction">Registrar una nueva incidencia</h2>                          
                        <h3>
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            ${model.datosPersonales.nombre.toUpperCase()}&nbsp;${model.datosPersonales.apellidoPaterno.toUpperCase()}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno.toUpperCase())}
                        </h3>   
                <div class="row">
                    <form id="formIncidencia" action="${pageContext.request.contextPath}/colaborador/registrar-incidencia/${model.agremiado.idAgremiado}.htm" method="post">
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                                <input type="hidden" name="idAgremiado" value="${model.agremiado.idAgremiado}">
                                <span class="input-group-addon" id="fchDRgstr">Fecha de registro</span>
                                <input type="datetime-local" class="form-control" placeholder="Campo requerido" aria-describedby="fchDLNcdnc" name="fRegistro" id="fchDRgstrID"  title="Fecha aproximada del registro de la incidencia" disabled>
                            </div>                
                        </div> 
                        <div class="col-xs-12 col-sm-6">
                            <div class="input-group">
                                <span class="input-group-addon" id="fchDLNcdnc">Fecha de la incidencia</span>
                                <input type="date" class="form-control" placeholder="Campo requerido" minlength="10" maxlength="10" aria-describedby="fchDLNcdnc" name="fIncidencia" id="fchDLNcdncID" required title="Fecha en que ocurrió la incidencia">
                            </div>                 
                        </div>
                        <div class="col-xs-8 col-sm-8">
                            <div class="input-group">
                                <span class="input-group-addon" id="tpDNcdnc">Tipo de incidencia</span>
                                <select name="tipoIncidencia" class="form-control" placeholder="Campo requerido" aria-describedby="tpDNcdnc"  required id="tpDNcdncID">
                                    <c:forEach items="${model.tipoIncidencias}" var="tipoIncidencia">
                                        <option  title="(${(tipoIncidencia.criterio)?"+":"-"}) ${(tipoIncidencia.dias)?" Día":" $"}"  value="${tipoIncidencia.idTipoIncidencia}">${tipoIncidencia.descripcion}</option>   
                                    </c:forEach>
                                </select>
                            </div>                
                        </div> 
                        <div class="col-xs-4 col-sm-4">
                            <div class="input-group">
                                <span class="input-group-addon" id="cntdd">Cantidad</span>
                                <input type="text" class="form-control toUpperText" placeholder="Campo requerido"  minlength="1" maxlength="10" autocomplete="off" aria-describedby="cntdd" name="cantidad" id="cntddID" required title="Cantidad correpondiente a la incidencia"  pattern="\d+(\.\d{2})*">
                            </div>                  
                        </div> 
                        <div class="col-xs-12">
                            <div class="input-group">
                                <span class="input-group-addon" id="cmntrs">Comentarios</span>
                                <input type="text" class="form-control firstLetterText trimTxt" maxlength="250" placeholder="Comentarios correpondientes a la incidencia" aria-describedby="comentarios" name="cmntrs" id="cmntrsID"  title="Comentarios correpondientes a la incidencia" required minlength="3"   pattern="[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+([,]*\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                            </div>                  
                        </div>
                        <div class="col-xs-12 lnbrk"></div>
                        <div class="col-xs-12 col-sm-3 col-sm-offset-2">
        <sec:authorize access="hasAnyRole('Registrar_Incidencia')"> 
                            <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                                <b>Guardar</b>
                            </button>
        </sec:authorize>
                        </div>
                        <div class="col-xs-12 col-md-3 col-sm-offset-1">
                            <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/colaboradores/activos.htm">
                                <b>Salir</b>
                            </button>
                        </div>
                    </form>

                </div>
                <div class="col-xs-12 lnbrk"></div>    
            </div>

            <!-- ==================================================== FIN =====  cuerpo de la página ================================================================== -->

            <!-- ==================================================== Sección de las notificaciones flotantes & footer ================================================================== -->
            <%@include file="../fragmentos/pie.jsp" %>

    </body>
</html>
