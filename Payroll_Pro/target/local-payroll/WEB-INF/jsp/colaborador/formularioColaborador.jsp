<%-- 
    Document   : formularioColaborador
    Created on : 3/05/2017, 04:59:13 PM
    Author     : PabloSagoz <pablo.samperio@it-seekers.com>
--%>
<%response.setHeader("pragma", "no-cache");
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Expires", "0");%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ taglib uri="/WEB-INF/tld/listLibrary.tld" prefix="listpasg" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../fragmentos/cabecera.jsp" %>
        <title>Payroll - Colaboradores</title>    
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
                    <c:when test="${!model.edicion}">                        
                        <h2 class="selectAction">Nuevo Colaborador</h2>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${model.estatus == 1}">                                
                                <h2 class="selectAction">Edición de un colaborador activo</h2>
                            </c:when>
                            <c:otherwise>                                
                                <h2 class="selectAction">Edición de un colaborador</h2>
                            </c:otherwise>
                        </c:choose>                  
                    </c:otherwise>
                </c:choose>  
                <div  class="col-xs-12">
                        <ul class="list-group well">
                            <li class="list-group-item">
                                <span class="glyphicon glyphicon-pawn" title="Nombre" aria-hidden="true"></span>&nbsp;&nbsp;
                                    <c:choose>
                                        <c:when test="${model.datosPersonales.nombre!=null}">          
                                                ${model.datosPersonales.nombre.toUpperCase()}&nbsp;${model.datosPersonales.apellidoPaterno.toUpperCase()}&nbsp;${((model.datosPersonales.apellidoMaterno==null)?"":model.datosPersonales.apellidoMaterno.toUpperCase())}
                                        </c:when>
                                        <c:otherwise>              
                                            Sin&nbsp;registrar
                                        </c:otherwise>
                                    </c:choose>    
                            </li>
                          </ul>
                </div>
                <input type="hidden" value="${pageContext.request.contextPath}" id="contextPathPage">   
                <input type="hidden" value="${model.contratoID}" id="contextIDPage">              
                <div class="row" id="addColaboradorNew">
                    <div class="col-xs-12">
                        <ul class="nav nav-tabs">
        <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador','Editar_Colaborador_Activo')">     
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.section'))}" >
                <li role="presentation" class="active"><a href="#" id="tabDatosPersonales">
                    <span title="${(model.datosPersonales!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.datosPersonales!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                    Datos personales
                    </a>
                </li>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.section'))}" >
                <li role="presentation"  class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}"><a href="#" id="tabDatosDomicilio">
                    <span title="${(model.DatosDomicilio!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.datosDomicilio!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                    Datos domicilio
                    </a>
                </li>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.section'))}" >
                <li role="presentation"  class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}"><a href="#" id="tabDatosLaborales">
                    <span title="${(model.datosLaborales!=null&&model.datosLaborales.fechaInicioContrato!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.datosLaborales!=null && model.datosLaborales.fechaInicioContrato!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                    Datos laborales
                    </a>
                </li>
            </c:if>
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.section'))}" >
                <li role="presentation"  class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}"><a href="#" id="tabDatosBeneficiario">
                    <span title="${(model.datosBeneficiario!=null)?"Formulario llenado corractamente":"El colaborador no tiene un beneficiario registrado"}" class="${(model.datosBeneficiario!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                    Datos beneficiario
                    </a>
                </li>
            </c:if>                
            <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.section'))}" >
                <li role="presentation"  class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}"><a href="#" id="tabDatosBancarios">
                    <span title="${(model.datosBancarios!=null)?"Formulario llenado corractamente":"Formulario pendiente de registrar"}" class="${(model.datosBancarios!=null)?"glyphicon glyphicon-ok-sign":"glyphicon glyphicon-question-sign"}" aria-hidden="true"></span>&nbsp;&nbsp;
                    Datos bancarios
                    </a>
                </li>
            </c:if>
                <li  role="presentation"  class="${(model.edicion)?"":"disabled"}" style="display:${(model.edicion)?"inherit":"none"}"><a href="#" id="tabDatosExpediente">Expediente</a></li>
        </sec:authorize>
                        </ul>
                    </div>
                    <div class="col-xs-12" id="formControlsColaborador">
                        <div class="row" >
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dPe.section'))}" >
                                <%@include file="fdc_dpersonales.jsp" %>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dDo.section'))}" >
                            <%@include file="fdc_ddomicilio.jsp" %>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dLa.section'))}" >
                                <%@include file="fdc_dlaborales.jsp" %>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBe.section'))}" >
                            <%@include file="fdc_dbeneficiario.jsp" %>
                        </c:if>
                        <c:if test="${(listpasg:existsInEsquemaDePago(model.campos,'dBa.section'))}" >
                                <%@include file="fdc_dbancarios.jsp" %>
                        </c:if>
                            <div class="row" id="divFormDatosExpediente">                               
                                <div class="col-xs-12 lnbrk"></div>
                                <c:if test="${!(model.datoFijo)}">
                                <div class="col-xs-12 col-md-8">
                                     <h1 >Documentos del proceso de alta </h1>
                                </div>
                                <div class="col-xs-12 col-md-4">  
        <sec:authorize access="hasAnyRole('Agregar_Editar_Colaborador')">     
                                    <c:if test="${(model.datosPersonales!=null)&&(model.datosDomicilio!=null)&&(model.datosLaborales!=null && model.datosLaborales.fechaInicioContrato!=null)}">
                                        <form method="post" action="${pageContext.request.contextPath}/colaborador/verificar-expediente-incompleto.htm">
                                            <input type="hidden" value="${model.agremiado.idAgremiado}" id="idAgremiado" name="idAgremiado">
                                        <input type="hidden" value="${model.esquema.idEsquemaPago}" id="schema" name="schemaId">
                                        <input type="hidden" value="${model.contratoID}" id="contratoId" name="contratoId">
                                            <button type="submit" value="" class="form-control btn btn-success btn-lg center-btn btn-helper " id="btnVrfcrClbrdr">
                                                <b>Verificar</b>
                                            </button>
                                    </form>
                                    </c:if>
        </sec:authorize>
                                </div>
                                    <table class="table table-hover">
                                        <tr>
                                            <th>Documento</th>
                                            <th>Requerido</th>
                                            <th>Buscador</th>
                                            <th>Subir</th>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td>
                                                <input type="hidden" value="${model.agremiado.idAgremiado}" id="idAgremiado" name="idAgremiado">
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <c:forEach items="${model.DocumentosRequeridos}" var="tipoDoc">
                                            <tr>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${tipoDoc.catalogoDocumentalObj==null||tipoDoc.catalogoDocumentalObj.servicioConfigurado==false}">
                                                            ${tipoDoc.nombreDocumento}
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a class="templateAvalible" href="${pageContext.request.contextPath}/documental/${tipoDoc.idTipoDocumento}/request.htm?idColaborador=${model.agremiado.idAgremiado}" target="_blank" title="Decargar documento base">${tipoDoc.nombreDocumento}&nbsp;&nbsp;<span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td><b>${(tipoDoc.obligatorio)?"Sí":"No"}</b></td>
                                                <td>
                                                    <input id="_${tipoDoc.idTipoDocumento}_" type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv"  ${(tipoDoc.obligatorio)?"required":""} name="${tipoDoc.idTipoDocumento}"  title="${tipoDoc.nombreDocumento}" accept=".jpg,.jpeg|images/*,.pdf"
                                                           data-buttonText="&nbsp;&nbsp;Adjuntar archivo" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado">
                                                </td>
                                                <td><button class="btn subirArchivosRegistro" value="${tipoDoc.idTipoDocumento}"  type="button" title="cargar archivo"><span  class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span></td>
                                            </tr>     
                                        </c:forEach>
                                    </table>
                                      </c:if>      
                                    <div>
                                        <h1>Documentos cargados </h1> 
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
                                                            <td> <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span> </td>
                                                            <td>${documento.tipoDocumentoObj.nombreDocumento}</td>
                                                            <td>
                                                                    <button type="button" class="btn btn-default redireccionarVentana" value="${pageContext.request.contextPath}/colaborador/ver-documentos/${documento.tipoDocumentoObj.idTipoDocumento}/${model.agremiado.idAgremiado}.htm" title="Ver documento">
                                                                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                                                    </button>
                                                                </td>
                                                            <td>
                                                                <button id="${documento.tipoDocumentoObj.idTipoDocumento}" type="button" class="btn btn-default deleteDocument" value="${model.agremiado.idAgremiado}" title="Eliminar documento" ${(model.datoFijo)?"hidden":""}>
                                                                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                                                </button>
                                                            </td>
                                                        </tr> 
                                                </c:forEach>
                                        </tbody>
                                        </table>
                                    </div>
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
           <c:if test="${model.datosLaborales.percepcionBasadaEnSUP}">
                <script>
                    existsSUP();
                </script>
           </c:if>
    </body>
</html>