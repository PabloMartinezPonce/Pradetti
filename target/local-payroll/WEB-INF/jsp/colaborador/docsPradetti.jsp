<%-- 
    Document   : docsPradetti
    Created on : 17/05/2017, 04:59:47 PM
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
        <c:choose>
            <c:when test="${model.documentosFinales}">
                <title>Documentos finales</title>    
            </c:when>
            <c:otherwise>
                    <title>Expediente por completar</title>    
            </c:otherwise>
        </c:choose>
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
                <c:choose>
                    <c:when test="${model.documentosFinales}">
                            <h2 class="selectAction">Documentos finales del proceso de baja</h2>
                    </c:when>
                    <c:otherwise>
                            <h2 class="selectAction">Documentos del proceso de baja</h2>  
                    </c:otherwise>
                </c:choose>
                
                <div class="row">
                    <div class="col-xs-12">
                        <div class="row">
                            <div class="col-xs-12 col-md-8">
                                <h1><i>${model.datosPersonales.nombre}&nbsp;${model.datosPersonales.apellidoPaterno}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno)}</i></h1>
                            </div>
                            <div class="col-xs-12 col-md-3 col-md-offset-1">                
                                <c:choose>
                                    <c:when test="${model.documentosFinales}">
                                             <form action="${pageContext.request.contextPath}/colaborador/documentos-finales.htm" method="post">
                                    </c:when>
                                    <c:otherwise>
                                            <form action="${pageContext.request.contextPath}/colaborador/continuar-proceso-de-baja.htm" method="post">
                                    </c:otherwise>
                                </c:choose>
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
                        <th>Buscador</th>
                        <th>Subir</th>
                    </tr>
                    
        <c:choose>
            <c:when test="${model.documentosFinales}">
                
                    <c:forEach items="${model.documentosBajaFinales}" var="tipoDocumentoObj">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${tipoDocumento.catalogoDocumentalObj==null||tipoDocumento.catalogoDocumentalObj.servicioConfigurado==false}">
                                        ${tipoDocumentoObj.nombreDocumento}
                                    </c:when>
                                    <c:otherwise>
                                        <a class="templateAvalible" href="${pageContext.request.contextPath}/documental/${tipoDocumentoObj.idTipoDocumento}/request.htm?idColaborador=${model.agremiado.idAgremiado}" target="_blank" title="Decargar documento base">${tipoDocumentoObj.nombreDocumento}&nbsp;&nbsp;<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <input id="_${tipoDocumentoObj.idTipoDocumento}_" type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="file"  title="Archivos del colaborador a cargar" accept=".xlsx,.pdf"
                                       data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado">
                            </td>
                            <td><button class="btn docBajas"  type="button" value="${tipoDocumentoObj.idTipoDocumento}" title="cargar archivo">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;</button></td>
                        </tr>   
                    </c:forEach>
                        
            </c:when>
            <c:otherwise>
                    
                    <c:forEach items="${model.documentosBaja}" var="documentoBaja">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${documentoBaja.tipoDocumentoObj.catalogoDocumentalObj==null||documentoBaja.tipoDocumentoObj.catalogoDocumentalObj.servicioConfigurado==false}">
                                        ${documentoBaja.tipoDocumentoObj.nombreDocumento}
                                    </c:when>
                                    <c:otherwise>
                                        <a class="templateAvalible" href="${pageContext.request.contextPath}/documental/${documentoBaja.tipoDocumentoObj.idTipoDocumento}/request.htm?idColaborador=${model.agremiado.idAgremiado}" target="_blank" title="Decargar documento base">${documentoBaja.tipoDocumentoObj.nombreDocumento}&nbsp;&nbsp;<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <input id="_${documentoBaja.tipoDocumentoObj.idTipoDocumento}_" type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="file"  title="Archivos del colaborador" accept=".xlsx,.pdf"
                                       data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado">
                            </td>
                            <td><button class="btn docBajas"  type="button" value="${documentoBaja.tipoDocumentoObj.idTipoDocumento}" title="cargar archivo">&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;</button></td>
                        </tr>   
                    </c:forEach>
                        
            </c:otherwise>
        </c:choose>
                        
                </table>      
                <div class="col-xs-12 lnbrk"></div> 
                <div class="col-xs-12 ">
                    <div class="row">             
                <c:choose>
                    <c:when test="${model.documentosFinales}">                        
                        <div class="col-xs-12 col-md-3 col-md-offset-1">                                                       
                                <button type="reset" value="${pageContext.request.contextPath}/colaboradores/bajas-por-finalizar.htm" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar">
                                  <b>Volver</b>
                              </button>
                        </div>
                    </c:when>
                    <c:otherwise>                        
                        <div class="col-xs-12 col-md-3 col-md-offset-1">                                                       
                                <button type="reset" value="${pageContext.request.contextPath}/colaboradores/bajas-solicitadas.htm" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar">
                                  <b>Volver</b>
                              </button>
                        </div>
                    </c:otherwise>
                </c:choose>
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
                            <c:if test="${documentoBaja.cargaGuardadaPra}">
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