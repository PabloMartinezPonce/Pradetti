<%-- 
    Document   : contratoEntreEmpresasFirmado
    Created on : 26/01/2017, 04:33:57 PM
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
    <title>Generar contrato</title>    
</head>
<body>
<!-- ==================================================== Sección del menu y header de la página web ================================================================== -->
<%@include file="../fragmentos/menu.jsp" %>
<!-- ==================================================== cuerpo de la página ================================================================== -->
<div class="col-xs-12 main" id="sidebody">
<h1 class="page-header" id="titlePage">
    <c:choose>
        <c:when test="${model.edicion}">
            <span class="glyphicon glyphicon-knight" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Sistema 
        </c:when>
        <c:otherwise>
            <span class="glyphicon glyphicon-king" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Contratistas 
        </c:otherwise>
    </c:choose>     
</h1>
<!-- ====================================== Identitificador del div ========================== frameContainer ================================================== -->
<div class="col-lg-10 col-lg-offset-1" id="frameContainer">
<%@include file="../fragmentos/menuSistema.jsp" %>      
<div class="col-xs-12 lnbrk"></div>    
<h2 class="selectAction">Subir contrato firmado</h2>
  <div class="row">
        <form action="${pageContext.request.contextPath}/sistema/contrato-entre-empresas-firmado.htm" method="post" enctype="multipart/form-data">              
        <div class="col-xs-12">
            <h3 class="subheading">Datos Del Contrato</h3>
            <input type="hidden" value="${model.contrato.idContratoEmpresas}" name="idContratoEmpresas">
        </div> 
        <div class="col-xs-12 col-md-8">
            <h4>Contratista:&nbsp;<b>${model.contratista.nombreEmpresa.toUpperCase()}</b></h4>
        </div>
        <div class="col-xs-12 col-md-4">
            <h4>RFC:&nbsp;<b>${model.contratista.rfc}</b></h4>
        </div>
        <div class="col-xs-12 col-md-8">
            <div class="input-group">
              <span class="input-group-addon" id="clnt">Cliente</span>
              &nbsp;&nbsp;<b>${model.cliente.nombreEmpresa.toUpperCase()}</b>
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="rfc">RFC</span>
              &nbsp;&nbsp;<b>${model.cliente.rfc.toUpperCase()}</b>
            </div>
        </div>                   
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="fch">Fecha</span>
              &nbsp;&nbsp;<b>${model.contrato.fecha}</b>
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="prdDPg">Periodo de pago</span>
                &nbsp;&nbsp;<b>${model.contrato.periodo}</b>
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="cmsn">Comisión</span>
                &nbsp;&nbsp;<b>${model.contrato.comision}&nbsp;%</b>
            </div>
        </div>               
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="fch">Informes</span>
                &nbsp;&nbsp;<b>${model.contrato.informes}&nbsp;</b>
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="prdDPg">Evaluaciones</span>
                &nbsp;&nbsp;<b>${model.contrato.evaluaciones}&nbsp;</b>
            </div>
        </div>
        <div class="col-xs-12 col-md-4">
            <div class="input-group">
              <span class="input-group-addon" id="cmsn">Depósito</span>
                &nbsp;&nbsp;<b>${model.contrato.deposito}&nbsp;Horas</b>
            </div>
        </div>
            <div class="col-xs-6">
                <div class="input-group">
                  <span class="input-group-addon" id="cmsn">Tiempo del contrato</span>
                    &nbsp;&nbsp;<b>${model.contrato.tiempoDelContrato}&nbsp;</b>
               </div>
            </div>
            <div class="col-xs-6">
                <div class="input-group">
                  <span class="input-group-addon" id="cmsn">Nombre del contrato</span>
                    &nbsp;&nbsp;<b>${model.contrato.nombreContrato}&nbsp;</b>
               </div>
            </div>
            <div class="col-xs-12 lnbrk"></div>
            <div class="row"   >   
                <div class="col-xs-12 col-md-6">
                    <div class="col-xs-12">
                        <h3 class="subheading">Testigo del contratista</h3>
                    </div>
                    <div class="col-xs-12">
                        <div class="input-group">
                          <span class="input-group-addon" id="nmbrCntst">Nombre</span>
                            &nbsp;&nbsp;<b>${model.contrato.tcontratistaNombre}&nbsp;</b>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="input-group">
                          <span class="input-group-addon" id="cpcnCntst">Ocupación</span>
                            &nbsp;&nbsp;<b>${model.contrato.tcontratistaOcupacion}&nbsp;</b>
                        </div>
                    </div> 
                    <div class="col-xs-12">
                        <div class="input-group">
                          <span class="input-group-addon" id="dmclCntst">Originario</span>
                            &nbsp;&nbsp;<b>${model.contrato.tcontratistaOrigen}&nbsp;</b>
                        </div>
                    </div> 
                </div>
                <div class="col-xs-12 col-md-6">
                    <div class="col-xs-12">
                        <h3 class="subheading">Testigo del contratante</h3>
                    </div>
                    <div class="col-xs-12">
                        <div class="input-group">
                          <span class="input-group-addon" id="nmbrClnt">Nombre</span>
                            &nbsp;&nbsp;<b>${model.contrato.tclienteNombre}&nbsp;</b>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="input-group">
                          <span class="input-group-addon" id="cpcnClnt">Ocupación</span>
                            &nbsp;&nbsp;<b>${model.contrato.tclienteOcupacion}&nbsp;</b>
                        </div>
                    </div> 
                    <div class="col-xs-12">
                        <div class="input-group">
                          <span class="input-group-addon" id="dmclClnt">Originario</span>
                            &nbsp;&nbsp;<b>${model.contrato.tclienteOrigen}&nbsp;</b>
                        </div>
                    </div>
                </div>
        </div>              
            <div class="col-xs-12 lnbrk"></div>             
            <div class="col-xs-12">
                <h3 class="subheading">Salarios Únicos Profesionales</h3>
            </div> 
            <div class="row">
                <c:forEach items="${model.contrato.salarioUnicoProfesionalList}" var="sup">
                    <div class="col-xs-12 col-md-6">
                        <span><b>${sup.oficio}</b>&nbsp;$&nbsp;${sup.pesosDiarios}&nbsp;MXN</span>
                    </div>
                </c:forEach>
            </div>     
            <div class="col-xs-12 lnbrk"></div>             
            <div class="col-xs-12">
                <h3 class="subheading">Servicios contratados</h3>
            </div> 
            <div class="row">
                <c:forEach items="${model.contrato.catalogoDocumentalList}" var="cat">
                    <div class="col-xs-12 col-md-4">
                        <span><b>${cat.nombreDocumento}</b>&nbsp;</span>
                    </div>
                </c:forEach>
            </div>
            <div class="col-xs-12 lnbrk"></div>
          <div class="col-xs-12 col-md-3">
              <div class="input-group">
                <span class="input-group-addon" id="dmclClnt">Contrato sin firmar</span>
                  <button type="button" class="btn btn-default redireccionarVentana toolTip" title="Descargar contrato sin firmas" value="${pageContext.request.contextPath}/sistema/contrato-contratista-cliente/${model.contrato.idContratoEmpresas}/pdf.htm">
                      <span class="glyphicon glyphicon-save-file" aria-hidden="true"></span>
                  </button>                      
              </div>            
          </div>
            <c:if test="${(model.contrato.urlDocumento==null)}">
                <div class="col-xs-12 col-md-9">
                    <div class="input-group">
                      <input  type="file" class="filestyle" class="form-control" placeholder="Campo requerido" aria-describedby="rchv" name="contratoPDF" required title="Adjuntar archivo" id="rchvID" accept=".pdf"
                                    data-buttonText="&nbsp;&nbsp;Cargar contrato firmado" data-buttonName="btn-primary"  data-iconName="glyphicon glyphicon-paperclip" data-buttonBefore="true" data-placeholder="Sin archivo seleccionado">
                    </div>
                </div>
            </c:if>        
<div class="col-xs-12 lnbrk"></div>
        <div class="col-xs-12 col-sm-4">
            <div class="alert alert-warning" role="alert"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>&nbsp;&nbsp;&nbsp;Todos los campos son requeridos</div>
        </div>
        <div class="col-xs-12 col-sm-4">
            <button type="submit" class="form-control btn btn-success btn-lg center-btn btn-helper">
                <b>Guardar</b>
            </button>
        </div>
        <div class="col-xs-12 col-sm-4">            
        <button type="reset" class="form-control btn btn-info btn-lg center-btn btn-helper redireccionar" value="${pageContext.request.contextPath}/sistema/contratos-generados-contratista-cliente.htm">
              <b>Cancelar</b>
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
