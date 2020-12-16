<%-- 
    Document   : documentosYFondoDeAhorro
    Created on : 15/05/2019, 12:38:41 PM
    Author     : Gabriela Jaime
--%>
<%response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Expires", "0");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../fragmentos/cabecera.jsp" %>
        <title>Fondo de ahorro</title>
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
                <h2 class="selectAction">Solicitar fondo de ahorro</h2>
                <div class="row"> 
                    <div class="col-xs-4"><h3>Nombre del colaborador</h3></div>
                    <div class="col-xs-8">         
                        <h3>
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            ${model.datosPersonales.nombre.toUpperCase()}&nbsp;${model.datosPersonales.apellidoPaterno.toUpperCase()}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno.toUpperCase())}
                        </h3>   
                    </div>
                    <div class="col-xs-4"><h3>RFC</h3></div>
                    <div class="col-xs-8"><h3><i>${model.datosPersonales.rfc}</i></h3></div>
                </div>
                <div class="row">                            
                    <div class="col-xs-12 lnbrk">
                    </div>
                    <div class="col-xs-12 col-md-8">
                         <h1 >Documentos para continuar el proceso de alta </h1>
                    </div>
                    <div class="col-xs-12 col-md-4">         
                            <form method="post" action="${pageContext.request.contextPath}/colaborador/verificar-documentos-fondo-ahorro.htm">
                                <input type="hidden" id="contextPathPage" value="${pageContext.request.contextPath}">
                                <input type="hidden" value="${model.agremiado.idAgremiado}" id="idAgremiado" name="idAgremiado">
                                <button type="submit" value="" class="form-control btn btn-success btn-lg center-btn btn-helper " id="btnVrfcrClbrdr">
                                    <b>Continuar</b>
                                </button>
                        </form>
                    </div>
                </div>
                    <table class="table table-hover">
                        <tr>
                            <th>Documento</th>
                            <th>Requerido</th>
                            <th>Buscador</th>
                            <th>Subir<br>documento</th>
                        </tr>
                        <c:forEach items="${model.tipoDocumento}" var="tipoDocumento">
                            <c:if test="${tipoDocumento.status}" > 
                                <form action="" method="post" enctype="multipart/form-data" id="_${tipoDocumento.idTipoDocumento}">
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${tipoDocumento.catalogoDocumentalObj==null||tipoDocumento.catalogoDocumentalObj.servicioConfigurado==false}">
                                                    ${tipoDocumento.nombreDocumento}
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="templateAvalible" href="${pageContext.request.contextPath}/documental/${tipoDocumento.idTipoDocumento}/request.htm?idColaborador=${model.agremiado.idAgremiado}" target="_blank" title="Decargar documento base">${tipoDocumento.nombreDocumento}&nbsp;&nbsp;<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><b>${(tipoDocumento.obligatorio)? "Obligatorio": "No requerido"}</b></td>
                                        <td>
                                            <input id="_${tipoDocumento.idTipoDocumento}_" type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="file" ${(tipoDocumento.obligatorio)? "required": ""} title="Archivos del colaborador" accept=".pdf"
                                                   data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado">
                                        </td>
                                        <td><button class="btn subirArchivosContrato" value="${tipoDocumento.idTipoDocumento}"  type="button" title="cargar archivo"><span  class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span></td>
                                    </tr>
                                </form>
                            </c:if>
                        </c:forEach>                                   
                    </table>
                    <div class="row">
                        <div class="col-xs-12 col-md-2 col-md-offset-1">                            
                            <button type="reset" value="${pageContext.request.contextPath}/colaboradores/todos-los-colaboradores.htm" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar">
                              <b>Volver</b>
                          </button>
                        </div>
                  </div> 
                        <div class="row">
                            <div class="col-xs-12 col-md-10 col-md-offset-1">                                
                                <h1>Documentos cargados</h1> 
                                        <table id="tableDocs" class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>Documento</th>
                                                    <th>Ver</th>
                                                    <th>Eliminar</th>
                                                </tr>
                                            </thead>                                            
                                            <tbody id="innerTableDocs">         
                                                <c:forEach items="${model.documentosDelColaborador}" var="documento">
                                                    
                                                        <tr>
                                                            <c:if test="${documento.tipoDocumentoObj.nombreDocumento == 'Fondo de ahorro'}">
                                                            <td> <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span> </td>
                                                            <td>${documento.tipoDocumentoObj.nombreDocumento}</td>
                                                            <td>
                                                                    <button type="button" class="btn btn-default redireccionarVentana" value="${pageContext.request.contextPath}/colaborador/ver-documentos/${documento.tipoDocumentoObj.idTipoDocumento}/${model.agremiado.idAgremiado}.htm" title="Ver documento">
                                                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                                                    </button>
                                                                </td>
                                                            <td>
                                                                <button id="${documento.tipoDocumentoObj.idTipoDocumento}" type="button" class="btn btn-default deleteDocument" value="${model.agremiado.idAgremiado}" title="Eliminar documento">
                                                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                                                </button>
                                                            </td>
                                                            </c:if> 
                                                        </tr> 
                                                       
                                                </c:forEach>
                                        </tbody>
                                        </table>
                            </div>
                        </div>                              
                    </div>
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
