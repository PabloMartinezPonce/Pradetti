<%-- 
    Document   : docsCliente
    Created on : 19/05/2017, 10:39:04 AM
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
        <title>Expediente por completar</title>    
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
                <h2 class="selectAction">Documentos del proceso de baja</h2>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-12 col-md-8">
                                <h1><i>${model.datosPersonales.nombre}&nbsp;${model.datosPersonales.apellidoPaterno}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno)}</i></h1>
                            </div>
                            <div class="col-xs-12 col-md-3 col-md-offset-1">
                                <form action="${pageContext.request.contextPath}/colaborador/continuar-proceso-de-baja.htm" method="post">
                                    <input type="hidden" name="contextPath" value="${pageContext.request.contextPath}" id="contextPath">
                                    <input type="hidden" name="idAgremiado" value="${model.agremiado.idAgremiado}" id="idAgremiado">
                                    <input type="hidden" name="idSolicitudBaja" value="${model.ultimaSolicitudBaja.idSolicitudBaja}" id="idSolicitudBaja">
                                    <input type="hidden" name="posicion" value="${model.posicion}" id="posicion">
                                    <input type="hidden" name="modulo" value="${model.modulo}" id="modulo">
                                    <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper" id="btnVrfcrClbrdr">
                                        <b>Guardar</b>
                                    </button>
                                </form>
                            </div>                                 
                        </div>                           
                        </div>
                </div>
                <table class="table table-hover">
                    <tr>
                        <th>Documento</th>
                        <th>Documento<br>sin firma</th>
                        <th>Buscador</th>
                        <th>Subir</th>
                    </tr>
                    <c:forEach items="${model.documentosBaja}" var="documentoBaja">
                        <%--<c:if test="${documentoBaja.cargaGuardadaPra}">--%>
                            <tr>
                                <td>${documentoBaja.tipoDocumentoObj.nombreDocumento}</td>                            
                                <td>
                                    <button type="button" class="btn btn-default redireccionarVentana" title="Ver documento cargado por Pradetti" value="${pageContext.request.contextPath}/colaborador/ver-documento-de-baja/Pradetti/${model.agremiado.idAgremiado}/${model.ultimaSolicitudBaja.idSolicitudBaja}/${documentoBaja.tipoDocumentoObj.idTipoDocumento}.htm">
                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                    </button>
                                </td>
                                <td>
                                    <input id="_${documentoBaja.tipoDocumentoObj.idTipoDocumento}_" type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="file"  title="Archivos del colaborador" accept=".xlsx,.pdf"
                                           data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado">
                                </td>
                                <td><button class="btn docBajas"  type="button" value="${documentoBaja.tipoDocumentoObj.idTipoDocumento}" title="Cargar archivo firmado">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;</button></td>
                            </tr>   
                        <%--</c:if>--%>
                    </c:forEach>
                </table>      
                <div class="col-xs-12 lnbrk"></div> 
                <div class="col-xs-12 ">
                    <div class="row">
                        <div class="col-xs-12 col-md-3 col-md-offset-1">                                                       
                                <button type="reset" value="${pageContext.request.contextPath}/colaboradores/bajas-pendientes.htm" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar">
                                  <b>Volver</b>
                              </button>
                        </div>
                    </div>
               </div> 
                <div class="col-xs-12 lnbrk"></div>   
                <h1>Documentos cargados </h1> 
                <table id="tableDocs" class="table table-hover">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Documento</th>
                            <th>Ver</th>
                            <th>Borrar</th>
                        </tr>
                    </thead>
                    <tbody id="innerTableDocs">
                        <c:forEach items="${model.documentosBaja}" var="documentoBaja">
                            <c:if test="${documentoBaja.cargaGuardadaUsu}">
                                <tr>
                                    <td> <span   class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span> </td>
                                    <td>${documentoBaja.tipoDocumentoObj.nombreDocumento}</td>
                                    <td>
                                        <button type="button" class="btn btn-default redireccionarVentana" title="Ver documento" value="${pageContext.request.contextPath}/colaborador/ver-documento-de-baja/${model.posicion}/${model.agremiado.idAgremiado}/${model.ultimaSolicitudBaja.idSolicitudBaja}/${documentoBaja.tipoDocumentoObj.idTipoDocumento}.htm">
                                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                    <td>                                                
                                        <button id="" type="button" class="btn btn-default deleteDocumentBaja" value="${pageContext.request.contextPath}/colaborador/borrar-documento-de-baja/${model.posicion}/${model.agremiado.idAgremiado}/${model.ultimaSolicitudBaja.idSolicitudBaja}/${documentoBaja.tipoDocumentoObj.idTipoDocumento}.htm" title="Eliminar documento">
                                            <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                        </button>
                                    </td>
                                </tr>  
                            </c:if>
                            </c:forEach>
                    </tbody>
                </table>
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