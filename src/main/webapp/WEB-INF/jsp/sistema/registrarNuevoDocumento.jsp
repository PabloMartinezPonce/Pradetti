<%-- 
    Document   : registrarNuevoDocumento
    Created on : 28/12/2017, 12:23:03 PM
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
<title>Payroll - Sistema</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <span class="glyphicon glyphicon-knight" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sistema      
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">   
<%@include file="../fragmentos/menuSistema.jsp" %>
<div class="col-xs-12 lnbrk"></div>
<h2 class="selectAction">${(model.edicion)?"Editar un documento":"Registrar un nuevo documento"}</h2>
<div class="col-xs-12 lnbrk"></div>
<div class="sistemaContainer"> 
    
<sec:authorize access="hasAnyRole('Registrar_Editar_Documentos')">
    <form method="post" id="formRegistrarNuevoDocumento" action="${pageContext.request.contextPath}/sistema/datos-del-documento.htm">
        <div class="row">            
            <div class="col-xs-12 col-md-8 col-md-offset-2">
                <div class="input-group">
                  <span class="input-group-addon" id="">Documento Base</span>
                <select name="catDocumental" class="form-control" placeholder="Campo requerido" aria-describedby="v"  required id="catDocumental">
                    <c:forEach items="${model.catalogoDocumental}" var="cd">
                        <c:choose>
                            <c:when test="${cd.idCatalogo==model.documento.catalogoDocumentalObj.idCatalogo}">
                                <option selected value="${cd.idCatalogo}">${cd.nombreDocumento}</option>
                            </c:when>
                            <c:otherwise>                                
                                <option value="${cd.idCatalogo}">${cd.nombreDocumento}</option>
                            </c:otherwise>
                        </c:choose>
                  </c:forEach>
                </select>
                </div>
           </div>
            <div class="col-xs-12 col-md-8 col-md-offset-2">
                <div class="input-group">
                    <input type="hidden" name="id" value="${model.documento.idTipoDocumento}">
                    <span class="input-group-addon" id="nmbrDlDcmnt">Nombre del documento</span>
                    <input value="${model.documento.nombreDocumento}" type="text" class="form-control" placeholder="Campo requerido" aria-describedby="nmbrDlDcmnt" name="nmbrDlDcmnt" required title="Nombre del documento" minlength="5"   pattern="[a-zA-ZñÑáéíóúÁÉÍÓÚ]+(\s[0-9a-zA-ZñÑáéíóúÁÉÍÓÚ]+)*">
                </div>
            </div>
            <div class="col-xs-12 col-md-8 col-md-offset-2">
                <div class="input-group">
                  <span class="input-group-addon" id="bccndlRchv">Módulo del Archivo</span>
                <select name="bccndlRchv" class="form-control" placeholder="Campo requerido" aria-describedby="v"  required id="bccndlRchvID">
                    <c:forEach items="${model.modulos}" var="modulo">
                        <c:choose>
                            <c:when test="${modulo.idModulo==model.documento.moduloObj}">                                
                                <option selected value="${modulo.idModulo}">${modulo.nombre}</option>
                            </c:when>
                            <c:otherwise>                                
                                <option value="${modulo.idModulo}">${modulo.nombre}</option>
                            </c:otherwise>
                        </c:choose>
                  </c:forEach>
                </select>
                </div>
           </div>
            <div class="col-xs-12 col-md-2 col-md-offset-2 ">
                <div class="input-group">
                  <span class="input-group-addon" id="dcmntRqrd">Documento requerido</span>
                  <input ${(model.documento.obligatorio)?'checked="checked"' : ''} name="requerido"  type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                </div>
            </div>     
            <div class="col-xs-12 col-md-3 col-md-offset-1 ">
                <div class="input-group">
                  <span class="input-group-addon" id="dcmntRqrd">Documento activo</span>
                  <input ${(model.documento.status)?'checked="checked"' : ''} name="estatus"type="checkbox" data-toggle="toggle" data-on="Sí" data-off="No" data-onstyle="success" data-offstyle="default" class="form-control">
                </div>
            </div>
            <div class="col-xs-12 col-sm-6 col-sm-offset-3">
            <div class="col-xs-6"><h4 class="titulo-h4">Esquemas disponibles</h4></div>
                <div class="col-xs-6"><h4 class="titulo-h4">Esquemas del documento</h4></div>
                    <div class="col-xs-5">
                        <div class="containerListPermisos">
                            <select name="origenEsquemas" id="origenEsquemas" multiple="multiple" size="${model.esquemas.size()}">
                                <c:forEach items="${model.esquemas}"  var="esquemasD">
                                    <option value="${esquemasD.idEsquemaPago}">${esquemasD.descripcion}</option>                            
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                <div class="col-xs-2 centerMiddleEsquemas" title="Pasar todas los esquemas">
                    <h2><span class="glyphicon glyphicon-transfer" aria-hidden="true"></span></h2> 
                </div>
                <div class="col-xs-5">
                    <div class="containerListPermisos">   
                        <select required name="destinoEsquemas" id="destinoEsquemas" multiple="multiple" size="${model.esquemaPagos.size()}">
                            <c:forEach items="${model.documento.esquemaPagos}"  var="esqPago">
                                <option value="${esqPago.idEsquemaPago}">${esqPago.descripcion}</option>                            
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>       
        </div>
        <div class="row">
    <div class="col-xs-12 lnbrk"></div>                                                
            <div class="col-xs-12 col-sm-4 col-sm-offset-1">
                <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
            </div>
            <div class="col-xs-12 col-sm-3 col-sm-offset-1">
                <button id="selectAllOptions" type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                    <b>Guardar</b>
                </button>
            </div>
            <div class="col-xs-12 col-sm-3">
                <c:choose>
                    <c:when test="${(model.edicion)}">                        
                    <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/documentos-por-modulo.htm">
                        <b>Cancelar</b>
                    </button>
                    </c:when>
                    <c:otherwise>
                        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/opciones-del-sistema.htm">
                            <b>Cancelar</b>
                        </button>                        
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form>
</sec:authorize>               
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